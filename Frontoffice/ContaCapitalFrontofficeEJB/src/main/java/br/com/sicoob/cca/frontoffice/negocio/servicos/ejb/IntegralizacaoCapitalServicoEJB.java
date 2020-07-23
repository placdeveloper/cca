package br.com.sicoob.cca.frontoffice.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.joda.time.LocalDate;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.Resultado;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.enums.EnumCanalIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoLote;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoParcelamento;
import br.com.sicoob.cca.frontoffice.negocio.dto.EntradaIntegralizacaoCapitalDTO;
import br.com.sicoob.cca.frontoffice.negocio.dto.SaidaIntegralizacaoDTO;
import br.com.sicoob.cca.frontoffice.negocio.excecao.ContaCapitalFrontofficeNegocioException;
import br.com.sicoob.cca.frontoffice.negocio.servicos.IntegralizacaoCapitalServico;
import br.com.sicoob.cca.frontoffice.negocio.servicos.interfaces.IntegralizacaoCapitalServicoLocal;
import br.com.sicoob.cca.frontoffice.negocio.util.EnumTipoAgendamentoCCA;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.cca.frontoffice.negocio.util.RetornoSRTBHelper;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoIntegralizacaoExternaServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalExternoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.ContaCorrenteIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.GenIntIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ParcelamentoCCALegadoServicoLocal;

/**
 * Serviço para integralização de capital nos canais de atendimento
 * Nome da transação: CCAINT
 * @author Nairon.Silva
 */
@Stateless
@Local({ IntegralizacaoCapitalServicoLocal.class })
@Remote ({ Transacao.class })
public class IntegralizacaoCapitalServicoEJB extends ContaCapitalFrontofficeServicoEJB<EntradaIntegralizacaoCapitalDTO, SaidaIntegralizacaoDTO> implements IntegralizacaoCapitalServico {

	private static final Integer QTD_MAX_MESES = 24;
	
	private static final String NOME_TRANSACAO = "CCAINT";
	
	@EJB
	private ContaCorrenteIntegracaoServicoLocal contaCorrenteIntegracaoServico;

	@EJB
	private LancamentoIntegralizacaoExternaServicoLocal lancamentoIntegralizacaoExternaServico;
	
	@EJB
	private ParcelamentoContaCapitalExternoServicoLocal parcelamentoContaCapitalExternoServico;
	
	@EJB
	private ParcelamentoCCALegadoServicoLocal parcelamentoCCALegadoServico;
	
	@EJB
	private GenIntIntegracaoServicoLocal genIntIntegracaoServico;
	
	@Override
	protected RetornoTransacaoObjeto executarTransacao(EntradaIntegralizacaoCapitalDTO dto, Mensagem mensagem) throws BancoobException, ExcecaoTransacao {
		RetornoTransacaoObjeto retornoTransacao;
		if (dto.isAgendamentoNestaData()) {
			retornoTransacao = executarTransacaoNestaData(dto);
		} else {
			retornoTransacao = executarTransacaoProgramada(dto);
		}
		return retornoTransacao;
	}

	/**
	 * Executa a transacao de forma programada, criando parcelamentos.
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	private RetornoTransacaoObjeto executarTransacaoProgramada(EntradaIntegralizacaoCapitalDTO dto) throws BancoobException {
		getLogger().info("executarTransacaoProgramada");
		validarTransacaoProgramada(dto);
		getLogger().info("Incluindo lancamento");
		lancamentoIntegralizacaoExternaServico.incluir(montarSubscricaoParcelamentoCapitalDTO(dto));
		getLogger().info("Incluindo parcelamentos");
		parcelamentoContaCapitalExternoServico.incluirParcelamento(montarParcelasDTO(dto));
		return criarRetornoTransacao(montarResultado(dto));
	}

	/**
	 * Valida as regras de negócio para transações programadas.
	 * @param dto
	 * @throws BancoobException 
	 */
	private void validarTransacaoProgramada(EntradaIntegralizacaoCapitalDTO dto) throws BancoobException {
		validarExistenciaDeParcelasEmAberto(dto);
		validarDiaDebito(dto);
		validarQtdMeses(dto);
	}

	private void validarExistenciaDeParcelasEmAberto(EntradaIntegralizacaoCapitalDTO dto) throws BancoobException {
		if (parcelamentoCCALegadoServico.verificarParcelasEmAbertoPelosCanais(dto.getNumCooperativa(), dto.getNumMatricula())) {
			throw new ContaCapitalFrontofficeNegocioException("msg.integralizacao.validacao.parcelasEmAberto");
		}
	}

	private void validarDiaDebito(EntradaIntegralizacaoCapitalDTO dto) throws ContaCapitalFrontofficeNegocioException {
		final int limiteInferior = 1;
		final int limiteSuperior = 31;
		int diaDebito = dto.getDiaDebito().intValue();
		if (diaDebito < limiteInferior || diaDebito > limiteSuperior) {
			throw new ContaCapitalFrontofficeNegocioException("msg.integralizacao.validacao.diaDebito");
		}
	}

	private void validarQtdMeses(EntradaIntegralizacaoCapitalDTO dto) throws ContaCapitalFrontofficeNegocioException {
		if (dto.getQtdMeses() > QTD_MAX_MESES) {
			throw new ContaCapitalFrontofficeNegocioException("msg.integralizacao.validacao.meses");
		}
	}

	/**
	 * Executa a transação de forma imediata (nesta data).
	 * Inclui na CCA a subscrição e integralização e debita a CCO (as validacoes de saldo e conta bloq/enc sao feitas pelo CCO).
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	private RetornoTransacaoObjeto executarTransacaoNestaData(EntradaIntegralizacaoCapitalDTO dto) throws BancoobException {
		getLogger().info("executarTransacaoNestaData");
		getLogger().info("[integracao cco] - Incluindo lancamento em CCO");
		gravarLancamentoCco(dto);
		getLogger().info("Incluindo lancamentos em CCA");
		lancamentoIntegralizacaoExternaServico.incluir(montarIntegralizacaoCapitalDTO(dto, EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_SUBSC));
		lancamentoIntegralizacaoExternaServico.incluir(montarIntegralizacaoCapitalDTO(dto, EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_CONTA));
		getLogger().info("Lancamentos incluidos");
		return criarRetornoTransacao(montarResultado(dto));
	}

	/**
	 * Grava os lançamentos de débito em CCO
	 * @param dto
	 * @throws BancoobException
	 */
	private void gravarLancamentoCco(EntradaIntegralizacaoCapitalDTO dto) throws BancoobException {
		
		LancamentoContaCorrenteIntegracaoDTO lancDtoCco = montarLancamentoCco(dto);
		LancamentoContaCorrenteIntegracaoRetDTO lancDtoCcoRet = (LancamentoContaCorrenteIntegracaoRetDTO) 
				contaCorrenteIntegracaoServico.gravarLancamentosIntegracao(lancDtoCco);

		tratarExcecaoCco(lancDtoCcoRet);
	}

	private void tratarExcecaoCco(LancamentoContaCorrenteIntegracaoRetDTO lancDtoCcoRet) throws ContaCapitalFrontofficeNegocioException {
		if (lancDtoCcoRet.getCodRetorno() == 0){
			if (lancDtoCcoRet.getMensagem() != null && lancDtoCcoRet.getMensagem().trim().length() > 0) {
				if (lancDtoCcoRet.getMensagem().toUpperCase().contains("ENCERRADA") || 
						lancDtoCcoRet.getMensagem().toUpperCase().contains("BLOQUEADA")) {
					throw new ContaCapitalFrontofficeNegocioException("msg.integralizacao.validacao.conta.encerrada.bloqueada");
				}
				if ((lancDtoCcoRet.getMensagem().toUpperCase().contains("SALDO") && lancDtoCcoRet.getMensagem().toUpperCase().contains("INSUFICIENTE"))
						|| lancDtoCcoRet.getMensagem().toUpperCase().contains("VALOR DO LANÇAMENTO SUPERIOR")) {
					throw new ContaCapitalFrontofficeNegocioException("msg.integralizacao.validacao.saldo.indisponivel");
				}
				throw new ContaCapitalFrontofficeNegocioException("msg.integracao.cco.erro", lancDtoCcoRet.getMensagem());
			} else {
				throw new ContaCapitalFrontofficeNegocioException("msg.integracao.cco.erroGenerico");
			}
		}
	}
	
	private LancamentoContaCorrenteIntegracaoDTO montarLancamentoCco(EntradaIntegralizacaoCapitalDTO dto) throws BancoobException{
		LancamentoContaCorrenteIntegracaoDTO lancDtoCco = new LancamentoContaCorrenteIntegracaoDTO();
		
		lancDtoCco.setDataLote(obterDataLote(dto.getIdInstituicao()));
		lancDtoCco.setBolConsideraLimite(false);
		lancDtoCco.setNumLoteLanc(ContaCapitalConstantes.COD_LOTE_PARC_AVISTA_CCA);		
		lancDtoCco.setDescNumDocumento(dto.getNumMatricula().toString());
		lancDtoCco.setNumContaCorrente(dto.getNumContaCorrente().longValue());
		lancDtoCco.setValorLanc(dto.getValorLancamento());			
		lancDtoCco.setIdProduto(ContaCapitalConstantes.PRODUTO_CONTA_CORRENTE);
		lancDtoCco.setIdTipoHistoricoLanc(ContaCapitalConstantes.COD_HIST_LANC_CCO);
		lancDtoCco.setIdProdutoEstorno(null);
		lancDtoCco.setIdTipoHistoricoEstorno(null);
		lancDtoCco.setIdUsuarioResp(null); 
		lancDtoCco.setIdAplicativo(1); // TODO SubscricaoContaCapitalServicoEJB.montarLancamentoCco hardcoded
		lancDtoCco.setIdInstituicao(dto.getIdInstituicao());
		lancDtoCco.setBolVerificaContaAnt(true);
		lancDtoCco.setBolVerificaSaldo(true);			
		lancDtoCco.setCodOrigemLote(2); // TODO SubscricaoContaCapitalServicoEJB.montarLancamentoCco hardcoded
		lancDtoCco.setDescInfComplementar(null);		
				
		return lancDtoCco;	
	}

	/**
	 * Monta as parcelas para a transação programada de acordo com os parâmetros de entrada.
	 * @param dto
	 * @return
	 * @throws BancoobException 
	 */
	private List<ParcelamentoCapitalDTO> montarParcelasDTO(EntradaIntegralizacaoCapitalDTO dto) throws BancoobException {
		List<ParcelamentoCapitalDTO> parcelas = new ArrayList<ParcelamentoCapitalDTO>();
		DateTimeDB dataVencimento = null;
		for (int numParcela = 0; numParcela < dto.getQtdMeses(); numParcela++) {
			dataVencimento = criarDataVencimento(dto, numParcela);
			ParcelamentoCapitalDTO parcela = new ParcelamentoCapitalDTO();
			parcela.setDataVencimento(dataVencimento); 
			parcela.setIdInstituicao(dto.getIdInstituicao());
			parcela.setIdSituacaoParcelamento(EnumSituacaoParcelamento.COD_PARCELA_GERADA.getCodigo().shortValue());
			parcela.setIdTipoIntegralizacao(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo().shortValue());
			parcela.setIdTipoParcelamento(EnumTipoParcelamento.COD_TIPO_PARCELAMENTO_INTEGRAL.getCodigo().shortValue());
			parcela.setNumContaCapital(dto.getNumMatricula());
			parcela.setNumContaCorrente(dto.getNumContaCorrente().longValue());
			parcela.setNumParcela(Integer.valueOf(numParcela).shortValue());
			parcela.setValorParcela(dto.getValorLancamento());
			parcela.setCodCanal(dto.getCodigoCanal());
			parcelas.add(parcela);
		}
		return parcelas;
	}
	
	/**
	 * Cria a data de vencimento considerando as regras:
	 * Se o dia do debito for anterior a data atual, entao a primeira parcela vai para o mes seguinte, caso contrario,
	 * a primeira parcela ja comeca no mes corrente.
	 * Alem disso, caso o dia do debito nao exista no mes em questao, a parcela vai para o primeiro dia do mes seguinte.
	 * Por fim, a data de vencimento deve pular dias nao uteis.
	 * @param dto
	 * @param numParcela
	 * @return
	 * @throws BancoobException
	 */
	private DateTimeDB criarDataVencimento(EntradaIntegralizacaoCapitalDTO dto, int numParcela) throws BancoobException {
		LocalDate hoje = LocalDate.now();
		int diaDebito = dto.getDiaDebito();
		boolean deslocaPrimeiraData = hoje.getDayOfMonth() > diaDebito; 
		LocalDate data = montarDataConsiderandoFimDeMes(hoje.plusMonths(numParcela), diaDebito);
		if (deslocaPrimeiraData) {
			data = data.plusMonths(1);
		}
		DateTimeDB dataVencimento = new DateTimeDB(data.toDate().getTime());
		return montarDataConsiderandoDiaUtil(dto, dataVencimento);
	}
	
	/**
	 * Monta a data verificando se o dia do debito existe no mes. Se existir, retorna a data no dia do debito.
	 * Se NAO existir, entao retorna o primeiro dia do mes seguinte.
	 * @param data
	 * @param diaDebito
	 * @return
	 */
	private LocalDate montarDataConsiderandoFimDeMes(LocalDate data, int diaDebito) {
		final int um = 1;
		int ultimoDiaDoMes = data.dayOfMonth().getMaximumValue();
		if (diaDebito > ultimoDiaDoMes) {
			return data.withDayOfMonth(um).plusMonths(um);
		}
		return data.withDayOfMonth(diaDebito);
	}
	
	/**
	 * Monta a data considerando dia util.
	 * @param dto
	 * @param dataVencimento
	 * @return
	 * @throws BancoobException
	 */
	private DateTimeDB montarDataConsiderandoDiaUtil(EntradaIntegralizacaoCapitalDTO dto, DateTimeDB dataVencimento) throws BancoobException{
		if (!genIntIntegracaoServico.verificarDiaUtil(dto.getIdInstituicao(), dataVencimento)){
			return new DateTimeDB(genIntIntegracaoServico.recuperarProximoDiaUtil(
					dto.getIdInstituicao(), dataVencimento).getTime());
		}
		return dataVencimento;
	}

	/**
	 * Monta o DTO de integralização de acordo com os parâmetros de entrada do serviço e o tipo de histórico
	 * (subscrição / integralização)
	 * @param dto
	 * @param tipoHistoricoCCA
	 * @return
	 */
	private IntegralizacaoCapitalDTO montarIntegralizacaoCapitalDTO(EntradaIntegralizacaoCapitalDTO dto,
			EnumTipoHistoricoCCA tipoHistoricoCCA) {
		IntegralizacaoCapitalDTO integralizacaoCapitalDTO = new IntegralizacaoCapitalDTO();
		integralizacaoCapitalDTO.setIdInstituicao(dto.getIdInstituicao());
		integralizacaoCapitalDTO.setNumMatricula(dto.getNumMatricula());
		integralizacaoCapitalDTO.setIdTipoHistoricoLanc(tipoHistoricoCCA.getCodigo()); 
		integralizacaoCapitalDTO.setValorLancamento(dto.getValorLancamento());
		if (EnumTipoLote.COD_LOTE_CCA_CANAIS_ATENDIMENTO.isVigente()) {
			integralizacaoCapitalDTO.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_CANAIS_ATENDIMENTO.getCodigo());
		} else {
			integralizacaoCapitalDTO.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_PARC_AVISTA.getCodigo());
		}
		integralizacaoCapitalDTO.setIdOperacaoOrigem(NOME_TRANSACAO + "| DIA | CodCanal: " + dto.getCodigoCanal());
		return integralizacaoCapitalDTO;
	}
	
	/**
	 * Monta o DTO de integralização de subscricao para o parcelamento.
	 * (subscrição / integralização)
	 * @param dto
	 * @return
	 */
	private IntegralizacaoCapitalDTO montarSubscricaoParcelamentoCapitalDTO(EntradaIntegralizacaoCapitalDTO dto) {
		IntegralizacaoCapitalDTO integralizacaoCapitalDTO = new IntegralizacaoCapitalDTO();
		integralizacaoCapitalDTO.setIdInstituicao(dto.getIdInstituicao());
		integralizacaoCapitalDTO.setNumMatricula(dto.getNumMatricula());
		integralizacaoCapitalDTO.setIdTipoHistoricoLanc(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_SUBSC.getCodigo()); 
		integralizacaoCapitalDTO.setValorLancamento(dto.getValorLancamento().multiply(BigDecimal.valueOf(dto.getQtdMeses())));
		if (EnumTipoLote.COD_LOTE_CCA_CANAIS_ATENDIMENTO.isVigente()) {
			integralizacaoCapitalDTO.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_CANAIS_ATENDIMENTO.getCodigo());
		} else {
			integralizacaoCapitalDTO.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_PARC_AVISTA.getCodigo());
		}
		integralizacaoCapitalDTO.setIdOperacaoOrigem(NOME_TRANSACAO + " | PARC | CodCanal: " + dto.getCodigoCanal());
		return integralizacaoCapitalDTO;
	}
	
	/**
	 * Monta o resultado para a saída do serviço.
	 * @param dto
	 * @return
	 */
	private Resultado<SaidaIntegralizacaoDTO> montarResultado(EntradaIntegralizacaoCapitalDTO dto) {
		Resultado<SaidaIntegralizacaoDTO> resultado = criarResultado();
		resultado.add(montarSaidaDTO(dto));
		return resultado;
	}

	/**
	 * Monta o DTO de saída para o resultado do serviço.
	 * @param dtoEntrada
	 * @return
	 */
	private SaidaIntegralizacaoDTO montarSaidaDTO(EntradaIntegralizacaoCapitalDTO dtoEntrada) {
		SaidaIntegralizacaoDTO dto = new SaidaIntegralizacaoDTO();
		dto.setDataTransacao(RetornoSRTBHelper.formatarData(new Date()));
		dto.setValorLancamento(RetornoSRTBHelper.formatarValor(dtoEntrada.getValorLancamento()));
		dto.setTipoAgendamento(dtoEntrada.getTipoAgendamento().getCodigo().toString());
		dto.setQtdMeses(dtoEntrada.getQtdMeses() == null ? "" : dtoEntrada.getQtdMeses().toString());
		dto.setDiaDebito(dtoEntrada.getDiaDebito() == null ? "" : dtoEntrada.getDiaDebito().toString());
		dto.setNumCooperativa(dtoEntrada.getNumCooperativa().toString());
		dto.setNomeCooperativa(dtoEntrada.getNomeCooperativa());
		dto.setNumContaCorrente(""+dtoEntrada.getNumContaCorrente().intValue());
		dto.setNomePessoa(dtoEntrada.getNomePessoa());
		return dto;
	}
	
	@Override
	protected EntradaIntegralizacaoCapitalDTO criarDTO(Mensagem mensagem) throws BancoobException {
		EntradaIntegralizacaoCapitalDTO dto = new EntradaIntegralizacaoCapitalDTO();

		dto.setTipoAgendamento(EnumTipoAgendamentoCCA.getTipoPorCodigo(
				ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.TIPO_AGENDAMENTO, Number.class).intValue()));
		dto.setValorLancamento(ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.VALOR, BigDecimal.class));
		dto.setNumCpfCnpj(ContaCapitalUtil.retirarMascaraCpfCnpj(ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.CPF_CNPJ, String.class)));
		dto.setNumContaCorrente(ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.NUMERO_CONTA_CORRENTE, Number.class).doubleValue());
		dto.setIdInstituicao(ParametroSRTBCCA.extrairIdInstituicao(mensagem));
		int codCanal = Short.valueOf(mensagem.getCodigoCanal()).intValue();
		if (codCanal == 0) {
			codCanal = EnumCanalIntegralizacao.FRONTOFFICE_IB.getCodigo();
		}
		dto.setCodigoCanal(codCanal);
		if (!dto.isAgendamentoNestaData()) {
			dto.setQtdMeses(ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.MESES, Number.class).intValue());
			dto.setDiaDebito(ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.DIA_DEBITO, Number.class).intValue());
		}
		
		PessoaIntegracaoDTO pessoaIntegracaoDTO = obterPessoaIntegracaoPorCpfCnpjInstituicao(dto.getNumCpfCnpj(), dto.getIdInstituicao());
		dto.setIdPessoa(pessoaIntegracaoDTO.getIdPessoa());
		dto.setNomePessoa(pessoaIntegracaoDTO.getNomeCompleto());
		
		InstituicaoIntegracaoDTO instituicao = obterInstituicaoIntegracaoDTO(dto.getIdInstituicao());
		dto.setNumCooperativa(Integer.valueOf(instituicao.getNumero()));
		dto.setNomeCooperativa(instituicao.getNomeInstituicao());
		ContaCapitalLegado contaCapitalLegado = obterContaCapitalClienteLegado(dto.getNumCooperativa(), pessoaIntegracaoDTO.getIdPessoaLegado());
		dto.setNumMatricula(contaCapitalLegado.getNumMatricula());
		
		return dto;
	}

	@Override
	protected String getNomeTransacao() {
		return NOME_TRANSACAO;
	}
	
}

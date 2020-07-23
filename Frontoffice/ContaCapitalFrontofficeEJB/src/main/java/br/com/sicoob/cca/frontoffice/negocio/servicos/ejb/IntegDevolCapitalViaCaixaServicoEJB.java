package br.com.sicoob.cca.frontoffice.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.Resultado;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.cca.cadastro.negocio.dto.ContaCapitalResumoDTO;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoLote;
import br.com.sicoob.cca.frontoffice.negocio.dto.EntradaIntegDevolCapitalViaCaixaDTO;
import br.com.sicoob.cca.frontoffice.negocio.dto.SaidaIntegDevolCapitalViaCaixaCabecalhoDTO;
import br.com.sicoob.cca.frontoffice.negocio.dto.SaidaIntegDevolCapitalViaCaixaDTO;
import br.com.sicoob.cca.frontoffice.negocio.excecao.ContaCapitalFrontofficeNegocioException;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.cca.frontoffice.negocio.util.RetornoSRTBHelper;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoIntegralizacaoExternaServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalExternoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;

/**
 * Serviço de integralização e devolução de parcelamentos via caixa.
 * Nome da transação: CCAINTEGDEVOLCAIXA
 * @author Nairon.Silva
 */
@Stateless
@Remote({ Transacao.class })
public class IntegDevolCapitalViaCaixaServicoEJB extends ContaCapitalFrontofficeServicoEJB<EntradaIntegDevolCapitalViaCaixaDTO, SaidaIntegDevolCapitalViaCaixaDTO> implements Transacao {

	private static final String DELIMITADOR_IDENTIFICADOR = "\\|";
	
	private static final String NOME_TRANSACAO = "CCAINTEGDEVOLCAIXA";
	
	@EJB
	private ParcelamentoContaCapitalExternoServicoLocal parcelamentoContaCapitalExternoServico;
	
	@EJB
	private LancamentoIntegralizacaoExternaServicoLocal lancamentoIntegralizacaoExternaServico;
	
	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;

	@Override
	protected String getNomeTransacao() {
		return NOME_TRANSACAO;
	}

	@Override
	protected RetornoTransacaoObjeto executarTransacao(EntradaIntegDevolCapitalViaCaixaDTO dto, Mensagem mensagem) throws BancoobException, ExcecaoTransacao {
		
		List<ParcelamentoRenDTO> parcelamentos = criarParcelamentos(dto);
		getLogger().info("Atualizando parcelamentos");
		parcelamentoContaCapitalExternoServico.atualizarParcelamentos(dto.getIdInstituicao(), parcelamentos, 
				EnumSituacaoParcelamento.COD_PARCELA_PAGA_VIA_CAIXA.getCodigo());
		
		getLogger().info("Incluindo lançamentos");
		for (ParcelamentoRenDTO parcDTO : parcelamentos) {
			String descNumDocumento = montarDescNumDocumento(dto, parcDTO);
			if (dto.isTipoParcelamentoIntegralizacao()) {
				lancamentoIntegralizacaoExternaServico.incluir(montarLancamentoDTO(dto, parcDTO, descNumDocumento, EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_CAIXA));
			} else if (dto.isTipoParcelamentoDevolucao()) {
				lancamentoIntegralizacaoExternaServico.incluir(montarLancamentoDTO(dto, parcDTO, descNumDocumento, EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_DEV_CAIXA));
			}
		}
		
		return criarRetornoTransacao(montarResultados(dto, parcelamentos));
	}

	private List<Resultado<? extends BancoobDto>> montarResultados(EntradaIntegDevolCapitalViaCaixaDTO dto, List<ParcelamentoRenDTO> parcelamentos) {
		List<Resultado<? extends BancoobDto>> resultados = new ArrayList<Resultado<? extends BancoobDto>>();
		
		Resultado<SaidaIntegDevolCapitalViaCaixaCabecalhoDTO> resultadoInfos = new Resultado<SaidaIntegDevolCapitalViaCaixaCabecalhoDTO>();
		resultadoInfos.add(montarSaidaCabecalho(dto));
		
		Resultado<SaidaIntegDevolCapitalViaCaixaDTO> resultadoParcelas = criarResultado();
		for (ParcelamentoRenDTO parcDTO : parcelamentos) {
			resultadoParcelas.add(montarSaidaDTO(parcDTO));
		}
		
		resultados.add(resultadoInfos);
		resultados.add(resultadoParcelas);
		
		return resultados;
	}

	private SaidaIntegDevolCapitalViaCaixaDTO montarSaidaDTO(ParcelamentoRenDTO parcDTO) {
		final int indice = 2;
		SaidaIntegDevolCapitalViaCaixaDTO dto = new SaidaIntegDevolCapitalViaCaixaDTO();
		dto.setIndice(indice);
		dto.setNumParcelamento(parcDTO.getNumParcelamento().intValue());
		dto.setNumParcela(parcDTO.getNumParcela().intValue());
		dto.setValorParcela(RetornoSRTBHelper.formatarValor(parcDTO.getValorParcela()));
		dto.setDataVencimento(RetornoSRTBHelper.formatarData(parcDTO.getDataVencimento()));
		return dto;
	}

	private SaidaIntegDevolCapitalViaCaixaCabecalhoDTO montarSaidaCabecalho(EntradaIntegDevolCapitalViaCaixaDTO dto) {
		final int indice = 1;
		SaidaIntegDevolCapitalViaCaixaCabecalhoDTO saida = new SaidaIntegDevolCapitalViaCaixaCabecalhoDTO();
		saida.setIndice(indice);
		saida.setNumCooperativa(dto.getNumCooperativa());
		saida.setNomeCooperativa(dto.getNomeCooperativa());
		saida.setCpfCnpj(dto.getCpfCnpj());
		saida.setNomePessoa(dto.getNomePessoa());
		saida.setNumContaCapital(dto.getNumContaCapital());
		if (dto.isTipoParcelamentoIntegralizacao()) {
			saida.setOperacao("Integralização de Capital");
		} else if (dto.isTipoParcelamentoDevolucao()) {
			saida.setOperacao("Devolução de Capital");
		}
		return saida;
	}

	private String montarDescNumDocumento(EntradaIntegDevolCapitalViaCaixaDTO dto, ParcelamentoRenDTO parcDTO) {
		final String caracter = "0";
		final int tamParcelamento = 4;
		final int tamParcela = 3;
		StringBuilder desc = new StringBuilder();
		if (dto.isTipoParcelamentoIntegralizacao()) {
			desc.append("000");
		} else if (dto.isTipoParcelamentoDevolucao()) {
			if (EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo().equals(dto.getIdSituacaoContaCapital())) {
				desc.append("001");
			} else {
				desc.append("002");
			}
		}
		desc.append(ContaCapitalUtil.completaComCaracterAEsquerda(parcDTO.getNumParcelamento().toString(), caracter, tamParcelamento))
			.append(ContaCapitalUtil.completaComCaracterAEsquerda(parcDTO.getNumParcela().toString(), caracter, tamParcela));
		return desc.toString();
	}

	private IntegralizacaoCapitalDTO montarLancamentoDTO(EntradaIntegDevolCapitalViaCaixaDTO dto, ParcelamentoRenDTO parcDTO, 
			String descNumDocumento, EnumTipoHistoricoCCA tipoHistorico) {
		IntegralizacaoCapitalDTO integralizacaoCapitalDTO = new IntegralizacaoCapitalDTO();
		integralizacaoCapitalDTO.setIdInstituicao(dto.getIdInstituicao());
		integralizacaoCapitalDTO.setNumMatricula(dto.getNumContaCapital());
		integralizacaoCapitalDTO.setIdTipoHistoricoLanc(tipoHistorico.getCodigo()); 
		integralizacaoCapitalDTO.setValorLancamento(parcDTO.getValorParcela());
		integralizacaoCapitalDTO.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_PARC_APRAZO.getCodigo());
		integralizacaoCapitalDTO.setIdOperacaoOrigem(NOME_TRANSACAO);
		integralizacaoCapitalDTO.setDescNumDocumento(descNumDocumento);
		integralizacaoCapitalDTO.setIdUsuario(dto.getUsuario());
		integralizacaoCapitalDTO.setIgnoraValidacaoContaCapitalAtiva(true);
		return integralizacaoCapitalDTO;
	}

	private List<ParcelamentoRenDTO> criarParcelamentos(EntradaIntegDevolCapitalViaCaixaDTO dto) throws BancoobException {
		String[] identificadoresArray = dto.getIdentificador().split(DELIMITADOR_IDENTIFICADOR);
		List<ParcelamentoRenDTO> parcelamentos = new ArrayList<ParcelamentoRenDTO>();
		for (String identificador : identificadoresArray) {
			String[] identificadorArray = identificador.split("-");
			int i=2;
			Short numParcelamento = Short.valueOf(identificadorArray[i++]);
			Short numParcela = Short.valueOf(identificadorArray[i++]);
			Number numValorParcela = RetornoSRTBHelper.recuperarValor(identificadorArray[i++]);
			if (numValorParcela == null) {
				throw new ContaCapitalFrontofficeNegocioException("msg.integdevolviacaixa.validacao.identificador.valor");
			}
			BigDecimal valorParcela = BigDecimal.valueOf(numValorParcela.doubleValue());
			ParcelamentoRenDTO parcelamento = pesquisarParcelamento(dto, numParcelamento, numParcela);
			validarParcelamento(parcelamento, valorParcela);
			parcelamentos.add(parcelamento);
		}
		return parcelamentos;
	}

	private void validarParcelamento(ParcelamentoRenDTO parcelamento, BigDecimal valorParcela) throws ContaCapitalFrontofficeNegocioException {
		Integer situacaoParcelamento = parcelamento.getIdSituacaoParcelamento().intValue();
		Integer tipoIntegralizacao = parcelamento.getIdTipoInteg().intValue();
		if (!EnumSituacaoParcelamento.COD_PARCELA_GERADA.getCodigo().equals(situacaoParcelamento)) {
			throw new ContaCapitalFrontofficeNegocioException("msg.integdevolviacaixa.validacao.situacao");
		}
		if (!EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getCodigo().equals(tipoIntegralizacao)) {
			throw new ContaCapitalFrontofficeNegocioException("msg.integdevolviacaixa.validacao.tipointeg");
		}
		if (valorParcela.compareTo(parcelamento.getValorParcela()) != 0) {
			throw new ContaCapitalFrontofficeNegocioException("msg.integdevolviacaixa.validacao.valorparcela", valorParcela, parcelamento.getValorParcela());
		}
		if (parcelamento.getDataVencimento().after(new Date())) {
			throw new ContaCapitalFrontofficeNegocioException("msg.integdevolviacaixa.validacao.datavencimento");
		}
	}

	@Override
	protected EntradaIntegDevolCapitalViaCaixaDTO criarDTO(Mensagem mensagem) throws BancoobException {
		EntradaIntegDevolCapitalViaCaixaDTO dto = new EntradaIntegDevolCapitalViaCaixaDTO();
		dto.setIdInstituicao(ParametroSRTBCCA.extrairIdInstituicao(mensagem));
		dto.setUsuario(ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.ID_USUARIO, String.class));
		dto.setIdentificador(ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.IDENTIFICADOR, String.class));
		
		String[] identificadoresArray = dto.getIdentificador().split(DELIMITADOR_IDENTIFICADOR);
		if (identificadoresArray.length == 0) {
			throw new ContaCapitalFrontofficeNegocioException("msg.integdevolviacaixa.validacao.identificadores");
		}
		String primeiroIdentificador = identificadoresArray[0];
		String[] identificadorArray = primeiroIdentificador.split("-");
		Integer tipoParcelamento = Integer.valueOf(identificadorArray[0]);
		Integer numContaCapital = Integer.valueOf(identificadorArray[1]);
		dto.setTipoParcelamento(tipoParcelamento);
		dto.setNumContaCapital(numContaCapital);
		
		ContaCapitalResumoDTO cca = contaCapitalServico.obterResumo(dto.getIdInstituicao(), dto.getNumContaCapital());
		dto.setIdSituacaoContaCapital(cca.getIdSituacaoContaCapital());
		
		PessoaIntegracaoDTO pessoaIntegracaoDTO = obterPessoaInstituicao(cca.getIdPessoa(), dto.getIdInstituicao());
		dto.setNomePessoa(pessoaIntegracaoDTO.getNomeCompleto());
		dto.setCpfCnpj(pessoaIntegracaoDTO.getCpfCnpj());
		
		InstituicaoIntegracaoDTO instituicao = obterInstituicaoIntegracaoDTO(dto.getIdInstituicao());
		dto.setNumCooperativa(Integer.valueOf(instituicao.getNumero()));
		dto.setNomeCooperativa(instituicao.getNomeInstituicao());
		
		return dto;
	}
	
	private ParcelamentoRenDTO pesquisarParcelamento(EntradaIntegDevolCapitalViaCaixaDTO dto, Short numParcelamento, Short numParcela) throws BancoobException {
		ParcelamentoCapitalDTO filtro = new ParcelamentoCapitalDTO();
		filtro.setIdInstituicao(dto.getIdInstituicao());
		filtro.setIdTipoParcelamento(dto.getTipoParcelamento().shortValue());
		filtro.setNumContaCapital(dto.getNumContaCapital());
		filtro.setNumParcelamento(numParcelamento);
		filtro.setNumParcela(numParcela);
		List<ParcelamentoRenDTO> parcelamentos = parcelamentoContaCapitalExternoServico.pesquisarParcelamentosEmAbertoViaCaixa(filtro);
		if (parcelamentos.isEmpty()) {
			throw new ContaCapitalFrontofficeNegocioException("msg.integdevolviacaixa.validacao.identificador.nao.encontrado");
		}
		return parcelamentos.get(0);
	}
	
}

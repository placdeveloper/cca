package br.com.sicoob.cca.frontoffice.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.Resultado;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoLote;
import br.com.sicoob.cca.frontoffice.negocio.dto.EntradaCancelamentoIntegralizacaoAgendadaDTO;
import br.com.sicoob.cca.frontoffice.negocio.dto.SaidaCancelamentoIntegralizacaoAgendadaDTO;
import br.com.sicoob.cca.frontoffice.negocio.dto.SaidaConteudoUnicoDTO;
import br.com.sicoob.cca.frontoffice.negocio.dto.SaidaInformacoesGeraisDTO;
import br.com.sicoob.cca.frontoffice.negocio.excecao.ContaCapitalFrontofficeNegocioException;
import br.com.sicoob.cca.frontoffice.negocio.servicos.CancelamentoIntegralizacaoAgendadaServico;
import br.com.sicoob.cca.frontoffice.negocio.servicos.interfaces.CancelamentoIntegralizacaoAgendadaServicoLocal;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.cca.frontoffice.negocio.util.ParcelamentoCCAPKHelper;
import br.com.sicoob.cca.frontoffice.negocio.util.RetornoSRTBHelper;
import br.com.sicoob.cca.movimentacao.negocio.dto.CancelamentoParcelamentoDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoIntegralizacaoExternaServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalExternoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegadoPK;

/**
 * Serviço de cancelamento de integralização agendada.
 * Nome da transação: CCACANCPARC
 * @author Nairon.Silva
 */
@Stateless
@Local({ CancelamentoIntegralizacaoAgendadaServicoLocal.class })
@Remote ({ Transacao.class })
public class CancelamentoIntegralizacaoAgendadaServicoEJB extends ContaCapitalFrontofficeServicoEJB<EntradaCancelamentoIntegralizacaoAgendadaDTO, SaidaCancelamentoIntegralizacaoAgendadaDTO> implements CancelamentoIntegralizacaoAgendadaServico {

	private static final String DELIMITADOR_IDENTIFICADOR = "\\|";
	
	private static final String NOME_TRANSACAO = "CCACANCPARC";
	
	@EJB
	private ParcelamentoContaCapitalExternoServicoLocal parcelamentoContaCapitalExternoServico;
	
	@EJB
	private LancamentoIntegralizacaoExternaServicoLocal lancamentoIntegralizacaoExternaServico;
	
	@Override
	protected RetornoTransacaoObjeto executarTransacao(EntradaCancelamentoIntegralizacaoAgendadaDTO dto, Mensagem mensagem) throws BancoobException, ExcecaoTransacao {
		
		List<CancelamentoParcelamentoDTO> dtosCancelamento = montarCancelamentoDTO(dto);
		getLogger().info("Cancelando parcelamentos");
		parcelamentoContaCapitalExternoServico.cancelarParcelamentos(dtosCancelamento);
		getLogger().info("Incluindo lançamentos de cancelamento");
		incluirLancamentos(mensagem, dto, dtosCancelamento);
		
		return criarRetornoTransacao(montarResultados(dto, dtosCancelamento));
	}

	private void incluirLancamentos(Mensagem mensagem, EntradaCancelamentoIntegralizacaoAgendadaDTO dtoEntrada, 
			List<CancelamentoParcelamentoDTO> dtosCancelamento) throws BancoobException {
		for (CancelamentoParcelamentoDTO dtoCancelamento : dtosCancelamento) {
			lancamentoIntegralizacaoExternaServico.incluir(montarDtoLancamento(mensagem, dtoEntrada, dtoCancelamento));
		}
	}

	private IntegralizacaoCapitalDTO montarDtoLancamento(Mensagem mensagem, EntradaCancelamentoIntegralizacaoAgendadaDTO dtoEntrada, 
			CancelamentoParcelamentoDTO dtoCancelamento) throws BancoobException {
		IntegralizacaoCapitalDTO dto = new IntegralizacaoCapitalDTO();
		
		dto.setIdInstituicao(dtoEntrada.getIdInstituicao());
		dto.setNumMatricula(dtoEntrada.getNumMatricula());
		dto.setIdTipoHistoricoLanc(ContaCapitalConstantes.COD_HISTORICO_CCA_CANCEL_SUBSCRICAO);
		dto.setValorLancamento(dtoCancelamento.getValorParcela());
		if (EnumTipoLote.COD_LOTE_CCA_CANAIS_ATENDIMENTO.isVigente()) {
			dto.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_CANAIS_ATENDIMENTO.getCodigo());
		} else {
			dto.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_PARC_AVISTA.getCodigo());
		}
		dto.setIdOperacaoOrigem(NOME_TRANSACAO + " | CodCanal: " + mensagem.getCodigoCanal());
		
		return dto;
	}

	private List<Resultado<? extends BancoobDto>> montarResultados(EntradaCancelamentoIntegralizacaoAgendadaDTO dtoEntrada, List<CancelamentoParcelamentoDTO> dtos) {
		List<Resultado<? extends BancoobDto>> resultados = new ArrayList<Resultado<? extends BancoobDto>>();
		
		Resultado<SaidaInformacoesGeraisDTO> resultadoInfos = new Resultado<SaidaInformacoesGeraisDTO>();
		resultadoInfos.add(montarSaidaInformacoesGeraisDTO(dtoEntrada));
		
		final int indiceData = 2;
		Resultado<SaidaConteudoUnicoDTO> resultadoDataCancelamento = new Resultado<SaidaConteudoUnicoDTO>();
		resultadoDataCancelamento.add(new SaidaConteudoUnicoDTO(indiceData, RetornoSRTBHelper.formatarData(dtos.get(0).getDataSituacao())));
		
		Resultado<SaidaCancelamentoIntegralizacaoAgendadaDTO> resultadoParcelasCanceladas = criarResultado();
		for (CancelamentoParcelamentoDTO dto : dtos) {
			resultadoParcelasCanceladas.add(montarSaidaDTO(dto));
		}
		
		resultados.add(resultadoInfos);
		resultados.add(resultadoDataCancelamento);
		resultados.add(resultadoParcelasCanceladas);
		
		return resultados;
	}

	private SaidaInformacoesGeraisDTO montarSaidaInformacoesGeraisDTO(EntradaCancelamentoIntegralizacaoAgendadaDTO dtoEntrada) {
		final int indice = 1;
		SaidaInformacoesGeraisDTO saida = new SaidaInformacoesGeraisDTO();
		saida.setIndice(indice);
		saida.setNomeCooperativa(dtoEntrada.getNomeCooperativa());
		saida.setNumCooperativa(dtoEntrada.getNumCooperativa().toString());
		saida.setNomePessoa(dtoEntrada.getNomePessoa());
		saida.setNumContaCorrente(""+dtoEntrada.getNumContaCorrente().intValue());
		return saida;
	}

	private SaidaCancelamentoIntegralizacaoAgendadaDTO montarSaidaDTO(CancelamentoParcelamentoDTO cancelamentoDTO) {
		final int indice = 3;
		SaidaCancelamentoIntegralizacaoAgendadaDTO dto = new SaidaCancelamentoIntegralizacaoAgendadaDTO();
		dto.setIndice(indice);
		dto.setValor(RetornoSRTBHelper.formatarValor(cancelamentoDTO.getValorParcela()));
		dto.setDataVencimento(RetornoSRTBHelper.formatarData(cancelamentoDTO.getDataVencimento()));
		return dto;
	}

	private List<CancelamentoParcelamentoDTO> montarCancelamentoDTO(EntradaCancelamentoIntegralizacaoAgendadaDTO dtoEntrada) {
		List<CancelamentoParcelamentoDTO> dtos = new ArrayList<CancelamentoParcelamentoDTO>();
		for (ParcelamentoCCALegadoPK parcelamentoCCALegadoPK : dtoEntrada.getParcelamentoPKs()) {
			CancelamentoParcelamentoDTO dto = new CancelamentoParcelamentoDTO();
			dto.setIdInstituicao(dtoEntrada.getIdInstituicao());
			dto.setNumParcela(parcelamentoCCALegadoPK.getNumParcela().shortValue());
			dto.setNumParcelamento(parcelamentoCCALegadoPK.getNumParcelamento().shortValue());
			dto.setIdTipoParcelamento(parcelamentoCCALegadoPK.getCodTipoParcelamento().shortValue());
			dto.setNumContaCapital(parcelamentoCCALegadoPK.getContaCapitalLegado().getNumMatricula());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	protected EntradaCancelamentoIntegralizacaoAgendadaDTO criarDTO(Mensagem mensagem) throws BancoobException {
		EntradaCancelamentoIntegralizacaoAgendadaDTO dto = new EntradaCancelamentoIntegralizacaoAgendadaDTO();
		
		dto.setNumCpfCnpj(ContaCapitalUtil.retirarMascaraCpfCnpj(ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.CPF_CNPJ, String.class)));
		dto.setNumContaCorrente(ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.NUMERO_CONTA_CORRENTE, Number.class).doubleValue());
		dto.setIdInstituicao(ParametroSRTBCCA.extrairIdInstituicao(mensagem));
		
		List<ParcelamentoCCALegadoPK> parcelamentoCCALegadoPKs = new ArrayList<ParcelamentoCCALegadoPK>();
		String pks = ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.IDENTIFICADOR, String.class);
		String[] pksArray = pks.split(DELIMITADOR_IDENTIFICADOR);
		if (pksArray.length == 0) {
			throw new ContaCapitalFrontofficeNegocioException("msg.cancelamento.validacao.pk");
		}
		for (String pk : pksArray) {
			parcelamentoCCALegadoPKs.add(ParcelamentoCCAPKHelper.toParcelamentoCCALegadoPK(pk));
		}
		dto.setParcelamentoPKs(parcelamentoCCALegadoPKs);
		
		PessoaIntegracaoDTO pessoaIntegracaoDTO = obterPessoaIntegracaoPorCpfCnpjInstituicao(dto.getNumCpfCnpj(), dto.getIdInstituicao());
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

package br.com.sicoob.cca.frontoffice.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.Resultado;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.frontoffice.negocio.dto.EntradaConsultaIntegralizacaoAgendadaDTO;
import br.com.sicoob.cca.frontoffice.negocio.dto.SaidaConsultaIntegralizacaoAgendadaDTO;
import br.com.sicoob.cca.frontoffice.negocio.servicos.ConsultaIntegralizacaoAgendadaServico;
import br.com.sicoob.cca.frontoffice.negocio.servicos.interfaces.ConsultaIntegralizacaoAgendadaServicoLocal;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.cca.frontoffice.negocio.util.ParcelamentoCCAPKHelper;
import br.com.sicoob.cca.frontoffice.negocio.util.RetornoSRTBHelper;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ParcelamentoCCALegadoServicoLocal;

/**
 * Serviço para consulta de integralizações agendadas (parcelamentos)
 * Nome da transação: CCACONSPARC
 * @author Nairon.Silva
 */
@Stateless
@Local({ ConsultaIntegralizacaoAgendadaServicoLocal.class })
@Remote ({ Transacao.class })
public class ConsultaIntegralizacaoAgendadaServicoEJB extends ContaCapitalFrontofficeServicoEJB<EntradaConsultaIntegralizacaoAgendadaDTO, SaidaConsultaIntegralizacaoAgendadaDTO> implements ConsultaIntegralizacaoAgendadaServico {

	private static final String NOME_TRANSACAO = "CCACONSPARC";
	
	@EJB
	private ParcelamentoCCALegadoServicoLocal parcelamentoCCALegadoServico;
	
	@Override
	protected RetornoTransacaoObjeto executarTransacao(EntradaConsultaIntegralizacaoAgendadaDTO dto, Mensagem mensagem) throws BancoobException, ExcecaoTransacao {
		List<ParcelamentoCCALegado> parcelamentos = parcelamentoCCALegadoServico.obterParcelasEmAbertoPelosCanais(
				dto.getCodigoCooperativa(), dto.getNumMatricula());
		return criarRetornoTransacao(montarResultado(parcelamentos));
	}
	
	private Resultado<SaidaConsultaIntegralizacaoAgendadaDTO> montarResultado(List<ParcelamentoCCALegado> parcelamentos) {
		Resultado<SaidaConsultaIntegralizacaoAgendadaDTO> resultado = criarResultado();
		for (ParcelamentoCCALegado parcelamento : parcelamentos) {
			resultado.add(montarSaidaDTO(parcelamento));
		}
		return resultado;
	}

	private SaidaConsultaIntegralizacaoAgendadaDTO montarSaidaDTO(ParcelamentoCCALegado parcelamento) {
		SaidaConsultaIntegralizacaoAgendadaDTO dto = new SaidaConsultaIntegralizacaoAgendadaDTO();
		dto.setDataAgendamento(RetornoSRTBHelper.formatarData(parcelamento.getDataSituacaoParcela()));
		dto.setValor(RetornoSRTBHelper.formatarValor(parcelamento.getValorParcela()));
		dto.setDataVencimento(RetornoSRTBHelper.formatarData(parcelamento.getDataVencParcela()));
		dto.setCodigoCanal(parcelamento.getCodCanal() == null ? "" : parcelamento.getCodCanal().toString());
		dto.setParcelamentoPK(ParcelamentoCCAPKHelper.toStringPK(parcelamento.getParcelamentoCCALegadoPK()));
		return dto;
	}

	@Override
	protected EntradaConsultaIntegralizacaoAgendadaDTO criarDTO(Mensagem mensagem) throws BancoobException {
		EntradaConsultaIntegralizacaoAgendadaDTO dto = new EntradaConsultaIntegralizacaoAgendadaDTO();
		
		dto.setNumCpfCnpj(ContaCapitalUtil.retirarMascaraCpfCnpj(ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.CPF_CNPJ, String.class)));
		dto.setIdInstituicao(ParametroSRTBCCA.extrairIdInstituicao(mensagem));
		dto.setCodigoCooperativa(obterNumeroCooperativa(dto.getIdInstituicao()));
		
		PessoaIntegracaoDTO pessoaIntegracaoDTO = obterPessoaIntegracaoPorCpfCnpjInstituicao(dto.getNumCpfCnpj(), dto.getIdInstituicao());
		ContaCapitalLegado contaCapitalLegado = obterContaCapitalClienteLegado(dto.getCodigoCooperativa(), pessoaIntegracaoDTO.getIdPessoaLegado());
		dto.setNumMatricula(contaCapitalLegado.getNumMatricula());
		
		return dto;
	}

	@Override
	protected String getNomeTransacao() {
		return NOME_TRANSACAO;
	}

}

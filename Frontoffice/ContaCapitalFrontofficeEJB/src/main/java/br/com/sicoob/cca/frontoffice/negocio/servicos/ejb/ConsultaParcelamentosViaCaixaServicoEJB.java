package br.com.sicoob.cca.frontoffice.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.Resultado;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.cca.frontoffice.negocio.dto.EntradaConsultaParcelasViaCaixaDTO;
import br.com.sicoob.cca.frontoffice.negocio.dto.SaidaConsultaParcelasViaCaixaDTO;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.cca.frontoffice.negocio.util.RetornoSRTBHelper;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalExternoServicoLocal;

/**
 * Serviço de consulta de parcelamentos abertos via caixa.
 * Nome da transação: CCACONSPARCCAIXA
 * @author Nairon.Silva
 */
@Stateless
@Remote({ Transacao.class })
public class ConsultaParcelamentosViaCaixaServicoEJB extends ContaCapitalFrontofficeServicoEJB<EntradaConsultaParcelasViaCaixaDTO, SaidaConsultaParcelasViaCaixaDTO> implements Transacao {

	private static final String NOME_TRANSACAO = "CCACONSPARCCAIXA";
	
	@EJB
	private ParcelamentoContaCapitalExternoServicoLocal parcelamentoContaCapitalExternoServico;
	
	@Override
	protected String getNomeTransacao() {
		return NOME_TRANSACAO;
	}

	@Override
	protected RetornoTransacaoObjeto executarTransacao(EntradaConsultaParcelasViaCaixaDTO dto, Mensagem mensagem) throws BancoobException, ExcecaoTransacao {
		List<ParcelamentoRenDTO> parcelamentos = pesquisarParcelamentos(dto);
		Resultado<SaidaConsultaParcelasViaCaixaDTO> resultado = criarResultado();
		for (ParcelamentoRenDTO parcDTO : parcelamentos) {
			resultado.add(montarSaidaDTO(dto, parcDTO));
		}
		return criarRetornoTransacao(resultado);
	}

	private SaidaConsultaParcelasViaCaixaDTO montarSaidaDTO(EntradaConsultaParcelasViaCaixaDTO dtoEntrada, ParcelamentoRenDTO parcDTO) {
		SaidaConsultaParcelasViaCaixaDTO dto = new SaidaConsultaParcelasViaCaixaDTO();
		dto.setNumParcelamento(parcDTO.getNumParcelamento().intValue());
		dto.setNumParcela(parcDTO.getNumParcela().intValue());
		dto.setValorParcela(RetornoSRTBHelper.formatarValor(parcDTO.getValorParcela()));
		dto.setDataVencimento(RetornoSRTBHelper.formatarData(parcDTO.getDataVencimento()));
		dto.setIdentificador(ContaCapitalUtil.concatenarValores("-", dtoEntrada.getTipoParcelamento(), dtoEntrada.getNumContaCapital(), 
				dto.getNumParcelamento(), dto.getNumParcela(), dto.getValorParcela()));
		return dto;
	}

	private List<ParcelamentoRenDTO> pesquisarParcelamentos(EntradaConsultaParcelasViaCaixaDTO dto) throws BancoobException {
		ParcelamentoCapitalDTO filtro = new ParcelamentoCapitalDTO();
		filtro.setIdInstituicao(dto.getIdInstituicao());
		filtro.setIdTipoParcelamento(dto.getTipoParcelamento().shortValue());
		filtro.setNumContaCapital(dto.getNumContaCapital());
		filtro.setIdTipoIntegralizacao(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getCodigo().shortValue());
		filtro.setIdSituacaoParcelamento(EnumSituacaoParcelamento.COD_PARCELA_GERADA.getCodigo().shortValue());
		return parcelamentoContaCapitalExternoServico.pesquisarParcelamentosEmAbertoViaCaixa(filtro);
	}

	@Override
	protected EntradaConsultaParcelasViaCaixaDTO criarDTO(Mensagem mensagem) throws BancoobException {
		EntradaConsultaParcelasViaCaixaDTO dto = new EntradaConsultaParcelasViaCaixaDTO();
		dto.setIdInstituicao(ParametroSRTBCCA.extrairIdInstituicao(mensagem));
		dto.setNumContaCapital(ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.MATRICULA, Number.class).intValue());
		dto.setTipoParcelamento(ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.TIPO_PARCELAMENTO, Number.class).intValue());
		return dto;
	}

}

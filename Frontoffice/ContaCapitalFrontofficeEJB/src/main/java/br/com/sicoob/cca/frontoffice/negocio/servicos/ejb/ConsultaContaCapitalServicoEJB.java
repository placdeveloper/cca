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
import br.com.sicoob.cca.comum.negocio.dto.PesquisaContaCapitalDTO;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.PesquisaContaCapitalServicoLocal;
import br.com.sicoob.cca.frontoffice.negocio.dto.EntradaConsultaContaCapitalDTO;
import br.com.sicoob.cca.frontoffice.negocio.dto.SaidaConsultaContaCapitalDTO;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;

/**
 * Serviço de consulta de conta capital.
 * Nome da transação: CCACONSULTACCA
 * @author Nairon.Silva
 */
@Stateless
@Remote({ Transacao.class })
public class ConsultaContaCapitalServicoEJB extends ContaCapitalFrontofficeServicoEJB<EntradaConsultaContaCapitalDTO, SaidaConsultaContaCapitalDTO> implements Transacao {

	private static final String NOME_TRANSACAO = "CCACONSULTACCA";
	
	@EJB
	private PesquisaContaCapitalServicoLocal servico;
	
	@Override
	protected String getNomeTransacao() {
		return NOME_TRANSACAO;
	}

	@Override
	protected RetornoTransacaoObjeto executarTransacao(EntradaConsultaContaCapitalDTO dto, Mensagem mensagem) throws BancoobException, ExcecaoTransacao {
		PesquisaContaCapitalDTO pesquisaDTO = criarPesquisaDTO(dto);
		List<PesquisaContaCapitalDTO> ccas = servico.pesquisar(pesquisaDTO);
		Resultado<SaidaConsultaContaCapitalDTO> resultado = criarResultado();
		for (PesquisaContaCapitalDTO cca : ccas) {
			resultado.add(montarSaidaDTO(cca));
		}
		return criarRetornoTransacao(resultado);
	}

	private SaidaConsultaContaCapitalDTO montarSaidaDTO(PesquisaContaCapitalDTO cca) {
		SaidaConsultaContaCapitalDTO dto = new SaidaConsultaContaCapitalDTO();
		dto.setNumContaCapital(cca.getNumContaCapital());
		dto.setCpfCnpj(cca.getCpfCnpj());
		dto.setNome(cca.getNome());
		dto.setDescSituacaoContaCapital(cca.getDescSituacaoContaCapital());
		return dto;
	}

	private PesquisaContaCapitalDTO criarPesquisaDTO(EntradaConsultaContaCapitalDTO dto) {
		PesquisaContaCapitalDTO pesquisaDTO = new PesquisaContaCapitalDTO();
		pesquisaDTO.setIdInstituicao(dto.getIdInstituicao());
		pesquisaDTO.setSemFiltroSituacaoCadastro();
		if (dto.isTipoProcuraCCA()) {
			pesquisaDTO.setNumContaCapital(Integer.valueOf(dto.getTextoProcura()));
		} else if (dto.isTipoProcuraCpfCnpj()) {
			pesquisaDTO.setCpfCnpj(dto.getTextoProcura());
		} else if (dto.isTipoProcuraNome()) {
			pesquisaDTO.setNome(dto.getTextoProcura());
		}
		return pesquisaDTO;
	}

	@Override
	protected EntradaConsultaContaCapitalDTO criarDTO(Mensagem mensagem) throws BancoobException {
		EntradaConsultaContaCapitalDTO dto = new EntradaConsultaContaCapitalDTO();
		dto.setIdInstituicao(ParametroSRTBCCA.extrairIdInstituicao(mensagem));
		dto.setTipoProcura(ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.TIPO_PESQUISA, Number.class).intValue());
		dto.setTextoProcura(ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.CHAVE_PESQUISA, String.class));
		return dto;
	}

}

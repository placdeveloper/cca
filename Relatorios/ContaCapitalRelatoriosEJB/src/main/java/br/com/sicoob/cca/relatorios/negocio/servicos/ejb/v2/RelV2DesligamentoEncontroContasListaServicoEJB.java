package br.com.sicoob.cca.relatorios.negocio.servicos.ejb.v2;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.dto.DadosDesligamentoDTO;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.dto.RelDesligamentoEncontroContasListaDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelDesligamentoAssociadoServicoLocal;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * RelV2DesligamentoEncontroContasListaServicoEJB
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelV2DesligamentoEncontroContasListaServicoEJB implements IProcessamentoRelatorioServico {
	
	@EJB
	private RelDesligamentoAssociadoServicoLocal servico;
	
	/**
	 * @see br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico#gerarRelatorio(br.com.sicoob.relatorio.api.dto.ParametroDTO, br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		RelDesligamentoEncontroContasListaDTO filtro = new RelDesligamentoEncontroContasListaDTO();
		filtro.setIdInstituicao(Integer.valueOf(rDto.getUsuarioBancoobDTO().getIdInstituicao()));
		filtro.setNumContaCapitalInicial(Integer.valueOf(dto.getDados().get("numContaCapitalInicial").toString()));
		filtro.setNumContaCapitalFinal(Integer.valueOf(dto.getDados().get("numContaCapitalFinal").toString()));
		servico.configurarParametrosComuns(rDto);
		RelatorioContaCapitalV2<DadosDesligamentoDTO> rel = 
				(RelatorioContaCapitalV2<DadosDesligamentoDTO>) servico.gerarRelatorioDesligamentoEncontroContasLista(filtro);
		return rel.gerar(rDto);
	}

}

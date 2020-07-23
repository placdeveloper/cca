package br.com.sicoob.cca.relatorios.negocio.servicos.ejb.v2;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.HistBloqueioCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelBloqueioServicoLocal;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Relatorio HistoricoBloqueio
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelV2HistoricoBloqueioServicoEJB implements IProcessamentoRelatorioServico {

	@EJB
	private RelBloqueioServicoLocal servico;
	
	/**
	 * @see br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico#gerarRelatorio(br.com.sicoob.relatorio.api.dto.ParametroDTO, br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		BloqueioContaCapitalDTO filtro = new BloqueioContaCapitalDTO();
		filtro.setIdBloqueio(Integer.valueOf(dto.getDados().get("idBloqueio").toString()));
		filtro.setIdInstituicao(Integer.valueOf(dto.getDados().get("idInstituicao").toString()));
		
		servico.configurarParametrosComuns(rDto);
		RelatorioContaCapitalV2<HistBloqueioCapital> rel = (RelatorioContaCapitalV2<HistBloqueioCapital>) 
				servico.gerarRelatorioHistoricoBloqueio(filtro);
		return rel.gerar(rDto);
	}

}

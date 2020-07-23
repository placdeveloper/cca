package br.com.sicoob.cca.replicacao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.replicacao.negocio.servicos.interfaces.ReplicacaoContaCapitalServicoLocal;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;
import br.com.sicoob.relatorio.api.jasper.SicoobJasperReports;

/**
 * Relatorio BatimentoSQLDB2
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelBatimentoSQLDB2 implements IProcessamentoRelatorioServico {

	@EJB
	private ReplicacaoContaCapitalServicoLocal servico;
	
	/**
	 * @see br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico#gerarRelatorio(br.com.sicoob.relatorio.api.dto.ParametroDTO, br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		
		String coopParam = dto.getDados().get("cooperativa").toString();
		List<Integer> cooperativas = null;
		if (coopParam.length() > 0) {
			cooperativas = new ArrayList<Integer>();
			String[] coops = coopParam.split(",");
			for (String coop : coops) {
				cooperativas.add(Integer.valueOf(coop));	
			}
		}
		
		SicoobJasperReports rel = (SicoobJasperReports) servico.gerarRelatorioBatimentoSQLxDB2(cooperativas);
		
		return rel.gerar(rDto);
	}

}

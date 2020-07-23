package br.com.sicoob.cca.relatorios.negocio.servicos.ejb.v2;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.dto.RelValorParametroDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelValorParametroServicoLocal;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Relatorio ValorParametro
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelV2ValorParametroServicoEJB implements IProcessamentoRelatorioServico {

	@EJB
	private RelValorParametroServicoLocal servico;
	
	/**
	 * @see br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico#gerarRelatorio(br.com.sicoob.relatorio.api.dto.ParametroDTO, br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		
		List<Integer> idsInstituicao = (List) dto.getDados().get("idsInstituicao");
		Integer idConfiguracao = Integer.valueOf(dto.getDados().get("idConfiguracao").toString());
		
		servico.configurarParametrosComuns(rDto);
		RelatorioContaCapitalV2<RelValorParametroDTO> rel = 
				(RelatorioContaCapitalV2<RelValorParametroDTO>) servico.gerarHistorico(idsInstituicao, idConfiguracao);
		
		return rel.gerar(rDto);
	}

}

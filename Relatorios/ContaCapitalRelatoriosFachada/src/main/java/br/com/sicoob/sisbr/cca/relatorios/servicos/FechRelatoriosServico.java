package br.com.sicoob.sisbr.cca.relatorios.servicos;

import java.util.Date;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.relatorios.negocio.delegates.ContaCapitalRelatoriosFabricaDelegates;
import br.com.sicoob.cca.relatorios.negocio.delegates.FechRelatoriosDelegate;
import br.com.sicoob.cca.relatorios.negocio.dto.FechRelatorioDTO;
import br.com.sicoob.sisbr.cca.relatorios.RelatoriosContaCapital;

/**
* @author Ricardo.Barcante
*/
@RemoteService
public class FechRelatoriosServico  extends RelatoriosContaCapital {

	private FechRelatoriosDelegate fechRelatoriosDelegate = 
			ContaCapitalRelatoriosFabricaDelegates.getInstance().criarFechRelatoriosDelegate();
	
	/**
	 * 
	 * @param formato
	 * @param caminhoDoJasper
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO emitirRelatorio(String formato, String caminhoDoJasper) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		FechRelatorioDTO dto = fechRelatoriosDelegate.exportaRelatorioParaFormato(formato, caminhoDoJasper);
		retornoDTO.getDados().put("reportStream", dto.getReportStream());
		retornoDTO.getDados().put("reportName", dto.getReportName());
		
		return retornoDTO;
	}
	
	/**
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO buscarListaRelatorios(RequisicaoReqDTO dto) throws BancoobException{
		RetornoDTO retorno = new RetornoDTO();
		FechRelatorioDTO relatorioDTO = new FechRelatorioDTO();
		Map<String, Object> relFechamentoVO  = (Map<String, Object>) dto.getDados().get("relFechamentoVO");

		relatorioDTO.setIdInstituicao(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		
		if( !(relFechamentoVO.get("data") instanceof Date)) {
			throw new NegocioException("Data invalida.");
		}
		
		relatorioDTO.setData((Date) relFechamentoVO.get("data"));
		
		retorno.getDados().put("lstDadosRetorno", fechRelatoriosDelegate.listaRelatoriosPorData(relatorioDTO));
		
		return retorno;
	}
}

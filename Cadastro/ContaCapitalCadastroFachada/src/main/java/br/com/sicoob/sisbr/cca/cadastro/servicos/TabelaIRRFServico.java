package br.com.sicoob.sisbr.cca.cadastro.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.cca.cadastro.CadastroContaCapital;
import br.com.sicoob.sisbr.cca.cadastro.vo.TabelaIRRFVO;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.TabelaIRRFLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TabelaIRRFLegadoDTO;

/**
 * @author marco.nascimento
 */
@RemoteService
public class TabelaIRRFServico extends CadastroContaCapital {
	
	private TabelaIRRFLegadoDelegate tabelaIRRFDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarTabelaIRRFLegadoDelegate();
	
	/**
	 * Apresentacao inicial da tela
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO reqDTO) throws BancoobException {
		return new RetornoDTO();
	}
	
	/**
	 * Consulta por ano base
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultar(RequisicaoReqDTO reqDTO) throws BancoobException {
		
		RetornoDTO retornoDTO = new RetornoDTO();
		
		String anoBaseTexto = reqDTO.getDados().get("anoBase").toString();
		Integer anoBase = null;
		
		if (anoBaseTexto != null && anoBaseTexto.length() > 0) {
			anoBase = Integer.valueOf(anoBaseTexto);	
		}		
		
		retornoDTO.getDados().put("registros", consultarPorAnoBase(anoBase));
		
		return retornoDTO;
	}
	
	/**
	 * Consulta valores do ano base anterior
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarAnoAnterior(RequisicaoReqDTO reqDTO) throws BancoobException {
		
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Integer anoBase = Integer.valueOf(reqDTO.getDados().get("anoBase").toString());
		
		List<TabelaIRRFVO> lstVO = consultarPorAnoBase(anoBase);
		
		for(TabelaIRRFVO tabelaIRRFVO : lstVO) {
			tabelaIRRFVO.setAnoBase(tabelaIRRFVO.getAnoBase() +1);
		}
		
		retornoDTO.getDados().put("registros", lstVO);
		
		return retornoDTO;
	}
	
	/**
	 * Realiza inclusao da tabela progressiva de IRRF
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO incluir(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		List<TabelaIRRFVO> lstVO = (List<TabelaIRRFVO>) reqDTO.getDados().get("listaVO");
		
		if(lstVO != null && !lstVO.isEmpty()) {
			tabelaIRRFDelegate.incluir(converterVOparaDTO(lstVO));
		}
		
		return retornoDTO;
	}
	
	/**
	 * Realiza inclusao da tabela progressiva de IRRF
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO excluir(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		List<TabelaIRRFVO> lstVO = (List<TabelaIRRFVO>) reqDTO.getDados().get("listaVO");
		
		if(lstVO != null && !lstVO.isEmpty()) {
			tabelaIRRFDelegate.excluir(converterVOparaDTO(lstVO));
		}
		
		return retornoDTO;
	}
	
	private List<TabelaIRRFVO> consultarPorAnoBase(Integer anoBase) throws BancoobException {
		List<TabelaIRRFVO> lst = null;
		List<TabelaIRRFLegadoDTO> lstDTO = tabelaIRRFDelegate.consultarPorAnoBase(anoBase);
		lst = converterDTOparaVO(lstDTO);
		return lst;
	}
	
	
	private List<TabelaIRRFVO> converterDTOparaVO(List<TabelaIRRFLegadoDTO> lstDTO) throws BancoobException {
		List<TabelaIRRFVO> lstVO = new ArrayList<TabelaIRRFVO>();
		TabelaIRRFVO vo = null;
		for(TabelaIRRFLegadoDTO dto : lstDTO) {
			vo = new TabelaIRRFVO();
			vo.setAnoBase(dto.getAnoBase());
			vo.setPercAliquota(dto.getPercAliquota());
			vo.setValorBaseInicial(dto.getValorBaseInicial());
			vo.setValorBaseFinal(dto.getValorBaseFinal());
			vo.setValorDeducao(dto.getValorParcelaDeducao());
			lstVO.add(vo);
		}
		return lstVO;
	}
	
	private List<TabelaIRRFLegadoDTO> converterVOparaDTO(List<TabelaIRRFVO> lstVO) throws BancoobException {
		List<TabelaIRRFLegadoDTO> lstDTO = new ArrayList<TabelaIRRFLegadoDTO>();
		TabelaIRRFLegadoDTO dto = null;
		for(TabelaIRRFVO vo : lstVO) {
			dto = new TabelaIRRFLegadoDTO();
			dto.setAnoBase(vo.getAnoBase());
			dto.setPercAliquota(vo.getPercAliquota());
			dto.setValorBaseInicial(vo.getValorBaseInicial());
			dto.setValorBaseFinal(vo.getValorBaseFinal());
			dto.setValorParcelaDeducao(vo.getValorDeducao());
			lstDTO.add(dto);
		}
		return lstDTO;
	}
}
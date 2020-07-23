/*
 * 
 */
package br.com.sicoob.sisbr.cca.replicacao.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.cca.comum.util.JsonCapital;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ReplicacaoContaCapitalLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ConsultaMonitoracaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.MonitorReplicacaoCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.replicacao.ReplicacaoContaCapital;
import br.com.sicoob.sisbr.cca.replicacao.vo.MonitoracaoCapitalVO;
import br.com.sicoob.sisbr.cca.replicacao.vo.ReplicacaoContaCapitalLegadoVO;

/**
 * @author Marco.Nascimento
 */
public class MonitoracaoReplicacaoCapitalFachada extends ReplicacaoContaCapital {

	private ReplicacaoContaCapitalLegadoDelegate repLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarReplicacaoContaCapitalLegadoDelegate();
	
	/**
	 * Tela inicial da monitoracao
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		ConsultaMonitoracaoDTO dto = new ConsultaMonitoracaoDTO();
		if(reqDTO.getDados().get("numCoop") != null) {
			dto.setNumCooperativa(Integer.valueOf(reqDTO.getDados().get("numCoop").toString()));
		}
		if (reqDTO.getDados().get("apenasPilotos") != null) {
			dto.setApenasPilotos((Boolean) reqDTO.getDados().get("apenasPilotos"));
		}
		
		retornoDTO.getDados().put("lstMonitor", converterParaVo(repLegadoDelegate.consultarMonitoracaoReplicacao(dto)));
		
		retornoDTO.getDados().put("lstErros", converterParaVoErros(repLegadoDelegate.consultarErrosReplicacao(dto)));
		
		return retornoDTO;
	}
	
	/**
	 * Marca registros da replicacao para serem reprocessados
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	public RetornoDTO reprocessar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		List<ReplicacaoContaCapitalLegadoDTO> lstReprocessar = new ArrayList<ReplicacaoContaCapitalLegadoDTO>();
		ReplicacaoContaCapitalLegadoDTO reprocessar = null;
		
		List<ReplicacaoContaCapitalLegadoVO> lst = (List<ReplicacaoContaCapitalLegadoVO>) reqDTO.getDados().get("listaReprocessar");
		for(ReplicacaoContaCapitalLegadoVO vo : lst) {
			if(vo.getSelecionado()) {
				reprocessar = new ReplicacaoContaCapitalLegadoDTO();
				reprocessar.setIdReplicacaoCCA(vo.getIdReplicacaoCCA());
				lstReprocessar.add(reprocessar);
			}
		}
		
		String justificativa = reqDTO.getDados().get("justificativa").toString();
		
		repLegadoDelegate.reprocessar(lstReprocessar, justificativa, InformacoesUsuario.getInstance().getLogin());
		
		return retornoDTO;
	}
	
	/**
	 * Marca registros da replicacao como invalidos
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	public RetornoDTO invalidar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		List<ReplicacaoContaCapitalLegadoDTO> listaInvalido = new ArrayList<ReplicacaoContaCapitalLegadoDTO>();
		ReplicacaoContaCapitalLegadoDTO invalido = null;
		
		List<ReplicacaoContaCapitalLegadoVO> lst = (List<ReplicacaoContaCapitalLegadoVO>) reqDTO.getDados().get("listaInvalidar");
		for(ReplicacaoContaCapitalLegadoVO vo : lst) {
			if(vo.getSelecionado()) {
				invalido = new ReplicacaoContaCapitalLegadoDTO();
				invalido.setIdReplicacaoCCA(vo.getIdReplicacaoCCA());
				listaInvalido.add(invalido);
			}
		}
		
		String justificativa = reqDTO.getDados().get("justificativa").toString();
		
		repLegadoDelegate.invalidar(listaInvalido, justificativa, InformacoesUsuario.getInstance().getLogin());
		
		return retornoDTO;
	}
	
	/**
	 * Conversao DTO -> VO
	 * @param lst
	 * @return
	 */
	private List<MonitoracaoCapitalVO> converterParaVo(List<MonitorReplicacaoCapitalLegadoDTO> lst) {
		List<MonitoracaoCapitalVO> lstRetorno = new ArrayList<MonitoracaoCapitalVO>();
		for(MonitorReplicacaoCapitalLegadoDTO dto : lst) {
			lstRetorno.add(new MonitoracaoCapitalVO(dto));
		}
		return lstRetorno;
	}
	
	/**
	 * Conversao DTO -> VO
	 * @param lst
	 * @return
	 * @throws BancoobException
	 */
	private List<ReplicacaoContaCapitalLegadoVO> converterParaVoErros(List<ReplicacaoContaCapitalLegadoDTO> lst) throws BancoobException {
		List<ReplicacaoContaCapitalLegadoVO> lstRetorno = new ArrayList<ReplicacaoContaCapitalLegadoVO>();
		
		JsonCapital jsonCapital = new JsonCapital();
		
		ReplicacaoContaCapitalLegadoVO vo;
		for(ReplicacaoContaCapitalLegadoDTO dto : lst) {
			
			vo = jsonCapital.converterJSon(dto.getDescChaveReplicacaoSQL(), ReplicacaoContaCapitalLegadoVO.class);
			
			vo.setIdReplicacaoCCA(dto.getIdReplicacaoCCA());
			vo.setIdTabelaReplicadaCCA(dto.getIdTabelaReplicadaCCA());
			vo.setIdSituacaoReplicacaoCCA(dto.getIdSituacaoReplicacaoCCA());
			vo.setCodAcao(dto.getCodAcao());
			vo.setDescChaveReplicacaoSQL(dto.getDescChaveReplicacaoSQL());
			vo.setDataHoraCadastro(dto.getDataHoraCadastro());
			vo.setDataHoraReplicacao(dto.getDataHoraReplicacao());
			vo.setNumCooperativa(dto.getNumCooperativa());
			vo.setIdInstituicao(dto.getIdInstituicao());
			vo.setDescMensagemReplicacao(dto.getDescMensagemReplicacao());
			
			if(dto.getDescChaveReplicacaoDB2() != null) {
				vo.setDescChaveReplicacaoDB2(dto.getDescChaveReplicacaoDB2());
			}
			
			lstRetorno.add(vo);
		}
		
		return lstRetorno;
	}
}
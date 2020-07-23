/*
 * 
 */
package br.com.sicoob.sisbr.cca.replicacao.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.LancamentoContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ParcelamentoContaCapitalDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.constantes.ReplicacaoLegadoConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ReplicacaoContaCapitalLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.replicacao.ReplicacaoContaCapital;
import br.com.sicoob.sisbr.cca.replicacao.conversor.ConversorMonitoracaoContaCapital;
import br.com.sicoob.sisbr.cca.replicacao.vo.MonitoracaoQuadroTotalVO;

/**
 * Fachada MonitoracaoContaCapitalFachada
 */
public class MonitoracaoContaCapitalFachada extends ReplicacaoContaCapital{

	/**
	 * Construtor
	 */
	public MonitoracaoContaCapitalFachada(){
		
	}
	
	//delegate acesso conta capital
	private ContaCapitalDelegate contaCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate();
	
	//delegate acesso parcelamento conta capital
	private ParcelamentoContaCapitalDelegate parcelamentoContaCapitalDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarParcelamentoContaCapitalDelegate();

	//delegate acesso lancamentos 
	private LancamentoContaCapitalDelegate lancamentoContaCapitalDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarLancamentoContaCapitalDelegate();

	//delegate acesso ao servicos de replicacao do legado
	private ReplicacaoContaCapitalLegadoDelegate replicacaoContaCapitalLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarReplicacaoContaCapitalLegadoDelegate();
	
	//Classe auxiliar 
	private ConversorMonitoracaoContaCapital conversorMonitoracaoContaCapital = new ConversorMonitoracaoContaCapital();
	
	//lista geral de monitoracao
	private List<ReplicacaoContaCapitalLegadoDTO> lstMonitoracaoGeral;
	
	/**
	 * Consulta a lista geral com todas as cooperativas, tabelas e seus totais por situação
	 * @return
	 * @throws BancoobException
	 */
	private List<ReplicacaoContaCapitalLegadoDTO> consultarTotalReplicacaoPorTabela() throws BancoobException{
		
		if (lstMonitoracaoGeral == null){	
			lstMonitoracaoGeral = replicacaoContaCapitalLegadoDelegate.consultarTabelaMonitoracaoReplicacao();
		}
		return lstMonitoracaoGeral;
		
	}
	
	/**
	 * Consulta os totais para o quadro de contacapital
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarQuadroContaCapitalTotal()throws BancoobException{
		RetornoDTO dto = new RetornoDTO();
		lstMonitoracaoGeral = consultarTotalReplicacaoPorTabela();		
		dto.getDados().put("tabelaTotalContaCapital", montarQuadroTotalVO(ReplicacaoLegadoConstantes.IDTIPOTABELAREPLICACAO_CONTACAPITAL));
		return dto;

	}
	
	/**
	 * Consulta os totais para o quadro de parcelamentocontacapital
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarQuadroParcelamentoContaCapitalTotal()throws BancoobException{
		RetornoDTO dto = new RetornoDTO();
		lstMonitoracaoGeral = consultarTotalReplicacaoPorTabela();		
		dto.getDados().put("tabelaTotalParcelamentoContaCapital", montarQuadroTotalVO(ReplicacaoLegadoConstantes.IDTIPOTABELAREPLICACAO_PARCELAMENTOCCA));
		return dto;
	}	
	
	/**
	 * Consulta os totais para o quadro de lancamentocontacapital
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarQuadroLancamentoContaCapitalTotal()throws BancoobException{
		RetornoDTO dto = new RetornoDTO();
		lstMonitoracaoGeral = consultarTotalReplicacaoPorTabela();		
		dto.getDados().put("tabelaTotalLancamentoContaCapital", montarQuadroTotalVO(ReplicacaoLegadoConstantes.IDTIPOTABELAREPLICACAO_LANCAMENTOSCCAPITAL));
		return dto;
	}		

	/**
	 * Consulta os totais para o quadro de total geral
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarQuadroTotal()throws BancoobException{
		RetornoDTO dto = new RetornoDTO();
		lstMonitoracaoGeral = consultarTotalReplicacaoPorTabela();		
		dto.getDados().put("tabelaTotalLancamentoContaCapital", montarQuadroTotalVO(ReplicacaoLegadoConstantes.IDTIPOTABELAREPLICACAO_LANCAMENTOSCCAPITAL));
		return dto;
	}	
	
	/**
	 * Monta o VO para os quadros de total para a monitoração
	 * @param idSituacaoReplicacao
	 * @return
	 * @throws BancoobException
	 */
	private MonitoracaoQuadroTotalVO montarQuadroTotalVO(Integer idTabelaReplicada) throws BancoobException{
		MonitoracaoQuadroTotalVO monitoracaoQuadroTotalVO = new MonitoracaoQuadroTotalVO();
		
		monitoracaoQuadroTotalVO.setCountAguardando(Long.valueOf("0"));
		monitoracaoQuadroTotalVO.setCountInvalido(Long.valueOf("0"));
		monitoracaoQuadroTotalVO.setCountReplicado(Long.valueOf("0"));
		monitoracaoQuadroTotalVO.setCountErro(Long.valueOf("0"));		
		
		for (ReplicacaoContaCapitalLegadoDTO item : lstMonitoracaoGeral) {
			if (item.getIdTabelaReplicadaCCA().equals(idTabelaReplicada)){
				
				if (item.getIdSituacaoReplicacaoCCA().equals(ReplicacaoLegadoConstantes.SITUACAO_AGUARDANDO_REPLICACAO)){
					monitoracaoQuadroTotalVO.setCountAguardando(monitoracaoQuadroTotalVO.getCountAguardando() + item.getCountMonitoracao());					
				}
				
				if (item.getIdSituacaoReplicacaoCCA().intValue() > ReplicacaoLegadoConstantes.SITUACAO_ERRO_NA_REPLICACAO.intValue()){
					monitoracaoQuadroTotalVO.setCountInvalido(monitoracaoQuadroTotalVO.getCountInvalido()+ item.getCountMonitoracao());					
				}
				
				if (item.getIdSituacaoReplicacaoCCA().equals(ReplicacaoLegadoConstantes.SITUACAO_REPLICACAO_COM_SUCESSO)){
					monitoracaoQuadroTotalVO.setCountReplicado(monitoracaoQuadroTotalVO.getCountReplicado() + item.getCountMonitoracao());					
				}
				
				if (item.getIdSituacaoReplicacaoCCA().equals(ReplicacaoLegadoConstantes.SITUACAO_ERRO_NA_REPLICACAO)){
					monitoracaoQuadroTotalVO.setCountErro(monitoracaoQuadroTotalVO.getCountErro() + item.getCountMonitoracao());					
				}
			}			
		}		
		
		return monitoracaoQuadroTotalVO;		
	}
	
	/**
	 * Consulta os dados gravados do DB2
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarDadosDB2(RequisicaoReqDTO req) throws BancoobException{
		RetornoDTO dto = new RetornoDTO();
		
		Integer idTabelaReplicadaCCA = Integer.valueOf(req.getDados().get("idTabelaReplicadaCCA").toString()); 
		
		if (idTabelaReplicadaCCA.equals(ReplicacaoLegadoConstantes.IDTIPOTABELAREPLICACAO_CONTACAPITAL)){
			dto.getDados().put("tabelaContaCapital", conversorMonitoracaoContaCapital.converterCapitalEntidadeparaVO(consultarTabelaContaCapital(req)));			
		}
		if (idTabelaReplicadaCCA.equals(ReplicacaoLegadoConstantes.IDTIPOTABELAREPLICACAO_PARCELAMENTOCCA)){
			dto.getDados().put("tabelaParcelamentoContaCapital", conversorMonitoracaoContaCapital.converterParcelamentoCapitalEntidadeparaVO(consultarTabelaParcelamentoContaCapital(req)));			
		}
		if (idTabelaReplicadaCCA.equals(ReplicacaoLegadoConstantes.IDTIPOTABELAREPLICACAO_LANCAMENTOSCCAPITAL)){
			dto.getDados().put("tabelaLancamentoContaCapital", conversorMonitoracaoContaCapital.converterLancamentoCapitalEntidadeparaVO(consultarTabelaLancamentoContaCapital(req)));			
		}
		return dto;
	}
	
	/**
	 * Consulta a conta capital no db2
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	private ContaCapital consultarTabelaContaCapital(RequisicaoReqDTO req) throws BancoobException {
		Integer idContaCapital = Integer.valueOf(req.getDados().get("idContaCapital").toString()); 
		return contaCapitalDelegate.obter(idContaCapital);
	}
	
	/**
	 * Consulta a parcelamentocontacapital no db2
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	private Parcelamento consultarTabelaParcelamentoContaCapital(RequisicaoReqDTO req) throws BancoobException {
		Integer idParcelamentoContaCapital = Integer.valueOf(req.getDados().get("idParcelamentoContaCapital").toString()); 
		return parcelamentoContaCapitalDelegate.obter(idParcelamentoContaCapital); 
	}
	
	/**
	 * Consulta a lancamentocontacapital no db2
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	private LancamentoContaCapital consultarTabelaLancamentoContaCapital(RequisicaoReqDTO req) throws BancoobException {
		Integer idLancamentoContaCapital = Integer.valueOf(req.getDados().get("idLancamentoContaCapital").toString()); 
		return lancamentoContaCapitalDelegate.obter(idLancamentoContaCapital);
	}	
}
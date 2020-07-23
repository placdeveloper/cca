/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.delegates;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.replicacao.negocio.dto.BatimentoSaldoDTO;
import br.com.sicoob.cca.replicacao.negocio.servicos.ReplicacaoContaCapitalServico;
import br.com.sicoob.cca.replicacao.negocio.servicos.locator.ContaCapitalReplicacaoServiceLocator;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaLancamentosCCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaParcelamentoCCALegadoDTO;

/**
 * Delegate ReplicacaoContaCapitalDelegate
 */
public class ReplicacaoContaCapitalDelegate extends ContaCapitalReplicacaoDelegate<ReplicacaoContaCapitalServico> {

	/**
	 * Recupera a instancia
	 * @return
	 */
	public static ReplicacaoContaCapitalDelegate getInstance(){
		return new ReplicacaoContaCapitalDelegate();
	}		
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected ReplicacaoContaCapitalServico localizarServico() {
		return (ReplicacaoContaCapitalServico) ContaCapitalReplicacaoServiceLocator.getInstance().localizarReplicacaoContaCapitalServico();	
	}

	
	/**
	 * @see br.com.sicoob.cca.replicacao.negocio.servicos.ReplicacaoContaCapitalServico#incluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO)
	 * @param obj
	 * @return
	 * @throws BancoobException
	 */
	public Integer incluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj) throws BancoobException{
		return getServico().incluirContaCapital(obj);
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.negocio.servicos.ReplicacaoContaCapitalServico#incluirParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO)
	 * @param obj
	 * @return
	 * @throws BancoobException
	 */
	public Long incluirParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO obj) throws BancoobException{
		return getServico().incluirParcelamentoContaCapital(obj);
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.negocio.servicos.ReplicacaoContaCapitalServico#incluirLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO)
	 * @param obj
	 * @return
	 * @throws BancoobException
	 */
	public Long incluirLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj) throws BancoobException{
		return getServico().incluirLancamentoContaCapital(obj);		
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.negocio.servicos.ReplicacaoContaCapitalServico#alterarContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO)
	 * @param obj
	 * @throws BancoobException
	 */
	public void alterarContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj) throws BancoobException{
		getServico().alterarContaCapital(obj);			
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.negocio.servicos.ReplicacaoContaCapitalServico#alterarParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO) 
	 * @param obj
	 * @throws BancoobException
	 */
	public void alterarParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO obj) throws BancoobException{
		getServico().alterarParcelamentoContaCapital(obj);	
	}
	
	/**
	 * @see br.com.sicoob.cca.replicacao.negocio.servicos.ReplicacaoContaCapitalServico#alterarLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO) 
	 * @param obj
	 * @throws BancoobException
	 */
	public void alterarLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj) throws BancoobException{
		getServico().alterarLancamentoContaCapital(obj);
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.negocio.servicos.ReplicacaoContaCapitalServico#excluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO) 
	 * @param obj
	 * @throws BancoobException
	 */
	public void excluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj) throws BancoobException{
		getServico().excluirContaCapital(obj);		
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.negocio.servicos.ReplicacaoContaCapitalServico#excluirParcelamentoContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO)
	 * @param obj
	 * @throws BancoobException
	 */
	public void excluirParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO obj) throws BancoobException{
		getServico().excluirParcelamentoContaCapital(obj);		
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.negocio.servicos.ReplicacaoContaCapitalServico#alterarLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO) 
	 * @param obj
	 * @throws BancoobException
	 */
	public void excluirLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj) throws BancoobException{
		getServico().excluirLancamentoContaCapital(obj);		
	}

	/**
	 * @see ReplicacaoContaCapitalServico#prepararCooperativaPiloto(Integer)
	 * @param cooperativa
	 * @throws BancoobException
	 */
	public void prepararCooperativaPiloto(Integer cooperativa) throws BancoobException {
		getServico().prepararCooperativaPiloto(cooperativa);
	}

	/**
	 * @see ReplicacaoContaCapitalServico#consultarCooperativaPiloto(Integer)
	 * @param cooperativa
	 * @throws BancoobException
	 */
	public Map<String, Map<String, Long>> consultarCooperativaPiloto(Integer cooperativa) throws BancoobException {
		return getServico().consultarCooperativaPiloto(cooperativa);
	}

	/**
	 * @see ReplicacaoContaCapitalServico#gerarRelatorioBatimentoSQLxDB2(Integer)
	 * @param cooperativas
	 * @throws BancoobException
	 */
	public Object gerarRelatorioBatimentoSQLxDB2(List<Integer> cooperativas) throws BancoobException {
		return getServico().gerarRelatorioBatimentoSQLxDB2(cooperativas);
	}
	
	/**
	 * @see ReplicacaoContaCapitalServico#verificarCooperativaComDivergenciasBatimentos(Integer)
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	public boolean verificarCooperativaComDivergenciasBatimentos(Integer cooperativa) throws BancoobException {
		return getServico().verificarCooperativaComDivergenciasBatimentos(cooperativa);
	}

	/**
	 * @see ReplicacaoContaCapitalServico#consultarBatimentoSaldos(Integer)
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	public List<BatimentoSaldoDTO> consultarBatimentoSaldos(Integer cooperativa) throws BancoobException {
		return getServico().consultarBatimentoSaldos(cooperativa);
	}
	
}

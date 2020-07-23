package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import java.util.Date;

import javax.ejb.EJB;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.Fechamento;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoSQL;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;

/**
 * Classe abstrata para os steps.
 * @author Nairon.Silva
 */
public abstract class ContaCapitalProcessamentoStep extends StepSicoobServico {
	
	@EJB
	private FechamentoContaCapitalServicoLocal fechamentoServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@EJB
	private ProdutoLegadoServicoLocal produtoLegadoServico;
	
	/**
	 * Retorna o numero da cooperativa no parametro dinamico simples.
	 * @param ctx
	 * @return
	 */
	protected Integer getNumCoop(ContextoExecucao ctx) {
		return Integer.valueOf(ctx.getParametroDinamico().getValor().toString());
	}
	
	/**
	 * Retorna o logger
	 * @return
	 */
	protected static ISicoobLogger getLogger(Class<?> clazz) {
		return SicoobLoggerPadrao.getInstance(clazz);
	}
	
	/**
	 * Roda o fechamento para DB2 indicado para a cooperativa fornecida
	 * @param fechamento
	 * @param numCoop
	 * @param idProcesso
	 * @throws BancoobException
	 */
	public <T extends Fechamento> void rodarFechamento(T fechamento, Integer numCoop, Integer idProcesso) throws BancoobException {
		
		Integer idInstituicao = instituicaoIntegracaoServico.obterIdInstituicao(numCoop);
		Date dataAtualProduto = produtoLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, idInstituicao);
		
		try{
			boolean iniciado = fechamentoServico.isStepFechamentoIniciado(dataAtualProduto, idInstituicao, idProcesso);
			if(! iniciado) {
				
				getLogger(ContaCapitalProcessamentoStep.class).info("---- RODAR DB2 ------ idProcesso: " + idProcesso + " ---------- RODAR DB2 ----------");
				fechamento.rodar(numCoop);
				
				fechamentoServico.processoConcluido(dataAtualProduto, idInstituicao, idProcesso);
			}
		} catch (Exception e) {// NOSONAR 
			
			getLogger(ContaCapitalProcessamentoStep.class).erro(e, e.getMessage());
			
			getLogger(ContaCapitalProcessamentoStep.class).info("----- ANTES PROCESSO REJEITADO ------ idProcesso: " + idProcesso + " | DescErroProcesso: " + e.getMessage() + " ---------- ANTES PROCESSO REJEITADO ----------");
			fechamentoServico.processoRejeitado(dataAtualProduto, idInstituicao, idProcesso, e.getMessage());
			
			throw new BancoobException(e);
		}
	}	
	
	/**
	 * Roda o fechamento para SQL indicado para a cooperativa fornecida
	 * @param fechamento
	 * @param numCoop
	 * @param idProcesso
	 * @throws BancoobException
	 */
	public <T extends FechamentoSQL> void rodarFechamentoSQL(T fechamento, Integer numCoop, Integer idProcesso) throws BancoobException {
		
		Integer idInstituicao = instituicaoIntegracaoServico.obterIdInstituicao(numCoop);
		Date dataAtualProduto = produtoLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, idInstituicao);
		
		try{
			boolean iniciado = fechamentoServico.isStepFechamentoIniciado(dataAtualProduto, idInstituicao, idProcesso);
			if(! iniciado) {
				
				getLogger(ContaCapitalProcessamentoStep.class).info("---- RODAR SQL ------ idProcesso: " + idProcesso + " ---------- RODAR SQL ----------");
				fechamento.rodarSQL(numCoop);
				
				getLogger(ContaCapitalProcessamentoStep.class).info("---- PROCESSO CONCLUIDO ------ idProcesso: " + idProcesso + " ---------- PROCESSO CONCLUIDO ----------");;
				fechamentoServico.processoConcluido(dataAtualProduto, idInstituicao, idProcesso);
			}
		} catch (Exception e) {// NOSONAR 
			
			getLogger(ContaCapitalProcessamentoStep.class).erro(e, e.getMessage());
			
			getLogger(ContaCapitalProcessamentoStep.class).info("----- ANTES PROCESSO REJEITADO ------ idProcesso: " + idProcesso + " | DescErroProcesso: " + e.getMessage() + " ---------- ANTES PROCESSO REJEITADO ----------");
			fechamentoServico.processoRejeitado(dataAtualProduto, idInstituicao, idProcesso, e.getMessage());
			
			throw new BancoobException(e);
		}
		
	}	
}

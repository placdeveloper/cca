package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.processamento.util.ProcessamentoUtil;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * @author marco.nascimento@sicoob.com.br
 * @since 03/06/2014
 */
@Stateless
@Remote(JobServico.class)
public class PercentualSingularJobServicoEJB extends ContaCapitalProcessamentoJob {
	
	private static final ISicoobLogger LOG = getLogger(PercentualSingularJobServicoEJB.class);
	
	@SuppressWarnings("unused")
	private static final int DIA_EXECUCAO_FLUXO = 1;
	
	private static final String SERVICO_STEP = "cca_processamento/PercentualSingularStepServicoRemote";
	private static final String MSG_COOP_SITUACAO_ESPECIAL = "Cooperativa com situacao especial: ";

	/**
	 * @see ISicoobContextualJob#obterSteps(ContextoExecucaoJob)
	 */
	public List<Step> obterSteps(ContextoExecucao ctx) {
		List<Step> steps = new ArrayList<Step>();
		steps.addAll(criarStep(ctx));
		return steps;
	}
	
	/**
	 * Cria Step apenas para as centrais verificando o dia de execucao acordado 
	 * @param ctx
	 * @return
	 */
	private List<Step> criarStep(ContextoExecucao ctx) {
		LOG.info("Criando Step");
		Integer numCoop = getNumCoop(ctx);
		
		List<Step> steps = new ArrayList<Step>();
		try {
			if (isExecucaoValida(numCoop)) {
				steps.add(ejb(SERVICO_STEP).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
				LOG.info("Step criado, numCoop:" + numCoop);
			}
		} catch (NumberFormatException e) {
			LOG.erro(e, "CCA." + e.getMessage());
		} catch (BancoobException e) {
			LOG.erro(e, "CCA." + e.getMessage());
		}
		
		return steps;
	}
	
	/**
	 * Verifica se eh execucao valida.
	 *
	 * @param numCoop o valor de num coop
	 * @return {@code true}, se for execucao valida
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private boolean isExecucaoValida(Integer numCoop) throws BancoobException {
		boolean valido = true;
		Integer idInstituicao = ProcessamentoUtil.getIdInstituicao(numCoop);
		
		if (!ProcessamentoUtil.isCentral(idInstituicao) || !ProcessamentoUtil.isInstituicaosAtiva(idInstituicao)) {
			valido = false;
		}
		
		InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate().obterInformacoesInstituicaoSCI(idInstituicao);
		if (instituicaoIntegracaoDTO.getCodigoSituacaoEspecial() != null && instituicaoIntegracaoDTO.getCodigoSituacaoEspecial().intValue() > 0) {
			LOG.info(numCoop + " - " + MSG_COOP_SITUACAO_ESPECIAL + instituicaoIntegracaoDTO.getCodigoSituacaoEspecial().toString());
			valido = false;
		}
		
		return valido;
	}
	
}
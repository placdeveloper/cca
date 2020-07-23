package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * Classe centralizadora do participação indireta. Apenas para aguardar a execução do Job PercentualSingularJobServicoEJB
 * @author Marcos.Balbi
 */
@Stateless
@Remote(JobServico.class)
public class ParticipacaoIndiretaCentralizadorJobServicoEJB extends ContaCapitalProcessamentoJob {

	public List<Step> obterSteps(ContextoExecucao arg0) {
		return new ArrayList<Step>();
	}

}
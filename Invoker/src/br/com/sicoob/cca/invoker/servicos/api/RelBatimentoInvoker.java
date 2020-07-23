package br.com.sicoob.cca.invoker.servicos.api;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.invoker.ContaCapitalInvoker;
import br.com.sicoob.cca.replicacao.negocio.servicos.ReplicacaoContaCapitalServico;

/**
 * RelBatimento
 * @author Nairon.Silva
 */
public class RelBatimentoInvoker extends ContaCapitalInvoker {

	private static final String LOOKUP_NAME = "cca_replicacao/ReplicacaoContaCapitalServicoRemote";

	@Override
	protected void executar() throws BancoobException {
		ReplicacaoContaCapitalServico servico = criarServico(ReplicacaoContaCapitalServico.class);
		List<Integer> cooperativas = new ArrayList<Integer>();
		cooperativas.add(3008);
		Object obj = servico.gerarRelatorioBatimentoSQLxDB2(null);
		System.out.println(obj);
	}
	
	public static void main(String[] args) {
		new RelBatimentoInvoker().invoke(LOOKUP_NAME);
	}

}

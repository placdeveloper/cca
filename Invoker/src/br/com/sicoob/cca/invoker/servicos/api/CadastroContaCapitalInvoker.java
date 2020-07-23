package br.com.sicoob.cca.invoker.servicos.api;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.invoker.ContaCapitalInvoker;
import br.com.sicoob.sisbr.cca.api.negocio.dto.CadastroContaCapitalDTO;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.CadastroContaCapitalServico;

/**
 * CadastroContaCapitalInvoker
 */
public class CadastroContaCapitalInvoker extends ContaCapitalInvoker {

	private static final String LOOKUP_NAME = "APIContaCapital/CadastroContaCapitalServicoRemote";

	private static final int ID_PESSOA = 747635;
	private static final int ID_INSTITUICAO = 29;

	@Override
	protected void executar() throws BancoobException {
		CadastroContaCapitalServico servico = criarServico(CadastroContaCapitalServico.class);

		CadastroContaCapitalDTO dto = new CadastroContaCapitalDTO();
		dto.setIdPessoa(ID_PESSOA);
		dto.setIdInstituicao(ID_INSTITUICAO);

		servico.cadastrarContaCapital(dto);
	}

	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		new CadastroContaCapitalInvoker().invoke(LOOKUP_NAME);
	}

}

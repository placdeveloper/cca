/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelValorParametroServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.locator.ContaCapitalRelatoriosServiceLocator;

/**
 * @author Marco.Nascimento
 */
public class RelValorParametroDelegate extends ContaCapitalRelatoriosDelegate<RelValorParametroServico> {

	/**
	 * M�todo para garantir que o delegate seja criado apenas pela fabrica de delegates
	 */
	RelValorParametroDelegate() {
		
	}
	
	/**
	 * M�todo para criar uma instancia do delegate do relat�rio de desligamento associado.
	 * @return
	 */
	public static RelValorParametroDelegate getInstance() {
		return new RelValorParametroDelegate();
	}
	
	/**
	 * M�todo para criar uma instancia do localizador de servico do relat�rio de desligamento de associado.
	 */
	@Override
	protected RelValorParametroServico localizarServico() {
		return (RelValorParametroServico) ContaCapitalRelatoriosServiceLocator.getInstance().localizarRelValorParametroServico();
	}

	/**
	 * {@link RelValorParametroServico#gerarHistorico(List, Integer)}
	 */
	public Object gerarRelatorio(List<Integer> idsInstituicao, Integer idParametro) throws BancoobException {
		return getServico().gerarHistorico(idsInstituicao, idParametro);
	}
}
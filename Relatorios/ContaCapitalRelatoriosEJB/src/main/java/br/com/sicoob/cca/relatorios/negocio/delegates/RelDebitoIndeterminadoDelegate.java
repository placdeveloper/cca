/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelDebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelDebitoIndeterminadoServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.locator.ContaCapitalRelatoriosServiceLocator;

/**
 * @author marco.nascimento
 */
public class RelDebitoIndeterminadoDelegate extends ContaCapitalRelatoriosDelegate<RelDebitoIndeterminadoServico> {
	
	/**
	 * Método para garantir que o delegate seja criado apenas pela fabrica de delegates
	 */
	RelDebitoIndeterminadoDelegate() {
		
	}
	
	/**
	 * Método para criar uma instancia do delegate do relatório de debito indeterminado
	 * @return
	 */
	public static RelDebitoIndeterminadoDelegate getInstance() {
		return new RelDebitoIndeterminadoDelegate();
	}
	
	/**
	 * Método para criar uma instancia do localizador de servico do relatório de debito indeterminado
	 */
	@Override
	protected RelDebitoIndeterminadoServico localizarServico() {
		return (RelDebitoIndeterminadoServico) ContaCapitalRelatoriosServiceLocator.getInstance().localizarRelDebitoIndeterminadoServico();
	}
	
	/**
	 * Método responsável para gerar relatório de debito indeterminado
	 * @param lst
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarRelatorioDebitoIndeterminado(List<RelDebitoIndeterminadoRenDTO> lst, String filtro) throws BancoobException {
		return getServico().gerarRelatorioDebitoIndeterminado(lst, filtro);
	}	
}
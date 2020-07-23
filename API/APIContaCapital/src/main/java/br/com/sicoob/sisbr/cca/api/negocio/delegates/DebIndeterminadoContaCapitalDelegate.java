/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ContaCapitalDebIndeterminadoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.DebIndeterminadoContaCapitalServico;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.locator.APIContaCapitalServiceLocator;

/**
 * @author Marco.Nascimento
 */
public class DebIndeterminadoContaCapitalDelegate extends APIContaCapitalDelegate<DebIndeterminadoContaCapitalServico> {

	@Override
	protected DebIndeterminadoContaCapitalServico localizarServico() {
		return (DebIndeterminadoContaCapitalServico) APIContaCapitalServiceLocator.getInstance().localizarDebIndeterminadoContaCapitalServico();
	}
	
	/**
	 * {@link DebIndeterminadoContaCapitalServico#incluirDebitoIndeterminado(ContaCapitalDebIndeterminadoDTO)}
	 */
	public Boolean incluirDebitoIndeterminado(ContaCapitalDebIndeterminadoDTO dto) throws BancoobException{
		return getServico().incluirDebitoIndeterminado(dto);
	}
}
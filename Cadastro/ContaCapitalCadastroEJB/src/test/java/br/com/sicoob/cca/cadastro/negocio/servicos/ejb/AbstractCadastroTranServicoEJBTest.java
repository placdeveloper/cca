/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.servicos.ejb;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.negocio.servicos.ejb.BancoobServicoEJB;

/**
 * @author Marco.Nascimento
 *
 * @param <T>
 */
public abstract class AbstractCadastroTranServicoEJBTest<T extends BancoobServicoEJB> {
	
	/**
	 * O m�todo Sets the up.
	 *
	 * @throws Exception lan�a a exce��o Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Recupera o valor de EJB.
	 *
	 * @return o valor de EJB
	 */
	protected abstract T getEJB();	

}
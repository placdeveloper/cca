/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.servicos.ejb;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorCotaDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ValorCotasLegadoDelegate;

/**
 * @author Marco.Nascimento
 *
 * @param <T>
 */
public abstract class AbstractCadastroCrudServicoEJBTest<T extends BancoobEntidade> {
	
	/** Delegates */
	@Mock 
	protected ValorCotaDelegate valorCotaDelegate;
	
	/** O atributo valorCotaLegadoDelegate. */
	@Mock 
	protected ValorCotasLegadoDelegate valorCotaLegadoDelegate;
	
	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Recupera o valor de crudEJB.
	 *
	 * @return o valor de crudEJB
	 */
	protected abstract ContaCapitalCadastroCrudServicoEJB<T> getCrudEJB();	

}
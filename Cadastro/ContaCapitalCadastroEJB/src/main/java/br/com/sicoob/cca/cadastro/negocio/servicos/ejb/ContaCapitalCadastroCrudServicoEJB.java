package br.com.sicoob.cca.cadastro.negocio.servicos.ejb;

import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.cadastro.negocio.excecao.CadastroContaCapitalException;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDaoIF;

/**
 * Implementacao padrao do contrato de servicos CRUD de todo o sistema ContaCapitalCadastro
 *
 * @author Stefanini IT Solutions
 */
public abstract class ContaCapitalCadastroCrudServicoEJB<T extends BancoobEntidade> extends
		BancoobCrudServicoEJB<T> {

	/**
	 * @return
	 */
	protected abstract ContaCapitalCadastroCrudDaoIF<T> getDAO();
	
	
	/**
	 * @see br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB#pesquisar(br.com.bancoob.negocio.dto.ConsultaDto)
	 */
	@Override
	public ConsultaDto<T> pesquisar(ConsultaDto<T> criterios) throws BancoobException {
		ConsultaDto<T> pesquisa = null;
		
		try {
			pesquisa = super.pesquisar(criterios); 
		} catch (PersistenceException e) {
			getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalException(e);
		}
		
		return pesquisa; 
	}
	
	/**
	 * @see br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB#incluir(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	public T incluir(T objeto) throws BancoobException {
		try {
			return super.incluir(objeto); 
		} catch (PersistenciaException e) {
			getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} 
	}
	
}

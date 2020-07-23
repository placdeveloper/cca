package br.com.sicoob.cca.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.cadastro.negocio.dto.CondicaoEstatutariaDTO;
import br.com.sicoob.cca.cadastro.negocio.excecao.CadastroContaCapitalException;
import br.com.sicoob.cca.cadastro.negocio.excecao.CadastroContaCapitalNegocioException;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ConfiguracaoCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ConfiguracaoCapitalServicoRemote;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDaoIF;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroDaoFactory;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ConfiguracaoCapitalDao;
import br.com.sicoob.cca.entidades.negocio.entidades.ConfiguracaoCapital;

/**
 * EJB contendo servicos relacionados a ConfiguracaoCapital.
 */
@Stateless
@Local(ConfiguracaoCapitalServicoLocal.class)
@Remote(ConfiguracaoCapitalServicoRemote.class)
public class ConfiguracaoCapitalServicoEJB extends ContaCapitalCadastroCrudServicoEJB<ConfiguracaoCapital> implements ConfiguracaoCapitalServicoLocal, ConfiguracaoCapitalServicoRemote {
	
	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalCadastroDaoFactory.class)
	private ConfiguracaoCapitalDao configuracaoCapitalDao;	
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ejb.ContaCapitalCadastroCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalCadastroCrudDaoIF<ConfiguracaoCapital> getDAO() {
		return configuracaoCapitalDao;
	}
		
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ConfiguracaoCapitalServico#incluirConfiguracaoCapital(br.com.sicoob.cca.entidades.negocio.entidades.ConfiguracaoCapital)
	 */
	public void incluirConfiguracaoCapital(ConfiguracaoCapital configuracaoCapital) throws BancoobException {
		try{						
			
			ConfiguracaoCapital configuracaoCapitalAlt = configuracaoCapitalDao.obter(configuracaoCapital.getId());
			
			if(configuracaoCapitalAlt != null){				
				throw new CadastroContaCapitalNegocioException("MSG_013");								
			}						
			configuracaoCapitalDao.incluir(configuracaoCapital);
			
		} catch (CadastroContaCapitalNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalNegocioException(e.getMessage());
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalException("MSG_012");
		}
	}
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ConfiguracaoCapitalServico#alterarConfiguracaoCapital(br.com.sicoob.cca.entidades.negocio.entidades.ConfiguracaoCapital)
	 */
	public void alterarConfiguracaoCapital(ConfiguracaoCapital configuracaoCapital) throws BancoobException {
		try{									
			configuracaoCapitalDao.alterar(configuracaoCapital);			
		}catch (CadastroContaCapitalNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalNegocioException(e.getMessage());
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalException("MSG_012");
		}
	}

	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ConfiguracaoCapitalServico#pesquisarProximoSeqConfiguracaoCapital()
	 */
	public Integer pesquisarProximoSeqConfiguracaoCapital() throws BancoobException {
		try{						
			Integer idConfiguracaoCapital = null;			
			idConfiguracaoCapital = configuracaoCapitalDao.pesquisarProximoSeqConfiguracaoCapital();			
			
			return idConfiguracaoCapital;
		}catch (CadastroContaCapitalNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalNegocioException(e.getMessage());
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalException("MSG_011");
		}
	}
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ConfiguracaoCapitalServico#listarConfiguracaoCapital(br.com.bancoob.negocio.dto.ConsultaDto)
	 */
	public List<ConfiguracaoCapital> listarConfiguracaoCapital(ConsultaDto<ConfiguracaoCapital> consultaDTO) throws BancoobException {
		try{						
			List<ConfiguracaoCapital> lstConfiguracaoCapital = null;			
			lstConfiguracaoCapital = configuracaoCapitalDao.listar(consultaDTO);			
			
			return lstConfiguracaoCapital;
		}catch (CadastroContaCapitalNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalNegocioException(e.getMessage());
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalException("MSG_011");
		}
	}
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ConfiguracaoCapitalServico#obterConfiguracaoCapital(java.lang.Integer)
	 */
	public ConfiguracaoCapital obterConfiguracaoCapital(Integer idConfiguracaoCapital) throws BancoobException {
		try{						
			ConfiguracaoCapital configuracaoCapital = null;			
			configuracaoCapital = configuracaoCapitalDao.obter(idConfiguracaoCapital);			
			
			return configuracaoCapital;
		}catch (CadastroContaCapitalNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalNegocioException(e.getMessage());
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalException("MSG_011");
		}
	}
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ConfiguracaoCapitalServico#consultarConfiguracaoEstatutaria(java.lang.Integer)
	 */
	public List<CondicaoEstatutariaDTO> consultarConfiguracaoEstatutaria(Integer idInstituicao) throws BancoobException {
		try {
			
			return configuracaoCapitalDao.consultarConfiguracaoEstatutaria(idInstituicao);
			
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalException(e);
		}
	}
}

package br.com.sicoob.cca.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dom4j.dom.DOMDocument;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.excecao.CadastroContaCapitalException;
import br.com.sicoob.cca.cadastro.negocio.excecao.CadastroContaCapitalNegocioException;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ValorConfiguracaoCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ValorConfiguracaoCapitalServicoRemote;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDaoIF;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroDaoFactory;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ValorConfiguracaoCapitalDao;
import br.com.sicoob.cca.entidades.negocio.entidades.HistValorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.HistValorConfiguracaoCapitalPK;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;

/**
 * EJB contendo servicos relacionados a ValorConfiguracaoCapital.
 */
@Stateless
@Local(ValorConfiguracaoCapitalServicoLocal.class)
@Remote(ValorConfiguracaoCapitalServicoRemote.class)
public class ValorConfiguracaoCapitalServicoEJB extends	ContaCapitalCadastroCrudServicoEJB<ValorConfiguracaoCapital> implements	ValorConfiguracaoCapitalServicoLocal, ValorConfiguracaoCapitalServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalCadastroDaoFactory.class)
	private ValorConfiguracaoCapitalDao valorconfiguracaoCapitalDao;	
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ejb.ContaCapitalCadastroCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalCadastroCrudDaoIF<ValorConfiguracaoCapital> getDAO() {
		return valorconfiguracaoCapitalDao;
	}

	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ValorConfiguracaoCapitalServico#listarValorConfiguracaoCapital(br.com.bancoob.negocio.dto.ConsultaDto)
	 */
	public List<ValorConfiguracaoCapital> listarValorConfiguracaoCapital(ConsultaDto<ValorConfiguracaoCapital> consultaDTO) throws BancoobException {
		try{						
			List<ValorConfiguracaoCapital> lstValorConfiguracaoCapital = null;			
			lstValorConfiguracaoCapital = valorconfiguracaoCapitalDao.listar(consultaDTO);						
			
			return lstValorConfiguracaoCapital;
		}catch (CadastroContaCapitalNegocioException e) {	
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalNegocioException(e.getMessage());
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalException("MSG_011");
		}
	}
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ValorConfiguracaoCapitalServico#gravarListaValorConfiguracaoCapital(java.util.List)
	 */
	public void gravarListaValorConfiguracaoCapital(List<ValorConfiguracaoCapital> lstValorConfiguracaoCapital)throws BancoobException {			
		try{												
			for(ValorConfiguracaoCapital valorConfiguracaoCapital:lstValorConfiguracaoCapital){								
				ValorConfiguracaoCapital valorConfiguracaoCapitalAlt = valorconfiguracaoCapitalDao.obter(valorConfiguracaoCapital.getId());

				valorConfiguracaoCapital.getHistorico().add(criarHistorico(valorConfiguracaoCapital));
				
				if(valorConfiguracaoCapitalAlt != null){				
					valorConfiguracaoCapital.getHistorico().addAll(valorConfiguracaoCapitalAlt.getHistorico());
					valorconfiguracaoCapitalDao.alterar(valorConfiguracaoCapital);		
				}else{
					valorConfiguracaoCapital.setId(null);
					valorconfiguracaoCapitalDao.incluir(valorConfiguracaoCapital);					
				}
			}			
		}catch (CadastroContaCapitalNegocioException e) {			
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalNegocioException(e.getMessage());
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalException("MSG_012");
		}
	}

	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ValorConfiguracaoCapitalServico#consultaListaInstituicaoParametro(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public DOMDocument consultaListaInstituicaoParametro(Integer idConfiguracaoCapital, Integer codTipoGrauCoop, Integer numCoop) throws BancoobException {	
		return valorconfiguracaoCapitalDao.consultaListaInstituicaoParametro(idConfiguracaoCapital, codTipoGrauCoop, numCoop);
	}
	
	/**
	 * Criar historico.
	 *
	 * @param valorConfiguracaoCapital o valor de valor configuracao capital
	 * @return HistValorConfiguracaoCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public HistValorConfiguracaoCapital criarHistorico(ValorConfiguracaoCapital valorConfiguracaoCapital) throws BancoobException {
		HistValorConfiguracaoCapital histValorconfiguracaoCapital = new HistValorConfiguracaoCapital();		
		
		histValorconfiguracaoCapital.setId(new HistValorConfiguracaoCapitalPK(valorConfiguracaoCapital, new DateTimeDB()));		
		histValorconfiguracaoCapital.setIdConfiguracaoCapital(valorConfiguracaoCapital.getConfiguracaoCapital().getId());
		histValorconfiguracaoCapital.setIdInstituicao(valorConfiguracaoCapital.getIdInstituicao());
		histValorconfiguracaoCapital.setIdUsuario(valorConfiguracaoCapital.getIdUsuario());
		histValorconfiguracaoCapital.setValorConfiguracao(valorConfiguracaoCapital.getValorConfiguracao());
		
		return histValorconfiguracaoCapital;
	}

	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ValorConfiguracaoCapitalServico#obterValorConfiguracao(java.lang.Integer, java.lang.Integer)
	 */
	public ValorConfiguracaoCapital obterValorConfiguracao(Integer idParametro, Integer idInstituicao) throws BancoobException {
		return valorconfiguracaoCapitalDao.obterValorConfiguracao(idParametro, idInstituicao);
	}
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ValorConfiguracaoCapitalServico#obterValorConfiguracao(java.util.List, java.lang.Integer)
	 */
	public List<ValorConfiguracaoCapital> obterValorConfiguracao(List<Integer> idsInstituicao, Integer idParametro) throws BancoobException {
		return valorconfiguracaoCapitalDao.obterValorConfiguracao(idsInstituicao, idParametro);
	}
}

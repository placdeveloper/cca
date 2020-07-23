package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.BancoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RemessaIntegralizacaoOutrosBancosLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoNegocioException;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.IntegralizacaoOutrosBancosLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.IntegralizacaoOutrosBancosLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.IntegralizacaoOutrosBancosLegadoDao;

/**
 * IntegralizacaoOutrosBancosLegadoServicoEJB
 */
@Stateless
@Local(IntegralizacaoOutrosBancosLegadoServicoLocal.class)
@Remote(IntegralizacaoOutrosBancosLegadoServicoRemote.class)
public class IntegralizacaoOutrosBancosLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements IntegralizacaoOutrosBancosLegadoServicoLocal, IntegralizacaoOutrosBancosLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;

	@Dao (entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private IntegralizacaoOutrosBancosLegadoDao integralizacaoOutrosBancosLegadoDao;

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico#obtemListaBancos()
	 */
	public List<BancoLegadoDTO> obtemListaBancos() throws BancoobException {
		return integralizacaoOutrosBancosLegadoDao.obtemListaBancos();
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico#consultarFavorecidosIntegralizacao(br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO)
	 */
	public List<IntegralizacaoOutrosBancosLegadoDTO> consultarFavorecidosIntegralizacao(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException {
		return integralizacaoOutrosBancosLegadoDao.consultarFavorecidosIntegralizacao(filtro);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico#enviarRemessa(java.util.List)
	 */
	public void enviarRemessa(List<IntegralizacaoOutrosBancosLegadoDTO> dtos) throws BancoobException {
		validarEnviarRemessa(dtos);
		integralizacaoOutrosBancosLegadoDao.enviarRemessa(dtos);
	}

	private void validarEnviarRemessa(List<IntegralizacaoOutrosBancosLegadoDTO> dtos) throws ContaCapitalIntegracaoLegadoNegocioException {
		for (IntegralizacaoOutrosBancosLegadoDTO dto : dtos) {
			if (dto.getValorIntegralizacao() == null || dto.getValorIntegralizacao().compareTo(BigDecimal.ZERO) < 1) {
				throw new ContaCapitalIntegracaoLegadoNegocioException("INTEG_OUTROS_BANCOS_VALOR");
			}
		}
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico#consultarContasFavorecidos(br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO, java.lang.Integer)
	 */
	public List<IntegralizacaoOutrosBancosLegadoDTO> consultarContasFavorecidos(IntegralizacaoOutrosBancosLegadoDTO filtro, Integer tipoSituacao) throws BancoobException {
		return integralizacaoOutrosBancosLegadoDao.consultarContasFavorecidos(filtro, tipoSituacao);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico#atualizarContas()
	 */
	public void atualizarContas() throws BancoobException {
		integralizacaoOutrosBancosLegadoDao.atualizarContas();
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico#definirPrincipal(java.util.List)
	 */
	public void definirPrincipal(List<IntegralizacaoOutrosBancosLegadoDTO> dtos) throws BancoobException {
		validarDefinicaoPrincipal(dtos);
		integralizacaoOutrosBancosLegadoDao.definirPrincipal(dtos);
	}

	private void validarDefinicaoPrincipal(List<IntegralizacaoOutrosBancosLegadoDTO> dtos) throws ContaCapitalIntegracaoLegadoNegocioException {
		Set<Integer> clientes = new HashSet<Integer>();
		for (IntegralizacaoOutrosBancosLegadoDTO dto : dtos) {
			if (!clientes.add(dto.getNumCliente())) {
				throw new ContaCapitalIntegracaoLegadoNegocioException("INTEG_OUTROS_BANCOS_DEFINIR_PRINCIPAL_CONTA", 
						dto.getDescNomePessoa(), dto.getNumCliente().toString());
			}
		}
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico#consultarRemessaIntegralizacaoOutrosBancos(br.com.sicoob.sisbr.cca.legado.negocio.dto.RemessaIntegralizacaoOutrosBancosLegadoDTO)
	 */
	public List<RemessaIntegralizacaoOutrosBancosLegadoDTO> consultarRemessaIntegralizacaoOutrosBancos(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException {
		return integralizacaoOutrosBancosLegadoDao.consultarRemessaIntegralizacaoOutrosBancos(filtro);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico#enviarIntegBancos()
	 */
	public void enviarIntegBancos() throws BancoobException {
		integralizacaoOutrosBancosLegadoDao.enviarIntegBancos();
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico#consultarRemessaIntegralizacaoOutrosBancosDetalhe(Integer sequencialArquivo)
	 */
	public List<IntegralizacaoOutrosBancosLegadoDTO> consultarRemessaEnvDetalhe(IntegralizacaoOutrosBancosLegadoDTO dto) throws BancoobException {
		return integralizacaoOutrosBancosLegadoDao.consultarRemessaEnvDetalhe(dto);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico#consultarRemessaRetornoDetalhe(br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO)
	 */
	public List<IntegralizacaoOutrosBancosLegadoDTO> consultarRemessaRetornoDetalhe(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException {
		return integralizacaoOutrosBancosLegadoDao.consultarRemessaRetornoDetalhe(filtro);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico#consultarRemessaRetorno(br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO)
	 */
	public List<RemessaIntegralizacaoOutrosBancosLegadoDTO> consultarRemessaRetorno(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException {
		return integralizacaoOutrosBancosLegadoDao.consultarRemessaRetorno(filtro);
	}
	
	
	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico#gravarIntegralizacao(java.util.List)
	 */
	public void gravarIntegralizacao(IntegralizacaoOutrosBancosLegadoDTO dto) throws BancoobException {
		integralizacaoOutrosBancosLegadoDao.gravarIntegralizacao(dto);
	}
	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico#consultarRemessaIntegralizacaoOutrosBancos(br.com.sicoob.sisbr.cca.legado.negocio.dto.RemessaIntegralizacaoOutrosBancosLegadoDTO)
	 */
	public List<IntegralizacaoOutrosBancosLegadoDTO> consultarRemessaIntegralizacaoOutrosBancosSWS(Integer numCoop) throws BancoobException {
		return integralizacaoOutrosBancosLegadoDao.consultarFavorecidosIntegralizacaoSWS(numCoop);
	}
	
	/**
	 * @throws SQLException 
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico#gravarIntegralizacao(java.util.List)
	 */
	public void updateIntegralizacaoSWS(IntegralizacaoOutrosBancosLegadoDTO dto) throws BancoobException {
		integralizacaoOutrosBancosLegadoDao.updateIntegralizacaoSWS(dto);
	}
	
	
}

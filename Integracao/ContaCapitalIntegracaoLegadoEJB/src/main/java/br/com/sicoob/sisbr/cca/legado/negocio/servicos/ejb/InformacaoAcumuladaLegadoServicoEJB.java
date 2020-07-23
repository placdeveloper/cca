package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ParticipacaoIndiretaBancoobLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.InformacaoAcumuladaLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.InformacaoAcumuladaLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.InformacaoAcumuladaLegadoDao;

/**
 * Disponibiliza informacoes acumuladas (Legado)
 * @author Marco.Nascimento
 * @since 09/06/2014
 */
@Stateless
@Local(InformacaoAcumuladaLegadoServicoLocal.class)
@Remote(InformacaoAcumuladaLegadoServicoRemote.class)
public class InformacaoAcumuladaLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements InformacaoAcumuladaLegadoServicoLocal, InformacaoAcumuladaLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao(entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private InformacaoAcumuladaLegadoDao informacaoAcumuladaLegadoDao;
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.InformacaoAcumuladaLegadoServico#calcularParticipacaoIndiretaBancoob(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ParticipacaoIndiretaBancoobLegadoDTO> calcularParticipacaoIndiretaBancoob(Integer numCoopCentral, List<Integer> cooperativasSingulares, int qtdMesesDataBase) throws BancoobException {		
		DateTime dataBase = new DateTime().minusMonths(qtdMesesDataBase);
		List<ParticipacaoIndiretaBancoobLegadoDTO> lst = informacaoAcumuladaLegadoDao.obterSaldoCapitalSingulares(dataBase, numCoopCentral, cooperativasSingulares);
		if (!lst.isEmpty()){
			calcularPercentualParticipacaoCentral(lst);
		}
 		return lst;
	}
	
	/**
	 * Calcula percentual da singular na central.
	 * Eh utilizado o valor total para compor o saldo total, mas os clientes que nao sao cooperativas nao devem fazer parte do calculo.
	 * @param lst
	 */
	private void calcularPercentualParticipacaoCentral(List<ParticipacaoIndiretaBancoobLegadoDTO> lst) throws BancoobException {
		BigDecimal somatorioCentral = BigDecimal.ZERO;
		
		//guarda o item que faz parte do somatorio mas nao faz parte da contabilização do percentual
		//somente 1 item sempre
		ParticipacaoIndiretaBancoobLegadoDTO dtoRemove = null;
		
		for(ParticipacaoIndiretaBancoobLegadoDTO dto : lst) {
			somatorioCentral = somatorioCentral.add(dto.getValor());
			if (dto.getNumCooperativa() == null){
				dtoRemove = dto;
			}
		}

		if (dtoRemove != null){
			lst.remove(dtoRemove);		
		}
		
		for(ParticipacaoIndiretaBancoobLegadoDTO dto : lst) {
			if(dto.getValor() != null && dto.getValor().intValue() != 0) {
				dto.setPercPartCentral(dto.getValor().divide(somatorioCentral, 8, RoundingMode.HALF_EVEN).multiply(new BigDecimal(100)).setScale(6, RoundingMode.HALF_EVEN));
			}
		}
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.InformacaoAcumuladaLegadoServico#consultarSnapshotCooperativas(java.lang.Integer, DateTime)
	 */
	public List<ParticipacaoIndiretaBancoobLegadoDTO> consultarSnapshotCooperativas(Integer numCoopCentral, DateTime dataSnapshot) throws BancoobException {
		return informacaoAcumuladaLegadoDao.consultarSnapshotCooperativas(numCoopCentral, dataSnapshot);
	}
	
}
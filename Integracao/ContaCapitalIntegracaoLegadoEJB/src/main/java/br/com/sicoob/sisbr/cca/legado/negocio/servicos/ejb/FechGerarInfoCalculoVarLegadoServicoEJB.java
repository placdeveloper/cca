package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechGerarInfoCalculoVarLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechGerarInfoCalculoVarLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechGerarInfoCalculoVarLegadoDao;

/**
 * @author Ricardo.Barcante
 */
@Stateless
@Local(FechGerarInfoCalculoVarLegadoServicoLocal.class)
@Remote(FechGerarInfoCalculoVarLegadoServicoRemote.class)
public class FechGerarInfoCalculoVarLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements FechGerarInfoCalculoVarLegadoServicoLocal, FechGerarInfoCalculoVarLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao(entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private FechGerarInfoCalculoVarLegadoDao fechamentoGerarInfoCalculoVarLegadoDao;
	
	public void rodar(Integer numCoop) throws BancoobException {
		int usaResgAuto = fechamentoGerarInfoCalculoVarLegadoDao.obterValorParametro(numCoop, ContaCapitalConstantes.PAR_INDICA_USO_RESG_AUTO);
		int tipoFechDesejado = fechamentoGerarInfoCalculoVarLegadoDao.obterValorParametro(numCoop, ContaCapitalConstantes.PAR_SITUACAO_FECHAMENTO_SICOOBBR);
		
		if(tipoFechDesejado == ContaCapitalConstantes.COD_FECHAMENTO_BANCOOB_NOITE) {
			tipoFechDesejado = ContaCapitalConstantes.COD_FECHAMENTO_BANCOOB_MANHA;
		}else {
			tipoFechDesejado = ContaCapitalConstantes.COD_FECHAMENTO_COOPERATIVA;
		}
		
		if(obterIndExecucaoFech(usaResgAuto, tipoFechDesejado, numCoop)) {
			fechamentoGerarInfoCalculoVarLegadoDao.gerarInfoCalculoVar(numCoop);
		}
	}
	
	public boolean obterIndExecucaoFech(int usaResgAuto, int tipoFechDesejado, Integer numCoop) throws BancoobException {		
		if( tipoFechDesejado == ContaCapitalConstantes.COD_FECHAMENTO_COOPERATIVA) {
			return true;
		} else if( usaResgAuto != 0 ) {
			return tipoFechDesejado == ContaCapitalConstantes.COD_FECHAMENTO_BANCOOB_NOITE;
		} else if( usaResgAuto == 0) {
			return tipoFechDesejado == ContaCapitalConstantes.COD_FECHAMENTO_BANCOOB_MANHA || fechamentoGerarInfoCalculoVarLegadoDao.fechOrdemProcessoRowCountIsZero(numCoop);
		}
		
		// Default estava como true no VB
		return true;
	}	
}
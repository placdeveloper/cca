package br.com.sicoob.cca.comum.negocio.servicos.ejb;

import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoContaCapitalServicoRemote;
import br.com.sicoob.cca.comum.persistencia.dao.ContaCapitalComumDaoFactory;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.FechamentoContaCapitalDao;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Responsavel por fornecer informacoes sobre o fechamento do produto conta capital
 * 
 * @author Marco.Nascimento
 */
@Stateless
@Local(FechamentoContaCapitalServicoLocal.class)
@Remote(FechamentoContaCapitalServicoRemote.class)
public class FechamentoContaCapitalServicoEJB extends ContaCapitalComumServicoEJB implements FechamentoContaCapitalServicoLocal, FechamentoContaCapitalServicoRemote {

	private static final int TAM_MAX_MENSAGEM_ERRO = 2000;
	
	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager emCCAEntidades;
	
	@Dao(entityManager = "emCCAEntidades", fabrica = ContaCapitalComumDaoFactory.class)
	private FechamentoContaCapitalDao fechamentoContaCapitalDao;
	
	/**
	 * @see br.com.sicoob.cca.comum.negocio.servicos.FechamentoContaCapitalServico#isFechamentoIniciado(java.lang.Integer)
	 */
	public boolean isFechamentoIniciado(Integer numCoop) throws BancoobException {
		return fechamentoContaCapitalDao.isFechamentoIniciado(numCoop);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String buscarIdUsuarioFechamento(Integer numCoop) throws BancoobException{
		return fechamentoContaCapitalDao.buscarIdUsuarioFechamento(numCoop);
	}
	
	/**
	 * @see br.com.sicoob.cca.comum.negocio.servicos.FechamentoContaCapitalServico#isStepFechamentoIniciado(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public boolean isStepFechamentoIniciado(Date dataAtualProduto, Integer idInstituicao, Integer idProcesso) throws BancoobException {		
		SicoobLoggerPadrao.getInstance(FechamentoContaCapitalServicoEJB.class).info("---- IS STEP FECHAMENTO INICIADO ------ idProcesso: " + idProcesso + " ---------- IS STEP FECHAMENTO INICIADO ----------");
		return fechamentoContaCapitalDao.isStepFechamentoIniciado(dataAtualProduto, idInstituicao, idProcesso);
	}

	/**
	 * @see br.com.sicoob.cca.comum.negocio.servicos.FechamentoContaCapitalServico#processoConcluido(java.lang.Integer)
	 */
	public boolean processoConcluido(Date dataAtualProduto, Integer idInstituicao, Integer idProcesso) throws BancoobException {
		SicoobLoggerPadrao.getInstance(FechamentoContaCapitalServicoEJB.class).info("---- PROCESSO CONCLUIDO ------ idProcesso: " + idProcesso + " ---------- PROCESSO CONCLUIDO----------");
		return fechamentoContaCapitalDao.processoConcluido(dataAtualProduto, idInstituicao, idProcesso);
	}
	
	/**
	 * @see br.com.sicoob.cca.comum.negocio.servicos.FechamentoContaCapitalServico#processoRejeitado(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean processoRejeitado(Date dataAtualProduto, Integer idInstituicao, Integer idProcesso, String descErroProcesso) throws BancoobException {
		 SicoobLoggerPadrao.getInstance(FechamentoContaCapitalServicoEJB.class).info("---- PROCESSO REJEITADO ------ idProcesso: " + idProcesso + " | DescErroProcesso: " + descErroProcesso + " ---------- PROCESSO REJEITADO ----------");
		return fechamentoContaCapitalDao.processoRejeitado(dataAtualProduto, idInstituicao, idProcesso, limitarTamanhoErro(descErroProcesso));
	}

	private String limitarTamanhoErro(String mensagemErro) {
		if (mensagemErro != null && mensagemErro.length() > TAM_MAX_MENSAGEM_ERRO){
				mensagemErro = mensagemErro.substring(0, TAM_MAX_MENSAGEM_ERRO);			
		}
		return mensagemErro;
	}
	
}
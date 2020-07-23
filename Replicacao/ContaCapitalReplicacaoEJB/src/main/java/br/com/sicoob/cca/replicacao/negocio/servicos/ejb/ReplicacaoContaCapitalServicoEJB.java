package br.com.sicoob.cca.replicacao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.replicacao.negocio.batimento.ExecutorBatimentos;
import br.com.sicoob.cca.replicacao.negocio.batimento.RegistroBatimentoAdapter;
import br.com.sicoob.cca.replicacao.negocio.batimento.RegistroBatimentoContaCapital;
import br.com.sicoob.cca.replicacao.negocio.batimento.RegistroBatimentoLancamento;
import br.com.sicoob.cca.replicacao.negocio.batimento.RegistroBatimentoParcelamento;
import br.com.sicoob.cca.replicacao.negocio.batimento.RelBatimentoCooperativa;
import br.com.sicoob.cca.replicacao.negocio.batimento.RelBatimentoRaiz;
import br.com.sicoob.cca.replicacao.negocio.batimento.RelBatimentoResumo;
import br.com.sicoob.cca.replicacao.negocio.dto.BatimentoSaldoDTO;
import br.com.sicoob.cca.replicacao.negocio.excecao.ContaCapitalReplicacaoException;
import br.com.sicoob.cca.replicacao.negocio.excecao.ReplicacaoContaCapitalDb2Exception;
import br.com.sicoob.cca.replicacao.negocio.excecao.ReplicacaoContaCapitalDb2NegocioException;
import br.com.sicoob.cca.replicacao.negocio.servicos.interfaces.ReplicacaoContaCapitalServicoLocal;
import br.com.sicoob.cca.replicacao.negocio.servicos.interfaces.ReplicacaoContaCapitalServicoRemote;
import br.com.sicoob.cca.replicacao.persistencia.dao.ContaCapitalReplicacaoDaoFactory;
import br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.constantes.ReplicacaoLegadoConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.BatimentoSaldoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoConfiguracaoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaLancamentosCCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaParcelamentoCCALegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ReplicacaoContaCapitalLegadoServicoLocal;

/**
 * EJB com servicos de replicacao.
 */
@Stateless
@Local(ReplicacaoContaCapitalServicoLocal.class)
@Remote(ReplicacaoContaCapitalServicoRemote.class)	
public class ReplicacaoContaCapitalServicoEJB extends ContaCapitalReplicacaoServicoEJB implements ReplicacaoContaCapitalServicoLocal,ReplicacaoContaCapitalServicoRemote {

	private ReplicacaoContaCapitalDao replicacaoContaCapitalDao = ContaCapitalReplicacaoDaoFactory.getInstance().criarReplicacaoContaCapitalDao();
	
	@EJB
	private ReplicacaoContaCapitalLegadoServicoLocal replicacaoContaCapitalLegadoServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	/**
	 * @see ReplicacaoContaCapitalServicoLocal#incluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Integer incluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj)throws BancoobException {
		try{
			return replicacaoContaCapitalDao.incluirContaCapital(obj);	
		}catch (ReplicacaoContaCapitalDb2Exception e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_CRUD_TABELA_CONTACAPITAL",e.getMessage(),e);
		}catch (BancoobException e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception(e.getMessage(),e);
		}
	}

	/**
	 * @see ReplicacaoContaCapitalServicoLocal#incluirParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO)
	 */	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)	
	public Long incluirParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO obj)throws BancoobException {
		try{
			return replicacaoContaCapitalDao.incluirParcelamentoContaCapital(obj);
		}catch (ReplicacaoContaCapitalDb2Exception e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_CRUD_TABELA_PARCELAMENTOCONTACAPITAL",e.getMessage(),e);
		}catch (BancoobException e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception(e.getMessage(),e);
		}
	}

	/**
	 * @see ReplicacaoContaCapitalServicoLocal#incluirLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO)
	 */	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)	
	public Long incluirLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj)throws BancoobException {
		try{
			return replicacaoContaCapitalDao.incluirLancamentoContaCapital(obj);
		}catch (ReplicacaoContaCapitalDb2Exception e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_CRUD_TABELA_LANCAMENTOCONTACAPITAL",e.getMessage(),e);
		}catch (BancoobException e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception(e.getMessage(),e);
		}
	}

	/**
	 * @see ReplicacaoContaCapitalServicoLocal#alterarContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)	
	public void alterarContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj)throws BancoobException {
		try{
			replicacaoContaCapitalDao.alterarContaCapital(obj);	
		}catch (ReplicacaoContaCapitalDb2Exception e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_CRUD_TABELA_CONTACAPITAL",e.getMessage(),e);
		}catch (BancoobException e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception(e.getMessage(),e);
		}
	}

	/**
	 * @see ReplicacaoContaCapitalServicoLocal#alterarParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)	
	public void alterarParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO obj)throws BancoobException {
		try{
			replicacaoContaCapitalDao.alterarParcelamentoContaCapital(obj);	
		}catch (ReplicacaoContaCapitalDb2Exception e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_CRUD_TABELA_PARCELAMENTOCONTACAPITAL",e.getMessage(),e);
		}catch (BancoobException e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception(e.getMessage(),e);
		}	
	}

	/**
	 * @see ReplicacaoContaCapitalServicoLocal#alterarLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO)
	 */	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)	
	public void alterarLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj)throws BancoobException {
		try{
			replicacaoContaCapitalDao.alterarLancamentoContaCapital(obj);	
		}catch (ReplicacaoContaCapitalDb2Exception e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_CRUD_TABELA_LANCAMENTOCONTACAPITAL",e.getMessage(),e);
		}catch (BancoobException e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception(e.getMessage(),e);
		}
	}

	/**
	 * @see ReplicacaoContaCapitalServicoLocal#excluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO)
	 */	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)	
	public void excluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj)throws BancoobException {
		try{
			replicacaoContaCapitalDao.excluirDocumentoContaCapital(obj);
			replicacaoContaCapitalDao.excluirPropostaContaCapital(obj);
			replicacaoContaCapitalDao.excluirDebitoContaCapital(obj);
			replicacaoContaCapitalDao.excluirContaCapital(obj);	
		}catch (ReplicacaoContaCapitalDb2Exception e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_CRUD_TABELA_CONTACAPITAL",e.getMessage(),e);
		}catch (BancoobException e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception(e.getMessage(),e);
		}		
	}

	/**
	 * @see ReplicacaoContaCapitalServicoLocal#excluirParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO)
	 */	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)	
	public void excluirParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO obj)throws BancoobException {
		try{
			replicacaoContaCapitalDao.excluirParcelamentoContaCapital(obj);
		}catch (ReplicacaoContaCapitalDb2Exception e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_CRUD_TABELA_PARCELAMENTOCONTACAPITAL",e.getMessage(),e);
		}catch (BancoobException e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception(e.getMessage(),e);
		}
	}

	/**
	 * @see ReplicacaoContaCapitalServicoLocal#excluirLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO)
	 */	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)	
	public void excluirLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj)throws BancoobException {
		try{
			replicacaoContaCapitalDao.excluirLancamentoContaCapital(obj);
		}catch (ReplicacaoContaCapitalDb2Exception e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_CRUD_TABELA_LANCAMENTOCONTACAPITAL",e.getMessage(),e);
		}catch (BancoobException e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception(e.getMessage(),e);
		}		
	}
	
	/**
	 * @see ReplicacaoContaCapitalServicoLocal#prepararCooperativaPiloto(Integer)
	 */
	public void prepararCooperativaPiloto(Integer cooperativa) throws BancoobException {
		if (InformacoesUsuario.getInstance().getLogin() == null) {
			throw new ContaCapitalReplicacaoException("Acesso negado.");
		}
		if (isCooperativaPilotoValida(cooperativa)) {
			Integer instituicao = instituicaoIntegracaoServico.obterIdInstituicao(cooperativa);
			// db2
			replicacaoContaCapitalDao.prepararCooperativaPiloto(instituicao);
			// sql
			replicacaoContaCapitalLegadoServico.prepararCooperativaPiloto(cooperativa);
		} else {
			throw new ReplicacaoContaCapitalDb2NegocioException("Esta cooperativa já está configurada como piloto.");
		}
	}
	
	private boolean isCooperativaPilotoValida(Integer cooperativa) throws BancoobException {
		return !recuperarCooperativasPiloto().contains(cooperativa);
	}
	
	private List<Integer> recuperarCooperativasPiloto() throws BancoobException {
		List<Integer> cooperativas = new ArrayList<Integer>();
		String paramCoopsPiloto = recuperarValorCooperativasPiloto();
		if (paramCoopsPiloto != null && paramCoopsPiloto.length() > 0) {
			String[] valores = paramCoopsPiloto.split(",");
			for (String coop : valores) {
				try {
					cooperativas.add(Integer.valueOf(coop));
				} catch (NumberFormatException nfe) {
					throw new ReplicacaoContaCapitalDb2NegocioException("Erro ao interpretar parâmetro de cooperativas piloto.");
				}
			}
		}
		return cooperativas;
	}

	private String recuperarValorCooperativasPiloto() throws BancoobException {
		List<ReplicacaoConfiguracaoLegadoDTO> confs = replicacaoContaCapitalLegadoServico.consultarListaConfiguracaoReplicacao();
		for (ReplicacaoConfiguracaoLegadoDTO conf : confs) {
			if (ReplicacaoLegadoConstantes.PARAM_COOP_PILOTO_REPLICACAO.equals(conf.getIdConfiguracaoReplicacaoCCA())) {
				return conf.getDescConfiguracaoReplicacao();
			}
		}
		return "";
	}

	/**
	 * @see ReplicacaoContaCapitalServicoLocal#consultarCooperativaPiloto(Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Map<String, Map<String, Long>> consultarCooperativaPiloto(Integer cooperativa) throws BancoobException {
		Map<String, Map<String, Long>> map = new HashMap<String, Map<String,Long>>();
		
		Integer instituicao = instituicaoIntegracaoServico.obterIdInstituicao(cooperativa);
		map.put("db2", replicacaoContaCapitalDao.consultarCooperativaPiloto(instituicao));
		map.put("sql", replicacaoContaCapitalLegadoServico.consultarCooperativaPiloto(cooperativa));
		
		return map;
	}
	
	/**
	 * @see br.com.sicoob.cca.replicacao.negocio.servicos.ReplicacaoContaCapitalServico#verificarCooperativaComDivergenciasBatimentos(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@SuppressWarnings("unchecked")
	public boolean verificarCooperativaComDivergenciasBatimentos(Integer cooperativa) throws BancoobException {
		ExecutorBatimentos executor = new ExecutorBatimentos();
		Integer idInstituicao = instituicaoIntegracaoServico.obterIdInstituicao(cooperativa);
		
		List<RegistroBatimentoContaCapital> registrosContaCapitalDB2 = replicacaoContaCapitalDao.consultarBatimentosContaCapital(idInstituicao);
		List<RegistroBatimentoLancamento> registrosLancamentoDB2 = replicacaoContaCapitalDao.consultarBatimentosLancamento(idInstituicao);
		List<RegistroBatimentoParcelamento> registrosParcelamentoDB2 = replicacaoContaCapitalDao.consultarBatimentosParcelamento(idInstituicao);
		List<RegistroBatimentoContaCapital> registrosContaCapitalSQL = 
				RegistroBatimentoAdapter.adaptarRegistroBatimentoContaCapital(replicacaoContaCapitalLegadoServico.consultarBatimentosContaCapital(cooperativa), idInstituicao);
		List<RegistroBatimentoLancamento> registrosLancamentoSQL = 
				RegistroBatimentoAdapter.adaptarRegistroBatimentoLancamento(replicacaoContaCapitalLegadoServico.consultarBatimentosLancamento(cooperativa), idInstituicao);
		List<RegistroBatimentoParcelamento> registrosParcelamentoSQL = 
				RegistroBatimentoAdapter.adaptarRegistroBatimentoParcelamento(replicacaoContaCapitalLegadoServico.consultarBatimentosParcelamento(cooperativa), idInstituicao);
		
		List<RegistroBatimentoContaCapital> batimentosContaCapital = executor.realizarBatimentos(registrosContaCapitalSQL, registrosContaCapitalDB2);
		List<RegistroBatimentoLancamento> batimentosLancamento = executor.realizarBatimentos(registrosLancamentoSQL, registrosLancamentoDB2);
		List<RegistroBatimentoParcelamento> batimentosParcelamento = executor.realizarBatimentos(registrosParcelamentoSQL, registrosParcelamentoDB2);
		
		return executor.possuiDivergencias(batimentosContaCapital, batimentosLancamento, batimentosParcelamento);
	}
	
	/**
	 * @see ReplicacaoContaCapitalServicoLocal#gerarRelatorioBatimentoSQLxDB2(Integer)
	 */	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@SuppressWarnings("unchecked")
	public Object gerarRelatorioBatimentoSQLxDB2(List<Integer> cooperativas) throws BancoobException {
		ExecutorBatimentos executor = new ExecutorBatimentos();
		List<Integer> cooperativasConsulta = inicializarCooperativasBatimento(cooperativas);
		
		RelBatimentoRaiz raiz = new RelBatimentoRaiz("CCA_Relatorio_Batimento_SQL_DB2.jasper");
		
		for (Integer cooperativa : cooperativasConsulta) {
			Integer idInstituicao = instituicaoIntegracaoServico.obterIdInstituicao(cooperativa);
			
			List<RegistroBatimentoContaCapital> registrosContaCapitalDB2 = replicacaoContaCapitalDao.consultarBatimentosContaCapital(idInstituicao);
			List<RegistroBatimentoLancamento> registrosLancamentoDB2 = replicacaoContaCapitalDao.consultarBatimentosLancamento(idInstituicao);
			List<RegistroBatimentoParcelamento> registrosParcelamentoDB2 = replicacaoContaCapitalDao.consultarBatimentosParcelamento(idInstituicao);
			
			List<RegistroBatimentoContaCapital> registrosContaCapitalSQL = 
					RegistroBatimentoAdapter.adaptarRegistroBatimentoContaCapital(replicacaoContaCapitalLegadoServico.consultarBatimentosContaCapital(cooperativa), idInstituicao);
			List<RegistroBatimentoLancamento> registrosLancamentoSQL = 
					RegistroBatimentoAdapter.adaptarRegistroBatimentoLancamento(replicacaoContaCapitalLegadoServico.consultarBatimentosLancamento(cooperativa), idInstituicao);
			List<RegistroBatimentoParcelamento> registrosParcelamentoSQL = 
					RegistroBatimentoAdapter.adaptarRegistroBatimentoParcelamento(replicacaoContaCapitalLegadoServico.consultarBatimentosParcelamento(cooperativa), idInstituicao);
			
			List<RegistroBatimentoContaCapital> batimentosContaCapital = executor.realizarBatimentos(registrosContaCapitalSQL, registrosContaCapitalDB2);
			List<RegistroBatimentoLancamento> batimentosLancamento = executor.realizarBatimentos(registrosLancamentoSQL, registrosLancamentoDB2);
			List<RegistroBatimentoParcelamento> batimentosParcelamento = executor.realizarBatimentos(registrosParcelamentoSQL, registrosParcelamentoDB2);
			
			if (executor.possuiDivergencias(batimentosContaCapital, batimentosLancamento, batimentosParcelamento)) {
				adicionarSecaoResumos(raiz, cooperativa, idInstituicao);
				adicionarSecaoBatimentos(raiz, cooperativa, batimentosContaCapital, batimentosLancamento, batimentosParcelamento);
			}
		}
		
		if (raiz.getResumos().isEmpty()) {
			throw new ReplicacaoContaCapitalDb2NegocioException("Nenhuma divergência encontrada!");
		}
		
		return criarObjetoRelatorio(raiz);
	}

	private Object criarObjetoRelatorio(RelBatimentoRaiz relatorio) throws BancoobException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("cca_replicacao.propriedades");
		String caminhologosicoob = propriedades.getProperty("cca.caminhologosicoob");
		parametros.put("IMAGEM_SICOOB", ContaCapitalUtil.recuperarImagemRelatorio(caminhologosicoob));
		parametros.put("USUARIO", InformacoesUsuario.getInstance().getLogin());
		relatorio.addParametros(parametros);
		relatorio.configurarParametrosBatimento();
		return relatorio;
	}

	private void adicionarSecaoBatimentos(RelBatimentoRaiz raiz, Integer cooperativa, List<RegistroBatimentoContaCapital> batimentosContaCapital,
			List<RegistroBatimentoLancamento> batimentosLancamento, List<RegistroBatimentoParcelamento> batimentosParcelamento) {
		RelBatimentoCooperativa relBatimentoCooperativa = new RelBatimentoCooperativa();
		relBatimentoCooperativa.setCooperativa(cooperativa);
		relBatimentoCooperativa.setBatimentosContaCapital(batimentosContaCapital);
		relBatimentoCooperativa.setBatimentosLancamento(batimentosLancamento);
		relBatimentoCooperativa.setBatimentosParcelamento(batimentosParcelamento);
		raiz.addBatimentoCooperativa(relBatimentoCooperativa);
	}

	private void adicionarSecaoResumos(RelBatimentoRaiz raiz, Integer coop, Integer idInstituicao) throws BancoobException {
		InstituicaoIntegracaoDTO instituicaoDTO = instituicaoIntegracaoServico.obterInstituicaoIntegracao(idInstituicao);
		RelBatimentoResumo resumo = new RelBatimentoResumo();
		resumo.setCooperativa(coop);
		resumo.setIdInstituicao(idInstituicao);
		resumo.setNomeCooperativa(instituicaoDTO.getSiglaInstituicao());
		raiz.addResumo(resumo);
	}

	private List<Integer> inicializarCooperativasBatimento(List<Integer> cooperativasParam)	throws BancoobException {
		List<Integer> cooperativas = null;
		if (cooperativasParam == null) {
			cooperativas = recuperarCooperativasPiloto();
			if (cooperativas.isEmpty()) {
				// recupera todas as cooperativas
				cooperativas = replicacaoContaCapitalLegadoServico.consultarCooperativasConciliacao();
			}
		} else {
			cooperativas = cooperativasParam;
		}
		return cooperativas;
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.negocio.servicos.ReplicacaoContaCapitalServico#consultarBatimentoSaldos(java.lang.Integer)
	 */
	public List<BatimentoSaldoDTO> consultarBatimentoSaldos(Integer cooperativa) throws BancoobException {
		Integer idInstituicao = instituicaoIntegracaoServico.obterIdInstituicao(cooperativa);
		List<BatimentoSaldoDTO> saldosDB2 = replicacaoContaCapitalDao.consultarBatimentoSaldos(idInstituicao);
		List<BatimentoSaldoLegadoDTO> saldosSQL = replicacaoContaCapitalLegadoServico.consultarBatimentoSaldosLegado(cooperativa);
		Map<Integer, BatimentoSaldoLegadoDTO> mapSQL = transformarParaMapaBatimentoSQL(saldosSQL);
		for (Iterator<BatimentoSaldoDTO> iteratorDB2 = saldosDB2.iterator(); iteratorDB2.hasNext();) {
			BatimentoSaldoDTO dtoDB2 = iteratorDB2.next();
			BatimentoSaldoLegadoDTO dtoSQL = mapSQL.get(dtoDB2.getNumMatricula());
			if (dtoSQL != null && dtoDB2.getNumMatricula().equals(dtoSQL.getNumMatricula())
					&& existeDivergenciaSaldos(dtoDB2, dtoSQL)) {
				dtoDB2.setValorSaldoIntegLegado(dtoSQL.getValorSaldoInteg());
				dtoDB2.setValorSaldoSubscLegado(dtoSQL.getValorSaldoSubsc());
				dtoDB2.setValorSaldoDevolLegado(dtoSQL.getValorSaldoDevol());
				dtoDB2.setValorSaldoBloqLegado(dtoSQL.getValorSaldoBloq());
			}
			if (dtoDB2.getValorSaldoSubscLegado() == null) {
				iteratorDB2.remove();
			}
		}
		return saldosDB2;
	}

	private Map<Integer, BatimentoSaldoLegadoDTO> transformarParaMapaBatimentoSQL(List<BatimentoSaldoLegadoDTO> saldosSQL) {
		Map<Integer, BatimentoSaldoLegadoDTO> map = new HashMap<Integer, BatimentoSaldoLegadoDTO>();
		for (BatimentoSaldoLegadoDTO dto : saldosSQL) {
			map.put(dto.getNumMatricula(), dto);
		}
		return map;
	}

	private boolean existeDivergenciaSaldos(BatimentoSaldoDTO dtoDB2, BatimentoSaldoLegadoDTO dtoSQL) {
		return dtoDB2.getValorSaldoInteg().compareTo(dtoSQL.getValorSaldoInteg()) != 0
				|| dtoDB2.getValorSaldoSubsc().compareTo(dtoSQL.getValorSaldoSubsc()) != 0
				|| dtoDB2.getValorSaldoDevol().compareTo(dtoSQL.getValorSaldoDevol()) != 0
				|| dtoDB2.getValorSaldoBloq().compareTo(dtoSQL.getValorSaldoBloq()) != 0;
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.negocio.servicos.ReplicacaoContaCapitalServico#replicarDebitoIndeterminado(br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaContaCapitalLegadoDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void replicarDebitoIndeterminado(ReplicacaoTabelaContaCapitalLegadoDTO obj, String codAcao) throws BancoobException {
		if (obj.getBolDebIndeterminado() == null) {
			return;
		}
		try{
			replicacaoContaCapitalDao.replicarDebitoIndeterminado(obj, codAcao);	
		}catch (ReplicacaoContaCapitalDb2Exception e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_CRUD_TABELA_DEBITOINDETERMINADO",e.getMessage(),e);
		}catch (BancoobException e) {
			sessionContext.setRollbackOnly();
			throw new ReplicacaoContaCapitalDb2Exception(e.getMessage(),e);
		}
	}

}

package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.dto.ContaCapitalResumoDTO;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.HistoricoLancamentoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.LancamentoCCADTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoContaCapitalServicoRemote;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDaoIF;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDaoFactory;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.HistoricoLancamentoContaCapitalDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.LancamentoContaCapitalDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.CapaLoteCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.LancamentosCCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
 * EJB contendo servicos relacionados a LancamentoContaCapital.
 *
 * @author Antonio.Genaro
 */
@Stateless
@Local (LancamentoContaCapitalServicoLocal.class)
@Remote(LancamentoContaCapitalServicoRemote.class) 
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LancamentoContaCapitalServicoEJB extends ContaCapitalMovimentacaoCrudServicoEJB<LancamentoContaCapital> implements LancamentoContaCapitalServicoLocal, LancamentoContaCapitalServicoRemote {
	
	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private LancamentoContaCapitalDao lancamentoContaCapitalDao;	
	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private HistoricoLancamentoContaCapitalDao historicoLancamentoContaCapitalDao;
	
	@EJB
	private CapaLoteCapitalLegadoServicoLocal capaLoteCapitalLegadoServico;
	
	@EJB
	private LancamentosCCapitalLegadoServicoLocal lancamentosCCapitalLegadoServico;	
	
	@EJB
	private ProdutoLegadoServicoLocal prodLegadoServico;	
	
	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;

	/**
	 * Recupera o valor de DAO.
	 *
	 * @return o valor de DAO
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ejb.ContaCapitalCadastroCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalMovimentacaoCrudDaoIF<LancamentoContaCapital> getDAO() {
		return lancamentoContaCapitalDao;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LancamentoContaCapital incluir(LancamentoContaCapital lancamentoContaCapital) throws BancoobException {
		LancamentoContaCapital lancamentoContaCapitalInc;
		
		try{
			CapaLoteCapitalLegado capaLoteCapitalLegado = montarCapaLoteCapitalLegado(lancamentoContaCapital.getTipoLote().getId());
						
			if (capaLoteCapitalLegado.getBolNovo()){
				capaLoteCapitalLegadoServico.incluir(capaLoteCapitalLegado);
			}
			
			Integer numSeqLanc = lancamentosCCapitalLegadoServico.obterUltimoNumSeqLanc(capaLoteCapitalLegado)+1;
			lancamentoContaCapital.setNumSeqLanc(numSeqLanc);
						
			lancamentosCCapitalLegadoServico.incluir(montarLancamentosCCapitalLegado(lancamentoContaCapital, capaLoteCapitalLegado, numSeqLanc));
			
			capaLoteCapitalLegadoServico.atualizarCapaLote(Integer.valueOf(InformacoesUsuario.getInstance().getCooperativa()), 
					capaLoteCapitalLegado.getCapaLoteCapitalLegadoPK().getDataLote(), 
					capaLoteCapitalLegado.getCapaLoteCapitalLegadoPK().getNumLoteLanc());
			
			lancamentoContaCapitalInc = lancamentoContaCapitalDao.incluir(lancamentoContaCapital);		
			incluirHistoricoLancamentoCCA(lancamentoContaCapitalInc);

		} catch (ContaCapitalMovimentacaoNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
			
		}  catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_003");
		}			
		
		return lancamentoContaCapitalInc;
	}
	
	/**
	 * O método Incluir historico lancamento cca.
	 *
	 * @param lancamentoContaCapital o valor de lancamento conta capital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void incluirHistoricoLancamentoCCA(LancamentoContaCapital lancamentoContaCapital) throws BancoobException{
		
		HistoricoLancamentoCCA historicoLancamentoCCA = new HistoricoLancamentoCCA();
		
		historicoLancamentoCCA.setDataHoraAtualizacao(new DateTimeDB());
		historicoLancamentoCCA.setDataLancamento(lancamentoContaCapital.getDataLancamento());
		historicoLancamentoCCA.setDescNumDocumento(lancamentoContaCapital.getDescNumDocumento());
		historicoLancamentoCCA.setId(lancamentoContaCapital.getId());
		historicoLancamentoCCA.setIdContaCapital(lancamentoContaCapital.getContaCapital().getId());
		historicoLancamentoCCA.setIdHistLancEstornoContaCapital(lancamentoContaCapital.getIdLancamentoContaCapitalEstorno());
		historicoLancamentoCCA.setIdInstituicao(lancamentoContaCapital.getIdInstituicao());
		historicoLancamentoCCA.setIdTipoHistorico(lancamentoContaCapital.getTipoHistoricoCCA().getId());
		if (lancamentoContaCapital.getTipoHistoricoEstorno() != null) {
			historicoLancamentoCCA.setIdTipoHistoricoEstorno(lancamentoContaCapital.getTipoHistoricoEstorno().getId());
		}
		if(lancamentoContaCapital.getTipoSubscricao()!=null){
			historicoLancamentoCCA.setIdTipoSubscricao(lancamentoContaCapital.getTipoSubscricao().getId());			
		}
		historicoLancamentoCCA.setIdUsuario(lancamentoContaCapital.getIdUsuario());
		historicoLancamentoCCA.setValorLancamento(lancamentoContaCapital.getValorLancamento());
		historicoLancamentoCCA.setIdTipoLote(lancamentoContaCapital.getTipoLote().getId());
		historicoLancamentoCCA.setNumSeqLanc(lancamentoContaCapital.getNumSeqLanc());
		
		historicoLancamentoContaCapitalDao.incluir(historicoLancamentoCCA);		
	}		
	
	/**
	 * Montar capa lote capital legado.
	 *
	 * @param idTipoLote o valor de id tipo lote
	 * @return CapaLoteCapitalLegado
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private CapaLoteCapitalLegado montarCapaLoteCapitalLegado(Short idTipoLote) throws BancoobException {

		CapaLoteCapitalLegado entidade = null;		
		CapaLoteCapitalLegadoPK entidadePK = montarCapaLoteCapitalLegadoPK(idTipoLote);		
		CapaLoteCapitalLegado entidadeConsulta = capaLoteCapitalLegadoServico.obter(entidadePK); 
		
		if(entidadeConsulta != null){
			entidade = entidadeConsulta;
			entidade.setBolNovo(false);
		}else{
			entidade = new CapaLoteCapitalLegado();
			entidade.setCapaLoteCapitalLegadoPK(entidadePK);
			entidade.setBolAtualizado(false);
			entidade.setCodOrigemLote(null);
			entidade.setiDProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
			entidade.setiDProdutoEst(null);
			entidade.setiDTipoHistoricoEstorno(null);
			entidade.setiDTipoHistoricoLanc(ContaCapitalConstantes.COD_HISTORICO_CCA_SUBSCRICAO);
			entidade.setQtdLancApu(0);
			entidade.setQtdLancInf(0);
			entidade.setValorTotalLoteApu(BigDecimal.ZERO);
			entidade.setValorTotalLoteInf(BigDecimal.ZERO);
			entidade.setBolNovo(true);			
		}
		
		return entidade;
	}	
	
	/**
	 * Montar capa lote capital legado pk.
	 *
	 * @param idTipoLote o valor de id tipo lote
	 * @return CapaLoteCapitalLegadoPK
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private CapaLoteCapitalLegadoPK montarCapaLoteCapitalLegadoPK(Short idTipoLote) throws BancoobException{
		
		DateTimeDB dataAtualProduto = new DateTimeDB(prodLegadoServico.obterDataAtualProdutoCCALogado().getTime());
		CapaLoteCapitalLegadoPK entidadePK = new CapaLoteCapitalLegadoPK();
		entidadePK.setDataLote(dataAtualProduto);
		if(idTipoLote > 0 ){
			entidadePK.setNumLoteLanc(idTipoLote.intValue());				
		}else{
			entidadePK.setNumLoteLanc(ContaCapitalConstantes.COD_LOTE_CCA_PARC_AVISTA);				
		}
		return entidadePK;
	}	

	/**
	 * Montar lancamentos c capital legado.
	 *
	 * @param lancamentoContaCapital o valor de lancamento conta capital
	 * @param capaLoteCapitalLegado o valor de capa lote capital legado
	 * @param numSeqLanc o valor de num seq lanc
	 * @return LancamentosCCapitalLegado
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentosCCapitalLegado montarLancamentosCCapitalLegado(LancamentoContaCapital lancamentoContaCapital, CapaLoteCapitalLegado capaLoteCapitalLegado, Integer numSeqLanc) throws BancoobException{
		Integer novoNumSeqLanc = (numSeqLanc == null) ? (lancamentosCCapitalLegadoServico.obterUltimoNumSeqLanc(capaLoteCapitalLegado)+1) : numSeqLanc; 
		
		LancamentosCCapitalLegado entidade = new LancamentosCCapitalLegado();
		LancamentosCCapitalLegadoPK entidadePK = new LancamentosCCapitalLegadoPK();			
		entidadePK.setCapaLoteCapitalLegado(capaLoteCapitalLegado);
		
		ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();	
		contaCapitalLegado.setNumMatricula(lancamentoContaCapital.getContaCapital().getNumContaCapital());		
		
		entidadePK.setNumSeqLanc(novoNumSeqLanc); 
		entidade.setDescNumDocumento(lancamentoContaCapital.getDescNumDocumento());
		entidade.setiDTipoHistoricoLanc(lancamentoContaCapital.getTipoHistoricoCCA().getId().intValue());		
		entidade.setLancamentosCCapitalLegadoPK(entidadePK);
		entidade.setContaCapitalLegado(contaCapitalLegado);
		entidade.setBolAtualizado(false);
		entidade.setCodMotivoDevolucao(null);
		entidade.setDataHoraInclusao(new DateTimeDB());
		entidade.setDescObsDevolucao(null);
		entidade.setiDProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
		entidade.setiDProdutoEst(null);
		entidade.setiDTipoHistoricoEstorno(null);				
		entidade.setiDUsuarioResp(lancamentoContaCapital.getIdUsuario());
		entidade.setValorLanc(lancamentoContaCapital.getValorLancamento());	
		if(lancamentoContaCapital.getTipoSubscricao()!=null){
			entidade.setIdTipoSubscricao(lancamentoContaCapital.getTipoSubscricao().getId());			
		}
		entidade.setIdLancamentoContaCapital(ContaCapitalConstantes.IDLANCAMENTOCONTACAPITAL_NAOREPLICA);
		
		return entidade;
	}		
	
	/**
	 * {@link LancamentoContaCapitalServicoRemote#pesquisarLancamentosDoDiaPorContaCapital(Integer)}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<LancamentoContaCapital> pesquisarLancamentosDoDiaPorContaCapital(Integer idContaCapital) throws BancoobException {
		Date dataProduto = null;
		List<LancamentoContaCapital> lista = null;
		try {
			Integer idInstituicao = contaCapitalServico.obterIdInstituicaoPorIdContaCapital(idContaCapital);
			dataProduto = prodLegadoServico.obterDataAtualProduto(
					ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, 
					idInstituicao);
			lista = lancamentoContaCapitalDao.pesquisarLancamentosDoDiaPorContaCapital(idContaCapital, idInstituicao, dataProduto);	
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}
		return lista;
	}
	
	private List<LancamentoCCADTO> pesquisarLancamentosDoDiaPorContaCapitalSimplificado(Integer idContaCapital, Integer idInstituicao) throws BancoobException {
		Date dataProduto = null;
		List<LancamentoCCADTO> lista = null;
		try {
			dataProduto = prodLegadoServico.obterDataAtualProduto(
					ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, 
					idInstituicao);
			lista = lancamentoContaCapitalDao.pesquisarLancamentosDoDiaPorContaCapitalSimplificado(idContaCapital, idInstituicao, dataProduto);	
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}
		return lista;
	}
	
	/**
	 * {@link LancamentoContaCapitalServicoRemote#pesquisarLancamentosDoDiaPorContaCapital(Integer)}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<LancamentoContaCapital> pesquisarLancamentosPorContaCapital(Integer idContaCapital) throws BancoobException {
		List<LancamentoContaCapital> lista = null;
		try {
			Integer idInstituicao = contaCapitalServico.obterIdInstituicaoPorIdContaCapital(idContaCapital);
			lista = lancamentoContaCapitalDao.pesquisarLancamentosPorContaCapital(idContaCapital, idInstituicao);	
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}
		return lista;
	}	
	
	
	/**
	 * {@link LancamentoContaCapitalServicoRemote#pesquisarCountLancamentosPorContaCapitalSubscricao(Integer, Integer)}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idInstituicao o valor de id instituicao
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Integer pesquisarCountLancamentosPorContaCapitalSubscricao(Integer idContaCapital) throws BancoobException {
		try {
			Integer idInstituicao = contaCapitalServico.obterIdInstituicaoPorIdContaCapital(idContaCapital);
			return lancamentoContaCapitalDao.pesquisarCountLancamentosPorContaCapitalSubscricao(idContaCapital, idInstituicao);	
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}
	}		
	
	/**
	 * {@link LancamentoContaCapitalServicoRemote#pesquisarLancamentosDoDiaTipoHistContaCapital(Integer, Integer, Integer,Date)}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idInstituicao o valor de id instituicao
	 * @param idTipoHistorico o valor de id tipo historico
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<LancamentoContaCapital> pesquisarLancamentosDoDiaTipoHistContaCapital(Integer idContaCapital, Integer idInstituicao, Integer idTipoHistorico) throws BancoobException {
		Date dataProduto = null;
		List<LancamentoContaCapital> lista = null;
		try {
			dataProduto = prodLegadoServico.obterDataAtualProduto(
					ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, 
					idInstituicao);
			lista = lancamentoContaCapitalDao.pesquisarLancamentosDoDiaTipoHistContaCapital(idContaCapital, idInstituicao, idTipoHistorico, dataProduto);	
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}
		return lista;
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoContaCapitalServico#calcularValorSubscrito(java.lang.Integer)
	 */
	public BigDecimal calcularValorSubscrito(Integer idContaCapital) throws BancoobException {
		ContaCapitalResumoDTO cca = contaCapitalServico.obterResumo(idContaCapital);
		List<LancamentoCCADTO> lancamentos = pesquisarLancamentosDoDiaPorContaCapitalSimplificado(idContaCapital, cca.getIdInstituicao());
		return cca.getValorSubs().add(somarValorLancamentosPeloGrupoHistorico(lancamentos, ContaCapitalConstantes.COD_GRUPO_HIST_SUBSCRICAO));
	}	

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoContaCapitalServico#calcularValorIntegralizado(java.lang.Integer)
	 */
	public BigDecimal calcularValorIntegralizado(Integer idContaCapital) throws BancoobException {
		ContaCapitalResumoDTO cca = contaCapitalServico.obterResumo(idContaCapital);
		List<LancamentoCCADTO> lancamentos = pesquisarLancamentosDoDiaPorContaCapitalSimplificado(idContaCapital, cca.getIdInstituicao());
		return cca.getValorInteg().add(somarValorLancamentosPeloGrupoHistorico(lancamentos, ContaCapitalConstantes.COD_GRUPO_HIST_INTEGRALIZACAO));
	}	
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoContaCapitalServico#calcularValorDevolucao(java.lang.Integer)
	 */
	public BigDecimal calcularValorDevolucao(Integer idContaCapital) throws BancoobException {
		ContaCapitalResumoDTO cca = contaCapitalServico.obterResumo(idContaCapital);
		List<LancamentoCCADTO> lancamentos = pesquisarLancamentosDoDiaPorContaCapitalSimplificado(idContaCapital, cca.getIdInstituicao());
		return cca.getValorDevol().add(somarValorLancamentosPeloGrupoHistorico(lancamentos, ContaCapitalConstantes.COD_GRUPO_HIST_DEVOLUCAO));
	}
	
	/**
	 * Soma os valores dos lancamentos pelo codigo do grupo historico.
	 * @param lancamentos
	 * @param codGrupo
	 * @return
	 * @throws BancoobException
	 */
	private BigDecimal somarValorLancamentosPeloGrupoHistorico(List<LancamentoCCADTO> lancamentos, Integer codGrupo) throws BancoobException {
		BigDecimal somatorio = BigDecimal.ZERO;
		for (LancamentoCCADTO lanc : lancamentos) {
			if (lanc.getIdGrupoHistorico().equals(codGrupo)) {
				if (ContaCapitalConstantes.COD_LANC_OPERACAO_CREDITO.equals(lanc.getCodNaturezaOperacao())) {
					somatorio = somatorio.add(lanc.getValorLancamento());	
				} else if (ContaCapitalConstantes.COD_LANC_OPERACAO_DEBITO.equals(lanc.getCodNaturezaOperacao())) {
					somatorio = somatorio.subtract(lanc.getValorLancamento());	
				} else if (ContaCapitalConstantes.COD_LANC_OPERACAO_ESTORNO.equals(lanc.getCodNaturezaOperacao())
						&& lanc.getIdTipoHistoricoEstorno() != null) {
					if (ContaCapitalConstantes.COD_LANC_OPERACAO_CREDITO.equals(lanc.getCodNaturezaOperacaoEstorno())) {
						somatorio = somatorio.subtract(lanc.getValorLancamento());
					} else {
						somatorio = somatorio.add(lanc.getValorLancamento());
					}
				}
				
			}
		}
		return somatorio;
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoContaCapitalServico#incluirEmLote(java.lang.List)
	 */	
	public void incluirEmLote(List<LancamentoContaCapital> lancamentos) throws BancoobException {
		lancamentoContaCapitalDao.incluirEmLote(lancamentos);
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoContaCapitalServico#rodar(java.lang.Integer)
	 */	
	public void rodar(Integer numCoop) throws BancoobException {		
		Integer idInstituicao = instituicaoIntegracaoServico.obterIdInstituicao(numCoop);		
		DateTimeDB dataAtualProd = new DateTimeDB(prodLegadoServico.obterDatasProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, numCoop).getDataAtualProd().getTime());		
		lancamentoContaCapitalDao.atualizarMovimentoLancamentos(idInstituicao, dataAtualProd);
		lancamentoContaCapitalDao.atualizarLancamentosMovimento(idInstituicao, dataAtualProd);
	}
		
}
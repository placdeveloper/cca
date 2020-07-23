package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.negocio.annotation.LogCCA;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.MotivoDevolucao;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoLote;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoParcelamento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumMetodoOperacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumMotivoDevolucao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoParcelamento;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalServicoRemote;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDaoIF;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDaoFactory;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ParcelamentoContaCapitalDao;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ParcelamentoCCALegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.TrabalhaLegadoServicoLocal;

/**
 * EJB contendo servicos relacionados a ParcelamentoContaCapital.
 *
 * @author Antonio.Genaro
 */
@Stateless
@Local (ParcelamentoContaCapitalServicoLocal.class)
@Remote(ParcelamentoContaCapitalServicoRemote.class) 
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ParcelamentoContaCapitalServicoEJB extends ContaCapitalMovimentacaoCrudServicoEJB<Parcelamento> implements ParcelamentoContaCapitalServicoLocal, ParcelamentoContaCapitalServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private ParcelamentoContaCapitalDao parcelamentoContaCapitalDao;	

	@Resource
	private SessionContext context;
	
	@EJB
	private ProdutoLegadoServicoLocal prodLegadoServico;	
	
	@EJB
	private LancamentoContaCapitalServicoLocal lancamentoContaCapitalServico;
	
	@EJB
	private ParcelamentoCCALegadoServicoLocal parcelamentoCCALegadoServico;
	
	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;
	
	@EJB
	private TrabalhaLegadoServicoLocal trabalhaLegadoServico;
	
	@EJB
	private FechamentoContaCapitalServicoLocal fechamentoServico;
	
	/**
	 * Recupera o valor de DAO.
	 *
	 * @return o valor de DAO
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ejb.ContaCapitalCadastroCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalMovimentacaoCrudDaoIF<Parcelamento> getDAO() {
		return parcelamentoContaCapitalDao;
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ParcelamentoRenDTO> pesquisarParcelamentos(Integer idContaCapital, Integer idTipoParcelamento) throws BancoobException {
		return parcelamentoContaCapitalDao.pesquisarParcelamentos(idContaCapital, idTipoParcelamento);
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@LogCCA(metodo=EnumMetodoOperacaoContaCapital.MOVIMENTACAO_PARCELAMENTOCONTACAPITALSERVICO_CANCELARPARCELAS)	
	public void cancelarParcelas(List<ParcelamentoRenDTO> listaParcelasDTO) throws BancoobException {
		try{
			ContaCapital contaCapital = null;
				
			validarDevolucaoCancelamentoLancamentosDoDiaLegado(listaParcelasDTO, true);
			
			for (ParcelamentoRenDTO parcela : listaParcelasDTO) {		
				
				if(parcela != null && parcela.getIdSituacaoParcelamento().shortValue() == ContaCapitalConstantes.COD_PARCELA_GERADA.shortValue()){
					
					Parcelamento parcelamento = parcelamentoContaCapitalDao.obter(parcela.getIdParcelamento());
					
					if(parcelamento.getSituacaoParcelamento().getId().shortValue() != ContaCapitalConstantes.COD_SITUACAO_EM_ABERTO.shortValue()) {
						throw new ContaCapitalMovimentacaoNegocioException("MSG_037");			
					}		
					
					parcelamento.setSituacaoParcelamento(new SituacaoParcelamento(ContaCapitalConstantes.COD_PARCELA_CANCELADA.shortValue()));
					parcelamento.setDataSituacao(new DateTimeDB(prodLegadoServico.obterDataAtualProdutoCCALogado().getTime()));
					
					parcelamentoContaCapitalDao.alterar(parcelamento);
					alterarParcelaLegado(parcelamento);
	
					if(parcelamento.getTipoParcelamento().getId().shortValue() == ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_INTEGRAL.shortValue()){
						incluirLancamentoContaCapital(parcelamento, ContaCapitalConstantes.COD_HISTORICO_CCA_CANCEL_SUBSCRICAO.shortValue());
					}else{
						
						contaCapital = contaCapitalServico.obter(parcelamento.getContaCapital().getId());
						
						if(contaCapital.getSituacaoContaCapital().getId().intValue() != EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo().intValue()
							|| contaCapital.getSituacaoCadastroProposta().getId().intValue() != EnumSituacaoCadastroProposta.COD_APROVADO.getCodigo().intValue()) {
							throw new ContaCapitalMovimentacaoNegocioException("MSG_036");			
						}		
						
						incluirLancamentoContaCapital(parcelamento, ContaCapitalConstantes.COD_HISTORICO_CCA_SUBSCRICAO.shortValue());
						incluirLancamentoContaCapital(parcelamento, ContaCapitalConstantes.COD_INTEGRALIZACAO_CANC_PARCELA_DEVOLUCAO.shortValue());
						incluirLancamentoContaCapital(parcelamento, ContaCapitalConstantes.CANCELAMENTO_PARCELA_DEVOLUCAO.shortValue());
					}
					
				}			
			}
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
		}  catch (BancoobException e) {
			context.setRollbackOnly();
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_003");
		}				
	}	
		
	/**
	 * Validacao para tratar erro quando o usuario cancela lancamentos do dia para o desligamento, deixando parcela de devolucao em aberto e depois cancelando pelo renovacao. 
	 * @param parcelas
	 * @throws BancoobException
	 */
	private void validarDevolucaoCancelamentoLancamentosDoDiaLegado(List<ParcelamentoRenDTO> parcelas, boolean deveCancelar) throws BancoobException {
		if (parcelas == null || parcelas.isEmpty()) {
			return;
		}
		ContaCapital contaCapital = contaCapitalServico.obter(parcelas.get(0).getIdContaCapital());
		if (!isContaCapitalAtiva(contaCapital)) {
			return;
		}
		for (Iterator<ParcelamentoRenDTO> iterator = parcelas.iterator(); iterator.hasNext();) {
			ParcelamentoRenDTO parcelaDTO = iterator.next();
			if (isParcelaDevolucaoEmAberto(parcelaDTO) && isMotivoDevolucaoPorContaCapitalInativa(parcelaDTO)) {
				if (deveCancelar) {
					Parcelamento parcelamento = parcelamentoContaCapitalDao.obter(parcelaDTO.getIdParcelamento());
					parcelamento.setSituacaoParcelamento(new SituacaoParcelamento(ContaCapitalConstantes.COD_PARCELA_EXCLUIDA.shortValue()));
					parcelamento.setDataSituacao(new DateTimeDB(prodLegadoServico.obterDataAtualProdutoCCALogado().getTime()));
					parcelamentoContaCapitalDao.alterar(parcelamento);
					alterarParcelaLegado(parcelamento);
					iterator.remove();
				} else {
					throw new ContaCapitalMovimentacaoNegocioException("MSG_DEVOLUCAO_CANCELAMENTO_LANCAMENTOS_DO_DIA_LEGADO"); 
				}
			}
		}
	}

	private boolean isContaCapitalAtiva(ContaCapital contaCapital) {
		return contaCapital.getSituacaoContaCapital().getId().intValue() == EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo().intValue();
	}

	private boolean isParcelaDevolucaoEmAberto(ParcelamentoRenDTO parcelaDTO) {
		return parcelaDTO.getIdTipoParcelamento().shortValue() == EnumTipoParcelamento.COD_TIPO_PARCELAMENTO_DEVOLUCAO.getCodigo().shortValue()
				&& parcelaDTO.getIdSituacaoParcelamento().shortValue() == EnumSituacaoParcelamento.COD_PARCELA_GERADA.getCodigo().shortValue();
	}

	private boolean isMotivoDevolucaoPorContaCapitalInativa(ParcelamentoRenDTO parcelaDTO) {
		Integer idMotivoDevolucao = parcelaDTO.getIdMotivoDevolucao() == null ? null : parcelaDTO.getIdMotivoDevolucao().intValue();
		return EnumMotivoDevolucao.COD_MOT_DEV_DEMISSAO.getCodigo().equals(idMotivoDevolucao)
				|| EnumMotivoDevolucao.COD_MOT_DEV_ELIMINACAO.getCodigo().equals(idMotivoDevolucao)
				|| EnumMotivoDevolucao.COD_MOT_DEV_EXCLUSAO.getCodigo().equals(idMotivoDevolucao);
	}

	/**
	 * O método Alterar parcela legado.
	 *
	 * @param parcelamento o valor de parcelamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void alterarParcelaLegado(Parcelamento parcelamento) throws BancoobException{
		ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();				
		contaCapitalLegado.setNumMatricula(parcelamento.getContaCapital().getNumContaCapital());
		
		ParcelamentoCCALegadoPK parcelamentoCCALegadoPK = new ParcelamentoCCALegadoPK();								
		parcelamentoCCALegadoPK.setContaCapitalLegado(contaCapitalLegado);
		parcelamentoCCALegadoPK.setNumParcela(parcelamento.getNumParcela().intValue());
		parcelamentoCCALegadoPK.setNumParcelamento(parcelamento.getNumParcelamento().intValue());
		parcelamentoCCALegadoPK.setCodTipoParcelamento(parcelamento.getTipoParcelamento().getId().intValue());
		
		ParcelamentoCCALegado parcelamentoCCALegado = parcelamentoCCALegadoServico.obter(parcelamentoCCALegadoPK);
		validarParcelamentoLegadoCanceladoDia(parcelamentoCCALegado);
		
		parcelamentoCCALegado.setCodSituacaoParcela(parcelamento.getSituacaoParcelamento().getId().intValue());
		parcelamentoCCALegado.setDataSituacaoParcela(parcelamento.getDataSituacao());
		parcelamentoCCALegado.setIdParcelamentoContaCapital(parcelamento.getId());
		
		parcelamentoCCALegadoServico.alterar(parcelamentoCCALegado);			
	}

	/**
	 * Valida se o parcelamento do legado não foi apagado por alguma operação do legado(ex: cancelar lancamentos do dia)
	 * @param parcelamentoCCALegado
	 * @throws BancoobException
	 */
	private void validarParcelamentoLegadoCanceladoDia(ParcelamentoCCALegado parcelamentoCCALegado) throws BancoobException{
		if (parcelamentoCCALegado == null) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_LANCAMENTO_REPLICACAO_EXECUCAO");
		}		
	}
	
	/**
	 * O método Incluir lancamento conta capital.
	 *
	 * @param parcelamento o valor de parcelamento
	 * @param idTipoHistoricoCCA o valor de id tipo historico cca
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void incluirLancamentoContaCapital(Parcelamento parcelamento, Short idTipoHistoricoCCA) throws BancoobException{
		
		DateTimeDB dataAtualProduto = new DateTimeDB(prodLegadoServico.obterDataAtualProdutoCCALogado().getTime());
		
		TipoLote tipoLote = new TipoLote(); 
		tipoLote.setId(ContaCapitalConstantes.COD_LOTE_CCA_PARC_AVISTA.shortValue());
		
		TipoHistoricoCCA tipoHistoricoCCA = new TipoHistoricoCCA(); 
		tipoHistoricoCCA.setId(idTipoHistoricoCCA);
		
		LancamentoContaCapital lancamentoContaCapitalInteg = new LancamentoContaCapital();		
		lancamentoContaCapitalInteg.setContaCapital(parcelamento.getContaCapital());
		lancamentoContaCapitalInteg.setDataLancamento(dataAtualProduto);
		lancamentoContaCapitalInteg.setIdInstituicao(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		lancamentoContaCapitalInteg.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
		lancamentoContaCapitalInteg.setTipoSubscricao(null);
		lancamentoContaCapitalInteg.setTipoLote(tipoLote);				
		lancamentoContaCapitalInteg.setBolProcessado(ContaCapitalConstantes.NUM_ZERO.shortValue());
		lancamentoContaCapitalInteg.setDataHoraAtualizacao(new DateTimeDB());
		lancamentoContaCapitalInteg.setValorLancamento(parcelamento.getValor());			
		lancamentoContaCapitalInteg.setDescNumDocumento(parcelamento.getContaCapital().getNumContaCapital().toString());	
		lancamentoContaCapitalInteg.setTipoHistoricoCCA(tipoHistoricoCCA);
	
		lancamentoContaCapitalServico.incluir(lancamentoContaCapitalInteg);				
	}	

	/**
	 * {@inheritDoc}
	 */
	@LogCCA(metodo=EnumMetodoOperacaoContaCapital.MOVIMENTACAO_PARCELAMENTOCONTACAPITALSERVICO_BAIXARPARCELAS)		
	public void baixarParcelas(List<ParcelamentoRenDTO> listaParcelasDTO) throws BancoobException {
		try{
				
			validarDevolucaoCancelamentoLancamentosDoDiaLegado(listaParcelasDTO, false);
			
			for (ParcelamentoRenDTO parcela : listaParcelasDTO) {		
				
				if(parcela != null && parcela.getIdSituacaoParcelamento().shortValue() == ContaCapitalConstantes.COD_PARCELA_GERADA.shortValue()){
					
					Parcelamento parcelamento = parcelamentoContaCapitalDao.obter(parcela.getIdParcelamento());
					
					if(parcelamento.getSituacaoParcelamento().getId().shortValue() != ContaCapitalConstantes.COD_SITUACAO_EM_ABERTO.shortValue()) {
						throw new ContaCapitalMovimentacaoNegocioException("MSG_038");			
					}		
					
					parcelamento.setSituacaoParcelamento(new SituacaoParcelamento(ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_CHADMIN.shortValue()));
					parcelamento.setDataSituacao(new DateTimeDB(prodLegadoServico.obterDataAtualProdutoCCALogado().getTime()));
					
					parcelamentoContaCapitalDao.alterar(parcelamento);
					alterarParcelaLegado(parcelamento);
	
					if(parcelamento.getTipoParcelamento().getId().shortValue() == ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_INTEGRAL.shortValue()){
						incluirLancamentoContaCapital(parcelamento, ContaCapitalConstantes.COD_HISTORICO_CCA_INTEG_BANCO.shortValue());
					}else{						
						incluirLancamentoContaCapital(parcelamento, ContaCapitalConstantes.COD_HISTORICO_CCA_DEVOLUCAO_CHQ_ADM.shortValue());
					}
					
				}			
			}
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
			
		}  catch (BancoobException e) {
			context.setRollbackOnly();
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_003");
		}				
	}		
	
	/**
	 * {@inheritDoc}
	 */
	public void gravarParcelas(List<ParcelamentoRenDTO> listaParcelasDTO) throws BancoobException {
		try{
				
			validaGravarParcelas(listaParcelasDTO);
			
			ParcelamentoRenDTO primeiraParcela = null;
			for (ParcelamentoRenDTO parcela : listaParcelasDTO) {
				if (primeiraParcela == null || parcela.getNumParcela().intValue() < primeiraParcela.getNumParcela().intValue()) {
					primeiraParcela = parcela;
				}
			}
			
			for (ParcelamentoRenDTO parcela : listaParcelasDTO) {							
				if(parcela != null){			
					if (primeiraParcela.getIdMotivoDevolucao() != null && primeiraParcela.getIdMotivoDevolucao().intValue() > 0) {
						parcela.setIdMotivoDevolucao(primeiraParcela.getIdMotivoDevolucao());
					}
					if(parcela.getIdParcelamento() != null && parcela.getIdSituacaoParcelamento().shortValue() == ContaCapitalConstantes.COD_PARCELA_EXCLUIDA.shortValue()){
						Parcelamento parcelamento = parcelamentoContaCapitalDao.obter(parcela.getIdParcelamento());						
						parcelamento.setSituacaoParcelamento(new SituacaoParcelamento(parcela.getIdSituacaoParcelamento()));
						parcelamentoContaCapitalDao.alterar(parcelamento);
						parcelamentoCCALegadoServico.alterar(montarParcelamentoCCALegado(parcelamento));																	
					}else if(parcela.getIdParcelamento() == null && parcela.getIdSituacaoParcelamento().shortValue() == ContaCapitalConstantes.COD_PARCELA_GERADA.shortValue()){
						Parcelamento parcelamento = montarParcelamento(parcela);
						parcelamentoContaCapitalDao.incluir(parcelamento);
						parcelamentoCCALegadoServico.incluir(montarParcelamentoCCALegado(parcelamento));																	
					}
				}			
			}			
			
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
		}  catch (BancoobException e) {
			context.setRollbackOnly();
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_003",e);
		}				
	}	
	
	/**
	 * {@inheritDoc}
	 */
	public Parcelamento incluirParcelas(Integer numContaCapital, Integer codTipoParcelamento, List<ParcelamentoRenDTO> listaParcelasDTO) throws BancoobException {
		try{
			Parcelamento parcelamentoAvista = new Parcelamento();
				
			validaIncluirParcelas(listaParcelasDTO);
			Short numParcelamento = parcelamentoCCALegadoServico.obterProximoNumParcelamento(numContaCapital, codTipoParcelamento).shortValue();						
			
			for (ParcelamentoRenDTO parcela : listaParcelasDTO) {							
				if(parcela != null){															
					Parcelamento parcelamento = montarParcelamento(parcela);
					parcelamento.setNumParcelamento(numParcelamento);
					parcelamentoContaCapitalDao.incluir(parcelamento);
					parcelamentoCCALegadoServico.incluir(montarParcelamentoCCALegado(parcelamento));	
					
					if(parcelamento.getNumParcela() == ContaCapitalConstantes.NUM_ZERO.shortValue()){
						parcelamentoAvista = parcelamento;
					}
				}			
			}	
			
			return parcelamentoAvista;
			
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
			
		}  catch (BancoobException e) {
			context.setRollbackOnly();
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_003");
		}				
	}	
	
	/**
	 * Montar parcelamento.
	 *
	 * @param parcela o valor de parcela
	 * @return Parcelamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Parcelamento montarParcelamento(ParcelamentoRenDTO parcela) throws BancoobException{
		
		Parcelamento parcelamento = new Parcelamento();
		ContaCapital contaCapital = contaCapitalServico.obter(parcela.getIdContaCapital());
		parcelamento.setContaCapital(contaCapital);
		parcelamento.setDataSituacao(new DateTimeDB(prodLegadoServico.obterDataAtualProdutoCCALogado().getTime()));
		parcelamento.setDataVencimento(new DateTimeDB(parcela.getDataVencimento().getTime()));
		parcelamento.setMatriculaFuncionario(parcela.getDescNumMatriculaFunc());		
		if(parcela.getIdMotivoDevolucao()!=null){
			parcelamento.setMotivoDevolucao(new MotivoDevolucao(parcela.getIdMotivoDevolucao()));			
		}		
		parcelamento.setNumContaCorrente(parcela.getNumContaCorrente());
		parcelamento.setNumParcela(parcela.getNumParcela());
		parcelamento.setNumParcelamento(parcela.getNumParcelamento());
		parcelamento.setObservacao(null);					
		parcelamento.setTipoIntegralizacao(new TipoIntegralizacao(parcela.getIdTipoInteg()));
		parcelamento.setTipoParcelamento(new TipoParcelamento(parcela.getIdTipoParcelamento()));					
		parcelamento.setValor(parcela.getValorParcela());
		parcelamento.setSituacaoParcelamento(new SituacaoParcelamento(parcela.getIdSituacaoParcelamento()));
		parcelamento.setIdInstituicao(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));		
		
		return parcelamento;		
	}
	
	/**
	 * Montar parcelamento cca legado.
	 *
	 * @param parcelamento o valor de parcelamento
	 * @return ParcelamentoCCALegado
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private ParcelamentoCCALegado montarParcelamentoCCALegado(Parcelamento parcelamento) throws BancoobException{

		ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();	
		contaCapitalLegado.setNumMatricula(parcelamento.getContaCapital().getNumContaCapital());
		
		ParcelamentoCCALegado entidade = new ParcelamentoCCALegado();
		ParcelamentoCCALegadoPK entidadePK = new ParcelamentoCCALegadoPK();

		entidadePK.setContaCapitalLegado(contaCapitalLegado);		
		entidadePK.setCodTipoParcelamento(parcelamento.getTipoParcelamento().getId().intValue());								
		entidadePK.setNumParcelamento(parcelamento.getNumParcelamento().intValue());
		entidadePK.setNumParcela(parcelamento.getNumParcela().intValue());		
		entidade.setParcelamentoCCALegadoPK(entidadePK);
		
		entidade.setCodModoLanc(parcelamento.getTipoIntegralizacao().getId().intValue());
		if(parcelamento.getMotivoDevolucao() != null && parcelamento.getMotivoDevolucao().getId() != null){
			entidade.setCodMotivoDevolucao(parcelamento.getMotivoDevolucao().getId().intValue());
		}
		entidade.setDataEnvioCob(null);
		Date dtAtualProduto = prodLegadoServico.obterDataAtualProdutoCCALogado();
		entidade.setDataSituacaoParcela(new DateTimeDB(dtAtualProduto.getTime()));	
		entidade.setDataVencParcela(parcelamento.getDataVencimento());
		entidade.setDescObservacao(null);
		entidade.setValorParcela(parcelamento.getValor());
		entidade.setCodSituacaoParcela(parcelamento.getSituacaoParcelamento().getId().intValue());	
		
		if(parcelamento.getTipoIntegralizacao().getId().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.shortValue()) {
			entidade.setNumContaCorrente(parcelamento.getNumContaCorrente());	
		}		
		if(parcelamento.getTipoIntegralizacao().getId() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_FOLHA.shortValue()) {
			if(!parcelamento.getMatriculaFuncionario().equals("")){			
				entidade.setuIDTrabalha(trabalhaLegadoServico.obterDadosTrabalhaPorMatricula(parcelamento.getMatriculaFuncionario()).getUIDTrabalha());							
			}
		}					
		entidade.setIdParcelamentoContaCapital(parcelamento.getId());
		
		return entidade;
	}

	/**
	 * O método Valida gravar parcelas.
	 *
	 * @param listaParcelasDTO o valor de lista parcelas dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validaGravarParcelas(List<ParcelamentoRenDTO> listaParcelasDTO) throws BancoobException{
				
		List<ParcelamentoRenDTO> lstParcelamento;
		BigDecimal valorTotalParcelas = BigDecimal.ZERO;		
		
		if (listaParcelasDTO == null || listaParcelasDTO.isEmpty()){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_003");
		}
		
		for (ParcelamentoRenDTO parcela : listaParcelasDTO) {						
			if(parcela != null && parcela.getIdSituacaoParcelamento().equals(ContaCapitalConstantes.COD_PARCELA_GERADA.shortValue())){					
				valorTotalParcelas = valorTotalParcelas.add(parcela.getValorParcela());
			}			
		}

		lstParcelamento = parcelamentoContaCapitalDao.pesquisarParcelamentos(listaParcelasDTO.get(0).getIdContaCapital(), listaParcelasDTO.get(0).getIdTipoParcelamento().intValue());
		
		for (ParcelamentoRenDTO item : lstParcelamento) {									
			if(item.getNumParcelamento().shortValue() == listaParcelasDTO.get(0).getNumParcelamento().shortValue() && valorTotalParcelas.compareTo(item.getValorAberto())!=0){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_039");			
			}						
		}
		
		if(fechamentoServico.isFechamentoIniciado(Integer.valueOf(InformacoesUsuario.getInstance().getCooperativa()))) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_002");
		}			

	}

	/**
	 * {@link ParcelamentoContaCapitalServicoRemote#pesquisarParcelasEmAberto(Integer)}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<Parcelamento> pesquisarParcelasEmAberto(Integer idContaCapital) throws BancoobException {
		return parcelamentoContaCapitalDao.pesquisarParcelasEmAberto(idContaCapital);
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.ParcelamentoContaCapitalServico#pesquisarValorParcelasDevolucaoEmAberto(java.lang.Integer)
	 */
	public BigDecimal pesquisarValorParcelasDevolucaoEmAberto(Integer idContaCapital) throws BancoobException {
		ConsultaDto<Parcelamento> criterios = new ConsultaDto<Parcelamento>();
		Parcelamento filtro = new Parcelamento();
		ContaCapital cca = new ContaCapital();
		cca.setId(idContaCapital);
		filtro.setContaCapital(cca);
		filtro.setTipoParcelamento(new TipoParcelamento(EnumTipoParcelamento.COD_TIPO_PARCELAMENTO_DEVOLUCAO.getCodigo().shortValue()));
		filtro.setSituacaoParcelamento(new SituacaoParcelamento(EnumSituacaoParcelamento.COD_PARCELA_GERADA.getCodigo().shortValue()));
		criterios.setFiltro(filtro);
		List<Parcelamento> parcelas = listar(criterios);
		BigDecimal valor = BigDecimal.ZERO;
		for (Parcelamento parc : parcelas) {
			valor = valor.add(parc.getValor());
		}
		return valor;
	}
	
	/**
	 * O método Valida incluir parcelas.
	 *
	 * @param listaParcelasDTO o valor de lista parcelas dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validaIncluirParcelas(List<ParcelamentoRenDTO> listaParcelasDTO) throws BancoobException{
		
		if (listaParcelasDTO == null || listaParcelasDTO.isEmpty()){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_003");
		}
				
		if(fechamentoServico.isFechamentoIniciado(Integer.valueOf(InformacoesUsuario.getInstance().getCooperativa()))) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_002");
		}			

	}	

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.ParcelamentoContaCapitalServico#alterarEmLote(java.lang.List)
	 */
	public void alterarEmLote(List<Parcelamento> listParcelamento) throws BancoobException {
		parcelamentoContaCapitalDao.alterarEmLote(listParcelamento);
	}	
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.ParcelamentoContaCapitalServico#incluirEmLote(java.lang.List)
	 */
	public void incluirEmLote(List<Parcelamento> listParcelamento) throws BancoobException {
		parcelamentoContaCapitalDao.incluirEmLote(listParcelamento);
	}	
}
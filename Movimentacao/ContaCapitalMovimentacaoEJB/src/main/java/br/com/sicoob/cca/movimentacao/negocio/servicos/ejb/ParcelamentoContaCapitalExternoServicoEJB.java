package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoParcelamento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCorrenteViewDelegate;
import br.com.sicoob.cca.movimentacao.negocio.dto.CancelamentoParcelamentoDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalExternoServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalExternoServicoRemote;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDaoIF;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDaoFactory;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ParcelamentoContaCapitalDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ParcelamentoCCALegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.persistencia.ContaCapitalIntegracaoLegadoDataSource;

/**
 * EJB contendo servicos relacionados a ParcelamentoContaCapitalExterno.
 *
 * @author Antonio.Genaro
 */
@Stateless
@Local (ParcelamentoContaCapitalExternoServicoLocal.class)
@Remote(ParcelamentoContaCapitalExternoServicoRemote.class) 
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ParcelamentoContaCapitalExternoServicoEJB extends ContaCapitalMovimentacaoCrudServicoEJB<Parcelamento> implements ParcelamentoContaCapitalExternoServicoLocal, ParcelamentoContaCapitalExternoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private ParcelamentoContaCapitalDao parcelamentoContaCapitalDao;	

	@EJB
	private ParcelamentoCCALegadoServicoLocal parcelamentoCCALegadoServico;
	
	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;
	
	@EJB
	private FechamentoContaCapitalServicoLocal fechamentoServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@EJB
	private ProdutoLegadoServicoLocal prodLegadoServico;
	
	/**
	 * Recupera o valor de DAO.
	 *
	 * @return o valor de DAO
	 * @see ParcelamentoContaCapitalDao
	 */
	@Override
	protected ContaCapitalMovimentacaoCrudDaoIF<Parcelamento> getDAO() {
		return parcelamentoContaCapitalDao;
	}

	/**
	 * {@link ParcelamentoContaCapitalExternoServicoRemote#incluirParcelamento(List)}.
	 *
	 * @param listaParcelas o valor de lista parcelas
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void incluirParcelamento(List<ParcelamentoCapitalDTO> listaParcelas)throws BancoobException {
		getLogger().info("ParcelamentoContaCapitalExternoServicoEJB.incluirParcelamento");
		
		validarParcelamento(listaParcelas);
		Integer numCooperativa = instituicaoIntegracaoServico.obterNumeroCooperativa(listaParcelas.get(0).getIdInstituicao());
		ContaCapitalIntegracaoLegadoDataSource.definirNumeroCooperativa(numCooperativa);		
		validarFechamentoExecucao(numCooperativa);
		//ContaCorrente, Valida apenas uma vez
		getLogger().info("Validando conta corrente");
		validarContaCorrenteParcelamento(listaParcelas.get(0));
		getLogger().info("Obtendo proximo numParcelamento");
		Short numParcelamento = parcelamentoCCALegadoServico.obterProximoNumParcelamento(listaParcelas.get(0).getNumContaCapital(), 1).shortValue();
		
		getLogger().info("Incluindo parcelamento SQL");
		incluirParcelamentoSql(listaParcelas,numParcelamento);
		getLogger().info("Incluindo parcelamento DB2");
		incluirParcelamentoDb2(listaParcelas, numParcelamento);
		
	}
	
	/**
	 * Validar Fechamento em Execucao.
	 *
	 * @param numCooperativa o valor de num cooperativa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarFechamentoExecucao(Integer numCooperativa) throws BancoobException{
		if (fechamentoServico.isFechamentoIniciado(numCooperativa)){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_002");			
		}
	}
	
	/**
	 * Inclui a lista de parcelamentos no banco de dados sql.
	 *
	 * @param listaParcelas o valor de lista parcelas
	 * @param numParcelamento o valor de num parcelamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void incluirParcelamentoSql(List<ParcelamentoCapitalDTO> listaParcelas,Short numParcelamento) throws BancoobException{
		for (ParcelamentoCapitalDTO parcela : listaParcelas) {
			parcelamentoCCALegadoServico.incluir(montarParcelaLegado(parcela,numParcelamento));																	
		}
	}
	
	/**
	 * Inclui a lista de parcelamentos no banco de dados db2
	 * 
	 * @param listaParcelas o valor de lista parcelas
	 * @param numParcelamento o valor de num parcelamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void incluirParcelamentoDb2(List<ParcelamentoCapitalDTO> listaParcelas, Short numParcelamento) throws BancoobException{
		ContaCapital contaCapital = contaCapitalServico.obterPorInstituicaoMatricula(listaParcelas.get(0).getIdInstituicao(), listaParcelas.get(0).getNumContaCapital());
		if (contaCapital != null) {		
			for (ParcelamentoCapitalDTO parcela : listaParcelas) {
				parcelamentoContaCapitalDao.incluir(montarParcela(contaCapital, parcela, numParcelamento));																	
			}
		}
	}
	
	/**
	 * Valida os dados basicos para o parcelamento
	 * 0 - Lista vazia
	 * 1 - Atributos Obrigatorios
	 * 2 - Conta Corrente existente, sem bloqueio e não encerrada
	 * 3 - Sequenciamento crescente de Datas e Parcela
	 * 4 - Mesma CCA para todas as parcelas.
	 *
	 * @param listaParcelas o valor de lista parcelas
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarParcelamento(List<ParcelamentoCapitalDTO> listaParcelas) throws BancoobException{
		
		if (listaParcelas.isEmpty()){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_OBR_LIST_PARC");
		}		

		//Obrigatorios
		for (ParcelamentoCapitalDTO parcela : listaParcelas) {
			verificarParcelamentoAtributosNull(parcela);
			
			if (EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo().equals(parcela.getIdTipoIntegralizacao().intValue())) {
				if (parcela.getNumContaCorrente() == null) {
					throw new ContaCapitalMovimentacaoNegocioException("MSG_OBR_LIST_PARC_INCOMPLETA","Conta Corrente");
				}
			}
		}
		
		//Sequenciamento de Datas, Parcela e Conformidade da Conta Capital
		Integer numContaCapitalAnt = listaParcelas.get(0).getNumContaCapital();	
		Short numParcelaAnt = 0;		
		DateTimeDB dataVencimentoAnt = null;
		
		for (ParcelamentoCapitalDTO parcela : listaParcelas) {
					
			if (!parcela.getNumContaCapital().equals(numContaCapitalAnt)){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_PARC_CONTACAPITAL_DIF");		
			}
			if (parcela.getNumParcela().shortValue() != numParcelaAnt.shortValue()){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_PARC_ORDEM_DIF");
			}
			if (dataVencimentoAnt != null && (dataVencimentoAnt.after(parcela.getDataVencimento()) || dataVencimentoAnt.equals(parcela.getDataVencimento()) )){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_PARC_DATA_DIF");				
			}
			if (parcela.getValorParcela().compareTo(BigDecimal.ZERO) < 1) {
				throw new ContaCapitalMovimentacaoNegocioException("MSG_PARC_MAIOR_ZERO");
			}
			
			dataVencimentoAnt = parcela.getDataVencimento();
			numParcelaAnt++;	
		}		
		
	}
	
	/**
	 * Valida se na lista de parcelas tem algum atributo null.
	 * NumContaCorrente trata a parte
	 * Canal não é obrigatorio e numero do parcelamento pode ser gerado dinamicamente
	 * @param Object
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void verificarParcelamentoAtributosNull(Object o) throws BancoobException{
		String campo = null;
		try {
			for (Method method : o.getClass().getMethods()) {
				if (isGetter(method) && !isMetodoPossivelNulo(method) && method.invoke(o) == null){
					campo = method.getName().replace("get", "");
					throw new ContaCapitalMovimentacaoNegocioException("MSG_OBR_LIST_PARC_INCOMPLETA",campo);	
				}
			}
		} catch (IllegalAccessException e) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_ERR_PARCELAMENTO", e);
		} catch (InvocationTargetException e) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_ERR_PARCELAMENTO", e);
		}
	}

	private boolean isMetodoPossivelNulo(Method method) {
		return method.getName().toLowerCase().contains("codcanal") || method.getName().toLowerCase().contains("numparcelamento")  || method.getName().toLowerCase().contains("numcontacorrente");
	}
	
	private boolean isGetter(Method method) {
		return method.getName().startsWith("get");
	}
	
	/**
	 * Valida a Conta Corrente.
	 *
	 * @param dto o valor de dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarContaCorrenteParcelamento(ParcelamentoCapitalDTO dto) throws BancoobException{
		
		if (!EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo().equals(dto.getIdTipoIntegralizacao().intValue())) {
			return;
		}
		
		if (dto.getNumContaCorrente() == null) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_OBR_LIST_PARC_INCOMPLETA","Conta Corrente");
		}
		
		ContaCorrenteViewDelegate contaCorrenteViewDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarContaCorrenteViewDelegate();
		if (contaCorrenteViewDelegate.verificarContaCorrenteBloqueadaEncerrada(dto.getIdInstituicao(), dto.getNumContaCorrente().intValue())) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_CCO_ENCE_BLOQ");
		}

	}	
	
	/**
	 * Monta a entidade do Parcelamento, não esta preparado para via folha.
	 * 
	 * @param contaCapital 
	 * @param dto o valor de dto
	 * @param numParcelamento o valor de num parcelamento
	 * @return Parcelamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Parcelamento montarParcela(ContaCapital contaCapital, ParcelamentoCapitalDTO dto, Short numParcelamento) throws BancoobException{
		
		Parcelamento parcelamento = new Parcelamento();
		
		parcelamento.setContaCapital(contaCapital);
		parcelamento.setDataSituacao(obterDataLote(dto.getIdInstituicao()));
		parcelamento.setDataVencimento(dto.getDataVencimento());
		
		if (dto.getNumContaCorrente() != null){
			parcelamento.setNumContaCorrente(dto.getNumContaCorrente());			
		}

		parcelamento.setNumParcela(dto.getNumParcela());
		parcelamento.setNumParcelamento(numParcelamento);
		parcelamento.setTipoIntegralizacao(new TipoIntegralizacao(dto.getIdTipoIntegralizacao()));
		parcelamento.setTipoParcelamento(new TipoParcelamento(dto.getIdTipoParcelamento()));
		parcelamento.setValor(dto.getValorParcela());
		parcelamento.setSituacaoParcelamento(new SituacaoParcelamento(dto.getIdSituacaoParcelamento()));
		parcelamento.setCodCanal(dto.getCodCanal());
		parcelamento.setIdInstituicao(dto.getIdInstituicao());				
		
		return parcelamento;
	}
	
	/**
	 * Monta a entidade do parcelamento legado, não esta preparado para via folha.
	 *
	 * @param dto o valor de dto
	 * @param numParcelamento o valor de num parcelamento
	 * @return ParcelamentoCCALegado
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private ParcelamentoCCALegado montarParcelaLegado(ParcelamentoCapitalDTO dto, Short numParcelamento) throws BancoobException{

		ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();	
		contaCapitalLegado.setNumMatricula(dto.getNumContaCapital());
		
		ParcelamentoCCALegado entidade = new ParcelamentoCCALegado();
		ParcelamentoCCALegadoPK entidadePK = new ParcelamentoCCALegadoPK();

		entidadePK.setContaCapitalLegado(contaCapitalLegado);		
		entidadePK.setCodTipoParcelamento(dto.getIdTipoParcelamento().intValue());								
		entidadePK.setNumParcelamento(numParcelamento.intValue());
		entidadePK.setNumParcela(dto.getNumParcela().intValue());		
		entidade.setParcelamentoCCALegadoPK(entidadePK);
		
		entidade.setCodModoLanc(dto.getIdTipoIntegralizacao().intValue());
		entidade.setDataSituacaoParcela(obterDataLote(dto.getIdInstituicao()));	
		entidade.setDataVencParcela(dto.getDataVencimento());
		entidade.setValorParcela(dto.getValorParcela());
		entidade.setCodSituacaoParcela(dto.getIdSituacaoParcelamento().intValue());	
		entidade.setCodCanal(dto.getCodCanal());
		
		if(dto.getIdTipoIntegralizacao().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.shortValue()) {
			entidade.setNumContaCorrente(dto.getNumContaCorrente());	
		}		
					
		entidade.setIdParcelamentoContaCapital(ContaCapitalConstantes.IDPARCELAMENTOCONTACAPITAL_NAOREPLICA);
		
		return entidade;
	}			

	/**
	 * Data Atual do Lote de Conta Capital
	 * Atenção: Retorna a data do SQL.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return DateTimeDB
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private DateTimeDB obterDataLote(Integer idInstituicao) throws BancoobException {
		return new DateTimeDB(prodLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, idInstituicao).getTime());
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.ParcelamentoContaCapitalExternoServico#cancelarParcelamentos(java.util.List)
	 */
	public void cancelarParcelamentos(List<CancelamentoParcelamentoDTO> listCancelamentos) throws BancoobException {
		getLogger().info("ParcelamentoContaCapitalExternoServicoEJB.cancelarParcelamentos");
		validarCancelamento(listCancelamentos);
		Integer idInstituicao = listCancelamentos.get(0).getIdInstituicao();
		Integer numContaCapital = listCancelamentos.get(0).getNumContaCapital();
		Integer numCooperativa = instituicaoIntegracaoServico.obterNumeroCooperativa(idInstituicao);
		validarFechamentoExecucao(numCooperativa);
		ContaCapitalIntegracaoLegadoDataSource.definirNumeroCooperativa(numCooperativa);
		ContaCapital contaCapital = contaCapitalServico.obterPorInstituicaoMatricula(idInstituicao, numContaCapital);
		DateTimeDB dataCancelamento = obterDataLote(idInstituicao);
		for (CancelamentoParcelamentoDTO cancelamentoParcelamentoDTO : listCancelamentos) {
			Parcelamento parcelamento = null;
			if (contaCapital != null) {
				getLogger().info("Cancelando parcelamento DB2");
				parcelamento = cancelarParcelamentoDB2(cancelamentoParcelamentoDTO, dataCancelamento);
			}
			getLogger().info("Cancelando parcelamento SQL");
			ParcelamentoCCALegado parcelamentoLegado = cancelarParcelamentoSQL(cancelamentoParcelamentoDTO, dataCancelamento);
			acrescentarInformacoesDto(cancelamentoParcelamentoDTO, parcelamento, parcelamentoLegado);
		}
	}

	private void validarCancelamento(List<CancelamentoParcelamentoDTO> listCancelamento) throws ContaCapitalMovimentacaoNegocioException {
		if (listCancelamento == null || listCancelamento.isEmpty()) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_CANC_PARC");
		}
		for (CancelamentoParcelamentoDTO dto : listCancelamento) {
			if (dto.getIdInstituicao() == null) {
				lancarExcecaoCancelamentoIncompleto("idInstituicao");
			}
			if (dto.getNumParcela() == null) {
				lancarExcecaoCancelamentoIncompleto("numParcela");
			}
			if (dto.getNumParcelamento() == null) {
				lancarExcecaoCancelamentoIncompleto("numParcelamento");
			}
			if (dto.getIdTipoParcelamento() == null) {
				lancarExcecaoCancelamentoIncompleto("idTipoParcelamento");
			}
			if (dto.getNumContaCapital() == null) {
				lancarExcecaoCancelamentoIncompleto("numContaCapital");
			}
		}
	}

	private void lancarExcecaoCancelamentoIncompleto(String campo) throws ContaCapitalMovimentacaoNegocioException {
		throw new ContaCapitalMovimentacaoNegocioException("MSG_CANC_INCOMPLETO", campo);
	}

	private void acrescentarInformacoesDto(CancelamentoParcelamentoDTO dto,
			Parcelamento parcelamento, ParcelamentoCCALegado parcelamentoLegado) {
		if (parcelamento != null) {
			dto.setDataSituacao(parcelamento.getDataSituacao());
			dto.setValorParcela(parcelamento.getValor());
			dto.setDataVencimento(parcelamento.getDataVencimento());
		} else if (parcelamentoLegado != null) {
			dto.setDataSituacao(parcelamentoLegado.getDataSituacaoParcela());
			dto.setValorParcela(parcelamentoLegado.getValorParcela());
			dto.setDataVencimento(parcelamentoLegado.getDataVencParcela());
		}
	}

	private ParcelamentoCCALegado cancelarParcelamentoSQL(CancelamentoParcelamentoDTO cancelamentoParcelamentoDTO, DateTimeDB dataCancelamento) 
			throws BancoobException {
		ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();				
		contaCapitalLegado.setNumMatricula(cancelamentoParcelamentoDTO.getNumContaCapital());
		
		ParcelamentoCCALegadoPK parcelamentoCCALegadoPK = new ParcelamentoCCALegadoPK();								
		parcelamentoCCALegadoPK.setContaCapitalLegado(contaCapitalLegado);
		parcelamentoCCALegadoPK.setNumParcela(cancelamentoParcelamentoDTO.getNumParcela().intValue());
		parcelamentoCCALegadoPK.setNumParcelamento(cancelamentoParcelamentoDTO.getNumParcelamento().intValue());
		parcelamentoCCALegadoPK.setCodTipoParcelamento(cancelamentoParcelamentoDTO.getIdTipoParcelamento().intValue());
		
		ParcelamentoCCALegado parcelamentoCCALegado = parcelamentoCCALegadoServico.obter(parcelamentoCCALegadoPK);
		if (parcelamentoCCALegado != null) {
			parcelamentoCCALegado.setCodSituacaoParcela(ContaCapitalConstantes.COD_PARCELA_CANCELADA);
			parcelamentoCCALegado.setDataSituacaoParcela(dataCancelamento);
			parcelamentoCCALegado.setIdParcelamentoContaCapital(ContaCapitalConstantes.IDPARCELAMENTOCONTACAPITAL_NAOREPLICA);
			parcelamentoCCALegadoServico.alterar(parcelamentoCCALegado);
			return parcelamentoCCALegado;
		}
		
		return null;
	}

	private Parcelamento cancelarParcelamentoDB2(CancelamentoParcelamentoDTO cancelamentoParcelamentoDTO, DateTimeDB dataCancelamento) throws BancoobException {
		Parcelamento parcelamento = buscarParcelamentoDB2(cancelamentoParcelamentoDTO);
		if (parcelamento != null) {
			if (parcelamento.getSituacaoParcelamento().getId().shortValue() != ContaCapitalConstantes.COD_SITUACAO_EM_ABERTO.shortValue()) {
				throw new ContaCapitalMovimentacaoNegocioException("MSG_037");			
			}		
			
			parcelamento.setSituacaoParcelamento(new SituacaoParcelamento(ContaCapitalConstantes.COD_PARCELA_CANCELADA.shortValue()));
			parcelamento.setDataSituacao(dataCancelamento);
			
			parcelamentoContaCapitalDao.alterar(parcelamento);
			return parcelamento;
		}
		return null;
	}
	
	private Parcelamento buscarParcelamentoDB2(CancelamentoParcelamentoDTO cancelamentoParcelamentoDTO) throws BancoobException {
		ConsultaDto<Parcelamento> criterios = montarCriteriosParcelamentosParaCancelamento(cancelamentoParcelamentoDTO);
		List<Parcelamento> parcelamentos = parcelamentoContaCapitalDao.listar(criterios);
		if (!parcelamentos.isEmpty()) {
			return parcelamentos.get(0);
		}
		return null;
	}

	private ConsultaDto<Parcelamento> montarCriteriosParcelamentosParaCancelamento(CancelamentoParcelamentoDTO cancelamentoParcelamentoDTO) {
		ConsultaDto<Parcelamento> criterios = new ConsultaDto<Parcelamento>();
		
		Parcelamento filtro = new Parcelamento();
		filtro.setNumParcela(cancelamentoParcelamentoDTO.getNumParcela());
		filtro.setNumParcelamento(cancelamentoParcelamentoDTO.getNumParcelamento());
		
		ContaCapital contaCapital = new ContaCapital();
		contaCapital.setIdInstituicao(cancelamentoParcelamentoDTO.getIdInstituicao());
		contaCapital.setNumContaCapital(cancelamentoParcelamentoDTO.getNumContaCapital());
		filtro.setContaCapital(contaCapital);
		
		TipoParcelamento tipoParcelamento = new TipoParcelamento();
		tipoParcelamento.setId(cancelamentoParcelamentoDTO.getIdTipoParcelamento());
		filtro.setTipoParcelamento(tipoParcelamento);
		
		criterios.setFiltro(filtro);
		return criterios;
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.ParcelamentoContaCapitalExternoServico#pesquisarParcelamentosEmAbertoViaCaixa(br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoCapitalDTO)
	 */
	public List<ParcelamentoRenDTO> pesquisarParcelamentosEmAbertoViaCaixa(ParcelamentoCapitalDTO dto) throws BancoobException {
		return parcelamentoContaCapitalDao.pesquisarParcelamentosEmAbertoViaCaixa(dto);
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.ParcelamentoContaCapitalExternoServico#atualizarParcelamentos(java.lang.Integer, java.util.List, java.lang.Integer)
	 */
	public void atualizarParcelamentos(Integer idInstituicao, List<ParcelamentoRenDTO> parcelamentos, Integer idSituacaoParcelamento) throws BancoobException {
		List<Long> idsParcelamento = new ArrayList<Long>();
		for (ParcelamentoRenDTO dto : parcelamentos) {
			idsParcelamento.add(dto.getIdParcelamento());
		}
		DateTimeDB data = obterDataLote(idInstituicao);
		getLogger().info("Atualizando parcelamentos DB2");
		parcelamentoContaCapitalDao.atualizarParcelamentos(idsParcelamento, data, idSituacaoParcelamento);
		getLogger().info("Atualizando parcelamentos SQL");
		Integer numCooperativa = instituicaoIntegracaoServico.obterNumeroCooperativa(idInstituicao);
		ContaCapitalIntegracaoLegadoDataSource.definirNumeroCooperativa(numCooperativa);
		for (ParcelamentoRenDTO dto : parcelamentos) {
			atualizarParcelamentoSQL(dto, data, idSituacaoParcelamento);
		}
	}
	
	private void atualizarParcelamentoSQL(ParcelamentoRenDTO dto, DateTimeDB data, Integer idSituacaoParcelamento) throws BancoobException {
		ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();				
		contaCapitalLegado.setNumMatricula(dto.getNumContaCapital());
		
		ParcelamentoCCALegadoPK parcelamentoCCALegadoPK = new ParcelamentoCCALegadoPK();								
		parcelamentoCCALegadoPK.setContaCapitalLegado(contaCapitalLegado);
		parcelamentoCCALegadoPK.setNumParcela(dto.getNumParcela().intValue());
		parcelamentoCCALegadoPK.setNumParcelamento(dto.getNumParcelamento().intValue());
		parcelamentoCCALegadoPK.setCodTipoParcelamento(dto.getIdTipoParcelamento().intValue());
		
		ParcelamentoCCALegado parcelamentoCCALegado = parcelamentoCCALegadoServico.obter(parcelamentoCCALegadoPK);
		if (parcelamentoCCALegado != null) {
			parcelamentoCCALegado.setCodSituacaoParcela(idSituacaoParcelamento);
			parcelamentoCCALegado.setDataSituacaoParcela(data);
			parcelamentoCCALegado.setIdParcelamentoContaCapital(ContaCapitalConstantes.IDPARCELAMENTOCONTACAPITAL_NAOREPLICA);
			parcelamentoCCALegadoServico.alterar(parcelamentoCCALegado);
		}
	}
	
}
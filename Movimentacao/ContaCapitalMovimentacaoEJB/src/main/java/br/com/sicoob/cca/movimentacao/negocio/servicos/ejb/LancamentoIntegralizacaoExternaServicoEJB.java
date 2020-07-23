package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
import br.com.sicoob.cca.entidades.negocio.entidades.HistoricoLancamentoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoLote;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoSubscricao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoLote;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoSubscricaoCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.LancamentoEstornoRateioDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoIntegralizacaoExternaServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoIntegralizacaoExternaServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoIntegralizacaoExternaServicoRemote;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDaoIF;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDaoFactory;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.HistoricoLancamentoContaCapitalDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.LancamentoContaCapitalDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoInstituicaoNegocioException;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.CapaLoteCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.LancamentosCCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.persistencia.ContaCapitalIntegracaoLegadoDataSource;

/**
 * EJB contendo servicos relacionados a LancamentoIntegralizacaoExterna.
 *
 * @author Antonio.Genaro
 */
@Stateless
@Local (LancamentoIntegralizacaoExternaServicoLocal.class)
@Remote(LancamentoIntegralizacaoExternaServicoRemote.class) 
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LancamentoIntegralizacaoExternaServicoEJB extends ContaCapitalMovimentacaoCrudServicoEJB<LancamentoContaCapital> implements LancamentoIntegralizacaoExternaServicoLocal, LancamentoIntegralizacaoExternaServicoRemote {
	
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
	private ContaCapitalLegadoServicoLocal contaCapitalLegadoServico;	
	
	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@EJB
	private CapesIntegracaoServicoLocal capesIntegracaoServico;
	
	@EJB
	private FechamentoContaCapitalServicoLocal fechamentoServico;
	
	@EJB
	private ProdutoLegadoServicoLocal prodLegadoServico;
	
	/** A constante TAM_MAX_OPERACAO. */
	private static final int TAM_MAX_OPERACAO = 50;	
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.ejb.ContaCapitalMovimentacaoCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalMovimentacaoCrudDaoIF<LancamentoContaCapital> getDAO() {
		return lancamentoContaCapitalDao;
	}
	
	/**
	 * Obrigatório usar o definirNumeroCooperativa().
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoIntegralizacaoExternaServico#incluir(br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO)
	 */
	public LancamentoContaCapital incluir(IntegralizacaoCapitalDTO dto)throws BancoobException {
		getLogger().info("LancamentoIntegralizacaoExternaServicoEJB.incluir");
		try{
			validarIntegralizacaoObrigatorios(dto); //Não mudar a ordem
			
			Integer numCooperativa = instituicaoIntegracaoServico.obterNumeroCooperativa(dto.getIdInstituicao());			
			ContaCapitalIntegracaoLegadoDataSource.definirNumeroCooperativa(numCooperativa);
			validarFechamentoExecucao(numCooperativa);
			validarValoresObrigatorios(dto);
			validarContaCapital(dto);
			
			getLogger().info("Incluindo lancamento SQL");
			LancamentosCCapitalLegado lancamentoSql = incluirLancamentoSql(dto);			
			dto.setNumSeqLanc(lancamentoSql.getLancamentosCCapitalLegadoPK().getNumSeqLanc());
			getLogger().info("Incluindo lancamento DB2");
			LancamentoContaCapital lancamentoDb2 = incluirLancamentoDb2(dto);
			getLogger().info("Atualizando capa lote");
			atualizarCapaLote(numCooperativa, lancamentoSql);
			
			return (lancamentoDb2!=null?lancamentoDb2:converterLancLegadoToLancContaCapital(lancamentoSql,dto));
			
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
		}catch (IntegracaoInstituicaoNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
		}  catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_003",e);
		}
	}	
	
	private void atualizarCapaLote(Integer numCooperativa, LancamentosCCapitalLegado lancamentoSql) throws BancoobException {
		LancamentosCCapitalLegadoPK lancamentosCCapitalLegadoPK = lancamentoSql.getLancamentosCCapitalLegadoPK();
		CapaLoteCapitalLegadoPK capaLoteCapitalLegadoPK = lancamentosCCapitalLegadoPK.getCapaLoteCapitalLegado().getCapaLoteCapitalLegadoPK();
		capaLoteCapitalLegadoServico.atualizarCapaLote(numCooperativa, 
				capaLoteCapitalLegadoPK.getDataLote(), capaLoteCapitalLegadoPK.getNumLoteLanc());
	}

	/**
	 * Obrigatorio usar o definirNumeroCooperativa().
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoIntegralizacaoExternaServico#consultarIntegralizacao(br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public List<LancamentoContaCapital> consultarIntegralizacao(IntegralizacaoCapitalDTO dto) throws BancoobException {
		validarObrigatoriosConsultaIntegralizacao(dto);
		ContaCapitalIntegracaoLegadoDataSource.definirNumeroCooperativa(instituicaoIntegracaoServico.obterNumeroCooperativa(dto.getIdInstituicao()));
		List<LancamentoContaCapital> lstLancamentoContaCapital = null;
		
		try{		
		
			ContaCapital contaCapital = contaCapitalServico.obterPorInstituicaoMatricula(dto.getIdInstituicao(), dto.getNumMatricula());	
					
			if (contaCapital != null){			
				lstLancamentoContaCapital = consultarLancamentosDb2(dto);
			}else{
				lstLancamentoContaCapital = converterListaLancLegadoToLancContaCapital(consultarLancamentosSql(dto),dto);
			}
		
		}catch (ContaCapitalMovimentacaoNegocioException e) {
			getLogger().info(e.getMessage());
			throw new ContaCapitalMovimentacaoNegocioException("MSG_ERR_CONSULTA_INTEG",e);			
		}catch (BancoobException e) {		
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_ERR_CONSULTA_INTEG",e);
		}
		return lstLancamentoContaCapital; 	
	}	
	
	/**
	 * Consulta os lancamentos na base sql da cooperativa.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<LancamentosCCapitalLegado> consultarLancamentosSql(IntegralizacaoCapitalDTO dto)throws BancoobException {
		
		ConsultaDto<LancamentosCCapitalLegado> criterios = new ConsultaDto<LancamentosCCapitalLegado>();
		Integer numLote = (dto.getNumLoteLanc()!=null?dto.getNumLoteLanc():EnumTipoLote.COD_LOTE_CCA_PARC_AVISTA.getCodigo()); 

		LancamentosCCapitalLegado lanc = new LancamentosCCapitalLegado();		
		
		CapaLoteCapitalLegado capaLote = new CapaLoteCapitalLegado();
		capaLote.setCapaLoteCapitalLegadoPK(new CapaLoteCapitalLegadoPK(dto.getDataLancamento(),numLote));

		lanc.setiDTipoHistoricoLanc((dto.getIdTipoHistoricoLanc() != null?dto.getIdTipoHistoricoLanc():null));
		lanc.setLancamentosCCapitalLegadoPK(new LancamentosCCapitalLegadoPK(capaLote,dto.getNumSeqLanc()));
		lanc.setDescOperacaoExterna(dto.getIdOperacaoOrigem());
		
		ContaCapitalLegado cca = new ContaCapitalLegado();
		cca.setNumMatricula(dto.getNumMatricula());
		lanc.setContaCapitalLegado(cca);
		
		criterios.setFiltro(lanc);
		return lancamentosCCapitalLegadoServico.listar(criterios);
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
	 * Consulta os lancamentos na base db2.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<LancamentoContaCapital> consultarLancamentosDb2(IntegralizacaoCapitalDTO dto) throws BancoobException {
		ConsultaDto<LancamentoContaCapital> criterios = new ConsultaDto<LancamentoContaCapital>();
		LancamentoContaCapital filtro = new LancamentoContaCapital();
		filtro.setIdInstituicao(dto.getIdInstituicao());
		filtro.setDataLancamento(dto.getDataLancamento());
		
		Integer numLote = (dto.getNumLoteLanc()!=null?dto.getNumLoteLanc():EnumTipoLote.COD_LOTE_CCA_PARC_AVISTA.getCodigo());  		
		filtro.setTipoLote(new TipoLote(numLote.shortValue()));

		filtro.setNumSeqLanc(dto.getNumSeqLanc());
		
		if (dto.getIdTipoHistoricoLanc() != null){
			filtro.setTipoHistoricoCCA(new TipoHistoricoCCA(dto.getIdTipoHistoricoLanc().shortValue()));
		}
		filtro.setDescOperacaoExterna(dto.getIdOperacaoOrigem());
		
		ContaCapital cca = new ContaCapital();
		cca.setId(dto.getIdInstituicao());
		cca.setIdPessoa(dto.getIdPessoa());
		cca.setNumContaCapital(dto.getNumMatricula());
		filtro.setContaCapital(cca);
		
		criterios.setFiltro(filtro);
		return lancamentoContaCapitalDao.listar(criterios);
	}	
	
	/**
	 * Valida os dados obrigatórios para integralização.
	 *
	 * @param dtoIntegralizacao o valor de dto integralizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarIntegralizacaoObrigatorios(IntegralizacaoCapitalDTO dtoIntegralizacao)throws BancoobException{
		
		if (dtoIntegralizacao.getIdInstituicao() == null){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_OBR_INTEG","A Instituição");		
		}	
		
		if (dtoIntegralizacao.getNumMatricula() == null){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_OBR_INTEG","A Conta Capital");		
		}		
		
		if (dtoIntegralizacao.getIdTipoHistoricoLanc() == null){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_OBR_INTEG","O Tipo de Histórico");		
		}	
		
		if (dtoIntegralizacao.getIdOperacaoOrigem() != null && dtoIntegralizacao.getIdOperacaoOrigem().length() > TAM_MAX_OPERACAO){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_TAM_MAX_OP_LANC");
		}
		
	}	
	
	/**
	 * Valida os valores.
	 *
	 * @param dtoIntegralizacao o valor de dto integralizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarValoresObrigatorios(IntegralizacaoCapitalDTO dtoIntegralizacao) throws BancoobException{
	
		if (dtoIntegralizacao.getNumLoteLanc() == null) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_OBR_INTEG","Numero do lote");
		}
		
		if (dtoIntegralizacao.getValorLancamento() == null ){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_OBR_INTEG","O valor do lançamento");
		}		
		
		if (!(dtoIntegralizacao.getValorLancamento().compareTo(BigDecimal.ZERO)==1)){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_INTEG_VALOR");
		}
	
	}		
	
	/**
	 * Valida a contacapital.
	 *
	 * @param dtoIntegralizacao o valor de dto integralizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarContaCapital(IntegralizacaoCapitalDTO dtoIntegralizacao)throws BancoobException{
		ContaCapitalLegado ccaLegado = contaCapitalLegadoServico.obter(dtoIntegralizacao.getNumMatricula());
		
		if(ccaLegado == null){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_INTEG_CCA_NAO_ENC");
		}
		
		if(!dtoIntegralizacao.isIgnoraValidacaoContaCapitalAtiva() 
				&& !ccaLegado.getCodSituacao().equals(EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo())) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_INTEG_CCA_NAO_ATIVO");
		}		
	}
	
	/**
	 * Valida os dados obrigatórios para integralização.
	 *
	 * @param dtoIntegralizacao o valor de dto integralizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarObrigatoriosConsultaIntegralizacao(IntegralizacaoCapitalDTO dtoIntegralizacao)throws BancoobException{

		if (dtoIntegralizacao.getIdInstituicao() == null){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_OBR_CONSULTA","A instituição");
		}

		if (dtoIntegralizacao.getIdOperacaoOrigem() == null && dtoIntegralizacao.getDataLancamento() == null){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_OBR_CONSULTA","A data do lançamento");
		}

		if (dtoIntegralizacao.getIdTipoHistoricoLanc() == null){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_OBR_CONSULTA","O histórico do lançamento");		
		}

	}
	
	/**
	 * Converte a List<LancamentosCCapitalLegado> para List<LancamentoContaCapital>.
	 *
	 * @param lstLancamentoSql o valor de lst lancamento sql
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<LancamentoContaCapital> converterListaLancLegadoToLancContaCapital(List<LancamentosCCapitalLegado> lstLancamentoSql,IntegralizacaoCapitalDTO dto) throws BancoobException{
		List<LancamentoContaCapital> lstLancamentoDb2 = new ArrayList<LancamentoContaCapital>();
		
		for (LancamentosCCapitalLegado item : lstLancamentoSql) {
			lstLancamentoDb2.add(converterLancLegadoToLancContaCapital(item,dto));
		} 
		
		return lstLancamentoDb2;
	}
	
	/**
	 * Converte o LancamentosCCapitalLegado para LancamentoContaCapital
	 * Preenche todos os campos possíveis de preenchimento
	 * Usado durante fase de transicao legado --> db2.
	 *
	 * @param lancamentoSql o valor de lancamento sql
	 * @param dto o valor de dto
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */	
	private LancamentoContaCapital converterLancLegadoToLancContaCapital(LancamentosCCapitalLegado lancamentoSql,IntegralizacaoCapitalDTO dto) throws BancoobException{
		LancamentoContaCapital saida = new LancamentoContaCapital();
		LancamentosCCapitalLegadoPK pkLanc = lancamentoSql.getLancamentosCCapitalLegadoPK();
		CapaLoteCapitalLegadoPK pkCapaLote = pkLanc.getCapaLoteCapitalLegado().getCapaLoteCapitalLegadoPK();
	
		short bolAtualizado = (short) (lancamentoSql.getBolAtualizado()?1:0); 
		saida.setIdInstituicao(dto.getIdInstituicao());
		
		ContaCapitalLegado ccaLegado = contaCapitalLegadoServico.obter(lancamentoSql.getContaCapitalLegado().getNumMatricula());
		
		ContaCapital cca = new ContaCapital();
		cca.setIdInstituicao(dto.getIdInstituicao());
		cca.setNumContaCapital(ccaLegado.getNumMatricula());
		cca.setIdPessoa(capesIntegracaoServico.obterIdPessoaPorIdPessoaLegado(ccaLegado.getNumCliente(), dto.getIdInstituicao()));
		
		saida.setContaCapital(cca);
		saida.setBolProcessado(bolAtualizado);
		saida.setDataHoraAtualizacao(lancamentoSql.getDataHoraInclusao());
		saida.setDataLancamento(pkCapaLote.getDataLote());
		saida.setDescNumDocumento(lancamentoSql.getDescNumDocumento());
		saida.setNumSeqLanc(pkLanc.getNumSeqLanc());
		saida.setTipoHistoricoCCA(new TipoHistoricoCCA(lancamentoSql.getiDTipoHistoricoLanc().shortValue()));
		saida.setTipoLote(new TipoLote(pkCapaLote.getNumLoteLanc().shortValue()));
		saida.setTipoSubscricao(new TipoSubscricao(EnumTipoSubscricaoCapital.COD_TIPO_SUBSCRICAO_CCA_VOLUNTARIA.getCodigo().shortValue()));
		saida.setValorLancamento(lancamentoSql.getValorLanc());
		saida.setDescOperacaoExterna(lancamentoSql.getDescOperacaoExterna());
		return saida;
	}	
	
	/**
	 * Inclui o lancamento na base sql
	 * Grava nas tabelas CapaLoteCapital,LancamentosCCapital 
	 * Realiza o Lancamento, gera ou atualiza a capalotecapital
	 * Atualiza os contadores da CapaLoteCapital.
	 *
	 * @param dto o valor de dto
	 * @return LancamentosCCapitalLegado
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentosCCapitalLegado incluirLancamentoSql(IntegralizacaoCapitalDTO dto)throws BancoobException {
		DateTimeDB dataLote = obterDataLote(dto.getIdInstituicao());
		CapaLoteCapitalLegado capaLoteCapitalLegado = montarCapaLoteCapitalLegado(dataLote, dto.getNumLoteLanc());
		
		if (capaLoteCapitalLegado.getBolNovo()){
			capaLoteCapitalLegadoServico.incluir(capaLoteCapitalLegado);
		}
		
		return lancamentosCCapitalLegadoServico.incluir(montarLancamentosCCapitalLegado(dto, capaLoteCapitalLegado, dataLote));
	}		
	
	/**
	 * Inclui o lancamento na base DB2
	 * Grava nas tabelas LancamentoContaCapital e HistLancamentoContaCapital.
	 *
	 * @param dto o valor de dto
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCapital incluirLancamentoDb2(IntegralizacaoCapitalDTO dto) throws BancoobException {
		LancamentoContaCapital lancamentoDb2 = null;
		ContaCapital contaCapital = contaCapitalServico.obterPorInstituicaoMatricula(dto.getIdInstituicao(), dto.getNumMatricula());	
					
		if (contaCapital != null) {
			dto.setIdContaCapital(contaCapital.getId());
			lancamentoDb2 = lancamentoContaCapitalDao.incluir(montarLancamentoContaCapital(dto));
			historicoLancamentoContaCapitalDao.incluir(montarHistLancamentoContaCapital(lancamentoDb2));			
		}
		return lancamentoDb2;
	}
	
	/**
	 * Monta o LancamentoContaCapital do DB2
	 * Utiliza o numseqlanc do dto, em outro caso obtem um novo. O mesmo para idUsuario.
	 * Para a contaCapital, se vier idContaCapital, o objeto eh criado de forma mais rapida (sem consulta a banco), 
	 * 	caso contrario, tem que buscar a referencia contaCapital no banco pela instituicao e matricula.
	 * Existe uma sobrecarga no metodo para receber codigoLote e dataLote, sendo os valores default, respectivamente: 9000 e obterDataLote(idInstituicao)
	 * 
	 * @param dto o valor de dto
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCapital montarLancamentoContaCapital(IntegralizacaoCapitalDTO dto)throws BancoobException {
		return montarLancamentoContaCapital(dto, obterDataLote(dto.getIdInstituicao()));
	}
	
	/**
	 * {@link LancamentoIntegralizacaoExternaServicoEJB#montarLancamentoContaCapital(IntegralizacaoCapitalDTO)}
	 */
	private LancamentoContaCapital montarLancamentoContaCapital(IntegralizacaoCapitalDTO dto, DateTimeDB dataLote) throws BancoobException {
		ContaCapital contaCapital = null;
		if (dto.getIdContaCapital() == null) {
			contaCapital = contaCapitalServico.obterPorInstituicaoMatricula(dto.getIdInstituicao(), dto.getNumMatricula());
		} else {
			contaCapital = new ContaCapital();
			contaCapital.setId(dto.getIdContaCapital());
		}
		Integer numSeqLanc = (dto.getNumSeqLanc() == null ? obterUltimoNumSeqLanc(obterDataLote(dto.getIdInstituicao()), dto.getNumLoteLanc()):dto.getNumSeqLanc()); 				
		
		LancamentoContaCapital lancamentoDb2 = new LancamentoContaCapital();
		lancamentoDb2.setContaCapital(contaCapital);
		lancamentoDb2.setDataLancamento(dataLote);
		lancamentoDb2.setIdInstituicao(dto.getIdInstituicao());
		lancamentoDb2.setIdUsuario(dto.getIdUsuario() == null ? ContaCapitalConstantes.USR_EXTERNO_SISBR : dto.getIdUsuario());
		lancamentoDb2.setTipoSubscricao(null);
		lancamentoDb2.setValorLancamento(dto.getValorLancamento());		
		lancamentoDb2.setDescNumDocumento(dto.getDescNumDocumento() == null ? dto.getNumMatricula().toString() : dto.getDescNumDocumento());			
		lancamentoDb2.setTipoHistoricoCCA(new TipoHistoricoCCA(dto.getIdTipoHistoricoLanc().shortValue()));
		if (dto.getIdTipoHistoricoEstorno() != null) {
			lancamentoDb2.setTipoHistoricoEstorno(new TipoHistoricoCCA(dto.getIdTipoHistoricoEstorno().shortValue()));
		}
		lancamentoDb2.setTipoLote(new TipoLote(dto.getNumLoteLanc().shortValue()));				
		lancamentoDb2.setBolProcessado(ContaCapitalConstantes.NUM_ZERO.shortValue());
		lancamentoDb2.setDataHoraAtualizacao(new DateTimeDB());
		lancamentoDb2.setNumSeqLanc(numSeqLanc);
		lancamentoDb2.setDescOperacaoExterna(dto.getIdOperacaoOrigem());
		
		return lancamentoDb2;
	}	
	
	/**
	 * Monta a HistoricoLancamentoCCA do DB2.
	 *
	 * @param lancamentoContaCapital o valor de lancamento conta capital
	 * @return HistoricoLancamentoCCA
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private HistoricoLancamentoCCA montarHistLancamentoContaCapital(LancamentoContaCapital lancamentoContaCapital) throws BancoobException{
		HistoricoLancamentoCCA histLancamentoDb2 = new HistoricoLancamentoCCA();		
		histLancamentoDb2.setDataHoraAtualizacao(new DateTimeDB());
		histLancamentoDb2.setDataLancamento(lancamentoContaCapital.getDataLancamento());
		histLancamentoDb2.setDescNumDocumento(lancamentoContaCapital.getDescNumDocumento());
		histLancamentoDb2.setId(lancamentoContaCapital.getId());
		histLancamentoDb2.setIdContaCapital(lancamentoContaCapital.getContaCapital().getId());
		histLancamentoDb2.setIdHistLancEstornoContaCapital(lancamentoContaCapital.getIdLancamentoContaCapitalEstorno());
		histLancamentoDb2.setIdInstituicao(lancamentoContaCapital.getIdInstituicao());
		histLancamentoDb2.setIdTipoHistorico(lancamentoContaCapital.getTipoHistoricoCCA().getId());
		if (lancamentoContaCapital.getTipoHistoricoEstorno() != null) {
			histLancamentoDb2.setIdTipoHistoricoEstorno(lancamentoContaCapital.getTipoHistoricoEstorno().getId());
		}
		histLancamentoDb2.setIdTipoSubscricao(null);			
		histLancamentoDb2.setIdUsuario(lancamentoContaCapital.getIdUsuario());
		histLancamentoDb2.setValorLancamento(lancamentoContaCapital.getValorLancamento());
		histLancamentoDb2.setIdTipoLote(lancamentoContaCapital.getTipoLote().getId());
		histLancamentoDb2.setNumSeqLanc(lancamentoContaCapital.getNumSeqLanc());
		return histLancamentoDb2;
	}		
	
	/**
	 * {@link LancamentoIntegralizacaoExternaServicoEJB#montarCapaLoteCapitalLegado(DateTimeDB)}
	 */
	private CapaLoteCapitalLegado montarCapaLoteCapitalLegado(DateTimeDB data, Integer codigoLote) throws BancoobException {
		CapaLoteCapitalLegado entidade = null;		
		CapaLoteCapitalLegadoPK entidadePK = new CapaLoteCapitalLegadoPK(data, codigoLote);		
		CapaLoteCapitalLegado entidadeConsulta = capaLoteCapitalLegadoServico.obter(entidadePK); 
		
		if(entidadeConsulta != null){
			entidade = entidadeConsulta;
			entidade.setBolNovo(false);
			entidade.setValorTotalLoteApu((entidade.getValorTotalLoteApu()==null)?BigDecimal.ZERO:entidade.getValorTotalLoteApu());
			entidade.setValorTotalLoteInf(entidade.getValorTotalLoteInf()==null?BigDecimal.ZERO:entidade.getValorTotalLoteInf());
		}else{
			entidade = new CapaLoteCapitalLegado();
			entidade.setCapaLoteCapitalLegadoPK(entidadePK);
			entidade.setBolAtualizado(Boolean.FALSE);
			entidade.setiDProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
			entidade.setiDTipoHistoricoLanc(ContaCapitalConstantes.COD_HISTORICO_CCA_SUBSCRICAO);
			entidade.setQtdLancApu(0);
			entidade.setQtdLancInf(0);
			entidade.setValorTotalLoteApu(BigDecimal.ZERO);
			entidade.setValorTotalLoteInf(BigDecimal.ZERO);
			entidade.setBolNovo(Boolean.TRUE);			
		}
		
		return entidade;
	}	
	
	/**
	 * Monta a entidade Lancamentos para o legado .
	 *
	 * @param dto o valor de dto
	 * @param capaLoteCapitalLegado o valor de capa lote capital legado
	 * @param dataLote 
	 * @return LancamentosCCapitalLegado
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentosCCapitalLegado montarLancamentosCCapitalLegado(IntegralizacaoCapitalDTO dto, CapaLoteCapitalLegado capaLoteCapitalLegado, 
			DateTimeDB dataLote) throws BancoobException{
		 
		LancamentosCCapitalLegado entidade = new LancamentosCCapitalLegado();
		LancamentosCCapitalLegadoPK entidadePK = new LancamentosCCapitalLegadoPK();			
		entidadePK.setCapaLoteCapitalLegado(capaLoteCapitalLegado);
		
		Integer numSeqLanc = (dto.getNumSeqLanc() == null ? obterUltimoNumSeqLanc(dataLote, dto.getNumLoteLanc()):dto.getNumSeqLanc());		
		entidadePK.setNumSeqLanc(numSeqLanc); 
		
		ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();	
		contaCapitalLegado.setNumMatricula(dto.getNumMatricula());		
		entidade.setContaCapitalLegado(contaCapitalLegado);
		
		entidade.setDescNumDocumento(dto.getDescNumDocumento() == null ? dto.getNumMatricula().toString() : dto.getDescNumDocumento());			
		entidade.setiDTipoHistoricoLanc(dto.getIdTipoHistoricoLanc());
		entidade.setiDTipoHistoricoEstorno(dto.getIdTipoHistoricoEstorno());
		entidade.setLancamentosCCapitalLegadoPK(entidadePK);
		entidade.setBolAtualizado(Boolean.FALSE);
		entidade.setDataHoraInclusao(new DateTimeDB());
		entidade.setiDProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
		entidade.setValorLanc(dto.getValorLancamento());	
		entidade.setIdTipoSubscricao(null);
		entidade.setIdLancamentoContaCapital(ContaCapitalConstantes.IDLANCAMENTOCONTACAPITAL_NAOREPLICA); //Não Gerar Replicacao
		entidade.setDescOperacaoExterna(dto.getIdOperacaoOrigem());
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
	private DateTimeDB obterDataLote(Integer idInstituicao) throws BancoobException{
		return new DateTimeDB(prodLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, idInstituicao).getTime());
	}
	
	/**
	 * {@link LancamentoIntegralizacaoExternaServicoEJB#obterUltimoNumSeqLanc(DateTimeDB)}
	 */
	private Integer obterUltimoNumSeqLanc(DateTimeDB data, Integer codLote)throws BancoobException {
		return lancamentosCCapitalLegadoServico.obterUltimoNumSeqLanc(montarCapaLoteCapitalLegado(data, codLote))+1;
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoIntegralizacaoExternaServico#incluirRateioEmLote(java.util.List)
	 */
	public List<LancamentoContaCapital> incluirRateioEmLote(List<IntegralizacaoCapitalDTO> dtos) throws BancoobException {
		getLogger().info("LancamentoIntegralizacaoExternaServicoEJB.incluirRateioEmLote");
		List<LancamentoContaCapital> lancamentosIncluidos = null;
		if (dtos == null || dtos.isEmpty()) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_INTEG_RATEIO_LISTA_VAZIA");
		}
		try{
			lancamentosIncluidos = validarIncluirRateioEmLote(dtos);
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
		}catch (IntegracaoInstituicaoNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
		}  catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_003", e);
		}
		return lancamentosIncluidos;
	}

	/**
	 * Valida e inclui os lotes de lancamentos de rateio.
	 * @param dtos
	 * @return
	 * @throws BancoobException
	 */
	private List<LancamentoContaCapital> validarIncluirRateioEmLote(List<IntegralizacaoCapitalDTO> dtos) throws BancoobException {
		Integer idInstituicao = null;
		Integer numCooperativa = null;
		for (IntegralizacaoCapitalDTO dto : dtos) {
			validarIntegralizacaoRateioObrigatorios(dto);
			validarValoresObrigatorios(dto);
			if (idInstituicao == null) {
				idInstituicao = dto.getIdInstituicao();
				numCooperativa = instituicaoIntegracaoServico.obterNumeroCooperativa(idInstituicao);
				ContaCapitalIntegracaoLegadoDataSource.definirNumeroCooperativa(numCooperativa, false);
				validarFechamentoExecucao(numCooperativa);
			}
			if (idInstituicao != null && !idInstituicao.equals(dto.getIdInstituicao())) {
				throw new ContaCapitalMovimentacaoNegocioException("MSG_INTEG_RATEIO_INSTITUICAO", idInstituicao, dto.getIdInstituicao());
			}
		}
		return incluirLotesRateio(dtos, idInstituicao, numCooperativa);
	}

	/**
	 * Realiza os procedimento para incluir os lancamentos em lote no SQL e DB2.
	 * A dataLote eh recuperada uma unica vez e repassada para os metodos posteriores.
	 * O numSqlLanc eh recuperado uma unica vez (ultimo valor) e setado nos objetos sequencialmente.
	 * @param dtos
	 * @param idInstituicao
	 * @param numCooperativa
	 * @return
	 * @throws BancoobException
	 */
	private List<LancamentoContaCapital> incluirLotesRateio(List<IntegralizacaoCapitalDTO> dtos, Integer idInstituicao, Integer numCooperativa) throws BancoobException {
		getLogger().info("Incluindo lancamentos em lote para [Instituicao "+idInstituicao+"] | [Cooperativa "+numCooperativa+"]");
		DateTimeDB dataLote = obterDataLote(idInstituicao);
		Integer numSeqLanc = obterUltimoNumSeqLanc(dataLote, EnumTipoLote.COD_LOTE_CCA_DEST_RATEIO.getCodigo());
		for (IntegralizacaoCapitalDTO dto : dtos) {
			dto.setNumSeqLanc(numSeqLanc++);
		}
		incluirLotesRateioSQL(dataLote, dtos);
		getLogger().info("Atualizando capa lote");
		capaLoteCapitalLegadoServico.atualizarCapaLote(numCooperativa, dataLote, EnumTipoLote.COD_LOTE_CCA_DEST_RATEIO.getCodigo());
		return incluirLotesRateioDB2(dataLote, dtos);
	}

	/**
	 * Realiza a inclusao em lote dos lancamentos de rateio no DB2.
	 * @param dataLote
	 * @param dtos
	 * @return
	 * @throws BancoobException
	 */
	private List<LancamentoContaCapital> incluirLotesRateioDB2(DateTimeDB dataLote, List<IntegralizacaoCapitalDTO> dtos) throws BancoobException {
		getLogger().info("Incluindo lancamentos em lote DB2");
		List<LancamentoContaCapital> lancamentos = new ArrayList<LancamentoContaCapital>();
		for (IntegralizacaoCapitalDTO dto : dtos) {
			lancamentos.add(montarLancamentoContaCapital(dto, dataLote));
		}
		return lancamentoContaCapitalDao.incluirEmLote(lancamentos);
	}

	/**
	 * Realiza a inclusao em lote dos lancamentos de rateio no SQL.
	 * @param dataLote
	 * @param dtos
	 * @throws BancoobException
	 */
	private void incluirLotesRateioSQL(DateTimeDB dataLote, List<IntegralizacaoCapitalDTO> dtos) throws BancoobException {
		getLogger().info("Incluindo lancamentos em lote SQL");
		List<LancamentosCCapitalLegado> lancamentosLegado = new ArrayList<LancamentosCCapitalLegado>();
		CapaLoteCapitalLegado capaLoteCapitalLegado = montarCapaLoteCapitalLegado(dataLote, EnumTipoLote.COD_LOTE_CCA_DEST_RATEIO.getCodigo());
		if (capaLoteCapitalLegado.getBolNovo()){
			capaLoteCapitalLegadoServico.incluir(capaLoteCapitalLegado);
		}
		for (IntegralizacaoCapitalDTO dto : dtos) {
			lancamentosLegado.add(montarLancamentosCCapitalLegado(dto, capaLoteCapitalLegado, dataLote));
		}
		lancamentosCCapitalLegadoServico.incluirEmLote(lancamentosLegado);
	}

	private void validarIntegralizacaoRateioObrigatorios(IntegralizacaoCapitalDTO dtoIntegralizacao)throws BancoobException{
		if (dtoIntegralizacao.getIdInstituicao() == null){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_OBR_INTEG","A Instituição");		
		}	
		
		if (dtoIntegralizacao.getIdContaCapital() == null){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_OBR_INTEG","O ID Conta Capital");		
		}		
		
		if (dtoIntegralizacao.getIdTipoHistoricoLanc() == null){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_OBR_INTEG","O Tipo de Histórico");		
		}	
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoIntegralizacaoExternaServico#verificarLancamentoExistente(java.lang.Integer, java.lang.String)
	 */
	public Boolean verificarLancamentoExistente(Integer numCooperativa, Integer numMatricula, String descOperacaoExterna) throws BancoobException {
		return lancamentosCCapitalLegadoServico.verificarLancamentoExistente(numCooperativa, numMatricula, descOperacaoExterna);
	}
	
	/**
	 * @see LancamentoIntegralizacaoExternaServico#consultarLancamentoIntegralizacaoJaRealizada(IntegralizacaoCapitalDTO)
	 */
	public Boolean consultarLancamentoIntegralizacaoJaRealizada(IntegralizacaoCapitalDTO dto) throws BancoobException {
		return lancamentoContaCapitalDao.consultarLancamentoIntegralizacaoJaRealizada(dto);
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoIntegralizacaoExternaServico#consultarLancamentoEstornoRateio(br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital)
	 */
	public List<LancamentoEstornoRateioDTO> consultarLancamentoEstornoRateio(LancamentoContaCapital lancamento) throws BancoobException {
		return lancamentoContaCapitalDao.consultarLancamentoEstornoRateio(lancamento);
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoIntegralizacaoExternaServico#estornarRateio(java.lang.String, java.util.List)
	 */
	public List<LancamentoContaCapital> estornarRateio(String idUsuario, List<LancamentoEstornoRateioDTO> dtos) throws BancoobException {
		getLogger().info("LancamentoIntegralizacaoExternaServicoEJB.estornarRateio");
		Integer numCooperativa = validarParametrosEstornoRateio(dtos);
		Integer idInstituicao = dtos.get(0).getIdInstituicao();
		List<LancamentoEstornoRateioDTO> lancsCCAAtiva = separarLancsEstornoRateioPelaSituacaoCCA(dtos, true);
		List<LancamentoEstornoRateioDTO> lancsCCADesligada = separarLancsEstornoRateioPelaSituacaoCCA(dtos, false);
		List<IntegralizacaoCapitalDTO> dtosIntegralizacao = new ArrayList<IntegralizacaoCapitalDTO>();
		for (LancamentoEstornoRateioDTO dtoEstorno : lancsCCAAtiva) {
			dtosIntegralizacao.add(montarDtoIntegralizacaoEstornoRateioSubsInteg(idUsuario, dtoEstorno,
					EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_ESTORNO_GRUPO_SUBSCRICAO.getCodigo(), EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_SUBSC.getCodigo()));
			dtosIntegralizacao.add(montarDtoIntegralizacaoEstornoRateioSubsInteg(idUsuario, dtoEstorno,
					EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_ESTORNO_GRUPO_INTEGRALIZACAO.getCodigo(), EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_RATEIO.getCodigo()));
		}
		for (LancamentoEstornoRateioDTO dtoEstorno : lancsCCADesligada) {
			dtosIntegralizacao.add(montarDtoIntegralizacaoEstornoRateioSubsInteg(idUsuario, dtoEstorno,
					EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_ESTORNO_GRUPO_DEVOLUCAO.getCodigo(), EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_DEV_RATEIO_COOP_DESL.getCodigo()));
		}
		return incluirLotesRateio(dtosIntegralizacao, idInstituicao, numCooperativa);
	}
	
	private IntegralizacaoCapitalDTO montarDtoIntegralizacaoEstornoRateioSubsInteg(String idUsuario, LancamentoEstornoRateioDTO dtoEstorno, 
			Integer idTipoHistoricoLanc, Integer idTipoHistoricoEstorno) {
		IntegralizacaoCapitalDTO dtoIntegralizacao = new IntegralizacaoCapitalDTO();			
		dtoIntegralizacao.setIdInstituicao(dtoEstorno.getIdInstituicao());
		dtoIntegralizacao.setIdContaCapital(dtoEstorno.getIdContaCapital());
		dtoIntegralizacao.setNumMatricula(dtoEstorno.getNumContaCapital());
		dtoIntegralizacao.setValorLancamento(dtoEstorno.getValorLanc());
		dtoIntegralizacao.setIdUsuario(idUsuario);
		dtoIntegralizacao.setIdTipoHistoricoLanc(idTipoHistoricoLanc);		
		dtoIntegralizacao.setIdTipoHistoricoEstorno(idTipoHistoricoEstorno);
		dtoIntegralizacao.setDescNumDocumento(dtoEstorno.getDescNumDocumento());
		dtoIntegralizacao.setIdOperacaoOrigem(dtoEstorno.getDescOperacaoExterna());
		dtoIntegralizacao.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_DEST_RATEIO.getCodigo());
		return dtoIntegralizacao;
	}

	private Integer validarParametrosEstornoRateio(List<LancamentoEstornoRateioDTO> dtos) throws BancoobException {
		if (dtos == null || dtos.isEmpty()) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_ESTORNO_RATEIO_CHAVE_NAO_ENCONTRADA");
		}
		Integer idInstituicao = dtos.get(0).getIdInstituicao();
		Integer numCooperativa = instituicaoIntegracaoServico.obterNumeroCooperativa(idInstituicao);
		ContaCapitalIntegracaoLegadoDataSource.definirNumeroCooperativa(numCooperativa, false);
		validarFechamentoExecucao(numCooperativa);
		for (LancamentoEstornoRateioDTO dto : dtos) {
			if (!idInstituicao.equals(dto.getIdInstituicao())) {
				throw new ContaCapitalMovimentacaoNegocioException("MSG_INTEG_RATEIO_INSTITUICAO", idInstituicao, dto.getIdInstituicao());
			}
		}
		return numCooperativa;
	}	
	
	private List<LancamentoEstornoRateioDTO> separarLancsEstornoRateioPelaSituacaoCCA(List<LancamentoEstornoRateioDTO> lancamentos, boolean filtraCcaAtiva) {
		List<LancamentoEstornoRateioDTO> lancamentosFiltrados = new ArrayList<LancamentoEstornoRateioDTO>();
		for (LancamentoEstornoRateioDTO lancamento : lancamentos) {
			boolean ccaAtiva = EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo().equals(lancamento.getIdSituacaoContaCapital());
			if (filtraCcaAtiva == ccaAtiva) {
				lancamentosFiltrados.add(lancamento);
			} 
		}
		return lancamentosFiltrados;
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoIntegralizacaoExternaServico#verificarEstornoRealizado(br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital)
	 */
	public boolean verificarEstornoRealizado(LancamentoContaCapital lancamentoChave) throws BancoobException {
		return lancamentoContaCapitalDao.verificarEstornoRealizado(lancamentoChave);
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoIntegralizacaoExternaServico#validarCCAsSemSaldoParaEstornoRateio(br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital)
	 */
	public Map<String, List<Integer>> validarCCAsSemSaldoParaEstornoRateio(LancamentoContaCapital lancamentoChave) throws BancoobException {
		return lancamentoContaCapitalDao.validarCCAsSemSaldoParaEstornoRateio(lancamentoChave);
	}
	
}
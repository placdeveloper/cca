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
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorConfiguracaoCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.BloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.HistBloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.HistBloqueioCapitalPK;
import br.com.sicoob.cca.entidades.negocio.entidades.OrigemBloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumOrigemBloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.LancamentoContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.BloqueioContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.BloqueioContaCapitalServicoRemote;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDaoIF;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDaoFactory;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.BloqueioContaCapitalDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.HistBloqueioContaCapitalDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoInstituicaoNegocioException;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ProdutoLegadoDelegate;

/**
 * EJB contendo servicos relacionados a BloqueioContaCapital.
 *
 * @author Antonio.Genaro
 */
@Stateless
@Local (BloqueioContaCapitalServicoLocal.class)
@Remote(BloqueioContaCapitalServicoRemote.class) 
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BloqueioContaCapitalServicoEJB extends ContaCapitalMovimentacaoCrudServicoEJB<BloqueioCapital> implements BloqueioContaCapitalServicoLocal, BloqueioContaCapitalServicoRemote {

	private static final Integer NUM_PARAM_CAPITAL_BLOQUEADO = 1;
	
	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private BloqueioContaCapitalDao bloqueioContaCapitalDao;	
	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private HistBloqueioContaCapitalDao histBloqueioContaCapitalDao;	
	
	@EJB
	private ContaCapitalServicoLocal ccaServico;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ContaCapitalMovimentacaoCrudDaoIF<BloqueioCapital> getDAO() {
		return bloqueioContaCapitalDao;
	}	
	
	/** O atributo prodLegadoDelegate. */
	private ProdutoLegadoDelegate prodLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarProdutoLegadoDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BloqueioCapital incluir(BloqueioCapital bloqueioCapital) throws BancoobException {
		
		try{
			
			BloqueioCapital bloqueioCapitalInc = bloqueioContaCapitalDao.incluir(bloqueioCapital);
			incluirHistBloqueioCapital(bloqueioCapitalInc);		
			
			return bloqueioCapitalInc;
			
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
		}catch (IntegracaoInstituicaoNegocioException e) {
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
		}  catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_003",e);
		}		
		
	}	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(BloqueioCapital bloqueioCapital) throws BancoobException {
		
		try{
			
			bloqueioContaCapitalDao.alterar(bloqueioCapital);
			incluirHistBloqueioCapital(bloqueioCapital);		
			
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
		}catch (IntegracaoInstituicaoNegocioException e) {
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
		}  catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_003",e);
		}		
		
	}
	
	/**
	 * O método Incluir hist bloqueio capital.
	 *
	 * @param bloqueioCapital o valor de bloqueio capital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void incluirHistBloqueioCapital(BloqueioCapital bloqueioCapital) throws BancoobException{
		
		HistBloqueioCapital histBloqueioCapital = new HistBloqueioCapital();
		
		histBloqueioCapital.setId(new HistBloqueioCapitalPK(bloqueioCapital.getId()));
		histBloqueioCapital.setBolAtivo(bloqueioCapital.getBolAtivo());
		histBloqueioCapital.setIdContaCapital(bloqueioCapital.getContaCapital().getId());
		histBloqueioCapital.setDataFimBloqueio(bloqueioCapital.getDataFimBloqueio());
		histBloqueioCapital.setDataInicioBloqueio(bloqueioCapital.getDataInicioBloqueio());
		histBloqueioCapital.setIdOrigemBloqueioCapital(bloqueioCapital.getOrigemBloqueioCapital().getId());
		histBloqueioCapital.setValorBloqueio(bloqueioCapital.getValorBloqueio());
		histBloqueioCapital.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
		histBloqueioCapital.setDataProtocolo(bloqueioCapital.getDataProtocolo());
		histBloqueioCapital.setNumProtocolo(bloqueioCapital.getNumProtocolo());
		histBloqueioCapital.setNumProcesso(bloqueioCapital.getNumProcesso());
		
		histBloqueioContaCapitalDao.incluir(histBloqueioCapital);		
		
	}
	
	/**
	 * Retorna o soma do valor bloqueado via transferencia de capital.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @return BigDecimal
	 * @throws BancoobException lança a exceção BancoobException
	 */	
	public BigDecimal consultarValorBloqueadoViaTransferenciaCapital(Integer idContaCapital) throws BancoobException{
		ContaCapitalDelegate contaCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate();
		ContaCapital cca = contaCapitalDelegate.obter(idContaCapital);
		return bloqueioContaCapitalDao.consultarValorBloqueadoViaTransferenciaCapital(idContaCapital, prodLegadoDelegate.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, cca.getIdInstituicao()));
	}
	
	private BigDecimal consultarValorBloqueado(Integer idContaCapital) throws BancoobException {		
		ContaCapitalDelegate contaCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate();
		ContaCapital cca = contaCapitalDelegate.obter(idContaCapital);
		return bloqueioContaCapitalDao.consultarValorBloqueado(idContaCapital, prodLegadoDelegate.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, cca.getIdInstituicao()));
	}
	
	private BigDecimal consultarValorBloqueadoJudicial(Integer idContaCapital) throws BancoobException {		
		ContaCapitalDelegate contaCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate();
		ContaCapital cca = contaCapitalDelegate.obter(idContaCapital);
		return bloqueioContaCapitalDao.consultarValorBloqueadoPorTipo(idContaCapital, 
				prodLegadoDelegate.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, cca.getIdInstituicao()),
				EnumOrigemBloqueioCapital.JUDICIAL);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BloqueioContaCapitalDTO> consultarBloqueios(BloqueioContaCapitalDTO dto) throws BancoobException {
		validarConsulta(dto);
		return bloqueioContaCapitalDao.consultarBloqueios(dto, prodLegadoDelegate.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, dto.getIdInstituicao()));
	}
	
	private void validarConsulta(BloqueioContaCapitalDTO dto) throws ContaCapitalMovimentacaoNegocioException {
		final int qtdMinimaCaracteresNomePessoa = 3;
		if (dto.getIdInstituicao() == null) {
			throw new ContaCapitalMovimentacaoNegocioException("O campo Instituição é obrigatório.");
		}
		if (dto.getNomePessoa() != null && dto.getNomePessoa().length() < qtdMinimaCaracteresNomePessoa) {
			throw new ContaCapitalMovimentacaoNegocioException("Informe pelo menos 3 letras para o Nome.");
		}
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.BloqueioContaCapitalServico#calcularValorBloqueado(java.lang.Integer)
	 */
	public BigDecimal calcularValorBloqueado(Integer idContaCapital) throws BancoobException {
		ContaCapitalDelegate contaCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate();
		ValorConfiguracaoCapitalDelegate valorConfiguracaoCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorConfiguracaoCapitalDelegate();
		ContaCapital cca = contaCapitalDelegate.obter(idContaCapital);
		if ("0".equals(valorConfiguracaoCapitalDelegate.obterValorConfiguracao(NUM_PARAM_CAPITAL_BLOQUEADO, cca.getIdInstituicao()).getValorConfiguracao())){
			return cca.getValorBloq().add(consultarValorBloqueadoJudicial(cca.getId()));
		}			
		return cca.getValorBloq().add(consultarValorBloqueado(cca.getId()));			
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.BloqueioContaCapitalServico#incluir(br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO)
	 */
	public BloqueioCapital incluir(BloqueioContaCapitalDTO dto) throws BancoobException {
		BloqueioCapital bloqueio = null;
		if (validarBloqueio(dto)) {
			bloqueio = incluir(criarBloqueio(dto));
		}
		return bloqueio;
	}

	private BloqueioCapital criarBloqueio(BloqueioContaCapitalDTO dto) {
		ContaCapital cca = new ContaCapital();
		cca.setId(dto.getIdContaCapital());
		OrigemBloqueioCapital origemBloqueioCapital = new OrigemBloqueioCapital();
		origemBloqueioCapital.setId(dto.getCodTipoBloqueio().shortValue());
		
		BloqueioCapital bloqueio = new BloqueioCapital();
		bloqueio.setBolAtivo(Short.valueOf("1"));
		bloqueio.setContaCapital(cca);
		bloqueio.setDataInicioBloqueio(new DateTimeDB());
		bloqueio.setDataProtocolo(dto.getDataProtocolo());
		bloqueio.setNumProcesso(dto.getNumProcesso());
		bloqueio.setNumProtocolo(dto.getNumProtocolo());
		bloqueio.setOrigemBloqueioCapital(origemBloqueioCapital);
		bloqueio.setValorBloqueio(dto.getValorBloqueado());
		
		return bloqueio;
	}

	private boolean validarBloqueio(BloqueioContaCapitalDTO dto) throws BancoobException {
		validarCamposObrigatorios(dto);
		validarSituacaoContaCapital(dto);
		validarDataProtocolo(dto);
		validarValorBloqueio(dto);
		return true;
	}

	private void validarValorBloqueio(BloqueioContaCapitalDTO dto) throws BancoobException {
		if (dto.getValorBloqueado().compareTo(BigDecimal.ZERO) < 1) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_BLOQ_VALOR_MAIOR_ZERO");
		}
		LancamentoContaCapitalDelegate lancamentoDelegate = 
				ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarLancamentoContaCapitalDelegate();
		BigDecimal valorBloqueado = calcularValorBloqueado(dto.getIdContaCapital());
		BigDecimal valorIntegralizado = lancamentoDelegate.calcularValorIntegralizado(dto.getIdContaCapital());
		BigDecimal valorDisponivel = valorIntegralizado.subtract(valorBloqueado);
		if (dto.getValorBloqueado().compareTo(valorDisponivel) > 0) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_BLOQ_VALOR_SALDO_DISP");
		}
	}

	private void validarDataProtocolo(BloqueioContaCapitalDTO dto) throws ContaCapitalMovimentacaoNegocioException {
		if (EnumOrigemBloqueioCapital.JUDICIAL.getCodigo().equals(dto.getCodTipoBloqueio())
				&& dto.getDataProtocolo().after(new Date())) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_BLOQ_DATA_PROTOCOLO");
		}
	}

	private void validarSituacaoContaCapital(BloqueioContaCapitalDTO dto) throws BancoobException {
		ContaCapital cca = ccaServico.obter(dto.getIdContaCapital());
		Short situacaoAtivo = Short.valueOf(EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo().shortValue());
		if (!situacaoAtivo.equals(cca.getSituacaoContaCapital().getId()) || 
				!EnumSituacaoCadastroProposta.COD_APROVADO.getCodigo().equals(cca.getSituacaoCadastroProposta().getId())) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_BLOQ_SITUACAO_CCA");
		}
	}

	private void validarCamposObrigatorios(BloqueioContaCapitalDTO dto) throws ContaCapitalMovimentacaoNegocioException {
		if(dto.getIdInstituicao() == null) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_CAMPO_OBRIGATORIO", "Instituição");
		}
		if(dto.getIdContaCapital() == null) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_CAMPO_OBRIGATORIO", "Conta Capital");
		}
		if (dto.getCodTipoBloqueio() == null) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_CAMPO_OBRIGATORIO", "Tipo de Bloqueio");
		}
		if (dto.getDataProtocolo() == null) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_CAMPO_OBRIGATORIO", "Data do Protocolo");
		}
		if (!isStringPreenchida(dto.getNumProtocolo()) && !isStringPreenchida(dto.getNumProcesso())) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_BLOQ_NUMPROTOCOLO_NUMPROCESSO");
		}
		if (dto.getValorBloqueado() == null) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_CAMPO_OBRIGATORIO", "Valor do Bloqueio");
		}
	}
	
	private boolean isStringPreenchida(String texto) {
		return texto != null && texto.trim().length() > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public void desbloquear(BloqueioContaCapitalDTO dto) throws BancoobException {
		if (validarDesbloqueio(dto)) {
			BloqueioCapital bloqueio = obter(dto.getIdBloqueio());
			if (bloqueio.getValorBloqueio().compareTo(dto.getValorDesbloqueio()) == 0) {
				bloqueio.setBolAtivo(Short.valueOf("0"));
				bloqueio.setDataFimBloqueio(new DateTimeDB());
				bloqueio.setValorBloqueio(BigDecimal.ZERO);
			} else {
				bloqueio.setValorBloqueio(bloqueio.getValorBloqueio().subtract(dto.getValorDesbloqueio()));
			}
			bloqueioContaCapitalDao.alterar(bloqueio);
			incluirHistBloqueioCapital(bloqueio);
		}
	}

	private boolean validarDesbloqueio(BloqueioContaCapitalDTO dto) throws BancoobException {
		if (dto.getValorDesbloqueio() == null) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_CAMPO_OBRIGATORIO", "Valor do Desbloqueio"); 
		}
		if (dto.getValorDesbloqueio().compareTo(BigDecimal.ZERO) < 1) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_DESBLOQ_VALOR_MAIOR_ZERO");
		}
		if (dto.getValorDesbloqueio().compareTo(dto.getValorBloqueado()) > 0) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_DESBLOQ_VALOR_BLOQ");
		}
		return true;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public void excluirBloqueioCapital(Integer idBloqueioCapital) throws BancoobException {
		bloqueioContaCapitalDao.excluirBloqueioCapital(idBloqueioCapital);
	}	
}

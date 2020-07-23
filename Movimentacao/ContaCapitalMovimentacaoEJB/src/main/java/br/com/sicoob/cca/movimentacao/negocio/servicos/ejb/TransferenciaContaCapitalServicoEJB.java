package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.joda.time.DateTime;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.excecao.ContaCapitalCadastroNegocioException;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ValorConfiguracaoCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ValorCotaServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.entidades.negocio.entidades.BloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.OrigemBloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoLote;
import br.com.sicoob.cca.movimentacao.negocio.dto.TransferenciaRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.BloqueioContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.TransferenciaContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.TransferenciaContaCapitalServicoRemote;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.ContaCapitalIntegracaoNegocioException;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ParcelamentoCCALegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
 * EJB contendo servicos relacionados a TransferenciaContaCapital.
 *
 * @author Antonio.Genaro
 */
@Stateless
@Local (TransferenciaContaCapitalServicoLocal.class)
@Remote(TransferenciaContaCapitalServicoRemote.class) 
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TransferenciaContaCapitalServicoEJB extends ContaCapitalMovimentacaoServicoEJB implements TransferenciaContaCapitalServicoLocal, TransferenciaContaCapitalServicoRemote {
	
	@Resource
	private SessionContext context;
	
	@EJB
	private FechamentoContaCapitalServicoLocal fechamentoServico;
	
	@EJB
	private ParcelamentoCCALegadoServicoLocal parcelamentoCCALegadoServico;
	
	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;	
	
	@EJB
	private ValorCotaServicoLocal valorCotaServico;	
	
	@EJB
	private ValorConfiguracaoCapitalServicoLocal valorConfiguracaoCapitalServico;
	
	@EJB
	private BloqueioContaCapitalServicoLocal bloqueioContaCapitalServico;
	
	@EJB
	private ProdutoLegadoServicoLocal prodLegadoServico;	
	
	@EJB
	private LancamentoContaCapitalServicoLocal lancamentoContaCapitalServico;
	
	/** A constante NUM_PARAM_CAPITAL_BLOQUEADO. */
	private static final Integer NUM_PARAM_CAPITAL_BLOQUEADO = 1;
	
	/** A constante NUM_PARAM_MESES_BLOQUEADO_TRANSFERENCIA. */
	private static final Integer NUM_PARAM_MESES_BLOQUEADO_TRANSFERENCIA = 2;
	
	/**
	 * O método Incluir.
	 *
	 * @param transferenciaRenDTO o valor de transferencia ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DevolucaoContaCapitalServico#incluir()
	 */
	public void incluir(TransferenciaRenDTO transferenciaRenDTO) throws BancoobException {				
		
		try {
			
			validarIncluir(transferenciaRenDTO);
			
			List<LancamentoContaCapital> listaLancamentoContaCapital = montarListaLancamentoTransferenciaContaCapital(transferenciaRenDTO);					
			for(LancamentoContaCapital item : listaLancamentoContaCapital){
				this.getLogger().info("CCA.lancamentosCCapitalLegadoServico.incluir");				
				lancamentoContaCapitalServico.incluir(item);
			}					
			
			incluirBloqueioCapital(transferenciaRenDTO);
		
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
			
		} catch (ContaCapitalCadastroNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
			
		} catch (ContaCapitalIntegracaoNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
		}  catch (BancoobException e) {
			context.setRollbackOnly();
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_003", e);
		}	
		
	}		
	
	/**
	 * O método Incluir bloqueio capital.
	 *
	 * @param transferenciaRenDTO o valor de transferencia ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void incluirBloqueioCapital(TransferenciaRenDTO transferenciaRenDTO) throws BancoobException {
		
		if(valorConfiguracaoCapitalServico.obterValorConfiguracao(NUM_PARAM_CAPITAL_BLOQUEADO, Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao())).getValorConfiguracao().equals("1")){		
			Date dataAtualProduto = prodLegadoServico.obterDataAtualProdutoCCALogado();			
			
			BloqueioCapital bloqueioCapital = new BloqueioCapital();
			OrigemBloqueioCapital origemBloqueioCapital = new OrigemBloqueioCapital();
			origemBloqueioCapital.setId(ContaCapitalConstantes.COD_ORIGEM_BLOQUEIO_CAPITAL_TRANSFERENCIA);
			
			ContaCapital contaCapital = contaCapitalServico.obter(transferenciaRenDTO.getIdContaCapitalCredito());						
			
			bloqueioCapital.setBolAtivo(ContaCapitalConstantes.ST_BOL_ATIVO.shortValue());
			bloqueioCapital.setContaCapital(contaCapital);
			bloqueioCapital.setDataInicioBloqueio(new DateTimeDB(dataAtualProduto.getTime()));
			bloqueioCapital.setOrigemBloqueioCapital(origemBloqueioCapital);
			bloqueioCapital.setValorBloqueio(transferenciaRenDTO.getVlrTransferir());
			
			Integer meses = Integer.valueOf(valorConfiguracaoCapitalServico.obterValorConfiguracao(NUM_PARAM_MESES_BLOQUEADO_TRANSFERENCIA, transferenciaRenDTO.getIdInstituicaoCredito()).getValorConfiguracao());
			DateTime dataFimBloqueioPorTempo = new DateTime(dataAtualProduto.getTime()).plusMonths(meses);						
			bloqueioCapital.setDataFimBloqueio(new DateTimeDB(dataFimBloqueioPorTempo.toDate().getTime()));
			
			bloqueioContaCapitalServico.incluir(bloqueioCapital);
		}		
		
	}
	
	/**
	 * O método Validar incluir.
	 *
	 * @param transferenciaRenDTO o valor de transferencia ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarIncluir(TransferenciaRenDTO transferenciaRenDTO) throws BancoobException {
		
		validarFechamento();	
		validarSaldoTransferencia(transferenciaRenDTO);
		validarParcelasEmAberto(transferenciaRenDTO.getNumContaCapitalDebito());	
		
	}		
	
	/**
	 * O método Validar fechamento.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarFechamento() throws BancoobException {
		
		if(fechamentoServico.isFechamentoIniciado(Integer.valueOf(InformacoesUsuario.getInstance().getCooperativa()))) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_002");
		}		
		
	}	
	
	/**
	 * O método Validar parcelas em aberto.
	 *
	 * @param numContaCapital o valor de num conta capital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarParcelasEmAberto(Integer numContaCapital) throws BancoobException {
		
		if(parcelamentoCCALegadoServico.verificarParcelamentoAberto(numContaCapital, ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_INTEGRAL) > 0 ){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_TRANSF_PARC_ABERTO");
		}				
	}	
		
	/**
	 * O método Validar saldo transferencia.
	 *
	 * @param transferenciaRenDTO o valor de transferencia ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarSaldoTransferencia(TransferenciaRenDTO transferenciaRenDTO) throws BancoobException {	
		ContaCapital contaCapital = contaCapitalServico.obter(transferenciaRenDTO.getIdContaCapitalDebito());		
		BigDecimal valorIntegralizado = lancamentoContaCapitalServico.calcularValorIntegralizado(contaCapital.getId());
		BigDecimal valorMinimoSubscricao = valorCotaServico.obterValorMinimoSubscricao(contaCapital.getIdInstituicao(), contaCapital.getIdPessoa());
		BigDecimal valorBloqueado = bloqueioContaCapitalServico.calcularValorBloqueado(contaCapital.getId());
		BigDecimal valorTotalDisponivel =  valorIntegralizado.subtract(valorMinimoSubscricao.add(valorBloqueado));
		
		if(valorTotalDisponivel.compareTo(transferenciaRenDTO.getVlrTransferir()) < 0){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_TRANSF_SALDO");			
		}
	}
	
	/**
	 * Montar lancamento transferencia conta capital.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idTipoHistoricoCCA o valor de id tipo historico cca
	 * @param valorLancamento o valor de valor lancamento
	 * @param descNumDocumento o valor de desc num documento
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCapital montarLancamentoTransferenciaContaCapital(Integer idContaCapital, Short idTipoHistoricoCCA, BigDecimal valorLancamento, String descNumDocumento) throws BancoobException{
		
		TipoLote tipoLote = new TipoLote(); 
		tipoLote.setId(ContaCapitalConstantes.COD_LOTE_CCA_TRANSFERENCIA.shortValue());
		
		TipoHistoricoCCA tipoHistoricoCCA = new TipoHistoricoCCA(); 
		tipoHistoricoCCA.setId(idTipoHistoricoCCA);
		
		ContaCapital contaCapital = contaCapitalServico.obter(idContaCapital);				
		LancamentoContaCapital lancamentoContaCapitalDevol = new LancamentoContaCapital();
		lancamentoContaCapitalDevol.setContaCapital(contaCapital);
		lancamentoContaCapitalDevol.setDataLancamento(new DateTimeDB(prodLegadoServico.obterDataAtualProduto(
				ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, contaCapital.getIdInstituicao()).getTime()));
		lancamentoContaCapitalDevol.setIdInstituicao(contaCapital.getIdInstituicao());
		lancamentoContaCapitalDevol.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
		lancamentoContaCapitalDevol.setTipoSubscricao(null);
		lancamentoContaCapitalDevol.setValorLancamento(valorLancamento);		
		lancamentoContaCapitalDevol.setTipoLote(tipoLote);				
		lancamentoContaCapitalDevol.setBolProcessado(ContaCapitalConstantes.NUM_ZERO.shortValue());
		lancamentoContaCapitalDevol.setDataHoraAtualizacao(new DateTimeDB());
		lancamentoContaCapitalDevol.setTipoHistoricoCCA(tipoHistoricoCCA);		
		lancamentoContaCapitalDevol.setDescNumDocumento(descNumDocumento);				
		
		return lancamentoContaCapitalDevol;					
	}	
	
	/**
	 * Montar lista lancamento transferencia conta capital.
	 *
	 * @param transferenciaRenDTO o valor de transferencia ren dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<LancamentoContaCapital> montarListaLancamentoTransferenciaContaCapital(TransferenciaRenDTO transferenciaRenDTO) throws BancoobException{
		
		List<LancamentoContaCapital> listaLancamentoContaCapital = new ArrayList<LancamentoContaCapital>();		
		//Lançamentos de débito
		listaLancamentoContaCapital.add(montarLancamentoTransferenciaContaCapital(transferenciaRenDTO.getIdContaCapitalDebito(), ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSF_BAIXA_SUBSCRICAO.shortValue(), transferenciaRenDTO.getVlrTransferir(), transferenciaRenDTO.getNumContaCapitalCredito().toString()));					
		listaLancamentoContaCapital.add(montarLancamentoTransferenciaContaCapital(transferenciaRenDTO.getIdContaCapitalDebito(), ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSF_BAIXA_INTEG.shortValue(), transferenciaRenDTO.getVlrTransferir(), transferenciaRenDTO.getNumContaCapitalCredito().toString()));					
		//Lançamentos de crédito
		listaLancamentoContaCapital.add(montarLancamentoTransferenciaContaCapital(transferenciaRenDTO.getIdContaCapitalCredito(), ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSF_SUBSCRICAO.shortValue(), transferenciaRenDTO.getVlrTransferir(), transferenciaRenDTO.getNumContaCapitalDebito().toString()));					
		listaLancamentoContaCapital.add(montarLancamentoTransferenciaContaCapital(transferenciaRenDTO.getIdContaCapitalCredito(), ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSFERENCIA_INTEG.shortValue(), transferenciaRenDTO.getVlrTransferir(), transferenciaRenDTO.getNumContaCapitalDebito().toString()));					
		
		return listaLancamentoContaCapital;		
	}		
		
}

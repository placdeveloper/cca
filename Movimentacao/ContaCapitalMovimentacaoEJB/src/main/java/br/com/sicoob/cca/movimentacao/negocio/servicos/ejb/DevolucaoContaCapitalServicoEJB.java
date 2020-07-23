package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
import br.com.sicoob.cca.comum.negocio.annotation.LogCCA;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoLote;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumMetodoOperacaoContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.DevolucaoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.DevolucaoContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.DevolucaoContaCapitalServicoRemote;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.CaptacaoRemuneradaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaFisicaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.ContaCapitalIntegracaoNegocioException;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CaptacaoRemuneradaIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.ContaCorrenteIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.GenIntIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ParcelamentoCCALegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
 * EJB contendo servicos relacionados a DevolucaoContaCapital.
 *
 * @author Antonio.Genaro
 */
@Stateless
@Local (DevolucaoContaCapitalServicoLocal.class)
@Remote(DevolucaoContaCapitalServicoRemote.class) 
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DevolucaoContaCapitalServicoEJB extends ContaCapitalMovimentacaoServicoEJB implements DevolucaoContaCapitalServicoLocal, DevolucaoContaCapitalServicoRemote {		
	
	@Resource
	private SessionContext context;
	
	@EJB
	private GenIntIntegracaoServicoLocal genIntIntegracaoServico;
	
	@EJB
	private ProdutoLegadoServicoLocal prodLegadoServico;	
	
	@EJB
	private FechamentoContaCapitalServicoLocal fechamentoServico;
	
	@EJB
	private ContaCorrenteIntegracaoServicoLocal contaCorrenteIntegracaoServico;
	
	@EJB
	private ParcelamentoContaCapitalServicoLocal parcelamentoContaCapitalServico;
	
	@EJB
	private LancamentoContaCapitalServicoLocal lancamentoContaCapitalServico;	
	
	@EJB
	private ParcelamentoCCALegadoServicoLocal parcelamentoCCALegadoServico;
	
	@EJB
	private ValorConfiguracaoCapitalServicoLocal valorConfiguracaoCapitalServico;
	
	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;
	
	@EJB
	private CapesIntegracaoServicoLocal capesIntegracaoServico;		
	
	@EJB
	private CaptacaoRemuneradaIntegracaoServicoLocal captacaoRemuneradaIntegracaoServico;
	
	@EJB
	private ValorCotaServicoLocal valorCotaServico;
	
	//TEMPO MÍNIMO EM MESES DE ASSOCIAÇÃO PARA DEVOLUÇÃO EVENTUAL DE CAPITAL	
	/** A constante NUM_TEMPO_MINIMO_MESES_DEVOLUCAO. */
	private static final Integer NUM_TEMPO_MINIMO_MESES_DEVOLUCAO = 1570;
	//IDADE MÍNIMA PARA DEVOLUÇÃO EVENTUAL DE CAPITAL	
	/** A constante NUM_IDADE_MINIMA_DEVOLUCAO. */
	private static final Integer NUM_IDADE_MINIMA_DEVOLUCAO = 1571;	
	
	/**
	 * O método Incluir.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @param lstParcelamentoRenDTO o valor de lst parcelamento ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DevolucaoContaCapitalServico#incluir()
	 */
	@LogCCA(metodo=EnumMetodoOperacaoContaCapital.MOVIMENTACAO_DEVOLUCAOCONTACAPITALSERVICO_INCLUIR)
	public void incluir(DevolucaoRenDTO devolucaoRenDTO, List<ParcelamentoRenDTO> lstParcelamentoRenDTO) throws BancoobException {				
		
		try {
			
			validarIncluir(devolucaoRenDTO);
			validarQtdParcelasDevolucao(lstParcelamentoRenDTO.size());
			validarParcelas(devolucaoRenDTO, lstParcelamentoRenDTO);	
			
			Parcelamento parcelamento = parcelamentoContaCapitalServico.incluirParcelas(devolucaoRenDTO.getNumContaCapital(), ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_DEVOLUCAO, lstParcelamentoRenDTO);
			List<LancamentoContaCapital> listaLancamentoContaCapital = montarListaLancamentoContaCapital(devolucaoRenDTO, parcelamento);					
			for(LancamentoContaCapital item : listaLancamentoContaCapital){
				this.getLogger().info("CCA.lancamentosCCapitalLegadoServico.incluir");				
				lancamentoContaCapitalServico.incluir(item);
			}						
			
			//Gera lancamentos em conta corrente se forma de integralização via conta
			if (devolucaoRenDTO.getTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.shortValue()
					&& parcelamento.getDataVencimento().equals(prodLegadoServico.obterDataAtualProdutoCCALogado())){
				this.getLogger().info("CCA.incluirLancamentoCCO");						
				incluirLancamentoCCO(devolucaoRenDTO);
			}										
		
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
			
		} catch (ContaCapitalCadastroNegocioException e) {
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
			
		} catch (ContaCapitalIntegracaoNegocioException e) {
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
		}  catch (BancoobException e) {
			context.setRollbackOnly();
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_003", e);
		}	
		
	}	

	/**
	 * O método Incluir captacao remunerada.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DevolucaoContaCapitalServico#incluirCaptacaoRemunerada()
	 */
	@LogCCA(metodo=EnumMetodoOperacaoContaCapital.MOVIMENTACAO_DEVOLUCAOCONTACAPITALSERVICO_INCLUIRCAPTACAOREMUNERADA)
	public void incluirCaptacaoRemunerada(DevolucaoRenDTO devolucaoRenDTO) throws BancoobException {				
		
		try {
			
			validarIncluir(devolucaoRenDTO);						
			
			List<LancamentoContaCapital> listaLancamentoContaCapital = montarListaLancamentoContaCapitalCaptacaoRemunerada(devolucaoRenDTO);					
			for(LancamentoContaCapital item : listaLancamentoContaCapital){
				this.getLogger().info("CCA.lancamentosCCapitalLegadoServico.incluir");				
				lancamentoContaCapitalServico.incluir(item);
			}						
			
			incluirAplicacaoCaptacaoRemunerada(devolucaoRenDTO);
			incluirLancamentoCCO(devolucaoRenDTO);
		
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
			
		} catch (ContaCapitalCadastroNegocioException e) {
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);

		} catch (ContaCapitalIntegracaoNegocioException e) {
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
		}  catch (BancoobException e) {
			context.setRollbackOnly();
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_003", e);
		}	
		
	}	
					
	/**
	 * Montar lista lancamento conta capital.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @param parcelamento o valor de parcelamento
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<LancamentoContaCapital> montarListaLancamentoContaCapital(DevolucaoRenDTO devolucaoRenDTO, Parcelamento parcelamento) throws BancoobException{
		
		List<LancamentoContaCapital> listaLancamentoContaCapital = new ArrayList<LancamentoContaCapital>();		
		if (parcelamento.getContaCapital().getSituacaoContaCapital().getId() == ContaCapitalConstantes.COD_SITUACAO_COOPERADO_ATIVO.shortValue()) {
			listaLancamentoContaCapital.add(montarLancamentoContaCapital(devolucaoRenDTO, parcelamento, ContaCapitalConstantes.COD_HISTORICO_CCA_BAIXA_SUBSCRICAO.shortValue()));					
			listaLancamentoContaCapital.add(montarLancamentoContaCapital(devolucaoRenDTO, parcelamento, ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSF_A_RESTITUIR.shortValue()));								
			listaLancamentoContaCapital.add(montarLancamentoContaCapital(devolucaoRenDTO, parcelamento, ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSF_RESTITUIR_INTEG.shortValue()));								
		}
	
		if (parcelamento.getDataVencimento().equals(prodLegadoServico.obterDataAtualProdutoCCALogado())) {
			if (devolucaoRenDTO.getTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.shortValue()) {
				listaLancamentoContaCapital.add(montarLancamentoContaCapital(devolucaoRenDTO, parcelamento, ContaCapitalConstantes.COD_HISTORICO_CCA_DEVOLUCAO_CHQ_ADM.shortValue()));					
			} else if (devolucaoRenDTO.getTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.shortValue()) {
				listaLancamentoContaCapital.add(montarLancamentoContaCapital(devolucaoRenDTO, parcelamento, ContaCapitalConstantes.COD_HISTORICO_CCA_DEVOLUCAO_VIA_CC.shortValue()));					
			}						
		}
		
		return listaLancamentoContaCapital;		
	}	
	
	/**
	 * Montar lancamento conta capital.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @param parcelamento o valor de parcelamento
	 * @param idTipoHistoricoCCA o valor de id tipo historico cca
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCapital montarLancamentoContaCapital(DevolucaoRenDTO devolucaoRenDTO, Parcelamento parcelamento, Short idTipoHistoricoCCA) throws BancoobException{
		
		TipoLote tipoLote = new TipoLote(); 
		tipoLote.setId(ContaCapitalConstantes.COD_LOTE_CCA_PARC_AVISTA.shortValue());
		
		TipoHistoricoCCA tipoHistoricoCCA = new TipoHistoricoCCA(); 
		tipoHistoricoCCA.setId(idTipoHistoricoCCA);
		
		LancamentoContaCapital lancamentoContaCapitalDevol = new LancamentoContaCapital();
		lancamentoContaCapitalDevol.setContaCapital(parcelamento.getContaCapital());
		lancamentoContaCapitalDevol.setDataLancamento(new DateTimeDB(prodLegadoServico.obterDataAtualProduto(
				ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, devolucaoRenDTO.getIdInstituicao()).getTime()));
		lancamentoContaCapitalDevol.setIdInstituicao(devolucaoRenDTO.getIdInstituicao());
		lancamentoContaCapitalDevol.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
		lancamentoContaCapitalDevol.setTipoSubscricao(null);
		lancamentoContaCapitalDevol.setTipoLote(tipoLote);				
		lancamentoContaCapitalDevol.setBolProcessado(ContaCapitalConstantes.NUM_ZERO.shortValue());
		lancamentoContaCapitalDevol.setDataHoraAtualizacao(new DateTimeDB());
		lancamentoContaCapitalDevol.setTipoHistoricoCCA(tipoHistoricoCCA);
		
		if(parcelamento.getContaCapital().getSituacaoContaCapital().getId() == ContaCapitalConstantes.COD_SITUACAO_COOPERADO_ATIVO.shortValue()){
			lancamentoContaCapitalDevol.setDescNumDocumento(ContaCapitalConstantes.COD_DESC_DOCUMENTO_DEV_CAPITAL_ATIVO+parcelamento.getNumParcelamento().toString());							
		}else{			
			lancamentoContaCapitalDevol.setDescNumDocumento(parcelamento.getContaCapital().getNumContaCapital().toString());				
		}		
		
		if (idTipoHistoricoCCA == ContaCapitalConstantes.COD_HISTORICO_CCA_BAIXA_SUBSCRICAO.shortValue()
				|| idTipoHistoricoCCA == ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSF_A_RESTITUIR.shortValue()
				|| idTipoHistoricoCCA == ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSF_RESTITUIR_INTEG.shortValue()
				){
			lancamentoContaCapitalDevol.setValorLancamento(devolucaoRenDTO.getVlrDevolucao());					
		}else{
			lancamentoContaCapitalDevol.setValorLancamento(devolucaoRenDTO.getVlrAVista());					
		}
		
		return lancamentoContaCapitalDevol;					
	}
	
	/**
	 * Montar lista lancamento conta capital captacao remunerada.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<LancamentoContaCapital> montarListaLancamentoContaCapitalCaptacaoRemunerada(DevolucaoRenDTO devolucaoRenDTO) throws BancoobException{
		
		List<LancamentoContaCapital> listaLancamentoContaCapital = new ArrayList<LancamentoContaCapital>();		
		
		listaLancamentoContaCapital.add(montarLancamentoContaCapitalCaptacaoRemunerada(devolucaoRenDTO, ContaCapitalConstantes.COD_HISTORICO_CCA_BAIXA_SUBSCRICAO.shortValue()));					
		listaLancamentoContaCapital.add(montarLancamentoContaCapitalCaptacaoRemunerada(devolucaoRenDTO, ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSF_A_RESTITUIR.shortValue()));								
		listaLancamentoContaCapital.add(montarLancamentoContaCapitalCaptacaoRemunerada(devolucaoRenDTO, ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSF_RESTITUIR_INTEG.shortValue()));								
		listaLancamentoContaCapital.add(montarLancamentoContaCapitalCaptacaoRemunerada(devolucaoRenDTO, ContaCapitalConstantes.COD_HISTORICO_CCA_DEVOLUCAO_VIA_CAP_REM.shortValue()));					
		
		return listaLancamentoContaCapital;		
	}		
	
	/**
	 * Montar lancamento conta capital captacao remunerada.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @param idTipoHistoricoCCA o valor de id tipo historico cca
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCapital montarLancamentoContaCapitalCaptacaoRemunerada(DevolucaoRenDTO devolucaoRenDTO, Short idTipoHistoricoCCA) throws BancoobException{
		
		TipoLote tipoLote = new TipoLote(); 
		tipoLote.setId(ContaCapitalConstantes.COD_LOTE_CCA_PARC_AVISTA.shortValue());
		
		TipoHistoricoCCA tipoHistoricoCCA = new TipoHistoricoCCA(); 
		tipoHistoricoCCA.setId(idTipoHistoricoCCA);
		
		ContaCapital contaCapital = contaCapitalServico.obter(devolucaoRenDTO.getIdContaCapital());				
		LancamentoContaCapital lancamentoContaCapitalDevol = new LancamentoContaCapital();
		lancamentoContaCapitalDevol.setContaCapital(contaCapital);
		lancamentoContaCapitalDevol.setDataLancamento(new DateTimeDB(prodLegadoServico.obterDataAtualProduto(
				ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, devolucaoRenDTO.getIdInstituicao()).getTime()));
		lancamentoContaCapitalDevol.setIdInstituicao(devolucaoRenDTO.getIdInstituicao());
		lancamentoContaCapitalDevol.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
		lancamentoContaCapitalDevol.setTipoSubscricao(null);
		lancamentoContaCapitalDevol.setValorLancamento(devolucaoRenDTO.getVlrAVista());		
		lancamentoContaCapitalDevol.setTipoLote(tipoLote);				
		lancamentoContaCapitalDevol.setBolProcessado(ContaCapitalConstantes.NUM_ZERO.shortValue());
		lancamentoContaCapitalDevol.setDataHoraAtualizacao(new DateTimeDB());
		lancamentoContaCapitalDevol.setTipoHistoricoCCA(tipoHistoricoCCA);		
		lancamentoContaCapitalDevol.setDescNumDocumento(devolucaoRenDTO.getNumContaCapital().toString());				
		
		return lancamentoContaCapitalDevol;					
	}	
	
	/**
	 * O método Incluir lancamento cco.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void incluirLancamentoCCO(DevolucaoRenDTO devolucaoRenDTO) throws BancoobException {
		
		LancamentoContaCorrenteIntegracaoDTO lancDtoCco = montarLancamentoCco(devolucaoRenDTO);
		LancamentoContaCorrenteIntegracaoRetDTO lancDtoCcoRet = contaCorrenteIntegracaoServico.gravarLancamentosIntegracao(lancDtoCco);

		if (lancDtoCcoRet.getCodRetorno().equals(ContaCapitalConstantes.NUM_ZERO)){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_004", lancDtoCcoRet.getMensagem());
		}	
		
	}
	
	/**
	 * O método Incluir aplicacao captacao remunerada.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void incluirAplicacaoCaptacaoRemunerada(DevolucaoRenDTO devolucaoRenDTO) throws BancoobException {
		
		CaptacaoRemuneradaIntegracaoDTO captacaoRemuneradaIntegracaoDTO = montarAplicacaoCaptacaoRemunerada(devolucaoRenDTO);
		captacaoRemuneradaIntegracaoServico.incluirCaptacaoRemuneradaIntegracao(captacaoRemuneradaIntegracaoDTO);
		
	}
	
	/**
	 * Montar aplicacao captacao remunerada.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @return CaptacaoRemuneradaIntegracaoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private CaptacaoRemuneradaIntegracaoDTO montarAplicacaoCaptacaoRemunerada(DevolucaoRenDTO devolucaoRenDTO) throws BancoobException{
		
		CaptacaoRemuneradaIntegracaoDTO captacaoRemuneradaIntegracaoDTO = new CaptacaoRemuneradaIntegracaoDTO();
		
		captacaoRemuneradaIntegracaoDTO.setNumContaCorrente(new BigDecimal(devolucaoRenDTO.getNumContaCorrente()));
		captacaoRemuneradaIntegracaoDTO.setIdModalidadeProduto(devolucaoRenDTO.getIdModalidadeAplicacao());
		captacaoRemuneradaIntegracaoDTO.setQtdParcelas(devolucaoRenDTO.getQtdAplicacao());
		captacaoRemuneradaIntegracaoDTO.setValorAplicacao(devolucaoRenDTO.getVlrDevolucao());		
		
		return captacaoRemuneradaIntegracaoDTO;

	}	
		
	/**
	 * Montar lancamento cco.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @return LancamentoContaCorrenteIntegracaoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCorrenteIntegracaoDTO montarLancamentoCco(DevolucaoRenDTO devolucaoRenDTO) throws BancoobException{
		
		LancamentoContaCorrenteIntegracaoDTO lancDtoCco = new LancamentoContaCorrenteIntegracaoDTO();
		
		lancDtoCco.setBolConsideraLimite(false);
		lancDtoCco.setDataLote(new DateTimeDB(prodLegadoServico.obterDataAtualProduto(
				ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, devolucaoRenDTO.getIdInstituicao()).getTime()));
		lancDtoCco.setNumLoteLanc(ContaCapitalConstantes.COD_LOTE_PARC_AVISTA_CCA);		
		lancDtoCco.setDescNumDocumento(devolucaoRenDTO.getNumContaCapital().toString());
		lancDtoCco.setNumContaCorrente(devolucaoRenDTO.getNumContaCorrente());
		lancDtoCco.setValorLanc(devolucaoRenDTO.getVlrAVista());			
		lancDtoCco.setIdProduto(ContaCapitalConstantes.PRODUTO_CONTA_CORRENTE);
		lancDtoCco.setIdProdutoEstorno(null);
		lancDtoCco.setIdTipoHistoricoEstorno(null);
		lancDtoCco.setIdUsuarioResp(InformacoesUsuario.getInstance().getLogin()); 
		lancDtoCco.setIdAplicativo(1);
		lancDtoCco.setIdInstituicao(devolucaoRenDTO.getIdInstituicao());
		lancDtoCco.setBolVerificaContaAnt(true);
		lancDtoCco.setBolVerificaSaldo(false);			
		lancDtoCco.setCodOrigemLote(2);
		lancDtoCco.setDescInfComplementar(null);		
		
		ContaCapital contaCapital = contaCapitalServico.obter(devolucaoRenDTO.getIdContaCapital());		
		
		if(devolucaoRenDTO.getTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.shortValue()){			
			if(isCooperadoAtivo(contaCapital)){
				lancDtoCco.setIdTipoHistoricoLanc(ContaCapitalConstantes.COD_HIST_LANC_CCO_DEV_CAPITAL_COOP_ATIVO);			
			}else{
				lancDtoCco.setIdTipoHistoricoLanc(ContaCapitalConstantes.COD_HIST_LANC_CCO_DEV_CAPITAL_COOP_DESLIGADO);						
			}			
		}else if(devolucaoRenDTO.getTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAPTACAO_REMUNERADA.shortValue()){
			lancDtoCco.setIdTipoHistoricoLanc(ContaCapitalConstantes.COD_HIST_LANC_CCO_DEV_CAPITAL_COOP_CAPT_REM);			
		}		
						
		return lancDtoCco;	
	}					
	
	/**
	 * O método Validar incluir.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarIncluir(DevolucaoRenDTO devolucaoRenDTO) throws BancoobException {
		
		validarFechamento();		
		validarSaldoDevolucao(devolucaoRenDTO);
		validarLancamentoViaConta(devolucaoRenDTO);	
		if(devolucaoRenDTO.getTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAPTACAO_REMUNERADA.shortValue()){
			validarCaptacaoRemunerada(devolucaoRenDTO);
		}else{
			validarParcelasEmAberto(devolucaoRenDTO.getNumContaCapital());							
		}
		validarCooperadoAtivo(devolucaoRenDTO);		
		
	}		
	
	/**
	 * O método Validar captacao remunerada.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarCaptacaoRemunerada(DevolucaoRenDTO devolucaoRenDTO) throws BancoobException {	
		
		if(!devolucaoRenDTO.getVlrDevolucao().equals(devolucaoRenDTO.getVlrAVista())){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_044");							
		}
		
		Integer numMaxParcelas = Integer.valueOf(valorConfiguracaoCapitalServico.obterValorConfiguracao(ContaCapitalConstantes.PAR_MAXIMO_PARCELAS_DEVOL_CAPITAL , Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao())).getValorConfiguracao());
		
		if(devolucaoRenDTO.getQtdAplicacao() > numMaxParcelas){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_CAP_REM_MAX_APLICACAO", numMaxParcelas.toString());							
		}		
		
		if(devolucaoRenDTO.getIdMotivoDevolucao().shortValue() != ContaCapitalConstantes.COD_MOTIVO_DEVOLUCAO_RESGATE_PARCIAL_EM_FUNC_DA_IDADE_EOU_TEMPO_ASSOC_COOP.shortValue()){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_045");							
		}	
		
		if (lancamentoContaCapitalServico.pesquisarLancamentosDoDiaTipoHistContaCapital(devolucaoRenDTO.getIdContaCapital(), devolucaoRenDTO.getIdInstituicao(), ContaCapitalConstantes.COD_HISTORICO_CCA_DEVOLUCAO_VIA_CAP_REM).size() > 0){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_CAP_REM_LANC_DIA");										
		}
		
	}
	
	/**
	 * O método Validar lancamento via conta.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarLancamentoViaConta(DevolucaoRenDTO devolucaoRenDTO) throws BancoobException {
		
		if(devolucaoRenDTO.getTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.shortValue()){					
			ContaCorrenteIntegracaoDTO dto = new ContaCorrenteIntegracaoDTO();
			dto.setIdInstituicao(devolucaoRenDTO.getIdInstituicao());
			dto.setIdPessoa(devolucaoRenDTO.getIdPessoa());
			dto.setNumContaCorrente(devolucaoRenDTO.getNumContaCorrente());
			dto.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
							
			if(contaCorrenteIntegracaoServico.verificarContaCorrenteBloqueadaEncerrada(dto)){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_027");
			}			
		}			
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
		
		if(parcelamentoCCALegadoServico.verificarParcelamentoAberto(numContaCapital, ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_DEVOLUCAO) > 0 ){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_031");
		}		
		
	}
	
	/**
	 * O método Validar cooperado ativo.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarCooperadoAtivo(DevolucaoRenDTO devolucaoRenDTO) throws BancoobException {
		ContaCapital contaCapital = contaCapitalServico.obter(devolucaoRenDTO.getIdContaCapital());				
		if(isCooperadoAtivo(contaCapital) && isMotivoDevolucaoDiferenteDemissaoExclusaoEliminacao(devolucaoRenDTO) && isMotivoDevolucaoResgateParcial(devolucaoRenDTO)){
			
			ValorConfiguracaoCapital valorConfiguracaoMeses = valorConfiguracaoCapitalServico.obterValorConfiguracao(NUM_TEMPO_MINIMO_MESES_DEVOLUCAO, Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
			ValorConfiguracaoCapital valorConfiguracaoIdade = valorConfiguracaoCapitalServico.obterValorConfiguracao(NUM_IDADE_MINIMA_DEVOLUCAO, Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
			
			if (podeDevolverPorTempoMinimoMeses(valorConfiguracaoMeses, contaCapital) || podeDevolverPorIdadeMinimaPessoaFisica(valorConfiguracaoIdade, contaCapital)){ 
				return;
			}
			if (!podeDevolverPorTempoMinimoMeses(valorConfiguracaoMeses, contaCapital)) {
				throw new ContaCapitalMovimentacaoNegocioException("MSG_047");
			}
			if (!podeDevolverPorIdadeMinimaPessoaFisica(valorConfiguracaoIdade, contaCapital)) {
				throw new ContaCapitalMovimentacaoNegocioException("MSG_048");	
			}				
		}	
	}

	private boolean isMotivoDevolucaoResgateParcial(DevolucaoRenDTO devolucaoRenDTO) {
		return devolucaoRenDTO.getIdMotivoDevolucao().shortValue() == ContaCapitalConstantes.COD_MOTIVO_DEVOLUCAO_RESGATE_PARCIAL_EM_FUNC_DA_IDADE_EOU_TEMPO_ASSOC_COOP.shortValue();
	}

	private boolean isMotivoDevolucaoDiferenteDemissaoExclusaoEliminacao(DevolucaoRenDTO devolucaoRenDTO) {
		return devolucaoRenDTO.getIdMotivoDevolucao().shortValue() != ContaCapitalConstantes.COD_MOTIVO_DEVOLUCAO_DEMISSAO.shortValue() &&
				devolucaoRenDTO.getIdMotivoDevolucao().shortValue() != ContaCapitalConstantes.COD_MOTIVO_DEVOLUCAO_EXCLUSAO.shortValue() &&					
				devolucaoRenDTO.getIdMotivoDevolucao().shortValue() != ContaCapitalConstantes.COD_MOTIVO_DEVOLUCAO_ELIMINACAO.shortValue();
	}

	private boolean isCooperadoAtivo(ContaCapital contaCapital) {
		return contaCapital.getSituacaoContaCapital().getId() == ContaCapitalConstantes.COD_SITUACAO_COOPERADO_ATIVO.shortValue();
	}	
	
	private boolean podeDevolverPorTempoMinimoMeses(ValorConfiguracaoCapital valorConfiguracao, ContaCapital contaCapital) throws BancoobException {
		if (isValorConfiguracaoAtivo(valorConfiguracao)) {
			DateTime dtTempoMinimo = new DateTime(contaCapital.getDataMatricula().getTime()).plusMonths(Integer.valueOf(valorConfiguracao.getValorConfiguracao()));
			return prodLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, contaCapital.getIdInstituicao()).compareTo(dtTempoMinimo.toDate()) > 0;
		}
		return true; 
	}
	
	private boolean podeDevolverPorIdadeMinimaPessoaFisica(ValorConfiguracaoCapital valorConfiguracao, ContaCapital contaCapital) throws BancoobException {
		if (isValorConfiguracaoAtivo(valorConfiguracao) && !capesIntegracaoServico.isPessoaJuridica(contaCapital.getIdPessoa(), contaCapital.getIdInstituicao())) {
			PessoaFisicaIntegracaoDTO  pessoaFisicaIntegracaoDTO  = capesIntegracaoServico.obterPessoaFisicaInstituicao(contaCapital.getIdPessoa(), Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));													
			if(pessoaFisicaIntegracaoDTO.getNascimento() == null){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_DATA_NASCIMENTO_INVALIDA");												
			}
			DateTime dtIdadeMinima = new DateTime(pessoaFisicaIntegracaoDTO.getNascimento().getTime()).plusYears(Integer.valueOf(valorConfiguracao.getValorConfiguracao()));						
			return prodLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, contaCapital.getIdInstituicao()).compareTo(dtIdadeMinima.toDate()) > 0;
		}
		return true;
	}	
	
	private boolean isValorConfiguracaoAtivo(ValorConfiguracaoCapital valorConfiguracao) {
		return valorConfiguracao.getConfiguracaoCapital().getBolAtivo() && !valorConfiguracao.getValorConfiguracao().equals("0");
	}
	
	/**
	 * O método Validar parcelas.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @param lstParcelamentoRenDTO o valor de lst parcelamento ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarParcelas(DevolucaoRenDTO devolucaoRenDTO, List<ParcelamentoRenDTO> lstParcelamentoRenDTO) throws BancoobException {
		
		BigDecimal valorParcelaTotal = new BigDecimal(ContaCapitalConstantes.NUM_ZERO);
		Date dataVencimentoAnt = prodLegadoServico.obterDataAtualProduto(
				ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, devolucaoRenDTO.getIdInstituicao());
		Collections.sort(lstParcelamentoRenDTO);
		
		for(ParcelamentoRenDTO parcelamentoRenDTO:lstParcelamentoRenDTO){		
			
			if(parcelamentoRenDTO.getNumParcela().shortValue() == ContaCapitalConstantes.NUM_ZERO.shortValue()){
				if(parcelamentoRenDTO.getDataVencimento().compareTo(dataVencimentoAnt)<0){
					throw new ContaCapitalMovimentacaoNegocioException("MSG_049", ContaCapitalUtil.formatarDataMascara(dataVencimentoAnt,"dd/MM/yyyy"));													
				}			
			}else{
				validarDataParcela(parcelamentoRenDTO.getDataVencimento(), dataVencimentoAnt);
			}
			
			if(parcelamentoRenDTO.getNumParcela() == null){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_018");									
			}
			if(parcelamentoRenDTO.getNumParcela().shortValue() != ContaCapitalConstantes.NUM_ZERO.shortValue() && 
					parcelamentoRenDTO.getValorParcela().compareTo(new BigDecimal(ContaCapitalConstantes.NUM_ZERO)) <=0){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_019");									
			}
			if(parcelamentoRenDTO.getIdTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.shortValue()) {
				validarParcelaViaConta(parcelamentoRenDTO.getNumContaCorrente());				
			}		
			if(parcelamentoRenDTO.getNumParcela().shortValue() == ContaCapitalConstantes.NUM_ZERO.shortValue()){
				validarDiaUtil(parcelamentoRenDTO.getDataVencimento());																		
			}			
			dataVencimentoAnt = parcelamentoRenDTO.getDataVencimento();
			valorParcelaTotal = valorParcelaTotal.add(parcelamentoRenDTO.getValorParcela());			
		}
		validarValorTotalParcelas(devolucaoRenDTO.getVlrDevolucao(), valorParcelaTotal);
	}		
	
	/**
	 * O método Validar data parcela.
	 *
	 * @param dataVencimento o valor de data vencimento
	 * @param dataVencimentoAnt o valor de data vencimento ant
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarDataParcela(Date dataVencimento, Date dataVencimentoAnt) throws BancoobException {
		
		if(dataVencimento.toString().equals("")){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_017");									
		}
		if(dataVencimento.compareTo(dataVencimentoAnt)<0){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_025", ContaCapitalUtil.formatarDataMascara(dataVencimento,"dd/MM/yyyy"), ContaCapitalUtil.formatarDataMascara(dataVencimentoAnt,"dd/MM/yyyy"));													
		}
		
	}
	
	/**
	 * O método Validar dia util.
	 *
	 * @param dataVencimento o valor de data vencimento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarDiaUtil(Date dataVencimento) throws BancoobException {
		
		if(!genIntIntegracaoServico.verificarDiaUtil(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()), dataVencimento)){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_046");																	
		}
		
	}	
	
	/**
	 * O método Validar parcela via conta.
	 *
	 * @param numContaCorrente o valor de num conta corrente
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarParcelaViaConta(Long numContaCorrente) throws BancoobException {
		if(numContaCorrente == ContaCapitalConstantes.NUM_ZERO.longValue()) {
				throw new ContaCapitalMovimentacaoNegocioException("MSG_020");					
		}			
	}	
	
	/**
	 * O método Validar valor total parcelas.
	 *
	 * @param vlrDevolucao o valor de vlr devolucao
	 * @param valorParcelaTotal o valor de valor parcela total
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarValorTotalParcelas(BigDecimal vlrDevolucao, BigDecimal valorParcelaTotal) throws BancoobException {
		
		if(vlrDevolucao.compareTo(valorParcelaTotal)!=0){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_022");														
		}
		
	}	
	
	/**
	 * O método Validar saldo devolucao.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarSaldoDevolucao(DevolucaoRenDTO devolucaoRenDTO) throws BancoobException {	
		
		ContaCapital contaCapital = contaCapitalServico.obter(devolucaoRenDTO.getIdContaCapital());
		verificarContaCapitalNaoEncontrada(contaCapital);
		BigDecimal valorDevolver = BigDecimal.ZERO; 
		BigDecimal valorMinimoSubscricao = BigDecimal.ZERO;
		BigDecimal valorParcelasDevolucaoEmAberto = BigDecimal.ZERO;
		
		if(isCooperadoAtivo(contaCapital)){						
			valorMinimoSubscricao = valorCotaServico.obterValorMinimoSubscricao(contaCapital.getIdInstituicao(), contaCapital.getIdPessoa());
			valorDevolver = lancamentoContaCapitalServico.calcularValorIntegralizado(contaCapital.getId());
		}else{
			valorDevolver = lancamentoContaCapitalServico.calcularValorDevolucao(contaCapital.getId());				
			valorParcelasDevolucaoEmAberto = parcelamentoContaCapitalServico.pesquisarValorParcelasDevolucaoEmAberto(devolucaoRenDTO.getIdContaCapital());
		}
		
		BigDecimal valorTotalDisponivel = valorDevolver.subtract(valorMinimoSubscricao).subtract(valorParcelasDevolucaoEmAberto);
		
		if(valorTotalDisponivel.compareTo(devolucaoRenDTO.getVlrDevolucao()) < 0){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_DEVOL_SALDO");			
		}
	}
	
	/**
	 * O método Validar quantidade máxima de parcelas de devolucao.
	 *
	 * @param qtdParcelas 
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarQtdParcelasDevolucao(Integer qtdParcelas) throws BancoobException {	
		Integer numMaxParcelas = Integer.valueOf(valorConfiguracaoCapitalServico.obterValorConfiguracao(ContaCapitalConstantes.PAR_MAXIMO_PARCELAS_DEVOL_CAPITAL , Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao())).getValorConfiguracao());
				
		if(qtdParcelas > numMaxParcelas){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_012", numMaxParcelas.toString());			
		}
	}

}

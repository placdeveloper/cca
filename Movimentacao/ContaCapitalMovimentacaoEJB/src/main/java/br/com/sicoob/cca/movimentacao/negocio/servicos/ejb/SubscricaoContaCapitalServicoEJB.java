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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.excecao.ContaCapitalCadastroNegocioException;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.DocumentoCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.PropostaSubscricaoServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ValorConfiguracaoCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ValorCotaServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.negocio.annotation.LogCCA;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.DocumentoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.PropostaSubscricao;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoLote;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoParcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoSubscricao;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumMetodoOperacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoDocumento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoSubscricaoCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.SubscricaoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.SubscricaoContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.SubscricaoContaCapitalServicoRemote;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDaoFactory;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ParcelamentoContaCapitalDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.ContaCapitalIntegracaoNegocioException;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.ContaCorrenteIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TrabalhaLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ParcelamentoCCALegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.TrabalhaLegadoServicoLocal;

/**
 * EJB contendo servicos relacionados a SubscricaoContaCapital.
 *
 * @author Antonio.Genaro
 */
@Stateless
@Local (SubscricaoContaCapitalServicoLocal.class)
@Remote(SubscricaoContaCapitalServicoRemote.class) 
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SubscricaoContaCapitalServicoEJB extends ContaCapitalMovimentacaoServicoEJB implements SubscricaoContaCapitalServicoLocal, SubscricaoContaCapitalServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private ParcelamentoContaCapitalDao parcelamentoContaCapitalDao;

	@Resource
	private SessionContext context;
	
	private DateTimeDB dataAtualProduto = new DateTimeDB();
	
	@EJB
	private ProdutoLegadoServicoLocal prodLegadoServico;	
	
	@EJB
	private FechamentoContaCapitalServicoLocal fechamentoServico;
	
	@EJB
	private ParcelamentoCCALegadoServicoLocal parcelamentoCCALegadoServico;
	
	@EJB
	private ContaCorrenteIntegracaoServicoLocal contaCorrenteIntegracaoServico;
	
	@EJB
	private ValorCotaServicoLocal valorCotaServico;	
	
	@EJB
	private PropostaSubscricaoServicoLocal propostaServico;
	
	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;
	
	@EJB
	private TrabalhaLegadoServicoLocal trabalhaLegadoServico;
	
	@EJB
	private ValorConfiguracaoCapitalServicoLocal valorConfiguracaoCapitalServico;	
	
	@EJB
	private DocumentoCapitalServicoLocal documentoCapitalServico;
	
	@EJB
	private LancamentoContaCapitalServicoLocal lancamentoContaCapitalServico;	
	
//	Retirado o LIMITE_QTD_PARCELAS a pedido do Dr Augusto/Credimep e Vilaça 
//	/** A constante LIMITE_QTD_PARCELAS. */
//	private static final int LIMITE_QTD_PARCELAS = 100;
	
	/** A constante LIMITE_DIA_DEBITO. */
	private static final int LIMITE_DIA_DEBITO = 31;
	
	/**
	 * {@link SubscricaoContaCapitalServico#incluir(SubscricaoRenDTO, List<ParcelamentoRenDTO>)}.
	 *
	 * @param subscricaoRenDTO o valor de subscricao ren dto
	 * @param lstParcelamentoRenDTO o valor de lst parcelamento ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@LogCCA(metodo=EnumMetodoOperacaoContaCapital.MOVIMENTACAO_SUBSCRICAOCONTACAPITALSERVICO_INCLUIR)
	public void incluir(SubscricaoRenDTO subscricaoRenDTO, List<ParcelamentoRenDTO> lstParcelamentoRenDTO) throws BancoobException {
				
		try {
			
			for(ParcelamentoRenDTO parcelamentoRenDTO:lstParcelamentoRenDTO){	
				if(parcelamentoRenDTO.getNumParcela().shortValue() == ContaCapitalConstantes.NUM_ZERO.shortValue()){					
					subscricaoRenDTO.setTipoInteg(parcelamentoRenDTO.getIdTipoInteg());		
					break;
				}				
			}
			
			dataAtualProduto = new DateTimeDB(prodLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, subscricaoRenDTO.getIdInstituicao()).getTime());
			validarDocumento(subscricaoRenDTO);
			validarIncluir(subscricaoRenDTO);
			validarParcelas(subscricaoRenDTO, lstParcelamentoRenDTO);			
			
			ContaCapital contaCapital = contaCapitalServico.obter(subscricaoRenDTO.getIdContaCapital());
			
			SituacaoParcelamento situacaoParcelamento = new SituacaoParcelamento();
			TipoIntegralizacao tipoIntegralizacao = new TipoIntegralizacao();
			TipoParcelamento tipoParcelamento = new TipoParcelamento();
			tipoParcelamento.setId(ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_INTEGRAL.shortValue());
			
			Integer numParcelamento = parcelamentoCCALegadoServico.obterProximoNumParcelamento(subscricaoRenDTO.getNumContaCapital(), ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_INTEGRAL);			
						
			for(ParcelamentoRenDTO parcelamentoRenDTO:lstParcelamentoRenDTO){	
				Parcelamento parcelamento = new Parcelamento();

				parcelamento.setMatriculaFuncionario(null);
				parcelamento.setMotivoDevolucao(null);
				parcelamento.setObservacao(null);
				parcelamento.setNumContaCorrente(null);				
				parcelamento.setContaCapital(contaCapital);
				parcelamento.setDataSituacao(dataAtualProduto);
				parcelamento.setNumParcela(parcelamentoRenDTO.getNumParcela());
				parcelamento.setTipoParcelamento(tipoParcelamento);
				tipoIntegralizacao.setId(parcelamentoRenDTO.getIdTipoInteg());
				parcelamento.setTipoIntegralizacao(tipoIntegralizacao);
				parcelamento.setNumParcelamento(numParcelamento.shortValue());
				parcelamento.setIdInstituicao(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));		
				
				if(parcelamentoRenDTO.getIdTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.shortValue()) {
					parcelamento.setNumContaCorrente(parcelamentoRenDTO.getNumContaCorrente());
				}		
				if(parcelamentoRenDTO.getIdTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_FOLHA.shortValue()) {
					parcelamento.setMatriculaFuncionario(parcelamentoRenDTO.getDescNumMatriculaFunc());				
				}					
				
				if(parcelamentoRenDTO.getNumParcela().shortValue() != ContaCapitalConstantes.NUM_ZERO.shortValue()){					
					situacaoParcelamento.setId(ContaCapitalConstantes.COD_PARCELA_GERADA.shortValue());					
					parcelamento.setSituacaoParcelamento(situacaoParcelamento);			
					parcelamento.setDataVencimento(new DateTimeDB(parcelamentoRenDTO.getDataVencimento().getTime()));
					parcelamento.setValor(parcelamentoRenDTO.getValorParcela());								
					parcelamentoContaCapitalDao.incluir(parcelamento);									
				}else{									
					if(parcelamentoRenDTO.getIdTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAIXA.shortValue()){
						situacaoParcelamento.setId(ContaCapitalConstantes.COD_PARCELA_GERADA.shortValue());	
					}else if(parcelamentoRenDTO.getIdTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.shortValue()){
						situacaoParcelamento.setId(ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_CHADMIN.shortValue());	
					}else if(parcelamentoRenDTO.getIdTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.shortValue()){
						situacaoParcelamento.setId(ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_CONTA.shortValue());	
					}else if(parcelamentoRenDTO.getIdTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_FOLHA.shortValue()){
						situacaoParcelamento.setId(ContaCapitalConstantes.COD_PARCELA_GERADA.shortValue());	
					}
					
					parcelamento.setSituacaoParcelamento(situacaoParcelamento);						
					parcelamento.setDataVencimento(dataAtualProduto);
					parcelamento.setValor(parcelamentoRenDTO.getValorParcela());				
					parcelamentoContaCapitalDao.incluir(parcelamento);			
					
					if(parcelamento.getId().longValue() != ContaCapitalConstantes.NUM_ZERO.longValue()){			
						
						List<LancamentoContaCapital> listaLancamentoContaCapital = montarListaLancamentoContaCapital(subscricaoRenDTO, parcelamento);					
						for(LancamentoContaCapital item : listaLancamentoContaCapital){
							this.getLogger().info("CCA.lancamentosCCapitalLegadoServico.incluir");				
							lancamentoContaCapitalServico.incluir(item);
						}						
						
						//Gera lancamentos em conta corrente se forma de integralização via conta
						if (subscricaoRenDTO.getTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.shortValue()){
							this.getLogger().info("CCA.incluirLancamentoCCO");						
							incluirLancamentoCCO(subscricaoRenDTO);
						}							
					}	
					
					PropostaSubscricao propostaSubscricao = propostaServico.obter(subscricaoRenDTO.getIdContaCapital()); 
					if(propostaSubscricao != null && propostaSubscricao.getBolSubscricaoProposta().intValue() == ContaCapitalConstantes.ST_BOL_INATIVO.intValue()){
						propostaSubscricao.setBolSubscricaoProposta(ContaCapitalConstantes.ST_BOL_ATIVO);
						propostaServico.alterar(propostaSubscricao);						
					}
				}				
				//-----------------Parcelamento Legado
				parcelamentoCCALegadoServico.incluir(montarParcelamentoCCALegado(parcelamento));
				//-----------------				
					
			}
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
	 * Montar lista lancamento conta capital.
	 *
	 * @param subscricaoRenDTO o valor de subscricao ren dto
	 * @param parcelamento o valor de parcelamento
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<LancamentoContaCapital> montarListaLancamentoContaCapital(SubscricaoRenDTO subscricaoRenDTO, Parcelamento parcelamento) throws BancoobException{
		
		List<LancamentoContaCapital> listaLancamentoContaCapital = new ArrayList<LancamentoContaCapital>();
		
		TipoSubscricao tipoSubscricao = new TipoSubscricao(); 
		tipoSubscricao.setId(subscricaoRenDTO.getIdTipoSubscricao());
		
		TipoHistoricoCCA tipoHistoricoCCA = new TipoHistoricoCCA(); 
		tipoHistoricoCCA.setId(ContaCapitalConstantes.COD_HISTORICO_CCA_SUBSCRICAO.shortValue());
		
		TipoLote tipoLote = new TipoLote(); 
		tipoLote.setId(ContaCapitalConstantes.COD_LOTE_CCA_PARC_AVISTA.shortValue());
		
		LancamentoContaCapital lancamentoContaCapitalSubsc = new LancamentoContaCapital();
		lancamentoContaCapitalSubsc.setContaCapital(parcelamento.getContaCapital());
		lancamentoContaCapitalSubsc.setDataLancamento(dataAtualProduto);
		lancamentoContaCapitalSubsc.setIdInstituicao(subscricaoRenDTO.getIdInstituicao());
		lancamentoContaCapitalSubsc.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
		lancamentoContaCapitalSubsc.setTipoSubscricao(tipoSubscricao);
		lancamentoContaCapitalSubsc.setValorLancamento(subscricaoRenDTO.getVlrSubs());		
		
		if(subscricaoRenDTO.getBolSubscricaoProposta().intValue() == ContaCapitalConstantes.NUM_ZERO.intValue()){			
			lancamentoContaCapitalSubsc.setDescNumDocumento(ContaCapitalConstantes.COD_DESC_DOCUMENTO_INC_COOP);
		}else{			
			final int tamanho = 10;
			lancamentoContaCapitalSubsc.setDescNumDocumento((ContaCapitalConstantes.COD_DESC_DOCUMENTO_SUBSCRICAO + parcelamento.getNumParcelamento().toString() + "          ").substring(0, tamanho));			
		}
		 	
		lancamentoContaCapitalSubsc.setTipoHistoricoCCA(tipoHistoricoCCA);
		lancamentoContaCapitalSubsc.setTipoLote(tipoLote);				
		lancamentoContaCapitalSubsc.setBolProcessado(ContaCapitalConstantes.NUM_ZERO.shortValue());
		lancamentoContaCapitalSubsc.setDataHoraAtualizacao(new DateTimeDB());
		
		listaLancamentoContaCapital.add(lancamentoContaCapitalSubsc);					
		
		if(subscricaoRenDTO.getTipoInteg().shortValue() != ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAIXA.shortValue()
			&& subscricaoRenDTO.getTipoInteg().shortValue() != ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_FOLHA.shortValue()){			
			
			LancamentoContaCapital lancamentoContaCapitalInteg = new LancamentoContaCapital();		
			lancamentoContaCapitalInteg.setContaCapital(parcelamento.getContaCapital());
			lancamentoContaCapitalInteg.setDataLancamento(dataAtualProduto);
			lancamentoContaCapitalInteg.setIdInstituicao(subscricaoRenDTO.getIdInstituicao());
			lancamentoContaCapitalInteg.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
			lancamentoContaCapitalInteg.setTipoSubscricao(tipoSubscricao);
			lancamentoContaCapitalInteg.setTipoLote(tipoLote);				
			lancamentoContaCapitalInteg.setBolProcessado(ContaCapitalConstantes.NUM_ZERO.shortValue());
			lancamentoContaCapitalInteg.setDataHoraAtualizacao(new DateTimeDB());
			lancamentoContaCapitalInteg.setValorLancamento(parcelamento.getValor());			
			lancamentoContaCapitalInteg.setDescNumDocumento(parcelamento.getContaCapital().getNumContaCapital().toString());		
					
			TipoHistoricoCCA tipoHistoricoCCAInteg = new TipoHistoricoCCA(); 		
			
			if(subscricaoRenDTO.getTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAIXA.shortValue()){
				tipoHistoricoCCAInteg.setId(ContaCapitalConstantes.COD_HISTORICO_CCA_INTEG_CAIXA.shortValue());
			}else if(subscricaoRenDTO.getTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.shortValue()){
				tipoHistoricoCCAInteg.setId(ContaCapitalConstantes.COD_HISTORICO_CCA_INTEG_BANCO.shortValue());
			}else if(subscricaoRenDTO.getTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.shortValue()){
				tipoHistoricoCCAInteg.setId(ContaCapitalConstantes.COD_HISTORICO_CCA_INTEG_CONTA.shortValue());
			}else if(subscricaoRenDTO.getTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_FOLHA.shortValue()){
				tipoHistoricoCCAInteg.setId(ContaCapitalConstantes.COD_HISTORICO_CCA_INTEG_FOLHA.shortValue());
			}
			lancamentoContaCapitalInteg.setTipoHistoricoCCA(tipoHistoricoCCAInteg);			
			
			listaLancamentoContaCapital.add(lancamentoContaCapitalInteg);	
			
		}
		
		return listaLancamentoContaCapital;		
	}
	

	/**
	 * O método Validar parcelas.
	 *
	 * @param subscricaoRenDTO o valor de subscricao ren dto
	 * @param lstParcelamentoRenDTO o valor de lst parcelamento ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarParcelas(SubscricaoRenDTO subscricaoRenDTO, List<ParcelamentoRenDTO> lstParcelamentoRenDTO) throws BancoobException {
		
		BigDecimal valorParcelaTotal = new BigDecimal(ContaCapitalConstantes.NUM_ZERO);
		Date dataVencimentoAnt = dataAtualProduto;
		Collections.sort(lstParcelamentoRenDTO);
		
		for(ParcelamentoRenDTO parcelamentoRenDTO:lstParcelamentoRenDTO){		
			if(parcelamentoRenDTO.getDataVencimento().toString().equals("")){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_017");									
			}
			if(parcelamentoRenDTO.getNumParcela() == null){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_018");									
			}
			if(parcelamentoRenDTO.getValorParcela().compareTo(new BigDecimal(ContaCapitalConstantes.NUM_ZERO)) <=0){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_019");									
			}
			if(parcelamentoRenDTO.getIdTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.shortValue()
				&& parcelamentoRenDTO.getNumContaCorrente().longValue() == ContaCapitalConstantes.NUM_ZERO.longValue()) {
				throw new ContaCapitalMovimentacaoNegocioException("MSG_020");					
			}		
			if(parcelamentoRenDTO.getIdTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_FOLHA.shortValue() 
				&& parcelamentoRenDTO.getDescNumMatriculaFunc().equals("")) {
				throw new ContaCapitalMovimentacaoNegocioException("MSG_021");					
			}		
			if(parcelamentoRenDTO.getNumParcela().shortValue() == ContaCapitalConstantes.NUM_ZERO.shortValue()
				&& parcelamentoRenDTO.getDataVencimento().compareTo(dataAtualProduto)!=0){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_024");													
			}
			if(parcelamentoRenDTO.getDataVencimento().compareTo(dataVencimentoAnt)<0){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_025", ContaCapitalUtil.formatarDataMascara(parcelamentoRenDTO.getDataVencimento(),"dd/MM/yyyy"), ContaCapitalUtil.formatarDataMascara(dataVencimentoAnt,"dd/MM/yyyy"));													
			}
			
			dataVencimentoAnt = parcelamentoRenDTO.getDataVencimento();
			valorParcelaTotal = valorParcelaTotal.add(parcelamentoRenDTO.getValorParcela());			
		}
		
		if(subscricaoRenDTO.getVlrSubs().compareTo(valorParcelaTotal)!=0){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_022");														
		}
	}	
	
	/**
	 * O método Validar incluir.
	 *
	 * @param subscricaoRenDTO o valor de subscricao ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarIncluir(SubscricaoRenDTO subscricaoRenDTO) throws BancoobException {
		
		if(fechamentoServico.isFechamentoIniciado(Integer.valueOf(InformacoesUsuario.getInstance().getCooperativa()))) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_002");
		}				
		
		ContaCapital contaCapital = contaCapitalServico.obter(subscricaoRenDTO.getIdContaCapital());
		verificarContaCapitalNaoEncontrada(contaCapital);
		
		if(contaCapital.getSituacaoContaCapital().getId().shortValue() != EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo().shortValue()
			|| contaCapital.getSituacaoCadastroProposta().getId().intValue() != EnumSituacaoCadastroProposta.COD_APROVADO.getCodigo().intValue()) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_034");			
		}		
		
		PropostaSubscricao propostaSubscricao = propostaServico.obter(subscricaoRenDTO.getIdContaCapital()); 
		if(subscricaoRenDTO.getIdTipoSubscricao().shortValue() == EnumTipoSubscricaoCapital.COD_TIPO_SUBSCRICAO_CCA_INGRESSO.getCodigo().shortValue() && propostaSubscricao.getBolSubscricaoProposta().intValue() == ContaCapitalConstantes.ST_BOL_ATIVO.intValue()){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_035");			
		}
		
		if(subscricaoRenDTO.getVlrSubs().intValue() <= 0) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_006");
		}		
		if(subscricaoRenDTO.getVlrInteg().compareTo(new BigDecimal(ContaCapitalConstantes.NUM_ZERO)) <=0) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_007");
		}		
		if(subscricaoRenDTO.getVlrSubs().compareTo(subscricaoRenDTO.getVlrInteg())!=0) {
			//Retirado o LIMITE_QTD_PARCELAS a pedido do Dr Augusto/Credimep e Vilaça 
			//if(subscricaoRenDTO.getQtdParcelas().intValue() <= 0 || subscricaoRenDTO.getQtdParcelas().intValue() > LIMITE_QTD_PARCELAS){
			if(subscricaoRenDTO.getQtdParcelas().intValue() <= 0){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_008");				
			}					
		}		
		if(subscricaoRenDTO.getVlrSubs().compareTo(subscricaoRenDTO.getVlrInteg())<0) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_009");				
		}		
		if(subscricaoRenDTO.getDiaDebito() <= 0) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_010");				
		}		
		if(subscricaoRenDTO.getDiaDebito() > LIMITE_DIA_DEBITO) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_011");				
		}
		
		
		BigDecimal valorMinimoSubscricao = valorCotaServico.obterValorMinimoSubscricao(contaCapital.getIdInstituicao(), contaCapital.getIdPessoa());
		BigDecimal valorMinimoIntegralizacao = valorCotaServico.obterValorMinimoIntegralizacao(contaCapital.getIdInstituicao());
		BigDecimal valorCota = valorCotaServico.obterValorCota(contaCapital.getIdInstituicao());
		Integer numMaxParcelas = valorCotaServico.obterQtdMaxParcela(contaCapital.getIdInstituicao());
		
		if(subscricaoRenDTO.getQtdParcelas() > numMaxParcelas) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_012", numMaxParcelas);				
		}
		
		if(subscricaoRenDTO.getIdTipoSubscricao() == 1){				
			if(subscricaoRenDTO.getVlrSubs().compareTo(valorMinimoSubscricao) < 0){				
				throw new ContaCapitalMovimentacaoNegocioException("MSG_013", valorMinimoSubscricao);				
			}
			if(subscricaoRenDTO.getVlrInteg().compareTo(valorMinimoIntegralizacao) < 0){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_014", valorMinimoIntegralizacao);				
			}							
		}else{
			
			if(subscricaoRenDTO.getVlrSubs().compareTo(valorCota) < 0){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_015", valorMinimoSubscricao);				
			}								
		}
				
		if(subscricaoRenDTO.getTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.shortValue()){					
			ContaCorrenteIntegracaoDTO dto = new ContaCorrenteIntegracaoDTO();
			dto.setIdInstituicao(subscricaoRenDTO.getIdInstituicao());
			dto.setIdPessoa(subscricaoRenDTO.getIdPessoa());
			dto.setNumContaCorrente(subscricaoRenDTO.getNumContaCorrente());
			dto.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
							
			if(!contaCorrenteIntegracaoServico.verificarContaCorrentePorIdPessoa(dto)){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_026");			
			}else if(!contaCorrenteIntegracaoServico.verificarContaCorrenteAtiva(dto)){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_027");
			}			
		}		
		
		if(subscricaoRenDTO.getTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_FOLHA.shortValue()){
			
			if(trabalhaLegadoServico.obterDadosTrabalha(subscricaoRenDTO.getIdPessoaLegado()).isEmpty()){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_028");
			}
			 
			if(trabalhaLegadoServico.verificaSeDebIndFolhaCliente(null, subscricaoRenDTO.getIdPessoaLegado())){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_029");
			}	
								
			TrabalhaLegadoDTO trabalhaLegadoDTO = trabalhaLegadoServico.obterDadosTrabalhaPorMatricula(subscricaoRenDTO.getDescNumMatriculaFunc());						
			if(trabalhaLegadoServico.verificarSePrepRemessa(trabalhaLegadoDTO.getUIDTrabalha(), dataAtualProduto)){
				throw new ContaCapitalMovimentacaoNegocioException("MSG_030");								 					 					 
			}							
		}
		
		if(subscricaoRenDTO.getVlrSubs().compareTo(valorCota) < 0){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_032", valorCota);				
		}								
		
		if(subscricaoRenDTO.getVlrSubs().compareTo(subscricaoRenDTO.getVlrInteg()) < 0 || subscricaoRenDTO.getVlrInteg().compareTo(new BigDecimal(ContaCapitalConstantes.NUM_ZERO)) < 0){			
			throw new ContaCapitalMovimentacaoNegocioException("MSG_033");				
		}								
		
	}	
	
	/**
	 * O método Validar documento.
	 *
	 * @param subscricaoRenDTO o valor de subscricao ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarDocumento(SubscricaoRenDTO subscricaoRenDTO) throws BancoobException {

		if(valorConfiguracaoCapitalServico.obterValorConfiguracao(ContaCapitalConstantes.PARAMETRO_SUBSCRICAO_SEM_DOCUMENTO, Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao())).getValorConfiguracao().equals(ContaCapitalConstantes.ST_BOL_INATIVO.toString())){
			
			ContaCapital contaCapital = new ContaCapital();
			contaCapital.setId(subscricaoRenDTO.getIdContaCapital());
			DocumentoCapital documentoCapital = new DocumentoCapital();
			documentoCapital.setContaCapital(contaCapital);
			
			Boolean existeDoc = Boolean.FALSE;
			ConsultaDto<DocumentoCapital> consultaDto = new ConsultaDto<DocumentoCapital>();
			consultaDto.setFiltro(documentoCapital);
			List<DocumentoCapital> listaDocumento = documentoCapitalServico.listar(consultaDto);
			for (DocumentoCapital doc : listaDocumento) {				
				if (EnumTipoDocumento.FICHA_PROPOSTA_DE_MATRICULA.getCodigo().equals(doc.getTipoDocumento().getId())){
					existeDoc = Boolean.TRUE;
					break;
				}
			}
			
			if (!existeDoc) {
				throw new ContaCapitalMovimentacaoNegocioException("MSG_016");
			}

		}			
		
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
		entidadePK.setCodTipoParcelamento(ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_INTEGRAL);								
		entidadePK.setNumParcelamento(parcelamento.getNumParcelamento().intValue());
		entidadePK.setNumParcela(parcelamento.getNumParcela().intValue());		
		entidade.setParcelamentoCCALegadoPK(entidadePK);
		
		entidade.setCodModoLanc(parcelamento.getTipoIntegralizacao().getId().intValue());
		entidade.setCodMotivoDevolucao(null);
		entidade.setDataEnvioCob(null);
		entidade.setDataSituacaoParcela(dataAtualProduto);	
		entidade.setDataVencParcela(parcelamento.getDataVencimento());
		entidade.setDescObservacao(null);
		entidade.setValorParcela(parcelamento.getValor());
		entidade.setCodSituacaoParcela(parcelamento.getSituacaoParcelamento().getId().intValue());	
		
		if(parcelamento.getTipoIntegralizacao().getId().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.shortValue()) {
			entidade.setNumContaCorrente(parcelamento.getNumContaCorrente());	
		}		
		if(parcelamento.getTipoIntegralizacao().getId().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_FOLHA.shortValue()) {
			if(!parcelamento.getMatriculaFuncionario().equals("")){			
				TrabalhaLegadoDTO trabalhaLegadoDTO = trabalhaLegadoServico.obterDadosTrabalhaPorMatricula(parcelamento.getMatriculaFuncionario());			
				entidade.setuIDTrabalha(trabalhaLegadoDTO.getUIDTrabalha());							
			}
		}					
		entidade.setIdParcelamentoContaCapital(parcelamento.getId());
		return entidade;
	}	
	
	/**
	 * O método Incluir lancamento cco.
	 *
	 * @param subscricaoRenDTO o valor de subscricao ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void incluirLancamentoCCO(SubscricaoRenDTO subscricaoRenDTO) throws BancoobException {
		LancamentoContaCorrenteIntegracaoDTO lancDtoCco = montarLancamentoCco(subscricaoRenDTO);
		LancamentoContaCorrenteIntegracaoRetDTO lancDtoCcoRet = contaCorrenteIntegracaoServico.gravarLancamentosIntegracao(lancDtoCco);

		if (lancDtoCcoRet.getCodRetorno() == 0){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_004", lancDtoCcoRet.getMensagem());
		}					
	}
	
	/**
	 * Montar lancamento cco.
	 *
	 * @param subscricaoRenDTO o valor de subscricao ren dto
	 * @return LancamentoContaCorrenteIntegracaoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCorrenteIntegracaoDTO montarLancamentoCco(SubscricaoRenDTO subscricaoRenDTO) throws BancoobException{
		LancamentoContaCorrenteIntegracaoDTO lancDtoCco = new LancamentoContaCorrenteIntegracaoDTO();
		
		ValorConfiguracaoCapital valorConfiguracao = valorConfiguracaoCapitalServico.obterValorConfiguracao(
				ContaCapitalConstantes.PAR_UTILIZA_LIMITE_CHQ_ESPECIAL, 
				Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		
		String paramLimite = (valorConfiguracao == null) ? null : valorConfiguracao.getValorConfiguracao();
		if ("1".equals(paramLimite)) {
			lancDtoCco.setBolConsideraLimite(true);
		} else {
			lancDtoCco.setBolConsideraLimite(false);
		}
		
		lancDtoCco.setDataLote(dataAtualProduto);
		lancDtoCco.setNumLoteLanc(ContaCapitalConstantes.COD_LOTE_PARC_AVISTA_CCA);		
		lancDtoCco.setDescNumDocumento(subscricaoRenDTO.getNumContaCapital().toString());
		lancDtoCco.setNumContaCorrente(subscricaoRenDTO.getNumContaCorrente());
		lancDtoCco.setValorLanc(subscricaoRenDTO.getVlrInteg());			
		lancDtoCco.setIdProduto(ContaCapitalConstantes.PRODUTO_CONTA_CORRENTE);
		lancDtoCco.setIdTipoHistoricoLanc(ContaCapitalConstantes.COD_HIST_LANC_CCO);
		lancDtoCco.setIdProdutoEstorno(null);
		lancDtoCco.setIdTipoHistoricoEstorno(null);
		lancDtoCco.setIdUsuarioResp(InformacoesUsuario.getInstance().getLogin()); 
		lancDtoCco.setIdAplicativo(1);
		lancDtoCco.setIdInstituicao(subscricaoRenDTO.getIdInstituicao());
		lancDtoCco.setBolVerificaContaAnt(true);
		lancDtoCco.setBolVerificaSaldo(false);			
		lancDtoCco.setCodOrigemLote(2);
		lancDtoCco.setDescInfComplementar(null);		
				
		return lancDtoCco;	
	}	
	
}

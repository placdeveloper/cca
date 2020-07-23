package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.negocio.servicos.OperacaoContaCapitalServico;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.MetodoOperacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.OperacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.ResultadoOperacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoLote;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoParcelamento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumMetodoOperacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumResultadoOperacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoLote;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.FechBaixarParcViaCCOServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.FechBaixarParcViaCCOServicoRemote;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoLoteDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoLoteRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.ContaCorrenteIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosFechBaixarParcelasLegadoCCODTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.LancamentosCCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.CapaLoteCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.LancamentosCCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ParcelamentoCCALegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.persistencia.ContaCapitalIntegracaoLegadoDataSource;


@Stateless
@Local(FechBaixarParcViaCCOServicoLocal.class)
@Remote(FechBaixarParcViaCCOServicoRemote.class)
public class FechBaixarParcViaCCOServicoEJB extends ContaCapitalMovimentacaoServicoEJB implements FechBaixarParcViaCCOServicoLocal, FechBaixarParcViaCCOServicoRemote{

	@EJB
	private ParcelamentoCCALegadoServicoLocal parcelamentoCCALegadoServico;
	@EJB
	private ProdutoLegadoServicoLocal produtoLegadoServicoLocal;		
	@EJB
	private ContaCorrenteIntegracaoServicoLocal contaCorrenteIntegracaoServicoLocal;
	@EJB
	private LancamentosCCapitalLegadoServicoLocal lancamentosCCapitalLegadoServico;	
	@EJB
	private CapaLoteCapitalLegadoServicoLocal capaLoteCapitalLegadoServico;
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;		
	@EJB
	private LancamentoContaCapitalServicoLocal lancamentoContaCapitalServico;
	@EJB
	private ParcelamentoContaCapitalServicoLocal parcelamentoContaCapitalServico;
	@EJB
	private FechamentoContaCapitalServicoLocal fechamentoContaCapitalServico;
	@EJB
	private OperacaoContaCapitalServico operacaoContaCapitalServico;
	
	/** A constante COD_LOTE_CCO_CAPITAL. */
	private static final Integer COD_LOTE_CCO_CAPITAL = 9101;	
	/** A constante SEPARADOR_PIPE. */
	private static final String SEPARADOR = ",";
	/** A constante TEXTO_CCA. */
	private static final String TEXTO_CCA = "VIACCO";
	/** A constante POSICAO_PARCELAMENTO. */
	private static final Integer POSICAO_PARCELAMENTO = 2;
	/** A constante POSICAO_PARCELA. */
	private static final Integer POSICAO_PARCELA = 3;
	/** A constante POSICAO_TIPO_PARCELAMENTO. */
	private static final Integer POSICAO_TIPO_PARCELAMENTO = 4;
	/** A constante TIPO_LISTA_SUCESSO. */
	private static final Integer TIPO_LISTA_SUCESSO = 1;
	/** A constante TIPO_LISTA_ERRO. */
	private static final Integer TIPO_LISTA_ERRO = 2;
	
	/**
	 * Baixa as parcelas em aberto via cco.
	 *
	 * @param numCooperativa
	 * @return 
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void rodarSQL(Integer numCoop) throws BancoobException {		
		ContaCapitalIntegracaoLegadoDataSource.definirNumeroCooperativa(numCoop, false);		
		String idUsuario = fechamentoContaCapitalServico.buscarIdUsuarioFechamento(numCoop);		
		Integer idInstituicao = instituicaoIntegracaoServico.obterIdInstituicao(numCoop);		
		DateTimeDB dataAtualProd = new DateTimeDB(produtoLegadoServicoLocal.obterDatasProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, numCoop).getDataAtualProd().getTime());		
		List<DadosFechBaixarParcelasLegadoCCODTO> listParcDTO = parcelamentoCCALegadoServico.pesquisarFechParcelasEmAbertoViaCCO(dataAtualProd);				
		List<LancamentoContaCorrenteIntegracaoLoteRetDTO> listCCORetDTO = contaCorrenteIntegracaoServicoLocal.gravarLancamentosIntegracaoLote(montarListLancamentoContaCorrenteLoteDTO(listParcDTO, dataAtualProd), numCoop);		
		List<DadosFechBaixarParcelasLegadoCCODTO> listParcSucessoDTO = listarParcelasRetornoCCO(TIPO_LISTA_SUCESSO, listParcDTO, listCCORetDTO);				
		if(!listParcSucessoDTO.isEmpty()) {
			incluirLancamentos(listParcSucessoDTO, dataAtualProd, numCoop, idUsuario);
			alterarParcelamentos(listParcSucessoDTO, dataAtualProd);					
		}
		operacaoContaCapitalServico.incluirOperacaoContaCapitalLote(montarListaOperacao(listarParcelasRetornoCCO(TIPO_LISTA_ERRO, listParcDTO, listCCORetDTO), dataAtualProd, idInstituicao, idUsuario));		
	}
	
	/**
	 * Montar lista de retorno CCO dto.
	 *
	 * @param tipoLista 
	 * @param listParcDTO 
	 * @param listCCORetDTO 
	 * @return lista DadosFechBaixarParcelasLegadoCCODTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	
	private List<DadosFechBaixarParcelasLegadoCCODTO> listarParcelasRetornoCCO(Integer tipoLista, List<DadosFechBaixarParcelasLegadoCCODTO> listParcDTO, List<LancamentoContaCorrenteIntegracaoLoteRetDTO> listCCORetDTO) throws BancoobException {
		List<DadosFechBaixarParcelasLegadoCCODTO> listParcRetornoDTO = new ArrayList<DadosFechBaixarParcelasLegadoCCODTO>();	

		for(DadosFechBaixarParcelasLegadoCCODTO dadosParcDTO: listParcDTO) {			
			for(LancamentoContaCorrenteIntegracaoLoteRetDTO dadosCCODTO: listCCORetDTO) {
				if(montaChaveIdentificador(dadosParcDTO).equals(dadosCCODTO.getHashIdentificacaoLancamento())) {
					dadosParcDTO.setNumOcorrencia(dadosCCODTO.getCodRetorno());
					dadosParcDTO.setDescOcorrencia(dadosCCODTO.getMensagem());					
					if(tipoLista.equals(TIPO_LISTA_SUCESSO)) {
						if(!ContaCapitalConstantes.NUM_ZERO.equals(dadosCCODTO.getCodRetorno())) {
							listParcRetornoDTO.add(dadosParcDTO);							
						}
					}else {
						if(ContaCapitalConstantes.NUM_ZERO.equals(dadosCCODTO.getCodRetorno())) {
							listParcRetornoDTO.add(dadosParcDTO);							
						}						
					}
					break;
				}
			}
		}
		
		return listParcRetornoDTO;
	}
	
	
	/**
	 * Montar lista de lancamento CCO lote dto.
	 *
	 * @param lstDadosDTO 
	 * @param dataAtualProd 
	 * @return lista LancamentoContaCorrenteIntegracaoLoteDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<LancamentoContaCorrenteIntegracaoLoteDTO> montarListLancamentoContaCorrenteLoteDTO(List<DadosFechBaixarParcelasLegadoCCODTO> lstDadosDTO, DateTimeDB dataAtualProd) throws BancoobException{
		List<LancamentoContaCorrenteIntegracaoLoteDTO> listLancamentoContaCorrenteIntegracaoLoteDTO = new ArrayList<LancamentoContaCorrenteIntegracaoLoteDTO>();				
		
		for (DadosFechBaixarParcelasLegadoCCODTO dadosDTO : lstDadosDTO) {
			listLancamentoContaCorrenteIntegracaoLoteDTO.add(montarLancamentoContaCorrenteLoteDTO(dadosDTO, dataAtualProd));
		}		
		return listLancamentoContaCorrenteIntegracaoLoteDTO;				
	}	
	
	/**
	 * Montar lancamento CCO lote dto.
	 *
	 * @param DadosFechBaixarParcelasLegadoCCODTO 
	 * @param dataAtualProd 
	 * @return LancamentoContaCorrenteIntegracaoLoteDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCorrenteIntegracaoLoteDTO montarLancamentoContaCorrenteLoteDTO(DadosFechBaixarParcelasLegadoCCODTO dadosDTO, DateTimeDB dataAtualProd) throws BancoobException{
		LancamentoContaCorrenteIntegracaoLoteDTO lancamentoContaCorrenteIntegracaoLoteDTO = new LancamentoContaCorrenteIntegracaoLoteDTO();				

		lancamentoContaCorrenteIntegracaoLoteDTO.setDataLancamento(new Date(dataAtualProd.getTime()));
		lancamentoContaCorrenteIntegracaoLoteDTO.setDescNumDocumento(dadosDTO.getDescNumDocumento());
		lancamentoContaCorrenteIntegracaoLoteDTO.setIdTipoHistoricoLanc(dadosDTO.getTipoHistoricoCCO());
		lancamentoContaCorrenteIntegracaoLoteDTO.setNumContaCorrente(dadosDTO.getNumContaCorrente());
		lancamentoContaCorrenteIntegracaoLoteDTO.setNumLoteLanc(COD_LOTE_CCO_CAPITAL);
		lancamentoContaCorrenteIntegracaoLoteDTO.setBolVerificaSaldo(Boolean.TRUE);
		lancamentoContaCorrenteIntegracaoLoteDTO.setValorLanc(dadosDTO.getValorParcela());
		lancamentoContaCorrenteIntegracaoLoteDTO.setHashIdentificacaoLancamento(montaChaveIdentificador(dadosDTO));
		
		return lancamentoContaCorrenteIntegracaoLoteDTO;				
	}	
	
	/**
	 * Metodo para montar a chave identificadora do servico.
	 * 
	 * @param DadosFechBaixarParcelasLegadoCCODTO
	 * @return String.
	 * @throws BancoobException 
	 */
	private String montaChaveIdentificador(DadosFechBaixarParcelasLegadoCCODTO dadosDTO) throws BancoobException {
		return TEXTO_CCA + 
			   SEPARADOR + dadosDTO.getNumMatricula() +
			   SEPARADOR + dadosDTO.getNumParcelamento() +
			   SEPARADOR + dadosDTO.getNumParcela() +
			   SEPARADOR + dadosDTO.getCodTipoParcelamento();
	}	
	
	/**
	 * Incluir os lançamentos
	 * 
	 * @param lista DadosFechBaixarParcelasLegadoCCODTO
	 * @param DateTimeDB
	 * @return 
	 * @throws BancoobException 
	 */
	private void incluirLancamentos(List<DadosFechBaixarParcelasLegadoCCODTO> listParcDTO, DateTimeDB dataAtualProd, Integer numCoop, String idUsuario) throws BancoobException{		
		List<LancamentosCCapitalLegado> lancamentosLegado = new ArrayList<LancamentosCCapitalLegado>();		
		
		CapaLoteCapitalLegado capaLoteCapitalLegado = montarCapaLoteCapitalLegado(dataAtualProd, EnumTipoLote.COD_LOTE_CCA_PARC_APRAZO.getCodigo());
		if (capaLoteCapitalLegado.getBolNovo()){
			capaLoteCapitalLegadoServico.incluir(capaLoteCapitalLegado);
		}
		
		lancamentosLegado = montarListaLancamentosLegado(listParcDTO, dataAtualProd, numCoop, idUsuario, capaLoteCapitalLegado);			
		lancamentosCCapitalLegadoServico.incluirEmLote(lancamentosLegado);		
		capaLoteCapitalLegadoServico.atualizarCapaLote(numCoop, capaLoteCapitalLegado.getCapaLoteCapitalLegadoPK().getDataLote(), capaLoteCapitalLegado.getCapaLoteCapitalLegadoPK().getNumLoteLanc());		
	}

	/**
	 * monta lista de lançamentos
	 * 
	 * @param lista DadosFechBaixarParcelasLegadoCCODTO
	 * @param DateTimeDB
	 * @return lista LancamentosCCapitalLegado
	 * @throws BancoobException 
	 */
	private List<LancamentosCCapitalLegado> montarListaLancamentosLegado(List<DadosFechBaixarParcelasLegadoCCODTO> listParcDTO, DateTimeDB dataAtualProd, Integer numCoop, String idUsuario, CapaLoteCapitalLegado capaLoteCapitalLegado) throws BancoobException {
		List<LancamentosCCapitalLegado> lancamentosLegado = new ArrayList<LancamentosCCapitalLegado>();
		for (DadosFechBaixarParcelasLegadoCCODTO dto : listParcDTO) {
			lancamentosLegado.add(montarLancamentosCCapitalLegado(dto, capaLoteCapitalLegado, dataAtualProd, numCoop, idUsuario));				
		}
		return lancamentosLegado;
	}	
	
	/**
	 * monta para lote legado cca
	 * 
	 * @param DateTimeDB
	 * @param Integer
	 * @return CapaLoteCapitalLegado
	 * @throws BancoobException 
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
			entidade.setiDTipoHistoricoLanc(ContaCapitalConstantes.COD_HISTORICO_CCA_INTEG_CONTA);
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
	 * @param DadosFechBaixarParcelasLegadoCCODTO
	 * @param capaLoteCapitalLegado
	 * @param DateTimeDB 
	 * @return LancamentosCCapitalLegado
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentosCCapitalLegado montarLancamentosCCapitalLegado(DadosFechBaixarParcelasLegadoCCODTO dto, CapaLoteCapitalLegado capaLoteCapitalLegado, 
			DateTimeDB dataLote, Integer numCoop, String idUsuario) throws BancoobException{
		 
		LancamentosCCapitalLegado entidade = new LancamentosCCapitalLegado();
		LancamentosCCapitalLegadoPK entidadePK = new LancamentosCCapitalLegadoPK();			
		entidadePK.setCapaLoteCapitalLegado(capaLoteCapitalLegado);
		
		ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();	
		contaCapitalLegado.setNumMatricula(dto.getNumMatricula());		
		entidade.setContaCapitalLegado(contaCapitalLegado);		
		entidade.setDescNumDocumento(montarDescNumDocumentoLanc(dto.getNumParcelamento(), dto.getNumParcela()));			
		entidade.setiDTipoHistoricoLanc(dto.getTipoHistoricoCCO().equals(ContaCapitalConstantes.COD_HIST_LANC_CCO)?ContaCapitalConstantes.COD_HISTORICO_CCA_INTEG_CONTA:ContaCapitalConstantes.COD_HISTORICO_CCA_DEVOLUCAO_VIA_CC);
		entidade.setLancamentosCCapitalLegadoPK(entidadePK);
		entidade.setBolAtualizado(Boolean.FALSE);
		entidade.setDataHoraInclusao(new DateTimeDB());
		entidade.setiDProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
		entidade.setValorLanc(dto.getValorParcela());	
		entidade.setiDUsuarioResp(idUsuario); 
		entidade.setIdTipoSubscricao(null);
		entidade.setIdLancamentoContaCapital(ContaCapitalConstantes.IDLANCAMENTOCONTACAPITAL_NAOREPLICA); //Não Gerar Replicacao
		entidade.setDescOperacaoExterna(montaChaveIdentificador(dto));
		return entidade;
	}
	
	/**
	 * Montar campo desNumDocumento Lancamentos
	 *
	 * @param Integer 
	 * @param Integer
	 * @return String
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private String montarDescNumDocumentoLanc(Integer numParcelamento, Integer numParcela) throws BancoobException {
		return StringUtils.leftPad(numParcelamento.toString(), 4, "0")+"-"+StringUtils.leftPad(numParcela.toString(), 4, "0");				
	}
	
	/**
	 * Altera parcelamentos Legado
	 *
	 * @param lista DadosFechBaixarParcelasLegadoCCODTO 
	 * @param DateTimeDB
	 * @return 
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void alterarParcelamentos(List<DadosFechBaixarParcelasLegadoCCODTO> listParcDTO, DateTimeDB dataAtualProd) throws BancoobException{		
		List<ParcelamentoCCALegado> listParcelamentoCCALegado = new ArrayList<ParcelamentoCCALegado>();		
		listParcelamentoCCALegado = montarListaParcelamentosLegado(listParcDTO, dataAtualProd);		
		parcelamentoCCALegadoServico.alterarEmLote(listParcelamentoCCALegado);
	}
	
	/**
	 * monta lista de parcelamentos Legado
	 *
	 * @param lista DadosFechBaixarParcelasLegadoCCODTO 
	 * @param DateTimeDB
	 * @return lista ParcelamentoCCALegado
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<ParcelamentoCCALegado> montarListaParcelamentosLegado(List<DadosFechBaixarParcelasLegadoCCODTO> listParcDTO, DateTimeDB dataAtualProd) throws BancoobException {
		List<ParcelamentoCCALegado> listParcelamentoCCALegado = new ArrayList<ParcelamentoCCALegado>();
		
		for (DadosFechBaixarParcelasLegadoCCODTO dto : listParcDTO) {
			listParcelamentoCCALegado.add(montarParcelamentoCCapitalLegado(dto, dataAtualProd));
		}
		return listParcelamentoCCALegado;
	}		
	
	/**
	 * monta parcelamentos Legado
	 *
	 * @param DadosFechBaixarParcelasLegadoCCODTO 
	 * @param DateTimeDB
	 * @return ParcelamentoCCALegado
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private ParcelamentoCCALegado montarParcelamentoCCapitalLegado(DadosFechBaixarParcelasLegadoCCODTO dto, DateTimeDB dataLote) throws BancoobException{
		 
		ParcelamentoCCALegado entidade = new ParcelamentoCCALegado();
		ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();				
		contaCapitalLegado.setNumMatricula(dto.getNumMatricula());
		
		ParcelamentoCCALegadoPK parcelamentoCCALegadoPK = new ParcelamentoCCALegadoPK();								
		parcelamentoCCALegadoPK.setContaCapitalLegado(contaCapitalLegado);
		parcelamentoCCALegadoPK.setNumParcela(dto.getNumParcela().intValue());
		parcelamentoCCALegadoPK.setNumParcelamento(dto.getNumParcelamento().intValue());
		parcelamentoCCALegadoPK.setCodTipoParcelamento(dto.getCodTipoParcelamento().intValue());
		
		entidade.setParcelamentoCCALegadoPK(parcelamentoCCALegadoPK);
		entidade.setCodSituacaoParcela(ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_CONTA);
		entidade.setDataSituacaoParcela(dataLote);
		entidade.setIdParcelamentoContaCapital(ContaCapitalConstantes.IDLANCAMENTOCONTACAPITAL_NAOREPLICA); //Não Gerar Replicacao

		return entidade;
	}
	
	/**
	 * Baixa as parcelas em aberto via cco DB2.
	 *
	 * @param numCooperativa
	 * @return 
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void rodar(Integer numCoop) throws BancoobException {		
		ContaCapitalIntegracaoLegadoDataSource.definirNumeroCooperativa(numCoop, false);		
		Integer idInstituicao = instituicaoIntegracaoServico.obterIdInstituicao(numCoop);		
		DateTimeDB dataAtualProd = new DateTimeDB(produtoLegadoServicoLocal.obterDatasProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, numCoop).getDataAtualProd().getTime());		
		List<LancamentosCCapitalLegadoDTO> listLanc = lancamentosCCapitalLegadoServico.listarLancViaCCO(dataAtualProd);	
		if(!listLanc.isEmpty()) {
			lancamentoContaCapitalServico.incluirEmLote(montarListaLancamentosDB2(listLanc, dataAtualProd, idInstituicao));		
			parcelamentoContaCapitalServico.alterarEmLote(montarListaParcelamentosDB2(listLanc, dataAtualProd, idInstituicao));			
		}
	}		
	
	/**
	 * monta lista de Parcelamentos
	 *
	 * @param lista LancamentosCCapitalLegadoDTO 
	 * @param DateTimeDB
	 * @param Integer
	 * @return lista Parcelamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Parcelamento> montarListaParcelamentosDB2(List<LancamentosCCapitalLegadoDTO> listLanc, DateTimeDB dataAtualProd,Integer idInstituicao) throws BancoobException {
		List<Parcelamento> parcelamentos = new ArrayList<Parcelamento>();
		for (LancamentosCCapitalLegadoDTO dto : listLanc) {
			parcelamentos.add(montarParelamentoContaCapitalDB2(dto, dataAtualProd, idInstituicao));
		}
		return parcelamentos;
	}		
	
	/**
	 * monta lançamentos 
	 *
	 * @param LancamentosCCapitalLegadoDTO 
	 * @param DateTimeDB
	 * @param Integer
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */

	private Parcelamento montarParelamentoContaCapitalDB2(LancamentosCCapitalLegadoDTO dto, DateTimeDB dataLote,Integer idInstituicao) throws BancoobException {
		ContaCapital contaCapital = null;

		contaCapital = contaCapitalServico.obterPorInstituicaoMatricula(idInstituicao, dto.getNumMatricula());
		
		Parcelamento parcelamentoDb2 = new Parcelamento();
		parcelamentoDb2.setContaCapital(contaCapital);
		parcelamentoDb2.setDataSituacao(dataLote);
		parcelamentoDb2.setIdInstituicao(idInstituicao);		
		parcelamentoDb2.setSituacaoParcelamento(new SituacaoParcelamento(Short.valueOf(EnumSituacaoParcelamento.COD_PARCELA_PAGA_VIA_CONTA.getCodigo().toString())));
		String[] identificador = dto.getDescOperacaoExterna().split(SEPARADOR);
		parcelamentoDb2.setNumParcelamento(Short.valueOf(identificador[POSICAO_PARCELAMENTO]));
		parcelamentoDb2.setNumParcela(Short.valueOf(identificador[POSICAO_PARCELA]));
		parcelamentoDb2.setTipoParcelamento(new TipoParcelamento(Short.valueOf(identificador[POSICAO_TIPO_PARCELAMENTO])));		
		
		return parcelamentoDb2;
	}		
	
	/**
	 * monta lista de lancamentos
	 *
	 * @param lista LancamentosCCapitalLegadoDTO 
	 * @param DateTimeDB
	 * @param Integer
	 * @return lista LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<LancamentoContaCapital> montarListaLancamentosDB2(List<LancamentosCCapitalLegadoDTO> listLanc, DateTimeDB dataAtualProd,Integer idInstituicao) throws BancoobException {
		List<LancamentoContaCapital> lancamentos = new ArrayList<LancamentoContaCapital>();
		for (LancamentosCCapitalLegadoDTO dto : listLanc) {
			lancamentos.add(montarLancamentoContaCapitalDB2(dto, dataAtualProd, idInstituicao));
		}
		return lancamentos;
	}		
	/**
	 * monta lançamentos 
	 *
	 * @param LancamentosCCapitalLegadoDTO 
	 * @param DateTimeDB
	 * @param Integer
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */

	private LancamentoContaCapital montarLancamentoContaCapitalDB2(LancamentosCCapitalLegadoDTO dto, DateTimeDB dataLote,Integer idInstituicao) throws BancoobException {
		ContaCapital contaCapital = null;

		contaCapital = contaCapitalServico.obterPorInstituicaoMatricula(idInstituicao, dto.getNumMatricula());
		
		LancamentoContaCapital lancamentoDb2 = new LancamentoContaCapital();
		lancamentoDb2.setContaCapital(contaCapital);
		lancamentoDb2.setDataLancamento(dataLote);
		lancamentoDb2.setIdInstituicao(idInstituicao);
		lancamentoDb2.setIdUsuario(dto.getIdUsuarioResp());
		lancamentoDb2.setTipoSubscricao(null);
		lancamentoDb2.setValorLancamento(dto.getValorLanc());		
		lancamentoDb2.setDescNumDocumento(dto.getDescNumDocumento());			
		lancamentoDb2.setTipoHistoricoCCA(new TipoHistoricoCCA(dto.getIdTipoHistoricoLanc().shortValue()));
		if (dto.getIdTipoHistoricoEstorno() != null) {
			lancamentoDb2.setTipoHistoricoEstorno(new TipoHistoricoCCA(dto.getIdTipoHistoricoEstorno().shortValue()));
		}
		lancamentoDb2.setTipoLote(new TipoLote(dto.getNumLoteLanc().shortValue()));				
		lancamentoDb2.setBolProcessado(ContaCapitalConstantes.NUM_ZERO.shortValue());
		lancamentoDb2.setDataHoraAtualizacao(new DateTimeDB());
		lancamentoDb2.setNumSeqLanc(dto.getNumSeqLanc());
		lancamentoDb2.setDescOperacaoExterna(dto.getDescOperacaoExterna());
		
		return lancamentoDb2;
	}		
	
	/**
	 * monta lista de operações 
	 *
	 * @param DadosFechBaixarParcelasLegadoCCODTO 
	 * @param DateTimeDB
	 * @param Integer
	 * @param String
	 * @return OperacaoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<OperacaoContaCapital> montarListaOperacao(List<DadosFechBaixarParcelasLegadoCCODTO> listDados, DateTimeDB dataAtualProd,Integer idInstituicao, String idUsuario) throws BancoobException {
		List<OperacaoContaCapital> lancamentos = new ArrayList<OperacaoContaCapital>();
		for (DadosFechBaixarParcelasLegadoCCODTO dto : listDados) {
			lancamentos.add(montarOperacaoContaCapital(dto, dataAtualProd, idInstituicao, idUsuario));
		}
		return lancamentos;
	}		
	
	/**
	 * monta operações 
	 *
	 * @param DadosFechBaixarParcelasLegadoCCODTO 
	 * @param DateTimeDB
	 * @param Integer
	 * @param String
	 * @return OperacaoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */

	private OperacaoContaCapital montarOperacaoContaCapital(DadosFechBaixarParcelasLegadoCCODTO dto, DateTimeDB dataLote,Integer idInstituicao, String idUsuario) throws BancoobException {
		
		OperacaoContaCapital operacaoContaCapital = new OperacaoContaCapital();
		
		operacaoContaCapital.setDataHoraInicio(dataLote);
		operacaoContaCapital.setDataHoraFim(dataLote);
		operacaoContaCapital.setDescErro(dto.getDescOcorrencia());
		operacaoContaCapital.setIdInstituicao(idInstituicao);
		operacaoContaCapital.setResultado(new ResultadoOperacaoContaCapital(EnumResultadoOperacaoContaCapital.ERRO_NEGOCIO.getCodigo()));
		operacaoContaCapital.setMetodo(new MetodoOperacaoContaCapital(EnumMetodoOperacaoContaCapital.FECH_BAIXA_PARCELA_VIA_CCO.getCodigo()));
		operacaoContaCapital.setIdUnidadeInst(ContaCapitalConstantes.NUM_ZERO.shortValue());
		operacaoContaCapital.setIdUsuario(idUsuario);
		operacaoContaCapital.setDescParametros(dto.toString());
		
		return operacaoContaCapital;
	}			
	
}

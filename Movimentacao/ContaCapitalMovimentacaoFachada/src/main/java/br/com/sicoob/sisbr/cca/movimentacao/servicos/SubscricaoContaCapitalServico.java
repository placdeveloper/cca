package br.com.sicoob.sisbr.cca.movimentacao.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cca.cadastro.negocio.delegates.CadastroContaCapitalRenDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.PropostaSubscricaoDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorCotaDelegate;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.cadastro.negocio.excecao.ContaCapitalCadastroNegocioException;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.PropostaSubscricao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoSubscricaoCapital;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.LancamentoContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.delegates.SubscricaoContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.SubscricaoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCorrenteIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.GenIntIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ProdutoLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.TrabalhaLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TrabalhaLegadoDTO;
import br.com.sicoob.sisbr.cca.movimentacao.MovimentacaoContaCapital;
import br.com.sicoob.sisbr.cca.movimentacao.vo.CadastroContaCapitalRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ParcelamentoRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.SubscricaoRenVO;
import br.com.sicoob.tipos.DateTime;

/**
 * A Classe SubscricaoContaCapitalServico.
 *
 * @author Antonio.Genaro
 */
@RemoteService
public class SubscricaoContaCapitalServico extends MovimentacaoContaCapital {
	
	private GenIntIntegracaoDelegate genIntIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarGenIntIntegracaoDelegate();
	
	/** O atributo prodLegadoDelegate. */
	private ProdutoLegadoDelegate prodLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarProdutoLegadoDelegate();
	
	/** O atributo propostaDelegate. */
	private PropostaSubscricaoDelegate propostaDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarPropostaSubscricaoDelegate();
	
	/** O atributo valorCotaDelegate. */
	private ValorCotaDelegate valorCotaDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorCotaDelegate();
	
	/** O atributo trabalhaLegadoDelegate. */
	private TrabalhaLegadoDelegate trabalhaLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarTrabalhaLegadoDelegate();
	
	/** O atributo subscricaoContaCapitalDelegate. */
	private SubscricaoContaCapitalDelegate subscricaoContaCapitalDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarSubscricaoContaCapitalDelegate();	
	
	/** O atributo cadastroContaCapitalDelegate. */
	private ContaCorrenteIntegracaoDelegate contaCorrenteIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarContaCorrenteIntegracaoDelegate();
	
	/** O atributo cadastroDelegate. */
	private CadastroContaCapitalRenDelegate cadastroDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarCadastroContaCapitalRenDelegate();
	
	/** O atributo LancamentoDelegate. */
	private LancamentoContaCapitalDelegate lancamentoContaCapitalDelegate= ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarLancamentoContaCapitalDelegate();	
	
	/** A constante MSG_DADOS_GRAVADOS. */
	private static final String MSG_DADOS_GRAVADOS = "Dados gravados com sucesso.";
	
	/** A constante TAMANHO_MAX_LISTA. */
	private static final Integer TAMANHO_MAX_LISTA = 9;
	
	/**
	 * Obter definicoes.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Integer idInstituicao = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		
		String dataAtualProdutoFormatada = DataUtil.converterDateToString(prodLegadoDelegate.obterDataAtualProdutoCCALogado(), "dd/MM/yyyy");
		retornoDTO.getDados().put("dataAtualProduto", dataAtualProdutoFormatada);
		retornoDTO.getDados().put("comboTipoInteg", criarComboTipoIntegralizacao(ContaCapitalConstantes.COD_PROPOSTA_CADASTRO));
		retornoDTO.getDados().put("comboTipoSubscricao", criarComboTipoSubscricao());
		retornoDTO.getDados().put("idInstituicao", idInstituicao);		
		
		retornoDTO.getDados().put("numMinCota", valorCotaDelegate.obterQtdMinCota(idInstituicao, null));
		retornoDTO.getDados().put("percMinInteg", valorCotaDelegate.obterValorMinimoIntegralizacao(idInstituicao));		
		retornoDTO.getDados().put("valorCota", valorCotaDelegate.obterValorCota(idInstituicao));
		retornoDTO.getDados().put("numMaxParcelas", valorCotaDelegate.obterQtdMaxParcela(idInstituicao));
				
		return retornoDTO;
	}	
	
	/**
	 * Checks if is valor integralizacao maior salco cco.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO isValorIntegralizacaoMaiorSalcoCco(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();			
		
		BigDecimal valor = new BigDecimal(reqDTO.getDados().get("valor").toString());		
		retornoDTO.getDados().put("isValorIntegralizacaoMaiorSalcoCco", contaCorrenteIntegracaoDelegate.isValorIntegralizacaoMaiorSalcoCco(valor, 
				Long.valueOf(reqDTO.getDados().get("idPessoa").toString()), 
				Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()), 
				InformacoesUsuario.getInstance().getLogin().toString(), 
				Long.valueOf(reqDTO.getDados().get("numContaCorrente").toString())));					
		
		return retornoDTO;
	}	
	
	/**
	 * Obter proposta subscricao.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterPropostaSubscricao(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();	
		
		Integer idPessoa = null;
		if(reqDTO.getDados().get("idPessoaLegado") != null) {
			idPessoa = Integer.valueOf(reqDTO.getDados().get("idPessoaLegado").toString());
		} else {
			idPessoa = Integer.valueOf(reqDTO.getDados().get("idPessoa").toString());
		}
		
		retornoDTO.getDados().put("comboTrabalha", criarComboTrabalha(trabalhaLegadoDelegate.obterDadosTrabalha(idPessoa)));		
		retornoDTO.getDados().put("comboTipoSubscricao", criarComboTipoSubscricao());
		retornoDTO.getDados().put("propostaSubscricao", montarPropostaSubscricaoVO(propostaDelegate.obter(Integer.valueOf(reqDTO.getDados().get("idContaCapital").toString()))));
		retornoDTO.getDados().put("comboCco", criarComboCco(reqDTO));
		
		return retornoDTO;
	}
	
	private List<ItemListaVO> criarComboCco(RequisicaoReqDTO reqDTO) throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		ContaCapital cca = 
				ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate()
					.obter(Integer.valueOf(reqDTO.getDados().get("idContaCapital").toString()));
		verificarContaCapitalNaoEncontrada(cca);
		ContaCorrenteIntegracaoDTO ccoDTO = new ContaCorrenteIntegracaoDTO();
		ccoDTO.setIdInstituicao(cca.getIdInstituicao());
		ccoDTO.setIdPessoa(cca.getIdPessoa());
		List<ContaCorrenteIntegracaoRetDTO> lstCco = contaCorrenteIntegracaoDelegate.consultarContaCorrenteAtivaPorNumeroCliente(ccoDTO);
		for (ContaCorrenteIntegracaoRetDTO cco : lstCco) {
			lista.add(new ItemListaVO(cco.getNumeroContaCorrente().toString(), cco.getNumeroContaCorrente().toString()));
		}
		return lista;
	}

	/**
	 * Criar combo trabalha.
	 *
	 * @param listTrabalhaLegadoDTO o valor de list trabalha legado dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ItemListaVO> criarComboTrabalha(List<TrabalhaLegadoDTO> listTrabalhaLegadoDTO) throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(TAMANHO_MAX_LISTA);
				
		for(TrabalhaLegadoDTO item : listTrabalhaLegadoDTO){
			lista.add(new ItemListaVO(item.getDescMatriculaFunc(), item.getDescMatriculaFunc()));
		}					
		
		return lista;
	}
	
	/**
	 * Montar proposta subscricao vo.
	 *
	 * @param proposta o valor de proposta
	 * @return SubscricaoRenVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public SubscricaoRenVO montarPropostaSubscricaoVO(PropostaSubscricao proposta) throws BancoobException {
				
		SubscricaoRenVO subscricaoRenVO = new SubscricaoRenVO();
		if(proposta!=null){
			subscricaoRenVO.setTipoInteg(proposta.getTipoIntegralizacao().getId());
			subscricaoRenVO.setQtdParcelas(proposta.getQtdParcelaProposta());
			subscricaoRenVO.setDiaDebito(proposta.getDiaDebitoProposta());
			subscricaoRenVO.setVlrInteg(proposta.getValorIntegProposta());
			subscricaoRenVO.setVlrSubs(proposta.getValorSubsProposta());
			subscricaoRenVO.setVlrParcelas(proposta.getValorParcelaProposta());
			subscricaoRenVO.setNumContaCorrente(proposta.getNumContaCorrente());
			subscricaoRenVO.setBolSubscricaoProposta(proposta.getBolSubscricaoProposta());
			
			if (proposta.getBolSubscricaoProposta().equals(ContaCapitalConstantes.ST_BOL_INATIVO) && lancamentoContaCapitalDelegate.pesquisarCountLancamentosPorContaCapitalSubscricao(proposta.getIdContaCapital()).compareTo(ContaCapitalConstantes.NUM_ZERO) > 0) {
				subscricaoRenVO.setBolSubscricaoProposta(ContaCapitalConstantes.ST_BOL_ATIVO);	
			}
				
		}else{
			subscricaoRenVO.setBolSubscricaoProposta(ContaCapitalConstantes.ST_BOL_ATIVO);	
			subscricaoRenVO.setTipoInteg(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAIXA.shortValue());
			subscricaoRenVO.setDiaDebito(ContaCapitalConstantes.ST_BOL_ATIVO);
		}
		
		return subscricaoRenVO;
	}	
	
	
	/**
	 * Monta combo de acordo com o tipo de operacao de subscrição (novo cooperado, nova subscricao e reativar cooperado).
	 *
	 * @param tipoOpInteg o valor de tipo op integ
	 * @return tipo de integralizacao
	 * @throws BancoobException lança a exceção BancoobException
	 * @see ContaCapitalConstantes#COD_INCLUIR_COOPERADO
	 * @see ContaCapitalConstantes#COD_NOVA_SUBSCRICAO
	 * @see ContaCapitalConstantes#COD_REATIVAR_COOPERADO
	 * @see ContaCapitalConstantes#COD_PROPOSTA_CADASTRO
	 */
	public List<ItemListaVO> criarComboTipoIntegralizacao(Integer tipoOpInteg) throws BancoobException {
		
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(TAMANHO_MAX_LISTA);
		
		if(tipoOpInteg.equals(ContaCapitalConstantes.COD_PROPOSTA_CADASTRO)) {
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getDescricao()));
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getDescricao()));
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getDescricao()));
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_FOLHA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_FOLHA.getDescricao()));
		}
		
		return lista;
	}
	
	/**
	 * Monta combo de acordo com o tipo de subscrição.
	 *
	 * @return tipo de subscrição
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ItemListaVO> criarComboTipoSubscricao() throws BancoobException {
				
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(TAMANHO_MAX_LISTA);
		
		lista.add(new ItemListaVO(EnumTipoSubscricaoCapital.COD_TIPO_SUBSCRICAO_CCA_ESTATUTARIA.getCodigo().toString(), EnumTipoSubscricaoCapital.COD_TIPO_SUBSCRICAO_CCA_ESTATUTARIA.getDescricao()));
		lista.add(new ItemListaVO(EnumTipoSubscricaoCapital.COD_TIPO_SUBSCRICAO_CCA_VOLUNTARIA.getCodigo().toString(), EnumTipoSubscricaoCapital.COD_TIPO_SUBSCRICAO_CCA_VOLUNTARIA.getDescricao()));
		lista.add(new ItemListaVO(EnumTipoSubscricaoCapital.COD_TIPO_SUBSCRICAO_CCA_CAMPANHA.getCodigo().toString(), EnumTipoSubscricaoCapital.COD_TIPO_SUBSCRICAO_CCA_CAMPANHA.getDescricao()));
		
		return lista;
	}

	/**
	 * Gerar parcelas.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO gerarParcelas(RequisicaoReqDTO dto) throws BancoobException{
		RetornoDTO retornoDTO = new RetornoDTO();
		
		try{
					
			SubscricaoRenVO vo = (SubscricaoRenVO) dto.getDados().get("vo");
			
			Calendar dataAtualProduto = Calendar.getInstance();
			dataAtualProduto.setTime(new Date(prodLegadoDelegate.obterDataAtualProdutoCCALogado().getTime()));
			
			GregorianCalendar data = new GregorianCalendar(dataAtualProduto.get(Calendar.YEAR), dataAtualProduto.get(Calendar.MONTH), vo.getDiaDebito()); 
			Date dataVencimento = data.getTime();								
			
			Integer qtdParcelas = vo.getQtdParcelas();
			BigDecimal valorAParcelar = vo.getVlrParcelas();		
			BigDecimal valorParcela = valorAParcelar.divide(new BigDecimal(qtdParcelas), 2, BigDecimal.ROUND_DOWN);
					
			List<ParcelamentoRenVO> lstSaida = new ArrayList<ParcelamentoRenVO>();
						
			for (Integer i=0; i <= qtdParcelas;i++){				

				if(i!=0){					
					ParcelamentoRenVO itemSaida = new ParcelamentoRenVO();			
					
					itemSaida.setIdTipoInteg(vo.getTipoInteg());			
					itemSaida.setNumContaCorrente(vo.getNumContaCorrente());
					itemSaida.setNumParcela(i.shortValue());
					itemSaida.setDescNumMatriculaFunc(vo.getDescNumMatriculaFunc());						
					
					Date dataProximoVencimento = new Date(new org.joda.time.DateTime(dataVencimento).plusMonths(i).getMillis());	
					
					if (!genIntIntegracaoDelegate.verificarDiaUtil(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()), dataProximoVencimento)){
						dataProximoVencimento = new DateTime(ContaCapitalUtil.formatarDataSemHora(genIntIntegracaoDelegate.recuperarProximoDiaUtil(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()), dataProximoVencimento)).getTime());
					}
										
					itemSaida.setDataVencimento(ContaCapitalUtil.formatarDataMascara(dataProximoVencimento,"dd/MM/yyyy"));					
					itemSaida.setDataVencimentoOrdenacao(ContaCapitalUtil.formatarDataMascara(dataProximoVencimento,"yyyyMMdd"));

					if (qtdParcelas.equals(i)){
						itemSaida.setValorParcela(valorParcela.add(valorAParcelar.subtract(valorParcela.multiply(new BigDecimal(qtdParcelas)))));								
					}else{
						itemSaida.setValorParcela(valorParcela);												
					}										
					lstSaida.add(itemSaida);
				}
				
			}
			
			retornoDTO.getDados().put("listaParcelas", lstSaida);
			
			return retornoDTO;			
			
		}catch (BancoobException e) {
			SicoobLoggerPadrao.getInstance(getClass()).erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_023");
		}			
		
	}	
	
	/**
	 * Verificar dia util.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO verificarDiaUtil(RequisicaoReqDTO dto) throws BancoobException{
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Date dtParcela = (Date) dto.getDados().get("dtParcela");								
		
		if (!genIntIntegracaoDelegate.verificarDiaUtil(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()), dtParcela)){
			retornoDTO.getDados().put("bolDataUtil", "0");			
		}else{
			retornoDTO.getDados().put("bolDataUtil", "1");			
		}
			
		return retornoDTO;			
		
	}		
	
	
	/**
	 * Inclusao SQL e DB2.
	 *
	 * @param reqDTO o valor de req dto
	 * @return msg
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO incluir(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		SubscricaoRenVO subscricaoRenVO = (SubscricaoRenVO) reqDTO.getDados().get("vo");		
		SubscricaoRenDTO subscricaoRenDTO = montarSubscricao(subscricaoRenVO);		
		
		@SuppressWarnings("unchecked")
		List<ParcelamentoRenVO> lstParcelamentoRenVO = (List<ParcelamentoRenVO>) reqDTO.getDados().get("listaParcelasVO");					
		List<ParcelamentoRenDTO> lstParcelamentoRenDTO = montarParcelas(lstParcelamentoRenVO);			
			
		try {			
			subscricaoContaCapitalDelegate.incluir(subscricaoRenDTO, lstParcelamentoRenDTO);			
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			retornoDTO.getDados().put("msg", e.getMessage());
			retornoDTO.getDados().put("erroNegocial", e.getMessage());			
			return retornoDTO;			
		}catch (ContaCapitalCadastroNegocioException e) {
			retornoDTO.getDados().put("msg", e.getMessage());
			retornoDTO.getDados().put("erroNegocial", e.getMessage());			
			return retornoDTO;			
		} catch (ContaCapitalMovimentacaoException e) {
			retornoDTO.getDados().put("msg", e.getMessage());
			return retornoDTO;			
		}catch (BancoobException e) {
			SicoobLoggerPadrao.getInstance(getClass()).erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_001");
		}				
		retornoDTO.getDados().put("msg", MSG_DADOS_GRAVADOS);
		
		return retornoDTO;
	}
	
	
	/**
	 * Montar subscricao.
	 *
	 * @param vo o valor de vo
	 * @return SubscricaoRenDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private SubscricaoRenDTO montarSubscricao(SubscricaoRenVO vo) throws BancoobException {				
		SubscricaoRenDTO dto = new SubscricaoRenDTO();		
		
		dto.setBolSubscricaoProposta(vo.getBolSubscricaoProposta());
		dto.setDataHoraAtualizacao(vo.getDataHoraAtualizacao());
		dto.setDescNumMatriculaFunc(vo.getDescNumMatriculaFunc());
		dto.setDiaDebito(vo.getDiaDebito());
		dto.setIdContaCapital(vo.getIdContaCapital());
		dto.setIdInstituicao(vo.getIdInstituicao());
		dto.setNumContaCapital(vo.getNumContaCapital());
		dto.setNumContaCorrente(vo.getNumContaCorrente());
		dto.setQtdParcelas(vo.getQtdParcelas());
		dto.setTipoInteg(vo.getTipoInteg());
		dto.setVlrInteg(vo.getVlrInteg());
		dto.setVlrParcelas(vo.getVlrParcelas());
		dto.setVlrSubs(vo.getVlrSubs());
		dto.setIdTipoSubscricao(vo.getIdTipoSubscricao().shortValue());
		dto.setIdPessoaLegado(vo.getIdPessoaLegado());
		dto.setIdPessoa(vo.getIdPessoa());
		
		return dto;		
	}		
	
	/**
	 * Montar parcelas.
	 *
	 * @param lstVo o valor de lst vo
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<ParcelamentoRenDTO> montarParcelas(List<ParcelamentoRenVO> lstVo) throws BancoobException {				
		List<ParcelamentoRenDTO> lstDto = new ArrayList<ParcelamentoRenDTO>();		
				
		for(ParcelamentoRenVO parcelamentoRenVO:lstVo){			
					
			ParcelamentoRenDTO item = new ParcelamentoRenDTO();
			item.setDataVencimento(new DateTime(ContaCapitalUtil.formatarStringToDate(parcelamentoRenVO.getDataVencimento()).getTime()));
			item.setDataVencimentoOrdenacao(parcelamentoRenVO.getDataVencimentoOrdenacao());
			item.setDescNumMatriculaFunc(parcelamentoRenVO.getDescNumMatriculaFunc());
			item.setIdTipoInteg(parcelamentoRenVO.getIdTipoInteg());
			item.setNumContaCorrente(parcelamentoRenVO.getNumContaCorrente());
			item.setNumParcela(parcelamentoRenVO.getNumParcela());
			item.setValorParcela(parcelamentoRenVO.getValorParcela());
			
			lstDto.add(item);
		}		
		
		return lstDto;		
	}
	
	/**
	 * Consulta usada na plataforma de atendimento.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO consultar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Integer idPessoa = (Integer) reqDTO.getDados().get("idPessoa");
		Integer idInstituicao = (Integer) reqDTO.getDados().get("idInstituicao");
		
		if(idPessoa != null && idInstituicao != null) {
			
			CadastroContaCapitalRenDTO dto = new CadastroContaCapitalRenDTO();
			dto.setIdPessoa(idPessoa);
			dto.setIdInstituicao(idInstituicao);
			dto.setIdSituacaoContaCapital(EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo());
			dto.setIdSituacaoCadastro(EnumSituacaoCadastroProposta.COD_APROVADO.getCodigo());
			
			List<CadastroContaCapitalRenDTO> resultadoDTO = cadastroDelegate.pesquisar(dto);
			if(resultadoDTO != null && !resultadoDTO.isEmpty()) {
				retornoDTO.getDados().put("registros", lstDtoParaVo(resultadoDTO));
			}
		}
		
		return retornoDTO;
	}

	
	/**
	 * O método Dto para vo.
	 *
	 * @param vo o valor de vo
	 * @param dto o valor de dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void dtoParaVo(CadastroContaCapitalRenVO vo, CadastroContaCapitalRenDTO dto) throws BancoobException {
		vo.setIdContaCapital(dto.getIdContaCapital());
		vo.setIdInstituicao(dto.getIdInstituicao());
		vo.setIdPessoa(dto.getIdPessoa());
		vo.setNomePessoa(dto.getNomePessoa());
		vo.setNomeCompleto(dto.getNomeCompleto());
		vo.setCpfCnpj(dto.getCpfCnpj());
		vo.setIdPessoaLegado(dto.getIdPessoaLegado());
		vo.setNumContaCapital(dto.getNumContaCapital());
		vo.setNumContaCapitalGerada(dto.getNumContaCapitalGerada());
		vo.setVlrSubs(dto.getVlrSubs());
		vo.setVlrInteg(dto.getVlrInteg());
		vo.setQtdParcelas(dto.getQtdParcelas());
		vo.setVlrParcelas(dto.getVlrParcelas());
		vo.setDiaDebito(dto.getDiaDebito());
		vo.setTipoInteg(dto.getTipoInteg());
		vo.setNumCco(dto.getNumCco());
		vo.setIdSituacaoCadastro(dto.getIdSituacaoCadastro());
		vo.setDescSituacaoAprovacaoCapital(dto.getDescSituacaoAprovacaoCapital());
		vo.setDescSituacaoContaCapital(dto.getDescSituacaoContaCapital());
		vo.setMatriculaEscolhida(dto.getMatriculaEscolhida());
		vo.setDataHoraAtualizacao(dto.getDataHoraAtualizacao());
		vo.setIdAtividade(dto.getIdAtividade());
		vo.setObservacao(dto.getObservacao());
		vo.setIdSituacaoContaCapital(dto.getIdSituacaoContaCapital());
		
		Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
	    c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));
	    c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));
	    c.set(Calendar.MILLISECOND, c.getActualMinimum(Calendar.MILLISECOND));
	    
		if(dto.getDataMatricula() != null && dto.getDataMatricula().compareTo(c.getTime()) == 0) {
			vo.setPermissaoExcluir(true);
		} else {
			vo.setPermissaoExcluir(false);
		}
	}
	
	/**
	 * Lst dto para vo.
	 *
	 * @param lstDTO o valor de lst dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<CadastroContaCapitalRenVO> lstDtoParaVo(List<CadastroContaCapitalRenDTO> lstDTO) throws BancoobException {
		List<CadastroContaCapitalRenVO> lst = new ArrayList<CadastroContaCapitalRenVO>();
		CadastroContaCapitalRenVO vo = null;
		
		for(CadastroContaCapitalRenDTO dto : lstDTO) {
			vo = new CadastroContaCapitalRenVO();
			dtoParaVo(vo, dto);
			lst.add(vo);
		}
		
		return lst;
	}

}

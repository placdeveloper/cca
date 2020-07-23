/*
 * 
 */
package br.com.sicoob.sisbr.cca.movimentacao.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cca.cadastro.negocio.delegates.CadastroContaCapitalRenDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorConfiguracaoCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorCotaDelegate;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.cadastro.negocio.excecao.ContaCapitalCadastroNegocioException;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumMotivoDevolucao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.DevolucaoContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ParcelamentoContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.dto.DevolucaoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.CaptacaoRemuneradaIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCorrenteIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.GenIntIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ProdutoLegadoDelegate;
import br.com.sicoob.sisbr.cca.movimentacao.MovimentacaoContaCapital;
import br.com.sicoob.sisbr.cca.movimentacao.vo.CadastroContaCapitalRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.DevolucaoRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ParcelamentoRenVO;
import br.com.sicoob.tipos.DateTime;

/**
 * A Classe DevolucaoContaCapitalServico.
 *
 * @author Antonio.Genaro
 */
@RemoteService
public class DevolucaoContaCapitalServico extends MovimentacaoContaCapital {

	/** O atributo contaCapitalDelegate. */
	private ContaCapitalDelegate contaCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate();	
	
	/** O atributo valorCotaDelegate. */
	private ValorCotaDelegate valorCotaDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorCotaDelegate();	
	
	/** O atributo genIntIntegracaoDelegate. */
	private GenIntIntegracaoDelegate genIntIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarGenIntIntegracaoDelegate();	
	
	/** O atributo prodLegadoDelegate. */
	private ProdutoLegadoDelegate prodLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarProdutoLegadoDelegate();
	
	/** O atributo devolucaoContaCapitalDelegate. */
	private DevolucaoContaCapitalDelegate devolucaoContaCapitalDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarDevolucaoContaCapitalDelegate();		
	
	/** O atributo captacaoRemuneradaIntegracaoDelegate. */
	private CaptacaoRemuneradaIntegracaoDelegate captacaoRemuneradaIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarCaptacaoRemuneradaIntegracaoDelegate();
	/** O atributo cadastroDelegate. */
	private CadastroContaCapitalRenDelegate cadastroDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarCadastroContaCapitalRenDelegate();
	
	/** O atributo valorConfiguracaoCapitalDelegate. */
	private ValorConfiguracaoCapitalDelegate valorConfiguracaoCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorConfiguracaoCapitalDelegate();

	/** O atributo cadastroContaCapitalDelegate. */
	private ContaCorrenteIntegracaoDelegate contaCorrenteIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarContaCorrenteIntegracaoDelegate();
	
	private ParcelamentoContaCapitalDelegate parcelamentoDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarParcelamentoContaCapitalDelegate();
	
	/** A constante TAMANHO_MAX_LISTA. */
	private static final Integer TAMANHO_MAX_LISTA = 10;
	
	/** A constante MSG_DADOS_GRAVADOS. */
	private static final String MSG_DADOS_GRAVADOS = "Dados gravados com sucesso.";
	
	/**
	 * Obter definicoes.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();				
		
		retornoDTO.getDados().put("cboModalidade", criarComboModalidadeCaptacaoRemunerada());				
		retornoDTO.getDados().put("idInstituicao", Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));		
		String dataAtualProdutoFormatada = DataUtil.converterDateToString(prodLegadoDelegate.obterDataAtualProdutoCCALogado(), "dd/MM/yyyy");
		retornoDTO.getDados().put("dataAtualProduto", dataAtualProdutoFormatada);		
		retornoDTO.getDados().put("numMaxParcelas", valorConfiguracaoCapitalDelegate.obterValorConfiguracao(ContaCapitalConstantes.PAR_MAXIMO_PARCELAS_DEVOL_CAPITAL , Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao())).getValorConfiguracao());
		
		return retornoDTO;
	}	
	
	/**
	 * Obter dados devolucao.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDadosDevolucao(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();	
		
		Integer idContaCapital = Integer.valueOf(reqDTO.getDados().get("idContaCapital").toString());
		
		ContaCapital contaCapital = contaCapitalDelegate.obter(idContaCapital);
		verificarContaCapitalNaoEncontrada(contaCapital);
		
		BigDecimal valorMinimoSubscricao = valorCotaDelegate.obterValorMinimoSubscricao(contaCapital.getIdInstituicao(), contaCapital.getIdPessoa());
		BigDecimal valorCota = valorCotaDelegate.obterValorCota(contaCapital.getIdInstituicao());
		Integer qtdMinCota = valorCotaDelegate.obterQtdMaxParcela(contaCapital.getIdInstituicao());
		
		if(contaCapital.getSituacaoContaCapital().getId() == ContaCapitalConstantes.COD_SITUACAO_COOPERADO_ATIVO.shortValue()){						
			retornoDTO.getDados().put("cboTipoInteg", criarComboTipoIntegralizacao(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_ATIVO, false));
			retornoDTO.getDados().put("cboTipoIntegParcela", criarComboTipoIntegralizacao(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_ATIVO, true));
			retornoDTO.getDados().put("cboMotivoDevolucao", criarComboMotivoDevolucao(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_ATIVO));		
			retornoDTO.getDados().put("vlrDevolver", calcularValorIntegralizado(idContaCapital));	
			retornoDTO.getDados().put("vlrMinimoExigido", valorMinimoSubscricao);	
		}else{
			retornoDTO.getDados().put("cboTipoInteg", criarComboTipoIntegralizacao(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_DEMITIDO, false));
			retornoDTO.getDados().put("cboTipoIntegParcela", criarComboTipoIntegralizacao(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_DEMITIDO, true));
			retornoDTO.getDados().put("cboMotivoDevolucao", criarComboMotivoDevolucao(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_DEMITIDO));					
			retornoDTO.getDados().put("vlrDevolver", calcularValorParaDevolucao(idContaCapital));				
			retornoDTO.getDados().put("vlrMinimoExigido", ContaCapitalConstantes.NUM_ZERO);	
		}
		
		retornoDTO.getDados().put("idSituacaoContaCapital", contaCapital.getSituacaoContaCapital().getId());			
		retornoDTO.getDados().put("qtdCotas", qtdMinCota);				
		retornoDTO.getDados().put("vlrCota", valorCota);			
		retornoDTO.getDados().put("comboCco", criarComboCco(contaCapital));
		retornoDTO.getDados().put("vlrBloqueado", calcularValorBloqueado(idContaCapital));
			
		return retornoDTO;
	}	
	
	private BigDecimal calcularValorParaDevolucao(Integer idContaCapital) throws BancoobException {
		BigDecimal valorParcelasDevolucaoEmAberto = parcelamentoDelegate.pesquisarValorParcelasDevolucaoEmAberto(idContaCapital);
		return calcularValorDevolucao(idContaCapital).subtract(valorParcelasDevolucaoEmAberto);
	}

	private List<ItemListaVO> criarComboCco(ContaCapital cca) throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
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
	 * Criar combo tipo integralizacao.
	 *
	 * @param codSituacaoCooperado o valor de cod situacao cooperado
	 * @param bolParcelamento o valor de bol parcelamento
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ItemListaVO> criarComboTipoIntegralizacao(Integer codSituacaoCooperado, Boolean bolParcelamento) throws BancoobException {
		
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(TAMANHO_MAX_LISTA);
		
		if(codSituacaoCooperado.equals(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_ATIVO)) {
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getDescricao()));
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getDescricao()));
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getDescricao()));
			if(!bolParcelamento){ 
				lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAPTACAO_REMUNERADA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAPTACAO_REMUNERADA.getDescricao()));				
			}
		}else{
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getDescricao()));
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getDescricao()));			
		}
		
		return lista;
	}
	
	/**
	 * Criar combo motivo devolucao.
	 *
	 * @param codSituacaoCooperado o valor de cod situacao cooperado
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ItemListaVO> criarComboMotivoDevolucao(Integer codSituacaoCooperado) throws BancoobException {
		
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(TAMANHO_MAX_LISTA);
		
		if(codSituacaoCooperado.equals(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_ATIVO)) {
			lista.add(new ItemListaVO(EnumMotivoDevolucao.COD_MOT_DEV_COB_DEB_VENC_OU_VINC_COOP.getCodigo().toString(), EnumMotivoDevolucao.COD_MOT_DEV_COB_DEB_VENC_OU_VINC_COOP.getDescricao()));
			lista.add(new ItemListaVO(EnumMotivoDevolucao.COD_MOT_DEV_RESG_PARC_IDADE_TEMPO.getCodigo().toString(), EnumMotivoDevolucao.COD_MOT_DEV_RESG_PARC_IDADE_TEMPO.getDescricao()));
			lista.add(new ItemListaVO(EnumMotivoDevolucao.COD_MOT_DEV_POR_DEC_DO_CONS_DE_ADM.getCodigo().toString(), EnumMotivoDevolucao.COD_MOT_DEV_POR_DEC_DO_CONS_DE_ADM.getDescricao()));
		}else{
			lista.add(new ItemListaVO(EnumMotivoDevolucao.COD_MOT_DEV_DEMISSAO.getCodigo().toString(), EnumMotivoDevolucao.COD_MOT_DEV_DEMISSAO.getDescricao()));
			lista.add(new ItemListaVO(EnumMotivoDevolucao.COD_MOT_DEV_ELIMINACAO.getCodigo().toString(), EnumMotivoDevolucao.COD_MOT_DEV_ELIMINACAO.getDescricao()));
			lista.add(new ItemListaVO(EnumMotivoDevolucao.COD_MOT_DEV_EXCLUSAO.getCodigo().toString(), EnumMotivoDevolucao.COD_MOT_DEV_EXCLUSAO.getDescricao()));
		}
		
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
					
			DevolucaoRenVO vo = (DevolucaoRenVO) dto.getDados().get("vo");
			
			Date dataVencimento = new Date(ContaCapitalUtil.formatarStringToDate(vo.getDtInicioParcelamento()).getTime());						
			
			Integer qtdParcelas = vo.getQtdParcelas();
			BigDecimal valorAParcelar = vo.getVlrParcelas();		
			BigDecimal valorParcela = valorAParcelar.divide(new BigDecimal(qtdParcelas), 2, BigDecimal.ROUND_DOWN);
					
			List<ParcelamentoRenVO> lstSaida = new ArrayList<ParcelamentoRenVO>();
			
			if(vo.getVlrAVista().floatValue() == BigDecimal.ZERO.floatValue()){					
				ParcelamentoRenVO itemSaida = new ParcelamentoRenVO();			
				
				itemSaida.setIdTipoInteg(vo.getTipoInteg());			
				itemSaida.setNumContaCorrente(vo.getNumContaCorrente());
				itemSaida.setNumParcela(ContaCapitalConstantes.NUM_ZERO.shortValue());
				
				Date dataProximoVencimento = dataVencimento;	
				
				if (!genIntIntegracaoDelegate.verificarDiaUtil(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()), dataProximoVencimento)){
					dataProximoVencimento = new DateTime(ContaCapitalUtil.formatarDataSemHora(genIntIntegracaoDelegate.recuperarProximoDiaUtil(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()), dataProximoVencimento)).getTime());
				}
									
				itemSaida.setDataVencimento(ContaCapitalUtil.formatarDataMascara(dataProximoVencimento,"dd/MM/yyyy"));					
				itemSaida.setDataVencimentoOrdenacao(ContaCapitalUtil.formatarDataMascara(dataProximoVencimento,"yyyyMMdd"));

				itemSaida.setValorParcela(valorParcela);												
				
				lstSaida.add(itemSaida);
				
				valorAParcelar = valorAParcelar.subtract(valorParcela);	
				qtdParcelas = qtdParcelas-1;
			}
			
						
			for (Integer i=0; i <= qtdParcelas;i++){				

				if(i!=ContaCapitalConstantes.NUM_ZERO){					
					ParcelamentoRenVO itemSaida = new ParcelamentoRenVO();			
					
					itemSaida.setIdTipoInteg(vo.getTipoInteg());			
					itemSaida.setNumContaCorrente(vo.getNumContaCorrente());
					itemSaida.setNumParcela(i.shortValue());
					
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
			throw new ContaCapitalMovimentacaoException("MSG_023", e);
		}			
		
	}		
	
	/**
	 * Incluir.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO incluir(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		String msg = "msg";
		
		try {			
			DevolucaoRenVO devolucaoRenVO = (DevolucaoRenVO) reqDTO.getDados().get("vo");		
			DevolucaoRenDTO devolucaoRenDTO = montarDevolucao(devolucaoRenVO);		
			
			if(devolucaoRenDTO.getTipoInteg().shortValue() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAPTACAO_REMUNERADA.shortValue()){
				devolucaoContaCapitalDelegate.incluirCaptacaoRemunerada(devolucaoRenDTO);											
			}else{
				@SuppressWarnings("unchecked")
				List<ParcelamentoRenVO> lstParcelamentoRenVO = (List<ParcelamentoRenVO>) reqDTO.getDados().get("listaParcelasVO");					
				List<ParcelamentoRenDTO> lstParcelamentoRenDTO = montarParcelas(lstParcelamentoRenVO);			
				
				devolucaoContaCapitalDelegate.incluir(devolucaoRenDTO, lstParcelamentoRenDTO);							
			}
			
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);
		}catch (ContaCapitalCadastroNegocioException e) {
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(), e);			
		} catch (ContaCapitalMovimentacaoException e) {
			throw new ContaCapitalMovimentacaoException(e.getMessage(), e);			
		}catch (BancoobException e) {
			SicoobLoggerPadrao.getInstance(getClass()).erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_001", e);
		}				
		retornoDTO.getDados().put(msg, MSG_DADOS_GRAVADOS);
		
		return retornoDTO;
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
		String dataAtualProdutoFormatada = DataUtil.converterDateToString(prodLegadoDelegate.obterDataAtualProdutoCCALogado(), "dd/MM/yyyy");
				
		for(ParcelamentoRenVO parcelamentoRenVO:lstVo){			
					
			ParcelamentoRenDTO item = new ParcelamentoRenDTO();
			item.setDataVencimento(new DateTime(ContaCapitalUtil.formatarStringToDate(parcelamentoRenVO.getDataVencimento()).getTime()));
			item.setDataVencimentoOrdenacao(parcelamentoRenVO.getDataVencimentoOrdenacao());
			item.setDescNumMatriculaFunc(parcelamentoRenVO.getDescNumMatriculaFunc());
			item.setIdTipoInteg(parcelamentoRenVO.getIdTipoInteg());
			item.setNumContaCorrente(parcelamentoRenVO.getNumContaCorrente());
			item.setNumParcela(parcelamentoRenVO.getNumParcela());
			item.setValorParcela(parcelamentoRenVO.getValorParcela());
			item.setIdContaCapital(parcelamentoRenVO.getIdContaCapital());
			item.setIdTipoParcelamento(ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_DEVOLUCAO.shortValue());
			item.setIdMotivoDevolucao(parcelamentoRenVO.getIdMotivoDevolucao());
			item.setIdSituacaoParcelamento(ContaCapitalConstantes.COD_PARCELA_GERADA.shortValue());
			
			if (parcelamentoRenVO.getNumParcela() == ContaCapitalConstantes.NUM_ZERO.shortValue()
					&& dataAtualProdutoFormatada.equals(parcelamentoRenVO.getDataVencimento())) {
				if (parcelamentoRenVO.getIdTipoInteg() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.shortValue()) {
						item.setIdSituacaoParcelamento(ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_CHADMIN.shortValue());
				} else if (parcelamentoRenVO.getIdTipoInteg() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.shortValue()) {
					item.setIdSituacaoParcelamento(ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_CONTA.shortValue());
				}					
			}				
			
			lstDto.add(item);
		}		
		
		return lstDto;		
	}	
	
	/**
	 * Montar devolucao.
	 *
	 * @param vo o valor de vo
	 * @return DevolucaoRenDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private DevolucaoRenDTO montarDevolucao(DevolucaoRenVO vo) throws BancoobException {				
		DevolucaoRenDTO dto = new DevolucaoRenDTO();		
		
		dto.setIdContaCapital(vo.getIdContaCapital());
		dto.setIdInstituicao(vo.getIdInstituicao());
		dto.setNumContaCapital(vo.getNumContaCapital());
		dto.setNumContaCorrente(vo.getNumContaCorrente());
		dto.setQtdParcelas(vo.getQtdParcelas());
		dto.setTipoInteg(vo.getTipoInteg());
		dto.setVlrParcelas(vo.getVlrParcelas());
		dto.setIdPessoaLegado(vo.getIdPessoaLegado());
		dto.setIdPessoa(vo.getIdPessoa());
		dto.setIdMotivoDevolucao(vo.getIdMotivoDevolucao());
		dto.setVlrAVista(vo.getVlrAVista());
		dto.setVlrDevolucao(vo.getVlrDevolucao());
		
		//Capitação remunerada
		dto.setIdModalidadeAplicacao(vo.getIdModalidadeAplicacao());
		dto.setVlrPorAplicacao(vo.getVlrPorAplicacao());
		dto.setQtdAplicacao(vo.getQtdAplicacao());
		
		return dto;		
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
	 * Criar combo modalidade captacao remunerada.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ItemListaVO> criarComboModalidadeCaptacaoRemunerada() throws BancoobException {
		
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(TAMANHO_MAX_LISTA);		
		List<ItemListaIntegracaoDTO> listaModalidade = captacaoRemuneradaIntegracaoDelegate.listarModalidadeCaptacaoRemunerada();		
		
		for(ItemListaIntegracaoDTO itemListaIntegracaoDTO:listaModalidade){
			ItemListaVO item = new ItemListaVO(itemListaIntegracaoDTO.getCodListaItem(), itemListaIntegracaoDTO.getDescListaItem());
			lista.add(item);
		}		
		
		return lista;
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
			dto.setIdSituacaoCadastro(EnumSituacaoCadastroProposta.COD_APROVADO.getCodigo());
			
			List<CadastroContaCapitalRenDTO> resultadoDTO = cadastroDelegate.pesquisar(dto);
			if(resultadoDTO != null && !resultadoDTO.isEmpty()) {
				retornoDTO.getDados().put("registros", lstDtoParaVo(resultadoDTO));
			}
		}
		
		return retornoDTO;
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
	
}

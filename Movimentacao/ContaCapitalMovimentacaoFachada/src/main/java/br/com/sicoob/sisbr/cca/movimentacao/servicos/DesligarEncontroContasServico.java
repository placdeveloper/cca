package br.com.sicoob.sisbr.cca.movimentacao.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.DesligarContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.dto.DesligarContaCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.DevolucaoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalImpedimentosNegocioException;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.CreditoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.GenIntIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContratoLiquidacaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ProdutoLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.movimentacao.MovimentacaoContaCapital;
import br.com.sicoob.sisbr.cca.movimentacao.conversor.ConversorContratoLiquidacao;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ContratoLiquidacaoVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.DesligarContaCapitalRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.DevolucaoRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ParcelamentoRenVO;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class DesligarEncontroContasServico extends MovimentacaoContaCapital {

	private ProdutoLegadoDelegate prodLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarProdutoLegadoDelegate();
	private CreditoIntegracaoDelegate creditoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarCreditoIntegracaoDelegate();
	private ContaCapitalLegadoDelegate contaCapitalLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarContaCapitalLegadoDelegate();
	private GenIntIntegracaoDelegate genIntIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarGenIntIntegracaoDelegate();
	private DesligarContaCapitalDelegate desligarContaCapitalDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarDesligarContaCapitalDelegate();
	
	private ConversorContratoLiquidacao conversorContrato = new ConversorContratoLiquidacao();
	
	/**
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		retornoDTO.getDados().put("dataAtualProduto", DataUtil.converterDateToString(prodLegadoDelegate.obterDataAtualProdutoCCALogado(), "dd/MM/yyyy"));
		retornoDTO.getDados().put("motivosDesligamento", criarCboMotivoDesligamento());
		retornoDTO.getDados().put("formasCredito", criarCboFormaCredito());
		
		return retornoDTO;
	}

	private List<ItemListaVO> criarCboFormaCredito() {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getDescricao()));
		lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getDescricao()));
		return lista;
	}

	private List<ItemListaVO> criarCboMotivoDesligamento() throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		lista.add(new ItemListaVO(EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_DEMITIDO.getCodigo().toString(), "DEMISSÃO"));
		lista.add(new ItemListaVO(EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ELIMINADO.getCodigo().toString(), "ELIMINAÇÃO"));
		lista.add(new ItemListaVO(EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_EXCLUIDO.getCodigo().toString(), "EXCLUSÃO"));
		return lista;
	}
	
	/**
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterInformacoesDesligamento(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Integer idContaCapital = Integer.valueOf(reqDTO.getDados().get("idContaCapital").toString());
		Integer numMatricula = Integer.valueOf(reqDTO.getDados().get("numMatricula").toString());
		ContaCapitalLegado ccaLegado = contaCapitalLegadoDelegate.obter(numMatricula);
		List<ContratoLiquidacaoDTO> contratosDTO = creditoDelegate.consultarContratosLiquidacao(
				Integer.valueOf(InformacoesUsuario.getInstance().getCooperativa()), ccaLegado.getNumCliente());
		
		retornoDTO.getDados().put("valorIntegralizado", calcularValorIntegralizado(idContaCapital));
		retornoDTO.getDados().put("contratos", conversorContrato.converterListaDTOparaVO(contratosDTO));
		
		return retornoDTO;
	}
	
	/**
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO gerarParcelas(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Integer idInstituicao = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		DevolucaoRenVO vo = (DevolucaoRenVO) reqDTO.getDados().get("vo");
		
		Date dataVencimento = new Date(ContaCapitalUtil.formatarStringToDate(vo.getDtInicioParcelamento()).getTime());						
		Integer qtdParcelas = vo.getQtdParcelas();
		BigDecimal valorAParcelar = vo.getVlrParcelas();		
		BigDecimal valorParcela = valorAParcelar.divide(new BigDecimal(qtdParcelas), 2, BigDecimal.ROUND_DOWN);
		
		List<ParcelamentoRenVO> lstSaida = new ArrayList<ParcelamentoRenVO>();
		
		for (Integer i=0; i < qtdParcelas;i++){				
			ParcelamentoRenVO itemSaida = new ParcelamentoRenVO();			
			
			itemSaida.setIdTipoInteg(vo.getTipoInteg());			
			itemSaida.setNumParcela(i.shortValue());
			
			Date dataProximoVencimento = new Date(new org.joda.time.DateTime(dataVencimento).plusMonths(i).getMillis());	
			
			if (!genIntIntegracaoDelegate.verificarDiaUtil(idInstituicao, dataProximoVencimento)) {
				Date proximoDiaUtil = genIntIntegracaoDelegate.recuperarProximoDiaUtil(idInstituicao, dataProximoVencimento);
				dataProximoVencimento = new DateTime(ContaCapitalUtil.formatarDataSemHora(proximoDiaUtil).getTime());
			}
								
			itemSaida.setDataVencimento(ContaCapitalUtil.formatarDataMascara(dataProximoVencimento,"dd/MM/yyyy"));
			itemSaida.setDataVencimentoOrdenacao(ContaCapitalUtil.formatarDataMascara(dataProximoVencimento,"yyyyMMdd"));

			if (qtdParcelas.equals(i+1)) {
				itemSaida.setValorParcela(valorParcela.add(valorAParcelar.subtract(valorParcela.multiply(new BigDecimal(qtdParcelas)))));								
			} else {
				itemSaida.setValorParcela(valorParcela);												
			}										
			
			lstSaida.add(itemSaida);
		}
		
		retornoDTO.getDados().put("listaParcelas", lstSaida);
		return retornoDTO;
	}
	
	/**
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO validarDesligamento(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		DesligarContaCapitalRenVO vo = (DesligarContaCapitalRenVO) reqDTO.getDados().get("vo");
		DesligarContaCapitalDTO dto = montarDesligarDTO(vo);
		try {
			retornoDTO.getDados().put("valido", desligarContaCapitalDelegate.validarDesligamentoEncontroContas(dto));
		} catch (ContaCapitalImpedimentosNegocioException e) {
			retornoDTO.getDados().put("valido", false);			
			retornoDTO.getDados().put("msg", e.getMessage());
			retornoDTO.getDados().put("impedimentos", true);
		}
		return retornoDTO;
	}

	private DesligarContaCapitalDTO montarDesligarDTO(DesligarContaCapitalRenVO vo) {
		DesligarContaCapitalDTO dto = new DesligarContaCapitalDTO();
		dto.setIdContaCapital(vo.getIdContaCapital());
		dto.setValorIntegralizado(vo.getVlrInteg());
		dto.setIdTipoDesligamento(vo.getTipoOperacao());
		dto.setValorEmprestimos(vo.getVlrEmprestimos());
		return dto;
	}
	
	/**
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	public RetornoDTO desligar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Date dataAtualProduto = prodLegadoDelegate.obterDataAtualProdutoCCALogado();
		
		DesligarContaCapitalRenVO desligarVO = (DesligarContaCapitalRenVO) reqDTO.getDados().get("desligarVO");
		DevolucaoRenVO devolucaoVO = (DevolucaoRenVO) reqDTO.getDados().get("devolucaoVO");
		List<ParcelamentoRenVO> lstParcelamentoRenVO = (List<ParcelamentoRenVO>) reqDTO.getDados().get("listaParcelasVO");
		List<ContratoLiquidacaoVO> listaContratosLiquidarVO = (List<ContratoLiquidacaoVO>) reqDTO.getDados().get("listaContratosLiquidarVO");
		List<ContratoLiquidacaoVO> listaContratosAbertosVO = (List<ContratoLiquidacaoVO>) reqDTO.getDados().get("listaContratosAbertosVO");

		DesligarContaCapitalDTO desligarDTO = montarDesligarDTO(desligarVO);
		DevolucaoRenDTO devolucaoDTO = montarDevolucaoDTO(devolucaoVO);		
		List<ParcelamentoRenDTO> parcelamentosDTO = montarParcelasDTO(lstParcelamentoRenVO, dataAtualProduto);
		desligarDTO.setContratosLiquidar(conversorContrato.converterListaVOparaDTO(listaContratosLiquidarVO));
		desligarDTO.setContratosAbertos(conversorContrato.converterListaVOparaDTO(listaContratosAbertosVO));
		
		desligarContaCapitalDelegate.desligarEncontroContas(desligarDTO, devolucaoDTO, parcelamentosDTO);
		
		return retornoDTO;
	}
	
	private DevolucaoRenDTO montarDevolucaoDTO(DevolucaoRenVO vo) throws BancoobException {				
		DevolucaoRenDTO dto = new DevolucaoRenDTO();		
		
		dto.setIdContaCapital(vo.getIdContaCapital());
		dto.setIdInstituicao(vo.getIdInstituicao());
		dto.setNumContaCapital(vo.getNumContaCapital());
		dto.setTipoInteg(vo.getTipoInteg());
		dto.setIdPessoaLegado(vo.getIdPessoaLegado());
		dto.setIdPessoa(vo.getIdPessoa());
		dto.setIdMotivoDevolucao(vo.getIdMotivoDevolucao());
		dto.setVlrAVista(vo.getVlrAVista());
		dto.setVlrDevolucao(vo.getVlrDevolucao());
		
		return dto;		
	}
	
	private List<ParcelamentoRenDTO> montarParcelasDTO(List<ParcelamentoRenVO> lstVo, Date dataAtualProduto) throws BancoobException {				
		List<ParcelamentoRenDTO> lstDto = new ArrayList<ParcelamentoRenDTO>();
		String dataAtualProdutoFormatada = DataUtil.converterDateToString(dataAtualProduto, "dd/MM/yyyy");
				
		for(ParcelamentoRenVO parcelamentoRenVO:lstVo){			
					
			ParcelamentoRenDTO item = new ParcelamentoRenDTO();
			item.setNumParcela(parcelamentoRenVO.getNumParcela());
			item.setDataVencimento(new DateTime(ContaCapitalUtil.formatarStringToDate(parcelamentoRenVO.getDataVencimento()).getTime()));
			item.setDataVencimentoOrdenacao(parcelamentoRenVO.getDataVencimentoOrdenacao());
			item.setValorParcela(parcelamentoRenVO.getValorParcela());
			item.setIdTipoInteg(parcelamentoRenVO.getIdTipoInteg());
			item.setIdContaCapital(parcelamentoRenVO.getIdContaCapital());
			item.setIdMotivoDevolucao(parcelamentoRenVO.getIdMotivoDevolucao());
			item.setIdTipoParcelamento(ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_DEVOLUCAO.shortValue());
			item.setIdSituacaoParcelamento(ContaCapitalConstantes.COD_PARCELA_GERADA.shortValue());
			
			if (parcelamentoRenVO.getNumParcela() == ContaCapitalConstantes.NUM_ZERO.shortValue()
					&& dataAtualProdutoFormatada.equals(parcelamentoRenVO.getDataVencimento())
					&& parcelamentoRenVO.getIdTipoInteg() == ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.shortValue()) {
				item.setIdSituacaoParcelamento(ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_CHADMIN.shortValue());
			}				
			
			lstDto.add(item);
		}		
		
		return lstDto;		
	}	
	
}

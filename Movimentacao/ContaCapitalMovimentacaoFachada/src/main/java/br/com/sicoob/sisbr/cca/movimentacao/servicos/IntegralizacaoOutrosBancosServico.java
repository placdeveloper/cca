package br.com.sicoob.sisbr.cca.movimentacao.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.IntegralizacaoOutrosBancosDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.IntegralizacaoOutrosBancosLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.BancoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RemessaIntegralizacaoOutrosBancosLegadoDTO;
import br.com.sicoob.sisbr.cca.movimentacao.MovimentacaoContaCapital;
import br.com.sicoob.sisbr.cca.movimentacao.conversor.ConversorIntegralizacaoOutrosBancos;
import br.com.sicoob.sisbr.cca.movimentacao.vo.IntegralizacaoOutrosBancosVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ItemListaVO;

/**
 * IntegralizacaoOutrosBancosServico
 */
@RemoteService
public class IntegralizacaoOutrosBancosServico extends MovimentacaoContaCapital {

	private ConversorIntegralizacaoOutrosBancos conversor = new ConversorIntegralizacaoOutrosBancos(); 
	
	private IntegralizacaoOutrosBancosLegadoDelegate integralizacaoOutrosBancosLegadoDelegate = 
			ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarIntegralizacaoOutrosBancosLegadoDelegate(); 

	private IntegralizacaoOutrosBancosDelegate integralizacaoOutrosBancosDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarIntegralizacaoOutrosBancosDelegate();
	
	/**
	 * Obter definicoes de dados.
	 * 
	 * @param dto - Passa o RequisicaoReqDTO.
	 * @return - Retorna RetornoDTO. 
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("listaBancos", criarListaBancos());
		return retorno;
	}
	
	private List<ItemListaVO> criarListaBancos() throws BancoobException {
		List<ItemListaVO> listaItens = new ArrayList<ItemListaVO>();
		List<BancoLegadoDTO> listaBancos = integralizacaoOutrosBancosLegadoDelegate.obtemListaBancos();
		for (BancoLegadoDTO dto : listaBancos) {
			listaItens.add(new ItemListaVO(dto.getNumBanco().toString(), dto.getDescBanco()));
		}
		return listaItens;
	}
	
	/**
	 * Consultar - aba cadastro de favorecidos.
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarFav(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		IntegralizacaoOutrosBancosLegadoDTO filtro = new IntegralizacaoOutrosBancosLegadoDTO.Builder()
				.setNumBancoFavorecido(Integer.valueOf(dto.getDados().get("numBanco").toString()))
				.setNumMatricula(dto.getDados().get("numMatricula") == null ? null : Integer.valueOf(dto.getDados().get("numMatricula").toString()))
				.setContaPrincipal((Boolean) dto.getDados().get("apenasContaPrincipal"))
				.build(); 
		Integer tipoSituacao = Integer.valueOf(dto.getDados().get("tipoSituacao").toString());
		List<IntegralizacaoOutrosBancosLegadoDTO> favorecidos = integralizacaoOutrosBancosLegadoDelegate.consultarContasFavorecidos(filtro, tipoSituacao);
		retorno.getDados().put("favorecidos", conversor.converterListaDTOparaVO(favorecidos));
		return retorno;
	}
	
	/**
	 * Atualiza as contas de favorecidos.
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO atualizarContas(RequisicaoReqDTO dto) throws BancoobException {
		integralizacaoOutrosBancosLegadoDelegate.atualizarContas();
		return new RetornoDTO();
	}

	/**
	 * Consultar - aba preparar remessa.
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultar(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		IntegralizacaoOutrosBancosLegadoDTO filtro = new IntegralizacaoOutrosBancosLegadoDTO.Builder()
				.setNumBancoFavorecido(Integer.valueOf(dto.getDados().get("numBanco").toString()))
				.setNumMatricula(dto.getDados().get("numMatricula") == null ? null : Integer.valueOf(dto.getDados().get("numMatricula").toString()))
				.build(); 
		List<IntegralizacaoOutrosBancosLegadoDTO> favorecidos = integralizacaoOutrosBancosLegadoDelegate.consultarFavorecidosIntegralizacao(filtro);
		retorno.getDados().put("favorecidos", conversor.converterListaDTOparaVO(favorecidos));
		return retorno;
	}
	
	/**
	 * Consultar
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	public RetornoDTO enviarRemessa(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		List<IntegralizacaoOutrosBancosVO> vos = (List<IntegralizacaoOutrosBancosVO>) dto.getDados().get("favorecidos");
		List<IntegralizacaoOutrosBancosLegadoDTO> dtos = conversor.converterListaVOparaDTO(vos);
		integralizacaoOutrosBancosLegadoDelegate.enviarRemessa(dtos);
		integralizacaoOutrosBancosLegadoDelegate.enviarIntegBancos();
		return retorno;
	}

	/**
	 * Define as contas principais de favorecidos. 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	public RetornoDTO definirPrincipal(RequisicaoReqDTO dto) throws BancoobException {
		List<IntegralizacaoOutrosBancosVO> vos = (List<IntegralizacaoOutrosBancosVO>) dto.getDados().get("favorecidos");
		List<IntegralizacaoOutrosBancosLegadoDTO> dtos = conversor.converterListaVOparaDTO(vos);
		integralizacaoOutrosBancosLegadoDelegate.definirPrincipal(dtos);
		return consultarFav(dto);
	}
	
	/**
	 * Consultar - aba remessa enviada.
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarRemessa(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
				
		IntegralizacaoOutrosBancosLegadoDTO filtro = new IntegralizacaoOutrosBancosLegadoDTO();

		filtro.setNumMatricula(dto.getDados().get("numMatricula") == null ? null : Integer.valueOf(dto.getDados().get("numMatricula").toString()));
		filtro.setNumBanco(Integer.valueOf(dto.getDados().get("numBanco").toString()));
		filtro.setAnoMes(dto.getDados().get("anoMes").toString());				
		
		List<RemessaIntegralizacaoOutrosBancosLegadoDTO> remessas = integralizacaoOutrosBancosLegadoDelegate.consultarRemessaIntegralizacaoOutrosBancos(filtro);
		retorno.getDados().put("remessas", conversor.converterRemessaListaDTOparaVO(remessas));
		return retorno;
	}
	
	/**
	 * Consultar - aba remessa enviada.
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarRemessaEnvDetalhe(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		IntegralizacaoOutrosBancosLegadoDTO filtro = new IntegralizacaoOutrosBancosLegadoDTO();

		filtro.setNumMatricula(dto.getDados().get("numMatricula") == null ? null : Integer.valueOf(dto.getDados().get("numMatricula").toString()));
		filtro.setSequencialArquivo(Integer.valueOf(dto.getDados().get("sequencialArquivo").toString()));
		filtro.setAnoMes(dto.getDados().get("anoMes").toString());				
		filtro.setNumBanco(Integer.valueOf(dto.getDados().get("numBanco").toString()));
		
		List<IntegralizacaoOutrosBancosLegadoDTO> remessas = integralizacaoOutrosBancosLegadoDelegate.consultarRemessaEnvDetalhe(filtro);
		retorno.getDados().put("remessas", conversor.converterListaDTOparaVO(remessas));
		return retorno;
	}

	
	/**
	 * Consultar - aba preparar remessa.
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarRemessaRetorno(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		IntegralizacaoOutrosBancosLegadoDTO filtro = new IntegralizacaoOutrosBancosLegadoDTO();

		filtro.setNumMatricula(dto.getDados().get("numMatricula") == null ? null : Integer.valueOf(dto.getDados().get("numMatricula").toString()));
		filtro.setNumBanco(Integer.valueOf(dto.getDados().get("numBanco").toString()));
		filtro.setAnoMes(dto.getDados().get("anoMes").toString());		
		
		List<RemessaIntegralizacaoOutrosBancosLegadoDTO> remessas = integralizacaoOutrosBancosLegadoDelegate.consultarRemessaRetorno(filtro);
		retorno.getDados().put("remessas", conversor.converterRemessaListaDTOparaVO(remessas));
		return retorno;
	}	
	
	/**
	 * Consultar - aba preparar remessa.
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarRemessaRetornoDetalhe(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		IntegralizacaoOutrosBancosLegadoDTO filtro = new IntegralizacaoOutrosBancosLegadoDTO();

		filtro.setNumMatricula(dto.getDados().get("numMatricula") == null ? null : Integer.valueOf(dto.getDados().get("numMatricula").toString()));
		filtro.setSequencialArquivo(Integer.valueOf(dto.getDados().get("sequencialArquivo").toString()));
		filtro.setAnoMes(dto.getDados().get("anoMes").toString());						
		filtro.setNumBanco(Integer.valueOf(dto.getDados().get("numBanco").toString()));
		
		List<IntegralizacaoOutrosBancosLegadoDTO> remessas = integralizacaoOutrosBancosLegadoDelegate.consultarRemessaRetornoDetalhe(filtro);
		retorno.getDados().put("remessas", conversor.converterListaDTOparaVO(remessas));
		return retorno;
	}		
	
	/**
	 * Consultar
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	public RetornoDTO integralizar(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		List<IntegralizacaoOutrosBancosVO> vos = (List<IntegralizacaoOutrosBancosVO>) dto.getDados().get("favorecidos");
		
		for (IntegralizacaoOutrosBancosVO vo : vos) {			
			integralizacaoOutrosBancosLegadoDelegate.gravarIntegralizacao(conversor.converterVOparaDTO(vo));
			integralizacaoOutrosBancosDelegate.incluirIntegralizacao(conversor.converterVOparaDTOMov(vo));
		}		
		
		return retorno;
	}
	
}

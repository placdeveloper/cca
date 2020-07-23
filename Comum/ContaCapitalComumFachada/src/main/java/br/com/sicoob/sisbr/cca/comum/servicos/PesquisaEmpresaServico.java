/*
 * 
 */
package br.com.sicoob.sisbr.cca.comum.servicos;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.cca.comum.ContaCapitalComumServico;
import br.com.sicoob.sisbr.cca.comum.conversor.ConversorPesquisaEmpresa;
import br.com.sicoob.sisbr.cca.comum.vo.PesquisaEmpresaVO;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.PesquisaEmpresaLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.PesquisaEmpresaDTO;

/**
 * Responsavel por controlar componente de pesquisa empresa
 * @author Marco.Nascimento
 */
@RemoteService
public class PesquisaEmpresaServico extends ContaCapitalComumServico {
	
	/** O atributo pesquisaDelegate. */
	private PesquisaEmpresaLegadoDelegate pesquisaDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarPesquisaEmpresaLegadoDelegate();
	
	/** O atributo conversor. */
	private ConversorPesquisaEmpresa conversor = new ConversorPesquisaEmpresa();
	
	private static final Integer NUM_REGISTROS_PAGINA = 9;
	
	/**
	 * Pesquisar.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO pesquisar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		PesquisaEmpresaVO vo = (PesquisaEmpresaVO) reqDTO.getDados().get("pesquisaEmpresaVO");
		PesquisaEmpresaDTO dto = new PesquisaEmpresaDTO();
		conversor.voParaDto(dto, vo);
		
		dto.setNumCoop(Integer.valueOf(InformacoesUsuario.getInstance().getCooperativa()));

		List<PesquisaEmpresaDTO> lstRetorno = pesquisaDelegate.pesquisar(dto);
		
		if(CollectionUtils.isNotEmpty(lstRetorno)) {
			List<PesquisaEmpresaVO> lstVO = conversor.dtoParaVo(lstRetorno);
			configurarPaginacao(retorno, lstVO);
		}
		
		return retorno;
	}
	
	/**
	 * O método Configurar paginacao.
	 *
	 * @param retorno o valor de retorno
	 * @param lstVO o valor de lst vo
	 */
	private void configurarPaginacao(RetornoDTO retorno, List<PesquisaEmpresaVO> lstVO) {
		Integer numTotalRegistro = lstVO.size();
		Double numTotalPaginas = Math.ceil((numTotalRegistro.doubleValue() / NUM_REGISTROS_PAGINA.doubleValue()));			
		
		retorno.getDados().put("numRegistroPagina", NUM_REGISTROS_PAGINA);
		retorno.getDados().put("numTotalRegistro", numTotalRegistro);
		retorno.getDados().put("numTotalPaginas", numTotalPaginas);
		retorno.getDados().put("lstDadosRetorno", lstVO);
	}
}
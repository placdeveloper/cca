/*
 * 
 */
package br.com.sicoob.sisbr.cca.comum.servicos;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.comum.negocio.delegates.ContaCapitalComumFabricaDelegates;
import br.com.sicoob.cca.comum.negocio.delegates.PesquisaContaCapitalDelegate;
import br.com.sicoob.cca.comum.negocio.dto.PesquisaContaCapitalDTO;
import br.com.sicoob.sisbr.cca.comum.ContaCapitalComumServico;
import br.com.sicoob.sisbr.cca.comum.conversor.ConversorPesquisaContaCapital;
import br.com.sicoob.sisbr.cca.comum.vo.PesquisaContaCapitalVO;

/**
 * Responsavel por controlar componente de pesquisa conta capital (renovacao)
 * @author Marco.Nascimento
 */
@RemoteService
public class PesquisaContaCapitalServico extends ContaCapitalComumServico {
	
	/** O atributo pesquisaDelegate. */
	private PesquisaContaCapitalDelegate pesquisaDelegate = ContaCapitalComumFabricaDelegates.getInstance().criarPesquisaContaCapitalDelegate();
	
	/** O atributo conversor. */
	private ConversorPesquisaContaCapital conversor = new ConversorPesquisaContaCapital();
	
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
		
		PesquisaContaCapitalVO vo = (PesquisaContaCapitalVO) reqDTO.getDados().get("pesquisaContaCapitalVO");
		PesquisaContaCapitalDTO dto = new PesquisaContaCapitalDTO();
		conversor.voParaDto(dto, vo);
		
		if(dto.getIdInstituicao() == null) {
			if(InformacoesUsuario.getInstance().getIdInstituicao() != null && StringUtils.isNotBlank(InformacoesUsuario.getInstance().getIdInstituicao())) {
				dto.setIdInstituicao(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
			}
		}
		
		List<PesquisaContaCapitalDTO> lstRetorno = pesquisaDelegate.pesquisar(dto);
		
		if(CollectionUtils.isNotEmpty(lstRetorno)) {
			List<PesquisaContaCapitalVO> lstVO = conversor.dtoParaVo(lstRetorno);
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
	private void configurarPaginacao(RetornoDTO retorno, List<PesquisaContaCapitalVO> lstVO) {
		Integer numTotalRegistro = lstVO.size();
		Double numTotalPaginas = Math.ceil((numTotalRegistro.doubleValue() / NUM_REGISTROS_PAGINA.doubleValue()));			
		
		retorno.getDados().put("numRegistroPagina", NUM_REGISTROS_PAGINA);
		retorno.getDados().put("numTotalRegistro", numTotalRegistro);
		retorno.getDados().put("numTotalPaginas", numTotalPaginas);
		retorno.getDados().put("lstDadosRetorno", lstVO);
	}
}
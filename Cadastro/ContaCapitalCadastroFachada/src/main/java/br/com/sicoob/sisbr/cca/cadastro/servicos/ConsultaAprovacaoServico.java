/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.servicos;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.cadastro.negocio.delegates.CadastroContaCapitalRenDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.sisbr.cca.cadastro.CadastroContaCapital;
import br.com.sicoob.sisbr.cca.cadastro.conversor.ConversorCadastroContaCapitalRen;

/**
 * @author marco.nascimento
 */
@RemoteService
public class ConsultaAprovacaoServico extends CadastroContaCapital {
	
	/** O atributo cadastroDelegate. */
	private CadastroContaCapitalRenDelegate cadastroDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarCadastroContaCapitalRenDelegate();
	
	/** O atributo conversor. */
	private ConversorCadastroContaCapitalRen conversor = new ConversorCadastroContaCapitalRen();
	
	/**
	 * Apresentacao inicial da tela
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		CadastroContaCapitalRenDTO dto = new CadastroContaCapitalRenDTO();
		
		List<CadastroContaCapitalRenDTO> resultadoDTO = cadastroDelegate.pesquisarAprovacaoPendente(dto);
		if(CollectionUtils.isNotEmpty(resultadoDTO)) {
			retornoDTO.getDados().put("registros", conversor.lstDtoParaVo(resultadoDTO));
		}
		
		return retornoDTO;
	}
}
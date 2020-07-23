package br.com.sicoob.sisbr.cca.movimentacao.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCorrenteIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.movimentacao.MovimentacaoContaCapital;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ItemListaVO;

@RemoteService
public class OutrosDadosContaCapitalServico extends MovimentacaoContaCapital {
	
	private ContaCapitalLegadoDelegate contaCapitalLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarContaCapitalLegadoDelegate();
	/** O atributo cadastroContaCapitalDelegate. */
	private ContaCorrenteIntegracaoDelegate contaCorrenteIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarContaCorrenteIntegracaoDelegate();
	
	/**
	 * Obter definicoes de dados.
	 * 
	 * @param dto - Passa o RequisicaoReqDTO.
	 * @return - Retorna RetornoDTO. 
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		ContaCapitalLegado contaCapitalLegado = contaCapitalLegadoDelegate.obter(Integer.valueOf(dto.getDados().get("numMatricula").toString()));
		
		retorno.getDados().put("bolParticipaRateio", contaCapitalLegado.getBolParticipaRateio());
		retorno.getDados().put("bolRestricaoRateio", contaCapitalLegado.getBolRestricaoRateio());
		retorno.getDados().put("bolDireitoVoto", contaCapitalLegado.getBolDireitoVoto());
		retorno.getDados().put("numContaCorrente", contaCapitalLegado.getNumContaCorrente());		
		retorno.getDados().put("comboCco", criarComboCco(Integer.valueOf(dto.getDados().get("idInstituicao").toString()), Integer.valueOf(dto.getDados().get("idPessoa").toString())));
		
		return retorno;
	}
	
	
	/**
	 * Realiza alteracao outros dados
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException
	 */
	public RetornoDTO alterar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		ContaCapitalLegado contaCapitalLegado = contaCapitalLegadoDelegate.obter(Integer.valueOf(reqDTO.getDados().get("numMatricula").toString()));
		
		contaCapitalLegado.setBolParticipaRateio(reqDTO.getDados().get("bolParticipaRateio") == null ? null : (Boolean) reqDTO.getDados().get("bolParticipaRateio"));
		contaCapitalLegado.setBolRestricaoRateio(reqDTO.getDados().get("bolRestricaoRateio") == null ? null : (Boolean) reqDTO.getDados().get("bolRestricaoRateio"));
		contaCapitalLegado.setBolDireitoVoto(reqDTO.getDados().get("bolDireitoVoto") == null ? null : (Boolean) reqDTO.getDados().get("bolDireitoVoto"));
		contaCapitalLegado.setNumContaCorrente(reqDTO.getDados().get("numContaCorrente").equals("") ? null : Long.valueOf(reqDTO.getDados().get("numContaCorrente").toString()));

		contaCapitalLegadoDelegate.alterar(contaCapitalLegado);
		
		return retornoDTO;
	}	
	
	/**
	 * Combo de CCO
	 * @param cca
	 * @return
	 * @throws BancoobException
	 */
	private List<ItemListaVO> criarComboCco(Integer idInstituicao, Integer idPessoa) throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		ContaCorrenteIntegracaoDTO ccoDTO = new ContaCorrenteIntegracaoDTO();
		ccoDTO.setIdInstituicao(idInstituicao);
		ccoDTO.setIdPessoa(idPessoa);
		List<ContaCorrenteIntegracaoRetDTO> lstCco = contaCorrenteIntegracaoDelegate.consultarContaCorrenteAtivaPorNumeroCliente(ccoDTO);
		lista.add(new ItemListaVO("", ""));
		for(ContaCorrenteIntegracaoRetDTO cco : lstCco) {
			lista.add(new ItemListaVO(cco.getNumeroContaCorrente().toString(), cco.getNumeroContaCorrente().toString()));
		}
		return lista;
	}
	
}

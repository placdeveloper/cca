/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.servicos;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.cadastro.negocio.delegates.CadastroContaCapitalRenDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.SituacaoCadastroPropostaDelegate;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoPesquisa;
import br.com.sicoob.sisbr.cca.cadastro.CadastroContaCapital;
import br.com.sicoob.sisbr.cca.cadastro.conversor.ConversorCadastroContaCapitalRen;
import br.com.sicoob.sisbr.cca.cadastro.vo.CadastroContaCapitalRenVO;
import br.com.sicoob.sisbr.cca.cadastro.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.CapesIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;

/**
 * @author marco.nascimento
 */
@RemoteService
public class ConsultaContaCapitalRenServico extends CadastroContaCapital {
	
	/** O atributo situacaoCadastroDelegate. */
	private SituacaoCadastroPropostaDelegate situacaoCadastroDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarSituacaoCadastroPropostaDelegate();
	
	/** O atributo cadastroDelegate. */
	private CadastroContaCapitalRenDelegate cadastroDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarCadastroContaCapitalRenDelegate();
	
	/** O atributo conversor. */
	private ConversorCadastroContaCapitalRen conversor = new ConversorCadastroContaCapitalRen();
	
	private static final Integer TAMANHO_LISTA = 3;
	
	/**
	 * Apresentacao inicial da tela
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		retornoDTO.getDados().put("cboSituacaoCadastro", criarComboSituacaoCadastro());
		retornoDTO.getDados().put("cboTipoPesquisa", criarComboTipoPesquisa());
		
		return retornoDTO;
	}
	
	/**
	 * Realiza consulta de acordo com os filtros
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		CadastroContaCapitalRenVO vo = (CadastroContaCapitalRenVO) reqDTO.getDados().get("vo");
		CadastroContaCapitalRenDTO dto = new CadastroContaCapitalRenDTO();
		conversor.voParaDto(vo, dto);
		
		List<CadastroContaCapitalRenDTO> resultadoDTO = cadastroDelegate.pesquisar(dto);
		if(CollectionUtils.isNotEmpty(resultadoDTO)) {
			retornoDTO.getDados().put("registros", conversor.lstDtoParaVo(resultadoDTO));
		}
		
		if (vo.getIdPessoa() != null && vo.getIdInstituicao() != null) {
			retornoDTO.getDados().put("isClienteCadastrado", isClienteCadastrado(vo.getIdPessoa(), vo.getIdInstituicao()));
		}
		
		return retornoDTO;
	}
	
	private Boolean isClienteCadastrado(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		CapesIntegracaoDelegate capesDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarCapesIntegracaoDelegate();
		return capesDelegate.isClienteCadastrado(idPessoa, idInstituicao);
	}
	
	/**
	 * Realiza exclusao da conta capital
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO excluir(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Integer idContaCapital = (Integer) reqDTO.getDados().get("idContaCapital");
		
		cadastroDelegate.excluir(idContaCapital);
		
		return retornoDTO;
	}
	
	/**
	 * Combo com tipos de Situacao da Proposta
	 * @return
	 * @throws BancoobException
	 */
	private List<ItemListaVO> criarComboSituacaoCadastro() throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(TAMANHO_LISTA);
		lista.add(new ItemListaVO("-1", "TODOS"));
		for(SituacaoCadastroProposta sit : situacaoCadastroDelegate.listar()) {
			lista.add(new ItemListaVO(sit.getId().toString(), sit.getDescricao()));
		}
		return lista;
	}
	
	/**
	 * Combo de tipos de pesquisa
	 * @return
	 */
	private List<ItemListaVO> criarComboTipoPesquisa() {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(TAMANHO_LISTA);
		for(EnumTipoPesquisa tipo : EnumTipoPesquisa.values()) {
			lista.add(new ItemListaVO(tipo.getCodigo().toString(), tipo.getDescricao()));
		}
		return lista;
	}
}
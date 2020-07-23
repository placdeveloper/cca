package br.com.sicoob.sisbr.cca.relatorios.servicos;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.comum.negocio.delegates.ContaCapitalComumFabricaDelegates;
import br.com.sicoob.cca.comum.negocio.delegates.ViewInstituicaoDelegate;
import br.com.sicoob.cca.comum.negocio.dto.InstituicaoCooperativaSCIDTO;
import br.com.sicoob.cca.entidades.negocio.enums.EnumOrdenacaoRecolhimentoIrrfDestinacaoJuros;
import br.com.sicoob.sisbr.cca.relatorios.RelatoriosContaCapital;
import br.com.sicoob.sisbr.cca.relatorios.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.relatorios.vo.RelRecolhimentoIrrfDestinacaoJurosVO;

/**
 * @author Ricardo.Barcante
 * Classe de servico responsavel das funções relativas a relatório de destinação de juros 
 * e recolhimentos de irrf
 */
@RemoteService
public class RelRecolhimentoIrrfDestinacaoJurosServico extends RelatoriosContaCapital {
	private ContaCapitalDelegate ccaDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate();
	private ViewInstituicaoDelegate viewSciDelegate  = ContaCapitalComumFabricaDelegates.getInstance().criarViewInstituicaoDelegate();
	
	/**
	 * Método responsável para buscar as informações do usuário para montar o relatório.
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		retorno.getDados().put("comboOrdenacao", criarComboOrdenacao());
		retorno.getDados().put("comboPa", criarComboPa());
		
		retorno.getDados().put("vo", valorInicialTela());
		
		return retorno;
	}
	
	/**
	 * Valor inicial tela.
	 *
	 * @return the rel recolhimento irrf destinacao juros VO
	 * @throws BancoobException the bancoob exception
	 */
	private RelRecolhimentoIrrfDestinacaoJurosVO valorInicialTela() throws BancoobException {
		RelRecolhimentoIrrfDestinacaoJurosVO vo = new RelRecolhimentoIrrfDestinacaoJurosVO();
		
		Integer idInstituicao = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		
		vo.setIdInstituicao(idInstituicao);
		
		//vo.setNumContaCapital(ccaDelegate.obterMaiorContaCapitalPorInstituicao(idInstituicao));
		return vo;
	}
	
	/**
	 * Combo Ordenacao
	 * @return
	 */
	private List<ItemListaVO> criarComboOrdenacao() {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		for(EnumOrdenacaoRecolhimentoIrrfDestinacaoJuros tipo : EnumOrdenacaoRecolhimentoIrrfDestinacaoJuros.values()) {
			lista.add(new ItemListaVO(tipo.getCodigo().toString(), tipo.getDescricao()));
		}
		return lista;
	}
	
	
	/**
	 * Combo Pa
	 * @return
	 * @throws BancoobException 
	 * @throws NumberFormatException 
	 */
	private List<ItemListaVO> criarComboPa() throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		lista.add(new ItemListaVO("-1", "TODOS"));
		
		List<InstituicaoCooperativaSCIDTO> lst = viewSciDelegate.consultarPacPorCooperativa(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		for(InstituicaoCooperativaSCIDTO dto : lst) {
			lista.add(new ItemListaVO(dto.getNumPAC().toString(), StringUtils.leftPad(dto.getNumPAC().toString(), 4, "0") + " - " + dto.getNome()));
		}
		return lista;
	}
}

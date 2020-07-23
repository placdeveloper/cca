/*
 * 
 */
package br.com.sicoob.sisbr.cca.relatorios.servicos;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.comum.negocio.delegates.ContaCapitalComumFabricaDelegates;
import br.com.sicoob.cca.comum.negocio.delegates.ViewInstituicaoDelegate;
import br.com.sicoob.cca.comum.negocio.dto.InstituicaoCooperativaSCIDTO;
import br.com.sicoob.cca.entidades.negocio.enums.EnumOrdenacaoSituacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoPeriodo;
import br.com.sicoob.sisbr.cca.relatorios.RelatoriosContaCapital;
import br.com.sicoob.sisbr.cca.relatorios.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.relatorios.vo.RelSituacaoPeriodoCCARenVO;


/**
 * The Class RelSituacaoPeriodoCCARenServico.
 */
@RemoteService
public class RelSituacaoPeriodoCCARenServico extends RelatoriosContaCapital {
		
	/** The view sci delegate. */
	private ViewInstituicaoDelegate viewSciDelegate  = ContaCapitalComumFabricaDelegates.getInstance().criarViewInstituicaoDelegate();
	
	/**
	 * Obter definicoes.
	 *
	 * @param dto the dto
	 * @return the retorno DTO
	 * @throws BancoobException the bancoob exception
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("comboOrdenacao", criarComboOrdenacao());
		retorno.getDados().put("comboPac", criarComboPac());	
		retorno.getDados().put("comboSituacao", criarComboSituacao());	
		retorno.getDados().put("vo", valorInicialTela());
		
		return retorno;
	}
	

	/**
	 * Valor inicial tela.
	 *
	 * @return the rel situacao matricula CCA ren VO
	 * @throws BancoobException the bancoob exception
	 */
	private RelSituacaoPeriodoCCARenVO valorInicialTela() throws BancoobException {
		RelSituacaoPeriodoCCARenVO vo = new RelSituacaoPeriodoCCARenVO();
		
		Integer idInstituicao = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		LocalDate agora = new LocalDate();
		vo.setDataPeriodoInicial(new DateTimeDB(agora.dayOfMonth().withMinimumValue().toDate().getTime()));
		vo.setDataPeriodoFinal(new DateTimeDB(agora.dayOfMonth().withMaximumValue().toDate().getTime()));
		vo.setIdInstituicao(idInstituicao);
		return vo;
	}
	
	/**
	 * Combo Ordenacao
	 * @return
	 */
	private List<ItemListaVO> criarComboOrdenacao() {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		for(EnumOrdenacaoSituacao tipo : EnumOrdenacaoSituacao.values()) {
			lista.add(new ItemListaVO(tipo.getCodigo().toString(), tipo.getDescricao()));
		}
		return lista;
	}
	
	/**
	 * Criar combo situacao.
	 *
	 * @return the list
	 */
	private List<ItemListaVO> criarComboSituacao(){
		List<ItemListaVO> situacoes = new ArrayList<ItemListaVO>();
		situacoes.add(new ItemListaVO("-1", "TODAS"));
		for(EnumSituacaoPeriodo situacao: EnumSituacaoPeriodo.values()) {
			situacoes.add(new ItemListaVO(situacao.getCodigo().toString(), situacao.getDescricao()));
		}
		
		return situacoes;
	}
	
	/**
	 * Combo Pacs
	 * @return
	 * @throws BancoobException 
	 * @throws NumberFormatException 
	 */
	private List<ItemListaVO> criarComboPac() throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		lista.add(new ItemListaVO("-1", "TODOS"));
		
		List<InstituicaoCooperativaSCIDTO> lst = viewSciDelegate.consultarPacPorCooperativa(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		for(InstituicaoCooperativaSCIDTO dto : lst) {
			lista.add(new ItemListaVO(dto.getNumPAC().toString(), StringUtils.leftPad(dto.getNumPAC().toString(), 4, "0") + " - " + dto.getNome()));
		}
		return lista;
	}
}

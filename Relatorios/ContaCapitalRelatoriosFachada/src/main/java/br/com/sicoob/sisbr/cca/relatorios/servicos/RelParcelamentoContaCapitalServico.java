/*
 * 
 */
package br.com.sicoob.sisbr.cca.relatorios.servicos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.comum.negocio.delegates.ContaCapitalComumFabricaDelegates;
import br.com.sicoob.cca.comum.negocio.delegates.ViewInstituicaoDelegate;
import br.com.sicoob.cca.comum.negocio.dto.InstituicaoCooperativaSCIDTO;
import br.com.sicoob.cca.entidades.negocio.enums.EnumOrdenacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoParcela;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoParcelaNovo;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoParcelamento;
import br.com.sicoob.sisbr.cca.relatorios.RelatoriosContaCapital;
import br.com.sicoob.sisbr.cca.relatorios.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.relatorios.vo.RelParcelamentoContaCapitalVO;

/**
 * Classe de serviço responsável das funções relativas a relatório de parcelamento de conta capital
 */
@RemoteService
public class RelParcelamentoContaCapitalServico extends RelatoriosContaCapital {
	
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

		retorno.getDados().put("comboTipoParcelamento", criarComboTipoParcelamento());
		retorno.getDados().put("comboFormaParcelamento", criarComboFormaParcelamento());
		retorno.getDados().put("comboSituacaoParcela", criarComboSituacaoParcela());
		retorno.getDados().put("comboOrdenacao", criarComboOrdenacao());
		retorno.getDados().put("comboPac", criarComboPac());
		
		retorno.getDados().put("vo", valorInicialTela());
		
		return retorno;
	}
	
	/**
	 * Valor inicial tela.
	 *
	 * @return the rel parcelamento conta capital VO
	 * @throws BancoobException the bancoob exception
	 */
	private RelParcelamentoContaCapitalVO valorInicialTela() throws BancoobException {
		RelParcelamentoContaCapitalVO vo = new RelParcelamentoContaCapitalVO();
		
		Integer idInstituicao = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		
		vo.setIdInstituicao(idInstituicao);
		
		vo.setNumContaCapitalFinal(ccaDelegate.obterMaiorContaCapitalPorInstituicao(idInstituicao));
		vo.setNumContaCapitalInicial(ccaDelegate.obterMenorContaCapitalPorInstituicao(idInstituicao));
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		vo.setDataInicialVencimento(new DateTimeDB(calendar.getTimeInMillis()));
		
		calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
		vo.setDataFinalVencimento(new DateTimeDB(calendar.getTimeInMillis()));
		
		return vo;
	}
	
	/**
	 * Combo Tipo de Parcelamento
	 * @return
	 */
	private List<ItemListaVO> criarComboTipoParcelamento() {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(2);
		for(EnumTipoParcelamento tipo : EnumTipoParcelamento.values()) {
			lista.add(new ItemListaVO(tipo.getCodigo().toString(), tipo.getDescricao()));
		}
		return lista;
	}
	
	/**
	 * Combo Forma de Parcelamento
	 * @return
	 */
	private List<ItemListaVO> criarComboFormaParcelamento() {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		lista.add(new ItemListaVO("-1", "TODAS"));
		lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_MIGRAINCORP.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_MIGRAINCORP.getDescricao()));
		lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getDescricao()));
		lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getDescricao()));
		lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_FOLHA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_FOLHA.getDescricao()));
		lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_MIGRACAO.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_MIGRACAO.getDescricao()));
		lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getDescricao()));
		return lista;
	}
	
	/**
	 * Combo Situacao Parcela
	 * @return
	 */
	private List<ItemListaVO> criarComboSituacaoParcela() {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(3);
		lista.add(new ItemListaVO("-1", "TODAS"));
		for(EnumSituacaoParcelaNovo tipo : EnumSituacaoParcelaNovo.values()) {
			lista.add(new ItemListaVO(tipo.getCodigo().toString(), tipo.getDescricao()));
		}
		
		return lista;
	}
	
	/**
	 * Combo Ordenacao
	 * @return
	 */
	private List<ItemListaVO> criarComboOrdenacao() {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(3);
		for(EnumOrdenacaoParcelamento tipo : EnumOrdenacaoParcelamento.values()) {
			lista.add(new ItemListaVO(tipo.getCodigo().toString(), tipo.getDescricao()));
		}
		return lista;
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

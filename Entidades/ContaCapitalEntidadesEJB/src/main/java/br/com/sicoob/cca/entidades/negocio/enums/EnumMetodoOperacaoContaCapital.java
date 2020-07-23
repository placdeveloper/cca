package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * Metodos dos logs.
 * @author Nairon.Silva
 */
public enum EnumMetodoOperacaoContaCapital {

	OUTROS(1, "Outros"),
	MOVIMENTACAO_SUBSCRICAOCONTACAPITALSERVICO_INCLUIR(2, "br.com.sicoob.cca.movimentacao.negocio.servicos.ejb.SubscricaoContaCapitalServicoEJB.incluir(SubscricaoRenDTO, List<ParcelamentoRenDTO>)"),
	MOVIMENTACAO_DESLIGARCONTACAPITALSERVICO_DESLIGARCONTACAPITAL(3, "br.com.sicoob.cca.movimentacao.negocio.servicos.ejb.DesligarContaCapitalServicoEJB.desligarContaCapital(Integer, Integer, Date)"),
	MOVIMENTACAO_DESLIGARCONTACAPITALSERVICO_DESLIGARENCONTROCONTAS(4, "br.com.sicoob.cca.movimentacao.negocio.servicos.ejb.DesligarContaCapitalServicoEJB.desligarEncontroContas(DesligarContaCapitalDTO, DevolucaoRenDTO, List<ParcelamentoRenDTO>, List<ContratoLiquidacaoDTO>)"),
	MOVIMENTACAO_DEVOLUCAOCONTACAPITALSERVICO_INCLUIR(5, "br.com.sicoob.cca.movimentacao.negocio.servicos.ejb.DevolucaoContaCapitalServicoEJB.incluir(DevolucaoRenDTO, List<ParcelamentoRenDTO>)"),
	MOVIMENTACAO_DEVOLUCAOCONTACAPITALSERVICO_INCLUIRCAPTACAOREMUNERADA(6, "br.com.sicoob.cca.movimentacao.negocio.servicos.ejb.DevolucaoContaCapitalServicoEJB.incluirCaptacaoRemunerada(DevolucaoRenDTO)"),
	MOVIMENTACAO_PARCELAMENTOCONTACAPITALSERVICO_BAIXARPARCELAS(7,"br.com.sicoob.cca.movimentacao.negocio.servicos.ejb.ParcelamentoContaCapitalServicoEJB.cancelarParcelas(List<ParcelamentoRenDTO>)"),
	MOVIMENTACAO_PARCELAMENTOCONTACAPITALSERVICO_CANCELARPARCELAS(8,"br.com.sicoob.cca.movimentacao.negocio.servicos.ejb.ParcelamentoContaCapitalServicoEJB.baixarParcelas(List<ParcelamentoRenDTO>)"),
	FECH_BAIXA_PARCELA_VIA_CCO(9,"FECHAMENTO BAIXAR PARCELA VIA CCO"),
	FECH_BAIXA_PARCELA_DEBITO_INDET(10,"FECHAMENTO BAIXAR PARCELA DEBITO INDETERMINADO");

	private Integer codigo;
	private String descricao;
	
	private EnumMetodoOperacaoContaCapital(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return this.codigo;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}

package br.com.sicoob.cca.entidades.negocio.enums;

/**
* @author Ricardo.Barcante
*/
public enum EnumRelatorios {
	REC_IRRF_DEST_JUROS("CCA001", "Relatório de Destinacao de Juros/Recolhimento IRRF", "CCA_Rel_Destinacao_Juros_Recolhimento_IRRF"),
	PARC_CONTA_CAPITAL("CCA003", "Relatório Parcelamento Conta Capital", "CCA_Rel_Parcelamento"),
	SITUACAO_MATRICULA("CCA005", "Relatório Situacao Matricula Conta Capital", "CCA_Rel_Situacao_Matricula"),
	SALDO_ATUAL("CCA006", "Relatório Saldos Conta Capital", "CCA_Rel_Saldos"),
	SITUACAO_PERIODO("CCA035", "Relatório Situacao Periodo Conta Capital", "CCA_Rel_Situacao_Periodo"),
	IMPEDIMENTOS_DESLIGAMENTO("CCA046", "Relatório Impedimentos de Desligamento", "CCA_Rel_Impedimentos_Desligamento"),
	DEBITO_DETERMINADO("CCA051", "Relatório Debito Indeterminado", "CCA_Rel_Debito_Indeterminado"),
	BLOQUEIOS("CCA052", "Relatório de Bloqueios", "CCA_Rel_Bloqueios"),
	HISTORICO_BLOQUEIO("CCA053", "Relatório de Historico do Bloqueio", "CCA_Rel_Historico_Bloqueio"),
	VALOR_PARAMETRO("CCA054", "Relatório Valor Parametro", "CCA_Rel_Valor_Parametro"),
	FICHA_PROPOSTA_MATRICULA("CCA055", "Relatório Ficha Proposta Matricula", "CCA_Rel_Ficha_Proposta_Matricula"),
	DESLIGAMENTO_ASSOCIADO("CCA056", "Relatório Desligamento de Associado", "CCA_Rel_Desligamento_Associado"),
	SOL_DEVOLUCAO_CAPITAL("CCA057", "Relatório Solicitacao de Devolucao de Capital", "CCA_Rel_Solicitacao_Dev_Capital"),
	RECIBO_TRANSF_CAPITAL("CCA058", "Relatório Recibo de Transferencia de Capital", "CCA_Rel_Recibo_Transf_Capital"),
	BATIMENTO_SQL_DB2("CCA059", "Relatório Batimento SQL x DB2", "CCA_Rel_Batimento_SQL_DB2"),
	PARTI_INDIRETA_SINGULAR("CCA060", "Relatório Participacao Indireta Singular", "CCA_Rel_Participacao_Indireta_Singular"),
	DESL_ENCONTRO_CONTAS("CCA061", "Relatório Desligamento Encontro Contas", "CCA_Rel_Desligamento_Encontro_Contas"),
	DESL_ENCONTRO_CONTAS_LISTA("CCA067", "Relatório Desligamento Encontro Contas Lista", "CCA_Rel_Desliga_Encontro_Contas_Lista"),
	POSICAO_DIARIA_CARTEIRA("CCA018", "Relatório de Posição Diária da Carteira", "CCA_Rel_Posicao_Diaria_Carteira"),
	RESUMO_LANCAMENTOS("CCA019", "Relatorio de Resumo de Lançamentos", "CCA_Relatorio_Resumo_Lancamentos"),
	LANCAMENTOS_CONTABIL("CCA020", "Relatório de Lançamentos para Contabilidade", "CCA_Rel_Lancamentos_Contabil"),
	LANCAMENTOS_NAO_CONTABILIZADOS_SINTETICO("CCA017", "Relatorio de Lançamentos Não Contabilizados Sintético", "CCA_Relatorio_Lancamentos_Sintetico"),
	LANCAMENTOS_NAO_CONTABILIZADOS_ANALITICO("CCA016", "Relatorio de Lançamentos Não Contabilizados Analítico", "CCA_Relatorio_Lancamentos_Analitico"),
	RELATORIO_CONCILIACAO_CONTABIL("CCA021", "Relatorio de Conciliação Contábil", "CCA_Relatorio_Conciliacao_Contabil");

	private String codigo;
	private String descricao;
	private String nomeRelatorio;

	private EnumRelatorios(String codigo, String descricao, String nomeRelatorio) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.nomeRelatorio = nomeRelatorio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeRelatorio() {
		return nomeRelatorio;
	}

	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}
}
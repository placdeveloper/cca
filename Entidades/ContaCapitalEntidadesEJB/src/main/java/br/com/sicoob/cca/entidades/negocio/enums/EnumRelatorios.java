package br.com.sicoob.cca.entidades.negocio.enums;

/**
* @author Ricardo.Barcante
*/
public enum EnumRelatorios {
	REC_IRRF_DEST_JUROS("CCA001", "Relat�rio de Destinacao de Juros/Recolhimento IRRF", "CCA_Rel_Destinacao_Juros_Recolhimento_IRRF"),
	PARC_CONTA_CAPITAL("CCA003", "Relat�rio Parcelamento Conta Capital", "CCA_Rel_Parcelamento"),
	SITUACAO_MATRICULA("CCA005", "Relat�rio Situacao Matricula Conta Capital", "CCA_Rel_Situacao_Matricula"),
	SALDO_ATUAL("CCA006", "Relat�rio Saldos Conta Capital", "CCA_Rel_Saldos"),
	SITUACAO_PERIODO("CCA035", "Relat�rio Situacao Periodo Conta Capital", "CCA_Rel_Situacao_Periodo"),
	IMPEDIMENTOS_DESLIGAMENTO("CCA046", "Relat�rio Impedimentos de Desligamento", "CCA_Rel_Impedimentos_Desligamento"),
	DEBITO_DETERMINADO("CCA051", "Relat�rio Debito Indeterminado", "CCA_Rel_Debito_Indeterminado"),
	BLOQUEIOS("CCA052", "Relat�rio de Bloqueios", "CCA_Rel_Bloqueios"),
	HISTORICO_BLOQUEIO("CCA053", "Relat�rio de Historico do Bloqueio", "CCA_Rel_Historico_Bloqueio"),
	VALOR_PARAMETRO("CCA054", "Relat�rio Valor Parametro", "CCA_Rel_Valor_Parametro"),
	FICHA_PROPOSTA_MATRICULA("CCA055", "Relat�rio Ficha Proposta Matricula", "CCA_Rel_Ficha_Proposta_Matricula"),
	DESLIGAMENTO_ASSOCIADO("CCA056", "Relat�rio Desligamento de Associado", "CCA_Rel_Desligamento_Associado"),
	SOL_DEVOLUCAO_CAPITAL("CCA057", "Relat�rio Solicitacao de Devolucao de Capital", "CCA_Rel_Solicitacao_Dev_Capital"),
	RECIBO_TRANSF_CAPITAL("CCA058", "Relat�rio Recibo de Transferencia de Capital", "CCA_Rel_Recibo_Transf_Capital"),
	BATIMENTO_SQL_DB2("CCA059", "Relat�rio Batimento SQL x DB2", "CCA_Rel_Batimento_SQL_DB2"),
	PARTI_INDIRETA_SINGULAR("CCA060", "Relat�rio Participacao Indireta Singular", "CCA_Rel_Participacao_Indireta_Singular"),
	DESL_ENCONTRO_CONTAS("CCA061", "Relat�rio Desligamento Encontro Contas", "CCA_Rel_Desligamento_Encontro_Contas"),
	DESL_ENCONTRO_CONTAS_LISTA("CCA067", "Relat�rio Desligamento Encontro Contas Lista", "CCA_Rel_Desliga_Encontro_Contas_Lista"),
	POSICAO_DIARIA_CARTEIRA("CCA018", "Relat�rio de Posi��o Di�ria da Carteira", "CCA_Rel_Posicao_Diaria_Carteira"),
	RESUMO_LANCAMENTOS("CCA019", "Relatorio de Resumo de Lan�amentos", "CCA_Relatorio_Resumo_Lancamentos"),
	LANCAMENTOS_CONTABIL("CCA020", "Relat�rio de Lan�amentos para Contabilidade", "CCA_Rel_Lancamentos_Contabil"),
	LANCAMENTOS_NAO_CONTABILIZADOS_SINTETICO("CCA017", "Relatorio de Lan�amentos N�o Contabilizados Sint�tico", "CCA_Relatorio_Lancamentos_Sintetico"),
	LANCAMENTOS_NAO_CONTABILIZADOS_ANALITICO("CCA016", "Relatorio de Lan�amentos N�o Contabilizados Anal�tico", "CCA_Relatorio_Lancamentos_Analitico"),
	RELATORIO_CONCILIACAO_CONTABIL("CCA021", "Relatorio de Concilia��o Cont�bil", "CCA_Relatorio_Conciliacao_Contabil");

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
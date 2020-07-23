/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.constantes;

/**
 * Constantes
 */
public final class ReplicacaoLegadoConstantes {
	
	private ReplicacaoLegadoConstantes(){
		
	}

	public static final Integer COOP_BDSICOOBINTEGRACAO = 0;	
	public static final Integer COOP_BDSICOOBPRODLAB = 9162;	
	public static final Integer IDTIPOTABELAREPLICACAO_CONTACAPITAL = 1;
	public static final Integer IDTIPOTABELAREPLICACAO_PARCELAMENTOCCA = 2;
	public static final Integer IDTIPOTABELAREPLICACAO_LANCAMENTOSCCAPITAL = 3;
		
	public static final int  QTDE_MIN_REGISTRO_ALTERADO = 1;
	public static final int  POS_INI_CHAVESQL = 0;
	
	public static final Integer PARAM_QTD_REG_REPLICACAO = 1;
	public static final Integer PARAM_SUSPENDER_REPLICACAO = 2;
	public static final Integer PARAM_DELAY_REPLICACAO = 3;
	public static final Integer PARAM_COOP_PILOTO_REPLICACAO = 4;
	public static final Integer PARAM_REPLICACAO_INICIADA = 5;
	public static final Integer PARAM_LISTA_EMAIL_DEST = 6;
	public static final Integer PARAM_REMETENTE_EMAIL = 7;	
	public static final Integer PARAM_SUSPENDER_EXPURGO = 8;	
	public static final Integer PARAM_QTD_DIAS_NAO_EXPURGAR_REPLICACAO = 9;
	
	public static final Integer PARAM_SUSPENDER_EXPURGO_OPERACAO_CCA = 10;	
	public static final Integer PARAM_QTD_DIAS_NAO_EXPURGAR_OPERACAO_CCA = 11;

	public static final Integer VALOR_DEFAULT_QTD_REG_REPLICACAO = 900;
	public static final Integer VALOR_DEFAULT_DELAY_REPLICACAO = 0;	
	
	public static final Integer SITUACAO_AGUARDANDO_REPLICACAO = 0;
	public static final Integer SITUACAO_REPLICACAO_COM_SUCESSO = 1;
	public static final Integer SITUACAO_ERRO_NA_REPLICACAO = 2;
	public static final Integer SITUACAO_OBJETO_NAO_ENCONTRADO_PARA_REPLICACAO = 3;
	public static final Integer SITUACAO_OBJETO_CHAVE_REPLICACAO_INVALIDO = 4;
	public static final Integer SITUACAO_OBJETO_INVALIDADO_MANUALMENTE	 = 5;
	
	
}

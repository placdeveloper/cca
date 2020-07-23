/*
 * 
 */
package br.com.sicoob.sisbr.cca.processamento.negocio.constantes;

/**
 * Constantes
 */
public final class ProcessamentoReplicacaoConstantes {
	
	private ProcessamentoReplicacaoConstantes(){
		
	}	

	public static final Integer TAM_MIN_MENSAGEM_ERRO = 0; 	
	public static final Integer TAM_MAX_MENSAGEM_ERRO = 999;
	public static final Integer TAM_CHAVE_CONTACAPITAL = 1;
	public static final Integer TAM_CHAVE_PARCELAMENTOCCA = 4;
	public static final Integer TAM_CHAVE_LANCAMENTOSCCAPITAL = 3;

	public static final Integer IDTIPOTABELAREPLICACAO_CONTACAPITAL = 1;
	public static final Integer IDTIPOTABELAREPLICACAO_PARCELAMENTOCCA = 2;
	public static final Integer IDTIPOTABELAREPLICACAO_LANCAMENTOSCCAPITAL = 3;

	public static final String TIPO_ACAO_INSERT = "I"; 
	public static final String TIPO_ACAO_UPDATE = "U";
	public static final String TIPO_ACAO_DELETE = "D"; 
			
	public static final Integer IDSITUACAOREPLICACAO_AGUARDANDO_REPLICACAO = 0;
	public static final Integer IDSITUACAOREPLICACAO_REPLICACAO_COM_SUCESSO = 1;
	public static final Integer IDSITUACAOREPLICACAO_ERRO_NA_REPLICACAO = 2;
	public static final Integer IDSITUACAOREPLICACAO_OBJETO_NAO_ENC_PARA_REPLICACAO = 3;
	public static final Integer IDSITUACAOREPLICACAO_OBJETO_INVALIDO_PARA_REPLICACAO = 4;	
	
	public static final int QTDE_MIN_REGISTRO_ALTERADO = 1;
	public static final int TAM_MIN_COD_ACAO = 1;	

	public static final String MSG_ASSUNTO_ERRO = "ERRO - Replicação Conta Capital";
	
	
}

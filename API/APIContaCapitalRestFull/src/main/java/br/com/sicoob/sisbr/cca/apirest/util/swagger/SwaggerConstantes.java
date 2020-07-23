package br.com.sicoob.sisbr.cca.apirest.util.swagger;

/**
 * Classe para centralizar as mensagens / textos utilizados no swagger
 * @author Marcos.Balbi
 *
 */
public class SwaggerConstantes {

	//Descricao das mensagens do Recurso de Integralizações
	public static final String TAG_INTEGRALIZACOES = "Integralizações";

	//Integralizar
	public static final String INTEGRALIZACOES_INTEGRALIZAR_DESCRICAO = "Realiza o(s) lançamento(s) de subscrição/integralização.";
	public static final String INTEGRALIZACOES_INTEGRALIZAR_NOTA = "Realiza o(s) lançamento(s) de subscrição/integralização e retorna lista com as chaves de integralização.";
	
	//VO Integralizar
	public static final String INTEGRALIZAR_VO_DESCRICAO = "Dados da integralização.";
	public static final String INTEGRALIZAR_VO_VALOR = "Integralizacao";
	
	public static final String INTEGRALIZAR_VO_ATRIB_NUM_CCA = "O número da conta capital.";
	public static final String INTEGRALIZAR_VO_ATRIB_NUM_CCA_EXEMPLO = "154";
	public static final String INTEGRALIZAR_VO_ATRIB_ID_INST = "O número da instituição no SCI.";
	public static final String INTEGRALIZAR_VO_ATRIB_ID_INST_EXEMPLO = "29";	
	public static final String INTEGRALIZAR_VO_ATRIB_VALOR_SUBSC = "O valor da subcrição de capital a ser realizada.";
	public static final String INTEGRALIZAR_VO_ATRIB_VALOR_SUBSC_EXEMPLO = "100.00";
	public static final String INTEGRALIZAR_VO_ATRIB_VALOR_INTEG = "O valor da integralização de capital a ser realizada.";
	public static final String INTEGRALIZAR_VO_ATRIB_VALOR_INTEG_EXEMPLO = "50.00";
	public static final String INTEGRALIZAR_VO_ATRIB_ID_TIPOHISTORICO = "O identificador do histórico de integralização a ser lançado.";
	public static final String INTEGRALIZAR_VO_ATRIB_ID_TIPOHISTORICO_EXEMPLO = "138";	
	public static final String INTEGRALIZAR_VO_ATRIB_COD_OP = "O código identificador do rementente da operação.";
	public static final String INTEGRALIZAR_VO_ATRIB_COD_OP_EXEMPLO = "FAP - 154";
	public static final String INTEGRALIZAR_VO_ATRIB_NUM_CCO = "O número da conta corrente para os casos de integralização via cco.";
	public static final String INTEGRALIZAR_VO_ATRIB_NUM_CCO_EXEMPLO = "1996";	
	public static final String INTEGRALIZAR_VO_ATRIB_QTDE_PARC = "Quantidade de parcelas para o saldo residual de capital.(Casos em que valor subscrito > valor integralizado).";
	public static final String INTEGRALIZAR_VO_ATRIB_QTDE_PARC_EXEMPLO = "2";	
	public static final String INTEGRALIZAR_VO_ATRIB_DT_PARC = "Data de início do parcelamento (Casos em que valor subscrito > valor integralizado).";
	public static final String INTEGRALIZAR_VO_ATRIB_DT_PARC_EXEMPLO = "2019-08-01";	

	//VO Retorno Integralizar
	public static final String INTEGRALIZAR_RESP_VO_VALOR = "Lancamento";	
	public static final String INTEGRALIZAR_RESP_VO_DESCRICAO = "Dados do lançamento.";
	
	public static final String INTEGRALIZAR_RESP_VO_ATRIB_ID_INST = "O número da instituição no SCI.";
	public static final String INTEGRALIZAR_RESP_VO_ATRIB_ID_INST_EXEMPLO = "29";	
	public static final String INTEGRALIZAR_RESP_VO_ATRIB_DT_LOTE = "Data do lote.";
	public static final String INTEGRALIZAR_RESP_VO_ATRIB_DT_LOTE_EXEMPLO = "2019-08-01";	
	public static final String INTEGRALIZAR_RESP_VO_ATRIB_NUM_LOTE = "Número do lote.";
	public static final String INTEGRALIZAR_RESP_VO_ATRIB_NUM_LOTE_EXEMPLO = "9000";
	public static final String INTEGRALIZAR_RESP_VO_ATRIB_NUM_LANCAMENTO = "Número do lançamento.";
	public static final String INTEGRALIZAR_RESP_VO_ATRIB_NUM_LANCAMENTO_EXEMPLO = "1";		
	
	//Descricao das respostas de retorno
	public static final String RESPOSTA_INTEGRALIZACOES_INTEGRALIZAR_VALOR = "RespostaIntegralizacao";
	public static final String RESPOSTA_INTEGRALIZACOES_INTEGRALIZAR_DESCRICAO = "Modelo de retorno referente as integralizações realizadas.";	

	
	
	
	//Descricao dos codigos de retorno
	//SUCESSO
	public static final String OPERACAO_CODE_200 = "Operacao realizada com sucesso.";
	public static final String OPERACAO_CODE_201 = "Recurso criado com sucesso.";	

	//ERRO CLIENTE
	public static final String OPERACAO_CODE_400 = "Erros negociais e falhas de validação.";
	public static final String OPERACAO_CODE_404 = "Nenhum registro encontrado.";

	//ERRO SERVIDOR
	public static final String OPERACAO_CODE_500 = "Erro interno.";	
	
}

/*
 * 
 */
package br.com.sicoob.cca.comum.constantes;

/**
 * Constantes conta capital
 */
public final class ContaCapitalConstantes {

	/**
	 * Construtor
	 */
	private ContaCapitalConstantes() {
		
	}
	
	/**
	 * Mensagem padrao para quando ocorre erro ao manipular dados no DB2 e SQL Server na mesma transacao
	 */
	public static final String ERRO_TRANSACAO_DB2_SQLSERVER = "MSG_ERRO_001_TRAN_DB2_SQL";
	
	/** A constante NUM_COOPERATIVA_BANCOOB. */
	public static final Integer NUM_COOPERATIVA_BANCOOB = 1;
	
	/** A constante PRODUTO_CORPORATIVO. */
	public static final Integer PRODUTO_CORPORATIVO = 0;
	
	/** A constante PRODUTO_CLIENTE. */
	public static final Integer PRODUTO_CLIENTE = 1;
	
	/** A constante PRODUTO_CONTA_CAPITAL. */
	public static final Integer PRODUTO_CONTA_CAPITAL = 2;
	
	/** A constante PRODUTO_CONTA_CORRENTE. */
	public static final Integer PRODUTO_CONTA_CORRENTE = 3;   
	
	/** A constante PRODUTO_CAPTACAO_REMUNERADA. */
	public static final Integer PRODUTO_CAPTACAO_REMUNERADA = 4;
	
	/** A constante PRODUTO_RATEIO. */
	public static final Integer PRODUTO_RATEIO = 5;   
	
	//SITUACAO DO COOPERADO
	/** A constante COD_SITUACAO_COOPERADO_ATIVO. */
	public static final Integer COD_SITUACAO_COOPERADO_ATIVO = 1;
	
	/** A constante COD_SITUACAO_COOPERADO_DEMITIDO. */
	public static final Integer COD_SITUACAO_COOPERADO_DEMITIDO = 2;
	
	/** A constante COD_SITUACAO_COOPERADO_EXCLUIDO. */
	public static final Integer COD_SITUACAO_COOPERADO_EXCLUIDO = 3;
	
	/** A constante COD_SITUACAO_COOPERADO_ELIMINADO. */
	public static final Integer COD_SITUACAO_COOPERADO_ELIMINADO = 4;
	
	/** A constante COD_SITUACAO_EM_ABERTO. */
	public static final Integer COD_SITUACAO_EM_ABERTO = 1;
	
	/** A constante COD_SITUACAO_EFETIVADO. */
	public static final Integer COD_SITUACAO_EFETIVADO = 9;
		
	//Listas
	/** A constante LST_MOTIVOS_DEVOLUCAO_CAPITAL. */
	public static final Integer LST_MOTIVOS_DEVOLUCAO_CAPITAL = 1047;
	
	/** A constante LST_FORMA_DEBITO_INTEGRALIZACAO. */
	public static final Integer LST_FORMA_DEBITO_INTEGRALIZACAO = 252;
	
	/** A constante LST_GRUPO_HISTORICO_CCAPITAL. */
	public static final Integer LST_GRUPO_HISTORICO_CCAPITAL = 105;
	
	/** A constante LST_SIT_RETORNO_FOLHA. */
	public static final Integer LST_SIT_RETORNO_FOLHA = 878;
	
	//GRUPO DE HISTORICO PARA CONTA CAPITAL
	/** A constante COD_GRUPO_HIST_SUBSCRICAO. */
	public static final Integer COD_GRUPO_HIST_SUBSCRICAO = 1;
	
	/** A constante COD_GRUPO_HIST_INTEGRALIZACAO. */
	public static final Integer COD_GRUPO_HIST_INTEGRALIZACAO = 2;
	
	/** A constante COD_GRUPO_HIST_DEVOLUCAO. */
	public static final Integer COD_GRUPO_HIST_DEVOLUCAO = 3;
	
	/** A constante COD_GRUPO_HIST_VALORES_DIVERSOS. */
	public static final Integer COD_GRUPO_HIST_VALORES_DIVERSOS = 4;
	
	/** A constante COD_GRUPO_HIST_NAO_SENSIBILIZA. */
	public static final Integer COD_GRUPO_HIST_NAO_SENSIBILIZA = 9;
	
	/** A constante COD_MODO_LANCAMENTO_VIA_CAIXA. */
	public static final Integer COD_MODO_LANCAMENTO_VIA_CAIXA = 1;
	
	/** A constante COD_MODO_LANCAMENTO_VIA_CONTA. */
	public static final Integer COD_MODO_LANCAMENTO_VIA_CONTA = 2;
	
	/** A constante COD_MODO_LANCAMENTO_VIA_FOLHA. */
	public static final Integer COD_MODO_LANCAMENTO_VIA_FOLHA = 3;
	
	/** A constante COD_MODO_LANCAMENTO_VIA_COBRANCA. */
	public static final Integer COD_MODO_LANCAMENTO_VIA_COBRANCA = 4;
	
	/** A constante COD_MODO_LANCAMENTO_VIA_MIGRACAO. */
	public static final Integer COD_MODO_LANCAMENTO_VIA_MIGRACAO = 5;
	
	/** A constante COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN. */
	public static final Integer COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN = 6;
	
	/** A constante COD_MODO_LANCAMENTO_VIA_RATEIO. */
	public static final Integer COD_MODO_LANCAMENTO_VIA_RATEIO = 7;
	
	/** A constante COD_MODO_LANCAMENTO_VIA_RESERVA. */
	public static final Integer COD_MODO_LANCAMENTO_VIA_RESERVA = 8;
	
	/** A constante COD_MODO_LANCAMENTO_VIA_ESTORNO_DEVOLUCAO. */
	public static final Integer COD_MODO_LANCAMENTO_VIA_ESTORNO_DEVOLUCAO = 9;

	/** A constante COD_MODO_LANCAMENTO_VIA_ESTORNO_DEVOLUCAO. */
	public static final Integer COD_MODO_LANCAMENTO_VIA_CAPTACAO_REMUNERADA = 10;
	
	//LISTA - SITUACAO DA PARCELA
	/** A constante COD_PARCELA_GERADA. */
	public static final Integer COD_PARCELA_GERADA = 1;
	
	/** A constante COD_PARCELA_PAGA_VIA_CAIXA. */
	public static final Integer COD_PARCELA_PAGA_VIA_CAIXA = 2;
	
	/** A constante COD_PARCELA_PAGA_VIA_CONTA. */
	public static final Integer COD_PARCELA_PAGA_VIA_CONTA = 3;
	
	/** A constante COD_PARCELA_CANCELADA. */
	public static final Integer COD_PARCELA_CANCELADA = 4;
	
	/** A constante COD_PARCELA_PAGA_VIA_CHADMIN. */
	public static final Integer COD_PARCELA_PAGA_VIA_CHADMIN = 5;
	
	/** A constante COD_PARCELA_EXCLUIDA. */
	public static final Integer COD_PARCELA_EXCLUIDA = 6;
	
	/** A constante COD_PARCELA_PAGA_VIA_FOLHA. */
	public static final Integer COD_PARCELA_PAGA_VIA_FOLHA = 7;
	
	/** A constante COD_PARCELA_PAGA_VIA_COBRANCA. */
	public static final Integer COD_PARCELA_PAGA_VIA_COBRANCA = 8;
	
	/** A constante COD_PARCELA_PAGA_ANTES_MIGRACAO. */
	public static final Integer COD_PARCELA_PAGA_ANTES_MIGRACAO = 9;
	
	/** A constante COD_PARCELA_PAGA_VIA_RATEIO. */
	public static final Integer COD_PARCELA_PAGA_VIA_RATEIO = 10;
	
	/** A constante COD_PARCELA_PAGA_VIA_RESERVA. */
	public static final Integer COD_PARCELA_PAGA_VIA_RESERVA = 11;
	
	/** A constante COD_PARCELA_AGUARDA_RETORNO. */
	public static final Integer COD_PARCELA_AGUARDA_RETORNO = 12;
	
	//Tipos de Pessoa
	/** A constante COD_TIPO_PES_FISICA. */
	public static final Integer COD_TIPO_PES_FISICA = 0;
	
	/** A constante COD_TIPO_PES_JURIDICA. */
	public static final Integer COD_TIPO_PES_JURIDICA = 1;
	
	/** A constante COD_TIPO_AMBAS. */
	public static final Integer COD_TIPO_AMBAS = 2;   
	
	//Status do Cooperado
	/** A constante COOPERADO_ATIVO. */
	public static final Integer COOPERADO_ATIVO = 0;
	
	/** A constante COOPERADO_DESLIGADO. */
	public static final Integer COOPERADO_DESLIGADO = 1;
	
	/** A constante NAO_COOPERADO. */
	public static final Integer NAO_COOPERADO = 2;
	
	//Tipos de Parcelamento em Conta Capital
	/** A constante COD_TIPO_PARCELAMENTO_INTEGRAL. */
	public static final Integer COD_TIPO_PARCELAMENTO_INTEGRAL = 1;
	
	/** A constante COD_TIPO_PARCELAMENTO_DEVOLUCAO. */
	public static final Integer COD_TIPO_PARCELAMENTO_DEVOLUCAO = 2;

	// Parâmetros
	/** A constante PAR_CCA_REATIVA_COOP_NOVA_MATRICULA. */
	public static final Integer PAR_CCA_REATIVA_COOP_NOVA_MATRICULA = 1250;
	
	/** A constante PAR_TX_ANUAL_MAX_PAG_JUROS_CAPITAL. */
	public static final Integer PAR_TX_ANUAL_MAX_PAG_JUROS_CAPITAL = 624;
	
	/** A constante PAR_MAXIMO_PARCELAS_INTEGRAL_CAPITAL. */
	public static final Integer PAR_MAXIMO_PARCELAS_INTEGRAL_CAPITAL = 54;
	
	/** A constante PAR_MAXIMO_PARCELAS_DEVOL_CAPITAL. */
	public static final Integer PAR_MAXIMO_PARCELAS_DEVOL_CAPITAL = 102;
	
	/** A constante PAR_PERMITE_ALTERAR_MATRICULA. */
	public static final Integer PAR_PERMITE_ALTERAR_MATRICULA=771;
	
	/** A constante PAR_VALOR_MAX_LANCAMENTO_CCO. */
	public static final Integer PAR_VALOR_MAX_LANCAMENTO_CCO = 1199;
	
	/** A constante PAR_UTILIZA_LIMITE_CHQ_ESPECIAL. */
	public static final Integer PAR_UTILIZA_LIMITE_CHQ_ESPECIAL=518;
	
	/** A constante PAR_NUM_CONTA_DA_COOPERATIVA_ATUAL. */
	public static final Integer PAR_NUM_CONTA_DA_COOPERATIVA_ATUAL=74;
	
	/** A constante PAR_DATA_EXCLUSAO_RATEIO. */
	public static final Integer PAR_DATA_EXCLUSAO_RATEIO = 896;
	
	/** A constante PAR_PROCESSO_FECHREMNOITE. */
	public static final Integer PAR_PROCESSO_FECHREMNOITE = 1080;
	
	/** A constante DATA_INICIO_EXERCICIO_REMUNERADO. */
	public static final String DATA_INICIO_EXERCICIO_REMUNERADO = "01/12/";	
	
	/** A constante NUM_MAX_APLICACAO_CAP_REM. */
	public static final Integer NUM_MAX_APLICACAO_CAP_REM = 12;
	
	//Histórico
	/** A constante PAR_HIST_CCA_BAIXA_SUBSC. */
	public static final Integer PAR_HIST_CCA_BAIXA_SUBSC = 112;
	
	/** A constante PAR_HIST_CCA_BAIXA_INTEGR. */
	public static final Integer PAR_HIST_CCA_BAIXA_INTEGR = 113;
	
	/** A constante PAR_HIST_CCA_TRANSF_RESTITUIR. */
	public static final Integer PAR_HIST_CCA_TRANSF_RESTITUIR = 114;
	
	/** A constante PAR_HIST_CCA_TRANSF_DEB_COOP_ATIVO. */
	public static final Integer PAR_HIST_CCA_TRANSF_DEB_COOP_ATIVO = 115;
	
	/** A constante PAR_HIST_CCA_TRANSF_CRED_COOP_ATIVO. */
	public static final Integer PAR_HIST_CCA_TRANSF_CRED_COOP_ATIVO = 116;
	
	//Tipo Operação
	/** A constante COD_INCLUIR_COOPERADO. */
	public static final Integer COD_INCLUIR_COOPERADO = 0;
	
	/** A constante COD_NOVA_SUBSCRICAO. */
	public static final Integer COD_NOVA_SUBSCRICAO=1;
	
	/** A constante COD_REATIVAR_COOPERADO. */
	public static final Integer COD_REATIVAR_COOPERADO=2;
	
	/** A constante COD_PROPOSTA_CADASTRO. */
	public static final Integer COD_PROPOSTA_CADASTRO=3;
	
	//CONSTANTES PARA HISTÓRICOS DE CONTA CAPITAL
	/** A constante COD_HISTORICO_CCA_SUBSCRICAO. */
	public static final Integer COD_HISTORICO_CCA_SUBSCRICAO = 1;
	
	/** A constante COD_HISTORICO_CCA_BAIXA_SUBSCRICAO. */
	public static final Integer COD_HISTORICO_CCA_BAIXA_SUBSCRICAO = 2;  
	
	/** A constante COD_HISTORICO_CCA_SUBSC_IMPLANTACAO. */
	public static final Integer COD_HISTORICO_CCA_SUBSC_IMPLANTACAO = 6;  
	
	/** A constante COD_HISTORICO_CCA_CANCEL_SUBSCRICAO. */
	public static final Integer COD_HISTORICO_CCA_CANCEL_SUBSCRICAO = 7;   
	
	/** A constante COD_HISTORICO_CCA_INTEG_CAIXA. */
	public static final Integer COD_HISTORICO_CCA_INTEG_CAIXA = 100;
	
	public static final Integer COD_HISTORICO_CCA_TRANSF_BAIXA_INTEG = 105;
	
	/** A constante COD_HISTORICO_CCA_DEVOLUCAO_VIA_CC. */
	public static final Integer COD_HISTORICO_CCA_DEVOLUCAO_VIA_CC = 204;  
	
	/** A constante COD_HISTORICO_CCA_DEVOLUCAO_VIA_CAP_REM. */
	public static final Integer COD_HISTORICO_CCA_DEVOLUCAO_VIA_CAP_REM = 205;  	
	
	/**
	 * TRANSF. INTEGRALIZAÇÃO P/ A RESTITUIR
	 */
	public static final Integer COD_HISTORICO_CCA_TRANSF_A_RESTITUIR = 101; 
	
	/** A constante COD_HISTORICO_CCA_TRANSFERENCIA_INTEG. */
	public static final Integer COD_HISTORICO_CCA_TRANSFERENCIA_INTEG = 106;
	
	/** A constante COD_INTEGRALIZACAO_CANC_PARCELA_DEVOLUCAO. */
	public static final Integer COD_INTEGRALIZACAO_CANC_PARCELA_DEVOLUCAO = 110;

	/** A constante COD_HISTORICO_CCA_INTEG_BANCO. */
	public static final Integer COD_HISTORICO_CCA_INTEG_BANCO = 130;        
	
	/** A constante COD_HISTORICO_CCA_INTEG_FOLHA. */
	public static final Integer COD_HISTORICO_CCA_INTEG_FOLHA = 131;

	/** A constante INTEGRALIZACAO_VIA_OUTROS_BANCOS. */
	public static final Integer INTEGRALIZACAO_VIA_OUTROS_BANCOS = 137;        
	
	/**
	 * TRANSF. A RESTITUIR DE INTEGRALIZAÇÃO
	 */
	public static final Integer COD_HISTORICO_CCA_TRANSF_RESTITUIR_INTEG = 201;
	
	/** A constante COD_HISTORICO_CCA_DEVOLUCAO_CAIXA. */
	public static final Integer COD_HISTORICO_CCA_DEVOLUCAO_CAIXA = 202;    
	
	/** A constante COD_HISTORICO_CCA_DEVOLUCAO_CHQ_ADM. */
	public static final Integer COD_HISTORICO_CCA_DEVOLUCAO_CHQ_ADM = 203;  
	
	public static final Integer COD_HISTORICO_CCA_DEVOLUCAO_BAIXA_EMPRESTIMO = 206;
	
	/** A constante COD_HISTORICO_CCA_RATEIO_COOP_DESLIG. */
	public static final Integer COD_HISTORICO_CCA_RATEIO_COOP_DESLIG = 208;
	
	/** A constante CANCELAMENTO_PARCELA_DEVOLUCAO. */
	public static final Integer CANCELAMENTO_PARCELA_DEVOLUCAO = 210;

	/** A constante COD_HISTORICO_CCA_DEVOL_ESTORNO. */
	public static final Integer COD_HISTORICO_CCA_DEVOL_ESTORNO = 212;      

	//CONSTANTES PARA DESCRIÇÃO DO DOCUMENTO DE LANCAMENTOS PARA CONTA CAPITAL
	/** A constante COD_DESC_DOCUMENTO_INC_COOP. */
	public static final String COD_DESC_DOCUMENTO_INC_COOP = "INC.C.";       
	
	/** A constante COD_DESC_DOCUMENTO_REATIVACAO. */
	public static final String COD_DESC_DOCUMENTO_REATIVACAO = "REATI.";     
	
	/** A constante COD_DESC_DOCUMENTO_SUBSCRICAO. */
	public static final String COD_DESC_DOCUMENTO_SUBSCRICAO = "SUBSC.";     
	
	/** A constante COD_DESC_DOCUMENTO_DESLIGAMENTO. */
	public static final String COD_DESC_DOCUMENTO_DESLIGAMENTO = "DESLI.";   
	
	/** A constante COD_DESC_DOCUMENTO_DEV_CAPITAL_ATIVO. */
	public static final String COD_DESC_DOCUMENTO_DEV_CAPITAL_ATIVO = "DEV.A."; 
	
	/** A constante COD_DESC_DOCUMENTO_DEV_CAPITAL_INATIVO. */
	public static final String COD_DESC_DOCUMENTO_DEV_CAPITAL_INATIVO = "DEV.I.";


	/** A constante COD_DESC_DOCUMENTO_IMPORT_FOLHA. */
	public static final String COD_DESC_DOCUMENTO_IMPORT_FOLHA = "IMP.F.";   
	
	/** A constante COD_DESC_DOCUMENTO_IMPORT_RESERVA. */
	public static final String COD_DESC_DOCUMENTO_IMPORT_RESERVA = "IMP.R."; 
	
	/** A constante COD_DESC_DOCUMENTO_IMPORT_CCO. */
	public static final String COD_DESC_DOCUMENTO_IMPORT_CCO = "IMP.C.";     
	
	/** A constante COD_DESC_DOCUMENTO_IMPORT_BANCO. */
	public static final String COD_DESC_DOCUMENTO_IMPORT_BANCO = "IMP.B.";   
	
	/** A constante COD_DESC_DOCUMENTO_IMPORT_RATEIO. */
	public static final String COD_DESC_DOCUMENTO_IMPORT_RATEIO = "IMP.T."; 
	
	/** A constante COD_LOTE_CCA_PARC_AVISTA. */
	public static final Integer COD_LOTE_CCA_PARC_AVISTA = 9000;
	
	/** A constante COD_LOTE_CCA_PARCELAS. */
	public static final Integer COD_LOTE_CCA_PARCELAS = 9001;
	
	/** A constante COD_LOTE_CCA_DEST_RATEIO. */
	public static final Integer COD_LOTE_CCA_DEST_RATEIO = 9003;
	
	/** A constante COD_LOTE_CCA_TRANSFERENCIA. */
	public static final Integer COD_LOTE_CCA_TRANSFERENCIA = 9004;
	
	/** A constante COD_LOTE_CCA_ARQUIVO. */
	public static final Integer COD_LOTE_CCA_ARQUIVO = 9006;
	
	/** A constante COD_LOTE_CCA_MIGRACAO. */
	public static final Integer COD_LOTE_CCA_MIGRACAO = 9007;
	
	/** A constante COD_LOTE_CCA_CAPITAL_CONSIGNADO. */
	public static final Integer COD_LOTE_CCA_CAPITAL_CONSIGNADO = 9009;
	
	/** A constante COD_LOTE_CCA_CAPITAL_OUTROS_BANCOS. */
	public static final Integer COD_LOTE_CCA_CAPITAL_OUTROS_BANCOS = 9013;
	//EVENTO
	/** A constante COD_OPERACAO_INCLUIR_COOPERADO. */
	public static final Integer COD_OPERACAO_INCLUIR_COOPERADO = 0;
	
	/** A constante COD_OPERACAO_NOVA_SUBSCRICAO. */
	public static final Integer COD_OPERACAO_NOVA_SUBSCRICAO = 1;
	
	/** A constante COD_OPERACAO_REATIVACAO. */
	public static final Integer COD_OPERACAO_REATIVACAO = 2;
	
	/** A constante COD_OPERACAO_DESLIGAMENTO. */
	public static final Integer COD_OPERACAO_DESLIGAMENTO = 3;
	
	/** A constante COD_OPERACAO_DEVOLUCAO_ATIVO. */
	public static final Integer COD_OPERACAO_DEVOLUCAO_ATIVO = 4;
	
	/** A constante COD_OPERACAO_DEVOLUCAO_INATIVO. */
	public static final Integer COD_OPERACAO_DEVOLUCAO_INATIVO = 5;

	/** A constante PAR_HIST_CCA_SUBS. */
	public static final Integer PAR_HIST_CCA_SUBS = 61;
	
	/** A constante PAR_HIST_CCO_DEB_INTEG_PARC_CCA. */
	public static final Integer PAR_HIST_CCO_DEB_INTEG_PARC_CCA = 63;
	
	/** A constante PAR_HIST_INTEG_RATEIO. */
	public static final Integer PAR_HIST_INTEG_RATEIO = 311;
	
	/** A constante PAR_HIST_CCA_INTEGR_CONTA. */
	public static final Integer PAR_HIST_CCA_INTEGR_CONTA = 109;
	
	/** A constante PAR_HIST_CCA_DEVOL_ATIVO_CONTA. */
	public static final Integer PAR_HIST_CCA_DEVOL_ATIVO_CONTA = 124;
	
	/** A constante PAR_HIST_CCA_DEVOL_DESLIG_CONTA. */
	public static final Integer PAR_HIST_CCA_DEVOL_DESLIG_CONTA = 126;
	
	/** A constante PAR_HIST_CCA_INT_CANC_DEVOLUCAO. */
	public static final Integer PAR_HIST_CCA_INT_CANC_DEVOLUCAO = 132;
	
	/** A constante PAR_HIST_CCA_DEV_CANC_DEVOLUCAO. */
	public static final Integer PAR_HIST_CCA_DEV_CANC_DEVOLUCAO = 133;
	
	/** A constante PAR_HIST_CCA_DEV_DEVOLUCAO_CH_ADMIN. */
	public static final Integer PAR_HIST_CCA_DEV_DEVOLUCAO_CH_ADMIN = 134;
	
	/** A constante PAR_HIST_CCO_CRED_PARC_DEVOL_CCA_DES. */
	public static final Integer PAR_HIST_CCO_CRED_PARC_DEVOL_CCA_DES = 137;
	
	/** A constante PAR_HIST_CCO_CRED_PARC_DEVOL_CCA_ATI. */
	public static final Integer PAR_HIST_CCO_CRED_PARC_DEVOL_CCA_ATI = 138;
	
	/** A constante COD_HISTORICO_CCA_INTEG_RESERVA. */
	public static final Integer COD_HISTORICO_CCA_INTEG_RESERVA = 109;
	
	/** A constante COD_LOTE_PARC_AVISTA_CCA. */
	public static final Integer COD_LOTE_PARC_AVISTA_CCA = 9100;
	
	/** A constante COD_LOTE_TIPO_OPERACAO_LANCAMENTO. */
	public static final Integer COD_LOTE_TIPO_OPERACAO_LANCAMENTO = 0;
	
	//CONSTANTES PARA FECHAMENTO DE LANÇAMENTOS DE LOTES
	/** A constante COD_LANC_OPERACAO_ESTORNO. */
	public static final String COD_LANC_OPERACAO_ESTORNO = "E";
	
	/** A constante COD_LANC_OPERACAO_DEBITO. */
	public static final String COD_LANC_OPERACAO_DEBITO = "D";
	
	/** A constante COD_LANC_OPERACAO_CREDITO. */
	public static final String COD_LANC_OPERACAO_CREDITO = "C";
	
	/** A constante COD_LANC_OPERACAO_LIBERACAO. */
	public static final String COD_LANC_OPERACAO_LIBERACAO = "*";
	
	/** A constante COD_LANC_OPERACAO_BLOQ_JUDICIAL. */
	public static final String COD_LANC_OPERACAO_BLOQ_JUDICIAL = "B";
	
	/** A constante COD_LANC_OPERACAO_LIBE_JUDICIAL. */
	public static final String COD_LANC_OPERACAO_LIBE_JUDICIAL = "L";
	
	/** A constante PAR_HIST_CCA_TRANSF_SUBSC_ATIVO_DEB. */
	public static final Integer PAR_HIST_CCA_TRANSF_SUBSC_ATIVO_DEB = 121;
	
	/** A constante PAR_HIST_CCA_TRANSF_SUBSC_ATIVO_CRED. */
	public static final Integer PAR_HIST_CCA_TRANSF_SUBSC_ATIVO_CRED = 122;
	
	/** A constante COD_HISTORICO_CCA_INTEG_IMPLANTACAO. */
	public static final Integer COD_HISTORICO_CCA_INTEG_IMPLANTACAO = 111;
	
	/** A constante COD_LST_SIT_RETORNO_FOLHA_ABERTO. */
	public static final Integer COD_LST_SIT_RETORNO_FOLHA_ABERTO = 0;
	
	/** A constante COD_LST_TIPO_DETALHE_REMESSA_CCA. */
	public static final Integer COD_LST_TIPO_DETALHE_REMESSA_CCA = 1;
	
	/** A constante COD_LST_SIT_RETORNO_FOLHA_PREPARA_BAIXA_SEM_RETORNO. */
	public static final Integer COD_LST_SIT_RETORNO_FOLHA_PREPARA_BAIXA_SEM_RETORNO = 4;
	
	/** A constante COD_LST_TIPO_INTERVALO_PARTE. */
	public static final Integer COD_LST_TIPO_INTERVALO_PARTE = 1;
	
	/** A constante COD_LST_TIPO_INTERVALO_ULTIMOS. */
	public static final Integer COD_LST_TIPO_INTERVALO_ULTIMOS = 2;
	
	/** A constante COD_LST_TIPO_INTERVALO_TODO. */
	public static final Integer COD_LST_TIPO_INTERVALO_TODO = 0;
	
	/** A constante COD_HISTORICO_CCA_INTEG_CONTA. */
	public static final Integer COD_HISTORICO_CCA_INTEG_CONTA = 102;
	
	/** A constante COD_LST_TIPO_DEB_VALOR. */
	public static final Integer COD_LST_TIPO_DEB_VALOR = 0;      
	
	/** A constante COD_LST_TIPO_DEB_PERC_SALARIO_RENDA. */
	public static final Integer COD_LST_TIPO_DEB_PERC_SALARIO_RENDA = 1;
	
	/** A constante COD_LST_TIPO_DEB_PERC_SALARIO_BASE. */
	public static final Integer COD_LST_TIPO_DEB_PERC_SALARIO_BASE = 2;
	
	/** A constante COD_LST_TIPO_DEB_QTD_COTAS. */
	public static final Integer COD_LST_TIPO_DEB_QTD_COTAS = 3;
	
	/** A constante COD_LST_SIT_RETORNO_FOLHA_BAIXADO_MENOR. */
	public static final Integer COD_LST_SIT_RETORNO_FOLHA_BAIXADO_MENOR = 3;
	
	/** A constante LST_TIPO_VALOR_DEBITO_CCA. */
	public static final Integer LST_TIPO_VALOR_DEBITO_CCA = 876;
	
	/** A constante PAR_DIRETORIO_TEMPORARIO. */
	public static final Integer PAR_DIRETORIO_TEMPORARIO = 432;
	
	/** A constante COD_ORIGEM_LOTE_INTERNO. */
	public static final Integer COD_ORIGEM_LOTE_INTERNO = 0;
	
	/** A constante COD_ORIGEM_LOTE_CAIXA. */
	public static final Integer COD_ORIGEM_LOTE_CAIXA = 1;
	
	//Códigos de informações acumuladas
	/** A constante COD_TIPO_INF_ACUMULADA_SALDO_MEDIO_REAL. */
	public static final Integer COD_TIPO_INF_ACUMULADA_SALDO_MEDIO_REAL = 1;
	
	/** A constante COD_APLIC_COOP_CRED_MUTUO. */
	public static final Integer COD_APLIC_COOP_CRED_MUTUO = 1;
	
	/** A constante COD_APLIC_COOP_CRED_RURAL. */
	public static final Integer COD_APLIC_COOP_CRED_RURAL = 2;
	
	/** A constante COD_SEXO_MASCULINO. */
	public static final Integer COD_SEXO_MASCULINO = 1;
	
	/** A constante COD_SEXO_FEMININO. */
	public static final Integer COD_SEXO_FEMININO = 0;
	
	/** A constante LST_TIPO_PROFISSAO. */
	public static final Integer LST_TIPO_PROFISSAO = 196;
	
	/** A constante LST_TIPO_ESTADO_CIVIL. */
	public static final Integer LST_TIPO_ESTADO_CIVIL = 55;
	
	//Constantes que indicam os Tippos de Destino do Rateio
	/** A constante COD_TP_RATEIO_CONTA_CORRENTE. */
	public static final Integer COD_TP_RATEIO_CONTA_CORRENTE = 1;
	
	/** A constante COD_TP_RATEIO_CAPITAL. */
	public static final Integer COD_TP_RATEIO_CAPITAL = 2;
	
	/** A constante COD_TP_RATEIO_CONTA_CORRENTE_CAPITAL. */
	public static final Integer COD_TP_RATEIO_CONTA_CORRENTE_CAPITAL = 3;
	
	/** A constante COD_TP_RATEIO_CAPITAL_CONTA_CORRENTE. */
	public static final Integer COD_TP_RATEIO_CAPITAL_CONTA_CORRENTE = 4;
	
	/** A constante CONTA_CORRENTE. */
	public static final Integer CONTA_CORRENTE = 1;
	
	//CONSTANTES PARA IdTipoOrigemRateio da tabela DestinoRateio
	/** A constante COD_TIPO_ORIGEM_RATEIO. */
	public static final String COD_TIPO_ORIGEM_RATEIO = "R";
	
	/** A constante COD_TIPO_ORIGEM_RAT_DISTRIBUI. */
	public static final String COD_TIPO_ORIGEM_RAT_DISTRIBUI = "D";
	
	/** A constante COD_APLIC_COOP_AMBOS. */
	public static final Integer COD_APLIC_COOP_AMBOS = 3;  
	
	//CONSTANTES PARA CodSitExterno da tabela ControlaOperacaoRateio
	/** A constante COD_SIT_EXTERNO_SISTEMA. */
	public static final Integer COD_SIT_EXTERNO_SISTEMA = 0;
	
	/** A constante COD_SIT_EXTERNO_EM_ABERTO. */
	public static final Integer COD_SIT_EXTERNO_EM_ABERTO = 1;
	
	/** A constante COD_SIT_EXTERNO_EFETIVADO. */
	public static final Integer COD_SIT_EXTERNO_EFETIVADO = 9;
	
	//Constantes de valores que os TITULOS podem assumir - Tipo de Liquidação
	/** A constante COD_OPCREDITO_QUITADO. */
	public static final Integer COD_OPCREDITO_QUITADO = 2;
	
	/** A constante COD_OPCREDITO_BAIXADO_PARA_ACERTO. */
	public static final Integer COD_OPCREDITO_BAIXADO_PARA_ACERTO = 5;
	
	/** A constante COD_OPCREDITO_LIQUIDADO. */
	public static final Integer COD_OPCREDITO_LIQUIDADO = 23;
	
	/** A constante COD_STATUS_A_CONTABILIZAR. */
	public static final Integer COD_STATUS_A_CONTABILIZAR = 0;
	
	/** A constante COD_TIPO_MODALIDADE_RATEIO. */
	public static final Integer COD_TIPO_MODALIDADE_RATEIO = 1;
	
	/** A constante COD_VALOR_CONTABIL_RATEIO_IRRF. */
	public static final Integer COD_VALOR_CONTABIL_RATEIO_IRRF = 70;
	
	/** A constante COD_OPERACAO_RATEIO_ESTORNO_IRRF. */
	public static final Integer COD_OPERACAO_RATEIO_ESTORNO_IRRF = 3;
	
	/** A constante PAR_PERMITE_LANCTO_NEG_CTA_CAPITAL. */
	public static final Integer PAR_PERMITE_LANCTO_NEG_CTA_CAPITAL = 66;
	
	/** A constante PAR_HIST_DEV_RATEIO. */
	public static final Integer PAR_HIST_DEV_RATEIO = 313;
	
	/** A constante PAR_HIST_DEV_RATEIO_OUTROS_VAL. */
	public static final Integer PAR_HIST_DEV_RATEIO_OUTROS_VAL = 315;	
	
	/** A constante PAR_HIST_SOBRAS_CCO. */
	public static final Integer PAR_HIST_SOBRAS_CCO = 317;
	
	/** A constante PAR_HIST_DEB_RATEIO_OUTROS_VAL. */
	public static final Integer PAR_HIST_DEB_RATEIO_OUTROS_VAL = 320;
	
	/** A constante PAR_HIST_PERDAS_CCO. */
	public static final Integer PAR_HIST_PERDAS_CCO = 319;
	
	/** A constante COD_OPERACAO_RATEIO_IRRF. */
	public static final Integer COD_OPERACAO_RATEIO_IRRF = 2;
	
	/** A constante COD_HIST_LANC_CCO. */
	public static final Integer COD_HIST_LANC_CCO = 172;
	
	/** A constante COD_LIST_ORIGEM_LOTE_CCO. */
	public static final Integer COD_LIST_ORIGEM_LOTE_CCO = 446;

	/** A constante COD_HIST_LANC_CCO_DEV_CAPITAL_COOP_DESLIGADO. */
	public static final Integer COD_HIST_LANC_CCO_DEV_CAPITAL_COOP_DESLIGADO = 670;
	                  	
	/** A constante COD_HIST_LANC_CCO_DEV_CAPITAL_COOP_ATIVO. */
	public static final Integer COD_HIST_LANC_CCO_DEV_CAPITAL_COOP_ATIVO = 671;		


	/** A constante COD_HIST_LANC_CCO_DEV_CAPITAL_COOP_CAPT_REM. */
	public static final Integer COD_HIST_LANC_CCO_DEV_CAPITAL_COOP_CAPT_REM = 7096;		
	
	//Constantes participação central bancoob
	/** A constante DIA_LIM_CAD_PARTICIP_CENTRAL_BANCOOB. */
	public static final Integer DIA_LIM_CAD_PARTICIP_CENTRAL_BANCOOB = 10;
	
	/**
	 * OperacaoFinanceira
	 */
	public static final Integer COD_STATUS_CONTABILIZADO = 1;
	
	/** A constante COD_STATUS_PROCESSADO_COM_ERRO. */
	public static final Integer COD_STATUS_PROCESSADO_COM_ERRO = 2;
	
	/** A constante COD_STATUS_PROCESSADO. */
	public static final	Integer	COD_STATUS_PROCESSADO = 3;
	
	/** A constante COD_TIPO_MOV_NORMAL. */
	public static final	Integer	COD_TIPO_MOV_NORMAL = 0;
	
	/** A constante COD_TIPO_MOV_COMPLEMENTAR. */
	public static final	Integer	COD_TIPO_MOV_COMPLEMENTAR = 1;
	
	/** A constante COD_SUB_REL_CONT_DEFAULT. */
	public static final	Integer	COD_SUB_REL_CONT_DEFAULT = 0;
	
	/** A constante LCTOCONTABILIZACAO_A_FAZER. */
	public static final	Integer	LCTOCONTABILIZACAO_A_FAZER = 0;
	
	/** A constante LCTOCONTABILIZACAO_FEITO. */
	public static final	Integer	LCTOCONTABILIZACAO_FEITO = 1;
	
	/** A constante ID_OPERACAO_PARTICIPACAO_INDIRETA. */
	public static final	Integer	ID_OPERACAO_PARTICIPACAO_INDIRETA = 40;
	
	/** A constante COD_CONTABILIZA_COOP. */
	public static final	Integer	COD_CONTABILIZA_COOP = 1;
	
	/** A constante COD_OP_NAO_PROCESSADA. */
	public static final	Integer	COD_OP_NAO_PROCESSADA = 0;
	
	/** A constante COD_OP_PROCESSADA. */
	public static final	Integer	COD_OP_PROCESSADA = 1;
	
	/** A constante NUM_PAC_DEFAULT. */
	public static final	Integer	NUM_PAC_DEFAULT = 0;
	
	/** A constante VALOR_REF_ESTORNO_PART_INDIRETA. */
	public static final	Integer VALOR_REF_ESTORNO_PART_INDIRETA = 269;
	
	/** A constante VALOR_REF_PART_INDIRETA. */
	public static final	Integer VALOR_REF_PART_INDIRETA = 268;

	//Modalidade Produto
	/** A constante MODALIDADE_PRODUTO. */
	public static final	Integer MODALIDADE_PRODUTO = 1;
	
	/** A constante COD_INCREMENTADOR_INICIAL. */
	public static final Integer COD_INCREMENTADOR_INICIAL = 1;
	
	/** A constante COD_INCREMENTADOR_PARC_INICIAL. */
	public static final Integer COD_INCREMENTADOR_PARC_INICIAL = 0;	

	//AplicativoEnum, parametro obrigatorio para integracao com o CCO
	/** A constante COD_APLICATIVO_ENUM_CCA. */
	public static final Integer COD_APLICATIVO_ENUM_CCA = 1;
	
	/**
	 * Contas da contabilidade
	 */
	//Conta Contabil: 2.1.5.30.10-2 - PARTICIPAÇÕES INST FINANC CONTROLADA COOP CRÉDITO
	public static final String NUM_CONTA_PI = "2.1.5.30.10-2"; 
	
	/**
	 * Tipo grau cooperativa
	 */
	//Conta Contabil: 2.1.5.30.10-2 - PARTICIPAÇÕES INST FINANC CONTROLADA COOP CRÉDITO
	public static final Integer COD_TIPO_GRAU_INSTITUICAO_CENTRAL = 1;
	
	/** A constante COD_TIPO_GRAU_INSTITUICAO_SINGULAR. */
	public static final Integer COD_TIPO_GRAU_INSTITUICAO_SINGULAR = 2;
	
	/** A constante COD_TIPO_GRAU_INSTITUICAO_CONFEDERACAO. */
	public static final Integer COD_TIPO_GRAU_INSTITUICAO_CONFEDERACAO = 3;
	
	/** A constante COD_TIPO_GRAU_INSTITUICAO_CONFEDERACAO. */
	public static final Integer COD_TIPO_GRAU_INSTITUICAO_BANCOOB = 4;
	
	/**
	 * Configuração CCA
	 */
	//TIPO PESQUISA PARAMETRO
	public static final Integer NUM_TIPO_PESQUISA_PARAMETRO_CODIGO = 1;
	
	/** A constante NUM_TIPO_PESQUISA_PARAMETRO_NOME. */
	public static final Integer NUM_TIPO_PESQUISA_PARAMETRO_NOME = 0;
	
	/** A constante NUM_TIPO_PESQUISA_PARAMETRO_DESCRICAO. */
	public static final Integer NUM_TIPO_PESQUISA_PARAMETRO_DESCRICAO = 2;
	
	/** A constante ST_INCLUIR. */
	public static final Integer ST_INCLUIR = 1;
	
	/** A constante ST_ALTERAR. */
	public static final Integer ST_ALTERAR = 2;
	
	/** A constante ST_BOL_INATIVO. */
	public static final Integer ST_BOL_INATIVO = 0;
	
	/** A constante ST_BOL_ATIVO. */
	public static final Integer ST_BOL_ATIVO = 1;
	
	/** A constante NUM_ZERO. */
	public static final Integer NUM_ZERO = 0;
	
	/** A constante COD_CONFEDERACAO. */
	public static final Integer COD_CONFEDERACAO = 2;
	
	/** A constante NUM_CONFEDERACAO. */
	public static final String NUM_CONFEDERACAO = "0300";
	
	/** A constante NOME_CONFEDERACAO. */
	public static final String NOME_CONFEDERACAO = "CONFEDERAÇÃO NACIONAL DAS COOPERATIVAS DO SICOOB";
	
	/** A constante PARAMETRO_SITUACAO_PROPOSTA. */
	public static final Integer PARAMETRO_SITUACAO_PROPOSTA = 1568;
	
	/** A constante PARAMETRO_SUBSCRICAO_SEM_DOCUMENTO. */
	public static final Integer PARAMETRO_SUBSCRICAO_SEM_DOCUMENTO = 1569;
	
	/** A constante PARAMETRO_VALOR_INGRESSO_COOP. */
	public static final Integer PARAMETRO_VALOR_INGRESSO_COOP = 1572;	
	
	/** A constante PARAMETRO_VALIDAR_NATUREZA_JURIDICA_CADASTRO_ASSOCIADO. */
	public static final Integer PARAMETRO_VALIDAR_NATUREZA_JURIDICA_CADASTRO_ASSOCIADO = 1582;				
	
	/**Usuario para integralizacao via cartao cabal*/
	public static final String USR_EXTERNO_SISBR = "USR_EXTERNO_SISBR";
	
	/**IDLancamento para o SQL ignorar a replicacao*/
	public static final Long IDLANCAMENTOCONTACAPITAL_NAOREPLICA = -1L;
	
	/**MARCA REGISTRO PARA NAO SER REPLICADO NA TRIGGER DE EXCLUSAO*/
	public static final Integer ID_CONTACAPITAL_DELETE_NAOREPLICA = -2;
	
	/**IDParcelamento para o SQL ignorar a replicacao*/
	public static final Long IDPARCELAMENTOCONTACAPITAL_NAOREPLICA = -1L;	

	/**Cod para o SGBD SQL*/	
	public static final Short TIPO_SGBD_SQL = 1;
	
	/**Cod para o SGBD DB2*/	
	public static final Short TIPO_SGBD_DB2 = 2;			
	
	
	/**
	 * Motivo da devolução capital
	 */	
	/** A constante COD_MOTIVO_DEVOLUCAO_DEMISSAO. */
	public static final	Integer COD_MOTIVO_DEVOLUCAO_DEMISSAO = 2;

	/** A constante COD_MOTIVO_DEVOLUCAO_EXCLUSAO. */
	public static final	Integer COD_MOTIVO_DEVOLUCAO_EXCLUSAO = 3;
	
	/** A constante COD_MOTIVO_DEVOLUCAO_ELIMINACAO. */
	public static final	Integer COD_MOTIVO_DEVOLUCAO_ELIMINACAO = 4;
	
	/** A constante COBERTURA_DEBITOS_VENCIDOS_OU_VINCENDOS_COM_A_COOPERATIVA. */
	public static final	Integer COD_MOTIVO_DEVOLUCAO_COBERTURA_DEB_VENCIDOS_OU_VINCENDOS_COM_A_COOP = 5;
	
	/** A constante RESGATE_PARCIAL_EM_FUNC_DA_IDADE_EOU_TEMPO_ASSOC_COOP. */
	public static final	Integer COD_MOTIVO_DEVOLUCAO_RESGATE_PARCIAL_EM_FUNC_DA_IDADE_EOU_TEMPO_ASSOC_COOP = 6;
	
	/** A constante OUTROS. */
	public static final	Integer COD_MOTIVO_DEVOLUCAO_OUTROS = 99;
	
	/** A constante OUTROS. */
	public static final	Integer COD_MOTIVO_DEVOLUCAO_POR_DECISAO_DO_CONSELHO_DE_ADM = 7;	

	/**
	 * Valor de ingresso na cooperativa (FAÇA PARTE)
	 */
	public static final Integer VLR_INGRESSO_COOP = 1572;
	
	/**
	 * Valor de integralização mensal fixo (FAÇA PARTE)
	 */
	public static final Integer VLR_INTEG_MENSAL_FIXO = 1573;
	
	/**
	 * Valor de integralização mensal percentual (FAÇA PARTE)
	 */
	public static final Integer VLR_INTEG_MENSAL_PERC = 1574;
	
	/** A constante COD_FORMA_DEB_VIA_CONTA. */
	public static final Integer COD_FORMA_DEB_VIA_CONTA = 2;
	
	/** A constante COD_TIPO_PERIODO_DEB_MENSAL. */
	public static final Integer COD_TIPO_PERIODO_DEB_MENSAL = 1;
	
	/** A constante COD_HISTORICO_CCA_TRANSF_BAIXA_SUBSCRICAO. */
	public static final Integer COD_HISTORICO_CCA_TRANSF_BAIXA_SUBSCRICAO = 4;  
	
	/** A constante COD_HISTORICO_CCA_TRANSF_SUBSCRICAO. */
	public static final Integer COD_HISTORICO_CCA_TRANSF_SUBSCRICAO = 5;
	
	/**
	 * Motivo da devolução capital
	 */	
	/** A constante COD_ORIGEM_BLOQUEIO_CAPITAL. */
	public static final	Short COD_ORIGEM_BLOQUEIO_CAPITAL_TRANSFERENCIA = 1;
	
	/** Constante utilizada para filtrar todos os codigos de canais */
	public static final Integer COD_CANAL_FILTRAR_TODOS = -1;
	
	
	/**
	 * Debito Indeterminado
	 */
	/**
	 * Alteração de apenas de Valor do Debito
	 */
	public static final Integer TIPO_ALTERACAO_VALOR = 1;
	
	/**
	 * Alteração de apenas de Data do Debito
	 */
	public static final Integer TIPO_ALTERACAO_DATA = 2;
	
	/**
	 * Alteração de Valor e Data do Debito
	 */
	public static final Integer TIPO_ALTERACAO_VALOR_DATA = 3;
	
	/**
	 * Numero 100
	 */
	public static final int NUMERO_CEM = 100;
	
	/**
	 * Numero 300 confederacao
	 */
	public static final int NUMERO_CONFEDERACAO = 300;
	
	/**
	 * Usuário fechamento 
	 */
	public static final String USUARIO_FECHAMENTO_PRODUCAO = "USRBANCOOBFEC";
	
	/**
	 * Parametro valor salario base
	 */
	public static final Integer PAR_VALOR_SALARIO_BASE = 1583; 
	
	
	/** A constante TIPO_CONTRATO. */
	public static final short TIPO_CONTRATO_ABERTO = 1;	
	public static final short TIPO_CONTRATO_LIQUIDAR = 2;

	public static final int PAR_INDICA_USO_RESG_AUTO = 536;

	public static final int PAR_SITUACAO_FECHAMENTO_SICOOBBR = 506;

	public static final int COD_FECHAMENTO_BANCOOB_NOITE = 2;
	public static final int COD_FECHAMENTO_BANCOOB_MANHA = 0;

	public static final int COD_FECHAMENTO_COOPERATIVA = 1;

	public static final int COD_FECHAMENTO_INICIADO = 1;
	public static final int COD_FECHAMENTO_CONCLUIDO = 2;
	public static final int COD_FECHAMENTO_REJEITADO = 3;

	public static final int COD_SALDO_CONTA_CAPITAL = 6;	
	public static final int COD_SALDO_CONTA_CAPITAL_SUBSCRITO = 13;
	public static final int COD_SALDO_CONTA_CAPITAL_A_DEVOLVER = 14;
	
}
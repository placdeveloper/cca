package br.com.bancoob.sisbr
{
	public class Constantes
	{
		public static const PRODUTO_SISBRWEB:int = 50;
		public static const PRODUTO_SISBRWEB_AIR:int = 55;
				
		public static const PRODUTO_CONTA_CAPITAL:int = 2;
		public static const PRODUTO_CONTA_CORRENTE:int = 3;
		public static const PRODUTO_CAPTACAO_REMUNERADA:int = 4;
		public static const PRODUTO_CONTABILIDADE:int = 15;
		
		public static const PRODUTO_CRED_RURAL:int = 6;
		public static const PRODUTO_CRED_MUTUO:int = 7;
		public static const PRODUTO_TIT_DESCONTADO:int = 8;
		public static const PRODUTO_PREJUIZO:int = 52;
		
        public static const ID_PRODUTO_INSS:int = 16;
        
		public static const ID_TIPO_BORD_TITULOS:int = 1;
		public static const ID_TIPO_BORD_CHEQUES:int =  2;
        
        //MODALIDADES
		public static const COD_MODALIDADE_CCO:int = 1;
		public static const COD_MODALIDADE_CCI:int = 3;
		
		//CÓDIGO EXTRATO CCO
		public static const COD_EXTRATO_ACESSO_RAPIDO:int = 0;
		public static const COD_EXTRATO_CCO:int = 1;

		//ATENDIMENTO
		public static const COD_TIPO_NACIONALIDADE_PADRAO_INT:int = 10;
		
		//CONTA CAPITAL
		public static const COD_MODO_LANCAMENTO_VIA_CAIXA:int = 1;
		public static const COD_MODO_LANCAMENTO_VIA_CONTA:int = 2;
		public static const COD_MODO_LANCAMENTO_VIA_FOLHA:int = 3;
		public static const COD_MODO_LANCAMENTO_VIA_COBRANCA:int = 4;
		public static const COD_MODO_LANCAMENTO_VIA_MIGRACAO:int = 5;
		public static const COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN:int = 6;
		public static const COD_MODO_LANCAMENTO_VIA_RATEIO:int = 7;
		public static const COD_MODO_LANCAMENTO_VIA_RESERVA:int = 8;
		
		//CONTA CAPITAL - TIPO DE VALOR DE DÉBITO INDETERMINADO
		public static const COD_LST_TIPO_DEB_VALOR:int = 0;    
		public static const COD_LST_TIPO_DEB_PERC_SALARIO_RENDA:int = 1;    
		public static const COD_LST_TIPO_DEB_PERC_SALARIO_BASE:int = 2;     
		public static const COD_LST_TIPO_DEB_QTD_COTAS:int = 3;
		
		//CONTA CAPITAL - TIPO PERÍODO DE DÉBITO
		public static const COD_TIPO_PERIODO_DEBITO_CCA_DIARIO:int = 0;
		public static const COD_TIPO_PERIODO_DEBITO_CCA_MENSAL:int = 1;
		
		//CONTA CAPITAL - SITUACAO DO COOPERADO
		public static const COD_SITUACAO_COOPERADO_ATIVO:int = 1;
		public static const COD_SITUACAO_COOPERADO_DEMITIDO:int = 2;
		public static const COD_SITUACAO_COOPERADO_EXCLUIDO:int = 3;
		public static const COD_SITUACAO_COOPERADO_ELIMINADO:int = 4;
		
		//CONTA CAPITAL - PARCELAMENTO
		public static const COD_TIPO_PARCELAMENTO:int = 1;
		
		//CONTA CAPITAL - PARCELAS
		public static const COD_PARCELA_GERADA:int = 1;
		public static const COD_PARCELA_PAGA_VIA_CAIXA:int = 2;        
		public static const COD_PARCELA_PAGA_VIA_CONTA:int = 3;       
		public static const COD_PARCELA_CANCELADA:int = 4;
		public static const COD_PARCELA_PAGA_VIA_CHADMIN:int = 5; 
		public static const COD_PARCELA_EXCLUIDA:int = 6;
		public static const COD_PARCELA_PAGA_VIA_ARQUIVO:int = 7;  
		public static const COD_PARCELA_PAGA_VIA_COBRANCA:int = 8;          
		public static const COD_PARCELA_PAGA_ANTES_MIGRACAO:int = 9;     
		public static const COD_PARCELA_PAGA_VIA_RATEIO:int = 10;       
		public static const COD_PARCELA_PAGA_VIA_RESERVA:int = 11;  
		
		//CONTA CAPTAÇÂO
		public static const COD_TIPO_MODALIDADE_COM_VENC:int = 1;
		public static const COD_TIPO_MODALIDADE_SEM_VENC:int = 2;
		public static const COD_TIPO_MODALIDADE_COM_DATA_ANIV:int = 3;
		public static const COD_TIPO_MODALIDADE_LCA:int = 4;
		        	
		public static const COD_TIPO_PRE_FIXADO:int = 2;
		public static const COD_TIPO_POS_FIXADO:int = 3;
		public static const COD_TIPO_TAXA_DIA_CORRIDO:int = 1;
		public static const COD_TIPO_TAXA_DIA_UTIL:int = 2;
		
		public static const COD_TAXA_LINEAR_CAPREM:int = 0;
		public static const COD_TAXA_EXPONENCIAL_CAPREM:int = 1;

		public static const COD_SIT_CAPREM_APLIC_NORMAL:int = 1;         
		public static const COD_SIT_CAPREM_APLIC_CANCELADA:int = 2;        
		public static const COD_SIT_CAPREM_APLIC_RESGATADA:int = 3;        
		public static const COD_SIT_CAPREM_APLIC_BAIXADA:int = 4;  
		
		//Constantes para identificar os tipos de conta corrente
        public static const COD_TIPO_CONTA_CORRENTE_PF:int = 0;  
		public static const COD_TIPO_CONTA_CORRENTE_PJ:int = 1;
		public static const COD_TIPO_CONTA_CORRENTE_SF:int = 2;
		public static const COD_TIPO_CONTA_CORRENTE_COOP_FIL:int = 4;
		public static const COD_TIPO_CONTA_CORRENTE_CP:int = 5;
		public static const COD_TIPO_CONTA_CORRENTE_CS_SEM_REDE:int = 7;
		public static const COD_TIPO_CONTA_CORRENTE_CS_REDE_COMPRA:int = 8;
		public static const COD_TIPO_CONTA_CORRENTE_CS_REDE_COMPRA_SAQUE:int = 9;
						                
        //CODTIPOLOTE
        public static const INS_COD_TIPO_LOTE_ALTERACAO_FORMA_PAGAMENTO:int = 1;
        public static const INS_COD_TIPO_LOTE_SOLICITACAO_DADO_CADASTRAL:int = 2;
        
        //CONSTANTES PARA MEIO DE PAGAMENTO
        public static const COD_MEIO_PAGTO_CARTAO:int = 1;
        public static const COD_MEIO_PAGTO_CONTA_CORRENTE:int = 2;
        public static const COD_MEIO_PAGTO_PAB:int = 3;
        
        //TIPOS DE REQUISICAO
        public static const REQUISICAO_NAO_EXISTENTE:int = 0;
        public static const REQUISICAO_EXISTENTE:int = 1;
        public static const REQUISICAO_GRAVACAO_AUTORIZADA:int = 2;
        public static const REQUISICAO_GRAVACAO_NAO_AUTORIZADA:int = 3;
        
        //PARÂMETROS (COLOCAR O PARÂMETRO NA ORDEM CRESCENTE)
        public static const PAR_DIRETORIO_ARQUIVO_MOV_TERCEIRO:int = 136;
        public static const PAR_TAXA_MENSAL_CHEQUE_ESPECIAL:int = 140;
        public static const PAR_TAXA_MENSAL_CHEQUE_ESPECIAL_PLUS:int = 141;
        public static const PAR_TAXA_MENSAL_CONTA_GARANTIDA:int = 142;
        public static const PAR_COOP_UTILIZA_CH_PLUS:int = 415;
        public static const PAR_DIRETORIO_TEMPORARIO:int = 432;
		public static const PAR_LANCAMENTO_ON_LINE_CONTA_CORRENTE:int = 525;
		public static const PAR_DIRETORIO_IMP_SIAB_DESTINO:int = 553;	
		public static const PAR_DIRETORIO_IMP_SIAB_ORIGEM:int = 554;
		public static const PAR_CRITERIO_LIMITE_OPERACIONAL_OU_CONTRATO_PRODUTO:int = 791;
        public static const PAR_TAXA_MENSAL_CONTA_GARANTIDA_PLUS:int = 963;
        public static const PAR_COOP_TRABALHA_CONTA_GARANTIDA_PLUS:int = 968;
        public static const PAR_TRABALHA_LIMITE_PLUS_VINCULADO_CAPITAL_SOCIAL:int = 979;
        public static const PAR_TX_MENSAL_CH_PLUS_VINC_CAPITAL_ATE_SALDO:int = 980;
        public static const PAR_TX_MENSAL_CTA_GAR_PLUS_VINC_CAPITAL_ATE_SALDO:int = 981;
        public static const PAR_TX_MENSAL_CH_PLUS_VINC_CAPITAL_ACIMA_SALDO:int = 982;
        public static const PAR_TX_MENSAL_CTA_GAR_PLUS_VINC_CAPITAL_ACIMA_SALDO:int = 983;
		public static const PAR_CRITERIO_CONTROLE_LIMITE_TIT:int = 1215;
        
        //LISTAS
        public static const LST_SITUACAO_PROPOSTA:int = 77
		public static const LST_SITUACAO_BORDERO_TRANSFERENCIA:int = 703;
		public static const LST_FONTE_RECURSO_FINANCEIRO:int = 704;
        
        public static const COD_APLICACAO_TIPO_CONTA_FISICA:int = 0;
		public static const COD_APLICACAO_TIPO_CONTA_JURIDICA:int = 1;
		public static const COD_APLICACAO_TIPO_CONTA_UNIVERSITARIO:int = 20;

		public static const COD_INDIVIDUAL:int = 1;
		public static const COD_CONJUNTA_NAO_SOLIDARIA:int = 3;

		public static const COD_LST_EXTRATO_NAO_EMITE:int = 1;

		public static const COD_MODALIDADE_CAD:int = 4;
		public static const COD_MODALIDADE_COR:int = 5;
		public static const COD_MODALIDADE_CSA:int = 6;

		public static const COD_TIPO_PES_FISICA:int = 0;                   
		public static const COD_TIPO_PES_JURIDICA:int = 1;              
		public static const COD_TIPO_AMBAS:int = 2;
		
		public static const TIPOPROC_ADMINISTRADORESPJ:int = 6031;
		public static const TIPOPROC_AGENCIACAF:int = 24035;
		public static const TIPOPROC_BANCOCAF:int = 24036;
		public static const TIPOPROC_FONTE_PAGADORA:int = 6098;
		
		public static const COD_AFASTAMENTO_DESLIGADO:int = 1;
		
	    //Valores cadastrados na Tabela Lista (IDLista = 33)
        public static const ID_COD_TIPO_OP_CREDITO_NP:int = 1; //Desconto de NP
        public static const ID_COD_TIPO_OP_CREDITO_CH:int = 2; //Desconto de Cheque
        public static const ID_COD_TIPO_OP_CREDITO_TD:int = 3; //Desconto de Titulos
		
		//LISTA - 294 - LST_SITUACAO_COOPERATIVA
		public static const COD_SITUACAO_ATIVA:int = 1;
		public static const COD_SITUACAO_INATIVA:int = 2;
		public static const COD_SITUACAO_DESCRENDENCIADA:int = 3;		
		
		//LISTA 315 - CONSTANTES DA CARACTERISTICA ESPECIAL
		public static const COD_CARACTERISTICA_ESPECIAL_SEM:int = 0;
		public static const COD_CARACTERISTICA_ESPECIAL_RENEGOCIACAO:int = 1;
		public static const COD_CARACTERISTICA_ESPECIAL_RECUPERACAO_PREJUIZO:int = 2;
		public static const COD_CARACTERISTICA_ESPECIAL_RENEGOCIACAO_TERMOS_2471:int = 3;
		public static const COD_CARACTERISTICA_ESPECIAL_RENEGOCIACAO_TERMOS_RECOOP:int = 4;
		
		//Itens da lista 501 (LST_TIPO_DOCUMENTO_SUJEITO_CUSTODIA)
		public static const COD_DOCUMENTO_CUSTODIA_TITULO:int = 1;
		public static const COD_DOCUMENTO_CUSTODIA_CHEQUE:int = 2;
		public static const COD_DOCUMENTO_CUSTODIA_TITULO_CHEQUE:int = 3;
		
		//Lista 547 -  CRITERIO DE UTILIZAÇÃO DE CONTROLE DE LIMITE OPERACIONAL OU CONTRATO PRODUTO
		public static const COD_LST_UTILIZA_LIMITE_OPERACIONAL:int = 1
		public static const COD_LST_UTILIZA_CONTRATO_PRODUTO:int = 2
		
		//LISTA 896 - CRITERIO DE CONTROLE LIMITE OPERACIONAL OU CONTRATO PRODUTO
		public static const COOPERATIVA_NAO_CONTROLA_LIMITE:int = 1;
		public static const COOPERATIVA_PERMITE_CADASTRO_ACIMA_LIMITE:int = 2;
		public static const COOPERATIVA_NAO_PERMITE_CADASTRO_ACIMA_LIMITE:int = 3;
		
		//Lista para definir CRITERIO JUROS TITULO DESCONTADO (idlista = 154)
		public static const TIT_CRIT_SEM_JUROS:int = 1;
		public static const TIT_CRIT_JUROS_SIMPLES_MES:int = 2;
		public static const TIT_CRIT_JUROS_SIMPLES_ANO:int = 3;
		public static const TIT_JUROS_COMPOSTOS_MES:int = 4;
		public static const TIT_JUROS_COMPOSTOS_ANO:int = 5;
		public static const TIT_TAXA_EFETIVA_MES:int = 6;
		public static const TIT_TAXA_EFETIVA_ANO:int = 7;
		public static const TIT_JUROS_SIMPLES_ANO_CIVIL:int = 8;
		public static const TIT_TAXA_EFETIVA_ANO_CIVIL:int = 9;
		public static const TIT_JUROS_COMPOSTOS_ANO_CIVIL:int = 10;
		
		//ORIGEM DE IMPORTACAO
		public static const COD_ORIGEM_DADOS_IMPORTACAO:int = 1;
		public static const  COD_ORIGEM_DADOS_LOCAL:int = 0;
		
		//STATUS DE PROPOSTA
		public static const PROPOSTA_NAO_APROVADA:int = 0;              //Situação da Proposta
		public static const PROPOSTA_APROVADA:int = 1 ;                 //Situação da Proposta
		public static const PROPOSTA_CANCELADA:int = 2;                 //Situação da Proposta
		public static const PROPOSTA_EFETIVADA:int = 3;                 //Situação da Proposta
		public static const PROPOSTA_APROVADA_EM_ANDAMENTO:int = 4;     //Situação da Proposta
		public static const PROPOSTA_LANCADA_CC:int = 5;                //Situação da Proposta - Lançamento On-line de operações de crédito
		public static const PROPOSTA_ESTORNADA_CC:int = 6;              //Situação da Proposta - Lançamento On-line de operações de crédito
		public static const PROPOSTA_APROVADA_BNDES:int = 6;
		public static const PROPOSTA_APROVADA_PELA_COOPERATIVA:int = 7;
		public static const PROPOSTA_EM_ANALISE_BANCOOB:int = 8;
        public static const PROPOSTA_APROVADA_PELO_BANCOOB:int = 9;
		
		public static const PROPOSTA_BNDES_A_ENVIAR:int = 1;            //Situação da Proposta do BNDES
		public static const PROPOSTA_BNDES_PROTOCOLADA:int = 2;         //Situação da Proposta do BNDES
		public static const PROPOSTA_BNDES_APROVADA:int = 3 ;           //Situação da Proposta do BNDES
		public static const PROPOSTA_BNDES_COM_ERRO:int = 4;            //Situação da Proposta do BNDES
		
		//Constantes do número das cooperativas
		public static const COD_NUM_COOPERATIVA_BANCOOB:int = 0;
		public static const COD_NUM_COOPERATIVA_BANCOOB_SEDE:int = 1;
		public static const COD_NUM_COOPERATIVA_CENTRAL_AGENCIAS:int = 9999;
		
		//BANCOS
		public static const BANCO_BANCOOB_DV:int	= 0;
		public static const BANCO_BANCOOB:int 		= 756;
		public static const BANCO_DO_BRASIL:int 	= 1;
		public static const BANCO_ITAU:int 			= 341;
		public static const BANCO_REAL:int			= 356;
		
		//VALORES NULOS
        public static const SE_NULLCURRENCY:Number = -4.94065645841247E-324;
        public static const SE_NULLDOUBLE:Number = -4.94065645841247E-324;
        public static const SE_NULLSTRING:String = "";
        public static const SE_NULLBYTE:int = 0;
        public static const SE_NULLINT:int = -32768;
        public static const SE_NULLLONG:Number = -2147483648;
        public static const SE_NULLDATA:int = 0;
		
		//CONTABILIDADE
		public static const COD_FORMA_LANC_UM_UM:int = 11;	
		public static const COD_SITUACAO_PENDENTE:int = 1;
		public static const COD_SITUACAO_APROVADA:int = 2;
		public static const COD_SITUACAO_NAO_APROVADA:int = 3;  
		
		//'Indica se a instituição é uma Cooperativa ou Banco
		public static const COD_TIPO_INST_BANCO:String = "B" 			//'Banco;                     Tatiany de O. Sousa - Contabilidade - 10/02/2003
		public static const COD_TIPO_INST_COOPERATIVA:String = "R"; 	//'Cooperativa         Tatiany de O. Sousa - Contabilidade - 10/02/2003
		
		//Constantes para os limites mínimos das contas resultados
		public static const COD_LIM_MINIMO_FATES:int = 5;                   //Rodrigo M. Rodrigues - Contabilidade - 25.03.2009
		public static const COD_LIM_MINIMO_FUNDO_RESERVA:int = 10;          //Rodrigo M. Rodrigues - Contabilidade - 25.03.2009
		
		//Contantes para Zerar Contas Resultadas indicando se é semestral ou anual
		public static const FECH_SEMESTRE1:int = 6;         //Rodrigo M. Rodrigues - Contabilidade - 25.03.2009
		public static const FECH_SEMESTRE2:int = 12;       //Rodrigo M. Rodrigues - Contabilidade - 25.03.2009		
		
		//Constantes para especificar o 'CodTipoSeguro' de CM/CR (Seguro Prestamista)
		public static const PRESTAMISTA_INEXISTENTE:int = 0;
		public static const PRESTAMISTA_AVISTA:int = 1;
		public static const PRESTAMISTA_PARCELADO:int = 2;		
		
		///CONTABILIDADE - 5000 - 5099
		public static const TIPOPROC_LOTE_CTB:int = 5001;
		public static const TIPOPROC_LANC_CTB:int = 5002;
		public static const TIPOPROC_PLANO_CONTABIL:int = 5003;
		public static const TIPOPROC_HIST_CONTABIL:int = 5004;
		public static const TIPOPROC_GRUPO_CONTABIL:int = 5005;
		public static const TIPOPROC_ITEM_CONTABIL:int = 5006;
		public static const TIPOPROC_TP_INSTITUICAO:int = 5007;
		public static const TIPOPROC_CONTA_CTB:int = 5008;
		public static const TIPOPROC_RELACIONAMENTO_CONTABIL:int = 5009;
		public static const TIPOPROC_RELACIONAMENTO_NUMCONTA:int = 5010;
		public static const TIPOPROC_INSTITUICAO:int = 5011;
		public static const TIPOPROC_INSTITUICAO_MATRIZ:int = 5012;
		public static const TIPOPROC_LISTA_COOPERATIVA:int = 5013;
		public static const TIPOPROC_TIPO_LANCAMENTO:int = 5014;
		public static const TIPOPROC_FIM_SEMESTRE_CONTABIL:int = 5015;
		public static const TIPOPROC_ENCARGO_CONTABIL:int = 5016;
		public static const TIPOPROC_ALIQUOTA_CTB:int = 5017;
		public static const TIPOPROC_CONTAS_LANCAMENTO:int = 5018;
		public static const TIPOPROC_PLANO_CONTABIL_CGE:int = 5019;
		public static const TIPOPROC_REL_PLANO_CONTABIL:int = 5020;
		public static const TIPOPROC_BALANCETE_PAC:int = 5021;
		public static const TIPOPROC_VALORES_LONGO_PRAZO:int = 5022;
		public static const TIPOPROC_REL_PLE:int = 5023;
		public static const TIPOPROC_ADMIN_CONTABIL:int = 5024;
		public static const TIPOPROC_LANC_CONSULTA_CTB:int = 5025;
		public static const TIPOPROC_CTB_CONTAS_EXPORTACAO:int = 5026;
		public static const TIPOPROC_CGE_EVENTO_EXCLUSIVO:int = 5027;
		public static const TIPOPROC_CGE_EVENTO_IMPOSTO_CADASTRADO:int = 5028;
		public static const TIPOPROC_CGE_EVENTO_IMPOSTO_VINCULADO:int = 5029;
		public static const TIPOPROC_PLANO_CONTABIL_SEM_RESTRICAO:int = 5030;
		public static const TIPOPROC_CONSISTENCIA_CENTRAL_RISCO:int = 5031;
		public static const TIPOPROC_DATAS_MOVIMENTOS_CENTRAL_RISCO:int = 5032;
		public static const TIPOPROC_DATAS_CONSISTENCIA_NIVEIS_RISCO:int = 5033;
		public static const TIPOPROC_RELACIONAMENTO_NUMCONTA_CTB:int = 5034;
		public static const TIPOPROC_RELACIONAMENTO_NUMCONTA_ADM:int = 5035;
		
	    //Emitentes Cheques       
        public static const TIPOPROC_EMITENTE_CHEQUE:int = 9202;
		
		//Cobrança
		public static const TIPO_OCORRENCIAS:int = 14010;
		public static const TIPO_PROTESTO:int = 14020;
		public static const TIPO_CONTRATOS:int = 14030;
		public static const TIPO_TITULOS:int = 14040;
		public static const TIPO_VCTO_TITULOS:int = 14050;
		public static const TIPO_SACADOS:int = 14051;
		public static const TIPO_TITULOS_BORDERO:int = 14052;
		public static const TIPO_MODALIDADES_COBRANCA:int = 14053;
		public static const TIPO_CARTORIOS:int = 14054;
		public static const TIPO_CONTRATO_CREDITO:int = 14055;
		public static const TIPO_CARTORIO:int = 14056;
		public static const TIPO_CONTA_CORRENTE_TERCEIRO:int = 14057;
		public static const TIPO_DADOS_TITULOS:int = 14058;
		public static const TIPO_VALORES_CAUCAO:int = 14059;
		public static const TIPO_INSTRUCOES_RECEBIMENTO:int = 14060;
		public static const TIPO_SOLICITACAO_BLOQUETOS:int = 14061;
		public static const TIPO_SOLICITACAO_BLOQUETOS_ABERTO:int = 14062;
		public static const TIPO_SOLICITACAO_BLOQUETOS_PROCESSADO:int = 14063;
		public static const TIPO_SOLICITACAO_BLOQUETOS_ATUALIZADO:int = 14064;
		public static const TIPO_SOLICITACAO_BLOQUETOS_CANCELADO:int = 14065;
		public static const TIPO_SOLICITACAO_BLOQUETOS_ENVIADO:int = 14066;
		public static const TIPO_SOLICITACAO_BLOQUETOS_RECEBIDO:int = 14067;
		public static const TIPO_SOLICITACAO_BLOQUETOS_ENTREGUE:int = 14068;
		public static const TIPO_SOLICITACAO_BLOQUETOS_GERADO:int = 14069;
		public static const TIPO_SOLICITACAO_BLOQUETOS_NAO_CONSISTIDO:int = 14070;
		public static const TIPO_SOLICITACAO_BLOQUETOS_CONSISTIDO_COM_ERRO:int = 14071;
		public static const TIPO_SOLICITACAO_BLOQUETOS_CONSISTIDO:int = 14072;
		public static const TIPOPROC_TITULOS:int = 14073;
		public static const TIPOPROC_CCREMUNERADA:int = 14074;
		public static const TIPOPROC_TDFCR:int = 14075;
		public static const TIPOPROC_CONTACAPITAL:int = 14076;
		public static const TIPO_CAPA_LOTE_COB:int = 14077;
		public static const TIPO_BLOQUETOS_COB:int = 14078;
		public static const TIPOPROC_BORDERO_COB:int = 14079;
		public static const TIPOPROC_BORDERO_TITULO_COB:int = 14080;
		public static const TIPOPROC_COB_LIQUIDACAO_LOTES:int = 14081;
		public static const TIPOPROC_COB_LIQUIDACAO_TITULOS:int = 14082;
		public static const TIPOPROC_COB_LIQUIDACAO:int = 14083;
		public static const TIPO_PROTESTO_CONSULTA:int = 14084;
		public static const TIPO_TITULO_CLIENTE_CONSULTA:int = 14085;
		public static const TIPO_TITULO_CLIENTE_SACADO:int = 14086;
		public static const TIPO_VCTO_TITULOS_CONSULTA:int = 14087;
		public static const TIPO_INSTRUCOES_RECEBIMENTO_ACC:int = 14088;
		public static const TIPO_SACADOS_ACC:int = 14089;
		public static const TIPO_CEDENTES_ACC:int = 14090;
		public static const TIPO_USUARIOS_ACC:int = 14091;
		public static const TIPO_INSTRUCOES_CNAB_400_ACC:int = 14092;
		public static const TIPO_IMPORTACAO_CNAB_400:int = 14093;
		public static const TIPO_MOVIMENTOS_ACC:int = 14094;
		public static const TIPO_MOVIMENTOS_ABERTO_ACC:int = 14095;
		public static const TIPO_MOVIMENTOS_PROCESSADO_ACC:int = 14096;
		public static const TIPO_MOVIMENTOS_NAO_CONSISTIDO_ACC:int = 14097;
		public static const TIPO_MOVIMENTOS_CONSISTIDO_COM_ERRO_ACC:int = 14098;
		public static const TIPO_MOVIMENTOS_CONSISTIDO_ACC:int = 14099;
		public static const TIPO_SOLICITACAO_BLOQUETOS_ABERTO_ACC:int = 14100;
		public static const TIPO_SOLICITACAO_BLOQUETOS_PROCESSADO_ACC:int = 14101;
		public static const TIPO_SOLICITACAO_BLOQUETOS_ATUALIZADO_ACC:int = 14102;
		public static const TIPO_SOLICITACAO_BLOQUETOS_CANCELADO_ACC:int = 14103;
		public static const TIPO_SOLICITACAO_BLOQUETOS_GERADO_ACC:int = 14104;
		public static const TIPO_SOLICITACAO_BLOQUETOS_ENVIADO_ACC:int = 14105;
		public static const TIPO_SOLICITACAO_BLOQUETOS_RECEBIDO_ACC:int = 14106;
		public static const TIPO_SOLICITACAO_BLOQUETOS_ENTREGUE_ACC:int = 14107;
		public static const TIPO_SOLICITACAO_BLOQUETOS_ACC:int = 14108;
		public static const TIPO_REMESSA_CNAB_400_ACC:int = 14109;
		public static const TIPO_RETORNO_CNAB_400_ACC:int = 14110;
		public static const TIPO_TITULOS_ACC:int = 14111;
		public static const TIPO_TITULOS_ABERTO_ACC:int = 14112;
		public static const TIPO_TITULOS_BAIXADO_ACC:int = 14113;
		public static const TIPOPROC_TITULOS_ACC:int = 14114;
		public static const TIPO_TITULO_COB_TD_SACADO:int = 14115;
		public static const TIPO_GRUPOS_SACADOS_ACC:int = 14116;
		public static const TIPO_GRUPOS_SACADOS:int = 14117;
		public static const TIPO_CONTA_CORRENTE_ACC:int = 14118;
		public static const TIPO_SACADOS_ENDERECOS:int = 14119;
		public static const TIPO_SACADOS_EMAILS:int = 14120;
		public static const TIPOPROC_COBRANCA_LOTES:int = 14121;
		public static const TIPOPROC_COBRANCA_CHEQUES:int = 14122;
		public static const TIPOPROC_CLIENTES:int = 14123;
		public static const TIPOPROC_HISTORICO_CLIENTES:int = 14124;
		public static const TIPOPROC_BORDERO_TRANSF:int = 14125;
		public static const TIPOPROC_SACADOS_TITULO:int = 14126;
		public static const TIPOPROC_CUSTODIA_COBRANCA:int = 14127;
		public static const TIPOPROC_SACADOS_POR_GRUPO:int = 14128;
		public static const TIPOPROC_MUNICIPIO_AGENCIA_RECOR:int = 14129;
		public static const TIPO_CONTRATO_CREDITO_PORPRODUTO_EMABERTO:int = 14130;
		public static const TIPOPROC_CONTRATO_CREDITO_EMABERTO_PREJUIZO:int = 22065;
		public static const TIPOPROC_CONTRATO_CREDITO_TRANSFERIDOS_PREJUIZO:int = 30108;
		public static const TIPO_CONTRATO_CREDITO_PORPRODUTO_LIQUIDADO:int = 14131;
		public static const TIPO_CONTRATO_CREDITO_PORPRODUTO_FILHOTE:int = 14132;
		public static const TIPOPROC_TITULOS_REMESSA_BANCO_CORRESP:int = 14133;
		public static const TIPOPROC_PRORROGACAO_CHEQUE_CUSTODIA:int = 14134;
		public static const TIPOPROC_TITULOS_COBRANCA_CHEQUE:int = 14135;
		public static const TIPOPROC_CLIENTE_SITE_WEB_BOLETO:int = 14136;
		
		//TITULOS DESCONTADOS - 21000 - 21999
		public static const TIPOPROC_TD_REGISTRO_NP:int = 21000;
		public static const TIPOPROC_TD_FINALIDADE:int = 21001;
		public static const TIPOPROC_TD_TIPO_CALCULO:int = 21002;
		public static const TIPOPROC_TD_LIMITE_OPERACIONAL:int = 21003;
		public static const TIPOPROC_TD_BORDEROS_CHEQUES:int = 21004;
		public static const TIPOPROC_TD_BORDEROS_TITULOS:int = 21005;
		public static const TIPOPROC_TD_TITULOS:int = 21006;
		public static const TIPOPROC_TD_CHEQUES:int = 21007;
		public static const TIPOPROC_TD_OCORRENCIAS:int = 21008;
		public static const TIPOPROC_TD_PRORROGACAO:int = 21009;
		public static const TIPOPROC_TD_ALT_DADOS_CADASTRAIS:int = 21010;
		public static const TIPOPROC_TD_AVISO_VENCIMENTO:int = 21011;
		public static const TIPOPROC_TD_AVISO_VENCIMENTO_ITENS:int = 21012;
		public static const TIPOPROC_RENEGOCIACAOOPCREDITO:int = 21013;
		public static const TIPOPROC_TD_LIQUIDACAO_LOTE:int = 21014;
		public static const TIPOPROC_TD_LIQUIDACAO_TITULOS:int = 21015;
		public static const TIPOPROC_TD_TAXAS_MORA:int = 21016;
		public static const TIPOPROC_TD_OCORRENCIA_COM_PARAMETROS:int = 21017;
		public static const TIPOPROC_TD_PROTESTO:int = 21018;
		public static const TIPOPROC_TD_PARCELAS_LIQUIDACAO:int = 21019;
		public static const TIPOPROC_TD_PRORR:int = 21020;
		public static const TIPOPROC_MOVIMENTACAO_RECEBIVEIS:int = 21021;
		public static const TIPOPROC_COOPERATIVAS_RECEBIVEIS:int = 21022;
		public static const TIPOPROC_ARQUIVOS_RECEBIDOS:int = 21023;
		public static const TIPOPROC_COMPATIBILIZACAO:int = 21024;
		public static const TIPOPROC_ARQUIVOS_DETALHE:int = 21025;
		public static const TIPOPROC_IMPORTACAO_ARQUIVO:int = 21026;
		public static const TIPOPROC_IMPORTACAO_DETALHE_BORDERO:int = 21027;
		public static const TIPOPROC_TD_OCORRENCIA_RECEBIVEIS:int = 21028;
		public static const TIPOPROC_CONTA_CONTABIL_SELECAO:int = 21029;
		public static const TIPOPROC_TITULO_NP:int = 21030;
		public static const TIPOPROC_CUSTODIA_SIMPLES:int = 21031;
		public static const TIPOPROC_CONTRATO_LIQUIDACAO_CLIENTES:int = 21032;
		public static const TIPO_PROC_CLIENTE_POR_COOP:int = 21033;
		public static const TIPOPROC_TD_TITULOS_NP:int = 21034;
		public static const TIPOPROC_TD_MANUTENCAO_CUSTODIA:int = 21035;
		public static const TIPOPROC_TD_SOLICITACAO_OPERACAO_CUSTODIA:int = 21036;
		public static const TIPOPROC_TD_DOC_CUSTODIADO:int = 21037;
		public static const TIPOPROC_TD_COOPERATIVA_COM_CUSTODIA:int = 21038;
		public static const TIPOPROC_TD_CLIENTE_COOPERATIVA:int = 21039;
		public static const TIPOPROC_TD_CONFIGURACAO_VALOR_CUSTODIA:int = 21040;
		public static const TIPOPROC_TD_CADASTRO_COOPERATIVA_VALOR_CUSTODIA:int = 21041;
		public static const TIPOPROC_TD_CONTRATO_GARANTIA:int = 21042;
		public static const TIPOPROC_PARAM_SEG_CRED_ESPEC_PERFIL:int = 21043;
		public static const TIPOPROC_PARAM_SEG_CRED_ESPEC_CEDENTE:int = 21044;
		public static const TIPOPROC_TD_CPFS_INDESEJAVEIS:int = 21045;
		public static const TIPOPROC_TD_DOC_CUSTODIADO_COM_OCORRENCIA:int = 21046;

		//CRÉDITO MUTUO - 22000 - 22999
		public static const TIPO_PROPOSTA_MUTUO:int = 22000;
		public static const TIPOPROC_CM_PRORROGACAO:int = 22001;
		public static const TIPOPROC_RENEGOCIACAOOPCREDITO_EMP:int = 22002;     //Márden Stênio
		public static const TIPOPROC_TIPO_MODALIDADE:int = 22003;
		public static const TIPOPROC_CM_LIQUIDACAO_LOTE:int = 22004;
		public static const TIPOPROC_CM_LIQUIDACAO_TITULOS:int = 22005;
		public static const TIPOPROC_CM_PARCELAS:int = 22006;                            //Anderson Marcio
		public static const TIPOPROC_RECLASSIFICACAO_RISCO:int = 22007;                  //Anderson Marcio
		public static const TIPOPROC_RECLASSIFICACAO_RISCO_POR_CLIENTE:int = 22008;      //Anderson Marcio
		public static const TIPOPROC_CONTRATO_ROTATIVO:int = 22009;                      //João Carlos Schettino - 04/07/2002
		public static const TIPOPROC_ARQUIVO_FOLHA_PAGTO:int = 22010;
		public static const TIPOPROC_VLR_FINANC_FOLHA:int = 22011;
		public static const TIPOPROC_EMPRESAS_ARQ_FOLHA_PAGTO:int = 22012;
		public static const TIPOPROC_ORGAOS_ARQ_FOLHA_PAGTO:int = 22013;
		public static const TIPOPROC_ARQ_NAOEXISTE_VLR_FINANC:int = 22014;
		public static const TIPOPROC_ARQ_EXISTE_VLR_FINANC:int = 22015;
		public static const TIPOPROC_ARQ_PROC_VINC_VLR_FINANC:int = 22016;
		public static const TIPOPROC_RESUMO_CONCILIACAO:int = 22017;
		public static const TIPOPROC_CM_PROPOSTA_BNDES:int = 22018;
		public static const TIPOPROC_ARQUIVO_FOLHA_RETORNO:int = 22019;
		public static const TIPOPROC_ARQUIVO_FOLHA_RETORNO_DET:int = 22020;
		public static const TIPOPROC_TXJUROS_LANC_FOLHA:int = 22021;
		public static const TIPOPROC_ORGAOS_LANC_FOLHA:int = 22022;                     //Edson Lisboa - 12/03/2004
		public static const TIPOPROC_EMPRESAS_LANC_FOLHA:int = 22023;                   //Edson Lisboa - 12/03/2004
		public static const TIPOPROC_REMESSA:int = 22024;                               //Edson Lisboa - 02/04/2004
		public static const TIPOPROC_CONTRATO_MAE:int = 22025;                          //Edson Lisboa - 02/04/2004
		public static const TIPOPROC_CONTRATO_FILHOTE:int = 22026;                      //Edson Lisboa - 02/04/2004
		public static const TIPOPROC_TXJUROSCONVENIO_LANC_FOLHA:int = 22027;            //AlionesimoL  - 05/05/2004
		public static const TIPOPROC_PROPOSTA_CONTRATOMAE_EMP:int = 22028;
		public static const TIPOPROC_PROPOSTA_FILHOTE_EMP:int = 22029;
		public static const TIPOPROC_CONTRATOMAE_BAIXADO:int = 22030;
		public static const TIPOPROC_CONTRATOFILHOTE_BAIXADO:int = 22031;
		public static const TIPOPROC_CNAE_FINAME:int = 22032;                           //Igor Takaki - 04/01/2005
		public static const TIPOPROC_PRODUTO_BNDES:int = 22033;                         //Igor Takaki - 10/01/2005
		public static const TIPOPROC_CORRETOR_SEGUROS:int = 22034;
		public static const TIPOPROC_MUNICIPIO_IBGE:int = 22035;                        //Igor Takaki - 18/02/2005
		public static const TIPOPROC_FABRICANTE_PESSOA:int = 22036;                     //Igor Takaki - 24/02/2005
		public static const TIPOPROC_CNAE_FISCAL:int = 22037;                           //Igor Takaki - 14/03/2005
		public static const TIPOPROC_FM_PRORROGACAO_SEGURO:int = 22038;                 //AlionesimoL - 17/01/2006
		public static const TIPOPROC_PL:int = 22039;                                    //Igor Takaki - 14/02/2006
		public static const TIPOPROC_CLIENTES_BNDES:int = 22040;                        //Igor Takaki - 15/02/2006
		public static const TIPOPROC_PROPOSTA_AVERBACAO:int = 22041;                    //Stefanini - Kelly Meyre - 16/03/2006
		public static const TIPOPROC_ARQUIVO_AVERB_MANUT_RETORNO:int = 22042;           //Stefanini - Kelly Meyre - 05/04/2006
		public static const TIPOPROC_ARQUIVO_AVERB_MANUT_RETORNO_DET:int = 22043;       //Stefanini - Kelly Meyre - 05/04/2006
		public static const TIPOPROC_EMPRESAS_ARQ_AVERB_MANUT:int = 22044;              //Stefanini - Kelly Meyre - 05/04/2006
		public static const TIPOPROC_REPACTUACAO_TAXA_JUROS:int = 22045;                // - João Carlos Schettino 12/07/2007
		public static const TIPOPROC_SUBORGAO_LANC_FOLHA:int = 22046;                   //Stefanini - Aroldo A. Domingos 17/08/2007 - Dem 5235
		public static const TIPOPROC_CLIENTES_COM_CONTRATO_MUTUO:int = 22047;           //Stefanini - Antônio Fernando - 23/08/2007
		public static const TIPOPROC_CONTRATOSCM_POR_CLIENTE_COOP:int = 22048;          //Stefanini - Antônio Fernando - 23/08/2007
		public static const TIPOPROC_MODELO_PREENCH_PROPOSTA:int = 22049;               //Stefanini - Eduardo Cheng - 24/01/2008
		public static const TIPOPROC_ALT_PLANO_PAGAMENTO:int = 22050;                   //Stefanini - Humberto Salazar - 30/01/2008
		public static const TIPOPROC_ARQUIVO_FOLHA_PAGTO_CAPITAL:int = 22051;           //Stefanini - Kelly Meyre - 30/01/2008
		public static const TIPOPROC_AMORTIZACAO_SALDO_DEVEDOR:int = 22052;             //João Carlos Schettino - 08/05/2008
		public static const TIPOPROC_CLIENTE_POR_EMPRESA:int = 22053;                   //Stefanini - Marcus Fabricio - 30/10/2008
		public static const TIPOPROC_CONTRATOS_CM_POR_CLIENTE_VENCIMENTO:int = 22054;   //Stefanini - Marcus Fabricio - 30/10/2008
		public static const TIPOPROC_FAVORECIDO:int = 22055;                            //guilhermesctis - Guilherme de Mello e Silva
		public static const TIPOPROC_FAVORECIDOCLIENTE:int = 22056;                     //guilhermesctis - Guilherme de Mello e Silva
		public static const TIPOPROC_CLIENTES_COM_PROPOSTA_MUTUO:int = 22057;           //Stefanini - Michel Calheiros - 08/04/2009
		public static const TIPOPROC_PROPOSTACM_POR_CLIENTE_COOP:int = 22058;           //Stefanini - Michel Calheiros - 08/04/2009
		public static const TIPOPROC_ARQUIVO_FOLHA_SEM_RETORNO:int = 22059;             //Stefanini - Humberto Salazar - 01/04/2009
		public static const TIPOPROC_ARQUIVO_FOLHA_SEM_RETORNO_DET:int = 22060;         //Stefanini - Humberto Salazar - 01/04/2009
		public static const TIPOPROC_ARQUIVO_GLOSAS_RETORNO:int = 22061;                //Stefanini - Humberto Salazar - 23/08/2009
		public static const TIPOPROC_ARQUIVO_GLOSAS_RETORNO_DET:int = 22062;            //Stefanini - Humberto Salazar - 23/08/2009

		//FINANCIAMENTOS
		//CREDITO RURAL - 20000 - 20999
		public static const TIPOPROC_EMPREENDIMENTOS:int = 20000;
		public static const TIPOPROC_IRREGULARIDADES:int = 20001;
		public static const TIPOPROC_FISCALIZACAO:int = 20002;
		public static const TIPOPROC_MUNICIPIOSRECOR:int = 20003;
		public static const TIPOPROC_ORIGEMRECURSO:int = 20004;
		public static const TIPOPROC_OCORRENCIA:int = 20005;
		public static const TIPOPROC_SIMULACAO:int = 20007;
		public static const TIPOPROC_ASSISTENCIATECNICA:int = 20008;
		public static const TIPOPROC_EMPREENDIMENTORURAL:int = 20009;
		public static const TIPOPROC_PROPOSTARURAL:int = 20010;
		public static const TIPOPROC_CR_ALT_DADOS_CADASTRAIS:int = 20011;
		public static const TIPOPROC_CR_PRORROGACAO:int = 20012;
		public static const TIPOPROC_MODELO_INST_CREDITO:int = 20013;
		public static const TIPOPROC_MODELO_INST_CRED_CLAUSULA:int = 20014;
		public static const TIPOPROC_ADITIVOS_INST_CRED:int = 20015;
		public static const TIPOPROC_SEGURADORA:int = 20016;
		public static const TIPOPROC_TMP_PROTESTO_TITULO:int = 20017;
		public static const TIPOPROC_TMP_GARANTIA_OP_CRED:int = 20018;
		public static const TIPOPROC_PROGRAMASBNDES:int = 20019;
		public static const TIPOPROC_LIBERACAO:int = 20020;
		public static const TIPOPROC_PLANO_LIBERACAO:int = 20021;
		public static const TIPOPROC_HISTORICO_ALT_LIBERACAO:int = 20022;
		public static const TIPOPROC_PRORROGACAO:int = 20023;
		public static const TIPOPROC_RENEGOCIACAO:int = 20024;
		public static const TIPOPROC_EMPREENDIMENTO_RURAL_DEF:int = 20025;
		public static const TIPOPROC_GARANTIA_OP_CRED_DEF:int = 20026;
		public static const TIPOPROC_PROPOSTA_EFETIVADA:int = 20027;
		public static const TIPOPROC_EVENTO_PROPOSTA:int = 20028;
		public static const TIPOPROC_ITENS_ORCAMENTO:int = 20029;
		public static const TIPOPROC_TIPO_ORCAMENTO:int = 20030;
		public static const TIPOPROC_ORCAMENTO_PROPOSTA:int = 20031;
		public static const TIPOPROC_LIMITE_OPERACIONAL_PRODUTO:int = 20032;
		public static const TIPOPROC_PRODUTO_EMPREENDIMENTO_RURAL:int = 20033;
		public static const TIPOPROC_ORCAMENTO_EMP_RURAL:int = 20034;
		public static const TIPOPROC_MUNICIPIO_RECOR:int = 20035;
		public static const TIPOPROC_EMPREENDIMENTOS_CUSTEIO:int = 20036;
		public static const TIPOPROC_ITENS_TIPO_ORCAMENTO:int = 20037;
		public static const TIPOPROC_GARANTIA:int = 20038;
		public static const TIPOPROC_ENQUADRAMENTO_GARANTIA:int = 20039;
		public static const TIPOPROC_OP_BACEN:int = 20040;
		public static const TIPOPROC_SUB_OP_BACEN:int = 20041;
		public static const TIPOPROC_CONGLOMERADO:int = 20042;
		public static const TIPOPROC_APLICACAO_VINCULADA:int = 20043;
		public static const TIPO_PROC_GARANTIA_VINCULADA:int = 20044;
		public static const TIPOPROC_CM_ALT_DADOS_CADASTRAIS:int = 20045;
		public static const MSG_EXCLUSAO_LOTE_LACTO_CC:String = "Todas as parcelas deste lote foram debitadas no contas correntes e serão estornadas. Deseja excluir este lote?";
		public static const MSG_EXCLUSAO_LIQUIDACAO_LACTO_CC:String = "Esta liquidação foi debitada no contas correntes e será estornada do sistema. Deseja excluir esta liquidação?";		
		public static const MSG_PROIBIDO_ALT_DADOS_IMP:String = "Proibido Alterar Dados Importados";
		
		//Atenção!!
		//Este foi substituído pois existem 2 modulos na nova genlist que utilizam a mesma rotina,
		//porém estão em módulos diferentes:
		//(Credito Rural TIPOPROC_CR_ALT_DADOS_CADASTRAIS / Credito Mutuo - TIPOPROC_CM_ALT_DADOS_CADASTRAIS)
		//O valor 20045 está sendo utilizado pela constante
		//TIPOPROC_CM_ALT_DADOS_CADASTRAIS = 20045'. Com a nova estrutura do GenList
		//a alteração de dados cadastrais para os Produtos (CR-EMP) tem que utilizar
		//os valores num mesmo intervalo, pois a rotina de busca é a mesma......
		public static const TIPOPROC_DISTRIBUIDOR_PROP_BNDES:int = 20046;    //Distribuidor Autorizado
		public static const TIPOPROC_EQUIPAMENTO_PROP_BNDES:int = 20047;     //Equipamento BNDES
		public static const TIPOPROC_CR_PROPOSTA_BNDES:int = 20048;          //PropostaBNDES - Crédito Rural
		public static const TIPOPROC_CONTRATO_BNDES:int = 20049;            //ItalloM - 16/12/2004
		public static const TIPOPROC_PROPOSTA_CONTRATOMAE_RURAL:int = 20050; //AlionesimoL  - 08/11/2004
		public static const TIPOPROC_PROPOSTA_FILHOTE_RURAL:int = 20051;    //AlionesimoL  - 08/11/2004
		public static const TIPOPROC_EQUIPAMENTO_BNDES:int = 20052;         //ItalloM - 21/12/2004
		public static const TIPOPROC_CR_EXCL_OPER_RECOR:int = 20053;        //Andret - 01/02/2005
		public static const TIPOPROC_CR_CANCELAMENTO_SEGURO:int = 20054;    //João Carlos - 14/03/2005
		public static const TIPOPROC_CR_ENQUADRAMENTO_PRONAF:int = 20055;   //Márden Stênio - 25/07/2005
		public static const TIPOPROC_DAP:int = 20056;                       //Tiago de Souza- 22/11/2005
		public static const TIPOPROC_MOD_INST_CRED_AVISO_VENC:int = 20057;  //Stefanini - Eduardo Cheng - 29/05/2006
		public static const TIPOPROC_LIMITE_PRO_CAP_CRED:int = 20058;
		public static const TIPOPROC_LINHA:int = 20059;                     // - Antônio Fernando - Stefanini - 25/04/2007
		public static const TIPOPROC_PROGRAMA:int = 20060;                  // - Antônio Fernando - Stefanini - 25/04/2007
		public static const TIPOPROC_FINALIDADE:int = 20061;                // - Antônio Fernando - Stefanini - 04/04/2007
		public static const TIPOPROC_CONTRATO_MAPA:int = 20062;
		public static const TIPOPROC_IMPORTACAOARQBNDES:int = 20063;             //Antônio Fernando - Stefanini - 22/06/2007
		public static const TIPOPROC_CLIENTES_COM_CONTRATO_RURAL:int = 20064;    //Antonio Fernando - Stefanini - 23/08/2007
		public static const TIPOPROC_CONTRATOSCR_POR_CLIENTE_COOP:int = 20065;   //Antônio Fernando - Stefanini - 23/08/2007
		public static const TIPOPROC_CONTRATOS_MAPA:int = 20066;            //GilbertoRCTIS (Fluxo Funcafé)
		public static const TIPOPROC_PARAM_CONTRATOS_MAPA:int = 20067;      //GilbertoRCTIS (Fluxo Funcafé)
		public static const TIPOPROC_FINAL_CONTRATOS_MAPA:int = 20068;      //GilbertoRCTIS (Fluxo Funcafé)
		public static const TIPOPROC_RECEB_CONTRATOS_MAPA:int = 20069;      //GilbertoRCTIS (Fluxo Funcafé)
		public static const TIPOPROC_REMAN_CONTRATOS_MAPA:int = 20070;      //GilbertoRCTIS (Fluxo Funcafé)
		public static const TIPOPROC_PAGGRU_CONTRATOS_MAPA:int = 20071;     //GilbertoRCTIS (Fluxo Funcafé)
		public static const TIPOPROC_DEVREC_CONTRATOS_MAPA:int = 20072;     //GilbertoRCTIS (Fluxo Funcafé)
		public static const TIPOPROC_PAGTMS_CONTRATOS_MAPA:int = 20073;     //GilbertoRCTIS (Fluxo Funcafé)
		public static const TIPOPROC_CONSULTA_CEDULA_MAE:int = 20074;     //Maria Luiza MariaSCTIS
		public static const TIPOPROC_PARAMETRO_LINHA_CREDITO:int = 20075;     //Maria Luiza MariaSCTIS
		public static const TIPOPROC_PARAMETRO_LINHA_CREDITO_FILHOTE:int = 20076;     //Maria Luiza MariaSCTIS
		public static const TIPOPROC_PARAMETRO_BDINTEGRACAO_CEDULA_MAE:int = 20077;   //Maria Luiza MariaSCTIS
		public static const TIPOPROC_CONSULTA_CEDULA_FILHOTE:int = 20078;   //Maria Luiza MariaSCTIS
		public static const TIPOPROC_PROPOSTARURAL_MAE:int = 20079; //Maria Luiza mariasctis
		public static const TIPOPROC_CONSULTA_CONTRATO_MAE:int = 20080; //Maria Luiza mariasctis
		public static const TIPOPROC_CONSULTA_CONTRATO_FILHOTE:int = 20081; //Maria Luiza mariasctis
		public static const TIPOPROC_NUMERO_RECOR:int = 20082; //Maria Luiza mariasctis
		public static const TIPOPROC_ALTERA_DATA_LIMITE:int = 20083; //Maria Luiza mariasctis
		public static const TIPOPROC_PARAM_CONVERSAO:int = 20084; //AdilsonsCtis(Conversão FunCafé)
		public static const TIPOPROC_FINALIDADE_FUNCAFE:int = 20085; //AdilsonsCtis(Finalidade FunCafé)
		public static const TIPOPROC_REEMBO_CONTRATOS_MAPA:int = 20086;     //GilbertoRCTIS (Fluxo Funcafé)
		public static const TIPOPROC_CONSULTA_CONTRATOS_VINCULADOS:int = 20087; //AdilsonsCtis(Conversão FunCafé)
		public static const TIPOPROC_PROPOSTA_CONTRATO_MAE:int = 20088 //AdilsonsCtis(Conversão FunCafé)
		public static const TIPOPROC_CLIENTES_COM_CONTRATO_RURAL_FUNCAFE:int = 20089; //GilvaneyOCtis (Contratos FunCafé)
		public static const TIPOPROC_CLIENTES_COM_PROPOSTA:int = 20090;   //Michel Calheiros - Stefanini - 08/04/2009
		public static const TIPOPROC_PROPOSTA_POR_CLIENTE_COOP:int = 20091;   //Michel Calheiros - Stefanini - 08/04/2009

		//Constantes para o fechamento de protestos
		public static const COD_CRITERIO_PROTESTO_NORMAL:int = 0               //'Guilherme  30/06/2000 8-Desconto de Titulo
		public static const COD_CRITERIO_PROTESTO_NAO_ENCAMINHAR:int = 1       //'Guilherme  30/06/2000 8-Desconto de Titulo
		public static const COD_CRITERIO_PROTESTO_ENCAMINHAR:int = 2           //'Guilherme  30/06/2000 8-Desconto de Titulo
		public static const COD_CRITERIO_PROTESTO_ENCAMINHADO:int = 3          //'Guilherme  30/06/2000 8-Desconto de Titulo
		public static const COD_CRITERIO_PROTESTO_PROTESTADO:int = 4           //'Guilherme  30/06/2000 8-Desconto de Titulo
		
		//Encaminhamento Protesto
		public static const PROTESTO_NAO_ENCAMINHA:int = 0;
		public static const PROTESTO_ENCAMINHA_PARCELA:int = 1;
		public static const PROTESTO_ENCAMINHA_OPERACAO:int = 2;
		
		//Constantes de Tipo da Cooperativa
		public static const TIPO_COOPERATIVA_BANCOOB:int = 1;
		public static const TIPO_COOPERATIVA_CENTRAL:int = 2;
		public static const TIPO_COOPERATIVA_SINGULAR:int = 3;
		
		
		/*******************************************************************************************
		/' ESTRUTURAS DE TIPO HISTORICO - TÍTULO DESCONTADO ,CRÉDIO MUTUO E CRÉDITO RURAL
		/'   - COLOCAR EM ORDEM
		'*******************************************************************************************/
		//alteradas por Guilherme a pedido do Denio (valores) - 07/12/2000
		public static const COD_HISTORICO_ENTRADA_NORMAL:int = 1              ;//Luciano Araujo 19/02/2001
		public static const COD_HISTORICO_PRIMEIRA_REFORMA:int = 2            ;//Luciano Araujo 19/02/2001
		public static const COD_HISTORICO_ENTRADA_PARA_ACERTO:int = 5         ;//Luciano Araujo 19/02/2001
		public static const COD_HISTORICO_TRANSFERIDO_DE_CARTEIRA:int = 6     ;//Luciano Araujo 19/02/2001
		public static const COD_HISTORICO_JUROS_INCORPORADOS:int = 30         ;//Marcos Aurélio 11/01/2001  7-CM
		public static const COD_HISTORICO_PROTESTAR:int = 31                  ;//Guilherme  30/06/2000 8-Desconto de Titulo
		public static const COD_HISTORICO_ESTORNAR_PROTESTO:int = 32          ;//Guilherme  30/06/2000 8-Desconto de Titulo
		public static const COD_HISTORICO_SUSTAR_PROTESTO:int = 33            ;//Guilherme  30/06/2000 8-Desconto de Titulo
		public static const COD_HISTORICO_ESTORNAR_SUSTAR_PROTESTO:int = 34   ;//Guilherme  30/06/2000 8-Desconto de Titulo
		public static const COD_HISTORICO_QUITACAO_EMPRESTIMO:int = 48        ;//Marcos Aurélio 14/12/2000
		public static const COD_HISTORICO_QUITACAO_EMPRESTIMO_A_DEBITO:int = 49;//Marcos Aurélio 14/12/2000
		public static const COD_HISTORICO_APROPRIACAO_MENSAL_COMISSAO:int = 59;//Thiago Henrique Ramos da Mata 04/07/2008
		public static const COD_HISTORICO_PAGTO_PARCIAL:int = 65              ;//Clédison  26/07/2000 8-Desconto de Titulo
		public static const COD_HISTORICO_TRANS_CL:int = 70                   ;//Clédison  26/07/2000 8-Desconto de Titulo
		public static const COD_HISTORICO_CORRECAO_INCORPORADA:int = 71       ;//Marcos Aurélio 11/01/2001  7_CM
		public static const COD_HISTORICO_JUROS_A_INCORPORAR:int = 74         ;//Marcos Aurélio 11/01/2001  7-CM
		public static const COD_HISTORICO_CORRECAO_A_INCORPORAR:int = 75      ;//Marcos Aurélio 11/01/2001  7-CM
		public static const COD_HISTORICO_ALTERACAO_CADASTRAL_CM:int = 81     ;//Maurício  08/12/2000 7-Financiamento Mutuo
		public static const COD_HISTORICO_APROPRIACAO_RENDAS_MENSAL:int = 99  ;//Anderson  05/07/2000 8-Desconto de Titulo
		public static const COD_HISTORICO_CRITERIO_OPER_PRACA:int = 108       ;//Anderson  05/07/2000 8-Desconto de Titulo
		public static const COD_HISTORICO_CRITERIO_OPER_FORA_PRACA:int = 109  ;//Anderson  05/07/2000 8-Desconto de Titulo
		public static const COD_HISTORICO_CRITEIRO_OPER_CORRESP:int = 110     ;//Anderson  05/07/2000 8-Desconto de Titulo
		public static const COD_HISTORICO_TARIFA_MANUT_TIT_VENCIDO:int = 112  ;//Marcos Aurelio 29/08/2000  8-Desconto de Titulo
		public static const COD_HISTORICO_TARIFA_EMISSAO_AVISO:int = 114      ;//Gilson
		public static const COD_HISTORICO_TARIFA_CONTRATACAO_ATIVA:int = 116  ;//Anderson  05/07/2000 8-Desconto de Titulo
		public static const COD_HISTORICO_PORTE_EMISSAO_AVISO:int = 119       ;//Anderson  05/07/2000 8-Desconto de Titulo
		public static const COD_HISTORICO_TARIFA_LIB_VALOR_GARANTIDO:int = 124;//Wállace   16/04/2002 9-Cobrança
		public static const COD_HISTORICO_TRANS_EX_TD:int = 131               ;//Clédison  26/07/2000 8-Desconto de Titulo
		public static const COD_HISTORICO_TRANS_PREJUIZO:int = 132            ;//Clédison  26/07/2000 8-Desconto de Titulo
		public static const COD_HISTORICO_ESTORNO_CURSO_NORMAL:int = 140      ;//Marcos Aurélio 27/10/2000  8-Desconto de Títulos
		public static const COD_HISTORICO_ESTORNO_CURSO_ANORMAL:int = 141     ;//Marcos Aurélio 27/10/2000  8-Desconto de Títulos
		public static const COD_HISTORICO_DEBITO_SEGURO_PRESTAMISTA:int = 142 ;//Marcos Aurélio 06/12/2000  TD/CM/CR
		public static const COD_HISTORICO_DEBITO_FOLHA_PAGAMENTO:int = 147    ;//Anderson M 04/06/2003 - TD - EMP - CRU
		public static const COD_HISTORICO_ENVIO_DE_CH_PARA_COMPENSACAO:int = 149;//Anderson Marcio  26/12/2001 8 - Desconto de Títulos
		public static const COD_HISTORICO_ALT_AUMENTO_VLR_CONTR_ROTAT:int = 149 ;//João Carlos Schettino 18/03/2002 - 7 Emprestimo
		public static const COD_HISTORICO_ALT_REDUCAO_VLR_CONTR_ROTAT:int = 150 ;//João Carlos Schettino 18/03/2002 - 7 Emprestimo
		public static const COD_HISTORICO_DEBITO_PARCIAL_FOLHA_PAGTO:int = 162;//Luiz Cândido  23/03/2004 - 7 Empréstimo
		public static const COD_HISTORICO_DEBITO_MENSAL_IOF:int = 163           ;//AlionesimoL - 04/02/2004
		public static const COD_HISTORICO_TARIFA_CUSTODIA_DOCUMENTOS:int = 172;//AlionesimoL - 26/04/05 - Custodia titulos descontados
		public static const COD_HISTORICO_TARIFA_PRORROGACAO_DOC_CUSTODIADO:int = 173;//AlionesimoL - 26/04/05 - Custodia titulos descontados
		public static const COD_HISTORICO_TARIFA_RETIRADA_DOC_CUSTODIADO:int = 174;//AlionesimoL - 26/04/05 - Custodia titulos descontados
		public static const COD_HISTORICO_TARIFA_MUDANCA_CARTEIRA_DOC_CUSTODIADO:int = 175;//AlionesimoL - 26/04/05 - Custodia titulos descontados
		public static const COD_HISTORICO_TITULO_CUSTODIADO_COMPENSADO:int = 177 ;//AlionesimoL - 28-06-05
		public static const COD_HISTORICO_TITULO_CUSTODIADO_LIQUIDADO_CC:int = 178;//AlionesimoL - 07-07-2005 TIT
		public static const COD_HISTORICO_TITULO_CUSTODIADO_LIQUIDADO_SINGULAR:int = 179;//Alionesimo 15/07/05
		public static const COD_HISTORICO_TITULO_CUSTODIADO_PELA_CENTRAL:int = 180;//AlionesimoL - 07-07-2005 TIT
		public static const COD_HISTORICO_ENTRADA_CUSTODIA_SIMPLES:int = 183;//AlionesimoL - 22/08/05 - Titulos
		public static const COD_HISTORICO_ENTRADA_CUSTODIA_DESCONTO:int = 184;//AlionesimoL - 22/08/05 - Titulos
		public static const COD_HISTORICO_ENTRADA_CUSTODIA_GARANTIA:int = 185;//AlionesimoL - 22/08/05 - Titulos
		public static const COD_HISTORICO_RETIRADA_CUSTODIA_SIMPLES:int = 186;//AlionesimoL - 22/08/05 - Titulos
		public static const COD_HISTORICO_RETIRADA_CUSTODIA_DESCONTO:int = 187;//AlionesimoL - 22/08/05 - Titulos
		public static const COD_HISTORICO_RETIRADA_CUSTODIA_GARANTIA:int = 188;//AlionesimoL - 22/08/05 - Titulos
		public static const COD_HISTORICO_MUDANCA_CARTEIRA_SIMPLES_DESCONTO:int = 189;//AlionesimoL - 22/08/05 - Titulos
		public static const COD_HISTORICO_MUDANCA_CARTEIRA_GARANTIA_SIMPLES:int = 190;//AlionesimoL - 22/08/05 - Titulos
		public static const COD_HISTORICO_MUDANCA_CARTEIRA_SIMPLES_GARANTIA:int = 191;//AlionesimoL - 22/08/05 - Titulos
		public static const COD_HISTORICO_PAGAMENTO_PARCIAL_PASSIVO:int = 192;//Rogério Inácio CTIS - 02/05/06
		public static const COD_HISTORICO_DEBITO_AUTOMATICO_CHEQUE_CUSTODIA_DEVOLVIDO:int = 199   ;//AlionesimoL - 17/08/2006
		public static const COD_HISTORICO_LIQ_PARCELA_FERIAS_EM_FOLHA_PAGTO:int = 205;//Rogério Inácio CTIS - 05/03/2008
		
		public static const COD_HISTORICO_REG_RECEBIMENTO_RECURSO:int = 61       ;//Gilberto Xavier CTIS - 15/12/2008
		public static const COD_HISTORICO_APR_MENSAL_TMS_FLUXO_REPASSAR:int = 62 ;//Gilberto Xavier CTIS - 15/12/2008
		public static const COD_HISTORICO_APR_MENSAL_TMS_FLUXO_RESTITUIR:int = 63;//Gilberto Xavier CTIS - 15/12/2008
		public static const COD_HISTORICO_DEV_RECURSO_NAO_REPASSADO:int = 64     ;//Gilberto Xavier CTIS - 15/12/2008
		public static const COD_HISTORICO_PAG_RECURSO_FUNCAFE:int = 65           ;//Gilberto Xavier CTIS - 15/12/2008
		public static const COD_HISTORICO_REE_RECURSO_PAGO_MAPA:int = 66         ;//Gilberto Xavier CTIS - 15/12/2008
		public static const COD_HISTORICO_REM_RECURSO_NAO_REPASSADO:int = 67     ;//Gilberto Xavier CTIS - 15/12/2008
		public static const COD_HISTORICO_LIB_PASSIVO_REPASSADO:int = 68         ;//Gilberto Xavier CTIS - 15/12/2008
		public static const COD_HISTORICO_LIQ_PASSIVO:int = 69                   ;//Gilberto Xavier CTIS - 15/12/2008
				
		//constante para identificar o tipo de caracteristica especial
		public static const TIPO_SEM_CARACTERISTICA_ESPECIAL:int = 0;               //'João Carlos Schettino - Emprestimo e Rural - 13.11.2002
		public static const TIPO_TITULOS_DESCONTADOS:int = 3;                       //'João Carlos Schettino - Emprestimo e Rural - 20.11.2002
		public static const TIPO_DESCONTO_NP:int = 1;                               //'João Carlos Schettino - Emprestimo e Rural - 20.11.2002
		public static const TIPO_DESCONTO_CHEQUES:int = 2;                          //'João Carlos Schettino - Emprestimo e Rural - 20.11.2002
		public static const TIPO_DESCONTO_DUPLICATAS:int = 1;                      //'AlionesimoL - Emprestimo e Rural - 21.11.2002
		public static const TIPO_OUTROS_TITULOS_DESCONTADOS:int = 99;                //'João Carlos Schettino - Emprestimo e Rural - 20.11.2002
		public static const TIPO_OPERACOES_PROPRIAS_CONC_INSTITUICAO:int = 1;       //'João Carlos Schettino - Emprestimo e Rural - 20.11.2002
		
		//Variaveis para controle de tipo de juros
		public static const TIT_JUROS_POR_DENTRO:int = 0;                       //'Anderson 21/07/2000    8-Desconto de Titulos
		public static const TIT_JUROS_POR_FORA:int = 1;                         //'Anderson 19/09/2000    8-Desconto de Titulos
		public static const TIT_JUROS_EXPONENCIAL:int = 2;                      //'Marcos Aurelio 24/07/2000
		
		//Contantes para o tipo de crítica na concessão de crédito - lista 716
		public static const COD_CONCESSAO_NAO_CRITICA_E_CONCEDE_OPERACAO:int = 0   //'Gilberto Xavier 21/12/2006
		public static const COD_CONCESSAO_CRITICA_E_CONCEDE_OPERACAO:int = 1       //'Gilberto Xavier 21/12/2006
		public static const COD_CONCESSAO_CRITICA_E_NAO_CONCEDE_OPERACAO:int = 2   //'Gilberto Xavier 21/12/2006
		
		//Constante de código do cargo 'Contador'
        public static const COD_CARGO_CONTADOR:int = 15;
			
		//Corporativo - Utilizando de 24001 à 24999
		public static const TIPOPROC_PERFIL_TARIFARIO:int = 24001;
		public static const TIPOPROC_TARIFA:int = 24002;
		public static const TIPOPROC_TIPO_VALOR_CONTABIL:int = 24003;
		public static const TIPOPROC_BANCO:int = 24004;
		public static const TIPOPROC_AGENCIA:int = 24005;
		public static const TIPOPROC_OPERACAO:int = 24006;
		public static const TIPOPROC_HISTORICO_CONTABIL:int = 24007;
		public static const TIPOPROC_PERFIL_CONTABIL:int = 24008;
		public static const TIPOPROC_CONTA_CONTABIL:int = 24009;
		public static const TIPOPROC_INDICADORES:int = 24010;
		public static const TIPOPROC_LANCTO_CONTABIL:int = 24011;
		public static const TIPOPROC_LANCAMENTOS:int = 24012;
		public static const TIPOPROC_PERFIL_CONTABIL_OPERACAO:int = 24013;
		public static const TIPOPROC_COTACOES:int = 24014;
		public static const TIPOPROC_ARGUMENTO_CONTABIL:int = 24015;
		public static const TIPOPROC_ARGUMENTO_CONTABIL_VALOR:int = 24016;
		public static const TIPOPROC_VALORTARIFA:int = 24017;
		public static const TIPOPROC_PRODIND:int = 24018;
		public static const TIPOPROC_LANCAMENTO_CONTABIL:int = 24019;
		public static const TIPOPROC_TARIFA_PERFIL:int = 24020;
		public static const TIPOPROC_OCORRENCIAS:int = 24021;
		public static const TIPOPROC_TIPO_IOF:int = 24022;
		public static const TIPOPROC_AGENCIADEPOSITARIA:int = 24023;
		public static const TIPOPROC_FECH_PROCESSOS:int = 24024;
		public static const TIPOPROC_ALERTAS:int = 24025;
		public static const TIPO_PROC_PRODUTO:int = 24026;
		public static const TIPOPROC_PRODUTOCORP:int = 24027;
		public static const TIPOPROC_PROCESSOS:int = 24028;
		public static const TIPOPROC_PROCESSOS_DEPEN:int = 24029
		public static const TIPOPROC_PRODUTOS:int = 24030;
		public static const TIPOPROC_LANCTO_CONTABIL_PRODUTO:int = 24031;
		public static const TIPOPROC_PERFIL_OPERACAO:int = 24032;
		public static const TIPOPROC_NIVEIS_RISCO:int = 24033;
		public static const TIPOPROC_HIST_LANC_OPER:int = 24034;
		public static const TIPOPROC_HISTORICO_CREDITO:int = 24037;
		public static const TIPOPROC_HISTORICO_DEBITO:int = 24038;
		public static const TIPOPROC_HISTORICO:int = 24039;
		public static const TIPOPROC_NUM_RESERVADOS:int = 24040;
		public static const TIPOPROC_RESERVAR:int = 24041;
		public static const TIPOPROC_SPOOL_RELATORIOS:int = 24042;
		public static const TIPOPROC_CONTACONTABIL:int = 24043;
		public static const TIPOPROC_CONTACONTABILVALOR:int = 24044;
		public static const TIPOPROC_LISTASINTEGRIDADE:int = 24045;
		public static const TIPOPROC_CONTA_CONTABIL_ARG_CONTABIL:int = 24046;
		public static const TIPOPROC_COOPERATIVAS:int = 24047;
		public static const TIPOPROC_INDICES:int = 24048;
		public static const TIPOPROC_COTACAO:int = 24049;
		public static const TIPOPROC_CONTATERCEIRO:int = 24050;
		public static const TIPOPROC_CONTA_BACEN:int = 24051;
		public static const TIPOPROC_INDICE_IMPORTACAO:int = 24052;
		public static const TIPOPROC_VAR_PARAMETRO:int = 24053;
		public static const TIPOPROC_TIPO_TIT_OP_VAR:int = 24054;
		public static const TIPOPROC_GRUPO_OPERACOES_VAR:int = 24055;
		public static const TIPOPROC_GRUPO_OPERACOES_VAR2:int = 24056;
		public static const TIPOPROC_TIPO_TAXA_VAR:int = 24057;
		public static const TIPOPROC_VALOR_TAXA_VAR:int = 24058;
		public static const TIPOPROC_INFO_CALC_VAR:int = 24059;
		public static const TIPOPROC_EQUIP_ESTACAO_RETAGUARDA:int = 24060;           //'Wállace Sanches - 15/04/2003
		public static const TIPOPROC_ORIGEM_RECURSOS_RECOR:int = 24061;              //'Ítallo Matheus Costa - 25/08/2003
		public static const TIPOPROC_PROGRAMA_ORIGEM_RECURSOS_RECOR:int = 24062;     //'Ítallo Matheus Costa - 03/09/2003
		public static const TIPOPROC_CRITERIO_EXPURGO_ARQUIVOS:int = 24063;          //'Luiza Megumi Mikami - 07/10/2003
		public static const TIPOPROC_PARAMETROS_CLASSE_DIRETORIO:int = 24064;        //'Luiza Megumi Mikami - 09/10/2003
		public static const TIPOPROC_CONTRATO_MAE_CLIENTE:int = 24065;               //'AlionesimoL - 14/10/2003
		public static const TIPOPROC_CONTRATO_CREDITO_FILHOTE:int = 24066;           //'ALionesimoL - 17/10/2003
		public static const TIPOPROC_CONTRATO_CREDITO_NAO_FILHOTE:int = 24067;       //'ALionesimoL - 17/10/2003
		public static const TIPOPROC_PORTE_EMPRESA_BNDS:int = 24068                 //'ANDRÉ TERENZI - 23/10/2003
		public static const TIPOPROC_LEIAUTE:int = 24069                            //'Edson Lisboa - 06/11/2003
		public static const TIPOPROC_DETALHES_LEIAUTE:int = 24070                   //'Edson Lisboa - 06/11/2003
		public static const TIPOPROC_CNAE:int = 24071                               //'ItalloM - 13/11/2003
		public static const TIPOPROC_ERROS_LEIAUTE:int = 24072                      //'Igor Takaki - 08/12/2003
		public static const TIPOPROC_CONTA_CONTABIL_VALOR:int = 24073               //'Luiza Megumi Mikami - 09/12/2003
		public static const TIPOPROC_MUNICIPIO_INCENTIVADO_BNDES:int = 24074        //'ItalloM - 19/12/2003
		public static const TIPOPROC_TMP_COOBRIGACAO:int = 24075                    //'Igor Takaki - 16/01/2004
		public static const TIPOPROC_COOBRIGACAO:int = 24076                        //'Igor Takaki - 27/01/2004
		public static const TIPOPROC_ALT_COOBRIGACAO:int = 24077                    //'Igor TAkaki - 27/01/2004
		public static const TIPOPROC_LOTE_LIQ_COOBRIGACAO:int = 24078               //'Igor Takaki - 30/01/2004
		public static const TIPOPROC_TMP_LIQ_COOBRIGACAO:int = 24079                //'Igor Takaki - 30/01/2004
		public static const TIPOPROC_PERFIL_TAXAJUROS:int = 24080                   //'Edson Lisboa - 24/08/2004
		public static const TIPOPROC_SUBPERFIL_TAXAJUROS:int = 24081                //'Edson Lisboa - 24/08/2004
		public static const TIPOPROC_ARGUMENTO_TAXAJUROS:int = 24082                //'Edson Lisboa - 25/08/2004
		public static const TIPOPROC_ARGUMENTOVALOR_TAXAJUROS:int = 24083           //'Edson Lisboa - 25/08/2004
		public static const TIPOPROC_BANCOSPB:int = 24084                           //'Guilherme Bezerra - 31/08/2004
		public static const TIPOPROC_ARGUMENTO_SUBPERFIL_TAXAJUROS:int = 24085      //'Edson Lisboa - 02/09/2004
		public static const TIPOPROC_ARGUMENTOVALOR_SUBPERFIL_TAXAJUROS:int = 24086 //'Edson Lisboa - 02/09/2004
		public static const TIPOPROC_SPREAD_OPERACAO:int = 24087                    //'Edson Lisboa - 02/09/2004
		public static const TIPOPROC_SPREAD_SUBPERFIL_TAXAJUROS:int = 24088         //'Edson Lisboa - 02/09/2004
		public static const TIPOPROC_MELHORIA_GARANTIAS_BASICAS:int = 24089         //'Marden Stenio - 17/12/2004
		public static const TIPOPROC_PRAZO_PARA_AVALIACAO:int = 24090               //'Marden Stenio - 21/12/2004
		public static const TIPOPROC_MELHORIA_INTERVENIENCIA:int = 24091            //'Marden Stenio - 21/12/2004
		public static const TIPOPROC_MELHORIA_VINCULO_APLIC_FINANC:int = 24092      //'Marden Stenio - 27/12/2004
		public static const TIPOPROC_PERCENTUAL_SUFICIENCIA_GARANTIAS:int = 24093   //'Marden Stenio - 29/12/2004
		public static const TIPOPROC_ITENS_AVALIACAO_OPERACAO:int = 24094           //'Marden Stenio - 03/01/2005
		public static const TIPOPROC_PESO_AVALIACAO_OPERACAO:int = 24095            //'Marden Stenio - 03/01/2005
		public static const TIPOPROC_CONTRATO_MAE_CLIENTE_TODOS:int = 24096         //'Anderson M.   - 10/02/2005
		public static const TIPOPROC_CCSJUD_IMPORTACAO_ARQ_BDV:int = 24097          //'Wállace
		public static const TIPOPROC_TARIFA_BANCO_COR:int = 24098                   //'Flávio Martins - 30/09/2005
		public static const TIPOPROC_BANCO_TARIFA_CORRESP:int = 24099               //'Flávio Martins - 03/09/2005
		public static const TIPOPROC_FAIXA_CEP_MUNICIPIO_CAF:int = 24100            //'Fábio Diniz - CTIS - 04/10/2005
		public static const TIPOPROC_CEP_LOCALIDADE:int = 24101                     //'Fábio Diniz - CTIS - 10/10/2005
		public static const TIPOPROC_ESPECIE_DOCUMENTO:int = 24102                  //'Fábio Diniz - CTIS - 17/10/2005
		public static const TIPOPROC_MELHORIA_FUNDO_GARANTIDOR_CREDITO:int = 24103  //'AlionesimoL - 12/02/2007
		public static const TIPOPROC_TIPO_REG_CPMF:int = 24104                      //'EdsonFCtis - 13/02/2007
		public static const TIPOPROC_PARAM_CALCULO_DELCREDERE:int = 24105           //'Kelly Meyre - Stefanini - 06/10/2005
		public static const TIPOPROC_GRUPO_LEIAUTE:int = 24106                      //'Fábio Diniz - CTIS - 21/11/2005
		public static const TIPOPROC_GRUPO_LEIAUTE_DETALHES:int = 24107             //'Fábio Diniz - CTIS - 21/12/2005
		public static const TIPOPROC_DETALHES_LEIAUTE_ARQUIVO:int = 24108           //'Fábio Diniz - CTIS - 21/12/2005
		public static const TIPOPROC_CONSULTA_FERIADO_CAF:int = 24109               //'Luciano Lamounier - Stefanini - 16/02/2006
		public static const TIPOPROC_TIPOMOVIMENTO_BANCO_CORRESP:int = 24110        //'AlionesimoL 03/05/2007
		public static const TIPOPROC_MANTER_CEP:int = 24111                         //'Fábio Diniz - CTIS - 24/05/2007
		//'public static const TIPOPROC_IMPORTACAOARQBNDES:int = 24112                 //'Antônio Fernando - Stefanini - 22/06/2007
		public static const TIPOPROC_MELHORIA_VALOR_OPERACAO_PR_COOP:int = 24113    //'AlionesimoL - 04/07/2007
		public static const TIPOPROC_TXJUROS_CONVENIO_FINALIDADE:int = 24114        //'Marcus Fabricio - Stefanini - 20/05/2008
		public static const TIPOPROC_LEIAUTE_TIPO_NEGOCIO:int = 24115               //'Maria Luiza - mariasctis
		public static const TIPOPROC_DECLARACOESBNDES:int = 24116                   //'Michel Calheiros - 01/10/2008
				
		public static const COD_TIPO_VENCTO_NORMAL:int = 1             //'tipo de vencimento normal
		public static const COD_TIPO_VENCTO_AVISTA:int = 2             //'tipo de vencimento à vista
		public static const COD_TIPO_VENCTO_CONTRAAPRES:int = 3        //'tipo de vencimento contra apresentação
		
		public static const ADV_BLOQUEIO_MUITO_ALTO:String =                 "Confirma a quantidade de dias de bloqueio?"//11024
		
		//Atendimento - Utilizando de 06000 à 06999
		public static const TIPOPROC_COOPERATIVA:int = 6010;	
		public static const TIPOPROC_CENTRAIS_SINGULARES:int = 6022;
		
		//Constante de código Nota Promissoria		
		public static const COD_TIPO_CONTROLE_POR_CLIENTE:int = 2		
		public static const COD_SIT_EMIS_CONTRATO_EMITIDO:int = 2;
		public static const COD_SIT_EMIS_CONTRATO_REEMITIDO:int = 3; 
		public static const PAR_BOL_UTILIZA_SEG_PRESTAMISTA:int = 574;
		
		
		//Constantes para Origem Importacao - Lista 256
		public static const COD_ORIG_IMP_RETAGUARDA:int = 0;                    //'Wállace Sanches - 17.01.2001 - Cobrança
		public static const COD_ORIG_IMP_COMPENSACAO:int = 1;                   //'Wállace Sanches - 17.01.2001 - Cobrança
		public static const COD_ORIG_IMP_CAIXA:int = 2;                         //'Wállace Sanches - 17.01.2001 - Cobrança
		public static const COD_ORIG_IMP_ATM:int = 3;                           //'Wállace Sanches - 17.01.2001 - Cobrança
		public static const COD_ORIG_IMP_TITULOS_DESCONTADOS:int = 4;           //'Wállace Sanches - 17.01.2001 - Cobrança
		public static const COD_ORIG_IMP_CNAB:int = 5;                          //'Wállace Sanches - 17.01.2001 - Cobrança
		public static const COD_ORIG_IMP_CONTA_CAPITAL:int = 6;                 //'Wállace Sanches - 17.01.2001 - Cobrança
		public static const COD_ORIG_IMP_EMPRESTIMOS:int = 7;                   //'Wállace Sanches - 17.01.2001 - Cobrança
		public static const COD_ORIG_IMP_CREDITO_RURAL:int = 8;                 //'Wállace Sanches - 17.01.2001 - Cobrança
		public static const COD_ORIG_IMP_COBRANCA:int = 9;                      //'Iata Anderson   - 21.08.2007 - Cobrança
		public static const COD_ORIG_IMP_AGENDAMENTO:int = 10;                  //'Wállace Sanches - 20.09.2002 - Cobrança
		public static const COD_ORIGEM_FOLHA_PAGAMENTO:int = 11;                //'João Carlos 12/11/2004
		public static const COD_ORIG_IMP_CORRESPONDENTE_BANCARIO:int = 12;      //'Evilácio Ribeiro - 25.02.2005 - Caixa
		public static const COD_ORIG_IMP_CUSTODIA_RECEBIVEIS:int = 13;
		public static const COD_ORIG_IMP_BANCO_CORRESPONDENTE:int = 14;         //'Alionesimo - 07/11/2006
		public static const COD_ORIG_IMP_TIT_AGENDAMENTO:int = 15;              //'Carlos Júnior - 10/07/2006 - Cobrança
		public static const COD_ORIG_IMP_MODULO_CEDENTE:int = 16;               //'Carlos Júnior - 10/07/2006 - Cobrança		
		
		public static const LCTOContabilizacao_A_Fazer:int = 0;
		public static const LCTOContabilizacao_Feito:int = 1;
		
		//Tipo Historico
		public static const COD_INTERNO_HIST_ENTRADA_NORMAL:int = 1;
		public static const COD_INTERNO_HIST_LIQUIDACAO_NORMAL:int = 2;
		public static const COD_INTERNO_HIST_BAIXA_SIMPLES:int = 3;
		public static const COD_INTERNO_HIST_BAIXA_ACERTO:int = 4;
		public static const COD_INTERNO_HIST_DEBITO_CONTA_CEDENTE:int = 5;
		public static const COD_INTERNO_HIST_DEBITO_TERCEIROS:int = 6;
		public static const COD_INTERNO_HIST_TRANSFERENCIA_EX_TD_DEBITO_CONTA_CEDENTE:int = 7;
		public static const COD_INTERNO_HIST_TRANSFERENCIA_CREDITOS_LIQUIDACAO:int = 8;
		public static const COD_INTERNO_HIST_PAGAMENTO_PARCIAL:int = 9;
		public static const COD_INTERNO_HIST_USO_INTERNO:int = 10;
		public static const COD_INTERNO_HIST_TRANSFERENCIA_CARTEIRA_COBRANCA_SIMPLES:int = 11;
		public static const COD_INTERNO_HIST_QUITACAO_EMPRESTIMO_POS_FIXADO:int = 12;
		public static const COD_INTERNO_HIST_DEBITO_QUITACAO_EMPRESTIMO_POS_FIXADO:int = 13;
		public static const COD_INTERNO_HIST_ESTORNO_QUITACAO_POS_FIXADO:int = 14;
		public static const COD_INTERNO_HIST_RECUSA_DEBITO_POS_FIXADO:int = 15;
		public static const COD_INTERNO_HIST_CONFIRMACAO_DEBITO_CONTA_POS_FIXADO:int = 16;
		public static const COD_INTERNO_HIST_TRANSFERENCIA_DOMICILIO_BANCARIO_POS_FIXADO:int = 17;
		public static const COD_INTERNO_HIST_DEBITO_PARCIAL_CONTA_CEDENTE_PRE_FIXADO:int = 18;
		public static const COD_INTERNO_HIST_DEBITO_CONTA_AUTOMATICO:int = 19;
		public static const COD_INTERNO_HIST_CREDITO_CONTA_AUTOMATICO:int = 20;
		public static const COD_INTERNO_HIST_REPACTUACAO_NORMAL:int = 21;
		public static const COD_INTERNO_HIST_REPACTUACAO_DEBITO:int = 22;
		public static const COD_INTERNO_HIST_LIQUIDACAO_COBRANCA_SEM_REGISTRO:int = 23;
		public static const COD_INTERNO_HIST_LIQUIDACAO_VIA_COMPENSACAO:int = 24;
		public static const COD_INTERNO_HIST_DEBITO_FOLHA_PAGAMENTO:int = 25;
		public static const COD_INTERNO_HIST_REPACTUACAO_DEBITO_TERCEIROS:int = 26;
		public static const COD_INTERNO_HIST_DEBITO_QUITAÇÃO_FOLHA_PAGAMENTO:int = 27;
		public static const COD_INTERNO_HIST_TRANSFERIR_PREJUÍZO:int = 28;
		public static const COD_INTERNO_HIST_LIQUIDAÇÃO_CHEQUE:int = 29;
		public static const COD_INTERNO_HIST_PAGAMENTO_PARCIAL_FOLHA_PAGAMENTO:int = 40;
		public static const COD_INTERNO_HIST_LIQUIDAÇÃO_AGENDAMENTO:int = 41;
		public static const COD_INTERNO_HIST_LIQUIDACAO_OP_PASSIVAS:int = 42;
		public static const COD_INTERNO_HIST_QUITACAO_OP_PASSIVAS:int = 43;
		public static const COD_INTERNO_HIST_PROTESTO:int = 165;
		public static const COD_INT_HISTORICO_DEBITAR_TERCEIROS:int = 6;
		public static const COD_INT_HISTORICO_BAIXA_ACERTO:int = 4;
		
		//Critério Digitação de Nome/CPF no desconto de cheques
		public static const COD_CRITERIO_NAO_DIGITA:int = 1;               //Anderson Marcio        18/09/2000  8-Desconto de titulos
		public static const COD_CRITERIO_DIGITA:int = 2;                   //Anderson Marcio        18/09/2000  8-Desconto de titulos
		public static const COD_CRITERIO_DIGITA_NOME:int = 3;              //Anderson Marcio        18/09/2000  8-Desconto de titulos
		public static const COD_CRITERIO_DIGITA_CPF:int = 4;               //Anderson Marcio        18/09/2000  8-Desconto de titulos
		
		
		//Lista para definir tipos de obrigatoriedade de avalistas
        public static const TIT_AVALISTA_OBRIGATORIO:int = 0;
        public static const TIT_AVALISTA_PROIBIDO:int = 1;
        public static const AVALISTA_OPCIONAL:int = 2;
        
        public static const COD_CARTEIRA_BNDES:int = 12;
        public static const COD_CARTEIRA_CHEQUE_CUSTODIA:int = 16;
        public static const COD_CARTEIRA_CL:int = 4;
        public static const COD_CARTEIRA_COB_CAUCIONADA:int = 3;
        public static const COD_CARTEIRA_COB_SIMPLES:int = 1;
        public static const COD_CARTEIRA_COB_VINCULADA:int = 2;
        public static const COD_CARTEIRA_COOBRIGACAO:int = 14;
        public static const COD_CARTEIRA_CREDITO_RURAL:int = 10;
        public static const COD_CARTEIRA_EMPRESTIMO:int = 5;
        public static const COD_CARTEIRA_FIANCA:int = 8;
        public static const COD_CARTEIRA_FINANC:int = 9;
        public static const COD_CARTEIRA_OUTRAS_RESP:int = 11;
        public static const COD_CARTEIRA_PREJUIZO:int = 13;
        public static const COD_CARTEIRA_REP_REC_EXT:int = 7;
        public static const COD_CARTEIRA_REP_REC_INT:int = 6;
        public static const COD_CARTEIRA_SECURITIZACAO:int = 15;
        public static const COD_CARTEIRA_TD:int = 0;

		
		// Constantes de uso interno dos programas Conta Corrente, Convênio e Captação Remunerada
		public static const COD_ORIGEM_LOTE_INTERNO:int = 0;
		public static const COD_ORIGEM_LOTE_CAIXA:int = 1;
		public static const COD_ORIGEM_LOTE_OUTROS:int = 2;
		public static const COD_ORIGEM_LOTE_AUTO_ATENDIMENTO:int = 3;
		public static const COD_ORIGEM_LOTE_INTERNET_BANKING:int = 4;
		public static const COD_ORIGEM_LOTE_AGENDAMENTO:int = 5;
		public static const COD_ORIGEM_LOTE_COMPENSACAO:int = 14;
		
		public static const COD_LOTE_TIPO_OPERACAO_LANCAMENTO:int = 0;
		
		public static const ERR_FOLHA_CHEQUE_UTILIZADA:int = 30000;
		public static const ERR_CHEQUE_RESTRICAO:int = 12139;
		public static const ERR_CHEQUE_EXISTENTE_DEVOLUCAO:int = 6218;
		public static const ERR_SALDO_INSUFICIENTE_LANCAMENTO:int = 30001;
		
		//Constantes para a Situação do Arquivo de Movimentação Terceiros (ImportacaoTerceiros)
		public static const COD_IMP_MOV_TERC_RECEBIDO:int = 0;
		public static const COD_IMP_MOV_TERC_PROCESSADO:int = 1;            
		public static const COD_IMP_MOV_TERC_EXCLUIDO:int = 2;           
		public static const COD_IMP_MOV_TERC_RESTAURADO:int = 3;            
		public static const COD_IMP_MOV_TERC_CONSIST_COM_ERRO:int = 4;      
		public static const COD_IMP_MOV_TERC_CONSIST_SEM_ERRO:int = 5;      
		public static const COD_IMP_MOV_TERC_NAO_ENCONTRADO:int = 6;        
		public static const COD_IMP_MOV_TERC_REJEITADO_PROC_AUTO:int = 7;   
		public static const COD_IMP_MOV_TERC_EXCLUIDO_PROC_AUTO:int = 8;    
		public static const COD_IMP_MOV_TERC_RESTAURADO_PROC_AUTO:int = 9;  
		public static const COD_IMP_MOV_TERC_CONSIST_COM_ERRO_PROC_AUTO:int = 10;
		public static const COD_IMP_MOV_TERC_CONSIST_SEM_ERRO_PROC_AUTO:int = 11;
		public static const COD_IMP_MOV_TERC_NAO_ENCONTRADO_PROC_AUTO:int = 12;
		
		//CONSTANTES REFERENTES À LISTA 750 (TIPO DE PROCESSAMENTO IMPORTAÇÃO TERCEIROS)
		public static const COD_PROCESSAMENTO_MANUAL:int = 1;
		public static const COD_PROCESSAMENTO_AUTOMATICO:int = 2;
		public static const COD_PROCESSAMENTO_AUTOMATICO_CONTA_SALARIO:int = 3;
		
		//Constantes para tipagem de como deve ser efetuada a Geração da Remessa de Cheques Compensáveis (Parametro = 1591)
		public static const COD_TIPO_REMESSA_ARQUIVO_TEXTO:int       = 1; //remessa efetuada via geração de arquivos texto
		public static const COD_TIPO_REMESSA_REGISTRO_DB2:int        = 2; //remessa efetuada via geração de registro na base DB2
		public static const COD_TIPO_REMESSA_ARQUIVO_TEXTO_E_DB2:int = 3; //remessa efetuada via geração de arquivos texto e registro na base DB2
        
	}
}
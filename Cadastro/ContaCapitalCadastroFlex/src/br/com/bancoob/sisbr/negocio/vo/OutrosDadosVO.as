package br.com.bancoob.sisbr.negocio.vo
{
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.bancoob.sisbr.negocio.vo.OutrosDadosVO",
					    br.com.bancoob.sisbr.negocio.vo.OutrosDadosVO);

	public class OutrosDadosVO extends BancoobVO
	{
		private var _num_matricula:int;
		private var _data_matricula:IDateTime;
		private var _data_saida:IDateTime;
		private var _num_cliente:int;
		private var _num_conta_corrente:Number=0;
		private var _uidtrabalha:String;
		private var _valor_saldo_atu_integ:Number=0;
		private var _valor_saldo_atu_subsc:Number=0;
		private var _valor_saldo_atu_devolver:Number=0;
		private var _data_saldo_anterior:IDateTime;
		private var _valor_saldo_ant_integ:Number=0;
		private var _valor_saldo_ant_subsc:Number=0;
		private var _valor_saldo_ant_devolver:Number=0;
		private var _valor_saldo_med_real_acu:Number=0;
		private var _valor_saldo_med_pos_acu:Number=0;
		private var _valor_saldo_atu_divs:Number=0;
		private var _valor_saldo_ant_divs:Number=0;
		private var _cod_situacao:int;
		private var _bol_deb_indeterminado:Boolean;
		private var _valor_deb:Number=0;
		private var _cod_forma_deb:int;
		private var _data_vencimento_deb:IDateTime;
		private var _periodo_prox_deb:int;
		private var _tipo_periodo_deb:int;
		private var _bol_participa_rateio:Boolean;
		private var _cod_tipo_valor_debito:int;
		private var _idcondicao_associacao:int;
		private var _bol_direito_voto:Boolean;
		private var _bol_restricao_rateio:Boolean;
		private var _obs_restricao:String;
		private var _valor_saldo_med_adev_acu:Number=0;
		private var _valor_saldo_bloq_int:Number=0;
		private var _valor_saldo_integral_disp_acum:Number=0;
		
		private var _desc_condicao_associacao:String;
        private var _valor_atu_subsc:Number=0;
        private var _valor_atu_integ:Number=0;
        private var _valor_atu_devolver:Number=0;
        private var _valor_atu_divs:Number=0;
        private var _situacao:String;
        private var _id_forma_pagamento:int;
        private var _desc_forma_pagamento:String;
        private var _cod_pf_pj:int;
				
		private var _valor_cota:Number=0;
	    private var _valor_salario_base:Number=0;
	    private var _bol_lim_integral_cap_consignado:Boolean;
        private var _valor_limite_integral_minimo:Number=0;
	    private var _valor_limite_integral_maximo:Number=0;
		
		// NumMatricula
		public function get numMatricula():int
		{ return _num_matricula; }
		public function set numMatricula(vlr:int):void
		{ _num_matricula = vlr; }
		
		// DataMatricula
		public function get dataMatricula():IDateTime
		{ return _data_matricula; }
		public function set dataMatricula(vlr:IDateTime):void
		{ _data_matricula = vlr; }
		
		// DataSaida
		public function get dataSaida():IDateTime
		{ return _data_saida; }
		public function set dataSaida(vlr:IDateTime):void
		{ _data_saida = vlr; }
		
		// NumCliente
		public function get numCliente():int
		{ return _num_cliente; }
		public function set numCliente(vlr:int):void
		{ _num_cliente = vlr; }
		
		// NumContaCorrente
		public function get numContaCorrente():Number
		{ return _num_conta_corrente; }
		public function set numContaCorrente(vlr:Number):void
		{ _num_conta_corrente = vlr; }
		
		// UIDTrabalha
		public function get uIDTrabalha():String
		{ return _uidtrabalha; }
		public function set uIDTrabalha(vlr:String):void
		{ _uidtrabalha = vlr; }
		
		// ValorSaldoAtuInteg
		public function get valorSaldoAtuInteg():Number
		{ return _valor_saldo_atu_integ; }
		public function set valorSaldoAtuInteg(vlr:Number):void
		{ _valor_saldo_atu_integ = vlr; }
		
		// ValorSaldoAtuSubsc
		public function get valorSaldoAtuSubsc():Number
		{ return _valor_saldo_atu_subsc; }
		public function set valorSaldoAtuSubsc(vlr:Number):void
		{ _valor_saldo_atu_subsc = vlr; }
		
		// ValorSaldoAtuDevolver
		public function get valorSaldoAtuDevolver():Number
		{ return _valor_saldo_atu_devolver; }
		public function set valorSaldoAtuDevolver(vlr:Number):void
		{ _valor_saldo_atu_devolver = vlr; }
		
		// DataSaldoAnterior
		public function get dataSaldoAnterior():IDateTime
		{ return _data_saldo_anterior; }
		public function set dataSaldoAnterior(vlr:IDateTime):void
		{ _data_saldo_anterior = vlr; }
		
		// ValorSaldoAntInteg
		public function get valorSaldoAntInteg():Number
		{ return _valor_saldo_ant_integ; }
		public function set valorSaldoAntInteg(vlr:Number):void
		{ _valor_saldo_ant_integ = vlr; }
		
		// ValorSaldoAntSubsc
		public function get valorSaldoAntSubsc():Number
		{ return _valor_saldo_ant_subsc; }
		public function set valorSaldoAntSubsc(vlr:Number):void
		{ _valor_saldo_ant_subsc = vlr; }
		
		// ValorSaldoAntDevolver
		public function get valorSaldoAntDevolver():Number
		{ return _valor_saldo_ant_devolver; }
		public function set valorSaldoAntDevolver(vlr:Number):void
		{ _valor_saldo_ant_devolver = vlr; }
		
		// ValorSaldoMedRealAcu
		public function get valorSaldoMedRealAcu():Number
		{ return _valor_saldo_med_real_acu; }
		public function set valorSaldoMedRealAcu(vlr:Number):void
		{ _valor_saldo_med_real_acu = vlr; }
		
		// ValorSaldoMedPosAcu
		public function get valorSaldoMedPosAcu():Number
		{ return _valor_saldo_med_pos_acu; }
		public function set valorSaldoMedPosAcu(vlr:Number):void
		{ _valor_saldo_med_pos_acu = vlr; }
		
		// ValorSaldoAtuDivs
		public function get valorSaldoAtuDivs():Number
		{ return _valor_saldo_atu_divs; }
		public function set valorSaldoAtuDivs(vlr:Number):void
		{ _valor_saldo_atu_divs = vlr; }
		
		// ValorSaldoAntDivs
		public function get valorSaldoAntDivs():Number
		{ return _valor_saldo_ant_divs; }
		public function set valorSaldoAntDivs(vlr:Number):void
		{ _valor_saldo_ant_divs = vlr; }
		
		// CodSituacao
		public function get codSituacao():int
		{ return _cod_situacao; }
		public function set codSituacao(vlr:int):void
		{ _cod_situacao = vlr; }
		
		// BolDebIndeterminado
		public function get bolDebIndeterminado():Boolean
		{ return _bol_deb_indeterminado; }
		public function set bolDebIndeterminado(vlr:Boolean):void
		{ _bol_deb_indeterminado = vlr; }
		
		// ValorDeb
		public function get valorDeb():Number
		{ return _valor_deb; }
		public function set valorDeb(vlr:Number):void
		{ _valor_deb = vlr; }
		
		// CodFormaDeb
		public function get codFormaDeb():int
		{ return _cod_forma_deb; }
		public function set codFormaDeb(vlr:int):void
		{ _cod_forma_deb = vlr; }
		
		// DataVencimentoDeb
		public function get dataVencimentoDeb():IDateTime
		{ return _data_vencimento_deb; }
		public function set dataVencimentoDeb(vlr:IDateTime):void
		{ _data_vencimento_deb = vlr; }
		
		// PeriodoProxDeb
		public function get periodoProxDeb():int
		{ return _periodo_prox_deb; }
		public function set periodoProxDeb(vlr:int):void
		{ _periodo_prox_deb = vlr; }
		
		// TipoPeriodoDeb
		public function get tipoPeriodoDeb():int
		{ return _tipo_periodo_deb; }
		public function set tipoPeriodoDeb(vlr:int):void
		{ _tipo_periodo_deb = vlr; }
		
		// BolParticipaRateio
		public function get bolParticipaRateio():Boolean
		{ return _bol_participa_rateio; }
		public function set bolParticipaRateio(vlr:Boolean):void
		{ _bol_participa_rateio = vlr; }
		
		// CodTipoValorDebito
		public function get codTipoValorDebito():int
		{ return _cod_tipo_valor_debito; }
		public function set codTipoValorDebito(vlr:int):void
		{ _cod_tipo_valor_debito = vlr; }
		
		// IDCondicaoAssociacao
		public function get iDCondicaoAssociacao():int
		{ return _idcondicao_associacao; }
		public function set iDCondicaoAssociacao(vlr:int):void
		{ _idcondicao_associacao = vlr; }
		
		// BolDireitoVoto
		public function get bolDireitoVoto():Boolean
		{ return _bol_direito_voto; }
		public function set bolDireitoVoto(vlr:Boolean):void
		{ _bol_direito_voto = vlr; }
		
		// BolRestricaoRateio
		public function get bolRestricaoRateio():Boolean
		{ return _bol_restricao_rateio; }
		public function set bolRestricaoRateio(vlr:Boolean):void
		{ _bol_restricao_rateio = vlr; }
		
		// ObsRestricao
		public function get obsRestricao():String
		{ return _obs_restricao; }
		public function set obsRestricao(vlr:String):void
		{ _obs_restricao = vlr; }
		
		// ValorSaldoMedADevAcu
		public function get valorSaldoMedADevAcu():Number
		{ return _valor_saldo_med_adev_acu; }
		public function set valorSaldoMedADevAcu(vlr:Number):void
		{ _valor_saldo_med_adev_acu = vlr; }
		
		// ValorSaldoBloqInt
		public function get valorSaldoBloqInt():Number
		{ return _valor_saldo_bloq_int; }
		public function set valorSaldoBloqInt(vlr:Number):void
		{ _valor_saldo_bloq_int = vlr; }
		
		// ValorSaldoIntegralDispAcum
		public function get valorSaldoIntegralDispAcum():Number
		{ return _valor_saldo_integral_disp_acum; }
		public function set valorSaldoIntegralDispAcum(vlr:Number):void
		{ _valor_saldo_integral_disp_acum = vlr; }
		
		// DescCondicaoAssociacao
		public function get descCondicaoAssociacao():String
		{ return _desc_condicao_associacao; }
		public function set descCondicaoAssociacao(vlr:String):void
		{ _desc_condicao_associacao = vlr; }
		
				// valorAtuSubsc
		public function get valorAtuSubsc():Number
		{ return _valor_atu_subsc; }
		public function set valorAtuSubsc(vlr:Number):void
		{ _valor_atu_subsc = vlr; }
		
		// valorAtuInteg
		public function get valorAtuInteg():Number
		{ return _valor_atu_integ; }
		public function set valorAtuInteg(vlr:Number):void
		{ _valor_atu_integ = vlr; }
		
		// valorAtuDevolver
		public function get valorAtuDevolver():Number
		{ return _valor_atu_devolver; }
		public function set valorAtuDevolver(vlr:Number):void
		{ _valor_atu_devolver = vlr; }
		
		// valorAtuDivs
		public function get valorAtuDivs():Number
		{ return _valor_atu_divs; }
		public function set valorAtuDivs(vlr:Number):void
		{ _valor_atu_divs = vlr; }
		
		// situacao
		public function get situacao():String
		{ return _situacao; }
		public function set situacao(vlr:String):void
		{ _situacao = vlr; }
		
		// codPfPj
		public function get codPfPj():int
		{ return _cod_pf_pj; }
		public function set codPfPj(vlr:int):void
		{ _cod_pf_pj = vlr; }
		
				// idFormaPagamento
		public function get idFormaPagamento():int
		{ return _id_forma_pagamento; }
		public function set idFormaPagamento(vlr:int):void
		{ _id_forma_pagamento = vlr; }
		
		// descFormaPagamento
		public function get descFormaPagamento():String
		{ return _desc_forma_pagamento; }
		public function set descFormaPagamento(vlr:String):void
		{ _desc_forma_pagamento = vlr; }
		
				// valorCota
		public function get valorCota():Number
		{ return _valor_cota; }
		public function set valorCota(vlr:Number):void
		{ _valor_cota = vlr; }
		
		// valorSalarioBase
		public function get valorSalarioBase():Number
		{ return _valor_salario_base; }
		public function set valorSalarioBase(vlr:Number):void
		{ _valor_salario_base = vlr; }
		
		// bolLimIntegralCapConsignado
		public function get bolLimIntegralCapConsignado():Boolean
		{ return _bol_lim_integral_cap_consignado; }
		public function set bolLimIntegralCapConsignado(vlr:Boolean):void
		{ _bol_lim_integral_cap_consignado = vlr; }
		
		// valorLimiteIntegralMinimo
		public function get valorLimiteIntegralMinimo():Number
		{ return _valor_limite_integral_minimo; }
		public function set valorLimiteIntegralMinimo(vlr:Number):void
		{ _valor_limite_integral_minimo = vlr; }
		
		// valorLimiteIntegralMaximo
		public function get valorLimiteIntegralMaximo():Number
		{ return _valor_limite_integral_maximo; }
		public function set valorLimiteIntegralMaximo(vlr:Number):void
		{ _valor_limite_integral_maximo = vlr; }
	}
}
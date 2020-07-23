package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.BancoobVO;
	
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.ContratoLiquidacaoVO", ContratoLiquidacaoVO);
	public class ContratoLiquidacaoVO extends BancoobVO {
		
		private var _idProduto:Number;
		private var _idModalidadeProduto:Number;
		private var _idOrigemOperacaoCredito:Number;
		private var _numDiasAtraso:Number;
		private var _numOperacaoCredito:Number;
		private var _qtdParcelasOperacao:Number;
		private var _qtdParcelasAtraso:Number;
		private var _qtdParcelasAberto:Number;
		
		private var _descOperacaoCredito:String;
		private var _descProduto:String;
		private var _descLinha:String;
		private var _descTaxaJuros:String;
		private var _idNivelRisco:String;
		private var _descErro:String;
		
		private var _dataVencimento:IDateTime;
		private var _dataUltimaLiquidacao:IDateTime;
		private var _dataEntradaOperacao:IDateTime;
		
		private var _idOperacaoSISBR20:Number;
		
		private var _bolLegado:Boolean;
		private var _bolErro:Boolean;
		private var _bolRotativo:Boolean;
		
		private var _valorPrincipal:Number;
		private var _valorRendas:Number;
		private var _valorMulta:Number;
		private var _valorMora:Number;
		private var _valorOutrosEncargos:Number;
		private var _valorTotalEncargosAtraso:Number;
		private var _valorAtrasoRenegociado:Number;
		private var _valorContratado:Number;
		private var _valorSaldoContabil:Number;
		private var _valorSaldoGanhoAAuferir:Number;
		private var _valorCreditoIOF:Number;
		private var _valorQuitacao:Number;
		private var _percAliquotaDiarioIOF:Number;
		private var _percAliquotaRelativaIOF:Number;

		public function copiarAtributos(obj:Object):void {
			idProduto = obj.idProduto;
			idModalidadeProduto = obj.idModalidadeProduto;
			idOrigemOperacaoCredito = obj.idOrigemOperacaoCredito;
			numDiasAtraso = obj.numDiasAtraso;
			numOperacaoCredito = obj.numOperacaoCredito;
			qtdParcelasOperacao = obj.qtdParcelasOperacao;
			qtdParcelasAtraso = obj.qtdParcelasAtraso;
			qtdParcelasAberto = obj.qtdParcelasAberto;
			descOperacaoCredito = obj.descOperacaoCredito;
			descProduto = obj.descProduto;
			descLinha = obj.descLinha;
			descTaxaJuros = obj.descTaxaJuros;
			idNivelRisco = obj.idNivelRisco;
			descErro = obj.descErro;
			dataVencimento = obj.dataVencimento;
			dataUltimaLiquidacao = obj.dataUltimaLiquidacao;
			dataEntradaOperacao = obj.dataEntradaOperacao;
			idOperacaoSISBR20 = obj.idOperacaoSISBR20;
			bolLegado = obj.bolLegado;
			bolErro = obj.bolErro;
			bolRotativo = obj.bolRotativo;
			valorPrincipal = obj.valorPrincipal;
			valorRendas = obj.valorRendas;
			valorMulta = obj.valorMulta;
			valorMora = obj.valorMora;
			valorOutrosEncargos = obj.valorOutrosEncargos;
			valorTotalEncargosAtraso = obj.valorTotalEncargosAtraso;
			valorAtrasoRenegociado = obj.valorAtrasoRenegociado;
			valorContratado = obj.valorContratado;
			valorSaldoContabil = obj.valorSaldoContabil;
			valorSaldoGanhoAAuferir = obj.valorSaldoGanhoAAuferir;
			valorCreditoIOF = obj.valorCreditoIOF;
			valorQuitacao = obj.valorQuitacao;
			percAliquotaDiarioIOF = obj.percAliquotaDiarioIOF;
			percAliquotaRelativaIOF = obj.percAliquotaRelativaIOF;
		}
		
		public function get percAliquotaRelativaIOF():Number
		{
			return _percAliquotaRelativaIOF;
		}

		public function set percAliquotaRelativaIOF(value:Number):void
		{
			_percAliquotaRelativaIOF = value;
		}

		public function get percAliquotaDiarioIOF():Number
		{
			return _percAliquotaDiarioIOF;
		}

		public function set percAliquotaDiarioIOF(value:Number):void
		{
			_percAliquotaDiarioIOF = value;
		}

		public function get valorQuitacao():Number
		{
			return _valorQuitacao;
		}

		public function set valorQuitacao(value:Number):void
		{
			_valorQuitacao = value;
		}

		public function get valorCreditoIOF():Number
		{
			return _valorCreditoIOF;
		}

		public function set valorCreditoIOF(value:Number):void
		{
			_valorCreditoIOF = value;
		}

		public function get valorSaldoGanhoAAuferir():Number
		{
			return _valorSaldoGanhoAAuferir;
		}

		public function set valorSaldoGanhoAAuferir(value:Number):void
		{
			_valorSaldoGanhoAAuferir = value;
		}

		public function get valorSaldoContabil():Number
		{
			return _valorSaldoContabil;
		}

		public function set valorSaldoContabil(value:Number):void
		{
			_valorSaldoContabil = value;
		}

		public function get valorContratado():Number
		{
			return _valorContratado;
		}

		public function set valorContratado(value:Number):void
		{
			_valorContratado = value;
		}

		public function get valorAtrasoRenegociado():Number
		{
			return _valorAtrasoRenegociado;
		}

		public function set valorAtrasoRenegociado(value:Number):void
		{
			_valorAtrasoRenegociado = value;
		}

		public function get valorTotalEncargosAtraso():Number
		{
			return _valorTotalEncargosAtraso;
		}

		public function set valorTotalEncargosAtraso(value:Number):void
		{
			_valorTotalEncargosAtraso = value;
		}

		public function get valorOutrosEncargos():Number
		{
			return _valorOutrosEncargos;
		}

		public function set valorOutrosEncargos(value:Number):void
		{
			_valorOutrosEncargos = value;
		}

		public function get valorMora():Number
		{
			return _valorMora;
		}

		public function set valorMora(value:Number):void
		{
			_valorMora = value;
		}

		public function get valorMulta():Number
		{
			return _valorMulta;
		}

		public function set valorMulta(value:Number):void
		{
			_valorMulta = value;
		}

		public function get valorRendas():Number
		{
			return _valorRendas;
		}

		public function set valorRendas(value:Number):void
		{
			_valorRendas = value;
		}

		public function get valorPrincipal():Number
		{
			return _valorPrincipal;
		}

		public function set valorPrincipal(value:Number):void
		{
			_valorPrincipal = value;
		}

		public function get bolRotativo():Boolean
		{
			return _bolRotativo;
		}

		public function set bolRotativo(value:Boolean):void
		{
			_bolRotativo = value;
		}

		public function get bolErro():Boolean
		{
			return _bolErro;
		}

		public function set bolErro(value:Boolean):void
		{
			_bolErro = value;
		}

		public function get bolLegado():Boolean
		{
			return _bolLegado;
		}

		public function set bolLegado(value:Boolean):void
		{
			_bolLegado = value;
		}

		public function get idOperacaoSISBR20():Number
		{
			return _idOperacaoSISBR20;
		}

		public function set idOperacaoSISBR20(value:Number):void
		{
			_idOperacaoSISBR20 = value;
		}

		public function get dataEntradaOperacao():IDateTime
		{
			return _dataEntradaOperacao;
		}

		public function set dataEntradaOperacao(value:IDateTime):void
		{
			_dataEntradaOperacao = value;
		}

		public function get dataUltimaLiquidacao():IDateTime
		{
			return _dataUltimaLiquidacao;
		}

		public function set dataUltimaLiquidacao(value:IDateTime):void
		{
			_dataUltimaLiquidacao = value;
		}

		public function get dataVencimento():IDateTime
		{
			return _dataVencimento;
		}

		public function set dataVencimento(value:IDateTime):void
		{
			_dataVencimento = value;
		}

		public function get descErro():String
		{
			return _descErro;
		}

		public function set descErro(value:String):void
		{
			_descErro = value;
		}

		public function get idNivelRisco():String
		{
			return _idNivelRisco;
		}

		public function set idNivelRisco(value:String):void
		{
			_idNivelRisco = value;
		}

		public function get descTaxaJuros():String
		{
			return _descTaxaJuros;
		}

		public function set descTaxaJuros(value:String):void
		{
			_descTaxaJuros = value;
		}

		public function get descLinha():String
		{
			return _descLinha;
		}

		public function set descLinha(value:String):void
		{
			_descLinha = value;
		}

		public function get descProduto():String
		{
			return _descProduto;
		}

		public function set descProduto(value:String):void
		{
			_descProduto = value;
		}

		public function get descOperacaoCredito():String
		{
			return _descOperacaoCredito;
		}

		public function set descOperacaoCredito(value:String):void
		{
			_descOperacaoCredito = value;
		}

		public function get qtdParcelasAberto():Number
		{
			return _qtdParcelasAberto;
		}

		public function set qtdParcelasAberto(value:Number):void
		{
			_qtdParcelasAberto = value;
		}

		public function get qtdParcelasAtraso():Number
		{
			return _qtdParcelasAtraso;
		}

		public function set qtdParcelasAtraso(value:Number):void
		{
			_qtdParcelasAtraso = value;
		}

		public function get qtdParcelasOperacao():Number
		{
			return _qtdParcelasOperacao;
		}

		public function set qtdParcelasOperacao(value:Number):void
		{
			_qtdParcelasOperacao = value;
		}

		public function get numOperacaoCredito():Number
		{
			return _numOperacaoCredito;
		}

		public function set numOperacaoCredito(value:Number):void
		{
			_numOperacaoCredito = value;
		}

		public function get numDiasAtraso():Number
		{
			return _numDiasAtraso;
		}

		public function set numDiasAtraso(value:Number):void
		{
			_numDiasAtraso = value;
		}

		public function get idOrigemOperacaoCredito():Number
		{
			return _idOrigemOperacaoCredito;
		}

		public function set idOrigemOperacaoCredito(value:Number):void
		{
			_idOrigemOperacaoCredito = value;
		}

		public function get idModalidadeProduto():Number
		{
			return _idModalidadeProduto;
		}

		public function set idModalidadeProduto(value:Number):void
		{
			_idModalidadeProduto = value;
		}

		public function get idProduto():Number
		{
			return _idProduto;
		}

		public function set idProduto(value:Number):void
		{
			_idProduto = value;
		}

	}
}
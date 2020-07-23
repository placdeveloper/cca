package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.BancoobVO;

	/**
	 * @author: marco.nascimento
	 */
	registerClassAlias("br.com.sicoob.sisbr.cca.replicacao.vo.ReplicacaoContaCapitalLegadoVO", ReplicacaoContaCapitalLegadoVO);
	public class ReplicacaoContaCapitalLegadoVO extends BancoobVO {
		
		private var _idReplicacaoCCA:Number;
		private var _idContaCapital:Number;
		private var _dataSaida:IDateTime;
		private var _idTabelaReplicadaCCA:Number;
		private var _idSituacaoReplicacaoCCA:Number;
		private var _codAcao:String;
		private var _descChaveReplicacaoSQL:String;
		private var _descChaveReplicacaoDB2:String;
		private var _numCooperativa:Number;
		private var _idInstituicao:Number;
		private var _descMensagemReplicacao:String;
		private var _siglaTabela:String;
		private var _numMatricula:Number;
		private var _numCliente:Number;
		private var _codSituacao:Number;
		private var _valorSaldoAtuInteg:Number;
		private var _valorSaldoAtuSubsc:Number;
		private var _valorSaldoAtuDevolver:Number;
		private var _valorSaldoBloqInt:Number;
		private var _selecionado:Boolean;
		private var _dataHoraCadastro:IDateTime;
		private var _dataHoraReplicacao:IDateTime;
		private var _dataMatricula:IDateTime;
		
		
		private var _dataVencParcela:IDateTime;
		private var _dataSituacaoParcela:IDateTime;
		private var _numParcelamento:Number;
		private var _numParcela:Number;
		private var _codTipoParcelamento:Number;
		private var _codModoLanc:Number;
		private var _codSituacaoParcela:Number;
		private var _codMotivoDevolucao:Number;
		private var _valorParcela:Number;
		private var _idParcelamentoContaCapital:Number;
		private var _numContaCorrente:String;
		private var _uIDTrabalha:String;
		private var _descObservacao:String;
		
		private var _dataLote:IDateTime;
		private var _dataHoraInclusao:IDateTime;
		private var _descNumDocumento:String;
		private var _idUsuarioResp:String;
		private var _numLoteLanc:Number;
		private var _numSeqLanc:Number;
		private var _valorLanc:Number;
		private var _bolAtualizado:Number;
		private var _idTipoHistoricoLanc:Number;
		private var _idTipoHistoricoEstorno:Number;

		
		public function get idReplicacaoCCA():Number
		{
			return _idReplicacaoCCA;
		}

		public function set idReplicacaoCCA(value:Number):void
		{
			_idReplicacaoCCA = value;
		}

		public function get idTabelaReplicadaCCA():Number
		{
			return _idTabelaReplicadaCCA;
		}

		public function set idTabelaReplicadaCCA(value:Number):void
		{
			_idTabelaReplicadaCCA = value;
		}

		public function get idSituacaoReplicacaoCCA():Number
		{
			return _idSituacaoReplicacaoCCA;
		}

		public function set idSituacaoReplicacaoCCA(value:Number):void
		{
			_idSituacaoReplicacaoCCA = value;
		}

		public function get codAcao():String
		{
			return _codAcao;
		}

		public function set codAcao(value:String):void
		{
			_codAcao = value;
		}

		public function get descChaveReplicacaoSQL():String
		{
			return _descChaveReplicacaoSQL;
		}

		public function set descChaveReplicacaoSQL(value:String):void
		{
			_descChaveReplicacaoSQL = value;
		}

		public function get descChaveReplicacaoDB2():String
		{
			return _descChaveReplicacaoDB2;
		}

		public function set descChaveReplicacaoDB2(value:String):void
		{
			_descChaveReplicacaoDB2 = value;
		}

		public function get numCooperativa():Number
		{
			return _numCooperativa;
		}

		public function set numCooperativa(value:Number):void
		{
			_numCooperativa = value;
		}

		public function get idInstituicao():Number
		{
			return _idInstituicao;
		}

		public function set idInstituicao(value:Number):void
		{
			_idInstituicao = value;
		}

		public function get descMensagemReplicacao():String
		{
			return _descMensagemReplicacao;
		}

		public function set descMensagemReplicacao(value:String):void
		{
			_descMensagemReplicacao = value;
		}

		public function get siglaTabela():String
		{
			return _siglaTabela;
		}

		public function set siglaTabela(value:String):void
		{
			_siglaTabela = value;
		}

		public function get numMatricula():Number
		{
			return _numMatricula;
		}

		public function set numMatricula(value:Number):void
		{
			_numMatricula = value;
		}

		public function get numCliente():Number
		{
			return _numCliente;
		}

		public function set numCliente(value:Number):void
		{
			_numCliente = value;
		}

		public function get codSituacao():Number
		{
			return _codSituacao;
		}

		public function set codSituacao(value:Number):void
		{
			_codSituacao = value;
		}

		public function get valorSaldoAtuInteg():Number
		{
			return _valorSaldoAtuInteg;
		}

		public function set valorSaldoAtuInteg(value:Number):void
		{
			_valorSaldoAtuInteg = value;
		}

		public function get valorSaldoAtuSubsc():Number
		{
			return _valorSaldoAtuSubsc;
		}

		public function set valorSaldoAtuSubsc(value:Number):void
		{
			_valorSaldoAtuSubsc = value;
		}

		public function get valorSaldoAtuDevolver():Number
		{
			return _valorSaldoAtuDevolver;
		}

		public function set valorSaldoAtuDevolver(value:Number):void
		{
			_valorSaldoAtuDevolver = value;
		}

		public function get valorSaldoBloqInt():Number
		{
			return _valorSaldoBloqInt;
		}

		public function set valorSaldoBloqInt(value:Number):void
		{
			_valorSaldoBloqInt = value;
		}

		public function get selecionado():Boolean
		{
			return _selecionado;
		}

		public function set selecionado(value:Boolean):void
		{
			_selecionado = value;
		}

		public function get dataHoraCadastro():IDateTime
		{
			return _dataHoraCadastro;
		}

		public function set dataHoraCadastro(value:IDateTime):void
		{
			_dataHoraCadastro = value;
		}

		public function get dataHoraReplicacao():IDateTime
		{
			return _dataHoraReplicacao;
		}

		public function set dataHoraReplicacao(value:IDateTime):void
		{
			_dataHoraReplicacao = value;
		}

		public function get dataMatricula():IDateTime
		{
			return _dataMatricula;
		}

		public function set dataMatricula(value:IDateTime):void
		{
			_dataMatricula = value;
		}

		public function get dataVencParcela():IDateTime
		{
			return _dataVencParcela;
		}

		public function set dataVencParcela(value:IDateTime):void
		{
			_dataVencParcela = value;
		}

		public function get dataSituacaoParcela():IDateTime
		{
			return _dataSituacaoParcela;
		}

		public function set dataSituacaoParcela(value:IDateTime):void
		{
			_dataSituacaoParcela = value;
		}

		public function get numParcelamento():Number
		{
			return _numParcelamento;
		}

		public function set numParcelamento(value:Number):void
		{
			_numParcelamento = value;
		}

		public function get numParcela():Number
		{
			return _numParcela;
		}

		public function set numParcela(value:Number):void
		{
			_numParcela = value;
		}

		public function get codTipoParcelamento():Number
		{
			return _codTipoParcelamento;
		}

		public function set codTipoParcelamento(value:Number):void
		{
			_codTipoParcelamento = value;
		}

		public function get codModoLanc():Number
		{
			return _codModoLanc;
		}

		public function set codModoLanc(value:Number):void
		{
			_codModoLanc = value;
		}

		public function get codSituacaoParcela():Number
		{
			return _codSituacaoParcela;
		}

		public function set codSituacaoParcela(value:Number):void
		{
			_codSituacaoParcela = value;
		}

		public function get codMotivoDevolucao():Number
		{
			return _codMotivoDevolucao;
		}

		public function set codMotivoDevolucao(value:Number):void
		{
			_codMotivoDevolucao = value;
		}

		public function get valorParcela():Number
		{
			return _valorParcela;
		}

		public function set valorParcela(value:Number):void
		{
			_valorParcela = value;
		}

		public function get dataLote():IDateTime
		{
			return _dataLote;
		}

		public function set dataLote(value:IDateTime):void
		{
			_dataLote = value;
		}

		public function get dataHoraInclusao():IDateTime
		{
			return _dataHoraInclusao;
		}

		public function set dataHoraInclusao(value:IDateTime):void
		{
			_dataHoraInclusao = value;
		}

		public function get descNumDocumento():String
		{
			return _descNumDocumento;
		}

		public function set descNumDocumento(value:String):void
		{
			_descNumDocumento = value;
		}

		public function get idUsuarioResp():String
		{
			return _idUsuarioResp;
		}

		public function set idUsuarioResp(value:String):void
		{
			_idUsuarioResp = value;
		}

		public function get numLoteLanc():Number
		{
			return _numLoteLanc;
		}

		public function set numLoteLanc(value:Number):void
		{
			_numLoteLanc = value;
		}

		public function get numSeqLanc():Number
		{
			return _numSeqLanc;
		}

		public function set numSeqLanc(value:Number):void
		{
			_numSeqLanc = value;
		}

		public function get valorLanc():Number
		{
			return _valorLanc;
		}

		public function set valorLanc(value:Number):void
		{
			_valorLanc = value;
		}

		public function get bolAtualizado():Number
		{
			return _bolAtualizado;
		}

		public function set bolAtualizado(value:Number):void
		{
			_bolAtualizado = value;
		}

		public function get idTipoHistoricoLanc():Number
		{
			return _idTipoHistoricoLanc;
		}

		public function set idTipoHistoricoLanc(value:Number):void
		{
			_idTipoHistoricoLanc = value;
		}

		public function get idContaCapital():Number
		{
			return _idContaCapital;
		}

		public function set idContaCapital(value:Number):void
		{
			_idContaCapital = value;
		}

		public function get dataSaida():IDateTime
		{
			return _dataSaida;
		}

		public function set dataSaida(value:IDateTime):void
		{
			_dataSaida = value;
		}

		public function get idParcelamentoContaCapital():Number
		{
			return _idParcelamentoContaCapital;
		}

		public function set idParcelamentoContaCapital(value:Number):void
		{
			_idParcelamentoContaCapital = value;
		}

		public function get numContaCorrente():String
		{
			return _numContaCorrente;
		}

		public function set numContaCorrente(value:String):void
		{
			_numContaCorrente = value;
		}

		public function get uIDTrabalha():String
		{
			return _uIDTrabalha;
		}

		public function set uIDTrabalha(value:String):void
		{
			_uIDTrabalha = value;
		}

		public function get descObservacao():String
		{
			return _descObservacao;
		}

		public function set descObservacao(value:String):void
		{
			_descObservacao = value;
		}

		public function get idTipoHistoricoEstorno():Number
		{
			return _idTipoHistoricoEstorno;
		}

		public function set idTipoHistoricoEstorno(value:Number):void
		{
			_idTipoHistoricoEstorno = value;
		}


	}
}
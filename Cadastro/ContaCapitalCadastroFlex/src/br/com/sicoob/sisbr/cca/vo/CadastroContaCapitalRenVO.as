package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.BancoobVO;

	/**
	 * @author: Marco.Nascimento
	 */
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.CadastroContaCapitalRenVO", CadastroContaCapitalRenVO);
	public class CadastroContaCapitalRenVO extends BancoobVO {
		
		private var _idContaCapital:Number;
		private var _idInstituicao:Number;
		private var _idPessoa:Number;
		private var _nomePessoa:String;
		private var _nomeCompleto:String;
		private var _cpfCnpj:String; 
		private var _idPessoaLegado:Number;
		private var _numContaCapital:Number;
		private var _numContaCapitalGerada:Number;
		private var _vlrSubs:Number;
		private var _vlrInteg:Number;
		private var _qtdParcelas:Number;
		private var _vlrParcelas:Number;
		private var _diaDebito:Number;
		private var _tipoInteg:Number;
		private var _numCco:Number;
		private var _idSituacaoCadastro:Number;
		private var _descSituacaoAprovacaoCapital:String;
		private var _idSituacaoContaCapital:Number;
		private var _descSituacaoContaCapital:String;
		private var _dataInclusao:String;
		private var _descCentral:String;
		private var _descSingular:String;
		private var _dataHoraAtualizacao:IDateTime;
		private var _matriculaEscolhida:Booleano;
		private var _observacao:String;
		private var _idOcorrenciaAtividade:Number;
		private var _idAtividade:Number;
		private var _idsContaCapital:ArrayCollection;
		private var _permissaoExcluir:Boolean;
		
		private var _documentos:DocumentoCapitalVO;
		
		public function get idInstituicao():Number
		{
			return _idInstituicao;
		}

		public function set idInstituicao(value:Number):void
		{
			_idInstituicao = value;
		}

		public function get idPessoa():Number
		{
			return _idPessoa;
		}

		public function set idPessoa(value:Number):void
		{
			_idPessoa = value;
		}

		public function get numContaCapital():Number
		{
			return _numContaCapital;
		}

		public function set numContaCapital(value:Number):void
		{
			_numContaCapital = value;
		}

		public function get vlrSubs():Number
		{
			return _vlrSubs;
		}

		public function set vlrSubs(value:Number):void
		{
			_vlrSubs = value;
		}

		public function get vlrInteg():Number
		{
			return _vlrInteg;
		}

		public function set vlrInteg(value:Number):void
		{
			_vlrInteg = value;
		}

		public function get qtdParcelas():Number
		{
			return _qtdParcelas;
		}

		public function set qtdParcelas(value:Number):void
		{
			_qtdParcelas = value;
		}

		public function get vlrParcelas():Number
		{
			return _vlrParcelas;
		}

		public function set vlrParcelas(value:Number):void
		{
			_vlrParcelas = value;
		}

		public function get diaDebito():Number
		{
			return _diaDebito;
		}

		public function set diaDebito(value:Number):void
		{
			_diaDebito = value;
		}

		public function get tipoInteg():Number
		{
			return _tipoInteg;
		}

		public function set tipoInteg(value:Number):void
		{
			_tipoInteg = value;
		}

		public function get numCco():Number
		{
			return _numCco;
		}

		public function set numCco(value:Number):void
		{
			_numCco = value;
		}

		public function get idPessoaLegado():Number
		{
			return _idPessoaLegado;
		}

		public function set idPessoaLegado(value:Number):void
		{
			_idPessoaLegado = value;
		}
		
		public function get idSituacaoContaCapital():Number
		{
			return _idSituacaoContaCapital;
		}
		
		public function set idSituacaoContaCapital(value:Number):void
		{
			_idSituacaoContaCapital = value;
		}
		
		public function get idSituacaoCadastro():Number
		{
			return _idSituacaoCadastro;
		}

		public function set idSituacaoCadastro(value:Number):void
		{
			_idSituacaoCadastro = value;
		}

		public function get nomePessoa():String
		{
			return _nomePessoa;
		}

		public function set nomePessoa(value:String):void
		{
			_nomePessoa = value;
		}

		public function get cpfCnpj():String
		{
			return _cpfCnpj;
		}

		public function set cpfCnpj(value:String):void
		{
			_cpfCnpj = value;
		}

		public function get descSituacaoAprovacaoCapital():String
		{
			return _descSituacaoAprovacaoCapital;
		}

		public function set descSituacaoAprovacaoCapital(value:String):void
		{
			_descSituacaoAprovacaoCapital = value;
		}

		public function get descSituacaoContaCapital():String
		{
			return _descSituacaoContaCapital;
		}

		public function set descSituacaoContaCapital(value:String):void
		{
			_descSituacaoContaCapital = value;
		}

		public function get idContaCapital():Number
		{
			return _idContaCapital;
		}

		public function set idContaCapital(value:Number):void
		{
			_idContaCapital = value;
		}

		public function get numContaCapitalGerada():Number
		{
			return _numContaCapitalGerada;
		}

		public function set numContaCapitalGerada(value:Number):void
		{
			_numContaCapitalGerada = value;
		}

		public function get dataInclusao():String
		{
			return _dataInclusao;
		}

		public function set dataInclusao(value:String):void
		{
			_dataInclusao = value;
		}

		public function get descCentral():String
		{
			return _descCentral;
		}

		public function set descCentral(value:String):void
		{
			_descCentral = value;
		}

		public function get descSingular():String
		{
			return _descSingular;
		}

		public function set descSingular(value:String):void
		{
			_descSingular = value;
		}

		public function get documentos():DocumentoCapitalVO
		{
			return _documentos;
		}

		public function set documentos(value:DocumentoCapitalVO):void
		{
			_documentos = value;
		}

		public function get dataHoraAtualizacao():IDateTime
		{
			return _dataHoraAtualizacao;
		}

		public function set dataHoraAtualizacao(value:IDateTime):void
		{
			_dataHoraAtualizacao = value;
		}

		public function get matriculaEscolhida():Booleano
		{
			return _matriculaEscolhida;
		}

		public function set matriculaEscolhida(value:Booleano):void
		{
			_matriculaEscolhida = value;
		}

		public function get observacao():String
		{
			return _observacao;
		}

		public function set observacao(value:String):void
		{
			_observacao = value;
		}

		public function get nomeCompleto():String
		{
			return _nomeCompleto;
		}

		public function set nomeCompleto(value:String):void
		{
			_nomeCompleto = value;
		}

		public function get idAtividade():Number
		{
			return _idAtividade;
		}

		public function set idAtividade(value:Number):void
		{
			_idAtividade = value;
		}
		
		public function get idOcorrenciaAtividade():Number
		{
			return _idOcorrenciaAtividade;
		}
		
		public function set idOcorrenciaAtividade(value:Number):void
		{
			_idOcorrenciaAtividade = value;
		}

		public function get idsContaCapital():ArrayCollection
		{
			return _idsContaCapital;
		}

		public function set idsContaCapital(value:ArrayCollection):void
		{
			_idsContaCapital = value;
		}

		public function get permissaoExcluir():Boolean
		{
			return _permissaoExcluir;
		}

		public function set permissaoExcluir(value:Boolean):void
		{
			_permissaoExcluir = value;
		}
	}
}
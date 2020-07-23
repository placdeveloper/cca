package br.com.bancoob.sisbr.negocio.vo
{
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	import flash.utils.ByteArray;
		
	registerClassAlias("br.com.bancoob.sisbr.negocio.vo.PessoaVO",
					    br.com.bancoob.sisbr.negocio.vo.PessoaVO);
	
	public class PessoaVO extends BancoobVO
	{
		
		private var _num_pessoa_func_ger:int;
		private var _num_pessoa:int;
		private var _cod_pf_pj:int;
		private var _data_cadastramento_pessoa:IDateTime;
		private var _num_cgc_cpf:String;
		private var _desc_nome_pessoa:String;
		private var _desc_nome_completo:String;
		private var _desc_apelido_pessoa:String;
		private var _num_ddd:String;
		private var _num_cel_fax:String;
		private var _num_ramal:String;
		private var _desc_end_internet:String;
		private var _desc_obs_pessoa:String;
		private var _idgrupo_pessoa:int;
		private var _idatividade_economica:int;
		private var _data_ultima_atualizacao:IDateTime;		
		private var _foto_pessoa:Object;
		private var _assinatura_pessoa:String;
		private var _num_coop_origem_pessoa:int;
		private var _num_pac_origem_pessoa:int;
		private var _cod_hash_assinatura:String;
		private var _cod_hash_foto:String;		
		private var _num_telefone:String;
		private var _cod_envio_correspondencia:int;
		private var codTipoFoto:int;
		private var _foto_pessoa_byte:ByteArray;
		private var _assinatura_pessoa_byte:ByteArray;
		private var _cod_cnae:String;
		private var _desc_cnae:String;
		private var _acao_imagem_foto:String;
		private var _acao_imagem_assinatura:String;
		
		
		
		public function get NumPessoaFuncGer():int
		{ return _num_pessoa_func_ger; }
		public function set NumPessoaFuncGer(vlr:int):void
		{ _num_pessoa_func_ger = vlr; }
		
		public function get acaoImagemAssinatura():String
		{ return _acao_imagem_assinatura; }
		public function set acaoImagemAssinatura(vlr:String):void
		{ _acao_imagem_assinatura = vlr; }
		
		public function get acaoImagemFoto():String
		{ return _acao_imagem_foto; }
		public function set acaoImagemFoto(vlr:String):void
		{ _acao_imagem_foto = vlr; }
		
				
		// NumPessoa
		public function get NumPessoa():int
		{ return _num_pessoa; }
		public function set NumPessoa(vlr:int):void
		{ _num_pessoa = vlr; }
		
		// CodPF_PJ
		public function get CodPF_PJ():int
		{ return _cod_pf_pj; }
		public function set CodPF_PJ(vlr:int):void
		{ _cod_pf_pj = vlr; }
		
		// DataCadastramentoPessoa
		public function get DataCadastramentoPessoa():IDateTime
		{ return _data_cadastramento_pessoa; }
		public function set DataCadastramentoPessoa(vlr:IDateTime):void
		{ _data_cadastramento_pessoa = vlr; }
		
		// NumCGC_CPF
		public function get NumCGC_CPF():String
		{ return _num_cgc_cpf; }
		public function set NumCGC_CPF(vlr:String):void
		{ _num_cgc_cpf = vlr; }
		
		// DescNomePessoa
		public function get DescNomePessoa():String
		{ return _desc_nome_pessoa; }
		public function set DescNomePessoa(vlr:String):void
		{ _desc_nome_pessoa = vlr; }
		
		// DescApelidoPessoa
		public function get DescApelidoPessoa():String
		{ return _desc_apelido_pessoa; }
		public function set DescApelidoPessoa(vlr:String):void
		{ _desc_apelido_pessoa = vlr; }
		
		// NumDDD
		public function get NumDDD():String
		{ return _num_ddd; }
		public function set NumDDD(vlr:String):void
		{ _num_ddd = vlr; }
		
		// NumCelFax
		public function get NumCelFax():String
		{ return _num_cel_fax; }
		public function set NumCelFax(vlr:String):void
		{ _num_cel_fax = vlr; }
		
		// NumRamal
		public function get NumRamal():String
		{ return _num_ramal; }
		public function set NumRamal(vlr:String):void
		{ _num_ramal = vlr; }
		
		// DescEndInternet
		public function get DescEndInternet():String
		{ return _desc_end_internet; }
		public function set DescEndInternet(vlr:String):void
		{ _desc_end_internet = vlr; }
		
		// DescObsPessoa
		public function get DescObsPessoa():String
		{ return _desc_obs_pessoa; }
		public function set DescObsPessoa(vlr:String):void
		{ _desc_obs_pessoa = vlr; }
		
		// IDGrupoPessoa
		public function get IDGrupoPessoa():int
		{ return _idgrupo_pessoa; }
		public function set IDGrupoPessoa(vlr:int):void
		{ _idgrupo_pessoa = vlr; }
		
		// IDAtividadeEconomica
		public function get IDAtividadeEconomica():int
		{ return _idatividade_economica; }
		public function set IDAtividadeEconomica(vlr:int):void
		{ _idatividade_economica = vlr; }
		
		// DataUltimaAtualizacao
		public function get DataUltimaAtualizacao():IDateTime
		{ return _data_ultima_atualizacao; }
		public function set DataUltimaAtualizacao(vlr:IDateTime):void
		{ _data_ultima_atualizacao = vlr; }
		
		// FotoPessoa
		public function get FotoPessoa():Object
		{ return _foto_pessoa; }
		public function set FotoPessoa(vlr:Object):void
		{ _foto_pessoa = vlr; }
		
		// FotoPessoaByte
		public function get FotoPessoaByte():ByteArray
		{ return _foto_pessoa_byte; }
		public function set FotoPessoaByte(vlr:ByteArray):void
		{ _foto_pessoa_byte = vlr; }
		
		// AssinaturaPessoa
		public function get AssinaturaPessoa():String
		{ return _assinatura_pessoa; }
		public function set AssinaturaPessoa(vlr:String):void
		{ _assinatura_pessoa = vlr; }
		
		// AssinaturaPessoaByte
		public function get AssinaturaPessoaByte():ByteArray
		{ return _assinatura_pessoa_byte; }
		public function set AssinaturaPessoaByte(vlr:ByteArray):void
		{ _assinatura_pessoa_byte = vlr; }
		
		// NumCoopOrigemPessoa
		public function get NumCoopOrigemPessoa():int
		{ return _num_coop_origem_pessoa; }
		public function set NumCoopOrigemPessoa(vlr:int):void
		{ _num_coop_origem_pessoa = vlr; }
		
		// NumPacOrigemPessoa
		public function get NumPacOrigemPessoa():int
		{ return _num_pac_origem_pessoa; }
		public function set NumPacOrigemPessoa(vlr:int):void
		{ _num_pac_origem_pessoa = vlr; }
		
		// CodHashAssinatura
		public function get CodHashAssinatura():String
		{ return _cod_hash_assinatura; }
		public function set CodHashAssinatura(vlr:String):void
		{ _cod_hash_assinatura = vlr; }
		
		// CodHashFoto
		public function get CodHashFoto():String
		{ return _cod_hash_foto; }
		public function set CodHashFoto(vlr:String):void
		{ _cod_hash_foto = vlr; }

		//NumTelefone
		public function get NumTelefone():String
		{ return _num_telefone; }
		public function set NumTelefone(vlr:String):void
		{ _num_telefone = vlr; }
					
		//CodEnvioCorrespondencia
		public function get CodEnvioCorrespondencia():int
		{ return _cod_envio_correspondencia; }
		public function set CodEnvioCorrespondencia(vlr:int):void
		{ _cod_envio_correspondencia = vlr; }
		
		//CodTipoFoto
		public function get CodTipoFoto():int
		{ return codTipoFoto; }
		public function set CodTipoFoto(vlr:int):void
		{ codTipoFoto = vlr; }
		
		// CodCNAE
		public function get CodCNAE():String
		{ return _cod_cnae; }
		public function set CodCNAE(vlr:String):void
		{ _cod_cnae = vlr; }
		
		// DescCNAE
		public function get DescCodCNAE():String
		{ return _desc_cnae; }
		public function set DescCodCNAE(vlr:String):void
		{ _desc_cnae = vlr; }
		
		// DescNomeCompleto
		public function get DescNomeCompleto():String
		{ return _desc_nome_completo; }
		public function set DescNomeCompleto(vlr:String):void
		{ _desc_nome_completo = vlr; }
		
	}
}
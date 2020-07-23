package br.com.bancoob.sisbr.negocio.vo
{
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.bancoob.sisbr.negocio.vo.TrabalhaVO",
					    br.com.bancoob.sisbr.negocio.vo.TrabalhaVO);
	
	public class TrabalhaVO extends BancoobVO
	{
		private var _desc_matricula_func:String;
		private var _desc_empresa_trabalha:String;
		private var _desc_ocupacao_profissional:String;
		private var _data_admissao:Date;
		private var _num_ddd:String;
		private var _num_telefone:String;
		private var _num_ramal:String;
		private var _num_pessoa_fisica:int;
		private var _num_pessoa_juridica:int;
		private var _cod_orgao:int;
		private var _cod_suborgao:int;
		private var _cod_tipo_tomador:int = -1;
		private var _cod_tipo_verba:int = -1;
		private var _cod_tipo_sindicalizacao:int = -1;
		private var _uidtrabalha:String;
		
        private var _num_pessoa_juridica_anterior:int;
        private var _desc_matricula_func_anterior:String;
        
        private var _cod_tp_afast_funcionario:int = -1;
        private var _cod_tipo_funcionario:int = -1;
        private var _desc_per_aquisitivo:String;
        private var _cod_sit_funcionario:int = -1;
        
        private var _desc_sit_funcionario:String;
        private var _data_desligamento:Date;
        
        private var _numinscconsreg:String;
        private var _codsiglaconsreg:int = -1
        private var _siglaufconsreg:String
        
    	
		// DescMatriculaFunc
		public function get DescMatriculaFunc():String
		{ return _desc_matricula_func; }
		public function set DescMatriculaFunc(vlr:String):void
		{ _desc_matricula_func = vlr; }
		
		// DescEmpresaTrabalha
		public function get DescEmpresaTrabalha():String
		{ return _desc_empresa_trabalha; }
		public function set DescEmpresaTrabalha(vlr:String):void
		{ _desc_empresa_trabalha = vlr; }
		
		// DescOcupacaoProfissional
		public function get DescOcupacaoProfissional():String
		{ return _desc_ocupacao_profissional; }
		public function set DescOcupacaoProfissional(vlr:String):void
		{ _desc_ocupacao_profissional = vlr; }
		
		// DataAdmissao
		public function get DataAdmissao():Date
		{ return _data_admissao; }
		public function set DataAdmissao(vlr:Date):void
		{ _data_admissao = vlr; }
				
		// NumDDD
		public function get NumDDD():String
		{ return _num_ddd; }
		public function set NumDDD(vlr:String):void
		{ _num_ddd = vlr; }
		
		// NumTelefone
		public function get NumTelefone():String
		{ return _num_telefone; }
		public function set NumTelefone(vlr:String):void
		{ _num_telefone = vlr; }
		
		// NumRamal
		public function get NumRamal():String
		{ return _num_ramal; }
		public function set NumRamal(vlr:String):void
		{ _num_ramal = vlr; }
		
		// NumPessoaFisica
		public function get NumPessoaFisica():int
		{ return _num_pessoa_fisica; }
		public function set NumPessoaFisica(vlr:int):void
		{ _num_pessoa_fisica = vlr; }
		
		// NumPessoaJuridica
		public function get NumPessoaJuridica():int
		{ return _num_pessoa_juridica; }
		public function set NumPessoaJuridica(vlr:int):void
		{ _num_pessoa_juridica = vlr; }
		
		// CodOrgao
		public function get CodOrgao():int
		{ return _cod_orgao; }
		public function set CodOrgao(vlr:int):void
		{ _cod_orgao = vlr; }

		// CodSubOrgao
		public function get CodSubOrgao():int
		{ return _cod_suborgao; }
		public function set CodSubOrgao(vlr:int):void
		{ _cod_suborgao = vlr; }
		
		// CodTipoTomador
		public function get CodTipoTomador():int
		{ return _cod_tipo_tomador; }
		public function set CodTipoTomador(vlr:int):void
		{ _cod_tipo_tomador = vlr; }
		
		// CodTipoVerba
		public function get CodTipoVerba():int
		{ return _cod_tipo_verba; }
		public function set CodTipoVerba(vlr:int):void
		{ _cod_tipo_verba = vlr; }
		
		// CodTipoSindicalizacao
		public function get CodTipoSindicalizacao():int
		{ return _cod_tipo_sindicalizacao; }
		public function set CodTipoSindicalizacao(vlr:int):void
		{ _cod_tipo_sindicalizacao = vlr; }
		
		// UIDTrabalha
		public function get UIDTrabalha():String
		{ return _uidtrabalha; }
		public function set UIDTrabalha(vlr:String):void
		{ _uidtrabalha = vlr; }

 		// DescPessoaJuridicaAnterior
		public function get NumPessoaJuridicaAnterior():int
		{ return _num_pessoa_juridica_anterior; }
		public function set NumPessoaJuridicaAnterior(vlr:int):void
		{ _num_pessoa_juridica_anterior = vlr; }		

		// DescPessoaJuridicaAnterior
		public function get DescMatriculaFuncAnterior():String
		{ return _desc_matricula_func_anterior; }
		public function set DescMatriculaFuncAnterior(vlr:String):void
		{ _desc_matricula_func_anterior = vlr; }

		// DescPerAquisitivo
		public function get DescPerAquisitivo():String
		{ return _desc_per_aquisitivo; }
		public function set DescPerAquisitivo(vlr:String):void
		{ _desc_per_aquisitivo = vlr; }

		// CodTipoFuncionario
		public function get CodTipoFuncionario():int
		{return _cod_tipo_funcionario;}
		public function set CodTipoFuncionario(vlr:int):void
		{ _cod_tipo_funcionario = vlr;}
			
		//CodSitFuncionario
		public function get CodSitFuncionario():int
		{return _cod_sit_funcionario;}
		public function set CodSitFuncionario(vlr:int):void
		{ _cod_sit_funcionario = vlr; }
		
		//CodTpAfastFuncionario
		public function get CodTpAfastFuncionario():int
		{ return _cod_tp_afast_funcionario; }
		public function set CodTpAfastFuncionario(vlr:int):void
		{ _cod_tp_afast_funcionario = vlr; }
		
		//DescSitFuncionario
		public function get DescSitFuncionario():String
		{ return _desc_sit_funcionario; }
		public function set DescSitFuncionario(vlr:String):void
		{ _desc_sit_funcionario = vlr; }

		// dataDesligamento
		public function get dataDesligamento():Date
		{ return _data_desligamento; }
		public function set dataDesligamento(vlr:Date):void
		{ _data_desligamento = vlr; }
				    
        // NumInscConsReg
		public function get NumInscConsReg():String
		{ return _numinscconsreg; }
		public function set NumInscConsReg(vlr:String):void
		{ _numinscconsreg = vlr; }
		
		// CodSiglaConsReg
		public function get CodSiglaConsReg():int
		{ return _codsiglaconsreg; }
		public function set CodSiglaConsReg(vlr:int):void
		{ _codsiglaconsreg = vlr; }
		
		// SiglaUFConsReg
		public function get SiglaUFConsReg():String
		{ return _siglaufconsreg; }
		public function set SiglaUFConsReg(vlr:String):void
		{ _siglaufconsreg = vlr; }
		
	}
}
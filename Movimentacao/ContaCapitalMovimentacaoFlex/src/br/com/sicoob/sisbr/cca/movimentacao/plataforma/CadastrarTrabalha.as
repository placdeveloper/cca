package br.com.sicoob.sisbr.cca.cadastro.plataforma
{
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.sisbr.Configuracoes;
	import br.com.bancoob.sisbr.ProcuraClientePlataforma;
	import br.com.bancoob.sisbr.componentes.procurarCliente.procurarPessoa;
	import br.com.bancoob.sisbr.componentes.selecaoGeral.ProcurarGeral;
	import br.com.bancoob.sisbr.componentes.selecaoGeral.SelecaoGeralConstantes;
	import br.com.bancoob.sisbr.negocio.dto.LancFolhaPJReqDTO;
	import br.com.bancoob.sisbr.negocio.dto.PessoaReqDTO;
	import br.com.bancoob.sisbr.negocio.dto.TrabalhaReqDTO;
	import br.com.bancoob.sisbr.negocio.vo.LancFolhaPJVO;
	import br.com.bancoob.sisbr.negocio.vo.PessoaVO;
	import br.com.bancoob.sisbr.negocio.vo.TrabalhaVO;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.ICadastro;
	import br.com.bancoob.util.Servicos;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.eventos.EventData;

	public class CadastrarTrabalha extends CadastrarTrabalhaView implements ICadastro
	{
		private var servicos:Servicos = new Servicos();
		private var registro:TrabalhaVO = new TrabalhaVO();
		private var registroBkp:TrabalhaVO = new TrabalhaVO();
		
		private var _codTipoDadoMatricula:int;
		private var codigoExcluir:String;
		
		/*Informa se o preenchimento do campo esta sendo de forma automática.
		  Esta variável é usada para controlar o comportamento do formulário quando editado, para não sobrescrever
		  as informações gravadas.*/
		private var _bRecuperarCampos:Boolean = true;
		
		//Informações da Pessoa (Oriundo da Plataforma)
		private var oPessoa:Object = ProcuraClientePlataforma.getObjCliente();
		
		//Constantes com os nomes dos eventos a serem disparados.
		public static const DEFINICOES_CARREGADAS:String = "definicoesCarregadas";
		
		public function CadastrarTrabalha()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent):void{
			
			//carregarDefinicoes();
			
			 //--------------------------------------------------------------------------
		    // propriedades procurar Pessoas Jurídicas (Empresas)
		    //--------------------------------------------------------------------------											
			ctrNumPessoaJuridica.tipoPessoa = 2;
			ctrNumPessoaJuridica.desabilitarSelecaoTipoPessoa = true;
			ctrNumPessoaJuridica.addEventListener(procurarPessoa.ITEM_SELECIONADO, pessoaSelecionada);

		    //--------------------------------------------------------------------------
		    // propriedades procurar geral do campo Orgão
		    //--------------------------------------------------------------------------								
			procOrgao.tipoProcura = SelecaoGeralConstantes.TIPOPROC_ORGAO_LANC_FOLHA;
			procOrgao.titulo = "SELEÇÃO DE ORGÃO";
			procOrgao.addEventListener(ProcurarGeral.ITEM_SELECIONADO, pegarDadosOrgao);
			ctrNumPessoaJuridica.txtCodigo.addEventListener(Event.CHANGE, inicializarProcOrgao);
			
			txtCodOrgao.addEventListener(FocusEvent.FOCUS_OUT, procurarOrgao);
			txtCodOrgao.addEventListener(FlexEvent.ENTER, procurarOrgao);
			procOrgao.servicoOpcional = "br.com.bancoob.servicos.sisbr.Trabalha";
			procOrgao.enabled = false;
			txtCodOrgao.enabled = false;
			limparProcOrgao();
			
		    //--------------------------------------------------------------------------
		    // propriedades dos campos e botões do formulário formulário
		    //--------------------------------------------------------------------------	
			cboCodSitFuncionario.validarObrigatorio = true;
			cboCodSitFuncionario.validarMensagem = "Favor informar a situação do funcionário";
			 
			cboCodTipoFuncionario.validarObrigatorio = true;
			cboCodTipoFuncionario.validarMensagem = "Favor informar o tipo de funcionário";

			txtDescEmpresaTrabalha.validarObrigatorio = true;
			txtDescEmpresaTrabalha.validarMensagem = "Favor informar o nome da empresa";

			dtDataAdmissao.validarObrigatorio = true;
			dtDataAdmissao.validarMensagem = "Favor informar a data de admissão";

		    btnOK.addEventListener(MouseEvent.CLICK, gravar);
			btnCancelar.addEventListener(MouseEvent.CLICK, cancelarClicado);
		    btnFechar.addEventListener(MouseEvent.CLICK, voltarClicado); 
		    
			txtDescMatriculaFunc.validarMensagem = "Favor informar a Matrícula na Empresa";
		}
		
		public function excluirRegistro(obj:Object):void
		{
		}
		
		public function carregarRegistro(obj:Object):void
		{

		}
		
		public function preencherCampos():void
		{
			if(!_novo)
				this._bRecuperarCampos = false;

			ctrNumPessoaJuridica.limpar();
			limparProcOrgao();

			txtDescMatriculaFunc.validarObrigatorio = false;
			ctrNumPessoaJuridica.txtCodigo.text = registro.NumPessoaJuridica.toString();
			ctrNumPessoaJuridica.procurarCodigo();
			
			txtCodOrgao.text = (registro.CodOrgao == 0 ? "" : registro.CodOrgao.toString());
			procOrgao.enabled = false
			
			txtDescMatriculaFunc.text = registro.DescMatriculaFunc;
			txtDescEmpresaTrabalha.text = registro.DescEmpresaTrabalha;
			txtDescOcupacaoProfissional.text = registro.DescOcupacaoProfissional;
			txtNumDDD.text = registro.NumDDD;
			txtNumRamal.text = registro.NumRamal;
			
			txtNumTelefone.text = (registro.NumTelefone == "" ? "" : StringUtils.trim(registro.NumTelefone));
			txtNumTelefone.validateNow();
			
			txtPeriodoFerias.text = (registro.DescPerAquisitivo == "" ? "" : StringUtils.trim(registro.DescPerAquisitivo));
			txtPeriodoFerias.validateNow();

			if(registro.DataAdmissao == null){
				dtDataAdmissao.selectedDate = null;
			}else{
				dtDataAdmissao.selectedDate = registro.DataAdmissao;
			}
			
			
			cboCodSitFuncionario.procuraItemPorNome(registro.CodSitFuncionario,"codListaItem");
			cboCodTipoFuncionario.procuraItemPorNome(registro.CodTipoFuncionario,"codListaItem");
			cboCodTipoSindicalizacao.procuraItemPorNome(registro.CodTipoSindicalizacao,"codListaItem");
			cboCodTipoTomador.procuraItemPorNome(registro.CodTipoTomador,"codListaItem");
			cboCodTipoVerba.procuraItemPorNome(registro.CodTipoVerba,"codListaItem");
			cboCodTpAfastFuncionario.procuraItemPorNome(registro.CodTpAfastFuncionario,"codListaItem");
		}
		
		public function carregarDefinicoes(obj:Object=null):void
		{
			servicos.invocarServico(Configuracoes.pacoteServicos + ".sisbr.Trabalha",
									obterDefinicoes_onResult, null, this.destino).obterDefinicoes();
		}
		
		 /**
	     * Resultado do método carregarDefinicoes
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================		
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     * @param evt dados do evento disparado
	     */
		private function obterDefinicoes_onResult(evt:ResultEvent):void {			
			cboCodTipoFuncionario.dataProvider 		= evt.result.listas["ListaTipoFuncionario"];
			cboCodSitFuncionario.dataProvider		= evt.result.listas["ListaSituacaoFunc"];
			cboCodTpAfastFuncionario.dataProvider 	= evt.result.listas["ListaTipoAfastamento"];
			cboCodTipoTomador.dataProvider 			= evt.result.listas["ListaTipoTomador"];
			cboCodTipoVerba.dataProvider 			= evt.result.listas["ListaTipoVerba"];
			cboCodTipoSindicalizacao.dataProvider 	= evt.result.listas["ListaSindicalizacao"];
			
		}
		
		/**
	     * Método para preparar o ambiente de edição.
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================	
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     */				
		public function novoRegistro():void{
			//TODO: implement function		
			ctrNumPessoaJuridica.limpar();			
			_novo = true;	
			iniciarRegistro();
			registroBkp = ObjectUtil.copy(registro) as TrabalhaVO;
			preencherCampos();
			
		}
		
		/**
	     * Método para validar as informações e solicitar a gravação dos dados
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================		
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     * @param evt dados do evento disparado
	     */			
		private function gravar(evt:MouseEvent):void{
 			if(!validarCampos())
				return;
 			if(_novo) {
				//Novo registro.
				registro = new TrabalhaVO();
				registro.NumPessoaFisica = oPessoa.NUMPESSOA;
				registroBkp = ObjectUtil.copy(registro) as TrabalhaVO;
			}
			
 			atualizarCamposRegistro();
			
			gravarDados();
				
		}		

	    /**
	     * Método que valida as informações do formulário
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================		
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     */	
 		private function validarCampos():Boolean { 			
 			if(ctrNumPessoaJuridica.txtCodigo.valor > 0 ){
				//Verifica se a pessoa jurídica é válida.
	 			if (ctrNumPessoaJuridica.txtNome.text == "") {
					Alerta.show("O campo Código da Empresa precisa ter um valor válido!", "Atenção", Alerta.ALERTA_OK, ctrNumPessoaJuridica.txtCodigo);
					return false; 					 				
	 			}
 				
				//Se a Pessoa Juridica for informada é obrigatório a matrícula.
	 			if(StringUtils.trim(txtDescMatriculaFunc.text) == "" ){
					Alerta.show("O campo Matrícula na Empresa precisa ter um valor válido!", "Atenção", Alerta.ALERTA_OK, txtDescMatriculaFunc);
					return false; 					 				
	 			}
 			}
			//Verifica se a Empresa digitada é valida.
 			if(StringUtils.trim(txtDescEmpresaTrabalha.text) == "" ){
				Alerta.show("O campo Nome da Empresa precisa ter um valor válido!", "Atenção", Alerta.ALERTA_OK, txtDescEmpresaTrabalha);
				return false; 				
 			}
 			if(txtCodOrgao.text != ""){
				//Se o código do órgão é um código válido.
	 			if(txtDescOrgao.text == ""){
					Alerta.show("O campo Código do Órgão precisa ter um valor válido!", "Atenção", Alerta.ALERTA_OK, txtCodOrgao);
					return false; 					 				
	 			}
 			}
			if(dtDataAdmissao.selectedDate == null) {
				Alerta.show("O campo Data de admissão precisa ter um valor válido!", "Atenção", Alerta.ALERTA_OK, dtDataAdmissao);
				return false;
			}
			if(cboCodTipoFuncionario.selectedIndex < 0) {
				Alerta.show("O campo Tipo Funcionário precisa ter um valor válido!", "Atenção", Alerta.ALERTA_OK, cboCodTipoFuncionario);
				return false;
			}
			if(cboCodSitFuncionario.selectedIndex < 0) {
				Alerta.show("O campo Situação do Funcionário precisa ter um valor válido!", "Atenção", Alerta.ALERTA_OK, cboCodSitFuncionario);
				return false;
			}		
 			return true;
		}
		
	    /**
	     * Método que atualiza as informações do registro (VO)
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================		
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     */			
		private function atualizarCamposRegistro():void{

			registro.NumPessoaJuridica = ctrNumPessoaJuridica.txtCodigo.valor;
			registro.CodOrgao = txtCodOrgao.valor;
			
			registro.DescEmpresaTrabalha = StringUtils.trim(txtDescEmpresaTrabalha.text.toString());
			registro.NumDDD = StringUtils.trim(txtNumDDD.text.toString());
			registro.NumTelefone = StringUtils.trim(txtNumTelefone.text.toString());
			registro.NumRamal = StringUtils.trim(txtNumRamal.text.toString());
			registro.DescMatriculaFunc = StringUtils.trim(txtDescMatriculaFunc.text.toString());
			registro.DescOcupacaoProfissional = StringUtils.trim(txtDescOcupacaoProfissional.text.toString());

			registro.DataAdmissao = dtDataAdmissao.selectedDate;
			registro.DescPerAquisitivo = StringUtils.trim(txtPeriodoFerias.text.toString());

			registro.CodTipoFuncionario = (cboCodTipoFuncionario.selectedItem == null ? -1 : cboCodTipoFuncionario.selectedItem.codListaItem);
			registro.CodSitFuncionario = (cboCodSitFuncionario.selectedItem == null ? -1 : cboCodSitFuncionario.selectedItem.codListaItem);
			registro.CodTpAfastFuncionario = (cboCodTpAfastFuncionario.selectedItem == null ? -1 : cboCodTpAfastFuncionario.selectedItem.codListaItem);
			
			registro.CodTipoTomador = (cboCodTipoTomador.selectedItem == null ? -1 : cboCodTipoTomador.selectedItem.codListaItem);
			registro.CodTipoVerba = (cboCodTipoVerba.selectedItem == null ? -1 : cboCodTipoVerba.selectedItem.codListaItem);
			registro.CodTipoSindicalizacao = (cboCodTipoSindicalizacao.selectedItem == null ? -1 : cboCodTipoSindicalizacao.selectedItem.codListaItem);
			
			//Informações para validação na alteração
			registro.NumPessoaJuridicaAnterior = registroBkp.NumPessoaJuridica;
			registro.DescMatriculaFuncAnterior = registroBkp.DescMatriculaFunc;
		} 
			
	    /**
	     * Método para gravar as informações editadas
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================		
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     */		
		private function gravarDados():void {
			 			
			var req:TrabalhaReqDTO = new TrabalhaReqDTO(); 
			req.DadosTrabalha = registro;
			 
			if(_novo){	//verifica se é uma edição nova		
				MostraCursor.setBusyCursor("Gravando Registro!", Application.application);					
				servicos.invocarServico(Configuracoes.pacoteServicos + ".sisbr.Trabalha",
									gravar_onResult, null, this.destino).incluirDados(req);
										
			}else{ //Edição de um registro existente na base de dados
				if(!verificaAlteracoes()){
					MostraCursor.setBusyCursor("Gravando Registro!", Application.application);
					servicos.invocarServico(Configuracoes.pacoteServicos + ".sisbr.Trabalha",
											gravar_onResult, null, this.destino).
											alterarDados(req);
				}else{ //Caso não haja alterações na edição
					gravar_onResult();
				}
			}
		}
		
	    /**
	     * Resultado do método gravarDados
	     * <br>
		 * <br><b>Data:</b>	23/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================		
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     * @param evt dados do evento disparado
	     */	
		private function gravar_onResult(evt:ResultEvent=null):void {
			MostraCursor.removeBusyCursor();

			/* Voltar para a lista */
			confirmaVoltar();
		}
		
	    /**
	     * Método para verificar se foi feito alguma alteração nos dados do formulário
	     * <br>
		 * <br><b>Data:</b>	23/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================		
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     */			
		private function verificaAlteracoes():Boolean{
			
			if(registro.DescEmpresaTrabalha != registroBkp.DescEmpresaTrabalha){
				return false;			
			}
			if(registro.DescMatriculaFunc != registroBkp.DescMatriculaFunc){
				return false;			
			}
			if(registro.DescOcupacaoProfissional != registroBkp.DescOcupacaoProfissional){
				return false;			
			}
			if(registro.DescPerAquisitivo != registroBkp.DescPerAquisitivo){
				return false;			
			}			
			if(registro.NumDDD != registroBkp.NumDDD){
				return false;			
			}			
			if(registro.NumPessoaJuridica != registroBkp.NumPessoaJuridica){
				return false;			
			}			
			if(registro.NumRamal != registroBkp.NumRamal){
				return false;			
			}			
			if(registro.NumTelefone != registroBkp.NumTelefone){
				return false;			
			}			
			
			if(registro.CodOrgao != registroBkp.CodOrgao){
				return false;			
			}
		
			if(FormataData.formataData(registro.DataAdmissao) != FormataData.formataData(registroBkp.DataAdmissao)){
				return false;			
			}

			if(registro.CodSitFuncionario != registroBkp.CodSitFuncionario){
				return false;			
			}
			if(registro.CodTipoFuncionario != registroBkp.CodTipoFuncionario){
				return false;			
			}
			if(registro.CodTipoSindicalizacao != registroBkp.CodTipoSindicalizacao){
				return false;			
			}
			if(registro.CodTipoTomador != registroBkp.CodTipoTomador){
				return false;			
			}
			if(registro.CodTipoVerba != registroBkp.CodTipoVerba){
				return false;			
			}
			if(registro.CodTpAfastFuncionario != registroBkp.CodTpAfastFuncionario){
				return false;			
			}
			
			return true;
		}
		
		/**
	     * Método para iniciar procedimento para fechar o formulário de edição.
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================	
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     * @param evt dados do evento disparado
	     */
 		private function voltarClicado(evt:MouseEvent=null):void {
			var msgVoltar:String = "Tem certeza que deseja sair sem salvar as alterações";
			atualizarCamposRegistro();
			
			if(!verificaAlteracoes()){
				Alerta.show(msgVoltar, "Confirmação", Alerta.ALERTA_PERGUNTA, null, confirmaVoltar);
			}else{
				confirmaVoltar();
			}
		}	
		
	    /**
	     * Método para fechar o formulário de edição e voltar para a lista de dados.
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================	
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     * @param evt dados do evento disparado
	     */
		private function confirmaVoltar(evt:MouseEvent=null):void{
			this.fecharJanela();
			
			//inicializarSelecao();
			//txtAca.text = "";
			//vsEdicao.selectedIndex = 0;
			//desabilitarBotoes(false);
		}
		
		 /**
	     * Funcão para inicializar as propriedades do procurar órgão.
	     * <br>
		 * <br><b>Data:</b>	26/12/2007
		 * <br><b>Criado:</b> Paulílio Castello Branco
	     * <br>===============================		 
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     */	
		private function inicializarProcOrgao(evt:Event):void{
			procOrgao.enabled = false;
			txtCodOrgao.enabled = false;
			limparProcOrgao();
		}
		
		   /**
	     * Funcão para limpar campos do procurar órgão.
	     * <br>
		 * <br><b>Data:</b>	26/12/2007
		 * <br><b>Criado:</b> Paulílio Castello Branco
	     * <br>===============================		 
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     */	
		private function limparProcOrgao():void{
			//Limpa campos do Orgão
			txtCodOrgao.text = "";
			txtDescOrgao.text = ""
		}
		
		/**
	     * Método executado quando uma empresa é selecionada.
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================	
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     * @param evt dados do evento disparado
	     */				
		private function pessoaSelecionada(evt:Event):void {
			if(ctrNumPessoaJuridica.registro != null){
	 			definicoesProcOrgao();
	 			if(_bRecuperarCampos){
					obterTelefonePessoa();
					txtDescMatriculaFunc.text = "";
	 			}
				obterTipoMatriculaPessoa();
				txtCodOrgao.enabled = true;
				txtCodOrgao.editable = true;
				procOrgao.enabled = true;			
	
				txtDescMatriculaFunc.validarObrigatorio = true;
			}else{
				txtCodOrgao.enabled = false;
				txtCodOrgao.editable = false;
				procOrgao.enabled = false;			
				limparProcOrgao();
				txtDescMatriculaFunc.validarObrigatorio = false;
			}
			_bRecuperarCampos = true;
		}		
		
		 /**
	     * Funcão para atualizar código e descrição do órgão (componente procurarGeral)
	     * <br>
		 * <br><b>Data:</b>	21/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco	     
	     * <br>===============================		 
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     *
	     * @param evt dados do evento disparado
	     */			
		private function pegarDadosOrgao(evt:EventData):void {
			txtCodOrgao.valor = evt.data.CODORGAO;
			txtDescOrgao.text = evt.data.DESCORGAO;
		}
		
		 /**
	     * Funcão para executar a procura do órgão (componente procurarGeral)
	     * <br>
		 * <br><b>Data:</b>	21/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco	     
	     * <br>===============================		 
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     *
	     * @param evt dados do evento disparado
	     */	
		private function procurarOrgao(evt:Object=null):void {
			if(txtCodOrgao.valor != 0 && ctrNumPessoaJuridica.txtCodigo.valor!=0){
				procOrgao.procurar(txtCodOrgao.valor, 0);
			}
			else{
				txtDescOrgao.text = "";
			}
		}
		
		/**
	     * Método para cancelar a edição do registro.
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================	
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     * @param evt dados do evento disparado
	     */	
		private function cancelarClicado(evt:Event):void{
			if(registroBkp == null)
				registroBkp = new TrabalhaVO();
			
			registro = ObjectUtil.copy(registroBkp) as TrabalhaVO;		
			preencherCampos();
		}
		
		  /**
	     * Método que busca informações quanto ao órgão.
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================	
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     */	
		private function definicoesProcOrgao():void{		
			procOrgao.tipoProcura = SelecaoGeralConstantes.TIPOPROC_ORGAO_LANC_FOLHA;
			
			if (ctrNumPessoaJuridica.txtCodigo.text == "") {
				procOrgao.metodoOpcional = "";				
				procOrgao.usaSqlKey = false;
			}else{
				procOrgao.usaSqlKey = true;
				procOrgao.metodoOpcional = "obterDadosSelecaoOrgao";
				procOrgao.parametros = ctrNumPessoaJuridica.txtCodigo.valor.toString();
			}
			
			procurarOrgao();
		}
		
		/**
	     * Método para obter informações de telefone da pessoa jurídica.
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================	
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     * @param obj dados para tratamento das informações de definições.
	     */
		private function obterTelefonePessoa(obj:Object=null):void{		
 			var vo:PessoaVO = new PessoaVO();
			var req:PessoaReqDTO = new PessoaReqDTO();
			
			vo.NumPessoa = int(ctrNumPessoaJuridica.txtCodigo.valor);
			req.DadosPessoa = vo;
				
 			servicos.invocarServico(Configuracoes.pacoteServicos + ".sisbr.Pessoa",
									obterTelefonePessoa_onResult, null, this.destino).obterPrimeiroTelefone(req);
		}
		
		/**
	     * Método para obter informações de matrícula da pessoa física.
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================	
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     */
		private function obterTipoMatriculaPessoa():void
		{
 			var vo:LancFolhaPJVO = new LancFolhaPJVO();
			var req:LancFolhaPJReqDTO = new LancFolhaPJReqDTO();
			
			vo.NumPessoaJuridica = int(ctrNumPessoaJuridica.txtCodigo.valor);
			req.DadosLancFolhaPJ = vo;
			req.InfProfissional = true;
				
 			servicos.invocarServico(Configuracoes.pacoteServicos + ".sisbr.LancFolhaPJ",
									obterTipoMatriculaPessoa_onResult, null, this.destino).obterDados(req);
		}
		
			    /**
	     * Resultado do método obterTipoMatriculaPessoa
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================	
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     * @param evt dados do evento disparado
	     */
		private function obterTipoMatriculaPessoa_onResult(evt:ResultEvent):void {		
			var vo:LancFolhaPJVO = new LancFolhaPJVO();	
			vo = evt.result.dados["LancFolhaPJVO"];
			_codTipoDadoMatricula = vo.CodTipoDadoMatricula;	
			atualizarTipoDadoMatricula();
		}
		
		/**
	     * Método que atualiza as informações de matrícula da pessoa física.
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================	
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     * @param evt dados do evento disparado
	     */
		private function atualizarTipoDadoMatricula():void
		{
			if(_codTipoDadoMatricula==1){
				//Numerico
				txtDescMatriculaFunc.tipoEntrada = 1;
			}else{
				//Alfanumérico
				txtDescMatriculaFunc.tipoEntrada = 0;
			}			
		}
		
		    /**
	     * Resultado do método obterTelefonePessoa
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================	
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     * @param evt dados do evento disparado
	     */
		private function obterTelefonePessoa_onResult(evt:ResultEvent):void {		
			var vo:PessoaVO = new PessoaVO();	
			vo = evt.result.dados["DadosPrimeiroTelefone"];
			txtNumDDD.text = vo.NumDDD;
			
			txtNumTelefone.text = (vo.NumTelefone == null ? "" : StringUtils.trim(vo.NumTelefone));
			txtNumTelefone.validateNow();
			
			txtNumRamal.text = vo.NumRamal;
			txtDescEmpresaTrabalha.text = ctrNumPessoaJuridica.txtNome.text.toString();
		}
		
		/**
	     * Inicializa o RegistroVO.
	     * <br>
		 * <br><b>Data:</b>	22/11/2007
		 * <br><b>Criado:</b> Paulilio Castello Branco
	     * <br>===============================	
	     * <br><b>Histórico de Alterações:</b>
	     * <br><b>Data:</b>
	     * <br><b>Motivo:</b>
	     * <br>===============================
	     * 
	     */		
		private function iniciarRegistro():void{

			registro = new TrabalhaVO();
			
			registro.CodOrgao = 0;
			registro.NumPessoaJuridica = 0;
			registro.NumPessoaFisica = 0;
			registro.UIDTrabalha = "";
			registro.NumRamal = "";
			registro.NumDDD = "";
			registro.NumTelefone = "";

			registro.DescEmpresaTrabalha = "";
			registro.DescMatriculaFunc = "";
			registro.DescOcupacaoProfissional = "";
			registro.DescPerAquisitivo = "";
			registro.DataAdmissao = null;

			registro.CodSitFuncionario = -1;
			registro.CodTipoFuncionario = -1;
			registro.CodTipoSindicalizacao = -1;
			registro.CodTipoTomador = -1;
			registro.CodTipoVerba = -1;
			registro.CodTpAfastFuncionario = -1;

			registro.DescMatriculaFuncAnterior = "";
			registro.NumPessoaJuridicaAnterior = 0;
		}
	}
}
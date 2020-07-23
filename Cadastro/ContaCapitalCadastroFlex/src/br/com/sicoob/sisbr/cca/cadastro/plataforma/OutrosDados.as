/**
 * OutrosDados.as
 * 
 * Copyright (c) Banco Cooperativo do Brasil S.A. - BANCOOB
 * Todos os Direitos Reservados
 * 
 * Este software é informação proprietária confidencial do BANCOOB
 * Estas informações não devem ser divulgadas e somente poderão ser
 * utilizadas em concordância com os termos de licença acordados com o BANCOOB
 */

package br.com.sicoob.sisbr.cca.cadastro.plataforma
{
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.Configuracoes;
	import br.com.bancoob.sisbr.Constantes;
	import br.com.bancoob.sisbr.ProcuraClientePlataforma;
	import br.com.bancoob.sisbr.componentes.selecaoGeral.ProcurarGeral;
	import br.com.bancoob.sisbr.componentes.selecaoGeral.SelecaoGeralConstantes;
	import br.com.bancoob.sisbr.negocio.dto.OutrosDadosReqDTO;
	import br.com.bancoob.sisbr.negocio.vo.OutrosDadosVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.ICadastro;
	import br.com.bancoob.util.Servicos;
	import br.com.bancoob.util.eventos.EventData;
	import br.com.bancoob.vo.DestinoVO;
	
	/**
	 * Classe <b>OutrosDados</b>
	 * <br/>
	 * <br/><b>Data:</b>	01/06/2008
	 * <br/><b>Versão:</b>	1.0
	 * <br/><b>Autor:</b>	Daniel Cardoso de Sousa Leite
	 * <br/>
	 * <br/>Classe do formulário que mantem os registros de Outros Dados da Entrega de cheques.
	 * <br/>Essa classe contém todos os métodos da funcionalidade.
	 */
	public class OutrosDados extends OutrosDadosView implements ICadastro
	{
		//--------------------------------------------------------------------------
		//  Constantes
		//--------------------------------------------------------------------------
		private static const VS_CONTA_CORRENTE:int = 0;
		private static const VS_FOLHA:int = 1;
		private static const PERIODO_PROX_DEB:int = 30;
		
		//--------------------------------------------------------------------------
		//  Atributos
		//--------------------------------------------------------------------------
		public var numMatricula:int;
		
		private var janelaEditarTrabalha:Janela = new Janela();
		private var telaEditarTrabalha:CadastrarTrabalha = new CadastrarTrabalha();
		private var servicos:Servicos = new Servicos();
		private var objCliente:Object;
		private var registro:OutrosDadosVO = new OutrosDadosVO();
		private var registroBkp:OutrosDadosVO = new OutrosDadosVO();
		private var listaMatricula:ArrayCollection;
		private var listaFormaDebito:ArrayCollection;
		private var listaCotas:ArrayCollection;
		private var m_iCondicao:int;
		private var m_bSoValor:Boolean;
		private var aplicCoopDif:int;
		
		/**
		 * Construtor.
		 */
		public function OutrosDados()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE,initDestino);
			objCliente = ProcuraClientePlataforma.getObjCliente();	
		}
		
		private function initDestino(event:Event):void {
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino("servicosAtendimento", configurarDestinoRecuperado);
		}
		
		private function configurarDestinoRecuperado(destino:DestinoVO):void {
			this.destino = destino
			
			init(null);
		}
		
		/**
		 * Método executado após a geração da tela.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param 	evt Dados do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	01/06/2008 
		 */
		private function init(evt:FlexEvent):void{			
			janelaEditarTrabalha.title = "Cadastro de Empresas nas quais a Pessoa Física trabalha";
			//janelaEditarTrabalha.addChild(telaEditarTrabalha);
			//telaEditarTrabalha.carregarDefinicoes();
			
			//btnIncluiMatricula.addEventListener(MouseEvent.CLICK,abrirTelaCadastroTrabalha)
			btnOK.addEventListener(MouseEvent.CLICK,gravarDados);
			btnFechar.addEventListener(MouseEvent.CLICK,sair);
			btnCancelar.addEventListener(MouseEvent.CLICK,cancelar);
			
			//procCondicao.addEventListener(ProcurarGeral.ITEM_SELECIONADO,dadosProcurarGeral);
			//procCondicao.tipoProcura = SelecaoGeralConstantes.TIPOPROC_CONDICAO_ASSOCIACAO;
			//procCondicao.titulo = "SELEÇÃO DAS CONDIÇÕES DA ASSOCIAÇÃO";
			//procCondicao.destino = this.destino;
			//procCondicao.parametros = objCliente.CODPF_PJ + "|" + aplicCoopDif.toString();
			//procCondicao.procurar("1",0);
			//procCondicao.
			
			procRendas.addEventListener(ProcurarGeral.ITEM_SELECIONADO,dadosProcurarRendas);
			procRendas.tipoProcura = SelecaoGeralConstantes.TIPOPROC_RENDAS_CALCULADO_FOLHA;
			procRendas.titulo = "VALORES CALCULADOS";
			procRendas.destino = this.destino;
			procRendas.parametros = txtValorDebFolha.valor.toString() + "|" + numMatricula.toString();
			procRendas.usaSqlKey = true;
			//procRendas.procurar();
			
			chkRestricao.addEventListener(MouseEvent.CLICK,chkRestricao_Click);
			chkUtilizaPrazo.addEventListener(MouseEvent.CLICK,chkUtilizaPrazo_Click);
			cboCodFormaDebito.addEventListener(ListEvent.CHANGE,cboCodFormaDebito_Change);
			
			optPeriodoProxDeb.addEventListener(MouseEvent.CLICK,optGroupPeriodo_Click);
			optDiaFixo.addEventListener(MouseEvent.CLICK,optGroupPeriodo_Click);
			
			optCotas.addEventListener(MouseEvent.CLICK,optGroupTipoDebFolha_Click);
			optSalAssociado.addEventListener(MouseEvent.CLICK,optGroupTipoDebFolha_Click);
			optSalBase.addEventListener(MouseEvent.CLICK,optGroupTipoDebFolha_Click);
			optValorFixo.addEventListener(MouseEvent.CLICK,optGroupTipoDebFolha_Click);
			
			txtValorDebFolha.addEventListener(FocusEvent.FOCUS_OUT,txtValorDebFolha_LostFocus);
			txtDiaFixo.enabled = true;
			
			selecaoContaCorrente.filtrarContasCliente = objCliente.NUMPESSOA;
			
			//selecaoContaCorrente.procurarCCCliente(objCliente.NUMPESSOA);
			//selecaoContaCorrente.enableWindowSearch = false;
		}
		
		/**
		 * Método usado para abrir a tela de cadastro onde trabalha.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param 	evt Dados providos do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	01/06/2008
		 */
		private function abrirTelaCadastroTrabalha(evt:MouseEvent):void{
			var obj:Object = new Object();
			
			janelaEditarTrabalha.abrir(this,true,true);
			telaEditarTrabalha.novoRegistro();
		}
		
		/**
		 * Método que exclui o registro selecionado.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param evt Dados providos do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	01/06/2008
		 */
		public function excluirRegistro(obj:Object):void
		{
		}
		
		/**
		 * Método que carrega os dados ao alterar.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param 	obj Dados providos disparado pelo evento.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	01/06/2008 
		 */
		public function carregarRegistro(obj:Object):void
		{
			_novo = false;		
			
			MostraCursor.setBusyCursor("Carregando Registros ...", Application.application, MostraCursor.CURSOR_PESQUISAR);
			
			registro = new OutrosDadosVO();
			if(numMatricula == 0) {
				registro.numMatricula = obj.numMatricula;
			} else {
				registro.numMatricula = numMatricula;
			}
			
			var req:OutrosDadosReqDTO = new OutrosDadosReqDTO();
			req.dadosContaCapital = registro;
			
			servicos.invocarServico(Configuracoes.pacoteServicos + ".sisbr.OutrosDados",
				carregarRegistro_OnResult, carregarRegistro_OnFault, this.destino).obterDados(req);
		}
		
		private function carregarRegistro_OnFault(evt:FaultEvent):void {
			trace(evt.message);
		}
		
		/**
		 * Recebe o registro carregado e atribui aos campos.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @private
		 * 
		 * @param evt Dados providos do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	01/06/2008
		 */
		private function carregarRegistro_OnResult(evt:ResultEvent):void{
			registro = evt.result.dados["registro"];
			registroBkp = ObjectUtil.copy(registro) as OutrosDadosVO;
			
			//btnIncluiMatricula.enabled = (objCliente.CODPF_PJ == Constantes.COD_TIPO_PES_JURIDICA ? false : true);
			
			if(registro.codSituacao == Constantes.COD_SITUACAO_COOPERADO_ATIVO){
				chkUtilizaPrazo.enabled = true;
				cboCodFormaDebito.enabled = true;
				//cboMatricula.enabled = true;
				//btnIncluiMatricula.enabled = true;
				VSDadosDebito_Conta.enabled = true;
				VSDadosDebito_Folha.enabled = true;
			}else{
				chkUtilizaPrazo.enabled = false;
				cboCodFormaDebito.enabled = false;
				//cboMatricula.enabled = false;
				//btnIncluiMatricula.enabled = false;
				VSDadosDebito_Conta.enabled = false;
				VSDadosDebito_Folha.enabled = false;
			}
			
			MostraCursor.removeBusyCursor();
			
			preencherCampos();
		}
		
		/**
		 * Função que preenche os campos.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	01/06/2008
		 */
		public function preencherCampos():void
		{
			txtMatricula.valor = registro.numMatricula;
			selecaoContaCorrente.txtNumCC.valor = registro.numContaCorrente;
			chkUtilizaPrazo.selected = registro.bolDebIndeterminado;			
			txtValorDeb.valor = registro.valorDeb;
			txtValorCalculadoFolha.valor = 0;
			txtValorDebFolha.valor = registro.valorDeb;
			if(registro.dataVencimentoDeb != null){
				dtmDataVencimentoDeb.selectedDate = registro.dataVencimentoDeb.data;
				dtmDataVencimentoDebFolha.selectedDate = registro.dataVencimentoDeb.data;
			}
			
			//Quando for -1 é pq veio nulo da base
			if(registro.codFormaDeb!=-1)
				cboCodFormaDebito.procuraItemPorNome(registro.codFormaDeb, "idFormaPagamento");
			else
				cboCodFormaDebito.selectedIndex = -1;
			
			if(registro.tipoPeriodoDeb == -1){
				optPeriodoProxDeb.selected = false;
				optDiaFixo.selected = false;
				txtPeriodoProxDeb.visible = false;
				txtDiaFixo.visible = false;
			}else{
				if(registro.tipoPeriodoDeb==Constantes.COD_TIPO_PERIODO_DEBITO_CCA_DIARIO)
					optPeriodoProxDeb.selected = true;
				else
					optDiaFixo.selected = true;
				
				txtPeriodoProxDeb.valor = registro.periodoProxDeb;				
			}
			
			if (registro.codTipoValorDebito!=-1)
				if(registro.codTipoValorDebito==Constantes.COD_LST_TIPO_DEB_QTD_COTAS)
					optCotas.selected = true;
				else
					if(registro.codTipoValorDebito==Constantes.COD_LST_TIPO_DEB_PERC_SALARIO_RENDA)
						optSalAssociado.selected = true;
					else
						if(registro.codTipoValorDebito==Constantes.COD_LST_TIPO_DEB_PERC_SALARIO_BASE)
							optSalBase.selected = true;
						else
							if(registro.codTipoValorDebito==Constantes.COD_LST_TIPO_DEB_VALOR)
								optValorFixo.selected = true;
			
			//if(registro.uIDTrabalha==null)
			//	cboMatricula.selectedIndex=-1;
			//else
			//	cboMatricula.procuraItemPorNome(registro.uIDTrabalha,"uIdTrabalha");
			
			chkParticipaRateio.selected = registro.bolParticipaRateio;
			chkVoto.selected = registro.bolDireitoVoto;
			
			m_iCondicao = registro.iDCondicaoAssociacao;
			
			//if(m_iCondicao!=-1)
			//	procCondicao.procurar(registro.iDCondicaoAssociacao,0);
			//else
			//	txtCondicao.text = "";
			
			chkRestricao.selected = registro.bolRestricaoRateio;
			//txtRestricao.text = (registro.obsRestricao!=null ? registro.obsRestricao : "");
			
			chkRestricao_Click();
			chkUtilizaPrazo_Click();
			optGroupTipoDebFolha_Click();
			optGroupPeriodo_Click();	
			
		}
		
		/**
		 * Método que carrega as definições da tela.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param 	obj Dados do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	01/06/2008
		 */
		public function carregarDefinicoes(obj:Object=null):void
		{
			MostraCursor.setBusyCursor("Carregando Definições ...", Application.application, MostraCursor.CURSOR_PESQUISAR);
			
			var vo:OutrosDadosVO = new OutrosDadosVO();
			vo.codPfPj = objCliente.CODPF_PJ;
			vo.numMatricula = numMatricula;
			vo.numCliente = objCliente.NUMPESSOA;
			
			var req:OutrosDadosReqDTO = new OutrosDadosReqDTO();
			req.dadosContaCapital = vo;
			
			servicos.invocarServico(Configuracoes.pacoteServicos + ".sisbr.OutrosDados",
				carregarDefinicoes_OnResult,null, this.destino).
				obterDefinicoes(req);
		}
		
		/**
		 * Método que armazena as definições da tela.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param 	evt Dados do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	01/06/2008
		 */
		private function carregarDefinicoes_OnResult(evt:ResultEvent):void{
			listaFormaDebito = evt.result.listas["comboFormaDebito"];
			listaCotas = evt.result.listas["listaCotas"];
			aplicCoopDif = evt.result.dados["aplicCoopDif"];
			
			cboCodFormaDebito.dataProvider = listaFormaDebito;
			cboCodFormaDebito.labelField = "descFormaPagamento";
			
			listaMatricula = evt.result.listas["listaMatriculas"];
			//cboMatricula.dataProvider = listaMatricula;
			//cboMatricula.labelField = "descMatriculaFunc";
			
			carregarRegistro(new Object());
		}
		
		/**
		 * Método que carrega os campos em branco para um novo registro.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param 	evt Dados do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	01/06/2008
		 */
		public function novoRegistro():void
		{
		}
		
		/**
		 * Método que armazena os dados para serem gravados.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param 	evt Dados providos do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	01/06/2008
		 */
		private function gravarDados(evt:MouseEvent):void {
			MostraCursor.setBusyCursor("Gravando Registro!", Application.application);
			
			registro.numContaCorrente = selecaoContaCorrente.selecaoCCOVO.numContaCorrente;
			registro.bolDebIndeterminado = chkUtilizaPrazo.selected;
			registro.valorDeb = (m_bSoValor ? txtValorDeb.valor : txtValorDebFolha.valor);
			registro.codFormaDeb = (cboCodFormaDebito.selectedIndex == -1 ? -1 : cboCodFormaDebito.selectedItem.idFormaPagamento);
			registro.dataVencimentoDeb = DateTimeBase.getDateTime((m_bSoValor ? dtmDataVencimentoDeb.selectedDate : dtmDataVencimentoDebFolha.selectedDate));
			
			if(!m_bSoValor){
				registro.periodoProxDeb = 1;
				registro.tipoPeriodoDeb = Constantes.COD_TIPO_PERIODO_DEBITO_CCA_MENSAL;
			}else{
				if(optPeriodoProxDeb.selected){
					registro.periodoProxDeb = txtPeriodoProxDeb.valor;
					registro.tipoPeriodoDeb = Constantes.COD_TIPO_PERIODO_DEBITO_CCA_DIARIO;
				}else{
					if(optDiaFixo.selected){
						registro.periodoProxDeb = 1;
						registro.tipoPeriodoDeb = Constantes.COD_TIPO_PERIODO_DEBITO_CCA_MENSAL;
					}else{
						registro.periodoProxDeb = -1;
						registro.tipoPeriodoDeb = -1;
					}
				}
			}
			
			registro.uIDTrabalha = "";//( cboMatricula.selectedIndex != -1 ? cboMatricula.selectedItem.uIdTrabalha : "");
			registro.bolParticipaRateio = chkParticipaRateio.selected;
			
			if(chkUtilizaPrazo.selected)
				if(m_bSoValor)
					registro.codTipoValorDebito = Constantes.COD_LST_TIPO_DEB_VALOR;
				else
					if(optCotas.selected)
						registro.codTipoValorDebito = Constantes.COD_LST_TIPO_DEB_QTD_COTAS;
					else
						if(optSalAssociado.selected)
							registro.codTipoValorDebito = Constantes.COD_LST_TIPO_DEB_PERC_SALARIO_RENDA;
						else
							if(optSalBase.selected)
								registro.codTipoValorDebito = Constantes.COD_LST_TIPO_DEB_PERC_SALARIO_BASE;
							else
								if(optValorFixo.selected)
									registro.codTipoValorDebito = Constantes.COD_LST_TIPO_DEB_VALOR;
								else
									registro.codTipoValorDebito = -1;
			
			registro.bolDireitoVoto = chkVoto.selected;
			registro.iDCondicaoAssociacao = m_iCondicao;
			registro.bolRestricaoRateio = chkRestricao.selected;
			//			registro.obsRestricao = txtRestricao.text;			
			
			var req:OutrosDadosReqDTO = new OutrosDadosReqDTO();
			req.dadosContaCapital = registro;
			
			if(!_novo){
				servicos.invocarServico(Configuracoes.pacoteServicos + ".sisbr.OutrosDados",
					gravarDados_onResult, null, this.destino).
					alterarDados(req);
			}
		}
		
		/**
		 * Função executado após invocar o serviço de inclusão/alteração.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @private
		 * 
		 * @param 	evt Dados providos do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	01/06/2008
		 */
		private function gravarDados_onResult(evt:ResultEvent):void{
			MostraCursor.removeBusyCursor();
			this.pegaJanela().fecharJanela();
		}
		
		/**
		 * Método que preenche os campos que envolvem A Condição de Associado.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	27/05/2008
		 */
		private function dadosProcurarGeral(evt:EventData):void{
			if(evt.data){
				//txtCondicao.text = evt.data.DESCCONDICAOASSOCIACAO;
				m_iCondicao = evt.data.IDCONDICAOASSOCIACAO;
			}
			
			//txtCondicao.text = txtCondicao.text.toUpperCase();
		}
		
		/**
		 * Método que preenche os campos que envolvem o procurar geral rendas.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	01/06/2008
		 */
		private function dadosProcurarRendas(evt:EventData):void{
			if(evt.data){
				
			}
		}
		
		
		/**
		 * Método para fechar a tela.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param evt Dados providos do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	27/05/2008
		 */
		private function sair(evt:MouseEvent):void {
			if(!verificarAlteracoes())
				Alerta.show("Informações não foram salvas. Deseja realmente sair?", "Confirmação", Alerta.ALERTA_PERGUNTA, null, confirmaSair);
			else
				this.fecharJanela();
		}
		
		/**
		 * Método para confirmar o fechamento da tela.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @private
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	27/05/2008
		 */
		private function confirmaSair(evt:MouseEvent):void{			
			this.fecharJanela();
		}
		
		/**
		 * Método para verificar alteração dos dados da tela.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	01/06/2008
		 */
		private function verificarAlteracoes():Boolean{
			var intTipoPeriodoDeb:int;
			var intCodTipoValorDeb:int;
			
			if(registroBkp.numContaCorrente != selecaoContaCorrente.selecaoCCOVO.numContaCorrente)
				return false;
			
			if(registroBkp.bolDebIndeterminado != chkUtilizaPrazo.selected)
				return false;
			
			if(registroBkp.codFormaDeb == -1){
				if(registroBkp.codFormaDeb != cboCodFormaDebito.selectedIndex)
					return false;
			}else{
				if(cboCodFormaDebito.selectedItem.idFormaPagamento != registroBkp.codFormaDeb)
					return false;
			}
			
			if(m_bSoValor){
				if(txtValorDeb.valor != registroBkp.valorDeb)
					return false;
				
				if(FormataData.formataData(dtmDataVencimentoDeb.selectedDate) != FormataData.formataData(registroBkp.dataVencimentoDeb.data))
					return false;
				
				if(optPeriodoProxDeb.selected){
					intTipoPeriodoDeb = Constantes.COD_TIPO_PERIODO_DEBITO_CCA_DIARIO;
					if(txtPeriodoProxDeb.valor != registroBkp.periodoProxDeb)
						return false;
				}else{
					if(optDiaFixo.selected)
						intTipoPeriodoDeb = Constantes.COD_TIPO_PERIODO_DEBITO_CCA_MENSAL;					
					else
						intTipoPeriodoDeb = -1;
				}
				
				if(intTipoPeriodoDeb != registroBkp.tipoPeriodoDeb)
					return false;
			}else{
				if(txtValorDebFolha.valor != registroBkp.valorDeb)
					return false;
				
				if(FormataData.formataData(dtmDataVencimentoDebFolha.selectedDate) != FormataData.formataData(registroBkp.dataVencimentoDeb.data))
					return false;
				
				if(optCotas.selected)
					intCodTipoValorDeb = Constantes.COD_LST_TIPO_DEB_QTD_COTAS;
				else
					if(optSalAssociado.selected)
						intCodTipoValorDeb = Constantes.COD_LST_TIPO_DEB_PERC_SALARIO_RENDA;
					else
						if(optSalBase.selected)
							intCodTipoValorDeb = Constantes.COD_LST_TIPO_DEB_PERC_SALARIO_BASE;
						else
							if(optSalBase.selected)
								intCodTipoValorDeb = Constantes.COD_LST_TIPO_DEB_VALOR;
							else
								intCodTipoValorDeb = -1;
				
				if(intCodTipoValorDeb != registroBkp.codTipoValorDebito)
					return false;
			}
			
			/*if(registroBkp.uIDTrabalha == null){
			if(cboMatricula.selectedIndex != -1)
			return false;
			}else{
			if(registroBkp.uIDTrabalha != cboMatricula.selectedItem.uIdTrabalha)
			return false;
			}*/
			
			if(registroBkp.bolParticipaRateio != chkParticipaRateio.selected)
				return false;
			
			if(registroBkp.iDCondicaoAssociacao != m_iCondicao)
				return false;
			
			if(registroBkp.bolDireitoVoto != chkVoto.selected)
				return false;
			
			if(registroBkp.bolRestricaoRateio != chkRestricao.selected)
				return false;
			
//			if(registroBkp.obsRestricao != txtRestricao.text)
//				return false;
			
			return true;
		}
		
		/**
		 * Método que recupera os registros carregado na tela.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param evt Dados providos do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	05/06/2008
		 */
		private function cancelar(evt:MouseEvent):void{
			registro = ObjectUtil.copy(registroBkp) as OutrosDadosVO;
			
			preencherCampos();
		}
		
		/**
		 * Método executado ao clicar no check Restrição.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param evt Dados providos do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	05/06/2008
		 */
		private function chkRestricao_Click(evt:MouseEvent=null):void{
			if(chkRestricao.selected){
//				txtRestricao.enabled = true;
				lblRestricao.enabled = true;
//				lblObs.enabled = true;
//				txtRestricao.text = (registro.obsRestricao!=null ? registro.obsRestricao : "");					
			}else{
//				txtRestricao.enabled = false;
				lblRestricao.enabled = false;
//				lblObs.enabled = false;
//				txtRestricao.text = "";	
			}
		}
		
		/**
		 * Método executado ao clicar no check Utiliza Prazo.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param evt Dados providos do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	05/06/2008
		 */
		public function chkUtilizaPrazo_Click(evt:MouseEvent=null):void{
			if(chkUtilizaPrazo.selected){
				cboCodFormaDebito.enabled = true;
				
				if(cboCodFormaDebito.selectedIndex!=-1)
					cboCodFormaDebito_Change();
				
			}else{
				cboCodFormaDebito.enabled = false;
				VSDadosDebito_Conta.enabled = false;
				VSDadosDebito_Folha.enabled = false;
				txtDiaFixo.visible = false;
				txtPeriodoProxDeb.visible = false;
			}
		}
		
		/**
		 * Método executado ao selecionar a combo Forma de Pagamento.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param evt Dados providos do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	05/06/2008
		 */
		public function cboCodFormaDebito_Change(evt:ListEvent=null):void{
			
			m_bSoValor = true;
			
			if(cboCodFormaDebito.selectedItem.idFormaPagamento == Constantes.COD_MODO_LANCAMENTO_VIA_COBRANCA ||
				cboCodFormaDebito.selectedItem.idFormaPagamento == Constantes.COD_MODO_LANCAMENTO_VIA_CONTA){
				
				m_bSoValor = true;
				
				VSDadosDebito_Conta.enabled = true;
				VSDadosDebito.selectedIndex = VS_CONTA_CORRENTE;
				
				if(optPeriodoProxDeb.selected && optDiaFixo.selected){
					optPeriodoProxDeb.selected = true;
					txtPeriodoProxDeb.visible = true;
					txtDiaFixo.visible = false;
					txtPeriodoProxDeb.valor = (registro.periodoProxDeb==0 || registro.tipoPeriodoDeb == Constantes.COD_TIPO_PERIODO_DEBITO_CCA_MENSAL ? PERIODO_PROX_DEB : registro.periodoProxDeb);
				}
				
			}else{
				if(cboCodFormaDebito.selectedItem.idFormaPagamento == Constantes.COD_MODO_LANCAMENTO_VIA_FOLHA ||
					cboCodFormaDebito.selectedItem.idFormaPagamento == Constantes.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN){
					
					m_bSoValor = false;
					
					VSDadosDebito_Folha.enabled = true;
					VSDadosDebito.selectedIndex = VS_FOLHA;	
				}
			}	
		}
		
		/**
		 * Método executado ao selecionar o período do débito em conta.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param evt Dados providos do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	05/06/2008
		 */
		private function optGroupPeriodo_Click(evt:MouseEvent=null):void{
			
			if(optPeriodoProxDeb.selected){
				txtPeriodoProxDeb.visible = true;
				txtDiaFixo.visible = false;
				
				if(txtPeriodoProxDeb.valor==0)
					txtPeriodoProxDeb.valor = PERIODO_PROX_DEB;
				txtDiaFixo.valor = 0;
			}else{
				if(optDiaFixo.selected){
					txtPeriodoProxDeb.visible = false;
					txtDiaFixo.visible = true;
					
					txtDiaFixo.valor = (dtmDataVencimentoDeb.selectedDate==null ? 0 : dtmDataVencimentoDeb.selectedDate.date)
					
					txtPeriodoProxDeb.valor = 0;
				}
			}
		}
		
		/**
		 * Método executado ao selecionar o período do débito em folha.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param evt Dados providos do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	05/06/2008
		 */
		private function optGroupTipoDebFolha_Click(evt:MouseEvent=null):void{
			if(optCotas.selected){
				lblValorDebFolha.text = "Quantidade de Cotas:";
				lblValorCalculadoFolha.text = "Valor:";
				lblValorCalculadoFolha.visible = true;
				txtValorCalculadoFolha.visible = true;
				procRendas.visible = false;
			}else{
				if(optSalAssociado.selected){
					lblValorDebFolha.text = "Percentual:";
					lblValorCalculadoFolha.text = "Verificar Valores Calculados:";
					lblValorCalculadoFolha.visible = true;
					txtValorCalculadoFolha.visible = false;
					procRendas.visible = true;
				}else{
					if(optSalBase.selected){
						lblValorDebFolha.text = "Percentual:";
						lblValorCalculadoFolha.text = "Valor:";
						lblValorCalculadoFolha.visible = true;
						txtValorCalculadoFolha.visible = true;
						procRendas.visible = false;
					}else{
						if(optValorFixo.selected){
							lblValorDebFolha.text = "Valor:";
							lblValorCalculadoFolha.text = "Valor:";
							lblValorCalculadoFolha.visible = false;
							txtValorCalculadoFolha.visible = false;
							procRendas.visible = false;
							txtValorCalculadoFolha.valor = 0;
						}
					}
				}
			}
			
			txtValorDebFolha_LostFocus();
		}
		
		/**
		 * Função executada ao perder o foco do valor do débito em folha.
		 * <br/>
		 * <br/><b>Histórico de Alterações:</b>
		 * <br/><b>Data:</b>
		 * <br/><b>Motivo:</b>
		 * <br/>===============================
		 * 
		 * @param evt Dados providos do evento disparado.
		 * @author 	Daniel Cardoso de Sousa Leite
		 * @date	05/06/2008
		 */
		private function txtValorDebFolha_LostFocus(evt:FocusEvent=null):void{
			
			txtValorCalculadoFolha.valor = 0;
			
			if(optCotas.selected){
				if(listaCotas.length != 0)
					txtValorCalculadoFolha.valor = txtValorDebFolha.valor * (listaCotas[0].valorCota != null ? listaCotas[0].valorCota : 0);
			}else{
				if(optSalAssociado.selected){			
					procRendas.parametros = txtValorDebFolha.valor.toString() + "|" + numMatricula.toString();
				}else{
					if(optSalBase.selected){
						if(listaCotas.length != 0)
							txtValorCalculadoFolha.valor = txtValorDebFolha.valor * (listaCotas[0].valorSalarioBase == null ? 0 : listaCotas[0].valorSalarioBase) / 100
					}
				}
			}
		}
		
	}
}
package br.com.sicoob.sisbr.cca.movimentacao.bloqueio
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.cca.comum.nivelinstituicao.NivelInstituicaoEvent;
	import br.com.sicoob.sisbr.cca.comum.pesquisa.SelecionarContaCapitalEvent;
	import br.com.sicoob.sisbr.cca.movimentacao.bloqueio.IncluirBloqueioView;
	import br.com.sicoob.sisbr.cca.vo.BloqueioContaCapitalVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.BloqueioContaCapitalVO", BloqueioContaCapitalVO);
	public class IncluirBloqueio extends IncluirBloqueioView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.BloqueioContaCapitalServico";
		
		private var servico:ServicoJava = new ServicoJava();
		
		private var listaVO:ArrayCollection = new ArrayCollection();
		
		public function IncluirBloqueio() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);				
		}
		
		private function init(event:FlexEvent):void {
			controlarServico();
			controlarEventos();
			inicializaServico();
		}
		
		private function inicializaServico():void {
			servico.configurarDestino(this.destino);
			servico.source = SERVICO_SOURCE;
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			controlarTela();
		}
		
		private function controlarServico():void {
			servico.obterInformacoesBloqueio.addEventListener(ResultEvent.RESULT, retornoObterInformacoes);
			servico.incluir.addEventListener(ResultEvent.RESULT, retornoIncluir);
		}
		
		private function controlarEventos():void {
			procurarCCA.addEventListener(SelecionarContaCapitalEvent.ITEM_SELECIONADO, retornoProcurarCCA);
			procurarCCA.addEventListener(SelecionarContaCapitalEvent.REGISTRO_NAO_ENCONTRADO, retornoCCANaoEncontrada);
			compNivelInst.addEventListener(NivelInstituicaoEvent.NIVEL_INSTITUICAO_SELECIONADO, retornoNivelInst);
			compNivelInst.cmbSingular.addEventListener(Event.CHANGE, onChangeCmbSingular);
			
			btOk.addEventListener(MouseEvent.CLICK, incluir);
			btCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
		}
		
		private function onChangeCmbSingular(evt:Event):void {
			if (evt.currentTarget.selectedIndex > 0) {
				procurarCCA.idInstituicao = evt.currentTarget.selectedItem.codListaItem;
			}
		}
		
		private function retornoProcurarCCA(event:SelecionarContaCapitalEvent):void {
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			reqDTO.dados.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;
			servico.obterInformacoesBloqueio(reqDTO);
		}
		
		private function retornoCCANaoEncontrada(event:SelecionarContaCapitalEvent):void {
			limpar();
		}
		
		private function retornoNivelInst(event:NivelInstituicaoEvent):void {
			limpar();
		}
		
		private function limpar():void {
			procurarCCA.txtNumCCA.text = "";
			procurarCCA.txtNome.text = "";
			procurarCCA.txtCpfCnpj.text = "";
			procurarCCA.limparRegistro();
			limparDadosSaldoAtualCapital();
			limparDadosBloqueio();
		}
		
		private function limparDadosSaldoAtualCapital():void {
			vlrInteg.valor = 0;			
			vlrInteg.text = "0,00";			
			vlrBloqueado.valor = 0;			
			vlrBloqueado.text = "0,00";			
			vlrDisponivel.valor = 0;
			vlrDisponivel.text = "0,00";
		}
		
		private function limparDadosBloqueio():void {
			cboTipoBloqueio.dataProvider = new ArrayCollection();
			dtProtocolo.selectedDate = new Date();
			txtNumProtocolo.text = "";
			txtNumProcesso.text = "";
			txtValorBloqueio.text = "0,00";
		}
		
		private function controlarTela():void {
			compNivelInst.cmbCentral.percentWidth = new Number(100);
			compNivelInst.rtlCentral.width = new Number(100);
			
			compNivelInst.cmbSingular.percentWidth = new Number(100);
			compNivelInst.rtlSingular.width = new Number(100);
		}
		
		private function cancelar(evt:MouseEvent):void {
			limpar();
		}
		
		private function fechar(evt:MouseEvent):void {
			fecharJanela();
		}
		
		private function retornoObterInformacoes(event:ResultEvent):void {
			vlrInteg.valor = event.result.dados["valorIntegralizado"];			
			vlrBloqueado.valor = event.result.dados["valorBloqueado"];			
			vlrDisponivel.valor = (vlrInteg.valor - vlrBloqueado.valor);
			limparDadosBloqueio();
			cboTipoBloqueio.dataProvider = event.result.dados["cboTipoBloqueio"] as ArrayCollection;
			
			MostraCursor.removeBusyCursor();
		}
		
		private function incluir(evt:MouseEvent):void {
			if (!validarCampos()) {
				return;
			}
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();			
			reqDTO.dados.vo = carregarVO();
			this.servico.incluir(reqDTO);
		}
		
		private function validarCampos():Boolean {
			if(procurarCCA.txtNumCCA.text == "") {
				Alerta.show("O campo Conta Capital é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, procurarCCA.txtNumCCA);
				return false;
			}
			if(cboTipoBloqueio.selectedItem == null){
				Alerta.show("O campo Tipo de Bloqueio é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoBloqueio);
				return false;										
			}	
			if (dtProtocolo.selectedDate == null) {
				Alerta.show("O campo Data do Protocolo é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, dtProtocolo);
				return false;										
			}
			if (txtNumProtocolo.text == "" && txtNumProcesso.text == "") {
				Alerta.show("O campo Nº do Protocolo ou Nº do Processo deve ser preenchido.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			if(txtValorBloqueio.text == "" || txtValorBloqueio.valor <= 0){
				Alerta.show("O valor do bloqueio deve ser maior que zero (R$ 0,00).", "ATENÇÃO", Alerta.ALERTA_OK, txtValorBloqueio);
				return false;										
			}
			if (txtValorBloqueio.valor > vlrDisponivel.valor) {
				Alerta.show("O valor a bloquear não pode ser maior que o saldo disponível para bloqueio.", "ATENÇÃO", Alerta.ALERTA_OK, txtValorBloqueio);
				return false;										
			}
			if (dtProtocolo.selectedDate.getTime() > new Date().getTime()) {
				Alerta.show("A data do protocolo deve ser inferior ou igual a data atual.", "ATENÇÃO", Alerta.ALERTA_OK, dtProtocolo);
				return false;
			}
			return true;
		}
		
		private function carregarVO():BloqueioContaCapitalVO{
			var vo:BloqueioContaCapitalVO = new BloqueioContaCapitalVO();;
			vo.idInstituicao = new Number(compNivelInst.cmbSingular.selectedItem.codListaItem);
			vo.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;
			vo.codTipoBloqueio = new Number(cboTipoBloqueio.selectedItem["codListaItem"]);
			vo.dataProtocolo = DateTimeBase.getDateTimeEntity(dtProtocolo.selectedDate);
			vo.numProtocolo = txtNumProtocolo.text;
			vo.numProcesso = txtNumProcesso.text;
			vo.valorBloqueado = txtValorBloqueio.valor; 
			return vo;
		}
		
		private function retornoIncluir(evt:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			if (evt.result.dados["msg"]) {
				Alerta.show(evt.result.dados["msg"], "SUCESSO", Alerta.ALERTA_SUCESSO, null, null);
				this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));
				/* A PEDIDO DO GESTOR - manter tela aberta e apenas limpar campos
				(this.parentDocument as MovimentacaoBloqueio).recarregarConsulta();				
				fecharJanela();
				*/
				limpar();
			}
		}
		
	}
}
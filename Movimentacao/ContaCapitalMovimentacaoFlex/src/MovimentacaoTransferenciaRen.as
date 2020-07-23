package
{
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.comum.pesquisa.SelecionarContaCapitalEvent;
	import br.com.sicoob.sisbr.cca.comum.util.NumeroUtil;
	import br.com.sicoob.sisbr.cca.movimentacao.transferencia.MovimentacaoTransferenciaRenView;
	
	public class MovimentacaoTransferenciaRen extends MovimentacaoTransferenciaRenView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.TransferenciaContaCapitalServico";
		private const SERVICO_REL_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelContaCapitalServico";
		
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var _dadosRetorno:Object = new Object();
		
		
		public function MovimentacaoTransferenciaRen() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);				
		}
		
		private function init(event:FlexEvent):void {
			configurarServico();			
			controlarServico();			
		}
		
		private function configurarServico():void {
//			PortalModel.portal.obterDefinicoesDestino("servicosJava", onDestinoRecuperado);
			onDestinoRecuperado(this.destino);

		}
		
		private function onDestinoRecuperado(destino:DestinoVO):void {
			destinoVO = destino;
			inicializaServico();
		}
		
		private function inicializaServico():void {
			servico.configurarDestino(destinoVO);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
		}		
				
		private function controlarServico():void {			
			procurarCCADebito.addEventListener(SelecionarContaCapitalEvent.ITEM_SELECIONADO, retornoObterProcurarCCA);	
			procurarCCADebito.addEventListener(SelecionarContaCapitalEvent.REGISTRO_NAO_ENCONTRADO, retornoObterProcurarCCANaoEcontrado);	
			procurarCCACredito.addEventListener(SelecionarContaCapitalEvent.ITEM_SELECIONADO, retornoCCACredito);	
			servico.obterDadosTransferencia.addEventListener(ResultEvent.RESULT, retornoObterDadosTransferencia);	
			servico.incluir.addEventListener(ResultEvent.RESULT, retornoIncluir);			
			
			btCanc.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btOk.addEventListener(MouseEvent.CLICK, validarIncluir);
			
			procurarCCADebito.txtNumCCA.validarObrigatorio = true;			
			procurarCCACredito.txtNumCCA.validarObrigatorio = true;
			
		}				
		
		private function retornoObterProcurarCCA(event:SelecionarContaCapitalEvent):void {	
			limparCampos();
			reqDTO.dados.idContaCapital = procurarCCADebito.resultadoPesquisaVO.idContaCapital;
			reqDTO.dados.idPessoa = procurarCCADebito.resultadoPesquisaVO.idPessoaLegado;
			this.servico.obterDadosTransferencia(reqDTO);			
		}		
		
		private function retornoCCACredito(event:SelecionarContaCapitalEvent):void {	
			apagarTransfMesmaConta();			
		}
		
		private function retornoObterProcurarCCANaoEcontrado(event:SelecionarContaCapitalEvent):void {			
			limparCampos();
		}		
		
		private function fechar(evt:MouseEvent):void {
			super.fecharJanela();
		}		
		
		/**
		 * Retorna os valores da camada de apresentacao para o estado inicial 
		 */
		private function cancelar(evt:MouseEvent):void {
			iniciarTelaCancelar();
		}		
		
		private function iniciarTelaCancelar():void {
			
			procurarCCADebito.txtNumCCA.text = "";
			procurarCCADebito.txtNome.text = "";				
			procurarCCADebito.resultadoPesquisaVO.numContaCapital = 0;			
			procurarCCADebito.resultadoPesquisaVO.idContaCapital = 0;		
			
			limparCampos();						
						
		}	
		
		private function limparCampos():void {
			vlrIntegralizado.valor = 0;
			vlrMinimoExigido.valor = 0;
			vlrBloqueado.valor = 0;
			vlrDisponivel.valor = 0;
			vlrTransferir.valor = 0;			
			procurarCCACredito.txtNumCCA.text = "";
			procurarCCACredito.txtNome.text = "";				
			procurarCCACredito.resultadoPesquisaVO.numContaCapital = 0;			
			procurarCCACredito.resultadoPesquisaVO.idContaCapital = 0;		
			
		}		
		
		private function retornoObterDadosTransferencia(event:ResultEvent):void {
			
			_dadosRetorno = event.result.dados;
			
			vlrIntegralizado.valor = _dadosRetorno["vlrIntegralizado"];	
			vlrMinimoExigido.valor = _dadosRetorno["vlrMinimoExigido"];			
			vlrBloqueado.valor = _dadosRetorno["vlrBloqueado"];		
			
			var valorDisponivel:Number = new Number();
			valorDisponivel = (vlrIntegralizado.valor - (vlrMinimoExigido.valor + vlrBloqueado.valor));		
			
			if(valorDisponivel < 0){
				valorDisponivel = 0;
			}			
			
			vlrDisponivel.valor = NumeroUtil.ajustarArredondamento(valorDisponivel);
			
			apagarTransfMesmaConta();
			
			MostraCursor.removeBusyCursor();
			
		}				
		
		private function validarIncluir(evt:MouseEvent):void {	
			apagarTransfMesmaConta();
			
			if(!validarCampos()) {
				return;
			}
			incluir(evt);
		}		
		
		private function apagarTransfMesmaConta():void {		
			if(procurarCCADebito.txtNumCCA.text == procurarCCACredito.txtNumCCA.text) {
				procurarCCACredito.txtNumCCA.text = "";
				procurarCCACredito.txtNome.text = "";				
				procurarCCACredito.resultadoPesquisaVO.numContaCapital = 0;			
				procurarCCACredito.resultadoPesquisaVO.idContaCapital = 0;					
			}				
		}
		
		private function validarCampos():Boolean {		
			
			
			if(procurarCCADebito.txtNumCCA.text == "") {
				Alerta.show("O campo Conta Capital Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, procurarCCADebito.txtNumCCA);
				return false;
			}		
			
			if(procurarCCACredito.txtNumCCA.text == "") {
				Alerta.show("O campo Conta Capital Crédito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, procurarCCACredito.txtNumCCA);
				return false;
			}					
			
			if(vlrTransferir.valor <= 0 || vlrDisponivel.valor < vlrTransferir.valor){
				Alerta.show("O valor de capital a transferir deve ser maior que zero(0) e não pode ser maior que o valor de capital disponível.", "ATENÇÃO", Alerta.ALERTA_OK, vlrTransferir);
				return false;														
			}
			
			return true;
		}
		
		private function incluir(evt:MouseEvent=null):void {		
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();			
			
			dto.dados.idContaCapitalDebito = procurarCCADebito.resultadoPesquisaVO.idContaCapital;
			dto.dados.idInstituicaoDebito = procurarCCADebito.resultadoPesquisaVO.idInstituicao;
			dto.dados.numContaCapitalDebito = procurarCCADebito.resultadoPesquisaVO.numContaCapital;
						
			dto.dados.idContaCapitalCredito = procurarCCACredito.resultadoPesquisaVO.idContaCapital;
			dto.dados.idInstituicaoCredito = procurarCCACredito.resultadoPesquisaVO.idInstituicao;
			dto.dados.numContaCapitalCredito = procurarCCACredito.resultadoPesquisaVO.numContaCapital;
			
			dto.dados.vlrTransferir = vlrTransferir.valor; 
			
			this.servico.incluir(dto);			
		}	
		
		private function retornoIncluir(evt:ResultEvent):void {
			
			MostraCursor.removeBusyCursor();			
			Alerta.show("Dados gravados com sucesso. Deseja emitir o recibo?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, emitirReciboTransferenciaCapital, cancelar);
			
		}				
		
		private function emitirReciboTransferenciaCapital(evt:MouseEvent):void {
			var formatoRelatorio:String = null;
			var exibirPreImpressao:Boolean = false;					
//			var dtoRel:RequisicaoReqDTO = new RequisicaoReqDTO();
			var dtoRel:ParametroDTO = new ParametroDTO();							
			dtoRel.dados.idInstituicaoDebito = procurarCCADebito.resultadoPesquisaVO.idInstituicao;
			dtoRel.dados.numContaCapitalDebito = procurarCCADebito.resultadoPesquisaVO.numContaCapital;
			dtoRel.dados.idPessoaDebito = procurarCCADebito.resultadoPesquisaVO.idPessoa;				
			dtoRel.dados.idContaCapitalDebito = procurarCCADebito.resultadoPesquisaVO.idContaCapital;				
			
			dtoRel.dados.idInstituicaoCredito = procurarCCACredito.resultadoPesquisaVO.idInstituicao;
			dtoRel.dados.numContaCapitalCredito = procurarCCACredito.resultadoPesquisaVO.numContaCapital;
			dtoRel.dados.idPessoaCredito = procurarCCACredito.resultadoPesquisaVO.idPessoa;				
			dtoRel.dados.idContaCapitalCredito = procurarCCACredito.resultadoPesquisaVO.idContaCapital;				
			
			dtoRel.dados.vlrTransferir = vlrTransferir.valor; 
			
//			RelatorioUtil.getRelatorioUtil().emitirRelatorio("gerarReciboTransferenciaCapital",
//				SERVICO_REL_SOURCE, dtoRel, "RelReciboTransferenciaCapital", destinoVO, "Emitindo relatório",
//				formatoRelatorio, exibirPreImpressao);
			
			RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelReciboTransferenciaCapitalServicoRemote", 
				dtoRel, "CCA_RelReciboTransferenciaCapital", this.destino, "Emitindo relatório...", null, false);
			
			iniciarTelaCancelar();			
		}			
				
	}	
	
}
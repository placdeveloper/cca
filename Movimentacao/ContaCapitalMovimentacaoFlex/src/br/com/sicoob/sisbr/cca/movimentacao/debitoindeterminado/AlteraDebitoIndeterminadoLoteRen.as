package br.com.sicoob.sisbr.cca.movimentacao.debitoindeterminado
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.comum.vo.DebitoIndeterminadoRenVO;
	
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.DebitoIndeterminadoRenVO", DebitoIndeterminadoRenVO);
	public class AlteraDebitoIndeterminadoLoteRen extends AlteraDebitoIndeterminadoLoteRenView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.DebitoIndeterminadoServico";
		
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var vo:DebitoIndeterminadoRenVO;
		private var arrIdsDeb:ArrayCollection;
		private var arrIdsNumMatricula:ArrayCollection;
		
		private var informacaoGeralTxt:String = "Será alterado em lote apenas os débitos via conta corrente antes selecionados e que contenham conta corrente " + "\n" + "ativa e de primeira titularidade.";
		private var informacaoCCOIntervaloTxt:String = "Os débitos subsequentes serão realizados a cada 'N' dia(s) após a data do primeiro débito. Ex: Data Inicial " + "\n" +" do Débito 07/06/2016, Quant. de Dias é 10, débitos subsequentes = 'N' dias após o dia 07 ou seja "+ "\n" + " 17/06/2016, 27/06/2016 e 07/07/2016 e etc.";
		
		public var destinoPai:DestinoVO;
		
		public function AlteraDebitoIndeterminadoLoteRen(pArrIdsDeb:ArrayCollection, pArrIdsNumMatricula:ArrayCollection) {
			super();
			this.arrIdsDeb = pArrIdsDeb;
			this.arrIdsNumMatricula = pArrIdsNumMatricula;
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			configurarServico();
			
			informacaoGeral.text = informacaoGeralTxt;
			informacaoCCOIntervalo.text = informacaoCCOIntervaloTxt;
			
			definirVisibilidadeElementos(hboxSubtituloDadosDebito, false);
			definirVisibilidadeElementos(hboxDadosDebito, false);
			definirVisibilidadeElementos(hboxSubtituloPeriodo, false);
			definirVisibilidadeElementos(hboxDadosPeriodo, false);
			definirVisibilidadeElementos(hxBoxInformacaoCCOIntervalo, false);
		}
		
		private function configurarServico():void {
			onDestinoRecuperado(destinoPai);
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
			
			controlarServico();
			controlarEventos();
			
			servico.obterDefinicoesAlterarEmLote(reqDTO);
		}
		
		private function controlarServico():void {
			servico.obterDefinicoesAlterarEmLote.addEventListener(ResultEvent.RESULT, retornoObterDefinicoesAlterarEmLote);	
			servico.alterarEmLote.addEventListener(ResultEvent.RESULT, retornoAlterarEmLote);
		}
		
		private function controlarEventos():void {
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btOk.addEventListener(MouseEvent.CLICK, alterar);
			chkAplicarPercentual.addEventListener(MouseEvent.CLICK, onClickPercentual);
			
			optValor.addEventListener(MouseEvent.CLICK, onClickTipoAlteracao);
			optData.addEventListener(MouseEvent.CLICK, onClickTipoAlteracao);
			optValorData.addEventListener(MouseEvent.CLICK, onClickTipoAlteracao);
		}
		
		private function obterDefinicoesAlterarEmLote():void {
			servico.obterDefinicoesAlterarEmLote(reqDTO);
		}
		
		private function retornoObterDefinicoesAlterarEmLote(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
		}
		
		private function alterar(evt:MouseEvent):void {
			
			if(validar()) {
				
				var tipoAlteracao:int = 0; 
				
				this.vo = new DebitoIndeterminadoRenVO();
				this.vo.idsDebitoContaCapital = arrIdsDeb;
				this.vo.idsNumMatricula = arrIdsNumMatricula;
				
				if(optValor.selected) {
					if(chkAplicarPercentual.selected) {
						this.vo.percentual = percentualDebito.valor;
					} else {
						this.vo.vlrDebito = vlrDebito.valor;
					}
					tipoAlteracao = 1;
				}
				
				if(optData.selected) {
					this.vo.dataInicialDeb = DateTimeBase.getDateTime(dataInicialDeb.selectedDate);
					tipoAlteracao = 2;
				}
				
				if(optValorData.selected) {
					if(chkAplicarPercentual.selected) {
						this.vo.percentual = percentualDebito.valor;
					} else {
						this.vo.vlrDebito = vlrDebito.valor;
					}
					this.vo.dataInicialDeb = DateTimeBase.getDateTime(dataInicialDeb.selectedDate);
					tipoAlteracao = 3;
				}
				
				reqDTO.dados.vo = this.vo;
				reqDTO.dados.tipoAlteracao = tipoAlteracao;
				servico.alterarEmLote(reqDTO);
			}
		}
		
		private function validar():Boolean {
			if(!optValor.selected && !optValorData.selected && !optData.selected) {
				Alerta.show("O campo Tipo de Alteração é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, vlrDebito);
				return false;
			}
			
			if(optValor.selected || optValorData.selected) {
				
				if(chkAplicarPercentual.selected) {
					if (percentualDebito.text == "") {
						Alerta.show("O campo Percentual(%) é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, percentualDebito);
						return false;
					}
					if(percentualDebito.valor <= 0 || percentualDebito.valor > 100) {
						Alerta.show("O percentual sobre o valor anterior deve ser maior que 0 e menor ou igual a 100", "ATENÇÃO", Alerta.ALERTA_OK, percentualDebito);
						return false;
					}
					
				} else {
					if(vlrDebito.text == "") {
						Alerta.show("O campo Valor para Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, vlrDebito);
						return false;
					}
					
					if(vlrDebito.valor <= 0) {
						Alerta.show("O Valor para Débito deve ser maior que zero (R$ 0,00).", "ATENÇÃO", Alerta.ALERTA_OK, vlrDebito);
						return false;
					}
					
					if(vlrDebito.valor > 999999999.99) {
						Alerta.show("Valor para Débito inválido.", "ATENÇÃO", Alerta.ALERTA_OK, vlrDebito);
						return false;
					}
				}
			}
			
			if(optData.selected || optValorData.selected) {
				if(dataInicialDeb.selectedDate == null) {
					Alerta.show("O campo Data Inicial do Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, dataInicialDeb);
					return false;
				}
			}
			
			return true;
		}
		
		private function onClickTipoAlteracao(evt:MouseEvent):void {
			if(optValor.selected) {
				definirVisibilidadeElementos(hboxSubtituloDadosDebito, true);
				definirVisibilidadeElementos(hboxDadosDebito, true);
				
				definirVisibilidadeElementos(hboxSubtituloPeriodo, false);
				definirVisibilidadeElementos(hboxDadosPeriodo, false);
				definirVisibilidadeElementos(hxBoxInformacaoCCOIntervalo, false);
				
				height = 250;
			}
			
			if(optData.selected) {
				definirVisibilidadeElementos(hboxSubtituloDadosDebito, false);
				definirVisibilidadeElementos(hboxDadosDebito, false);
				
				definirVisibilidadeElementos(hboxSubtituloPeriodo, true);
				definirVisibilidadeElementos(hboxDadosPeriodo, true);
				definirVisibilidadeElementos(hxBoxInformacaoCCOIntervalo, true);
				
				height = 320;
			}
			
			if(optValorData.selected) {
				definirVisibilidadeElementos(hboxSubtituloDadosDebito, true);
				definirVisibilidadeElementos(hboxDadosDebito, true);
				
				definirVisibilidadeElementos(hboxSubtituloPeriodo, true);
				definirVisibilidadeElementos(hboxDadosPeriodo, true);
				definirVisibilidadeElementos(hxBoxInformacaoCCOIntervalo, true);
				
				height = 360;
			}
			
			onClickPercentual(null);
		}
		
		private function onClickPercentual(evt:MouseEvent):void {
			if(chkAplicarPercentual.selected) {
				
				lblVlrDebito.includeInLayout = false;
				lblVlrDebito.visible = false;
				vlrDebito.includeInLayout = false;
				vlrDebito.visible = false;
				
				lblPercentualDebito.includeInLayout = true;
				lblPercentualDebito.visible = true;
				percentualDebito.includeInLayout = true;
				percentualDebito.visible = true;
				
			} else {
				
				lblVlrDebito.includeInLayout = true;
				lblVlrDebito.visible = true;
				vlrDebito.includeInLayout = true;
				vlrDebito.visible = true;
				
				lblPercentualDebito.includeInLayout = false;
				lblPercentualDebito.visible = false;
				percentualDebito.includeInLayout = false;
				percentualDebito.visible = false;
			}
		}
		
		private function definirVisibilidadeElementos(el:Object, visivel:Boolean):void {
			for(var i:int = 0; i < el.numChildren; i++) {
				if(el.getChildAt(i) is UIComponent) {
					if(el.getChildAt(i).visible != null && el.getChildAt(i).includeInLayout != null) {
						el.getChildAt(i).visible = visivel;
						el.getChildAt(i).includeInLayout = visivel;
					}
					if(el.getChildAt(i).numChildren != null && el.getChildAt(i).numChildren > 0) {
						definirVisibilidadeElementos(el.getChildAt(i), visivel);
					}
				}
			}
			el.visible = visivel;
			el.includeInLayout = visivel;
		}
		
		private function retornoAlterarEmLote(event:ResultEvent):void {
			Alerta.show("Dados gravados com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO, null, fechar);
		}
		
		/**
		 * Encerra funcionalidade
		 */
		private function fechar(evt:MouseEvent):void {
			(this.parentDocument as DebitoIndeterminadoRen).recarregarConsulta(evt);
			super.fecharJanela();
		}
	}
}

package br.com.sicoob.sisbr.cca.movimentacao.debitoindeterminado
{
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	import flash.utils.setTimeout;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.comum.vo.DebitoIndeterminadoRenVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.DebitoIndeterminadoRenVO", DebitoIndeterminadoRenVO);
	public class AlteraDebitoIndeterminadoRen extends AlteraDebitoIndeterminadoRenView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.DebitoIndeterminadoServico";
		
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var vo:DebitoIndeterminadoRenVO;
		private var arrVO:ArrayCollection;
		
		private var informacaoCCODiaFixo:String = "Os débitos subsequentes serão realizados no dia referente a data inicial do débito." + "\n" + "Ex: Data Inicial do Débito = 07/06/2016, débitos subsequentes = todo dia 07 ou seja 07/07/2016, 07/08/2016 e etc.";
		private var informacaoCCOIntervalo:String = "Os débitos subsequentes serão realizados a cada 'N' dia(s) após a data do primeiro débito." + "\n" + "Ex: Data Inicial do Débito = 07/06/2016. Quant. de Dias é 10, débitos subsequentes = 'N' dias após o dia 07 ou seja 17/06/2016, 27/06/2016 e 07/07/2016 e etc.";
		private var legendaFolhaBanco:String = "Para a forma de débito em questão (folha/banco), a data é meramente um indicador de quando foi realizado o cadastro do débito por tempo indeterminado " + "\n" + "para o associado. A data real do débito vai ser a data do retorno da remessa gerada pela empresa.";
		
		public var listaCco:ArrayCollection = new ArrayCollection();
		
		public var destinoPai:DestinoVO;
		
		
		public function AlteraDebitoIndeterminadoRen(arrVO:ArrayCollection) {
			super();
			this.arrVO = arrVO;
			
			this.vo = new DebitoIndeterminadoRenVO();
			this.vo.idDebitoContaCapital = arrVO[0]; 
			
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			configurarServico();
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
			
			obterDefinicoesAlterar();
		}
		
		private function controlarServico():void {
			servico.obterDefinicoesAlterar.addEventListener(ResultEvent.RESULT, retornoObterDefinicoesAlterar);
			servico.calcularValorDebitoCota.addEventListener(ResultEvent.RESULT, retornoCalcularValorDebitoCota);
			servico.calcularValorDebitoPercentual.addEventListener(ResultEvent.RESULT, retornoCalcularValorDebitoPercentual);
			servico.carregarTipoInteg.addEventListener(ResultEvent.RESULT, retornoCarregarTipoInteg);
			servico.alterar.addEventListener(ResultEvent.RESULT, retornoAlterar);
		}
		
		private function controlarEventos():void {
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			tipoInteg.addEventListener(Event.CHANGE, onChangeTipoInteg);
			formaDebito.addEventListener(Event.CHANGE, onChangeFormaDebito);
			periodoDebito.addEventListener(Event.CHANGE, onChangePeriodoDebito);
			qtdCotas.addEventListener(FocusEvent.FOCUS_OUT, onFocusChangeCotas);
			percentual.addEventListener(FocusEvent.FOCUS_OUT, onFocusChangePercentual);
			btOk.addEventListener(MouseEvent.CLICK, alterar);
		}
		
		private function obterDefinicoesAlterar():void {
			reqDTO.dados.vo = this.vo;
			servico.obterDefinicoesAlterar(reqDTO);
		}
		
		private function retornoObterDefinicoesAlterar(event:ResultEvent):void {
			controlarExibicaoInicial();
			
			this.vo = event.result.dados["vo"];
			
			tipoInteg.dataProvider = event.result.dados["comboTipoInteg"] as ArrayCollection;
			tipoInteg.procuraItemPorNome(this.vo.tipoInteg.toString(), "codListaItem");
			
			periodoDebito.dataProvider = event.result.dados["comboPeriodoDebito"] as ArrayCollection;
			periodoDebito.procuraItemPorNome(this.vo.periodoDebito.toString(), "codListaItem");
			
			formaDebito.dataProvider = event.result.dados["comboFormaDebito"] as ArrayCollection;
			formaDebito.procuraItemPorNome(this.vo.formaDebito.toString(), "codListaItem");
			
			listaCco.removeAll();
			listaCco.addAll(event.result.dados["comboCco"] as ArrayCollection);
			cboCco.dataProvider = listaCco.list;
			cboCco.procuraItemPorNome(this.vo.numContaCorrente.toString(), "codListaItem");
			
			controlarExibicaoTipoInteg();
			controlarExibicaoFormaDebito();
			controlarExibicaoPeriodoDebito();
			
			if(this.vo.formaDebito == 0) {
				this.vlrDebito.valor = this.vo.vlrDebito;
			}
			if(this.vo.formaDebito == 1) {
				this.percentual.valor = this.vo.percentual;
				this.vlrDebito.valor = this.vo.vlrDebito;
			}
			if(this.vo.formaDebito == 2) {
				this.percentual.valor = this.vo.percentual;
				this.vlrDebito.valor = this.vo.vlrDebito;
			}
			if(this.vo.formaDebito == 3) {
				this.qtdCotas.valor = this.vo.qtdCotas;
				this.vlrDebito.valor = this.vo.vlrDebito;
			}
			
			nome.text = StringUtils.trim(this.vo.nome);
			numContaCapital.text = this.vo.numContaCapital.toString();
			qtdDias.valor = this.vo.numPeriodo;
			dataInicialDeb.selectedDate = this.vo.dataInicialDeb.data;
			
			MostraCursor.removeBusyCursor();
		}
		
		private function alterar(me:MouseEvent):void {
			if(validar()) {
				this.vo = carregarVO();
				reqDTO = new RequisicaoReqDTO();
				reqDTO.dados.vo = this.vo;
				servico.alterar(reqDTO);
			}
		}
		
		private function retornoAlterar(event:ResultEvent):void {
			Alerta.show("Dados gravados com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO, null);
			MostraCursor.removeBusyCursor();
			fechar(null);
		}
		
		private function validar():Boolean {
			
			if (tipoInteg.selectedItem == null) {
				Alerta.show("O campo Forma de Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, tipoInteg);
				return false;
			}
			
			if (tipoInteg.text == "") {
				Alerta.show("O campo Forma de Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, tipoInteg);
				return false;
			}				
			
			if (qtdCotas.visible && qtdCotas.text == "") {
				Alerta.show("O campo Quant. de Cotas é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, qtdCotas);
				return false;
			}
			
			if (percentual.visible && percentual.text == "") {
				Alerta.show("O campo Percentual (%) é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, percentual);
				return false;
			}
			
			if (vlrDebito.visible && vlrDebito.text == "") {
				Alerta.show("O campo Valor para Débito (R$) é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, vlrDebito);
				return false;
			}
			
			if (vlrDebito.visible && vlrDebito.valor <= 0) {
				Alerta.show("O Valor para Débito deve ser maior que zero (R$ 0,00).", "ATENÇÃO", Alerta.ALERTA_OK, vlrDebito);
				return false;
			}
			
			if (vlrDebito.visible && vlrDebito.valor > 999999999.99) {
				Alerta.show("Valor para Débito inválido.", "ATENÇÃO", Alerta.ALERTA_OK, vlrDebito);
				return false;
			}
			
			if (qtdDias.visible && qtdDias.text == "") {
				Alerta.show("O campo Quant. de Dias é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, qtdDias);
				return false;
			}
			
			//CCO
			if(tipoInteg.selectedItem["codListaItem"] == 2) {
				if (periodoDebito.selectedItem == null) {
					Alerta.show("O campo Período para Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, periodoDebito);
					return false;
				}
				
				//Quantidade de dias
				if(periodoDebito.selectedItem["codListaItem"] == 0 && (qtdDias.valor <= 0 || qtdDias.valor > 365)) {
					Alerta.show("A quantidade de dias para o débito deve ser maior que 0 e menor ou igual a 365.", "ATENÇÃO", Alerta.ALERTA_OK, qtdDias);
					return false;
				}
			}
			
			//Banco/Folha
			if(tipoInteg.selectedItem["codListaItem"] == 3){ // || tipoInteg.selectedItem["codListaItem"] == 6) { Foi necessário retirar essa validação devido a regra de nogocio esta sendo utilizada de maneira indevida
				if (formaDebito.selectedItem == null) {
					Alerta.show("O campo Tipo de Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, formaDebito);
					return false;
				}
				
				if(formaDebito.selectedItem["codListaItem"] == 1 || formaDebito.selectedItem["codListaItem"] == 2) {
					if(percentual.valor <= 0 || percentual.valor > 100) {
						Alerta.show("O Percentual deve ser maior que 0 e menor ou igual a 100.", "ATENÇÃO", Alerta.ALERTA_OK, vlrDebito);
						return false;
					}
				}
				
			}
			
			if(dataInicialDeb.selectedDate == null) {
				Alerta.show("O campo Data Inicial do Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, vlrDebito);
				return false;
			}
			
			return true;
		}
		
		private function carregarVO():DebitoIndeterminadoRenVO {
			this.vo.tipoInteg = tipoInteg.selectedItem["codListaItem"];
			this.vo.numContaCorrente = new Number(cboCco.text);
			this.vo.formaDebito = formaDebito.selectedItem["codListaItem"];
			this.vo.qtdCotas = qtdCotas.valor;
			this.vo.percentual = percentual.valor;
			this.vo.vlrDebito = vlrDebito.valor;
			this.vo.periodoDebito = periodoDebito.selectedItem["codListaItem"];
			this.vo.dataInicialDeb = DateTimeBase.getDateTime(dataInicialDeb.selectedDate);
			this.vo.numPeriodo = qtdDias.valor;
			return vo;
		}
		
		private function carregarTipoInteg():void {
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			reqDTO.dados.codTipoPessoa = this.vo.codTipoPessoa;
			servico.carregarTipoInteg(reqDTO);
		}
		
		private function retornoCarregarTipoInteg(event:ResultEvent):void {
			tipoInteg.dataProvider = event.result.dados["comboTipoInteg"] as ArrayCollection;
			controlarExibicaoInicial();
			MostraCursor.removeBusyCursor(); 
		}
		
		private function onFocusChangeCotas(evt:Event):void {
			if(qtdCotas.valor > 0) {
				reqDTO = new RequisicaoReqDTO();
				reqDTO.dados.qtdCotas = qtdCotas.valor;
				setTimeout(function():void {
					servico.calcularValorDebitoCota(reqDTO);
				},100);
			} else {
				vlrDebito.valor = 0;
			}
		}
		
		private function retornoCalcularValorDebitoCota(event:ResultEvent):void {
			vlrDebito.valor = new Number(event.result.dados["valorDebito"]);
			MostraCursor.removeBusyCursor();
		}
		
		private function onFocusChangePercentual(evt:Event):void {
			if(percentual.valor > 0 && this.vo.idPessoa > 0 && this.vo.idInstituicao > 0) {
				reqDTO = new RequisicaoReqDTO();
				reqDTO.dados.percentual = percentual.valor;
				reqDTO.dados.idPessoa = this.vo.idPessoa;
				reqDTO.dados.idInstituicao = this.vo.idInstituicao;
				reqDTO.dados.percentualSalarioBase = formaDebito.selectedItem != null && formaDebito.selectedItem["codListaItem"] == 2;
				setTimeout(function():void {
					servico.calcularValorDebitoPercentual(reqDTO);
				},100);
			} else {
				vlrDebito.valor = 0;
			}
		}
		
		private function retornoCalcularValorDebitoPercentual(event:ResultEvent):void {
			vlrDebito.valor = new Number(event.result.dados["valorDebito"]);
			MostraCursor.removeBusyCursor();
		}
		
		private function onChangeTipoInteg(evt:Event):void {
			controlarExibicaoInicial();
			controlarExibicaoTipoInteg();
		}
		
		private function onChangeFormaDebito(evt:Event):void {
			controlarExibicaoFormaDebito();
		}
		
		private function onChangePeriodoDebito(evt:Event):void {
			controlarExibicaoPeriodoDebito();
		}
		
		private function controlarExibicaoInicial():void {
			labelCco.visible = false;
			labelCco.enabled = false;
			labelCco.includeInLayout = false;
			cboCco.enabled = false;
			cboCco.visible = false;
			cboCco.includeInLayout = false;
			
			boxFormaDebito.visible = false;
			boxFormaDebito.includeInLayout = false;
			formaDebito.selectedIndex = 0;
			
			lblQtdCotas.visible = false;
			lblQtdCotas.includeInLayout = false;
			qtdCotas.visible = false;
			qtdCotas.includeInLayout = false;
			qtdCotas.valor = 0;
			
			lblPercentual.visible = false;
			lblPercentual.includeInLayout = false;
			percentual.visible = false;
			percentual.includeInLayout = false;
			percentual.valor = 0;
			
			lblVlrDebito.visible = true;
			lblVlrDebito.width = 110;
			lblVlrDebito.setStyle("paddingLeft", 0) ;
			lblVlrDebito.includeInLayout = true;
			vlrDebito.visible = true;
			vlrDebito.includeInLayout = true;
			vlrDebito.enabled = true;
			vlrDebito.valor = 0;
			
			lblQtdDias.includeInLayout = false;
			lblQtdDias.visible = false;
			qtdDias.includeInLayout = false;
			qtdDias.visible = false;
			qtdDias.valor = 0;
			
			lblDataInicialDeb.x = 10;
			lblDataInicialDeb.y = 0;
			dataInicialDeb.x = 130;
			dataInicialDeb.y = 0;
			
			lblPeriodoDebito.visible = false;
			lblPeriodoDebito.includeInLayout = false;
			periodoDebito.visible = false;
			periodoDebito.includeInLayout = false;
			periodoDebito.selectedIndex = 1;
			
			informacao.text = informacaoCCODiaFixo;
		}
		
		private function controlarExibicaoTipoInteg():void {
			if(tipoInteg.selectedItem != null) {
				//CCO
				if(tipoInteg.selectedItem["codListaItem"] == 2) {
					labelCco.enabled = true;
					labelCco.visible = true;
					labelCco.includeInLayout = true;
					
					cboCco.enabled = true;
					cboCco.visible = true;
					cboCco.includeInLayout = true;
					
					lblPeriodoDebito.visible = true;
					lblPeriodoDebito.includeInLayout = true;
					periodoDebito.visible = true;
					periodoDebito.enabled = true;
					periodoDebito.includeInLayout = true;
					
					lblDataInicialDeb.x = 305;
					lblDataInicialDeb.y = 0;
					dataInicialDeb.x = 425;
					dataInicialDeb.y = 0;
				}
				
				//Banco/Folha
				if(tipoInteg.selectedItem["codListaItem"] == 3){ // || tipoInteg.selectedItem["codListaItem"] == 6) { Foi necessário retirar essa validação devido a regra de nogocio esta sendo utilizada de maneira indevida
					informacao.text = legendaFolhaBanco;
					setTimeout(function():void {
						boxFormaDebito.visible = true;
						boxFormaDebito.includeInLayout = true;
					}, 300);
				}
			}
		}
		
		private function controlarExibicaoFormaDebito():void {
			lblQtdCotas.visible = false;
			lblQtdCotas.includeInLayout = false;
			qtdCotas.visible = false;
			qtdCotas.includeInLayout = false;
			qtdCotas.valor = 0;
			
			lblPercentual.visible = false;
			lblPercentual.includeInLayout = false;
			percentual.visible = false;
			percentual.includeInLayout = false;
			percentual.valor = 0;
			
			lblVlrDebito.visible = false;
			lblVlrDebito.includeInLayout = false;
			lblVlrDebito.width = 110;
			lblVlrDebito.setStyle("paddingLeft", 0);
			vlrDebito.visible = false;
			vlrDebito.includeInLayout = false;
			vlrDebito.valor = 0;
			
			if(formaDebito.selectedItem != null) {
				if(formaDebito.selectedItem["codListaItem"] == 0) {
					lblVlrDebito.visible = true;
					lblVlrDebito.includeInLayout = true;
					vlrDebito.enabled = true;
					vlrDebito.visible = true;
					vlrDebito.includeInLayout = true;
				}
				
				if(formaDebito.selectedItem["codListaItem"] == 1 || formaDebito.selectedItem["codListaItem"] == 2) {
					lblPercentual.visible = true;
					lblPercentual.includeInLayout = true;
					percentual.visible = true;
					percentual.includeInLayout = true;
					
					lblVlrDebito.width = 120;
					lblVlrDebito.setStyle("paddingLeft", 10) ;
					lblVlrDebito.visible = true;
					lblVlrDebito.includeInLayout = true;
					vlrDebito.enabled = false;
					vlrDebito.visible = true;
					vlrDebito.includeInLayout = true;
				}
				
				if(formaDebito.selectedItem["codListaItem"] == 3) {
					lblQtdCotas.visible = true;
					lblQtdCotas.includeInLayout = true;
					qtdCotas.visible = true;
					qtdCotas.includeInLayout = true;
					
					lblVlrDebito.width = 120;
					lblVlrDebito.setStyle("paddingLeft", 10);
					lblVlrDebito.visible = true;
					lblVlrDebito.includeInLayout = true;
					vlrDebito.enabled = false;
					vlrDebito.visible = true;
					vlrDebito.includeInLayout = true;
				}
			}
		}
		
		private function controlarExibicaoPeriodoDebito():void {
			qtdDias.valor = 0;
			
			if (periodoDebito.selectedItem != null && periodoDebito.visible) {
				if(periodoDebito.selectedItem["codListaItem"] == 0) {
					informacao.text = informacaoCCOIntervalo;
					
					lblQtdDias.includeInLayout = true;
					lblQtdDias.visible = true;
					lblQtdDias.x = 305;
					lblQtdDias.y = 0;
					qtdDias.includeInLayout = true;
					qtdDias.visible = true;
					qtdDias.x = 425;
					qtdDias.y = 0;
					
					lblDataInicialDeb.x = 10;
					lblDataInicialDeb.y = 30;
					dataInicialDeb.x = 130;
					dataInicialDeb.y = 30;
				}
				
				if(periodoDebito.selectedItem["codListaItem"] == 1) {
					informacao.text = informacaoCCODiaFixo;
					
					if(tipoInteg.selectedItem["codListaItem"] == 2) {
						lblDataInicialDeb.x = 305;
						lblDataInicialDeb.y = 0;
						dataInicialDeb.x = 425;
						dataInicialDeb.y = 0;
					} else {
						lblDataInicialDeb.x = 10;
						lblDataInicialDeb.y = 0;
						dataInicialDeb.x = 130;
						dataInicialDeb.y = 0;
					}
					
					qtdDias.includeInLayout = false;
					qtdDias.visible = false;
					lblQtdDias.includeInLayout = false;
					lblQtdDias.visible = false;
				}
			}
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
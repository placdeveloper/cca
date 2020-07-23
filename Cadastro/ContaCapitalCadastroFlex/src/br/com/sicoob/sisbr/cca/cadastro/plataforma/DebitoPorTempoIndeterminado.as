package br.com.sicoob.sisbr.cca.cadastro.plataforma{
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
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.IEdicaoPlataformaAtendimento;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.sisbr.cca.comum.vo.DebitoIndeterminadoRenVO;
	
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.DebitoIndeterminadoRenVO", DebitoIndeterminadoRenVO);
	public class DebitoPorTempoIndeterminado extends DebitoPorTempoIndeterminadoView implements IEdicaoPlataformaAtendimento{
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.DebitoIndeterminadoServico";
		
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var vo:DebitoIndeterminadoRenVO;
		public var pessoaSelecionada:PessoaPlataformaVO;
		private var arrVO:ArrayCollection;
		private var isAlterar:Boolean;
		public var idContaCapital:Number;
		public var idDebitoContaCapital:Number;		
		
		private var informacaoCCODiaFixo:String = "Os débitos subsequentes serão realizados no dia referente a data inicial do débito." + "\n" + "Ex: Data Inicial do Débito = 07/06/2016, débitos subsequentes = todo dia 07 ou seja 07/07/2016, 07/08/2016 e etc.";
		private var informacaoCCOIntervalo:String = "Os débitos subsequentes serão realizados a cada 'N' dia(s) após a data do primeiro débito." + "\n" + "Ex: Data Inicial do Débito = 07/06/2016. Quant. de Dias é 10, débitos subsequentes = 'N' dias após o dia 07"  + "\n" + "ou seja 17/06/2016, 27/06/2016 e 07/07/2016 e etc.";
		private var legendaFolhaBanco:String = "Para a forma de débito em questão (folha), a data é meramente um indicador de quando foi realizado o cadastro  " + "\n" + "do débito por tempo indeterminado para o associado. A data real do débito vai ser a data do retorno da remessa gerada"+ "\n" + "pela empresa.";
		public var listaCco:ArrayCollection = new ArrayCollection();
		public var telaCadastroContaCapitalAtendimento:CadastroContaCapitalAtendimento; 
		
		public var destinoParent:DestinoVO;		
		
		public function DebitoPorTempoIndeterminado() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			inicializaServico();
			controlarEventos();
			obterDefinicoes();			
		}
	
		private function inicializaServico():void {
			servico.configurarDestino(destinoParent);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			controlarServico();
		}		
		
		private function controlarServico():void {
			servico.obterDefinicoesPlataforma.addEventListener(ResultEvent.RESULT, retornoObterDefinicoesPlataforma);
			servico.calcularValorDebitoCota.addEventListener(ResultEvent.RESULT, retornoCalcularValorDebitoCota);
			servico.calcularValorDebitoPercentual.addEventListener(ResultEvent.RESULT, retornoCalcularValorDebitoPercentual);
			//servico.carregarTipoInteg.addEventListener(ResultEvent.RESULT, retornoCarregarTipoInteg);
			servico.alterar.addEventListener(ResultEvent.RESULT, retornoAlterar);
			servico.incluir.addEventListener(ResultEvent.RESULT, retornoAlterar);
			servico.excluir.addEventListener(ResultEvent.RESULT, retornoExcluir);
		}
		
		private function controlarEventos():void {
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btOk.addEventListener(MouseEvent.CLICK, alterar);
			btExcluir.addEventListener(MouseEvent.CLICK, confirmaExcluir);
			
			tipoInteg.addEventListener(Event.CHANGE, onChangeTipoInteg);
			formaDebito.addEventListener(Event.CHANGE, onChangeFormaDebito);
			periodoDebito.addEventListener(Event.CHANGE, onChangePeriodoDebito);
			qtdCotas.addEventListener(FocusEvent.FOCUS_OUT, onFocusChangeCotas);
			percentual.addEventListener(FocusEvent.FOCUS_OUT, onFocusChangePercentual);
		}
		
		private function obterDefinicoes():void {
			reqDTO.dados.idContaCapital = idContaCapital;
			reqDTO.dados.idPessoa = pessoaSelecionada.idPessoa;
			reqDTO.dados.codPessoa = pessoaSelecionada.codTipoPessoa;
			servico.obterDefinicoesPlataforma(reqDTO);
		}		

		private function retornoObterDefinicoesPlataforma(event:ResultEvent):void {
			controlarExibicaoInicial();
			this.vo = event.result.dados["vo"];
			
			if(this.vo != null){
				isAlterar = true;
				configurarModoAlterar(event, this.vo);
				btExcluir.visible = true;
				btExcluir.includeInLayout = true;				
			}else{
				configurarModoIncluir(event);
				btExcluir.visible = false;
				btExcluir.includeInLayout = false;				
			}
		}
		
		private function configurarModoAlterar(event:ResultEvent, vo:DebitoIndeterminadoRenVO):void {
			carregarCombosProvider(event);	
			habilitarComboCco(event);	
			cboCco.procuraItemPorNome(this.vo.numContaCorrente.toString(), "codListaItem");
			controlarExibicaoTipoInteg();
			controlarExibicaoFormaDebito();
			controlarExibicaoPeriodoDebito();
			validarFormaDebito(this.vo);
			
			nome.text = StringUtils.trim(this.vo.nome);
			numContaCapital.text = this.vo.numContaCapital.toString();
			qtdDias.valor = this.vo.numPeriodo;
			dataInicialDeb.selectedDate = this.vo.dataInicialDeb.data;
			idDebitoContaCapital = this.vo.idDebitoContaCapital;
			
			MostraCursor.removeBusyCursor();
		}
		
		private function configurarModoIncluir(event:ResultEvent):void {
			this.vo = new DebitoIndeterminadoRenVO;
			isAlterar = false;
			tipoInteg.dataProvider = event.result.dados["comboTipoInteg"] as ArrayCollection;
			formaDebito.dataProvider = event.result.dados["comboFormaDebito"] as ArrayCollection;
			periodoDebito.dataProvider = event.result.dados["comboPeriodoDebito"] as ArrayCollection;
			nome.text = StringUtils.trim(pessoaSelecionada.nomeCompleto);
			controlarExibicaoTipoInteg();
			controlarExibicaoFormaDebito();
			controlarExibicaoPeriodoDebito();
			habilitarBntValorDebito()
			habilitarComboCco(event);
			MostraCursor.removeBusyCursor();
		}
		
		private function validarFormaDebito(vo:DebitoIndeterminadoRenVO):void {
			if(vo.formaDebito == 0) {
				this.vlrDebito.valor = vo.vlrDebito;
			}
			if(vo.formaDebito == 1) {
				this.percentual.valor = vo.percentual;
				this.vlrDebito.valor = vo.vlrDebito;
			}
			if(vo.formaDebito == 2) {
				this.percentual.valor = vo.percentual;
				this.vlrDebito.valor = vo.vlrDebito;
			}
			if(vo.formaDebito == 3) {
				this.qtdCotas.valor = vo.qtdCotas;
				this.vlrDebito.valor = vo.vlrDebito;
			}
		}
		
		private function carregarCombosProvider(event:ResultEvent):void {
			tipoInteg.dataProvider = event.result.dados["comboTipoInteg"] as ArrayCollection;
			tipoInteg.procuraItemPorNome(this.vo.tipoInteg.toString(), "codListaItem");
			
			periodoDebito.dataProvider = event.result.dados["comboPeriodoDebito"] as ArrayCollection;
			periodoDebito.procuraItemPorNome(this.vo.periodoDebito.toString(), "codListaItem");
			
			formaDebito.dataProvider = event.result.dados["comboFormaDebito"] as ArrayCollection;
			formaDebito.procuraItemPorNome(this.vo.formaDebito.toString(), "codListaItem");
		}
		
		private function habilitarBntValorDebito():void {
			lblVlrDebito.visible = true;
			lblVlrDebito.width = 110;
			lblVlrDebito.setStyle("paddingLeft", 0) ;
			lblVlrDebito.includeInLayout = true;
			vlrDebito.visible = true;
			vlrDebito.includeInLayout = true;
			vlrDebito.enabled = true;
			vlrDebito.valor = 0;	
		}
		
		private function habilitarComboCco(event:ResultEvent):void {
			listaCco.removeAll();
			listaCco.addAll(event.result.dados["comboCco"] as ArrayCollection);
			cboCco.dataProvider = listaCco.list;
		}
		
		private function retornoCalcularValorDebitoCota(event:ResultEvent):void {
			vlrDebito.valor = new Number(event.result.dados["valorDebito"]);
			MostraCursor.removeBusyCursor();
		}
		
		private function retornoCalcularValorDebitoPercentual(event:ResultEvent):void {
			vlrDebito.valor = new Number(event.result.dados["valorDebito"]);
			MostraCursor.removeBusyCursor();
		}
		
		private function retornoCarregarTipoInteg(event:ResultEvent):void {
			tipoInteg.dataProvider = event.result.dados["comboTipoInteg"] as ArrayCollection;
			controlarExibicaoInicial();
			MostraCursor.removeBusyCursor(); 
		}
		
		private function retornoAlterar(event:ResultEvent):void {
			Alerta.show("Dados gravados com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO, null);
			MostraCursor.removeBusyCursor();
			fechar(null);
		}
		
		/**
		 * Encerra funcionalidade
		 */
		private function fechar(evt:MouseEvent):void {
			//(this.parentDocument as DebitoIndeterminadoRen).recarregarConsulta(evt);
			super.fecharJanela();
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
		
		private function alterar(me:MouseEvent):void {
			if(validar()) {
				this.vo = carregarVO();
				reqDTO = new RequisicaoReqDTO();
				reqDTO.dados.vo = this.vo;
				if(isAlterar){
					servico.alterar(reqDTO);
				}else{
					servico.incluir(reqDTO);
				}
			}
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
			if(this.vo == null){
				this.vo = new DebitoIndeterminadoRenVO;
			}
			this.vo.numContaCapital = new Number(numContaCapital.text);
			this.vo.tipoInteg = tipoInteg.selectedItem["codListaItem"];
			this.vo.numContaCorrente = new Number(cboCco.text);
			this.vo.formaDebito = formaDebito.selectedItem["codListaItem"];
			this.vo.qtdCotas = qtdCotas.valor;
			this.vo.percentual = percentual.valor;
			this.vo.vlrDebito = vlrDebito.valor;
			this.vo.periodoDebito = periodoDebito.selectedItem["codListaItem"];
			this.vo.dataInicialDeb = DateTimeBase.getDateTime(dataInicialDeb.selectedDate);
			this.vo.numPeriodo = qtdDias.valor;				
			this.vo.idInstituicao = pessoaSelecionada.idInstituicao;
			this.vo.idPessoa = pessoaSelecionada.idPessoa;
			this.vo.idContaCapital = idContaCapital;
			return vo;
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
				
				//Folha
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
		
		private function confirmaExcluir(evt:MouseEvent = null):void {
			Alerta.show("Deseja realmente excluir o débito por tempo indeterminado?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, excluir);
		}
		
		private function excluir(evt:MouseEvent = null):void {
			var arrIdsDeb:ArrayCollection = new ArrayCollection();
			var arrNumMatricula:ArrayCollection = new ArrayCollection();
			
			if(idDebitoContaCapital > 0){
				arrIdsDeb.addItem(idDebitoContaCapital);
				arrNumMatricula.addItem(new Number(numContaCapital.text));
				
				if(arrIdsDeb.length > 0) {
					var reqDTODel:RequisicaoReqDTO = new RequisicaoReqDTO();
					reqDTODel.dados.arrIdsDeb = arrIdsDeb;
					reqDTODel.dados.arrNumMatricula = arrNumMatricula;
					servico.excluir(reqDTODel);				
				}				
			}
		}
		
		private function retornoExcluir(event:ResultEvent):void {
			Alerta.show("Dados excluídos com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO, null);
			MostraCursor.removeBusyCursor();
			fechar(null);
		}				
		
		public function gravarRegistro():void{}
		
		public function carregarRegistro(registro:Object):void {}
		
		public function preencherCampos():void { }
		
		public function carregarDefinicoes(obj:Object = null):void { }
		
		public function novoRegistro():void	{ }
		
		public function atualizarCamposRegistro():void { }
		
		public function restaurarRegistro():void { }
		
		public function excluirRegistro(obj:Object):void { }
		
		public function verificarAlteracoes():Boolean {
			return false;
		}
}}
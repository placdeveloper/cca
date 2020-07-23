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
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.comum.pesquisa.SelecionarContaCapitalEvent;
	import br.com.sicoob.sisbr.cca.comum.vo.DebitoIndeterminadoRenVO;
	
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.DebitoIndeterminadoRenVO", DebitoIndeterminadoRenVO);
	public class CadastroDebitoIndeterminadoRen extends CadastroDebitoIndeterminadoRenView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.DebitoIndeterminadoServico";
		
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var vo:DebitoIndeterminadoRenVO;
		
		private var informacaoCCODiaFixo:String = "Os débitos subsequentes serão realizados no dia referente a data inicial do débito." + "\n" + "Ex: Data Inicial do Débito = 07/06/2016, débitos subsequentes = todo dia 07 ou seja 07/07/2016, 07/08/2016 e etc.";
		private var informacaoCCOIntervalo:String = "Os débitos subsequentes serão realizados a cada 'N' dia(s) após a data do primeiro débito." + "\n" + "Ex: Data Inicial do Débito = 07/06/2016. Quant. de Dias é 10, débitos subsequentes = 'N' dias após o dia 07 ou seja 17/06/2016, 27/06/2016 e 07/07/2016 e etc.";
		private var legendaFolhaBanco:String = "Para a forma de débito em questão (folha/banco), a data é meramente um indicador de quando foi realizado o cadastro do débito por tempo indeterminado " + "\n" + "para o associado. A data real do débito vai ser a data do retorno da remessa gerada pela empresa.";
		
		private var informacaoLoteFolha:String = "São listados apenas os associados pessoa física, sem débito por tempo indeterminado e que sejam funcionários ativos em " + "\n" + "empresas que rodem folha de pagamento.";
		private var informacaoLoteCCO:String = "São listados apenas os associados sem débito por tempo indeterminado e que contenham conta corrente ativa de primeira titularidade.";
		
		private var arrIdsCCA:ArrayCollection;
		private var arrIdsNumMatricula:ArrayCollection;
		private var arrContaCorrente:ArrayCollection;

		public var destinoPai:DestinoVO;
		
		public var listaCco:ArrayCollection = new ArrayCollection();
		
		public function CadastroDebitoIndeterminadoRen() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			configurarServico();
		}
		
		private function configurarServico():void {
			//PortalModel.portal.obterDefinicoesDestino("servicosJava", onDestinoRecuperado);
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
			
			obterDefinicoesIncluir();
		}
		
		private function controlarServico():void { 
			servico.obterDefinicoesIncluir.addEventListener(ResultEvent.RESULT, retornoObterDefinicoesIncluir);
			servico.calcularValorDebitoCota.addEventListener(ResultEvent.RESULT, retornoCalcularValorDebitoCota);
			servico.calcularValorDebitoPercentual.addEventListener(ResultEvent.RESULT, retornoCalcularValorDebitoPercentual);
			servico.carregarTipoInteg.addEventListener(ResultEvent.RESULT, retornoCarregarTipoInteg);
			servico.verificarDebitoCadastrado.addEventListener(ResultEvent.RESULT, retornoVerificarDebitoCadastrado);
			servico.incluir.addEventListener(ResultEvent.RESULT, retornoIncluir);
			servico.incluirEmLote.addEventListener(ResultEvent.RESULT, retornoIncluirEmLote);
			servico.consultarAssociadosSemDebitoInd.addEventListener(ResultEvent.RESULT, retornoConsultarAssociadosSemDebitoInd);
			servico.obterDefinicoesIncluirEmLote.addEventListener(ResultEvent.RESULT, retornoObterDefinicoesIncluirEmLote);
			servico.obterDefinicoesIncluirIndividual.addEventListener(ResultEvent.RESULT, retornoObterDefinicoesIncluirIndividual);
		}
		
		private function controlarEventos():void {
			btOk.addEventListener(MouseEvent.CLICK, incluir);
			btCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			
			tipoInteg.addEventListener(Event.CHANGE, onChangeTipoInteg);
			formaDebito.addEventListener(Event.CHANGE, onChangeFormaDebito);
			periodoDebito.addEventListener(Event.CHANGE, onChangePeriodoDebito);
			qtdCotas.addEventListener(FocusEvent.KEY_FOCUS_CHANGE, onFocusChangeCotas);
			qtdCotas.addEventListener(FocusEvent.MOUSE_FOCUS_CHANGE, onFocusChangeCotas);
			percentual.addEventListener(FocusEvent.KEY_FOCUS_CHANGE, onFocusChangePercentual);
			percentual.addEventListener(FocusEvent.MOUSE_FOCUS_CHANGE, onFocusChangePercentual);
			
//			optIndividual.addEventListener(MouseEvent.CLICK, onClickIndividual);
//			optLote.addEventListener(MouseEvent.CLICK, onClickLote);
			optGroupOperacao.addEventListener(Event.CHANGE, onChangeOptIndLote);
			
			btConsultarSemDeb.addEventListener(MouseEvent.CLICK, onClickConsultarSemDeb); 
			
			procurarCCA.addEventListener(SelecionarContaCapitalEvent.ITEM_SELECIONADO, retornoObterProcurarCCA);
			procurarCCA.addEventListener(SelecionarContaCapitalEvent.REGISTRO_NAO_ENCONTRADO, retornoObterProcurarCCANaoEcontrado);
		}
		
		private function obterDefinicoesIncluir():void {
			servico.obterDefinicoesIncluir(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoesIncluir(event:ResultEvent):void {
			tipoInteg.dataProvider = event.result.dados["comboTipoInteg"] as ArrayCollection;
			formaDebito.dataProvider = event.result.dados["comboFormaDebito"] as ArrayCollection;
			periodoDebito.dataProvider = event.result.dados["comboPeriodoDebito"] as ArrayCollection;
			procurarCCA.idInstituicao = event.result.dados["idInstituicao"];											
			
			controlarExibicaoInicial();
			
			MostraCursor.removeBusyCursor();
		}
		
		private function controlarExibicaoInicial():void {
			tipoInteg.selectedIndex = 0;
			tipoInteg.enabled = false;
			
			cboCco.selectedIndex = 0;
			cboCco.enabled = false;
			cboCco.visible = false;
			labelCco.enabled = false;
			labelCco.visible = false;
			
			boxFormaDebito.visible = false;
			boxFormaDebito.includeInLayout = false;
			formaDebito.selectedIndex = 0;
			
			boxQtdCotas.visible = false;
			boxQtdCotas.includeInLayout = false;
			
			boxPercentual.visible = false;
			boxPercentual.includeInLayout = false;
			
			boxVlrDebito.visible = false;
			boxVlrDebito.includeInLayout = false;
			
			lblPeriodoDebito.visible = true;
			lblPeriodoDebito.includeInLayout = true;
			periodoDebito.visible = true;
			periodoDebito.selectedIndex = 1;
			periodoDebito.enabled = false;
			periodoDebito.includeInLayout = true;
			
			qtdDias.includeInLayout = false;
			qtdDias.visible = false;
			lblQtdDias.includeInLayout = false;
			lblQtdDias.visible = false;
			
			lblGridSemDebito.includeInLayout = false;
			lblGridSemDebito.visible = false;
			gridSemDebitoCCO.includeInLayout = false;
			gridSemDebitoCCO.visible = false;
			gridSemDebitoFolhaBanco.includeInLayout = false;
			gridSemDebitoFolhaBanco.visible = false;
			
			btConsultarSemDeb.visible = false;
			btConsultarSemDeb.includeInLayout = false;
			hboxInformacaoLote.visible = false;
			hboxInformacaoLote.includeInLayout = false;
			
			hboxInformacaoFolhaBanco.visible = false;
			hboxInformacaoFolhaBanco.includeInLayout = false;
			
			informacao.text = informacaoCCODiaFixo;
		}
		
		private function controlarExibicaoPorTipoIntegralizacao():void {
			tipoInteg.enabled = true;
			
			labelCco.visible = false;
			labelCco.enabled = false;
			labelCco.includeInLayout = false;
			cboCco.enabled = false;
			cboCco.visible = false;
			cboCco.includeInLayout = false;
			
			boxFormaDebito.visible = false;
			boxFormaDebito.includeInLayout = false;
			formaDebito.selectedIndex = 0;
			
			boxQtdCotas.visible = false;
			boxQtdCotas.includeInLayout = false;
			qtdCotas.valor = 0;
			
			boxPercentual.visible = false;
			boxPercentual.includeInLayout = false;
			percentual.valor = 0;
			
			vlrDebito.enabled = true;
			boxVlrDebito.visible = true;
			boxVlrDebito.includeInLayout = true;
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
			
			gridSemDebitoCCO.dataProvider = null;
			gridSemDebitoCCO.visible = false;
			gridSemDebitoCCO.includeInLayout = false;
			gridSemDebitoFolhaBanco.dataProvider = null;
			gridSemDebitoFolhaBanco.visible = false;
			gridSemDebitoFolhaBanco.includeInLayout = false;
			
			informacao.text = informacaoCCODiaFixo;			
			
			if(tipoInteg.selectedItem != null){

				//CCO
				if(tipoInteg.selectedItem["codListaItem"] == 2) {
					
					if(getTipoInclusao() == 1) {
						labelCco.enabled = procurarCCA.isContaCapitalSelecionada();
						labelCco.visible = true;
						labelCco.includeInLayout = true;
						cboCco.enabled = procurarCCA.isContaCapitalSelecionada();
						cboCco.visible = true;
						cboCco.includeInLayout = true;
					}
					
					lblPeriodoDebito.visible = true;
					lblPeriodoDebito.includeInLayout = true;
					periodoDebito.visible = true;
					periodoDebito.enabled = true;
					periodoDebito.includeInLayout = true;
					
					lblDataInicialDeb.x = 305;
					lblDataInicialDeb.y = 0;
					dataInicialDeb.x = 425;
					dataInicialDeb.y = 0;
					
					informacaoLote.text = informacaoLoteCCO;
				}
				
				//Folha
				if(tipoInteg.selectedItem["codListaItem"] == 3){ //|| tipoInteg.selectedItem["codListaItem"] == 6) {   Foi necessário retirar essa validação devido a regra de nogocio esta sendo utilizada de maneira indevida
					boxFormaDebito.visible = true;
					boxFormaDebito.includeInLayout = true;
					
					informacaoLote.text = informacaoLoteFolha;
					informacaoFolhaBanco.text = legendaFolhaBanco;
				}
				
				var isCCODiaFixo:Boolean;
				if 	(tipoInteg.selectedItem != null && tipoInteg.selectedItem["codListaItem"] == 2 && 
					periodoDebito.selectedItem != null && periodoDebito.selectedItem["codListaItem"] == 1)	 			
				{
					isCCODiaFixo = true;
				} else {
					isCCODiaFixo = false;	
				}
				hboxInformacaoFolhaBanco.visible = !isCCODiaFixo;
				hboxInformacaoFolhaBanco.includeInLayout = !isCCODiaFixo;
				
				hboxInformacao.visible = isCCODiaFixo;
				hboxInformacao.includeInLayout = isCCODiaFixo;
			}
			
		}
		
		private function retornoObterProcurarCCA(event:SelecionarContaCapitalEvent):void {
			MostraCursor.removeBusyCursor();
			reqDTO.dados.idInstituicao = procurarCCA.resultadoPesquisaVO.idInstituicao;			
			reqDTO.dados.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;			
			servico.verificarDebitoCadastrado(reqDTO);
		}
		
		private function retornoVerificarDebitoCadastrado(event:ResultEvent):void {
			MostraCursor.removeBusyCursor(); 			
			
			if (event.result.dados["isDebitoCadastrado"] == true){
				Alerta.show("Já consta débito indeterminado cadastrado para o associado.", "ATENÇÃO", Alerta.ALERTA_OK);				
				procurarCCA.limparRegistro();
				procurarCCA.limparCampos();
				MostraCursor.removeBusyCursor();
			}else{
				controlarExibicaoInicial();
				carregarTipoInteg();
				controlarExibicaoPorTipoIntegralizacao();
				dataInicialDeb.selectedDate = new Date();				
			}			
		}	
		
		private function retornoObterProcurarCCANaoEcontrado(event:SelecionarContaCapitalEvent):void {
			controlarExibicaoInicial();
//			controlarExibicaoPorTipoIntegralizacao();
			listaCco.removeAll();
			
			cboCco.selectedIndex = 0;
			cboCco.enabled = false;
			cboCco.visible = false;
			labelCco.enabled = false;
			labelCco.visible = false;
			
			MostraCursor.removeBusyCursor();
		}
		
		private function carregarTipoInteg():void {
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			reqDTO.dados.codTipoPessoa = procurarCCA.resultadoPesquisaVO.codTipoPessoa;
			reqDTO.dados.idInstituicao = procurarCCA.resultadoPesquisaVO.idInstituicao;
			reqDTO.dados.idPessoa = procurarCCA.resultadoPesquisaVO.idPessoa;
			servico.carregarTipoInteg(reqDTO);
		}
		
		private function retornoCarregarTipoInteg(event:ResultEvent):void {
			if (tipoInteg.dataProvider != null) {
				tipoInteg.dataProvider.removeAll();
				tipoInteg.dataProvider.addAll(event.result.dados["comboTipoInteg"] as ArrayCollection);
			} else {
				tipoInteg.dataProvider = event.result.dados["comboTipoInteg"] as ArrayCollection;
			}
			
			listaCco.removeAll();
			listaCco.addAll(event.result.dados["comboCco"] as ArrayCollection);
			cboCco.dataProvider = listaCco.list;
			cboCco.selectedIndex = 0;
			
			controlarExibicaoPorTipoIntegralizacao();
			MostraCursor.removeBusyCursor(); 
		}
		
		private function onChangeTipoInteg(evt:Event):void {
			controlarExibicaoPorTipoIntegralizacao();
			definirLayoutPorTipoInclusao();
		}
		
		private function onChangeFormaDebito(evt:Event):void {
			boxQtdCotas.visible = false;
			boxQtdCotas.includeInLayout = false;
			qtdCotas.valor = 0;
			
			boxPercentual.visible = false;
			boxPercentual.includeInLayout = false;
			percentual.valor = 0;
			
			boxVlrDebito.visible = false;
			boxVlrDebito.includeInLayout = false;
			vlrDebito.valor = 0;
			
			if(formaDebito.selectedItem != null) {
				if(formaDebito.selectedItem["codListaItem"] == 0) {
					vlrDebito.enabled = true;
					boxVlrDebito.visible = true;
					boxVlrDebito.includeInLayout = true;
				}
				
				if(formaDebito.selectedItem["codListaItem"] == 1 || formaDebito.selectedItem["codListaItem"] == 2) {
					boxPercentual.visible = true;
					boxPercentual.includeInLayout = true;
					
					if(getTipoInclusao() == 1) {
						vlrDebito.enabled = false;
						boxVlrDebito.visible = true;
						boxVlrDebito.includeInLayout = true;
					}
				}
				
				if(formaDebito.selectedItem["codListaItem"] == 3) {
					boxQtdCotas.visible = true;
					boxQtdCotas.includeInLayout = true;
					
					vlrDebito.enabled = false;
					boxVlrDebito.visible = true;
					boxVlrDebito.includeInLayout = true;
				}
				
				//Salario base da cooperativa
				if(getTipoInclusao() == 2 && tipoInteg.selectedItem["codListaItem"] == 3 && formaDebito.selectedItem["codListaItem"] == 2) {
					vlrDebito.enabled = false;
					boxVlrDebito.visible = true;
					boxVlrDebito.includeInLayout = true;
				}
			}
		}
		
		private function onChangePeriodoDebito(evt:Event):void {
			qtdDias.valor = 0;
			
			if (periodoDebito.selectedItem != null) {
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
				} else if(periodoDebito.selectedItem["codListaItem"] == 1) {
					informacao.text = informacaoCCODiaFixo;
					
					lblDataInicialDeb.x = 305;
					lblDataInicialDeb.y = 0;
					dataInicialDeb.x = 425;
					dataInicialDeb.y = 0;
					
					qtdDias.includeInLayout = false;
					qtdDias.visible = false;
					lblQtdDias.includeInLayout = false;
					lblQtdDias.visible = false;
				}
			}
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
			
			var isPercentualSalarioBase:Boolean = tipoInteg.selectedItem != null && formaDebito.selectedItem != null && 
				tipoInteg.selectedItem["codListaItem"] == 3 && formaDebito.selectedItem["codListaItem"] == 2;
			
			if(percentual.valor > 0) {
				
				if((getTipoInclusao() == 1 && procurarCCA.resultadoPesquisaVO.idPessoa > 0 && procurarCCA.resultadoPesquisaVO.idInstituicao > 0)
					|| (getTipoInclusao() == 2 && isPercentualSalarioBase)) {
					
					reqDTO = new RequisicaoReqDTO();
					reqDTO.dados.percentual = percentual.valor;
					reqDTO.dados.idPessoa = procurarCCA.resultadoPesquisaVO.idPessoa;
					reqDTO.dados.idInstituicao = procurarCCA.resultadoPesquisaVO.idInstituicao;
					reqDTO.dados.percentualSalarioBase = isPercentualSalarioBase;
					setTimeout(function():void {
						servico.calcularValorDebitoPercentual(reqDTO);
					},100);
				} else {
					vlrDebito.valor = 0;					
				}
			}
		}
		
		private function retornoCalcularValorDebitoPercentual(event:ResultEvent):void {
			vlrDebito.valor = new Number(event.result.dados["valorDebito"]);
			MostraCursor.removeBusyCursor();
		}
		
		private function onChangeOptIndLote(event:Event):void {
			if (optIndividual.selected) {
				onClickIndividual(null);
			} else {
				onClickLote(null);
			}
		}
		
		private function onClickIndividual(me:MouseEvent):void {
			reqDTO = new RequisicaoReqDTO();
			servico.obterDefinicoesIncluirIndividual(reqDTO);
		}
		
		private function retornoObterDefinicoesIncluirIndividual(event:ResultEvent):void {
			procurarCCA.limparRegistro();
			procurarCCA.limparCampos();
			
			dataInicialDeb.selectedDate = new Date();
			
			definirLayoutPorTipoInclusao();
			
			controlarExibicaoPorTipoIntegralizacao();
			controlarExibicaoInicial();
			
			MostraCursor.removeBusyCursor();
		}
		
		private function onClickLote(me:MouseEvent):void {
			reqDTO = new RequisicaoReqDTO();
			servico.obterDefinicoesIncluirEmLote(reqDTO);
		}
		
		private function retornoObterDefinicoesIncluirEmLote(event:ResultEvent):void {
			dataInicialDeb.selectedDate = new Date();
			
			tipoInteg.dataProvider = event.result.dados["comboTipoInteg"] as ArrayCollection;
			
			definirLayoutPorTipoInclusao();
			
			controlarExibicaoPorTipoIntegralizacao();
			
			MostraCursor.removeBusyCursor();
		}
		
		private function definirLayoutPorTipoInclusao():void {
			height = 440;
			(this.parentDocument as DebitoIndeterminadoRen).centralizarTelaCadastro();
			
			hboxInformacao.visible = false;
			hboxInformacao.includeInLayout = false;
			
			if(getTipoInclusao() == 1) {
				
				lblDadosAssociados.visible = true;
				lblDadosAssociados.includeInLayout = true;
				dadosAssociados.visible = true;
				dadosAssociados.includeInLayout = true;
				
				btConsultarSemDeb.includeInLayout = false;
				btConsultarSemDeb.visible = false;
				
				lblGridSemDebito.visible = false;
				lblGridSemDebito.includeInLayout = false;
				gridSemDebitoCCO.visible = false;
				gridSemDebitoCCO.includeInLayout = false;
				gridSemDebitoCCO.dataProvider = null;
				gridSemDebitoFolhaBanco.visible = false;
				gridSemDebitoFolhaBanco.includeInLayout = false;
				gridSemDebitoFolhaBanco.dataProvider = null;
				
				if(tipoInteg.selectedItem != null && tipoInteg.selectedItem["codListaItem"] == 2) {
					hboxInformacao.visible = true;
					hboxInformacao.includeInLayout = true;
				}
				
				hboxInformacaoLote.visible = false;
				hboxInformacaoLote.includeInLayout = false;
				
			} else {
				
				lblDadosAssociados.visible = false;
				lblDadosAssociados.includeInLayout = false;
				dadosAssociados.visible = false;
				dadosAssociados.includeInLayout = false;
				
				lblGridSemDebito.visible = true;
				lblGridSemDebito.includeInLayout = true;
				
				btConsultarSemDeb.visible = true;
				btConsultarSemDeb.includeInLayout = true;
				
				hboxInformacaoLote.visible = true;
				hboxInformacaoLote.includeInLayout = true;
				
				if(tipoInteg.selectedItem != null && tipoInteg.selectedItem["codListaItem"] == 2) {
					informacaoLote.text = informacaoLoteCCO;
					hboxInformacao.visible = true;
					hboxInformacao.includeInLayout = true;
				} else {
					informacaoLote.text = informacaoLoteFolha;
				}
				
			}
		}
		
		private function onClickConsultarSemDeb(me:MouseEvent):void {
			this.vo = carregarVO();
			reqDTO = new RequisicaoReqDTO();
			reqDTO.dados.vo = this.vo;
			servico.consultarAssociadosSemDebitoInd(reqDTO);
		}
		
		private function retornoConsultarAssociadosSemDebitoInd(event:ResultEvent):void {
			
			definirLayoutPorTipoInclusao();
			height = 650;
			(this.parentDocument as DebitoIndeterminadoRen).centralizarTelaCadastro();
			
			lblGridSemDebito.includeInLayout = true;
			lblGridSemDebito.visible = true;
			
			if(tipoInteg.selectedItem != null && tipoInteg.selectedItem["codListaItem"] == 2) {
				gridSemDebitoCCO.includeInLayout = true;
				gridSemDebitoCCO.visible = true;
				gridSemDebitoCCO.dataProvider = event.result.dados["lstVO"] as ArrayCollection;
				gridSemDebitoCCO.labelFunction = formataDataGridSemDebito;
				gridSemDebitoFolhaBanco.includeInLayout = false;
				gridSemDebitoFolhaBanco.visible = false;
				
				lblPeriodoDebito.visible = true;
				lblPeriodoDebito.includeInLayout = true;
				periodoDebito.visible = true;
				periodoDebito.includeInLayout = true;
				periodoDebito.enabled = true;
				
			} else {
				lblPeriodoDebito.visible = false;
				lblPeriodoDebito.includeInLayout = false;
				periodoDebito.visible = false;
				periodoDebito.includeInLayout = false;
				periodoDebito.enabled = false;
				
				gridSemDebitoFolhaBanco.includeInLayout = true;
				gridSemDebitoFolhaBanco.visible = true;
				gridSemDebitoFolhaBanco.dataProvider = event.result.dados["lstVO"] as ArrayCollection;
				gridSemDebitoFolhaBanco.labelFunction = formataDataGridSemDebito;
				gridSemDebitoCCO.includeInLayout = false;
				gridSemDebitoCCO.visible = false;
			}
			
			labelCco.enabled = false;
			labelCco.visible = false;
			labelCco.includeInLayout = false;
			//controleCco.enabled = false;
			//controleCco.visible = false;
			//controleCco.includeInLayout = false;
			
			if(formaDebito.selectedItem != null && formaDebito.selectedItem["codListaItem"] == 3) {
				boxVlrDebito.visible = true;
				boxVlrDebito.includeInLayout = true;
			}
			
			tipoInteg.dataProvider = event.result.dados["comboTipoInteg"] as ArrayCollection;
			tipoInteg.enabled = true;
			tipoInteg.procuraItemPorNome(this.vo.tipoInteg, "codListaItem");
			
			MostraCursor.removeBusyCursor();
		}
		
		private function formataDataGridSemDebito(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			
			switch(col.dataField) {
				case "cpfCnpj": {
					retorno = FormatUtil.formataCPFCNPJ(obj[col.dataField]);
					break;
				}
				case "numContaCorrente": {
					var strNumCCO:String = obj[col.dataField].toString();
					retorno =  strNumCCO.slice(0, strNumCCO.length -1) + "-" + strNumCCO.charAt(strNumCCO.length - 1); 
					break;
				}
				default: {
					if(obj == null || obj[col.dataField] == null) {
						retorno = "";
					} else {
						retorno = obj[col.dataField].toString();
					}
					break;
				}
			}                
			
			return retorno;
		}
		
		private function incluir(me:MouseEvent):void {
			if(validar()) {
				
				if(getTipoInclusao() == 1) {
					incluirIndividual();
				}
				
				if(getTipoInclusao() == 2) {
					incluirEmLote();
				}
			}
		}
		
		private function incluirIndividual():void {
			this.vo = carregarVO();
			reqDTO = new RequisicaoReqDTO();
			reqDTO.dados.vo = this.vo;
			servico.incluir(reqDTO);
		}
		
		private function incluirEmLote():void {
			this.vo = carregarVO();
			reqDTO = new RequisicaoReqDTO();
			reqDTO.dados.vo = this.vo;
			servico.incluirEmLote(reqDTO);
		}
		
		private function retornoIncluir(event:ResultEvent):void {
			Alerta.show("Dados gravados com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO, null);
			MostraCursor.removeBusyCursor(); 
			super.fecharJanela();
		}
		
		private function retornoIncluirEmLote(event:ResultEvent):void {
			Alerta.show("Dados gravados com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO, null, onClickConsultarSemDeb);
		}
		
		private function validar():Boolean {
			if(!procurarCCA.isContaCapitalSelecionada() && getTipoInclusao() == 1) {
				Alerta.show("O campo Conta Capital é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, procurarCCA.txtNumCCA);
				return false;
			}
			
			if (tipoInteg.selectedItem == null) {
				Alerta.show("O campo Forma de Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, tipoInteg);
				return false;
			}
			
			if (tipoInteg.text == "") {
				Alerta.show("O campo Forma de Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, tipoInteg);
				return false;
			}				
			
			if (boxQtdCotas.visible && qtdCotas.text == "") {
				Alerta.show("O campo Quant. de Cotas é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, qtdCotas);
				return false;
			}
			
			if (boxPercentual.visible && percentual.text == "") {
				Alerta.show("O campo Percentual (%) é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, percentual);
				return false;
			}
			
			if (boxVlrDebito.visible && vlrDebito.text == "") {
				Alerta.show("O campo Valor para Débito (R$) é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, vlrDebito);
				return false;
			}
			
			if (boxVlrDebito.visible && vlrDebito.valor <= 0) {
				Alerta.show("O Valor para Débito deve ser maior que zero (R$ 0,00).", "ATENÇÃO", Alerta.ALERTA_OK, vlrDebito);
				return false;
			}
			
			if (boxVlrDebito.visible && vlrDebito.valor > 999999999.99) {
				Alerta.show("Valor para Débito inválido.", "ATENÇÃO", Alerta.ALERTA_OK, vlrDebito);
				return false;
			}
			
			if (qtdDias.visible && qtdDias.text == "") {
				Alerta.show("O campo Quant. de Dias é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, qtdDias);
				return false;
			}
			
			//CCO
			if(tipoInteg.selectedItem["codListaItem"] == 2) {
				if (getTipoInclusao() == 1 && cboCco.selectedItem == null) {
					Alerta.show("O campo Nº Conta Corrente é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, periodoDebito);
					return false;
				}
				
				if (periodoDebito.selectedItem == null) {
					Alerta.show("O campo Período para Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, periodoDebito);
					return false;
				}
				
				//Quantidade de dias
				if (periodoDebito.selectedItem != null) {
					if(periodoDebito.selectedItem["codListaItem"] == 0 && (qtdDias.valor <= 0 || qtdDias.valor > 365)) {
						Alerta.show("A quantidade de dias para o débito deve ser maior que 0 e menor ou igual a 365.", "ATENÇÃO", Alerta.ALERTA_OK, qtdDias);
						return false;
					}
				}
			}
			
			//Banco/Folha
			if(tipoInteg.selectedItem["codListaItem"] == 3){// || tipoInteg.selectedItem["codListaItem"] == 6) {    Foi necessário retirar essa validação devido a regra de nogocio esta sendo utilizada de maneira indevida 
				
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
				Alerta.show("O campo Data Inicial do Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, dataInicialDeb);
				return false;
			}
			var hoje:Date = new Date();
			hoje.setHours(0,0,0,0);
			if (dataInicialDeb.selectedDate.getTime() < hoje.getTime()) {
				Alerta.show("A Data Inicial do Débito deve ser igual ou superior a data atual.", "ATENÇÃO", Alerta.ALERTA_OK, dataInicialDeb);
				return false;
			}
			
			if(getTipoInclusao() == 2) {
				var registroSelecionado:Boolean = false;
				if(tipoInteg.selectedItem["codListaItem"] == 2) {
					for(var i:int = 0; i < gridSemDebitoCCO.dataProvider.length; i++) {
						if(gridSemDebitoCCO.dataProvider[i].selecionado) {
							registroSelecionado = true;
							break;
						}
					}
				}
				if(tipoInteg.selectedItem["codListaItem"] == 3){// || tipoInteg.selectedItem["codListaItem"] == 6) { Foi necessário retirar essa validação devido a regra de nogocio esta sendo utilizada de maneira indevida 
					for(var x:int = 0; x < gridSemDebitoFolhaBanco.dataProvider.length; x++) {
						if(gridSemDebitoFolhaBanco.dataProvider[x].selecionado) {
							registroSelecionado = true;
							break;
						}
					}
				}
				if(!registroSelecionado) {
					Alerta.show("Selecione pelo menos um associado para realizar o cadastro de débito por tempo indeterminado.", "ATENÇÃO", Alerta.ALERTA_OK);
					return false;
				}
			}
			
			return true;
		}
		
		private function carregarVO():DebitoIndeterminadoRenVO {
			this.vo = new DebitoIndeterminadoRenVO();
			this.vo.tipoInclusao = getTipoInclusao();
			this.vo.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;
			this.vo.idPessoa = procurarCCA.resultadoPesquisaVO.idPessoa;
			this.vo.idInstituicao = procurarCCA.resultadoPesquisaVO.idInstituicao;
			this.vo.tipoInteg = tipoInteg.selectedItem["codListaItem"];
			this.vo.numContaCorrente = new Number(cboCco.text);
			this.vo.formaDebito = formaDebito.selectedItem["codListaItem"];
			this.vo.qtdCotas = qtdCotas.valor;
			this.vo.percentual = percentual.valor;
			this.vo.vlrDebito = vlrDebito.valor;
			this.vo.periodoDebito = periodoDebito.selectedItem["codListaItem"];
			this.vo.dataInicialDeb = DateTimeBase.getDateTime(dataInicialDeb.selectedDate);
			this.vo.numPeriodo = qtdDias.valor;
			
			var arrIdsCCA:ArrayCollection = new ArrayCollection();
			var arrIdsNumMatricula:ArrayCollection = new ArrayCollection();
			var arrContaCorrente:ArrayCollection = new ArrayCollection();
			//CCO
			if(tipoInteg.selectedItem["codListaItem"] == 2) {
				for(var i:int = 0; i < gridSemDebitoCCO.dataProvider.length; i++) {
					if(gridSemDebitoCCO.dataProvider[i].selecionado) {
						arrIdsCCA.addItem(gridSemDebitoCCO.dataProvider[i].idContaCapital);
						arrIdsNumMatricula.addItem(gridSemDebitoCCO.dataProvider[i].numContaCapital);
						arrContaCorrente.addItem(gridSemDebitoCCO.dataProvider[i].numContaCorrente);
					}
				}
			}
			//Banco/Folha
			if(tipoInteg.selectedItem["codListaItem"] == 3){ // || tipoInteg.selectedItem["codListaItem"] == 6) {  Foi necessário retirar essa validação devido a regra de nogocio esta sendo utilizada de maneira indevida 
				for(var i:int = 0; i < gridSemDebitoFolhaBanco.dataProvider.length; i++) {
					if(gridSemDebitoFolhaBanco.dataProvider[i].selecionado) {
						arrIdsCCA.addItem(gridSemDebitoFolhaBanco.dataProvider[i].idContaCapital);
						arrIdsNumMatricula.addItem(gridSemDebitoFolhaBanco.dataProvider[i].numContaCapital);
					}
				}
			}
			
			this.vo.idsContaCapital = arrIdsCCA;
			this.vo.idsNumMatricula = arrIdsNumMatricula;
			this.vo.contasCorrente = arrContaCorrente;
			
			return vo;
		}
		
		private function getTipoInclusao():int {
			if(optIndividual.selected) {
				return parseInt(optIndividual.value.toString());
			}
			if(optLote.selected) {
				return parseInt(optLote.value.toString());
			}
			return 0;
		}
		
		/**
		 * Encerra funcionalidade
		 */
		private function fechar(evt:MouseEvent):void {
			(this.parentDocument as DebitoIndeterminadoRen).recarregarConsulta(evt);
			super.fecharJanela();
		}
		
		private function cancelar(evt:MouseEvent):void {
			onChangeOptIndLote(null);			
		}
		
	}
}
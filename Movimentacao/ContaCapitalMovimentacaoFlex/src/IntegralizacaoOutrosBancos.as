package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.DataGridEvent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.movimentacao.integralizacaooutrosbancos.IntegralizacaoOutrosBancosView;
	import br.com.sicoob.sisbr.cca.vo.IntegralizacaoOutrosBancosVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.IntegralizacaoOutrosBancosVO", IntegralizacaoOutrosBancosVO);
	public class IntegralizacaoOutrosBancos extends IntegralizacaoOutrosBancosView {
					
		private var servico:ServicoJava = new ServicoJava();
		private var valorIntegralizacaoTemp:String;
		public var destinoPai:DestinoVO;
		private var anoMesPreparada:String;
		private var matriculaPreparada:String;
		private var anoMesRetorno:String;
		private var matriculaRetorno:String;
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.IntegralizacaoOutrosBancosServico";
		private const SERVICO_REL_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelIntegralizacaoOutrosBancosServico";
		
		public function IntegralizacaoOutrosBancos() {			
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);		
		}
		
		private function init(event:FlexEvent):void {
			destinoPai = this.destino;
			servico.configurarDestino(destinoPai);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			controlarServico();
			controlarEventos();
			obterDefinicoes();
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			
			// ABA CADASTRO DE FAVORECIDOS
			servico.consultarFav.addEventListener(ResultEvent.RESULT, retornoConsultarFav);
			servico.atualizarContas.addEventListener(ResultEvent.RESULT, retornoAtualizarContas);
			servico.definirPrincipal.addEventListener(ResultEvent.RESULT, retornoDefinirPrincipal);
			
			// ABA PREPARAR REMESSA
			servico.consultar.addEventListener(ResultEvent.RESULT, retornoConsultar);
			servico.enviarRemessa.addEventListener(ResultEvent.RESULT, retornoEnviarRemessa);
			// ABA REMESSAS ENVIADAS
			servico.consultarRemessa.addEventListener(ResultEvent.RESULT, retornoConsultarRem);
			servico.consultarRemessaEnvDetalhe.addEventListener(ResultEvent.RESULT, retornoConsultarRemessaEnvDetalhe);			
			// ABA REMESSAS RETORNO
			servico.consultarRemessaRetorno.addEventListener(ResultEvent.RESULT, retornoConsultarRemessaRetorno);
			servico.consultarRemessaRetornoDetalhe.addEventListener(ResultEvent.RESULT, retornoConsultarRemessaRetornoDetalhe);			
			servico.integralizar.addEventListener(ResultEvent.RESULT, retornoIntegralizar);
		
		}
		
		private function controlarEventos():void {
			// ABA CADASTRO DE FAVORECIDOS
			btConsultar_fav.addEventListener(MouseEvent.CLICK, consultarFav);
			btAtualizarContas.addEventListener(MouseEvent.CLICK, atualizarContas);
			btDefinirPrincipal.addEventListener(MouseEvent.CLICK, definirPrincipal);
			btFecharFav.addEventListener(MouseEvent.CLICK, fechar);
			
			// ABA PREPARAR REMESSA
			btConsultar.addEventListener(MouseEvent.CLICK, consultar);
			btEnviarRemessa.addEventListener(MouseEvent.CLICK, enviarRemessa);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btDefinirValor.addEventListener(MouseEvent.CLICK, definirValor);
			txtValorIntegralizar.addEventListener(FlexEvent.ENTER, definirValor);
			
			gridDados.addEventListener(DataGridEvent.ITEM_EDIT_BEGIN, gridDadosInicioEdicao);
			gridDados.addEventListener(DataGridEvent.ITEM_FOCUS_OUT, gridDadosFimEdicao); 
			
			// ABA REMESSA ENVIADA
			gridRemEnv.addEventListener(MouseEvent.CLICK, listarRemEnvDetalhe);			
			btConsultar_rem.addEventListener(MouseEvent.CLICK, consultarRem);
			btImprimir.addEventListener(MouseEvent.CLICK, emitirRelatorio);
			btFecharRemEnv.addEventListener(MouseEvent.CLICK, fechar);
			
			// ABA INTEGRELIZAR
			
			gridRemRet.addEventListener(MouseEvent.CLICK, listarRemRetDetalhe);						
			btConsultar_rem_ret.addEventListener(MouseEvent.CLICK, consultarRemRetBusca);
			btIntegralizar.addEventListener(MouseEvent.CLICK, integralizar);
			btFecharRemRet.addEventListener(MouseEvent.CLICK, fechar);
		}

		private function obterDefinicoes():void {
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoes(evt:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			var listaBancos:ArrayCollection = evt.result.dados["listaBancos"] as ArrayCollection;
			listaBancos.addItemAt({codListaItem: 0, descListaItem: "TODOS"}, 0);
			cboBancos.dataProvider = listaBancos;
			cboBancos_fav.dataProvider = listaBancos;
			cboBancos_rem.dataProvider = listaBancos;
			cboBancos_rem_ret.dataProvider = listaBancos;			
			
			var listaTipoFiltro:ArrayCollection = new ArrayCollection();
			listaTipoFiltro.addItem({codListaItem: 0, descListaItem: "TODOS"});
			listaTipoFiltro.addItem({codListaItem: 1, descListaItem: "FAVORECIDO COM MAIS DE UMA CONTA"});
			listaTipoFiltro.addItem({codListaItem: 2, descListaItem: "FAVORECIDO COM UMA CONTA"});
			cboTipoFiltro.dataProvider = listaTipoFiltro;
		}
		
		/** ---------- ABA CADASTRO DE FAVORECIDOS ---------- */
		private function consultarFav(event:Event=null):void {
			if (validarConsultaFav()) {
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				montarDtoConsultaFav(dto);
				servico.consultarFav(dto);
			}
		}
		
		private function fechar(evt:MouseEvent):void {
			super.fecharJanela();
		}
		
		private function montarDtoConsultaFav(dto:RequisicaoReqDTO):void {
			dto.dados.tipoSituacao = cboTipoFiltro.selectedItem.codListaItem;
			dto.dados.apenasContaPrincipal = chkPrincipal.selected;
			dto.dados.numBanco = cboBancos_fav.selectedItem.codListaItem;
			dto.dados.numMatricula = procurarCCA_fav.resultadoPesquisaVO.numContaCapital;
		}
		
		private function validarConsultaFav():Boolean {
			if (cboTipoFiltro.selectedItem == null) {
				Alerta.show("O campo Tipo de Filtros é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoFiltro);
				return false;
			}
			if (cboBancos_fav.selectedItem == null) {
				Alerta.show("O campo Banco é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboBancos_fav);
				return false;
			}
			return true;
		}
		
		private function retornoConsultarFav(event:ResultEvent):void {
			var favorecidos:ArrayCollection = event.result.dados["favorecidos"] as ArrayCollection;
			atualizarGridContasFav(favorecidos);
			MostraCursor.removeBusyCursor();
		}
		
		private function atualizarGridContasFav(favorecidos:ArrayCollection):void {
			gridContasFav.dataProvider = favorecidos;
			gridContasFav.labelFunction = formataDataGrid;
			gridContasFav.dataProvider.refresh();
		}
		
		private function atualizarContas(event:MouseEvent):void {
			servico.atualizarContas(new RequisicaoReqDTO());
		}
		
		private function retornoAtualizarContas(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			Alerta.show("Contas de favorecidos atualizadas.", "SUCESSO", Alerta.ALERTA_SUCESSO, null, null);
		}
		
		private function definirPrincipal(event:MouseEvent):void {
			if (validarDefinirPrincipal()) {
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				var favorecidos:ArrayCollection = new ArrayCollection();
				for (var i:int=0; i < gridContasFav.dataProvider.length; i++) {
					if (gridContasFav.dataProvider[i].selecionado) {
						favorecidos.addItem(gridContasFav.dataProvider[i]);
					}
				}
				dto.dados.favorecidos = favorecidos;
				montarDtoConsultaFav(dto);
				servico.definirPrincipal(dto);
			}
		}
		
		private function validarDefinirPrincipal():Boolean {
			if(gridContasFav.dataProvider == null){
				Alerta.show("É necessário selecionar pelo menos um favorecido.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			var selecionouAlgum:Boolean = false;
			var setClientes:Object = {};
			for (var i:int=0; i < gridContasFav.dataProvider.length; i++) {
				if (gridContasFav.dataProvider[i].selecionado) {
					if (setClientes[gridContasFav.dataProvider[i].numCliente] != null) {
						Alerta.show("Cliente "+gridContasFav.dataProvider[i].descNomePessoa+" ("
							+gridContasFav.dataProvider[i].numMatricula+") está associado a mais de uma conta.", 
							"ATENÇÃO", Alerta.ALERTA_OK);
						return false;
					}
					setClientes[gridContasFav.dataProvider[i].numCliente] = 1;
					selecionouAlgum = true;
				}
			}
			if (!selecionouAlgum) {
				Alerta.show("É necessário selecionar pelo menos um favorecido.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			return true;
		}
		
		private function retornoDefinirPrincipal(event:ResultEvent):void {
			var favorecidos:ArrayCollection = event.result.dados["favorecidos"] as ArrayCollection;
			atualizarGridContasFav(favorecidos);
			MostraCursor.removeBusyCursor();
			Alerta.show("Contas de favorecidos definidas com sucesso", "SUCESSO", Alerta.ALERTA_SUCESSO, null, null);
		}
		
		/** /---------- ABA CADASTRO DE FAVORECIDOS ---------- */

		/** ---------- ABA PREPARACAO DE REMESSA ---------- */
		private function consultar(event:Event=null):void {
			if (validarConsulta()) {
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.numBanco = cboBancos.selectedItem.codListaItem;
				dto.dados.numMatricula = procurarCCA.resultadoPesquisaVO.numContaCapital;
				servico.consultar(dto);
			}
		}
		
		private function validarConsulta():Boolean {
			if (cboBancos.selectedItem == null) {
				Alerta.show("O campo Banco é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboBancos);
				return false;
			}
			return true;
		}
		
		private function retornoConsultar(event:ResultEvent):void {
			var favorecidos:ArrayCollection = event.result.dados["favorecidos"] as ArrayCollection;
			gridDados.dataProvider = favorecidos;
			gridDados.labelFunction = formataDataGrid;
			gridDados.dataProvider.refresh();
			btEnviarRemessa.enabled = (favorecidos.length > 0);
			MostraCursor.removeBusyCursor();
		}
		
		private function formataDataGrid(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			if (col.dataField != null) {
				switch(col.dataField) {
					case "numCpfCnpj":
						if (obj[col.dataField]) {
							retorno = FormatUtil.formataCPFCNPJ(obj[col.dataField]);
						} else {
							retorno = "";						
						}
						break;
					case "valorIntegralizacaoStr":
						if (obj[col.dataField] != null && obj[col.dataField] != "") {
							retorno = FormataNumero.formata(obj[col.dataField], 2);	
						} else {
							retorno = "";						
						}
						break;
					case "numBancoFavorecido":
						if (obj[col.dataField]) {
							retorno = completarAEsquerda(obj[col.dataField], "0", 3);
						} else {
							retorno = "";						
						}
						break;					
					default:
						if(obj[col.dataField] == null) {
							retorno = "";
						} else {
							retorno = obj[col.dataField].toString();
						}
						break;
				}                                       
			}
			return retorno;
		}
		
		private function completarAEsquerda(string:String, caracter:String, tamanho:int):String {
			var valor:String = (string == null) ? "" : string;
			var qtdCaracteres:int = tamanho - valor.length;
			var result:String = "";
			for (var i:int=0; i < qtdCaracteres; i++) {
				result += caracter;
			}
			result += valor;
			return result;
		}
		
		private function definirValor(event:Event=null):void {
			if (txtValorIntegralizar.valor <= 0) {
				Alerta.show("O campo Valor a Integralizar (R$) deve ser maior que zero.", "ATENÇÃO", Alerta.ALERTA_OK, txtValorIntegralizar);
			} else {
				if (gridDados.dataProvider != null) {
					for (var i:int=0; i < gridDados.dataProvider.length; i++) {
						gridDados.dataProvider[i].valorIntegralizacaoStr = txtValorIntegralizar.valor;
						gridDados.dataProvider[i].valorIntegralizacao = txtValorIntegralizar.valor;
					}
					gridDados.dataProvider.refresh();
				}
			}
		}
		
		private function enviarRemessa(event:Event=null):void {
			if (validarEnvioRemessa()) {
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				var favorecidos:ArrayCollection = new ArrayCollection();
				for (var i:int=0; i < gridDados.dataProvider.length; i++) {
					if (gridDados.dataProvider[i].selecionado) {
						favorecidos.addItem(gridDados.dataProvider[i]);
					}
				}
				dto.dados.favorecidos = favorecidos;
				servico.enviarRemessa(dto);
			}
		}
		
		private function validarEnvioRemessa():Boolean {
			var selecionouAlgum:Boolean = false;
			for (var i:int=0; i < gridDados.dataProvider.length; i++) {
				if (gridDados.dataProvider[i].selecionado) {
					if (gridDados.dataProvider[i].valorIntegralizacao == null
						|| gridDados.dataProvider[i].valorIntegralizacao <= 0) {
						Alerta.show("É necessário definir um valor de integralização para os favorecidos selecionados.", 
							"ATENÇÃO", Alerta.ALERTA_OK, txtValorIntegralizar);
						return false;
					}
					selecionouAlgum = true;
				}
			}
			if (!selecionouAlgum) {
				Alerta.show("É necessário selecionar pelo menos um favorecido.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			return true;
		}
		
		private function retornoEnviarRemessa(event:ResultEvent):void {
			limpar();
			MostraCursor.removeBusyCursor();
			Alerta.show("Preparação de remessa realizada com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO, null, null);
		}
		
		private function limpar():void {
			procurarCCA.limparCampos();
			procurarCCA.limparRegistro();
			cboBancos.selectedIndex = 0;
			txtValorIntegralizar.valor = 0;
			gridDados.dataProvider = new ArrayCollection();
			gridDados.dataProvider.refresh();
		}
		
		protected function gridDadosInicioEdicao(evt:DataGridEvent):void {	
			valorIntegralizacaoTemp = gridDados.selectedItem.valorIntegralizacaoStr;
		}
		
		protected function gridDadosFimEdicao(evt:DataGridEvent):void {
			// try catch para o caso do ESC
			try {
				gridDados.selectedItem.valorIntegralizacao = gridDados.selectedItem.valorIntegralizacaoStr;					
				if (gridDados.selectedItem.valorIntegralizacaoStr != null && gridDados.selectedItem.valorIntegralizacaoStr != "") {
					var numeroDigitado:Number = new Number(gridDados.selectedItem.valorIntegralizacaoStr.replace(/\./g, '').replace(/,/g, '.'));					
					if (isNaN(numeroDigitado) || numeroDigitado <= 0 || numeroDigitado.toString().length > 12) {
						gridDados.selectedItem.valorIntegralizacaoStr = "";
						gridDados.selectedItem.valorIntegralizacao = "";
					} else {
						gridDados.selectedItem.valorIntegralizacao = numeroDigitado;
						gridDados.selectedItem.valorIntegralizacaoStr = numeroDigitado;
					}
				}
			} catch (error:Error) {
				trace(error);
			}
		}
		/** /---------- ABA PREPARACAO DE REMESSA ---------- */
				
		/** ---------- ABA REMESSA ENVIADA ---------- */
		private function consultarRem(event:Event=null):void {
			if(validarConsultaRemPrep()){
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();				
				gridDetalheRem.dataProvider = new ArrayCollection();				
				
				montarDtoConsultaRem(dto);
				servico.consultarRemessa(dto);
			}
		}
		
		private function anoMes(numAno, numMes):String{
			var mesPreparado:String = numMes.valor < 10 ? "0" + numMes.valor.toString() : numMes.text;
			return numAno.text+mesPreparado;				
		}
		
		private function montarDtoConsultaRem(dto:RequisicaoReqDTO):void {
			dto.dados.numBanco = cboBancos_rem.selectedItem.codListaItem;
			
			anoMesPreparada = anoMes(numAnoRem, numMesRem);
			matriculaPreparada = '0';

			if(procurarCCA_rem.txtNumCCA.text!=''){
				matriculaPreparada = procurarCCA_rem.txtNumCCA.text;
			}
			
			dto.dados.numMatricula = matriculaPreparada;								
			dto.dados.anoMes = anoMesPreparada;			
		}
		
		private function retornoConsultarRem(event:ResultEvent):void {
			var remessas:ArrayCollection = event.result.dados["remessas"] as ArrayCollection;
			
			atualizarGridContasRem(remessas);
			
//			if(remessas.length == 0){
//				Alerta.show("Não há remessas para o filtro escolhido.", "ATENÇÃO", Alerta.ALERTA_OK, numMesRem);
//			}
			
			MostraCursor.removeBusyCursor();
		}
		
		private function validarConsultaRemPrep():Boolean {
			if (numAnoRem.text == '' || numMesRem.text == '') {
				Alerta.show("O campo Mês/Ano é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, numMesRem);
				return false;
			}
			
			if(numMesRem.valor > 12){
				Alerta.show("O campo Mês apresenta valor maior do que o número de meses presentes em um ano.", "ATENÇÃO", Alerta.ALERTA_OK, numMesRem);
				return false;
			}
			
			return true;
		}
		
		private function atualizarGridContasRem(favorecidos:ArrayCollection):void {
			gridRemEnv.dataProvider = favorecidos;
			gridRemEnv.labelFunction = formataDataGridRem;
			gridRemEnv.dataProvider.refresh();
		}		
		
		private function formataDataGridRem(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			if (col.dataField != null) {
				switch(col.dataField) {
					case "valorTotal":
						if (obj[col.dataField] != null && obj[col.dataField] != "") {
							retorno = FormataNumero.formata(obj[col.dataField], 2);	
						} else {
							retorno = "";						
						}
						break;
					case "numBanco":
						if (obj[col.dataField]) {
							retorno = completarAEsquerda(obj[col.dataField], "0", 3);
						} else {
							retorno = "";						
						}
						break;
					case "dataGeracao":
						if (obj[col.dataField]) {
							retorno = FormataData.formataData(obj[col.dataField].data);	
						} else {
							retorno = "";						
						}
						break;
					default:
						if(obj[col.dataField] == null) {
							retorno = "";
						} else {
							retorno = obj[col.dataField].toString();
						}
						break;
				}                                       
			}
			return retorno;
		}		
		
		private function retornoConsultarRemessaEnvDetalhe(event:ResultEvent):void {
			var remessas:ArrayCollection = event.result.dados["remessas"] as ArrayCollection;
			atualizarGridContasRemEnv(remessas);
			MostraCursor.removeBusyCursor();			
		}		
		
		private function atualizarGridContasRemEnv(remessas:ArrayCollection):void {
			gridDetalheRem.dataProvider = remessas;
			gridDetalheRem.labelFunction = formataDataGridRemDetalhe;
			gridDetalheRem.dataProvider.refresh();
		}			
		
		private function emitirRelatorio():void {
			//jboss
			var dtoRel:RequisicaoReqDTO = new RequisicaoReqDTO();
			//was
			//var dtoRel:ParametroDTO = new ParametroDTO();
			
			//comum
			dtoRel.dados.sequencialArquivo = 17;//sequencialArquivo.text;
			
			//jboss
			RelatorioUtil.getRelatorioUtil().emitirRelatorio("emitirRelEnvioIntegOutrosBancosDetalhe",
				SERVICO_REL_SOURCE, dtoRel, "RelEnvioIntegOutrosBancosDetalhe", destino, "Emitindo relatório...",
				null, false);
			
			//was
			//RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelIntegralizacaoOutrosBancosServicoRemote", 
			//	dtoRel, "CCA_RelEnvioIntegOutrosBancosDetalhe", destino, "Emitindo relatório...", null, false);
		}		
		
		private function listarRemEnvDetalhe(evt:MouseEvent):void {
			if(gridRemEnv.selectedItem != null){
				var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
				reqDTO.dados.sequencialArquivo = gridRemEnv.selectedItem.sequencialArquivo;			
				reqDTO.dados.anoMes = anoMesPreparada;				
				reqDTO.dados.numMatricula = matriculaPreparada;								
				reqDTO.dados.numBanco = cboBancos_rem.selectedItem.codListaItem;
				
				servico.consultarRemessaEnvDetalhe(reqDTO);
			}
			
		}
		
		private function formataDataGridRemDetalhe(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			if (col.dataField != null) {
				switch(col.dataField) {
					case "numCpfCnpj":
						if (obj[col.dataField]) {
							retorno = FormatUtil.formataCPFCNPJ(obj[col.dataField]);
						} else {
							retorno = "";						
						}
						break;
					case "valorIntegralizacao":
						if (obj[col.dataField] != null && obj[col.dataField] != "") {
							retorno = FormataNumero.formata(obj[col.dataField], 2);	
						} else {
							retorno = "";						
						}
						break;
					default:
						if(obj[col.dataField] == null) {
							retorno = "";
						} else {
							retorno = obj[col.dataField].toString();
						}
						break;
				}                                       
			}
			return retorno;
		}		
		
		
		/** ---------- FIM ABA REMESSA ENVIADA ---------- */		
		/** ---------- ABA REMESSA RETORNO ---------- */		
		
		private function consultarRemRetBusca(event:Event=null):void {
			if(validarConsultaRemRet()){
				consultarRemRet();				
			}
		}
	
		private function validarConsultaRemRet():Boolean {
			if (numAno.text == '' || numMes.text == '') {
				Alerta.show("O campo Mês/Ano é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, numMes);
				return false;
			}
			
			if(numMes.valor > 12){
				Alerta.show("O campo Mês apresenta valor maior do que o número de meses presentes em um ano.", "ATENÇÃO", Alerta.ALERTA_OK, numMes);
				return false;
			}
			
			return true;
		}
		
		private function consultarRemRet():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			gridRemRetDetalhe.dataProvider = new ArrayCollection();				
			
			montarDtoConsultaRemRet(dto);
			servico.consultarRemessaRetorno(dto);
		}
		
		private function montarDtoConsultaRemRet(dto:RequisicaoReqDTO):void {
			dto.dados.numBanco = cboBancos_rem_ret.selectedItem.codListaItem;
			
			anoMesRetorno = anoMes(numAno, numMes);	
			matriculaRetorno = '0';
			
			if(procurarCCA_rem_ret.txtNumCCA.text!=''){
				matriculaRetorno = procurarCCA_rem_ret.txtNumCCA.text;								
			}
			
			dto.dados.numMatricula = matriculaRetorno;								
			dto.dados.anoMes = anoMesRetorno;			
		}		
		
		private function retornoConsultarRemessaRetorno(event:ResultEvent):void {
			var remessas:ArrayCollection = event.result.dados["remessas"] as ArrayCollection;
			gridRemRet.dataProvider = remessas;
			gridRemRet.labelFunction = formataDataGridRem;
			gridRemRet.dataProvider.refresh();
			
//			if(remessas.length == 0){
//				Alerta.show("Não há remessas para o filtro escolhido.", "ATENÇÃO", Alerta.ALERTA_OK, numMes);
//			}
			
			MostraCursor.removeBusyCursor();
		}
		
		private function integralizar(event:Event=null):void {
			if(gridRemRetDetalhe.dataProvider != null){
				if (validarIntegralizacao()) {
					var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
					var cooperados:ArrayCollection = new ArrayCollection();
					for (var i:int=0; i < gridRemRetDetalhe.dataProvider.length; i++) {
						if (gridRemRetDetalhe.dataProvider[i].selecionado=="1") {
							cooperados.addItem(gridRemRetDetalhe.dataProvider[i]);
						}
					}
					dto.dados.favorecidos = cooperados;
					servico.integralizar(dto);
				}
			}
		}
		
		private function validarIntegralizacao():Boolean {
			var selecionouAlgum:Boolean = false;
			for (var i:int=0; i < gridRemRetDetalhe.dataProvider.length; i++) {
				if (gridRemRetDetalhe.dataProvider[i].selecionado) {
					selecionouAlgum = true;
				}
			}
			if (!selecionouAlgum) {
				Alerta.show("É necessário selecionar pelo menos uma conta capital.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			return true;
		}
		
		private function retornoIntegralizar(event:ResultEvent):void {
			consultarRemRet();
			Alerta.show("Integralização realizada com sucesso", "SUCESSO", Alerta.ALERTA_SUCESSO, null, null);
		}	
		
		private function listarRemRetDetalhe(evt:MouseEvent):void {
			
			if(gridRemRet.selectedItem != null){
				var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
				
				reqDTO.dados.anoMes = anoMesRetorno;			
				reqDTO.dados.numMatricula = matriculaRetorno;												
				reqDTO.dados.numBanco = gridRemRet.selectedItem.numBanco;		
				reqDTO.dados.numAgencia = gridRemRet.selectedItem.numAgencia;		
				reqDTO.dados.sequencialArquivo = gridRemRet.selectedItem.sequencialArquivo;		
				reqDTO.dados.nomeArquivo = gridRemRet.selectedItem.nomeArquivo;		
				
				servico.consultarRemessaRetornoDetalhe(reqDTO);
			}
						
		}
		
		private function retornoConsultarRemessaRetornoDetalhe(event:ResultEvent):void {
			var remessas:ArrayCollection = event.result.dados["remessas"] as ArrayCollection;
			gridRemRetDetalhe.dataProvider = remessas;
			gridRemRetDetalhe.labelFunction = formataDataGridRemDetalhe;
			gridRemRetDetalhe.dataProvider.refresh();
			MostraCursor.removeBusyCursor();
		}		
	
		/** ---------- FIM ABA REMESSA ENVIADA ---------- */		
		
	}
}
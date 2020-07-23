package br.com.sicoob.sisbr.cca.movimentacao.desligarencontrocontas
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.cca.vo.ContratoLiquidacaoVO;
	import br.com.sicoob.sisbr.cca.vo.DesligarContaCapitalRenVO;
	import br.com.sicoob.sisbr.cca.vo.DevolucaoRenVO;
	import br.com.sicoob.sisbr.cca.vo.ParcelamentoRenVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	public class DesligarEncontroContasConfirmacao extends DesligarEncontroContasConfirmacaoView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.DesligarEncontroContasServico";
		private const SERVICO_REL_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelDesligamentoAssociadoServico";
		
		private var servico:ServicoJava = new ServicoJava();
		private var dados:Object;
		
		private var txtAlerta:String = "Observação: Caso escolhido(s) contrato(s) de crédito para pagamento, o sistema realizará a tentativa de pagamento \nintegrado ao sistema de crédito, e em caso de impedimentos o valor não empregado na operação crédito estará \ndisponível no capital a devolver e poderá ser devolvido na funcionalidade de devolução."; 
		
		public function DesligarEncontroContasConfirmacao(_dados:Object) {
			super();
			this.dados = _dados;
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			var parent:DesligarEncontroContas = (this.parentDocument as DesligarEncontroContas);
			rtAlerta.text = txtAlerta;
			
			//cabecalho
			rtCCA.text = dados.descCCA;
			rtMotivoDesligamento.text = dados.descMotivoDesligamento;
			rtDataDesligamento.text = dados.dataDesligamento;
			
			//valores
			gridValores.dataProvider = dados.valores;
			gridValores.columns[0].labelFunction = parent.formataValorMonetario;
			gridValores.columns[2].labelFunction = parent.formataValorMonetario;
			gridValores.columns[4].labelFunction = parent.formataValorMonetario;
			
			//emprestimos
			gridEmprestimos.dataProvider = dados.emprestimosLiquidar;
			gridEmprestimos.columns[1].labelFunction = parent.formataData;
			gridEmprestimos.columns[3].labelFunction = parent.formataValorMonetario;
			
			//devolucao
			gridParcelas.dataProvider = dados.parcelas;
			gridParcelas.columns[0].labelFunction = parent.formataParcela;
			gridParcelas.columns[2].labelFunction = parent.formataValorMonetario;
			gridParcelas.columns[3].labelFunction = parent.formataTipoInteg;
			
			inicializaServico();
		}
		
		private function inicializaServico():void {
			servico.configurarDestino(this.destino);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			controlarServico();
			controlarEventos();	
		}
		
		private function controlarServico():void {
			servico.validarDesligamento.addEventListener(ResultEvent.RESULT, retornoValidarDesligamento);			
			servico.desligar.addEventListener(ResultEvent.RESULT, retornoDesligar);
		}
		
		private function controlarEventos():void {
			btCancelar.addEventListener(MouseEvent.CLICK, fechar);
			btConfirmar.addEventListener(MouseEvent.CLICK, validar);
		}
		
		private function confirmar():void {
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var desligarVO:DesligarContaCapitalRenVO = new DesligarContaCapitalRenVO();
			desligarVO.idContaCapital = dados.idContaCapital;
			desligarVO.tipoOperacao = dados.idMotivoDesligamento;
			desligarVO.vlrInteg = dados.vlrInteg;
			desligarVO.vlrEmprestimos = dados.vlrEmprestimos;
			
			var devolucaoVO:DevolucaoRenVO = new DevolucaoRenVO();
			devolucaoVO.idContaCapital = dados.idContaCapital;
			devolucaoVO.idInstituicao = dados.idInstituicao;
			devolucaoVO.numContaCapital = dados.numContaCapital;
			devolucaoVO.tipoInteg = new Number(dados.tipoInteg);
			devolucaoVO.idPessoaLegado = dados.idPessoaLegado;
			devolucaoVO.idPessoa = dados.idPessoa;
			devolucaoVO.idMotivoDevolucao = new Number(dados.idMotivoDesligamento);
			if(gridParcelas.dataProvider.length > 0) {
				devolucaoVO.vlrAVista = gridParcelas.dataProvider[0].valorParcela;
			}
			devolucaoVO.vlrDevolucao = dados.vlrDevolucao;
			var i:int=0;
			var arrParcelamentoVO:ArrayCollection = new ArrayCollection();
			for(i=0;i<gridParcelas.dataProvider.length;i++){
				var voParcela:ParcelamentoRenVO = new ParcelamentoRenVO();
				voParcela.numParcela = gridParcelas.dataProvider[i].numParcela;
				voParcela.dataVencimento = gridParcelas.dataProvider[i].dataVencimento;
				voParcela.valorParcela = gridParcelas.dataProvider[i].valorParcela;
				voParcela.idTipoInteg = gridParcelas.dataProvider[i].idTipoInteg;
				voParcela.dataVencimentoOrdenacao = gridParcelas.dataProvider[i].dataVencimentoOrdenacao;
				voParcela.idContaCapital = dados.idContaCapital;
				voParcela.idMotivoDevolucao = new Number(dados.idMotivoDesligamento);
				arrParcelamentoVO.addItem(voParcela);
			}
			
			reqDTO.dados.desligarVO = desligarVO;
			reqDTO.dados.devolucaoVO = devolucaoVO;
			reqDTO.dados.listaParcelasVO = arrParcelamentoVO;
			
			var arrContratosLiquidar:ArrayCollection = new ArrayCollection();
			for (i=0;i<dados.emprestimosLiquidar.length;i++) {
				var voContrato:ContratoLiquidacaoVO = new ContratoLiquidacaoVO();
				voContrato.copiarAtributos(dados.emprestimosLiquidar[i]);
				arrContratosLiquidar.addItem(voContrato);
			}
			var arrContratosAbertos:ArrayCollection = new ArrayCollection();
			for (i=0;i<dados.emprestimosAbertos.length;i++) {
				var voContrato:ContratoLiquidacaoVO = new ContratoLiquidacaoVO();
				voContrato.copiarAtributos(dados.emprestimosAbertos[i]);
				arrContratosAbertos.addItem(voContrato);
			}
			reqDTO.dados.listaContratosLiquidarVO = arrContratosLiquidar;
			reqDTO.dados.listaContratosAbertosVO = arrContratosAbertos;
			servico.desligar(reqDTO);
		}
		
		private function validar(evt:Event):void {
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			var vo:DesligarContaCapitalRenVO = new DesligarContaCapitalRenVO();
			vo.idContaCapital = dados.idContaCapital;
			vo.vlrInteg = dados.vlrInteg;
			reqDTO.dados.vo = vo;
			servico.validarDesligamento(reqDTO);
		}
		
		private function retornoValidarDesligamento(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			if(!event.result.dados["valido"] && event.result.dados["impedimentos"]) {
				Alerta.show(event.result.dados["msg"], "ATENÇÃO", Alerta.ALERTA_OK, null, emitirImpedimentos);
				return;
			} 
			confirmar();
		}		
		
		private function emitirImpedimentos(evt:MouseEvent):void {
			if (dados != null) {
				
				//jboss
				var dtoRel:RequisicaoReqDTO = new RequisicaoReqDTO();
				//was
				//				var dtoRel:ParametroDTO = new ParametroDTO();
				
				//comum
				dtoRel.dados.idContaCapital = dados.idContaCapital;
				dtoRel.dados.idPessoa = dados.idPessoa;
				dtoRel.dados.idInstituicao = dados.idInstituicao;
				dtoRel.dados.esconderEmprestimos = true;
				
				//jboss
				RelatorioUtil.getRelatorioUtil().emitirRelatorio("emitirImpedimentos",
					SERVICO_REL_SOURCE, dtoRel, "RelImpedimentosDesligamento", this.destino, "Emitindo relatório...",
					null, false);
				
				//was
				//				RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelImpedimentosDesligamentoServicoRemote", 
				//					dtoRel, "CCA_RelImpedimentosDesligamento", this.destino, "Emitindo relatório...", null, false);
			}
		}		
		
		private function retornoDesligar(evt:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			Alerta.show("Dados gravados com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO);
			(this.parentDocument as DesligarEncontroContas).limpar(evt);
			emitirTermoDesligamento();
			fecharJanela();
		}
		
		private function fechar(evt:MouseEvent):void {
			fecharJanela();
		}
		
		private function emitirTermoDesligamento():void {
			//jboss
			var dtoRel:RequisicaoReqDTO = new RequisicaoReqDTO();
			//was
			//var dtoRel:ParametroDTO = new ParametroDTO();
			
			//comum
			dtoRel.dados.idContaCapital = dados.idContaCapital;
			
			//jboss
			RelatorioUtil.getRelatorioUtil().emitirRelatorio("emitirDesligamentoEncontroContas",
				SERVICO_REL_SOURCE, dtoRel, "RelDesligamentoEncontroContas", destino, "Emitindo relatório...",
				null, false);
			
			//was
			//RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelDesligamentoEncontroContasServicoRemote", 
			//	dtoRel, "CCA_RelDesligamentoEncontroContas", destino, "Emitindo relatório...", null, false);
		}
		
	}
}
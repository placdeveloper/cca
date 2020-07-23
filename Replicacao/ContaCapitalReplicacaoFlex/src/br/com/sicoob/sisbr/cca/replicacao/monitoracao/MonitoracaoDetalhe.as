package br.com.sicoob.sisbr.cca.replicacao.monitoracao
{
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	import mx.formatters.DateFormatter;
	
	import br.com.bancoob.util.FormataNumero;
	import br.com.sicoob.sisbr.cca.vo.ReplicacaoContaCapitalLegadoVO;
	
	public class MonitoracaoDetalhe extends MonitoracaoDetalheView {
		
		private var vo:ReplicacaoContaCapitalLegadoVO;
		private var format:DateFormatter = new DateFormatter();
		
		public function MonitoracaoDetalhe(obj:Object):void {
			super();
			this.vo = obj as ReplicacaoContaCapitalLegadoVO;
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			format.formatString = "DD/MM/YYYY J:NN:SS";
			if (vo.dataHoraReplicacao != null) {
				dtReplicacao.text = format.format(vo.dataHoraReplicacao.data);
			}
			
			numMatricula.text = vo.numMatricula.toString();
			numMatriculaPar.text = vo.numMatricula.toString();
			numMatriculaLan.text = vo.numMatricula.toString();
			numCoop.text = vo.numCooperativa.toString();
			tabela.text = vo.siglaTabela;
			acao.text = vo.codAcao;
			numCliente.text = vo.numCliente.toString();
			mensagem.text = vo.descMensagemReplicacao;
			
			if(vo.idTabelaReplicadaCCA == 1) {
				prencherDadosCCA();
				detalhePAR.removeAllChildren(); 
				detalheLAN.removeAllChildren();
				super.height = 525;
			}
			
			if(vo.idTabelaReplicadaCCA == 2) {
				prencherDadosPAR();
				detalheCCA.removeAllChildren(); 
				detalheLAN.removeAllChildren();
				super.height = 580;
			}
			
			if(vo.idTabelaReplicadaCCA == 3) {
				prencherDadosLAN();
				detalheCCA.removeAllChildren(); 
				detalhePAR.removeAllChildren();
				super.height = 540;
			}
			
			btFechar.addEventListener(MouseEvent.CLICK, fecharDetalhe);
		}
		
		private function prencherDadosCCA():void {
			valorSaldoAtuInteg.text = FormataNumero.formata(vo.valorSaldoAtuInteg);
			valorSaldoAtuSubs.text = FormataNumero.formata(vo.valorSaldoAtuSubsc);
			valorDevolver.text = FormataNumero.formata(vo.valorSaldoAtuDevolver);
			valorBloq.text = FormataNumero.formata(vo.valorSaldoBloqInt);
			idContaCapital.text = vo.idContaCapital.toString();
			
			if(vo.dataMatricula != null) {
				format.formatString = "DD/MM/YYYY";
				dtMatricula.text = format.format(vo.dataMatricula.data);
			}
			
			switch(vo.codSituacao) {
				case 1: 
					situacaoCCA.text = "ATIVO";
					break;
				case 2: 
					situacaoCCA.text = "DEMITIDO";
					break;
				case 3: 
					situacaoCCA.text = "EXCLUIDO";
					break;
				case 4: 
					situacaoCCA.text = "ELIMINADO";
					break;
			}
		}
		
		private function prencherDadosPAR():void {
			format.formatString = "DD/MM/YYYY";
			
			dataVencParcela.text = format.format(vo.dataVencParcela.data);
			dataSituacaoParcela.text = format.format(vo.dataSituacaoParcela.data);
			numParcelamento.text = vo.numParcelamento.toString();
			numParcela.text = vo.numParcela.toString();
			vlrParcela.text = FormataNumero.formata(vo.valorParcela);
			
			numContaCorrente.text = vo.numContaCorrente;
			uIDTrabalha.text = vo.uIDTrabalha;
			descObservacao.text = vo.descObservacao;
			idParcelamentoContaCapital.text = vo.idParcelamentoContaCapital.toString();
			
			if(vo.codTipoParcelamento == 1) {
				tipoParcelamento.text = "INTEGRALIZAÇÃO"
			} else {
				tipoParcelamento.text = "DEVOLUÇÃO"
			}
			
			if(vo.codSituacaoParcela == 1) {
				situacaoParcela.text = "EM ABERTO"
			} else {
				situacaoParcela.text = "PAGO"
			}
			
			switch(vo.codModoLanc) {
				case 1: tipoLancamento.text = "CAIXA"; break;
				case 2: tipoLancamento.text = "CONTA CORRENTE"; break;
				case 3: tipoLancamento.text = "FOLHA"; break;
				case 4: tipoLancamento.text = "COBRANÇA"; break;
				case 5: tipoLancamento.text = "MIGRAÇÃO"; break;
				case 6: tipoLancamento.text = "BANCO"; break;
				case 7: tipoLancamento.text = "RATEIO"; break;
				case 8: tipoLancamento.text = "RESERVA"; break;
				case 9: tipoLancamento.text = "ESTORNO DEVOLUCAO"; 
			}
			
			switch(vo.codMotivoDevolucao) {
				case 2  : motivoDevolucao.text = "DEMISSÃO"; break;
				case 3  : motivoDevolucao.text = "EXCLUSÃO"; break;
				case 4  : motivoDevolucao.text = "ELIMINAÇÃO"; break;
				case 5  : motivoDevolucao.text = "COBERTURA DE DEBITOS VENCIDOS OU VINCENDOS COM A COOPERATIVA"; break;
				case 6  : motivoDevolucao.text = "RESGATE PARCIAL EM FUNCAO DA IDADE E/OU TEMPO DE ASSOCIACAO A COOPERATIVA"; break;
				case 99 : motivoDevolucao.text = "OUTROS"; 
			}
		}
		
		private function prencherDadosLAN():void {
			numSeqLanc.text = vo.numSeqLanc.toString();
			valorLanc.text = FormataNumero.formata(vo.valorLanc);
			descNumDocumento.text = vo.descNumDocumento;
			idTipoHistoricoLanc.text = vo.idTipoHistoricoLanc.toString();
			numLoteLanc.text = vo.numLoteLanc.toString();
			dataHoraInclusao.text = format.format(vo.dataHoraInclusao.data);
			idUsuarioResp.text = vo.idUsuarioResp;
			bolAtualizado.text = vo.bolAtualizado == 1 ? "Sim" : "Não";
			
			format.formatString = "DD/MM/YYYY";
			dataLote.text = format.format(vo.dataLote.data);
		}
		
		private function fecharDetalhe(evt:MouseEvent):void {
			fecharJanela();
		}
	}
}
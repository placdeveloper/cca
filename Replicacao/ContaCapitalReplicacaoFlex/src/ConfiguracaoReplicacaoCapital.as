package
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.DadosLogin;
	import br.com.sicoob.sisbr.cca.replicacao.Configuracao.ConfiguracaoAbaAPI;
	import br.com.sicoob.sisbr.cca.replicacao.Configuracao.ConfiguracaoAbaAcoes;
	import br.com.sicoob.sisbr.cca.replicacao.Configuracao.ConfiguracaoAbaBatimento;
	import br.com.sicoob.sisbr.cca.replicacao.Configuracao.ConfiguracaoAbaConciliacao;
	import br.com.sicoob.sisbr.cca.replicacao.Configuracao.ConfiguracaoAbaCoopPiloto;
	import br.com.sicoob.sisbr.cca.replicacao.Configuracao.ConfiguracaoAbaExpurgo;
	import br.com.sicoob.sisbr.cca.replicacao.Configuracao.ConfiguracaoAbaMonitoracao;
	import br.com.sicoob.sisbr.cca.replicacao.Configuracao.ConfiguracaoAbaPiloto;
	import br.com.sicoob.sisbr.cca.replicacao.Configuracao.ConfiguracaoAbaReplicacao;
	import br.com.sicoob.sisbr.cca.replicacao.Configuracao.ConfiguracaoReplicacaoCapitalView;
	import br.com.bancoob.util.servico.ServicoJava;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	public class ConfiguracaoReplicacaoCapital extends ConfiguracaoReplicacaoCapitalView
	{		
		public var servico:ServicoJava = new ServicoJava();
		
		public var SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.replicacao.servicos.ConfiguracaoReplicacaoFachada";		
		private var _numCooperativa:int=parseInt(DadosLogin.coop);
		
		public var abaReplicacao:ConfiguracaoAbaReplicacao;
		public var abaPiloto:ConfiguracaoAbaPiloto;
		public var abaBatimento:ConfiguracaoAbaBatimento;
		public var abaConciliacao:ConfiguracaoAbaConciliacao;
		public var abaExpurgo:ConfiguracaoAbaExpurgo;
		public var abaCoopPiloto:ConfiguracaoAbaCoopPiloto;
		public var abaAPI:ConfiguracaoAbaAPI;
		public var abaMonitoracao:ConfiguracaoAbaMonitoracao;
		public var abaAcoes:ConfiguracaoAbaAcoes;
		
		public function ConfiguracaoReplicacaoCapital()	{
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, validarConfederacao); 
//			addEventListener(FlexEvent.CREATION_COMPLETE, function(event:FlexEvent):void {
//				configurarServico();
//				initAbas();
//			});
		}
		
		private function validarConfederacao(event:FlexEvent):void {
			if(_numCooperativa != 300){
				Alerta.show("Somente o usuário do Sicoob Confederação tem permissão para acessar a funcionalidade.","Atenção",Alerta.ALERTA_OK);					
				fecharJanela();
				return;								
			}else{
				inicializaServico();
			}
		}	
		
		private function inicializaServico():void {
			configurarServico();
			servico.gerarChaveAcesso(new RequisicaoReqDTO());
			servico.validarChaveAcesso.addEventListener(ResultEvent.RESULT, retornoValidarChaveAcesso);
			btChaveAcesso.addEventListener(MouseEvent.CLICK, validarChaveAcesso);
		}
		
		private function configurarServico():void {
			servico.configurarDestino(this.destino);
			servico.source = SERVICO_SOURCE;
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
		}
		
		private function validarChaveAcesso(evt:MouseEvent):void {
			if (this.txtChaveAcesso.text == "") {
				Alerta.show("Informe a chave de acesso.", "ATENÇÃO", Alerta.ALERTA_OK);
				return;
			}
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.chave = this.txtChaveAcesso.text;
			servico.validarChaveAcesso(dto);
		}
		
		private function retornoValidarChaveAcesso(event:ResultEvent):void {
			if (event.result.dados.valido) {
				initAbas();				
			} else {
				Alerta.show("Chave inválida.", "ATENÇÃO", Alerta.ALERTA_OK);
			}
		}
		
		private function initAbas():void {
			telaAcesso.visible = false;
			telaAcesso.includeInLayout = false;
			telaConfiguracao.visible = true;
			telaConfiguracao.includeInLayout = true;
			
			abaReplicacao = new ConfiguracaoAbaReplicacao(this);
			abaReplicacao.obterDefinicoes();
			abaPiloto = new ConfiguracaoAbaPiloto(this);
			abaBatimento = new ConfiguracaoAbaBatimento(this);
			abaConciliacao = new ConfiguracaoAbaConciliacao(this);
			abaExpurgo = new ConfiguracaoAbaExpurgo(this);
			abaCoopPiloto = new ConfiguracaoAbaCoopPiloto(this);
			abaAPI = new ConfiguracaoAbaAPI(this);
			abaMonitoracao = new ConfiguracaoAbaMonitoracao(this);
			abaAcoes = new ConfiguracaoAbaAcoes(this);
		}
		
	}
}
package br.com.sicoob.cca.replicacao;

import javax.naming.CommunicationException;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;

import org.jboss.remoting.CannotConnectException;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.replicacao.negocio.servicos.EmailReplicacaoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico;
import br.com.sicoob.sisbr.cca.processamento.negocio.excecao.ProcessamentoException;
import br.com.sicoob.sisbr.cca.processamento.negocio.servicos.interfaces.ProcessamentoReplicacaoServico;

/**
 * Executa a chamada remota no Servico de Replicação <br>
 * 1 - Verifica se não tem execução em andamento <br>
 * 2 - Bloqueia a execução enquanto roda a replicação <br>
 * 3 - Executa a Replicação <br>
 * 4 - Desbloqueia a execução <br>
 * 5 - Envia e-mail no caso de execucao bloqueada ou erros 
 */
public class ExecucaoReplicacaoCca  {

	private ReplicacaoContaCapitalLegadoServico replicacaoContaCapitalLegadoServico;
	private EmailReplicacaoServico emailReplicacaoServico;
	private ProcessamentoReplicacaoServico processamentoReplicacaoServico;
	
	private ResultadoStatusExecucao resultado;
	
	private static final int CODIGO_EXECUCAO_LIBERAR = 0;
	private static final int CODIGO_EXECUCAO_BLOQUEAR = 1;
	
	/**
	 * @param args pode receber -help para informações adicionais
	 */
	public static void main(String[] args) {
		ExecucaoReplicacaoUtil.inicializar();
		if (!ExecucaoReplicacaoHelp.exibeHelp(args)) {
			new ExecucaoReplicacaoCca().executar();
		}
	}

	private void executar() {
		
		try {
			ExecucaoReplicacaoUtil.log("Ciclo Iniciado.");
			inicializarServicos();			
			
			ExecucaoReplicacaoUtil.log("Verificando consulta bloqueada.");
			if (isConsultaBloqueada()) {
				ExecucaoReplicacaoUtil.log("Consulta bloqueada. Enviando e-mail e finalizando processo.");
				enviarEmailReplicacaoBloqueada();
				finalizarEmAndamento();
			}
				
			ExecucaoReplicacaoUtil.log("Consulta liberada. Criando bloqueio.");
			atualizarBloqueioExecucao(CODIGO_EXECUCAO_BLOQUEAR);
			ExecucaoReplicacaoUtil.log("Iniciando replicação.");
			processamentoReplicacaoServico.iniciarReplicacao();
			sinalizarSucesso();

		}catch (CommunicationException e) {
			sinalizarErroComunicacao(e);
		}catch (NameNotFoundException e) {
			sinalizarIndisponibilidade(e);		
		}catch (CannotConnectException e) {
			sinalizarErroComunicacao(e);		
		}catch(ProcessamentoException e){
			sinalizarErroNegocial(e);
		} catch (Exception e) {
			sinalizarErroInesperado(e);			
		} finally {
			try {
				if (replicacaoContaCapitalLegadoServico != null) {
					ExecucaoReplicacaoUtil.log("Desbloqueando consulta.");
					atualizarBloqueioExecucao(CODIGO_EXECUCAO_LIBERAR);
				}
			} catch (BancoobException e) {
				ExecucaoReplicacaoUtil.log(e.getMessage());
			}
		}
		verificarCodigoErro();
		
		System.exit(resultado.getStatus().getCodigo()); 
	}

	/**
	 * Verifica se eh um status de erro. Em caso positivo, envia e-mail.
	 */
	private void verificarCodigoErro() {
		try {
			if (resultado.getStatus().isCodigoErro() && emailReplicacaoServico != null) {
				ExecucaoReplicacaoUtil.log("Enviando e-mail de erro.");
				enviarEmailErro();
			}
		} catch (BancoobException e) {
			ExecucaoReplicacaoUtil.log(e.getMessage());
		}
	}

	private void enviarEmailErro() throws BancoobException {
		emailReplicacaoServico.enviar(ExecucaoReplicacaoUtil.lerDestinatarios(), 
				ExecucaoReplicacaoUtil.MSG_ASSUNTO_ERRO, 
				resultado.getMensagem());
	}

	/**
	 * Atualiza a liberacao/bloqueio da execucao de acordo com o parametro.
	 * @see ExecucaoReplicacaoCca#CODIGO_EXECUCAO_LIBERAR
	 * @see ExecucaoReplicacaoCca#CODIGO_EXECUCAO_BLOQUEAR
	 * @param codigoBloqueio
	 * @throws BancoobException
	 */
	private void atualizarBloqueioExecucao(int codigoBloqueio) throws BancoobException {
		replicacaoContaCapitalLegadoServico.liberarBloquearExecucao(codigoBloqueio);
	}

	private void enviarEmailReplicacaoBloqueada() throws BancoobException {
		emailReplicacaoServico.enviar(ExecucaoReplicacaoUtil.lerDestinatarios(), 
				ExecucaoReplicacaoUtil.MSG_ASSUNTO_ERRO, 
				StatusExecucao.CICLO_INICIADO.getMensagem(), 
				true);
	}

	private Boolean isConsultaBloqueada() throws BancoobException {
		return !replicacaoContaCapitalLegadoServico.consultarExecucaoLiberada();
	}

	private void inicializarServicos() throws NamingException {
		ExecucaoReplicacaoUtil.log("Inicializando serviços.");
		InitialContext ctx = new InitialContext();
		emailReplicacaoServico = (EmailReplicacaoServico) ctx.lookup("cca_replicacao/EmailReplicacaoServicoRemote");
		replicacaoContaCapitalLegadoServico = (ReplicacaoContaCapitalLegadoServico) ctx.lookup("ContaCapitalIntegracaoLegado/ReplicacaoContaCapitalLegadoServicoRemote");
		processamentoReplicacaoServico = (ProcessamentoReplicacaoServico) ctx.lookup("cca_processamento/ProcessamentoReplicacaoServicoRemote");
	}

	/**
	 * Sinaliza para o TWS o sucesso na replicação.
	 */
	private void sinalizarSucesso(){
		resultado = StatusExecucao.CICLO_SUCESSO.finalizar();
	}
	
	/**
	 * Sinaliza para o TWS que a replicacao ja esta em execução e finaliza com sucesso.
	 * @throws BancoobException 
	 */
	private void finalizarEmAndamento(){
		resultado = StatusExecucao.CICLO_INICIADO.finalizar();
		System.exit(StatusExecucao.CICLO_INICIADO.getCodigo());
	}
	
	/**
	 * Sinaliza para o TWS erro de comunicação
	 */
	private void sinalizarErroComunicacao(Throwable e){
		resultado = StatusExecucao.ERRO_COMUNICAR_SERVIDOR.finalizar(e);	
	}	
	
	/**
	 * Sinaliza para o TWS indisponibilidade do serviço
	 */
	private void sinalizarIndisponibilidade(Throwable e){
		resultado = StatusExecucao.ERRO_LOCALIZAR_SERVICO.finalizar(e);
	}	
	
	/**
	 * Sinaliza para o TWS o erro na replicação
	 */
	private void sinalizarErroNegocial(Throwable e){
		resultado = StatusExecucao.ERRO_NEGOCIAL.finalizar(e);
	}

	/**
	 * Sinaliza para o TWS um erro inesperado na replicação
	 */
	private void sinalizarErroInesperado(Throwable e){
		resultado = StatusExecucao.ERRO_INESPERADO.finalizar(e);		
	}		
	
}

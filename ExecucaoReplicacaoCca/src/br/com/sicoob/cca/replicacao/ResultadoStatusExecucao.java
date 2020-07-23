package br.com.sicoob.cca.replicacao;

/**
 * O resultado da execucao com o status e a mensagem final.
 * @author Nairon.Silva
 */
public class ResultadoStatusExecucao {

	private StatusExecucao status;
	private String mensagem;
	
	public ResultadoStatusExecucao(StatusExecucao status, String mensagem) {
		this.status = status;
		this.mensagem = mensagem;
	}
	
	public StatusExecucao getStatus() {
		return this.status;
	}
	
	public String getMensagem() {
		return this.mensagem;
	}
	
}

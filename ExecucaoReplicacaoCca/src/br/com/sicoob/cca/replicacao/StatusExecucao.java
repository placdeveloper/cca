package br.com.sicoob.cca.replicacao;

/**
 * Contem os status de execucao possiveis, alem de metodos auxiliares de verificacao e finalizacao.
 * @author Nairon.Silva
 */
public enum StatusExecucao {

	//***********INDICAM SUCESSO AO TWS - EXIT 0***********//
	CICLO_SUCESSO (0, "0 - Ciclo de replicação finalizado com sucesso.\n"),
	CICLO_INICIADO (0, "0 - Replicação bloqueada, pode existir um Job em execução.\nFavor entrar em contato com a GEPRO para verificar o duplo processamento no TWS\n"),
	
	//***********INDICAM ERRO AO TWS, NÃO RECEBE INFORMAÇÃO POR E-MAIL - EXIT 1,2***********//
	ERRO_COMUNICAR_SERVIDOR (1, "1 - Erro ao comunicar com o servidor.\n"),
	ERRO_LOCALIZAR_SERVICO (2, "2 - Erro ao localizar o serviço de replicação.\nServiço indisponível para o servidor.\n"),
	
	//***********INDICAM ERRO AO TWS,RECEBE INFORMAÇÃO POR E-MAIL - EXIT 3,4***********//
	ERRO_NEGOCIAL(3, "3 - Ciclo de replicação finalizado com erro. Verificar Log JBoss.\n"+
			" O fluxo pode ser finalizado pelos seguintes motivos:\n"+
			" * - Erro no momento de consultar a lista de replicação.\n"+
			" * - Erro ao atualizar o status da tabela de controle de replicação (ReplicacaoCCA).\n"+
			" * - BancoobException inesperada dentro do mecanismo de replicação\n"),
	ERRO_INESPERADO (4, "4 - Erro inesperado na replicação.\n Exception Generica - Não esperada nos moldes do tipo 3.\n");
	
	private StatusExecucao(int codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}
	
	private int codigo;
	private String mensagem;
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getMensagem() {
		return this.mensagem;
	}
	
	/**
	 * Verifica se eh um codigo erro (>0).
	 * @return
	 */
	public boolean isCodigoErro() {
		return getCodigo() > CICLO_SUCESSO.getCodigo();
	}
	
	/**
	 * Finaliza o status de execucao.
	 * @return
	 */
	public ResultadoStatusExecucao finalizar() {
		ExecucaoReplicacaoUtil.log(getMensagem());
		return new ResultadoStatusExecucao(this, getMensagem());
	}
	
	/**
	 * Finaliza o status de execucao com uma excecao.
	 * @param e excecao que sera colocada no atributo mensagem.
	 * @return
	 */
	public ResultadoStatusExecucao finalizar(Throwable e) {
		String mensagem = montarMensagemSaida(e);
		ExecucaoReplicacaoUtil.log(mensagem);
		return new ResultadoStatusExecucao(this, mensagem);
	}
	
	private String montarMensagemSaida(Throwable e) {
		return getMensagem()+"\n"+e.getMessage();
	}
	
}

package br.com.sicoob.sisbr.cca.apirest.recursos;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.sicoob.rest.resposta.SicoobMensagem;
import br.com.sicoob.rest.resposta.SicoobResposta;

/**
 * 
 * @author Marcos.Balbi
 *
 */
public class ContaCapitalBaseRecurso {

	/**
	 * Cria o response com o resultado
	 * @param resultado
	 * @param status
	 * @return
	 */
	protected Response criarResultado(Object resultado,Status status) {
		return Response.status(status).entity(getSicoobRespostaResultado(resultado)).build();
	}		
	
	
	/**
	 * Cria o response para uma mensagem negocial
	 * @param mensagem
	 * @param status
	 * @return
	 */
	protected Response criarMensagemNegocio(String mensagem,Status status) {
		return Response.status(status).entity(getSicoobRespostaMensagem(mensagem)).build();		
	}	
	
	
	/**
	 * Cria o response para uma mensagem de  erro
	 * @param mensagem
	 * @param status
	 * @return
	 */
	protected Response criarMensagemErro(String mensagem,Status status) {
		return Response.status(status).entity(getSicoobRespostaErro(mensagem)).build();		
	}	
			
	/**
	 * @param o objeto a ser encapsulado na resposta
	 * @return sicoob resposta
	 */
	protected SicoobResposta getSicoobRespostaResultado(Object resultado) {
		return new SicoobResposta.Builder().resultado(resultado).build();
	}
	
	/**
	 * @param mensagem mensagem a ser retornada na resposta
	 * @return sicoob resposta com o objeto e a mensagem
	 */
	private SicoobResposta getSicoobRespostaMensagem(String mensagem) {
		SicoobMensagem sicoobMensagem = new SicoobMensagem();
		sicoobMensagem.setMensagem(mensagem);						
		return new SicoobResposta.Builder().mensagem(sicoobMensagem).build();
	}
	
	/**
	 * @param o objeto a ser encapsulado na resposta
	 * @param mensagem mensagem a ser retornada na resposta
	 * @return sicoob resposta com o objeto e a mensagem
	 */
	private SicoobResposta getSicoobRespostaMensagem(Object o, String mensagem) {
		SicoobMensagem sicoobMensagem = new SicoobMensagem();
		sicoobMensagem.setMensagem(mensagem);			
		return new SicoobResposta.Builder().resultado(o).mensagem(sicoobMensagem).build();
	}		
	
	/**
	 * @param mensagem mensagem a ser retornada na resposta
	 * @return sicoob resposta com o objeto e a mensagem
	 */
	private SicoobResposta getSicoobRespostaErro(String erro) {
		SicoobMensagem sicoobMensagem = new SicoobMensagem();
		sicoobMensagem.setMensagem(erro);				
		return new SicoobResposta.Builder().erro(sicoobMensagem).build();
	}		
		
}

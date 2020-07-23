package br.com.sicoob.cca.replicacao.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.replicacao.negocio.servicos.interfaces.EmailReplicacaoServicoLocal;
import br.com.sicoob.cca.replicacao.negocio.servicos.interfaces.EmailReplicacaoServicoRemote;
import br.com.sicoob.cca.replicacao.negocio.util.ControleEmailBloqueio;
import br.com.sicoob.infraestrutura.email.EmailAbstract;
import br.com.sicoob.sisbr.cca.legado.negocio.constantes.ReplicacaoLegadoConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoConfiguracaoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ReplicacaoContaCapitalLegadoServicoLocal;

/**
 * EJB para envio de emails da replicacao.
 */
@Stateless
@Local(EmailReplicacaoServicoLocal.class)
@Remote(EmailReplicacaoServicoRemote.class)	
public class EmailReplicacaoServicoEJB extends EmailAbstract implements EmailReplicacaoServicoLocal,EmailReplicacaoServicoRemote {

	private static final String NOME_JNDI_SESSION_PADRAO = "SicoobMailDS";
	
	@EJB
	private ReplicacaoContaCapitalLegadoServicoLocal replicacaoContaCapitalLegadoServico;	

	/**
	 * Construtor
	 */
	public EmailReplicacaoServicoEJB() {
		super();
	}

	/**
	 * Recebe o nome jndi da sessao de email
	 * @param nomeJndiSessaoEmail
	 */
	public EmailReplicacaoServicoEJB(String nomeJndiSessaoEmail) {
		super(nomeJndiSessaoEmail);
	}	
	
	/**
	 * Caminho padrao do servico de email do sicoob
	 */
	@Override
	public String getNomeJNDISessionPadrao() {
		return NOME_JNDI_SESSION_PADRAO;
	}	
	
	/**
	 * Dispara e-mail apartado do TWS, caso ocorra uma falha de controle por parte do tws
	 * @param destinatarios
	 * @param assunto
	 * @param texto
	 * @throws BancoobException 
	 */
	@Override
	public void enviar(String[] destinatarios, String assunto, String texto) throws BancoobException{
		enviar(destinatarios, assunto, texto, false);
	}
	
	/**
	 * @see br.com.sicoob.cca.replicacao.negocio.servicos.EmailReplicacaoServico#enviar(java.lang.String[], java.lang.String, java.lang.String, boolean)
	 */
	public void enviar(String[] destinatarios, String assunto, String texto, boolean processoBloqueado) throws BancoobException{
		
		if (!possuiDestinatarios(destinatarios)) {
			return;
		}
		
		if (processoBloqueado && !ControleEmailBloqueio.deveEnviarEmail()) {
			return;
		}
		
		Session sessao = null;
		Context contexto = null;

		try {
			String emailOrigem = consultarItemConfiguracao(replicacaoContaCapitalLegadoServico.consultarListaConfiguracaoReplicacao(),ReplicacaoLegadoConstantes.PARAM_REMETENTE_EMAIL).getDescConfiguracaoReplicacao();
			
			InternetAddress[] listaDestinatarios = null;
			javax.mail.Message mensagem = null;
			
		    contexto = new InitialContext();
		    sessao = (Session) contexto.lookup(getNomeJNDISessionPadrao());
			
		    listaDestinatarios = criarListaDestinatarios(destinatarios);

		    // Criacao/configuracao da mensagem:
		    mensagem = new MimeMessage(sessao);
		    mensagem.setFrom(new InternetAddress(emailOrigem));
		    mensagem.setRecipients(javax.mail.Message.RecipientType.TO, listaDestinatarios);
		    mensagem.setSubject(assunto);
		    mensagem.setSentDate(new Date());
		    mensagem.setText(texto);
		
		    // Envio da mensagem:
		    Transport.send(mensagem);
		} catch (NamingException excecao) {
		    throw new BancoobException("Erro ao recuperar a sessao de envio de e-mails.", excecao);
		} catch (AddressException excecao) {
			throw new BancoobException("Erro ao configurar a lista de destinatarios.", excecao);
		} catch (MessagingException excecao) {
			throw new BancoobException("Erro ao enviar o e-mail.", excecao);
		}
	}
	
	private boolean possuiDestinatarios(String[] destinatarios) {
		return destinatarios != null 
				&& destinatarios.length > 0
				&& !"".equals(destinatarios[0].trim());
	}
	
	/**
	 * Consulta o item de configuração desejado na lista 
	 */
	private ReplicacaoConfiguracaoLegadoDTO consultarItemConfiguracao(List<ReplicacaoConfiguracaoLegadoDTO> listConfig, Integer parametro) throws BancoobException{
		ReplicacaoConfiguracaoLegadoDTO dto = null;
		for (ReplicacaoConfiguracaoLegadoDTO item :listConfig){
			if (item.getIdConfiguracaoReplicacaoCCA().equals(parametro)){
				dto = item;
				break;
			}
		}
		return dto;
	}	
	
	/**
	 * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
	 */
	public void verificarDisponibilidade() {
		// TODO Auto-generated method stub
		
	}

	
}

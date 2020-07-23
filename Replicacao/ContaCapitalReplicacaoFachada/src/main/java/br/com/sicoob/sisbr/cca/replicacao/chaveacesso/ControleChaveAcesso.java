package br.com.sicoob.sisbr.cca.replicacao.chaveacesso;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.cca.replicacao.negocio.delegates.ContaCapitalReplicacaoFabricaDelegates;
import br.com.sicoob.cca.replicacao.negocio.delegates.EmailReplicacaoDelegate;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.cca.legado.negocio.constantes.ReplicacaoLegadoConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ReplicacaoContaCapitalLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoConfiguracaoLegadoDTO;

/**
 * Controlador da chave de acesso.
 * @author Nairon.Silva
 */
public class ControleChaveAcesso {

	private static String chaveAcesso = "";
	
	private ReplicacaoContaCapitalLegadoDelegate replicacaoContaCapitalLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarReplicacaoContaCapitalLegadoDelegate();
	private EmailReplicacaoDelegate emailDelegate = ContaCapitalReplicacaoFabricaDelegates.getInstance().criarEmailReplicacaoDelegate();
	
	/**
	 * Gera a chave de acesso e envia o e-mail.
	 * @throws BancoobException
	 */
	public void gerarChaveAcesso() throws BancoobException {
		atualizarChaveAcesso();
		String texto = montarTextoChaveAcesso();
		List<ReplicacaoConfiguracaoLegadoDTO> lstConfig = replicacaoContaCapitalLegadoDelegate.consultarListaConfiguracaoReplicacao();
		String param = recuperarParam(lstConfig, ReplicacaoLegadoConstantes.PARAM_LISTA_EMAIL_DEST);
		if (param.trim().length() == 0) {
			param = recuperarParam(lstConfig, ReplicacaoLegadoConstantes.PARAM_REMETENTE_EMAIL);
		}
		String[] destinatarios = param.split(",");
		emailDelegate.enviar(destinatarios, "Chave de acesso - Configuração de Replicação", texto);
	}
	
	private String recuperarParam(List<ReplicacaoConfiguracaoLegadoDTO> lstConfig, Integer param) {
		for (ReplicacaoConfiguracaoLegadoDTO dtoConfig : lstConfig) {
			if (param.equals(dtoConfig.getIdConfiguracaoReplicacaoCCA())
					&& dtoConfig.getDescConfiguracaoReplicacao().trim().length() > 0) {
				return dtoConfig.getDescConfiguracaoReplicacao();
			}
		}
		return "";
	}
	
	private void atualizarChaveAcesso() {
		final int hex0xff = 0xff;
		String texto = "CCA" + new Date().getTime();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(texto.getBytes("UTF-8"));
			byte[] digest = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & hex0xff));
			}
			chaveAcesso = sb.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			chaveAcesso = texto;
		} catch (UnsupportedEncodingException e) {
			chaveAcesso = texto;
		}
		SicoobLoggerPadrao.getInstance(getClass()).info("CCAREPKEY > "+chaveAcesso);
	}
	
	private String montarTextoChaveAcesso() {
		StringBuilder sb = new StringBuilder();
		sb
			.append("Usuário ")
			.append(InformacoesUsuario.getInstance().getLogin())
			.append(" requisitou acesso a configuração de replicação.\n\n")
			.append("Chave de acesso: ")
			.append(chaveAcesso);
		return sb.toString();
	}
	
	/**
	 * Valida se a chave informada eh valida.
	 * @param chave
	 * @return
	 */
	public boolean isChaveValida(String chave) {
		return chaveAcesso.equals(chave);
	}
	
}

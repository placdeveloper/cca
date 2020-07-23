package br.com.sicoob.cca.invoker;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.dto.RetornoMensagem;
import br.com.bancoob.srtb.dto.TipoParametro;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;

/**
 * Classe abstrata dos invokers do frontoffice
 * @author Nairon.Silva
 */
public abstract class ContaCapitalFrontofficeInvoker extends ContaCapitalInvoker {
	
	private static final Logger LOG = Logger.getLogger(ContaCapitalFrontofficeInvoker.class.getName());
	
	private static final Integer ID_INSTITUICAO = 29;

	@Override
	protected void executar() throws BancoobException {
		try {
			Transacao transacao = criarServico(Transacao.class);
			Mensagem mensagem = criarMensagem();
			LOG.info("Executando transacao");
			RetornoMensagem retornoMensagem = transacao.executarTransacao(mensagem);
			LOG.info("CodRetorno: "+retornoMensagem.getCodRetorno());
			LOG.info("Mensagem: "+retornoMensagem.getMensagem());
			LOG.info("ConteudoRetorno: "+retornoMensagem.getConteudoRetorno());
		} catch (ExcecaoTransacao e) {
			LOG.error("Erro ao executar invoker", e);
		}
	}

	private Mensagem criarMensagem() {
		Mensagem mensagem = new Mensagem();
		mensagem.setIdInstituicao(ID_INSTITUICAO);
		mensagem.setCodigoCanal((short) 0);
        List<Parametro> parametros = criarParametros();
        mensagem.setParametros(parametros);
		return mensagem;
	}
	
	protected abstract List<Parametro> criarParametros();
	
	protected Parametro criarParametro(ParametroSRTBCCA parametro, Object valor, int jdbcType) {
		return new Parametro(parametro.getRotulo(), TipoParametro.ENTRADA, valor, jdbcType);
	}
	
}

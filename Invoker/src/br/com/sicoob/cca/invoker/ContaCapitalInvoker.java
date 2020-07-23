package br.com.sicoob.cca.invoker;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;

/**
 * Classe abstrata pra criacao de invokers
 * @author Nairon.Silva
 */
public abstract class ContaCapitalInvoker {

	private static final Logger LOG = Logger.getLogger(ContaCapitalInvoker.class.getName());
	
	private Context context;
	private String lookupName;
	
	/**
	 * Template method para execucao
	 * @param lookupName
	 */
	public void invoke(String lookupName) {
		try {
			StopWatch watch = new StopWatch();
			watch.start();
			this.lookupName = lookupName;
			inicializarContexto();
			executar();
			watch.stop();
			LOG.info("Tempo gasto: " + watch.getTime() + "ms");
		} catch (NamingException e) {
			LOG.error("Erro ao inicializar contexto", e);
		} catch (BancoobException be) {
			LOG.error("Erro ao executar invoker", be);
		}
	}
	
	/**
	 * Metodo a ser implementado pelos invokers
	 * @throws BancoobException
	 */
	protected abstract void executar() throws BancoobException;

	private void inicializarContexto() throws NamingException {
		LOG.info("Inicializando contexto");
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY, bundle.getString("initial_context_factory"));
		properties.put(Context.URL_PKG_PREFIXES, bundle.getString("url_pkg_prefixes"));
		if ("1".equals(bundle.getString("tunnel"))) {
			properties.put(Context.PROVIDER_URL, bundle.getString("tunnel_provider_url"));
			properties.put("com.ibm.CORBA.TunnelAgentURL", bundle.getString("tunnel_agent_url"));
			properties.put("org.omg.CORBA.ORBClass", "com.ibm.CORBA.iiop.ORB");
			properties.put("com.ibm.CORBA.ForceTunnel", "always");
			properties.put("com.ibm.CORBA.FragmentSize", "0");
		} else {
			properties.put(Context.PROVIDER_URL, bundle.getString("provider_url"));
		}
		LOG.info(properties);
		context = new InitialContext(properties);
	}
	
	/**
	 * Cria o servico passado fazendo o lookup.
	 * @param classeServico
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <S> S criarServico(Class<S> classeServico) {
		LOG.info("Criando servico "+classeServico+" em "+lookupName);
		try {
			Object ejbObject = context.lookup(lookupName);
			return (S) PortableRemoteObject.narrow(ejbObject, classeServico);
		} catch (NamingException ne) {
			LOG.error("Erro ao criar serviço", ne);
			throw new BancoobRuntimeException("Erro ao fazer lookup no serviço: "+lookupName, ne);
		}
	}
	
	/**
	 * Retorna o contexto.
	 * @return
	 */
	public Context getContext() {
		return this.context;
	}

}

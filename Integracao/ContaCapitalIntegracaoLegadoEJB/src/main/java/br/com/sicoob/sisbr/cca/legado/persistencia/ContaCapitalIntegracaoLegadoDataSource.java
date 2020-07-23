/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia;

import java.util.Properties;

import br.com.bancoob.infraestrutura.conexao.BancoobDataSource;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * A Classe ContaCapitalIntegracaoLegadoDataSource.
 */
public class ContaCapitalIntegracaoLegadoDataSource extends BancoobDataSource {


	/** O atributo informacoes cooperativa. */
	private static ThreadLocal<Integer> informacoesCooperativa = new ThreadLocal<Integer>();
	
	/** O atributo logger. */
	private final ISicoobLogger logger = SicoobLoggerPadrao.getInstance(getClass());
	
	
	/**
	 * Instancia um novo ContaCapitalIntegracaoLegadoDataSource.
	 *
	 * @param nomeJndi o valor de nome jndi
	 * @param propriedades o valor de propriedades
	 */
	public ContaCapitalIntegracaoLegadoDataSource(String nomeJndi,
			Properties propriedades) {
		super(nomeJndi, propriedades);
	}


	/**
	 * @param numeroCooperativa
	 *            the numeroCooperativa to set
	 */
	public static void definirNumeroCooperativa(Integer numCooperativa) {
		definirNumeroCooperativa(numCooperativa, true);
	}
	
	/**
	 * Define o numero da cooperativa para consulta
	 * @param numCooperativa
	 * @param escreverLog
	 */
	public static void definirNumeroCooperativa(Integer numCooperativa, boolean escreverLog) {
		if (escreverLog) {
			SicoobLoggerPadrao.getInstance(ContaCapitalIntegracaoLegadoDataSource.class).info("Informando usuario NAO logado no sisbr. Cooperativa: " +numCooperativa);
		}
		informacoesCooperativa.set(numCooperativa);
	}

	/**
	 * Tenta utilizar o usuario da ThreadLocal do ContaCapital informacoesCooperativa
	 * Tenta utilizar o usuario da ThreadLocal do ContaCapital informacoesCooperativa 
	 */
	protected String recuperarNumeroCooperativa() {

		String numCooperativa = null;
		logger.info("ContaCapitalIntegracaoLegadoDataSource.recuperarNumeroCooperativa");
		
		if (informacoesCooperativa.get() != null){
			numCooperativa = informacoesCooperativa.get().toString(); 
			logger.info("ContaCapitalIntegracaoLegadoDataSource.Definindo usuario NAO logado no sisbr."+numCooperativa);			
		}else{
			numCooperativa = super.recuperarNumeroCooperativa();
			logger.info("ContaCapitalIntegracaoLegadoDataSource.Definindo usuario logado no sisbr."+numCooperativa);		
		}
		
		return numCooperativa;

	}
	
}

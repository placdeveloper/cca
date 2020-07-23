/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.sql.DataSource;

import br.com.bancoob.infraestrutura.conexao.BancoobConnectionProvider;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Connection provider - esta com implementacao para WAS (hibernate5) e JBoss (hibernate3)
 */
public class ContaCapitalIntegracaoLegadoConnectionProvider extends BancoobConnectionProvider {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo logger. */
	private final transient ISicoobLogger logger = SicoobLoggerPadrao.getInstance(getClass());
	
	/**
	 * WAS - Hibernate 5
	 * @see org.hibernate.connection.DatasourceConnectionProvider#configure(java.util.Properties)
	 */
//	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void configure(Map configValues) {
		
		logger.debug("Configurando o Connection Provider ContaCapitalIntegracaoLegadoConnectionProvider.");
		
		Properties propriedades = new Properties();
		Map<Object, Object> map = configValues;
		
		for (Entry<Object, Object> entry : map.entrySet()) {
			propriedades.put(entry.getKey(), entry.getValue());
		}
		
		DataSource dataSource = new ContaCapitalIntegracaoLegadoDataSource(propriedades.getProperty(NOME_JNDI), propriedades);
		setDataSource(dataSource);

		logger.debug("Connection Provider configurado ContaCapitalIntegracaoLegadoConnectionProvider.");
	}	

	/**
	 * JBOSS - Hibernate 3
	 */
	public void configure(Properties propriedades) {
		
		logger.debug("Configurando o Connection Provider ContaCapitalIntegracaoLegadoConnectionProvider.");
		
		DataSource dataSource = new ContaCapitalIntegracaoLegadoDataSource(propriedades.getProperty(NOME_JNDI), propriedades);
		setDataSource(dataSource);

		logger.debug("Connection Provider configurado ContaCapitalIntegracaoLegadoConnectionProvider.");
	}	

}

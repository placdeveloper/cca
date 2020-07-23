/*
 * 
 */
package br.com.sicoob.cca.relatorios.infraestrutura.relatorios;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRVirtualizer;
import br.com.bancoob.negocio.excecao.RelatorioException;
import br.com.bancoob.negocio.relatorios.ColecaoDataSource;
import br.com.bancoob.negocio.relatorios.jasper.RelatorioJasperReports;

/**
 * @author gesin1
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class RelatorioContaCapital<T> extends RelatorioJasperReports implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Dados para construcao do relatorio
	 */
	private List<T> dados;

	/**
	 * Relatorio e seus dados dinamicos/parametros fixos
	 * @param dados
	 * @param arquivo
	 * @param parametros
	 */
	public RelatorioContaCapital(List<T> dados, String arquivo, Map<String, Object> parametros) {
		super(arquivo);
		this.dados = dados;
		getParametros().putAll(parametros);
	}

	/**
	 * @see JRDataSource
	 */
	@Override
	protected JRDataSource getDataSource() throws RelatorioException {
		return new ColecaoDataSource(this.dados, 1, true);
	}
	
	//TODO: Verificar com a GEARQ motivo de bug em homol java.lang.ClassCastException: org.jboss.serial.finalcontainers.IntegerContainer incompatible with java.lang.String
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected JRVirtualizer getVirtualizer() {
		return null;
	}
}

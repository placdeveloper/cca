package br.com.sicoob.cca.relatorios.infraestrutura.relatorios;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.relatorio.api.datasource.ColecaoDataSource;
import br.com.sicoob.relatorio.api.jasper.SicoobJasperReports;

/**
 * Classe base de relatorios para versao nova.
 * @author Nairon.Silva
 * @param <E>
 */
public class RelatorioContaCapitalV2<E> extends SicoobJasperReports {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** Os dados. */
	private final List<E> dados;

	/**
	 * Construtor
	 * @param dados
	 * @param arquivo
	 * @param parametros
	 */
	public RelatorioContaCapitalV2(List<E> dados, String arquivo, Map<String, Object> parametros) {
		super(arquivo);
		this.dados = dados;		
		if (parametros != null) {
			getParametros().putAll(parametros);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.sicoob.relatorio.api.jasper.SicoobJasperReports#getDataSource()
	 */
	@Override
	protected JRDataSource getDataSource() throws BancoobException {
		return new ColecaoDataSource(dados, 1, true);
	}

}

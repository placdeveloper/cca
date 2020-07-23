package br.com.sicoob.cca.replicacao.negocio.batimento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import br.com.bancoob.negocio.excecao.RelatorioException;
import br.com.bancoob.negocio.relatorios.ColecaoDataSource;
import br.com.sicoob.relatorio.api.jasper.SicoobJasperReports;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Classe raiz para o relatorio de batimento na configuracao de replicacao.
 * @author Nairon.Silva
 */
public class RelBatimentoRaiz extends SicoobJasperReports {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Recebe o arquivo jasper
	 * @param arquivo
	 */
	public RelBatimentoRaiz(String arquivo) {
		super(arquivo);
	}
	
	private final List<RelBatimentoResumo> resumos = new ArrayList<RelBatimentoResumo>();
	private final List<RelBatimentoCooperativa> batimentosCooperativa = new ArrayList<RelBatimentoCooperativa>();
	
	public List<RelBatimentoResumo> getResumos() {
		return resumos;
	}
	public List<RelBatimentoCooperativa> getBatimentosCooperativa() {
		return batimentosCooperativa;
	}
	
	/**
	 * Adiciona parametros
	 * @param parametros
	 */
	public void addParametros(Map<String, Object> parametros) {
		getParametros().putAll(parametros);
	}
	
	/**
	 * Adiciona o RelBatimentoResumo
	 * @param resumo
	 */
	public void addResumo(RelBatimentoResumo resumo) {
		this.resumos.add(resumo);
	}
	
	/**
	 * Adiciona o RelBatimentoCooperativa
	 * @param batimento
	 */
	public void addBatimentoCooperativa(RelBatimentoCooperativa batimento) {
		this.batimentosCooperativa.add(batimento);
	}
	
	@Override
	protected JRDataSource getDataSource() throws RelatorioException {
		return new ColecaoDataSource(Arrays.asList(this), 1, true);
	}
	
	/**
	 * Configura os parametros de batimento do relatorio.
	 */
	public void configurarParametrosBatimento() {
		getParametros().put("resumos", resumos);
		getParametros().put("batimentos", batimentosCooperativa);
	}
	
}

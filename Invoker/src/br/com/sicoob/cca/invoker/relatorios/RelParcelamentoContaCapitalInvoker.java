package br.com.sicoob.cca.invoker.relatorios;

import java.io.IOException;
import java.util.Date;

import org.joda.time.DateTime;

import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import net.sf.jasperreports.engine.JRException;

/**
 * Relatorio parcelamento conta capital
 * @author Marco.Nascimento
 */
public class RelParcelamentoContaCapitalInvoker extends ContaCapitalJasperReportInvoker {
	private static final String LOOKUP_NAME = "cca_relatorios/RelParcelamentoContaCapitalServicoRemote";
	
	private static final int PARAM_ID_INSTITUICAO = 29;
	private static final int PARAM_ID_CCA_INICIAL = 1;
	private static final int PARAM_ID_CCA_FINAL = 500;
	private static final int PARAM_TIPO_PARCELAMENTO = 1;
	private static final int PARAM_FORMA_PARCELAMENTO = -1;
	private static final int PARAM_SITUACAO_PARCELA = 1;
	private static final Date DT_INICIAL = new Date(DateTime.parse("2017-01-01").getMillis());
	private static final Date DT_FINAL = new Date(DateTime.parse("2017-12-31").getMillis());
	private static final int PA = -1;
	private static final int ORDENACAO = 2;
	
	@Override
	protected ParametroDTO criarParametros() {
		ParametroDTO dto = new ParametroDTO();
		dto.getDados().put("filtroIdInstituicao", PARAM_ID_INSTITUICAO);
		
		dto.getDados().put("filtroNumContaCapitalInicial", PARAM_ID_CCA_INICIAL);
		dto.getDados().put("filtroNumContaCapitalFinal", PARAM_ID_CCA_FINAL);
		dto.getDados().put("filtroTipoParcelameto", PARAM_TIPO_PARCELAMENTO);
		dto.getDados().put("filtroFormaParcelamento", PARAM_FORMA_PARCELAMENTO);
		dto.getDados().put("filtroSituacaoParcela", PARAM_SITUACAO_PARCELA);
		dto.getDados().put("filtroPeriodoInicial", DT_INICIAL);
		dto.getDados().put("filtroPeriodoFinal", DT_FINAL);
		dto.getDados().put("filtroOrdenacao", ORDENACAO);
		dto.getDados().put("filtroNumPA", PA);
		return dto;
	}
	
	/**
	 * main
	 */
	public static void main(String[] args) throws JRException, IOException {
		new RelParcelamentoContaCapitalInvoker().invoke(LOOKUP_NAME);
	}	
}
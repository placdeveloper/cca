package br.com.sicoob.cca.relatorios.infraestrutura.relatorios;

import java.io.File;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

/**
* @author Ricardo.Barcante
* implementação para utilizar CsvExporter do JasperReports
*/
public class ContaCapitalOutputRelatorioCSV extends ContaCapitalOutputRelatorio {

	
	@Override
	public RetornoProcessamentoRelatorioDTO createReportFile(JasperPrint relatorio, JRDataSource jrDataSource, String arquivo, String caminho) throws BancoobException {		
		try {
			return exportToCSV(relatorio, caminho + arquivo);
		} catch (JRException e) {
			throw new BancoobException(e);
		}
	}
	
	private RetornoProcessamentoRelatorioDTO exportToCSV(JasperPrint jasperPrint, String outputFilename) throws JRException {
		JRCsvExporter exporter = new JRCsvExporter();
		SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();	
		configuration.setFieldDelimiter(";");
		exporter.setConfiguration(configuration);

		SimpleWriterExporterOutput output = new SimpleWriterExporterOutput(new File(outputFilename));
		SimpleExporterInput input = new SimpleExporterInput( jasperPrint );

		exporter.setExporterInput(input);
		exporter.setExporterOutput(output);
		exporter.exportReport();

		output.close();
		
		// Return apenas para manter a implementação do OutputRelatorio
		return null;
	}
	
	@Override
	protected JRAbstractExporter<?, ?, ?, ?> getExporter(File out) {
		// TODO Auto-generated method stub
		return null;
	}

}

package br.com.sicoob.cca.relatorios.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.ContaCapitalOutputRelatorio;
import br.com.sicoob.relatorio.api.output.OutputRelatorio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;

/**
* @author Ricardo.Barcante
*/
public class FileHandlerUtil {
	public OutputStream streamDeDestino(String pathArquivoDeDestino) throws IOException {
		File arquivoDeDestino = new File(pathArquivoDeDestino);
		if(!arquivoDeDestino.exists()) {
			arquivoDeDestino.createNewFile();
    	}
		
		return new FileOutputStream(arquivoDeDestino);
	}

	public InputStream inputStreamJasperFile(String arquivoJasper) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(arquivoJasper);
	}
	
	public File[] arquivosPorFiltro(String pathDiretorio, FilenameFilter filtro) {
		File caminho = new File(pathDiretorio);
		if(caminho.isDirectory()) {
			return caminho.listFiles(filtro);
		}
		return null;	
	}
	
	public InputStream relatorioExistente(File arquivo) throws FileNotFoundException {
		return new FileInputStream(arquivo);
	}
	
	public void createReportFile(File fileArquivoFormato, String caminhoDoJasperPrint, String formato) throws BancoobException, JRException {
		String caminho = fileArquivoFormato.getParent();
		String arquivo = fileArquivoFormato.getName();
		
		OutputRelatorio outputRelatorio = ContaCapitalOutputRelatorio.createByFormat(formato);
		// Retirado de exportReportTo*File de JasperExportManager
		File arquivoJasperPrint = new File(caminhoDoJasperPrint);
		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(arquivoJasperPrint);
		
		outputRelatorio.createReportFile(jasperPrint, null, arquivo, caminho);	
	}
}

package br.com.sicoob.cca.relatorios.infraestrutura.relatorios;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.relatorio.api.enumaration.EnumFormatoRelatorio;
import br.com.sicoob.relatorio.api.output.OutputRelatorio;
import br.com.sicoob.relatorio.api.output.OutputRelatorioCSV;
import br.com.sicoob.relatorio.api.output.OutputRelatorioDOC;
import br.com.sicoob.relatorio.api.output.OutputRelatorioDOCX;
import br.com.sicoob.relatorio.api.output.OutputRelatorioPDF;
import br.com.sicoob.relatorio.api.output.OutputRelatorioXLSJasperExport;
import br.com.sicoob.relatorio.api.output.OutputRelatorioXLSJasperSemFormatacao;
import br.com.sicoob.relatorio.api.output.OutputRelatorioXLSX;
import br.com.sicoob.relatorio.api.output.OutputRelatorioXLSXSemFormatacao;

/**
* @author Ricardo.Barcante
* suporte para implementação do OutputRelatorio
*/
public abstract class ContaCapitalOutputRelatorio extends OutputRelatorio{

	/**
	 * Creates the by format.
	 *
	 * @param formato the formato
	 * @return the output relatorio
	 * @throws BancoobException the bancoob exception
	 */
	public static OutputRelatorio createByFormat(String formato) throws BancoobException {

		if (EnumFormatoRelatorio.CSV.isCodigo(formato)) {
			return new ContaCapitalOutputRelatorioCSV();
			
		} else if (EnumFormatoRelatorio.XLS.isCodigo(formato)) {
			return new OutputRelatorioXLSJasperExport();

		} else if (EnumFormatoRelatorio.XLSX.isCodigo(formato)) {
			return new OutputRelatorioXLSX();

		} else if (EnumFormatoRelatorio.XLS_SEM_FORMATACAO.isCodigo(formato)) {
			return new OutputRelatorioXLSJasperSemFormatacao();

		} else if (EnumFormatoRelatorio.XLSX_SEM_FORMATACAO.isCodigo(formato)) {
			return new OutputRelatorioXLSXSemFormatacao();

		} else if (EnumFormatoRelatorio.WORD.isCodigo(formato)) {
			return new OutputRelatorioDOC();
			
		} else if (EnumFormatoRelatorio.WORD_2007.isCodigo(formato)) {
			return new OutputRelatorioDOCX();
		}

		return new OutputRelatorioPDF();
	}

}

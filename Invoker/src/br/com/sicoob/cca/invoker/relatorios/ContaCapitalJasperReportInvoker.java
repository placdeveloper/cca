package br.com.sicoob.cca.invoker.relatorios;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.invoker.ContaCapitalInvoker;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.relatorio.api.enumaration.EnumFormatoRelatorio;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Classe abstrata para testar a execucao de relatorios.
 * @author Nairon.Silva
 */
public abstract class ContaCapitalJasperReportInvoker extends ContaCapitalInvoker {

	private static final Logger LOG = Logger.getLogger(ContaCapitalJasperReportInvoker.class.getName());
	
	private String tmpDir;
	private static final String FILE_NAME = "relatorio.pdf";

	@Override
	protected void executar() throws BancoobException {
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		tmpDir = bundle.getString("tmp_dir");
		verificarEstrutura();
		deletarArquivo();
		IProcessamentoRelatorioServico servico = criarServico(IProcessamentoRelatorioServico.class);
		LOG.info("Gerando relatorio");
		servico.gerarRelatorio(criarParametros(), criarRelatorioDados());
		LOG.info("Relatorio gerado");
		abrirArquivo();
	}
	
	/**
	 * Verifica se existe (e cria se necessario) a estrutura para onde o relatorio sera montado.
	 */
	private void verificarEstrutura() {
		File tmpRelDir = new File(tmpDir);
		if (!tmpRelDir.exists()) {
			LOG.info("Criando estrutura: "+tmpRelDir.getPath());
			if (!tmpRelDir.mkdirs()) {
				LOG.info("Nao conseguiu criar estrutura");
			}
		}
	}

	/**
	 * Abre o relatorio apos a execucao.
	 */
	private void abrirArquivo() {
		if (Desktop.isDesktopSupported()) {
			File file = new File(tmpDir+File.separatorChar+FILE_NAME);
			try {
				LOG.info("Abrindo arquivo "+file.getPath());
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				LOG.error("Erro ao abrir o arquivo do relatorio", e);
			}
		}
	}

	/**
	 * Deleta arquivo anterior caso exista.
	 */
	private void deletarArquivo() {
		File file = new File(tmpDir+File.separatorChar+FILE_NAME);
		if (file.exists()) {
			LOG.info("Deletando arquivo existente");
			if (!file.delete()) {
				LOG.info("Nao deletou arquivo existente");
			}
		}
	}
	
	/**
	 * Metodo a ser implementado pelas classes de teste com os parametros do servico.
	 * @return
	 */
	protected abstract ParametroDTO criarParametros();

	/**
	 * Cria a configuracao padrao.
	 * @return
	 */
	private RelatorioDadosDTO criarRelatorioDados() {
		UsuarioBancoobDTO usuario = new UsuarioBancoobDTO();
		usuario.setLogin("Invoker");
		usuario.setIdInstituicao("29");
		return new RelatorioDadosDTO(FILE_NAME, tmpDir, EnumFormatoRelatorio.PDF.getCodigo(), usuario);
	}

}

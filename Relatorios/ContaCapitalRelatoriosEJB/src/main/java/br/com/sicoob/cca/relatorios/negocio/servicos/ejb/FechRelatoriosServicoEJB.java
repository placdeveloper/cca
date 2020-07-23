package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.io.Closeable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.enums.EnumRelatorios;
import br.com.sicoob.cca.relatorios.negocio.dto.FechRelatorioDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelatoriosServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelatoriosServicoRemote;
import br.com.sicoob.cca.relatorios.util.FileHandlerUtil;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
* @author Ricardo.Barcante
*/
@Stateless
@Local(FechRelatoriosServicoLocal.class)
@Remote(FechRelatoriosServicoRemote.class)
public class FechRelatoriosServicoEJB extends ContaCapitalRelatoriosServicoEJB implements FechRelatoriosServicoLocal, FechRelatoriosServicoRemote {

	@EJB
	private ProdutoLegadoServicoLocal produtoLegadoServicoLocal;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal integracaoServico;
	
	//Inicialização para facilitar mock em testes
	private FileHandlerUtil fileHandlerUtil = new FileHandlerUtil();
	
	/**
	 * @param arquivoJasper
	 * @param codRelatorio
	 * @param collectionDtos
	 * @param mapaParametros
	 * @param numCoop
	 * @throws BancoobException
	 */
	public void geraJasperPrintAPartirDeCollection(String arquivoJasper, String codRelatorio, 
			Collection<? extends BancoobDto> collectionDtos, Map<String, Object> mapaParametros, Integer numCoop) throws BancoobException {
		
		String pathArquivoDeDestino = arquivoDeDestino(numCoop, codRelatorio);
		JRDataSource jrDsDataSource = new JRBeanCollectionDataSource(collectionDtos);
		
		InputStream inputStream = null;
	    OutputStream outputStream = null;
	    			
	    try {
	    	this.getLogger().info("Gerando jasper print a partir de collection no caminho: " + pathArquivoDeDestino);

	    	// Recupera o .jasper do jar
	    	inputStream = fileHandlerUtil.inputStreamJasperFile(arquivoJasper);
 	    	outputStream = fileHandlerUtil.streamDeDestino(pathArquivoDeDestino);
	    	
	    	JasperFillManager.fillReportToStream(inputStream, outputStream, mapaParametros, jrDsDataSource);
		} catch (JRException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException("Erro ao gerar jasper print." , e);
		} catch (IOException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException("Erro ao gerar jasper print." , e);
		} catch (Exception e) { // NOSONAR
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException("Erro ao gerar jasper print." , e);
		} finally {
			close(inputStream);
			close(outputStream);
		}
	}
	
	/**
	 * @param formato
	 * @param caminhoDoJasperPrint
	 * @return FechRelatorioDTO
	 * @throws BancoobException
	 */
	public FechRelatorioDTO exportaRelatorioParaFormato(String formato, String caminhoDoJasperPrint) throws BancoobException {
		String arquivoFormato = caminhoDoJasperPrint.split("\\.")[0] + "." + formato;
		
		File fileArquivoFormato = new File(arquivoFormato);
		
		this.getLogger().info("Exportando arquivo " + arquivoFormato + "\nno caminho:  " + caminhoDoJasperPrint + "...");
		
		if(fileArquivoFormato.exists()) {			
			try {
				return geraFechRelatorioDtoDeRetorno(fileArquivoFormato);
			} catch (IOException e) {
				this.getLogger().erro(e, e.getMessage());
				throw new BancoobException("MSG_RELATORIO_ERRO" , e);
			} 
		}
		
		try {
			fileHandlerUtil.createReportFile(fileArquivoFormato, caminhoDoJasperPrint, formato);
			return geraFechRelatorioDtoDeRetorno(fileArquivoFormato);
		} catch (Exception e) { // NOSONAR
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException("MSG_RELATORIO_ERRO" , e);
		}
	}
	
	/**
	 * Popula DTO com byteArray e nome do relatorio para retornar para a fachada
	 * @param fileArquivoFormato
	 * @return
	 * @throws IOException
	 * @throws BancoobException
	 */
	private FechRelatorioDTO geraFechRelatorioDtoDeRetorno(File fileArquivoFormato) throws IOException, BancoobException {
		InputStream input = fileHandlerUtil.relatorioExistente(fileArquivoFormato);
		FechRelatorioDTO retorno = new FechRelatorioDTO();
		retorno.setReportStream(IOUtils.toByteArray(input));
		retorno.setReportName(geraNameRelatorio(fileArquivoFormato.getName()));
		close(input);
		
		return retorno;
	}
	
	/**
	 * Retorna lista de dtos pros arquivos de relatorio na pasta da data e cooperativas passados no dto de entrada
	 * @param dto
	 * @return List<FechRelatorioDTO>
	 * @throws BancoobException
	 */
	public List<FechRelatorioDTO> listaRelatoriosPorData(FechRelatorioDTO dto) throws BancoobException {
		Date data = dto.getData();
		Integer numCoop = integracaoServico.obterNumeroCooperativa(dto.getIdInstituicao());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		
		StringBuilder pathDiretorio = montaPathDiretorio(numCoop, calendar);
		List<FechRelatorioDTO> relatorios = new ArrayList<FechRelatorioDTO>();
		
		this.getLogger().info("Listando relatorios no caminho: " + pathDiretorio.toString());
		
		File[] arquivos = fileHandlerUtil.arquivosPorFiltro(pathDiretorio.toString(), filtroParaNomeDeArquivo(numCoop, calendar));
		
		if(arquivos == null || arquivos.length == 0) {
			throw new NegocioException("Não há relatórios para a data escolhida.");
		}
	
		FechRelatorioDTO fechRelatorioDTO = null;
		for(File arquivo : arquivos) {
			fechRelatorioDTO = criaFechRelatorioComCodEDescricao(arquivo);
			fechRelatorioDTO.setCaminho(arquivo.getAbsolutePath());
			fechRelatorioDTO.setData(data);
			
			relatorios.add(fechRelatorioDTO);
		}
		
		return relatorios;
	}
	
	/**
	 * Monta o filtro para nome do arquivo com base na data e numero da cooperativa
	 * @param numCoop
	 * @param calendar
	 * @return FilenameFilter
	 */
	private FilenameFilter filtroParaNomeDeArquivo(Integer numCoop, Calendar calendar) {
		String fileSuffix = montaData(calendar) + numCoop + ".jrprint";
		return new SuffixFileFilter(fileSuffix);
	}
	
	/**
	 * 
	 * @param arquivo
	 * @return
	 */
	private FechRelatorioDTO criaFechRelatorioComCodEDescricao(File arquivo) {
		FechRelatorioDTO fechRelatorioDTO = new FechRelatorioDTO();
		for(EnumRelatorios enumRelatorio : EnumRelatorios.values()) {
			if(arquivo.getName().contains(enumRelatorio.getCodigo())){
				fechRelatorioDTO.setCodRelatorio(enumRelatorio.getCodigo());
				fechRelatorioDTO.setDescricao(enumRelatorio.getDescricao());
			}
		}
		
		return fechRelatorioDTO;
	}
	
	/**
	 * 
	 * @param closeable
	 * @throws BancoobException
	 */
	private void close(Closeable closeable) throws BancoobException {
	     if (closeable == null) return; 
	     try {
	         closeable.close();
	     } catch (IOException e) {
	    	 this.getLogger().erro(e, e.getMessage());
	    	 throw new BancoobException("Erro ao gerar jasper print." , e);
	     }
	}
	
	/**
	 * Gera caminho e nome do arquvo para data atual do produto , cooperativa e relatorio passado
	 * @param numCoop
	 * @param codRelatorio
	 * @return String
	 * @throws BancoobException
	 */
	private String arquivoDeDestino(Integer numCoop, String codRelatorio) throws BancoobException {
		Date dataAtualProdutoLegado = produtoLegadoServicoLocal.obterDataAtualProdutoNumCoop(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, numCoop);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataAtualProdutoLegado);
		
		StringBuilder sBuilder = montaPathDiretorio(numCoop, calendar);
		criaDiretorios(sBuilder.toString());
		
		sBuilder.append(codRelatorio)
		.append(montaData(calendar))
		.append(numCoop)
		.append(".jrprint");
		
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @param calendar
	 * @return
	 */
	private String montaData(Calendar calendar) {
		int ano = calendar.get(Calendar.YEAR);
		
		int month = calendar.get(Calendar.MONTH) + 1;
		String mes =  month < 10 ? "0" + month : "" + month;
		
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String dia =  day < 10 ? "0" + day : "" + day;
		
		return ano + mes + dia;
	}
	
	/**
	 * Monta caminho do diretorio onde os jrprint serao gerados ou pesquisados
	 * @param numCoop
	 * @param calendar
	 * @return StringBuilder
	 */
	private StringBuilder montaPathDiretorio(Integer numCoop, Calendar calendar) {
		// Cria StringBuilder com capacidade inicial de 76
		// para evitar alocações durante o append
		StringBuilder sBuilder = new StringBuilder(76);
		sBuilder.append("/mnt/arquivos/cooperativas/");

		int ano = calendar.get(Calendar.YEAR);
		
		int month = calendar.get(Calendar.MONTH) + 1;
		String mes =  month < 10 ? "0" + month : "" + month;

		sBuilder.append(numCoop)
		.append("/spool/")
		.append(ano)
		.append("/")
		.append(mes)
		.append("/cca/");
		
		return sBuilder;
	}
	
	/**
	 * Cria diretorios recursivamente até o caminho passado
	 * @param caminho
	 */
	private void criaDiretorios(String caminho) {
		File directory = new File(caminho);
		if(! directory.exists()) {
			directory.mkdirs();
		}
	}
	
	/**
	 * 
	 * @param arquivo
	 * @return
	 */
	private String geraNameRelatorio(String arquivo) {
		String retorno = "";
		for(EnumRelatorios enumRelatorio : EnumRelatorios.values()) {
			if(arquivo.contains(enumRelatorio.getCodigo())){
				retorno = enumRelatorio.getNomeRelatorio() + "_" + extrairDataDoArquivo(arquivo, enumRelatorio.getCodigo());
			}
		}
		
		return retorno;
	}
	
	/**
	 * Extrai data e número da  cooperativa do nome do arquivo
	 * @param arquivo
	 * @param codRelatorio
	 * @return String
	 */
	private String extrairDataDoArquivo(String arquivo, String codRelatorio) {
		return arquivo.split("\\.")[0].split(codRelatorio)[1];
	}
}
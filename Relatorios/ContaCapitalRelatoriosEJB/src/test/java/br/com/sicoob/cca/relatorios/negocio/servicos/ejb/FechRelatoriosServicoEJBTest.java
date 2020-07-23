package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.cca.relatorios.negocio.dto.FechRelatorioDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSaldoAtualDTO;
import br.com.sicoob.cca.relatorios.util.FileHandlerUtil;
import br.com.sicoob.cca.relatorios.util.MapaParametrosUtil;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import net.sf.jasperreports.engine.JRException;

/**
* @author Ricardo.Barcante
*/
@PrepareForTest(FechRelatoriosServicoEJB.class)
public class FechRelatoriosServicoEJBTest extends Mockito {
	
	@Mock
	private ProdutoLegadoServicoLocal produtoLegadoServicoLocal;
	
	@Mock
	private InstituicaoIntegracaoServicoLocal integracaoServico;
	
	@Spy
	private FileHandlerUtil fileHandlerUtil;
	
	@InjectMocks
	private FechRelatoriosServicoEJB ejb = new FechRelatoriosServicoEJB();

	@Before
	public void inicializaMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeClass
	public static void setUp() {
		InformacoesUsuario info = new InformacoesUsuario();
		info.setIdInstituicao("3008");
		info.setPac("0");
		info.setLogin("Teste");
		InformacoesUsuario.INSTANCIA.set(info);
	}
	
	@Test
	public void testeGerarJasperPrint() throws Exception{
		RelSaldoAtualDTO dto = new RelSaldoAtualDTO();
		dto.setDataAtualProduto(new Date());
		
		Collection<RelSaldoAtualDTO> collectionDtos = new ArrayList<RelSaldoAtualDTO>();
		collectionDtos.add(dto);
		
		InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = new InstituicaoIntegracaoDTO();
		instituicaoIntegracaoDTO.setIdInstituicao(29);
		instituicaoIntegracaoDTO.setDescInstituicao("3008 - TESTANDO");
		
		Map<String, Object> mapaParametros = MapaParametrosUtil.criarParametros(new Date(), instituicaoIntegracaoDTO);
		
		Mockito.when(produtoLegadoServicoLocal.obterDataAtualProdutoNumCoop(Mockito.anyInt(), Mockito.anyInt())).thenReturn(new Date());

		// Mock de classe para escrever bytes na memoria
		Mockito.doReturn(new ByteArrayOutputStream()).when(fileHandlerUtil).streamDeDestino(Mockito.anyString());
		
		ejb.geraJasperPrintAPartirDeCollection("CCA_Relatorio_Saldo_Atual.jasper", "CCA06", collectionDtos, mapaParametros, 3008);

	}
	
	@Test
	public void testeListaRelatoriosExistente() throws BancoobException, ParseException, IOException {
		FechRelatorioDTO dto = new FechRelatorioDTO();
		
		dto.setData(new Date());
		dto.setIdInstituicao(29);

		File[] listaDeArquivos = {
				new File("CCA006TESTE"),
				new File("CCA018TESTE"),
		};
		
		Mockito.when(integracaoServico.obterNumeroCooperativa(Mockito.anyInt())).thenReturn(3008);
		Mockito.doReturn(listaDeArquivos).when(fileHandlerUtil).arquivosPorFiltro(Mockito.anyString(), (FilenameFilter) Mockito.anyObject());		
		
		List<FechRelatorioDTO> lista = ejb.listaRelatoriosPorData(dto);

		assertNotNull(lista);
		assertFalse(lista.isEmpty());
		
		assertEquals(lista.get(0).getCodRelatorio(), "CCA006");
		assertEquals(lista.get(1).getCodRelatorio(), "CCA018");
	}
	
	@Test(expected = BancoobException.class)
	public void testeListaRelatoriosInexistentesParaData_RetornaException() throws BancoobException, ParseException {
		FechRelatorioDTO dto = new FechRelatorioDTO();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dto.setData(dateFormat.parse("1970-01-01"));
		dto.setIdInstituicao(29);
		
		Mockito.when(integracaoServico.obterNumeroCooperativa(Mockito.anyInt())).thenReturn(3008);
		
		ejb.listaRelatoriosPorData(dto);
	}
	
	@Test(expected = BancoobException.class)
	public void testeExportarPrintInexistente_RetornaException() throws BancoobException {
		ejb.exportaRelatorioParaFormato("PDF", "");
	}
	
	@Test
	public void testeExportarPrintExistente() throws BancoobException, JRException, FileNotFoundException {
		Mockito.doNothing().when(fileHandlerUtil).createReportFile((File) Mockito.anyObject() , Mockito.anyString(), Mockito.anyString());
		
		byte[] bytea = {0};
		InputStream inputStream = new ByteArrayInputStream(bytea);
		Mockito.doReturn(inputStream).when(fileHandlerUtil).relatorioExistente((File) Mockito.anyObject());
		
		FechRelatorioDTO rDto = ejb.exportaRelatorioParaFormato("PDF", "\\caminho\\de\\teste\\arquivoTeste.jrprint");
		
		assertNotNull(rDto);
	}
	
}
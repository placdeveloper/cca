package br.com.sicoob.cca.relatorios.negocio.servicos;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.cca.relatorios.negocio.dto.FechRelatorioDTO;

/**
* @author Ricardo.Barcante
*/
public interface FechRelatoriosServico extends ContaCapitalRelatoriosServico{
	
	/**
	 * 
	 * @param formato
	 * @param jasperPrintFileName
	 * @return 
	 * @throws BancoobException
	 */
	FechRelatorioDTO exportaRelatorioParaFormato(String formato, String jasperPrintFileName) throws BancoobException;
	
	/**
	 * 
	 * @param sourceFileName
	 * @param codRelatorio
	 * @param collectionDtos
	 * @param mapaParametros
	 * @param numCoop
	 * @throws BancoobException
	 */
	void geraJasperPrintAPartirDeCollection(String sourceFileName, String codRelatorio, Collection<? extends BancoobDto> collectionDtos, Map<String, Object> mapaParametros, Integer numCoop) throws BancoobException;

	List<FechRelatorioDTO> listaRelatoriosPorData(FechRelatorioDTO dto) throws BancoobException;
}
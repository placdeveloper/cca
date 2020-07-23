package br.com.sicoob.cca.relatorios.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;

/**
* @author Ricardo.Barcante
*/
public class MapaParametrosUtil {	
	public static Map<String, Object> criarParametros(Date dataAtualProduto, InstituicaoIntegracaoDTO instituicao) throws BancoobException{
		Map<String, Object> mapaParametros = setaImagemSicoob();
		
		mapaParametros = setaDescricaoCooperativa(mapaParametros, instituicao);
		mapaParametros = setaDadosDoProduto(mapaParametros, dataAtualProduto);
	    
		return mapaParametros;
	}
	
	private static Map<String, Object> setaImagemSicoob() throws BancoobException{
		Map<String, Object> mapaParametros = new HashMap<String, Object>();
		
		Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("cca_relatorios.propriedades");
		String caminhologosicoob = propriedades.getProperty("cca.caminhologosicoob");
		mapaParametros.put("IMAGEM_SICOOB", ContaCapitalUtil.recuperarImagemRelatorio(caminhologosicoob));
		
	    return mapaParametros;
	}
	
	private static Map<String, Object> setaDescricaoCooperativa(Map<String, Object> mapaParametros, InstituicaoIntegracaoDTO instituicao) throws BancoobException{
		mapaParametros.put("DESC_SIGLA_COOP", instituicao.getNumero() + " - " + instituicao.getSiglaInstituicao());

		return mapaParametros;
	}
	
	private static Map<String, Object> setaDadosDoProduto(Map<String, Object> mapaParametros, Date dataAtualProduto) throws BancoobException{
		mapaParametros.put("dataProcessamento", dataAtualProduto);
	    
	    return mapaParametros;
	}
}

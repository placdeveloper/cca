package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelLancContabilServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelLancContabilServicoRemote;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelatoriosServicoLocal;
import br.com.sicoob.cca.relatorios.util.MapaParametrosUtil;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.FechRelLancContabilDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechRelLancContabilLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
* @author Ricardo.Barcante
*/
@Stateless
@Local(FechRelLancContabilServicoLocal.class)
@Remote(FechRelLancContabilServicoRemote.class)
public class FechRelLancContabilServicoEJB extends ContaCapitalRelatoriosServicoEJB implements FechRelLancContabilServicoLocal, FechRelLancContabilServicoRemote {
	
	@EJB
	private FechRelLancContabilLegadoServicoLocal fechRelLancContabilLegadoServicoLocal;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal integracaoServicoLocal;
	
	@EJB
	private ProdutoLegadoServicoLocal produtoLegadoServicoLocal;
	
	@EJB
	private FechRelatoriosServicoLocal fechRelatoriosServicoLocal;
	
	public void rodar(Integer numCoop) throws BancoobException {
		final String COD_RELATORIO = "CCA020";
		final String RELATORIO_LANCAMENTO_CONTABIL = "CCA_Relatorio_Lancamento_Contabil.jasper";

		InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = integracaoServicoLocal.obterInstituicaoIntegracaoPorNumCoop(numCoop);
		Integer idInstituicao = instituicaoIntegracaoDTO.getIdInstituicao();

		Date dataProduto = produtoLegadoServicoLocal.obterDataAnteriorProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL,
				idInstituicao);

		List<FechRelLancContabilDTO> collectionDtos = new ArrayList<FechRelLancContabilDTO>();
		FechRelLancContabilDTO filtro = new FechRelLancContabilDTO();

		filtro.setIdInstituicao(idInstituicao);
		filtro.setDataAtualProduto(dataProduto);

		collectionDtos.addAll(fechRelLancContabilLegadoServicoLocal.pesquisarLancamentoContabil(filtro, numCoop));

		if (collectionDtos.isEmpty() || collectionDtos.get(0) == null)
		{
			return;
		}

		Map<String, Object> mapaParametros = MapaParametrosUtil.criarParametros(dataProduto, instituicaoIntegracaoDTO);
		mapaParametros = setaParametrosDoRelatorio(mapaParametros);

		fechRelatoriosServicoLocal.geraJasperPrintAPartirDeCollection(RELATORIO_LANCAMENTO_CONTABIL, COD_RELATORIO, collectionDtos,
				mapaParametros, numCoop);
	}

	private Map<String, Object> setaParametrosDoRelatorio(Map<String, Object> mapaParametros) throws BancoobException{
	    mapaParametros.put("COD_RELATORIO", "CCA - 020");
	    
	    return mapaParametros;
	}
	
}
package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.constantes.TemplatesRelatorio;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelConciliacaoContabilServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelConciliacaoContabilServicoRemote;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelatoriosServicoLocal;
import br.com.sicoob.cca.relatorios.util.MapaParametrosUtil;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RelConciliacaoContabilDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechRelConciliacaoContabilLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
 * @author Kleber Alves
 */
@Stateless
@Local(FechRelConciliacaoContabilServicoLocal.class)
@Remote(FechRelConciliacaoContabilServicoRemote.class)
public class FechRelConciliacaoContabilServicoEJB extends ContaCapitalRelatoriosServicoEJB
		implements FechRelConciliacaoContabilServicoLocal, FechRelConciliacaoContabilServicoRemote {

	@EJB
	private FechRelConciliacaoContabilLegadoServicoLocal relatorioConciliacaoService;

	@EJB
	private InstituicaoIntegracaoServicoLocal integracaoService;

	@EJB
	private ProdutoLegadoServicoLocal produtoLegadoService;

	@EJB
	private FechRelatoriosServicoLocal relatorioService;

	public void rodar(Integer numeroCooperativa) throws BancoobException {

		InstituicaoIntegracaoDTO instituicao = obtemInstituicacao(numeroCooperativa);
		Date dataProduto = obtemDataProduto(instituicao.getIdInstituicao());

		List<RelConciliacaoContabilDTO> resultado = obtemDadosRelatorio(dataProduto, numeroCooperativa);
		
		if (resultado == null || resultado.isEmpty()) {
			return;
		}

		emiteRelatorio(numeroCooperativa, dataProduto, instituicao, resultado);
	}

	private void emiteRelatorio(Integer numeroCooperativa, Date dataProduto, InstituicaoIntegracaoDTO instituicao,
			List<RelConciliacaoContabilDTO> resultado) throws BancoobException {

		Map<String, Object> mapaParametros = MapaParametrosUtil.criarParametros(dataProduto, instituicao);
		relatorioService.geraJasperPrintAPartirDeCollection(TemplatesRelatorio.RELATORIO_CONCILIACAO_CONTABIL,
				CodigoRelatorio.RELATORIO_CONCILIACAO_CONTABIL, resultado, mapaParametros, numeroCooperativa);
	}

	private List<RelConciliacaoContabilDTO> obtemDadosRelatorio(Date dataProduto, Integer numeroCooperativa) throws BancoobException {

		return relatorioConciliacaoService.obtemDadosConcialicaoContabil(dataProduto, dataProduto, numeroCooperativa);
	}

	private Date obtemDataProduto(Integer idInstituicao) throws BancoobException {

		return produtoLegadoService.obterDataAnteriorProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, idInstituicao);
	}

	private InstituicaoIntegracaoDTO obtemInstituicacao(Integer numeroCooperativa) throws BancoobException {

		return integracaoService.obterInstituicaoIntegracaoPorNumCoop(numeroCooperativa);
	}
}
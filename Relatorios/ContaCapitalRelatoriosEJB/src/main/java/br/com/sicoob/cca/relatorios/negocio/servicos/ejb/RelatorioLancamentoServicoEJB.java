package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.relatorios.negocio.dto.RelLancamentosDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelatorioLancamentoServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelatoriosServicoLocal;
import br.com.sicoob.cca.relatorios.persistencia.dao.ContaCapitalRelatoriosDaoFactory;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelLancamentosDao;
import br.com.sicoob.cca.relatorios.util.MapaParametrosUtil;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
 * @author Kleber Alves
 */
public abstract class RelatorioLancamentoServicoEJB<T extends RelLancamentosDTO> extends ContaCapitalRelatoriosServicoEJB
		implements RelatorioLancamentoServico {

	@Dao(entityManager = "em", fabrica = ContaCapitalRelatoriosDaoFactory.class)
	protected RelLancamentosDao relatorioLancamentosDAO;

	@EJB
	private ProdutoLegadoServicoLocal produtoLegadoService;

	@EJB
	private InstituicaoIntegracaoServicoLocal integracaoService;

	@EJB
	private FechRelatoriosServicoLocal relatorioService;

	public void rodar(Integer numeroCooperativa) throws BancoobException {

		InstituicaoIntegracaoDTO instituicao = obtemInstituicacao(numeroCooperativa);
		Date dataProduto = obtemDataProduto(instituicao.getIdInstituicao());

		List<T> lancamentos = obtemLancamentos(instituicao.getIdInstituicao(), dataProduto);
		
		if (lancamentos == null || lancamentos.isEmpty()) {
			return;
		}
		
		emiteRelatorio(numeroCooperativa, dataProduto, instituicao, lancamentos);
	}

	protected abstract String obtemCodigoRelatorio();

	protected abstract String obtemTemplateRelatorio();

	protected abstract List<T> obtemLancamentos(Integer instituicaoID, Date dataProduto) throws BancoobException;

	private void emiteRelatorio(Integer numeroCooperativa, Date dataProduto, InstituicaoIntegracaoDTO instituicao, List<T> lancamentos)
			throws BancoobException {

		Map<String, Object> mapaParametros = MapaParametrosUtil.criarParametros(dataProduto, instituicao);
		relatorioService.geraJasperPrintAPartirDeCollection(obtemTemplateRelatorio(), obtemCodigoRelatorio(), lancamentos, mapaParametros,
				numeroCooperativa);
	}

	private Date obtemDataProduto(Integer idInstituicao) throws BancoobException {

		return produtoLegadoService.obterDataAnteriorProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, idInstituicao);
	}

	private InstituicaoIntegracaoDTO obtemInstituicacao(Integer numeroCooperativa) throws BancoobException {

		return integracaoService.obterInstituicaoIntegracaoPorNumCoop(numeroCooperativa);
	}

}

package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.enums.EnumOrdenacaoSaldoAtual;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSaldoAtualDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelSaldoContaCapitalServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelSaldoContaCapitalServicoRemote;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelatoriosServicoLocal;
import br.com.sicoob.cca.relatorios.persistencia.dao.ContaCapitalRelatoriosDaoFactory;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelSaldoAtualDao;
import br.com.sicoob.cca.relatorios.util.MapaParametrosUtil;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
* @author Ricardo.Barcante
*/
@Stateless
@Local(FechRelSaldoContaCapitalServicoLocal.class)
@Remote(FechRelSaldoContaCapitalServicoRemote.class)
public class FechRelSaldoContaCapitalServicoEJB extends ContaCapitalRelatoriosServicoEJB implements FechRelSaldoContaCapitalServicoLocal, FechRelSaldoContaCapitalServicoRemote {

	@Dao(entityManager = "em", fabrica = ContaCapitalRelatoriosDaoFactory.class)
	private RelSaldoAtualDao relSaldoAtualDao;

	@EJB
	private ProdutoLegadoServicoLocal produtoLegadoServicoLocal;

	@EJB
	private FechRelatoriosServicoLocal fechRelatoriosServicoLocal;
	
	@EJB
	private ContaCapitalServicoLocal contaCapitalServicoLocal;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal integracaoServicoLocal;
	
	public void rodar(Integer numCoop) throws BancoobException {
		final String COD_RELATORIO = "CCA006";
		final String RELATORIO_SALDO_CONTA_CAPITAL = "CCA_Relatorio_Saldo_Atual.jasper";

		InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = integracaoServicoLocal.obterInstituicaoIntegracaoPorNumCoop(numCoop);
		Integer idInstituicao = instituicaoIntegracaoDTO.getIdInstituicao();

		Integer numContaCapitalInicial = contaCapitalServicoLocal.obterMenorContaCapitalPorInstituicao(idInstituicao);
		Integer numContaCapitalFinal = contaCapitalServicoLocal.obterMaiorContaCapitalPorInstituicao(idInstituicao);
		Date dataProduto = produtoLegadoServicoLocal.obterDataAnteriorProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL,
				idInstituicao);

		List<RelSaldoAtualDTO> collectionDtos = null;
		RelSaldoAtualDTO filtro = new RelSaldoAtualDTO();

		filtro.setIdInstituicao(idInstituicao);
		filtro.setNumContaCapitalInicial(numContaCapitalInicial);
		filtro.setNumContaCapitalFinal(numContaCapitalFinal);

		filtro.setDataAtualProduto(dataProduto);
		filtro.setAgruparPorPA(false);
		filtro.setOrdenacao(1);

		collectionDtos = relSaldoAtualDao.pesquisarSaldoAtual(filtro);

		if (collectionDtos == null || collectionDtos.isEmpty())
		{
			return;
		}

		Map<String, Object> mapaParametros = MapaParametrosUtil.criarParametros(dataProduto, instituicaoIntegracaoDTO);
		mapaParametros = setaParametrosDoRelatorio(mapaParametros);
		mapaParametros = setaDadosDoProduto(mapaParametros, numContaCapitalInicial, numContaCapitalFinal);

		fechRelatoriosServicoLocal.geraJasperPrintAPartirDeCollection(RELATORIO_SALDO_CONTA_CAPITAL, COD_RELATORIO, collectionDtos,
				mapaParametros, numCoop);
	}
	
	private Map<String, Object> setaDadosDoProduto(Map<String, Object> mapaParametros, Integer numContaCapitalInicial, Integer numContaCapitalFinal) throws BancoobException{
	    mapaParametros.put("numContaCapitalInicial", numContaCapitalInicial);
	    mapaParametros.put("numContaCapitalFinal", numContaCapitalFinal);
	    
	    return mapaParametros;
	}
	
	private Map<String, Object> setaParametrosDoRelatorio(Map<String, Object> mapaParametros) throws BancoobException{
		mapaParametros.put("filtroOrdenacao", EnumOrdenacaoSaldoAtual.CONTACAPITAL.getDescricaoRelatorio());
	    mapaParametros.put("COD_RELATORIO", "CCA - 006");
	    mapaParametros.put("AGRUPAR_PA", false);		
	    
	    return mapaParametros;
	}
}
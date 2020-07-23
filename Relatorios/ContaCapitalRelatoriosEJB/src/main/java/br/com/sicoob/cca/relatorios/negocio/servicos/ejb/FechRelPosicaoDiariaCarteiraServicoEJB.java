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
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.relatorios.negocio.dto.FechRelPosicaoDiariaCarteiraDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelPosicaoDiariaCarteiraServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelPosicaoDiariaCarteiraServicoRemote;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelatoriosServicoLocal;
import br.com.sicoob.cca.relatorios.persistencia.dao.ContaCapitalRelatoriosDaoFactory;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.FechRelPosicaoDiariaCarteiraDao;
import br.com.sicoob.cca.relatorios.util.MapaParametrosUtil;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
* @author Ricardo.Barcante
*/
@Stateless
@Local(FechRelPosicaoDiariaCarteiraServicoLocal.class)
@Remote(FechRelPosicaoDiariaCarteiraServicoRemote.class)
public class FechRelPosicaoDiariaCarteiraServicoEJB extends ContaCapitalRelatoriosServicoEJB implements FechRelPosicaoDiariaCarteiraServicoLocal, FechRelPosicaoDiariaCarteiraServicoRemote {
	
	@Dao(entityManager = "em", fabrica = ContaCapitalRelatoriosDaoFactory.class)
	private FechRelPosicaoDiariaCarteiraDao fechRelPosicaoDiariaCarteiraDao;

	@EJB
	InstituicaoIntegracaoServicoLocal integracaoServicoLocal;
	
	@EJB
	ProdutoLegadoServicoLocal produtoLegadoServicoLocal;
	
	@EJB
	FechRelatoriosServicoLocal fechRelatoriosServicoLocal;
	
	public void rodar(Integer numCoop) throws BancoobException {
		final String COD_RELATORIO = "CCA018";
		final String RELATORIO_SALDO_CONTA_CAPITAL = "CCA_Relatorio_Posicao_Diaria_Carteira.jasper";

		InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = integracaoServicoLocal.obterInstituicaoIntegracaoPorNumCoop(numCoop);
		Integer idInstituicao = instituicaoIntegracaoDTO.getIdInstituicao();

		Date dataProduto = produtoLegadoServicoLocal.obterDataAnteriorProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL,
				idInstituicao);

		List<FechRelPosicaoDiariaCarteiraDTO> collectionDtos = new ArrayList<FechRelPosicaoDiariaCarteiraDTO>();
		FechRelPosicaoDiariaCarteiraDTO filtro = new FechRelPosicaoDiariaCarteiraDTO();

		filtro.setIdInstituicao(idInstituicao);
		filtro.setDataAtualProduto(dataProduto);

		collectionDtos.add(fechRelPosicaoDiariaCarteiraDao.pesquisarPosicaoDiariaCarteira(filtro));

		if (collectionDtos.isEmpty() || collectionDtos.get(0) == null)
		{
			throw new NegocioException("MSG_NOVA_RELATORIO_SEM_REGISTROS");
		}

		Map<String, Object> mapaParametros = MapaParametrosUtil.criarParametros(dataProduto, instituicaoIntegracaoDTO);
		mapaParametros = setaParametrosDoRelatorio(mapaParametros);

		fechRelatoriosServicoLocal.geraJasperPrintAPartirDeCollection(RELATORIO_SALDO_CONTA_CAPITAL, COD_RELATORIO, collectionDtos,
				mapaParametros, numCoop);
	}

	private Map<String, Object> setaParametrosDoRelatorio(Map<String, Object> mapaParametros) throws BancoobException{
	    mapaParametros.put("COD_RELATORIO", "CCA - 018");
	    
	    return mapaParametros;
	}

}
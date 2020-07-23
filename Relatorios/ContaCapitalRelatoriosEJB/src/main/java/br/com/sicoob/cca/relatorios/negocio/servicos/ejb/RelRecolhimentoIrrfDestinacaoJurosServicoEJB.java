package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.enums.EnumOrdenacaoRecolhimentoIrrfDestinacaoJuros;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.dto.RelRecolhimentoIrrfDestinacaoJurosDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelRecolhimentoIrrfDestinacaoJurosServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelRecolhimentoIrrfDestinacaoJurosServicoRemote;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RecolhimentoIrrfDestinacaoJurosLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.RecolhimentoIrrfDestinacaoJurosLegadoServicoLocal;

@Stateless
@Local(RelRecolhimentoIrrfDestinacaoJurosServicoLocal.class)
@Remote(RelRecolhimentoIrrfDestinacaoJurosServicoRemote.class)
public class RelRecolhimentoIrrfDestinacaoJurosServicoEJB extends ContaCapitalRelatoriosServicoEJB implements RelRecolhimentoIrrfDestinacaoJurosServicoLocal, RelRecolhimentoIrrfDestinacaoJurosServicoRemote {

	/** The Constant PARAM_COD_RELATORIO. */
	private static final String PARAM_COD_RELATORIO = "COD_RELATORIO";
	
	/** The Constant REL_RECOLHIMENTO_IRRF_DESTINACAO_JUROS. */
	private static final String REL_RECOLHIMENTO_IRRF_DESTINACAO_JUROS= "CCA_Relatorio_Recolhimento_Irrf_Destinacao_Juros.jasper";
	
	/** The Constant REL_RECOLHIMENTO_IRRF_DESTINACAO_JUROS. */
	private static final String REL_RECOLHIMENTO_IRRF_DESTINACAO_JUROS_AGRUPADO = "CCA_Relatorio_Recolhimento_Irrf_Destinacao_Juros_Agrupado.jasper";
	
	
	/** The instituicao integracao servico. */
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@EJB
	private RecolhimentoIrrfDestinacaoJurosLegadoServicoLocal recolhimentoIrrfDestinacaoJurosLegadoServico;
	
	@EJB
	private ProdutoLegadoServicoLocal produtoLegadoServico;
	
	@EJB
	private CapesIntegracaoServicoLocal capesIntegracaoServico;

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object gerarRelatorioRecolhimentoIrrfDestinacaoJuros(RelRecolhimentoIrrfDestinacaoJurosDTO dtoEntrada)
			throws BancoobException {
		
		Map<String, Object> parametros = getParametrosComuns();
		adicionarPropriedadesComuns(dtoEntrada.getIdInstituicao(), parametros);
		adicionarFiltro(parametros, dtoEntrada);

		parametros.put(PARAM_COD_RELATORIO, CodigoRelatorio.COD_REL_RECOLHIMENTO_IRRF_DESTINACAO_JUROS);
		
		Date dataAtualProduto = produtoLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, dtoEntrada.getIdInstituicao());
		dtoEntrada.setDataAtualProduto(dataAtualProduto);
		
		String relatorioJRXML = dtoEntrada.getAgruparPorPA() ? REL_RECOLHIMENTO_IRRF_DESTINACAO_JUROS_AGRUPADO : REL_RECOLHIMENTO_IRRF_DESTINACAO_JUROS;
		
		return new RelatorioContaCapitalV2(obterDadosRelatorioRecolhimentoIrrfDestinacaoJuros(dtoEntrada), relatorioJRXML, parametros);
	}
	
	/**
	 * Obter dados relatorio recolhimento irrf e destinacao de juros.
	 *
	 * @param relRecolhimentoIrrfDestinacaoJurosDTO the relatorio recolhimento irrf e destinacao de juros
	 * @return the list
	 * @throws BancoobException the bancoob exception
	 */
	private List<RecolhimentoIrrfDestinacaoJurosLegadoDTO> obterDadosRelatorioRecolhimentoIrrfDestinacaoJuros(RelRecolhimentoIrrfDestinacaoJurosDTO relRecolhimentoIrrfDestinacaoJurosDTO) throws BancoobException {
		RecolhimentoIrrfDestinacaoJurosLegadoDTO destinacaoJurosLegadoDTO = relRecolhimentoIrrfDestinacaoJurosDTO.createDtoLegado();
		
		List<RecolhimentoIrrfDestinacaoJurosLegadoDTO> dadosRelatorio = recolhimentoIrrfDestinacaoJurosLegadoServico
				.gerarRelatorioRecolhimentoIrrfDestinacaoJuros(destinacaoJurosLegadoDTO);
		
		if (dadosRelatorio.isEmpty()) {
			throw new NegocioException("MSG_NOVA_RELATORIO_SEM_REGISTROS");
		}
		
		return dadosRelatorio;
	}
	
	/**
	 * Adicionar propriedades comuns.
	 * 
	 * @param idInstituicao the id instituicao
	 * @param parametros the parametros
	 * @throws BancoobException the bancoob exception
	 */
	private void adicionarPropriedadesComuns(Integer idInstituicao, Map<String, Object> parametros) throws BancoobException {
		InstituicaoIntegracaoDTO dtoInstituicaoIntegracao = instituicaoIntegracaoServico.obterInstituicaoIntegracao(idInstituicao);
		parametros.put("DESC_SIGLA_COOP", dtoInstituicaoIntegracao.getNumero() + " - " + dtoInstituicaoIntegracao.getDescInstituicao());
	}
	
	/**
	 * Adicionar filtro.
	 *
	 * @param parametros the parametros
	 * @param dtoEntrada the dto entrada
	 * @throws BancoobException the bancoob exception
	 */
	private void adicionarFiltro(Map<String, Object> parametros, RelRecolhimentoIrrfDestinacaoJurosDTO dtoEntrada) throws BancoobException {		
		parametros.put("filtroIdInstituicao", dtoEntrada.getIdInstituicao());
		
		parametros.put("filtroTodos", dtoEntrada.getTodos());
		parametros.put("filtroNumContaCapital", dtoEntrada.getNumContaCapital());
		
		parametros.put("filtroAnoBase", dtoEntrada.getAnoBase());
		parametros.put("filtroNumPac", dtoEntrada.getNumPac());
		
		parametros.put("filtroAgruparPorPA", dtoEntrada.getAgruparPorPA());
		
		parametros.put("filtroOrdenacao", EnumOrdenacaoRecolhimentoIrrfDestinacaoJuros.buscarPorCodigo(dtoEntrada.getOrdenacao()).getDescricaoRelatorio());
	}
}

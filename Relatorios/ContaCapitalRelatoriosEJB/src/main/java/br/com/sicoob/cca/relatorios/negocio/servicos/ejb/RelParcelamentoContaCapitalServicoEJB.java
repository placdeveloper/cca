package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumOrdenacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoParcelaNovo;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoParcelamento;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.dto.RelParcelamentoContaCapitalDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelParcelamentoContaCapitalServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelParcelamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelParcelamentoContaCapitalServicoRemote;
import br.com.sicoob.cca.relatorios.persistencia.dao.ContaCapitalRelatoriosDaoFactory;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelParcelamentoContaCapitalDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;

/**
 * Servico para geracao dos relatorios de parcelamento de conta capital
 * @author Marco.Nascimento
 */
@Stateless
@Local(RelParcelamentoContaCapitalServicoLocal.class)
@Remote(RelParcelamentoContaCapitalServicoRemote.class)
public class RelParcelamentoContaCapitalServicoEJB extends ContaCapitalRelatoriosServicoEJB implements RelParcelamentoContaCapitalServicoLocal, RelParcelamentoContaCapitalServicoRemote {

	/** The Constant PARAM_COD_RELATORIO. */
	private static final String PARAM_COD_RELATORIO = "COD_RELATORIO";
	
	/** The Constant REL_PARCELAMENTO_CCA. */
	private static final String REL_PARCELAMENTO_CCA = "CCA_Relatorio_Parcelamento.jasper";
	
	private static final String REL_PARCELAMENTO_CCA_AGRUPADO = "CCA_Relatorio_Parcelamento_Agrupado.jasper";
	
	/** The Constant ORDENACAO_POR_NOME. */
	private static final Integer ORDENACAO_POR_NOME = 2;
	
	/** The Constant ORDENACAO_POR_CONTA_CAPITAL. */
	private static final Integer ORDENACAO_POR_CONTA_CAPITAL = 1;
	
	/** The instituicao integracao servico. */
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	/** The rel parcelamento conta capital dao. */
	@Dao(entityManager = "em", fabrica = ContaCapitalRelatoriosDaoFactory.class)
	private RelParcelamentoContaCapitalDao relParcelamentoContaCapitalDao;
	
	/**
	 * {@link RelParcelamentoContaCapitalServico#gerarRelatorioParcelamentoContaCapital(RelParcelamentoContaCapitalDTO)}
	 */
	@SuppressWarnings("unchecked")
	public Object gerarRelatorioParcelamentoContaCapital(RelParcelamentoContaCapitalDTO dtoEntrada) throws BancoobException {
		
		Map<String, Object> parametros = getParametrosComuns();
		adicionarPropriedadesComuns(dtoEntrada.getIdInstituicao(), parametros);
		adicionarFiltro(parametros, dtoEntrada);
		
		parametros.put(PARAM_COD_RELATORIO, CodigoRelatorio.COD_REL_PARCELAMENTO);
		
		String relatorioJRXML = dtoEntrada.getOrdenacao() == ORDENACAO_POR_CONTA_CAPITAL || dtoEntrada.getOrdenacao() == ORDENACAO_POR_NOME ? REL_PARCELAMENTO_CCA_AGRUPADO : REL_PARCELAMENTO_CCA;
	
		return new RelatorioContaCapitalV2(obterDadosRelatorio(dtoEntrada), relatorioJRXML, parametros);
	}
	
	/**
	 * Obter dados relatorio.
	 *
	 * @param dtoEntrada the dto entrada
	 * @return the list
	 * @throws BancoobException the bancoob exception
	 */
	private List<RelParcelamentoContaCapitalDTO> obterDadosRelatorio(RelParcelamentoContaCapitalDTO dtoEntrada) throws BancoobException {
		List<RelParcelamentoContaCapitalDTO> dadosRelatorio = relParcelamentoContaCapitalDao.pesquisarParcelamentos(dtoEntrada);
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
	private void adicionarFiltro(Map<String, Object> parametros, RelParcelamentoContaCapitalDTO dtoEntrada) throws BancoobException {
		parametros.put("filtroIdInstituicao", dtoEntrada.getIdInstituicao());
		parametros.put("filtroTipoParcelameto", EnumTipoParcelamento.buscarPorCodigo(dtoEntrada.getTipoParcelamento()).getDescricaoRelatorio());
		
		if(dtoEntrada.getFormaParcelamento().intValue() == -1) {
			parametros.put("filtroFormaParcelamento", "Todas Parcelas");
		} else {
			parametros.put("filtroFormaParcelamento", EnumTipoIntegralizacao.buscarPorCodigo(dtoEntrada.getFormaParcelamento()).getDescricao());
		}
		
		parametros.put("filtroNumContaCapitalInicial", dtoEntrada.getNumContaCapitalInicial());
		parametros.put("filtroNumContaCapitalFinal", dtoEntrada.getNumContaCapitalFinal());
		parametros.put("filtroOrdenacao", EnumOrdenacaoParcelamento.buscarPorCodigo(dtoEntrada.getOrdenacao()).getDescricaoRelatorio());
		parametros.put("filtroNumOrdenacao", dtoEntrada.getOrdenacao());
		
		EnumSituacaoParcelaNovo enumSitPar = EnumSituacaoParcelaNovo.buscarPorCodigo(dtoEntrada.getSituacaoParcela()); 
		if(enumSitPar != null) {
			parametros.put("filtroSituacaoParcela", enumSitPar.getDescricao());
		} else {
			parametros.put("filtroSituacaoParcela", "TODOS");
		}
		
		if(dtoEntrada.getNumPA() >= 0 ) {
			parametros.put("filtroNumPA", dtoEntrada.getNumPA().toString());
		} else {
			parametros.put("filtroNumPA", "TODOS");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(dtoEntrada.getDataInicialVencimento() != null) {
			parametros.put("filtroPeriodoInicial", sdf.format(dtoEntrada.getDataInicialVencimento()));
		}
		if(dtoEntrada.getDataInicialVencimento() != null) {
			parametros.put("filtroPeriodoFinal", sdf.format(dtoEntrada.getDataFinalVencimento()));
		}
	}
}
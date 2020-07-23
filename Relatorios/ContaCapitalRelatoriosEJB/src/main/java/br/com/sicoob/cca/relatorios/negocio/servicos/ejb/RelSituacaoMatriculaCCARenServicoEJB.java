package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumOrdenacaoSituacao;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSituacaoMatriculaCCARenDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelSituacaoMatriculaCCARenServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelSituacaoMatriculaCCARenServicoRemote;
import br.com.sicoob.cca.relatorios.persistencia.dao.ContaCapitalRelatoriosDaoFactory;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelSituacaoMatriculaCCARenDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;

/**
 * Servico para geracao dos relatorios de situacao CCA por matrícula.
 *
 * @author Eduardo.Paulo
 */
@Stateless
@Local(RelSituacaoMatriculaCCARenServicoLocal.class)
@Remote(RelSituacaoMatriculaCCARenServicoRemote.class)
public class RelSituacaoMatriculaCCARenServicoEJB extends ContaCapitalRelatoriosServicoEJB implements RelSituacaoMatriculaCCARenServicoLocal, RelSituacaoMatriculaCCARenServicoRemote {

	/** The Constant PARAM_COD_RELATORIO. */
	private static final String PARAM_COD_RELATORIO = "COD_RELATORIO";
	
	private static final String REL_SITUACAO_POR_MATRICULA_CCA_REN = "CCA_Relatorio_Situacao_Matricula.jasper";
	
	/** The instituicao integracao servico. */
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	/** The rel saldo atual dao. */
	@Dao(entityManager = "em", fabrica = ContaCapitalRelatoriosDaoFactory.class)
	private RelSituacaoMatriculaCCARenDao relSituacaoMatriculaCCARenDao;
	
	
	/* (non-Javadoc)
	 * @see br.com.sicoob.cca.relatorios.negocio.servicos.RelSituacaoMatriculaCCARenServico#gerarRelatorio(br.com.sicoob.cca.relatorios.negocio.dto.RelSituacaoMatriculaCCARenDTO)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object gerarRelatorio(RelSituacaoMatriculaCCARenDTO dtoEntrada) throws BancoobException {
		
		Map<String, Object> parametros = getParametrosComuns();
		adicionarPropriedadesComuns(dtoEntrada.getIdInstituicao(), parametros);
		adicionarFiltro(parametros, dtoEntrada);
		// TODO: Ajustar com o código correto do relatório
		parametros.put(PARAM_COD_RELATORIO, CodigoRelatorio.COD_REL_SITUACAO_MATRICULA);
	
		List<RelSituacaoMatriculaCCARenDTO> dados = relSituacaoMatriculaCCARenDao.pesquisarSituacaoCCAPorMatricula(dtoEntrada);
	
		return new RelatorioContaCapitalV2(dados, REL_SITUACAO_POR_MATRICULA_CCA_REN, parametros);
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
	private void adicionarFiltro(Map<String, Object> parametros, RelSituacaoMatriculaCCARenDTO dtoEntrada) throws BancoobException {		
		parametros.put("filtroIdInstituicao", dtoEntrada.getIdInstituicao());
		parametros.put("filtroNumContaCapitalInicial", dtoEntrada.getNumContaCapitalInicial());
		parametros.put("filtroNumContaCapitalFinal", dtoEntrada.getNumContaCapitalFinal());
		parametros.put("filtroIdSituacaoConta", dtoEntrada.getIdSituacaoConta());
		parametros.put("filtroUltimaSituacao", dtoEntrada.getUltimaSituacao());
		parametros.put("filtroCadastrosAprovados", dtoEntrada.getCadastrosAprovados());
		parametros.put("filtroNumPA", dtoEntrada.getNumPA());
		parametros.put("filtroOrdenacao", EnumOrdenacaoSituacao.buscarPorCodigo(dtoEntrada.getOrdenacao()).getDescricaoRelatorio());
	}
}
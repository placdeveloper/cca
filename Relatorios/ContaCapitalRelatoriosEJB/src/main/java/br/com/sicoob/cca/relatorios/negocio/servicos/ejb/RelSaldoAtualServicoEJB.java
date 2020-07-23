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
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.enums.EnumOrdenacaoSaldoAtual;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSaldoAtualDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelSaldoAtualServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelSaldoAtualServicoRemote;
import br.com.sicoob.cca.relatorios.persistencia.dao.ContaCapitalRelatoriosDaoFactory;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelSaldoAtualDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
 * Servico para geracao dos relatorios de saldo atual de conta capital.
 *
 * @author Eduardo.Paulo
 */
@Stateless
@Local(RelSaldoAtualServicoLocal.class)
@Remote(RelSaldoAtualServicoRemote.class)
public class RelSaldoAtualServicoEJB extends ContaCapitalRelatoriosServicoEJB implements RelSaldoAtualServicoLocal, RelSaldoAtualServicoRemote {

	/** The Constant PARAM_COD_RELATORIO. */
	private static final String PARAM_COD_RELATORIO = "COD_RELATORIO";
	
	/** The Constant REL_SALDO_ATUAL_CCA. */
	private static final String REL_SALDO_ATUAL_CCA= "CCA_Relatorio_Saldo_Atual.jasper";	
	
	/** The instituicao integracao servico. */
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@EJB
	private ProdutoLegadoServicoLocal produtoLegadoServico;
	
	@EJB
	private CapesIntegracaoServicoLocal capesIntegracaoServico;
	
	/** The rel saldo atual dao. */
	@Dao(entityManager = "em", fabrica = ContaCapitalRelatoriosDaoFactory.class)
	private RelSaldoAtualDao relSaldoAtualDao;
	
	
	/* (non-Javadoc)
	 * @see br.com.sicoob.cca.relatorios.negocio.servicos.RelSaldoAtualServico#gerarRelatorioSaldoAtual(br.com.sicoob.cca.relatorios.negocio.dto.RelSaldoAtualDTO)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object gerarRelatorioSaldoAtual(RelSaldoAtualDTO dtoEntrada) throws BancoobException {
		
		Map<String, Object> parametros = getParametrosComuns();
		adicionarPropriedadesComuns(dtoEntrada.getIdInstituicao(), parametros);
		adicionarFiltro(parametros, dtoEntrada);
		
		parametros.put(PARAM_COD_RELATORIO, CodigoRelatorio.COD_REL_SALDO_ATUAL);
		
		parametros.put("AGRUPAR_PA", dtoEntrada.getAgruparPorPA());
		
		Date dataAtualProduto = produtoLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, dtoEntrada.getIdInstituicao());
		dtoEntrada.setDataAtualProduto(dataAtualProduto);
		parametros.put("dataProcessamento", dataAtualProduto);
		
		parametros.put("numContaCapitalInicial", dtoEntrada.getNumContaCapitalInicial());
		parametros.put("numContaCapitalFinal", dtoEntrada.getNumContaCapitalFinal());
		
		if(dtoEntrada.getNumPessoaJuridica() != null && dtoEntrada.getNumPessoaJuridica().intValue() > 0) {
			Integer idPessoaJuridica = capesIntegracaoServico.obterIdPessoaPorIdPessoaLegado(dtoEntrada.getNumPessoaJuridica(), dtoEntrada.getIdInstituicao());
			dtoEntrada.setIdPessoaJuridica(idPessoaJuridica);
		}
		
		return new RelatorioContaCapitalV2(obterDadosRelatorioSaldoAtual(dtoEntrada), REL_SALDO_ATUAL_CCA, parametros);
	}
	
	/**
	 * Obter dados relatorio saldo atual.
	 *
	 * @param relSaldoAtualDTO the rel saldo atual DTO
	 * @return the list
	 * @throws BancoobException the bancoob exception
	 */
	private List<RelSaldoAtualDTO> obterDadosRelatorioSaldoAtual(RelSaldoAtualDTO relSaldoAtualDTO) throws BancoobException {
		List<RelSaldoAtualDTO> dadosRelatorio = relSaldoAtualDao.pesquisarSaldoAtual(relSaldoAtualDTO);
		if (dadosRelatorio.isEmpty()) {
			throw new NegocioException("MSG_NOVA_RELATORIO_SEM_REGISTROS");
		}
		
		return dadosRelatorio;
	}
	
	/**
	 * Adicionar propriedades comuns.
	 * b
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
	private void adicionarFiltro(Map<String, Object> parametros, RelSaldoAtualDTO dtoEntrada) throws BancoobException {		
		parametros.put("filtroIdInstituicao", dtoEntrada.getIdInstituicao());
		parametros.put("filtroNumContaCapitalInicial", dtoEntrada.getNumContaCapitalInicial());
		parametros.put("filtroNumContaCapitalFinal", dtoEntrada.getNumContaCapitalFinal());
		parametros.put("filtroIdSituacaoConta", dtoEntrada.getIdSituacaoConta());
		parametros.put("filtroCadastrosAprovados", dtoEntrada.getCadastrosAprovados());
		parametros.put("filtroCnpjEmpresa", dtoEntrada.getCnpjEmpresa());
		parametros.put("filtroDescEmpresa", dtoEntrada.getDescEmpresa());
		parametros.put("filtroNumPA", dtoEntrada.getNumPA());
		parametros.put("filtroAgruparPorPA", dtoEntrada.getAgruparPorPA());
		parametros.put("filtroOrdenacao", EnumOrdenacaoSaldoAtual.buscarPorCodigo(dtoEntrada.getOrdenacao()).getDescricaoRelatorio());
	}

}
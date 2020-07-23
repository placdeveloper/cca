package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.DadosConciliacaoContabilIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.ContabilidadeIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ConciliacaoContabilLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ConciliacaoContabilLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.processamento.util.ProcessamentoUtil;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * @author marco.nascimento@sicoob.com.br
 * @since 04/02/2014
 */
@Stateless
@Remote(StepServico.class)
public class ConciliacaoContabilStepServicoEJB extends ContaCapitalProcessamentoStep  {
	
	private static final ISicoobLogger LOG = getLogger(ConciliacaoContabilStepServicoEJB.class);
	
	@EJB
	private ConciliacaoContabilLegadoServicoLocal conciliacaoContabilLegadoServico;
	
	@EJB
	private ContabilidadeIntegracaoServicoLocal contabilidadeIntegracaoServico;
	
	/**
	 * Unidade instituicao sempre será 0, a conciliacao contabil deve ser feita apenas para Singulares e Centrais
	 */
	private static final Integer UNIDADE_INSTITUICAO = 0;
	
	public RetornoExecucao executar(ContextoExecucao ctx) {
		try {
			
			Integer numCoop = getNumCoop(ctx);
			List<ConciliacaoContabilLegadoDTO> listaLegado = conciliacaoContabilLegadoServico.obterListaDadosConciliacaoContabil(numCoop, null);
		
			List<DadosConciliacaoContabilIntegracaoDTO> lstConciliacao = criarListaConciliacaoContabil(listaLegado, numCoop);
			
			if(CollectionUtils.isNotEmpty(lstConciliacao)) {
				contabilidadeIntegracaoServico.incluirConciliacaoContabil(lstConciliacao);
				
				for(ConciliacaoContabilLegadoDTO dto : listaLegado) {
					conciliacaoContabilLegadoServico.atualizarConciliacaoContabil(numCoop, dto);
				}
			}
			
			return sucesso();
		} catch (BancoobException e) {
			LOG.erro(e, e.getMessage());
			return erro(e.getMessage());
		}
	}
	
	/**
	 * Preenche DadosConciliacaoContabilIntegracaoDTO com dados do legado (SQL SERVER)
	 * @param listaLegado
	 * @param ctx
	 * @return
	 * @throws BancoobException
	 */
	private List<DadosConciliacaoContabilIntegracaoDTO> criarListaConciliacaoContabil(List<ConciliacaoContabilLegadoDTO> listaLegado, Integer numCoop) throws BancoobException {
		List<DadosConciliacaoContabilIntegracaoDTO> lista = new ArrayList<DadosConciliacaoContabilIntegracaoDTO>(0);
		DadosConciliacaoContabilIntegracaoDTO dcci = null;
		Integer idInstituicao = ProcessamentoUtil.getIdInstituicao(numCoop);
		for(ConciliacaoContabilLegadoDTO ccl : listaLegado) {
			dcci = new DadosConciliacaoContabilIntegracaoDTO();
			dcci.setIdInstituicao(idInstituicao);
			dcci.setBolContabilizado(false);
			dcci.setIdProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
			dcci.setIdUnidadeInstituicao(UNIDADE_INSTITUICAO);
			dcci.setDataContabilizacao(new DateTimeDB(ccl.getDataContabilizacao().getTime()));
			dcci.setNumConta(ccl.getNumContaBACEN());
			dcci.setValorSaldo(ccl.getSaldoTotal());
			dcci.setCodRelatorio("CCA018");
			lista.add(dcci);
		}
		return lista;
	}
	
}
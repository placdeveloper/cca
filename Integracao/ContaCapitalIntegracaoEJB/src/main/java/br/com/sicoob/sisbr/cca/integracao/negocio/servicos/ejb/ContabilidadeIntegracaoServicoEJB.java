package br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.ctb.api.integracao.negocio.delegates.ContabilidadeIntegracaoFabricaDelegates;
import br.com.sicoob.ctb.api.integracao.negocio.vo.DadosConciliacaoContabilVO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.DadosConciliacaoContabilIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoContabilidadeException;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.ContabilidadeIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.ContabilidadeIntegracaoServicoRemote;

/**
 * EJB contendo servicos relacionados a ContabilidadeIntegracao.
 */
@Stateless
@Local (ContabilidadeIntegracaoServicoLocal.class)
@Remote(ContabilidadeIntegracaoServicoRemote.class)
public class ContabilidadeIntegracaoServicoEJB extends ContaCapitalIntegracaoServicoEJB implements ContabilidadeIntegracaoServicoLocal, ContabilidadeIntegracaoServicoRemote {

	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ContabilidadeIntegracaoServico#incluirConciliacaoContabil(java.util.List)
	 */
	public void incluirConciliacaoContabil(List<DadosConciliacaoContabilIntegracaoDTO> lst) throws BancoobException {

		this.getLogger().info("CCA.incluirConciliacaoContabil");
		
		try {			
			List<DadosConciliacaoContabilVO> lista = new ArrayList<DadosConciliacaoContabilVO>();		
			for (DadosConciliacaoContabilIntegracaoDTO dto : lst) {
				DadosConciliacaoContabilVO dtoCC = new DadosConciliacaoContabilVO();
				dtoCC.setBolContabilizado(dto.getBolContabilizado());
				dtoCC.setCodRelatorio(dto.getCodRelatorio());
				dtoCC.setDataContabilizacao(new Date(dto.getDataContabilizacao().getTime()));
				dtoCC.setDescTipoSaldoConciliacao(dto.getDescTipoSaldoConciliacao());
				dtoCC.setIdInstituicao(dto.getIdInstituicao());
				dtoCC.setIdProduto(dto.getIdProduto());
				dtoCC.setIdUnidadeInstituicao(dto.getIdUnidadeInstituicao());
				dtoCC.setNumConta(dto.getNumConta());
				dtoCC.setValorSaldo(dto.getValorSaldo());
				lista.add(dtoCC);
			}
			
			ContabilidadeIntegracaoFabricaDelegates.getInstance().criarConciliacaoContabilIntegracaoDelegate().incluirDadosConciliacaoContabil(lista);
			
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());			
			throw new IntegracaoContabilidadeException(e);			
		}
	
	}	
	
}

package br.com.sicoob.cca.movimentacao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.DebitoContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.ConsultaDebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.DebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.QuadroGeralAssociadoDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.DebitoIndeterminadoServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

/**
 * Delegate de Debito Indeterminado
 * 
 * @author marco.nascimento
 */
public class DebitoIndeterminadoDelegate extends ContaCapitalMovimentacaoDelegate<DebitoIndeterminadoServico>{

	/**
	 * Instancia um novo DebitoIndeterminadoDelegate.
	 */
	DebitoIndeterminadoDelegate() {
		
	}

	/**
	 * Locator DebitoIndeterminadoDelegate.
	 *
	 * @return DebitoIndeterminadoServico
	 */
	@Override
	protected DebitoIndeterminadoServico localizarServico() {
		return (DebitoIndeterminadoServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarDebitoIndeterminadoServico();
	}
	
	/**
	 * {@link DebitoIndeterminadoServico#pesquisarQuadroGeralAssociados()}
	 */
	public List<QuadroGeralAssociadoDTO> pesquisarQuadroGeralAssociados() throws BancoobException {
		return getServico().pesquisarQuadroGeralAssociados();
	}
	
	/**
	 * {@link DebitoIndeterminadoServico#pesquisarQtdDebCCODiaFixo()}
	 */
	public List<QuadroGeralAssociadoDTO> pesquisarQtdDebCCODiaFixo() throws BancoobException {
		return getServico().pesquisarQtdDebCCODiaFixo();
	}

	/**
	 * {@link DebitoIndeterminadoServico#pesquisarQtdDebCCOIntervalo()}
	 */
	public List<QuadroGeralAssociadoDTO> pesquisarQtdDebCCOIntervalo() throws BancoobException {
		return getServico().pesquisarQtdDebCCOIntervalo();
	}

	/**
	 * {@link DebitoIndeterminadoServico#pesquisarQtdDebFolhaBanco()}
	 */
	public List<QuadroGeralAssociadoDTO> pesquisarQtdDebFolhaBanco() throws BancoobException {
		return getServico().pesquisarQtdDebFolhaBanco();
	}
	
	/**
	 * {@link DebitoIndeterminadoServico#pesquisar(ConsultaDebitoIndeterminadoRenDTO)}
	 */
	public List<ConsultaDebitoIndeterminadoRenDTO> pesquisar(ConsultaDebitoIndeterminadoRenDTO filtro) throws BancoobException {
		return getServico().pesquisar(filtro);
	}
	
	/**
	 * {@link DebitoIndeterminadoServico#incluirDebIndividual(DebitoIndeterminadoRenDTO)}
	 */
	public void incluirDebIndividual(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		getServico().incluirDebIndividual(dto);
	}
	
	/**
	 * {@link DebitoIndeterminadoServico#incluirDebEmLote(DebitoIndeterminadoRenDTO)}
	 */
	public void incluirDebEmLote(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		getServico().incluirDebEmLote(dto);
	}
	
	/**
	 * {@link DebitoIndeterminadoServico#alterarDebIndividual(DebitoIndeterminadoRenDTO)}
	 */
	public void alterarDebIndividual(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		getServico().alterarDebIndividual(dto);
	}
	
	/**
	 * {@link DebitoIndeterminadoServico#alterarDebEmLote(DebitoIndeterminadoRenDTO, Integer)}
	 */
	public void alterarDebEmLote(DebitoIndeterminadoRenDTO dto, Integer tipoAlteracao) throws BancoobException {
		getServico().alterarDebEmLote(dto, tipoAlteracao);
	}
	
	/**
	 * {@link DebitoIndeterminadoServico#pesquisar(Long)}
	 */
	public DebitoContaCapital pesquisar(Long idDebitoContaCapital) throws BancoobException {
		return getServico().pesquisar(idDebitoContaCapital);
	}
	
	/**
	 * {@link DebitoIndeterminadoServico#pesquisar(Long)}
	 * @param idInstituicao 
	 */
	public DebitoContaCapital pesquisarPorIdContaCapital(Integer idContaCapital, Integer idInstituicao) throws BancoobException {
		return getServico().pesquisarPorIdContaCapital(idContaCapital, idInstituicao);
	}
	
	/**
	 * {@link DebitoIndeterminadoServico#pesquisarAssociadosSemDebito(ConsultaDebitoIndeterminadoRenDTO)}
	 */
	public List<ConsultaDebitoIndeterminadoRenDTO> pesquisarAssociadosSemDebito(ConsultaDebitoIndeterminadoRenDTO filtro) throws BancoobException {
		return getServico().pesquisarAssociadosSemDebito(filtro);
	}
	
	/**
	 * {@link DebitoIndeterminadoServico#excluirDebEmLote(DebitoIndeterminadoRenDTO)}
	 */
	public void excluirDebEmLote(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		getServico().excluirDebEmLote(dto);
	}
}
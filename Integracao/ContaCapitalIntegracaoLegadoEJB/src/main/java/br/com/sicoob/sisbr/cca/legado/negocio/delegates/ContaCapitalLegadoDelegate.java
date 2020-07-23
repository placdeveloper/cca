/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosRelExtratoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosRelFichaMatriculaDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RelExtratoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * A Classe ContaCapitalLegadoDelegate.
 */
public class ContaCapitalLegadoDelegate extends	ContaCapitalIntegracaoLegadoCrudDelegate<ContaCapitalLegado, ContaCapitalLegadoServico> {

	
	/**
	 * Recupera a unica instancia de ContaCapitalLegadoDelegate.
	 *
	 * @return uma instancia de ContaCapitalLegadoDelegate
	 */
	public static ContaCapitalLegadoDelegate getInstance(){
		return new ContaCapitalLegadoDelegate();
	}
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected ContaCapitalLegadoServico localizarServico() {
		return (ContaCapitalLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarContaCapitalLegadoServico();
	}

	/**
	 * Consulta a entidade ContaCapital através do numero da Pessoa no Legado
	 * @param numCliente (Numero da pessoa no legado)
	 * @return
	 * @throws BancoobException
	 */
	public List<ContaCapitalLegado> obterContaCapital(Integer numCliente) throws BancoobException{
		return getServico().obterContaCapital(numCliente);
	}
		
	/**
	 * Consulta a ultima matricula de conta capital no legado
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterUltimaMatricula() throws BancoobException{
		return getServico().obterUltimaMatricula();
	}
	
	/**
	 * Verifica se o cliente ja tem conta capital cadastrada
	 * @return
	 * @throws BancoobException
	 */
	public Boolean verificaClienteCadastrado(Integer numCliente) throws BancoobException{
		return getServico().verificarClienteCadastrado(numCliente);
	}	
	
	
	/**
	 * 
	 * @param idPessoa
	 * @param iAplicCoopDif
	 * @return
	 * @throws BancoobException
	 */
	public List<DadosRelFichaMatriculaDTO> listarRelFichaAdmissao(Integer idPessoa, Integer iAplicCoopDif) throws BancoobException {
		return getServico().listarRelFichaAdmissao(idPessoa, iAplicCoopDif);
	}
	
	/**
	 * 
	 * @param matricula
	 * @param iAplicCoopDif
	 * @return
	 * @throws BancoobException
	 */
	public List<DadosRelFichaMatriculaDTO> listarRelFicha(Integer matricula, Integer iAplicCoopDif) throws BancoobException {
		return getServico().listarRelFicha(matricula, iAplicCoopDif);
	}
	
	/**
	 * 
	 * @param RelExtratoLegadoDTO
	 * @return
	 * @throws BancoobException
	 */
	public List<DadosRelExtratoLegadoDTO> listarRelExtrato(RelExtratoLegadoDTO relExtratoDTO) throws BancoobException {
		return getServico().listarRelExtrato(relExtratoDTO);
	}
	
	/**
	 * 
	 * @param numCoop
	 * @param numMatricula
	 * @return
	 * @throws BancoobException
	 */
	public Boolean verificarContaCapitalCadastrada(Integer numCoop, Integer numMatricula) throws BancoobException {
		return getServico().verificarContaCapitalCadastrada(numCoop, numMatricula);
	}

	/**
	 * Exclui a conta capital e historico
	 * @param numMatricula
	 * @param idInstituicao
	 * @throws BancoobException
	 */
	public void excluir(Integer numMatricula, Integer idInstituicao) throws BancoobException {
		getServico().excluir(numMatricula, idInstituicao);
	}
	
	/**
	 * Consulta a ContaCapital através do numero da Pessoa no Legado e da Cooperativa 
	 * @param numCooperativa
	 * @param numCliente
	 * @param situacao (1 = ativo, 2 = desligados, null = todos)
	 * @return
	 * @throws BancoobException
	 */
	public List<ContaCapitalLegado> obterContaCapitalCooperativaCliente(Integer numCooperativa,Integer numCliente,Integer situacao) throws BancoobException {	
		return getServico().obterContaCapitalCooperativaCliente(numCooperativa, numCliente,situacao);
	}		
	
	/**
	 * Obtem nova matricula
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterNovaContaCapital(Integer idInstituicao) throws BancoobException {
		return getServico().obterNovaContaCapital(idInstituicao);
	}
	
	/**
	 * Excluir debito indeterminado por matricula
	 * @param numMatriculas
	 * @return
	 * @throws BancoobException
	 */
	public void excluirDebIndeterminadoEmLote(List<Integer> numMatriculas) throws BancoobException {
		getServico().excluirDebIndeterminadoEmLote(numMatriculas);
	}
	
	/**
	 * Atualiza informacoes sobre debito indeterminado
	 * @param lst
	 * @return
	 * @throws BancoobException
	 */
	public void atualizarDebIndeterminadoEmLote(List<ContaCapitalLegado> lst) throws BancoobException {
		getServico().atualizarDebIndeterminadoEmLote(lst);
	}
}
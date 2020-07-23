/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TrabalhaLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.TrabalhaLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;
import br.com.sicoob.tipos.DateTime;

/**
 * A Classe TrabalhaLegadoDelegate.
 */
public class TrabalhaLegadoDelegate extends	ContaCapitalIntegracaoLegadoDelegate<TrabalhaLegadoServico> {

	/**
	 * Recupera a unica instancia de TrabalhaLegadoDelegate.
	 *
	 * @return uma instancia de TrabalhaLegadoDelegate
	 */
	public static TrabalhaLegadoDelegate getInstance(){
		return new TrabalhaLegadoDelegate();
	}	
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected TrabalhaLegadoServico localizarServico() {
		return (TrabalhaLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarTrabalhaLegadoServico();
	}
	
	/**
	 * Obter dados trabalha.
	 *
	 * @param numPessoa o valor de num pessoa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<TrabalhaLegadoDTO> obterDadosTrabalha(Integer numPessoa) throws BancoobException{
		return getServico().obterDadosTrabalha(numPessoa);
	}
	
	/**
	 * Obter dados trabalha por matricula.
	 *
	 * @param descMatriculaFunc o valor de desc matricula func
	 * @return TrabalhaLegadoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public TrabalhaLegadoDTO obterDadosTrabalhaPorMatricula(String descMatriculaFunc) throws BancoobException{
		return getServico().obterDadosTrabalhaPorMatricula(descMatriculaFunc);
	}
	
	/**
	 * Verifica se deb ind folha cliente.
	 *
	 * @param numMatricula o valor de num matricula
	 * @param numCliente o valor de num cliente
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Boolean verificaSeDebIndFolhaCliente(Integer numMatricula, Integer numCliente)  throws BancoobException{
		return getServico().verificaSeDebIndFolhaCliente(numMatricula, numCliente);
	}
	
	/**
	 * Verificar parcela via folha cliente.
	 *
	 * @param numMatricula o valor de num matricula
	 * @param numCliente o valor de num cliente
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Boolean verificarParcelaViaFolhaCliente(Integer numMatricula, Integer numCliente)  throws BancoobException{
		return getServico().verificarParcelaViaFolhaCliente(numMatricula, numCliente);
	}
	
	/**
	 * Verificar se prep remessa.
	 *
	 * @param strUIDTrabalha o valor de str uid trabalha
	 * @param dataReferencia o valor de data referencia
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Boolean verificarSePrepRemessa(String strUIDTrabalha, DateTime dataReferencia)  throws BancoobException{
		return getServico().verificarSePrepRemessa(strUIDTrabalha, dataReferencia);
	}	
	
	/**
	 * Verificar se matricula trabalha valida.
	 *
	 * @param strUIDTrabalha o valor de str uid trabalha
	 * @param numCliente o valor de num cliente
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Boolean verificarSeMatriculaTrabalhaValida(String strUIDTrabalha, Integer numCliente) throws BancoobException {
		return getServico().verificarSeMatriculaTrabalhaValida(strUIDTrabalha, numCliente);
	}
	
}

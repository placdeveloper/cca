package br.com.sicoob.sisbr.cca.api.negocio.servicos.ejb;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.servicos.ejb.BancoobServicoEJB;
import br.com.sicoob.sisbr.cca.api.negocio.excecao.APIContaCapitalNegocioException;
import br.com.sicoob.sisbr.cca.api.negocio.excecao.IntegralizacaoCapitalNegocioException;
import br.com.sicoob.sisbr.cca.api.negocio.excecao.LancamentoBoletoFAPPagoNegocioException;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.APIContaCapitalServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.CapesIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.TipoGrauCooperativaDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;

/**
 * Implementacao base de todos os servicos do sistema APIContaCapital
 * 
 */
public abstract class APIContaCapitalServicoEJB extends BancoobServicoEJB
		implements APIContaCapitalServico {

	/**
	 * Método responsável por obter o número da cooperativa através do idInstituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	protected Integer obterNumeroCooperativa(Integer idInstituicao) throws BancoobException {
		// Integracao com o ContaCapitalIntegracao
		InstituicaoIntegracaoDelegate instituicaoIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();
		// Recupera o numero da cooperativa a partir do idInstituicao
		return instituicaoIntegracaoDelegate.obterNumeroCooperativa(idInstituicao);
	}
	
	/**
	 * Consulta Informacoes de Pessoa no Capes (PessoaIntegracaoDTO)
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	protected PessoaIntegracaoDTO obterPessoaInstituicao(Integer idPessoa,Integer idInstituicao) throws BancoobException {
		// Integracao com o ContaCapitalIntegracao		
		CapesIntegracaoDelegate capesIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarCapesIntegracaoDelegate();
		//Recupera um DTO com informacoes basicas do CAPES 
		return capesIntegracaoDelegate.obterPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Consulta o idpessoalegado na integracao com o Capes (PessoaIntegracaoDTO)
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	protected Integer obterIdPessoaLegado(Integer idPessoa,Integer idInstituicao) throws BancoobException {
		return obterPessoaInstituicao(idPessoa, idInstituicao).getIdPessoaLegado();
	}	
	
	/**
	 * Consulta o grau da cooperativa a partir do numero da instituicao 	
	 * @param idCooperativa
	 * @return
	 * @throws BancoobException
	 */
	protected TipoGrauCooperativaDTO obterTipoGrauCooperativa(Integer idInstituicao) throws BancoobException{
		InstituicaoIntegracaoDelegate instituicaoIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();		
		return instituicaoIntegracaoDelegate.obterTipoGrauCooperativa(idInstituicao);
	}	
	
	
	/**
	 * Consulta as informações de conta capital e retorna uma lista com a Entidade ContaCapitalLegado
	 * @param idPessoaLegado
	 * @return
	 * @throws BancoobException
	 * @deprecated {@link #obterContaCapitalCooperativaCliente(Integer, Integer, Integer)}
	 */
	@Deprecated
	protected List<ContaCapitalLegado> obterContaCapital(Integer idPessoaLegado) throws BancoobException{
		ContaCapitalLegadoDelegate contaCapitalLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarContaCapitalLegadoDelegate();
		return contaCapitalLegadoDelegate.obterContaCapital(idPessoaLegado);
	}
	
	/**
	 * Consulta a ContaCapital através do numero da Pessoa no Legado e da Cooperativa
	 * @param numCooperativa
	 * @param numCliente
	 * @param situacao (1 = ativo, 2 = desligados, null = todos)
	 * @return
	 * @throws BancoobException
	 */
	protected List<ContaCapitalLegado> obterContaCapitalCooperativaCliente(Integer numCooperativa,Integer numCliente, Integer situacao) throws BancoobException{
		ContaCapitalLegadoDelegate contaCapitalLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarContaCapitalLegadoDelegate();
		return contaCapitalLegadoDelegate.obterContaCapitalCooperativaCliente(numCooperativa, numCliente,situacao);
	}
		
	/**
	 * Lanca a excecao {@link IntegralizacaoCapitalNegocioException}
	 * @param mensagem
	 * @throws IntegralizacaoCapitalNegocioException
	 */
	protected void lancarIntegralizacaoCapitalNegocioException(String mensagem) throws IntegralizacaoCapitalNegocioException {
		throw new IntegralizacaoCapitalNegocioException(mensagem);
	}
	
	/**
	 * Lanca a excecao {@link APIContaCapitalNegocioException}
	 * @param mensagem
	 * @throws APIContaCapitalNegocioException
	 */
	protected void lancarAPIContaCapitalNegocioException(String mensagem) throws APIContaCapitalNegocioException {
		throw new APIContaCapitalNegocioException(mensagem);
	}
	
	/**
	 * Lanca a excecao {@link LancamentoBoletoFAPPagoNegocioException}
	 * @param mensagem
	 * @throws LancamentoBoletoFAPPagoNegocioException
	 */
	protected void lancarLancamentoBoletoPagoFAPNegocioException(String mensagem) throws LancamentoBoletoFAPPagoNegocioException {
		throw new LancamentoBoletoFAPPagoNegocioException(mensagem);
	}
	
}

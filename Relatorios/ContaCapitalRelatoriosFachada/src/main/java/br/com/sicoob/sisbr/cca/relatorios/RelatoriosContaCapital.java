/*
 * 
 */
package br.com.sicoob.sisbr.cca.relatorios;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.fachada.BancoobFachada;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.CentralCooperativaDTO;

/**
 * Classe Auxiliar da camada de integração
 * @author Marcos.Balbi
 *
 */
public abstract class RelatoriosContaCapital extends BancoobFachada {

	/**
	 * Carrega um mapa com os dados da central e da cooperativa  
	 * @author Marcos.Balbi  
	 * @throws BancoobException  
	 */			
	protected CentralCooperativaDTO obterCentralCooperativa() throws BancoobException {		
		InstituicaoIntegracaoDelegate delegateIntegracao  = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();		
		Integer idInstituicao =  Integer.valueOf(ContextoHttp.getInstance().recuperarIdInstituicao());

		return delegateIntegracao.consultarCentralCooperativa(idInstituicao);		
	}
				
	/**
	 * Implementar aqui na superclasse quando for usar a primeira
	 */
	//public Date obterDataAtualMovimento() throws BancoobException{	
	//	Produto produto = delegateProduto.obter(Integer.valueOf(String.valueOf(PoupancaConstantes.PRODUTO_POUPANCA)));
	//	return produto.getDataAtualMovimento();
	//}
	
	
	/**
	 * Implementar aqui na superclasse quando for usar a primeira
	 */
	//public Date obterDataAtualProduto() throws BancoobException{
	//	Produto produto = delegateProduto.obter(Integer.valueOf(String.valueOf(PoupancaConstantes.PRODUTO_POUPANCA)));
	//	return produto.getDataAtualProd();
	//}

	
}

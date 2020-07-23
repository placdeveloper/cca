/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;

/**
 * @author Marco.Nascimento
 */
public interface GftIntegracaoServico extends ContaCapitalIntegracaoServico {
	
	/**
	 * Nova Instancia Fluxo de Aprovacao do Conta Capital no GFT
	 * @param gftIntegracaoDTO
	 * @throws BancoobException
	 */
	void instanciarFluxoAprovacao(GftIntegracaoDTO gftIntegracaoDTO) throws BancoobException;
	
	/**
	 * Procedimentos do Fluxo de Aprovacao do Conta Capital
	 * @param gftIntegracaoDTO
	 * @return procedimentos a serem tomados de acordo com o processo definido no ged
	 * @throws BancoobException
	 */
	List<ItemListaIntegracaoDTO> listaProcedimentosAprovacao(GftIntegracaoDTO gftIntegracaoDTO) throws BancoobException;
	
	/**
	 * Segue com andamento do fluxo de aprovacao do conta capital
	 * @param gftIntegracaoDTO
	 * @throws BancoobException
	 */
	void executarAtividadeAprovacao(GftIntegracaoDTO gftIntegracaoDTO) throws BancoobException; 
	
	/**
	 * Lista atividades pendentes do usuario logado
	 * @param gftIntegracaoDTO
	 * @return
	 * @throws BancoobException
	 */
	List<GftIntegracaoDTO> listarAtividadesPendentes(GftIntegracaoDTO gftIntegracaoDTO) throws BancoobException;
	
	/**
	 * Recupera nome do procedimento no GFT
	 * @param gftIntegracaoDTO
	 * @return
	 * @throws BancoobException
	 */
	String recuperarNomeProcedimento(GftIntegracaoDTO gftIntegracaoDTO) throws BancoobException;

	/**
	 * Verifica se o processo esta completo
	 * @param idAtividade
	 * @param idRegistroControlado
	 * @return
	 * @throws BancoobException
	 */
	Boolean isProcessoCompleto(Integer idAtividade, Integer idRegistroControlado) throws BancoobException;
}
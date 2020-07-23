/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;

/**
 * A Interface CadastroContaCapitalRenServico.
 */
public interface CadastroContaCapitalRenServico extends ContaCapitalCadastroServico {

	/**
	 * Obtem nova conta capital (matricula) para a instituicao em questao
	 * @param idInstituicao
	 * @return 
	 * @throws BancoobException
	 */
	Integer obterNovaContaCapital(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Realiza pesquisa de acordo com o filtro informado
	 * @param consultaDTO
	 * @return
	 * @throws BancoobException
	 */
	List<CadastroContaCapitalRenDTO> pesquisar(CadastroContaCapitalRenDTO dto) throws BancoobException;
	
	/**
	 * Cadastros de Conta Capital em fase de aprovacao
	 * @param consultaDTO
	 * @return
	 * @throws BancoobException
	 */
	List<CadastroContaCapitalRenDTO> pesquisarAprovacaoPendente(CadastroContaCapitalRenDTO dto) throws BancoobException;
	
	/**
	 * Realiza inclusão de proposta/conta capital
	 * @param consultaDTO
	 * @return
	 * @throws BancoobException
	 */
	CadastroContaCapitalRenDTO incluir(CadastroContaCapitalRenDTO dto) throws BancoobException;
	
	/**
	 * Realiza alteração de proposta/conta capital
	 * @param consultaDTO
	 * @return
	 * @throws BancoobException
	 */
	CadastroContaCapitalRenDTO alterar(CadastroContaCapitalRenDTO dto) throws BancoobException;

	/**
	 * Realiza exclusao de conta capital
	 * Regras: So podera ser excluida conta capital criada no mesmo dia e que nao tenha subscricao (lancamentos e/ou parcelamentos)
	 * @param idContaCapital
	 * @throws BancoobException
	 */
	void excluir(Integer idContaCapital) throws BancoobException;
}
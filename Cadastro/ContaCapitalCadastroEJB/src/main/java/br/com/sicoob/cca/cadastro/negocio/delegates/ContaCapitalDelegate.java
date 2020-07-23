/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.cadastro.negocio.dto.ContaCapitalResumoDTO;
import br.com.sicoob.cca.cadastro.negocio.servicos.ContaCapitalServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.locator.ContaCapitalCadastroServiceLocator;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;

/**
 * @author Marco.Nascimento
 */
public class ContaCapitalDelegate extends ContaCapitalCadastroCrudDelegate<ContaCapital, ContaCapitalServico> {

	/**
	 * Instancia um novo ContaCapitalDelegate.
	 */
	ContaCapitalDelegate() {
		
	}

	/**
	 * Locator ContaCapitalServico
	 */
	@Override
	protected ContaCapitalServico localizarServico() {
		return (ContaCapitalServico) ContaCapitalCadastroServiceLocator.getInstance().localizarContaCapitalServico();
	}
		
	/**
	 * {@link ContaCapitalServico#atualizarSituacaoCadastro(Integer)}
	 */
	public void atualizarSituacaoCadastro(Integer idContaCapital) throws BancoobException {
		getServico().atualizarSituacaoCadastro(idContaCapital);
	}
	
	/**
	 * {@link ContaCapitalServico#	(Integer)}
	 */
	public void atualizarSituacaoCadastroPorDocumento(Integer idContaCapital) throws BancoobException {
		getServico().atualizarSituacaoCadastroPorDocumento(idContaCapital);
	}
	
	/**
	 * {@link ContaCapitalServico#obterPorInstituicaoMatricula(Integer, Integer)}
	 */
	public ContaCapital obterPorInstituicaoMatricula(Integer idInstituicao,Integer numMatricula) throws BancoobException{
		return getServico().obterPorInstituicaoMatricula(idInstituicao, numMatricula);
	}

	/**
	 * {@link ContaCapitalServico#incluirExterno(ContaCapital)}
	 */	
	public ContaCapital incluirExterno(ContaCapital contaCapital) throws BancoobException{
		return getServico().incluirExterno(contaCapital);
	}
	
	/**
	 * Obtem nova conta capital (matricula) para a instituição em questão
	 * @param idInstituicao
	 * @return 
	 * @throws BancoobException
	 */
	public Integer obterNovaContaCapital(Integer idInstituicao) throws BancoobException{
		return getServico().obterNovaContaCapital(idInstituicao);
	}
	
	
	/**
	 * Pesquisa de acordo com o filtro informado
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public List<CadastroContaCapitalRenDTO> pesquisar(CadastroContaCapitalRenDTO dto) throws BancoobException{
		return getServico().pesquisar(dto);
	}
	
	/**
	 * Pesquisa quantidade de lancamentos existentes para a conta capital
	 * @param idContaCapital
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Integer pesquisarLancamentosContaCapital(Integer idContaCapital, Integer idInstituicao) throws BancoobException{
		return getServico().pesquisarLancamentosContaCapital(idContaCapital, idInstituicao);
	}
	
	/**
	 * Pesquisa quantidade de parcelamentos existentes para a conta capital
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	public Integer pesquisarParcelamentosContaCapital(Integer idContaCapital) throws BancoobException{
		return getServico().pesquisarParcelamentosContaCapital(idContaCapital);
	}
	
	/**
	 * Exclui cadastro de conta capital
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	public void excluir(Integer idContaCapital) throws BancoobException{
		getServico().excluir(idContaCapital);
	}
	
	/**
	 * {@link ContaCapitalServico#obterResumo(Integer)}
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	public ContaCapitalResumoDTO obterResumo(Integer idContaCapital) throws BancoobException {
		return getServico().obterResumo(idContaCapital);
	}

	/**
	 * {@link ContaCapitalServico#obterResumos(Integer)}
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	public List<ContaCapitalResumoDTO> obterResumos(List<Integer> idsContaCapital) throws BancoobException {
		return getServico().obterResumos(idsContaCapital);
	}
	
	/**
	 * Consulta conta capital por pessoa (apenas ativas)
	 * @param lstPessoas
	 * @return
	 * @throws BancoobException
	 */
	public List<CadastroContaCapitalRenDTO> obterContaCapitalPorPessoa(List<PessoaIntegracaoDTO> lstPessoas) throws BancoobException {
		return getServico().obterContaCapitalPorPessoa(lstPessoas);
	}
	
	/**
	 * Obtem maior numero de conta capital ativa por instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterMaiorContaCapitalAtivaPorInstituicao(Integer idInstituicao) throws BancoobException {
		return getServico().obterMaiorContaCapitalAtivaPorInstituicao(idInstituicao);
	}
	
	/**
	 * Obtem menor numero de conta capital ativa por instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterMenorContaCapitalAtivaPorInstituicao(Integer idInstituicao) throws BancoobException {
		return getServico().obterMenorContaCapitalAtivaPorInstituicao(idInstituicao);
	}
	
	/**
	 * Obtem maior numero de conta capital ativa por instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterMaiorContaCapitalPorInstituicao(Integer idInstituicao) throws BancoobException {
		return getServico().obterMaiorContaCapitalPorInstituicao(idInstituicao);
	}
	
	/**
	 * Obtem menor numero de conta capital ativa por instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterMenorContaCapitalPorInstituicao(Integer idInstituicao) throws BancoobException {
		return getServico().obterMenorContaCapitalPorInstituicao(idInstituicao);
	}
}
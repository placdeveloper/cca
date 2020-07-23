package br.com.sicoob.cca.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.cadastro.negocio.dto.ContaCapitalResumoDTO;
import br.com.sicoob.cca.cadastro.negocio.dto.DadosDesligamentoDTO;
import br.com.sicoob.cca.cadastro.negocio.dto.FiltroContaCapitalDTO;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;

/**
 * @author marco.nascimento
 */
public interface ContaCapitalServico extends ContaCapitalCadastroCrudServico<ContaCapital> {
	
	
	/**
	 * Atualiza situacao da proposta para Aguardando Aprovacao caso esteja como Devolvido ou Rejeitado 
																	   
	 * @param idContaCapital
	 * @throws BancoobException
	 */
	void atualizarSituacaoCadastro(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Atualiza situacao proposta/conta capital de acordo com os documentos vinculados
	 * <p> Utilizado na inclusao/alteracao da proposta/conta capital </p> 
	 * @param idContaCapital
	 * @throws BancoobException
	 */
	void atualizarSituacaoCadastroPorDocumento(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Consulta a ContaCapital por Cooperativa e Matricula(AK_CONTACAPITAL)
	 * @param idInstituicao
	 * @param numMatricula
	 * @return
	 * @throws BancoobException
	 */
	ContaCapital obterPorInstituicaoMatricula(Integer idInstituicao,Integer numMatricula) throws BancoobException;
	
	/**
	 * Consulta a ContaCapital por Cooperativa e Pessoa
	 * @param idInstituicao
	 * @param idPessoa
	 * @return
	 * @throws BancoobException
	 */
	List<ContaCapital> obterPorInstituicaoPessoa(Integer idInstituicao, Integer idPessoa) throws BancoobException;
	
	/**
	 * Incluir utilizado para usuario fora do Sisbr 2.0
	 * @param contaCapital
	 * @return
	 * @throws BancoobException
	 */
	ContaCapital incluirExterno(ContaCapital contaCapital) throws BancoobException;	
	
	/**
	 * Obtem nova conta capital (matricula) para a instituição em questão
	 * @param idInstituicao
	 * @return 
	 * @throws BancoobException
	 */
	Integer obterNovaContaCapital(Integer idInstituicao) throws BancoobException;
	
	
	/**
	 * Pesquisa de acordo com o filtro informado
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	List<CadastroContaCapitalRenDTO> pesquisar(CadastroContaCapitalRenDTO dto) throws BancoobException;
	
	/**
	 * Pesquisa quantidade de lancamentos existentes para a conta capital
	 * @param idContaCapital
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Integer pesquisarLancamentosContaCapital(Integer idContaCapital, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Pesquisa quantidade de parcelamentos existentes para a conta capital
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	Integer pesquisarParcelamentosContaCapital(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Exclui cadastro de conta capital
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	void excluir(Integer idContaCapital) throws BancoobException;
	
	/**
	 * {@link br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ContaCapitalDao#obterResumo(Integer)}
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	ContaCapitalResumoDTO obterResumo(Integer idContaCapital) throws BancoobException;
	
	/**
	 * {@link br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ContaCapitalDao#obterResumo(Integer, Integer)}
	 * @param idInstituicao
	 * @param numContaCapital
	 * @return
	 * @throws BancoobException
	 */
	ContaCapitalResumoDTO obterResumo(Integer idInstituicao, Integer numContaCapital) throws BancoobException;

	/**
	 * {@link br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ContaCapitalDao#obterResumos(Integer)}
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	List<ContaCapitalResumoDTO> obterResumos(List<Integer> idsContaCapital) throws BancoobException;
	
	/**
	 * Consulta conta capital por pessoa (apenas ativas)
	 * @param lstPessoas
	 * @return
	 * @throws BancoobException
	 */
	List<CadastroContaCapitalRenDTO> obterContaCapitalPorPessoa(List<PessoaIntegracaoDTO> lstPessoas) throws BancoobException;
	
	/**
	 * Recupera o idInstituicao pelo idContaCapital
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	Integer obterIdInstituicaoPorIdContaCapital(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Obtem maior numero de conta capital ativa por instituicao
	 * @param idInstituicao
	 * @return numContacapital
	 * @throws BancoobException
	 */
	Integer obterMaiorContaCapitalAtivaPorInstituicao(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem menor numero de conta capital ativa por instituicao
	 * @param idInstituicao
	 * @return numContacapital
	 * @throws BancoobException
	 */
	Integer obterMenorContaCapitalAtivaPorInstituicao(Integer idInstituicao) throws BancoobException;	
	
	/**
	 * Obter maior conta capital por instituicao.
	 *
	 * @param idInstituicao the id instituicao
	 * @return the integer
	 * @throws BancoobException the bancoob exception
	 */
	Integer obterMaiorContaCapitalPorInstituicao(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obter menor conta capital por instituicao.
	 *
	 * @param idInstituicao the id instituicao
	 * @return the integer
	 * @throws BancoobException the bancoob exception
	 */
	Integer obterMenorContaCapitalPorInstituicao(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Recupera os JSONs com os dados de desligamento
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	List<DadosDesligamentoDTO> obterDadosDesligamento(FiltroContaCapitalDTO filtro) throws BancoobException;
}
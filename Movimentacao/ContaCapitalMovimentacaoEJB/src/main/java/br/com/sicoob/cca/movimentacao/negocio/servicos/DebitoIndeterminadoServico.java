package br.com.sicoob.cca.movimentacao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.DebitoContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.ConsultaDebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.DebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.QuadroGeralAssociadoDTO;

/**
 * @author marco.nascimento
 */
public interface DebitoIndeterminadoServico extends ContaCapitalMovimentacaoServico {

	/**
	 * Realiza pesquisa referente a quantidade de associados com e sem debito indeterminado
	 * @return
	 * @throws BancoobException
	 */
	List<QuadroGeralAssociadoDTO> pesquisarQuadroGeralAssociados() throws BancoobException;
	
	/**
	 * Realiza pesquisa referente a quantidade de associados com debito via CCO por DIA FIXO
	 * @return
	 * @throws BancoobException
	 */
	List<QuadroGeralAssociadoDTO> pesquisarQtdDebCCODiaFixo() throws BancoobException;
	
	/**
	 * Realiza pesquisa referente a quantidade de associados com debito via CCO por INTERVALO de dias
	 * @return
	 * @throws BancoobException
	 */
	List<QuadroGeralAssociadoDTO> pesquisarQtdDebCCOIntervalo() throws BancoobException;
	
	/**
	 * Realiza pesquisa referente a debitos via FOLHA/BANCO
	 * @return
	 * @throws BancoobException
	 */
	List<QuadroGeralAssociadoDTO> pesquisarQtdDebFolhaBanco() throws BancoobException;
	
	/**
	 * Pesquisa da tela de debito indeterminado
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	List<ConsultaDebitoIndeterminadoRenDTO> pesquisar(ConsultaDebitoIndeterminadoRenDTO filtro) throws BancoobException;
	
	/**
	 * Realiza inclusao de debito indeterminado individual
	 * @param dto
	 * @throws BancoobException
	 */
	void incluirDebIndividual(DebitoIndeterminadoRenDTO dto) throws BancoobException;
	
	/**
	 * Realiza inclusao de debito indeterminado em lote
	 * @param dto
	 * @throws BancoobException
	 */
	void incluirDebEmLote(DebitoIndeterminadoRenDTO dto) throws BancoobException;
	
	/**
	 * Realiza alteracao de debito indeterminado individual
	 * @param dto
	 * @throws BancoobException
	 */
	void alterarDebIndividual(DebitoIndeterminadoRenDTO dto) throws BancoobException;
	
	/**
	 * Realiza alteracao de debito indeterminado em lote
	 * @param dto
	 * @param tipoAlteracao
	 * @throws BancoobException
	 */
	void alterarDebEmLote(DebitoIndeterminadoRenDTO dto, Integer tipoAlteracao) throws BancoobException;
	
	/**
	 * Pesquisa DebitoContaCapital por PK
	 * @param idDebitoContaContaCapital
	 * @return
	 * @throws BancoobException
	 */
	DebitoContaCapital pesquisar(Long idDebitoContaContaCapital) throws BancoobException;
	
	/**
	 * Pesquisa idNumMatricula por PK
	 * @param idInstituicao 
	 * @return
	 * @param idNumMatricula
	 * @throws BancoobException
	 */
	DebitoContaCapital pesquisarPorIdContaCapital(Integer idContaCapital, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Realiza pesquisa de associado sem debito indeterminado cadastrado. <br><b>Limitado a 5.000 associados por vez<b>
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	List<ConsultaDebitoIndeterminadoRenDTO> pesquisarAssociadosSemDebito(ConsultaDebitoIndeterminadoRenDTO filtro) throws BancoobException;
	
	/**
	 * Realiza exclusao de debito indeterminado em lote
	 * @param dto
	 * @throws BancoobException
	 */
	void excluirDebEmLote(DebitoIndeterminadoRenDTO dto) throws BancoobException;
}
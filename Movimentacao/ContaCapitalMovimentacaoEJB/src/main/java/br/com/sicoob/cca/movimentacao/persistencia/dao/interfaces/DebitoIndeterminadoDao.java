/*
 * 
 */
package br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.DebitoContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.ConsultaDebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.DebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.QuadroGeralAssociadoDTO;


/**
 * DAO referente a Debito Inderterminado
 * 
 * @author marco.nascimento
 */
public interface DebitoIndeterminadoDao {
	
	/**
	 * Realiza inclusao de debito indeterminado
	 * @param debCCA
	 * @return
	 * @throws BancoobException
	 */
	DebitoContaCapital incluir(DebitoContaCapital debCCA) throws BancoobException;
	
	/**
	 * Realiza inclusao de debito indeterminado em lote
	 * @param dto
	 * @throws BancoobException
	 */
	void incluirEmLote(DebitoIndeterminadoRenDTO dto) throws BancoobException;
	
	/**
	 * Realiza alteracao de debito indeterminado
	 * @param debCCA
	 * @return
	 * @throws BancoobException
	 */
	DebitoContaCapital alterar(DebitoContaCapital debCCA) throws BancoobException;
	
	/**
	 * Realiza alteracao de debito indeterminado em lote
	 * @param dto
	 * @param tipoAlteracao
	 * @return
	 * @throws BancoobException
	 */
	void alterarEmLote(DebitoIndeterminadoRenDTO dto, Integer tipoAlteracao) throws BancoobException;

	/**
	 * Realiza pesquisa referente a quantidade de associados com e sem debito indeterminado
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<QuadroGeralAssociadoDTO> pesquisarQuadroGeralAssociados(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Realiza pesquisa referente a quantidade de associados com debito via CCO por DIA FIXO
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<QuadroGeralAssociadoDTO> pesquisarQtdDebCCODiaFixo(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Realiza pesquisa referente a quantidade de associados com debito via CCO por INTERVALO de dias
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<QuadroGeralAssociadoDTO> pesquisarQtdDebCCOIntervalo(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Realiza pesquisa referente a debitos via FOLHA/BANCO
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<QuadroGeralAssociadoDTO> pesquisarQtdDebFolhaBanco(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Consulta da tela de debito indeterminado
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	List<ConsultaDebitoIndeterminadoRenDTO> pesquisar(ConsultaDebitoIndeterminadoRenDTO filtro) throws BancoobException;
	
	/**
	 * Pesquisa debito indeterminado por conta capital e instituicao (Indice)
	 * @param idContaCapital
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	DebitoContaCapital pesquisarDebitoContaCapital(Integer idContaCapital, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Pesquisa DebitoContaCapital por PK
	 * @param idDebitoContaContaCapital
	 * @return
	 * @throws BancoobException
	 */
	DebitoContaCapital pesquisar(Long idDebitoContaContaCapital) throws BancoobException;
	
	/**
	 * Pesquisa DebitoContaCapital por PK
	 * @param idInstituicao 
	 * @param idDebitoContaContaCapital
	 * @return
	 * @throws BancoobException
	 */
	DebitoContaCapital pesquisarPorNumMatricula(Integer numMatricula, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Realiza pesquisa de associado sem debito indeterminado cadastrado. <br><b>Limitado a 5.000 associados por vez<b>
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	List<ConsultaDebitoIndeterminadoRenDTO> pesquisarAssociadosSemDebitoCCO(ConsultaDebitoIndeterminadoRenDTO filtro) throws BancoobException;
	
	/**
	 * Realiza pesquisa de associado sem debito indeterminado cadastrado. <br><b>Limitado a 5.000 associados por vez<b>
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	List<ConsultaDebitoIndeterminadoRenDTO> pesquisarAssociadosSemDebitoFolhaBanco(ConsultaDebitoIndeterminadoRenDTO filtro) throws BancoobException;
	
	/**
	 * Realiza exclusao de debito indeterminado em lote
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	void excluirEmLote(DebitoIndeterminadoRenDTO dto) throws BancoobException;
}
package br.com.sicoob.cca.relatorios.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSituacaoMatriculaCCARenDTO;

/**
 * The Interface RelSituacaoMatriculaCCARenDao.
 */
public interface RelSituacaoMatriculaCCARenDao {
	

	/**
	 * Pesquisar situacao CCA por matricula.
	 *
	 * @param filtro the filtro
	 * @return the list
	 * @throws BancoobException the bancoob exception
	 */
	List<RelSituacaoMatriculaCCARenDTO> pesquisarSituacaoCCAPorMatricula(RelSituacaoMatriculaCCARenDTO filtro) throws BancoobException;
}
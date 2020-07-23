package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.FechRelLancContabilDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RelConciliacaoContabilDTO;

/**
 * @author Ricardo.Barcante
 */
public interface FechRelContabilLegadoDao {

	List<FechRelLancContabilDTO> pesquisarLancamentoContabil(FechRelLancContabilDTO filtro, Integer numCoop) throws BancoobException;

	List<RelConciliacaoContabilDTO> obtemDadosConcialicaoContabil(Date dataInicial, Date dataFinal, Integer numeroCooperativa)
			throws BancoobException;

}
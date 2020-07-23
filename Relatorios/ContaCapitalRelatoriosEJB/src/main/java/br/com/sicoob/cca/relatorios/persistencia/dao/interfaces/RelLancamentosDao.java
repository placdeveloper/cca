package br.com.sicoob.cca.relatorios.persistencia.dao.interfaces;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelLancamentosAnaliticoDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelLancamentosSinteticoDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelResumoLancamentosDTO;

/**
 * @author Kleber Alves
 */
public interface RelLancamentosDao {

	List<RelResumoLancamentosDTO> obtemResumoLancamentos(Date dataInicial, Date dataFinal, Integer instituicaoID) throws BancoobException;

	List<RelLancamentosSinteticoDTO> obtemLancamentosNaoContabilizadosSintetico(Date dataInicial, Date dataFinal, Integer instituicaoID)
			throws BancoobException;

	List<RelLancamentosAnaliticoDTO> obtemLancamentosNaoContabilizadosAnalitico(Date dataInicial, Date dataFinal, Integer instituicaoID)
			throws BancoobException;
}

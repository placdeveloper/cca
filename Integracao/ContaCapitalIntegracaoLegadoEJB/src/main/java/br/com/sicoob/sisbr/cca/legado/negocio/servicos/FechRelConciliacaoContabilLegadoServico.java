package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RelConciliacaoContabilDTO;

/**
 * @author Kleber Alves
 */
public interface FechRelConciliacaoContabilLegadoServico {

	List<RelConciliacaoContabilDTO> obtemDadosConcialicaoContabil(Date dataInicial, Date dataFinal, Integer numeroCooperativa)
			throws BancoobException;
}
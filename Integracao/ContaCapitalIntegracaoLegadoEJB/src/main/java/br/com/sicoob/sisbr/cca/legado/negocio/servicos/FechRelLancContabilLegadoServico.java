package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.FechRelLancContabilDTO;

/**
* @author Ricardo.Barcante
*/
public interface FechRelLancContabilLegadoServico {
	List<FechRelLancContabilDTO> pesquisarLancamentoContabil(FechRelLancContabilDTO filtro,
			Integer numCoop) throws BancoobException;
}
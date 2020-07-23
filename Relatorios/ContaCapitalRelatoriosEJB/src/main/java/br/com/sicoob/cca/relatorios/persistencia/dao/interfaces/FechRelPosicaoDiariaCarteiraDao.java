package br.com.sicoob.cca.relatorios.persistencia.dao.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.FechRelPosicaoDiariaCarteiraDTO;

/**
* @author Ricardo.Barcante
*/
public interface FechRelPosicaoDiariaCarteiraDao {

	FechRelPosicaoDiariaCarteiraDTO pesquisarPosicaoDiariaCarteira(FechRelPosicaoDiariaCarteiraDTO filtro) throws BancoobException;

}
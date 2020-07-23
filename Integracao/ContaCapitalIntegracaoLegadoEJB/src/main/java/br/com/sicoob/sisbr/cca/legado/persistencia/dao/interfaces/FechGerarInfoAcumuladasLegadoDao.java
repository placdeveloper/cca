package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import br.com.bancoob.excecao.BancoobException;

public interface FechGerarInfoAcumuladasLegadoDao {

	void gerarInfoAcumulada(Integer numCoop) throws BancoobException;

}
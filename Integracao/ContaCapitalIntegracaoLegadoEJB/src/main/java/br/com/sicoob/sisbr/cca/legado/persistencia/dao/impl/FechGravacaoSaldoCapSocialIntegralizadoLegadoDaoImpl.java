package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechGravacaoSaldoCapSocialIntegralizadoLegadoDao;

public class FechGravacaoSaldoCapSocialIntegralizadoLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements FechGravacaoSaldoCapSocialIntegralizadoLegadoDao {

	public void realizarCarga(Integer numCoop, Date dataAtualProduto) throws BancoobException {
		Comando comando = null;
		Connection conn = null;

		try {
			comando = getComando("FECHGRAVACAOSALDOCAPSOCIALINTEGRALIZADO");
			
			comando.adicionarVariavel("dataAtualProd", dataAtualProduto);
			
			comando.adicionarVariavel("idProduto", 2);
			
			comando.adicionarVariavel("codSaldoContaCapital", ContaCapitalConstantes.COD_SALDO_CONTA_CAPITAL);
			comando.adicionarVariavel("codSaldoContaCapitalSubscrito", ContaCapitalConstantes.COD_SALDO_CONTA_CAPITAL_SUBSCRITO);
			comando.adicionarVariavel("codSaldoContaCapitalADevolver", ContaCapitalConstantes.COD_SALDO_CONTA_CAPITAL_A_DEVOLVER);
			
			comando.configurar();
			conn = estabelecerConexao(numCoop);
			comando.executarAtualizacao(conn);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conn);
		}	
	}

}
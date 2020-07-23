package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RecolhimentoIrrfDestinacaoJurosLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.RecolhimentoIrrfDestinacaoJurosLegadoDao;

public class RecolhimentoIrrfDestinacaoJurosLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements RecolhimentoIrrfDestinacaoJurosLegadoDao {
	
	public List<RecolhimentoIrrfDestinacaoJurosLegadoDTO> pesquisarRecolhimentoIrrfDestinacaoJuros(
			RecolhimentoIrrfDestinacaoJurosLegadoDTO filtro) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;

		List<RecolhimentoIrrfDestinacaoJurosLegadoDTO> lstRetorno = new ArrayList<RecolhimentoIrrfDestinacaoJurosLegadoDTO>();		

		try {

			List<RecolhimentoIrrfDestinacaoJurosLegadoDTO> recolhimentos = new ArrayList<RecolhimentoIrrfDestinacaoJurosLegadoDTO>();
			RecolhimentoIrrfDestinacaoJurosLegadoDTO recolhimento = null;

			comando = getComando("PESQUISARECOLHIMENTOIRRFDESTINACAOJUROS");
			comando.adicionarVariavel("filtro", filtro);
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);

			while(rs.next()) {
				recolhimento = new RecolhimentoIrrfDestinacaoJurosLegadoDTO();
				recolhimento.setNumContaCapital(rs.getInt("NUMCONTACAPITAL"));

				recolhimento.setNomeCliente(rs.getString("NOMECLIENTE"));
				recolhimento.setNumContaCorrente(rs.getInt("NUMCONTACORRENTE"));

				recolhimento.setValorContaCorrente(rs.getBigDecimal("VALORCONTACORRENTE"));
				recolhimento.setValorContaCapital(rs.getBigDecimal("VALORCONTACAPITAL"));

				recolhimento.setValorTotal(recolhimento.getValorContaCorrente().add(recolhimento.getValorContaCapital()));
				recolhimento.setValorIrrf(rs.getBigDecimal("VALORIRRF"));

				recolhimento.setDescPA(rs.getString("DESCPA"));
				recolhimento.setNumPac(rs.getInt("NUMPAC"));

				recolhimentos.add(recolhimento);
			}

			if(!recolhimentos.isEmpty()) {
				RecolhimentoIrrfDestinacaoJurosLegadoDTO dtoRelatorio = new RecolhimentoIrrfDestinacaoJurosLegadoDTO();
				dtoRelatorio.setRecolhimentos(recolhimentos);
				dtoRelatorio.setAnoBase(filtro.getAnoBase());

				lstRetorno.add(dtoRelatorio);
			}

		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalLegadoException(e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}

		return lstRetorno;
	}
}

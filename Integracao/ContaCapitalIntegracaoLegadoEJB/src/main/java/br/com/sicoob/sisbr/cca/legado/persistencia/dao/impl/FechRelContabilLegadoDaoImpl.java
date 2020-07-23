package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.FechRelLancContabilDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RelConciliacaoContabilDTO;
import br.com.sicoob.sisbr.cca.legado.persistencia.builder.RelContabilDTOBuilder;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechRelContabilLegadoDao;

/**
 * @author Ricardo.Barcante
 */
public class FechRelContabilLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements FechRelContabilLegadoDao {

	public List<FechRelLancContabilDTO> pesquisarLancamentoContabil(FechRelLancContabilDTO filtro, Integer numCoop)
			throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;

		List<FechRelLancContabilDTO> retorno = new ArrayList<FechRelLancContabilDTO>();
		FechRelLancContabilDTO dto = null;

		try
		{
			comando = getComando("FECHRELLANCAMENTOCONTABIL");
			comando.adicionarVariavel("filtro", filtro);
			comando.configurar();
			conn = estabelecerConexao(numCoop);
			rs = comando.executarConsulta(conn);

			while (rs.next())
			{
				dto = new FechRelLancContabilDTO();
				dto.setContaReduzida(rs.getInt("NUMCONTASISTCONTABIL"));
				dto.setNumContaBacen(rs.getString("NUMCONTABACEN"));

				dto.setAnaliticoContabil(rs.getString("ANALITICOCONTABIL"));
				dto.setDescricaoConta(rs.getString("DESCCOMPCONTACONTABIL"));

				dto.setCodHistorico(rs.getInt("CODHISTORICO"));
				dto.setDescricaoHistoricoContabil(rs.getString("DESCHISTCONTABIL"));

				dto.setValorCredito(rs.getDouble("VALORCRED"));
				dto.setValorDebito(rs.getDouble("VALORDEB"));

				retorno.add(dto);
			}

			return retorno;

		}
		catch (PersistenciaException e)
		{
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}
		catch (SQLException e)
		{
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}
		finally
		{
			fecharComando(comando);
			fecharConexao(conn);
		}
	}

	public List<RelConciliacaoContabilDTO> obtemDadosConcialicaoContabil(Date dataInicial, Date dataFinal, Integer numeroCooperativa)
			throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet resultado = null;

		List<RelConciliacaoContabilDTO> conciliacao = null;
		
		try
		{
			comando = getComando("PESQUISA_RELATORIO_CONCILIACAO_CONTABIL");
			comando.adicionarVariavel("dataInicial", dataInicial);
			comando.adicionarVariavel("dataFinal", dataFinal);
			comando.configurar();
			
			conexao = estabelecerConexao(numeroCooperativa);
			resultado = comando.executarConsulta(conexao);

			conciliacao = RelContabilDTOBuilder.builderConciliacaoContabil(resultado);
		}
		catch (PersistenciaException e)
		{
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}
		catch (SQLException e)
		{
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}
		finally
		{
			fecharComando(comando);
			fecharConexao(conexao);
		}

		return conciliacao;
	}
}
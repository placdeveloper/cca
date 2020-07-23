package br.com.sicoob.cca.relatorios.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelLancamentosAnaliticoDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelLancamentosSinteticoDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelResumoLancamentosDTO;
import br.com.sicoob.cca.relatorios.negocio.excecao.ContaCapitalRelatoriosException;
import br.com.sicoob.cca.relatorios.persistencia.builder.RelLancamentosDTOBuilder;
import br.com.sicoob.cca.relatorios.persistencia.dao.ContaCapitalRelatoriosDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelLancamentosDao;

/**
 * @author Kleber Alves
 */
public class RelLancamentosDaoImpl extends ContaCapitalRelatoriosDao implements RelLancamentosDao {

	public List<RelResumoLancamentosDTO> obtemResumoLancamentos(Date dataInicial, Date dataFinal, Integer instituicaoID)
			throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet resultado = null;

		List<RelResumoLancamentosDTO> lancamentos = null;

		try
		{
			comando = inicializaComando("PESQUISA_RELATORIO_RESUMO_LANCAMENTOS", dataInicial, dataFinal, instituicaoID);
			conexao = estabelecerConexao();
			resultado = comando.executarConsulta(conexao);

			lancamentos = RelLancamentosDTOBuilder.builderLancamentosResumido(resultado);
		}
		catch (SQLException e)
		{
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalRelatoriosException(e);
		}
		catch (PersistenciaException e)
		{
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalRelatoriosException(e);
		}
		finally
		{
			fecharComando(comando);
			fecharConexao(conexao);
		}

		return lancamentos;
	}

	public List<RelLancamentosSinteticoDTO> obtemLancamentosNaoContabilizadosSintetico(Date dataInicial, Date dataFinal,
			Integer instituicaoID) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet resultado = null;

		List<RelLancamentosSinteticoDTO> lancamentos = null;

		try
		{
			comando = inicializaComando("PESQUISA_RELATORIO_LANCAMENTOS_NAO_CONTABILIZADOS_SINTETICO", dataInicial, dataFinal,
					instituicaoID);
			conexao = estabelecerConexao();
			resultado = comando.executarConsulta(conexao);

			lancamentos = RelLancamentosDTOBuilder.builderLancamentosSintetico(resultado);
		}
		catch (SQLException e)
		{
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalRelatoriosException(e);
		}
		catch (PersistenciaException e)
		{
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalRelatoriosException(e);
		}
		finally
		{
			fecharComando(comando);
			fecharConexao(conexao);
		}

		return lancamentos;
	}

	public List<RelLancamentosAnaliticoDTO> obtemLancamentosNaoContabilizadosAnalitico(Date dataInicial, Date dataFinal,
			Integer instituicaoID) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet resultado = null;

		List<RelLancamentosAnaliticoDTO> lancamentos = null;

		try
		{
			comando = inicializaComando("PESQUISA_RELATORIO_LANCAMENTOS_NAO_CONTABILIZADOS_ANALITICO", dataInicial, dataFinal,
					instituicaoID);
			conexao = estabelecerConexao();
			resultado = comando.executarConsulta(conexao);

			lancamentos = RelLancamentosDTOBuilder.builderLancamentosAnalitico(resultado);
		}
		catch (SQLException e)
		{
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalRelatoriosException(e);
		}
		catch (PersistenciaException e)
		{
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalRelatoriosException(e);
		}
		finally
		{
			fecharComando(comando);
			fecharConexao(conexao);
		}

		return lancamentos;
	}

	private Comando inicializaComando(String nomeQuery, Date dataInicial, Date dataFinal, Integer instituicaoID) {

		Comando comando = getComando(nomeQuery);
		comando.adicionarVariavel("dataInicial", dataInicial);
		comando.adicionarVariavel("dataFinal", dataFinal);
		comando.adicionarVariavel("instituicaoID", instituicaoID);
		comando.configurar();

		return comando;
	}

}

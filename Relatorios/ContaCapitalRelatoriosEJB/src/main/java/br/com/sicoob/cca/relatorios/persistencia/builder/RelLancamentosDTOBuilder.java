package br.com.sicoob.cca.relatorios.persistencia.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.cca.relatorios.negocio.dto.RelLancamentosAnaliticoDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelLancamentosSinteticoDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelResumoLancamentosDTO;

public final class RelLancamentosDTOBuilder {

	private RelLancamentosDTOBuilder() {
	}

	public static List<RelLancamentosAnaliticoDTO> builderLancamentosAnalitico(ResultSet resultado) throws SQLException {

		List<RelLancamentosAnaliticoDTO> lancamentos = new ArrayList<RelLancamentosAnaliticoDTO>();
		RelLancamentosAnaliticoDTO lancamento;

		while (resultado.next())
		{
			lancamento = new RelLancamentosAnaliticoDTO();
			lancamento.setDataLancamento(resultado.getDate("DATALANCAMENTO"));
			lancamento.setTipoHistoricoLancamento(resultado.getInt("IDTIPOHISTORICO"));
			lancamento.setTipoHistoricoEstorno(resultado.getInt("IDTIPOHISTORICOESTORNO"));
			lancamento.setDescricaoHistorico(resultado.getString("DESCRICAOHISTORICO"));
			lancamento.setValorTotalDebito(resultado.getBigDecimal("VALORDEBITO"));
			lancamento.setValorTotalCredito(resultado.getBigDecimal("VALORCREDITO"));
			lancamento.setTipoLote(resultado.getInt("IDTIPOLOTE"));
			lancamento.setSequencialLancamento(resultado.getInt("NUMSEQLANC"));
			lancamento.setNumeroDocumento(resultado.getString("DESCNUMDOCUMENTO"));
			lancamento.setNumeroCliente(resultado.getInt("IDPESSOALEGADO"));
			lancamento.setNumeroContaCapital(resultado.getInt("NUMCONTACAPITAL"));
			lancamento.setNomePessoa(resultado.getString("NOMEPESSOA"));
			lancamento.setNaturezaOperacao(resultado.getString("CODNATUREZAOPERACAO"));
			lancamentos.add(lancamento);
		}

		return lancamentos;
	}

	public static List<RelResumoLancamentosDTO> builderLancamentosResumido(ResultSet resultado) throws SQLException {

		List<RelResumoLancamentosDTO> lancamentos = new ArrayList<RelResumoLancamentosDTO>();
		RelResumoLancamentosDTO lancamento;

		while (resultado.next())
		{
			lancamento = new RelResumoLancamentosDTO();
			lancamento.setDataLancamento(resultado.getDate("DATALANCAMENTO"));
			lancamento.setGrupoHistorico(resultado.getInt("IDGRUPOHISTORICO"));
			lancamento.setValorTotalDebito(resultado.getBigDecimal("TOTALDEBITO"));
			lancamento.setValorTotalCredito(resultado.getBigDecimal("TOTALCREDITO"));
			lancamentos.add(lancamento);
		}

		return lancamentos;
	}

	public static List<RelLancamentosSinteticoDTO> builderLancamentosSintetico(ResultSet resultado) throws SQLException {

		List<RelLancamentosSinteticoDTO> lancamentos = new ArrayList<RelLancamentosSinteticoDTO>();
		RelLancamentosSinteticoDTO lancamento;

		while (resultado.next())
		{
			lancamento = new RelLancamentosSinteticoDTO();
			lancamento.setDataLancamento(resultado.getDate("DATALANCAMENTO"));
			lancamento.setTipoHistoricoLancamento(resultado.getInt("IDTIPOHISTORICO"));
			lancamento.setTipoHistoricoEstorno(resultado.getInt("IDTIPOHISTORICOESTORNO"));
			lancamento.setDescricaoHistorico(resultado.getString("DESCRICAOHISTORICO"));
			lancamento.setQuantidadeLancamentos(resultado.getInt("QUANTIDADELANCAMENTOS"));
			lancamento.setValorTotalDebito(resultado.getBigDecimal("TOTALDEBITO"));
			lancamento.setValorTotalCredito(resultado.getBigDecimal("TOTALCREDITO"));
			lancamentos.add(lancamento);
		}

		return lancamentos;
	}

}

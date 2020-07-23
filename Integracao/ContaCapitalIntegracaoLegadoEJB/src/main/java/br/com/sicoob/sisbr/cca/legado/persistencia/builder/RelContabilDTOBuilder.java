package br.com.sicoob.sisbr.cca.legado.persistencia.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.sisbr.cca.legado.negocio.dto.RelConciliacaoContabilDTO;

public final class RelContabilDTOBuilder {

	private RelContabilDTOBuilder() {
	}

	public static List<RelConciliacaoContabilDTO> builderConciliacaoContabil(ResultSet resultado) throws SQLException {

		List<RelConciliacaoContabilDTO> concialicao = new ArrayList<RelConciliacaoContabilDTO>();
		RelConciliacaoContabilDTO lancamento;

		while (resultado.next())
		{
			lancamento = new RelConciliacaoContabilDTO();
			lancamento.setDataLancamento(resultado.getDate("DATALOTE"));
			lancamento.setTipoHistoricoLancamento(resultado.getInt("IDTIPOHISTORICOLANC"));
			lancamento.setTipoHistoricoEstorno(resultado.getInt("IDTIPOHISTORICOESTORNO"));
			lancamento.setDescricaoHistorico(resultado.getString("DESCRICAOHISTORICO"));
			lancamento.setGrupoHistorico(resultado.getString("GRUPOHISTORICO"));
			lancamento.setQuantidadeLancamentos(resultado.getInt("QUANTIDADELANCAMENTOS"));
			lancamento.setValorTotal(resultado.getBigDecimal("VALORTOTAL"));
			concialicao.add(lancamento);
		}

		return concialicao;
	}

}

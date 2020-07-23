package br.com.sicoob.cca.relatorios.negocio.dto;

/**
 * @author Kleber Alves
 */
public class RelResumoLancamentosDTO extends RelLancamentosDTO {

	private static final long serialVersionUID = -7954537310817426816L;

	private Integer grupoHistorico;

	public Integer getGrupoHistorico() {
		return grupoHistorico;
	}

	public void setGrupoHistorico(Integer grupoHistorico) {
		this.grupoHistorico = grupoHistorico;
	}
}

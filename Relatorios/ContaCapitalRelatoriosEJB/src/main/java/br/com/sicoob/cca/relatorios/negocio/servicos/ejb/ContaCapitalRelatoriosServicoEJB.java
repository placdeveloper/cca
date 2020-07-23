package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.servicos.ejb.BancoobServicoEJB;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.relatorios.negocio.servicos.ContaCapitalRelatoriosServico;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;

/**
 *  Implementacao abstrata de relatorios conta capital
 */
public abstract class ContaCapitalRelatoriosServicoEJB extends BancoobServicoEJB implements ContaCapitalRelatoriosServico {

	private Map<String, Object> parametrosComuns;
	private RelatorioDadosDTO rDto;
	
	/**
	 * @see br.com.sicoob.cca.relatorios.negocio.servicos.ContaCapitalRelatoriosServico#configurarParametrosComuns(br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
	 */
	public void configurarParametrosComuns(RelatorioDadosDTO rDto) throws BancoobException {
		parametrosComuns = new HashMap<String, Object>();
		configurarImagemSicoob();
		parametrosComuns.put("USUARIO", rDto.getUsuarioBancoobDTO().getLogin());
		this.rDto = rDto;
	}
	
	private void configurarImagemSicoob() throws BancoobException {
		Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("cca_relatorios.propriedades");
		String caminhologosicoob = propriedades.getProperty("cca.caminhologosicoob");
		parametrosComuns.put("IMAGEM_SICOOB", ContaCapitalUtil.recuperarImagemRelatorio(caminhologosicoob));
	}

	protected Map<String, Object> getParametrosComuns() throws BancoobException {
		if (parametrosComuns == null) {
			configurarParametrosLegado();
		}
		return parametrosComuns;
	}

	private void configurarParametrosLegado() throws BancoobException {
		parametrosComuns = new HashMap<String, Object>();
		configurarImagemSicoob();
		parametrosComuns.put("USUARIO", InformacoesUsuario.getInstance().getLogin());		
	}
	
	protected Integer getIdInstituicaoUsuario() {
		Integer idInstituicao = null;
		if (rDto != null) {
			idInstituicao = Integer.valueOf(rDto.getUsuarioBancoobDTO().getIdInstituicao());
		} else {
			idInstituicao = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		}
		return idInstituicao;
	}
	
}
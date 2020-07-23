package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.dto.RelDebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelDebitoIndeterminadoServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelDebitoIndeterminadoServicoRemote;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;

/**
 * @author marco.nascimento
 */
@Stateless
@Local(RelDebitoIndeterminadoServicoLocal.class)
@Remote(RelDebitoIndeterminadoServicoRemote.class)
public class RelDebitoIndeterminadoServicoEJB extends ContaCapitalRelatoriosServicoEJB implements RelDebitoIndeterminadoServicoLocal, RelDebitoIndeterminadoServicoRemote {
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	/**
	 * Metodo responsavel de gerar o relatorio de debito indeterminado
	 */
	public Object gerarRelatorioDebitoIndeterminado(List<RelDebitoIndeterminadoRenDTO> lstDTOEntrada, String filtro) throws BancoobException {
		
		String nomeRelatorio = "CCA_Relatorio_DebitoIndeterminado.jasper";
		
		Map<String, Object> parametros = getParametrosComuns();	
		parametros.put("COD_RELATORIO", CodigoRelatorio.COD_REL_DEBITO_INDETERMINADO);
		parametros.put("FILTRO", filtro == null || filtro.trim().equals("") ? "Nenhum" : filtro);
		
		Integer idInstituicao = getIdInstituicaoUsuario();
		InstituicaoIntegracaoDTO iiDTO = instituicaoIntegracaoServico.obterInstituicaoIntegracao(idInstituicao);
		parametros.put("DESC_SIGLA_COOP", iiDTO.getNumero() + " - " + iiDTO.getDescInstituicao());
		
		return new RelatorioContaCapitalV2<RelDebitoIndeterminadoRenDTO>(lstDTOEntrada, nomeRelatorio, parametros);
	}
	
}
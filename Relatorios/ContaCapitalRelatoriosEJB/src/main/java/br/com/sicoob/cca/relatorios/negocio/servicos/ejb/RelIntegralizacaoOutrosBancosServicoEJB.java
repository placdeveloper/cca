package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelIntegralizacaoOutrosBancosServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelIntegralizacaoOutrosBancosServicoRemote;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.IntegralizacaoOutrosBancosLegadoServicoLocal;
/**
 * EJB com os servicos do relatorio de integralização outros bancos.
 */
@Stateless
@Local(RelIntegralizacaoOutrosBancosServicoLocal.class)
@Remote(RelIntegralizacaoOutrosBancosServicoRemote.class) 
public class RelIntegralizacaoOutrosBancosServicoEJB extends ContaCapitalRelatoriosServicoEJB implements RelIntegralizacaoOutrosBancosServicoLocal, RelIntegralizacaoOutrosBancosServicoRemote {

	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;

	@EJB
	private IntegralizacaoOutrosBancosLegadoServicoLocal integralizacaoOutrosBancos;
	
	/**
	 * @see br.com.sicoob.cca.relatorios.negocio.servicos.RelIntegralizacaoOutrosBancosServico#gerarRelatorioRemessaIntegralizacaoOutrosBancosDetalhe(br.com.sicoob.cca.relatorios.negocio.dto.RelRemessaIntegralizacaoOutrosBancosDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object gerarRelatorioRemessaIntegralizacaoOutrosBancosDetalhe(IntegralizacaoOutrosBancosLegadoDTO dtoEntrada) throws BancoobException {

		List<IntegralizacaoOutrosBancosLegadoDTO> lista = integralizacaoOutrosBancos.consultarRemessaEnvDetalhe(dtoEntrada);
		
		String nomeRelatorio = "CCA_Relatorio_RemessaIntegralizacaoOutrosBancosDetalhe.jasper";
		
		Map<String, Object> parametros = getParametrosComuns();
		
		InstituicaoIntegracaoDTO dtoInstituicaoIntegracao = instituicaoIntegracaoServico.obterInstituicaoIntegracao(dtoEntrada.getIdInstituicao());
				
		parametros.put("DESC_SIGLA_COOP", dtoInstituicaoIntegracao.getNumero() + " - " + dtoInstituicaoIntegracao.getDescInstituicao());
		parametros.put("COD_RELATORIO", CodigoRelatorio.COD_REL_REMESSA_INTEGRALIZACAO_OUTROS_BANCOS);
		
		return new RelatorioContaCapitalV2<IntegralizacaoOutrosBancosLegadoDTO>(lista, nomeRelatorio, parametros);
	}


}

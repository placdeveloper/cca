package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ConfiguracaoCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ValorConfiguracaoCapitalServicoLocal;
import br.com.sicoob.cca.entidades.negocio.entidades.ConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.HistValorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.dto.RelValorParametroDTO;
import br.com.sicoob.cca.relatorios.negocio.excecao.ContaCapitalRelatoriosException;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelValorParametroServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelValorParametroServicoRemote;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;

/**
 * Responsavel por fornecer relatorios de valor cota
 * @author Marco.Nascimento
 */
@Stateless
@Local(RelValorParametroServicoLocal.class)
@Remote(RelValorParametroServicoRemote.class) 
public class RelValorParametroServicoEJB extends ContaCapitalRelatoriosServicoEJB implements RelValorParametroServicoLocal, RelValorParametroServicoRemote {
	
	@EJB
	private ValorConfiguracaoCapitalServicoLocal valorConfServico;
	
	@EJB
	private ConfiguracaoCapitalServicoLocal confServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal insIntServico;

 	/**
 	 * @see br.com.sicoob.cca.relatorios.negocio.servicos.RelValorParametroServico#gerarHistorico(java.util.List, java.lang.Integer)
 	 */
 	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
 	public Object gerarHistorico(List<Integer> idsInstituicao, Integer idParametro) throws BancoobException {
 		try {
 			
 			String nomeRelatorio = "CCA_Relatorio_HistValorParametro.jasper";
 			
 			ConfiguracaoCapital cc = confServico.obter(idParametro);
 			
 			Map<String, Object> parametros = getParametrosComuns();
 			parametros.put("COD_RELATORIO", CodigoRelatorio.COD_REL_VALOR_PARAMETRO);
 			parametros.put("ATIVO", cc.getBolAtivo() ? "Sim" : "Não");
 			parametros.put("RESPONSAVEL", cc.getPerfilConfiguracaoCapital().getDescPerfilConfiguracaoCapital());
 			parametros.put("TIPOVALOR", cc.getTipoValorConfiguracaoCapital().getDescTipoValorConfiguracaoCapital());
 			parametros.put("DESCPARAMETRO", descParametro(cc));
 			
 			List<RelValorParametroDTO> listaRelDTO = new ArrayList<RelValorParametroDTO>();
 			
 			List<ValorConfiguracaoCapital> lstVlrConf = valorConfServico.obterValorConfiguracao(idsInstituicao, idParametro);
 			for(ValorConfiguracaoCapital vlr : lstVlrConf) {
 				for(HistValorConfiguracaoCapital histVlr : vlr.getHistorico()) {
 					listaRelDTO.add(new RelValorParametroDTO(histVlr.getValorConfiguracao(), histVlr.getId().getDataHoraAtualizacao(), histVlr.getIdUsuario(), descCoop(histVlr.getIdInstituicao())));
 				}
			}
 			
 			return new RelatorioContaCapitalV2<RelValorParametroDTO>(listaRelDTO, nomeRelatorio, parametros);
 			
 		} catch (BancoobException e) {
 			this.getLogger().erro(e, e.getMessage());
 			throw new ContaCapitalRelatoriosException("MSG_RELATORIO_ERRO", e);
 		}
 	}
 	
 	private String descCoop(Integer idInstituicao) throws BancoobException {
 		InstituicaoIntegracaoDTO inst = insIntServico.obterInstituicaoIntegracao(idInstituicao);
 		return inst.getNumero() + " - " + inst.getSiglaInstituicao();
 	}
 	
 	private String descParametro(ConfiguracaoCapital cc) {
 		String descParametro = cc.getId().toString().concat(" - ");
 		
 		if(cc.getAgrupadorConfiguracaoCapital() != null && cc.getAgrupadorConfiguracaoCapital().getId() != null) {
 			descParametro += "[" + cc.getAgrupadorConfiguracaoCapital().getNome() + "] ";
 		}
 		
 		descParametro += cc.getNomeConfiguracaoCapital();
 		
 		return descParametro;
 	}
}
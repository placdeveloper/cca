/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.sisbr.capremrenovacao.api.negocio.delegates.AplicacaoDelegate;
import br.com.sicoob.sisbr.capremrenovacao.api.negocio.delegates.CapRemAPIFabricaDelegates;
import br.com.sicoob.sisbr.capremrenovacao.api.negocio.delegates.ModalidadeDelegate;
import br.com.sicoob.sisbr.capremrenovacao.api.negocio.dto.ModalidadeDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.CaptacaoRemuneradaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoCaptacaoRemuneradaNegocioException;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CaptacaoRemuneradaIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CaptacaoRemuneradaIntegracaoServicoRemote;

/**
 * EJB contendo servicos relacionados a Captacao Remunerada.
 */
@Stateless
@Local (CaptacaoRemuneradaIntegracaoServicoLocal.class)
@Remote(CaptacaoRemuneradaIntegracaoServicoRemote.class)
public class CaptacaoRemuneradaIntegracaoServicoEJB extends ContaCapitalIntegracaoServicoEJB implements CaptacaoRemuneradaIntegracaoServicoLocal, CaptacaoRemuneradaIntegracaoServicoRemote {

	private static final String MSG_ERRO_CAPREM = "Erro na integração com o Captacao Remunerada.";
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CaptacaoRemuneradaIntegracaoServico#listarModalidadeCaptacaoRemunerada()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ItemListaIntegracaoDTO> listarModalidadeCaptacaoRemunerada() throws BancoobException {
		ModalidadeDelegate modalidadeDelegate = CapRemAPIFabricaDelegates.getInstance().criarModalidadeDelegate();
		List<ModalidadeDTO> modalidades = modalidadeDelegate.listaDisponiveisParaDevolucaoDeCapital(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		return montarListaModalidadeCapRemDTO(modalidades);
	}
	
	private List<ItemListaIntegracaoDTO> montarListaModalidadeCapRemDTO(List<ModalidadeDTO> lista) {
		List<ItemListaIntegracaoDTO> listaVO = new ArrayList<ItemListaIntegracaoDTO>();

		for(ModalidadeDTO modalidadeCapRemDTO:lista){
			ItemListaIntegracaoDTO item = new ItemListaIntegracaoDTO(modalidadeCapRemDTO.getId().toString(), modalidadeCapRemDTO.getDescricao());
			listaVO.add(item);
		}
		
		Collections.sort(listaVO, new Comparator<ItemListaIntegracaoDTO>() {
			public int compare(ItemListaIntegracaoDTO o1, ItemListaIntegracaoDTO o2){
					return o1.getCodListaItem().compareTo(o2.getCodListaItem());
				} 
		});
		
		return listaVO;		
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CaptacaoRemuneradaIntegracaoServico#incluirCaptacaoRemuneradaIntegracao(br.com.sicoob.sisbr.cca.integracao.negocio.dto.CaptacaoRemuneradaIntegracaoDTO)
	 */
	public void incluirCaptacaoRemuneradaIntegracao(CaptacaoRemuneradaIntegracaoDTO captacaoRemuneradaIntegracaoDTO) throws BancoobException{
		this.getLogger().info("CCA.incluirCaptacaoRemuneradaIntegracao");
		AplicacaoDelegate aplicacaoDelegate = CapRemAPIFabricaDelegates.getInstance().criarAplicacaoDelegate();
		
		try{
			aplicacaoDelegate.incluirDevolucaoDeCapital(captacaoRemuneradaIntegracaoDTO.getNumContaCorrente().longValue(), captacaoRemuneradaIntegracaoDTO.getValorAplicacao(), 
					captacaoRemuneradaIntegracaoDTO.getQtdParcelas(), captacaoRemuneradaIntegracaoDTO.getIdModalidadeProduto(), 
					InformacoesUsuario.getInstance().getLogin(), Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		}catch (NegocioException e) {
			throw new IntegracaoCaptacaoRemuneradaNegocioException(e.getMessage(),e);
		}catch(BancoobException e){
			throw new IntegracaoCaptacaoRemuneradaNegocioException(MSG_ERRO_CAPREM + e.getMessage(),e);
		}				
		
	}
	
}

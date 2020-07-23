/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoGftException;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.GftIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.GftIntegracaoServicoRemote;
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiConsultaOcorrenciaProcessoDelegate;
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiConsultaProcessoDelegate;
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiFabricaDelegates;
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiProcessoDelegate;
import br.com.sicoob.sisbr.ged.api.negocio.dto.filtro.GedApiFabricaFiltros;
import br.com.sicoob.sisbr.ged.api.negocio.dto.filtro.IFiltroConsultaProcesso;
import br.com.sicoob.sisbr.ged.api.negocio.dto.parametro.GedApiFabricaParametro;
import br.com.sicoob.sisbr.ged.api.negocio.dto.parametro.IParametroProcesso;
import br.com.sicoob.sisbr.ged.api.negocio.dto.retorno.IRetornoOcorrenciaAtividade;
import br.com.sicoob.sisbr.ged.api.negocio.dto.retorno.IRetornoOcorrenciaProcesso;
import br.com.sicoob.sisbr.ged.api.negocio.dto.retorno.IRetornoProcedimento;
import br.com.sicoob.sisbr.ged.api.negocio.enums.GedApiEnumEstadoOcorrencia;

/**
 * @author Marco.Nascimento
 */
@Stateless
@Local (GftIntegracaoServicoLocal.class)
@Remote(GftIntegracaoServicoRemote.class)
public class GftIntegracaoServicoEJB extends ContaCapitalIntegracaoServicoEJB implements GftIntegracaoServicoLocal, GftIntegracaoServicoRemote {

	/** A constante SIGLA_PROCESSO_APROVACAO. */
	private static final String SIGLA_PROCESSO_APROVACAO = "APROVCADCONTACAPITAL";
	
	private Integer idInstUsu = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
	private Integer idUnInstUsu = Integer.valueOf(InformacoesUsuario.getInstance().getIdUnidadeInstituicao());
	
	private GedApiProcessoDelegate apiProcessoDelegate = GedApiFabricaDelegates.getInstance().criarGedApiProcessoDelegate();
	private GedApiConsultaProcessoDelegate apiConsultaProcDelegate = GedApiFabricaDelegates.getInstance().criarGedApiConsultaProcessoDelegate();
	private GedApiConsultaOcorrenciaProcessoDelegate apiConsultaOcorProcDelegate = GedApiFabricaDelegates.getInstance().criarGedApiConsultaOcorrenciaProcessoDelegate();
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.GftIntegracaoServico#instanciarFluxoAprovacao(br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO)
	 */
	public void instanciarFluxoAprovacao(GftIntegracaoDTO gftIntegracaoDTO) throws BancoobException {
		
		try {
			
			String descInstanciaAtividade = "[CCA] Aprovação ("+ gftIntegracaoDTO.getNumContaCapital() + ")";
			
			String registro = gftIntegracaoDTO.getIdRegistroControlado().toString();
			Integer idInstituicaoProcesso = gftIntegracaoDTO.getIdInstituicaoProcesso();
			Integer idUnidadeProcesso = Integer.valueOf(InformacoesUsuario.getInstance().getIdUnidadeInstituicao());
			String sigla = SIGLA_PROCESSO_APROVACAO;
			
			IParametroProcesso paramProcesso = GedApiFabricaParametro.getInstance().criarParametroInstanciarProcesso(sigla, registro, idInstituicaoProcesso, idUnidadeProcesso, descInstanciaAtividade);
			
			apiProcessoDelegate.instanciarOcorrenciaProcesso(paramProcesso);
			
			executarAtividadeAprovacao(gftIntegracaoDTO);
			
		} catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new IntegracaoGftException(e);
		}
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.GftIntegracaoServico#executarAtividadeAprovacao(br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO)
	 */
	public void executarAtividadeAprovacao(GftIntegracaoDTO gftIntegracaoDTO) throws BancoobException {
		
		try {
			
			if(gftIntegracaoDTO.getExecutarAtividadeAprovacao()) {
				
				String siglaProcesso = SIGLA_PROCESSO_APROVACAO;
				String idRegistro = gftIntegracaoDTO.getIdRegistroControlado().toString();
				Integer idOcorrenciaAtividade = gftIntegracaoDTO.getIdOcorrenciaAtividade();
				Date dataOcorrencia = new Date();
				String nomeProcedimento = gftIntegracaoDTO.getNomeProcedimento();
				String justificativa = gftIntegracaoDTO.getJustificativa();
				
				IParametroProcesso paramProcesso = GedApiFabricaParametro.getInstance().criarParametroExecutarAtividadeProcesso(siglaProcesso, idRegistro, idOcorrenciaAtividade, dataOcorrencia, nomeProcedimento, justificativa);
				apiProcessoDelegate.executarAtividadeProcesso(paramProcesso);
			}
			
		} catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new IntegracaoGftException(e);
		}
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.GftIntegracaoServico#listaProcedimentosAprovacao(br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO)
	 */
	public List<ItemListaIntegracaoDTO> listaProcedimentosAprovacao(GftIntegracaoDTO gftIntegracaoDTO) throws BancoobException {
		List<ItemListaIntegracaoDTO> lstRetorno = new ArrayList<ItemListaIntegracaoDTO>();
		
		try {
			
			IFiltroConsultaProcesso filtro = GedApiFabricaFiltros.getInstance().criarFiltroListarProcedimentoPorAtividade(gftIntegracaoDTO.getIdAtividade());
			List<IRetornoProcedimento> lstProcedimento = apiConsultaProcDelegate.listarProcedimentoPorAtividade(filtro);
			
			if(lstProcedimento != null && !lstProcedimento.isEmpty()) {
				for(IRetornoProcedimento p : lstProcedimento) {
					lstRetorno.add(new ItemListaIntegracaoDTO(p.getIdProcedimento().toString(), p.getNomeProcedimento()));
				}
			}
			
		} catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new IntegracaoGftException(e);
		}
		
		return lstRetorno;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.GftIntegracaoServico#recuperarNomeProcedimento(br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO)
	 */
	public String recuperarNomeProcedimento(GftIntegracaoDTO gftIntegracaoDTO) throws BancoobException {
		
		try {
			
			IFiltroConsultaProcesso filtro = GedApiFabricaFiltros.getInstance().criarFiltroListarProcedimentoPorAtividade(gftIntegracaoDTO.getIdAtividade());
			List<IRetornoProcedimento> lstProcedimento = apiConsultaProcDelegate.listarProcedimentoPorAtividade(filtro);
			
			if(lstProcedimento != null && !lstProcedimento.isEmpty()) {
				for(IRetornoProcedimento p : lstProcedimento) {
					if (p.getIdProcedimento().equals(gftIntegracaoDTO.getIdProcedimentoControle())) {
						return p.getNomeProcedimento();
					}
				}
			}
			
		} catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new IntegracaoGftException(e);
		}
		
		return null;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.GftIntegracaoServico#listarAtividadesPendentes(br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO)
	 */
	public List<GftIntegracaoDTO> listarAtividadesPendentes(GftIntegracaoDTO gftIntegracaoDTO) throws BancoobException {
		List<GftIntegracaoDTO> lstRetorno = new ArrayList<GftIntegracaoDTO>(0);
		
		try {
			
			IFiltroConsultaProcesso filtro = GedApiFabricaFiltros.getInstance().criarBuilderFiltroRecuperarAtividadesInstanciadasProcesso(
				SIGLA_PROCESSO_APROVACAO, false, InformacoesUsuario.getInstance().getLogin())
			.build();
			
			Collection<IRetornoOcorrenciaAtividade> lst = apiConsultaOcorProcDelegate.recuperarAtividadesInstanciadasProcesso(filtro);
			
			GftIntegracaoDTO dtoInteg = null;
			for(IRetornoOcorrenciaAtividade atv : lst){
				dtoInteg = new GftIntegracaoDTO();
				dtoInteg.setIdAtividade(atv.getIdAtividade());
				dtoInteg.setIdOcorrenciaAtividade(atv.getIdOcorrenciaAtividade());
				dtoInteg.setIdRegistroControlado(Integer.valueOf(atv.getIdRegistroControlado()));
				lstRetorno.add(dtoInteg);
			}
			
		} catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new IntegracaoGftException(e);
		}
		
		return lstRetorno;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.GftIntegracaoServico#isProcessoCompleto(java.lang.Integer, java.lang.Integer)
	 */
	public Boolean isProcessoCompleto(Integer idAtividade, Integer idRegistroControlado) throws BancoobException {
		
		try {
			
			IFiltroConsultaProcesso filtro = GedApiFabricaFiltros.getInstance().criarFiltroConsultarProcessoFinalizado(
					SIGLA_PROCESSO_APROVACAO, idInstUsu, Integer.valueOf(InformacoesUsuario.getInstance().getIdUnidadeInstituicao()), 
					InformacoesUsuario.getInstance().getLogin(), idInstUsu, idUnInstUsu, false);
			
			List<IRetornoOcorrenciaProcesso> lst = apiConsultaOcorProcDelegate.consultarProcessoFinalizado(filtro);
			
			if(lst != null && !lst.isEmpty()) {
				for (IRetornoOcorrenciaProcesso ocorrencia : lst) {
					if(ocorrencia.getIdRegistroControlado().equals(idRegistroControlado.toString())
							&& ocorrencia.getCodigoEstadoOcorrencia().equals(GedApiEnumEstadoOcorrencia.COMPLETO.getCodEstadoOcorrencia())) {
						return true;
					}
				}
			}
			
			return false;
			
		} catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new IntegracaoGftException(e);
		}
	}
	
}
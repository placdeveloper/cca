package br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.negocio.dto.InstituicaoCooperativaSCIDTO;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.ViewInstituicaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.LocalizacaoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.CentralCooperativaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LocalizacaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.TipoGrauCooperativaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoInstituicaoCooperativaException;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoInstituicaoException;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoInstituicaoNegocioException;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoRemote;
import br.com.sicoob.sisbr.sci.integracao.negocio.delegates.SciIntFabricaDelegate;
import br.com.sicoob.sisbr.sci.integracao.negocio.delegates.SciIntHierarquiaCooperativaDelegate;
import br.com.sicoob.sisbr.sci.integracao.negocio.delegates.SciIntHierarquiaInstituicaoCTADelegate;
import br.com.sicoob.sisbr.sci.integracao.negocio.delegates.SciIntInstituicaoCooperativaDelegate;
import br.com.sicoob.sisbr.sci.integracao.negocio.delegates.SciIntInstituicaoDelegate;
import br.com.sicoob.sisbr.sci.integracao.negocio.descriptors.EnumInstituicao;
import br.com.sicoob.sisbr.sci.integracao.negocio.descriptors.EnumSituacaoInstituicao;
import br.com.sicoob.sisbr.sci.integracao.negocio.descriptors.EnumUnidadeInstituicao;
import br.com.sicoob.sisbr.sci.integracao.negocio.dto.filtro.SciIntFiltroHierarquiaCooperativa;
import br.com.sicoob.sisbr.sci.integracao.negocio.dto.filtro.SciIntFiltroInstituicaoGeral;
import br.com.sicoob.sisbr.sci.integracao.negocio.entidades.ISciIntInstituicaoCooperativa;
import br.com.sicoob.sisbr.sci.integracao.negocio.entidades.ISciIntInstituicaoGeral;
import br.com.sicoob.sisbr.sci.integracao.negocio.entidades.cta.ISciIntInstituicaoCTA;
import br.com.sicoob.sisbr.sci.integracao.negocio.excecao.SciIntInstituicaoInativaException;
import br.com.sicoob.sisbr.sci.integracao.negocio.excecao.SciIntegracaoException;
import br.com.sicoob.tipos.DateTime;

/**
 * EJB contendo servicos relacionados a InstituicaoIntegracao.
 */
@Stateless
@Local (InstituicaoIntegracaoServicoLocal.class)
@Remote(InstituicaoIntegracaoServicoRemote.class)
public class InstituicaoIntegracaoServicoEJB extends ContaCapitalIntegracaoServicoEJB implements
		InstituicaoIntegracaoServicoLocal, InstituicaoIntegracaoServicoRemote {

	@EJB
	private ViewInstituicaoServicoLocal viewInstituicaoServico;
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico#obterNumeroCooperativa(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public Integer obterNumeroCooperativa(Integer idInstituicao) throws BancoobException {

		if (idInstituicao == null) {
			throw new IntegracaoInstituicaoNegocioException("MSG_004");
		}	
		
		this.getLogger().info("CCA.obterNumeroCooperativa");		
		Integer numero = null;
		ISciIntInstituicaoCooperativa instituicao = null;
		
		if(EnumInstituicao.ID_BANCOOB.getIdInstituicao() == idInstituicao){
			numero = EnumInstituicao.ID_BANCOOB.getIdInstituicao();
		} else {
			instituicao = obterInstituicaoCooperativaSCI(idInstituicao); 
			if (instituicao == null) {
				throw new IntegracaoInstituicaoNegocioException("MSG_004");
			}	
			numero = instituicao.getNumCooperativa();
		}			
			
		return numero;
		
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico#obterIdInstituicao(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public Integer obterIdInstituicao(Integer numCoop) throws BancoobException {
		Integer numero = null;
		this.getLogger().info("CCA.obterIdInstituicao");
		SciIntInstituicaoCooperativaDelegate instituicaoCooperativaDelegate = SciIntFabricaDelegate.getInstance().criarInstituicaoCooperativaDelegate();
		ISciIntInstituicaoCooperativa ic = instituicaoCooperativaDelegate.recuperarInstituicaoCooperativa(numCoop);
		if (ic != null){
			numero	= ic.getIdInstituicao();
		}
		return numero;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico#isInstituicaoAtiva(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public Boolean isInstituicaoAtiva(Integer idInstituicao) throws BancoobException {
		try {
			ISciIntInstituicaoCTA instituicaoCTA = obterInstituicaoCTA(idInstituicao);
			return instituicaoCTA.getCodigoSituacaoInstituicao().equals(EnumSituacaoInstituicao.ATIVA.getCodSituacao());
		} catch (SciIntInstituicaoInativaException e) {
			return Boolean.FALSE;
		}
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico#obterTipoGrauCooperativa(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)		
	public TipoGrauCooperativaDTO obterTipoGrauCooperativa(Integer idInstituicao) throws BancoobException {
		this.getLogger().info("CCA.obterTipoGrauCooperativa");
		TipoGrauCooperativaDTO tipoGrauCooperativaDTO = new TipoGrauCooperativaDTO();
		
		if(EnumInstituicao.ID_BANCOOB.getIdInstituicao() == idInstituicao) {
			tipoGrauCooperativaDTO.setCodTipoGrauCoop(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_BANCOOB);
			return tipoGrauCooperativaDTO;
		}
		
		ISciIntInstituicaoCooperativa instituicaoCooperativa = obterInstituicaoCooperativaSCI(idInstituicao);
		if (instituicaoCooperativa == null) {
			throw new IntegracaoInstituicaoNegocioException("MSG_005");
		}	
		
		Integer codTipoGrauCoop = instituicaoCooperativa.getCodTipoGrauCoop();
		if (codTipoGrauCoop == null) {
			throw new IntegracaoInstituicaoNegocioException("MSG_006");
		}		
		
		tipoGrauCooperativaDTO.setCodTipoGrauCoop(codTipoGrauCoop);
		
		return tipoGrauCooperativaDTO;
	}

	/**
	 * Necessario quando precisar de informacoes descritivas da instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	private ISciIntInstituicaoCTA obterInstituicaoCTA(Integer idInstituicao) throws BancoobException {
		ISciIntInstituicaoCTA instituicaoCTA = null;
		try{
			Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
			SciIntHierarquiaInstituicaoCTADelegate hierarquiaInstituicaoCTADelegate = SciIntFabricaDelegate.getInstance().criarSciIntHierarquiaInstituicaoCTADelegate();
			instituicaoCTA = hierarquiaInstituicaoCTADelegate.recuperarInstituicao(numeroCooperativa);
		} catch (SciIntInstituicaoInativaException ie) {
			throw new SciIntInstituicaoInativaException(ie.getMessage());
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoInstituicaoException(e);
		}
		return instituicaoCTA;
	}
	
	/**
	 * Obtem InstituicaoCooperativa do SCI
	 * @param idInstituicao
	 * @return
	 * @throws IntegracaoInstituicaoException
	 */
	private ISciIntInstituicaoCooperativa obterInstituicaoCooperativaSCI(Integer idInstituicao) throws IntegracaoInstituicaoException {
		ISciIntInstituicaoCooperativa instituicaoCooperativa = null;
		try{
			SciIntInstituicaoCooperativaDelegate instituicaoCooperativaDelegate = SciIntFabricaDelegate.getInstance().criarInstituicaoCooperativaDelegate();
			instituicaoCooperativa = instituicaoCooperativaDelegate.obterInstituicaoCooperativaCache(idInstituicao);
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoInstituicaoException(e);
		}
	
		return instituicaoCooperativa;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico#obterTipoCooperativa(java.lang.Integer)
	 */
	public Integer obterTipoCooperativa(Integer idInstituicao) throws BancoobException {		
		return obterInstituicaoCooperativaSCI(idInstituicao).getCodTipoCooperativa();		
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico#listarCentral()
	 */
	public List<ItemListaIntegracaoDTO> listarCentral() throws IntegracaoInstituicaoException {
		List<InstituicaoCooperativaSCIDTO> centrais;
		try {
			centrais = viewInstituicaoServico.listarCentrais();
		} catch (BancoobException e) {
			throw new IntegracaoInstituicaoException(e);
		}
		return montarListaInstituicaoDTO(centrais);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico#listarCentralEConfederacao()
	 */
	public List<ItemListaIntegracaoDTO> listarCentralEConfederacao() throws IntegracaoInstituicaoException {
		List<InstituicaoCooperativaSCIDTO> centrais;
		try {
			centrais = viewInstituicaoServico.listarCentraisEConfederacao();
		} catch (BancoobException e) {
			throw new IntegracaoInstituicaoException(e);
		}
		return montarListaInstituicaoIdInstituicaoDTO(centrais);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico#listarCentralInstituicao()
	 */
	public List<ItemListaIntegracaoDTO> listarCentralInstituicao() throws IntegracaoInstituicaoException {
		List<InstituicaoCooperativaSCIDTO> centrais;
		try {
			centrais = viewInstituicaoServico.listarCentrais();
		} catch (BancoobException e) {
			throw new IntegracaoInstituicaoException(e);
		}
		return montarListaInstituicaoIdInstituicaoDTO(centrais);
	}
		
	/**
	 * Montar lista instituicao dto.
	 *
	 * @param lista o valor de lista
	 * @return List
	 */
	private List<ItemListaIntegracaoDTO> montarListaInstituicaoDTO(List<InstituicaoCooperativaSCIDTO> lista) {
		List<ItemListaIntegracaoDTO> listaVO = new ArrayList<ItemListaIntegracaoDTO>();

		for(InstituicaoCooperativaSCIDTO instituicao:lista){
			ItemListaIntegracaoDTO item = new ItemListaIntegracaoDTO(instituicao.getNumCooperativa().toString(), instituicao.getNumCooperativa() + " - " + instituicao.getNome());
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
	 * Montar lista instituicao id instituicao dto.
	 *
	 * @param lista o valor de lista
	 * @return List
	 */
	private List<ItemListaIntegracaoDTO> montarListaInstituicaoIdInstituicaoDTO(List<InstituicaoCooperativaSCIDTO> lista) {
		List<ItemListaIntegracaoDTO> listaVO = new ArrayList<ItemListaIntegracaoDTO>();

		for(InstituicaoCooperativaSCIDTO instituicao:lista){
			String cooperativa = instituicao.getNumCooperativa().toString();
			if(instituicao.getNumCooperativa() == 300) {
				cooperativa = "0300";
			}
			ItemListaIntegracaoDTO item = new ItemListaIntegracaoDTO(instituicao.getIdInstituicao().toString(), cooperativa + " - " + instituicao.getNome());
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
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico#listarSingulares(java.lang.Integer)
	 */
	public List<ItemListaIntegracaoDTO> listarSingulares(Integer idInstituicao) throws IntegracaoInstituicaoException {
		List<InstituicaoCooperativaSCIDTO> singulares = null; 
		Integer cooperativa;
		try {
			cooperativa = obterNumeroCooperativa(idInstituicao);
			singulares = viewInstituicaoServico.consultarCooperativasAtivas(cooperativa);
		} catch (BancoobException e) {
			throw new IntegracaoInstituicaoException(e);
		}
		return montarListaInstituicaoDTO(singulares);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico#listarSingularesInstituicao(java.lang.Integer)
	 */
	public List<ItemListaIntegracaoDTO> listarSingularesInstituicao(Integer idInstituicao) throws IntegracaoInstituicaoException {
		List<InstituicaoCooperativaSCIDTO> singulares = null; 
		Integer cooperativa;
		try {
			cooperativa = obterNumeroCooperativa(idInstituicao);
			singulares = viewInstituicaoServico.consultarCooperativasAtivas(cooperativa);
		} catch (BancoobException e) {
			throw new IntegracaoInstituicaoException(e);
		}
		return montarListaInstituicaoIdInstituicaoDTO(singulares);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico#consultarCentralCooperativa(java.lang.Integer)
	 */
	public CentralCooperativaDTO consultarCentralCooperativa(Integer idInstituicao) throws BancoobException {
		CentralCooperativaDTO dto = new CentralCooperativaDTO();
		
		if(EnumInstituicao.ID_BANCOOB.getIdInstituicao() == idInstituicao) {
			dto.setNumCentral(null);
			dto.setDescCentral(null);
			dto.setNumCooperativa(null);
			dto.setDescCooperativa(null);
			dto.setIdInstituicaoCentral(null);
			dto.setIdInstituicaoCooperativa(null);
			return dto;
		}
		
		ISciIntInstituicaoCTA instituicao = obterInstituicaoCTA(idInstituicao);
		if (instituicao == null) {
			throw new IntegracaoInstituicaoNegocioException("MSG_004");
		}	
		
		if(EnumInstituicao.ID_SICOOB.getIdInstituicao() == idInstituicao) {
			dto.setNumCentral(instituicao.getNumeroCooperativa().shortValue());
			dto.setDescCentral(instituicao.getSiglaInstituicao());
			dto.setIdInstituicaoCentral(instituicao.getIdInstituicao());
			dto.setNumCooperativa(null);
			dto.setDescCooperativa(null);
			dto.setIdInstituicaoCooperativa(null);
		} else if (obterTipoGrauCooperativa(idInstituicao).isCentral()){
			dto.setNumCentral(instituicao.getNumeroCooperativa().shortValue());
			dto.setDescCentral(instituicao.getSiglaInstituicao());
			dto.setIdInstituicaoCentral(instituicao.getIdInstituicao());
			dto.setNumCooperativa(null);
			dto.setDescCooperativa(null);
			dto.setIdInstituicaoCooperativa(null);
		}else{		
			Integer idInstituicaoCentral = consultarIdInstitucaoCentralporSingular(idInstituicao);
			ISciIntInstituicaoCTA instituicaoCentral = obterInstituicaoCTA(idInstituicaoCentral);
			
			dto.setNumCentral(instituicaoCentral.getNumeroCooperativa().shortValue());			
			dto.setDescCentral(instituicaoCentral.getSiglaInstituicao());			
			dto.setIdInstituicaoCentral(instituicaoCentral.getIdInstituicao());
			dto.setNumCooperativa(instituicao.getNumeroCooperativa().shortValue());
			dto.setDescCooperativa(instituicao.getSiglaInstituicao());			
			dto.setIdInstituicaoCooperativa(instituicao.getIdInstituicao());
		}
		
		return dto;
	}	

	/**
	 * Consulta o idInstituicao Pai (Central) no SCI pelo idInstituicao da Singular
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Integer consultarIdInstitucaoCentralporSingular(Integer idInstituicao) throws BancoobException{
		SciIntFiltroHierarquiaCooperativa filtro = new SciIntFiltroHierarquiaCooperativa();
		filtro.setIdInstituicao(idInstituicao);
		
		SciIntHierarquiaCooperativaDelegate delegate = SciIntFabricaDelegate.getInstance().criarSciIntHierarquiaCooperativaDelegate();
		try {
			return delegate.pesquisarHierarquiaCooperativaNivelMenor(filtro).getIdInstituicaoResp();
		} catch (SciIntegracaoException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoInstituicaoCooperativaException(e);
		}
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico#obterInstituicaoIntegracao(java.lang.Integer)
	 */
	public InstituicaoIntegracaoDTO obterInstituicaoIntegracao(Integer idInstituicao) throws BancoobException {
		InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = new InstituicaoIntegracaoDTO();
		ISciIntInstituicaoCTA instituicao = obterInstituicaoCTA(idInstituicao); 
		
		if (instituicao == null) {
			throw new IntegracaoInstituicaoNegocioException("MSG_004");
		}			
		
		instituicaoIntegracaoDTO.setDescInstituicao(instituicao.getDescricaoInstituicao());
		instituicaoIntegracaoDTO.setIdInstituicao(instituicao.getIdInstituicao());
		instituicaoIntegracaoDTO.setNomeInstituicao(instituicao.getNomeInstituicao());
		instituicaoIntegracaoDTO.setNumero(instituicao.getNumeroCooperativa().toString());
		instituicaoIntegracaoDTO.setSiglaInstituicao(instituicao.getSiglaInstituicao());
		instituicaoIntegracaoDTO.setSituacaoInst(instituicao.getCodigoSituacaoInstituicao());
	
		return instituicaoIntegracaoDTO;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico#obterInstituicaoIntegracaoPorNumCoop(java.lang.Integer)
	 */
	public InstituicaoIntegracaoDTO obterInstituicaoIntegracaoPorNumCoop(Integer numCoop) throws BancoobException {
		Integer idInstituicao = this.obterIdInstituicao(numCoop);
		return this.obterInstituicaoIntegracao(idInstituicao);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico#obterInformacoesInstituicaoSCI(java.lang.Integer)
	 */
	public InstituicaoIntegracaoDTO obterInformacoesInstituicaoSCI(Integer idInstituicaoSCI) throws BancoobException {
		SciIntInstituicaoDelegate sciDel = SciIntFabricaDelegate.getInstance().criarInstituicaoDelegate();
	    SciIntFiltroInstituicaoGeral filtro = new SciIntFiltroInstituicaoGeral();
	    filtro.setIdInstituicao(idInstituicaoSCI);
	    filtro.setIdUnidadeInstituicao(EnumUnidadeInstituicao.MATRIZ.getCodigoUnidadeInstituicao());
	    List<ISciIntInstituicaoGeral> lst = sciDel.pesquisarInformacoesGeraisInstituicao(filtro);
	    if (lst != null && !lst.isEmpty()) {
	        return montarInstituicaoIntegracaoDTO(lst);
	      }
	    return null;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico#obterInformacoesInstituicaoSCI(java.lang.Integer, java.lang.String)
	 */
	public InstituicaoIntegracaoDTO obterInformacoesInstituicaoSCI(Integer idInstituicaoSCI, String pac) throws BancoobException {
		SciIntInstituicaoDelegate sciDel = SciIntFabricaDelegate.getInstance().criarInstituicaoDelegate();
	    SciIntFiltroInstituicaoGeral filtro = new SciIntFiltroInstituicaoGeral();
	    filtro.setIdInstituicao(idInstituicaoSCI);
	    filtro.setIdUnidadeInstituicao(Integer.valueOf(pac));
	    List<ISciIntInstituicaoGeral> lst = sciDel.pesquisarInformacoesGeraisInstituicao(filtro);
	    if (lst != null && !lst.isEmpty()) {
	        return montarInstituicaoIntegracaoDTO(lst);
	      }
	    return null;
	}
	
	private InstituicaoIntegracaoDTO montarInstituicaoIntegracaoDTO(List<ISciIntInstituicaoGeral> lst) throws BancoobException {
		ISciIntInstituicaoGeral iSciInst = lst.get(0);
		InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = new InstituicaoIntegracaoDTO();
		instituicaoIntegracaoDTO.setNomeInstituicao(iSciInst.getNomeInstituicao());
		instituicaoIntegracaoDTO.setDescInstituicao(iSciInst.getDescInstituicao());
		instituicaoIntegracaoDTO.setIdInstituicao(iSciInst.getIdInstituicao());
		instituicaoIntegracaoDTO.setCodigoSituacaoEspecial(iSciInst.getCodigoSituacaoEspecial());
		instituicaoIntegracaoDTO.setNumCNPJ(iSciInst.getNumCnpj());
		if(iSciInst.getDataSituacaoEspecial() != null) {
			instituicaoIntegracaoDTO.setDataSituacaoEspecial(new DateTime(iSciInst.getDataSituacaoEspecial().getTime()));
		}
		instituicaoIntegracaoDTO.setSituacaoInst(iSciInst.getCodSituacaoInstituicao());
		instituicaoIntegracaoDTO.setSiglaInstituicao(iSciInst.getSiglaInstituicao());			
		instituicaoIntegracaoDTO.setDescEndereco(iSciInst.getDescEndereco());
		instituicaoIntegracaoDTO.setNumero(iSciInst.getNumero() == null ? null : iSciInst.getNumero().toString());
		instituicaoIntegracaoDTO.setNomeCidade(iSciInst.getNomeCidade());
		instituicaoIntegracaoDTO.setIdLocalidade(iSciInst.getIdLocalidade());
		
		if (instituicaoIntegracaoDTO.getIdLocalidade() != null) {
			LocalizacaoIntegracaoDTO loc = pesquisarLocalizacao(instituicaoIntegracaoDTO.getIdLocalidade());
			if (loc != null) {
				instituicaoIntegracaoDTO.setUf(loc.getUf());
			}
		}
		
		return instituicaoIntegracaoDTO;
	}

	private LocalizacaoIntegracaoDTO pesquisarLocalizacao(Integer idLocalidade) throws BancoobException {
		return LocalizacaoIntegracaoDelegate.getInstance().consultarLocalidade(idLocalidade);
	}
	
}
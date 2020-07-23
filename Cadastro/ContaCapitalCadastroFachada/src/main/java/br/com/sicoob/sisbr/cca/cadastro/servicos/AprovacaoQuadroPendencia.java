/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.servico.ServicoBancoob;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.cca.cadastro.negocio.delegates.AprovacaoContaCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.relatorios.negocio.delegates.ContaCapitalRelatoriosFabricaDelegates;
import br.com.sicoob.cca.relatorios.negocio.delegates.RelAprovacaoQuadroPendenciaDelegate;
import br.com.sicoob.cca.relatorios.negocio.dto.RelAprovacaoQuadroPendenciaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.DocumentoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.GftIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.DocumentoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;
import br.com.sicoob.sisbr.ged.quadro.interfaces.IGEDQuadroPendencia;
import br.com.sicoob.sisbr.ged.quadro.vo.DocumentoQuadroVO;
import br.com.sicoob.sisbr.ged.quadro.vo.PendenciaVO;

/**
 * @author Marco.Nascimento
 */
@RemoteService
public class AprovacaoQuadroPendencia extends ServicoBancoob implements IGEDQuadroPendencia {
	
	/** O atributo aprovacaoDelegate. */
	private AprovacaoContaCapitalDelegate aprovacaoDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarAprovacaoContaCapitalDelegate();
	
	private GftIntegracaoDelegate gftIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarGftIntegracaoDelegate();
	
	/** O atributo contaCapitalDelegate. */
	private ContaCapitalDelegate contaCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate();
	
	private RelAprovacaoQuadroPendenciaDelegate  relAprovQuadDelegate = ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelAprovacaoQuadroPendenciaDelegate();
	
	/** O atributo docInt. */
	private DocumentoIntegracaoDelegate docInt = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarDocumentoIntegracaoDelegate();
	
	/**
	 * @see br.com.sicoob.sisbr.ged.quadro.interfaces.IGEDQuadroPendencia#recuperarDescricaoPendencia(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
	 */
	public RetornoDTO recuperarDescricaoPendencia(RequisicaoReqDTO dto) throws BancoobException {
		return null;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.ged.quadro.interfaces.IGEDQuadroPendencia#executarProcedimento(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
	 */
	public RetornoDTO executarProcedimento(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		PendenciaVO pendenciaVO = (PendenciaVO) dto.getDados().get("pendenciaVO");
		
		ContaCapital contaCapital = contaCapitalDelegate.obter(Integer.valueOf(pendenciaVO.getIdRegistroControlado()));
		
		String justificativa = (String) dto.getDados().get("justificativa");
		if(justificativa != null) {
			final int tamanhoMaximo = 200;
			if(justificativa.length() > tamanhoMaximo) {
				contaCapital.setDescObsAprovacao(justificativa.substring(0, tamanhoMaximo));
			} else {
				contaCapital.setDescObsAprovacao(justificativa);	
			}
		}
		
		ItemListaIntegracaoDTO itemProcedimento = recuperarItemProcedimento(pendenciaVO.getAtividadeVO().getCodigo(), dto.getDados().get("procedimento").toString());
		
		aprovacaoDelegate.aprovar(contaCapital, pendenciaVO.getAtividadeVO().getCodigo(), pendenciaVO.getIdOcorrenciaDirecionamentoAtividade(), Integer.valueOf(itemProcedimento.getCodListaItem()), itemProcedimento.getDescListaItem());

		return retornoDTO;
	}
	
	private ItemListaIntegracaoDTO recuperarItemProcedimento(Integer idAtividade, String nomeProcedimento) throws BancoobException {
		GftIntegracaoDTO filtro = new GftIntegracaoDTO();
		filtro.setIdAtividade(idAtividade);
		
		List<ItemListaIntegracaoDTO> lstProcedimento = gftIntegracaoDelegate.listaProcedimentosAprovacao(filtro);
		for(ItemListaIntegracaoDTO item : lstProcedimento) {
			if(item.getDescListaItem().equals(nomeProcedimento)) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.ged.quadro.interfaces.IGEDQuadroPendencia#emitirDocumento(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
	 */
	public RetornoDTO emitirDocumento(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Integer idContaCapital = Integer.valueOf(reqDTO.getDados().get("idRegistroControlado").toString());
		
		ContaCapital cca = contaCapitalDelegate.obter(idContaCapital);

		RelAprovacaoQuadroPendenciaDTO dtoRel = new RelAprovacaoQuadroPendenciaDTO();
		dtoRel.setIdInstituicao(cca.getIdInstituicao());
		dtoRel.setIdPessoa(cca.getIdPessoa());
		dtoRel.setIdContaCapital(cca.getId());
		
		ContextoHttp.getInstance().adicionarContexto("Documento_Quadro", relAprovQuadDelegate.gerarRelatorioAprovacaoQuadroPendencia(dtoRel));
		
		return retornoDTO;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.ged.quadro.interfaces.IGEDQuadroPendencia#recuperarDocumentos(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
	 */
	public RetornoDTO recuperarDocumentos(RequisicaoReqDTO dto) throws BancoobException {
		PendenciaVO pendenciaVO = (PendenciaVO) dto.getDados().get("pendenciaVO");
		
		Long idDocumento = null;
		//No Caso da aprovacao, sempre havera apenas um documento, sem dossiê 
		ContaCapital cca = contaCapitalDelegate.obter(Integer.valueOf(pendenciaVO.getIdRegistroControlado()));
		if(cca.getDocumentos() != null && cca.getDocumentos().size() > 0) {
			idDocumento = cca.getDocumentos().get(0).getId();
		}
		
		RetornoDTO retornoDTO = new RetornoDTO();
		if(idDocumento != null) {
			DocumentoIntegracaoDTO docDTO = docInt.recuperarDocumento(idDocumento);
			DocumentoQuadroVO docQuadroVO = new DocumentoQuadroVO();
			docQuadroVO.setIdDocumento(docDTO.getIdDocumento());
			docQuadroVO.setDescricaoTipoDocumento(docDTO.getDescTipoDoc());
			docQuadroVO.setBolDocVisualizado(false);
			
			List<DocumentoQuadroVO> listaDocumentosQuadro = new ArrayList<DocumentoQuadroVO>();
			listaDocumentosQuadro.add(docQuadroVO);
			retornoDTO.getDados().put("listaDocumentosQuadro", listaDocumentosQuadro);
		}
		
		return retornoDTO;
	}
}
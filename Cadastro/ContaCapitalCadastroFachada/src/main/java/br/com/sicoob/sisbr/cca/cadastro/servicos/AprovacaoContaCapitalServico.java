/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.cadastro.negocio.delegates.AprovacaoContaCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.DocumentoCapitalDelegate;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.DocumentoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoDocumentoCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoDocumento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.cca.cadastro.CadastroContaCapital;
import br.com.sicoob.sisbr.cca.cadastro.conversor.ConversorCadastroContaCapitalRen;
import br.com.sicoob.sisbr.cca.cadastro.vo.CadastroContaCapitalRenVO;
import br.com.sicoob.sisbr.cca.cadastro.vo.DocumentoCapitalVO;
import br.com.sicoob.sisbr.cca.cadastro.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.GftIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;

/**
 * @author marco.nascimento
 */
@RemoteService
public class AprovacaoContaCapitalServico extends CadastroContaCapital {
	
	/** O atributo documentoCapitalDelegate. */
	private DocumentoCapitalDelegate documentoCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarDocumentoCapitalDelegate();
	
	/** O atributo contaCapitalDelegate. */
	private ContaCapitalDelegate ccaDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate();
	
	/** O atributo aprovacaoDelegate. */
	private AprovacaoContaCapitalDelegate aprovacaoDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarAprovacaoContaCapitalDelegate();
	
	/** O atributo gftIntDelegate. */
	private GftIntegracaoDelegate gftIntDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarGftIntegracaoDelegate();
	
	/** O atributo conversor. */
	private ConversorCadastroContaCapitalRen conversor = new ConversorCadastroContaCapitalRen();
	
	/**
	 * Apresentacao inicial da tela
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		CadastroContaCapitalRenVO vo = (CadastroContaCapitalRenVO) reqDTO.getDados().get("vo");
		
		ContaCapital cca = ccaDelegate.obter(vo.getIdContaCapital());
		
		conversor.entidadeParaVo(cca, vo);
		
		retornoDTO.getDados().put("vo", vo);
		retornoDTO.getDados().put("documentos", vo.getDocumentos());
		retornoDTO.getDados().put("cboSituacaoCadastro", criarComboSituacaoCadastro(vo));
		retornoDTO.getDados().put("descTipoIntegralizacao", EnumTipoIntegralizacao.buscarPorCodigo(vo.getTipoInteg()).getDescricao());
		
		return retornoDTO;
	}
	
	/**
	 * Apresentacao inicial da tela Aprovacao -> Documentos
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoesDocumentos(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		CadastroContaCapitalRenVO vo = (CadastroContaCapitalRenVO) reqDTO.getDados().get("vo");
		
		ContaCapital cca = ccaDelegate.obter(vo.getIdContaCapital());
		conversor.entidadeParaVo(cca, vo);
		
		retornoDTO.getDados().put("vo", vo);
		retornoDTO.getDados().put("documentos", vo.getDocumentos());
		retornoDTO.getDados().put("cboSituacaoCadastro", criarComboSituacaoCadastro(vo));
		retornoDTO.getDados().put("descTipoIntegralizacao", EnumTipoIntegralizacao.buscarPorCodigo(vo.getTipoInteg()).getDescricao());
		
		return retornoDTO;
	}
	
	/**
	 * Altera situacao da conta capital, andamento ao fluxo de aprovacao da proposta   
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO aprovar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		CadastroContaCapitalRenVO vo = (CadastroContaCapitalRenVO) reqDTO.getDados().get("vo");
		
		ContaCapital contaCapital = ccaDelegate.obter(vo.getIdContaCapital());
		contaCapital.setDescObsAprovacao(vo.getObservacao());
		
		Integer idProcedimento = Integer.valueOf(reqDTO.getDados().get("idProcedimento").toString());
		String nomeProcedimento = reqDTO.getDados().get("nomeProcedimento").toString();
		
		aprovacaoDelegate.aprovar(contaCapital, vo.getIdAtividade(), vo.getIdOcorrenciaAtividade(), idProcedimento, nomeProcedimento);

		String descSituacaoAtualProposta = EnumSituacaoCadastroProposta.find(contaCapital.getSituacaoCadastroProposta().getId()).getDescricao();
		retornoDTO.getDados().put("descSituacaoAtualProposta", descSituacaoAtualProposta);
		retornoDTO.getDados().put("idSituacaoAtualProposta", contaCapital.getSituacaoCadastroProposta().getId());
		
		return retornoDTO;
	}
	
	/**
	 * Combo com tipos de situacoes possiveis do fluxo de aprovacao
	 * @return
	 * @throws BancoobException
	 */
	private List<ItemListaVO> criarComboSituacaoCadastro(CadastroContaCapitalRenVO vo) throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		lista.add(new ItemListaVO("-1", "SELECIONE"));
		
		GftIntegracaoDTO gftDTO = new GftIntegracaoDTO();
		gftDTO.setIdRegistroControlado(vo.getIdContaCapital());
		gftDTO.setIdAtividade(vo.getIdAtividade());
		
		for(ItemListaIntegracaoDTO dto : gftIntDelegate.listaProcedimentosAprovacao(gftDTO)) {
			lista.add(new ItemListaVO(dto.getCodListaItem(), dto.getDescListaItem()));
		}
		
		return lista;
	}
	
	/**
	 * Cria vinculo DocumentoCapital x GED
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO vincularDocumentos(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		CadastroContaCapitalRenVO vo = (CadastroContaCapitalRenVO) reqDTO.getDados().get("vo");

		Object[] idDocs = (Object[]) reqDTO.getDados().get("idDocs");
		
		if(vo != null && vo.getIdContaCapital() != null && idDocs != null && idDocs.length > 0) {
			
			List<DocumentoCapitalVO> documentos = new ArrayList<DocumentoCapitalVO>(0);
			
			for (Object idDoc : idDocs) {
				DocumentoCapital doc = criarDocumentoCapital(vo.getIdContaCapital(), Double.valueOf(idDoc.toString()).longValue());
				documentos.add(conversor.documentoEntidadeParaVO(doc));
			}
			
			retornoDTO.getDados().put("documentos", documentos);
		}
		
		return retornoDTO;
	}
	
	/**
	 * Exclui documento capital e marca o mesmo para expurgo no GED
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO excluirDocumento(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		DocumentoCapitalVO docVO = (DocumentoCapitalVO) reqDTO.getDados().get("documentoVO");
		
		if(docVO != null && docVO.getIdDocumento() != null) {
			documentoCapitalDelegate.excluir(docVO.getIdDocumento());
		}

		return retornoDTO;
	}
	
	/**
	 * Cria DocumentoCapital vinculado ao GED
	 * @param idContaCapital
	 * @param idDocumento
	 * @return
	 * @throws BancoobException
	 */
	private DocumentoCapital criarDocumentoCapital(Integer idContaCapital, Long idDocumento) throws BancoobException {
		DocumentoCapital documentoCapital = null;
		
		try {
			
			documentoCapital = new DocumentoCapital();
			documentoCapital.setId(idDocumento);
			documentoCapital.setContaCapital(ccaDelegate.obter(idContaCapital));
			documentoCapital.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
			documentoCapital.setDataHoraAtualizacao(new DateTimeDB());
			documentoCapital.setTipoDocumento(new TipoDocumentoCapital(EnumTipoDocumento.FICHA_PROPOSTA_DE_MATRICULA.getCodigo()));
			documentoCapital.setNome(EnumTipoDocumento.FICHA_PROPOSTA_DE_MATRICULA.getDescricao());
			
			documentoCapitalDelegate.incluir(documentoCapital);
			
		} catch (BancoobException e) {
			SicoobLoggerPadrao.getInstance(getClass()).erro(e, e.getMessage());
			throw new BancoobException(e);
		}
		
		return documentoCapital;
	}
}
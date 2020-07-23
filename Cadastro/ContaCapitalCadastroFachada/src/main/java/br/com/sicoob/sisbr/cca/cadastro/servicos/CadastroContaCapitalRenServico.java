/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.servicos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cca.cadastro.negocio.delegates.CadastroContaCapitalRenDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.DocumentoCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.SituacaoCadastroPropostaDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorConfiguracaoCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorCotaDelegate;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.cadastro.negocio.excecao.ContaCapitalCadastradaNegocioException;
import br.com.sicoob.cca.cadastro.negocio.excecao.ContaCapitalCadastroNegocioException;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.DocumentoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoDocumentoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoDocumento;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.cca.cadastro.CadastroContaCapital;
import br.com.sicoob.sisbr.cca.cadastro.conversor.ConversorCadastroContaCapitalRen;
import br.com.sicoob.sisbr.cca.cadastro.vo.CadastroContaCapitalRenVO;
import br.com.sicoob.sisbr.cca.cadastro.vo.DocumentoCapitalVO;
import br.com.sicoob.sisbr.cca.cadastro.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ProdutoLegadoDelegate;

/**
 * @author marco.nascimento
 */
@RemoteService
public class CadastroContaCapitalRenServico extends CadastroContaCapital {
	
	/** O atributo instituicaoDelegate. */
	private InstituicaoIntegracaoDelegate instituicaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();
	
	/** O atributo prodLegadoDelegate. */
	private ProdutoLegadoDelegate prodLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarProdutoLegadoDelegate();	
	
	/** O atributo situacaoCadastroDelegate. */
	private SituacaoCadastroPropostaDelegate situacaoCadastroDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarSituacaoCadastroPropostaDelegate();
	
	/** O atributo cadastroRenDelegate. */
	private CadastroContaCapitalRenDelegate cadastroRenDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarCadastroContaCapitalRenDelegate();
	
	/** O atributo documentoCapitalDelegate. */
	private DocumentoCapitalDelegate documentoCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarDocumentoCapitalDelegate();
	
	/** O atributo contaCapitalDelegate. */
	private ContaCapitalDelegate ccaDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate();
	
	/** O atributo valorCotaDelegate. */
	private ValorCotaDelegate valorCotaDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorCotaDelegate();
	
	/** O atributo valorConfDelegate. */
	private ValorConfiguracaoCapitalDelegate valorConfDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorConfiguracaoCapitalDelegate();
	
	/** O atributo ccaUtil. */
	private CadastroContaCapitalUtil ccaUtil = new CadastroContaCapitalUtil();
	
	/** O atributo conversor. */
	private ConversorCadastroContaCapitalRen conversor = new ConversorCadastroContaCapitalRen();
	
	/** A constante MSG_DADOS_GRAVADOS. */
	private static final String MSG_DADOS_GRAVADOS = "Dados gravados com sucesso.";
	
	/**
	 * Definicoes iniciais da tela de inclusao
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		String dataInclusaoFormatada = DataUtil.converterDateToString(prodLegadoDelegate.obterDataAtualProdutoCCALogado(), "dd/MM/yyyy");
		retornoDTO.getDados().put("dataInclusao", dataInclusaoFormatada);
		
		retornoDTO.getDados().put("comboTipoInteg", ccaUtil.criarComboTipoIntegralizacao(ContaCapitalConstantes.COD_PROPOSTA_CADASTRO));
		retornoDTO.getDados().put("comboSituacaoCadastro", criarComboSituacaoCadastro());
		
		SicoobLoggerPadrao.getInstance(getClass()).alerta("obterDefinicoes -> idPessoaSelecionada=" + reqDTO.getDados().get("idPessoaSelecionada"));
		if(reqDTO.getDados().get("idPessoaSelecionada") != null) {
			Integer idPessoa = Integer.valueOf(reqDTO.getDados().get("idPessoaSelecionada").toString());
			Integer idInstituicao = Integer.valueOf(reqDTO.getDados().get("idInstituicao").toString());
			
			retornoDTO.getDados().put("vlrSubs", valorCotaDelegate.obterValorMinimoSubscricao(idInstituicao, idPessoa));
			retornoDTO.getDados().put("vlrInteg", valorCotaDelegate.obterValorMinimoIntegralizacao(idInstituicao));
			retornoDTO.getDados().put("numContaCapitalGerada", gerarNovaContaCapital(idInstituicao));
			retornoDTO.getDados().put("permiteAlterarMatricula", permiteAlterarMatricula());
		}
		
		Integer idInstituicao = instituicaoDelegate.obterIdInstituicao(Integer.valueOf(InformacoesUsuario.getInstance().getCooperativa().toString()));	
		Integer tipoGrauCoopetativa = instituicaoDelegate.obterTipoGrauCooperativa(idInstituicao).getCodTipoGrauCoop();	
		retornoDTO.getDados().put("tipoGrauCoopetativa", tipoGrauCoopetativa);
			
		return retornoDTO;
	}
	
	/**
	 * Definicoes iniciais da tela de alteração
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoesAlterar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		CadastroContaCapitalRenVO vo = (CadastroContaCapitalRenVO) reqDTO.getDados().get("vo");
		
		ContaCapital cca = ccaDelegate.obter(vo.getIdContaCapital());
		
		conversor.entidadeParaVo(cca, vo);
		
		retornoDTO.getDados().put("vo", vo);
		retornoDTO.getDados().put("documentos", vo.getDocumentos());
		retornoDTO.getDados().put("comboTipoInteg", ccaUtil.criarComboTipoIntegralizacao(ContaCapitalConstantes.COD_PROPOSTA_CADASTRO));
		
		return retornoDTO;
	}
	
	/**
	 * Inclusao SQL e DB2
	 * @param reqDTO
	 * @return msg 
	 * @throws BancoobException
	 */
	public RetornoDTO incluir(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		CadastroContaCapitalRenVO vo = (CadastroContaCapitalRenVO) reqDTO.getDados().get("vo");
		CadastroContaCapitalRenDTO dto = new CadastroContaCapitalRenDTO();
		conversor.voParaDto(vo, dto);
		
		try {
			
			dto = cadastroRenDelegate.incluir(dto);
			
		} catch (ContaCapitalCadastradaNegocioException e) {
			retornoDTO.getDados().put("numContaCapitalGerada", gerarNovaContaCapital(vo.getIdInstituicao()));
			retornoDTO.getDados().put("msg", e.getMessage());
			return retornoDTO;
			
		} catch (ContaCapitalCadastroNegocioException e) {
			retornoDTO.getDados().put("erroNegocial", true);
			retornoDTO.getDados().put("msg", e.getMessage());
			return retornoDTO;
		}
		
		conversor.dtoParaVo(vo, dto);

		retornoDTO.getDados().put("vo", vo);
		
		retornoDTO.getDados().put("msg", MSG_DADOS_GRAVADOS);
		
		return retornoDTO;
	}
	
	/**
	 * Alteracao SQL e DB2
	 * @param reqDTO
	 * @return msg 
	 * @throws BancoobException
	 */
	public RetornoDTO alterar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		CadastroContaCapitalRenVO vo = (CadastroContaCapitalRenVO) reqDTO.getDados().get("vo");
		CadastroContaCapitalRenDTO dto = new CadastroContaCapitalRenDTO();
		conversor.voParaDto(vo, dto);
		
		try {
			
			if (reqDTO.getDados().get("isDadosPropostaAlterados") != null && (Boolean) reqDTO.getDados().get("isDadosPropostaAlterados")) {
				cadastroRenDelegate.alterar(dto);
			}		
			
			if (reqDTO.getDados().get("atualizarSituacaoCadastro") != null && (Boolean) reqDTO.getDados().get("atualizarSituacaoCadastro")) {
				ccaDelegate.atualizarSituacaoCadastro(vo.getIdContaCapital());
			}																  	
			
		} catch (ContaCapitalCadastroNegocioException e) {
			
			retornoDTO.getDados().put("erroNegocial", true);
			retornoDTO.getDados().put("msg", e.getMessage());
			return retornoDTO;
		}
		
		conversor.dtoParaVo(vo, dto);

		retornoDTO.getDados().put("vo", vo);
		retornoDTO.getDados().put("msg", MSG_DADOS_GRAVADOS);
		
		return retornoDTO;
	}
	
	/**
	 * Verifica se e possivel editar o numero da conta capital
	 * @return
	 * @throws BancoobException
	 */
	private boolean permiteAlterarMatricula() throws BancoobException {
		Integer idInstUsu = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		ValorConfiguracaoCapital valorConf = valorConfDelegate.obterValorConfiguracao(ContaCapitalConstantes.PAR_PERMITE_ALTERAR_MATRICULA, idInstUsu);
		return valorConf.getValorConfiguracao().equals("1");
	}
	
	/**
	 * Gera novo numero de conta capital de acordo com a instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	private Integer gerarNovaContaCapital(Integer idInstituicao) throws BancoobException {
		return cadastroRenDelegate.obterNovaContaCapital(idInstituicao);
	}
	
	/**
	 * Combo de situacao cadastro
	 * @return
	 * @throws BancoobException
	 */
	private List<ItemListaVO> criarComboSituacaoCadastro() throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		for(SituacaoCadastroProposta sit : situacaoCadastroDelegate.listar()) {
			lista.add(new ItemListaVO(sit.getId().toString(), sit.getDescricao()));
		}
		return lista;
	}
	
	/**
	 * Obtem numero cooperativa por instituicao
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterNumeroCooperativa(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		if(reqDTO.getDados().get("idInstituicao") != null) {
			Integer idInstituicao = Integer.valueOf(reqDTO.getDados().get("idInstituicao").toString());
			Integer numCoop = instituicaoDelegate.obterNumeroCooperativa(idInstituicao);
			
			retorno.getDados().put("numCoop", numCoop);
			retorno.getDados().put("permiteAlterarMatricula", permiteAlterarMatricula());
			retorno.getDados().put("vlrSubs", valorCotaDelegate.obterValorMinimoSubscricao(idInstituicao, null));
			retorno.getDados().put("vlrInteg", valorCotaDelegate.obterValorMinimoIntegralizacao(idInstituicao));
		}
		
		return retorno;
	}
	
	/**
	 * Novo numero conta capital por instituicao
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterNovoNumContaCapital(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		if(reqDTO.getDados().get("idInstituicao") != null) {
			Integer idInstituicao = Integer.valueOf(reqDTO.getDados().get("idInstituicao").toString());
			retorno.getDados().put("numContaCapitalGerada", gerarNovaContaCapital(idInstituicao));
		}
		
		return retorno;
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

		Object[] docs = null;
		
		docs = (Object[]) reqDTO.getDados().get("docs");	
		
		if(vo != null && vo.getIdContaCapital() != null && isDocsDoDtoValido(reqDTO)) {
			
			List<DocumentoCapitalVO> documentos = geraListaDeDocumentoCapitalVO(docs, vo.getIdContaCapital());			
			
			ContaCapital contaCapital = ccaDelegate.obter(vo.getIdContaCapital());
			
			if(contaCapital != null && contaCapital.getSituacaoCadastroProposta().getId().equals(EnumSituacaoCadastroProposta.COD_APROVADO.getCodigo())) {
				retornoDTO.getDados().put("isPropostaAprovada", true);
			}
			
			retornoDTO.getDados().put("documentos", documentos);
		}
		
		return retornoDTO;
	}
	
	/**
	 * Verifica se lista de documentos está preenchida
	 * @param reqDTO
	 * @return
	 */
	private boolean isDocsDoDtoValido(RequisicaoReqDTO reqDTO) {
		Object docs = reqDTO.getDados().get("docs");  
		return docs != null && (docs instanceof Object[]) && ((Object[]) docs).length > 0;
	}
	
	/**
	 * Transforma lista dos documentos em lista de DocumentoCapitalVO
	 * @param docs
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	private List<DocumentoCapitalVO> geraListaDeDocumentoCapitalVO(Object[] docs, int idContaCapital) throws BancoobException{
		List<DocumentoCapitalVO> documentos = new ArrayList<DocumentoCapitalVO>(0);
		for (Object doc : docs) {
			if(doc instanceof HashMap) {
				HashMap<Object, Object> docMap = (HashMap<Object, Object>) doc;
				
				long idDocumento = Double.valueOf(docMap.get("idDocumento").toString()).longValue();
				String siglaTipoDocumento = docMap.get("siglaTipoDocumento").toString();
				
				DocumentoCapital documentoCapital = criarDocumentoCapital(
						idContaCapital,
						idDocumento,
						siglaTipoDocumento
				);
				
				documentos.add(conversor.documentoEntidadeParaVO(documentoCapital));
			}
		}
		
		return documentos;
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
		
		retornoDTO.getDados().put("idDocumentoExcluido", docVO.getIdDocumento());

		return retornoDTO;
	}
	
	/**
	 * Cria DocumentoCapital vinculado ao GED
	 * @param idContaCapital
	 * @param idDocumento
	 * @return
	 * @throws BancoobException
	 */
	private DocumentoCapital criarDocumentoCapital(Integer idContaCapital, Long idDocumento, String siglaTipoDocumento) throws BancoobException {
		DocumentoCapital documentoCapital = null;
		
		try {
			
			documentoCapital = new DocumentoCapital();
			documentoCapital.setId(idDocumento);
			documentoCapital.setContaCapital(ccaDelegate.obter(idContaCapital));
			documentoCapital.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
			documentoCapital.setDataHoraAtualizacao(new DateTimeDB());
			
			EnumTipoDocumento tipoDocumento = EnumTipoDocumento.bsucarPorSigla(siglaTipoDocumento);
			documentoCapital.setTipoDocumento(new TipoDocumentoCapital(tipoDocumento.getCodigo()));
			documentoCapital.setNome(tipoDocumento.getDescricao());
			
			documentoCapitalDelegate.incluir(documentoCapital);
			
		} catch (BancoobException e) {
			SicoobLoggerPadrao.getInstance(getClass()).erro(e, e.getMessage());
			throw new BancoobException(e);
		}
		
		return documentoCapital;
	}  
}
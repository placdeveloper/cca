package br.com.sicoob.sisbr.cca.movimentacao.servicos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorConfiguracaoCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorCotaDelegate;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.DebitoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoPeriodoDebito;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoPesquisaDebitoIndeterminado;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoPessoa;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoValorDebito;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.DebitoIndeterminadoDelegate;
import br.com.sicoob.cca.movimentacao.negocio.dto.ConsultaDebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.DebitoIndeterminadoRenDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.CapesIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCorrenteIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.FonteRendaPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.movimentacao.MovimentacaoContaCapital;
import br.com.sicoob.sisbr.cca.movimentacao.conversor.ConversorDebitoIndeterminado;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ConsultaDebitoIndeterminadoRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.DebitoIndeterminadoRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.QuadroGeralAssociadoVO;


/**
 * @author marco.nascimento
 */
@RemoteService
public class DebitoIndeterminadoServico extends MovimentacaoContaCapital {
	
	/** O atributo capesIntDelegate. */
	private CapesIntegracaoDelegate capesIntDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarCapesIntegracaoDelegate();
	
	/** O atributo vlrCotaDelegate. */
	private ValorCotaDelegate vlrCotaDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorCotaDelegate();
	
	/** O atributo debIndDelegate. */
	private DebitoIndeterminadoDelegate debIndDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarDebitoIndeterminadoDelegate();
	
	/** O atributo conversor. */
	private ConversorDebitoIndeterminado conversor = new ConversorDebitoIndeterminado();
	
	/** O atributo cadastroContaCapitalDelegate. */
	private ContaCorrenteIntegracaoDelegate contaCorrenteIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarContaCorrenteIntegracaoDelegate();
	
	private ValorConfiguracaoCapitalDelegate valorConfDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorConfiguracaoCapitalDelegate();
	
	/**
	 * Obter definicoes da tela de inclusao (debito indeterminado)
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoesIncluir(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		retornoDTO.getDados().put("comboTipoInteg", criarComboTipoIntegralizacao(null));
		retornoDTO.getDados().put("comboPeriodoDebito", criarComboPeriodoDebito());
		retornoDTO.getDados().put("comboFormaDebito", criarComboFormaDebito());
		retornoDTO.getDados().put("idInstituicao", Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		
		return retornoDTO;
	}
	
	/**
	 * Obter definicoes inclusao de debito indeterminado individual
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoesIncluirIndividual(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		retornoDTO.getDados().put("comboTipoInteg", criarComboTipoIntegralizacao(null));
		return retornoDTO;
	}
	
	/**
	 * Obter definicoes inclusao de debito indeterminado em Lote
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoesIncluirEmLote(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		retornoDTO.getDados().put("comboTipoInteg", criarComboTipoIntegralizacao(EnumTipoPessoa.COD_TIPO_PES_FISICA.getCodigo()));
		return retornoDTO;
	}
	
	/**
	 * Obter definicoes da tela de alterar (debito indeterminado)
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoesAlterar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		DebitoIndeterminadoRenVO vo = (DebitoIndeterminadoRenVO) reqDTO.getDados().get("vo");
		DebitoContaCapital debCCA = debIndDelegate.pesquisar(vo.getIdDebitoContaCapital());
		
		vo = conversor.converterEntidadeparaVO(debCCA);
		
		retornoDTO.getDados().put("vo", vo);
		retornoDTO.getDados().put("comboTipoInteg", criarComboTipoIntegralizacao(vo.getCodTipoPessoa()));
		retornoDTO.getDados().put("comboPeriodoDebito", criarComboPeriodoDebito());
		retornoDTO.getDados().put("comboFormaDebito", criarComboFormaDebito());
		retornoDTO.getDados().put("comboCco", criarComboCco(debCCA.getIdInstituicao(), debCCA.getContaCapital().getIdPessoa()));
		
		return retornoDTO;
	}
	
	/**
	 * Obter definicoes da tela de alterar em lote (debito indeterminado)
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoesAlterarEmLote(RequisicaoReqDTO reqDTO) throws BancoobException {
		return new RetornoDTO();
	}
	
	/**
	 * Consulta associados que nao possuem debito indeterminado
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarAssociadosSemDebitoInd(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		DebitoIndeterminadoRenVO vo = (DebitoIndeterminadoRenVO) reqDTO.getDados().get("vo"); 
		
		ConsultaDebitoIndeterminadoRenDTO filtro = new ConsultaDebitoIndeterminadoRenDTO();
		filtro.setIdTipoInteg(vo.getTipoInteg());
		
		List<ConsultaDebitoIndeterminadoRenVO> lstVO = conversor.converterListDTOparaListVO(debIndDelegate.pesquisarAssociadosSemDebito(filtro));
		
		retornoDTO.getDados().put("lstVO", lstVO);
		retornoDTO.getDados().put("comboTipoInteg", criarComboTipoIntegralizacao(EnumTipoPessoa.COD_TIPO_PES_FISICA.getCodigo()));
		
		return retornoDTO;
	}
	
	/**
	 * Calculo de debito por quantidade de cotas
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO calcularValorDebitoCota(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		BigDecimal vlrCota = vlrCotaDelegate.obterValorCota(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		BigDecimal valorDebito = vlrCota.multiply(new BigDecimal(reqDTO.getDados().get("qtdCotas").toString()));
		retornoDTO.getDados().put("valorDebito", valorDebito);
		return retornoDTO;
	}
	
	/**
	 * Calculo de debito por percentual do salario
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO calcularValorDebitoPercentual(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Integer idInstituicao = null;
		BigDecimal valor = null;
		
		BigDecimal percentual = new BigDecimal(reqDTO.getDados().get("percentual").toString());
		Boolean isPercentualSalarioBase = Boolean.valueOf(reqDTO.getDados().get("percentualSalarioBase").toString());
		
		if(isPercentualSalarioBase) {
			
			if (reqDTO.getDados().get("idInstituicao") != null) {
				idInstituicao = Integer.valueOf(reqDTO.getDados().get("idInstituicao").toString());
			} else {
				idInstituicao = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
			}
			
			ValorConfiguracaoCapital valorConf = valorConfDelegate.obterValorConfiguracao(ContaCapitalConstantes.PAR_VALOR_SALARIO_BASE, idInstituicao);
			valor = new BigDecimal(valorConf.getValorConfiguracao());
			
		} else {
			
			Integer idPessoa = Integer.valueOf(reqDTO.getDados().get("idPessoa").toString());
			idInstituicao = Integer.valueOf(reqDTO.getDados().get("idInstituicao").toString());
			
			FonteRendaPessoaIntegracaoDTO frDTO = capesIntDelegate.obterFonteRendaPessoaInstituicao(idPessoa, idInstituicao);
			if(frDTO != null && frDTO.getRendaMensal() != null) {
				valor = frDTO.getRendaMensal();
			}
		}
		
		if(valor != null && percentual != null) {
			retornoDTO.getDados().put("valorDebito", valor.multiply(percentual).divide(new BigDecimal(ContaCapitalConstantes.NUMERO_CEM), 2, RoundingMode.HALF_UP));
		}
		
		return retornoDTO;
	}
	
	/**
	 * Obter definicoes da tela debito indeterminado
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		List<QuadroGeralAssociadoVO> lstQuadroGeral = conversor.converterQuadroGeralAssociado(debIndDelegate.pesquisarQuadroGeralAssociados());
		retornoDTO.getDados().put("lstQuadroGeral", lstQuadroGeral);
		
		List<QuadroGeralAssociadoVO> lstDebCCODia = conversor.converterDTOparaVO(debIndDelegate.pesquisarQtdDebCCODiaFixo());
		retornoDTO.getDados().put("lstDebCCODia", lstDebCCODia);
		
		List<QuadroGeralAssociadoVO> lstDebCCOIntervalo = conversor.converterDTOparaVO(debIndDelegate.pesquisarQtdDebCCOIntervalo());
		retornoDTO.getDados().put("lstDebCCOIntervalo", lstDebCCOIntervalo);
		
		List<QuadroGeralAssociadoVO> lstDebFolhaBanco = conversor.converterDTOparaVO(debIndDelegate.pesquisarQtdDebFolhaBanco());
		retornoDTO.getDados().put("lstDebFolhaBanco", lstDebFolhaBanco);
		
		retornoDTO.getDados().put("cboTipoPesquisa", criarComboTipoPesquisa());
		
		return retornoDTO;
	}	
	
	/**
	 * Consulta debito indeterminado
	 * 
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO consultar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		ConsultaDebitoIndeterminadoRenVO vo = (ConsultaDebitoIndeterminadoRenVO) reqDTO.getDados().get("vo");
		
		ConsultaDebitoIndeterminadoRenDTO filtro = conversor.converterVOparaDTO(vo);
		
		retornoDTO.getDados().put("registros", conversor.converterListDTOparaListVO(debIndDelegate.pesquisar(filtro)));
		
		return retornoDTO;
	}
	
	/**
	 * Realiza inclusao de debito indeterminado
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException
	 */
	public RetornoDTO incluir(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		DebitoIndeterminadoRenVO vo = (DebitoIndeterminadoRenVO) reqDTO.getDados().get("vo");
		
		DebitoIndeterminadoRenDTO dto = conversor.converterVOparaDTO(vo);
		dto.setInclusaoDebito(Boolean.TRUE);
		debIndDelegate.incluirDebIndividual(dto);
	
		return retornoDTO;
	}
	
	/**
	 * Realiza inclusao de debito indeterminado
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException
	 */
	public RetornoDTO incluirEmLote(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		DebitoIndeterminadoRenVO vo = (DebitoIndeterminadoRenVO) reqDTO.getDados().get("vo");
		
		DebitoIndeterminadoRenDTO dto = conversor.converterVOparaDTO(vo);
		dto.setInclusaoDebito(Boolean.TRUE);
		debIndDelegate.incluirDebEmLote(dto);
		
		return retornoDTO;
	}
	
	/**
	 * Realiza inclusao de debito indeterminado
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException
	 */
	public RetornoDTO excluir(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		DebitoIndeterminadoRenDTO dto = new DebitoIndeterminadoRenDTO();
		dto.setIdsDebitoContaCapital((List<Integer>) reqDTO.getDados().get("arrIdsDeb"));
		dto.setIdsNumMatricula((List<Integer>) reqDTO.getDados().get("arrNumMatricula"));
		
		debIndDelegate.excluirDebEmLote(dto);
		
		return retornoDTO;
	}
	
	/**
	 * Realiza alteracao de debito indeterminado
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException
	 */
	public RetornoDTO alterar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		DebitoIndeterminadoRenVO vo = (DebitoIndeterminadoRenVO) reqDTO.getDados().get("vo");
		
		DebitoIndeterminadoRenDTO dto = conversor.converterVOparaDTO(vo);
		dto.setInclusaoDebito(Boolean.FALSE);		
		debIndDelegate.alterarDebIndividual(dto);
		
		return retornoDTO;
	}
	
	/**
	 * Realiza alteracao de debito indeterminado em lote
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException
	 */
	public RetornoDTO alterarEmLote(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		DebitoIndeterminadoRenVO vo = (DebitoIndeterminadoRenVO) reqDTO.getDados().get("vo");
		
		Integer tipoAlteracao = Integer.valueOf(reqDTO.getDados().get("tipoAlteracao").toString());
		
		DebitoIndeterminadoRenDTO dto = conversor.converterVOparaDTO(vo);
		dto.setInclusaoDebito(Boolean.FALSE);
		debIndDelegate.alterarDebEmLote(dto, tipoAlteracao);
		
		return retornoDTO;
	}
	
	/**
	 * Combo de tipos de pesquisa
	 * @return
	 */
	private List<ItemListaVO> criarComboTipoPesquisa() {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		for(EnumTipoPesquisaDebitoIndeterminado tipo : EnumTipoPesquisaDebitoIndeterminado.values()) {
			lista.add(new ItemListaVO(tipo.getCodigo().toString(), tipo.getDescricao()));
		}
		return lista;
	}
	
	/**
	 * Combo Periodo Debito
	 * @return
	 */
	private List<ItemListaVO> criarComboPeriodoDebito() {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		for(EnumTipoPeriodoDebito o : EnumTipoPeriodoDebito.values()) {
			lista.add(new ItemListaVO(o.getCodigo().toString(), o.getDescricao()));
		}
		return lista;
	}
	
	/**
	 * Tipo Integralizacao por tipo de pessoa (PF e PJ)
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO carregarTipoInteg(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Integer codTipoPessoa = Integer.valueOf(reqDTO.getDados().get("codTipoPessoa").toString());
		retornoDTO.getDados().put("comboTipoInteg", criarComboTipoIntegralizacao(codTipoPessoa));
		
		Integer idInstituicao = Integer.valueOf(reqDTO.getDados().get("idInstituicao").toString());
		Integer idPessoa = Integer.valueOf(reqDTO.getDados().get("idPessoa").toString());
		retornoDTO.getDados().put("comboCco", criarComboCco(idInstituicao, idPessoa));

		return retornoDTO;
	}

	/**
	 * Verifica se o associado ja tem o débito cadastrado
	 * @param reqDTO
	 * @throws BancoobException
	 */
	public RetornoDTO verificarDebitoCadastrado(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Integer idInstituicao = Integer.valueOf(reqDTO.getDados().get("idInstituicao").toString());		
		DebitoContaCapital debCCA = debIndDelegate.pesquisarPorIdContaCapital(Integer.valueOf(reqDTO.getDados().get("idContaCapital").toString()), idInstituicao);
		
		if (debCCA != null) {
			retornoDTO.getDados().put("isDebitoCadastrado", Boolean.TRUE);			
		}else {
			retornoDTO.getDados().put("isDebitoCadastrado", Boolean.FALSE);			
		}
		return retornoDTO;
	}
	
	/**
	 * Forma de Debito para Debito Indeterminado (CCO, Folha e Banco)
	 * @return
	 * @throws BancoobException
	 */
	private List<ItemListaVO> criarComboTipoIntegralizacao(Integer codTipoPessoa) throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getDescricao()));
		
		if(codTipoPessoa != null && codTipoPessoa.equals(EnumTipoPessoa.COD_TIPO_PES_FISICA.getCodigo())) {
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_FOLHA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_FOLHA.getDescricao()));
		}
		
		return lista;
	}
	
	/**
	 * Forma de Debito para Debito Indeterminado (CCO, Folha e Banco)
	 * @return
	 * @throws BancoobException
	 */
	private List<ItemListaVO> criarComboTipoIntegralizacaoPlataforma(Integer codTipoPessoa) throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		lista.add(new ItemListaVO("", "")); 
		lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getDescricao()));
		
		if(codTipoPessoa != null && codTipoPessoa.equals(EnumTipoPessoa.COD_TIPO_PES_FISICA.getCodigo())) {
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_FOLHA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_FOLHA.getDescricao()));
		}
		
		return lista;
	}	
	
	/**
	 * Tipo de Debito
	 * @return
	 * @throws BancoobException
	 */
	private List<ItemListaVO> criarComboFormaDebito() throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		for(EnumTipoValorDebito o : EnumTipoValorDebito.values()) {
			lista.add(new ItemListaVO(o.getCodigo().toString(), o.getDescricao()));
		}
		return lista;
	}
	
	/**
	 * Combo de CCO
	 * @param cca
	 * @return
	 * @throws BancoobException
	 */
	private List<ItemListaVO> criarComboCco(Integer idInstituicao, Integer idPessoa) throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		ContaCorrenteIntegracaoDTO ccoDTO = new ContaCorrenteIntegracaoDTO();
		ccoDTO.setIdInstituicao(idInstituicao);
		ccoDTO.setIdPessoa(idPessoa);
		List<ContaCorrenteIntegracaoRetDTO> lstCco = contaCorrenteIntegracaoDelegate.consultarContaCorrenteAtivaPorNumeroCliente(ccoDTO);
		for(ContaCorrenteIntegracaoRetDTO cco : lstCco) {
			lista.add(new ItemListaVO(cco.getNumeroContaCorrente().toString(), cco.getNumeroContaCorrente().toString()));
		}
		return lista;
	}
	
	
	
	/**
	 * Obter definicoes da tela de alterar (debito indeterminado)
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoesPlataforma(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		Integer idInstituicao = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		Integer idContaCapital = Integer.valueOf(reqDTO.getDados().get("idContaCapital").toString());
		Integer idPessoa = Integer.valueOf(reqDTO.getDados().get("idPessoa").toString());
		Integer codPessoa = Integer.valueOf(reqDTO.getDados().get("codPessoa").toString());
		
		DebitoContaCapital debCCA = debIndDelegate.pesquisarPorIdContaCapital(idContaCapital, idInstituicao);
		
		
		if(debCCA == null) {
			
			retornoDTO.getDados().put("comboTipoInteg", criarComboTipoIntegralizacaoPlataforma(codPessoa));
			retornoDTO.getDados().put("comboPeriodoDebito", criarComboPeriodoDebito());
			retornoDTO.getDados().put("comboFormaDebito", criarComboFormaDebito());
			retornoDTO.getDados().put("idInstituicao", idPessoa);
			retornoDTO.getDados().put("comboCco", criarComboCco(idInstituicao, idPessoa));
			
		}else {
			DebitoIndeterminadoRenVO vo = conversor.converterEntidadeparaVO(debCCA);
			
			retornoDTO.getDados().put("vo", vo);
			retornoDTO.getDados().put("comboTipoInteg", criarComboTipoIntegralizacaoPlataforma(vo.getCodTipoPessoa()));
			retornoDTO.getDados().put("comboPeriodoDebito", criarComboPeriodoDebito());
			retornoDTO.getDados().put("comboFormaDebito", criarComboFormaDebito());
			retornoDTO.getDados().put("comboCco", criarComboCco(debCCA.getIdInstituicao(), debCCA.getContaCapital().getIdPessoa()));
		}
		
		return retornoDTO;
	}
}
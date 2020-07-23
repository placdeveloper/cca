package br.com.sicoob.cca.cadastro.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.cadastro.negocio.excecao.ContaCapitalCadastradaNegocioException;
import br.com.sicoob.cca.cadastro.negocio.excecao.ContaCapitalCadastroException;
import br.com.sicoob.cca.cadastro.negocio.excecao.ContaCapitalCadastroNegocioException;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.CadastroContaCapitalRenServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.CadastroContaCapitalRenServicoRemote;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.PropostaSubscricaoServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.PropostaSubscricao;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoContaCapital;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.GftIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.HistContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.HistContaCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.HistContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
 *  @author Marco.Nascimento
 */
@Stateless
@Local (CadastroContaCapitalRenServicoLocal.class)
@Remote(CadastroContaCapitalRenServicoRemote.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CadastroContaCapitalRenServicoEJB extends ContaCapitalCadastroServicoEJB implements CadastroContaCapitalRenServicoLocal, CadastroContaCapitalRenServicoRemote {
	
	@Resource
	private SessionContext context;
	
	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;
	
	@EJB
	private PropostaSubscricaoServicoLocal propostaServico;
	
	@EJB
	private ContaCapitalLegadoServicoLocal contaCapitalLegadoServico;
	
	@EJB
	private HistContaCapitalLegadoServicoLocal histContaCapitalLegadoServico;
	
	@EJB
	private CapesIntegracaoServicoLocal capesServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@EJB
	private GftIntegracaoServicoLocal gftIntegracaoServico;
	
	@EJB
	private ProdutoLegadoServicoLocal prodLegadoServico;

	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.CadastroContaCapitalRenServico#obterNovaContaCapital(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Integer obterNovaContaCapital(Integer idInstituicao) throws BancoobException {
		return contaCapitalServico.obterNovaContaCapital(idInstituicao);
	}

	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.CadastroContaCapitalRenServico#pesquisar(br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CadastroContaCapitalRenDTO> pesquisar(CadastroContaCapitalRenDTO dto) throws BancoobException {
		return contaCapitalServico.pesquisar(dto);
	}
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.CadastroContaCapitalRenServico#incluir(br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO)
	 */
	public CadastroContaCapitalRenDTO incluir(CadastroContaCapitalRenDTO dto) throws BancoobException {
		
		try {
			ContaCapital contaCapital = contaCapitalServico.incluir(criarContaCapital(dto));
			
			PropostaSubscricao proposta = propostaServico.incluir(criarPropostaSubscricao(dto, contaCapital));
			
			ContaCapitalLegado contaCapitalLegado = criarContaCapitalLegado(proposta);
			
			contaCapitalLegadoServico.incluir(contaCapitalLegado);
			
			histContaCapitalLegadoServico.incluir(criarHistContaCapitalLegado(contaCapitalLegado));
			
			dto.setIdContaCapital(contaCapital.getId());
			dto.setIdInstituicao(contaCapital.getIdInstituicao());
			dto.setIdPessoa(contaCapital.getIdPessoa());
			
		} catch (ContaCapitalCadastradaNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalCadastradaNegocioException(e.getMessage());
			
		} catch (ContaCapitalCadastroNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalCadastroNegocioException(e.getMessage());
			
		}  catch (BancoobException e) {
			context.setRollbackOnly();
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalCadastroException("MSG_012");
		}
		
		return dto;
	}
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.CadastroContaCapitalRenServico#alterar(br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO)
	 */
	public CadastroContaCapitalRenDTO alterar(CadastroContaCapitalRenDTO dto) throws BancoobException {
		
		try {
			PropostaSubscricao proposta = criarAlterarPropostaSubscricao(dto);
			
			propostaServico.alterar(proposta);
			
			ContaCapital cca = contaCapitalServico.obter(proposta.getId());
			
			dto.setIdContaCapital(cca.getId());
			dto.setIdInstituicao(cca.getIdInstituicao());
			dto.setIdPessoa(cca.getIdPessoa());
			
		} catch (ContaCapitalCadastroNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalCadastroNegocioException(e.getMessage());
			
		}  catch (BancoobException e) {
			context.setRollbackOnly();
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalCadastroException("MSG_012");
			
		}
		
		return dto;
	}
	
	/**
	 * Historico Conta Capital legado (SQL Server)
	 * @param contaCapitalLegado
	 * @return
	 * @throws BancoobException
	 */
	private HistContaCapitalLegado criarHistContaCapitalLegado(ContaCapitalLegado contaCapitalLegado) throws BancoobException {
		HistContaCapitalLegado hist = new HistContaCapitalLegado();
		
		HistContaCapitalLegadoPK histPK = new HistContaCapitalLegadoPK();
		histPK.setContaCapitalLegado(contaCapitalLegado);
		histPK.setDataOcorrencia(new DateTimeDB());
		hist.setHistContaCapitalLegadoPK(histPK);
		
		hist.setCodSituacao(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_ATIVO);
		hist.setBolAtualizado(true);
				
		hist.setNumCoop(contaCapitalLegado.getNumCoop());
		
		return hist;
	}
	
	/**
	 * Conta Capital legado (SQL Server)
	 * @param contaCapitalLegado
	 * @return
	 * @throws BancoobException
	 */
	private ContaCapitalLegado criarContaCapitalLegado(PropostaSubscricao proposta) throws BancoobException {
		ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();
		
		ContaCapital cca = contaCapitalServico.obter(proposta.getId());
		
		PessoaIntegracaoDTO pessoaIntegracao = capesServico.obterPessoaInstituicao(cca.getIdPessoa(), cca.getIdInstituicao());
		contaCapitalLegado.setNumCliente(pessoaIntegracao.getIdPessoaLegado());
		
		contaCapitalLegado.setNumContaCorrente(null);
		contaCapitalLegado.setNumMatricula(cca.getNumContaCapital());
		contaCapitalLegado.setDataMatricula(obterDataProduto(cca.getIdInstituicao()));
		contaCapitalLegado.setDataSaldoAnterior(new DateTimeDB());
		contaCapitalLegado.setDataSaida(null);
		contaCapitalLegado.setUidTrabalha(null);
		contaCapitalLegado.setValorSaldoAtuInteg(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoAtuSubsc(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoAtuDevolver(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoAntInteg(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoAntSubsc(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoAntDevolver(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoMedRealAcu(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoMedPosAcu(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoAtuDivs(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoAntDivs(BigDecimal.ZERO);
		contaCapitalLegado.setCodSituacao(cca.getSituacaoContaCapital().getId().intValue());
		contaCapitalLegado.setBolDebIndeterminado(Boolean.FALSE);
		contaCapitalLegado.setValorDeb(BigDecimal.ZERO);
		contaCapitalLegado.setCodFormaDeb(null);
		contaCapitalLegado.setDataVencimentoDeb(null);
		contaCapitalLegado.setPeriodoProxDeb(null);
		contaCapitalLegado.setTipoPeriodoDeb(null);
		contaCapitalLegado.setBolParticipaRateio(Boolean.TRUE);
		contaCapitalLegado.setCodTipoValorDebito(null);
		contaCapitalLegado.setIdCondicaoAssociacao(null);
		contaCapitalLegado.setBolDireitoVoto(Boolean.TRUE);
		contaCapitalLegado.setBolRestricaoRateio(Boolean.FALSE);
		contaCapitalLegado.setObsRestricao(null);
		contaCapitalLegado.setValorSaldoMedADevAcu(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoBloqInt(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoIntegralDispAcum(BigDecimal.ZERO);
		
		contaCapitalLegado.setNumCoop(instituicaoIntegracaoServico.obterNumeroCooperativa(cca.getIdInstituicao()));
		
		contaCapitalLegado.setIdContaCapital(cca.getId());
		
		return contaCapitalLegado;
	}
	
	/**
	 * Cria Conta Capital com dados da camada de apresentacao
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	private ContaCapital criarContaCapital(CadastroContaCapitalRenDTO dto) throws BancoobException {
		ContaCapital contaCapital = new ContaCapital();
		contaCapital.setDataHoraAtualizacao(new DateTimeDB());
		contaCapital.setDataMatricula(obterDataProduto(dto.getIdInstituicao()));
		contaCapital.setIdInstituicao(dto.getIdInstituicao());
		contaCapital.setIdPessoa(dto.getIdPessoa());		
		contaCapital.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
		contaCapital.setMatriculaEscolhida(dto.getMatriculaEscolhida());
		contaCapital.setNumContaCapital(dto.getNumContaCapital());
		contaCapital.setValorBloq(BigDecimal.ZERO);
		contaCapital.setValorDevol(BigDecimal.ZERO);
		contaCapital.setValorInteg(BigDecimal.ZERO);
		contaCapital.setValorSubs(BigDecimal.ZERO);
		
		SituacaoContaCapital situacaoConta = new SituacaoContaCapital();
		situacaoConta.setId(EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo().shortValue());
		contaCapital.setSituacaoContaCapital(situacaoConta);
		
		if(dto.getNumContaCapital().equals(dto.getNumContaCapitalGerada())) {
			contaCapital.setMatriculaEscolhida(Boolean.FALSE);
		} else {
			contaCapital.setMatriculaEscolhida(Boolean.TRUE);
		}
		
		return contaCapital;
	}
	
	/**
	 * Atualizacao da proposta de subscricao
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	private PropostaSubscricao criarAlterarPropostaSubscricao(CadastroContaCapitalRenDTO dto) throws BancoobException {
		PropostaSubscricao proposta = propostaServico.obter(dto.getIdContaCapital());
		
		proposta.setDataHoraAtualizacao(new DateTimeDB());
		proposta.setDiaDebitoProposta(dto.getDiaDebito());
		proposta.setIdUsuario(InformacoesUsuario.getInstance().getLogin());		
		proposta.setQtdParcelaProposta(dto.getQtdParcelas());
		proposta.setValorIntegProposta(dto.getVlrInteg());
		proposta.setValorParcelaProposta(dto.getVlrParcelas());
		proposta.setValorSubsProposta(dto.getVlrSubs());
		
		TipoIntegralizacao tipoIntegralizacao = new TipoIntegralizacao();
		tipoIntegralizacao.setId(dto.getTipoInteg().shortValue());
		proposta.setTipoIntegralizacao(tipoIntegralizacao);
		
		if(dto.getNumCco() != null && dto.getNumCco().intValue() > 0) {
			proposta.setNumContaCorrente(dto.getNumCco());
		} else {
			proposta.setNumContaCorrente(null);
		}
		
		return proposta;
	}
	
	/**
	 * Cria proposta de subscricao
	 * @param dto
	 * @param contaCapital
	 * @return
	 */
	private PropostaSubscricao criarPropostaSubscricao(CadastroContaCapitalRenDTO dto, ContaCapital contaCapital) {
		PropostaSubscricao proposta = new PropostaSubscricao();
		
		proposta.setDataHoraAtualizacao(new DateTimeDB());
		proposta.setDiaDebitoProposta(dto.getDiaDebito());
		proposta.setId(contaCapital.getId());
		proposta.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
		proposta.setQtdParcelaProposta(dto.getQtdParcelas());
		proposta.setValorIntegProposta(dto.getVlrInteg());
		proposta.setValorParcelaProposta(dto.getVlrParcelas());
		proposta.setValorSubsProposta(dto.getVlrSubs());
		proposta.setBolSubscricaoProposta(0);
		
		TipoIntegralizacao tipoIntegralizacao = new TipoIntegralizacao();
		tipoIntegralizacao.setId(dto.getTipoInteg().shortValue());
		proposta.setTipoIntegralizacao(tipoIntegralizacao);
		
		if(dto.getNumCco() != null && dto.getNumCco().intValue() > 0) {
			proposta.setNumContaCorrente(dto.getNumCco());
		}

		return proposta;
	}
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.CadastroContaCapitalRenServico#pesquisarAprovacaoPendente(br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CadastroContaCapitalRenDTO> pesquisarAprovacaoPendente(CadastroContaCapitalRenDTO dto) throws BancoobException {
		
		//Busca atividades pendentes de aprovacao que o usuario logado tenha acesso.
		List<GftIntegracaoDTO> lstGFT = gftIntegracaoServico.listarAtividadesPendentes(new GftIntegracaoDTO());
		List<Integer> idsContaCapital = new ArrayList<Integer>();
		for(GftIntegracaoDTO gftIntegracaoDTO : lstGFT) {
			idsContaCapital.add(gftIntegracaoDTO.getIdRegistroControlado());
		}
		
		dto.setIdInstituicao(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		dto.setIdsContaCapital(idsContaCapital);
		dto.setIdSituacaoCadastro(EnumSituacaoCadastroProposta.COD_AGUARDANDO_APROVACAO.getCodigo());
		List<CadastroContaCapitalRenDTO> lstCCA = contaCapitalServico.pesquisar(dto);
		
		//Relaciona CCA x GFT
		for(CadastroContaCapitalRenDTO cca : lstCCA) {
			for(GftIntegracaoDTO gft : lstGFT) {
				if(gft.getIdRegistroControlado().equals(cca.getIdContaCapital())) {
					cca.setIdAtividade(gft.getIdAtividade());
					cca.setIdOcorrenciaAtividade(gft.getIdOcorrenciaAtividade());
				}
			}
		}
		
		return lstCCA;
	}

	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.CadastroContaCapitalRenServico#excluir(java.lang.Integer)
	 */
	public void excluir(Integer idContaCapital) throws BancoobException {
		
		ContaCapital cca = contaCapitalServico.obter(idContaCapital);
		
		String msgExclusaoCCA = "Verifique se a conta capital(" + idContaCapital + ") existe";
		if(cca == null || cca.getId() == null) {
			getLogger().alerta(msgExclusaoCCA);
			throw new ContaCapitalCadastroNegocioException("MSG_012");
		}
		
		if(cca.getDataMatricula().compareTo(
				prodLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, cca.getIdInstituicao())) != 0) {
			throw new ContaCapitalCadastroNegocioException("MSG_032");
		}
		
		if(contaCapitalServico.pesquisarLancamentosContaCapital(idContaCapital, cca.getIdInstituicao()) > 0) {
			throw new ContaCapitalCadastroNegocioException("MSG_031");
		}
		
		if(contaCapitalServico.pesquisarParcelamentosContaCapital(idContaCapital) > 0) {
			throw new ContaCapitalCadastroNegocioException("MSG_033");
		}
		
		contaCapitalLegadoServico.excluir(cca.getNumContaCapital(), cca.getIdInstituicao());
		contaCapitalServico.excluir(cca.getId());
	}
	
	/**
	 * Data Atual do Produto
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	private DateTimeDB obterDataProduto(Integer idInstituicao) throws BancoobException {
		return new DateTimeDB(prodLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, idInstituicao).getTime());
	}		
	
}
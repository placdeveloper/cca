package br.com.sicoob.cca.cadastro.negocio.servicos.ejb;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.excecao.CadastroContaCapitalNegocioException;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.PropostaSubscricaoServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.PropostaSubscricaoServicoRemote;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ValorCotaServicoLocal;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDaoIF;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroDaoFactory;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.PropostaSubscricaoDao;
import br.com.sicoob.cca.comum.negocio.delegates.ContaCapitalComumFabricaDelegates;
import br.com.sicoob.cca.comum.negocio.delegates.FechamentoContaCapitalDelegate;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.entidades.HistoricoPropostaSubscricao;
import br.com.sicoob.cca.entidades.negocio.entidades.HistoricoPropostaSubscricaoPK;
import br.com.sicoob.cca.entidades.negocio.entidades.PropostaSubscricao;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.GftIntegracaoServicoLocal;

/**
 *  @author Marco.Nascimento
 */
@Stateless
@Local (PropostaSubscricaoServicoLocal.class)
@Remote(PropostaSubscricaoServicoRemote.class) 
public class PropostaSubscricaoServicoEJB extends ContaCapitalCadastroCrudServicoEJB<PropostaSubscricao> implements PropostaSubscricaoServicoLocal, PropostaSubscricaoServicoRemote {
	
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalCadastroDaoFactory.class)
	private PropostaSubscricaoDao propostaSubscricao;
	
	@EJB
	private ValorCotaServicoLocal valorCotaServico;
	
	@EJB
	private ContaCapitalServicoLocal ccaServico;
	
	@EJB
	private GftIntegracaoServicoLocal gftServico;

	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ejb.ContaCapitalCadastroCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalCadastroCrudDaoIF<PropostaSubscricao> getDAO() {
		return this.propostaSubscricao;
	}
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ejb.ContaCapitalCadastroCrudServicoEJB#incluir(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	public PropostaSubscricao incluir(PropostaSubscricao proposta) throws BancoobException {
		
		if(validarIncluir(proposta)) {
			
			proposta.getHistorico().add(criarHistorico(proposta));
			
			proposta = super.incluir(proposta);
		}
		
		return proposta;
	}

	/**
	 * Inclui a proposta de subscrição default para os serviços for do sisbr 2.0
	 * @param proposta
	 * @return
	 * @throws BancoobException
	 */
	public PropostaSubscricao incluirExterno(PropostaSubscricao proposta) throws BancoobException {
		proposta.getHistorico().add(criarHistorico(proposta));
		return super.incluir(proposta);
	}	
	
	/**
	 * @see br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB#alterar(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	public void alterar(PropostaSubscricao proposta) throws BancoobException {
	
		if(validarAlterar(proposta)) {
			
//			ContaCapital cca = ccaServico.obter(proposta.getId());
//			
//			if(cca.getSituacaoCadastroProposta().getId().equals(EnumSituacaoCadastroProposta.COD_DEVOLVIDO.getCodigo())) {
//				SituacaoCadastroProposta situacao = new SituacaoCadastroProposta();
//				situacao.setId(EnumSituacaoCadastroProposta.COD_AGUARDANDO_APROVACAO.getCodigo());
//				cca.setSituacaoCadastroProposta(situacao);
//				ccaServico.alterar(cca);
//				
//				GftIntegracaoDTO gftIntegracaoDTO = new GftIntegracaoDTO();
//				gftIntegracaoDTO.setExecutarAtividadeAprovacao(Boolean.FALSE);
//				gftIntegracaoDTO.setIdInstituicaoProcesso(cca.getIdInstituicao());
//				gftIntegracaoDTO.setIdRegistroControlado(cca.getId());
//				gftIntegracaoDTO.setNumContaCapital(cca.getNumContaCapital());
//				gftServico.instanciarFluxoAprovacao(gftIntegracaoDTO);
//			}
			
			super.alterar(proposta);
			
			em.persist(criarHistorico(proposta));
		}
	}
	
	/**
	 * Cria historico
	 * @param proposta
	 * @return
	 */
	private HistoricoPropostaSubscricao criarHistorico(PropostaSubscricao proposta) {
		HistoricoPropostaSubscricao hist = new HistoricoPropostaSubscricao();
		
		HistoricoPropostaSubscricaoPK id = new HistoricoPropostaSubscricaoPK(proposta.getId(), new DateTimeDB());
		hist.setId(id);
		
		hist.setDiaDebitoProposta(proposta.getDiaDebitoProposta());
		hist.setIdUsuario(proposta.getIdUsuario());
		hist.setNumContaCorrente(proposta.getNumContaCorrente());
		hist.setQtdParcelaProposta(proposta.getQtdParcelaProposta());
		hist.setTipoIntegralizacao(proposta.getTipoIntegralizacao());
		hist.setValorParcelaProposta(proposta.getValorParcelaProposta());
		hist.setValorSubsProposta(proposta.getValorSubsProposta());
		hist.setValorIntegProposta(proposta.getValorIntegProposta());
		hist.setBolSubscricaoProposta(proposta.getBolSubscricaoProposta());
		
		return hist;
	}
	
	/**
	 * Valida dados para inclusao
	 * @param proposta
	 * @return
	 * @throws BancoobException
	 */
	private boolean validarIncluir(PropostaSubscricao proposta) throws BancoobException {
		
		validarCondicoesIngresso(proposta);
		
		return true;
	}
	
	/**
	 * Valida dados para alteracao
	 * @param proposta
	 * @return
	 * @throws BancoobException
	 */
	private boolean validarAlterar(PropostaSubscricao proposta) throws BancoobException {
		
		validarFechamento(proposta);
		
		validarCondicoesIngresso(proposta);
		
		return true;
	}
	
	/**
	 * O método Validar fechamento.
	 *
	 * @param proposta o valor de proposta
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarFechamento(PropostaSubscricao proposta) throws BancoobException {
		
		Integer idInstituicao = ccaServico.obter(proposta.getId()).getIdInstituicao();
		
		FechamentoContaCapitalDelegate fechamentoDelegate = ContaCapitalComumFabricaDelegates.getInstance().criarFechamentoContaCapitalDelegate();
		Integer numCoop = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate().obterNumeroCooperativa(idInstituicao);
		if(fechamentoDelegate.isFechamentoIniciado(numCoop)) {
			throw new CadastroContaCapitalNegocioException("MSG_009");
		}
	}
	
	/**
	 * Valida se valores informados sao validos para o ingresso insituticao em questao
	 */
	private void validarCondicoesIngresso(PropostaSubscricao proposta) throws BancoobException {
		Integer idInstituicao = ccaServico.obter(proposta.getId()).getIdInstituicao();
		
		BigDecimal vlrMinSubs = valorCotaServico.obterValorMinimoSubscricao(idInstituicao, null);
		if(proposta.getValorSubsProposta().compareTo(vlrMinSubs) < 0) {
			String msg = "Valor da Subscrição >= Valor Mínimo Subscrição (" + ContaCapitalUtil.formatarValor(vlrMinSubs) + ")";
			throw new CadastroContaCapitalNegocioException("MSG_016", msg);
		}

		BigDecimal vlrMinInteg = valorCotaServico.obterValorMinimoIntegralizacao(idInstituicao);
		if(proposta.getValorIntegProposta().compareTo(vlrMinInteg) < 0) {
			String msg = "Valor Integralização à Vista >= Valor Mínimo (" + ContaCapitalUtil.formatarValor(vlrMinInteg) + ")";
			throw new CadastroContaCapitalNegocioException("MSG_017", msg);
		}
		
		Integer qtdMaxPar = valorCotaServico.obterQtdMaxParcela(idInstituicao);
		if(proposta.getQtdParcelaProposta().intValue() > qtdMaxPar.intValue()) {
			String msg = "Quant. Parcelas <= Quant. Máx. de Parcelas (" + qtdMaxPar + ")";
			throw new CadastroContaCapitalNegocioException("MSG_026", msg);
		}
	}
}
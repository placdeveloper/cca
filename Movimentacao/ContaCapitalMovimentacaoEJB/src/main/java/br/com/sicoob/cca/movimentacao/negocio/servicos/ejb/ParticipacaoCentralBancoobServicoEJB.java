package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.excecao.CadastroContaCapitalException;
import br.com.sicoob.cca.cadastro.negocio.excecao.CadastroContaCapitalNegocioException;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.HistParticipacaoCentralBancoob;
import br.com.sicoob.cca.entidades.negocio.entidades.HistParticipacaoCentralBancoobPK;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoob;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoobPK;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoIndiretaBancoob;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoIndiretaBancoobPK;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.HistParticipacaoCentralBancoobServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParticipacaoCentralBancoobServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParticipacaoCentralBancoobServicoRemote;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParticipacaoIndiretaBancoobServicoLocal;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDaoIF;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDaoFactory;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ParticipacaoCentralBancoobDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.OperacaoFinanceiraLegadoDelegate;

/**
 * EJB contendo servicos relacionados a ParticipacaoCentralBancoob.
 */
@Stateless
@Local (ParticipacaoCentralBancoobServicoLocal.class)
@Remote(ParticipacaoCentralBancoobServicoRemote.class) 
public class ParticipacaoCentralBancoobServicoEJB extends ContaCapitalMovimentacaoCrudServicoEJB<ParticipacaoCentralBancoob> implements ParticipacaoCentralBancoobServicoLocal, ParticipacaoCentralBancoobServicoRemote {
	
	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private ParticipacaoCentralBancoobDao participacaoCentralBancoobDao;	
	
	@EJB
	private HistParticipacaoCentralBancoobServicoLocal histParticipacaoCentralBancoobServico;
	
	@EJB
	private ParticipacaoIndiretaBancoobServicoLocal participacaoIndiretaBancoobServico; 
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.ejb.ContaCapitalMovimentacaoCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalMovimentacaoCrudDaoIF<ParticipacaoCentralBancoob> getDAO() {
		return participacaoCentralBancoobDao;
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.ParticipacaoCentralBancoobServico#incluirParticipacaoCentral(br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoob)
	 */
	public void incluirParticipacaoCentral(ParticipacaoCentralBancoob participacaoCentralBancoob) throws BancoobException {
		try{						
			if(validarGravacao(participacaoCentralBancoob, true)){
				ParticipacaoCentralBancoob participacaoCentralBancoobAlt = obter(participacaoCentralBancoob.getId());
				
				if(participacaoCentralBancoobAlt != null){
					throw new CadastroContaCapitalException("MSG_JACADASTRADO");				
				}							
				incluir(participacaoCentralBancoob);						
				gravarHistParticipacaoCentral(participacaoCentralBancoob);				
			}		
		}catch (CadastroContaCapitalNegocioException e) {			
			throw new CadastroContaCapitalNegocioException(e.getMessage());
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalException("MSG_006", e);
		}
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.ParticipacaoCentralBancoobServico#atualizarParticipacaoCentralPorSaldoContabil(br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoobPK)
	 */
	public void atualizarParticipacaoCentralPorSaldoContabil(ParticipacaoCentralBancoobPK pk) throws BancoobException {
		ParticipacaoCentralBancoob pcb = buscarParticipacaoCentralBancoob(pk);
		if (pcb != null) {
			Integer numCoopCentral = instituicaoIntegracaoServico.obterNumeroCooperativa(pk.getIdInstituicaoCentral());
			OperacaoFinanceiraLegadoDelegate delOpFin = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarOperacaoFinanceiraLegadoDelegate();
			BigDecimal valorPICentral = delOpFin.consultarValorParticipacaoCentralPorAnoMes(numCoopCentral, pk.getNumAnoMesBase());
			
			if(valorPICentral == null) {
				valorPICentral = BigDecimal.ZERO;
			}
			
			pcb.setValorParticipacao(valorPICentral);
			pcb.setIdUsuario(ContaCapitalConstantes.USUARIO_FECHAMENTO_PRODUCAO);
			pcb.setDataHoraAtualizacao(new DateTimeDB());
			alterar(pcb);
			gravarHistParticipacaoCentral(pcb);
		}
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.ParticipacaoCentralBancoobServico#existeParticipacaoCentralBancoob(br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoobPK)
	 */
	public boolean existeParticipacaoCentralBancoob(ParticipacaoCentralBancoobPK pk) throws BancoobException {
		return buscarParticipacaoCentralBancoob(pk) != null;
	}
	
	/**
	 * Consulta ParticipacaoCentralBancoob por ParticipacaoIndiretaBancoob
	 * @param pib
	 * @return
	 * @throws BancoobException
	 */
	private ParticipacaoCentralBancoob buscarParticipacaoCentralBancoob(ParticipacaoCentralBancoobPK pcbPK) throws BancoobException {
		ConsultaDto<ParticipacaoCentralBancoob> criterios = new ConsultaDto<ParticipacaoCentralBancoob>();
		ParticipacaoCentralBancoob pcb = new ParticipacaoCentralBancoob();
		pcb.setId(pcbPK);
		criterios.setFiltro(pcb);
		List<ParticipacaoCentralBancoob> resultado = listar(criterios);
		return resultado.isEmpty() ? null : resultado.get(0);
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.ParticipacaoCentralBancoobServico#alterarParticipacaoCentral(br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoob)
	 */
	public void alterarParticipacaoCentral(ParticipacaoCentralBancoob participacaoCentralBancoob) throws BancoobException {
		try{			
			if(validarGravacao(participacaoCentralBancoob, false)){
				ParticipacaoCentralBancoob participacaoCentralBancoobAlt = obter(participacaoCentralBancoob.getId());
				
				if(participacaoCentralBancoobAlt != null){
					alterar(participacaoCentralBancoob);
					gravarHistParticipacaoCentral(participacaoCentralBancoob);
				}							
			}			
		}catch (CadastroContaCapitalNegocioException e) {			
			throw new CadastroContaCapitalNegocioException(e.getMessage());
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());			
			throw new CadastroContaCapitalException("MSG_006", e);
		}
	}	
	
	/**
	 * O método Gravar hist participacao central.
	 *
	 * @param participacaoCentralBancoob o valor de participacao central bancoob
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void gravarHistParticipacaoCentral(ParticipacaoCentralBancoob participacaoCentralBancoob) throws BancoobException {
		try{						
			HistParticipacaoCentralBancoob histParticipacaoCentralBancoob = new HistParticipacaoCentralBancoob();					
			histParticipacaoCentralBancoob.setId(new HistParticipacaoCentralBancoobPK(participacaoCentralBancoob.getId().getIdInstituicaoCentral(), participacaoCentralBancoob.getId().getNumAnoMesBase(),participacaoCentralBancoob.getDataHoraAtualizacao()));
			histParticipacaoCentralBancoob.setValorParticipacao(participacaoCentralBancoob.getValorParticipacao());
			histParticipacaoCentralBancoob.setIdUsuario(participacaoCentralBancoob.getIdUsuario());
			histParticipacaoCentralBancoobServico.incluir(histParticipacaoCentralBancoob);					
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());			
			throw new CadastroContaCapitalException("MSG_007", e);
		}
	}
	
	/**
	 * Validar gravacao.
	 *
	 * @param participacaoCentralBancoob o valor de participacao central bancoob
	 * @param bIncluir o valor de b incluir
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Boolean validarGravacao(ParticipacaoCentralBancoob participacaoCentralBancoob, Boolean bIncluir) throws BancoobException {
		Boolean valido = true;

		if(bIncluir){				
			ParticipacaoCentralBancoob participacaoCentralBancoobAlt = obter(participacaoCentralBancoob.getId());
			
			if(participacaoCentralBancoobAlt != null){				
				throw new CadastroContaCapitalNegocioException("MSG_JACADASTRADO");								
			}			
		}
		
		List<ParticipacaoIndiretaBancoob> participacaoIndiretaBancoob = null;		
		ConsultaDto<ParticipacaoIndiretaBancoob> criterios = new ConsultaDto<ParticipacaoIndiretaBancoob>();
		ParticipacaoIndiretaBancoob filtroParticipacao = new ParticipacaoIndiretaBancoob();
		
		filtroParticipacao.setId(new ParticipacaoIndiretaBancoobPK(null, participacaoCentralBancoob.getId().getNumAnoMesBase(), participacaoCentralBancoob.getId().getIdInstituicaoCentral()));
		criterios.setFiltro(filtroParticipacao);
		participacaoIndiretaBancoob = participacaoIndiretaBancoobServico.listar(criterios);		
		
		if(!participacaoIndiretaBancoob.isEmpty()){				
			throw new CadastroContaCapitalNegocioException("MSG_008");								
		}							
		return valido;
	}
	
}
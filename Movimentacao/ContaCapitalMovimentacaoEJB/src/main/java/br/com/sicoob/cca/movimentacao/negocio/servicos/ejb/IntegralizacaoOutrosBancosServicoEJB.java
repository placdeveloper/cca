package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.negocio.delegates.ContaCapitalComumFabricaDelegates;
import br.com.sicoob.cca.comum.negocio.delegates.FechamentoContaCapitalDelegate;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoLote;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoLote;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoOutrosBancosDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalImpedimentosNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.IntegralizacaoOutrosBancosServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.IntegralizacaoOutrosBancosServicoRemote;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoIntegralizacaoExternaServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.IntegralizacaoOutrosBancosLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
 * Responsavel por integralização outros bancos (conta capital).
 *
 * @author antonio.genaro
 */
@Stateless
@Local (IntegralizacaoOutrosBancosServicoLocal.class)
@Remote(IntegralizacaoOutrosBancosServicoRemote.class) 
@TransactionManagement(TransactionManagementType.CONTAINER)
public class IntegralizacaoOutrosBancosServicoEJB extends ContaCapitalMovimentacaoServicoEJB implements IntegralizacaoOutrosBancosServicoLocal, IntegralizacaoOutrosBancosServicoRemote {

	@Resource
	private SessionContext context;
	
	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;

	@EJB
	private ContaCapitalServicoLocal ccaServico;

	@EJB
	private ProdutoLegadoServicoLocal prodLegadoServico;

	@EJB
	private LancamentoContaCapitalServicoLocal lancamentoServico;
	
	@EJB
	private LancamentoIntegralizacaoExternaServicoLocal lancamentoIntegralizacaoExternaServicoLocal;
	
	@EJB
	private IntegralizacaoOutrosBancosLegadoServicoLocal integralizacaoOutrosBancosLegadoServicoLocal;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServicoLocal;
	
	@EJB
	private FechamentoContaCapitalServicoLocal fechamentoContaCapitalServicoLocal;
	
	
	public void incluirIntegralizacao(IntegralizacaoOutrosBancosDTO dto) throws BancoobException  {
		
		
		try {
			
			ContaCapital cca = ccaServico.obterPorInstituicaoMatricula(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()), dto.getNumMatricula());
			
			verificarContaCapitalNaoEncontrada(cca);
			
			if (validarIntegralizacao(cca)) {				
				gravarLancamentos(cca, dto);
			}
			
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			throw e;
		} catch (ContaCapitalImpedimentosNegocioException e) {
			throw e;
		} catch (PersistenciaException e) {
			context.setRollbackOnly();
			getLogger().erro(e, e.getMessage());
			throw new BancoobException("MSG_001");
		} catch (BancoobException e) {
			context.setRollbackOnly();
			getLogger().erro(e, e.getMessage());
			throw new BancoobException("MSG_001");
		}		
		
	}
		
	private boolean validarIntegralizacao(ContaCapital cca) throws BancoobException {
		
		validarFechamento(cca);
		
		if (!cca.isSituacaoContaCapitalAtiva()) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_INTEG_CCA_NAO_ATIVO_MATRICULA", cca.getNumContaCapital().toString());
		}
		
		return true;
	}
	
	private void validarFechamento(ContaCapital contaCapital) throws BancoobException {
		FechamentoContaCapitalDelegate fechamentoDelegate = ContaCapitalComumFabricaDelegates.getInstance().criarFechamentoContaCapitalDelegate();
		Integer numCoop = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate().obterNumeroCooperativa(contaCapital.getIdInstituicao());
		if(fechamentoDelegate.isFechamentoIniciado(numCoop)) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_002");
		}
	}
	

	/**
	 * Realiza lancamentos CCA.
	 *
	 * @param cca o valor de cca
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void gravarLancamentos(ContaCapital cca, IntegralizacaoOutrosBancosDTO dto)  throws BancoobException {
		if(!dto.getValorIntegralizacao().equals(BigDecimal.ZERO) && !dto.getValorIntegralizacao().equals(BigDecimal.ZERO)) {
			
			LancamentoContaCapital lanSubs = gerarSubscricao(cca, dto);
			LancamentoContaCapital lanInteg = gerarIntegralizacao(cca, dto);
						
			lancamentoServico.incluir(lanSubs);
			lancamentoServico.incluir(lanInteg);
		}
	}
	
	/**
	 * Gera lancamento de baixa de subscricao.
	 *
	 * @param cca o valor de cca
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCapital gerarSubscricao(ContaCapital cca, IntegralizacaoOutrosBancosDTO dto) throws BancoobException {
		LancamentoContaCapital lan = criarLancamento(cca, dto.getValorIntegralizacao());
		
		TipoHistoricoCCA tipoHistCCA = new TipoHistoricoCCA();
		tipoHistCCA.setId(ContaCapitalConstantes.COD_HISTORICO_CCA_SUBSCRICAO.shortValue());
		lan.setTipoHistoricoCCA(tipoHistCCA);		
		lan.setDescNumDocumento(dto.getSequencialDetalhe().toString());
		
		return lan;
	}	
	
	/**
	 * Gera lancamento de baixa de integralização.
	 *
	 * @param cca o valor de cca
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCapital gerarIntegralizacao(ContaCapital cca, IntegralizacaoOutrosBancosDTO dto) throws BancoobException {
		LancamentoContaCapital lan = criarLancamento(cca, dto.getValorIntegralizacao());
		
		TipoHistoricoCCA tipoHistCCA = new TipoHistoricoCCA();
		tipoHistCCA.setId(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_OUTROS_BANCOS.getCodigo().shortValue());
		lan.setTipoHistoricoCCA(tipoHistCCA);		
		lan.setDescNumDocumento(cca.getNumContaCapital().toString());
		
		return lan;
	}	
	
	/**
	 * Cria lancamento padrao 
	 *
	 * @param cca o valor de cca
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCapital criarLancamento(ContaCapital cca, BigDecimal valor) throws BancoobException {
		LancamentoContaCapital lan = new LancamentoContaCapital();
		
		lan.setContaCapital(cca);
		lan.setBolProcessado((short) 0);
		lan.setDataHoraAtualizacao(new DateTimeDB());
		lan.setDataLancamento(new DateTimeDB(prodLegadoServico.obterDataAtualProdutoCCALogado().getTime()));
		lan.setIdInstituicao(cca.getIdInstituicao());
		lan.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
		
		lan.setValorLancamento(valor);
		
		TipoLote tpLote = new TipoLote();
		tpLote.setId(ContaCapitalConstantes.COD_LOTE_CCA_CAPITAL_OUTROS_BANCOS.shortValue());
		lan.setTipoLote(tpLote);
		
		return lan;
	}

	public void IntegralizacaoOutrosBancosSWS(Integer numCoop) throws BancoobException {
		if(validarFechamentoSWS(numCoop)) {
			List<IntegralizacaoOutrosBancosDTO> listaIntegralizacaoOutrosBancosDTO = criarIntegralizacaoDTO(numCoop);
			
			for (IntegralizacaoOutrosBancosDTO dto : listaIntegralizacaoOutrosBancosDTO) {
				integralizacaoOutrosBancosLegadoServicoLocal.updateIntegralizacaoSWS(criarDTOLegado(dto));
				lancamentoIntegralizacaoExternaServicoLocal.incluir(criarDtoSubscricao(dto));
				lancamentoIntegralizacaoExternaServicoLocal.incluir(criarDtoIntegralizacao(dto));
			}
		}
	}

	private List<IntegralizacaoOutrosBancosDTO> criarIntegralizacaoDTO(Integer numCoop) throws BancoobException {
		List<IntegralizacaoOutrosBancosDTO> listaDto = new ArrayList<IntegralizacaoOutrosBancosDTO>();
		Integer idInstituicao = instituicaoIntegracaoServicoLocal.obterIdInstituicao(numCoop);
		
		for (IntegralizacaoOutrosBancosLegadoDTO dto : integralizacaoOutrosBancosLegadoServicoLocal.consultarRemessaIntegralizacaoOutrosBancosSWS(numCoop)) {
			listaDto.add(new IntegralizacaoOutrosBancosDTO(dto,idInstituicao,numCoop));
		}
		return listaDto;
	}
	
	private boolean validarFechamentoSWS(Integer numCoop) throws BancoobException {
		if(fechamentoContaCapitalServicoLocal.isFechamentoIniciado(numCoop)) {
			return false;
		}
		return true;
	}
	

	private IntegralizacaoCapitalDTO criarDtoSubscricao(IntegralizacaoOutrosBancosDTO dto) {
		IntegralizacaoCapitalDTO dtoInt = new IntegralizacaoCapitalDTO();
		
		dtoInt.setIdTipoHistoricoLanc(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_SUBSC.getCodigo());   
		dtoInt.setNumMatricula(dto.getNumMatricula());
		dtoInt.setValorLancamento(dto.getValorIntegralizacao());
		dtoInt.setIdInstituicao(dto.getIdInstituicao());
		dtoInt.setIdOperacaoOrigem(dto.getNumMatricula().toString());
		dtoInt.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_OUTROS_BANCOS.getCodigo());

		return dtoInt;
	}

	private IntegralizacaoCapitalDTO criarDtoIntegralizacao(IntegralizacaoOutrosBancosDTO dto) {
		IntegralizacaoCapitalDTO dtoIntegralizacao = criarDtoSubscricao(dto);
		dtoIntegralizacao.setIdTipoHistoricoLanc(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_OUTROS_BANCOS.getCodigo());
		dtoIntegralizacao.setIdOperacaoOrigem("SWS - " + dto.getSequencialDetalhe().toString());
		return dtoIntegralizacao;
	}
	
	private IntegralizacaoOutrosBancosLegadoDTO criarDTOLegado(IntegralizacaoOutrosBancosDTO dto) {
		return new IntegralizacaoOutrosBancosLegadoDTO(
				dto.getNumBanco(),
				dto.getNumAgencia(),
				dto.getSequencialArquivo(),
				dto.getNomeArquivo(),
				dto.getSequencialDetalhe(),
				dto.getNumMatricula(),
				dto.getValorIntegralizacao(),
				dto.getBolIntegralizadoCapital(),
				dto.getNumCoop(),
				dto.getIdInstituicao(),
				dto.getNumParcela());
	}
}

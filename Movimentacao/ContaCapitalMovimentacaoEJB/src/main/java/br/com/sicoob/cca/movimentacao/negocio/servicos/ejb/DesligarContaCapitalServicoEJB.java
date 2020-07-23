package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import com.google.gson.Gson;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ValorConfiguracaoCapitalServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.negocio.annotation.LogCCA;
import br.com.sicoob.cca.comum.negocio.delegates.ContaCapitalComumFabricaDelegates;
import br.com.sicoob.cca.comum.negocio.delegates.FechamentoContaCapitalDelegate;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.HistoricoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoLote;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumMetodoOperacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.ContratoLiquidacaoSimplesDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.DadosDesligamentoEncontroContasDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.DesligarContaCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.DevolucaoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalImpedimentosNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.BloqueioContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.DesligarContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.DesligarContaCapitalServicoRemote;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.DevolucaoContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.AnotacaoPessoaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContratoLiquidacaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.EnderecoPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LocalizacaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaFisicaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaJuridicaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ResultadoLiquidacaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.TelefonePessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.ContaCorrenteIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CreditoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.LocalizacaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.EmprestimoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.HistContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.HistContaCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.EmprestimoIntegracaoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.HistContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
 * Responsavel por realizar desligamento do associado (conta capital).
 *
 * @author marco.nascimento
 */
@Stateless
@Local (DesligarContaCapitalServicoLocal.class)
@Remote(DesligarContaCapitalServicoRemote.class) 
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DesligarContaCapitalServicoEJB extends ContaCapitalMovimentacaoServicoEJB implements DesligarContaCapitalServicoLocal, DesligarContaCapitalServicoRemote {
	
	@Resource
	private SessionContext context;

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@EJB
	private ContaCapitalServicoLocal ccaServico;
	
	@EJB
	private ContaCorrenteIntegracaoServicoLocal ccoIntServico;
	
	@EJB
	private ParcelamentoContaCapitalServicoLocal parServico; 
	
	@EJB
	private ContaCapitalLegadoServicoLocal ccaLegadoServico;
	
	@EJB
	private HistContaCapitalLegadoServicoLocal histCcaLegadoServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntServico;
	
	@EJB
	private LocalizacaoIntegracaoServicoLocal locInt;
	
	@EJB
	private ProdutoLegadoServicoLocal prodLegadoServico;
	
	@EJB
	private EmprestimoIntegracaoLegadoServicoLocal empIntegracaoServico;
	
	@EJB
	private CapesIntegracaoServicoLocal capesIntServico;
	
	@EJB
	private LancamentoContaCapitalServicoLocal lancamentoServico;
	
	@EJB
	private BloqueioContaCapitalServicoLocal bloqueioContaCapitalServico;
	
	@EJB
	private ValorConfiguracaoCapitalServicoLocal valorConfiguracaoCapitalServico;
	
	@EJB
	private DevolucaoContaCapitalServicoLocal devolucaoContaCapitalServico;
	
	@EJB
	private CreditoIntegracaoServicoLocal creditoIntegracaoServico;
	
	private static final Integer PARAM_BLOQUEIA_DESLIGAMENTO_COM_IMPEDIMENTO = 1391;
	
	private static final Integer ANOTACAO_501_CNPJ_IRREGULAR = 501;
	private static final Integer ANOTACAO_505_CPF_FALECIDO = 505;
	private static final Integer ANOTACAO_513_CPF_FALECIDO = 513;
	
	/**
	 * {@link DesligarContaCapitalServicoRemote#desligarContaCapital(Integer, Integer)}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param tipoDesligamento o valor de tipo desligamento
	 * @param dataDesligamento o valor de data desligamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@LogCCA(metodo=EnumMetodoOperacaoContaCapital.MOVIMENTACAO_DESLIGARCONTACAPITALSERVICO_DESLIGARCONTACAPITAL)
	public void desligarContaCapital(Integer idContaCapital, Integer tipoDesligamento, Date dataDesligamento) throws BancoobException {
		
		try {
			ContaCapital cca = ccaServico.obter(idContaCapital);
			verificarContaCapitalNaoEncontrada(cca);
			
			if (validarDesligamento(cca, dataDesligamento)) {
				
				desligarContaCapitalDB2(cca, tipoDesligamento);
				
				desligarContaCapitalSQL(cca, tipoDesligamento);
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
	
	/**
	 * Desliga conta capital no legado (SQL).
	 *
	 * @param cca o valor de cca
	 * @param tipoDesligamento o valor de tipo desligamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void desligarContaCapitalSQL(ContaCapital cca, Integer tipoDesligamento) throws BancoobException {
		ContaCapitalLegado ccaLegado = ccaLegadoServico.obter(cca.getNumContaCapital());
		ccaLegado.setCodSituacao(tipoDesligamento);
		ccaLegado.setIdContaCapital(cca.getId());
		
		ccaLegado.setDataSaida(new DateTimeDB(new DateTime().withTime(0, 0, 0, 0).getMillis()));
		ccaLegadoServico.alterar(ccaLegado);
		
		HistContaCapitalLegado hist = criarHistoricoCCALegado(ccaLegado, cca.getIdInstituicao()); 
		histCcaLegadoServico.incluir(hist);
	}
	
	/**
	 * Cria HistContaCapitalLegado.
	 *
	 * @param ccaLegado o valor de cca legado
	 * @param idInstituicao o valor de id instituicao
	 * @return HistContaCapitalLegado
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private HistContaCapitalLegado criarHistoricoCCALegado(ContaCapitalLegado ccaLegado, Integer idInstituicao) throws BancoobException {
		HistContaCapitalLegado hist = new HistContaCapitalLegado();
		HistContaCapitalLegadoPK histPK = new HistContaCapitalLegadoPK();
		histPK.setContaCapitalLegado(ccaLegado);
		histPK.setDataOcorrencia(new DateTimeDB());
		hist.setHistContaCapitalLegadoPK(histPK);
		hist.setNumCoop(instituicaoIntServico.obterNumeroCooperativa(idInstituicao));
		hist.setCodSituacao(ccaLegado.getCodSituacao());
		hist.setBolAtualizado(true);
		return hist;
	}
	
	/**
	 * Desliga conta capital no DB2.
	 *
	 * @param cca o valor de cca
	 * @param tipoDesligamento o valor de tipo desligamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void desligarContaCapitalDB2(ContaCapital cca, Integer tipoDesligamento) throws BancoobException {
		SituacaoContaCapital sit = new SituacaoContaCapital();
		sit.setId(tipoDesligamento.shortValue());
		cca.setSituacaoContaCapital(sit);
		cca.setDataSaida(new DateTimeDB());
		
		ccaServico.alterar(cca);
		
		gerarLancamentos(cca);
	}
	
	/**
	 * Realiza lancamentos do desligamento CCA.
	 *
	 * @param cca o valor de cca
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void gerarLancamentos(ContaCapital cca)  throws BancoobException {
		if(!cca.getValorInteg().equals(BigDecimal.ZERO) && !cca.getValorSubs().equals(BigDecimal.ZERO)) {
			
			LancamentoContaCapital lanBaixaSubs = gerarBaixaSubscricao(cca);
			LancamentoContaCapital lanTransfInteg = gerarTransfInteg(cca);
			LancamentoContaCapital lanTransfRestInteg = gerarTransfRestituirInteg(cca);
			
			if(lanBaixaSubs.getValorLancamento().compareTo(BigDecimal.ZERO) == 1) {
				lancamentoServico.incluir(lanBaixaSubs);
				lancamentoServico.incluir(lanTransfInteg);
				lancamentoServico.incluir(lanTransfRestInteg);
			}
		}
	}
	
	/**
	 * Cria lancamentos de desligamento padrao para: 
	 * Baixa de subscricao(2) 
	 * Transferir de integralizacao para a restituir(101)
	 * A restituir de integralizacao(201).
	 *
	 * @param cca o valor de cca
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCapital criarLancamentoDesligamento(ContaCapital cca) throws BancoobException {
		LancamentoContaCapital lan = new LancamentoContaCapital();
		
		lan.setContaCapital(cca);
		lan.setBolProcessado((short) 0);
		lan.setDataHoraAtualizacao(new DateTimeDB());
		lan.setDataLancamento(new DateTimeDB(prodLegadoServico.obterDataAtualProdutoCCALogado().getTime()));
		lan.setIdInstituicao(cca.getIdInstituicao());
		lan.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
		
		lan.setValorLancamento(lancamentoServico.calcularValorIntegralizado(cca.getId()));
		
		TipoLote tpLote = new TipoLote();
		tpLote.setId(ContaCapitalConstantes.COD_LOTE_CCA_PARC_AVISTA.shortValue());
		lan.setTipoLote(tpLote);
		
		return lan;
	}
	
	/**
	 * Gera lancamento de baixa de subscricao (Desligamento).
	 *
	 * @param cca o valor de cca
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCapital gerarBaixaSubscricao(ContaCapital cca) throws BancoobException {
		LancamentoContaCapital lan = criarLancamentoDesligamento(cca);
		
		TipoHistoricoCCA tipoHistCCA = new TipoHistoricoCCA();
		tipoHistCCA.setId(ContaCapitalConstantes.COD_HISTORICO_CCA_BAIXA_SUBSCRICAO.shortValue());
		lan.setTipoHistoricoCCA(tipoHistCCA);
		
		int numDesligamento = 0;
		if(cca.getHistorico() != null && cca.getHistorico().size() > 0) {
			for(HistoricoContaCapital hist : cca.getHistorico()) {
				if(!hist.getSituacaoContaCapital().getId().equals(EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo().shortValue())) {
					numDesligamento++;
				}
			}
		}
		
		lan.setDescNumDocumento(ContaCapitalConstantes.COD_DESC_DOCUMENTO_DESLIGAMENTO + numDesligamento);
		
		return lan;
	}
	
	/**
	 * Lancamento de transferencia de saldo integralizado para restituir.
	 *
	 * @param cca o valor de cca
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCapital gerarTransfInteg(ContaCapital cca) throws BancoobException {
		LancamentoContaCapital lan = criarLancamentoDesligamento(cca);
		
		TipoHistoricoCCA tipoHistCCA = new TipoHistoricoCCA();
		tipoHistCCA.setId(ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSF_A_RESTITUIR.shortValue());
		lan.setTipoHistoricoCCA(tipoHistCCA);
		
		lan.setDescNumDocumento(cca.getNumContaCapital().toString());
		
		return lan;
	}
	
	/**
	 * Lancamento de transferencia a restituir de integralizacao.
	 *
	 * @param cca o valor de cca
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCapital gerarTransfRestituirInteg(ContaCapital cca) throws BancoobException {
		LancamentoContaCapital lan = criarLancamentoDesligamento(cca);
		
		TipoHistoricoCCA tipoHistCCA = new TipoHistoricoCCA();
		tipoHistCCA.setId(ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSF_RESTITUIR_INTEG.shortValue());
		lan.setTipoHistoricoCCA(tipoHistCCA);
		
		lan.setDescNumDocumento(cca.getNumContaCapital().toString());
		
		return lan;
	}
	
	/**
	 * Valida desligamento de conta capital.
	 *
	 * @param cca o valor de cca
	 * @param dataDesligamento o valor de data desligamento
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private boolean validarDesligamento(ContaCapital cca, Date dataDesligamento) throws BancoobException {
		
		validarFechamento(cca);
		
		if (!cca.isSituacaoContaCapitalAtiva()) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_041");
		}
		
		if (isDataProdutoDiferenteDataDesligamento(dataDesligamento)) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_040");
		}
		
		if (possuiImpedimentos(cca)) {
			throw new ContaCapitalImpedimentosNegocioException("MSG_042");
		}
		
		if (isSaldoSubscritoDiferenteIntegralizado(cca)) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_043");
		}
		
		return true;
	}
	
	private boolean isSaldoSubscritoDiferenteIntegralizado(ContaCapital cca) throws BancoobException {
		BigDecimal saldoSubscrito = lancamentoServico.calcularValorSubscrito(cca.getId());
		BigDecimal saldoIntegralizado = lancamentoServico.calcularValorIntegralizado(cca.getId());
		return saldoSubscrito.compareTo(saldoIntegralizado) != 0;
	}

	private boolean isDataProdutoDiferenteDataDesligamento(Date dataDesligamento) throws BancoobException {
		Date dataProduto = prodLegadoServico.obterDataAtualProdutoCCALogado();
		return dataProduto.compareTo(dataDesligamento) != 0;
	}
	
	private boolean deveIgnorarImpedimentos(ContaCapital cca) throws BancoobException {
		ValorConfiguracaoCapital valorConfiguracao = valorConfiguracaoCapitalServico.obterValorConfiguracao(
				PARAM_BLOQUEIA_DESLIGAMENTO_COM_IMPEDIMENTO, cca.getIdInstituicao());
		return valorConfiguracao.isValorBooleanoZero();
	}

	/**
	 * Pode se entender como associado "Cancelado" na Receita Federal, o CPF ou CNPJ cancelado pelos motivos de falecimento da pessoa física ou baixa da pessoa jurídica. 
	 *
	 * @param cca o valor de cca
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private boolean isCpfCnpjIrregular(ContaCapital cca) throws BancoobException {
		List<AnotacaoPessoaDTO> anotacoes = capesIntServico.obterAnotacoesVigentes(cca.getIdPessoa(), cca.getIdInstituicao());
		List<Integer> anotacoesIrregulares = Arrays.asList(ANOTACAO_501_CNPJ_IRREGULAR, ANOTACAO_505_CPF_FALECIDO, ANOTACAO_513_CPF_FALECIDO);
		
		if(anotacoes != null && !anotacoes.isEmpty()) {
			for(AnotacaoPessoaDTO anotacaoPessoaDTO : anotacoes) {
				if(anotacaoPessoaDTO.getCodigoTipoAnotacao() != null 
						&& anotacoesIrregulares.contains(anotacaoPessoaDTO.getCodigoTipoAnotacao())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean verificarContaCorrenteImpeditivaDesligamento(ContaCapital cca) throws BancoobException {
		ContaCorrenteIntegracaoDTO ccoDTO = new ContaCorrenteIntegracaoDTO();
		ccoDTO.setIdPessoa(cca.getIdPessoa());
		ccoDTO.setIdInstituicao(cca.getIdInstituicao());
		return ccoIntServico.verificarContaCorrenteImpeditivaDesligamento(ccoDTO);
	}
	
	/**
	 * Verificar parcelas em aberto.
	 *
	 * @param cca o valor de cca
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private boolean verificarParcelasEmAberto(ContaCapital cca) throws BancoobException {
		List<Parcelamento> parcelas = parServico.pesquisarParcelasEmAberto(cca.getId()); 
		return parcelas != null && !parcelas.isEmpty();
	}
	
	/**
	 * Verificar emprestimo.
	 *
	 * @param cca o valor de cca
	 * @param lstCco 
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private boolean verificarEmprestimo(ContaCapital cca) throws BancoobException {
		ContaCorrenteIntegracaoDTO ccoDTO = new ContaCorrenteIntegracaoDTO();
		ccoDTO.setIdPessoa(cca.getIdPessoa());
		ccoDTO.setIdInstituicao(cca.getIdInstituicao());
		List<ContaCorrenteIntegracaoRetDTO> lstCco = ccoIntServico.consultarContaCorrentePorNumeroCliente(ccoDTO);
		if(lstCco != null) {
			PessoaIntegracaoDTO pessoaIntegracaoDTO = capesIntServico.obterPessoaInstituicao(cca.getIdPessoa(), cca.getIdInstituicao());
			for (ContaCorrenteIntegracaoRetDTO cco : lstCco) {
				List<EmprestimoIntegracaoDTO> lstEmprestimo = empIntegracaoServico.consultarEmprestimos(pessoaIntegracaoDTO.getIdPessoaLegado(), cco.getNumeroContaCorrente());
				if(lstEmprestimo != null && !lstEmprestimo.isEmpty()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Verifica se há impedimentos para desligar conta capital.
	 *
	 * @param cca o valor de cca
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private boolean possuiImpedimentos(ContaCapital cca) throws BancoobException {
		//Caso CPF/CNPJ irregular aplica validacao especifica
		if (isCpfCnpjIrregular(cca) || deveIgnorarImpedimentos(cca)) {
			return verificarBloqueio(cca)
					|| verificarParcelasEmAberto(cca);
		} else {
			return verificarContaCorrenteImpeditivaDesligamento(cca)
					|| verificarEmprestimo(cca)
					|| verificarBloqueio(cca)
					|| verificarParcelasEmAberto(cca);
		}
	}
	
	private boolean verificarBloqueio(ContaCapital cca) throws BancoobException {
		BigDecimal valorBloq = bloqueioContaCapitalServico.calcularValorBloqueado(cca.getId());
		return valorBloq.compareTo(BigDecimal.ZERO) > 0;
	}

	/**
	 * Caso fechamento da cooperativa iniciado  .
	 *
	 * @param contaCapital o valor de conta capital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarFechamento(ContaCapital contaCapital) throws BancoobException {
		FechamentoContaCapitalDelegate fechamentoDelegate = ContaCapitalComumFabricaDelegates.getInstance().criarFechamentoContaCapitalDelegate();
		Integer numCoop = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate().obterNumeroCooperativa(contaCapital.getIdInstituicao());
		if(fechamentoDelegate.isFechamentoIniciado(numCoop)) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_002");
		}
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DesligarContaCapitalServico#validarDesligamentoEncontroContas(br.com.sicoob.cca.movimentacao.negocio.dto.DesligarContaCapitalDTO)
	 */
	public boolean validarDesligamentoEncontroContas(DesligarContaCapitalDTO dto) throws BancoobException {
		ContaCapital cca = ccaServico.obter(dto.getIdContaCapital());
		validarFechamento(cca);
		if (!cca.isSituacaoContaCapitalAtiva()) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_041");
		}
		if (possuiImpedimentosEncontroContas(cca)) {
			throw new ContaCapitalImpedimentosNegocioException("MSG_042");
		}
		if (lancamentoServico.calcularValorIntegralizado(dto.getIdContaCapital()).compareTo(dto.getValorIntegralizado()) != 0) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_VALOR_INTEGRALIZADO_INDISPONIVEL");
		}
		return true;
	}
	
	private boolean possuiImpedimentosEncontroContas(ContaCapital cca) throws BancoobException {
		return verificarContaCorrenteImpeditivaDesligamento(cca)
				|| verificarBloqueio(cca)
				|| verificarParcelasEmAberto(cca);
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DesligarContaCapitalServico#desligarEncontroContas(br.com.sicoob.cca.movimentacao.negocio.dto.DesligarContaCapitalDTO, br.com.sicoob.cca.movimentacao.negocio.dto.DevolucaoRenDTO, java.util.List, java.util.List)
	 */
	@LogCCA(metodo=EnumMetodoOperacaoContaCapital.MOVIMENTACAO_DESLIGARCONTACAPITALSERVICO_DESLIGARENCONTROCONTAS)
	public void desligarEncontroContas(DesligarContaCapitalDTO desligarDTO, DevolucaoRenDTO devolucaoDTO, 
			List<ParcelamentoRenDTO> parcelamentosDTO) throws BancoobException {
		try {
			DadosDesligamentoEncontroContasDTO dadosDesligamento = new DadosDesligamentoEncontroContasDTO();
			ContaCapital cca = ccaServico.obter(desligarDTO.getIdContaCapital());
			verificarContaCapitalNaoEncontrada(cca);
			
			if (!cca.isSituacaoContaCapitalAtiva()) {
				throw new ContaCapitalMovimentacaoNegocioException("MSG_041");
			}
			
			Date dataAtualProduto = prodLegadoServico.obterDataAtualProdutoCCALogado();
			// desligamento
			desligarContaCapitalDB2(cca, desligarDTO.getIdTipoDesligamento());
			desligarContaCapitalSQL(cca, desligarDTO.getIdTipoDesligamento());
			// devolucao
			devolucaoContaCapitalServico.incluir(devolucaoDTO, parcelamentosDTO);
			// liquidacao de emprestimos
			liquidarEmprestimos(dadosDesligamento, desligarDTO.getContratosLiquidar(), cca, dataAtualProduto);
			// gravar JSON de desligamento
			gerarDadosDesligamento(dadosDesligamento, desligarDTO, devolucaoDTO, cca);
		}catch(ContaCapitalMovimentacaoNegocioException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(),e);
		} catch (PersistenciaException e) {
			getLogger().erro(e, e.getMessage());
			throw new BancoobException("MSG_001");
		} catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new BancoobException("MSG_001");
		}
		
	}

	private void gerarDadosDesligamento(DadosDesligamentoEncontroContasDTO dadosDesligamento, DesligarContaCapitalDTO desligarDTO, DevolucaoRenDTO devolucaoDTO, 
			ContaCapital cca) throws BancoobException {
		buscarDadosPessoaDesligamento(dadosDesligamento, cca);
		dadosDesligamento.setValorIntegralizado(desligarDTO.getValorIntegralizado());
		dadosDesligamento.setValorEmprestimos(desligarDTO.getValorEmprestimos());
		dadosDesligamento.setSaldoDevedorEmprestimos(desligarDTO.getValorEmprestimos().subtract(dadosDesligamento.getValorAmortizado()));
		dadosDesligamento.setSaldoDevolverProgramado(devolucaoDTO.getVlrDevolucao());
		dadosDesligamento.setContratosAbertos(simplificarContratos(desligarDTO.getContratosAbertos(),ContaCapitalConstantes.TIPO_CONTRATO_ABERTO));
		dadosDesligamento.setContratosLiquidar(simplificarContratos(desligarDTO.getContratosLiquidar(),ContaCapitalConstantes.TIPO_CONTRATO_LIQUIDAR));
		cca.setDescDadosDesligamento(new Gson().toJson(dadosDesligamento));
		ccaServico.alterar(cca);
	}
	
	private List<ContratoLiquidacaoSimplesDTO> simplificarContratos(List<ContratoLiquidacaoDTO> contratos, short tipoContrato) {
		
		List<ContratoLiquidacaoSimplesDTO> contratosSimples = new ArrayList<ContratoLiquidacaoSimplesDTO>();
		if (contratos != null && !contratos.isEmpty()) {
			for (ContratoLiquidacaoDTO contratoDTO : contratos) {
				ContratoLiquidacaoSimplesDTO contratoSimples = new ContratoLiquidacaoSimplesDTO();
				contratoSimples.setContrato(contratoDTO.getDescOperacaoCredito());
				contratoSimples.setDataOperacao(contratoDTO.getDataEntradaOperacao());
				contratoSimples.setModalidade(contratoDTO.getDescLinha());
				contratoSimples.setValorPrincipal(contratoDTO.getValorPrincipal());
				contratoSimples.setValorQuitacao(contratoDTO.getValorQuitacao());								
				if(tipoContrato==ContaCapitalConstantes.TIPO_CONTRATO_LIQUIDAR && contratoDTO.getBolErro()) {
					contratoSimples.setBolErro(contratoDTO.getBolErro());
					contratoSimples.setDescErro(contratoDTO.getDescErro());					
				}
				contratosSimples.add(contratoSimples);
			}
		}
		return contratosSimples;
	}
	
	private DadosDesligamentoEncontroContasDTO buscarDadosPessoaDesligamento(DadosDesligamentoEncontroContasDTO dto, ContaCapital cca) throws BancoobException {
		PessoaIntegracaoDTO pessoaIntegracaoDTO = capesIntServico.obterPessoaInstituicao(cca.getIdPessoa(), cca.getIdInstituicao());
		if(pessoaIntegracaoDTO.getCodTipoPessoa() != null){
			EnderecoPessoaIntegracaoDTO enderecoPessoaIntegracaoDTO = capesIntServico.obterEnderecoPessoaInstituicao(cca.getIdPessoa(), cca.getIdInstituicao());
			atribuirDadosComuns(cca, dto, pessoaIntegracaoDTO, enderecoPessoaIntegracaoDTO);
			if(pessoaIntegracaoDTO.getCodTipoPessoa().shortValue() == (short) 0){
				atribuirDadosPF(cca, dto, pessoaIntegracaoDTO);
			} else if(pessoaIntegracaoDTO.getCodTipoPessoa().shortValue() == (short) 1){
				atribuirDadosPJ(cca, dto, enderecoPessoaIntegracaoDTO);
			}
		}
		return dto;
	}

	private void atribuirDadosPJ(ContaCapital cca, DadosDesligamentoEncontroContasDTO dto,
			EnderecoPessoaIntegracaoDTO enderecoPessoaIntegracaoDTO) throws BancoobException {
		PessoaIntegracaoDTO pessoaIntegracaoPJDTO = capesIntServico.obterPessoaJuridicaFormaConstituicao(cca.getIdPessoa(), cca.getIdInstituicao());
		PessoaJuridicaIntegracaoDTO pessoaJuridicaIntegracaoDTO =  capesIntServico.obterPessoaJuridicaInstituicao(cca.getIdPessoa(), cca.getIdInstituicao());
		dto.setRazaoSocialEmpresa(pessoaJuridicaIntegracaoDTO.getRazaoSocialEmpresa());
		dto.setDataConstituicao(pessoaIntegracaoPJDTO.getDataConstituicao());
		dto.setCpfCnpj(pessoaJuridicaIntegracaoDTO.getCnpjEmpresa());
		dto.setNumInscricaoEstadual(pessoaJuridicaIntegracaoDTO.getInscricaoEstadual());
		if(enderecoPessoaIntegracaoDTO.getIdLocComercial() != null) {
			LocalizacaoIntegracaoDTO localizacaoIntegracaoComercialDTO = locInt.consultarLocalidade(enderecoPessoaIntegracaoDTO.getIdLocComercial());
			dto.setUfComercial(localizacaoIntegracaoComercialDTO.getUf());
			dto.setMunicipioComercial(localizacaoIntegracaoComercialDTO.getMunicipio());
		}
		dto.setRazaoSocialEmpresa(pessoaJuridicaIntegracaoDTO.getRazaoSocialEmpresa());
		dto.setDataConstituicao(pessoaIntegracaoPJDTO.getDataConstituicao());
		dto.setCpfCnpj(pessoaJuridicaIntegracaoDTO.getCnpjEmpresa());
		dto.setNumInscricaoEstadual(pessoaJuridicaIntegracaoDTO.getInscricaoEstadual());
	}

	private void atribuirDadosPF(ContaCapital cca, DadosDesligamentoEncontroContasDTO dto,
			PessoaIntegracaoDTO pessoaIntegracaoDTO) throws BancoobException {
		dto.setNomeCompleto(pessoaIntegracaoDTO.getNomeCompleto());
		dto.setCpfCnpj(pessoaIntegracaoDTO.getCpfCnpj());
		PessoaFisicaIntegracaoDTO pessoaFisicaIntegracaoDTO = capesIntServico.obterPessoaFisicaInstituicao(cca.getIdPessoa(), cca.getIdInstituicao());
		dto.setNumDocumento(pessoaFisicaIntegracaoDTO.getNumDocumento());
		dto.setEmissaoDocumento(pessoaFisicaIntegracaoDTO.getEmissaoDocumento());
		dto.setOrgaoDocumento(pessoaFisicaIntegracaoDTO.getOrgaoDocumento());
		dto.setUfDocumento(pessoaFisicaIntegracaoDTO.getUfDocumento());
		if(StringUtils.isNotBlank(pessoaFisicaIntegracaoDTO.getNomePai()) && StringUtils.isNotBlank(pessoaFisicaIntegracaoDTO.getNomeMae())){
			dto.setFiliacao((String) pessoaFisicaIntegracaoDTO.getNomePai() + " e " +  pessoaFisicaIntegracaoDTO.getNomeMae());	
		} else if(StringUtils.isNotBlank(pessoaFisicaIntegracaoDTO.getNomePai())) {
			dto.setFiliacao((String) pessoaFisicaIntegracaoDTO.getNomePai());
		} else if(StringUtils.isNotBlank(pessoaFisicaIntegracaoDTO.getNomeMae())) {
			dto.setFiliacao((String) pessoaFisicaIntegracaoDTO.getNomeMae());
		}
		dto.setNacionalidade(pessoaFisicaIntegracaoDTO.getNacionalidade());
		if(pessoaFisicaIntegracaoDTO.getIdNaturalidade() != null && pessoaFisicaIntegracaoDTO.getIdNaturalidade().intValue() > 0) {
			LocalizacaoIntegracaoDTO localizacaoIntegracaoDTO = locInt.consultarLocalidade(pessoaFisicaIntegracaoDTO.getIdNaturalidade());
			dto.setNaturalidade(localizacaoIntegracaoDTO.getMunicipio() + " - " + localizacaoIntegracaoDTO.getUf());
		} else {
			dto.setNaturalidade(pessoaFisicaIntegracaoDTO.getNaturalidade());
		}
		
		dto.setNascimento(pessoaFisicaIntegracaoDTO.getNascimento());
		dto.setDescSexo(pessoaFisicaIntegracaoDTO.getDescSexo());
		dto.setDescProfissao(pessoaFisicaIntegracaoDTO.getDescProfissao());
		if("OUTROS DECLARANTES NÃO ESPECIFICADOS NOS GRUPOS ANTERIORES".equals(dto.getDescProfissao())){
			dto.setDescProfissao("Não especificada");
		}
		dto.setEstadoCivil(pessoaFisicaIntegracaoDTO.getEstadoCivil());
	}

	private void atribuirDadosComuns(ContaCapital cca, DadosDesligamentoEncontroContasDTO dto,
			PessoaIntegracaoDTO pessoaIntegracaoDTO, EnderecoPessoaIntegracaoDTO enderecoPessoaIntegracaoDTO) throws BancoobException {
		TelefonePessoaIntegracaoDTO telefonePessoaIntegracaoDTO =  capesIntServico.obterTelefonePessoaInstituicao(cca.getIdPessoa(), cca.getIdInstituicao());
		dto.setTipoPessoa(pessoaIntegracaoDTO.getCodTipoPessoa());
		dto.setNumContaCapital(cca.getNumContaCapital());
		dto.setDescEnderecoResidencial(enderecoPessoaIntegracaoDTO.getDescEnderecoResidencial());
		dto.setNumResidencial(enderecoPessoaIntegracaoDTO.getNumEnderecoResidencial());
		dto.setComplementoResidencial(enderecoPessoaIntegracaoDTO.getComplementoEnderecoResidencial());
		dto.setBairroResidencial(enderecoPessoaIntegracaoDTO.getBairroEnderecoResidencial());
		dto.setCepResidencial(enderecoPessoaIntegracaoDTO.getCepEnderecoResidencial());
		dto.setDescEnderecoComercial(enderecoPessoaIntegracaoDTO.getDescEnderecoComercial());
		dto.setNumComercial(enderecoPessoaIntegracaoDTO.getNumEnderecoComercial());
		dto.setComplementoComercial(enderecoPessoaIntegracaoDTO.getComplementoEnderecoComercial());
		dto.setBairroComercial(enderecoPessoaIntegracaoDTO.getBairroEnderecoComercial());
		dto.setCepComercial(enderecoPessoaIntegracaoDTO.getCepEnderecoComercial());
		dto.setTelefoneResidencial(telefonePessoaIntegracaoDTO.getTelefoneEnderecoResidencial());
		dto.setTelefoneComercial(telefonePessoaIntegracaoDTO.getTelefoneEnderecoComercial());
		if(enderecoPessoaIntegracaoDTO.getIdLocResidencial() != null) {
			LocalizacaoIntegracaoDTO localizacaoIntegracaoResidencialDTO = locInt.consultarLocalidade(enderecoPessoaIntegracaoDTO.getIdLocResidencial());
			dto.setUfResidencial(localizacaoIntegracaoResidencialDTO.getUf());
			dto.setMunicipioResidencial(localizacaoIntegracaoResidencialDTO.getMunicipio());
		}
	}

	private void liquidarEmprestimos(DadosDesligamentoEncontroContasDTO dadosDesligamento, List<ContratoLiquidacaoDTO> contratosDTO, 
			ContaCapital cca, Date dataAtualProduto) throws BancoobException {
		BigDecimal valorPago = BigDecimal.ZERO;
		BigDecimal valorADevolver = BigDecimal.ZERO;
		if (contratosDTO != null && !contratosDTO.isEmpty()) {
			List<ResultadoLiquidacaoDTO> contratosResult = creditoIntegracaoServico.gravarLiquidacao(
					Integer.valueOf(InformacoesUsuario.getInstance().getCooperativa()), 
					InformacoesUsuario.getInstance().getLogin(), contratosDTO);
			for (int i = 0; i < contratosResult.size(); i++) {
				if (contratosResult.get(i).isSucesso()) {
					lancamentoServico.incluir(criarLancamentoEmprestimo(cca, dataAtualProduto, contratosDTO.get(i)));
					valorPago = valorPago.add(contratosResult.get(i).getValorPago());
				} else {					
					for (int j = 0; j < contratosDTO.size(); j++) {
						if(contratosDTO.get(j).getDescOperacaoCredito().equals(contratosResult.get(i).getDescOperacaoCredito())){
							contratosDTO.get(j).setBolErro(Boolean.TRUE);
							contratosDTO.get(j).setDescErro(contratosResult.get(i).getMsgErro());							
							valorADevolver = valorADevolver.add(contratosDTO.get(j).getValorQuitacao());
						}					
					}										
					getLogger().erro(new ContaCapitalMovimentacaoNegocioException("erro ao realizar liquidação de contrato: Contrato-"+contratosResult.get(i).getDescOperacaoCredito()), contratosResult.get(i).getMsgErro());					
				}
			}
		}
		dadosDesligamento.setValorAmortizado(valorPago);
		dadosDesligamento.setSaldoDevolverResidualEmprestimo(valorADevolver);
	}
	
	private LancamentoContaCapital criarLancamentoEmprestimo(ContaCapital cca, Date dataAtualProduto, ContratoLiquidacaoDTO contrato) throws BancoobException {
		LancamentoContaCapital lancamento = new LancamentoContaCapital();
		
		lancamento.setContaCapital(cca);
		lancamento.setBolProcessado((short) 0);
		lancamento.setDataHoraAtualizacao(new DateTimeDB());
		lancamento.setDataLancamento(new DateTimeDB(dataAtualProduto.getTime()));
		lancamento.setIdInstituicao(cca.getIdInstituicao());
		lancamento.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
		lancamento.setValorLancamento(contrato.getValorQuitacao());

		TipoLote tpLote = new TipoLote();
		tpLote.setId(ContaCapitalConstantes.COD_LOTE_CCA_PARC_AVISTA.shortValue());
		lancamento.setTipoLote(tpLote);
		
		TipoHistoricoCCA tipoHistCCA = new TipoHistoricoCCA();
		tipoHistCCA.setId(ContaCapitalConstantes.COD_HISTORICO_CCA_DEVOLUCAO_BAIXA_EMPRESTIMO.shortValue());
		lancamento.setTipoHistoricoCCA(tipoHistCCA);
		
		lancamento.setDescNumDocumento(contrato.getDescOperacaoCredito());
		
		return lancamento;
	}
	
}
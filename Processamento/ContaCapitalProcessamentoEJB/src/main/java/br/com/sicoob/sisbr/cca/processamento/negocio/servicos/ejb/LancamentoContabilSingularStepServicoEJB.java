package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.joda.time.DateTime;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoob;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoobPK;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoIndiretaBancoob;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoIndiretaBancoobPK;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParticipacaoCentralBancoobServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParticipacaoIndiretaBancoobServicoLocal;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ClienteCooperativaLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.OperacaoFinanceiraLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb.OperacaoFinanceiraLegadoServicoEJB;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.OperacaoFinanceiraLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.processamento.util.ProcessamentoUtil;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * @author marco.nascimento@sicoob.com.br
 * @since 20/06/2014
 */
@Stateless
@Remote(StepServico.class)
public class LancamentoContabilSingularStepServicoEJB extends ContaCapitalProcessamentoStep {
	
	private static final ISicoobLogger LOG = getLogger(LancamentoContabilSingularStepServicoEJB.class);
	
	@EJB
	private ParticipacaoCentralBancoobServicoLocal participacaoCentralBancoobServico;
	
	@EJB
	private ParticipacaoIndiretaBancoobServicoLocal participacaoIndiretaBancoobServico;
	
	@EJB
	private OperacaoFinanceiraLegadoServicoLocal operacaoFinanceiraLegadoServico;
	
	/**
	 * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public RetornoExecucao executar(ContextoExecucao ctx) {
		LOG.info("CCA LancamentoContabilSingularStepServicoEJB Executando... ");
		Integer numCoopSingular = getNumCoop(ctx);
		LOG.info("CCA LancamentoContabilSingularStepServicoEJB Executando Step numCoop singular: " + numCoopSingular);
		
		ParticipacaoIndiretaBancoob pib = null;
		try {
			pib = pesqParticipacaoIndiretaPorInstSingularEData(numCoopSingular);
			if (pib != null) {
				LOG.info("CCA LancamentoContabilSingularStepServicoEJB  Valor Participacao Bancoob " + pib.getValorParticipacaoBancoob());
				if (deveExecutarStep(pib)) {
					calcularValorParticipacaoBancoob(pib);
					if (ProcessamentoUtil.isInstituicaosAtiva(pib.getId().getIdInstituicaoSingular()) 
							&& operacaoFinanceiraLegadoServico.existeOperacaoFinanceiraPI(numCoopSingular)) {
						incluirOperacaoFinanceira(numCoopSingular, pib);
					}
				}
			}
			LOG.info("Executou Step");
			return sucesso();
		} catch (BancoobException e) {
			LOG.erro(e, "CCA." + e.getMessage());
			return erro(e.getMessage());
		}
	}

	private boolean deveExecutarStep(ParticipacaoIndiretaBancoob pib) {
		return pib.getValorParticipacaoBancoob() == null;
	}

	private void calcularValorParticipacaoBancoob(ParticipacaoIndiretaBancoob pib) throws BancoobException {
		ParticipacaoCentralBancoobPK pcbPK = new ParticipacaoCentralBancoobPK(pib.getId().getIdInstituicaoCentral(), pib.getId().getNumAnoMesBase());
		ParticipacaoCentralBancoob pcb = participacaoCentralBancoobServico.obter(pcbPK);
		
		BigDecimal valorPercPart = pib.getPercParticipacaoCentral().multiply(pcb.getValorParticipacao());
		valorPercPart = valorPercPart.divide(new BigDecimal(100), 2, RoundingMode.UP);
		
		if (pib.getValorSaldoInteg().compareTo(valorPercPart) > 0)
		{
			pib.setValorParticipacaoBancoob(valorPercPart);
		}else {
			pib.setValorParticipacaoBancoob(pib.getValorSaldoInteg());
		}
		
		participacaoIndiretaBancoobServico.alterar(pib);
	}
	
	/**
	 * Realiza inclusao de operacao financeira referente a particicao indireta e estorno 
	 * {@link OperacaoFinanceiraLegadoServicoEJB#incluir(OperacaoFinanceiraLegadoDTO)}
	 * @param numCoopSingular 
	 * @see OperacaoFinanceira
	 * @param pib
	 * @throws BancoobException
	 */
	private void incluirOperacaoFinanceira(Integer numCoopSingular, ParticipacaoIndiretaBancoob pib) throws BancoobException {
		
		Integer ultimoIdOperacaoFinanceira = operacaoFinanceiraLegadoServico.getUltimoId(numCoopSingular);
		LOG.info("CCA ultimoIdOperacaoFinanceira " + ultimoIdOperacaoFinanceira);
		
		LOG.info("CCA incluindo operacao financeira codigo 269 [VALOR_REF_ESTORNO_PART_INDIRETA]");
		OperacaoFinanceiraLegadoDTO ofEstorno = montarOperacaoFinanceira(numCoopSingular, pib);
		ofEstorno.setIdOperacaoFinanceira(ultimoIdOperacaoFinanceira.intValue() + 1);
		ofEstorno.setIdTipoValorCont(ContaCapitalConstantes.VALOR_REF_ESTORNO_PART_INDIRETA);
		ofEstorno.setValor(operacaoFinanceiraLegadoServico.consultarValorEstorno(numCoopSingular));
		operacaoFinanceiraLegadoServico.incluir(ofEstorno);
		
		LOG.info("CCA incluindo operacao financeira codigo 268 [VALOR_REF_PART_INDIRETA]");
		OperacaoFinanceiraLegadoDTO of = montarOperacaoFinanceira(numCoopSingular, pib);
		of.setIdOperacaoFinanceira(ultimoIdOperacaoFinanceira.intValue() + 2);
		of.setIdTipoValorCont(ContaCapitalConstantes.VALOR_REF_PART_INDIRETA);
		operacaoFinanceiraLegadoServico.incluir(of);
	}

	/**
	 * Retorna ParticipacaoIndiretaBancoob por cooperativa e data
	 * @param numCoopSingular 
	 * @return ParticipacaoIndiretaBancoob
	 */
	private ParticipacaoIndiretaBancoob pesqParticipacaoIndiretaPorInstSingularEData(Integer numCoopSingular) throws BancoobException {
		LOG.info("LancamentoContabilSingularStepServicoEJB pesqPartIndPorInstSingData");
		ConsultaDto<ParticipacaoIndiretaBancoob> criterios = new ConsultaDto<ParticipacaoIndiretaBancoob>();
		ParticipacaoIndiretaBancoob filtroParticipacao = new ParticipacaoIndiretaBancoob();
		filtroParticipacao.setId(new ParticipacaoIndiretaBancoobPK(ProcessamentoUtil.getIdInstituicao(numCoopSingular), ProcessamentoUtil.getAnoMesFormatadoAnterior(new DateTime()), null));
		criterios.setFiltro(filtroParticipacao);
		List<ParticipacaoIndiretaBancoob> resultado = participacaoIndiretaBancoobServico.listar(criterios);
		if(resultado != null && !resultado.isEmpty()) {
			LOG.info("LancamentoContabilSingularStepServicoEJB pesqPartIndPorInstSingData size:" + resultado.size());
			return resultado.get(0);
		}
		return null;
	}
	
	/**
	 * @param numCoopSingular 
	 * @see OperacaoFinanceiraLegadoDTO
	 * @return
	 * @throws BancoobException 
	 */
	private OperacaoFinanceiraLegadoDTO montarOperacaoFinanceira(Integer numCoopSingular, ParticipacaoIndiretaBancoob pib) throws BancoobException {
		OperacaoFinanceiraLegadoDTO of = new OperacaoFinanceiraLegadoDTO();
		of.setDataOpFinanceira(ProcessamentoUtil.getUltimoDiaUtilMesAnterior(pib.getId().getIdInstituicaoSingular()));
		of.setDataProcessamento(ProcessamentoUtil.getDataProcessamento(pib.getId().getIdInstituicaoSingular(), new DateTimeDB()));		
		of.setCodStatusOpFin(ContaCapitalConstantes.COD_STATUS_A_CONTABILIZAR);
		of.setCodTipoMovimento(ContaCapitalConstantes.COD_TIPO_MOV_COMPLEMENTAR);
		of.setCodSubRelCont(ContaCapitalConstantes.COD_SUB_REL_CONT_DEFAULT);
		of.setCodTipoContabilizacao(ContaCapitalConstantes.LCTOCONTABILIZACAO_A_FAZER);
		of.setIdOperacao(ContaCapitalConstantes.ID_OPERACAO_PARTICIPACAO_INDIRETA);
		of.setIdProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
		of.setBolContabilizaCoop(ContaCapitalConstantes.COD_CONTABILIZA_COOP.shortValue());
		of.setBolOpProcessada(ContaCapitalConstantes.COD_OP_NAO_PROCESSADA.shortValue());
		of.setNumPac(ContaCapitalConstantes.NUM_PAC_DEFAULT);
		of.setNumCoopRecebedora(numCoopSingular);
		of.setValor(pib.getValorParticipacaoBancoob().setScale(2, RoundingMode.UP));
		of.setNumCliente(ClienteCooperativaLegadoDelegate.getInstance().consultarClienteCooperativa(numCoopSingular, ContaCapitalConstantes.NUM_PAC_DEFAULT));
		of.setNumCoopSingular(numCoopSingular);
		of.setIdModalidadeProduto(ContaCapitalConstantes.MODALIDADE_PRODUTO);
		return of;
	}
	
}
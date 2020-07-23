package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.joda.time.DateTime;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoob;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoobPK;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoIndiretaBancoob;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoIndiretaBancoobPK;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParticipacaoCentralBancoobServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParticipacaoIndiretaBancoobServicoLocal;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ParticipacaoIndiretaBancoobLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.InformacaoAcumuladaLegadoServicoLocal;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * @author marco.nascimento@sicoob.com.br
 * @since 03/06/2014
 */
@Stateless
@Remote(StepServico.class)
public class PercentualSingularStepServicoEJB extends ContaCapitalProcessamentoStep {
	
	private static final ISicoobLogger LOG = getLogger(PercentualSingularStepServicoEJB.class);
	
	@EJB
	private ParticipacaoIndiretaBancoobServicoLocal participacaoIndiretaBancoobServico;
	
	@EJB
	private ParticipacaoCentralBancoobServicoLocal participacaoCentralBancoobServico;	
	
	@EJB
	private InformacaoAcumuladaLegadoServicoLocal informacaoAcumuladaLegadoServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	private static final String MSG_SUCESSO = "Percentual de participação das singulares calculado com sucesso.";
	private static final String MSG_SUCESSO_SEM_INF_ACUM = "Não existem informações acumuladas para o cálculo de participação.";	
	private static final String MSG_ERRO = "Erro ao calcular o percentual de participação das singulares.";

	private static final int QTD_MESES_DATA_BASE = 2;
	
	/**
	 * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 * Recebe sempre o numero da central.
	 */
	public RetornoExecucao executar(ContextoExecucao ctx) {
		try {
			Integer numCoopCentral = getNumCoop(ctx);
			LOG.info("PercentualSingularStepServicoEJB - Central: " + numCoopCentral.toString());
			Integer idInstCentral = instituicaoIntegracaoServico.obterIdInstituicao(numCoopCentral);
			
			verificarCentralBancoob(idInstCentral);
			verificarSnapshotParticipacaoIndireta(numCoopCentral);
			List<Integer> cooperativasSingulares = consultarCooperativasSingulares(numCoopCentral);
			
			List<ParticipacaoIndiretaBancoobLegadoDTO> lstParticipacaoIndireta = 
					informacaoAcumuladaLegadoServico.calcularParticipacaoIndiretaBancoob(numCoopCentral, cooperativasSingulares, QTD_MESES_DATA_BASE);
			
			for (ParticipacaoIndiretaBancoobLegadoDTO dto : lstParticipacaoIndireta) {
				dto.setIdInstituicao(instituicaoIntegracaoServico.obterIdInstituicao(dto.getNumCooperativa()));
				dto.setIdInstituicaoPai(instituicaoIntegracaoServico.obterIdInstituicao(numCoopCentral));
				atualizarParticipacaoIndiretaBancoob(dto);
			}
			
			LOG.info(lstParticipacaoIndireta.isEmpty() ? MSG_SUCESSO_SEM_INF_ACUM : MSG_SUCESSO);
			return sucesso();
		} catch (BancoobException e) {
			LOG.erro(e, e.getMessage());
			return erro(MSG_ERRO+e.getMessage());
		}
		
	}
	
	/**
	 * Consulta as cooperativas incluidas ha 2 meses atras via snapshots.
	 * @param numCoopCentral
	 * @return
	 * @throws BancoobException
	 */
	private List<Integer> consultarCooperativasSingulares(Integer numCoopCentral) throws BancoobException {
		List<Integer> numCoops = new ArrayList<Integer>();
		ConsultaDto<ParticipacaoIndiretaBancoob> criterios = new ConsultaDto<ParticipacaoIndiretaBancoob>();
		ParticipacaoIndiretaBancoob pib = new ParticipacaoIndiretaBancoob();
		pib.setId(new ParticipacaoIndiretaBancoobPK(null, getAnoMesProcessamento(), instituicaoIntegracaoServico.obterIdInstituicao(numCoopCentral)));
		criterios.setFiltro(pib);
		List<ParticipacaoIndiretaBancoob> results = participacaoIndiretaBancoobServico.listar(criterios);
		for (ParticipacaoIndiretaBancoob participacaoIndiretaBancoob : results) {
			numCoops.add(instituicaoIntegracaoServico.obterNumeroCooperativa(participacaoIndiretaBancoob.getId().getIdInstituicaoSingular()));
		}
		return numCoops;
	}

	/**
	 * Verifica se deve incluir snapshots de cooperativas, olhando o mesAno atual.
	 * @param numCoopCentral
	 * @throws BancoobException
	 */
	private void verificarSnapshotParticipacaoIndireta(Integer numCoopCentral) throws BancoobException {
		Integer anoMes = getAnoMesAtual();
		if (deveIncluirSnapshotParticipacaoIndireta(numCoopCentral, anoMes)) {
			Integer idInstCentral = instituicaoIntegracaoServico.obterIdInstituicao(numCoopCentral);
			List<ParticipacaoIndiretaBancoobLegadoDTO> singularesLst = 
					informacaoAcumuladaLegadoServico.consultarSnapshotCooperativas(numCoopCentral, new DateTime().minusMonths(1));
			for (ParticipacaoIndiretaBancoobLegadoDTO dto : singularesLst) {
				dto.setIdInstituicao(instituicaoIntegracaoServico.obterIdInstituicao(dto.getNumCooperativa()));
				dto.setIdInstituicaoPai(idInstCentral);
				incluirParticipacaoIndiretaBancoobSnapshot(dto, anoMes);
			}
		}
	}

	/**
	 * Deve incluir se nao existirem objetos de ParticipacaoIndireta para a central e anoMes atual.
	 * @param numCoopCentral
	 * @param anoMes
	 * @return
	 * @throws BancoobException
	 */
	private boolean deveIncluirSnapshotParticipacaoIndireta(Integer numCoopCentral, Integer anoMes) throws BancoobException {
		ConsultaDto<ParticipacaoIndiretaBancoob> criterios = new ConsultaDto<ParticipacaoIndiretaBancoob>();
		ParticipacaoIndiretaBancoob filtroParticipacao = new ParticipacaoIndiretaBancoob();
		filtroParticipacao.setId(new ParticipacaoIndiretaBancoobPK(null, anoMes, instituicaoIntegracaoServico.obterIdInstituicao(numCoopCentral)));
		criterios.setFiltro(filtroParticipacao);
		return participacaoIndiretaBancoobServico.listar(criterios).isEmpty();
	}
	
	/**
	 * Inclui a snapshot de ParticipacaoIndireta com os valores de saldo e percentual zerados.
	 * @param dto
	 * @param anoMes
	 * @throws BancoobException
	 */
	private void incluirParticipacaoIndiretaBancoobSnapshot(ParticipacaoIndiretaBancoobLegadoDTO dto, Integer anoMes) throws BancoobException {
		try {			
			ParticipacaoIndiretaBancoob pib = new ParticipacaoIndiretaBancoob();
			ParticipacaoIndiretaBancoobPK id = new ParticipacaoIndiretaBancoobPK(dto.getIdInstituicao(), anoMes, dto.getIdInstituicaoPai());
			pib.setId(id);
			pib.setValorSaldoInteg(BigDecimal.ZERO);
			pib.setPercParticipacaoCentral(BigDecimal.ZERO);
			participacaoIndiretaBancoobServico.incluir(pib);
		} catch (BancoobException e) {
			LOG.erro(e, "CCA." + e.getMessage());
			throw new BancoobException(e);
		}
	}

	/**
	 * Verifica se existe ParticipacaoCentral atual, incluindo caso nao exista.
	 * Atualiza a ParticipacaoCentral de 2 meses atras por saldo contabil.
	 * @param idInstCentral
	 * @throws BancoobException
	 */
	private void verificarCentralBancoob(Integer idInstCentral) throws BancoobException {
		ParticipacaoCentralBancoobPK pkAtual = montarParticipacaoCentralBancoobPK(idInstCentral, 0);
		ParticipacaoCentralBancoobPK pkProcessamento = montarParticipacaoCentralBancoobPK(idInstCentral, QTD_MESES_DATA_BASE);
		if (!participacaoCentralBancoobServico.existeParticipacaoCentralBancoob(pkAtual)) {
			ParticipacaoCentralBancoob pcb = new ParticipacaoCentralBancoob();
			pcb.setId(pkAtual);
			pcb.setValorParticipacao(BigDecimal.ZERO);
			pcb.setIdUsuario(ContaCapitalConstantes.USUARIO_FECHAMENTO_PRODUCAO);
			pcb.setDataHoraAtualizacao(new DateTimeDB());
			participacaoCentralBancoobServico.incluir(pcb);
		}
		participacaoCentralBancoobServico.atualizarParticipacaoCentralPorSaldoContabil(pkProcessamento);
	}

	private ParticipacaoCentralBancoobPK montarParticipacaoCentralBancoobPK(Integer idInstCentral, int qtdMesesAReduzir) {
		ParticipacaoCentralBancoobPK pk = new ParticipacaoCentralBancoobPK();
		pk.setIdInstituicaoCentral(idInstCentral);
		pk.setNumAnoMesBase(ContaCapitalUtil.getAnoMesFormatado(new DateTime().minusMonths(qtdMesesAReduzir)));
		return pk;
	}
	
	private void atualizarParticipacaoIndiretaBancoob(ParticipacaoIndiretaBancoobLegadoDTO dto) throws BancoobException {
		try {
			
			ParticipacaoIndiretaBancoobPK id = new ParticipacaoIndiretaBancoobPK(dto.getIdInstituicao(), getAnoMesProcessamento(), dto.getIdInstituicaoPai());
			ParticipacaoIndiretaBancoob pib = consultarParticipacaoIndireta(id);
			if (pib != null) {
				pib.setValorSaldoInteg(dto.getValor());
				pib.setPercParticipacaoCentral(dto.getPercPartCentral());
				participacaoIndiretaBancoobServico.alterar(pib);
			}
			
		} catch (BancoobException e) {
			LOG.erro(e, "CCA." + e.getMessage());
			throw new BancoobException(e);
		}
	}
	
	private ParticipacaoIndiretaBancoob consultarParticipacaoIndireta(ParticipacaoIndiretaBancoobPK id) throws BancoobException {
		ConsultaDto<ParticipacaoIndiretaBancoob> criterios = new ConsultaDto<ParticipacaoIndiretaBancoob>();
		ParticipacaoIndiretaBancoob pib = new ParticipacaoIndiretaBancoob();
		pib.setId(id);
		criterios.setFiltro(pib);
		List<ParticipacaoIndiretaBancoob> resultado = participacaoIndiretaBancoobServico.listar(criterios);
		return resultado.isEmpty() ? null : resultado.get(0);
	}

	private Integer getAnoMesAtual() {
		return ContaCapitalUtil.getAnoMesFormatado(new DateTime());
	}
	
	private Integer getAnoMesProcessamento() {
		return ContaCapitalUtil.getAnoMesFormatado(new DateTime().minusMonths(QTD_MESES_DATA_BASE));
	}
	
}
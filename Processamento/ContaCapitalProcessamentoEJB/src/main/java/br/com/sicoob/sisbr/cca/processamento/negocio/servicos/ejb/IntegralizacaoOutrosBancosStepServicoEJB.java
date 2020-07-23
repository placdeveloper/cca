package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoLote;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoIntegralizacaoExternaServicoLocal;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;

/**
 * IntegralizacaoOutrosBancosStepServicoEJB
 */
//@Stateless
//@Remote(StepServico.class)
public class IntegralizacaoOutrosBancosStepServicoEJB extends ContaCapitalProcessamentoStep {

	private static final ISicoobLogger LOG = getLogger(IntegralizacaoOutrosBancosStepServicoEJB.class);
	
	@EJB
	private LancamentoIntegralizacaoExternaServicoLocal lancamentoIntegralizacaoExternaServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	public RetornoExecucao executar(ContextoExecucao ctx) {
		Integer numCoopSingular = getNumCoop(ctx);
		try {
			Integer idInstituicao = instituicaoIntegracaoServico.obterIdInstituicao(numCoopSingular);
			List<IntegralizacaoOutrosBancosLegadoDTO> dtos = consultarIntegralizacoesPendentes(); 
			for (IntegralizacaoOutrosBancosLegadoDTO dto : dtos) {
				lancamentoIntegralizacaoExternaServico.incluir(montarIntegralizacaoCapitalDTO(dto, idInstituicao, EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_SUBSC));
				lancamentoIntegralizacaoExternaServico.incluir(montarIntegralizacaoCapitalDTO(dto, idInstituicao, EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_BANCO));
				atualizarSituacaoIntegralizacao(dto);
			}
		} catch (BancoobException e) {
			LOG.erro(e, "CCA." + e.getMessage());
			return erro(e.getMessage());
		}
		return sucesso();
	}

	/**
	 * Consulta as integralizações que ja retornaram dos outros bancos
	 * @return
	 */
	private ArrayList<IntegralizacaoOutrosBancosLegadoDTO> consultarIntegralizacoesPendentes() {
		return new ArrayList<IntegralizacaoOutrosBancosLegadoDTO>(); // TODO
	}
	
	/**
	 * Atualiza a situação da integralização
	 * @param dto
	 */
	private void atualizarSituacaoIntegralizacao(IntegralizacaoOutrosBancosLegadoDTO dto) {
		// TODO
	}
	
	/**
	 * Monta o DTO de integralização de acordo com os parâmetros de entrada do serviço e o tipo de histórico
	 * (subscrição / integralização)
	 * @param dto
	 * @param idInstituicao 
	 * @param tipoHistoricoCCA
	 * @return
	 */
	private IntegralizacaoCapitalDTO montarIntegralizacaoCapitalDTO(IntegralizacaoOutrosBancosLegadoDTO dto,
			Integer idInstituicao, EnumTipoHistoricoCCA tipoHistoricoCCA) {
		IntegralizacaoCapitalDTO integralizacaoCapitalDTO = new IntegralizacaoCapitalDTO();
		integralizacaoCapitalDTO.setIdInstituicao(idInstituicao);
		integralizacaoCapitalDTO.setNumMatricula(dto.getNumMatricula());
		integralizacaoCapitalDTO.setIdTipoHistoricoLanc(tipoHistoricoCCA.getCodigo()); 
		integralizacaoCapitalDTO.setValorLancamento(dto.getValorIntegralizacao());
		integralizacaoCapitalDTO.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_PARC_AVISTA.getCodigo());
		integralizacaoCapitalDTO.setIdOperacaoOrigem("INTEG_OUTROS_BANCOS");
		return integralizacaoCapitalDTO;
	}

}

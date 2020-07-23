package br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cru.api.negocio.delegates.CRUApiFabricaDelegates;
import br.com.sicoob.cru.api.negocio.delegates.LiquidacaoApiDelegate;
import br.com.sicoob.cru.api.negocio.servicos.LiquidacaoApiServico;
import br.com.sicoob.cru.api.vo.ContratoLiquidacaoVO;
import br.com.sicoob.cru.api.vo.ResultadoLiquidacaoVO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContratoLiquidacaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ResultadoLiquidacaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CreditoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CreditoIntegracaoServicoRemote;

/**
 * CreditoIntegracaoServicoEJB
 * @author Nairon.Silva
 */
@Stateless
@Local (CreditoIntegracaoServicoLocal.class)
@Remote(CreditoIntegracaoServicoRemote.class)
public class CreditoIntegracaoServicoEJB extends ContaCapitalIntegracaoServicoEJB implements CreditoIntegracaoServicoLocal, CreditoIntegracaoServicoRemote {

	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CreditoIntegracaoServico#consultarContratosLiquidacao(java.lang.Integer, java.lang.Integer)
	 * FICOU TUDO NO MESMO METODO PRA EVITAR ERRO DE CLASSE NAO ENCONTRADA (NAO SUBIRAM A API)
	 * PODE SER REFATORADO FUTURAMENTE
	 */
	@SuppressWarnings("unchecked")
	public List<ContratoLiquidacaoDTO> consultarContratosLiquidacao(Integer numCooperativa, Integer numCliente) throws BancoobException {
		LiquidacaoApiDelegate<LiquidacaoApiServico> liquidacaoApiDelegate = CRUApiFabricaDelegates.getInstance().getLiquidacaoApiDelegate();
		List<ContratoLiquidacaoDTO> contratosDTO = new ArrayList<ContratoLiquidacaoDTO>();
		List<ContratoLiquidacaoVO> contratosVO = liquidacaoApiDelegate.calcularLiquidacao(numCooperativa, numCliente);
		if (contratosVO != null && !contratosVO.isEmpty()) {
			for (ContratoLiquidacaoVO vo : contratosVO) {
				ContratoLiquidacaoDTO dto = new ContratoLiquidacaoDTO();
				dto.setBolErro(vo.getBolErro());
				dto.setBolLegado(vo.getBolLegado());
				dto.setBolRotativo(vo.getBolRotativo());
				dto.setDataEntradaOperacao(vo.getDataEntradaOperacao());
				dto.setDataUltimaLiquidacao(vo.getDataUltimaLiquidacao());
				dto.setDataVencimento(vo.getDataVencimento());
				dto.setDescErro(vo.getDescErro());
				dto.setDescLinha(vo.getDescLinha());
				dto.setDescOperacaoCredito(vo.getDescOperacaoCredito());
				dto.setDescProduto(vo.getDescProduto());
				dto.setDescTaxaJuros(vo.getDescTaxaJuros());
				dto.setIdModalidadeProduto(vo.getIdModalidadeProduto());
				dto.setIdNivelRisco(vo.getIdNivelRisco());
				dto.setIdOperacaoSISBR20(vo.getIdOperacaoSISBR20());
				dto.setIdOrigemOperacaoCredito(vo.getIdOrigemOperacaoCredito());
				dto.setIdProduto(vo.getIdProduto());
				dto.setNumDiasAtraso(vo.getNumDiasAtraso());
				dto.setNumOperacaoCredito(vo.getNumOperacaoCredito());
				dto.setPercAliquotaDiarioIOF(vo.getPercAliquotaDiarioIOF());
				dto.setPercAliquotaRelativaIOF(vo.getPercAliquotaRelativaIOF());
				dto.setQtdParcelasAberto(vo.getQtdParcelasAberto());
				dto.setQtdParcelasAtraso(vo.getQtdParcelasAtraso());
				dto.setQtdParcelasOperacao(vo.getQtdParcelasOperacao());
				dto.setValorAtrasoRenegociado(vo.getValorAtrasoRenegociado());
				dto.setValorContratado(vo.getValorContratado());
				dto.setValorCreditoIOF(vo.getValorCreditoIOF());
				dto.setValorMora(vo.getValorMora());
				dto.setValorMulta(vo.getValorMulta());
				dto.setValorOutrosEncargos(vo.getValorOutrosEncargos());
				dto.setValorPrincipal(vo.getValorPrincipal());
				dto.setValorRendas(vo.getValorRendas());
				dto.setValorSaldoContabil(vo.getValorSaldoContabil());
				dto.setValorSaldoGanhoAAuferir(vo.getValorSaldoGanhoAAuferir());
				dto.setValorTotalEncargosAtraso(vo.getValorTotalEncargosAtraso());
				dto.setValorQuitacao(vo.getValorQuitacao());
				contratosDTO.add(dto);
			}
		}
		return contratosDTO;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CreditoIntegracaoServico#gravarLiquidacao(java.lang.Integer, java.lang.String, java.util.List)
	 * FICOU TUDO NO MESMO METODO PRA EVITAR ERRO DE CLASSE NAO ENCONTRADA (NAO SUBIRAM A API)
	 * PODE SER REFATORADO FUTURAMENTE
	 */
	@SuppressWarnings("unchecked")
	public List<ResultadoLiquidacaoDTO> gravarLiquidacao(Integer numCooperativa, String idUsuario, List<ContratoLiquidacaoDTO> contratos) throws BancoobException {
		LiquidacaoApiDelegate<LiquidacaoApiServico> liquidacaoApiDelegate = CRUApiFabricaDelegates.getInstance().getLiquidacaoApiDelegate();
		List<ContratoLiquidacaoVO> contratosVO = new ArrayList<ContratoLiquidacaoVO>();
		for (ContratoLiquidacaoDTO dto : contratos) {
			ContratoLiquidacaoVO vo = new ContratoLiquidacaoVO();
			vo.setBolErro(dto.getBolErro());
			vo.setBolLegado(dto.getBolLegado());
			vo.setBolRotativo(dto.getBolRotativo());
			vo.setDataEntradaOperacao(dto.getDataEntradaOperacao());
			vo.setDataUltimaLiquidacao(dto.getDataUltimaLiquidacao());
			vo.setDataVencimento(dto.getDataVencimento());
			vo.setDescErro(dto.getDescErro());
			vo.setDescLinha(dto.getDescLinha());
			vo.setDescOperacaoCredito(dto.getDescOperacaoCredito());
			vo.setDescProduto(dto.getDescProduto());
			vo.setDescTaxaJuros(dto.getDescTaxaJuros());
			vo.setIdModalidadeProduto(dto.getIdModalidadeProduto());
			vo.setIdNivelRisco(dto.getIdNivelRisco());
			vo.setIdOperacaoSISBR20(dto.getIdOperacaoSISBR20());
			vo.setIdOrigemOperacaoCredito(dto.getIdOrigemOperacaoCredito());
			vo.setIdProduto(dto.getIdProduto());
			vo.setNumDiasAtraso(dto.getNumDiasAtraso());
			vo.setNumOperacaoCredito(dto.getNumOperacaoCredito());
			vo.setPercAliquotaDiarioIOF(dto.getPercAliquotaDiarioIOF());
			vo.setPercAliquotaRelativaIOF(dto.getPercAliquotaRelativaIOF());
			vo.setQtdParcelasAberto(dto.getQtdParcelasAberto());
			vo.setQtdParcelasAtraso(dto.getQtdParcelasAtraso());
			vo.setQtdParcelasOperacao(dto.getQtdParcelasOperacao());
			vo.setValorAtrasoRenegociado(dto.getValorAtrasoRenegociado());
			vo.setValorContratado(dto.getValorContratado());
			vo.setValorCreditoIOF(dto.getValorCreditoIOF());
			vo.setValorMora(dto.getValorMora());
			vo.setValorMulta(dto.getValorMulta());
			vo.setValorOutrosEncargos(dto.getValorOutrosEncargos());
			vo.setValorPrincipal(dto.getValorPrincipal());
			vo.setValorRendas(dto.getValorRendas());
			vo.setValorSaldoContabil(dto.getValorSaldoContabil());
			vo.setValorSaldoGanhoAAuferir(dto.getValorSaldoGanhoAAuferir());
			vo.setValorTotalEncargosAtraso(dto.getValorTotalEncargosAtraso());
			vo.setValorQuitacao(dto.getValorQuitacao());
			contratosVO.add(vo);
		}
		List<ResultadoLiquidacaoDTO> resultadosDTO = new ArrayList<ResultadoLiquidacaoDTO>();
		List<ResultadoLiquidacaoVO> resultadosVO = liquidacaoApiDelegate.gravarLiquidacao(numCooperativa, idUsuario, contratosVO);
		for (ResultadoLiquidacaoVO vo : resultadosVO) {
			ResultadoLiquidacaoDTO dto = new ResultadoLiquidacaoDTO();
			dto.setDescOperacaoCredito(vo.getContrato().getDescOperacaoCredito());
			dto.setMsgErro(vo.getMsgErro());
			dto.setSucesso(vo.isSucesso());
			dto.setValorPago(vo.getContrato().getValorQuitacao());
			resultadosDTO.add(dto);
		}
		return resultadosDTO;
	}
	
}

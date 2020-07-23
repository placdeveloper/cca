package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.contacapital.comum.negocio.entidades.ValorCotas;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ValorCotasDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.excecao.AtendimentoCadastroException;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.DadosValorCotasProxyVO;

@RemoteService
public class ValorCotaPorPeriodoServico extends AtendimentoFachada {

	ValorCotasDelegate valorDelegate = AtendimentoFabricaDelegates.getInstance().criarValorCotasDelegate();
	
	public RetornoDTO incluirDados(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		if (dto.getDados().containsKey("valorCotas")) {
			DadosValorCotasProxyVO valCota = (DadosValorCotasProxyVO) dto.getDados().get(
					"valorCotas");
			Boolean valido = valorDelegate.validarCamposObrigatorios(valCota);
			if (valido) {
				ValorCotas vc = new ValorCotas();
				vc.setId(new Date(valCota.getDataInicialCota().getTime()));
				vc.setDataCadastroCota(new Date(valCota.getDataCadastroCota().getTime()));
				vc.setBolLimIntegralCapConsignado(valCota.getBolLimIntegralCapConsignado());
				vc.setNumMinCotasInteg(valCota.getNumMinCotasInteg());					
				vc.setPercMinIntegralizacao(valCota.getPercMinIntegralizacao());
				vc.setValorCota(valCota.getValorCota());
				vc.setValorLimiteIntegralMaximo(valCota.getValorLimiteIntegralMaximo());
				vc.setValorLimiteIntegralMinimo(valCota.getValorLimiteIntegralMinimo());
				vc.setValorSalarioBase(valCota.getValorSalarioBase());
				ValorCotas val = valorDelegate.obter(vc.getId());
				if(val == null){
					valorDelegate.incluir(vc);
				}else{
					throw new AtendimentoCadastroException("Já existe cota cadastrada nesta data. A alteração não poderá ser efetuada.");
				}
			}
		}
		return retorno;
	}
		
	public RetornoDTO excluirDados(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		if (dto.getDados().containsKey("valorCotas")) {
			DadosValorCotasProxyVO valCota = (DadosValorCotasProxyVO) dto.getDados().get(
					"valorCotas");
			
			if (valCota.getDataInicialCota() != null) {
				valorDelegate.excluir(new Date(valCota.getDataInicialCota().getTime()));
			}
		}
		return retorno;
	}

	public RetornoDTO alterarDados(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		if (dto.getDados().containsKey("valorCotas")) {
			DadosValorCotasProxyVO valCota = (DadosValorCotasProxyVO) dto.getDados().get(
					"valorCotas");
			ValorCotas vc = new ValorCotas();
			Boolean valido = valorDelegate.validarCamposObrigatorios(valCota);
			if (valido) {
				vc.setId(new Date(valCota.getDataInicialCota().getTime()));
				vc.setDataCadastroCota(new Date(valCota.getDataCadastroCota().getTime()));
				vc.setBolLimIntegralCapConsignado(valCota.getBolLimIntegralCapConsignado());
				vc.setNumMinCotasInteg(valCota.getNumMinCotasInteg());					
				vc.setPercMinIntegralizacao(valCota.getPercMinIntegralizacao());
				vc.setValorCota(valCota.getValorCota());
				vc.setValorLimiteIntegralMaximo(valCota.getValorLimiteIntegralMaximo());
				vc.setValorLimiteIntegralMinimo(valCota.getValorLimiteIntegralMinimo());
				vc.setValorSalarioBase(valCota.getValorSalarioBase());
				valorDelegate.alterar(vc);
			}
		}
		return retorno;
	}

	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("dtInicial", obterDataAtualMovimento());
		return retorno;
	}

	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto)
			throws BancoobException {
		ConsultaDto<DadosValorCotasProxyVO> consultaDto = montarConsultaDto(dto,
				DadosValorCotasProxyVO.class);
		ConsultaDto<DadosValorCotasProxyVO> result = valorDelegate.obterListaSelecaoGeral(consultaDto);
		DadosSelGeralDTO retorno = montarResultado(result);
		return retorno;
	}

	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		return retorno;
	}

	public RetornoDTO obterDefinicoesSelecao(RequisicaoReqDTO dto)
			throws BancoobException {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}

package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.retaguarda.comum.util.DataUtil;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.mensagens.AtendimentoBOResourceBundle;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.FechamentoContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ProvisaoJurosCCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.ConsultaProvisaoJurosVO;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class ConsultaProvisaoJurosServico extends AtendimentoFachada {
	
	private ProvisaoJurosCCapitalDelegate provDelegate = 
			AtendimentoFabricaDelegates.getInstance().criarProvisaoJurosCCapitalDelegate();	
	
	private FechamentoContaCapitalDelegate fechCcaDelegate =
			AtendimentoFabricaDelegates.getInstance().criarFechamentoContaCapitalDelegate();

	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {		
		
		RetornoDTO retorno = new RetornoDTO();
		
		if(validarInicializacao(retorno)){
			DateTime dtAtualProduto = obterDataAtualProduto();
			
			Integer ano = DataUtil.getAno(dtAtualProduto);
			
			retorno.getDados().put("ano", ano);
		}
		
		return retorno;
	}

	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		
		RetornoDTO retorno = new RetornoDTO();
		
		return retorno;
	}

	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto)
			throws BancoobException {
		
		ConsultaDto<ConsultaProvisaoJurosVO> consultaDto = montarConsultaDto(
				dto, ConsultaProvisaoJurosVO.class);
		
		provDelegate.recuperarDadosProcProvisaoJuros(consultaDto);
		
		DadosSelGeralDTO retorno = montarResultado(consultaDto);
		
		return retorno;
	}
	
	private Boolean validarInicializacao(RetornoDTO retorno) throws BancoobException{
		
		if(fechCcaDelegate.verificarFechCapitalIniciado()){
			retorno.getDados().put("flagInicializacao", false);
			retorno.getDados().put("msgInicializacao"
					, AtendimentoBOResourceBundle.getInstance().getString(
							"msg.erro.verificaFechamentoCapitalIniciado"));
			return false;
		}
		
		retorno.getDados().put("flagInicializacao", true);
		return true;		
	}


}

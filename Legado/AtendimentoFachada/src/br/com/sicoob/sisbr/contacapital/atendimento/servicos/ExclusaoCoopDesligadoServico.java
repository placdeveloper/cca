package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.DestinoProvisaoDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.HistContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.InfAcumuladaDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.LancamentosCCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ParcelamentoCCADelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ProvisaoJurosCCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.excecao.AtendimentoCadastroException;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.DadosContaCapitalProxy;

@RemoteService
public class ExclusaoCoopDesligadoServico extends AtendimentoFachada {

	private ContaCapitalDelegate ccaDelegate = AtendimentoFabricaDelegates.getInstance().criarContaCapitalDelegate();
	private DestinoProvisaoDelegate oDestJuros = AtendimentoFabricaDelegates.getInstance().criarDestinoProvisaoDelegate();
	private ProvisaoJurosCCapitalDelegate oProvJuros = AtendimentoFabricaDelegates.getInstance().criarProvisaoJurosCCapitalDelegate();
	private InfAcumuladaDelegate oInfAcum = AtendimentoFabricaDelegates.getInstance().criarInfAcumuladaDelegate();
	private LancamentosCCapitalDelegate oLancCca = AtendimentoFabricaDelegates.getInstance().criarLancamentosCCapitalDelegate();
	private HistContaCapitalDelegate oHistCCA = AtendimentoFabricaDelegates.getInstance().criarHistContaCapitalDelegate();
	private ParcelamentoCCADelegate oParcela = AtendimentoFabricaDelegates.getInstance().criarParcelamentoCCADelegate();
	
	public RetornoDTO obterDados(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		Integer tipoProcura = (Integer) dto.getDados().get("tipoProcura");
		String procuraPor = (String) dto.getDados().get("txtProcura");
		if(procuraPor != null && procuraPor.equals("")){
			procuraPor = null;
		}
		List<DadosContaCapitalProxy> dados = ccaDelegate.obterDadosCCAExclusao(tipoProcura, procuraPor);

		retornoDTO.getDados().put("registros", dados);
		return retornoDTO;
	}
	
	public RetornoDTO validarExclusao(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		Integer lNumMatricula = (Integer) dto.getDados().get("lNumMatricula");
		Boolean valido = ccaDelegate.validarExcluirCoopDeslig(lNumMatricula.longValue(), obterDataAtualProduto());
		retorno.getDados().put("valido", valido);
		return retorno;
	}
	
	public RetornoDTO excluirCooperado(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		Integer lNumMatricula = (Integer) dto.getDados().get("lNumMatricula");
		Boolean valido = ccaDelegate.validarExcluirCoopDeslig(lNumMatricula.longValue(), obterDataAtualProduto());
		if(valido){
			try {
				//Excluir destinoprovisao
				oDestJuros.excluirDestinoProvCoop(lNumMatricula.longValue());			    
			    //Excluir provisaojurosccapital
			    oProvJuros.excluirProvJurosCoop(lNumMatricula.longValue());			    
			    //Excluir InfAcumulada where idproduto = 2
			    oInfAcum.excluirInfAcumCoopCCA(lNumMatricula.longValue());			    
			    //Excluir lancamentosccapital
			    //Alterar capalotecapital
			    oLancCca.excluirTodosLancCoop(lNumMatricula.longValue());			    
			    //Excluir histcontacapital
			    oHistCCA.excluirPorMatricula(lNumMatricula.longValue());
			    //Excluir parcelamentoCCA			    
			    oParcela.excluirPorMatricula(lNumMatricula.longValue());			    
			    //Excluir contacapital
			    ccaDelegate.excluir(lNumMatricula.longValue());
			} catch (AtendimentoCadastroException e) {
				throw new BancoobException("Erro ao excluir dados.");
			}
		}
		return retorno;
	}

	
}

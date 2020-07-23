package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.contacapital.comum.negocio.entidades.ValorCotas;
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.produto.negocio.delegates.ProdutoFabricaDelegates;
import br.com.sicoob.produto.negocio.delegates.ProdutosDelegate;
import br.com.sicoob.produto.negocio.entidades.Produto;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.FechamentoContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ParcelamentoCCADelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.SaldoContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ValorCotasDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.EstornoCapitalVO;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class EstornoCapitalServico extends AtendimentoFachada {

	private static final String KEY_DATA_ESTORNO = "dataEstorno";
	private static final String KEY_FLAG_INICIALIZACAO = "flagInicializacao";
	private static final String KEY_MSG_INICIALIZACAO = "msgInicializacao";
	private static final String KEY_VALOR_SUSBSCRICAO = "valorSubscricao";
	private static final String KEY_VALOR_INTEGRALIZADO = "valorIntegralizado";
	private static final String KEY_VALOR_INTEGRALIZAR = "valorIntegralizar";
	private static final String KEY_VALOR_BLOQUEADO = "valorBloqueado";
	private static final String KEY_QUANTIDADE_COTAS = "quantidadeCotas";
	private static final String KEY_VALOR_REST = "valorRest";
	private static final String KEY_PARCELAS = "parcelas";
	
	private ProdutosDelegate produtoDelegate = ProdutoFabricaDelegates
			.getInstance().criarProdutosDelegate();	
	private ValorCotasDelegate valorCotasDelegate = AtendimentoFabricaDelegates
			.getInstance().criarValorCotasDelegate();
	private SaldoContaCapitalDelegate saldoContaCapitalDelegate = AtendimentoFabricaDelegates
			.getInstance().criarSaldoContaCapitalDelegate();
	private ContaCapitalDelegate contaCapitalDelegate =  AtendimentoFabricaDelegates
			.getInstance().criarContaCapitalDelegate();
	private ParcelamentoCCADelegate parcelamentoCCADelegate = AtendimentoFabricaDelegates.getInstance().criarParcelamentoCCADelegate();
	private FechamentoContaCapitalDelegate fechamentoDelegate = AtendimentoFabricaDelegates.getInstance().criarFechamentoContaCapitalDelegate(); 
		
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {		
		RetornoDTO retornoDTO = new RetornoDTO();
		if(validarInicializacao(retornoDTO)){
			retornoDTO.getDados().put(KEY_DATA_ESTORNO, recuperarDataAtualProduto());			
		}
		valorCotasDelegate.obterValorMinimoSubscricaoCapital();
		return retornoDTO;
	}
	
private Boolean validarInicializacao(RetornoDTO retorno) throws BancoobException{
		
		if(fechamentoDelegate.verificarFechCapitalIniciado()){
			retorno.getDados().put(KEY_FLAG_INICIALIZACAO, false);
			retorno.getDados().put(KEY_MSG_INICIALIZACAO
					, MensagemUtil.getString(
							"msg.erro.verificaFechamentoCapitalIniciado"));
			return false;
		}
		
		List<ValorCotas> listaValorCotas = valorCotasDelegate.listar();
		if( listaValorCotas == null || listaValorCotas.isEmpty()){
			retorno.getDados().put(KEY_FLAG_INICIALIZACAO, false);
			retorno.getDados().put(KEY_MSG_INICIALIZACAO
					, MensagemUtil.getString("msg.erro.informeValorCota"));
			return false;		
		}		
		retorno.getDados().put(KEY_FLAG_INICIALIZACAO, true);
		return true;		
	}
		
	
	public RetornoDTO apuracaoCooperado(RequisicaoReqDTO dto)
			throws BancoobException {		
		RetornoDTO retornoDTO = new RetornoDTO();		
		EstornoCapitalVO estornoCapitalVO = (EstornoCapitalVO)dto.getDados().get("estornoCapitalVo");

		Long matricula = estornoCapitalVO.getMatricula();
		Integer statusCooperado = estornoCapitalVO.getStatusCooperado();
		Integer codigoCooperado = estornoCapitalVO.getCodigoCooperado();
		
		if(statusCooperado.equals(ContaCapitalConstantes.COOPERADO_ATIVO)){
			if(codigoCooperado!=0){
				BigDecimal cValorSubsc = saldoContaCapitalDelegate.obterSaldo(matricula, ContaCapitalConstantes.COD_GRUPO_HIST_SUBSCRICAO);
				BigDecimal cValorInteg = saldoContaCapitalDelegate.obterSaldo(matricula, ContaCapitalConstantes.COD_GRUPO_HIST_INTEGRALIZACAO);
				Integer quantidadeCotas = valorCotasDelegate.obterQuantidadeCotas(cValorSubsc);
				BigDecimal valorIntegralizar = cValorSubsc.subtract(cValorInteg);		
				BigDecimal valorBloqueado = contaCapitalDelegate.recuperarValorBloqueado(matricula);

				retornoDTO.getDados().put(KEY_VALOR_SUSBSCRICAO, cValorSubsc);
				retornoDTO.getDados().put(KEY_VALOR_INTEGRALIZADO, cValorInteg);
				retornoDTO.getDados().put(KEY_VALOR_INTEGRALIZAR, valorIntegralizar);
				retornoDTO.getDados().put(KEY_VALOR_BLOQUEADO, valorBloqueado);
				retornoDTO.getDados().put(KEY_QUANTIDADE_COTAS, quantidadeCotas);
			}
		}
		else{//COOPERADO DESLIGADO			
			if(statusCooperado.equals(ContaCapitalConstantes.COOPERADO_DESLIGADO)){
				BigDecimal valorRestituir = saldoContaCapitalDelegate.verificarSaldoRestituir(matricula.longValue());
				Integer numParcelamento = parcelamentoCCADelegate.obterParcelamentoAtual(matricula.longValue(), ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_DEVOLUCAO, ContaCapitalConstantes.COD_PARCELA_GERADA);
				BigDecimal valorParcelasAbertas = parcelamentoCCADelegate.obterValorTotalParcelamento(matricula.longValue(), ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_DEVOLUCAO, ContaCapitalConstantes.COD_PARCELA_GERADA,numParcelamento);
				retornoDTO.getDados().put(KEY_VALOR_REST, valorRestituir);
				retornoDTO.getDados().put(KEY_PARCELAS, valorParcelasAbertas);
			}
		}
		return retornoDTO;
	}

	public RetornoDTO estornarCapital(RequisicaoReqDTO dto)
			throws BancoobException {

		RetornoDTO retorno = new RetornoDTO();		
		EstornoCapitalVO estornoCapitalVO = (EstornoCapitalVO)dto.getDados().get("estornoCapitalVo");
		
		Long numMatricula = estornoCapitalVO.getMatricula();
		Integer statusCooperado = estornoCapitalVO.getStatusCooperado();		
		DateTime dataEstorno = estornoCapitalVO.getDataEstorno();		
		BigDecimal valorEstorno = estornoCapitalVO.getValorEstorno();
		
		Integer ativo;
		if(statusCooperado.equals(ContaCapitalConstantes.COOPERADO_ATIVO)){
			 ativo = 1;
		}
		else{
			 ativo = 0;
		}
		Boolean valido = contaCapitalDelegate.validarEstornoCapital(numMatricula.longValue(), ativo, dataEstorno, valorEstorno);
		if(valido){
			contaCapitalDelegate.estornarCapital(numMatricula.longValue(),ativo, valorEstorno, dataEstorno);
		}
		return retorno;
	}
	
	private DateTime recuperarDataAtualProduto() throws BancoobException {

		Produto produto = produtoDelegate.obter(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
		DateTime dataAtualMovimento = null;
		if (produto != null && produto.getDataAtualMovimento() != null) {
			dataAtualMovimento = new DateTime(produto.getDataAtualMovimento()
					.getTime());
		}
		return dataAtualMovimento;
	}

	
}

package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.awt.Image;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.ImageIcon;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.contacapital.comum.negocio.entidades.ValorCotas;
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.produto.negocio.delegates.ProdutoFabricaDelegates;
import br.com.sicoob.produto.negocio.delegates.ProdutosDelegate;
import br.com.sicoob.produto.negocio.entidades.Produto;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.mensagens.AtendimentoBOResourceBundle;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.relatorios.RelatorioAtendimentoCCA;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.FechamentoContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.SaldoContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.TransferenciaCotasDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ValorCotasDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.DadosReciboTransferenciaVO;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.TransferenciaCotasVO;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class TransferenciaCotasServico extends AtendimentoFachada {

	private static final String KEY_DATA_ESTORNO = "dataEstorno";
	private static final String KEY_FLAG_INICIALIZACAO = "flagInicializacao";
	private static final String KEY_MSG_INICIALIZACAO = "msgInicializacao";
	private static final String KEY_VALOR_SUSBSCRICAO = "valorSubscricao";
	private static final String KEY_VALOR_INTEGRALIZADO = "valorIntegralizado";
	private static final String KEY_VALOR_BLOQUEADO = "valorBloqueado";
	
	private static final String IMAGEM_TESOURA = 
			"br/com/sicoob/sisbr/contacapital/atendimento/infraestrutura/imagens/tesoura.gif";
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");	
	
	private ProdutosDelegate produtoDelegate = ProdutoFabricaDelegates
			.getInstance().criarProdutosDelegate();	
	private ValorCotasDelegate valorCotasDelegate = AtendimentoFabricaDelegates
			.getInstance().criarValorCotasDelegate();
	private SaldoContaCapitalDelegate saldoContaCapitalDelegate = AtendimentoFabricaDelegates
			.getInstance().criarSaldoContaCapitalDelegate();
	private ContaCapitalDelegate contaCapitalDelegate =  AtendimentoFabricaDelegates
			.getInstance().criarContaCapitalDelegate();
	private FechamentoContaCapitalDelegate fechamentoDelegate = AtendimentoFabricaDelegates.getInstance().criarFechamentoContaCapitalDelegate();
	private TransferenciaCotasDelegate transferenciaCotasDelegate = AtendimentoFabricaDelegates.getInstance().criarTransferenciaCotasDelegate();
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {		
		RetornoDTO retornoDTO = new RetornoDTO();
		if(validarInicializacao(retornoDTO)){
			retornoDTO.getDados().put(KEY_DATA_ESTORNO, recuperarDataAtualProduto());			
		}
		return retornoDTO;
	}
	
	private Boolean validarInicializacao(RetornoDTO retorno) throws BancoobException{
		
		if(fechamentoDelegate.verificarFechCapitalIniciado()){
			retorno.getDados().put(KEY_FLAG_INICIALIZACAO, false);
			retorno.getDados().put(KEY_MSG_INICIALIZACAO,AtendimentoBOResourceBundle.getInstance().getString(
							"msg.erro.verificaFechamentoCapitalIniciado"));
			return false;
		}
		
		List<ValorCotas> listaValorCotas = valorCotasDelegate.listar();
		if( listaValorCotas == null || listaValorCotas.isEmpty()){
			retorno.getDados().put(KEY_FLAG_INICIALIZACAO, false);
			retorno.getDados().put(KEY_MSG_INICIALIZACAO,
					AtendimentoBOResourceBundle.getInstance().getString("msg.erro.informeValorCota"));
			return false;		
		}		
		retorno.getDados().put(KEY_FLAG_INICIALIZACAO, true);
		return true;		
	}
		
	
	public RetornoDTO apuracaoCooperado(RequisicaoReqDTO dto)
			throws BancoobException {		
		RetornoDTO retornoDTO = new RetornoDTO();
		
		String matriculaStr = (String) dto.getDados().get("matricula");
		String codigoStr = (String) dto.getDados().get("codigo");

		Long matricula = Long.valueOf(matriculaStr);
		Integer codigo = Integer.valueOf(codigoStr);

		if(codigo != 0){
			BigDecimal cValorSubsc = saldoContaCapitalDelegate.obterSaldo(matricula, ContaCapitalConstantes.COD_GRUPO_HIST_SUBSCRICAO);
			BigDecimal cValorInteg = saldoContaCapitalDelegate.obterSaldo(matricula, ContaCapitalConstantes.COD_GRUPO_HIST_INTEGRALIZACAO);
			BigDecimal valorBloqueado = contaCapitalDelegate.recuperarValorBloqueado(matricula);

			retornoDTO.getDados().put(KEY_VALOR_SUSBSCRICAO, cValorSubsc);
			retornoDTO.getDados().put(KEY_VALOR_INTEGRALIZADO, cValorInteg);
			retornoDTO.getDados().put(KEY_VALOR_BLOQUEADO, valorBloqueado);
		}
		
		return retornoDTO;
	}

	public RetornoDTO transferirCotas(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		TransferenciaCotasVO vo = (TransferenciaCotasVO) dto.getDados().get("transferenciaCotas");
		
		transferenciaCotasDelegate.transferir(vo.getMatriculaCredito(), 
				vo.getMatriculaDebito(), vo.getValorTransferencia());
		
		return retornoDTO;
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

	public RetornoDTO emitirRecibo(RequisicaoReqDTO dto)
			throws BancoobException {
		TransferenciaCotasVO vo = (TransferenciaCotasVO) dto.getDados().get("transferenciaCotas");
		
		DateTime dataAtualProduto = obterDataAtualProduto();
		DadosReciboTransferenciaVO dadosVO = transferenciaCotasDelegate.gerarReciboTransferencia(vo.getMatriculaDebito(),vo.getMatriculaDebito(),
					new Date(dataAtualProduto.getTime()),new Date(dataAtualProduto.getTime()), -1, vo
						.getMatriculaCredito(), vo.getValorTransferencia());
		
		List<DadosReciboTransferenciaVO> lista = new ArrayList<DadosReciboTransferenciaVO>();
		lista.add(dadosVO);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		
		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");  
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		
		String siglaCooperativa = obterSiglaCooperativa();
		String descricaoCoop = obterCooperativaFmt() 
				+ " - " + siglaCooperativa;
		parametros.put("DESCRICAO_COOP", descricaoCoop);
		parametros.put("NOME_COOPERATIVA", siglaCooperativa);
		parametros.put("IMAGEM_TESOURA", recuperaImageTesoura());
		
		RelatorioAtendimentoCCA<DadosReciboTransferenciaVO> relatorio = new RelatorioAtendimentoCCA<DadosReciboTransferenciaVO>(
				lista, "CCARelatorioReciboTransferenciaCotas.jasper", parametros);
		
		Object conteudo = relatorio.gerarSincronamente();
		ContextoHttp.getInstance().adicionarContexto("CCARelatorioReciboTransferenciaCotas", conteudo);
		
		return new RetornoDTO();
	}
	
	private Image recuperaImageTesoura() {
		URL urlImagem = Thread.currentThread().getContextClassLoader()
				.getResource(IMAGEM_TESOURA);
		ImageIcon imagem = new ImageIcon(urlImagem);
		return imagem.getImage();
	}

	
}

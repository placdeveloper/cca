package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.contacapital.comum.negocio.entidades.CondicaoAssociacao;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.relatorios.RelatorioAtendimentoCCA;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.CondicaoAssociacaoDelegate;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class RelCondicoesDeAssociacaoServico extends AtendimentoFachada {

	private CondicaoAssociacaoDelegate condicaoAssociacaoDelegate = AtendimentoFabricaDelegates
			.getInstance().criarCondicaoAssociacaoDelegate();

	public static final String CODIGO_RELATORIO = "CCA-024";
	public static final String NOME_RELATORIO_SESSAO = "CCARelCondicoesDeAssociacao";
	public static final String NOME_RELATORIO_ARQUIVO = "CCARelCondicoesDeAssociacao.jasper";
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");	

	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		// TODO Auto-generated method stub
		return retorno;
	}

	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {

		RetornoDTO retorno;
		DateTime dataAtualProduto;
		List<CondicaoAssociacao> dadosRelatorio;
		Map<String, Object> parametros;
		RelatorioAtendimentoCCA<CondicaoAssociacao> relatorio;

		dataAtualProduto = obterDataAtualProduto();

		parametros = new HashMap<String, Object>();
		parametros.put("DADOS_COOPERATIVA", descricaoCooperativa());
		parametros.put("DATA_ATUAL_PRODUTO", dataAtualProduto);

		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");  
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		
		dadosRelatorio = condicaoAssociacaoDelegate.listar();

		relatorio = new RelatorioAtendimentoCCA<CondicaoAssociacao>(
				dadosRelatorio, NOME_RELATORIO_ARQUIVO, parametros);

		ContextoHttp.getInstance().adicionarContexto(NOME_RELATORIO_SESSAO,
				relatorio.gerarSincronamente());

		retorno = new RetornoDTO();

		return retorno;
	}

	private String descricaoCooperativa() throws BancoobException {
		StringBuilder sb = new StringBuilder();

		sb.append(obterCooperativaFmt());
		sb.append(" - ");
		sb.append(obterSiglaCooperativa());

		return sb.toString();
	}
	

}

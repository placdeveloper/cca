package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.ArrayList;
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
import br.com.sicoob.retaguarda.comum.negocio.vo.PacVO;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.vo.OrdenacaoVO;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.relatorios.RelatorioAtendimentoCCA;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.RelatorioNaoCooperadoVO;

@RemoteService
public class RelNaoCooperadoServico extends AtendimentoFachada {
	
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");

	private RelContaCapitalDelegate relContaCapitalDelegate =  AtendimentoFabricaDelegates
			.getInstance().criarRelContaCapitalDelegate();
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		
		RetornoDTO retornoDTO = new RetornoDTO();
		List<PacVO> lista = obterListaPACs();
		montaListaPacVO(lista);
		retornoDTO.getDados().put("listaPacs",lista);
		retornoDTO.getDados().put("listaOrdenacao",obterListaOrdenacao());		
		RelatorioNaoCooperadoVO vo = relContaCapitalDelegate.obterCoopInativoMinMax();
		retornoDTO.getDados().put("codInicial",vo.getCodigoInicial().toString());
		retornoDTO.getDados().put("codFinal",vo.getCodigoFinal().toString());
		
		return retornoDTO;
	}
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {
		
		RetornoDTO retornoDTO = new RetornoDTO();			
		Integer numPac = (Integer) dto.getDados().get("pac");
		String descricaoPac = (String) dto.getDados().get("descricaoPac");
		Integer idOrdenacao = (Integer) dto.getDados().get("ordenacao");
		Integer codigoInicial = (Integer) dto.getDados().get("codigoInicial");
		Integer codigoFinal = (Integer) dto.getDados().get("codigoFinal");		
		
		List<RelatorioNaoCooperadoVO> lista = RelContaCapitalDelegate.getInstance().gerarDadosRelatorioNaoCooperado(codigoInicial.longValue(), codigoFinal.longValue(), numPac, idOrdenacao);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		String siglaCooperativa = obterSiglaCooperativa();
		String descricaoCoop = obterCooperativaFmt() 
				+ " - " + siglaCooperativa;
		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		parametros.put("DESCRICAO_COOP", descricaoCoop);
		parametros.put("NOME_COOPERATIVA", siglaCooperativa);
		parametros.put("CODIGO_RELATORIO", "CCA-028");
		parametros.put("DATA_PROCESSAMENTO", obterDataAtualMovimento());
		
		if(numPac!=null && numPac != -1){
			descricaoPac = "Cooperativa/Pac: "+descricaoPac;
			parametros.put("DESCRICAO_PAC", descricaoPac);
		}
			
		
		RelatorioAtendimentoCCA<RelatorioNaoCooperadoVO> rel = new RelatorioAtendimentoCCA<RelatorioNaoCooperadoVO>(
				lista, "CCARelNaoCooperado.jasper", parametros);
							
		Object conteudo = rel.gerarSincronamente();
		ContextoHttp.getInstance().adicionarContexto("CCARelNaoCooperado", conteudo);
		
		return retornoDTO;
	}
	
	public List<OrdenacaoVO> obterListaOrdenacao(){
		List<OrdenacaoVO> lista = new ArrayList<OrdenacaoVO>();
		OrdenacaoVO ordenacao = new OrdenacaoVO();
		ordenacao.setIdOrdenacao(1);
		ordenacao.setDescOrdenacao("CÓDIGO DO CLIENTE");
		lista.add(ordenacao);
		
		ordenacao = new OrdenacaoVO();
		ordenacao.setIdOrdenacao(2);
		ordenacao.setDescOrdenacao("NOME DO CLIENTE");
		
		lista.add(ordenacao);
		return lista;
	}

	
}

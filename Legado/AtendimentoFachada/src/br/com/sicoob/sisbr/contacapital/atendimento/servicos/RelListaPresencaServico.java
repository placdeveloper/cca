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
import br.com.sicoob.retaguarda.comum.negocio.vo.PacVO;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.relatorios.RelatorioAtendimentoCCA;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.RelContaCapitalProxy;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.RelatorioListaPresencaVO;

@RemoteService
public class RelListaPresencaServico extends AtendimentoFachada {
	
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");

	private static final String KEY_LISTA_PACS = "listaPacs";
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		
		RetornoDTO retornoDTO = new RetornoDTO();
		List<PacVO> lista = recuperarListaPACs();
		montaListaPacVO(lista);
		retornoDTO.getDados().put(KEY_LISTA_PACS,lista);
		
		return retornoDTO;
	}

	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		RelContaCapitalProxy	relContaCapital = (RelContaCapitalProxy)dto.getDados().get("relContaCapitalProxy");		
		List<RelatorioListaPresencaVO> lista = RelContaCapitalDelegate.getInstance().gerarDadosRelatorioListaPresenca(relContaCapital);
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		String siglaCooperativa = obterSiglaCooperativa();
		String descricaoCoop = obterCooperativaFmt() 
				+ " - " + siglaCooperativa;
		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		parametros.put("DESCRICAO_COOP", descricaoCoop);
		parametros.put("NOME_COOPERATIVA", siglaCooperativa);
		parametros.put("CODIGO_RELATORIO", "CCA-023");
			
		parametros.put("DATA_PROCESSAMENTO", obterDataAtualProduto());
				
		if(relContaCapital.getiNumPac()!=null && relContaCapital.getiNumPac() != -1){
			relContaCapital.setDescricaoPac("Cooperativa/Pac: "+relContaCapital.getDescricaoPac());
			parametros.put("DESCRICAO_PAC", relContaCapital.getDescricaoPac());
		}
					
		RelatorioAtendimentoCCA<RelatorioListaPresencaVO> rel = new RelatorioAtendimentoCCA<RelatorioListaPresencaVO>(
				lista, "CCARelListaPresenca.jasper", parametros);
							
		Object conteudo = rel.gerarSincronamente();
		ContextoHttp.getInstance().adicionarContexto("CCARelListaPresenca", conteudo);
		
		return retornoDTO;
	}

	
}

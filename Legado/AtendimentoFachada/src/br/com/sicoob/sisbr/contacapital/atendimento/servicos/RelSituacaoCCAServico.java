package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.ArrayList;
import java.util.Date;
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
import br.com.sicoob.retaguarda.comum.util.RetaguardaComumUtil;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.relatorios.RelatorioAtendimentoCCA;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.RelContaCapitalProxy;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.RelatorioSituacaoCCAVO;

@RemoteService
public class RelSituacaoCCAServico extends AtendimentoFachada {

	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		
		RetornoDTO retornoDTO = new RetornoDTO();
		List<PacVO> lista = obterListaPACs();
		montaListaPacVO(lista);
		retornoDTO.getDados().put("listaPacs",lista);
		List<String> listaOrdenacao = new ArrayList<String>();
		listaOrdenacao.add("MATRÍCULA");//POSIÇÃO 0
		listaOrdenacao.add("NOME DO CLIENTE");//POSIÇÃO 1
		retornoDTO.getDados().put("listaOrdenacao",listaOrdenacao);		
		String[] minMax = obterMatricMinimaMaxima();
		retornoDTO.getDados().put("matriculaInicial", minMax[0]);
		retornoDTO.getDados().put("matriculaFinal",minMax[1]);
		
		return retornoDTO;
	}
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {

		RetornoDTO retornoDTO = new RetornoDTO();
		RelContaCapitalProxy relContaCapital = (RelContaCapitalProxy)dto.getDados().get("relContaCapitalProxy");		
		List<RelatorioSituacaoCCAVO> lista = RelContaCapitalDelegate.getInstance().gerarDadosRelatorioSituacaoCCA(relContaCapital);		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");
		String siglaCooperativa = obterSiglaCooperativa();
		String descricaoCoop = obterCooperativaFmt() 
				+ " - " + siglaCooperativa;
		parametros.put("DESCRICAO_COOP", descricaoCoop);
		parametros.put("NOME_COOPERATIVA", siglaCooperativa);
		parametros.put("CODIGO_RELATORIO", "CCA-005");
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));	
		String descricao = "Matrículas: "+RetaguardaComumUtil.completarEsquerdaString(relContaCapital.getlMatriculaInicial().toString(), "0", 8)
				+" a "+RetaguardaComumUtil.completarEsquerdaString(relContaCapital.getlMatriculaFinal().toString(), "0", 8)+" - Ordenação: ";
		if(relContaCapital.getiOrdenacao()==0){
			descricao = descricao.concat("Matrícula");
		}
		else{
			descricao = descricao.concat("Nome do Cliente");
		}
		parametros.put("DESCRICAO_MATRICULA", descricao	);
		parametros.put("DATA_PROCESSAMENTO", new Date());
		
		if(relContaCapital.getiNumPac()!=null && relContaCapital.getiNumPac() != -1){
			relContaCapital.setDescricaoPac("Cooperativa/Pac: "+relContaCapital.getDescricaoPac());
			parametros.put("DESCRICAO_PAC", relContaCapital.getDescricaoPac());
		}
					
		RelatorioAtendimentoCCA<RelatorioSituacaoCCAVO> rel = new RelatorioAtendimentoCCA<RelatorioSituacaoCCAVO>(
				lista, "CCARelSituacaoCCA.jasper", parametros);
							
		Object conteudo = rel.gerarSincronamente();
		ContextoHttp.getInstance().adicionarContexto("CCARelSituacaoCCA", conteudo);
		
		return retornoDTO;
	}
	
	
}

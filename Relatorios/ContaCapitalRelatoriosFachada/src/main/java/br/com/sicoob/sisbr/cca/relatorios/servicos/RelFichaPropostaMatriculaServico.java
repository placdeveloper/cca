/*
 * 
 */
package br.com.sicoob.sisbr.cca.relatorios.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.cca.relatorios.negocio.delegates.ContaCapitalRelatoriosFabricaDelegates;
import br.com.sicoob.cca.relatorios.negocio.delegates.RelFichaPropostaMatriculaDelegate;
import br.com.sicoob.cca.relatorios.negocio.dto.RelFichaPropostaMatriculaDTO;
import br.com.sicoob.sisbr.cca.relatorios.RelatoriosContaCapital;

/**
 * Classe de serviço responsável das funções relativas a relatório de ficha de matrícula.
 */
@RemoteService
public class RelFichaPropostaMatriculaServico extends RelatoriosContaCapital {
	
	private RelFichaPropostaMatriculaDelegate  relFichaPropostaMatriculaDelegate = ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelFichaPropostaMatriculaDelegate(); 
	
	/**
	 * Método responsável para buscar as informações do usuário para montar o relatório.
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		retorno.getDados().put("idInstituicao", InformacoesUsuario.getInstance().getIdInstituicao());

		return retorno;
	}
		 
	/**
	 * Método responsável para preencher o DTO da pessoa do relatório com o seu idInstituicao, 
	 * idContaCapital e idPessoa para montar o relatório.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO emitirRelatorioFichaPropostaMatricula(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		RelFichaPropostaMatriculaDTO dtoRelatorio = new RelFichaPropostaMatriculaDTO();
		
		dtoRelatorio.setIdInstituicao(Integer.valueOf(dto.getDados().get("idInstituicao").toString()));
		dtoRelatorio.setIdContaCapital(Integer.valueOf(dto.getDados().get("idContaCapital").toString()));
		dtoRelatorio.setIdPessoa(Integer.valueOf(dto.getDados().get("idPessoa").toString()));		
		
		if(dtoRelatorio.getIdInstituicao() != null && dtoRelatorio.getIdInstituicao().intValue() > 0) {
			ContextoHttp.getInstance().adicionarContexto("RelFichaPropostaMatricula", relFichaPropostaMatriculaDelegate.gerarRelatorioFichaPropostaMatricula(dtoRelatorio));	
		}
		
		return retorno;
	}
}

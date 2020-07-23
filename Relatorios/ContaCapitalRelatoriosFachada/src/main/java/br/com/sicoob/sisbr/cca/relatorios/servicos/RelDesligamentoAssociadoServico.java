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
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.relatorios.negocio.delegates.ContaCapitalRelatoriosFabricaDelegates;
import br.com.sicoob.cca.relatorios.negocio.delegates.RelDesligamentoAssociadoDelegate;
import br.com.sicoob.cca.relatorios.negocio.delegates.RelImpedimentosDesligamentoDelegate;
import br.com.sicoob.cca.relatorios.negocio.dto.RelDesligamentoAssociadoDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelDesligamentoEncontroContasListaDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelImpedimentosDesligamentoDTO;
import br.com.sicoob.sisbr.cca.relatorios.RelatoriosContaCapital;

/**
 * RelDesligamentoAssociadoServico
 */
@RemoteService
public class RelDesligamentoAssociadoServico extends RelatoriosContaCapital {

	private RelDesligamentoAssociadoDelegate desligamentoDelegate = ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelDesligamentoAssociadoDelegate();
	private RelImpedimentosDesligamentoDelegate impedimentoDelegate = ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelImpedimentosDesligamentoDelegate();
	
	/**
	 * Método que recebe o objeto de requisição (dto) com os valores para gerar relatorio de demissao associado
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO emitirDesligamentoAssociado(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		RelDesligamentoAssociadoDTO dtoRelatorio = new RelDesligamentoAssociadoDTO();
		
		dtoRelatorio.setIdInstituicao(Integer.valueOf(dto.getDados().get("idInstituicao").toString()));
		dtoRelatorio.setIdContaCapital(Integer.valueOf(dto.getDados().get("idContaCapital").toString()));
		dtoRelatorio.setIdPessoa(Integer.valueOf(dto.getDados().get("idPessoa").toString()));		
		
		if(dtoRelatorio.getIdInstituicao() != null && dtoRelatorio.getIdInstituicao().intValue() > 0) {
			ContextoHttp.getInstance().adicionarContexto("RelDesligamentoAssociado", desligamentoDelegate.gerarRelatorioDesligamentoAssociado(dtoRelatorio));	
		}
		
		return retorno;
	}
	
	/**
	 * Método que recebe o objeto de requisição (dto) com os valores para gerar relatorio de demissao associado
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO emitirDesligamentoEncontroContas(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		if (dto.getDados().get("idContaCapital") != null) {
			Integer idContaCapital = Integer.valueOf(dto.getDados().get("idContaCapital").toString());
			ContextoHttp.getInstance().adicionarContexto("RelDesligamentoEncontroContas", desligamentoDelegate.gerarRelatorioDesligamentoEncontroContas(idContaCapital));
		}
		return retorno;
	}
	
	/**
	 * Método que recebe o objeto de requisição (dto) com os valores para gerar relatório de impedimentos de desligamento cca
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO emitirImpedimentos(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		RelImpedimentosDesligamentoDTO dtoRelatorio = new RelImpedimentosDesligamentoDTO();
		
		dtoRelatorio.setIdInstituicao(Integer.valueOf(dto.getDados().get("idInstituicao").toString()));
		dtoRelatorio.setIdContaCapital(Integer.valueOf(dto.getDados().get("idContaCapital").toString()));
		dtoRelatorio.setIdPessoa(Integer.valueOf(dto.getDados().get("idPessoa").toString()));
		if (dto.getDados().get("esconderEmprestimos") != null) {
			dtoRelatorio.setEsconderEmprestimos(true);
		}
		
		if(dtoRelatorio.getIdInstituicao() != null && dtoRelatorio.getIdInstituicao().intValue() > 0) {
			ContextoHttp.getInstance().adicionarContexto("RelImpedimentosDesligamento", impedimentoDelegate.gerarRelatorioImpedimentosDesligamento(dtoRelatorio));	
		}
		
		return retorno;
	}
	
	/**
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoesEncontroContas(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		ContaCapitalDelegate ccaDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate();
		Integer idInstituicao = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		retorno.getDados().put("idInstituicao", idInstituicao);
		retorno.getDados().put("numContaCapitalInicial", ccaDelegate.obterMenorContaCapitalPorInstituicao(idInstituicao));
		retorno.getDados().put("numContaCapitalFinal", ccaDelegate.obterMaiorContaCapitalPorInstituicao(idInstituicao));
		return retorno;
	}
	
	/**
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO emitirRelatorioDesligamentoEncontroContasLista(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		RelDesligamentoEncontroContasListaDTO filtro = new RelDesligamentoEncontroContasListaDTO();
		filtro.setIdInstituicao(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		filtro.setNumContaCapitalInicial(Integer.valueOf(dto.getDados().get("numContaCapitalInicial").toString()));
		filtro.setNumContaCapitalFinal(Integer.valueOf(dto.getDados().get("numContaCapitalFinal").toString()));
		ContextoHttp.getInstance().adicionarContexto("RelDesligamentoEncontroContasLista", desligamentoDelegate.gerarRelatorioDesligamentoEncontroContasLista(filtro));
		return retorno;
	}
	
}

/*
 * 
 */
package br.com.sicoob.sisbr.cca.relatorios.servicos;

import java.math.BigDecimal;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.cca.relatorios.negocio.delegates.ContaCapitalRelatoriosFabricaDelegates;
import br.com.sicoob.cca.relatorios.negocio.delegates.RelContaCapitalDelegate;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSolDevolucaoCapitalDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelTransferenciaCapitalDTO;
import br.com.sicoob.sisbr.cca.relatorios.RelatoriosContaCapital;

/**
 * Fachada RelContaCapitalServico
 */
@RemoteService
public class RelContaCapitalServico extends RelatoriosContaCapital {

	private RelContaCapitalDelegate relContaCapitalDelegate = ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelContaCapitalDelegate();
	
	/**
	 * Método que recebe o objeto de requisição (dto) com os valores para gerar relatorio de devolução
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
		
	public RetornoDTO gerarSolDevolucaoCapital(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		RelSolDevolucaoCapitalDTO dtoRelatorio = new RelSolDevolucaoCapitalDTO();
		
		dtoRelatorio.setIdInstituicao(Integer.valueOf(dto.getDados().get("idInstituicao").toString()));
		dtoRelatorio.setIdContaCapital(Integer.valueOf(dto.getDados().get("idContaCapital").toString()));
		dtoRelatorio.setIdPessoa(Integer.valueOf(dto.getDados().get("idPessoa").toString()));			
		
		if(dtoRelatorio.getIdInstituicao() != null && dtoRelatorio.getIdInstituicao().intValue() > 0) {
			ContextoHttp.getInstance().adicionarContexto("RelSolDevolucaoCapital", relContaCapitalDelegate.gerarSolDevolucaoCapital(dtoRelatorio));
		}
		
		return retorno;
	}
	

	/**
	 * Método que recebe o objeto de requisição (dto) com os valores para gerar recibo de transferencia de capital
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
		
	public RetornoDTO gerarReciboTransferenciaCapital(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		RelTransferenciaCapitalDTO dtoRelatorio = new RelTransferenciaCapitalDTO();
		
		dtoRelatorio.setIdInstituicaoDebito(Integer.valueOf(dto.getDados().get("idInstituicaoDebito").toString()));
		dtoRelatorio.setNumContaCapitalDebito(Integer.valueOf(dto.getDados().get("numContaCapitalDebito").toString()));
		dtoRelatorio.setIdPessoaDebito(Integer.valueOf(dto.getDados().get("idPessoaDebito").toString()));			
		dtoRelatorio.setIdContaCapitalDebito(Integer.valueOf(dto.getDados().get("idContaCapitalDebito").toString()));			
		
		dtoRelatorio.setIdInstituicaoCredito(Integer.valueOf(dto.getDados().get("idInstituicaoCredito").toString()));
		dtoRelatorio.setNumContaCapitalCredito(Integer.valueOf(dto.getDados().get("numContaCapitalCredito").toString()));
		dtoRelatorio.setIdPessoaCredito(Integer.valueOf(dto.getDados().get("idPessoaCredito").toString()));			
		dtoRelatorio.setIdContaCapitalCredito(Integer.valueOf(dto.getDados().get("idContaCapitalCredito").toString()));			

		dtoRelatorio.setVlrTransferir(new BigDecimal(dto.getDados().get("vlrTransferir").toString()));			
		
		ContextoHttp.getInstance().adicionarContexto("RelReciboTransferenciaCapital", relContaCapitalDelegate.gerarReciboTransferenciaCapital(dtoRelatorio));
		
		return retorno;
	}
	
	
}

/*
 * 
 */
package br.com.sicoob.sisbr.cca.relatorios.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.cca.relatorios.negocio.delegates.ContaCapitalRelatoriosFabricaDelegates;
import br.com.sicoob.cca.relatorios.negocio.delegates.RelAprovacaoQuadroPendenciaDelegate;
import br.com.sicoob.cca.relatorios.negocio.dto.RelAprovacaoQuadroPendenciaDTO;
import br.com.sicoob.sisbr.cca.relatorios.RelatoriosContaCapital;

/**
 * Fachada RelAprovacaoQuadroPendenciaServico
 */
@RemoteService
public class RelAprovacaoQuadroPendenciaServico extends RelatoriosContaCapital {

	private RelAprovacaoQuadroPendenciaDelegate delegate = ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelAprovacaoQuadroPendenciaDelegate();
	
	
	/**
	 * Método que recebe o objeto de requisição (dto) com os valores para gerar relatório de Aprovacao Quadro Pendencia
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO emitirRelatorioAprovacaoQuadroPendencia(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		RelAprovacaoQuadroPendenciaDTO dtoRelatorio = new RelAprovacaoQuadroPendenciaDTO();
		
		dtoRelatorio.setIdInstituicao(Integer.valueOf(dto.getDados().get("idInstituicao").toString()));
		dtoRelatorio.setIdContaCapital(Integer.valueOf(dto.getDados().get("idContaCapital").toString()));
		dtoRelatorio.setIdPessoa(Integer.valueOf(dto.getDados().get("idPessoa").toString()));		
		
		if(dtoRelatorio.getIdInstituicao() != null && dtoRelatorio.getIdInstituicao().intValue() > 0) {
			ContextoHttp.getInstance().adicionarContexto("RelAprovacaoQuadroPendencia", delegate.gerarRelatorioAprovacaoQuadroPendencia(dtoRelatorio));	
		}
		
		return retorno;
	}
	
}

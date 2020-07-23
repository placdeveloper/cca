package br.com.sicoob.cca.relatorios.negocio.servicos.ejb.v2;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.dto.RelFichaPropostaMatriculaDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelFichaPropostaMatriculaServicoLocal;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Relatorio FichaPropostaMatricula
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelV2FichaPropostaMatriculaServicoEJB implements IProcessamentoRelatorioServico {

	@EJB
	private RelFichaPropostaMatriculaServicoLocal servico;
	
	/**
	 * @see br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico#gerarRelatorio(br.com.sicoob.relatorio.api.dto.ParametroDTO, br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		
		RelFichaPropostaMatriculaDTO dtoRelatorio = new RelFichaPropostaMatriculaDTO();
		
		dtoRelatorio.setIdInstituicao(Integer.valueOf(dto.getDados().get("idInstituicao").toString()));
		dtoRelatorio.setIdContaCapital(Integer.valueOf(dto.getDados().get("idContaCapital").toString()));
		dtoRelatorio.setIdPessoa(Integer.valueOf(dto.getDados().get("idPessoa").toString()));
		
		servico.configurarParametrosComuns(rDto);
		RelatorioContaCapitalV2<RelFichaPropostaMatriculaDTO> rel = 
				(RelatorioContaCapitalV2<RelFichaPropostaMatriculaDTO>) servico.gerarRelatorioFichaPropostaMatricula(dtoRelatorio);
		
		return rel.gerar(rDto);
	}

}

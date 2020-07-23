package br.com.sicoob.cca.relatorios.negocio.servicos.ejb.v2;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.dto.RelImpedimentosDesligamentoDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelImpedimentosDesligamentoServicoLocal;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Relatorio de impedimentos
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelV2ImpedimentosDesligamentoServicoEJB implements IProcessamentoRelatorioServico {

	@EJB
	private RelImpedimentosDesligamentoServicoLocal servico;
	
	/**
	 * @see br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico#gerarRelatorio(br.com.sicoob.relatorio.api.dto.ParametroDTO, br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {

		RelImpedimentosDesligamentoDTO dtoRelatorio = new RelImpedimentosDesligamentoDTO();
		
		dtoRelatorio.setIdInstituicao(Integer.valueOf(dto.getDados().get("idInstituicao").toString()));
		dtoRelatorio.setIdContaCapital(Integer.valueOf(dto.getDados().get("idContaCapital").toString()));
		dtoRelatorio.setIdPessoa(Integer.valueOf(dto.getDados().get("idPessoa").toString()));
		if (dto.getDados().get("esconderEmprestimos") != null) {
			dtoRelatorio.setEsconderEmprestimos(true);
		}
		
		servico.configurarParametrosComuns(rDto);
		RelatorioContaCapitalV2<RelImpedimentosDesligamentoDTO> rel = 
				(RelatorioContaCapitalV2<RelImpedimentosDesligamentoDTO>) servico.gerarRelatorioImpedimentosDesligamento(dtoRelatorio);
		
		return rel.gerar(rDto);
	}

}

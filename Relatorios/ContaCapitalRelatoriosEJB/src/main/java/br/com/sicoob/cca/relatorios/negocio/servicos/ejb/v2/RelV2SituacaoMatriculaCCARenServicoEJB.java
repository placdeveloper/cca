package br.com.sicoob.cca.relatorios.negocio.servicos.ejb.v2;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSituacaoMatriculaCCARenDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelSituacaoMatriculaCCARenServicoLocal;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;


/**
 * The Class RelV2SituacaoMatriculaCCARenEJB.
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelV2SituacaoMatriculaCCARenServicoEJB implements IProcessamentoRelatorioServico {

	/** The RelSituacaoMatriculaCCARenServicoLocal. */
	@EJB
	private RelSituacaoMatriculaCCARenServicoLocal servico;

	/* (non-Javadoc)
	 * @see br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico#gerarRelatorio(br.com.sicoob.relatorio.api.dto.ParametroDTO, br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		
		RelSituacaoMatriculaCCARenDTO filtro = montarDTOConsulta(dto);
		
		servico.configurarParametrosComuns(rDto);
		RelatorioContaCapitalV2<RelSituacaoMatriculaCCARenDTO> rel = 
				(RelatorioContaCapitalV2<RelSituacaoMatriculaCCARenDTO>) 
				servico.gerarRelatorio(filtro);
		
		return rel.gerar(rDto);
	}
	
	/**
	 * Montar DTO consulta.
	 *
	 * @param param the param
	 * @return the rel situacao matricula CCA ren DTO
	 */
	private RelSituacaoMatriculaCCARenDTO montarDTOConsulta(ParametroDTO param) {
		Map<Serializable, Serializable> dados = param.getDados();
		RelSituacaoMatriculaCCARenDTO dto = new RelSituacaoMatriculaCCARenDTO();
		dto.setIdInstituicao(Integer.valueOf(dados.get("filtroIdInstituicao").toString()));
		dto.setNumContaCapitalInicial(Integer.valueOf(dados.get("filtroNumContaCapitalInicial").toString()));
		dto.setNumContaCapitalFinal(Integer.valueOf(dados.get("filtroNumContaCapitalFinal").toString()));
		dto.setIdSituacaoConta(Integer.valueOf(dados.get("filtroIdSituacaoConta").toString()));
		dto.setNumPA(Integer.valueOf(dados.get("filtroNumPA").toString()));
		dto.setOrdenacao(Integer.valueOf(dados.get("filtroOrdenacao").toString()));
		
		if(dados.get("filtroCadastrosAprovados") != null && StringUtils.isNotBlank(dados.get("filtroCadastrosAprovados").toString())) {
			dto.setCadastrosAprovados(Boolean.valueOf(dados.get("filtroCadastrosAprovados").toString()));
		}
		
		if(dados.get("filtroUltimaSituacao") != null && StringUtils.isNotBlank(dados.get("filtroUltimaSituacao").toString())) {
			dto.setUltimaSituacao(Boolean.valueOf(dados.get("filtroUltimaSituacao").toString()));
		}

		return dto;
	}
}
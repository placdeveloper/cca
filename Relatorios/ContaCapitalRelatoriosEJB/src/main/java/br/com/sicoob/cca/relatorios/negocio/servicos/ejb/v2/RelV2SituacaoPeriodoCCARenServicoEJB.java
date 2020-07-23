package br.com.sicoob.cca.relatorios.negocio.servicos.ejb.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSituacaoPeriodoCCARenDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelSituacaoPeriodoCCARenServicoLocal;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;


/**
 * The Class RelV2SituacaoPeriodoCCARenEJB.
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelV2SituacaoPeriodoCCARenServicoEJB implements IProcessamentoRelatorioServico {

	/** The RelSituacaoPeriodoCCARenServicoLocal. */
	@EJB
	private RelSituacaoPeriodoCCARenServicoLocal servico;

	/* (non-Javadoc)
	 * @see br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico#gerarRelatorio(br.com.sicoob.relatorio.api.dto.ParametroDTO, br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		
		RelSituacaoPeriodoCCARenDTO filtro = montarDTOConsulta(dto);
		
		servico.configurarParametrosComuns(rDto);
		RelatorioContaCapitalV2<RelSituacaoPeriodoCCARenDTO> rel = 
				(RelatorioContaCapitalV2<RelSituacaoPeriodoCCARenDTO>) 
				servico.gerarRelatorio(filtro);
		
		return rel.gerar(rDto);
	}
	
	/**
	 * Montar DTO consulta.
	 *
	 * @param param the param
	 * @return the rel situacao Periodo CCA ren DTO
	 */
	private RelSituacaoPeriodoCCARenDTO montarDTOConsulta(ParametroDTO param) {
		Map<Serializable, Serializable> dados = param.getDados();
		RelSituacaoPeriodoCCARenDTO dto = new RelSituacaoPeriodoCCARenDTO();
		dto.setIdInstituicao(Integer.valueOf(dados.get("filtroIdInstituicao").toString()));
		dto.setDataPeriodoInicial((Date) dados.get("filtroDataPeriodoInicial"));
		dto.setDataPeriodoFinal((Date) dados.get("filtroDataPeriodoFinal"));
		dto.setIdSituacao(Integer.valueOf(dados.get("filtroIdSituacao").toString()));
		dto.setNumPA(Integer.valueOf(dados.get("filtroNumPA").toString()));
		dto.setOrdenacao(Integer.valueOf(dados.get("filtroOrdenacao").toString()));
		
		if(dados.get("filtroCadastrosAprovados") != null && StringUtils.isNotBlank(dados.get("filtroCadastrosAprovados").toString())) {
			dto.setCadastrosAprovados(Boolean.valueOf(dados.get("filtroCadastrosAprovados").toString()));
		}
		
		return dto;
	}
}
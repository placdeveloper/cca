package br.com.sicoob.cca.relatorios.negocio.servicos.ejb.v2;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.dto.RelRecolhimentoIrrfDestinacaoJurosDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelRecolhimentoIrrfDestinacaoJurosServicoLocal;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;


/**
 * The Class RelV2RecolhimentoIrrfDestinacaoJurosEJB.
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelV2RecolhimentoIrrfDestinacaoJurosServicoEJB implements IProcessamentoRelatorioServico {

	@EJB
	private RelRecolhimentoIrrfDestinacaoJurosServicoLocal servico;

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		
		RelRecolhimentoIrrfDestinacaoJurosDTO filtro = montarDTOConsulta(dto);
		
		servico.configurarParametrosComuns(rDto);
		RelatorioContaCapitalV2<RelRecolhimentoIrrfDestinacaoJurosDTO> rel = 
				(RelatorioContaCapitalV2<RelRecolhimentoIrrfDestinacaoJurosDTO>) 
				servico.gerarRelatorioRecolhimentoIrrfDestinacaoJuros(filtro);
		
		return rel.gerar(rDto);
	}
	
	/**
	 * Montar DTO consulta
	 *
	 * @param param the param
	 * @return the rel recolhimento irrf destinacao juros DTO
	 */
	private RelRecolhimentoIrrfDestinacaoJurosDTO montarDTOConsulta(ParametroDTO param) {
		Map<Serializable, Serializable> dados = param.getDados();
		RelRecolhimentoIrrfDestinacaoJurosDTO dto = new RelRecolhimentoIrrfDestinacaoJurosDTO();
		
		dto.setIdInstituicao(Integer.valueOf(dados.get("filtroIdInstituicao").toString()));
		dto.setTodos(Boolean.valueOf(dados.get("filtroTodos").toString()));
		dto.setNumContaCapital(Integer.valueOf(dados.get("filtroNumContaCapital").toString()));
		dto.setAnoBase(Integer.valueOf(dados.get("filtroAnoBase").toString()));
		dto.setAgruparPorPA(Boolean.valueOf(dados.get("filtroAgruparPorPA").toString()));
		dto.setOrdenacao(Integer.valueOf(dados.get("filtroOrdenacao").toString()));
		
		if(dados.get("filtroNumPac") != null) {
			dto.setNumPac(Integer.valueOf(dados.get("filtroNumPac").toString()));
		}
		return dto;
	}
}
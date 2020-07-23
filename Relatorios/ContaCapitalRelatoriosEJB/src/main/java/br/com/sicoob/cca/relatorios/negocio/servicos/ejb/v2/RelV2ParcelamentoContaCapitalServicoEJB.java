package br.com.sicoob.cca.relatorios.negocio.servicos.ejb.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.dto.RelParcelamentoContaCapitalDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelParcelamentoContaCapitalServicoLocal;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Relatorio Parcelamento Conta Capital
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelV2ParcelamentoContaCapitalServicoEJB implements IProcessamentoRelatorioServico {

	@EJB
	private RelParcelamentoContaCapitalServicoLocal servico;
	
	/**
	 * @see br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico#gerarRelatorio(br.com.sicoob.relatorio.api.dto.ParametroDTO, br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		
		RelParcelamentoContaCapitalDTO filtro = montarDTOConsulta(dto);
		
		servico.configurarParametrosComuns(rDto);
		RelatorioContaCapitalV2<RelParcelamentoContaCapitalDTO> rel = 
				(RelatorioContaCapitalV2<RelParcelamentoContaCapitalDTO>) 
				servico.gerarRelatorioParcelamentoContaCapital(filtro);
		
		return rel.gerar(rDto);
	}
	
	/**
	 * Monta filtro com parametros da camada de apresentacao
	 * @param param
	 * @return Filtro RelParcelamentoContaCapitalDTO
	 */
	private RelParcelamentoContaCapitalDTO montarDTOConsulta(ParametroDTO param) {
		Map<Serializable, Serializable> dados = param.getDados();
		RelParcelamentoContaCapitalDTO dto = new RelParcelamentoContaCapitalDTO();
		dto.setIdInstituicao(Integer.valueOf(dados.get("filtroIdInstituicao").toString()));
		dto.setNumContaCapitalInicial(Integer.valueOf(dados.get("filtroNumContaCapitalInicial").toString()));
		dto.setNumContaCapitalFinal(Integer.valueOf(dados.get("filtroNumContaCapitalFinal").toString()));
		dto.setTipoParcelamento(Integer.valueOf(dados.get("filtroTipoParcelameto").toString()));
		dto.setFormaParcelamento(Integer.valueOf(dados.get("filtroFormaParcelamento").toString()));
		dto.setSituacaoParcela(Integer.valueOf(dados.get("filtroSituacaoParcela").toString()));
		dto.setDataInicialVencimento((Date) dados.get("filtroPeriodoInicial"));
		dto.setDataFinalVencimento((Date) dados.get("filtroPeriodoFinal"));
		dto.setOrdenacao(Integer.valueOf(dados.get("filtroOrdenacao").toString()));
		dto.setNumPA(Integer.valueOf(dados.get("filtroNumPA").toString()));
		if(dados.get("filtroDataSituacao") != null) {
			dto.setDataSituacao((Date) dados.get("filtroDataSituacao"));
		}
		return dto;
	}
}
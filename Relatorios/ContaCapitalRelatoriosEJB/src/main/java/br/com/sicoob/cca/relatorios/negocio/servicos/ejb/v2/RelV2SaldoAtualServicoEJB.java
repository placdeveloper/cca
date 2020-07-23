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
import br.com.sicoob.cca.relatorios.negocio.dto.RelSaldoAtualDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelSaldoAtualServicoLocal;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;


/**
 * The Class RelV2SaldoAtualServicoEJB.
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelV2SaldoAtualServicoEJB implements IProcessamentoRelatorioServico {

	@EJB
	private RelSaldoAtualServicoLocal servico;

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		
		RelSaldoAtualDTO filtro = montarDTOConsulta(dto);
		
		servico.configurarParametrosComuns(rDto);
		RelatorioContaCapitalV2<RelSaldoAtualDTO> rel = 
				(RelatorioContaCapitalV2<RelSaldoAtualDTO>) 
				servico.gerarRelatorioSaldoAtual(filtro);
		
		return rel.gerar(rDto);
	}
	
	/**
	 * Montar DTO consulta.
	 *
	 * @param param the param
	 * @return the rel saldo atual DTO
	 */
	private RelSaldoAtualDTO montarDTOConsulta(ParametroDTO param) {
		Map<Serializable, Serializable> dados = param.getDados();
		RelSaldoAtualDTO dto = new RelSaldoAtualDTO();
		dto.setIdInstituicao(Integer.valueOf(dados.get("filtroIdInstituicao").toString()));
		dto.setNumContaCapitalInicial(Integer.valueOf(dados.get("filtroNumContaCapitalInicial").toString()));
		dto.setNumContaCapitalFinal(Integer.valueOf(dados.get("filtroNumContaCapitalFinal").toString()));
		dto.setIdSituacaoConta(Integer.valueOf(dados.get("filtroIdSituacaoConta").toString()));
		dto.setAgruparPorPA(Boolean.valueOf(dados.get("filtroAgruparPorPA").toString()));
		dto.setOrdenacao(Integer.valueOf(dados.get("filtroOrdenacao").toString()));
		
		if(dados.get("filtroCadastrosAprovados") != null && StringUtils.isNotBlank(dados.get("filtroCadastrosAprovados").toString())) {
			dto.setCadastrosAprovados(Boolean.valueOf(dados.get("filtroCadastrosAprovados").toString()));
		}
		if(dados.get("filtroCnpjEmpresa") != null && StringUtils.isNotBlank(dados.get("filtroCnpjEmpresa").toString())) {
			dto.setCnpjEmpresa(dados.get("filtroCnpjEmpresa").toString());
		}
		if(dados.get("filtroDescEmpresa") != null && StringUtils.isNotBlank(dados.get("filtroDescEmpresa").toString())) {
			dto.setDescEmpresa(dados.get("filtroDescEmpresa").toString());
		}
		if(dados.get("filtroNumPessoaJuridica") != null) {
			dto.setNumPessoaJuridica(Integer.valueOf(dados.get("filtroNumPessoaJuridica").toString()));
		}
		if(dados.get("filtroNumPA") != null) {
			dto.setNumPA(Integer.valueOf(dados.get("filtroNumPA").toString()));
		}
		return dto;
	}
}
package br.com.sicoob.cca.relatorios.negocio.servicos.ejb.v2;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.dto.RelBloqueioContaCapitalDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelBloqueioServicoLocal;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Relatorio Bloqueios
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelV2BloqueiosServicoEJB implements IProcessamentoRelatorioServico {

	@EJB
	private RelBloqueioServicoLocal servico;
	
	/**
	 * @see br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico#gerarRelatorio(br.com.sicoob.relatorio.api.dto.ParametroDTO, br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		String tipoPesquisa = dto.getDados().get("tipoPesquisa").toString();
		BloqueioContaCapitalDTO filtro = montarDTOConsulta(dto);
		
		servico.configurarParametrosComuns(rDto);
		RelatorioContaCapitalV2<RelBloqueioContaCapitalDTO> rel = (RelatorioContaCapitalV2<RelBloqueioContaCapitalDTO>) 
				servico.gerarRelatorioBloqueios(tipoPesquisa, filtro);
		return rel.gerar(rDto);
	}
	
	private BloqueioContaCapitalDTO montarDTOConsulta(ParametroDTO param) {
		Map<Serializable, Serializable> dados = param.getDados();
		BloqueioContaCapitalDTO dto = new BloqueioContaCapitalDTO();
		dto.setIdInstituicao(Integer.valueOf(dados.get("idInstituicao").toString()));
		dto.setNomePessoa(dados.get("nomePessoa") == null ? null : dados.get("nomePessoa").toString());
		dto.setCpfCnpj(dados.get("cpfCnpj") == null ? null : dados.get("cpfCnpj").toString());
		dto.setCodTipoBloqueio(dados.get("codTipoBloqueio") == null ? null : Integer.valueOf(dados.get("codTipoBloqueio").toString()));
		dto.setNomeTipoBloqueio(dados.get("nomeTipoBloqueio") == null ? null : dados.get("nomeTipoBloqueio").toString());
		dto.setNumContaCapital(dados.get("numContaCapital") == null ? null : Integer.valueOf(dados.get("numContaCapital").toString()));
		dto.setNumProtocolo(dados.get("numProtocolo") == null ? null : dados.get("numProtocolo").toString());
		dto.setCodSituacaoBloqueio(dados.get("codSituacaoBloqueio") == null ? null : Integer.valueOf(dados.get("codSituacaoBloqueio").toString()));
		return dto;
	}

}

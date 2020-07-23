package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.entidades.HistBloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.HistBloqueioCapitalPK;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoBloqueio;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.BloqueioContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.HistBloqueioContaCapitalServicoLocal;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.dto.RelBloqueioContaCapitalDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelBloqueioServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelBloqueioServicoRemote;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;

/**
 * Servico para geracao dos relatorios de bloqueio
 * @author Nairon.Silva
 */
@Stateless
@Local(RelBloqueioServicoLocal.class)
@Remote(RelBloqueioServicoRemote.class)
public class RelBloqueioServicoEJB extends ContaCapitalRelatoriosServicoEJB implements RelBloqueioServicoLocal, RelBloqueioServicoRemote {

	private static final String PARAM_COD_RELATORIO = "COD_RELATORIO";
	private static final String REL_HISTORICO_BLOQUEIO = "CCA_Relatorio_Historico_Bloqueio.jasper";
	private static final String REL_BLOQUEIOS = "CCA_Relatorio_Bloqueios.jasper";
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@EJB
	private BloqueioContaCapitalServicoLocal bloqueioContaCapitalServico;
	
	@EJB
	private HistBloqueioContaCapitalServicoLocal histBloqueioContaCapitalServico;
	
	/**
	 * @see br.com.sicoob.cca.relatorios.negocio.servicos.RelBloqueioServico#gerarRelatorioHistoricoBloqueio(br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO)
	 */
	public Object gerarRelatorioHistoricoBloqueio(BloqueioContaCapitalDTO filtro) throws BancoobException {
		BloqueioContaCapitalDTO bloqueio = bloqueioContaCapitalServico.consultarBloqueios(filtro).get(0);
		Map<String, Object> parametros = getParametrosComuns();
		adicionarPropriedadesComuns(bloqueio.getIdInstituicao(), parametros);
		parametros.put(PARAM_COD_RELATORIO, CodigoRelatorio.COD_REL_HISTORICO_BLOQUEIO);
		adicionarPropriedadesBloqueio(bloqueio, parametros);
		
		List<HistBloqueioCapital> listaHistoricos = consultarListaHistoricos(bloqueio);
		
		return new RelatorioContaCapitalV2<HistBloqueioCapital>(listaHistoricos, REL_HISTORICO_BLOQUEIO, parametros);
	}

	private void adicionarPropriedadesBloqueio(BloqueioContaCapitalDTO dto, Map<String, Object> parametros) {
		parametros.put("numContaCapital", dto.getNumContaCapital());
		parametros.put("nomePessoa", dto.getNomePessoa());
		parametros.put("cpfCnpj", ContaCapitalUtil.formatarCpfCnpj(dto.getCpfCnpj()));
		parametros.put("idTipoBloqueio", dto.getIdTipoBloqueio());
		parametros.put("nomeTipoBloqueio", dto.getNomeTipoBloqueio());
		parametros.put("dataBloqueio", dto.getDataBloqueio());
		parametros.put("dataDesbloqueio", dto.getDataDesbloqueio());
		parametros.put("dataProtocolo", dto.getDataProtocolo());
		parametros.put("numProtocolo", dto.getNumProtocolo());
		parametros.put("numProcesso", dto.getNumProcesso());
	}

	private void adicionarPropriedadesComuns(Integer idInstituicao, Map<String, Object> parametros) throws BancoobException {
		InstituicaoIntegracaoDTO dtoInstituicaoIntegracao = instituicaoIntegracaoServico.obterInstituicaoIntegracao(idInstituicao);
		parametros.put("DESC_SIGLA_COOP", dtoInstituicaoIntegracao.getNumero() + " - " + dtoInstituicaoIntegracao.getDescInstituicao());
	}

	private List<HistBloqueioCapital> consultarListaHistoricos(BloqueioContaCapitalDTO bloqueio) throws BancoobException {
		ConsultaDto<HistBloqueioCapital> criterios = new ConsultaDto<HistBloqueioCapital>();
		HistBloqueioCapital filtro = new HistBloqueioCapital();
		HistBloqueioCapitalPK histPK = new HistBloqueioCapitalPK();
		histPK.setIdBloqueioCapital(bloqueio.getIdBloqueio());
		filtro.setId(histPK);
		criterios.setFiltro(filtro);
		return histBloqueioContaCapitalServico.listar(criterios);
	}

	/**
	 * @see br.com.sicoob.cca.relatorios.negocio.servicos.RelBloqueioServico#gerarRelatorioBloqueios(java.lang.String, br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO)
	 */
	public Object gerarRelatorioBloqueios(String tipoPesquisa, BloqueioContaCapitalDTO filtro) throws BancoobException {
		List<BloqueioContaCapitalDTO> bloqueios = bloqueioContaCapitalServico.consultarBloqueios(filtro);
		List<RelBloqueioContaCapitalDTO> bloqueiosPorConta = agruparBloqueios(bloqueios);
		
		Map<String, Object> parametros = getParametrosComuns();
		adicionarPropriedadesComuns(filtro.getIdInstituicao(), parametros);
		parametros.put(PARAM_COD_RELATORIO, CodigoRelatorio.COD_REL_BLOQUEIOS);
		parametros.put("tipoPesquisa", tipoPesquisa);
		parametros.put("valorTipoPesquisa", getValorTipoPesquisa(filtro));
		EnumSituacaoBloqueio situacaoBloqueio = EnumSituacaoBloqueio.getPorCodigo(filtro.getCodSituacaoBloqueio());
		parametros.put("situacaoBloqueio", situacaoBloqueio == null ? null : situacaoBloqueio.getDescricao());
		
		return new RelatorioContaCapitalV2<RelBloqueioContaCapitalDTO>(bloqueiosPorConta, REL_BLOQUEIOS, parametros);
	}

	private List<RelBloqueioContaCapitalDTO> agruparBloqueios(List<BloqueioContaCapitalDTO> bloqueios) {
		Map<Integer, List<BloqueioContaCapitalDTO>> controle = new LinkedHashMap<Integer, List<BloqueioContaCapitalDTO>>();
		List<RelBloqueioContaCapitalDTO> bloqueiosPorConta = new ArrayList<RelBloqueioContaCapitalDTO>();
		montarControleBloqueios(bloqueios, controle);
		montarBloqueiosPorConta(controle, bloqueiosPorConta);
		return bloqueiosPorConta;
	}

	private void montarBloqueiosPorConta(Map<Integer, List<BloqueioContaCapitalDTO>> controle, List<RelBloqueioContaCapitalDTO> bloqueiosPorConta) {
		for (Entry<Integer, List<BloqueioContaCapitalDTO>> entry : controle.entrySet()) {
			List<BloqueioContaCapitalDTO> listBloqueiosCCA = entry.getValue();
			BloqueioContaCapitalDTO item = listBloqueiosCCA.get(0);
			RelBloqueioContaCapitalDTO relDTO = new RelBloqueioContaCapitalDTO();
			relDTO.setNumContaCapital(item.getNumContaCapital());
			relDTO.setCpfCnpj(ContaCapitalUtil.formatarCpfCnpj(item.getCpfCnpj()));
			relDTO.setNomePessoa(item.getNomePessoa());
			relDTO.setBloqueios(listBloqueiosCCA);
			bloqueiosPorConta.add(relDTO);
		}
	}

	private void montarControleBloqueios(List<BloqueioContaCapitalDTO> bloqueios, Map<Integer, List<BloqueioContaCapitalDTO>> controle) {
		for (BloqueioContaCapitalDTO bloqueio : bloqueios) {
			List<BloqueioContaCapitalDTO> listBloqueiosCCA = controle.get(bloqueio.getNumContaCapital());
			if (listBloqueiosCCA == null) {
				listBloqueiosCCA = new ArrayList<BloqueioContaCapitalDTO>();
			}
			listBloqueiosCCA.add(bloqueio);
			controle.put(bloqueio.getNumContaCapital(), listBloqueiosCCA);
		}
	}

	private Object getValorTipoPesquisa(BloqueioContaCapitalDTO filtro) {
		String valorTipoPesquisa = null;
		if (filtro.getNomePessoa() != null) {
			valorTipoPesquisa = filtro.getNomePessoa();
		} else if (filtro.getCpfCnpj() != null) {
			valorTipoPesquisa = filtro.getCpfCnpj();
		} else if (filtro.getNomeTipoBloqueio() != null) {
			valorTipoPesquisa = filtro.getNomeTipoBloqueio();
		} else if (filtro.getNumContaCapital() != null) {
			valorTipoPesquisa = filtro.getNumContaCapital().toString();
		} else if (filtro.getNumProtocolo() != null) {
			valorTipoPesquisa = filtro.getNumProtocolo();
		}
		return valorTipoPesquisa;
	}

}

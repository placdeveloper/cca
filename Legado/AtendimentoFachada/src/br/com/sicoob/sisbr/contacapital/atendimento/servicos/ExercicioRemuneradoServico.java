package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.bancoob.comum.negocio.entidades.Parametro;
import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbr.negocio.dto.PesquisaDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.contacapital.comum.negocio.entidades.ExercicioRemunerado;
import br.com.sicoob.contacapital.comum.negocio.entidades.pk.ExercicioRemuneradoPK;
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.retaguarda.comum.util.DataUtil;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.vo.ExercicioRemuneradoVO;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ExercicioRemuneradoDelegate;
import br.com.sicoob.sisbr.corporativo.produto.negocio.entidades.ProdutoLegado;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class ExercicioRemuneradoServico extends AtendimentoFachada {
	
	private ExercicioRemuneradoDelegate exercicioRemuneradoDelegate = AtendimentoFabricaDelegates.getInstance().criarExercicioRemuneradoDelegate();
	
	public RetornoDTO obterDefinicoesSelecao(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		ProdutoLegado produto = produtoDelegate.obter(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
		
		Parametro parametro = parametroDelegate.recuperar(ContaCapitalConstantes.PAR_TX_ANUAL_MAX_PAG_JUROS_CAPITAL);

		retornoDTO.getDados().put("listaAnoExercicio", exercicioRemuneradoDelegate.obterListaAnoExercicioRemunerado());
		retornoDTO.getDados().put("dataAtualProduto", obterDataAtualProduto());
		retornoDTO.getDados().put("taxaAnual", parametro.getValor().getValor());
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(produto.getDataAtualProd());
		
		Date dtInicioConfCadastro = br.com.bancoob.util.DataUtil.converterStringToDate(
				ContaCapitalConstantes.DATA_INICIO_EXERCICIO_REMUNERADO + cal.get(Calendar.YEAR), "dd/MM/yyyy"); 
		
		retornoDTO.getDados().put("dtInicioConfCadastro", new DateTime(dtInicioConfCadastro.getTime()));
		retornoDTO.getDados().put("dtUltimoDiaUtilMes", new DateTime(DataUtil.obterUltimoDiaUtilMes(
				dtInicioConfCadastro, new Integer(ContextoHttp.getInstance().recuperarCooperativa())).getTime()));
		
		return retornoDTO;
	}
	
	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto)
			throws BancoobException {
		ExercicioRemuneradoVO vo = (ExercicioRemuneradoVO) ((PesquisaDTO) dto).getFiltro();
		
		if (vo.getAnoProvisao() == 0)
			vo.setAnoProvisao(null);
		
		ExercicioRemunerado entidade = vo2Entidade(vo);
		
		((PesquisaDTO) dto).setFiltro(entidade);
		
		ConsultaDto<ExercicioRemunerado> criterio = montarConsultaDto(dto,
				ExercicioRemunerado.class);
		
		criterio =
			exercicioRemuneradoDelegate.pesquisar((ConsultaDto<ExercicioRemunerado>) criterio);
				
		ConsultaDto<ExercicioRemuneradoVO> resultado = montarConsultaDto(dto,
				ExercicioRemuneradoVO.class);

		resultado.setResultado(converterLista(criterio.getResultado()));
		resultado.setTotalRegistros(criterio.getTotalRegistros());
		resultado.setPagina(criterio.getPagina());
		
		DadosSelGeralDTO retornoDTO = montarResultado(resultado);
		return retornoDTO;		
	}
	
	public RetornoDTO existeExercicioRemunerado(RequisicaoReqDTO req)
			throws BancoobException {
		
		ExercicioRemunerado exercicioRemunerado = vo2Entidade((ExercicioRemuneradoVO) req.getDados().get("exercicioRemunerado"));
		exercicioRemuneradoDelegate.existeExercicioRemunerado(exercicioRemunerado);
		
		return new RetornoDTO();
	}

	public RetornoDTO incluirDados(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		ExercicioRemunerado objeto = vo2Entidade((ExercicioRemuneradoVO) dto.getDados().get("exercicioRemunerado"));
		objeto = exercicioRemuneradoDelegate.incluir(objeto);
		retorno.getDados().put("exercicioRemunerado", entidade2Vo(objeto));
		
		return retorno;
	}

	public RetornoDTO alterarDados(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		ExercicioRemunerado objeto = vo2Entidade((ExercicioRemuneradoVO) dto.getDados().get("exercicioRemunerado"));
		
		exercicioRemuneradoDelegate.alterar(objeto);
		retorno.getDados().put("exercicioRemunerado", entidade2Vo(objeto));		
		
		return retorno;
	}

	public RetornoDTO excluirDados(RequisicaoReqDTO dto)
			throws BancoobException {
		
			ExercicioRemunerado entidade = vo2Entidade((ExercicioRemuneradoVO) dto.getDados().get("exercicioRemunerado"));
			exercicioRemuneradoDelegate.excluir(entidade.getId());
		
			return new RetornoDTO();
	}	
	
	public RetornoDTO obterDados(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		ExercicioRemunerado objeto = vo2Entidade((ExercicioRemuneradoVO) dto.getDados().get("exercicioRemunerado"));
		objeto = exercicioRemuneradoDelegate.obter(objeto.getId());
		retorno.getDados().put("exercicioRemunerado", entidade2Vo(objeto));
		
		return retorno;
	}

	private List<ExercicioRemuneradoVO> converterLista(List<ExercicioRemunerado> listaEntidades) {
		List<ExercicioRemuneradoVO> listaVO = new ArrayList<ExercicioRemuneradoVO>();
		
		
		for (int i = 0; i < listaEntidades.size(); i++) {
			listaVO.add(entidade2Vo(listaEntidades.get(i)));
		}
		
		return listaVO;
	}	

	private ExercicioRemunerado vo2Entidade(ExercicioRemuneradoVO vo) {
		ExercicioRemunerado entidade = new ExercicioRemunerado();
		ExercicioRemuneradoPK pk = new ExercicioRemuneradoPK();
		
		pk.setAnoProvisao(vo.getAnoProvisao());
		pk.setDataCadastro(vo.getDataCadastro());
		entidade.setId(pk);
		
		entidade.setIDUsuario(vo.getIdUsuario());
		entidade.setPercCCapital(vo.getPercCCapital());
		entidade.setPercCCorrente(vo.getPercCCorrente());
		entidade.setTaxaJurosProvisao(vo.getTaxaJurosProvisao());
		
		return entidade;
	}
	
	private ExercicioRemuneradoVO entidade2Vo(ExercicioRemunerado entidade) {
		ExercicioRemuneradoVO vo = new ExercicioRemuneradoVO();
		
		vo.setAnoProvisao(entidade.getId().getAnoProvisao());
		vo.setDataCadastro(new DateTime(entidade.getId().getDataCadastro().getTime()));
		vo.setIdUsuario(entidade.getIDUsuario());
		vo.setPercCCapital(entidade.getPercCCapital());
		vo.setPercCCorrente(entidade.getPercCCorrente());
		vo.setTaxaJurosProvisao(entidade.getTaxaJurosProvisao());
		
		return vo;
	}
	


}

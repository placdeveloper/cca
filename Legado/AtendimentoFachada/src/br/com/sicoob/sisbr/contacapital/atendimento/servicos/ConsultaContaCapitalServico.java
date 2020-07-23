package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.retaguarda.comum.util.DataUtil;
import br.com.sicoob.sisbr.cca.api.negocio.delegates.APIContaCapitalFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ParcelamentoCCADelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.DadosContaCapitalProxy;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.DadosParcelamentoCCAProxy;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.ConsultaContaCapitalVO;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.ExtratoConsultaCCAVO;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.TrabalhaVO;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class ConsultaContaCapitalServico extends AtendimentoFachada {

	private ContaCapitalDelegate ccaDelegate = AtendimentoFabricaDelegates
			.getInstance().criarContaCapitalDelegate();	
	
	private ParcelamentoCCADelegate parcelDelegate =
			AtendimentoFabricaDelegates.getInstance().criarParcelamentoCCADelegate();

	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		
		ConsultaContaCapitalVO auxvo = (ConsultaContaCapitalVO) 
				dto.getDados().get("ConsultaContaCapitalVO");
		
		DadosContaCapitalProxy dados = ccaDelegate.recuperar(
				auxvo.getNumMatricula());

		if (dados.getPeriodoProxDeb() == null) {
			dados.setPeriodoProxDeb(-1);
		}

		if (dados.getTipoPeriodoDeb() == null) {
			dados.setTipoPeriodoDeb(-1);
		}

		RetornoDTO retorno = new RetornoDTO();

		if (dados != null) {

			DateTime dtAtualProduto = obterDataAtualProduto();
			Date dtInicialProduto = DataUtil
					.obterPrimeiroDiaMes(dtAtualProduto);
			Date dtFinalProduto = DataUtil.obterUltimoDiaMes(dtAtualProduto);

//			BigDecimal valorBloqueado = ccaDelegate
//					.recuperarValorBloqueado(auxvo.getNumMatricula());
			
			br.com.sicoob.sisbr.cca.api.negocio.delegates.ContaCapitalDelegate ccaRenDelegate = 
					APIContaCapitalFabricaDelegates.getInstance().criarContaCapitalDelegate();
			BigDecimal valorBloqueado = ccaRenDelegate
					.obterValorBloqueado(Integer.valueOf(obterCooperativa()), auxvo.getNumMatricula().intValue());
			
			ConsultaContaCapitalVO vo = converter(dados);
			if (valorBloqueado != null && valorBloqueado.compareTo(BigDecimal.ZERO) > 0) {
				vo.setValorBloqueado(valorBloqueado);
			}
			vo.setDtInicialProduto(new DateTime(dtInicialProduto.getTime()));
			vo.setDtFinalProduto(new DateTime(dtFinalProduto.getTime()));
			
			if(vo.getUidTrabalha() != null){
				List<TrabalhaVO> listaTrabalha = obterListaMatricula(
						auxvo.getNumPessoa().toString());
				vo.setListaTrabalha(listaTrabalha);
			}else{
				vo.setListaTrabalha(new ArrayList<TrabalhaVO>());
			}
			
			List<DadosParcelamentoCCAProxy> listaParcelamento = 
					pesquisarParcelamento(vo.getNumMatricula(), false);
			vo.setListaParcelamento(listaParcelamento);

			obterListasExtrato(vo);

			retorno.getDados().put("ConsultaContaCapitalVO", vo);
		}

		return retorno;
	}
	
	public RetornoDTO obterDadosExtrato(RequisicaoReqDTO dto) throws BancoobException {		
		
		ConsultaContaCapitalVO vo = (ConsultaContaCapitalVO) 
				dto.getDados().get("ConsultaContaCapitalVO");
		
		obterListasExtrato(vo);
		
		RetornoDTO retorno = new RetornoDTO();
		
		retorno.getDados().put("ConsultaContaCapitalVO", vo);
		
		return retorno;
	}
	
	public RetornoDTO obterDadosParcelamento(RequisicaoReqDTO dto) throws BancoobException {		
		
		ConsultaContaCapitalVO vo = (ConsultaContaCapitalVO) 
				dto.getDados().get("ConsultaContaCapitalVO");
		
		List<DadosParcelamentoCCAProxy> parc = pesquisarParcelamento(
				vo.getNumMatricula(), vo.getBolSituacao());
		vo.setListaParcelamento(parc);
		
		RetornoDTO retorno = new RetornoDTO();
		
		retorno.getDados().put("ConsultaContaCapitalVO", vo);
		
		return retorno;
	}

	private void obterListasExtrato(ConsultaContaCapitalVO vo) throws BancoobException {
		
		Long numMatr = vo.getNumMatricula();
		Date dtInicial = vo.getDtInicialProduto();
		Date dtFinal = vo.getDtFinalProduto();		
		
		List<ExtratoConsultaCCAVO> listaExtSubsc = ccaDelegate
				.retornarExtratoCCASubsc(numMatr, dtInicial,
						dtFinal);
		vo.setListaExtSubscricao(listaExtSubsc);
		
		List<ExtratoConsultaCCAVO> listaExtInteg = ccaDelegate
				.retornarExtratoCCAInteg(numMatr, dtInicial,
						dtFinal);
		vo.setListaExtIntegralizacao(listaExtInteg);
		
		List<ExtratoConsultaCCAVO> listaExtDevol = ccaDelegate
				.retornarExtratoCCADevol(numMatr, dtInicial,
						dtFinal);
		vo.setListaExtDevolucao(listaExtDevol);
		
		List<ExtratoConsultaCCAVO> listaExtDivs = ccaDelegate
				.retornarExtratoCCADivs(numMatr, dtInicial,
						dtFinal);
		vo.setListaExtDiversos(listaExtDivs);
		
	}

	private ConsultaContaCapitalVO converter(DadosContaCapitalProxy dados)
			throws BancoobException {

		ConsultaContaCapitalVO vo = new ConsultaContaCapitalVO();
		try {
			BeanUtils.copyProperties(vo, dados);
		} catch (IllegalAccessException e) {
			throw new BancoobException("Erro ao converter", e);
		} catch (InvocationTargetException e) {
			throw new BancoobException("Erro ao converter", e);
		}

		return vo;
	}

	private List<TrabalhaVO> obterListaMatricula(String numPessoa)
			throws BancoobException {
		List<DadosContaCapitalProxy> dados = ccaDelegate
				.obterListaTrabalhaPorMatricula(Integer.valueOf(numPessoa));

		List<TrabalhaVO> ret = new ArrayList<TrabalhaVO>();

		Iterator<DadosContaCapitalProxy> it = dados.iterator();
		while (it.hasNext()) {
			DadosContaCapitalProxy vo = (DadosContaCapitalProxy) it.next();
			TrabalhaVO tvo = new TrabalhaVO();
			tvo.setUidTrabalha(vo.getUidTrabalha());
			tvo.setDescMatriculaFunc(vo.getDescMatriculaFunc());
			ret.add(tvo);
		}

		return ret;
	}
	
	private List<DadosParcelamentoCCAProxy> pesquisarParcelamento(
			Long numMatricula, Boolean stSituacao) throws BancoobException{
		
		DadosParcelamentoCCAProxy proxy = new DadosParcelamentoCCAProxy();
		proxy.setNumMatricula(numMatricula);
		if(stSituacao){
			proxy.setCodSituacaoParcela(1);
		}
		
		return parcelDelegate.recuperarDadosProcuraParcelamento(proxy);
	}
	
	
}

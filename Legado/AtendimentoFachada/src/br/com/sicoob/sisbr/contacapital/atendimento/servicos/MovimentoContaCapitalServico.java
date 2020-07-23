package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.fachada.vo.ConversorVO;
import br.com.bancoob.sisbrweb.fachada.vo.ConversorVOPadrao;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.contacapital.comum.negocio.entidades.CapaLoteCapital;
import br.com.sicoob.contacapital.comum.negocio.entidades.pk.CapaLoteCapitalPK;
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.vo.CapaLoteCapitalVO;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.vo.LancamentosContaCapitalVO;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.CapaLoteCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.FechamentoContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.LancamentosCCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.LancamentosCCAPorDataNumLoteProxy;
import br.com.sicoob.sisbr.corporativo.produto.negocio.delegates.ProdutosServicoDelegate;
import br.com.sicoob.sisbr.corporativo.produto.negocio.entidades.ProdutoLegado;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class MovimentoContaCapitalServico extends AtendimentoFachada {

	CapaLoteCapitalDelegate capaLoteCapitalDelegate = AtendimentoFabricaDelegates
			.getInstance().criarCapaLoteCapitalDelegate();
	LancamentosCCapitalDelegate lancamentosCCapitalDelegate = AtendimentoFabricaDelegates
			.getInstance().criarLancamentosCCapitalDelegate();
	ProdutosServicoDelegate produtoServicoDelegate = ProdutosServicoDelegate
			.getInstance();
	FechamentoContaCapitalDelegate fechamentoCCADelegate = AtendimentoFabricaDelegates
			.getInstance().criarFechamentoContaCapitalDelegate();

	private static final String KEY_DATA_ATUAL_MOVIMENTO = "keyDataAtualMovimento";
	private static final String KEY_FECHAMENTO_CAPITAL_INICIADO = "keyFechamentoCapitalIniciado";
	private static final String KEY_CAPA_LOTE_CAPITAL = "keyCapaLoteCapital";

	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		Boolean fechamentoCapitalIniciado = fechamentoCCADelegate
				.verificarFechCapitalIniciado();

		ProdutoLegado produto = produtoServicoDelegate
				.obter(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);

		DateTime dataAtualMovimento = produto.getDataAtualMovimento();

		retorno.getDados().put(KEY_DATA_ATUAL_MOVIMENTO, dataAtualMovimento);
		retorno.getDados().put(KEY_FECHAMENTO_CAPITAL_INICIADO,
				fechamentoCapitalIniciado);

		return retorno;
	}

	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto)
			throws BancoobException {

		ConversorVO<CapaLoteCapital, CapaLoteCapitalVO> conversorCapaLoteCapital = criarCapaLoteCapital();

		ConsultaDto<CapaLoteCapital> consultaDto = montarConsultaDto(dto,
				CapaLoteCapital.class, CapaLoteCapitalVO.class,
				conversorCapaLoteCapital);

		ConsultaDto<CapaLoteCapital> resultado = capaLoteCapitalDelegate
				.pesquisar(consultaDto);

		DadosSelGeralDTO retornoDTO = montarResultado(resultado,
				CapaLoteCapital.class, CapaLoteCapitalVO.class,
				conversorCapaLoteCapital);

		return retornoDTO;
	}

	public DadosSelGeralDTO obterLancamentosPorDataNumLote(SelGeralReqDTO dto)
			throws BancoobException {

		ConversorVO<LancamentosCCAPorDataNumLoteProxy, LancamentosContaCapitalVO> 
			conversor = criarLancamentosCCAPorDataNumLote();

		ConsultaDto<LancamentosCCAPorDataNumLoteProxy> consultaDto = montarConsultaDto(
				dto, LancamentosCCAPorDataNumLoteProxy.class, LancamentosContaCapitalVO.class, conversor);

		ConsultaDto<LancamentosCCAPorDataNumLoteProxy> dadosConsulta = lancamentosCCapitalDelegate
				.obterLancamentosPorDataNumLote(consultaDto);

		DadosSelGeralDTO retornoDTO = montarResultado(dadosConsulta, 
				LancamentosCCAPorDataNumLoteProxy.class, LancamentosContaCapitalVO.class, conversor);


		return retornoDTO;
	}

	public DadosSelGeralDTO obterLancamentosConsulta(SelGeralReqDTO dto)
			throws BancoobException {
		
		ConversorVO<LancamentosCCAPorDataNumLoteProxy, LancamentosContaCapitalVO> 
		conversor = criarLancamentosCCAPorDataNumLote();
		
		ConsultaDto<LancamentosCCAPorDataNumLoteProxy> consultaDto = montarConsultaDto(
				dto, LancamentosCCAPorDataNumLoteProxy.class, LancamentosContaCapitalVO.class, conversor);
		
		ConsultaDto<LancamentosCCAPorDataNumLoteProxy> dadosConsulta = lancamentosCCapitalDelegate
				.obterLancamentosConsulta(consultaDto);
		
		DadosSelGeralDTO retornoDTO = montarResultado(dadosConsulta, 
				LancamentosCCAPorDataNumLoteProxy.class, LancamentosContaCapitalVO.class, conversor);
		
		
		return retornoDTO;
	}
	
	public RetornoDTO validarLoteCCAAlteracao(RequisicaoReqDTO dto) throws BancoobException {
		
		CapaLoteCapitalVO capaLoteCapitalVO;
		CapaLoteCapital capaLoteCapital = new CapaLoteCapital();
		Boolean validacao;
		
		capaLoteCapitalVO = (CapaLoteCapitalVO) dto.getDados().get(KEY_CAPA_LOTE_CAPITAL);
		criarCapaLoteCapital().voParaEntidade(capaLoteCapital, capaLoteCapitalVO);
		
		
		validacao = capaLoteCapitalDelegate.validarLoteCCA(capaLoteCapital);
		
		// Para essa funcionalidade de consulta, não é possível realizar a alteração do lote.
		if(validacao){
			throw new BancoobException("Não é possível realizar a alteração!");
		}
		return null;
	}
	public RetornoDTO validarLoteCCAExclusao(RequisicaoReqDTO dto) throws BancoobException {
		
		CapaLoteCapitalVO capaLoteCapitalVO;
		CapaLoteCapital capaLoteCapital = new CapaLoteCapital();
		Boolean validacao;
		
		capaLoteCapitalVO = (CapaLoteCapitalVO) dto.getDados().get(KEY_CAPA_LOTE_CAPITAL);
		criarCapaLoteCapital().voParaEntidade(capaLoteCapital, capaLoteCapitalVO);
		
		validacao = capaLoteCapitalDelegate.validarLoteCCA(capaLoteCapital, true);
		
		// Para essa funcionalidade de consulta, não é possível realizar a alteração do lote.
		if(validacao){
			throw new BancoobException("Não é possível realizar a exclusão!");
		}
		return null;
	}
	
	/**
	 * Método que faz a conversão dos dados de VO para entidade e entidade para
	 * VO.
	 * 
	 * @return O conversor que contem a entidade e a VO.
	 */
	private ConversorVO<CapaLoteCapital, CapaLoteCapitalVO> criarCapaLoteCapital()
			throws BancoobException {

		return new ConversorVOPadrao<CapaLoteCapital, CapaLoteCapitalVO>() {

			@Override()
			public void entidadeParaVO(CapaLoteCapitalVO vo,
					CapaLoteCapital entidade) throws BancoobException {

				vo.setDataLote(new DateTime(entidade.getId().getDataLote()
						.getTime()));
				vo.setNumLoteLanc(entidade.getId().getNumLoteLanc());
				vo.setQtdLancInf(entidade.getQtdLancInf());
				vo.setQtdLancApu(entidade.getQtdLancApu());
				vo.setValorTotalLoteApu(entidade.getValorTotalLoteApu());
				vo.setValorTotalLoteInf(entidade.getValorTotalLoteInf());
				vo.setBolAtualizado(entidade.getBolAtualizado());
				vo.setCodOrigemLote(entidade.getCodOrigemLote());
				vo.setIdProduto(entidade.getIdProduto());
				vo.setIdTipoHistoricoLanc(entidade.getIdTipoHistoricoLanc());
				vo.setIdProdutoEst(entidade.getIdProdutoEst());
				vo.setIdTipoHistoricoEstorno(entidade
						.getIdTipoHistoricoEstorno());

				vo.setSituacao(entidade.getSituacao());
				vo.setOrigem(entidade.getOrigem());
			}

			@Override()
			public void voParaEntidade(CapaLoteCapital entidade,
					CapaLoteCapitalVO vo) {
				CapaLoteCapitalPK id = new CapaLoteCapitalPK(new Date(vo
						.getDataLote().getTime()), vo.getNumLoteLanc());
				entidade.setId(id);
				entidade.setQtdLancInf(vo.getQtdLancInf());
				entidade.setQtdLancApu(vo.getQtdLancApu());
				entidade.setValorTotalLoteApu(vo.getValorTotalLoteApu());
				entidade.setValorTotalLoteInf(vo.getValorTotalLoteInf());
				entidade.setBolAtualizado(vo.getBolAtualizado());
				entidade.setCodOrigemLote(vo.getCodOrigemLote());
				entidade.setIdProduto(vo.getIdProduto());
				entidade.setIdTipoHistoricoLanc(vo.getIdTipoHistoricoLanc());
				entidade.setIdProdutoEst(vo.getIdProdutoEst());
				entidade.setIdTipoHistoricoEstorno(vo
						.getIdTipoHistoricoEstorno());

				entidade.setSituacao(vo.getSituacao());

			}
		};
	}

	/**
	 * Método que faz a conversão dos dados de VO para entidade e entidade para
	 * VO.
	 * 
	 * @return O conversor que contem a entidade e a VO.
	 */
	private ConversorVO<LancamentosCCAPorDataNumLoteProxy, LancamentosContaCapitalVO> criarLancamentosCCAPorDataNumLote()
			throws BancoobException {

		return new ConversorVOPadrao<LancamentosCCAPorDataNumLoteProxy, LancamentosContaCapitalVO>() {

			@Override()
			public void entidadeParaVO(LancamentosContaCapitalVO vo,
					LancamentosCCAPorDataNumLoteProxy entidade)
					throws BancoobException {

				if(entidade.getDataLote() != null){
					vo.setDataLote(new DateTime(entidade.getDataLote().getTime()));
				}
				
				vo.setNumLoteLanc(entidade.getNumLoteLanc());
				vo.setNumSeqLanc(entidade.getNumSeqLanc());
				vo.setDescNumDocumento(entidade.getDescNumDocumento());
				vo.setValorLanc(entidade.getValorLanc());
				vo.setNumMatricula(entidade.getNumMatricula());
				vo.setDescNomePessoa(entidade.getDescNomePessoa());
				vo.setIdTipoHistoricoLanc(entidade.getIdTipoHistoricoLanc());
				vo.setIdTipoHistoricoEstorno(entidade.getIdTipoHistoricoEstorno());
				vo.setIdProduto(entidade.getIdProduto());
				vo.setBolAtualizado(entidade.getBolAtualizado());
				vo.setIdProdutoEst(entidade.getIdProdutoEst());
				
				if (entidade.getDataHoraInclusao() != null){
					vo.setDataHoraInclusao(new DateTime(entidade.getDataHoraInclusao().getTime()));
				}
				
				if (entidade.getDataLoteInicial() != null){
					vo.setDataLoteInicial(new DateTime(entidade.getDataLoteInicial().getTime()));
				}
				
				if (entidade.getDataLoteFinal() != null){
					vo.setDataLoteFinal(new DateTime(entidade.getDataLoteFinal().getTime()));
				}
				
				vo.setDescHistorico(entidade.getDescHistorico());

			}

			@Override()
			public void voParaEntidade(
					LancamentosCCAPorDataNumLoteProxy entidade,
					LancamentosContaCapitalVO vo) {

				entidade.setDataLote(vo.getDataLote());
				entidade.setNumLoteLanc(vo.getNumLoteLanc());
				entidade.setNumSeqLanc(vo.getNumSeqLanc());
				entidade.setDescNumDocumento(vo.getDescNumDocumento());
				entidade.setValorLanc(vo.getValorLanc());
				entidade.setNumMatricula(vo.getNumMatricula());
				entidade.setDescNomePessoa(vo.getDescNomePessoa());
				entidade.setIdTipoHistoricoLanc(vo.getIdTipoHistoricoLanc());
				entidade.setIdTipoHistoricoEstorno(vo
						.getIdTipoHistoricoEstorno());
				entidade.setIdProduto(vo.getIdProduto());
				entidade.setBolAtualizado(vo.getBolAtualizado());
				entidade.setIdProdutoEst(vo.getIdProdutoEst());
				entidade.setDataHoraInclusao(vo.getDataLote());
				entidade.setDescHistorico(vo.getDescHistorico());
				
				entidade.setDataLoteInicial(vo.getDataLoteInicial());
				entidade.setDataLoteFinal(vo.getDataLoteFinal());

			}
		};
	}

	
}

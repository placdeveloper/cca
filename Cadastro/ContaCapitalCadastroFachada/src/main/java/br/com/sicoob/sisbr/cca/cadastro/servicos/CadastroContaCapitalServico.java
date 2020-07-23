/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.cca.cadastro.CadastroContaCapital;

/**
 * A Classe CadastroContaCapitalServico.
 */
@RemoteService
public class CadastroContaCapitalServico extends CadastroContaCapital {
	
	/**
	 * Incluir dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO incluirDados(RequisicaoReqDTO dto) throws BancoobException{
		RetornoDTO retornoDTO = new RetornoDTO();
		return retornoDTO;
		/*
		//Exemplo Conta Corrente
		ContaCorrenteVO contaCorrenteVO = new ContaCorrenteVO();
		ConversorContaCorrente<ContaCorrente, ContaCorrenteVO> conversorConta = new ConversorContaCorrente<ContaCorrente, ContaCorrenteVO>();
		ContaCorrenteDelegate contaCorrenteLocalDelegate = AberturaContaCorrenteFabricaDelegates.getInstance().criarContaCorrenteDelegate();
		ContaCorrente contaCorrente = carregarEntidadeContaCorrente(dto);
		conversorConta.entidadeParaVO(contaCorrenteVO, contaCorrenteLocalDelegate.incluir(contaCorrente));
		retornoDTO.getDados().put("CONTA_CORRENTE", contaCorrenteVO);
		*/		
		
	}

	/**
	 * Alterar dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO alterarDados(RequisicaoReqDTO dto) throws BancoobException{
	
		return new RetornoDTO();
		/*
		 * exemplo Conta corrente
		ContaCorrenteDelegate contaCorrenteLocalDelegate = AberturaContaCorrenteFabricaDelegates.getInstance().criarContaCorrenteDelegate();
		ContaCorrente contaCorrente						 = carregarEntidadeContaCorrente(dto); 
		
		//Pacote futuro somente irá existir em alteração
		contaCorrente.setVigenciaTarifaria(carregaVigenciaPacoteFuturo(dto));
		
		contaCorrenteLocalDelegate.alterar(contaCorrente);		
 
		 */		
	}
	
	/**
	 * Obter definicoes.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException{
		RetornoDTO retorno 	 = new RetornoDTO();
		return retorno;
		
		/*
 		//exemplo conta corrente
   
		ContaCorrenteVO contaCorrenteVO 				 = (ContaCorrenteVO) dto.getDados().get(NOME_OBJETO_VO_CONTA_CORRENTE);

		listarModalidade(dto, retorno);
		
		if(contaCorrenteVO.getIdModalidade() != null){
			listarTipoConta(dto, retorno);
		}
		
		listarGrupoConta(retorno);
		
		listarEstoqueCheque(retorno, contaCorrenteVO);
		listarPacoteTarifa(dto, retorno);
		
		recuperaItensConfiguracaoInstituicaoConta(retorno);
		
		//Se em edição deve carregar a lista de participantes relacionados.
		if (contaCorrenteVO.getIdContaCorrente() != null) {
			//Deixa a instituição no vo de retorno a fim de ser utilizada pelas abas.
			retorno.getDados().put("ID_INSTITUICAO", contaCorrenteVO.getIdInstituicao());
			//Recupera participantes relacionados a conta corrente
			listarParticipantes(dto, retorno);
			//Recupera os relacionamentos dos participantes
			recuperaRelacionamentosParticipantes(dto, retorno);
			//Recupera as combinacoes da conta corrente
			listarCombinacoes(dto, retorno);
			
			if (contaCorrenteVO.getIdFontePagadora() != null && !contaCorrenteVO.getIdFontePagadora().equals(NumberUtils.LONG_ZERO)) {
				PessoaIntegracaoDTO fontePagadora = criarCAPESIntegracaoDelegate()
						.recuperaPessoaInstituicaoChave(contaCorrenteVO.getIdFontePagadora(), contaCorrenteVO.getIdInstituicao().intValue());
				
				retorno.getDados().put("ID_FONTE_PAGADORA", fontePagadora.getIdPessoaLegado() != null 
						? fontePagadora.getIdPessoaLegado() : "");
			}
			
			ContaFavorecida contaFavorecida = criarContaFavorecidaDelegate().obter(contaCorrenteVO.getIdContaCorrente());
			
			if (contaFavorecida != null) {
				ContaFavorecidaVO contaFavorecidaVO 												  = new ContaFavorecidaVO();
				ConversorContaFavorecida<ContaFavorecida, ContaFavorecidaVO> conversorContaFavorecida = new ConversorContaFavorecida<ContaFavorecida, ContaFavorecidaVO>();
				
				conversorContaFavorecida.entidadeParaVO(contaFavorecidaVO, contaFavorecida);
				
				retorno.getDados().put("CONTA_FAVORECIDA", contaFavorecidaVO);
			}
			
			
			//Recupera o pacote futuro, caso o mesmo exista para a modalidade que permite pacotes de tarifa
			if (contaCorrenteVO.getIdModalidade().equals(EnumModalidade.ID_MODALIDADE_DEPOSITO_A_VISTA.getIdModalidade()) &&  
					(contaCorrenteVO.getIdTipoContaCorrente().equals(EnumTipoConta.ID_TIPO_CONTA_FISICA.getCodigo()) 
					|| contaCorrenteVO.getIdTipoContaCorrente().equals(EnumTipoConta.ID_TIPO_CONTA_JURIDICA.getCodigo()) 
					|| contaCorrenteVO.getIdTipoContaCorrente().equals(EnumTipoConta.ID_TIPO_CONTA_UNIVERSITARIA.getCodigo()))) {
				carregaPacoteFuturo(dto, retorno);
			}
			
			//Caso a conta esteja encerrada recupera a data de encerramento da conta
			if (contaCorrenteVO.getCodSituacaoCCO().equals(EnumSituacaoConta.ENCERRADA.getCodSituacao())) {
				retorno.getDados().put("DATA_ENCERRAMENTO", EncerramentoContaCorrenteFabricaDelegates.getInstance().criarAgendamentoEncerramentoContaDelegate()
					.recuperaAgendamentoEncerramentoConta(contaCorrenteVO.getIdContaCorrente()).getDataHoraEncerramento());
			}
			
		}
		
		retorno.getDados().put("usuarioLogado", InformacoesUsuario.getInstance().getLogin());
		
		carregarCriteriosValidacaoConta(retorno, contaCorrenteVO);
		 */		
	}
	
	/**
	 * Listar exemplo.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO listarExemplo(RequisicaoReqDTO dto) throws BancoobException {

		RetornoDTO retorno = new RetornoDTO();
		return retorno;
		/*
 		//exemplo conta corrente
		MotivoBloqueioDelegate motivoBloqueioDelegate = ApoioFabricaDelegates.getInstance().criarMotivoBloqueioDelegate();

		List<MotivoBloqueio> lista = motivoBloqueioDelegate.motivosBloqueioInstituicao();
		List<MotivoBloqueioVO> listaVO = new ArrayList<MotivoBloqueioVO>();
		
		for (MotivoBloqueio motivoBloqueio : lista) {
			MotivoBloqueioVO vo = converteEntidadeParaVO(MotivoBloqueioVO.class, motivoBloqueio, new ConversorMotivoBloqueio<MotivoBloqueio, MotivoBloqueioVO>());
			listaVO.add(vo);
		}
		
		retorno.getDados().put("motivosBloqueioDaInstituicao", listaVO);
		
		*/		
	}	
	
	/**
	 * Emitir relatorio exemplo.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO emitirRelatorioExemplo(RequisicaoReqDTO dto) throws BancoobException{
		return new RetornoDTO();
		
		/*
 		//exemplo conta corrente
		ContaCorrenteDelegate delegateContaCorrente = AberturaContaCorrenteFabricaDelegates.getInstance().criarContaCorrenteDelegate();
		
		Object relatorio = delegateContaCorrente.gerarContratoAbertura(Long.valueOf((String) dto.getDados().get("idInstituicao")));		
		
		ContextoHttp.getInstance().adicionarContexto("Contrato_Deposito", relatorio);
		*/		
	}
	
}
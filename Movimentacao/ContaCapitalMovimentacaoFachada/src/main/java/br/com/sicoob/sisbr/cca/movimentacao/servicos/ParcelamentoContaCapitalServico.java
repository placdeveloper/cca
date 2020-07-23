package br.com.sicoob.sisbr.cca.movimentacao.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cca.cadastro.negocio.delegates.CadastroContaCapitalRenDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoParcelamento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoParcela;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoParcelamento;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ParcelamentoContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCorrenteIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.GenIntIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ProdutoLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.TrabalhaLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TrabalhaLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.movimentacao.MovimentacaoContaCapital;
import br.com.sicoob.sisbr.cca.movimentacao.vo.CadastroContaCapitalRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ParcelamentoRenVO;
import br.com.sicoob.tipos.DateTime;

/**
 * A Classe ParcelamentoContaCapitalServico.
 *
 * @author Antonio.Genaro
 */
@RemoteService
public class ParcelamentoContaCapitalServico extends MovimentacaoContaCapital {

	/** O atributo parcelamentoContaCapitalDelegate. */
	private ParcelamentoContaCapitalDelegate parcelamentoContaCapitalDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarParcelamentoContaCapitalDelegate();
	
	/** O atributo genIntIntegracaoDelegate. */
	private GenIntIntegracaoDelegate genIntIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarGenIntIntegracaoDelegate();	
	
	/** O atributo prodLegadoDelegate. */
	private ProdutoLegadoDelegate prodLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarProdutoLegadoDelegate();
	
	/** O atributo trabalhaLegadoDelegate. */
	private TrabalhaLegadoDelegate trabalhaLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarTrabalhaLegadoDelegate();
	
	/** O atributo cadastroDelegate. */
	private CadastroContaCapitalRenDelegate cadastroDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarCadastroContaCapitalRenDelegate();
	
	/** O atributo contaCapitalDelegate. */
	private ContaCapitalDelegate contaCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate();
	
	/** O atributo contaCapitalLegadoDelegate. */
	private ContaCapitalLegadoDelegate contaCapitalLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarContaCapitalLegadoDelegate();
	
	/** O atributo contaCorrenteIntegracaoDelegate. */
	private ContaCorrenteIntegracaoDelegate contaCorrenteIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarContaCorrenteIntegracaoDelegate();
	
	/** A constante TAMANHO_MAX_LISTA. */
	private static final Integer TAMANHO_MAX_LISTA = 9;
	/**
	 * Obter definicoes.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		retornoDTO.getDados().put("cboTipoParcelamento", criarComboTipoParcelamento());
		retornoDTO.getDados().put("cboSituacaoParcelamento", criarComboSituacaoParcelamento());
		retornoDTO.getDados().put("idInstituicao", Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		
		return retornoDTO;
	}		
	
	/**
	 * Procurar.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO procurar(RequisicaoReqDTO dto) throws BancoobException{
		RetornoDTO retornoDTO = new RetornoDTO();
		
		List<ParcelamentoRenVO> lstParcelamento = montarListaParcelamento(dto);
		
		if(!lstParcelamento.isEmpty()){
			retornoDTO.getDados().put("listaParcelamentos", lstParcelamento);
			
		} else if(dto.getDados().get("exibirMsgFiltro") == null) {
			
			retornoDTO.getDados().put("msg", "Não existem dados cadastrados para os filtros informados.");							
		}
				
		return retornoDTO;					
	}				
	
	/**
	 * Listar parcelas.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO listarParcelas(RequisicaoReqDTO dto) throws BancoobException{
		RetornoDTO retornoDTO = new RetornoDTO();
		
		List<ParcelamentoRenVO> lstParcelas = montarListaParcelas(dto);
		
		if(!lstParcelas.isEmpty()){				
			retornoDTO.getDados().put("listaParcelas", lstParcelas);			
		}else{
			retornoDTO.getDados().put("msg", "Não existem dados cadastrados para os filtros informados.");							
		}
		
		if(dto.getDados().get("numMatricula")!=null){
			ContaCapitalLegado  contaCapitalLegado = contaCapitalLegadoDelegate.obter(Integer.valueOf(dto.getDados().get("numMatricula").toString()));		
			retornoDTO.getDados().put("idPessoaLegado", contaCapitalLegado.getNumCliente());					
		}		
				
		return retornoDTO;					
	}				
	
	/**
	 * Criar filtro consulta.
	 *
	 * @param reqDTO o valor de req dto
	 * @return Parcelamento
	 */
	private Parcelamento criarFiltroConsulta(RequisicaoReqDTO reqDTO) {
		Parcelamento filtro = new Parcelamento();
		
		ContaCapital contaCapital = new ContaCapital(); 
		contaCapital.setId(Integer.valueOf(reqDTO.getDados().get("idContaCapital").toString()));
		filtro.setContaCapital(contaCapital);				
		
		if (reqDTO.getDados().get("idTipoParcelamento") != null) {
			TipoParcelamento tipoParcelamento = new TipoParcelamento(); 
			tipoParcelamento.setId(Short.valueOf(reqDTO.getDados().get("idTipoParcelamento").toString()));		
			filtro.setTipoParcelamento(tipoParcelamento);			
		}
		
		if(reqDTO.getDados().get("idSituacaoParcelamento")!=null){
			SituacaoParcelamento situacaoParcelamento = new SituacaoParcelamento();
			situacaoParcelamento.setId(Short.valueOf(reqDTO.getDados().get("idSituacaoParcelamento").toString()));
			filtro.setSituacaoParcelamento(situacaoParcelamento);			
		}
		
		if (reqDTO.getDados().get("numParcelamento") != null) {
			filtro.setNumParcelamento(Short.valueOf(reqDTO.getDados().get("numParcelamento").toString()));				
		}

		return filtro;
	}		
		
	/**
	 * Montar lista parcelas.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<ParcelamentoRenVO> montarListaParcelas(RequisicaoReqDTO dto) throws BancoobException {				
		
		ConsultaDto<Parcelamento> consultaDTO = new ConsultaDto<Parcelamento>();
		consultaDTO.setFiltro(criarFiltroConsulta(dto));
		List<Parcelamento> lstParcelas = parcelamentoContaCapitalDelegate.listar(consultaDTO);
		
		List<ParcelamentoRenVO> lstParcelamentoRenVo = new ArrayList<ParcelamentoRenVO>(0);
		
		for (Parcelamento item: lstParcelas){	
			
			ParcelamentoRenVO itemSaida = new ParcelamentoRenVO();		
						
			itemSaida.setNumParcela(item.getNumParcela());
			itemSaida.setDataVencimento(ContaCapitalUtil.formatarDataMascara(item.getDataVencimento(),"dd/MM/yyyy"));								
			itemSaida.setDataVencimentoOrdenacao(ContaCapitalUtil.formatarDataMascara(item.getDataVencimento(),"yyyyMMdd"));
			itemSaida.setValorParcela(item.getValor());
			itemSaida.setDataSituacao(ContaCapitalUtil.formatarDataMascara(item.getDataSituacao(),"dd/MM/yyyy"));					
			itemSaida.setDescSituacao(item.getSituacaoParcelamento().getDescricao());
			itemSaida.setDescFormaPagamento(item.getTipoIntegralizacao().getDescricao());
			itemSaida.setIdTipoInteg(item.getTipoIntegralizacao().getId());
			itemSaida.setNumContaCorrente(item.getNumContaCorrente());
			itemSaida.setDescNumMatriculaFunc(item.getMatriculaFuncionario());
			itemSaida.setIdSituacaoParcelamento(item.getSituacaoParcelamento().getId());
			itemSaida.setIdParcelamento(item.getId());
			itemSaida.setSelecionado(false);
			itemSaida.setIdContaCapital(item.getContaCapital() == null ? null : item.getContaCapital().getId());
			itemSaida.setIdTipoParcelamento(item.getTipoParcelamento() == null ? null :item.getTipoParcelamento().getId());
			itemSaida.setIdMotivoDevolucao(item.getMotivoDevolucao() == null ? null : item.getMotivoDevolucao().getId());
			lstParcelamentoRenVo.add(itemSaida);				
		}
		
		return lstParcelamentoRenVo;		
		
	}			
	
	/**
	 * Montar lista parcelamento.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<ParcelamentoRenVO> montarListaParcelamento(RequisicaoReqDTO dto) throws BancoobException {				
				
		List<ParcelamentoRenDTO> lstParcelamento = parcelamentoContaCapitalDelegate.pesquisarParcelamentos(Integer.valueOf(dto.getDados().get("idContaCapital").toString()), Integer.valueOf(dto.getDados().get("idTipoParcelamento").toString()));	
		List<ParcelamentoRenVO> lstParcelamentoRenVo = new ArrayList<ParcelamentoRenVO>(0);
		
		for (ParcelamentoRenDTO item: lstParcelamento){	
			
			ParcelamentoRenVO itemSaida = new ParcelamentoRenVO();						
			itemSaida.setNumContaCapital(item.getNumContaCapital());
			itemSaida.setDescTipoParcelamento(item.getDescTipoParcelamento());
			itemSaida.setNumParcelamento(item.getNumParcelamento());
			itemSaida.setQtdParcelas(item.getQtdParcelas());
			itemSaida.setValorTotal(item.getValorTotal());
			itemSaida.setValorAberto(item.getValorAberto());	
			itemSaida.setIdTipoParcelamento(item.getIdTipoParcelamento());

			if (dto.getDados().get("idSituacaoParcelamento").toString().equals(EnumSituacaoParcela.COD_ABERTO.getCodigo().toString())){
				if(item.getValorAberto().compareTo(new BigDecimal(ContaCapitalConstantes.NUM_ZERO)) != 0){
					lstParcelamentoRenVo.add(itemSaida);									
				}
			}else if (dto.getDados().get("idSituacaoParcelamento").toString().equals(EnumSituacaoParcela.COD_PAGO.getCodigo().toString())){
				if(item.getValorAberto().compareTo(new BigDecimal(ContaCapitalConstantes.NUM_ZERO)) == 0){
					lstParcelamentoRenVo.add(itemSaida);									
				}
			}else{
				lstParcelamentoRenVo.add(itemSaida);				
			}
		}
		
		return lstParcelamentoRenVo;		
		
	}	
	
	/**
	 * Criar combo tipo parcelamento.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ItemListaVO> criarComboTipoParcelamento() throws BancoobException {
		
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(TAMANHO_MAX_LISTA);
		
		lista.add(new ItemListaVO("0", "TODOS"));
		lista.add(new ItemListaVO(EnumTipoParcelamento.COD_TIPO_PARCELAMENTO_INTEGRAL.getCodigo().toString(), EnumTipoParcelamento.COD_TIPO_PARCELAMENTO_INTEGRAL.getDescricao()));
		lista.add(new ItemListaVO(EnumTipoParcelamento.COD_TIPO_PARCELAMENTO_DEVOLUCAO.getCodigo().toString(), EnumTipoParcelamento.COD_TIPO_PARCELAMENTO_DEVOLUCAO.getDescricao()));
		
		return lista;
	}
	
	/**
	 * Criar combo situacao parcelamento.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ItemListaVO> criarComboSituacaoParcelamento() throws BancoobException {
		
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(TAMANHO_MAX_LISTA);
		
		lista.add(new ItemListaVO("0", "TODOS"));
		lista.add(new ItemListaVO(EnumSituacaoParcela.COD_ABERTO.getCodigo().toString(), EnumSituacaoParcela.COD_ABERTO.getDescricao()));
		lista.add(new ItemListaVO(EnumSituacaoParcela.COD_PAGO.getCodigo().toString(), EnumSituacaoParcela.COD_PAGO.getDescricao()));
		
		return lista;
	}
	
	/**
	 * Criar combo tipo integralizacao.
	 *
	 * @param tipoOpInteg o valor de tipo op integ
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ItemListaVO> criarComboTipoIntegralizacao(Integer tipoOpInteg) throws BancoobException {
		
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(TAMANHO_MAX_LISTA);
		
		if(tipoOpInteg.equals(ContaCapitalConstantes.COD_PROPOSTA_CADASTRO)) {
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getDescricao()));
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getDescricao()));
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getDescricao()));
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_FOLHA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_FOLHA.getDescricao()));
		}
		
		return lista;
	}	
	
	/**
	 * Criar combo trabalha.
	 *
	 * @param listTrabalhaLegadoDTO o valor de list trabalha legado dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ItemListaVO> criarComboTrabalha(List<TrabalhaLegadoDTO> listTrabalhaLegadoDTO) throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(TAMANHO_MAX_LISTA);
				
		for(TrabalhaLegadoDTO item : listTrabalhaLegadoDTO){
			lista.add(new ItemListaVO(item.getDescMatriculaFunc(), item.getDescMatriculaFunc()));
		}					
		
		return lista;
	}
	
	/**
	 * Obter definicoes parcela.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoesParcela(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Integer idContaCapital = Integer.valueOf(reqDTO.getDados().get("idContaCapital").toString());
		ContaCapital contaCapital = contaCapitalDelegate.obter(idContaCapital);
		
		String dataAtualProdutoFormatada = DataUtil.converterDateToString(prodLegadoDelegate.obterDataAtualProdutoCCALogado(), "dd/MM/yyyy");
		retornoDTO.getDados().put("dataAtualProduto", dataAtualProdutoFormatada);
		retornoDTO.getDados().put("comboTipoInteg", criarComboTipoIntegralizacao(ContaCapitalConstantes.COD_PROPOSTA_CADASTRO));
		retornoDTO.getDados().put("comboTrabalha", criarComboTrabalha(trabalhaLegadoDelegate.obterDadosTrabalha(Integer.valueOf(reqDTO.getDados().get("idPessoaLegado").toString()))));		
		retornoDTO.getDados().put("comboCco", criarComboCco(contaCapital));
		
		return retornoDTO;
	}		
	
	private List<ItemListaVO> criarComboCco(ContaCapital cca) throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		ContaCorrenteIntegracaoDTO ccoDTO = new ContaCorrenteIntegracaoDTO();
		ccoDTO.setIdInstituicao(cca.getIdInstituicao());
		ccoDTO.setIdPessoa(cca.getIdPessoa());
		List<ContaCorrenteIntegracaoRetDTO> lstCco = contaCorrenteIntegracaoDelegate.consultarContaCorrenteAtivaPorNumeroCliente(ccoDTO);
		for (ContaCorrenteIntegracaoRetDTO cco : lstCco) {
			lista.add(new ItemListaVO(cco.getNumeroContaCorrente().toString(), cco.getNumeroContaCorrente().toString()));
		}
		return lista;
	}
	
	/**
	 * Verificar dia util.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO verificarDiaUtil(RequisicaoReqDTO dto) throws BancoobException{
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Date dtParcela = (Date) dto.getDados().get("dtParcela");								
		
		if (!genIntIntegracaoDelegate.verificarDiaUtil(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()), dtParcela)){
			retornoDTO.getDados().put("bolDataUtil", "0");			
		}else{
			retornoDTO.getDados().put("bolDataUtil", "1");			
		}
			
		return retornoDTO;					
	}		
	
	
	/**
	 * Cancelar parcelas.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO cancelarParcelas(RequisicaoReqDTO dto) throws BancoobException{
		RetornoDTO retorno = new RetornoDTO();
		
		List<ParcelamentoRenVO> listaParcelasVO = (List<ParcelamentoRenVO>) dto.getDados().get("listaParcelas");						
		parcelamentoContaCapitalDelegate.cancelarParcelas(montarListaParcelaDTO(listaParcelasVO));
		
		return retorno;							
	}			
	
	/**
	 * Baixar parcelas.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO baixarParcelas(RequisicaoReqDTO dto) throws BancoobException{
		RetornoDTO retorno = new RetornoDTO();
		
		List<ParcelamentoRenVO> listaParcelasVO = (List<ParcelamentoRenVO>) dto.getDados().get("listaParcelas");				
		parcelamentoContaCapitalDelegate.baixarParcelas(montarListaParcelaDTO(listaParcelasVO));
		
		return retorno;							
	}			
	
	/**
	 * Gravar parcelas.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO gravarParcelas(RequisicaoReqDTO dto) throws BancoobException{
		RetornoDTO retorno = new RetornoDTO();		
		List<ParcelamentoRenVO> listaParcelasVO = (List<ParcelamentoRenVO>) dto.getDados().get("listaParcelas");						
		parcelamentoContaCapitalDelegate.gravarParcelas(montarListaParcelaDTO(listaParcelasVO));		
		return retorno;							
	}			
	
	
	/**
	 * Montar lista parcela dto.
	 *
	 * @param listaParcelasVO o valor de lista parcelas vo
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ParcelamentoRenDTO> montarListaParcelaDTO(List<ParcelamentoRenVO> listaParcelasVO) throws BancoobException{
		List<ParcelamentoRenDTO> listaParcelasDTO = new ArrayList<ParcelamentoRenDTO>(0);	
		
		for (ParcelamentoRenVO item: listaParcelasVO){	
			
			ParcelamentoRenDTO itemSaida = new ParcelamentoRenDTO();						
			itemSaida.setNumContaCapital(item.getNumContaCapital());
			itemSaida.setDescTipoParcelamento(item.getDescTipoParcelamento());
			itemSaida.setNumParcelamento(item.getNumParcelamento());
			itemSaida.setQtdParcelas(item.getQtdParcelas());
			itemSaida.setValorTotal(item.getValorTotal());
			itemSaida.setValorAberto(item.getValorAberto());	
			itemSaida.setIdTipoParcelamento(item.getIdTipoParcelamento());
			itemSaida.setIdParcelamento(item.getIdParcelamento());
			itemSaida.setIdSituacaoParcelamento(item.getIdSituacaoParcelamento());
			itemSaida.setIdContaCapital(item.getIdContaCapital());
			itemSaida.setIdParcelamento(item.getIdParcelamento());
			itemSaida.setValorParcela(item.getValorParcela());
			itemSaida.setNumParcela(item.getNumParcela());
			itemSaida.setIdTipoInteg(item.getIdTipoInteg());
			itemSaida.setNumContaCorrente(item.getNumContaCorrente());
			itemSaida.setDescNumMatriculaFunc(item.getDescNumMatriculaFunc());			
			if(item.getDataVencimento()!=null){
				itemSaida.setDataVencimento(new DateTime(ContaCapitalUtil.formatarStringToDate(item.getDataVencimento()).getTime()));				
			}
			itemSaida.setIdMotivoDevolucao(item.getIdMotivoDevolucao());
			listaParcelasDTO.add(itemSaida);				
		}
		return listaParcelasDTO;
	}
	
	/**
	 * Consulta usada na plataforma de atendimento.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO consultar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Integer idPessoa = (Integer) reqDTO.getDados().get("idPessoa");
		Integer idInstituicao = (Integer) reqDTO.getDados().get("idInstituicao");
		
		if(idPessoa != null && idInstituicao != null) {
			
			CadastroContaCapitalRenDTO dto = new CadastroContaCapitalRenDTO();
			dto.setIdPessoa(idPessoa);
			dto.setIdInstituicao(idInstituicao);
			dto.setIdSituacaoCadastro(EnumSituacaoCadastroProposta.COD_APROVADO.getCodigo());
			
			List<CadastroContaCapitalRenDTO> resultadoDTO = cadastroDelegate.pesquisar(dto);
			if(resultadoDTO != null && !resultadoDTO.isEmpty()) {
				retornoDTO.getDados().put("registros", lstDtoParaVo(resultadoDTO));
			}
		}
		
		return retornoDTO;
	}	

	
	/**
	 * O método Dto para vo.
	 *
	 * @param vo o valor de vo
	 * @param dto o valor de dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void dtoParaVo(CadastroContaCapitalRenVO vo, CadastroContaCapitalRenDTO dto) throws BancoobException {
		vo.setIdContaCapital(dto.getIdContaCapital());
		vo.setIdInstituicao(dto.getIdInstituicao());
		vo.setIdPessoa(dto.getIdPessoa());
		vo.setNomePessoa(dto.getNomePessoa());
		vo.setNomeCompleto(dto.getNomeCompleto());
		vo.setCpfCnpj(dto.getCpfCnpj());
		vo.setIdPessoaLegado(dto.getIdPessoaLegado());
		vo.setNumContaCapital(dto.getNumContaCapital());
		vo.setNumContaCapitalGerada(dto.getNumContaCapitalGerada());
		vo.setVlrSubs(dto.getVlrSubs());
		vo.setVlrInteg(dto.getVlrInteg());
		vo.setQtdParcelas(dto.getQtdParcelas());
		vo.setVlrParcelas(dto.getVlrParcelas());
		vo.setDiaDebito(dto.getDiaDebito());
		vo.setTipoInteg(dto.getTipoInteg());
		vo.setNumCco(dto.getNumCco());
		vo.setIdSituacaoCadastro(dto.getIdSituacaoCadastro());
		vo.setDescSituacaoAprovacaoCapital(dto.getDescSituacaoAprovacaoCapital());
		vo.setDescSituacaoContaCapital(dto.getDescSituacaoContaCapital());
		vo.setMatriculaEscolhida(dto.getMatriculaEscolhida());
		vo.setDataHoraAtualizacao(dto.getDataHoraAtualizacao());
		vo.setIdAtividade(dto.getIdAtividade());
		vo.setObservacao(dto.getObservacao());
		vo.setIdSituacaoContaCapital(dto.getIdSituacaoContaCapital());
		
		Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
	    c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));
	    c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));
	    c.set(Calendar.MILLISECOND, c.getActualMinimum(Calendar.MILLISECOND));
	    
		if(dto.getDataMatricula() != null && dto.getDataMatricula().compareTo(c.getTime()) == 0) {
			vo.setPermissaoExcluir(true);
		} else {
			vo.setPermissaoExcluir(false);
		}
	}
	
	/**
	 * Lst dto para vo.
	 *
	 * @param lstDTO o valor de lst dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<CadastroContaCapitalRenVO> lstDtoParaVo(List<CadastroContaCapitalRenDTO> lstDTO) throws BancoobException {
		List<CadastroContaCapitalRenVO> lst = new ArrayList<CadastroContaCapitalRenVO>();
		CadastroContaCapitalRenVO vo = null;
		
		for(CadastroContaCapitalRenDTO dto : lstDTO) {
			vo = new CadastroContaCapitalRenVO();
			dtoParaVo(vo, dto);
			lst.add(vo);
		}
		
		return lst;
	}	
	
	/**
	 * Cancela todas as parcelas.
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO cancelarTodasParcelas(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		dto.getDados().put("idTipoParcelamento", EnumTipoParcelamento.COD_TIPO_PARCELAMENTO_INTEGRAL.getCodigo());
		dto.getDados().put("idSituacaoParcelamento", EnumSituacaoParcela.COD_ABERTO.getCodigo());
		List<ParcelamentoRenVO> lstParcelas = montarListaParcelas(dto);
		parcelamentoContaCapitalDelegate.cancelarParcelas(montarListaParcelaDTO(lstParcelas));
		return retorno;							
	}	
	
}

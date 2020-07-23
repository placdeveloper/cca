package br.com.sicoob.sisbr.cca.api.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.dto.ContaCapitalResumoDTO;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoLote;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoParcelamento;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.LancamentoEstornoRateioDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoIntegralizacaoExternaServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalExternoServicoLocal;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ConsultaIntegralizacaoCapitalCabalDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ConsultaIntegralizacaoCapitalCabalRetornoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.EstornoRateioDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.IntegralizacaoCapitalBoletoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.IntegralizacaoCapitalCabalDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.IntegralizacaoCapitalCabalRetornoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.IntegralizacaoCapitalCartaoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.IntegralizacaoCapitalRateioDTO;
import br.com.sicoob.sisbr.cca.api.negocio.excecao.IntegralizacaoCapitalNegocioException;
import br.com.sicoob.sisbr.cca.api.negocio.excecao.LancamentoBoletoFAPPagoNegocioException;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.interfaces.IntegralizacaoCapitalServicoLocal;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.interfaces.IntegralizacaoCapitalServicoRemote;
import br.com.sicoob.sisbr.cca.api.negocio.util.ChaveLancamentoRateioHelper;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.GenIntIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
 * API IntegralizacaoCapitalServicoEJB
 */
@Stateless
@Local(IntegralizacaoCapitalServicoLocal.class)
@Remote(IntegralizacaoCapitalServicoRemote.class)
public class IntegralizacaoCapitalServicoEJB extends APIContaCapitalServicoEJB implements IntegralizacaoCapitalServicoLocal, IntegralizacaoCapitalServicoRemote {

	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;

	@EJB
	private CapesIntegracaoServicoLocal capesIntegracaoServico;

	@EJB
	private LancamentoIntegralizacaoExternaServicoLocal lancamentoIntegralizacaoExternaServico;

	@EJB
	private GenIntIntegracaoServicoLocal genIntIntegracaoServico;

	@EJB
	private ParcelamentoContaCapitalExternoServicoLocal parcelamentoContaCapitalExternoServico;

	@EJB
	private ProdutoLegadoServicoLocal prodLegadoServico;

	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;

	private static final String RET_SUCESSO = "Integralização realizada com sucesso. ";
	private static final String RET_NAO_FOI_POSSIVEL = "Não foi possível realizar o lançamento. ";
	private static final String RET_ERRO = "Erro ao realizar o lançamento. ";
	private static final String RET_ERRO_CONSULTA = "Erro ao consultar a integralização de capital. ";

	/**
	 * {@link IntegralizacaoCapitalServicoRemote#integralizarPontosCabal(IntegralizacaoCapitalCabalDTO)}
	 * Integraliza os pontos do cartão Cabal no Conta Capital
	 * Não levanta exceção
	 */
	public IntegralizacaoCapitalCabalRetornoDTO integralizarPontosCabal(IntegralizacaoCapitalCabalDTO dtoCabal) {

		IntegralizacaoCapitalCabalRetornoDTO retorno = null;
		try{			
			validarObrigatoriosIntegralizacaoCabal(dtoCabal);
			validarIntegralizacaoNoDia(dtoCabal);
			lancamentoIntegralizacaoExternaServico.incluir(montarDtoIntegralizacaoPontosCabal(dtoCabal,Boolean.TRUE));
			retorno =  retornoSucesso(lancamentoIntegralizacaoExternaServico.incluir(montarDtoIntegralizacaoPontosCabal(dtoCabal,Boolean.FALSE)),dtoCabal);			
		}catch (IntegralizacaoCapitalNegocioException e) {
			retorno = retornoErro(RET_NAO_FOI_POSSIVEL+" "+e.getMessage(), dtoCabal);
		}catch (ContaCapitalMovimentacaoNegocioException e) {
			this.getLogger().erro(e, e.getMessage());			
			retorno = retornoErro(RET_NAO_FOI_POSSIVEL+" "+e.getMessage(), dtoCabal);			
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			retorno = retornoErro(RET_ERRO+" "+e.getMessage(), dtoCabal);			
		}
		return retorno;

	}

	/**
	 * Integraliza o capital via boleto bancário
	 */
	public String integralizarBoletoBancario(IntegralizacaoCapitalBoletoDTO dtoBoleto) throws BancoobException {
		String mensagem = null;
		try {

			validarTipoLiquicaoBoleto(dtoBoleto);
			validarValorObrigatorio(dtoBoleto.getValorSubscricao(), dtoBoleto.getValorIntegralizacao());
			validarBoletoPago(dtoBoleto);

			lancamentoIntegralizacaoExternaServico.incluir(montarDtoSubscricaoBoleto(dtoBoleto));
			LancamentoContaCapital lancInteg = lancamentoIntegralizacaoExternaServico.incluir(montarDtoIntegralizacaoBoleto(dtoBoleto));			
			mensagem = montarIdOperacaoContaCapital(lancInteg);

			if (dtoBoleto.getValorSubscricao().compareTo(dtoBoleto.getValorIntegralizacao())==1){
				validarParcelamentoObrigatorioBoleto(dtoBoleto);
				parcelamentoContaCapitalExternoServico.incluirParcelamento(montarDtoParcelamentoCapitalBoleto(dtoBoleto));				
			}

		} catch (LancamentoBoletoFAPPagoNegocioException e) {
			mensagem = RET_NAO_FOI_POSSIVEL + e.getMessage(); 
			lancarLancamentoBoletoPagoFAPNegocioException(mensagem);
		} catch (IntegralizacaoCapitalNegocioException e) {
			mensagem = RET_NAO_FOI_POSSIVEL + e.getMessage(); 
			lancarIntegralizacaoCapitalNegocioException(mensagem);
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			mensagem = RET_NAO_FOI_POSSIVEL + e.getMessage(); 
			lancarIntegralizacaoCapitalNegocioException(mensagem);		
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			mensagem = RET_ERRO + e.getMessage(); 
			lancarIntegralizacaoCapitalNegocioException(mensagem);			
		}
		return mensagem;
	}	

	private void validarBoletoPago(IntegralizacaoCapitalBoletoDTO dtoBoleto) throws BancoobException {
		Integer numCooperativa = instituicaoIntegracaoServico.obterNumeroCooperativa(dtoBoleto.getIdInstituicao());
		Integer numContaCapital = dtoBoleto.getNumContaCapital();
		String idOperacao = dtoBoleto.getIdOperacao();
		Boolean lancamentoExistente = lancamentoIntegralizacaoExternaServico.verificarLancamentoExistente(numCooperativa, numContaCapital, idOperacao);
		if (Boolean.TRUE.equals(lancamentoExistente)) {
			throw new LancamentoBoletoFAPPagoNegocioException("MSG_BOLETO_PAGO", idOperacao);
		}
	}

	/**
	 * Integraliza o capital via cartao de credito
	 */
	public String integralizarCartaoCredito(IntegralizacaoCapitalCartaoDTO dtoCartao) throws BancoobException {
		String mensagem = null;
		try{
			validarValorObrigatorio(dtoCartao.getValorSubscricao(),dtoCartao.getValorIntegralizacao());			
			lancamentoIntegralizacaoExternaServico.incluir(montarDtoSubscricaoCartao(dtoCartao));
			LancamentoContaCapital lancInteg = lancamentoIntegralizacaoExternaServico.incluir(montarDtoIntegralizacaoCartao(dtoCartao));			
			mensagem = montarIdOperacaoContaCapital(lancInteg);
		}catch (IntegralizacaoCapitalNegocioException e) {
			mensagem = RET_NAO_FOI_POSSIVEL + e.getMessage(); 
			lancarIntegralizacaoCapitalNegocioException(mensagem);				
		}catch (ContaCapitalMovimentacaoNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			mensagem = RET_NAO_FOI_POSSIVEL + e.getMessage(); 
			lancarIntegralizacaoCapitalNegocioException(mensagem);			
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			mensagem = RET_ERRO + e.getMessage(); 
			lancarIntegralizacaoCapitalNegocioException(mensagem);			
		}
		return mensagem;
	}		

	/**
	 * {@link IntegralizacaoCapitalServicoRemote#consultarIntegralizacaoPontosCabal(IntegralizacaoCapitalCabalDTO)}
	 * Retorna os registros de lançamentos de integralização via pontos do cartão
	 */
	public List<ConsultaIntegralizacaoCapitalCabalRetornoDTO> consultarIntegralizacaoPontosCabal(ConsultaIntegralizacaoCapitalCabalDTO dtoCabal) throws BancoobException {
		List<ConsultaIntegralizacaoCapitalCabalRetornoDTO> lstRetorno = null;

		try{
			validarObrigatoriosConsultaCabal(dtoCabal);
			lstRetorno = converterListaLancContaCapitalToConsultaIntegCabalRetorno(lancamentoIntegralizacaoExternaServico.consultarIntegralizacao(montarDtoConsultaIntegralizacaoPontosCabal(dtoCabal)));	
		}catch (IntegralizacaoCapitalNegocioException e) {
			lancarIntegralizacaoCapitalNegocioException(e.getMessage());
		}catch (ContaCapitalMovimentacaoNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			lancarIntegralizacaoCapitalNegocioException(RET_ERRO_CONSULTA+e.getMessage());
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			lancarIntegralizacaoCapitalNegocioException(RET_ERRO_CONSULTA+e.getMessage());
		}

		return lstRetorno;
	}

	/**
	 * Converte a List<LancamentoContaCapital> para List<ConsultaIntegralizacaoCapitalCabalRetornoDTO>
	 * @param List<LancamentoContaCapital> 
	 * @return
	 * @throws BancoobException
	 */
	private List<ConsultaIntegralizacaoCapitalCabalRetornoDTO> converterListaLancContaCapitalToConsultaIntegCabalRetorno(List<LancamentoContaCapital> lstLancamento) throws BancoobException{

		List<ConsultaIntegralizacaoCapitalCabalRetornoDTO> lstCabalRetorno = new ArrayList<ConsultaIntegralizacaoCapitalCabalRetornoDTO>();

		for (LancamentoContaCapital item : lstLancamento) {
			lstCabalRetorno.add(converterLancContaCapitalToConsultaIntegCabalRetorno(item));
		} 

		return lstCabalRetorno;

	}

	/**
	 * Converte o LancamentoContaCapital para IntegralizacaoCapitalCabalRetornoDTO
	 * @param LancamentoContaCapital
	 * @return
	 * @throws BancoobException
	 */	
	private ConsultaIntegralizacaoCapitalCabalRetornoDTO converterLancContaCapitalToConsultaIntegCabalRetorno(LancamentoContaCapital lancamento) throws BancoobException{
		ConsultaIntegralizacaoCapitalCabalRetornoDTO saida = new ConsultaIntegralizacaoCapitalCabalRetornoDTO();
		saida.setDataHoraOperacaoContaCapital(lancamento.getDataHoraAtualizacao());
		saida.setIdOperacaoCabal(lancamento.getDescOperacaoExterna());
		saida.setIdOperacaoContaCapital(montarIdOperacaoContaCapital(lancamento));
		saida.setNumCooperativa(instituicaoIntegracaoServico.obterNumeroCooperativa(lancamento.getIdInstituicao()));

		PessoaIntegracaoDTO capesDto = capesIntegracaoServico.obterPessoaInstituicao(lancamento.getContaCapital().getIdPessoa(), lancamento.getIdInstituicao());
		saida.setNumCpfCnpj(capesDto.getCpfCnpj());
		return saida;
	}		

	/**
	 * Retorno com Sucesso da Integralizacao via Cabal
	 * @param lanc
	 * @return
	 * @throws BancoobException
	 */
	private IntegralizacaoCapitalCabalRetornoDTO retornoSucesso(LancamentoContaCapital lanc,IntegralizacaoCapitalCabalDTO dtoCabal) throws BancoobException{
		IntegralizacaoCapitalCabalRetornoDTO retorno = new IntegralizacaoCapitalCabalRetornoDTO();
		retorno.setDataHoraOperacaoContaCapital(lanc.getDataHoraAtualizacao());
		retorno.setDescRetornoCapital(RET_SUCESSO);
		retorno.setIdOperacaoCabal(lanc.getDescOperacaoExterna());
		retorno.setIdOperacaoContaCapital(montarIdOperacaoContaCapital(lanc));
		retorno.setStatusRetornoCapital(Boolean.TRUE);
		retorno.setNumCpfCnpj(dtoCabal.getNumCpfCnpj());
		retorno.setNumCooperativa(dtoCabal.getNumCooperativa());		
		return retorno;
	}

	/**
	 * Retorno com erro na integralização
	 * @param lanc
	 * @return IntegralizacaoCapitalCabalRetornoDTO
	 */
	private IntegralizacaoCapitalCabalRetornoDTO retornoErro(String tipoRetErro,IntegralizacaoCapitalCabalDTO dtoCabal){
		IntegralizacaoCapitalCabalRetornoDTO retorno = new IntegralizacaoCapitalCabalRetornoDTO();		
		retorno.setDataHoraOperacaoContaCapital(new DateTimeDB());
		retorno.setDescRetornoCapital(tipoRetErro);
		retorno.setIdOperacaoCabal(dtoCabal.getIdOperacaoCabal());
		retorno.setStatusRetornoCapital(Boolean.FALSE);
		retorno.setNumCpfCnpj((dtoCabal.getNumCpfCnpj()!=null?dtoCabal.getNumCpfCnpj():null));
		retorno.setNumCooperativa((dtoCabal.getNumCooperativa()!=null?dtoCabal.getNumCooperativa():null));
		return retorno;
	}	

	/**
	 * Monta o ID de saida do Conta Capital (DataLote, NumLoteLanc, NumSeqLanc, Instituicao)
	 * @param lancamentoSql
	 * @return
	 * @throws BancoobException
	 */
	private String montarIdOperacaoContaCapital(LancamentoContaCapital lancamento) throws BancoobException{
		StringBuffer sbf = new StringBuffer(new SimpleDateFormat("yyyyMMdd").format(lancamento.getDataLancamento()));
		sbf.append("|");
		sbf.append(lancamento.getTipoLote().getId());
		sbf.append("|");
		sbf.append(lancamento.getNumSeqLanc());
		sbf.append("|");
		sbf.append(lancamento.getIdInstituicao());
		return sbf.toString();
	}		

	/**
	 * Transforma o DTO da Cabal para o DTO generico de integralização
	 * @param dtoCabal
	 * @return
	 * @throws BancoobException
	 */
	private IntegralizacaoCapitalDTO montarDtoIntegralizacaoPontosCabal(IntegralizacaoCapitalCabalDTO dtoCabal,Boolean bolSubscricao) throws BancoobException{
		IntegralizacaoCapitalDTO dtoIntegralizacao = new IntegralizacaoCapitalDTO();		

		Integer idInstituicao = instituicaoIntegracaoServico.obterIdInstituicao(dtoCabal.getNumCooperativa());
		dtoIntegralizacao.setNumCooperativa(dtoCabal.getNumCooperativa());
		dtoIntegralizacao.setIdInstituicao(idInstituicao);
		dtoIntegralizacao.setNumCpfCnpj(dtoCabal.getNumCpfCnpj());

		if (idInstituicao == null){
			throw new IntegralizacaoCapitalNegocioException("MSG_INTEG_INST_NAO_ENC");
		}		

		PessoaIntegracaoDTO pessoaIntegracaoDTO = capesIntegracaoServico.obterPorCpfCnpjInstituicao(dtoCabal.getNumCpfCnpj(),idInstituicao);

		if (pessoaIntegracaoDTO == null){
			throw new IntegralizacaoCapitalNegocioException("MSG_INTEG_CLI_NAO_ENC");
		}		

		dtoIntegralizacao.setIdPessoa(pessoaIntegracaoDTO.getIdPessoa());
		ContaCapitalLegado contaCapitalLegado = obterContaCapitalClienteLegado(dtoCabal.getNumCooperativa(),pessoaIntegracaoDTO.getIdPessoaLegado());
		dtoIntegralizacao.setNumMatricula(contaCapitalLegado.getNumMatricula());
		dtoIntegralizacao.setValorLancamento(dtoCabal.getValorIntegralizacao());
		dtoIntegralizacao.setIdTipoHistoricoLanc((bolSubscricao?EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_SUBSC.getCodigo():	EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_PONTOS_CARTAO.getCodigo()));
		dtoIntegralizacao.setIdOperacaoOrigem(dtoCabal.getIdOperacaoCabal());
		if (EnumTipoLote.COD_LOTE_CCA_PONTOS_CABAL.isVigente()) {
			dtoIntegralizacao.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_PONTOS_CABAL.getCodigo());
		} else {
			dtoIntegralizacao.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_PARC_AVISTA.getCodigo());
		}

		return dtoIntegralizacao;
	}

	/**
	 * Monta filtros para consulta da integralização via cabal para um associado no dia
	 * @param dtoCabal, filtros INST,MAT,DATA
	 * @return
	 * @throws BancoobException
	 */
	private IntegralizacaoCapitalDTO montarDtoConsultaIntegralizacaoNoDia(IntegralizacaoCapitalCabalDTO dtoCabal) throws BancoobException{
		IntegralizacaoCapitalDTO dtoIntegralizacao = new IntegralizacaoCapitalDTO();		

		Integer idInstituicao = instituicaoIntegracaoServico.obterIdInstituicao(dtoCabal.getNumCooperativa());
		dtoIntegralizacao.setNumCooperativa(dtoCabal.getNumCooperativa());
		dtoIntegralizacao.setIdInstituicao(idInstituicao);
		if (EnumTipoLote.COD_LOTE_CCA_PONTOS_CABAL.isVigente()) {
			dtoIntegralizacao.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_PONTOS_CABAL.getCodigo());
		} else {
			dtoIntegralizacao.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_PARC_AVISTA.getCodigo());
		}

		if (idInstituicao == null){
			throw new IntegralizacaoCapitalNegocioException("MSG_INTEG_INST_NAO_ENC");
		}

		PessoaIntegracaoDTO pessoaIntegracaoDTO = capesIntegracaoServico.obterPorCpfCnpjInstituicao(dtoCabal.getNumCpfCnpj(),idInstituicao);

		if (pessoaIntegracaoDTO == null){
			throw new IntegralizacaoCapitalNegocioException("MSG_INTEG_CLI_NAO_ENC");
		}

		ContaCapitalLegado contaCapitalLegado = obterContaCapitalClienteLegado(dtoCabal.getNumCooperativa(),pessoaIntegracaoDTO.getIdPessoaLegado());
		dtoIntegralizacao.setNumMatricula(contaCapitalLegado.getNumMatricula());		
		dtoIntegralizacao.setIdTipoHistoricoLanc(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_PONTOS_CARTAO.getCodigo());
		dtoIntegralizacao.setDataLancamento(new DateTimeDB(prodLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, idInstituicao).getTime()));		
		return dtoIntegralizacao;
	}	

	/**
	 * Transforma o DTO de consulta da Cabal para o DTO generico
	 * @param ConsultaIntegralizacaoCapitalCabalDTO
	 * @return
	 * @throws BancoobException
	 */
	private IntegralizacaoCapitalDTO montarDtoConsultaIntegralizacaoPontosCabal(ConsultaIntegralizacaoCapitalCabalDTO dtoCabal) throws BancoobException{
		IntegralizacaoCapitalDTO dtoIntegralizacao = new IntegralizacaoCapitalDTO();		
		Integer idInstituicao = instituicaoIntegracaoServico.obterIdInstituicao(dtoCabal.getNumCooperativa());
		dtoIntegralizacao.setNumCooperativa(dtoCabal.getNumCooperativa());
		dtoIntegralizacao.setIdInstituicao(idInstituicao);
		dtoIntegralizacao.setDataLancamento(dtoCabal.getDataIntegralizacao());
		dtoIntegralizacao.setIdOperacaoOrigem(dtoCabal.getIdOperacaoCabal());
		dtoIntegralizacao.setIdTipoHistoricoLanc(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_PONTOS_CARTAO.getCodigo());
		if (dtoCabal.getDataIntegralizacao() != null && !dtoCabal.getDataIntegralizacao().before(EnumTipoLote.COD_LOTE_CCA_PONTOS_CABAL.getDataVigencia())) {
			dtoIntegralizacao.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_PONTOS_CABAL.getCodigo());
		}
		return dtoIntegralizacao;
	}

	/**
	 * Valida os parametros obrigatorios para integralização via cartao cabal
	 * @param dtoCabal
	 * @throws BancoobException
	 */
	private void validarObrigatoriosIntegralizacaoCabal(IntegralizacaoCapitalCabalDTO dtoCabal)throws BancoobException{
		final int tamCoopValido = 4;

		if (dtoCabal.getIdOperacaoCabal() == null || dtoCabal.getIdOperacaoCabal().equals("") ){
			throw new IntegralizacaoCapitalNegocioException("MSG_OBR_INTEG","O identificador da Cabal");
		}	

		if (dtoCabal.getNumCooperativa() == null){
			throw new IntegralizacaoCapitalNegocioException("MSG_OBR_INTEG","A cooperativa");
		}

		if (dtoCabal.getNumCooperativa() != null && (dtoCabal.getNumCooperativa().toString().length() != tamCoopValido)){
			throw new IntegralizacaoCapitalNegocioException("MSG_INTEG_COOP");
		}				

		if (dtoCabal.getNumCpfCnpj() == null || dtoCabal.getNumCpfCnpj().equals("") ){
			throw new IntegralizacaoCapitalNegocioException("MSG_OBR_INTEG","O CPF\\CNPJ");
		}

		if (dtoCabal.getValorIntegralizacao() == null ){
			throw new IntegralizacaoCapitalNegocioException("MSG_OBR_INTEG","O valor");
		}

	}

	/**
	 * valida se ja teve uma integralização no dia para o cliente
	 * @param dtoCabal
	 * @return
	 * @throws BancoobException
	 */
	private void validarIntegralizacaoNoDia(IntegralizacaoCapitalCabalDTO dtoCabal) throws BancoobException{
		IntegralizacaoCapitalDTO dto = montarDtoConsultaIntegralizacaoNoDia(dtoCabal);				
		List<LancamentoContaCapital> lstLanc = lancamentoIntegralizacaoExternaServico.consultarIntegralizacao(dto);

		if (lstLanc != null && !lstLanc.isEmpty()){
			throw new IntegralizacaoCapitalNegocioException("MSG_LIMITE_INTEG");
		}
	}

	/**
	 * Valida os parametros obrigatorios para consulta da integralizacao cabal
	 * @param ConsultaIntegralizacaoCapitalCabalDTO
	 * @throws BancoobException
	 */
	private void validarObrigatoriosConsultaCabal(ConsultaIntegralizacaoCapitalCabalDTO dtoCabal)throws BancoobException{

		if (dtoCabal.getNumCooperativa() == null){
			throw new IntegralizacaoCapitalNegocioException("MSG_OBR_CONSULTA","A Cooperativa");
		}

		if (dtoCabal.getIdOperacaoCabal() == null && dtoCabal.getDataIntegralizacao() == null){
			throw new IntegralizacaoCapitalNegocioException("MSG_OBR_CONSULTA","A data da integralização");		
		}

	}	

	/**
	 * Valida se a conta capital é do cliente\instituicao informado e retorna a conta mais antiga
	 * @param numCooperativa
	 * @param numCliente
	 * @return Retorna a Conta Capital Ativa mais Antiga
	 * @throws BancoobException
	 */
	private ContaCapitalLegado obterContaCapitalClienteLegado(Integer numCooperativa,Integer numCliente) throws BancoobException{

		Boolean situacao = false;
		ContaCapitalLegado contaCapitalLegado = null;

		List<ContaCapitalLegado> listContaCapitalLegado = obterContaCapitalCooperativaCliente(numCooperativa, numCliente,null);

		if (listContaCapitalLegado == null || listContaCapitalLegado.isEmpty()){
			throw new IntegralizacaoCapitalNegocioException("MSG_INTEG_CCA_NAO_ENC");		
		}

		for (ContaCapitalLegado item: listContaCapitalLegado){
			if (item.getCodSituacao() == 1){
				contaCapitalLegado = item;
				situacao = true;
				break;
			}
		}

		if (!situacao){
			throw new IntegralizacaoCapitalNegocioException("MSG_INTEG_CCA_NAO_ATIVO");	
		}

		return contaCapitalLegado;

	}

	/**
	 * Dto para subscricao do boleto
	 * @param dtoCabal
	 * @return
	 * @throws BancoobException
	 */	
	private IntegralizacaoCapitalDTO montarDtoSubscricaoBoleto(IntegralizacaoCapitalBoletoDTO dtoBoleto) throws BancoobException{
		IntegralizacaoCapitalDTO dtoIntegralizacao = new IntegralizacaoCapitalDTO();			
		Integer idInstituicao = dtoBoleto.getIdInstituicao();
		dtoIntegralizacao.setIdInstituicao(idInstituicao);
		dtoIntegralizacao.setNumMatricula(dtoBoleto.getNumContaCapital());
		dtoIntegralizacao.setValorLancamento(dtoBoleto.getValorSubscricao());		
		dtoIntegralizacao.setIdTipoHistoricoLanc(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_SUBSC.getCodigo());		
		dtoIntegralizacao.setIdOperacaoOrigem(dtoBoleto.getIdOperacao());
		if (EnumTipoLote.COD_LOTE_CCA_INTEGRALIZACAO_FAP.isVigente()) {
			dtoIntegralizacao.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_INTEGRALIZACAO_FAP.getCodigo());
		} else {
			dtoIntegralizacao.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_PARC_AVISTA.getCodigo());
		}

		return dtoIntegralizacao;
	}		

	/**
	 * Dto para integralização do boleto
	 * @param dtoCabal
	 * @return
	 * @throws BancoobException
	 */
	private IntegralizacaoCapitalDTO montarDtoIntegralizacaoBoleto(IntegralizacaoCapitalBoletoDTO dtoBoleto) throws BancoobException{		
		IntegralizacaoCapitalDTO dtoIntegralizacao = montarDtoSubscricaoBoleto(dtoBoleto);				
		dtoIntegralizacao.setIdTipoHistoricoLanc(dtoBoleto.getTipoLiquidacao().getCodigoHistorico());
		dtoIntegralizacao.setValorLancamento(dtoBoleto.getValorIntegralizacao());				
		return dtoIntegralizacao;
	}	

	/**
	 * Dto para subscricao via cartao
	 * @param dtoCartao
	 * @return
	 * @throws BancoobException
	 */
	private IntegralizacaoCapitalDTO montarDtoSubscricaoCartao(IntegralizacaoCapitalCartaoDTO dtoCartao) throws BancoobException{		
		IntegralizacaoCapitalDTO dtoIntegralizacao = new IntegralizacaoCapitalDTO();				
		Integer idInstituicao = dtoCartao.getIdInstituicao();
		dtoIntegralizacao.setIdInstituicao(idInstituicao);
		dtoIntegralizacao.setNumMatricula(dtoCartao.getNumContaCapital());
		dtoIntegralizacao.setValorLancamento(dtoCartao.getValorSubscricao());
		dtoIntegralizacao.setIdTipoHistoricoLanc(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_SUBSC.getCodigo());		
		dtoIntegralizacao.setIdOperacaoOrigem(dtoCartao.getIdOperacao());
		if (EnumTipoLote.COD_LOTE_CCA_INTEGRALIZACAO_FAP.isVigente()) {
			dtoIntegralizacao.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_INTEGRALIZACAO_FAP.getCodigo());
		} else {
			dtoIntegralizacao.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_PARC_AVISTA.getCodigo());
		}
		return dtoIntegralizacao;
	}	

	/**
	 * Dto para integralização via cartao
	 * @param dtoCartao
	 * @return
	 * @throws BancoobException
	 */
	private IntegralizacaoCapitalDTO montarDtoIntegralizacaoCartao(IntegralizacaoCapitalCartaoDTO dtoCartao) throws BancoobException{
		IntegralizacaoCapitalDTO dtoIntegralizacao = montarDtoSubscricaoCartao(dtoCartao);		
		dtoIntegralizacao.setValorLancamento(dtoCartao.getValorIntegralizacao());
		dtoIntegralizacao.setIdTipoHistoricoLanc(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_CARTAO_CREDITO.getCodigo());
		return dtoIntegralizacao;
	}

	/**
	 * Dto para criação do parcelamento residual do boleto na forma de Conta Corrente
	 * @param dtoBoleto
	 * @return
	 * @throws BancoobException
	 */
	private List<ParcelamentoCapitalDTO> montarDtoParcelamentoCapitalBoleto(IntegralizacaoCapitalBoletoDTO dtoBoleto) throws BancoobException{

		List<ParcelamentoCapitalDTO> listaParcelas = new ArrayList<ParcelamentoCapitalDTO>();
		BigDecimal valorAParcelar = dtoBoleto.getValorSubscricao().subtract(dtoBoleto.getValorIntegralizacao());
		BigDecimal valorParcela = valorAParcelar.divide(new BigDecimal(dtoBoleto.getQtdeParcelas()), 2, BigDecimal.ROUND_DOWN);		

		DateTimeDB dataVencimento = dtoBoleto.getDataInicioParcelamento();		

		for (short cont=0;cont < dtoBoleto.getQtdeParcelas();cont++) {
			ParcelamentoCapitalDTO parcela = new ParcelamentoCapitalDTO();
			parcela.setDataVencimento(dataVencimento);
			parcela.setIdInstituicao(dtoBoleto.getIdInstituicao());
			parcela.setNumContaCapital(dtoBoleto.getNumContaCapital());
			parcela.setIdSituacaoParcelamento(EnumSituacaoParcelamento.COD_PARCELA_GERADA.getCodigo().shortValue());
			parcela.setIdTipoIntegralizacao(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo().shortValue());
			parcela.setIdTipoParcelamento(EnumTipoParcelamento.COD_TIPO_PARCELAMENTO_INTEGRAL.getCodigo().shortValue());
			parcela.setNumContaCorrente(dtoBoleto.getNumContaCorrente());
			parcela.setNumParcela(cont);
			parcela.setValorParcela(valorParcela);
//			parcela.setCodCanal(EnumCanalIntegralizacao.FAP_BOLETO.getCodigo()); TODO apenas quando atualizar o frontoffice
			listaParcelas.add(parcela);
			dataVencimento = montarDataProximoVencimento(dtoBoleto.getIdInstituicao(), dataVencimento);
		}

		ajustarUltimaParcela(listaParcelas, valorAParcelar);

		return listaParcelas;
	}

	private void ajustarUltimaParcela(List<ParcelamentoCapitalDTO> listaParcelas, BigDecimal valorAParcelar) {
		if (listaParcelas != null && !listaParcelas.isEmpty()) {
			BigDecimal somatorio = new BigDecimal(ContaCapitalConstantes.NUM_ZERO);
			for (Iterator<ParcelamentoCapitalDTO> iterator = listaParcelas.iterator(); iterator.hasNext();) {
				ParcelamentoCapitalDTO parcela = iterator.next();
				if (iterator.hasNext()) {
					somatorio = somatorio.add(parcela.getValorParcela());
				} else {
					BigDecimal valorRemanescente = valorAParcelar.subtract(somatorio);
					parcela.setValorParcela(valorRemanescente);
				}
			}
		}
	}

	private DateTimeDB montarDataProximoVencimento(Integer idInstituicao, DateTimeDB dataVencimento) throws BancoobException{
		DateTimeDB dataProximoVencimento = new DateTimeDB(new org.joda.time.DateTime(dataVencimento).plusMonths(1).getMillis());

		if (!genIntIntegracaoServico.verificarDiaUtil(idInstituicao, dataProximoVencimento)){
			dataProximoVencimento = new DateTimeDB(genIntIntegracaoServico.recuperarProximoDiaUtil(idInstituicao, dataProximoVencimento).getTime());
		}

		return dataProximoVencimento;
	}

	/**
	 * Valida os valores para Subscrição e Integralização em conjunto p\ integralizacao
	 * @param valorSubscricao
	 * @param valorIntegralizacao
	 * @throws BancoobException
	 */
	private void validarValorObrigatorio(BigDecimal valorSubscricao, BigDecimal valorIntegralizacao)throws BancoobException{

		if (valorIntegralizacao == null ){
			throw new IntegralizacaoCapitalNegocioException("MSG_OBR_INTEG","O valor de integralização");
		}		

		if (valorIntegralizacao.compareTo(BigDecimal.ZERO)!=1){
			throw new IntegralizacaoCapitalNegocioException("MSG_INTEG_VALOR");
		}

		if (valorSubscricao == null ){
			throw new IntegralizacaoCapitalNegocioException("MSG_OBR_INTEG","O valor de subscrição");
		}		

		if (valorSubscricao.compareTo(BigDecimal.ZERO)!=1){
			throw new IntegralizacaoCapitalNegocioException("MSG_INTEG_VALOR");
		}		

		if (valorSubscricao.compareTo(valorIntegralizacao)==-1){
			throw new IntegralizacaoCapitalNegocioException("MSG_SUBSC_MENOR_INTEG");
		}

	}	

	/**
	 * Valida tipo de liquidação de integralização através de boleto bancario
	 * 
	 * @throws BancoobException
	 */
	private void validarTipoLiquicaoBoleto(IntegralizacaoCapitalBoletoDTO dtoBoleto) throws BancoobException {

		if(dtoBoleto.getTipoLiquidacao() == null || dtoBoleto.getTipoLiquidacao().getCodigo().intValue() <= 0) {
			throw new IntegralizacaoCapitalNegocioException("MSG_OBR_INTEG_TP_LIQUID");
		}
	}

	/**
	 * Valida dados do parcelamento para Subscrição > Integralização
	 * A data do Boleto é a data em que foi integralizado o boleto, o parcelamento deve começar sempre um mês após o pagamento desse boleto.
	 * @param dtoIntegralizacao
	 * @throws BancoobException
	 */
	private void validarParcelamentoObrigatorioBoleto(IntegralizacaoCapitalBoletoDTO dto)throws BancoobException{

		DateTimeDB dataBoleto = new DateTimeDB(prodLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, dto.getIdInstituicao()).getTime());		
		DateTimeDB dataPrimeiraParcelaMin = new DateTimeDB(new org.joda.time.DateTime(dataBoleto).plusMonths(1).getMillis());
		DateTimeDB dataPrimeiraParcelaMax = new DateTimeDB(new org.joda.time.DateTime(dataPrimeiraParcelaMin).plusMonths(1).getMillis());

		if (dto.getDataInicioParcelamento() == null ){
			throw new IntegralizacaoCapitalNegocioException("MSG_OBR_INTEG","A data de início do parcelamento");
		}			

		if ((dto.getDataInicioParcelamento().compareTo(dataPrimeiraParcelaMin) < 0) || (dto.getDataInicioParcelamento().compareTo(dataPrimeiraParcelaMax) > 0)){
			throw new IntegralizacaoCapitalNegocioException("MSG_DATA_PRIMEIRA_PARC_INTERVALO",ContaCapitalUtil.formatarDataMascara(dataPrimeiraParcelaMin,"dd/MM/yyyy"),
					ContaCapitalUtil.formatarDataMascara(dataPrimeiraParcelaMax,"dd/MM/yyyy"), ContaCapitalUtil.formatarDataMascara(dto.getDataInicioParcelamento(),"dd/MM/yyyy"));
		}		

		if (dto.getQtdeParcelas() == null ){
			throw new IntegralizacaoCapitalNegocioException("MSG_OBR_INTEG","A quantidade de parcelas");
		}		

		if (dto.getQtdeParcelas().intValue() <= 0){
			throw new IntegralizacaoCapitalNegocioException("MSG_PARCELA_VALOR_ZERO");
		}

		if (dto.getQtdeParcelas().intValue() > 0 && dto.getNumContaCorrente() == null){
			throw new IntegralizacaoCapitalNegocioException("MSG_OBR_INTEG","A conta corrente");
		}
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.api.negocio.servicos.IntegralizacaoCapitalServico#integralizarRateio(java.util.List)
	 */
	public String integralizarRateio(List<IntegralizacaoCapitalRateioDTO> dtosRateio) throws BancoobException {
		String chaveRateio = null;
		try {
			validarIntegralizacaoRateio(dtosRateio);
			List<IntegralizacaoCapitalDTO> dtos = montarDtosIntegralizacaoPelaSituacaoCCA(dtosRateio);
			List<LancamentoContaCapital> lancamentos = lancamentoIntegralizacaoExternaServico.incluirRateioEmLote(dtos);
			chaveRateio = ChaveLancamentoRateioHelper.toString(lancamentos.get(0));
		} catch (IntegralizacaoCapitalNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			lancarAPIContaCapitalNegocioException(e.getMessage());
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			lancarAPIContaCapitalNegocioException(e.getMessage());		
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			lancarAPIContaCapitalNegocioException(e.getMessage());			
		}
		return chaveRateio;
	}

	/**
	 * Monta a lista de {@link IntegralizacaoCapitalDTO} de acordo com a situacao das contas capital passadas para rateio.
	 * Se ativa, cria lancamentos com historico 1 e 108, caso contrario, cria lancamento com historico 208.
	 * @param dtosRateio
	 * @return
	 * @throws BancoobException
	 */
	private List<IntegralizacaoCapitalDTO> montarDtosIntegralizacaoPelaSituacaoCCA(List<IntegralizacaoCapitalRateioDTO> dtosRateio) throws BancoobException {
		String idOperacao = "RAT"+new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
		List<IntegralizacaoCapitalDTO> dtos = new ArrayList<IntegralizacaoCapitalDTO>();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		List<ContaCapitalResumoDTO> ccas = contaCapitalServico.obterResumos(extrairIdsRateio(dtosRateio));
		Map<Integer, Map<String, Integer>> mapCCA = montarMapCCARateio(ccas);
		for (IntegralizacaoCapitalRateioDTO dtoRateio : dtosRateio) {
			Map<String, Integer> map = mapCCA.get(dtoRateio.getIdContaCapital());
			if (map != null) {
				Integer idSituacao = map.get("situacao");
				Integer numContaCapital = map.get("numContaCapital");
				if (EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo().equals(idSituacao)) {
					dtos.add(montarDtoIntegralizacaoRateio(idOperacao, sdf, dtoRateio, numContaCapital, EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_SUBSC.getCodigo()));
					dtos.add(montarDtoIntegralizacaoRateio(idOperacao, sdf, dtoRateio, numContaCapital, EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_RATEIO.getCodigo()));
				} else {
					dtos.add(montarDtoIntegralizacaoRateio(idOperacao, sdf, dtoRateio, numContaCapital, EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_DEV_RATEIO_COOP_DESL.getCodigo()));
				}
			}
		}
		return dtos;
	}

	/**
	 * Monta map de batimento (idContaCapital, {situacao,numContaCapital})
	 * @param ccas
	 * @return
	 */
	private Map<Integer, Map<String, Integer>> montarMapCCARateio(List<ContaCapitalResumoDTO> ccas) {
		Map<Integer, Map<String, Integer>> map = new HashMap<Integer, Map<String, Integer>>();
		for (ContaCapitalResumoDTO cca : ccas) {
			Map<String, Integer> innerMap = new HashMap<String, Integer>();
			innerMap.put("situacao", cca.getIdSituacaoContaCapital());
			innerMap.put("numContaCapital", cca.getNumContaCapital());
			map.put(cca.getIdContaCapital(), innerMap);
		}
		return map;
	}

	/**
	 * Extrai os ids dos dtos de rateio.
	 * @param dtosRateio
	 * @return
	 */
	private List<Integer> extrairIdsRateio(List<IntegralizacaoCapitalRateioDTO> dtosRateio) {
		List<Integer> ids = new ArrayList<Integer>();
		for (IntegralizacaoCapitalRateioDTO dto : dtosRateio) {
			ids.add(dto.getIdContaCapital());
		}
		return ids;
	}

	/**
	 * Validacao simples de preenchimento dos objetos de rateio.
	 * @param dtosRateio
	 * @throws IntegralizacaoCapitalNegocioException
	 */
	private void validarIntegralizacaoRateio(List<IntegralizacaoCapitalRateioDTO> dtosRateio) throws IntegralizacaoCapitalNegocioException {
		if (dtosRateio == null || dtosRateio.isEmpty()) {
			throw new IntegralizacaoCapitalNegocioException("MSG_ESTORNO_RATEIO_VAZIO");
		}
		for (int posicao = 0; posicao < dtosRateio.size(); posicao++) {
			IntegralizacaoCapitalRateioDTO dtoRateio = dtosRateio.get(posicao);
			if (dtoRateio.getIdContaCapital() == null) {
				throw new IntegralizacaoCapitalNegocioException("MSG_INTEG_RATEIO_CAMPO_OBRIG", "idContaCapital", posicao);
			}
			if (dtoRateio.getIdInstituicao() == null) {
				throw new IntegralizacaoCapitalNegocioException("MSG_INTEG_RATEIO_CAMPO_OBRIG", "idInstituicao", posicao);
			}
			if (dtoRateio.getIdUsuario() == null) {
				throw new IntegralizacaoCapitalNegocioException("MSG_INTEG_RATEIO_CAMPO_OBRIG", "idUsuario", posicao);
			}
			if (dtoRateio.getValorLancamento() == null) {
				throw new IntegralizacaoCapitalNegocioException("MSG_INTEG_RATEIO_CAMPO_OBRIG", "valorLancamento", posicao);
			}
			if (dtoRateio.getDataCalculo() == null) {
				throw new IntegralizacaoCapitalNegocioException("MSG_INTEG_RATEIO_CAMPO_OBRIG", "dataCalculo", posicao);
			}
		}
	}

	/**
	 * Metodo auxiliar de montagem dos {@link IntegralizacaoCapitalDTO} para rateio.
	 * @param idOperacao
	 * @param sdf
	 * @param dtoRateio
	 * @param numContaCapital
	 * @param idTipoHistoricoLanc
	 * @return
	 * @throws BancoobException
	 */
	private IntegralizacaoCapitalDTO montarDtoIntegralizacaoRateio(String idOperacao, SimpleDateFormat sdf, IntegralizacaoCapitalRateioDTO dtoRateio, 
			Integer numContaCapital, Integer idTipoHistoricoLanc) throws BancoobException{
		IntegralizacaoCapitalDTO dtoIntegralizacao = new IntegralizacaoCapitalDTO();			
		dtoIntegralizacao.setIdInstituicao(dtoRateio.getIdInstituicao());
		dtoIntegralizacao.setIdContaCapital(dtoRateio.getIdContaCapital());
		dtoIntegralizacao.setNumMatricula(numContaCapital);
		dtoIntegralizacao.setValorLancamento(dtoRateio.getValorLancamento());
		dtoIntegralizacao.setIdUsuario(dtoRateio.getIdUsuario());
		dtoIntegralizacao.setIdTipoHistoricoLanc(idTipoHistoricoLanc);		
		dtoIntegralizacao.setDescNumDocumento(sdf.format(dtoRateio.getDataCalculo())+"R2");
		dtoIntegralizacao.setNumLoteLanc(EnumTipoLote.COD_LOTE_CCA_DEST_RATEIO.getCodigo());
		dtoIntegralizacao.setIdOperacaoOrigem(idOperacao);
		return dtoIntegralizacao;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.api.negocio.servicos.IntegralizacaoCapitalServico#estornarRateio(br.com.sicoob.sisbr.cca.api.negocio.dto.EstornoRateioDTO)
	 */
	public void estornarRateio(EstornoRateioDTO dto) throws BancoobException {
		try {
			validarEstornoRateio(dto);
			for (String chaveRateio : dto.getChavesRateio()) {
				lancamentoIntegralizacaoExternaServico.estornarRateio(dto.getIdUsuario(), recuperarLancamentosRateio(chaveRateio));
			}
		} catch (IntegralizacaoCapitalNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			lancarAPIContaCapitalNegocioException(e.getMessage());
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			lancarAPIContaCapitalNegocioException(e.getMessage());		
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			lancarAPIContaCapitalNegocioException(e.getMessage());			
		}
	}

	/**
	 * Valida a entrada do estorno de rateio.
	 * @param dto
	 * @throws BancoobException
	 */
	private void validarEstornoRateio(EstornoRateioDTO dto) throws BancoobException {
		if (dto.getIdUsuario() == null) {
			throw new IntegralizacaoCapitalNegocioException("MSG_OBR_INTEG","idUsuario");
		}
		if (dto.getChavesRateio().isEmpty()) {
			throw new IntegralizacaoCapitalNegocioException("MSG_ESTORNO_RATEIO_VAZIO"); 
		}
		for (String chaveRateio : dto.getChavesRateio()) {
			LancamentoContaCapital lancamentoChave = ChaveLancamentoRateioHelper.toLancamento(chaveRateio);
			if (lancamentoIntegralizacaoExternaServico.verificarEstornoRealizado(lancamentoChave)) {
				throw new IntegralizacaoCapitalNegocioException("MSG_ESTORNO_REALIZADO", chaveRateio); 
			}
			Map<String, List<Integer>> ccasPorGrupo = lancamentoIntegralizacaoExternaServico.validarCCAsSemSaldoParaEstornoRateio(lancamentoChave);
			if (!ccasPorGrupo.isEmpty()) {
				throw new ContaCapitalMovimentacaoNegocioException("MSG_ESTORNO_RATEIO_SALDO", ContaCapitalUtil.formatarListaValores(ccasPorGrupo.get("SUBSC")),
						ContaCapitalUtil.formatarListaValores(ccasPorGrupo.get("INTEG")), ContaCapitalUtil.formatarListaValores(ccasPorGrupo.get("DEVOL")));
			}
		}
	}

	/**
	 * Recupera os lancamentos da chaveRateio
	 * @param chaveRateio
	 * @return
	 * @throws BancoobException
	 */
	private List<LancamentoEstornoRateioDTO> recuperarLancamentosRateio(String chaveRateio) throws BancoobException {
		LancamentoContaCapital lancamentoChave = ChaveLancamentoRateioHelper.toLancamento(chaveRateio);
		return lancamentoIntegralizacaoExternaServico.consultarLancamentoEstornoRateio(lancamentoChave);
	}
	
}

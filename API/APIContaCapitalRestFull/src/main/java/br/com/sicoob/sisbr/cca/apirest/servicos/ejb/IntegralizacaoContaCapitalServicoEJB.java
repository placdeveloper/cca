package br.com.sicoob.sisbr.cca.apirest.servicos.ejb;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoLote;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoParcelamento;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ContaCorrenteViewServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoIntegralizacaoExternaServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalExternoServicoLocal;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.cca.apirest.dtos.IntegralizacaoRecursoDTO;
import br.com.sicoob.sisbr.cca.apirest.dtos.IntegralizacaoRecursoRetornoDTO;
import br.com.sicoob.sisbr.cca.apirest.excecao.ContaCapitalAPIRestException;
import br.com.sicoob.sisbr.cca.apirest.servicos.interfaces.IntegralizacaoContaCapitalServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.ContaCorrenteIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.GenIntIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

	@Stateless
	@Local(IntegralizacaoContaCapitalServicoLocal.class)
	@TransactionManagement(TransactionManagementType.CONTAINER)
	public class IntegralizacaoContaCapitalServicoEJB implements IntegralizacaoContaCapitalServicoLocal {

		private ISicoobLogger logger = SicoobLoggerPadrao.getInstance(IntegralizacaoContaCapitalServicoEJB.class);
		
		@EJB
		private ContaCorrenteIntegracaoServicoLocal contaCorrenteIntegracaoServico;
		
		@EJB
		private LancamentoIntegralizacaoExternaServicoLocal lancamentoIntegralizacaoExternaServico;	
		
		@EJB
		private ProdutoLegadoServicoLocal prodLegadoServico;
		
		@EJB
		private ParcelamentoContaCapitalExternoServicoLocal parcelamentoContaCapitalExternoServico;	
		
		@EJB
		private GenIntIntegracaoServicoLocal genIntIntegracaoServico;	
		
		@EJB
		private ContaCorrenteViewServicoLocal contaCorrenteViewServico;
	
		@EJB
		private ContaCapitalServicoLocal contaCapitalServico;		
		
		/**
		 * {@link IntegralizacaoContaCapitalServicoLocal#integralizarCapital(IntegralizacaoRecursoDTO)}
		 */
		@Override
		@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)		
		public List<IntegralizacaoRecursoRetornoDTO> integralizarCapital(IntegralizacaoRecursoDTO dto) throws BancoobException {	

			logger.info("INTEG REST - Validações");
			validarDadosDTO(dto);
			ComplementarDadosDTO(dto);
			validarValor(dto.getValorSubscricao(), dto.getValorIntegralizacao());
			validarTipoIntegralizacaoAceito(dto);
			validarParcelamento(dto);
			validarIntegralizacaoViaCCO(dto);
			
			logger.info("INTEG REST - Subscrevendo Capital");
			LancamentoContaCapital lancRetornoSubsc = lancamentoIntegralizacaoExternaServico.incluir(montarDtoSubscricao(dto));
			LancamentoContaCapital lancRetornoInteg = null;
			
			if (!isIntegralizacaoSomenteComParcelamento(dto)) {
				logger.info("INTEG REST - Integralizando Capital");
				lancRetornoInteg = lancamentoIntegralizacaoExternaServico.incluir(montarDtoIntegralizacao(dto));
			}else {
				logger.info("INTEG REST - Parcelando o Capital no lugar da Integralização. CAIXA e FOLHA");
				parcelamentoContaCapitalExternoServico.incluirParcelamento(montarDtoParcelamentoCapitalAVista(dto));				
			}

			if (isIntegralizacaoViaCCO(dto)) {
				logger.info("INTEG REST - Debitando Conta Corrente");
				LancamentoContaCorrenteIntegracaoRetDTO lancDtoCcoRet = contaCorrenteIntegracaoServico.gravarLancamentosIntegracao(montarLancamentoCco(dto));

				if (lancDtoCcoRet.getCodRetorno().equals(0)){
					throw new ContaCapitalAPIRestException("MSG_ERRO_AO", "realizar o debito na conta corrente." + lancDtoCcoRet.getMensagem());
				}
			}
			
			if (isValorResidualSubscricao(dto)){
				logger.info("INTEG REST - Incluindo Parcelamento");
				parcelamentoContaCapitalExternoServico.incluirParcelamento(montarDtoParcelamentoCapitalResidual(dto));
			}			
						
			logger.info("INTEG REST - SUCESSO - Montando Retorno");
			List<IntegralizacaoRecursoRetornoDTO> listSaida = new ArrayList<IntegralizacaoRecursoRetornoDTO>();
			listSaida.add(montarIdOperacaoContaCapital(lancRetornoSubsc));
			if (lancRetornoInteg != null) {
				listSaida.add(montarIdOperacaoContaCapital(lancRetornoInteg));				
			}
					
			return listSaida;
		
		}

		/**
		 * Complementa o dto com mais informações
		 * @param dto
		 * @throws BancoobException
		 */
		private void ComplementarDadosDTO(IntegralizacaoRecursoDTO dto) throws BancoobException {
			ContaCapital contaCapital = contaCapitalServico.obterPorInstituicaoMatricula(dto.getIdInstituicao(), dto.getNumeroContaCapital());
			if (contaCapital == null) {
				throw new ContaCapitalAPIRestException("MSG_INEXISTENTE","Conta capital",dto.getNumeroContaCapital());
			}
			dto.setDataAtualProduto(prodLegadoServico.obterDataAtualProdutoDateTimeDB(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, dto.getIdInstituicao()));			
			dto.setIdContaCapital(contaCapital.getId());
			dto.setIdPessoa(contaCapital.getIdPessoa());
			if (dto.getDataInicioParcelamento() != null) {
				dto.setDataParcelamento(new DateTimeDB(ContaCapitalUtil.formatarStringToDateUS(dto.getDataInicioParcelamento()).getTime()));
			}
		}		

		/**
		 * Valida se o tipohistorico informado é aceito para integralização e se pode integralizar apenas uma vez
		 * @throws BancoobException
		 */
		private void validarTipoIntegralizacaoAceito(IntegralizacaoRecursoDTO dto) throws BancoobException{
			Boolean isValido = Boolean.FALSE;
			Map<Integer,Integer> map = mapaTipoIntegralizacaoLimiteIntegralizacao();
			
			if (map.containsKey(dto.getIdTipoHistorico())){
				if (map.get(dto.getIdTipoHistorico()).equals(1)) {
					validarIntegralizacaoPaga(dto);
				}
				isValido = Boolean.TRUE;
			}
			
			if (!isValido) {
				throw new ContaCapitalAPIRestException("MSG_LANC_INTEG_NAO_PERM_HIST_INFORMADO",dto.getIdTipoHistorico());
			}
			
		}
		
		/**
		 * Valida se o tipohistorico informado aceita parcelamento
		 * @throws BancoobException
		 */
		private void validarTipoIntegralizacaoModoParcelamento(IntegralizacaoRecursoDTO dto) throws BancoobException{

			Map<Integer,Integer> map = mapaTipoIntegralizacaoModoParcelamento();
			
			if (map.containsKey(dto.getIdTipoHistorico())){
				if (map.get(dto.getIdTipoHistorico()) == null) {
					throw new ContaCapitalAPIRestException("MSG_LANC_INTEG_NAO_PERM_HIST_PARCEL",dto.getIdTipoHistorico());
				}
			}
			
		}	
		
		/**
		 * Verifica se o historico tem o limite de uma integralização por dia
		 * @throws BancoobException
		 */
		private Boolean limiteUmaIntegracaoPorDia(IntegralizacaoRecursoDTO dto) throws BancoobException{
			return EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_PONTOS_CARTAO.getCodigo().equals(dto.getIdTipoHistorico());
		}
		
		/**
		 * Mapa com: os historicos permitidos e se podem integralizar apenas uma vez
		 * @return
		 * @throws BancoobException
		 */
		private Map<Integer,Integer> mapaTipoIntegralizacaoLimiteIntegralizacao() throws BancoobException{
			Map<Integer,Integer> map = new HashMap<Integer, Integer>();
			map.put(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_FOLHA.getCodigo(), 0);
			map.put(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_BANCO.getCodigo(), 0);
			map.put(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_CONTA.getCodigo(), 0);
			map.put(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_CONTA_DIGITAL.getCodigo(), 1);
			map.put(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_PONTOS_CARTAO.getCodigo(), 1);
			map.put(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_CAIXA.getCodigo(), 0);
			return map;
		}
		 
		/**
		 * Mapa com: os historicos e os modos de parcelamento relacionados
		 * @return
		 * @throws BancoobException
		 */
		private Map<Integer,Integer> mapaTipoIntegralizacaoModoParcelamento() throws BancoobException{
			Map<Integer,Integer> map = new HashMap<Integer, Integer>();
			map.put(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_FOLHA.getCodigo(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_FOLHA.getCodigo());
			map.put(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_BANCO.getCodigo(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getCodigo());
			map.put(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_CONTA.getCodigo(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo());
			map.put(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_CONTA_DIGITAL.getCodigo(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo());
			map.put(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_PONTOS_CARTAO.getCodigo(), null);
			map.put(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_CAIXA.getCodigo(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getCodigo());
			return map;
		}	
		
		
		/**
		 * Lista de Dto para criação do parcelamento residual da subscrição
		 * @param dto
		 * @return
		 * @throws BancoobException
		 */
		private List<ParcelamentoCapitalDTO> montarDtoParcelamentoCapitalResidual(IntegralizacaoRecursoDTO dto) throws BancoobException{

			List<ParcelamentoCapitalDTO> listaParcelas = new ArrayList<ParcelamentoCapitalDTO>();
			BigDecimal valorAParcelar = dto.getValorSubscricao().subtract(dto.getValorIntegralizacao());
			BigDecimal valorParcela = valorAParcelar.divide(new BigDecimal(dto.getQuantidadeParcelas()), 2, BigDecimal.ROUND_DOWN);		
			DateTimeDB dataVencimento = dto.getDataParcelamento();		

			for (short cont=0;cont < dto.getQuantidadeParcelas();cont++) {
				listaParcelas.add(MontarDtoParcela(dto,valorParcela,dataVencimento,cont));
				dataVencimento = montarDataProximoVencimento(dto.getIdInstituicao(), dataVencimento);
			}

			ajustarUltimaParcela(listaParcelas, valorAParcelar);

			return listaParcelas;
		}

		/**
		 * Dto para criação do parcelamento a vista, casos em que "isIntegralizacaoSomenteComParcelamento"
		 * @param dto
		 * @return
		 * @throws BancoobException
		 */
		private List<ParcelamentoCapitalDTO> montarDtoParcelamentoCapitalAVista(IntegralizacaoRecursoDTO dto) throws BancoobException{

			List<ParcelamentoCapitalDTO> listaParcelas = new ArrayList<ParcelamentoCapitalDTO>();
			
			DateTimeDB dataAVista = prodLegadoServico.obterDataAtualProdutoDateTimeDB(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, dto.getIdInstituicao());					
			DateTimeDB dataVencimento = montarDataVencimentoAVista(dto.getIdInstituicao(),dataAVista);
			
			listaParcelas.add(MontarDtoParcela(dto,dto.getValorIntegralizacao(),dataVencimento,(short) 0));
			return listaParcelas;
		}		
		
		/**
		 * Monta o ParcelamentoCapitalDTO para o parcelamento 
		 * @param dto
		 * @param valorParcela
		 * @param dataVencimento
		 * @param cont
		 * @return
		 * @throws BancoobException
		 */
		private ParcelamentoCapitalDTO MontarDtoParcela(IntegralizacaoRecursoDTO dto,BigDecimal valorParcela, DateTimeDB dataVencimento, short numParcela) throws BancoobException {
			ParcelamentoCapitalDTO parcela = new ParcelamentoCapitalDTO();
			parcela.setDataVencimento(dataVencimento);
			parcela.setIdInstituicao(dto.getIdInstituicao());
			parcela.setNumContaCapital(dto.getNumeroContaCapital());
			parcela.setIdSituacaoParcelamento(EnumSituacaoParcelamento.COD_PARCELA_GERADA.getCodigo().shortValue());
			parcela.setIdTipoIntegralizacao(mapaTipoIntegralizacaoModoParcelamento().get(dto.getIdTipoHistorico()).shortValue());
			parcela.setIdTipoParcelamento(EnumTipoParcelamento.COD_TIPO_PARCELAMENTO_INTEGRAL.getCodigo().shortValue());
			parcela.setNumContaCorrente(dto.getNumeroContaCorrente());
			parcela.setNumParcela(numParcela);
			parcela.setValorParcela(valorParcela);
			return parcela;
		}
		
		
		/**
		 * Valida os valores obrigatorios para integralização de capital
		 * @param dto
		 */
		private void validarDadosDTO(IntegralizacaoRecursoDTO dto) throws BancoobException{
		
			if (dto.getNumeroContaCapital() == null){
				throw new ContaCapitalAPIRestException("MSG_OBR_A", "A conta capital");
			}
			
			if (dto.getIdInstituicao() == null){
				throw new ContaCapitalAPIRestException("MSG_OBR_A", "A instituição");
			}
			
			if (dto.getIdTipoHistorico() == null){
				throw new ContaCapitalAPIRestException("MSG_OBR_O", "O tipo de histórico");
			}
			
		}

		/**
		 * Valida os valores para Subscrição e Integralização em conjunto p\ integralizacao
		 * @param valorSubscricao
		 * @param valorIntegralizacao
		 * @throws BancoobException
		 */
		private void validarValor(BigDecimal valorSubscricao, BigDecimal valorIntegralizacao)throws BancoobException{
			
			if (valorIntegralizacao == null){
				throw new ContaCapitalAPIRestException("MSG_OBR_O", "O valor de integralização");
			}
		
			if (valorIntegralizacao.compareTo(BigDecimal.ZERO)!=1){
				throw new ContaCapitalAPIRestException("MSG_MAIORQUE", "O valor de integralização","zero");
			}

			if (valorSubscricao == null){
				throw new ContaCapitalAPIRestException("MSG_OBR_O", "O valor de subscrição");
			}		

			if (valorSubscricao.compareTo(BigDecimal.ZERO)!=1){
				throw new ContaCapitalAPIRestException("MSG_MAIORQUE", "O valor de subscrição","zero");
			}		

			if (valorSubscricao.compareTo(valorIntegralizacao)==-1){
				throw new ContaCapitalAPIRestException("MSG_MAIORIGUALQUE", "O valor de subscrição","o valor de integralização");
			}

		}	
		
		/**
		 * Valida Integralização já realizada
		 * @param dto
		 * @throws BancoobException
		 */
		private void validarIntegralizacaoPaga(IntegralizacaoRecursoDTO dto) throws BancoobException {
			IntegralizacaoCapitalDTO dtoConsultaInteg = montarDtoIntegralizacao(dto);			
			dtoConsultaInteg.setIdContaCapital(dto.getIdContaCapital());
			
			if (limiteUmaIntegracaoPorDia(dto)){
				dtoConsultaInteg.setDataLancamento(dto.getDataAtualProduto());
			}
			
			Boolean lancamentoExistente = lancamentoIntegralizacaoExternaServico.consultarLancamentoIntegralizacaoJaRealizada(dtoConsultaInteg);

			if (Boolean.TRUE.equals(lancamentoExistente)) {
				String operacao = (dto.getCodigoOperacao() != null?dto.getCodigoOperacao():"");
				throw new ContaCapitalAPIRestException("MSG_LANC_INTEG_JA_EFETUADO_OPERACAO", operacao);			
			}
		}
		
		/**
		 * Valida dto para integralização via conta corrente
		 * Verifica cco informada e se correponde a uma cco da conta capital informada
		 * @param dto
		 * @throws BancoobException
		 */
		private void validarIntegralizacaoViaCCO(IntegralizacaoRecursoDTO dto) throws BancoobException {
			
			if (!isIntegralizacaoViaCCO(dto)) {
				return;
			}
			
			if (dto.getNumeroContaCorrente() == null){
				throw new ContaCapitalAPIRestException("MSG_OBR_O", "O número da conta corrente");
			}	
			
			if (!contaCorrenteViewServico.verificarContaCorrentePessoa(dto.getIdInstituicao(), dto.getIdPessoa(), dto.getNumeroContaCorrente().intValue())) {
				throw new ContaCapitalAPIRestException("MSG_CCO_CONTA_DIF_CLIENTE");
			}

		}	
		
		/**
		 * Historico corresponde a uma integralização via cco?
		 * @param dto
		 * @return
		 * @throws BancoobException
		 */
		private Boolean isIntegralizacaoViaCCO(IntegralizacaoRecursoDTO dto) throws BancoobException  {
			return (EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_CONTA_DIGITAL.getCodigo().equals(dto.getIdTipoHistorico()) ||
					EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_CONTA.getCodigo().equals(dto.getIdTipoHistorico()))? Boolean.TRUE:Boolean.FALSE;					
		}
		
		/**
		 * Casos em que não se integraliza, apenas subscreve e parcela a integralização 
		 * @param dto
		 * @return
		 * @throws BancoobException
		 */
		private Boolean isIntegralizacaoSomenteComParcelamento(IntegralizacaoRecursoDTO dto) throws BancoobException  {
			return (EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_FOLHA.getCodigo().equals(dto.getIdTipoHistorico()) ||
					EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_CAIXA.getCodigo().equals(dto.getIdTipoHistorico()))? Boolean.TRUE:Boolean.FALSE;
		}	

		/**
		 * Tem valor residual de subscrição, ou seja subscrição maior que o parcelamento?
		 * @param dto
		 * @return
		 * @throws BancoobException
		 */
		private Boolean isValorResidualSubscricao(IntegralizacaoRecursoDTO dto) throws BancoobException  {
			return (dto.getValorSubscricao().compareTo(dto.getValorIntegralizacao())==1)? Boolean.TRUE:Boolean.FALSE;					
		}	
		
		/**
		 * Dto para subscricao 
		 * @param dto
		 * @return
		 * @throws BancoobException
		 */	
		private IntegralizacaoCapitalDTO montarDtoSubscricao(IntegralizacaoRecursoDTO dto) throws BancoobException{
			IntegralizacaoCapitalDTO dtoIntegralizacao = new IntegralizacaoCapitalDTO();			
			Integer idInstituicao = dto.getIdInstituicao();
			dtoIntegralizacao.setIdInstituicao(idInstituicao);
			dtoIntegralizacao.setNumMatricula(dto.getNumeroContaCapital());
			dtoIntegralizacao.setValorLancamento(dto.getValorSubscricao());		
			dtoIntegralizacao.setIdTipoHistoricoLanc(EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_SUBSC.getCodigo());		
			dtoIntegralizacao.setIdOperacaoOrigem(dto.getCodigoOperacao());
			dtoIntegralizacao.setNumLoteLanc(loteCorrespondenteTipoHistorico(dto));

			return dtoIntegralizacao;
		}
		
		/**
		 * Dto para integralização 
		 * @param dto
		 * @return
		 * @throws BancoobException
		 */
		private IntegralizacaoCapitalDTO montarDtoIntegralizacao(IntegralizacaoRecursoDTO dto) throws BancoobException{		
			IntegralizacaoCapitalDTO dtoIntegralizacao = montarDtoSubscricao(dto);				
			dtoIntegralizacao.setIdTipoHistoricoLanc(dto.getIdTipoHistorico());
			dtoIntegralizacao.setValorLancamento(dto.getValorIntegralizacao());				
			return dtoIntegralizacao;
		}	

		/**
		 * retorna o lote correspondente a integralização solicitada
		 * @param dto
		 * @return
		 * @throws BancoobException
		 */
		private Integer loteCorrespondenteTipoHistorico(IntegralizacaoRecursoDTO dto) throws BancoobException{
			if (EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_CONTA_DIGITAL.getCodigo().equals(dto.getIdTipoHistorico())) {
				return EnumTipoLote.COD_LOTE_CCA_INTEGRALIZACAO_FAP.getCodigo();
			}

			if (EnumTipoHistoricoCCA.COD_TIPO_HIST_CCA_INTEG_PONTOS_CARTAO.getCodigo().equals(dto.getIdTipoHistorico())) {
				return EnumTipoLote.COD_LOTE_CCA_PONTOS_CABAL.getCodigo();
			}
					
			return EnumTipoLote.COD_LOTE_CCA_PARC_AVISTA.getCodigo();
		}
		
		/**
		 * Monta o Identificador do lancamento em conta capital(IdInstituicao,DataLote, NumLoteLanc, NumSeqLanc)
		 * @param lancamentoContaCapital
		 * @return
		 * @throws BancoobException
		 */
		private IntegralizacaoRecursoRetornoDTO montarIdOperacaoContaCapital(LancamentoContaCapital lancamento) throws BancoobException{
						
			IntegralizacaoRecursoRetornoDTO dto = new IntegralizacaoRecursoRetornoDTO();
			dto.setIdInstituicao(lancamento.getIdInstituicao());
			dto.setDataLancamento(new SimpleDateFormat("yyyy-MM-dd").format(lancamento.getDataLancamento()));			
			dto.setNumeroLote(lancamento.getTipoLote().getId().intValue());
			dto.setNumeroLancamento(lancamento.getNumSeqLanc());
			/*
			StringBuffer sbf = new StringBuffer();
			sbf.append(lancamento.getIdInstituicao());
			sbf.append("|");				
			sbf.append(new SimpleDateFormat("yyyyMMdd").format(lancamento.getDataLancamento()));
			sbf.append("|");
			sbf.append(lancamento.getTipoLote().getId());
			sbf.append("|");
			sbf.append(lancamento.getNumSeqLanc());
			return sbf.toString();
			*/
			return dto;
		}	
		
		/**
		 * Valida dados do parcelamento para casos de Subscrição > Integralização
		 * A data de início do parcelamento deve estar entre o primeiro e o ultimo dia do mês seguinte da integralização a vista
		 * @param dtoIntegralizacao
		 * @throws BancoobException
		 */
		private void validarParcelamento(IntegralizacaoRecursoDTO dto)throws BancoobException{

			if (!isValorResidualSubscricao(dto)) {
				return;
			}
			
			validarTipoIntegralizacaoModoParcelamento(dto);
			
			DateTimeDB dataIntegralizacao = dto.getDataAtualProduto();
			DateTimeDB dataPrimeiraParcelaMin = new DateTimeDB(new org.joda.time.DateTime(dataIntegralizacao).plusMonths(1).getMillis());
			DateTimeDB dataPrimeiraParcelaMax = new DateTimeDB(new org.joda.time.DateTime(dataPrimeiraParcelaMin).plusMonths(1).getMillis());

			String dataPrimeiraParcelaMinTexto = ContaCapitalUtil.formatarDataMascara(dataPrimeiraParcelaMin,"dd/MM/yyyy");
			String dataPrimeiraParcelaMaxTexto = ContaCapitalUtil.formatarDataMascara(dataPrimeiraParcelaMax,"dd/MM/yyyy");

			if (dto.getDataInicioParcelamento() ==null){
				throw new ContaCapitalAPIRestException("MSG_OBR_A", "A data de início do parcelamento");
			}		
			
			if ((dto.getDataParcelamento().compareTo(dataPrimeiraParcelaMin) < 0) || (dto.getDataParcelamento().compareTo(dataPrimeiraParcelaMax) > 0)){
				String dataIntegralizacaoTexto = ContaCapitalUtil.formatarDataMascara(dto.getDataParcelamento(),"dd/MM/yyyy");				
				throw new ContaCapitalAPIRestException("MSG_ENTRE","A data de início do parcelamento",dataPrimeiraParcelaMinTexto,dataPrimeiraParcelaMaxTexto,"Data informada: "+dataIntegralizacaoTexto);
			}
			
			if (dto.getQuantidadeParcelas() == null){
				throw new ContaCapitalAPIRestException("MSG_OBR_A", "A quantidade de parcelas");
			}
			
			if (dto.getQuantidadeParcelas().intValue() <= 0){
				throw new ContaCapitalAPIRestException("MSG_MAIORQUE", "A quantidade de parcelas","zero");
			}

		}
		
		/**
		 * Montar data do proximo Vencimento
		 * @param idInstituicao
		 * @param dataVencimento
		 * @return
		 * @throws BancoobException
		 */
		private DateTimeDB montarDataProximoVencimento(Integer idInstituicao, DateTimeDB dataVencimento) throws BancoobException{
			DateTimeDB dataProximoVencimento = new DateTimeDB(new org.joda.time.DateTime(dataVencimento).plusMonths(1).getMillis());

			if (!genIntIntegracaoServico.verificarDiaUtil(idInstituicao, dataProximoVencimento)){
				dataProximoVencimento = new DateTimeDB(genIntIntegracaoServico.recuperarProximoDiaUtil(idInstituicao, dataProximoVencimento).getTime());
			}

			return dataProximoVencimento;
		}
		
		/**
		 * Montar data do Vencimento a vista
		 * @param idInstituicao
		 * @param dataVencimento
		 * @return
		 * @throws BancoobException
		 */
		private DateTimeDB montarDataVencimentoAVista(Integer idInstituicao, DateTimeDB dataVencimento) throws BancoobException{

			if (!genIntIntegracaoServico.verificarDiaUtil(idInstituicao, dataVencimento)){
				dataVencimento = new DateTimeDB(genIntIntegracaoServico.recuperarProximoDiaUtil(idInstituicao, dataVencimento).getTime());
			}

			return dataVencimento;
		}	
		
		/**
		 * Monta o valor da última parcela com o valor residual do parcelamento,
		 * esse ajuste é necessário para que o somatório das parcelas não ultrapasse o valor total do parcelamento  
		 * @param listaParcelas
		 * @param valorAParcelar
		 */
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

		/**
		 * Monta o dto para lancamento em conta corrente
		 * @param dto
		 * @return
		 * @throws BancoobException
		 */
		private LancamentoContaCorrenteIntegracaoDTO montarLancamentoCco(IntegralizacaoRecursoDTO dto) throws BancoobException{
			LancamentoContaCorrenteIntegracaoDTO lancDtoCco = new LancamentoContaCorrenteIntegracaoDTO();
	
			lancDtoCco.setDataLote(dto.getDataAtualProduto());
			lancDtoCco.setBolConsideraLimite(false);
			lancDtoCco.setNumLoteLanc(ContaCapitalConstantes.COD_LOTE_PARC_AVISTA_CCA);		
			lancDtoCco.setDescNumDocumento(dto.getNumeroContaCapital().toString());
			lancDtoCco.setNumContaCorrente(dto.getNumeroContaCorrente().longValue());
			lancDtoCco.setValorLanc(dto.getValorIntegralizacao());			
			lancDtoCco.setIdProduto(ContaCapitalConstantes.PRODUTO_CONTA_CORRENTE);
			lancDtoCco.setIdTipoHistoricoLanc(ContaCapitalConstantes.COD_HIST_LANC_CCO);
			lancDtoCco.setIdProdutoEstorno(null);
			lancDtoCco.setIdTipoHistoricoEstorno(null);
			lancDtoCco.setIdUsuarioResp(null); 
			lancDtoCco.setIdAplicativo(1); // TODO SubscricaoContaCapitalServicoEJB.montarLancamentoCco hardcoded
			lancDtoCco.setIdInstituicao(dto.getIdInstituicao());
			lancDtoCco.setBolVerificaContaAnt(true);
			lancDtoCco.setBolVerificaSaldo(true);			
			lancDtoCco.setCodOrigemLote(2); // TODO SubscricaoContaCapitalServicoEJB.montarLancamentoCco hardcoded
			lancDtoCco.setDescInfComplementar(null);		
					
			return lancDtoCco;	
		}


		
	}

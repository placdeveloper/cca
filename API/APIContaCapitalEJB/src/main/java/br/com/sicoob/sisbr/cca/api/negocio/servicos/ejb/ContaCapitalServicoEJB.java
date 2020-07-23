package br.com.sicoob.sisbr.cca.api.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorConfiguracaoCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorCotaDelegate;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.negocio.delegates.ContaCapitalComumFabricaDelegates;
import br.com.sicoob.cca.comum.negocio.delegates.FechamentoContaCapitalDelegate;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.GestaoEmpresarialDelegate;
import br.com.sicoob.cca.movimentacao.negocio.dto.GestaoEmpresarialDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.BloqueioContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.delegates.ContaCapitalRelatoriosFabricaDelegates;
import br.com.sicoob.cca.relatorios.negocio.delegates.RelContaCapitalDelegate;
import br.com.sicoob.cca.relatorios.negocio.dto.RelExtratoRelatorioDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ContaCapitalDIRFDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ContaCapitalInstituicaoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.DadosContaCapitalDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.RelExtratoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.SaldoContaCapitalDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ValorIngressoCapitalDTO;
import br.com.sicoob.sisbr.cca.api.negocio.excecao.APIContaCapitalNegocioException;
import br.com.sicoob.sisbr.cca.api.negocio.excecao.ContaCapitalNaoEncontradaNegocioException;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.ContaCapitalServico;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.interfaces.ContaCapitalServicoRemote;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.CapesIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;


/**
 * EJB contendo servicos relacionados a ContaCapital.
 */
@Stateless
@Local(ContaCapitalServicoLocal.class)
@Remote(ContaCapitalServicoRemote.class)
public class ContaCapitalServicoEJB extends APIContaCapitalServicoEJB implements ContaCapitalServicoLocal, ContaCapitalServicoRemote {

	@EJB
	private br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal contaCapitalCadastroServico;
	
	@EJB
	private BloqueioContaCapitalServicoLocal bloqueioServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@EJB
	private LancamentoContaCapitalServicoLocal lancamentoContaCapitalServico;
	
	/**
	 * {@link ContaCapitalServico#verificarAssociadoContaCapital(Integer, Integer)}
	 */
	public Boolean verificarAssociadoContaCapital(Integer idPessoa,Integer idInstituicao) throws BancoobException {

		try{
			validarCamposObrigatorios(idPessoa, idInstituicao);
			
			List<ContaCapital> lstContaCapital = contaCapitalCadastroServico.obterPorInstituicaoPessoa(idInstituicao, idPessoa);
			
			if (lstContaCapital != null && !lstContaCapital.isEmpty()){
				for (ContaCapital item : lstContaCapital){
					if (item.isSituacaoContaCapitalAtiva()){
						return true;
					}
				}
			}

			return false;
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());		
			throw new APIContaCapitalNegocioException(e.getMessage());			
		}
	}

	/**
	 * validarCamposObrigatorios
	 * @param idPessoa
	 * @param idInstituicao
	 * @throws APIContaCapitalNegocioException
	 */
	private void validarCamposObrigatorios(Integer idPessoa,Integer idInstituicao) throws APIContaCapitalNegocioException{
		if (idPessoa == null){
			throw new APIContaCapitalNegocioException("MSG_001");
		}
		
		if (idInstituicao == null){
			throw new APIContaCapitalNegocioException("MSG_002");
		}	
	}

	/**
	 * {@link ContaCapitalServico#obterSaldoContaCapital(Integer, Integer)}
	 */
	public BigDecimal obterSaldoContaCapital(Integer idPessoa,Integer idInstituicao) throws BancoobException {
		
		try{
			validarCamposObrigatorios(idPessoa, idInstituicao);
			
			BigDecimal saldoIntegralizadoContaCapital = BigDecimal.ZERO;	
			List<ContaCapital> lstContaCapital = contaCapitalCadastroServico.obterPorInstituicaoPessoa(idInstituicao, idPessoa);
			if (lstContaCapital != null && !lstContaCapital.isEmpty()){
				for (ContaCapital item: lstContaCapital){
					saldoIntegralizadoContaCapital = saldoIntegralizadoContaCapital.add(
							lancamentoContaCapitalServico.calcularValorIntegralizado(item.getId()));
				}
			}else{
				throw new ContaCapitalNaoEncontradaNegocioException("MSG_003", idPessoa.toString());
			}
			return saldoIntegralizadoContaCapital;
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());			
			throw new APIContaCapitalNegocioException(e.getMessage());
		}
	}

	/**
	 * {@link ContaCapitalServico#obterMatriculaContaCapital(Integer, Integer)}
	 */
	public Integer obterMatriculaContaCapital(Integer idPessoa,Integer idInstituicao) throws BancoobException {
		try{
			validarCamposObrigatorios(idPessoa, idInstituicao);				
			
			Integer numMatricula = 0;
			List<ContaCapital> lstContaCapital = contaCapitalCadastroServico.obterPorInstituicaoPessoa(idInstituicao, idPessoa);
	
			//Pega a matricula(conta capital) mais antiga, caso mais de uma matricula ativa
			if (lstContaCapital != null && !lstContaCapital.isEmpty()){
				for (ContaCapital item : lstContaCapital){
					if (item.isSituacaoContaCapitalAtiva() && (item.getNumContaCapital() < numMatricula || numMatricula == 0)) {
						numMatricula = item.getNumContaCapital();
					}
				}
			}else{
				throw new ContaCapitalNaoEncontradaNegocioException("MSG_003", idPessoa.toString());
			}
			return numMatricula;
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());			
			throw new APIContaCapitalNegocioException(e.getMessage());
		}
	}

	/**
	 * {@link ContaCapitalServico#gerarFichaMatricula(Integer)}
	 */
	public Object gerarFichaMatricula(Integer numMatricula) throws BancoobException {
		try{
			RelContaCapitalDelegate delegate = ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelContaCapitalDelegate();
			return delegate.gerarFichaMatricula(numMatricula.longValue());
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());			
			throw new APIContaCapitalNegocioException(e.getMessage());
		}
	}
	
	/**
	 * {@link ContaCapitalServico#gerarFichaAdmissao(Integer, Integer)}
	 */
	public Object gerarFichaAdmissao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		try{
			RelContaCapitalDelegate delegate = ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelContaCapitalDelegate();
			Integer idPessoaLegado = obterIdPessoaLegado(idPessoa, idInstituicao);
			if(idPessoaLegado == null) {
				throw new APIContaCapitalNegocioException("MSG_004", idPessoa, idInstituicao);
			}
			getLogger().info("CCA usuarioLegado:" + idPessoaLegado.intValue());
			return delegate.gerarFichaAdmissao(idPessoaLegado);
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());			
			throw new APIContaCapitalNegocioException(e.getMessage());
		}
	}

	/**
	 * {@link ContaCapitalServico#obterSaldosContaCapital(Integer, Integer)}
	 */
	public SaldoContaCapitalDTO obterSaldosContaCapital(Integer idPessoa, Integer idInstituicao) throws BancoobException {

		try{
			SaldoContaCapitalDTO saldoDTO = new SaldoContaCapitalDTO();
			
			validarCamposObrigatorios(idPessoa, idInstituicao);				
			Integer idPessoaLegado = obterIdPessoaLegado(idPessoa,idInstituicao);
			
			BigDecimal saldoIntegralizadoContaCapital = BigDecimal.ZERO;
			BigDecimal saldoSubscritoContaCapital = BigDecimal.ZERO;
			BigDecimal saldoDevolverContaCapital = BigDecimal.ZERO;
			BigDecimal saldoBloqueadoContaCapital = BigDecimal.ZERO;	
			Integer numMatricula = 0;
			
			List<ContaCapital> lstContaCapital = contaCapitalCadastroServico.obterPorInstituicaoPessoa(idInstituicao, idPessoa);
			
			if (lstContaCapital != null && !lstContaCapital.isEmpty()){
				for (ContaCapital item: lstContaCapital){
					saldoIntegralizadoContaCapital = saldoIntegralizadoContaCapital.add(lancamentoContaCapitalServico.calcularValorIntegralizado(item.getId()));
					saldoSubscritoContaCapital = saldoSubscritoContaCapital.add(lancamentoContaCapitalServico.calcularValorSubscrito(item.getId()));
					saldoDevolverContaCapital = saldoDevolverContaCapital.add(lancamentoContaCapitalServico.calcularValorDevolucao(item.getId()));
					saldoBloqueadoContaCapital = saldoBloqueadoContaCapital.add(bloqueioServico.calcularValorBloqueado(item.getId()));
					
					if (item.getNumContaCapital() < numMatricula || numMatricula == 0) {
						numMatricula = item.getNumContaCapital();					
					}						
				}

				saldoDTO.setNumMatricula(numMatricula);
				saldoDTO.setNumCliente(idPessoaLegado);
				saldoDTO.setValorSaldoSubscrito(saldoSubscritoContaCapital);
				saldoDTO.setValorSaldoIntegralizado(saldoIntegralizadoContaCapital);
				saldoDTO.setValorSaldoRealizar(saldoSubscritoContaCapital.subtract(saldoIntegralizadoContaCapital));
				saldoDTO.setValorSaldoBloqueado(saldoBloqueadoContaCapital);
				saldoDTO.setValorSaldoDevolver(saldoDevolverContaCapital);

			}else{
				throw new ContaCapitalNaoEncontradaNegocioException("MSG_003", idPessoa.toString());
			}

			return saldoDTO;
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());			
			throw new APIContaCapitalNegocioException(e.getMessage());
		}
	}
	/**
	 * {@link ContaCapitalServico#gerarFichaMatricula(relExtratoDTO)}
	 */
	public Object gerarExtrato(RelExtratoDTO relExtratoDTO) throws BancoobException {
		try{
			RelContaCapitalDelegate delegate = ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelContaCapitalDelegate();			
			return delegate.gerarExtrato(montarExtratoDTO(relExtratoDTO));
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());			
			throw new APIContaCapitalNegocioException(e.getMessage());
		}
	}
	
	/**
	 * {@link ContaCapitalServico#gerarExtratoHTML(relExtratoDTO)}
	 */
	public String gerarExtratoHTML(RelExtratoDTO relExtratoDTO) throws BancoobException {
		try{
			RelContaCapitalDelegate delegate = ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelContaCapitalDelegate();			
			return delegate.gerarExtratoHTML(montarExtratoDTO(relExtratoDTO));
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());			
			throw new APIContaCapitalNegocioException(e.getMessage());
		}
	}
	
	/**
	 * Montar extrato dto.
	 *
	 * @param dtoEntrada o valor de dto entrada
	 * @return RelExtratoRelatorioDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private RelExtratoRelatorioDTO montarExtratoDTO(RelExtratoDTO dtoEntrada) throws BancoobException{
				
		RelExtratoRelatorioDTO dtoSaida = new RelExtratoRelatorioDTO();
		dtoSaida.setNumMatricula(dtoEntrada.getNumMatricula());
		dtoSaida.setDataFim(dtoEntrada.getDataFim());
		dtoSaida.setDataInicio(dtoEntrada.getDataInicio());
		
		return dtoSaida;
	}
			
	/**
	 * @see br.com.sicoob.sisbr.cca.api.negocio.servicos.ContaCapitalServico#obterDadosContaCapital(java.lang.Integer, java.lang.Integer)
	 */
	public DadosContaCapitalDTO obterDadosContaCapital(Integer idPessoa,Integer idInstituicao) throws BancoobException {		
		try{			
			
			validarCamposObrigatorios(idPessoa, idInstituicao);				
						
			DadosContaCapitalDTO dadosContaCapitalDTO = null;
			Integer numMatricula = 0;			
			List<ContaCapital> lstContaCapital = contaCapitalCadastroServico.obterPorInstituicaoPessoa(idInstituicao, idPessoa);
	
			//Pega a matricula(conta capital) mais antiga, caso mais de uma matricula ativa
			if (lstContaCapital != null && !lstContaCapital.isEmpty()){
				for (ContaCapital item: lstContaCapital){
					if (item.isSituacaoContaCapitalAtiva() && (item.getNumContaCapital() < numMatricula || numMatricula == 0)) {
						numMatricula = item.getNumContaCapital();					
						dadosContaCapitalDTO = montarDadosContaCapitalDTO(item);
					}
				}
			}else{
				throw new ContaCapitalNaoEncontradaNegocioException("MSG_003",idPessoa.toString());
			}
			
			return dadosContaCapitalDTO;
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());			
			throw new APIContaCapitalNegocioException(e.getMessage());
		}
	}			
	
	/**
	 * Montar dados conta capital dto.
	 *
	 * @param dtoEntrada o valor de dto entrada
	 * @return DadosContaCapitalDTO
	 */
	private DadosContaCapitalDTO montarDadosContaCapitalDTO(ContaCapital dtoEntrada){
		
		DadosContaCapitalDTO dtoSaida = new DadosContaCapitalDTO();
		dtoSaida.setNumMatricula(dtoEntrada.getNumContaCapital());
		dtoSaida.setDataMatricula(dtoEntrada.getDataMatricula());
		dtoSaida.setCodSituacao(dtoEntrada.getSituacaoContaCapital().getId().intValue());
		
		return dtoSaida;
	}
	
	/**
	 * {@link ContaCapitalServico#verificarFechamentoContaCapital(Integer)}
	 */
	public Boolean verificarFechamentoContaCapital(Integer numCoop) throws BancoobException {
		FechamentoContaCapitalDelegate fechamentoDelegate = null;
		
		try {
			
			fechamentoDelegate = ContaCapitalComumFabricaDelegates.getInstance().criarFechamentoContaCapitalDelegate();
			
			return fechamentoDelegate.isFechamentoIniciado(numCoop);
			
		} catch (BancoobException be) {
			this.getLogger().erro(be, be.getMessage());			
			throw new APIContaCapitalNegocioException(be.getMessage());
		}
	}

	/**
	 * {@link ContaCapitalServico#gerarExtratoDIRF(List, Date, Date)}
	 */
	public List<ContaCapitalDIRFDTO> gerarExtratoDIRF(List<Integer> idInstituicao, Date dataInicio, Date dataFim) throws BancoobException {
		List<ContaCapitalDIRFDTO> lstSaida = new ArrayList<ContaCapitalDIRFDTO>();
 		
		try {
			
			GestaoEmpresarialDelegate geDeleate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarGestaoEmpresarialDelegate();
	 		List<GestaoEmpresarialDTO> lstGestaoEmpresarial = geDeleate.gerarExtratoDIRF(idInstituicao, dataInicio, dataFim);
	 		
	 		ContaCapitalDIRFDTO dto;
	 		for(GestaoEmpresarialDTO gestaoEmpresarial : lstGestaoEmpresarial) {
	 			dto = new ContaCapitalDIRFDTO();
	 			dto.setIdInstituicao(gestaoEmpresarial.getIdInstituicao());
	 			dto.setNumPac(gestaoEmpresarial.getNumPac());
	 			dto.setCodPFPJ(gestaoEmpresarial.getCodPFPJ());
	 			dto.setBaseIRRF(gestaoEmpresarial.getBaseIRRF());
	 			dto.setValorIRRF(gestaoEmpresarial.getValorIRRF());
	 			dto.setDataInicio(gestaoEmpresarial.getDataInicio());
	 			dto.setDataFim(gestaoEmpresarial.getDataFim());
	 			dto.setIdProduto(gestaoEmpresarial.getIdProduto());
	 			dto.setNomeProduto(gestaoEmpresarial.getNomeProduto());
	 			lstSaida.add(dto);
			}
	 		
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			lancarAPIContaCapitalNegocioException(e.getMessage());
		}
 		
		return lstSaida;
	}

	/**
	 * {@link ContaCapitalServico#obterValorIngressoCapital(Integer)}
	 */
	public ValorIngressoCapitalDTO obterValorIngressoCapital(Integer idInstituicao) throws BancoobException {
		if(idInstituicao == null) {
			return null;
		}
		
		ValorConfiguracaoCapitalDelegate vlrConfDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorConfiguracaoCapitalDelegate();
		
		ValorCotaDelegate vlrCotaDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorCotaDelegate();
		
		ValorIngressoCapitalDTO vlrIngresso = new ValorIngressoCapitalDTO();
		vlrIngresso.setIdInstituicao(idInstituicao);
		vlrIngresso.setValorCapitalIngresso(vlrCotaDelegate.obterValorMinimoSubscricao(idInstituicao, null));
		
		BigDecimal vlrCapitalMensalFixo = new BigDecimal(vlrConfDelegate.obterValorConfiguracao(ContaCapitalConstantes.VLR_INTEG_MENSAL_FIXO, idInstituicao).getValorConfiguracao());
		vlrIngresso.setValorCapitalMensalFixo(vlrCapitalMensalFixo);
		
		BigDecimal vlrCapitalMensalPerc = new BigDecimal(vlrConfDelegate.obterValorConfiguracao(ContaCapitalConstantes.VLR_INTEG_MENSAL_PERC, idInstituicao).getValorConfiguracao());
		vlrIngresso.setValorCapitalMensalPercentual(vlrCapitalMensalPerc);
		
		return vlrIngresso;
	}
	
	/**
	 * {@link ContaCapitalServico#obterContaCapitalPorCpfCnpj(String)}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ContaCapitalInstituicaoDTO> obterContaCapitalPorCpfCnpj(String cpfCnpj) throws BancoobException {
		List<ContaCapitalInstituicaoDTO> lstRetorno = new ArrayList<ContaCapitalInstituicaoDTO>(0);
		
		try {
			
			if(cpfCnpj != null && cpfCnpj.length() > 0) {
				
				CapesIntegracaoDelegate capesInt = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarCapesIntegracaoDelegate();
				List<PessoaIntegracaoDTO> lstPessoas = capesInt.obterPessoaPorCpfCnpj(cpfCnpj);
				
				if(lstPessoas != null && !lstPessoas.isEmpty()) {
					
					ContaCapitalDelegate ccaDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate();
					List<CadastroContaCapitalRenDTO> lstCCA = ccaDelegate.obterContaCapitalPorPessoa(lstPessoas);
					
					if(lstCCA != null && !lstCCA.isEmpty()) {
						
						ContaCapitalInstituicaoDTO dto = null;
						for(CadastroContaCapitalRenDTO cca : lstCCA) {
							dto = new ContaCapitalInstituicaoDTO();
							dto.setIdInstituicao(cca.getIdInstituicao());
							dto.setIdPessoa(cca.getIdPessoa());
							dto.setIdContaCapital(cca.getIdContaCapital());
							dto.setDataMatricula(cca.getDataMatricula());
							dto.setNumCoop(cca.getNumCoop());
							dto.setNumContaCapital(cca.getNumContaCapital());
							
							lstRetorno.add(dto);
						}
					}
				}
			}
			
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			lancarAPIContaCapitalNegocioException(e.getMessage());
		}
		
		return lstRetorno;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.api.negocio.servicos.ContaCapitalServico#obterValorBloqueado(java.lang.Integer, java.lang.Integer)
	 */
	public BigDecimal obterValorBloqueado(Integer numCooperativa, Integer numMatricula) throws BancoobException {
		Integer idInstituicao = instituicaoIntegracaoServico.obterIdInstituicao(numCooperativa);
		ContaCapital cca = contaCapitalCadastroServico.obterPorInstituicaoMatricula(idInstituicao, numMatricula);
		if (cca == null) {
			return BigDecimal.ZERO;
		}
		return bloqueioServico.calcularValorBloqueado(cca.getId());
	}
}
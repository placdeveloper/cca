package br.com.sicoob.sisbr.cca.api.negocio.servicos.ejb;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.excecao.CadastroContaCapitalNegocioException;
import br.com.sicoob.cca.cadastro.negocio.excecao.ContaCapitalCadastradaNegocioException;
import br.com.sicoob.cca.cadastro.negocio.excecao.ContaCapitalCadastroNegocioException;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.PropostaSubscricaoServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.PropostaSubscricao;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.sisbr.cca.api.negocio.dto.CadastroContaCapitalDTO;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.interfaces.CadastroContaCapitalServicoLocal;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.interfaces.CadastroContaCapitalServicoRemote;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.HistContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.HistContaCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.HistContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.persistencia.ContaCapitalIntegracaoLegadoDataSource;

/**
 * @author Marcos.Balbi
 * Cadastro de Conta Capital
 *  
 */
@Stateless
@Local (CadastroContaCapitalServicoLocal.class)
@Remote(CadastroContaCapitalServicoRemote.class) 
public class CadastroContaCapitalServicoEJB extends	APIContaCapitalServicoEJB implements CadastroContaCapitalServicoLocal, CadastroContaCapitalServicoRemote {
	
	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;
	
	@EJB
	private PropostaSubscricaoServicoLocal propostaServico;
	
	@EJB
	private ContaCapitalLegadoServicoLocal contaCapitalLegadoServico;
	
	@EJB
	private HistContaCapitalLegadoServicoLocal histContaCapitalLegadoServico;
	
	@EJB
	private CapesIntegracaoServicoLocal capesServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@EJB
	private ProdutoLegadoServicoLocal prodLegadoServico;	
	
	private static final String RET_ERRO = "Erro ao cadastrar a Conta Capital. ";
	
	/**
	 * @see br.com.sicoob.sisbr.cca.api.negocio.servicos.CadastroContaCapitalServico#cadastrarContaCapital(br.com.sicoob.sisbr.cca.api.negocio.dto.CadastroContaCapitalDTO)
	 */
	public Integer cadastrarContaCapital(CadastroContaCapitalDTO dto)throws BancoobException {
				
		try{

			validarObrigatoriosCadastro(dto);
			ContaCapitalIntegracaoLegadoDataSource.definirNumeroCooperativa(instituicaoIntegracaoServico.obterNumeroCooperativa(dto.getIdInstituicao()));
			
			ContaCapital contaCapital = contaCapitalServico.incluirExterno(criarContaCapital(dto));
			
			PropostaSubscricao proposta = propostaServico.incluirExterno(criarPropostaSubscricao(contaCapital));			
			
			ContaCapitalLegado contaCapitalLegado = criarContaCapitalLegado(proposta);
			contaCapitalLegadoServico.incluir(contaCapitalLegado);
			
			histContaCapitalLegadoServico.incluir(criarHistContaCapitalLegado(contaCapitalLegado));
			
			return contaCapital.getNumContaCapital();
			
		}catch (ContaCapitalCadastradaNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			lancarAPIContaCapitalNegocioException(e.getMessage());
		}catch (CadastroContaCapitalNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			lancarAPIContaCapitalNegocioException(e.getMessage());
		}catch (ContaCapitalCadastroNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			lancarAPIContaCapitalNegocioException(e.getMessage());
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			lancarAPIContaCapitalNegocioException(RET_ERRO+e.getMessage());
		}
		return null;
	}
	
	/**
	 * Validar Obrigatorios para o Cadastro
	 * @return
	 * @throws BancoobException
	 */
	private void validarObrigatoriosCadastro(CadastroContaCapitalDTO dto) throws BancoobException{
		
		if (dto.getIdPessoa() == null ){
			throw new CadastroContaCapitalNegocioException("MSG_OBR","O Cliente Capes");
		}		

		if (dto.getIdInstituicao() == null ){
			throw new CadastroContaCapitalNegocioException("MSG_OBR","A Instituição SCI");
		}	
			
	}
	
	/**
	 * Historico Conta Capital legado (SQL Server)
	 * @param contaCapitalLegado
	 * @return
	 * @throws BancoobException
	 */
	private HistContaCapitalLegado criarHistContaCapitalLegado(ContaCapitalLegado contaCapitalLegado) throws BancoobException {
		HistContaCapitalLegado hist = new HistContaCapitalLegado();
		
		HistContaCapitalLegadoPK histPK = new HistContaCapitalLegadoPK();
		histPK.setContaCapitalLegado(contaCapitalLegado);
		histPK.setDataOcorrencia(new DateTimeDB());
		hist.setHistContaCapitalLegadoPK(histPK);
		
		hist.setCodSituacao(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_ATIVO);
		hist.setBolAtualizado(true);
				
		hist.setNumCoop(contaCapitalLegado.getNumCoop());
		
		return hist;
	}
	
	/**
	 * Conta Capital legado (SQL Server)
	 * @param contaCapitalLegado
	 * @return
	 * @throws BancoobException
	 */
	private ContaCapitalLegado criarContaCapitalLegado(PropostaSubscricao proposta) throws BancoobException {
		ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();
		
		ContaCapital cca = contaCapitalServico.obter(proposta.getId());
		
		
		PessoaIntegracaoDTO pessoaIntegracao = capesServico.obterPessoaInstituicao(cca.getIdPessoa(), cca.getIdInstituicao());
		contaCapitalLegado.setNumCliente(pessoaIntegracao.getIdPessoaLegado());
		
		contaCapitalLegado.setNumContaCorrente(null);
		contaCapitalLegado.setNumMatricula(cca.getNumContaCapital());
		contaCapitalLegado.setDataMatricula(obterDataProduto(cca.getIdInstituicao()));
		contaCapitalLegado.setDataSaldoAnterior(new DateTimeDB());
		contaCapitalLegado.setValorSaldoAtuInteg(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoAtuSubsc(BigDecimal.ZERO);	
		contaCapitalLegado.setValorSaldoAtuDevolver(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoAntInteg(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoAntSubsc(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoAntDevolver(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoMedRealAcu(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoMedPosAcu(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoAtuDivs(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoAntDivs(BigDecimal.ZERO);
		contaCapitalLegado.setCodSituacao(cca.getSituacaoContaCapital().getId().intValue());
		contaCapitalLegado.setBolDebIndeterminado(Boolean.FALSE);
		contaCapitalLegado.setValorDeb(BigDecimal.ZERO);
		contaCapitalLegado.setBolParticipaRateio(Boolean.TRUE);
		contaCapitalLegado.setBolDireitoVoto(Boolean.TRUE);
		contaCapitalLegado.setBolRestricaoRateio(Boolean.FALSE);
		contaCapitalLegado.setValorSaldoMedADevAcu(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoBloqInt(BigDecimal.ZERO);
		contaCapitalLegado.setValorSaldoIntegralDispAcum(BigDecimal.ZERO);
		
		contaCapitalLegado.setNumCoop(instituicaoIntegracaoServico.obterNumeroCooperativa(cca.getIdInstituicao()));
		
		contaCapitalLegado.setIdContaCapital(cca.getId());
		
		return contaCapitalLegado;
	}
	
	/**
	 * Cria Conta Capital com dados da camada de apresentacao
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	private ContaCapital criarContaCapital(CadastroContaCapitalDTO dto) throws BancoobException {
		
		Integer numMatricula = contaCapitalLegadoServico.obterUltimaMatricula()+1;
		
		ContaCapital contaCapital = new ContaCapital();
		contaCapital.setDataHoraAtualizacao(new DateTimeDB());
		contaCapital.setDataMatricula(obterDataProduto(dto.getIdInstituicao()));
		contaCapital.setIdInstituicao(dto.getIdInstituicao());
		contaCapital.setIdPessoa(dto.getIdPessoa());
		contaCapital.setIdUsuario(ContaCapitalConstantes.USR_EXTERNO_SISBR);
		contaCapital.setMatriculaEscolhida(Boolean.FALSE);
		contaCapital.setNumContaCapital(numMatricula);
		contaCapital.setValorBloq(BigDecimal.ZERO);
		contaCapital.setValorDevol(BigDecimal.ZERO);
		contaCapital.setValorInteg(BigDecimal.ZERO);
		contaCapital.setValorSubs(BigDecimal.ZERO);
		
		SituacaoContaCapital situacaoConta = new SituacaoContaCapital();
		situacaoConta.setId(EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo().shortValue());
		contaCapital.setSituacaoContaCapital(situacaoConta);
		
		SituacaoCadastroProposta situacaoCadastroProposta = new SituacaoCadastroProposta();
		situacaoCadastroProposta.setId(EnumSituacaoCadastroProposta.COD_APROVADO.getCodigo());
		contaCapital.setSituacaoCadastroProposta(situacaoCadastroProposta);
		
		return contaCapital;
	}
	
	/**
	 * Cria proposta de subscricao
	 * @param contaCapital
	 * @return
	 */
	private PropostaSubscricao criarPropostaSubscricao(ContaCapital contaCapital) throws BancoobException{
		PropostaSubscricao proposta = new PropostaSubscricao();
		
		BigDecimal valorSubscInteg = BigDecimal.ZERO; 
		
		proposta.setDataHoraAtualizacao(new DateTimeDB());
		proposta.setDiaDebitoProposta(1);
		proposta.setId(contaCapital.getId());
		proposta.setIdUsuario(ContaCapitalConstantes.USR_EXTERNO_SISBR);
		
		proposta.setQtdParcelaProposta(0);
		proposta.setValorIntegProposta(valorSubscInteg);
		proposta.setValorParcelaProposta(BigDecimal.ZERO);
		proposta.setValorSubsProposta(valorSubscInteg);
		proposta.setBolSubscricaoProposta(1);
		
		TipoIntegralizacao tipoIntegralizacao = new TipoIntegralizacao();
		tipoIntegralizacao.setId(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getCodigo().shortValue());
		proposta.setTipoIntegralizacao(tipoIntegralizacao);

		return proposta;
	}

	/**
	 * Data Atual do Produto
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	private DateTimeDB obterDataProduto(Integer idInstituicao) throws BancoobException{
		return new DateTimeDB(prodLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, idInstituicao).getTime());
	}	
	
}


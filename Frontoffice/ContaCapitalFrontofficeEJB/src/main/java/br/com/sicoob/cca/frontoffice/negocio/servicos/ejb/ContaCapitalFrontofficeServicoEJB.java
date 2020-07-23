package br.com.sicoob.cca.frontoffice.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.negocio.servicos.ejb.BancoobServicoEJB;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.RetornoMensagem;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.interceptors.TransacaoInterceptor;
import br.com.bancoob.srtb.montagemconteudo.objeto.MontagemConteudoRetornoObjeto;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.Resultado;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.frontoffice.negocio.excecao.ContaCapitalFrontofficeNegocioException;
import br.com.sicoob.cca.frontoffice.negocio.servicos.ContaCapitalFrontofficeServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
 * Implementação abstrata do Frontoffice do Conta Capital.
 * @author Nairon.Silva
 * @param <D> DTO de entrada do serviço, que receberá os parametros da mensagem
 * @param <R> DTO de saída do serviço, que será mapeado com os atributos de retorno
 */
public abstract class ContaCapitalFrontofficeServicoEJB<D extends BancoobDto, R extends BancoobDto> extends BancoobServicoEJB implements ContaCapitalFrontofficeServico {

	@EJB
	private CapesIntegracaoServicoLocal capesIntegracaoServico;
	
	@EJB
	private ContaCapitalLegadoServicoLocal contaCapitalLegadoServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@EJB
	private ProdutoLegadoServicoLocal prodLegadoServico;
	
	/** Código de retorno de erro. */
	protected static final int CODIGO_RETORNO_ERRO = 0;

	/** Código de retorno de sucesso. */
	protected static final int CODIGO_RETORNO_SUCESSO = 1;

	/**
	 * Método principal de execução da transação. Responsável por chamar os métodos abstratos que devem 
	 * ser implementados pelos serviços.
	 * @param mensagem
	 * @return
	 * @throws ExcecaoTransacao
	 */
	@Interceptors(TransacaoInterceptor.class)
	public RetornoMensagem executarTransacao(Mensagem mensagem) throws ExcecaoTransacao {
		RetornoMensagem retorno = null;
		try {
			getLogger().info(getNomeTransacao());
			getLogger().info("Recuperando parametros de entrada.");
			D dto = criarDTO(mensagem);
			getLogger().info("Parametros de entrada recuperados. Executando transacao.");
			RetornoTransacaoObjeto retornoTransacao = executarTransacao(dto, mensagem);
			getLogger().info("Transacao executada com sucesso");
			retorno = obterRetornoMensagemSucesso(retornoTransacao);
		} catch (NegocioException e) {
			getLogger().erro(e, "Erro de negócio");
			informarRollBack();
			retorno = tratamentoErro(e, RetornoMensagem.ERRO_NEGOCIO);
		} catch (BancoobException e) {
			getLogger().erro(e, "Erro de execução");
			informarRollBack();
			retorno = tratamentoErro(e, RetornoMensagem.ERRO_EXECUCAO);
		} catch (ExcecaoTransacao e) {
			getLogger().erro(e, "Erro de transação");
			retorno = tratamentoErro(e, RetornoMensagem.ERRO_EXECUCAO);
			throw e;
		} catch (PersistenciaException e) {
			getLogger().erro(e, "Erro de persistência");
			retorno = tratamentoErro(e, RetornoMensagem.ERRO_EXECUCAO);
			throw new ExcecaoTransacao(retorno.getMensagem(), e);
		}
		return retorno;
	}

	/**
	 * Monta um objeto RetornoMensagem para situações de sucesso na transação.
	 * 
	 * @param retornoTransacao O objeto de retorno da transacao. 
	 * @param montagem O objeto responsável pela montagem do conteúdo de retorno.
	 * 
	 * @return um objeto RetornoMensagem para situações de sucesso na transação.
	 * @throws ExcecaoTransacao Caso o objeto de montagem não seja uma instância de MontagemConteudoRetornoObjeto
	 */
	private RetornoMensagem obterRetornoMensagemSucesso(RetornoTransacaoObjeto retornoTransacao) {
		getLogger().info("Conta Capital - Gerando conteudo de retorno...");
		MontagemConteudoRetornoObjeto montagem = new MontagemConteudoRetornoObjeto();
		montagem.setRetornoTransacao(retornoTransacao);

		RetornoMensagem retorno = montagem.criarConteudoRetorno();
		retorno.setSucesso(true);
		retorno.setCodRetorno(CODIGO_RETORNO_SUCESSO);

		getLogger().info("Conta Capital - Conteudo gerado:\n ", retorno.getConteudoRetorno());
		return retorno;
	}

	/**
	 * Caso exista contexto transacional e ocorra alguma exceção devido a dupla
	 * persistencia SQL e DB2 será forçado o contexto a notificar para rollback.
	 */
	private void informarRollBack() {
		if (this.getSessionContext() != null) {
			this.getSessionContext().setRollbackOnly();
		}
	}

	/**
	 * Tratamento padrão para exceção quanto aos tipos NegocioException ou BancoobException.
	 * 
	 * @param e Exceção levantada na exceção
	 * @param erro Tipo do erro se de Negocio ou Bancoob.
	 * @return
	 */
	private RetornoMensagem tratamentoErro(Exception e, Integer tipoErro) {
		String mensagemErro = recuperaMensagemErro(e);
		imprimirLogger(e, tipoErro, mensagemErro);
		return obterRetornoMensagemErro(mensagemErro, tipoErro);
	}

	/**
	 * Recuperar a mensagem de erro para logger.
	 * @param e a excecao
	 * @return a mensagem de erro
	 */
	private String recuperaMensagemErro(Exception e) {
		if (e.getMessage() != null) {
			return  e.getMessage();
		} else if(e.getCause() != null) {
			return e.getCause().toString();
		} 
		return "ERRO AO EXECUTAR TRANSACAO";
	}

	/**
	 * Imprime log de erro.
	 * @param e
	 * @param tipoErro
	 * @param mensagemErro
	 */
	private void imprimirLogger(Exception e, Integer tipoErro, String mensagemErro) {
		StringBuilder mensagemLogger = new StringBuilder();
		mensagemLogger.append(" - Classe excecao: ");
		mensagemLogger.append(e.getClass().getName());
		mensagemLogger.append(" - Tipo Erro: ");
		mensagemLogger.append(tipoErro);
		mensagemLogger.append(" - Exception na Transacao do Conta Capital: ");
		mensagemLogger.append(mensagemErro);

		if (RetornoMensagem.ERRO_NEGOCIO.equals(tipoErro)) {
			getLogger().info(mensagemLogger.toString());
		} else if (RetornoMensagem.ERRO_EXECUCAO.equals(tipoErro)) {
			getLogger().erro(e, mensagemLogger.toString());
		}
	}

	/**
	 * Monta um objeto RetornoMensagem para situações de erro na transação.
	 * 
	 * @param mensagemErro
	 *            A mensagem do erro.
	 * @param tipoErro
	 *            O tipo de erro. Ex: 
	 *            RetornoMensagem.ERRO_NEGOCIO
	 *            RetornoMensagem.ERRO_EXECUCAO
	 * @param campoErro 
	 * @return um objeto RetornoMensagem para situações de erro na transação.
	 */
	private RetornoMensagem obterRetornoMensagemErro(String mensagemErro, Integer tipoErro) {
		RetornoMensagem retorno = new RetornoMensagem();
		retorno.setSucesso(false);
		retorno.setCodRetorno(CODIGO_RETORNO_ERRO);
		retorno.setMensagem(mensagemErro);
		retorno.setTipoErro(tipoErro);
		return retorno;
	}
	
	/**
	 * Cria uma nova instância de {@link Resultado} com o DTO de saída
	 * @return
	 */
	protected Resultado<R> criarResultado() {
		return new Resultado<R>();
	}
	
	/**
	 * Cria o {@link RetornoTransacaoObjeto} a partir de um {@link Resultado}
	 * @param resultado
	 * @return
	 */
	protected RetornoTransacaoObjeto criarRetornoTransacao(Resultado<R> resultado) {
		RetornoTransacaoObjeto retornoTransacao = new RetornoTransacaoObjeto();
		retornoTransacao.add(resultado);
		return retornoTransacao;
	}
	
	/**
	 * Cria o {@link RetornoTransacaoObjeto} a partir de uma lista de {@link Resultado}
	 * @param resultados
	 * @return
	 */
	protected RetornoTransacaoObjeto criarRetornoTransacao(List<Resultado<? extends BancoobDto>> resultados) {
		RetornoTransacaoObjeto retornoTransacao = new RetornoTransacaoObjeto();
		for (Resultado<? extends BancoobDto> resultado : resultados) {
			retornoTransacao.add(resultado);
		}
		return retornoTransacao;
	}

	/**
	 * Retorna o nome da transacao. Usado para log.
	 * @return
	 */
	protected abstract String getNomeTransacao();
	
	/**
	 * Método a ser implementado pelos serviços. Recebe o DTO de entrada (traduzido) e a mensagem original.
	 * @param dto
	 * @param mensagem
	 * @return
	 * @throws BancoobException
	 * @throws ExcecaoTransacao
	 */
	protected abstract RetornoTransacaoObjeto executarTransacao(D dto, Mensagem mensagem) throws BancoobException, ExcecaoTransacao;

	/**
	 * Método de criação do DTO de entrada. Os serviços devem implementar a tradução de {@link Mensagem} para um 
	 * DTO específico do serviço.
	 * @param mensagem
	 * @return
	 * @throws BancoobException
	 */
	protected abstract D criarDTO(Mensagem mensagem) throws BancoobException;

	/**
	 * Obtem o objeto {@link PessoaIntegracaoDTO} pelo cpf/cnpj e idInstituicao
	 * @param numCpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	protected PessoaIntegracaoDTO obterPessoaIntegracaoPorCpfCnpjInstituicao(String numCpfCnpj, Integer idInstituicao) throws BancoobException {
		getLogger().info("[integracao capes] - obterPessoaIntegracaoPorCpfCnpjInstituicao");
		PessoaIntegracaoDTO dto = capesIntegracaoServico.obterPorCpfCnpjInstituicao(numCpfCnpj, idInstituicao);
		if (dto == null) {
			throw new ContaCapitalFrontofficeNegocioException("msg.integracao.capes.pessoa.nao.encontrada");
		}
		return dto;
	}
	
	/**
	 * Obtem o objeto {@link PessoaIntegracaoDTO} pelo idPessoa e idInstituicao
	 * @param numCpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	protected PessoaIntegracaoDTO obterPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		getLogger().info("[integracao capes] - obterPessoaInstituicao");
		PessoaIntegracaoDTO dto = capesIntegracaoServico.obterPessoaInstituicao(idPessoa, idInstituicao);
		if (dto == null) {
			throw new ContaCapitalFrontofficeNegocioException("msg.integracao.capes.pessoa.nao.encontrada");
		}
		return dto;
	}
	
	/**
	 * Valida se a conta capital é do cliente/instituicao informado e retorna a conta mais antiga
	 * @param numCooperativa
	 * @param numCliente
	 * @return Retorna a Conta Capital Ativa mais Antiga
	 * @throws BancoobException
	 */
	protected ContaCapitalLegado obterContaCapitalClienteLegado(Integer numCooperativa, Integer numCliente) throws BancoobException {
		getLogger().info("obterContaCapitalClienteLegado");
		ContaCapitalLegado contaCapitalLegado = null;
		
		List<ContaCapitalLegado> listContaCapitalLegado = obterContaCapitalCooperativaCliente(numCooperativa, numCliente, null);
		
		if (listContaCapitalLegado == null || listContaCapitalLegado.isEmpty()){
			throw new ContaCapitalFrontofficeNegocioException("msg.integracao.cca.nao.encontrada");		
		}
		
		for (ContaCapitalLegado item : listContaCapitalLegado) {
			if (item.getCodSituacao() == 1) {
				contaCapitalLegado = item;
				break;
			}
		}
		
		if (contaCapitalLegado == null){
			throw new ContaCapitalFrontofficeNegocioException("msg.integracao.cca.nao.ativo");	
		}
		
		return contaCapitalLegado;
	}
	
	/**
	 * Consulta a ContaCapital através do numero da Pessoa no Legado e da Cooperativa
	 * @param numCooperativa
	 * @param numCliente
	 * @param situacao (1 = ativo, 2 = desligados, null = todos)
	 * @return
	 * @throws BancoobException
	 */
	private List<ContaCapitalLegado> obterContaCapitalCooperativaCliente(
			Integer numCooperativa, Integer numCliente, Integer situacao) throws BancoobException {
		return contaCapitalLegadoServico.obterContaCapitalCooperativaCliente(numCooperativa, numCliente,situacao);
	}
	
	/**
	 * Consulta o numero da cooperativa pelo numero da instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	protected Integer obterNumeroCooperativa(Integer idInstituicao) throws BancoobException {
		getLogger().info("[integracao sci] - obterNumeroCooperativa");
		return instituicaoIntegracaoServico.obterNumeroCooperativa(idInstituicao);
	}
	
	/**
	 * Consulta informacoes de instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	protected InstituicaoIntegracaoDTO obterInstituicaoIntegracaoDTO(Integer idInstituicao) throws BancoobException {
		getLogger().info("[integracao sci] - obterInstituicaoIntegracaoDTO");
		return instituicaoIntegracaoServico.obterInstituicaoIntegracao(idInstituicao);
	}
	
	/**
	 * Data Atual do Lote de Conta Capital
	 * Atenção: Retorna a data do SQL.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return DateTimeDB
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected DateTimeDB obterDataLote(Integer idInstituicao) throws BancoobException{
		return new DateTimeDB(prodLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, idInstituicao).getTime());
	}
	
}

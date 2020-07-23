package br.com.sicoob.cca.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.cadastro.negocio.dto.ContaCapitalResumoDTO;
import br.com.sicoob.cca.cadastro.negocio.dto.DadosDesligamentoDTO;
import br.com.sicoob.cca.cadastro.negocio.dto.FiltroContaCapitalDTO;
import br.com.sicoob.cca.cadastro.negocio.excecao.CadastroContaCapitalNegocioException;
import br.com.sicoob.cca.cadastro.negocio.excecao.ContaCapitalCadastradaNegocioException;
import br.com.sicoob.cca.cadastro.negocio.excecao.ContaCapitalCadastroNegocioException;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoRemote;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ValorConfiguracaoCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDaoIF;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroDaoFactory;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ContaCapitalDao;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.negocio.delegates.ContaCapitalComumFabricaDelegates;
import br.com.sicoob.cca.comum.negocio.delegates.FechamentoContaCapitalDelegate;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.HistoricoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.HistoricoContaCapitalPK;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoCadastroProposta;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.GftIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ContaCapitalLegadoServicoLocal;

/**
 * EJB com servicos de ContaCapital
 *  @author Marco.Nascimento
 */
@Stateless
@Local (ContaCapitalServicoLocal.class)
@Remote(ContaCapitalServicoRemote.class)
public class ContaCapitalServicoEJB extends ContaCapitalCadastroCrudServicoEJB<ContaCapital> implements ContaCapitalServicoLocal, ContaCapitalServicoRemote {
	
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalCadastroDaoFactory.class)
	private ContaCapitalDao contaCapitalDao;
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ejb.ContaCapitalCadastroCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalCadastroCrudDaoIF<ContaCapital> getDAO() {
		return this.contaCapitalDao;
	}
	
	@EJB
	private ValorConfiguracaoCapitalServicoLocal valorConfServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@EJB
	private ContaCapitalLegadoServicoLocal contaCapitalLegadoServico;
	
	@EJB
	private GftIntegracaoServicoLocal gftServico;
	
	@EJB
	private CapesIntegracaoServicoLocal capesIntegracaoServico;
	
	/**
	 * 1 - Valida fluxo de aprovação
	 * 0 - Não valida fluxo de aprovação
	 */
	private static final String VALIDAR_APROVACAO = "1"; 
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ejb.ContaCapitalCadastroCrudServicoEJB#incluir(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	public ContaCapital incluir(ContaCapital contaCapital) throws BancoobException {
		
		if(validarIncluir(contaCapital)) {
			
			ValorConfiguracaoCapital valorConf = valorConfServico.obterValorConfiguracao(ContaCapitalConstantes.PARAMETRO_SITUACAO_PROPOSTA, contaCapital.getIdInstituicao());
			
			SituacaoCadastroProposta situacaoProposta = new SituacaoCadastroProposta();
			if(valorConf.getValorConfiguracao().equals(VALIDAR_APROVACAO)) {
				situacaoProposta.setId(EnumSituacaoCadastroProposta.COD_AGUARDANDO_APROVACAO.getCodigo());
			} else {
				situacaoProposta.setId(EnumSituacaoCadastroProposta.COD_APROVADO.getCodigo());
			}
			contaCapital.setSituacaoCadastroProposta(situacaoProposta);
			
			contaCapital = super.incluir(contaCapital);
			
			contaCapital.getHistorico().add(criarHistorico(contaCapital));

			if(valorConf.getValorConfiguracao().equals(VALIDAR_APROVACAO)) {
				GftIntegracaoDTO gftIntegracaoDTO = new GftIntegracaoDTO();
				gftIntegracaoDTO.setIdInstituicaoProcesso(contaCapital.getIdInstituicao());
				gftIntegracaoDTO.setIdRegistroControlado(contaCapital.getId());
				gftIntegracaoDTO.setNumContaCapital(contaCapital.getNumContaCapital());
				gftServico.instanciarFluxoAprovacao(gftIntegracaoDTO);
			}
		}
		
		return contaCapital;
	}

	/**
	 * Incluir utilizado para usuario fora do Sisbr 2.0
	 * @param contaCapital
	 * @return
	 * @throws BancoobException
	 */
	public ContaCapital incluirExterno(ContaCapital contaCapital) throws BancoobException {
		validarIncluirExterno(contaCapital);
		return super.incluir(contaCapital);
	}
	
	/**
	 * Valida a inclusão de capital externa ao sisbr 2.0
	 * 1 - valida se tem cliente cadastrado no db2, sevico capes
	 * 2 - verifica no db2 se a conta capital gerada ja esta em uso, se sim troca o numero
	 * 3 - verificar se o cliente tem conta capital ativa cadastrada
	 * @param contaCapital
	 * @return
	 * @throws BancoobException
	 */
	private void validarIncluirExterno(ContaCapital contaCapital) throws BancoobException {
		
		if(!isClienteCadastrado(contaCapital)) {
			throw new ContaCapitalCadastradaNegocioException("MSG_023"); 
		}
				
		if(contaCapitalDao.pesquisarContaCapital(contaCapital.getIdInstituicao(), contaCapital.getNumContaCapital())) {
			Integer numMatricula = contaCapitalLegadoServico.obterUltimaMatricula()+1;
			contaCapital.setNumContaCapital(numMatricula); 
		}
		
		if(contaCapitalDao.pesquisarContaCapitalAtiva(contaCapital.getIdInstituicao(), contaCapital.getIdPessoa())){
			throw new ContaCapitalCadastroNegocioException("MSG_CCA_ATIVA"); 
		}
		
		validarIntegracao(contaCapital);
		
	}	
	
	/**
	 * Valida inclusao 
	 */
	private boolean validarIncluir(ContaCapital contaCapital) throws BancoobException {
		
		boolean clienteCadastrado = isClienteCadastrado(contaCapital);
		if(!clienteCadastrado) {
			throw new ContaCapitalCadastradaNegocioException("MSG_023"); 
		}
		
		boolean contaCapitalExistente = contaCapitalDao.pesquisarContaCapital(contaCapital.getIdInstituicao(), contaCapital.getNumContaCapital());
		if(contaCapitalExistente) {
			throw new ContaCapitalCadastradaNegocioException("MSG_019", contaCapital.getNumContaCapital()); 
		}
		
		boolean contaCapitalInativa = contaCapitalDao.pesquisarContaCapitalInativa(contaCapital.getIdInstituicao(), contaCapital.getIdPessoa());
		if(contaCapitalInativa) {
			throw new ContaCapitalCadastroNegocioException("MSG_015");
		}
		
		Integer numContaCapital = contaCapitalDao.pesquisarContaCapitalPessoa(contaCapital.getIdInstituicao(), contaCapital.getIdPessoa());
		if(numContaCapital != null && numContaCapital.intValue() > 0) {
			throw new ContaCapitalCadastroNegocioException("MSG_014", numContaCapital); 
		}
		
		ValorConfiguracaoCapital valorConf = valorConfServico.obterValorConfiguracao(ContaCapitalConstantes.PARAMETRO_VALIDAR_NATUREZA_JURIDICA_CADASTRO_ASSOCIADO, contaCapital.getIdInstituicao());
		
		if(isPessoaJuridica(contaCapital)) {
			if(valorConf.getValorConfiguracao().equals(VALIDAR_APROVACAO)) {
				validarNaturezaJuridica(contaCapital);
				validarCNAE(contaCapital);
			}
		}
		
		validarFechamento(contaCapital);
		
		validarIntegracao(contaCapital);
		
		return true;
	}
	
	/**
	 * Valida condicoes para inclusao de informacoes (DB2 x SQL)
	 * 1 - valida se a conta capital gerada ja esta em um uso no sql, para a cooperativa em questão
	 * 2 - valida se o cliente nao tem ainda conta capital ativa
	 * @param dto
	 * @throws BancoobException
	 */
	private void validarIntegracao(ContaCapital contaCapital) throws BancoobException {
		Integer numCoop = instituicaoIntegracaoServico.obterNumeroCooperativa(contaCapital.getIdInstituicao());
		
		if(contaCapitalLegadoServico.verificarContaCapitalCadastrada(numCoop, contaCapital.getNumContaCapital())) {
			throw new ContaCapitalCadastradaNegocioException("MSG_025", contaCapital.getNumContaCapital());
		}
		
		PessoaIntegracaoDTO pessoaIntegracao = capesIntegracaoServico.obterPessoaInstituicao(contaCapital.getIdPessoa(), contaCapital.getIdInstituicao());
		List<ContaCapitalLegado> listaContaCapital = contaCapitalLegadoServico.obterContaCapitalCooperativaCliente(numCoop, pessoaIntegracao.getIdPessoaLegado(),1);
		
		if (listaContaCapital != null && !listaContaCapital.isEmpty()){
			throw new ContaCapitalCadastradaNegocioException("MSG_014", listaContaCapital.get(0).getNumMatricula());			
		}		
	}
	
	/**
	 * @see br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB#alterar(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	public void alterar(ContaCapital contaCapital) throws BancoobException {

		if(validarAlterar(contaCapital)) {
			
			super.alterar(contaCapital);
			
			em.persist(criarHistorico(contaCapital));
		}
	}
	
	/**
	 * Valida alteracao de conta capital
	 * @param contaCapital
	 * @return
	 * @throws BancoobException
	 */
	private boolean validarAlterar(ContaCapital contaCapital) throws BancoobException {
		
		validarFechamento(contaCapital);
		
		return true;
	}
	
	/**
	 * Cria Historico Conta Capital
	 */
	private HistoricoContaCapital criarHistorico(ContaCapital contaCapital) throws BancoobException {
		HistoricoContaCapital hist = new HistoricoContaCapital();
		hist.setId(new HistoricoContaCapitalPK(contaCapital.getId()));
		
		hist.setDataMatricula(contaCapital.getDataMatricula());
		hist.setDataSaida(contaCapital.getDataSaida());
		hist.setDescObsAprovacao(contaCapital.getDescObsAprovacao());
		hist.setIdInstituicao(contaCapital.getIdInstituicao());
		hist.setIdPessoa(contaCapital.getIdPessoa());
		
		//Salva idUsuario logado na tabela de historico
		String idUsuario = InformacoesUsuario.getInstance().getLogin();
		hist.setIdUsuario(idUsuario);
		
		hist.setMatriculaEscolhida(contaCapital.getMatriculaEscolhida());
		hist.setNumContaCapital(contaCapital.getNumContaCapital());
		hist.setSituacaoCadastroProposta(contaCapital.getSituacaoCadastroProposta());
		hist.setSituacaoContaCapital(contaCapital.getSituacaoContaCapital());
		
		return hist;
	}
	
	/**
	 * Verifica se a natureza juridica da empresa é permitida
	 */
	private void validarNaturezaJuridica(ContaCapital contaCapital) throws BancoobException {
		Short codigoNatJur = capesIntegracaoServico.obterPessoaJuridicaFormaConstituicao(contaCapital.getIdPessoa(), contaCapital.getIdInstituicao()).getCodigoTipoFormaConstituicao();
		if(!contaCapitalDao.naturezaJuridicaPermitida(codigoNatJur)) {
			throw new ContaCapitalCadastroNegocioException("MSG_020");
		}
	}
	
	/**
	 * Verifica se o CNAE da empresa é permitida
	 */
	private void validarCNAE(ContaCapital contaCapital) throws BancoobException {
		String codigoCNAE = capesIntegracaoServico.obterPessoaJuridicaFormaConstituicao(contaCapital.getIdPessoa(), contaCapital.getIdInstituicao()).getCodigoCnaeFiscal();
		if(!contaCapitalDao.cnaePermitido(codigoCNAE)) {
			throw new ContaCapitalCadastroNegocioException("MSG_021");
		}
	}
	
	/**
	 * Caso fechamento da cooperativa iniciado, nao permite manutencao de informacoes do valor cota 
	 * @param contaCapital
	 * @throws BancoobException
	 */
	private void validarFechamento(ContaCapital contaCapital) throws BancoobException {
		FechamentoContaCapitalDelegate fechamentoDelegate = ContaCapitalComumFabricaDelegates.getInstance().criarFechamentoContaCapitalDelegate();
		Integer numCoop = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate().obterNumeroCooperativa(contaCapital.getIdInstituicao());
		if(fechamentoDelegate.isFechamentoIniciado(numCoop)) {
			throw new CadastroContaCapitalNegocioException("MSG_009");
		}
	}
	
	/**
	 * Verifica se é pessoa juridica
	 * @param contaCapital
	 * @throws BancoobException
	 */
	private boolean isPessoaJuridica(ContaCapital contaCapital) throws BancoobException {
		return capesIntegracaoServico.isPessoaJuridica(contaCapital.getIdPessoa(), contaCapital.getIdInstituicao());
	}
	
	/**
	 * Verifica se o cliente esta cadastrado
	 * @param contaCapital
	 * @throws BancoobException
	 */
	private boolean isClienteCadastrado(ContaCapital contaCapital) throws BancoobException {
		return contaCapitalDao.pesquisarClienteCadastrado(contaCapital.getIdInstituicao(), contaCapital.getIdPessoa());
	}

	/**
	 * {@link ContaCapitalServicoRemote#atualizarSituacaoCadastroPorDocumento(Integer)}
	 */
	public void atualizarSituacaoCadastroPorDocumento(Integer idContaCapital) throws BancoobException {
		
		ContaCapital contaCapital = obter(idContaCapital);
		
		if(contaCapital.getSituacaoCadastroProposta().getId().equals(EnumSituacaoCadastroProposta.COD_DEVOLVIDO.getCodigo())) {
			contaCapital.getHistorico().add(criarHistorico(contaCapital));
			
			SituacaoCadastroProposta situacao = new SituacaoCadastroProposta();
			situacao.setId(EnumSituacaoCadastroProposta.COD_AGUARDANDO_APROVACAO.getCodigo());
			contaCapital.setSituacaoCadastroProposta(situacao);
			getDAO().alterar(contaCapital);
		}
	}	
	
	/**
	 * {@link ContaCapitalServicoRemote#atualizarSituacaoCadastro(Integer)}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarSituacaoCadastro(Integer idContaCapital) throws BancoobException {
  
		ContaCapital contaCapital = obter(idContaCapital);
  
		Integer codSituacaoCadastro = contaCapital.getSituacaoCadastroProposta().getId();
		if(EnumSituacaoCadastroProposta.COD_DEVOLVIDO.getCodigo().equals(codSituacaoCadastro) ||
				EnumSituacaoCadastroProposta.COD_REJEITADO.getCodigo().equals(codSituacaoCadastro)) {
			contaCapital.getHistorico().add(criarHistorico(contaCapital));
			
			SituacaoCadastroProposta situacao = new SituacaoCadastroProposta();
			situacao.setId(EnumSituacaoCadastroProposta.COD_AGUARDANDO_APROVACAO.getCodigo());
			contaCapital.setSituacaoCadastroProposta(situacao);
			getDAO().alterar(contaCapital);
			
			GftIntegracaoDTO gftIntegracaoDTO = new GftIntegracaoDTO();
			gftIntegracaoDTO.setExecutarAtividadeAprovacao(Boolean.FALSE);
			gftIntegracaoDTO.setIdInstituicaoProcesso(contaCapital.getIdInstituicao());
			gftIntegracaoDTO.setIdRegistroControlado(contaCapital.getId());
			gftIntegracaoDTO.setNumContaCapital(contaCapital.getNumContaCapital());
			gftServico.instanciarFluxoAprovacao(gftIntegracaoDTO);
		}
	}
		
	/**
	*{@link ContaCapitalServicoRemote#obterPorInstituicaoMatricula(Integer, Integer)}
	*/
	public ContaCapital obterPorInstituicaoMatricula(Integer idInstituicao,Integer numMatricula) throws BancoobException{
		ContaCapital saida = null;
		
		ConsultaDto<ContaCapital> criterios = new ConsultaDto<ContaCapital>();
		ContaCapital filtro = new ContaCapital();
		filtro.setIdInstituicao(idInstituicao);
		filtro.setNumContaCapital(numMatricula);
		criterios.setFiltro(filtro);
		
		List<ContaCapital> resultado = listar(criterios);
		if (resultado != null && !resultado.isEmpty()){
			saida = resultado.get(0);	 
		}
		
		return saida;
	}
	
	/**
	*{@link ContaCapitalServicoRemote#obterPorInstituicaoPessoa(Integer, Integer)}
	*/
	public List<ContaCapital> obterPorInstituicaoPessoa(Integer idInstituicao, Integer idPessoa) throws BancoobException{
		ConsultaDto<ContaCapital> criterios = new ConsultaDto<ContaCapital>();
		ContaCapital filtro = new ContaCapital();
		filtro.setIdInstituicao(idInstituicao);
		filtro.setIdPessoa(idPessoa);
		criterios.setFiltro(filtro);
		return listar(criterios);
	}

	/**
	 * {@link ContaCapitalServicoRemote#obterNovaContaCapital(Integer)}
	 */
	public Integer obterNovaContaCapital(Integer idInstituicao) throws BancoobException {
		return contaCapitalLegadoServico.obterNovaContaCapital(idInstituicao);		
	}

	/**
	 * {@link ContaCapitalServicoRemote#pesquisar(CadastroContaCapitalRenDTO)}
	 */
	public List<CadastroContaCapitalRenDTO> pesquisar(CadastroContaCapitalRenDTO dto) throws BancoobException {
		return contaCapitalDao.pesquisar(dto);
	}

	/**
	 * {@link ContaCapitalServicoRemote#pesquisarLancamentosContaCapital(Integer, Integer)}
	 */
	public Integer pesquisarLancamentosContaCapital(Integer idContaCapital,Integer idInstituicao) throws BancoobException {
		return contaCapitalDao.pesquisarLancamentosContaCapital(idContaCapital, idInstituicao); 
	}

	/**
	 * {@link ContaCapitalServicoRemote#pesquisarParcelamentosContaCapital(Integer)}
	 */
	public Integer pesquisarParcelamentosContaCapital(Integer idContaCapital)throws BancoobException {
		return contaCapitalDao.pesquisarParcelamentosContaCapital(idContaCapital);
	}

	/**
	 * {@link ContaCapitalServicoRemote#excluir(Integer)}
	 */
	public void excluir(Integer idContaCapital) throws BancoobException {
		contaCapitalDao.excluir(idContaCapital);
		
	}

	/**
	 * {@link ContaCapitalServicoRemote#obterResumo(Integer)}
	 */
	public ContaCapitalResumoDTO obterResumo(Integer idContaCapital) throws BancoobException {
		return contaCapitalDao.obterResumo(idContaCapital);
	}
	
	/**
	 * {@link ContaCapitalServicoRemote#obterResumo(Integer, Integer)}
	 */
	public ContaCapitalResumoDTO obterResumo(Integer idInstituicao, Integer numContaCapital) throws BancoobException {
		return contaCapitalDao.obterResumo(idInstituicao, numContaCapital);
	}

	/**
	 * {@link ContaCapitalServicoRemote#obterResumos(List)}
	 */
	public List<ContaCapitalResumoDTO> obterResumos(List<Integer> idsContaCapital) throws BancoobException {
		return contaCapitalDao.obterResumos(idsContaCapital);
	}
	
	/**
	 * {@link ContaCapitalServicoRemote#obterContaCapitalPorPessoa(List)}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CadastroContaCapitalRenDTO> obterContaCapitalPorPessoa(List<PessoaIntegracaoDTO> lstPessoas) throws BancoobException {
		return contaCapitalDao.obterContaCapitalPorPessoa(lstPessoas);
	}

	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ContaCapitalServico#obterIdInstituicaoPorIdContaCapital(java.lang.Integer)
	 */
	public Integer obterIdInstituicaoPorIdContaCapital(Integer idContaCapital) throws BancoobException {
		return contaCapitalDao.obterIdInstituicaoPorIdContaCapital(idContaCapital);
	}

	/**
	 * {@link ContaCapitalServicoRemote#obterMaiorContaCapitalAtivaPorInstituicao(Integer)}
	 */
	public Integer obterMaiorContaCapitalAtivaPorInstituicao(Integer idInstituicao) throws BancoobException {
		return contaCapitalDao.obterMaiorContaCapitalAtivaPorInstituicao(idInstituicao);
	}

	/**
	 * {@link ContaCapitalServicoRemote#obterMenorContaCapitalAtivaPorInstituicao(Integer)}
	 */
	public Integer obterMenorContaCapitalAtivaPorInstituicao(Integer idInstituicao) throws BancoobException {
		return contaCapitalDao.obterMenorContaCapitalAtivaPorInstituicao(idInstituicao);
	}
	
	/* (non-Javadoc)
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ContaCapitalServico#obterMaiorContaCapitalPorInstituicao(java.lang.Integer)
	 */
	public Integer obterMaiorContaCapitalPorInstituicao(Integer idInstituicao) throws BancoobException {
		return contaCapitalDao.obterMaiorContaCapitalPorInstituicao(idInstituicao);
	}
	
	/* (non-Javadoc)
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ContaCapitalServico#obterMenorContaCapitalPorInstituicao(java.lang.Integer)
	 */
	public Integer obterMenorContaCapitalPorInstituicao(Integer idInstituicao) throws BancoobException {
		return contaCapitalDao.obterMenorContaCapitalPorInstituicao(idInstituicao);
	}

	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ContaCapitalServico#obterDadosDesligamento(br.com.sicoob.cca.cadastro.negocio.dto.FiltroContaCapitalDTO)
	 */
	public List<DadosDesligamentoDTO> obterDadosDesligamento(FiltroContaCapitalDTO filtro) throws BancoobException {
		return contaCapitalDao.obterDadosDesligamento(filtro);
	}
}
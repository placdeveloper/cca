package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.negocio.dto.InformacaoProfissionalDTO;
import br.com.sicoob.cca.comum.negocio.servicos.InformacaoProfissionalServico;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.DebitoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.HistDebitoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.HistDebitoContaCapitalPK;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoPeriodoDebito;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoValorDebito;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoPeriodoDebito;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoValorDebito;
import br.com.sicoob.cca.movimentacao.negocio.dto.ConsultaDebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.DebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.QuadroGeralAssociadoDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.DebitoIndeterminadoNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.DebitoIndeterminadoServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.DebitoIndeterminadoServicoRemote;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDaoFactory;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.DebitoIndeterminadoDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.ContaCorrenteIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ContaCapitalLegadoServicoLocal;

/**
 * @author marco.nascimento
 */
@Stateless
@Local (DebitoIndeterminadoServicoLocal.class)
@Remote(DebitoIndeterminadoServicoRemote.class) 
public class DebitoIndeterminadoServicoEJB extends ContaCapitalMovimentacaoServicoEJB implements DebitoIndeterminadoServicoLocal, DebitoIndeterminadoServicoRemote {
	
	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private DebitoIndeterminadoDao debitoIndeterminadoDao;
	
	@EJB
	private ContaCorrenteIntegracaoServicoLocal ccoIntServico;
	
	@EJB
	private ContaCapitalServicoLocal ccaServico;
	
	@EJB
	private ContaCapitalLegadoServicoLocal ccaLegadoServico;
	
	@EJB
	private InformacaoProfissionalServico informacaoProfissionalServico;
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DebitoIndeterminadoServico#incluirDebIndividual(br.com.sicoob.cca.movimentacao.negocio.dto.DebitoIndeterminadoRenDTO)
	 */
	public void incluirDebIndividual(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		if(validarIncluirDebIndividual(dto)) {
			DebitoContaCapital debCCA = preencherDebitoContaCapital(dto);
			debitoIndeterminadoDao.incluir(debCCA);
			debCCA.getHistorico().add(criarHistorico(debCCA));
			atualizarDebIndividualSQL(debCCA);
		}
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DebitoIndeterminadoServico#alterarDebIndividual(br.com.sicoob.cca.movimentacao.negocio.dto.DebitoIndeterminadoRenDTO)
	 */
	public void alterarDebIndividual(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		if(validarAlterarDebIndividual(dto)) {
			DebitoContaCapital debCCA = preencherDebitoContaCapital(dto);
			debitoIndeterminadoDao.alterar(debCCA);
			debCCA.getHistorico().add(criarHistorico(debCCA));
			atualizarDebIndividualSQL(debCCA);
		}
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DebitoIndeterminadoServico#incluirDebEmLote(br.com.sicoob.cca.movimentacao.negocio.dto.DebitoIndeterminadoRenDTO)
	 */
	public void incluirDebEmLote(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		if (validarIncluirDebLote(dto)) {
			dto.setIdInstituicao(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
			dto.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
			dto.setVlrDebito(valorDebitoPorTipoDeDebito(dto));
			debitoIndeterminadoDao.incluirEmLote(dto);
			atualizarDebEmLoteSQL(dto);
		}
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DebitoIndeterminadoServico#alterarDebEmLote(br.com.sicoob.cca.movimentacao.negocio.dto.DebitoIndeterminadoRenDTO, java.lang.Integer)
	 */
	public void alterarDebEmLote(DebitoIndeterminadoRenDTO dto, Integer tipoAlteracao) throws BancoobException {
		dto.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
		debitoIndeterminadoDao.alterarEmLote(dto, tipoAlteracao);
		alterarDebEmLoteSQL(dto, tipoAlteracao);
	}
	
	private void alterarDebEmLoteSQL(DebitoIndeterminadoRenDTO dto, Integer tipoAlteracao) throws BancoobException {
		List<ContaCapitalLegado> lstAtuaDebIndLegado = criarAlteracaoDebitoIndeterminadoLegadoEmLote(dto);
		ccaLegadoServico.alterarDebIndeterminadoEmLote(lstAtuaDebIndLegado, tipoAlteracao, dto.getPercentual());
	}
	
	private List<ContaCapitalLegado> criarAlteracaoDebitoIndeterminadoLegadoEmLote(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		List<ContaCapitalLegado> lst = new ArrayList<ContaCapitalLegado>();
		
		for (int i = 0; i < dto.getIdsNumMatricula().size(); i++) {
			ContaCapitalLegado debitoIndLegado = new ContaCapitalLegado();
			debitoIndLegado.setNumMatricula(dto.getIdsNumMatricula().get(i));
			debitoIndLegado.setValorDeb(dto.getVlrDebito());
			if (dto.getDataInicialDeb() != null) {
				debitoIndLegado.setDataVencimentoDeb(new DateTimeDB(dto.getDataInicialDeb().getTime()));
			}
			lst.add(debitoIndLegado);
		}
		
		return lst;
	}

	/**
	 * Valor Debito por tipo de Debito (Percentual, Valor Cota...)
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	private BigDecimal valorDebitoPorTipoDeDebito(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		BigDecimal valor = BigDecimal.ZERO;
		
		if (EnumTipoValorDebito.COD_TIPO_DEB_QTD_COTAS.getCodigo().equals(dto.getFormaDebito())) {
			valor = dto.getQtdCotas();
		} else if(EnumTipoValorDebito.COD_TIPO_DEB_VALOR.getCodigo().equals(dto.getFormaDebito())) {
			valor = dto.getVlrDebito();
		} else if (EnumTipoValorDebito.COD_TIPO_DEB_PERC_SALARIO_BASE.getCodigo().equals(dto.getFormaDebito())) {
			valor = dto.getPercentual();
		} else if(EnumTipoValorDebito.COD_TIPO_DEB_PERC_SALARIO_RENDA.getCodigo().equals(dto.getFormaDebito())) {
			valor = dto.getPercentual();
		}
		
		return valor;
	}
	
	/**
	 * Atualiza informacoes sobre debito indeterminado em lote no legado
	 * @param dto
	 * @throws BancoobException
	 */
	private void atualizarDebEmLoteSQL(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		List<ContaCapitalLegado> lstAtuaDebIndLegado = criarDebitoIndeterminadoLegadoEmLote(dto);
		ccaLegadoServico.atualizarDebIndeterminadoEmLote(lstAtuaDebIndLegado);
	}
	
	/**
	 * Lista de debito a ser atualizada no legado
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	private List<ContaCapitalLegado> criarDebitoIndeterminadoLegadoEmLote(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		List<ContaCapitalLegado> lst = new ArrayList<ContaCapitalLegado>();
		
		for (int i = 0; i < dto.getIdsNumMatricula().size(); i++) {
			ContaCapitalLegado debitoIndLegado = new ContaCapitalLegado();
			debitoIndLegado.setNumMatricula(dto.getIdsNumMatricula().get(i));
			debitoIndLegado.setNumContaCorrente(dto.getContasCorrente().size() > 0 ? dto.getContasCorrente().get(i) : null);
			debitoIndLegado.setBolDebIndeterminado(Boolean.TRUE);
			debitoIndLegado.setValorDeb(dto.getVlrDebito());
			debitoIndLegado.setDataVencimentoDeb(new DateTimeDB(dto.getDataInicialDeb().getTime()));
			debitoIndLegado.setCodFormaDeb(dto.getTipoInteg());
			debitoIndLegado.setPeriodoProxDeb(dto.getNumPeriodo());
			debitoIndLegado.setTipoPeriodoDeb(dto.getPeriodoDebito());
			debitoIndLegado.setCodTipoValorDebito(dto.getFormaDebito());
			lst.add(debitoIndLegado);
		}
		
		return lst;
	}
	
	/**
	 * Atualizacao do debito no legado (SQL Server)
	 * @param debCCA
	 * @throws BancoobException
	 */
	private void atualizarDebIndividualSQL(DebitoContaCapital debCCA) throws BancoobException {
		ContaCapitalLegado ccaLegado = ccaLegadoServico.obter(debCCA.getContaCapital().getNumContaCapital());
		
		ccaLegado.setBolDebIndeterminado(Boolean.TRUE);
		ccaLegado.setValorDeb(debCCA.getValorDebito());
		ccaLegado.setDataVencimentoDeb(debCCA.getDataVencimentoDebito());
		ccaLegado.setCodFormaDeb(debCCA.getTipoIntegralizacao().getId().intValue());
		ccaLegado.setPeriodoProxDeb(debCCA.getNumPeriodo().intValue());
		ccaLegado.setTipoPeriodoDeb(debCCA.getTipoPeriodoDebito().getId().intValue());
		ccaLegado.setCodTipoValorDebito(debCCA.getTipoValorDebito().getId().intValue());
		
		if (debCCA.getNumContaCorrente() != null && debCCA.getNumContaCorrente().intValue() > 0) {
			ccaLegado.setNumContaCorrente(debCCA.getNumContaCorrente());
		}
		
		ccaLegadoServico.alterar(ccaLegado);
	}
	
	/**
	 * Prepara entidade para persistencia
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	private DebitoContaCapital preencherDebitoContaCapital(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		ContaCapital cca = ccaServico.obter(dto.getIdContaCapital());
		DebitoContaCapital debCCA = null;
		
		if (dto.getIdDebitoContaCapital() != null) {
			debCCA = debitoIndeterminadoDao.pesquisar(dto.getIdDebitoContaCapital());
		} else {
			debCCA = new DebitoContaCapital();
		}
		
		debCCA.setContaCapital(cca);
		debCCA.setIdInstituicao(cca.getIdInstituicao());
		debCCA.setDescricaoMatriculaFunc(dto.getDescMatriculaFunc());
		debCCA.setDataVencimentoDebito(new DateTimeDB(new DateTime(dto.getDataInicialDeb().getTime()).withTimeAtStartOfDay().getMillis()));
		debCCA.setHoraVencimentoDebito(new DateTimeDB());
		debCCA.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
		debCCA.setNumContaCorrente(dto.getNumContaCorrente());
		debCCA.setNumPeriodo(dto.getNumPeriodo().shortValue());
		debCCA.setTipoIntegralizacao(new TipoIntegralizacao(dto.getTipoInteg().shortValue()));
		debCCA.setTipoPeriodoDebito(new TipoPeriodoDebito(dto.getPeriodoDebito().shortValue()));
		debCCA.setTipoValorDebito(new TipoValorDebito(dto.getFormaDebito().shortValue()));
		
		if(EnumTipoValorDebito.COD_TIPO_DEB_QTD_COTAS.getCodigo().equals(dto.getFormaDebito())) {
			debCCA.setValorDebito(dto.getQtdCotas());
		} else if(EnumTipoValorDebito.COD_TIPO_DEB_VALOR.getCodigo().equals(dto.getFormaDebito())) {
			debCCA.setValorDebito(dto.getVlrDebito());
		} else if(EnumTipoValorDebito.COD_TIPO_DEB_PERC_SALARIO_BASE.getCodigo().equals(dto.getFormaDebito())) {
			debCCA.setValorDebito(dto.getPercentual());
		} else if(EnumTipoValorDebito.COD_TIPO_DEB_PERC_SALARIO_RENDA.getCodigo().equals(dto.getFormaDebito())) {
			debCCA.setValorDebito(dto.getPercentual());
		}
		
		return debCCA;
	}

	/**
	 * Cria historico de DebitoContaCapital
	 * @param debCCA
	 * @return
	 * @throws BancoobException
	 */
	private HistDebitoContaCapital criarHistorico(DebitoContaCapital debCCA) throws BancoobException {
		HistDebitoContaCapital hist = new HistDebitoContaCapital();
		
		hist.setId(new HistDebitoContaCapitalPK(debCCA.getId(), debCCA.getHoraVencimentoDebito()));
		hist.setContaCapital(debCCA.getContaCapital());
		hist.setDataVencimentoDebito(debCCA.getDataVencimentoDebito());
		hist.setDescricaoMatriculaFunc(debCCA.getDescricaoMatriculaFunc());
		hist.setIdInstituicao(debCCA.getIdInstituicao());
		hist.setIdUsuario(debCCA.getIdUsuario());
		hist.setNumContaCorrente(debCCA.getNumContaCorrente());
		hist.setNumPeriodo(debCCA.getNumPeriodo());
		hist.setTipoIntegralizacao(debCCA.getTipoIntegralizacao());
		hist.setTipoPeriodoDebito(debCCA.getTipoPeriodoDebito());
		hist.setTipoValorDebito(debCCA.getTipoValorDebito());
		hist.setValorDebito(debCCA.getValorDebito());
		
		return hist;
	}
	
	/**
	 * Valida dados da inclusao de debito (individual)
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	private boolean validarIncluirDebIndividual(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		DebitoContaCapital debCCA = debitoIndeterminadoDao.pesquisarDebitoContaCapital(dto.getIdContaCapital(), dto.getIdInstituicao());
		if(debCCA != null) {
			throw new DebitoIndeterminadoNegocioException("MSG_DEB_JACADASTRADO");
		}
		
		validarDadosAssociado(dto);
		validarDadosDebito(dto);
		validarPeriodoDebito(dto);
		return true;
	}
	
	/**
	 * Valida dados da inclusao de debito (em lote)
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	private boolean validarIncluirDebLote(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		validarDadosDebito(dto);
		validarPeriodoDebito(dto);
		return true;
	}
	
	/**
	 * Valida dados da inclusao de debito (individual)
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	private boolean validarAlterarDebIndividual(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		validarDadosAssociado(dto);
		validarDadosDebito(dto);
		validarPeriodoDebito(dto);
		return true;
	}
	
	/**
	 * Valida dados do associado
	 * @param dto
	 * @throws BancoobException
	 */
	private void validarDadosAssociado(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		if (dto.getIdContaCapital() == null || dto.getIdContaCapital().intValue() == 0) {
			throw new DebitoIndeterminadoNegocioException("MSG_DEB_CCA_INVALIDO");
		}
		if (dto.getIdInstituicao() == null || dto.getIdInstituicao().intValue() == 0) {
			throw new DebitoIndeterminadoNegocioException("MSG_DEB_INST_INVALIDO");
		}
		if (dto.getTipoInteg().equals(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo())) {
			if(dto.getPeriodoDebito() == null) {
				throw new DebitoIndeterminadoNegocioException("MSG_DEB_PERIODO_INVALIDO");
			}
			
			validarContaCorrente(dto);
		}
		if (dto.getTipoInteg().equals(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_FOLHA.getCodigo())
				|| dto.getTipoInteg().equals(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getCodigo())) {
			List<InformacaoProfissionalDTO> infos = informacaoProfissionalServico.consultarInformacaoProfissional(dto.getIdContaCapital());
			if (infos == null || infos.isEmpty()) {
				throw new DebitoIndeterminadoNegocioException("MSG_DEB_FOLHA_INF_PROF_NAO_ENCONTRADA");
			}
		}
	}
	
	/**
	 * Valida dados forma de debito
	 * @param dto
	 * @throws BancoobException
	 */
	private void validarDadosDebito(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		if (dto.getFormaDebito().equals(EnumTipoValorDebito.COD_TIPO_DEB_VALOR.getCodigo()) && dto.getVlrDebito().compareTo(BigDecimal.ZERO) < 1) {
			throw new DebitoIndeterminadoNegocioException("MSG_DEB_VALOR_INVALIDO");
		}
		if (dto.getFormaDebito().equals(EnumTipoValorDebito.COD_TIPO_DEB_QTD_COTAS.getCodigo()) && dto.getQtdCotas().compareTo(BigDecimal.ZERO) < 1) {
			throw new DebitoIndeterminadoNegocioException("MSG_DEB_COTA_INVALIDO");
		}
		if (dto.getFormaDebito().equals(EnumTipoValorDebito.COD_TIPO_DEB_PERC_SALARIO_BASE.getCodigo())
				&& (dto.getPercentual().compareTo(BigDecimal.ZERO) < 1 || dto.getPercentual().intValue() > ContaCapitalConstantes.NUMERO_CEM)) {
			throw new DebitoIndeterminadoNegocioException("MSG_DEB_PERCBASE_INVALIDO");
		}
		if (dto.getFormaDebito().equals(EnumTipoValorDebito.COD_TIPO_DEB_PERC_SALARIO_RENDA.getCodigo())
				&& (dto.getPercentual().compareTo(BigDecimal.ZERO) < 1 || dto.getPercentual().intValue() > ContaCapitalConstantes.NUMERO_CEM)) {
			throw new DebitoIndeterminadoNegocioException("MSG_DEB_PERCRENDA_INVALIDO");
		}
	}
	
	/**
	 * Valida data do debito
	 * @param dto
	 * @throws BancoobException
	 */
	private void validarPeriodoDebito(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		final int diasAno = 365;
		if (dto.getDataInicialDeb() == null) {
			throw new DebitoIndeterminadoNegocioException("MSG_DEB_DATAINICIAL_INVALIDO");
		}
		if (DateTimeComparator.getDateOnlyInstance().compare(dto.getDataInicialDeb(), new Date()) < 0 && dto.getInclusaoDebito()) {
			throw new DebitoIndeterminadoNegocioException("MSG_DEB_DATA_INVALIDO");
		}
		if (dto.getTipoInteg().equals(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo()) 
				&& dto.getPeriodoDebito().equals(EnumTipoPeriodoDebito.COD_TIPO_PER_INTERVALO.getCodigo())
				&& (dto.getNumPeriodo().intValue() <= 0 || dto.getNumPeriodo().intValue() > diasAno)) {
			throw new DebitoIndeterminadoNegocioException("MSG_DEB_INTERVALO_INVALIDO");
		}
	}
	
	/**
	 * Valida Conta Corrente do debito
	 * @param dto
	 * @throws BancoobException
	 */
	private void validarContaCorrente(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		ContaCorrenteIntegracaoDTO ccoDTO = new ContaCorrenteIntegracaoDTO();
		ccoDTO.setIdInstituicao(dto.getIdInstituicao());
		ccoDTO.setIdPessoa(dto.getIdPessoa());
		ccoDTO.setNumContaCorrente(dto.getNumContaCorrente());
		if (!ccoIntServico.verificarContaCorrenteAtiva(ccoDTO)) {
			throw new DebitoIndeterminadoNegocioException("MSG_027");
		}
		if (!ccoIntServico.validarTitularidade(dto.getIdPessoa(), dto.getIdInstituicao(), Long.valueOf(dto.getNumContaCorrente()))) {
			throw new DebitoIndeterminadoNegocioException("MSG_DEB_TIT_CCO_INVALIDA");
		}
		if (ccoIntServico.verificarContaCorrenteBloqueadaEncerrada(ccoDTO)) {
			throw new DebitoIndeterminadoNegocioException("MSG_CCO_ENCE_BLOQ");
		}
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DebitoIndeterminadoServico#pesquisarQuadroGeralAssociados()
	 */
	public List<QuadroGeralAssociadoDTO> pesquisarQuadroGeralAssociados() throws BancoobException {
		Integer idInstituicao = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		if (idInstituicao != null) {
			return debitoIndeterminadoDao.pesquisarQuadroGeralAssociados(idInstituicao);
		}
		return null;
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DebitoIndeterminadoServico#pesquisarQtdDebCCODiaFixo()
	 */
	public List<QuadroGeralAssociadoDTO> pesquisarQtdDebCCODiaFixo() throws BancoobException {
		Integer idInstituicao = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		if (idInstituicao != null) {
			return debitoIndeterminadoDao.pesquisarQtdDebCCODiaFixo(idInstituicao);
		}
		return null;
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DebitoIndeterminadoServico#pesquisarQtdDebCCOIntervalo()
	 */
	public List<QuadroGeralAssociadoDTO> pesquisarQtdDebCCOIntervalo() throws BancoobException {
		Integer idInstituicao = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		if (idInstituicao != null) {
			return debitoIndeterminadoDao.pesquisarQtdDebCCOIntervalo(idInstituicao);
		}
		return null;
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DebitoIndeterminadoServico#pesquisarQtdDebFolhaBanco()
	 */
	public List<QuadroGeralAssociadoDTO> pesquisarQtdDebFolhaBanco() throws BancoobException {
		Integer idInstituicao = Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		if (idInstituicao != null) {
			return debitoIndeterminadoDao.pesquisarQtdDebFolhaBanco(idInstituicao);
		}
		return null;
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DebitoIndeterminadoServico#pesquisar(br.com.sicoob.cca.movimentacao.negocio.dto.ConsultaDebitoIndeterminadoRenDTO)
	 */
	public List<ConsultaDebitoIndeterminadoRenDTO> pesquisar(ConsultaDebitoIndeterminadoRenDTO filtro) throws BancoobException {
		if (filtro.getIdInstituicao() == null) {
			filtro.setIdInstituicao(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		}
		return debitoIndeterminadoDao.pesquisar(filtro);
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DebitoIndeterminadoServico#pesquisar(java.lang.Long)
	 */
	public DebitoContaCapital pesquisarPorIdContaCapital(Integer idContaCapital, Integer idInstituicao) throws BancoobException {
		return debitoIndeterminadoDao.pesquisarDebitoContaCapital(idContaCapital, idInstituicao);
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DebitoIndeterminadoServico#pesquisar(java.lang.Long)
	 */
	public DebitoContaCapital pesquisar(Long idDebitoContaContaCapital) throws BancoobException {
		return debitoIndeterminadoDao.pesquisar(idDebitoContaContaCapital);
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DebitoIndeterminadoServico#pesquisarAssociadosSemDebito(br.com.sicoob.cca.movimentacao.negocio.dto.ConsultaDebitoIndeterminadoRenDTO)
	 */
	public List<ConsultaDebitoIndeterminadoRenDTO> pesquisarAssociadosSemDebito(ConsultaDebitoIndeterminadoRenDTO filtro) throws BancoobException {
		filtro.setIdInstituicao(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		
		if (EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo().equals(filtro.getIdTipoInteg())) {
			return debitoIndeterminadoDao.pesquisarAssociadosSemDebitoCCO(filtro);
		} else if (EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_FOLHA.getCodigo().equals(filtro.getIdTipoInteg()) || 
				EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getCodigo().equals(filtro.getIdTipoInteg())) {
			return debitoIndeterminadoDao.pesquisarAssociadosSemDebitoFolhaBanco(filtro);
		}
		
		return null;
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.DebitoIndeterminadoServico#excluirDebEmLote(br.com.sicoob.cca.movimentacao.negocio.dto.DebitoIndeterminadoRenDTO)
	 */
	public void excluirDebEmLote(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		dto.setIdUsuario(InformacoesUsuario.getInstance().getLogin());
		debitoIndeterminadoDao.excluirEmLote(dto);
		ccaLegadoServico.excluirDebIndeterminadoEmLote(dto.getIdsNumMatricula());
	}
}
package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosFechBaixarParcelasLegadoCCODTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ParcelamentoCCALegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ParcelamentoCCALegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.ContaCapitalIntegracaoLegadoDataSource;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDaoIF;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ParcelamentoCCALegadoDao;

/**
 * EJB contendo servicos relacionados a ParcelamentoCCALegado.
 */
@Stateless
@Local (ParcelamentoCCALegadoServicoLocal.class)
@Remote(ParcelamentoCCALegadoServicoRemote.class)
public class ParcelamentoCCALegadoServicoEJB extends ContaCapitalIntegracaoLegadoCrudServicoEJB<ParcelamentoCCALegado> implements ParcelamentoCCALegadoServicoLocal, ParcelamentoCCALegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private ParcelamentoCCALegadoDao parcelamentoCCALegadoDao; 
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb.ContaCapitalIntegracaoLegadoCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalIntegracaoLegadoCrudDaoIF<ParcelamentoCCALegado> getDAO() {
		return parcelamentoCCALegadoDao;
	}
	
	/**
	 * Obter proximo num parcelamento.
	 *
	 * @param numMatricula o valor de num matricula
	 * @param codTipoParcelamento o valor de cod tipo parcelamento
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Integer obterProximoNumParcelamento(Integer numMatricula, Integer codTipoParcelamento)throws BancoobException {
		Integer numSeqLanc = parcelamentoCCALegadoDao.obterProximoNumParcelamento(numMatricula, codTipoParcelamento);
		if (numSeqLanc == null){
			numSeqLanc = 0;
		}

		return numSeqLanc;
	}
	
	/**
	 * Verificar parcelamento aberto.
	 *
	 * @param numMatricula o valor de num matricula
	 * @param codTipoParcelamento o valor de cod tipo parcelamento
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Integer verificarParcelamentoAberto(Integer numMatricula, Integer codTipoParcelamento)throws BancoobException {
		Integer numSeqLanc = parcelamentoCCALegadoDao.verificarParcelamentoAberto(numMatricula, codTipoParcelamento);
		if (numSeqLanc == null){
			numSeqLanc = 0;
		}

		return numSeqLanc;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<ParcelamentoCCALegado> obterParcelasEmAbertoPelosCanais(Integer numeroCooperativa, Integer numMatricula) throws BancoobException {
		ContaCapitalIntegracaoLegadoDataSource.definirNumeroCooperativa(numeroCooperativa);
		return listar(montarCriteriosConsulta(numMatricula));
	}
	
	private ConsultaDto<ParcelamentoCCALegado> montarCriteriosConsulta(Integer numMatricula) {
		final int tipoParcelamentoIntegral = 1;
		final int codModoLancViaConta = 2;
		ConsultaDto<ParcelamentoCCALegado> criterios = new ConsultaDto<ParcelamentoCCALegado>();
		ParcelamentoCCALegado filtroParcelamento = new ParcelamentoCCALegado();
		ParcelamentoCCALegadoPK parcelamentoPK = new ParcelamentoCCALegadoPK();
		ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();
		
		contaCapitalLegado.setNumMatricula(numMatricula);
		parcelamentoPK.setContaCapitalLegado(contaCapitalLegado);
		parcelamentoPK.setCodTipoParcelamento(tipoParcelamentoIntegral);
		filtroParcelamento.setParcelamentoCCALegadoPK(parcelamentoPK);
		filtroParcelamento.setCodModoLanc(codModoLancViaConta);
		filtroParcelamento.setCodSituacaoParcela(ContaCapitalConstantes.COD_SITUACAO_EM_ABERTO);
		filtroParcelamento.setCodCanal(ContaCapitalConstantes.COD_CANAL_FILTRAR_TODOS);
		criterios.setFiltro(filtroParcelamento);
		return criterios;
	}

	/**
	 * {@inheritDoc}
	 */
	public Boolean verificarParcelasEmAbertoPelosCanais(Integer numeroCooperativa, Integer numMatricula) throws BancoobException {
		ContaCapitalIntegracaoLegadoDataSource.definirNumeroCooperativa(numeroCooperativa);
		return parcelamentoCCALegadoDao.verificarParcelasEmAbertoPelosCanais(numMatricula);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ParcelamentoCCALegadoServico#excluirParcelasDevolucaoAbertoViaCaixa(java.lang.Integer)
	 */
	public void excluirParcelasDevolucaoAbertoViaCaixa(Integer numMatricula) throws BancoobException {
		parcelamentoCCALegadoDao.excluirParcelasDevolucaoAbertoViaCaixa(numMatricula);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ParcelamentoCCALegadoServico#pesquisarFechParcelasEmAbertoViaCCO(DateTimeDB)
	 */
	public List<DadosFechBaixarParcelasLegadoCCODTO> pesquisarFechParcelasEmAbertoViaCCO(DateTimeDB dataAtualProduto) throws BancoobException {
		return parcelamentoCCALegadoDao.pesquisarFechParcelasEmAbertoViaCCO(dataAtualProduto);
	}	
		
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ParcelamentoCCALegadoServico#alterarEmLote(java.lang.list)
	 */
	public void alterarEmLote(List<ParcelamentoCCALegado> listParcelamentoCCALegado) throws BancoobException {
		parcelamentoCCALegadoDao.alterarEmLote(listParcelamentoCCALegado);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ParcelamentoCCALegadoServico#pesquisarFechParcelasDebIndet(DateTimeDB)
	 */
	public List<DadosFechBaixarParcelasLegadoCCODTO> pesquisarFechParcelasDebIndet(DateTimeDB dataAtualProduto) throws BancoobException {
		return parcelamentoCCALegadoDao.pesquisarFechParcelasDebIndet(dataAtualProduto);
	}	
		
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ParcelamentoCCALegadoServico#incluirEmLote(java.lang.list)
	 */
	public void incluirEmLote(List<ParcelamentoCCALegado> listParcelamentoCCALegado) throws BancoobException {
		parcelamentoCCALegadoDao.incluirEmLote(listParcelamentoCCALegado);
	}

}

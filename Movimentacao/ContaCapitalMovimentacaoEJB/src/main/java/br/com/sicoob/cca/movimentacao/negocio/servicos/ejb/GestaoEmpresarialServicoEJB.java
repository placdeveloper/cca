package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.joda.time.DateTime;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.movimentacao.negocio.dto.GestaoEmpresarialDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.GestaoEmpresarialServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.GestaoEmpresarialServicoRemote;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDaoFactory;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.GestaoEmpresarialDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.GestaoEmpresarialLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.GestaoEmpresarialLegadoServicoLocal;

/**
 * EJB contendo servicos relacionados a GestaoEmpresarial.
 *
 * @author Antonio.Genaro
 */
@Stateless
@Local(GestaoEmpresarialServicoLocal.class)
@Remote(GestaoEmpresarialServicoRemote.class)
public class GestaoEmpresarialServicoEJB extends ContaCapitalMovimentacaoServicoEJB implements GestaoEmpresarialServicoLocal, GestaoEmpresarialServicoRemote {
	
	@EJB
	private GestaoEmpresarialLegadoServicoLocal gestaoLegadoServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal iiServico;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private GestaoEmpresarialDao gestaoEmpresarialDao;	
	
	/**
	 * {@link GestaoEmpresarialServicoLocal#iniciarProcessamento(numCoop)}.
	 *
	 * @param numCoop o valor de num coop
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Boolean iniciarProcessamento(Integer numCoop) throws BancoobException {
		
		/**
		 * Data Lote do ultimo registro processado na tabela DB2
		 */
		Date dataLote = gestaoEmpresarialDao.pesquisarDataLoteRecente(iiServico.obterIdInstituicao(numCoop));
		
		/**
		 * Data posterior, d+1 da ultima data de processamento
		 */
		Date dataLotePosterior = new DateTime(dataLote).plusDays(1).toDate();
		
		return gestaoLegadoServico.novosLancamentosDIRF(numCoop, dataLotePosterior);
	}
	
	/**
	 * {@link GestaoEmpresarialServicoLocal#isPrimeiraCarga(Integer)}.
	 *
	 * @param numCoop o valor de num coop
	 * @return {@code true}, se for primeira carga
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Boolean isPrimeiraCarga(Integer numCoop) throws BancoobException {
		
		Integer idInstituicao = iiServico.obterIdInstituicao(numCoop);
		
		return gestaoEmpresarialDao.isPrimeiraCarga(idInstituicao);
	}

	/**
	 * {@link GestaoEmpresarialServicoLocal#realizarCarga(Integer)}.
	 *
	 * @param numCoop o valor de num coop
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void realizarCarga(Integer numCoop) throws BancoobException {
		
		/**
		 * Caso nao seja primeira carga, verifica ultima dataLote registrada na tabela e pega todos registros dali para frente
		 */
		Date data = null;
		if(!isPrimeiraCarga(numCoop)) {
			
			/**
			 * Data Lote do ultimo registro processado na tabela DB2
			 */
			Date dataLote = gestaoEmpresarialDao.pesquisarDataLoteRecente(iiServico.obterIdInstituicao(numCoop));
			
			/**
			 * Data posterior, d+1 da ultima data de processamento
			 */
			data = new DateTime(dataLote).plusDays(1).toDate();
		}
		
		List<GestaoEmpresarialLegadoDTO> lst = gestaoLegadoServico.gerarExtratoDIRF(numCoop, data);

		gestaoEmpresarialDao.incluir(lst);
	}

	/**
	 * {@link GestaoEmpresarialServicoLocal#gerarExtratoDIRF(List<Integer>, Date, Date)}.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param dataInicio o valor de data inicio
	 * @param dataFim o valor de data fim
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<GestaoEmpresarialDTO> gerarExtratoDIRF(List<Integer> idInstituicao, Date dataInicio, Date dataFim) throws BancoobException {
		final int anoDataInicio = 2014;
		Date dataInicioParam = (dataInicio == null) ? new DateTime(anoDataInicio, 1, 1, 0, 0).withTimeAtStartOfDay().toDate() : dataInicio;
		Date dataFimParam = (dataFim == null) ? new DateTime().withTimeAtStartOfDay().minusDays(1).toDate() : dataFim; 
		
		return gestaoEmpresarialDao.gerarExtratoDIRF(idInstituicao, dataInicioParam, dataFimParam);
	}
}
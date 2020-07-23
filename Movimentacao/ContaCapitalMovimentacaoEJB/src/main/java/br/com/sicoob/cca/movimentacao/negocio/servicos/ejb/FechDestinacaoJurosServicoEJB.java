package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoLote;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.FechDestinacaoJurosServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.FechDestinacaoJurosServicoRemote;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoContaCapitalServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.LancamentosCCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ProdutoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechDestinacaoJurosLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.LancamentosCCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.persistencia.ContaCapitalIntegracaoLegadoDataSource;

/**
* @author Antonio.Genaro
*/
@Stateless
@Local(FechDestinacaoJurosServicoLocal.class)
@Remote(FechDestinacaoJurosServicoRemote.class)
public class FechDestinacaoJurosServicoEJB extends ContaCapitalMovimentacaoServicoEJB implements FechDestinacaoJurosServicoLocal, FechDestinacaoJurosServicoRemote {

	@EJB
	private ProdutoLegadoServicoLocal produtoLegadoServico;		
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	@EJB
	private FechamentoContaCapitalServicoLocal fechamentoContaCapitalServico;
	@EJB
	private FechDestinacaoJurosLegadoServicoLocal fechDestinacaoJurosLegadoServico;		
	@EJB
	private LancamentosCCapitalLegadoServicoLocal lancamentosCCapitalLegadoServico;	
	@EJB
	private LancamentoContaCapitalServicoLocal lancamentoContaCapitalServico;
	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;		
	
	/**
	 * Baixa as parcelas em aberto via cco.
	 *
	 * @param numCooperativa
	 * @return 
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void rodar(Integer numCoop) throws BancoobException {	
		if(!bolViradaAno(numCoop)) {
			return;
		}
		
		ContaCapitalIntegracaoLegadoDataSource.definirNumeroCooperativa(numCoop, false);		
		Integer idInstituicao = instituicaoIntegracaoServico.obterIdInstituicao(numCoop);		
		DateTimeDB dataAtualProd = new DateTimeDB(produtoLegadoServico.obterDatasProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, numCoop).getDataAtualProd().getTime());		

		List<LancamentosCCapitalLegadoDTO> listLanc = lancamentosCCapitalLegadoServico.listarLancamentosDestinacaoJuros(dataAtualProd);	
		if(!listLanc.isEmpty()) {
			lancamentoContaCapitalServico.incluirEmLote(montarListaLancamentosDB2(listLanc, dataAtualProd, idInstituicao));		
		}			
	}
	
	/**
	 * Baixa as parcelas em aberto via cco no SQL.
	 *
	 * @param numCooperativa
	 * @return 
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void rodarSQL(Integer numCoop) throws BancoobException {
		if(!bolViradaAno(numCoop)) {
			return;
		}		
		String idUsuario = fechamentoContaCapitalServico.buscarIdUsuarioFechamento(numCoop);		
		fechDestinacaoJurosLegadoServico.gerarLancamentoProvisaoJuros(numCoop, idUsuario);
		fechDestinacaoJurosLegadoServico.gerarLancamentoDestinacaoJuros(numCoop, idUsuario);			
	}
	
	private Boolean bolViradaAno(Integer numCoop) throws BancoobException {
		
		ProdutoDTO produtoDTO = produtoLegadoServico.obterDatasProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, numCoop);						
		if(retornaAno(produtoDTO.getDataAtualProd()) != retornaAno(produtoDTO.getDataProxProd())) {			
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
	
	/**
	 * monta lista de lancamentos
	 *
	 * @param lista LancamentosCCapitalLegadoDTO 
	 * @param DateTimeDB
	 * @param Integer
	 * @return lista LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<LancamentoContaCapital> montarListaLancamentosDB2(List<LancamentosCCapitalLegadoDTO> listLanc, DateTimeDB dataAtualProd,Integer idInstituicao) throws BancoobException {
		List<LancamentoContaCapital> lancamentos = new ArrayList<LancamentoContaCapital>();
		for (LancamentosCCapitalLegadoDTO dto : listLanc) {
			lancamentos.add(montarLancamentoContaCapitalDB2(dto, dataAtualProd, idInstituicao));
		}
		return lancamentos;
	}		
	/**
	 * monta lançamentos 
	 *
	 * @param LancamentosCCapitalLegadoDTO 
	 * @param DateTimeDB
	 * @param Integer
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */

	private LancamentoContaCapital montarLancamentoContaCapitalDB2(LancamentosCCapitalLegadoDTO dto, DateTimeDB dataLote,Integer idInstituicao) throws BancoobException {
		ContaCapital contaCapital = null;

		contaCapital = contaCapitalServico.obterPorInstituicaoMatricula(idInstituicao, dto.getNumMatricula());
		
		LancamentoContaCapital lancamentoDb2 = new LancamentoContaCapital();
		lancamentoDb2.setContaCapital(contaCapital);
		lancamentoDb2.setDataLancamento(dataLote);
		lancamentoDb2.setIdInstituicao(idInstituicao);
		lancamentoDb2.setIdUsuario(dto.getIdUsuarioResp());
		lancamentoDb2.setTipoSubscricao(null);
		lancamentoDb2.setValorLancamento(dto.getValorLanc());		
		lancamentoDb2.setDescNumDocumento(dto.getDescNumDocumento());			
		lancamentoDb2.setTipoHistoricoCCA(new TipoHistoricoCCA(dto.getIdTipoHistoricoLanc().shortValue()));
		if (dto.getIdTipoHistoricoEstorno() != null) {
			lancamentoDb2.setTipoHistoricoEstorno(new TipoHistoricoCCA(dto.getIdTipoHistoricoEstorno().shortValue()));
		}
		lancamentoDb2.setTipoLote(new TipoLote(dto.getNumLoteLanc().shortValue()));				
		lancamentoDb2.setBolProcessado(ContaCapitalConstantes.NUM_ZERO.shortValue());
		lancamentoDb2.setDataHoraAtualizacao(new DateTimeDB());
		lancamentoDb2.setNumSeqLanc(dto.getNumSeqLanc());
		lancamentoDb2.setDescOperacaoExterna(dto.getDescOperacaoExterna());
		
		return lancamentoDb2;
	}			
	
	/**
	 * Recupera o ano de uma data
	 * 
	 * @param data
	 *            a data a ser valida
	 * @return o ano
	 */
	private int retornaAno(Date data) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.YEAR);
	}
	
}
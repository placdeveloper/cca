package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.SaldoContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.SaldoContaCapitalServicoRemote;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDaoFactory;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.SaldoContaCapitalDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ProdutoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
 * EJB contendo servicos relacionados a SaldoContaCapitalService.
 *
 * @author Rodrigo.Melchior
 */
@Stateless
@Local(SaldoContaCapitalServicoLocal.class)
@Remote(SaldoContaCapitalServicoRemote.class)
public class SaldoContaCapitalServicoEJB extends ContaCapitalMovimentacaoServicoEJB implements SaldoContaCapitalServicoLocal, SaldoContaCapitalServicoRemote{

	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private SaldoContaCapitalDao saldoContaCapitalDao;
	
	@EJB
	private ProdutoLegadoServicoLocal produtoLegadoServicoLocal; 
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServicoLocal; 

	/* Carrega a SALDOCONTACAPITAL no DB2 para chamada do SWS 
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.SaldoContaCapitalServico#realizarCarga(java.lang.Integer)
	 */
	public void realizarCargaSWS(Integer numCoop) throws BancoobException {
		Integer idInstituicao = instituicaoIntegracaoServicoLocal.obterIdInstituicao(numCoop);
		
		ProdutoDTO produtoDTO = produtoLegadoServicoLocal.obterDatasProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, numCoop);

		if(isTrocouMesProduto(produtoDTO.getDataAtualProd(), produtoDTO.getDataAntProd())) {
			
			if(!saldoContaCapitalDao.validarDataSaldoSeJaPossuiCarga(idInstituicao, produtoDTO.getDataAntProd())) {
				saldoContaCapitalDao.incluirCarga(idInstituicao,produtoDTO.getDataAntProd());
			}
		}
	}

	private Boolean isTrocouMesProduto(Date dataAtualProduto, Date dataAnteriorProduto) {
		return DataUtil.getMes(dataAnteriorProduto) == DataUtil.getMes(dataAtualProduto) ? Boolean.FALSE : Boolean.TRUE;
	}

}

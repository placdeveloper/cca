/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoIntegralizacaoExternaServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

// TODO: Auto-generated Javadoc
/**
 * A Classe LancamentoIntegralizacaoExternaDelegate.
 *
 * @author Antonio.Genaro
 */
public class LancamentoIntegralizacaoExternaDelegate extends ContaCapitalMovimentacaoCrudDelegate<LancamentoContaCapital, LancamentoIntegralizacaoExternaServico> {

	/**
	 * Instancia um novo LancamentoIntegralizacaoExternaDelegate.
	 */
	LancamentoIntegralizacaoExternaDelegate(){
		
	}

	/**
	 * Locator LancamentoIntegralizacaoExternaServico.
	 *
	 * @return LancamentoIntegralizacaoExternaServico
	 */
	@Override
	protected LancamentoIntegralizacaoExternaServico localizarServico() {
		return (LancamentoIntegralizacaoExternaServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarLancamentoIntegralizacaoExternaServico();
	}	
	

	/**
	 * {@link LancamentoIntegralizacaoExternaServico#incluir(IntegralizacaoCapitalDTO)}.
	 *
	 * @param dto o valor de dto
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public LancamentoContaCapital incluir(IntegralizacaoCapitalDTO dto) throws BancoobException{
		return getServico().incluir(dto);
	}	

	/**
	 * {@link LancamentoIntegralizacaoExternaServico#consultarIntegralizacao(IntegralizacaoCapitalDTO)}.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */	
	public List<LancamentoContaCapital> consultarIntegralizacao(IntegralizacaoCapitalDTO dto) throws BancoobException{
		return getServico().consultarIntegralizacao(dto);
	}
			
	/**
	 * {@link LancamentoIntegralizacaoExternaServico#incluirRateioEmLote(List)}
	 * 
	 * @param dtos
	 * @return
	 * @throws BancoobException
	 */
	public List<LancamentoContaCapital> incluirRateioEmLote(List<IntegralizacaoCapitalDTO> dtos) throws BancoobException {
		return getServico().incluirRateioEmLote(dtos);
	}
	
	
}
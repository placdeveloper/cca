/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.movimentacao.negocio.dto.CancelamentoParcelamentoDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.ParcelamentoContaCapitalExternoServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

// TODO: Auto-generated Javadoc
/**
 * A Classe ParcelamentoContaCapitalExternoDelegate.
 *
 * @author Antonio.Genaro
 */
public class ParcelamentoContaCapitalExternoDelegate extends ContaCapitalMovimentacaoCrudDelegate<Parcelamento, ParcelamentoContaCapitalExternoServico>{

	/**
	 * Instancia um novo ParcelamentoContaCapitalExternoDelegate.
	 */
	ParcelamentoContaCapitalExternoDelegate(){
		
	}

	/**
	 * Locator CadastroContaCapitalServico.
	 *
	 * @return ParcelamentoContaCapitalExternoServico
	 */
	@Override
	protected ParcelamentoContaCapitalExternoServico localizarServico() {
		return (ParcelamentoContaCapitalExternoServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarParcelamentoContaCapitalExternoServico();
	}	
	
	/**
	 * {@link ParcelamentoContaCapitalExternoServico#incluirParcelamento(ParcelamentoCapitalDTO)}.
	 *
	 * @param listParcelas o valor de list parcelas
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void incluirParcelamento(List<ParcelamentoCapitalDTO> listParcelas)  throws BancoobException {
		getServico().incluirParcelamento(listParcelas);
	}
	
	/**
	 * {@link ParcelamentoContaCapitalExternoServico#cancelarParcelamentos(List)}
	 * @param listParcelas
	 * @throws BancoobException
	 */
	public void cancelarParcelamentos(List<CancelamentoParcelamentoDTO> listParcelas) throws BancoobException {
		getServico().cancelarParcelamentos(listParcelas);
	}
		
}

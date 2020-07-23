/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.SubscricaoRenDTO;

// TODO: Auto-generated Javadoc
/**
 * A Interface SubscricaoContaCapitalServico.
 *
 * @author Antonio.Genaro
 */
public interface SubscricaoContaCapitalServico extends ContaCapitalMovimentacaoServico {
	
	/**
	 * O m�todo Incluir.
	 *
	 * @param subscricaoRenDTO o valor de subscricao ren dto
	 * @param lstParcelamentoRenDTO o valor de lst parcelamento ren dto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void incluir(SubscricaoRenDTO subscricaoRenDTO, List<ParcelamentoRenDTO> lstParcelamentoRenDTO) throws BancoobException;
	
}

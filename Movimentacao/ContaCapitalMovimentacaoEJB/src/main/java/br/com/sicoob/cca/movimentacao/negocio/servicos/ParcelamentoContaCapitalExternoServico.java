package br.com.sicoob.cca.movimentacao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.movimentacao.negocio.dto.CancelamentoParcelamentoDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;

/**
 * A Interface ParcelamentoContaCapitalExternoServico.
 *
 * @author Antonio.Genaro
 */
public interface ParcelamentoContaCapitalExternoServico extends ContaCapitalMovimentacaoCrudServico<Parcelamento> {
	
	/**
	 * Grava no parcelamento do sql e db2 os dados do parcelamento, não tratado para via folha .
	 *
	 * @param listaParcelas o valor de lista parcelas
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void incluirParcelamento(List<ParcelamentoCapitalDTO> listaParcelas)  throws BancoobException;

	/**
	 * Cancela os parcelamentos nas bases sql e db2. <br>
	 * Devem ser informados nos DTOs os campos:
	 * <ul>
	 * 	<li>numContaCapital</li>
	 * 	<li>idInstituicao</li>
	 * 	<li>numParcela</li>
	 * 	<li>numParcelamento</li>
	 * 	<li>idTipoParcelamento</li>
	 * </ul>
	 * Os parcelamentos passados recebem informacoes de dataSituacao (data de cancelamento), valorParcela e dataVencimento.
	 * @param listParcelas a lista de parcelamentos
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void cancelarParcelamentos(List<CancelamentoParcelamentoDTO> listParcelas) throws BancoobException;
	
	/**
	 * Pesquisa parcelamentos em aberto via caixa.
	 * Parametros dto: numContaCapital, idInstituicao e idTipoParcelamento.
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	List<ParcelamentoRenDTO> pesquisarParcelamentosEmAbertoViaCaixa(ParcelamentoCapitalDTO dto) throws BancoobException;
	
	/**
	 * Atualiza os parcelamentos para o idSituacaoParcelamento parametrizado.
	 * @param idInstituicao
	 * @param parcelamentos
	 * @param idSituacaoParcelamento
	 * @throws BancoobException
	 */
	void atualizarParcelamentos(Integer idInstituicao, List<ParcelamentoRenDTO> parcelamentos, Integer idSituacaoParcelamento) throws BancoobException;
	
}

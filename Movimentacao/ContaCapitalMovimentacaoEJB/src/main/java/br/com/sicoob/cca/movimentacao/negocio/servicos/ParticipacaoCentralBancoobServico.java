package br.com.sicoob.cca.movimentacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoob;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoobPK;

/**
 * A Interface ParticipacaoCentralBancoobServico.
 */
public interface ParticipacaoCentralBancoobServico extends ContaCapitalMovimentacaoCrudServico<ParticipacaoCentralBancoob> {
	
	/**
	 * O método Incluir participacao central.
	 *
	 * @param participacaoCentralBancoob o valor de participacao central bancoob
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void incluirParticipacaoCentral(ParticipacaoCentralBancoob participacaoCentralBancoob) throws BancoobException;
	
	/**
	 * O método Alterar participacao central.
	 *
	 * @param participacaoCentralBancoob o valor de participacao central bancoob
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void alterarParticipacaoCentral(ParticipacaoCentralBancoob participacaoCentralBancoob) throws BancoobException;
	
	/**
	 * Incluir ParticipacaoCentralBancoob consultando o valor de participacao da tabela saldo contabil do legado.
	 * @param pk
	 * @throws BancoobException
	 */
	void atualizarParticipacaoCentralPorSaldoContabil(ParticipacaoCentralBancoobPK pk) throws BancoobException;
	
	/**
	 * @param pk
	 * @return
	 * @throws BancoobException
	 */
	boolean existeParticipacaoCentralBancoob(ParticipacaoCentralBancoobPK pk) throws BancoobException;
	
}
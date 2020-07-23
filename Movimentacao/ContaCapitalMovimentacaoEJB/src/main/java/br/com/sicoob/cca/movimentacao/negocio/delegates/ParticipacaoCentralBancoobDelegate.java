package br.com.sicoob.cca.movimentacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoob;
import br.com.sicoob.cca.movimentacao.negocio.servicos.ParticipacaoCentralBancoobServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

/**
 * Delegate Participacao das Singulares nas Centrais 
 */
public class ParticipacaoCentralBancoobDelegate extends ContaCapitalMovimentacaoCrudDelegate<ParticipacaoCentralBancoob, ParticipacaoCentralBancoobServico> {
	
	/**
	 * Instancia um novo ParticipacaoCentralBancoobDelegate.
	 */
	ParticipacaoCentralBancoobDelegate() {
		
	}

	/**
	 * Locator ParticipacaoCentralBancoobServico
	 */
	@Override
	protected ParticipacaoCentralBancoobServico localizarServico() {
		return (ParticipacaoCentralBancoobServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarParticipacaoCentralBancoobServico();
	}
	
	/**
	 * {@link ParticipacaoCentralBancoobServico#incluirParticipacaoCentral(ParticipacaoCentralBancoob)}
	 * @param participacaoCentralBancoob
	 * @throws BancoobException
	 */
	public void incluirParticipacaoCentral(ParticipacaoCentralBancoob participacaoCentralBancoob)throws BancoobException {		
		getServico().incluirParticipacaoCentral(participacaoCentralBancoob);
	}
	
	/**
	 * {@link ParticipacaoCentralBancoobServico#alterarParticipacaoCentral(ParticipacaoCentralBancoob)}
	 * @param participacaoCentralBancoob
	 * @throws BancoobException
	 */
	public void alterarParticipacaoCentral(ParticipacaoCentralBancoob participacaoCentralBancoob)throws BancoobException {		
		getServico().alterarParticipacaoCentral(participacaoCentralBancoob);
	}
}
package br.com.sicoob.cca.comum.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.dto.InstituicaoCooperativaSCIDTO;
import br.com.sicoob.cca.comum.negocio.servicos.ViewInstituicaoServico;
import br.com.sicoob.cca.comum.negocio.servicos.locator.ContaCapitalComumServiceLocator;

/**
 * @author Nairon.Silva
 */
public class ViewInstituicaoDelegate extends ContaCapitalComumDelegate<ViewInstituicaoServico> {

	/**
	 * Utilizar a fabrica para criar o delegate
	 * @see ContaCapitalComumFabricaDelegates#criarViewInstituicaoDelegate()
	 */
	ViewInstituicaoDelegate() {
		
	}

	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected ViewInstituicaoServico localizarServico() {
		return ContaCapitalComumServiceLocator.getInstance().localizarViewInstituicaoServico();
	}
	
	/**
	 * {@link ViewInstituicaoServico#consultarCooperativasAtivas(Integer)}
	 * @param numCoopPai opcional
	 * @return
	 * @throws BancoobException
	 */
	public List<InstituicaoCooperativaSCIDTO> consultarCooperativasAtivas(Integer numCoopPai) throws BancoobException {
		return getServico().consultarCooperativasAtivas(numCoopPai);
	}
	
	/**
	 * {@link ViewInstituicaoServico#consultarPacPorCooperativa(Integer)}
	 */
	public List<InstituicaoCooperativaSCIDTO> consultarPacPorCooperativa(Integer idInstituicao) throws BancoobException {
		return getServico().consultarPacPorCooperativa(idInstituicao);
	}
}
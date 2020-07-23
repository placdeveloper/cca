package br.com.sicoob.cca.frontoffice.negocio.util;

import java.math.BigDecimal;

import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.cca.frontoffice.negocio.excecao.ContaCapitalFrontofficeNegocioException;

/**
 * Parametros/Rotulos utilizados no CCA
 * @author Nairon.Silva
 */
public enum ParametroSRTBCCA {

	COOPERATIVA("00023", Number.class),
	ID_USUARIO("00184", String.class),
	DIA_DEBITO("00299", Number.class),
	NUMERO_CONTA_CORRENTE("01402", Number.class),
	MESES("01409", Integer.class),
	TIPO_AGENDAMENTO("01411", Number.class),
	VALOR("02000", BigDecimal.class),
	TIPO_PESQUISA("03011", Number.class),
	CHAVE_PESQUISA("03012", String.class),
	MATRICULA("08074", Number.class),
	CPF_CNPJ("09007", String.class),
	IDENTIFICADOR("09272", String.class),
	INSTITUICAO("09306", Number.class),
	TIPO_PARCELAMENTO("09486", Number.class);
	
	private String rotulo;
	private Class<?> classe;

	private ParametroSRTBCCA(String rotulo, Class<?> classe) {
		this.rotulo = rotulo;
		this.classe = classe;
	}
	
	/**
	 * Retorna o rotulo
	 * @return
	 */
	public String getRotulo() {
		return this.rotulo;
	}
	
	/**
	 * Retorna a classe do parametro
	 * @return
	 */
	public Class<?> getClasse() {
		return this.classe;
	}
	
	/**
	 * Retorna o nome formatado do rotulo
	 * @return
	 */
	public String getNomeRotulo() {
		return this.name() + "(" + this.rotulo + ")";
	}
	
	/**
	 * Extrai o parametro da mensagem fazendo o devido cast.
	 * @param mensagem
	 * @param parametro
	 * @param clazz
	 * @return
	 * @throws ContaCapitalFrontofficeNegocioException
	 */
	@SuppressWarnings("unchecked")
	public static <P> P extrairParametro(Mensagem mensagem, ParametroSRTBCCA parametro, Class<P> clazz) throws ContaCapitalFrontofficeNegocioException {
		Parametro parametroRetorno = mensagem.recuperarParametro(parametro.getRotulo());
		if (parametroRetorno != null && parametroRetorno.getValor() != null) {
			try {
				return (P) parametroRetorno.getValor();
			} catch (ClassCastException cce) {
				throw new ContaCapitalFrontofficeNegocioException("msg.erro.parametro.class.cast", 
						parametro.getNomeRotulo(), clazz.getName(), parametroRetorno.getValor().getClass().getName());
			}
		} else {
			throw new ContaCapitalFrontofficeNegocioException("msg.erro.parametro.nao.informado", parametro.getNomeRotulo());
		}
	}
	
	/**
	 * Extrai o idInstituicao da mensagem.
	 * @param mensagem
	 * @return
	 * @throws ContaCapitalFrontofficeNegocioException
	 */
	public static Integer extrairIdInstituicao(Mensagem mensagem) throws ContaCapitalFrontofficeNegocioException {
		if (mensagem.getIdInstituicao() == null) {
			throw new ContaCapitalFrontofficeNegocioException("msg.erro.parametro.nao.informado", "IdInstituicao");
		}
		return mensagem.getIdInstituicao();
	}
	
}

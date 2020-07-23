package br.com.sicoob.cca.frontoffice.negocio.util;

import org.apache.commons.lang.StringUtils;

import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegadoPK;

/**
 * Helper para fazer a traducao de {@link ParcelamentoCCALegadoPK} entre string e objeto.
 * @author Nairon.Silva
 */
public final class ParcelamentoCCAPKHelper {

	private ParcelamentoCCAPKHelper() {
	}
	
	private static final String DELIMITADOR = "-";

	/**
	 * Transforma do objeto para string
	 * @param parcelamentoCCALegadoPK
	 * @return
	 */
	public static String toStringPK(ParcelamentoCCALegadoPK parcelamentoCCALegadoPK) {
		if (parcelamentoCCALegadoPK == null) {
			return null;
		}
		
		Integer numMatricula = parcelamentoCCALegadoPK.getContaCapitalLegado().getNumMatricula();
		Integer numParcelamento = parcelamentoCCALegadoPK.getNumParcelamento();
		Integer numParcela = parcelamentoCCALegadoPK.getNumParcela();
		Integer codTipoParcelamento = parcelamentoCCALegadoPK.getCodTipoParcelamento();
		
		return StringUtils.join(new Object[]{
				numMatricula,
				numParcelamento,
				numParcela,
				codTipoParcelamento}, DELIMITADOR);
	}
	
	/**
	 * Transforma de string para objeto
	 * @param stringPK
	 * @return
	 */
	public static ParcelamentoCCALegadoPK toParcelamentoCCALegadoPK(String stringPK) {
		if (stringPK == null) {
			return null;
		}
		
		ParcelamentoCCALegadoPK pk = new ParcelamentoCCALegadoPK();
		ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();
		int i = 0;
		
		String[] pkParts = stringPK.split(DELIMITADOR);
		String numMatricula = pkParts[i++];
		String numParcelamento = pkParts[i++];
		String numParcela = pkParts[i++];
		String codTipoParcelamento = pkParts[i++];
		
		contaCapitalLegado.setNumMatricula(Integer.valueOf(numMatricula));
		pk.setContaCapitalLegado(contaCapitalLegado);
		pk.setNumParcelamento(Integer.valueOf(numParcelamento));
		pk.setNumParcela(Integer.valueOf(numParcela));
		pk.setCodTipoParcelamento(Integer.valueOf(codTipoParcelamento));
		return pk;
	}
	
}

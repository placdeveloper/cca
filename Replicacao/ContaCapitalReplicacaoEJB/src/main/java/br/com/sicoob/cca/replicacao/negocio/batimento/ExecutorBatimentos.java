package br.com.sicoob.cca.replicacao.negocio.batimento;

import java.util.ArrayList;
import java.util.List;

/**
 * Motor para realizacao dos batimentos
 * @author Nairon.Silva
 */
public class ExecutorBatimentos {

	/**
	 * Realiza os batimentos entre os registros SQL e DB2
	 * @param registrosSQL
	 * @param registrosDB2
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <E extends RegistroBatimento> List<E> realizarBatimentos(List<E> registrosSQL, List<E> registrosDB2) {
		List<E> registros = new ArrayList<E>();
		for (E registroSQL : registrosSQL) {
			registros.add(registroSQL);
			RegistroBatimento correspondenteDB2 = registroSQL.procurarCorrespondente(registrosDB2);
			if (correspondenteDB2 == null) {
				registroSQL.setNaoPossuiCorrespondente(true);
				continue;
			}
			registroSQL.verificarDivergencia(correspondenteDB2);
			registros.add((E) correspondenteDB2);
		}
		return registros;
	}
	
	/**
	 * Verifica se nas listas passadas existe algum registro com divergencia
	 * @param listasRegistros
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean possuiDivergencias(List<? extends RegistroBatimento>... listasRegistros) {
		for (List<? extends RegistroBatimento> registros : listasRegistros) {
			for (RegistroBatimento registro : registros) {
				if (registro.isPossuiDivergencia() || registro.isNaoPossuiCorrespondente()) {
					return true;
				}
			}
		}
		return false;
	}
	
}

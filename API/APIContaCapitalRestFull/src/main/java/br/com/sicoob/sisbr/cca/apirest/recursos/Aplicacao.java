package br.com.sicoob.sisbr.cca.apirest.recursos;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class Aplicacao extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(IntegralizacaoRecurso.class);
		return classes;
	}
}

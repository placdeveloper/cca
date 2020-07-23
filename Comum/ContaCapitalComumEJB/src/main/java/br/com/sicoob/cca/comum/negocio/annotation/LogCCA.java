package br.com.sicoob.cca.comum.negocio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.sicoob.cca.entidades.negocio.enums.EnumMetodoOperacaoContaCapital;

/**
 * Annotation a ser utilizada nos métodos que precisem ser interceptados para log.
 * @author Nairon.Silva
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogCCA {

	EnumMetodoOperacaoContaCapital metodo();
	
}

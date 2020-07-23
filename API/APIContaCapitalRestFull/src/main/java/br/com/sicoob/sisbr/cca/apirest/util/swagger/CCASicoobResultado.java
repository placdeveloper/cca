package br.com.sicoob.sisbr.cca.apirest.util.swagger;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CCASicoobResultado<T>{
	
	private List<T> resultado;

	public List<T> getResultado() {
		if (resultado == null) {
			resultado = new ArrayList<>();
		}
		return resultado;
	}

	public void setResultado(List<T> resultado) {
		this.resultado = resultado;
	}

}

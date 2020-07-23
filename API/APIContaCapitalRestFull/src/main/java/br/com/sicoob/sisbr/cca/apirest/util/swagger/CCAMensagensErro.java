package br.com.sicoob.sisbr.cca.apirest.util.swagger;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;

/**
 * Classe generica de erros do swagger
 * @author Marcos.Balbi
 *
 */
@ApiModel(value="MensagensErro")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CCAMensagensErro {
	
	private List<CCASicoobMensagem> mensagens;

	/** getMensagens
	 * @return getMensagens
	 */
	public List<CCASicoobMensagem> getMensagens() {
		if (mensagens == null) {
			mensagens = new ArrayList<>();
		}
		return mensagens;
	}

	public void setMensagens(List<CCASicoobMensagem> mensagens) {
		this.mensagens = mensagens;
	}

}

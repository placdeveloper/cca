package br.com.sicoob.sisbr.cca.apirest.util.swagger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;

/**
 * Classe generica de erros do swagger
 * 
 * @author Marcos.Balbi
 *
 */
@ApiModel(value="SicoobMensagem")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CCASicoobMensagem {

	private String mensagem;

	private String codigo;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}

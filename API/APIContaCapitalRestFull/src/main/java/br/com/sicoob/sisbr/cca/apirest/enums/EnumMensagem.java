package br.com.sicoob.sisbr.cca.apirest.enums;

public enum EnumMensagem {

	MSG_OBR("001","{0} é obrigatório."),
	MSG_MAIORQUE("002","{0} deve ser maior que {1}."),
	MSG_MENORQUE("003","{0} deve ser menor que {1}."),
	MSG_MAIORIGUALQ0UE("004","{0} deve ser maior ou igual que {1}."),
	MSG_MENORIGUALQUE("005","{0} deve ser menor ou igual que {1}."),
	MSG_ENTRE("006","{0} deve ser entre {1} e {2}."),	
	MSG_JACADASTRADO("007","{0} já cadastrado."),
	MSG_ERRO_AO("008","Erro ao {0}."),	
	MSG_DATALIMITE("009","Data limite {0}.");

	private String codigo;	
	private String mensagem;
	
	private EnumMensagem(String codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public static EnumMensagem getMensagemPorCodigo(String codigo) {
		for (EnumMensagem tipo : values()) {
			if (tipo.getCodigo().equals(codigo)) {
				return tipo;
			}
		}
		return null;
	}	
		
}

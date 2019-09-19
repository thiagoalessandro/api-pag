package br.com.intelector.api.model.enums;

public enum SituacaoRegistroEnum implements IBasicEnum {

	Ativo("A"),
	Inativo("I"),
	Excluido("E");
	
	private String name;

	SituacaoRegistroEnum(String name) {
		this.name = name; 
	}

	@Override
	public String getName() {
		return this.name;
	}

	public static SituacaoRegistroEnum convertStringToEnum(String situacao) {
		switch (situacao) {
			case "A" :
				return SituacaoRegistroEnum.Ativo;
			case "I" :
				return SituacaoRegistroEnum.Inativo;
			case "E" :
				return SituacaoRegistroEnum.Excluido;
			default:
				return SituacaoRegistroEnum.Ativo;
		}
	}
	
	
}

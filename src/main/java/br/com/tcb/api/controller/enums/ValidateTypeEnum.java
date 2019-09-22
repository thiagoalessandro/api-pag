package br.com.tcb.api.controller.enums;

import br.com.tcb.api.model.enums.IBasicEnum;

public enum ValidateTypeEnum implements IBasicEnum {

	REQUIRED("REQUIRED");

	private String name;

	ValidateTypeEnum(String name) {
		this.name = name; 
	}

	@Override
	public String getName() {
		return this.name;
	}
	
}

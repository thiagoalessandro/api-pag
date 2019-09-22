package br.com.tcb.api.model.enums;

public enum RequestMethodEnum implements IBasicEnum {

	GET("GET"),
	POST("POST"),
	DELETE("DELETE"),
	PUT("PUT"),
	OPTIONS("OPTIONS");

	private String name;

	RequestMethodEnum(String name) {
		this.name = name; 
	}

	@Override
	public String getName() {
		return this.name;
	}

}

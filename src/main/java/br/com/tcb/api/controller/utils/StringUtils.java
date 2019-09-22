package br.com.tcb.api.controller.utils;

public class StringUtils {
	
	public static String noNull(String value) {
		return value.replace("null", "");
	}
	
	public static Long initLong(String value) {
		return Long.parseLong(value);
	}
	
}

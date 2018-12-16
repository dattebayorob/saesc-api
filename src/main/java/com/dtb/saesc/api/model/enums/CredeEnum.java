package com.dtb.saesc.api.model.enums;

public enum CredeEnum {
	SEDUC,
	SEFOR_1,
	SEFOR_2,
	SEFOR_3,
	CREDE_1;
	
	public static boolean isValid(String string) {
		for (CredeEnum c : CredeEnum.values()) {
			if(c.toString().equals(string))
				return true;
		}
		return false;
	}
}

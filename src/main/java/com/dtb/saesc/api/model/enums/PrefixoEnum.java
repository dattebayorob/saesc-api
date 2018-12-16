package com.dtb.saesc.api.model.enums;

public enum PrefixoEnum{
	EEM,
	EEFM,
	EEEMTI,
	EEEP;
	
	public static boolean isValid(String string) {
		for (PrefixoEnum c : PrefixoEnum.values()) {
			if(c.toString().equals(string))
				return true;
		}
		return false;
	}
}

package com.dtb.saesc.api.model.utils;

public class EnumUtils {
	public static <T> boolean isValid(String string, T[] enumValues) {
		for(T c: enumValues) {
			if(c.toString().equals(string))
				return true;
		}
		return false;
	}
	
}

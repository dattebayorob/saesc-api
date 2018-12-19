package com.dtb.saesc.api.model.utils.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.dtb.saesc.api.model.utils.EnumUtils;

public class EnumValidator implements ConstraintValidator<Enum, String> {
	private java.lang.Enum[] enumValues;

	@Override
	public void initialize(Enum constraintAnnotation) {
		Class<? extends java.lang.Enum<?>> enumClass = constraintAnnotation.enumClass();
		this.enumValues = enumClass.getEnumConstants();

		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	/**
	 * 
	 * Por enquanto utilizando a classe auxiliar EnumUtils, caso EnumUtils se torne
	 * necessário apenas nesta validação, migra-la pra cá.
	 * 
	 */

	@Override
	public boolean isValid(String string, ConstraintValidatorContext context) {
		return EnumUtils.isValid(string, enumValues);
	}
}

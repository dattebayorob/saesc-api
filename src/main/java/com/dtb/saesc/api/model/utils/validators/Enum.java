package com.dtb.saesc.api.model.utils.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Target(value = { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
public @interface Enum {
	Class<? extends java.lang.Enum<?>> enumClass();

	String message() default "Invalid Enum.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}

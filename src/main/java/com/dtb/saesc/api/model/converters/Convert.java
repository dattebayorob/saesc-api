package com.dtb.saesc.api.model.converters;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

public interface Convert<I, O> extends Function<I, O>{
	
	default O convert(final I input) {
		return this.apply(input);
	}
	
	default List<O> convert(final List<I> input){
		return input.stream().map(this::apply).collect(Collectors.toList());
	}
	
	default Page<O> convert(final Page<I> input){
		return input.map(this::apply);
	}

}

package com.dtb.saesc.api.model.converters;

public interface Converter<E,D> {
	
	public Convert<E,D> toDto(Class<D> cls);
	
	public Convert<E,D> toDto(D dto);
	
	public Convert<D, E> toEntity(Class<E> cls);
	
	public Convert<D, E> toEntity(E entity);
}

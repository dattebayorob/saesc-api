package com.dtb.saesc.api.model.converters.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dtb.saesc.api.model.converters.Convert;
import com.dtb.saesc.api.model.converters.Converter;

@Component
public class ConverterImpl<E, D> implements Converter<E, D> {
	@Autowired
	private ModelMapper map;

	@Override
	public Convert<E, D> toDto(Class<D> cls) {
		return entity -> map.map(entity, cls);
	}

	@Override
	public Convert<E, D> toDto(D dto) {
		return entity -> {
			map.map(entity, dto);
			return dto;
		};
	}

	@Override
	public Convert<D, E> toEntity(Class<E> cls) {
		return dto -> map.map(dto, cls);
	}

	@Override
	public Convert<D, E> toEntity(E entity) {
		return dto -> {
			map.map(dto, entity);
			return entity;
		};
	}

}

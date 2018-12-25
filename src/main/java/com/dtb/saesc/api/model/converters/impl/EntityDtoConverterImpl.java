package com.dtb.saesc.api.model.converters.impl;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dtb.saesc.api.model.converters.EntityDtoConverter;
import com.dtb.saesc.api.model.entities.GenericEntity;

@Component
public class EntityDtoConverterImpl<D extends GenericEntity,E extends GenericEntity> implements EntityDtoConverter<D, E>{
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public D toDto(E entity, D dto) {
		modelMapper.map(entity, dto);
		return dto;
	}

	@Override
	public D toDto(E entity, Class<D> cls) {
		return modelMapper.map(entity, cls);
	}

	@Override
	public E toEntity(D dto, E entity) {
		dto.setId(entity.getId());
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.map(dto, entity);
		return entity;
	}

	@Override
	public E toEntity(D dto, Class <E> cls) {
		return modelMapper.map(dto, cls);
	}

}

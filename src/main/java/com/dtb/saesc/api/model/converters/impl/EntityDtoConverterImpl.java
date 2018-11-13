package com.dtb.saesc.api.model.converters.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dtb.saesc.api.model.converters.EntityDtoConverter;

@Component
public class EntityDtoConverterImpl<Dto,Entity> implements EntityDtoConverter<Dto, Entity>{
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Dto toDto(Entity entity, Dto dto) {
		modelMapper.map(entity, dto);
		return dto;
	}

	@Override
	public Dto toDto(Entity entity, Class<Dto> cls) {
		return modelMapper.map(entity, cls);
		//return null;
	}

	@Override
	public Entity toEntity(Dto dto, Entity entity) {
		modelMapper.map(dto, entity);
		return entity;
	}

	@Override
	public Entity toEntity(Dto dto, Class <Entity> cls) {
		return modelMapper.map(dto, cls);
	}

}

package com.dtb.saesc.api.model.converters;

public interface EntityDtoConverter<Dto,Entity> {
	public Dto toDto(Entity entity,Dto dto);
	public Dto toDto(Entity entity);
	public Entity toEntity(Dto dto, Entity entity);
	public Entity toEntity(Dto dto);
}

package com.dtb.saesc.api.model.converters;

public interface EntityDtoConverter<Dto, Entity> {
	/**
	 * Converte uma entidade para um dto com ModelMapper Neste metodo entity é o
	 * source e dto é o destino
	 * 
	 * @param entity
	 * @param dto
	 * 
	 * @return dto
	 * 
	 */
	public Dto toDto(Entity entity, Dto dto);

	/**
	 * Converte uma entidade para um dto com Model Mapper. Neste metodo entity é o
	 * source e uma classe será passada como segundo parametro.
	 * 
	 * @param entity
	 * @param        Dto.class
	 * 
	 * @return dto
	 * 
	 */
	public Dto toDto(Entity entity, Class<Dto> cls);

	/**
	 * Converte um dto para uma entidade com Model Mapper. Neste metodo dto é o
	 * source e a entidade passada é o destino
	 * 
	 * @param dto
	 * @param entity
	 * 
	 * @return entity
	 * 
	 */

	public Entity toEntity(Dto dto, Entity entity);

	/**
	 * 
	 * Converte um dto para uma entidade com Model Mapper. Neste metodo entity é o
	 * source e uma classe será passada como segundo parametro
	 * 
	 * @param dto
	 * @param     Entity.class
	 * 
	 * @return entity
	 * 
	 */
	public Entity toEntity(Dto dto, Class<Entity> cls);
}

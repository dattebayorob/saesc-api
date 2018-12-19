package com.dtb.saesc.api.model.converters.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dtb.saesc.api.model.converters.EntityDtoConverter;
import com.dtb.saesc.api.model.dtos.EscolaDto;
import com.dtb.saesc.api.model.entities.Escola;

@Component
public class EscolaDtoConverterImpl implements EntityDtoConverter<EscolaDto, Escola> {
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EscolaDto toDto(Escola entity, EscolaDto dto) {
		modelMapper.map(entity, dto);
		return dto;
	}

	@Override
	public EscolaDto toDto(Escola entity, Class<EscolaDto> cls) {
		return modelMapper.map(entity, EscolaDto.class);
	}

	/**
	 * 
	 * Gamb...
	 * 
	 * O map de dto <> entidade, altera as informações da própria entidade retornada
	 * pelo findById, e o cache do Jpa acaba persistindo as mudanças idependente de
	 * realizar uma nova consulta para testar as informações antigas da entidade...
	 * 
	 * Por enquanto estou mapeando a entidade com as informações do Dto manualmente,
	 * e isso é um problema.
	 * 
	 **/

	@Override
	public Escola toEntity(EscolaDto dto, Escola entity) {
		Escola escola = modelMapper.map(dto, Escola.class);
		escola.setDataCriacao(entity.getDataCriacao());
		escola.setDataAtualizacao(entity.getDataAtualizacao());
		escola.setId(entity.getId());
		return escola;
	}

	@Override
	public Escola toEntity(EscolaDto dto, Class<Escola> cls) {
		return modelMapper.map(dto, Escola.class);
	}

}

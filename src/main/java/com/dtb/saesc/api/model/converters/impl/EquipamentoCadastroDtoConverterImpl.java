package com.dtb.saesc.api.model.converters.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dtb.saesc.api.model.converters.Convert;
import com.dtb.saesc.api.model.converters.Converter;
import com.dtb.saesc.api.model.dtos.EquipamentoCadastroDto;
import com.dtb.saesc.api.model.entities.Equipamento;

@Component
public class EquipamentoCadastroDtoConverterImpl implements Converter<Equipamento, EquipamentoCadastroDto>{
	@Autowired
	private ModelMapper map;
	
	@Override
	public Convert<Equipamento, EquipamentoCadastroDto> toDto(Class<EquipamentoCadastroDto> cls) {
		return entity -> map.map(entity, cls);
	}

	@Override
	public Convert<Equipamento, EquipamentoCadastroDto> toDto(EquipamentoCadastroDto dto) {
		return entity -> {
			map.map(entity, dto);
			return dto;
		};
	}

	@Override
	public Convert<EquipamentoCadastroDto, Equipamento> toEntity(Class<Equipamento> cls) {
		return dto -> map.map(dto, cls);
	}

	@Override
	public Convert<EquipamentoCadastroDto, Equipamento> toEntity(Equipamento entity) {
		return dto -> {
			dto.setId(entity.getId());
			Equipamento e = map.map(dto, Equipamento.class);
			e.setDataCriacao(entity.getDataCriacao());
			return e;
		};
	}
	
}

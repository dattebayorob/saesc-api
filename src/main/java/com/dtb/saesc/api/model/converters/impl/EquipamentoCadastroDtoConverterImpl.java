package com.dtb.saesc.api.model.converters.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dtb.saesc.api.model.converters.EntityDtoConverter;
import com.dtb.saesc.api.model.dtos.EquipamentoCadastroDto;
import com.dtb.saesc.api.model.entities.Equipamento;

@Component
public class EquipamentoCadastroDtoConverterImpl implements EntityDtoConverter<EquipamentoCadastroDto, Equipamento>{
	@Autowired
	private ModelMapper converter;
	@Override
	public EquipamentoCadastroDto toDto(Equipamento entity, EquipamentoCadastroDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EquipamentoCadastroDto toDto(Equipamento entity, Class<EquipamentoCadastroDto> cls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Equipamento toEntity(EquipamentoCadastroDto dto, Equipamento equipamento) {
		dto.setId(equipamento.getId());
		Equipamento e = converter.map(dto, Equipamento.class);
		e.setDataCriacao(equipamento.getDataCriacao());
		return e;
	}

	@Override
	public Equipamento toEntity(EquipamentoCadastroDto dto, Class<Equipamento> cls) {
		return null;
	}
	
}

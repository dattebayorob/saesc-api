package com.dtb.saesc.api.model.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dtb.saesc.api.model.dtos.EscolaDto;
import com.dtb.saesc.api.model.entities.Escola;

public class GenericConverter {
	@Autowired
	public ModelMapper mapper;
	
	public GenericConverter() {
		// TODO Auto-generated constructor stub
	}
	public Escola toEntity(EscolaDto dto, Escola entity) {
		mapper.map(dto, entity);
		return entity;
	}
	public EscolaDto toDto(Escola escola, EscolaDto escolaDto) {
		mapper.map(escola, escolaDto);
		return escolaDto;
	}
	

}

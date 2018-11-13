package com.dtb.saesc.api.model.converters.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.converters.EntityDtoConverter;
import com.dtb.saesc.api.model.dtos.EscolaDto;
import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.services.LinkService;

@Component
public class EscolaDtoConverter implements EntityDtoConverter<EscolaDto, Escola>{
	@Autowired
	private LinkService linkService;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Escola toEntity(EscolaDto escolaDto, Escola escola) {
		modelMapper.map(escolaDto, escola);
		return escola;
	}
	@Override
	public EscolaDto toDto(Escola escola, EscolaDto escolaDto) {
		modelMapper.map(escola, escolaDto);
		List<Link> links = linkService.buscarPorescola(escola.getId());
		links.forEach(link -> escolaDto.getLinksIp().add(
				link.getProvedor().getNome()+" - "+link.getIp()));
		return escolaDto;
	}
	@Override
	public EscolaDto toDto(Escola entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Escola toEntity(EscolaDto dto) {
		// TODO Auto-generated method stub
		return null;
	}
}
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
public class EscolaDtoConverter implements EntityDtoConverter<EscolaDto, Escola> {
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
		adicionarLinks(escola.getId(), escolaDto.getLinksIp());
		return escolaDto;
	}

	@Override
	public EscolaDto toDto(Escola escola, Class<EscolaDto> cls) {
		EscolaDto escolaDto = modelMapper.map(escola, cls);
		adicionarLinks(escola.getId(), escolaDto.getLinksIp());
		return escolaDto;
	}

	@Override
	public Escola toEntity(EscolaDto escolaDto, Class<Escola> cls) {
		return modelMapper.map(escolaDto, cls);
	}

	/**
	 * 
	 * Buscar o ip e provedor dos links de acordo com o id da escola passado e
	 * adiciona-los a lista de links do Dto
	 * 
	 * @param id
	 * @param    List<String>
	 * 
	 */

	private void adicionarLinks(Long id, List<String> linksIps) {
		List<Link> links = linkService.buscarPorescola(id);
		links.forEach(link -> linksIps.add(link.getProvedor().getNome() + " - " + link.getIp()));
	}
}
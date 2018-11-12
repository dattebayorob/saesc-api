/*package com.dtb.saesc.api.model.converters;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dtb.saesc.api.model.dtos.EscolaDto;
import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.services.LinkService;

@Component
public class EscolaDtoConverter {
	@Autowired
	private LinkService linkService;
	//private static ModelMapper modelMapper;
	private ModelMapper modelMapper = new ModelMapper();
	
	public Escola toEntity(EscolaDto escolaDto, Escola escola) {
		modelMapper.map(escolaDto, escola);
		return escola;
	}
	public EscolaDto toDto(Escola escola, EscolaDto escolaDto) {
		modelMapper.map(escola, escolaDto);
		List<Link> links = linkService.buscarPorescola(escola.getId());
		links.forEach(link -> escolaDto.getLinksIp().add(
				link.getProvedor().getNome()+" - "+link.getIp()));
		return escolaDto;
	}
}
*/
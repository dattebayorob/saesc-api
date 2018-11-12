package com.dtb.saesc.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.dtos.LinkDto;
import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.services.EscolaService;
import com.dtb.saesc.api.services.LinkService;

@RestController
@RequestMapping("/links")
public class LinkController {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private LinkService linkService;
	private EscolaService escolaService;
	
	@GetMapping("/escola/{id}")
	public ResponseEntity<Response> buscarLinksPorEscola(@PathVariable("id") Long idEscola){
		
		List<Link> links = linkService.buscarPorescola(idEscola);
		if(links.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		List<LinkDto> ipsDto = new ArrayList<>();
		links.forEach(ip -> ipsDto.add(converterLinksParaDto(ip)));
		return ResponseEntity.ok(Response.data(ipsDto));
	}

	private LinkDto converterLinksParaDto(Link escolaLink) {
		LinkDto linkDto = modelMapper.map(escolaLink, LinkDto.class);
		return linkDto;
	}
}

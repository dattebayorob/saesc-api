package com.dtb.saesc.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.converters.EntityDtoConverter;
import com.dtb.saesc.api.model.dtos.LinkDto;
import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.services.LinkService;

@RestController
@RequestMapping("/links")
public class LinkController {
	@Autowired
	private EntityDtoConverter<LinkDto, Link> converter;
	@Autowired
	private LinkService linkService;
	
	@GetMapping
	public ResponseEntity<Response> buscarLinks(@RequestParam(name = "s", defaultValue = "") String s){
		List<Link> links = linkService.buscarLinks(s);
		List<LinkDto> dto = new ArrayList<>();
		links.forEach(link -> dto.add(converter.toDto(link, LinkDto.class)));
		return ResponseEntity.ok(Response.data(dto));
	}
	
	@GetMapping("/escola/{id}")
	public ResponseEntity<Response> buscarLinksPorEscola(@PathVariable("id") Long idEscola){
		
		List<Link> links = linkService.buscarPorescola(idEscola);
		if(links.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		List<LinkDto> linksDto = new ArrayList<>();
		links.forEach(link -> linksDto.add(converterLinkParaDto(link)));
		return ResponseEntity.ok(Response.data(linksDto));
	}

	private LinkDto converterLinkParaDto(Link link) {
		return converter.toDto(link, LinkDto.class);
	}
}

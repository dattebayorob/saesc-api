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
	public ResponseEntity<Response> buscarLinks(@RequestParam(name = "s", defaultValue = "") String s) {
		List<LinkDto> dtos = new ArrayList<>();
		linkService.buscarLinks(s).forEach(link -> dtos.add(converter.toDto(link, LinkDto.class)));
		return dtos.isEmpty()?
				ResponseEntity.noContent().build():
				ResponseEntity.ok(Response.data(dtos));
	}

	@GetMapping("/escola/{id}")
	public ResponseEntity<Response> buscarLinksPorEscola(@PathVariable("id") Long idEscola) {
		List<LinkDto> dtos = new ArrayList<>();
		linkService.buscarPorescola(idEscola).forEach(link -> dtos.add(converter.toDto(link, LinkDto.class)));
		return dtos.isEmpty()?
				ResponseEntity.noContent().build():
				ResponseEntity.ok(Response.data(dtos));
	}
}

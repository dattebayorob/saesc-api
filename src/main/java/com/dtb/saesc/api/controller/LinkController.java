package com.dtb.saesc.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.converters.Converter;
import com.dtb.saesc.api.model.dtos.LinkDto;
import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.exceptions.ResourceNotFoundException;
import com.dtb.saesc.api.model.exceptions.messages.LinkMessages;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.model.response.impl.ResponseData;
import com.dtb.saesc.api.services.LinkService;

@RestController
@RequestMapping("/links")
public class LinkController {
	
	@Autowired
	private LinkService service;
	
	@Autowired
	private Converter<Link, LinkDto> converter;

	@GetMapping
	public ResponseEntity<Response> buscarLinks(@RequestParam(name = "s", defaultValue = "") String s) {
		
		List<Link> links = service.buscarLinks(s).orElseThrow(() -> new ResourceNotFoundException(LinkMessages.LINK_NAO_ENCONTRADO));
		
		return ResponseEntity.ok(
				ResponseData.data(
						converter.toDto(LinkDto.class).convert(links)
						)
				);
	}

	@GetMapping("/escola/{id}")
	public ResponseEntity<Response> buscarLinksPorEscola(@PathVariable("id") Long id) {
		
		List<Link> links = service.buscarPorescola(id).orElseThrow(() -> new ResourceNotFoundException(LinkMessages.LINK_NAO_ENCONTRADO));
		
		return ResponseEntity.ok(
				ResponseData.data(
						converter.toDto(LinkDto.class).convert(links)
						)
				);
		
	}
}

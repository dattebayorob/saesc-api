package com.dtb.saesc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.converters.Converter;
import com.dtb.saesc.api.model.dtos.LinkDto;
import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.exceptions.ResourceNotFoundException;
import com.dtb.saesc.api.model.exceptions.messages.LinkMessages;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.model.response.impl.ResponseData;
import com.dtb.saesc.api.model.response.impl.ResponseError;
import com.dtb.saesc.api.services.LinkService;

@RestController
@RequestMapping("/links")
public class LinkController {
	
	@Autowired
	private LinkService service;
	
	@Autowired
	private Converter<Link, LinkDto> converter;
	
	@PostMapping
	public ResponseEntity<Response> adicionar(@Validated @RequestBody LinkDto dto){
		return new ResponseEntity<Response>(
				ResponseData.data(service
						.adicionar(converter
								.toEntity(Link.class)
								.convert(dto))
						.fold(ResponseError::exception,
								l -> converter.toDto(LinkDto.class).convert(l))
				), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response> atualizar(@PathVariable("id") Long id, @Validated @RequestBody LinkDto dto){
		Link link = service.buscarPeloId(id)
				.orElseThrow(() -> new ResourceNotFoundException(LinkMessages.LINK_NAO_ENCONTRADO));
		dto.setId(link.getId());
		String ip = link.getIp();
		return ResponseEntity.ok(
				ResponseData.data(service.atualizar(
						converter.toEntity(Link.class).convert(dto),
						ip).fold(ResponseError::exception,
								l -> converter.toDto(LinkDto.class).convert(l))
				));
	}

	
}

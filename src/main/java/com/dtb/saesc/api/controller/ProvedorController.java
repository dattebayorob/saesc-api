package com.dtb.saesc.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.converters.Converter;
import com.dtb.saesc.api.model.dtos.ProvedorDto;
import com.dtb.saesc.api.model.entities.Provedor;
import com.dtb.saesc.api.model.exceptions.ResourceNotFoundException;
import com.dtb.saesc.api.model.exceptions.messages.ProvedorMessages;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.model.response.impl.ResponseData;
import com.dtb.saesc.api.model.response.impl.ResponseError;
import com.dtb.saesc.api.services.ProvedorService;

@RestController
@RequestMapping(value = "/links/provedor")
@CrossOrigin(value = "*")
public class ProvedorController {

	@Autowired
	private ProvedorService service;
	@Autowired
	private Converter<Provedor, ProvedorDto> converter;

	@PostMapping
	public ResponseEntity<Response> adicionar(@Valid @RequestBody ProvedorDto provedorDto) {

		return ResponseEntity
				.ok(ResponseData.data(service
						.adicionar(converter
								.toEntity(Provedor.class)
								.convert(provedorDto))
						.fold(ResponseError::exception,
								e -> converter.toDto(ProvedorDto.class).convert(e))));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarPeloId(@PathVariable("id") Long id) {
		
		Provedor provedor = service.buscarPeloId(id)
				.orElseThrow(() -> new ResourceNotFoundException(ProvedorMessages.PROVEDOR_NAO_ENCONTRADO));
		
		return ResponseEntity.ok(
				ResponseData.data(converter.toDto(ProvedorDto.class).convert(provedor))
				);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> atualizarPeloId(@PathVariable("id") Long id, @Validated @RequestBody ProvedorDto dto) {
		
		Provedor provedor = service.buscarPeloId(id)
				.orElseThrow(() -> new ResourceNotFoundException(ProvedorMessages.PROVEDOR_NAO_ENCONTRADO));
		String cnpj = provedor.getCnpj();
		
		dto.setId(id);
		
		return ResponseEntity
				.ok(ResponseData.data(service
						.atualizar(converter
								.toEntity(provedor)
								.convert(dto),cnpj)
						.fold(ResponseError::exception,
								e -> converter.toDto(ProvedorDto.class).convert(e))));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> removerPeloId(@PathVariable("id") Long id) {
		if (!service.existePeloId(id))
			throw new ResourceNotFoundException(ProvedorMessages.PROVEDOR_NAO_ENCONTRADO);
		service.removerPeloId(id);
		return ResponseEntity.noContent().build();
	}
}

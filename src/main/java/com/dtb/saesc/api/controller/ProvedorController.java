package com.dtb.saesc.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.converters.EntityDtoConverter;
import com.dtb.saesc.api.model.dtos.ProvedorDto;
import com.dtb.saesc.api.model.entities.Provedor;
import com.dtb.saesc.api.model.exceptions.ResourceNotFoundException;
import com.dtb.saesc.api.model.exceptions.ValidationErrorsException;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.services.ProvedorService;

@RestController
@RequestMapping(value = "/links/provedor")
@CrossOrigin(value = "*")
public class ProvedorController {

	@Autowired
	private ProvedorService provedorService;
	@Autowired
	private EntityDtoConverter<ProvedorDto, Provedor> converter;
	
	private static final String PROVEDOR_NAO_ENCONTRADO = "Nenhum provedor encontrado";

	@PostMapping
	public ResponseEntity<Response> adicionar(@Valid @RequestBody ProvedorDto provedorDto) {
		Provedor provedor = converter.toEntity(provedorDto, new Provedor());
		try {
			provedorService.adicionar(provedor);
		} catch (ValidationErrorsException e) {
			return ResponseEntity.badRequest().body(Response.error(e.getErrors()));
		}
		return ResponseEntity.ok(Response.data(converter.toDto(provedor, provedorDto)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarPeloId(@PathVariable("id") Long id) {
		Optional<Provedor> provedorById = provedorService.buscarPeloId(id);
		if (!provedorById.isPresent())
			throw new ResourceNotFoundException(PROVEDOR_NAO_ENCONTRADO);
		return ResponseEntity.ok(Response.data(converter.toDto(provedorById.get(), new ProvedorDto())));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> atualizarPeloId(@PathVariable("id") Long id,
			@Valid @RequestBody ProvedorDto dto) {
		Optional<Provedor> provedorPeloId = provedorService.buscarPeloId(id);
		if (!provedorPeloId.isPresent())
			throw new ResourceNotFoundException(PROVEDOR_NAO_ENCONTRADO);
		try {
			String cnpj = provedorPeloId.get().getCnpj();
			provedorService.atualizar(converter.toEntity(dto, provedorPeloId.get()), cnpj);
		} catch (ValidationErrorsException e) {
			return ResponseEntity.badRequest().body(Response.error(e.getErrors()));
		}
		return ResponseEntity.ok(Response.data(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> removerPeloId(@PathVariable("id") Long id) {
		if (provedorService.existePeloId(id))
			throw new ResourceNotFoundException(PROVEDOR_NAO_ENCONTRADO);
		provedorService.removerPeloId(id);
		return ResponseEntity.noContent().build();
	}

}

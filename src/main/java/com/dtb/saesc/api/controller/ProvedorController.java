package com.dtb.saesc.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

	@PostMapping
	public ResponseEntity<Response> adicionar(@Valid @RequestBody ProvedorDto provedorDto) {
		Provedor provedor = converter.toEntity(provedorDto, new Provedor());
		provedorService.persistir(provedor);
		return ResponseEntity.ok(Response.data(converter.toDto(provedor, provedorDto)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarPeloId(@PathVariable("id") Long id) {
		Optional<Provedor> provedorById = provedorService.buscarPeloId(id);
		if (!provedorById.isPresent())
			throw new ResourceNotFoundException("Nenhum provedor encontrado");
		return ResponseEntity.ok(Response.data(converter.toDto(provedorById.get(),new ProvedorDto())));
	}


	@PutMapping("/{id}")
	public ResponseEntity<Response> atualizarPeloId(@PathVariable("id") Long id,
			@Valid @RequestBody ProvedorDto provedorDto) {
		Optional<Provedor> provedorById = provedorService.buscarPeloId(id);
		if (!provedorById.isPresent())
			throw new ResourceNotFoundException("Nenhum provedor encontrado");
		Provedor provedor = converter.toEntity(provedorDto, provedorById.get());
		provedorService.persistir(provedor);
		return ResponseEntity.ok(Response.data(provedorDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> removerPeloId(@PathVariable("id") Long id) {
		Optional<Provedor> provedor = provedorService.buscarPeloId(id);
		if (!provedor.isPresent())
			throw new ResourceNotFoundException("Nenhum provedor encontrado");
		provedorService.removerPeloId(id);
		return ResponseEntity.noContent().build();
	}

}

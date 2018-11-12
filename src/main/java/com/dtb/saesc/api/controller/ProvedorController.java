package com.dtb.saesc.api.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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

import com.dtb.saesc.api.model.dtos.ProvedorDto;
import com.dtb.saesc.api.model.entities.Provedor;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.services.ProvedorService;

@RestController
@RequestMapping(value = "/links/provedor")
@CrossOrigin(value = "*")
public class ProvedorController {

	@Autowired
	private ProvedorService provedorService;
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<Response> adicionar(@Valid @RequestBody ProvedorDto provedorDto, BindingResult result) {
		Provedor provedor = converterDtoParaProvedor(provedorDto, new Provedor());
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(Response.error(result.getAllErrors()));
		}
		provedorService.persistir(provedor);
		return ResponseEntity.ok(Response.data(converterProvedorParaDto(provedor,provedorDto)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarPeloId(@PathVariable("id") Long id) {
		Optional<Provedor> provedor = provedorService.buscarPeloId(id);
		if (!provedor.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(Response.data(converterProvedorParaDto(provedor.get(),new ProvedorDto())));
	}


	@PutMapping("/{id}")
	public ResponseEntity<Response> atualizarPeloId(@PathVariable("id") Long id,
			@Valid @RequestBody ProvedorDto provedorDto, BindingResult result) {
		Optional<Provedor> provedorById = provedorService.buscarPeloId(id);
		if (!provedorById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(Response.error(result.getAllErrors()));
		}
		provedorDto.setId(id);
		Provedor provedor = converterDtoParaProvedor(provedorDto, provedorById.get());
		provedorService.persistir(provedor);
		return ResponseEntity.ok(Response.data(provedorDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> removerPeloId(@PathVariable("id") Long id) {
		Optional<Provedor> provedor = provedorService.buscarPeloId(id);
		if (!provedor.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		provedorService.removerPeloId(id);
		return ResponseEntity.noContent().build();
	}

	private ProvedorDto converterProvedorParaDto(Provedor provedor, ProvedorDto provedorDto) {
		modelMapper.map(provedor, provedorDto);
		return provedorDto;
	}

	private Provedor converterDtoParaProvedor(ProvedorDto provedorDto, Provedor provedor) {
		modelMapper.map(provedorDto, provedor);
		return provedor;
	}

}

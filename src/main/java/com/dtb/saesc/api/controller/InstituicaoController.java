package com.dtb.saesc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.converters.Converter;
import com.dtb.saesc.api.model.dtos.InstituicaoDto;
import com.dtb.saesc.api.model.dtos.InstituicaoResumidoDto;
import com.dtb.saesc.api.model.entities.Instituicao;
import com.dtb.saesc.api.model.exceptions.ResourceNotFoundException;
import com.dtb.saesc.api.model.exceptions.messages.EscolaMessages;
import com.dtb.saesc.api.model.repositories.custom.filter.InstituicaoFilter;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.model.response.impl.ResponseData;
import com.dtb.saesc.api.model.response.impl.ResponseError;
import com.dtb.saesc.api.services.InstituicaoService;

@RestController
@RequestMapping(value = "/instituicoes")
@CrossOrigin(value = "*")
public class InstituicaoController {
	@Autowired
	private InstituicaoService service;
	@Autowired
	private Converter<Instituicao, InstituicaoDto> converter;
	@Autowired
	private Converter<Instituicao, InstituicaoResumidoDto> converterResumido;

	@GetMapping
	public ResponseEntity<Response> pesquisarInstituicaos(InstituicaoFilter filtros, Pageable page) {
		Page<Instituicao> escolas = service.pesquisarEscolas(filtros, page)
				.orElseThrow(() -> new ResourceNotFoundException(EscolaMessages.PESQUISA_SEM_RESULTADOS));
		return ResponseEntity.ok(
				ResponseData.data(
						converterResumido.toDto(InstituicaoResumidoDto.class).convert(escolas)
						)
				);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarPeloId(@PathVariable("id") Long id) {
		
		Instituicao escola = service.buscarPeloId(id)
				.orElseThrow(()-> new ResourceNotFoundException(EscolaMessages.ESCOLA_NAO_ENCONTRADA));
		return ResponseEntity.ok(
				ResponseData.data(
						converter.toDto(InstituicaoDto.class).convert(escola)
						)
				);

	}
	
	@PostMapping
	public ResponseEntity<Response> adicionar(@Validated @RequestBody InstituicaoDto dto) {
		
		Instituicao escola = converter.toEntity(Instituicao.class).convert(dto);
		
		return new ResponseEntity<>(
				ResponseData.data(
						service.adicionar(escola).fold(ResponseError::exception,
								e ->converter.toDto(InstituicaoDto.class).convert(e))
						),HttpStatus.CREATED
				);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> atualizar(@PathVariable("id") Long id, @Validated @RequestBody InstituicaoDto dto){
		
		Instituicao escola = service.buscarPeloId(id)
				.orElseThrow(() -> new ResourceNotFoundException(EscolaMessages.ESCOLA_NAO_ENCONTRADA));
		String inep = escola.getInep();
		dto.setId(id);
		return ResponseEntity.ok(
				ResponseData.data(
						service.atualizar(
								converter
								.toEntity(escola)
								.convert(dto), inep)
						.fold(ResponseError::exception,
								e -> converter.toDto(InstituicaoDto.class).convert(e))
						)
				);
	}

}
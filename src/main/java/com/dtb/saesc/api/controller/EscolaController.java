package com.dtb.saesc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.converters.Converter;
import com.dtb.saesc.api.model.dtos.EscolaDto;
import com.dtb.saesc.api.model.dtos.EscolaResumidoDto;
import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.exceptions.ResourceNotFoundException;
import com.dtb.saesc.api.model.exceptions.messages.EscolaMessages;
import com.dtb.saesc.api.model.repositories.custom.filter.EscolaFilter;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.model.response.impl.ResponseData;
import com.dtb.saesc.api.model.response.impl.ResponseError;
import com.dtb.saesc.api.services.EscolaService;

@RestController
@RequestMapping(value = "/escolas")
@CrossOrigin(value = "*")
public class EscolaController {
	@Autowired
	private EscolaService service;
	@Autowired
	private Converter<Escola, EscolaDto> converter;
	@Autowired
	private Converter<Escola, EscolaResumidoDto> converterResumido;

	/**
	 * Retorna todas as escolas paginadas de acordo com o criterio de pesquisa
	 * 
	 * @param filtros
	 * @param page
	 * 
	 * @return ResponseEntity<Response>
	 *
	 * @throws ResourceNotFoundException
	 * 
	 */

	@GetMapping
	public ResponseEntity<Response> pesquisarEscolas(EscolaFilter filtros, Pageable page) {
		
		Page<Escola> escolas = service.pesquisarEscolas(filtros, page)
				.orElseThrow(() -> new ResourceNotFoundException(EscolaMessages.PESQUISA_SEM_RESULTADOS));
		return ResponseEntity.ok(
				ResponseData.data(
						converterResumido.toDto(EscolaResumidoDto.class).convert(escolas)
						)
				);

	}

	/**
	 * Retorna as informações da escola de acordo com o id passado na url
	 * 
	 * @param id
	 * @return ResponseEntity<Response>
	 * 
	 * @throws ResourceNotFoundException
	 * 
	 */
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarPeloId(@PathVariable("id") Long id) {
		
		Escola escola = service.buscarPeloId(id)
				.orElseThrow(()-> new ResourceNotFoundException(EscolaMessages.ESCOLA_NAO_ENCONTRADA));
		return ResponseEntity.ok(
				ResponseData.data(
						converter.toDto(EscolaDto.class).convert(escola)
						)
				);

	}

	/**
	 * 
	 * Adiciona uma escola
	 * 
	 * @param dto
	 * 
	 * @return ResponseEntity<Response>
	 * 
	 * @throws MethodArgumentNotValidException
	 * @throws ResourceNotFoundException
	 * 
	 */
	
	@PostMapping
	public ResponseEntity<Response> adicionar(@Validated @RequestBody EscolaDto dto) {
		
		Escola escola = converter.toEntity(Escola.class).convert(dto);
		
		return new ResponseEntity<>(
				ResponseData.data(
						service.adicionar(escola).fold(ResponseError::exception,
								e ->converter.toDto(EscolaDto.class).convert(e))
						),HttpStatus.CREATED
				);

	}

	/**
	 * 
	 * Atualiza uma escola
	 * 
	 * @param id;
	 * @param dto
	 * 
	 * @return ResponseEntity<Response>
	 * 
	 * @throws MethodArgumentNotValidException
	 * @throws ResourceNotFoundException
	 * 
	 */

	@PutMapping("/{id}")
	public ResponseEntity<Response> atualizar(@PathVariable("id") Long id, @Validated @RequestBody EscolaDto dto){
		
		Escola escola = service.buscarPeloId(id)
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
								e -> converter.toDto(EscolaDto.class).convert(e))
						)
				);
	}

}
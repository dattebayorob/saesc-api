package com.dtb.saesc.api.controller;

import java.util.Optional;

import javax.validation.Valid;

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

import com.dtb.saesc.api.model.converters.EntityDtoConverter;
import com.dtb.saesc.api.model.dtos.EscolaDto;
import com.dtb.saesc.api.model.dtos.EscolaResumidoDto;
import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.exceptions.ResourceNotFoundException;
import com.dtb.saesc.api.model.exceptions.ValidationErrorsException;
import com.dtb.saesc.api.model.repositories.EscolaRepository;
import com.dtb.saesc.api.model.repositories.custom.filter.EscolaFilter;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.services.EscolaService;

@RestController
@RequestMapping(value = "/escolas")
@CrossOrigin(value = "*")
public class EscolaController {
	@Autowired
	private EscolaService escolaService;
	@Autowired
	private EntityDtoConverter<EscolaDto, Escola> converter;
	@Autowired
	private EntityDtoConverter<EscolaResumidoDto, Escola> converterResumido;

	/**
	 * Retorna todas as escolas paginadas de acordo com o criterio de pesquisa
	 * 
	 * @param filtros
	 * @param page
	 * 
	 * @return ResponseEntity<Response>
	 *
	 * 
	 */

	@GetMapping
	public ResponseEntity<Response> pesquisarEscolas(EscolaFilter filtros, Pageable page) {
		Page<Escola> escolas = escolaService.pesquisarEscolas(filtros, page);
		return responseEntityParaEscolaPaginado(escolas);

	}

	/**
	 * 
	 * Retorna uma entidade rest 200 ou 204
	 * 
	 * @param Page<Escola> escolas
	 * @return ResponseEntity
	 * 
	 * @throws ResourceNotFoundException
	 * 
	 */

	private ResponseEntity<Response> responseEntityParaEscolaPaginado(Page<Escola> escolas) {
		if (!escolas.hasContent()) {
			throw new ResourceNotFoundException("Escola não encontrada para o Id informado.");
		}
		Page<EscolaResumidoDto> escolasDto = escolas
				.map(escola -> converterResumido.toDto(escola, EscolaResumidoDto.class));
		return ResponseEntity.ok(Response.data(escolasDto));
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
		Optional<Escola> escolaPeloId = escolaService.buscarPeloId(id);
		if (!escolaPeloId.isPresent()) {
			throw new ResourceNotFoundException("Escola não encontrada para o Id informado.");
		}
		EscolaDto escolaDto = converter.toDto(escolaPeloId.get(), EscolaDto.class);
		return ResponseEntity.ok(Response.data(escolaDto));

	}

	/**
	 * 
	 * Adiciona uma escola
	 * 
	 * @param EscolaDto
	 * @param result
	 * 
	 * @return ResponseEntity<Response>
	 * 
	 */
	@PostMapping
	public ResponseEntity<Response> adicionar(@Validated @RequestBody EscolaDto dto) {
		Escola escola = converter.toEntity(dto, Escola.class);
		try {
			escola = escolaService.adicionar(escola);
		} catch (ValidationErrorsException e) {
			return ResponseEntity.badRequest().body(Response.error(e.getErrors()));
		}

		dto = converter.toDto(escola, dto);
		return new ResponseEntity<Response>(Response.data(dto), HttpStatus.CREATED);

	}
	
	/**
	 * 
	 * Atualiza uma escola
	 * 
	 * @param id;
	 * @param dto
	 * 
	 * @throws ResourceNotFoundException
	 * 
	 * */

	@PutMapping("/{id}")
	public ResponseEntity<Response> atualizar(@PathVariable("id") Long id, @Valid @RequestBody EscolaDto dto) {
		Optional<Escola> escolaPeloId = escolaService.buscarPeloId(id);
		if (!escolaPeloId.isPresent()) {
			throw new ResourceNotFoundException("Escola não encontrada para o Id informado.");
		}

		try {
			String inep = escolaPeloId.get().getInep();
			Escola escola = escolaService.atualizar(converter.toEntity(dto, escolaPeloId.get()),inep);
			dto = converter.toDto(escola, dto);
		} catch (ValidationErrorsException e) {
			return ResponseEntity.badRequest().body(Response.error(e.getErrors()));
		}

		return ResponseEntity.ok(Response.data(dto));
	}

}
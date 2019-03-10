package com.dtb.saesc.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
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

import com.dtb.saesc.api.model.converters.EntityDtoConverter;
import com.dtb.saesc.api.model.dtos.EscolaDto;
import com.dtb.saesc.api.model.dtos.EscolaResumidoDto;
import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.exceptions.ResourceNotFoundException;
import com.dtb.saesc.api.model.exceptions.ValidationErrorsException;
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

	private static final String ESCOLA_NAO_ENCONTRADA = "Escola não encontrada para o Id informado.";
	private static final String INEP_JA_CADASTRADO = "Inep já cadastrado";

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
			throw new ResourceNotFoundException(ESCOLA_NAO_ENCONTRADA);
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
			throw new ResourceNotFoundException(ESCOLA_NAO_ENCONTRADA);
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
	 * @throws MethodArgumentNotValidException
	 * 
	 */
	@PostMapping
	public ResponseEntity<Response> adicionar(@Validated @RequestBody EscolaDto dto) {
		Escola escola = converter.toEntity(dto, Escola.class);
		Optional<Escola> e = escolaService.adicionar(escola);
		dto = converter.toDto(
				e.orElseThrow(() -> new ValidationErrorsException(new ObjectError("Escola", INEP_JA_CADASTRADO))), dto);
		return new ResponseEntity<>(Response.data(dto), HttpStatus.CREATED);

	}

	/**
	 * 
	 * Atualiza uma escola
	 * 
	 * @param     id;
	 * @param dto
	 * @throws MethodArgumentNotValidException
	 * 
	 * @throws ResourceNotFoundException
	 * 
	 */

	@PutMapping("/{id}")
	public ResponseEntity<Response> atualizar(@PathVariable("id") Long id, @Valid @RequestBody EscolaDto dto)
			throws MethodArgumentNotValidException {
		Optional<Escola> escolaPeloId = escolaService.buscarPeloId(id);
		if (!escolaPeloId.isPresent()) {
			throw new ResourceNotFoundException(ESCOLA_NAO_ENCONTRADA);
		}
		String inep = escolaPeloId.get().getInep();
		Optional<Escola> escola = escolaService.atualizar(converter.toEntity(dto, escolaPeloId.get()), inep);
		dto = converter.toDto(
				escola.orElseThrow(() -> new ValidationErrorsException(new ObjectError("Escola", INEP_JA_CADASTRADO))),
				dto);

		return ResponseEntity.ok(Response.data(dto));
	}

}
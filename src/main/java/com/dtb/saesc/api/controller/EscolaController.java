package com.dtb.saesc.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
import com.dtb.saesc.api.model.enums.PrefixoEnum;
import com.dtb.saesc.api.model.repositories.custom.filter.EscolaFilter;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.model.utils.EnumUtils;
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
	 */

	private ResponseEntity<Response> responseEntityParaEscolaPaginado(Page<Escola> escolas) {
		if (!escolas.hasContent()) {
			return retornoParaNotFound();
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
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarPeloId(@PathVariable("id") Long id) {
		Optional<Escola> escolaPeloId = escolaService.buscarPeloId(id);
		if (!escolaPeloId.isPresent()) {
			return retornoParaNotFound();
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
	 */
	@PostMapping
	public ResponseEntity<Response> adicionar(@Valid @RequestBody EscolaDto dto, BindingResult result) {
		Escola escola = converter.toEntity(dto, Escola.class);
		existeEscolaPeloInep(dto.getInep(), result);
		verificaPrefixo(dto.getInep(), result);
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(Response.error(result.getAllErrors()));
		}
		escola = escolaService.persistir(escola);
		dto = converter.toDto(escola, dto);
		return ResponseEntity.ok(Response.data(dto));

	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> atualizar(@PathVariable("id") Long id, @Valid @RequestBody EscolaDto escolaDto,
			BindingResult result) {
		Optional<Escola> escolaPeloId = escolaService.buscarPeloId(id);
		if (!escolaPeloId.isPresent()) {
			return retornoParaNotFound();
		}
		Escola escola = validaEAtualizaEscola(escolaPeloId.get(), escolaDto, result);
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(Response.error(result.getAllErrors()));
		}
		escolaService.persistir(escola);
		escolaDto = converter.toDto(escola, escolaDto);
		return ResponseEntity.ok(Response.data(escolaDto));
	}

	/**
	 * 
	 * Valida dados especificos para entidade Escola e entao converte para um Dto
	 * 
	 * @param escola
	 * @param escolaDto
	 * @param result
	 * 
	 */

	private Escola validaEAtualizaEscola(Escola escola, EscolaDto dto, BindingResult result) {
		if (!escola.getInep().equals(dto.getInep())) {
			existeEscolaPeloInep(dto.getInep(), result);
		}
		verificaPrefixo(dto.getPrefixo(), result);
		return converter.toEntity(dto, escola);
	}

	/**
	 * Checa se já existe alguma escola cadastrada com o inep informado
	 * 
	 * @param inep
	 * @param result
	 * 
	 */

	private void existeEscolaPeloInep(String inep, BindingResult result) {
		if (escolaService.existePeloInep(inep))
			result.addError(new ObjectError("Escola", "Inep já cadastrado"));
	}

	private void verificaPrefixo(String s, BindingResult result) {
		if (!EnumUtils.isValid(s, PrefixoEnum.values()))
			result.addError(new ObjectError("Escola", "Prefixo invalido."));
	}

	private ResponseEntity<Response> retornoParaNotFound() {
		String notFoundMsg = "Nenhuma escola encontrada";
		return new ResponseEntity<Response>(Response.error(notFoundMsg), HttpStatus.NOT_FOUND);
	}

}
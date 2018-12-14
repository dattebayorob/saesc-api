package com.dtb.saesc.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.converters.EntityDtoConverter;
import com.dtb.saesc.api.model.dtos.EscolaDto;
import com.dtb.saesc.api.model.dtos.EscolaResumidoDto;
import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.enums.CredeEnum;
import com.dtb.saesc.api.model.enums.PrefixoEnum;
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
	 * @param order
	 * @param size
	 * @param dir
	 * @param s
	 * @return ResponseEntity<Response>
	 *
	 * 
	 */

	@GetMapping
	public ResponseEntity<Response> buscarEscolas(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir,
			@RequestParam(value = "s", defaultValue = "") String s) {
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(dir), order);
		Page<Escola> escolas = escolaService.buscarTodas(pageRequest, s);
		return responseEntityParaEscolaPaginado(escolas);
	}

	/**
	 * Retorna todas as escolas por crede paginadas de acordo com o criterio de
	 * pesquisa
	 * 
	 * @param order
	 * @param size
	 * @param dir
	 * @param s
	 * @param crede
	 * @return ResponseEntity<Response>
	 *
	 * 
	 */

	@GetMapping("/crede/{crede}")
	public ResponseEntity<Response> buscarEscolasPorCrede(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir,
			@RequestParam(value = "s", defaultValue = "") String s, @PathVariable("crede") String crede) {
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(dir), order);
		try {
			Page<Escola> escolas = escolaService.buscarTodasPorCrede(pageRequest,
					CredeEnum.valueOf(crede.toUpperCase()), s);
			return responseEntityParaEscolaPaginado(escolas);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * Retorna todas as escolas por prefixo paginadas de acordo com o criterio de
	 * pesquisa
	 * 
	 * @param order
	 * @param size
	 * @param dir
	 * @param s
	 * @param prefixo
	 * @return ResponseEntity<Response>
	 *
	 * 
	 */

	@GetMapping("/prefixo/{prefixo}")
	public ResponseEntity<Response> buscarEscolasPorPrefixo(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir,
			@RequestParam(value = "s", defaultValue = "") String s, @PathVariable("prefixo") String pref) {
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(dir), "id");
		try {
			Page<Escola> escolas = escolaService.buscarTodasPorPrefixo(pageRequest,
					PrefixoEnum.valueOf(pref.toUpperCase()), s);
			return responseEntityParaEscolaPaginado(escolas);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.noContent().build();
		}
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
		if (!escolas.hasContent())
			return ResponseEntity.noContent().build();
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
			return ResponseEntity.notFound().build();
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
	public ResponseEntity<Response> adicionar(@Valid @RequestBody EscolaDto escolaDto, BindingResult result) {
		Escola escola = converter.toEntity(escolaDto, Escola.class);
		validaEscolaInep(escola.getInep(), result);
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(Response.error(result.getAllErrors()));
		}
		escola = escolaService.persistir(escola);
		escolaDto = converter.toDto(escola, escolaDto);
		return ResponseEntity.ok(Response.data(escolaDto));

	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> atualizar(@PathVariable("id") Long id, @Valid @RequestBody EscolaDto escolaDto,
			BindingResult result) {
		Optional<Escola> escolaPeloId = escolaService.buscarPeloId(id);
		if (!escolaPeloId.isPresent()) {
			return ResponseEntity.notFound().build();
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

	private Escola validaEAtualizaEscola(Escola escola, EscolaDto escolaDto, BindingResult result) {
		if (!escola.getInep().equals(escolaDto.getInep())) {
			validaEscolaInep(escolaDto.getInep(), result);
		}
		return converter.toEntity(escolaDto, escola);
	}

	/**
	 * Checa se já existe alguma escola cadastrada com o inep informado
	 * 
	 * @param inep
	 * @param result
	 * 
	 */

	private void validaEscolaInep(String inep, BindingResult result) {
		Optional<Escola> escolaPeloInep = escolaService.buscarPeloInep(inep);
		if (escolaPeloInep.isPresent()) {
			result.addError(new ObjectError("escola", "Escola já cadastrada com o inep informado"));
		}

	}

}
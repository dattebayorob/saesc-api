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
import com.dtb.saesc.api.model.entities.Escola;
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

	@GetMapping
	public ResponseEntity<Response> buscarEscolas(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		PageRequest pageRequest = PageRequest.of(page, 10, Direction.valueOf(dir), order);
		Page<Escola> escolas = escolaService.buscarTodas(pageRequest);
		Page<EscolaDto> escolasDto = escolas.map(escola -> converterEscolaParaDto(escola, new EscolaDto()));
		return ResponseEntity.ok(Response.data(escolasDto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarPeloId(@PathVariable("id") Long id) {
		Optional<Escola> escolaPeloId = escolaService.buscarPeloId(id);
		if (!escolaPeloId.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		EscolaDto escolaDto = converterEscolaParaDto(escolaPeloId.get(), new EscolaDto());
		return ResponseEntity.ok(Response.data(escolaDto));

	}

	@PostMapping
	public ResponseEntity<Response> adicionar(@Valid @RequestBody EscolaDto escolaDto, BindingResult result) {
		Escola escola = converterDtoParaEscola(escolaDto, new Escola());
		validaEscolaInep(escola.getInep(), result);
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(Response.error(result.getAllErrors()));
		}
		escola = escolaService.persistir(escola);
		escolaDto = converterEscolaParaDto(escola, escolaDto);
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
		escolaDto = converterEscolaParaDto(escola, escolaDto);
		return ResponseEntity.ok(Response.data(escolaDto));
	}

	private Escola validaEAtualizaEscola(Escola escola, EscolaDto escolaDto, BindingResult result) {
		System.out.println(escolaDto.toString());
		if (!escola.getInep().equals(escolaDto.getInep())) {
			validaEscolaInep(escolaDto.getInep(), result);
		}
		// Não sei porque diabos o id não é mapeado no modellmapper ver depois isso.
		escolaDto.setId(escola.getId());
		return converterDtoParaEscola(escolaDto, escola);
	}

	private void validaEscolaInep(String inep, BindingResult result) {
		Optional<Escola> escolaPeloInep = escolaService.buscarPeloInep(inep);
		if (escolaPeloInep.isPresent()) {
			result.addError(new ObjectError("escola", "Escola já cadastrada com o inep informado"));
		}

	}

	private Escola converterDtoParaEscola(EscolaDto escolaDto, Escola escola) {
		converter.toEntity(escolaDto, escola);
		return escola;
	}

	private EscolaDto converterEscolaParaDto(Escola escola, EscolaDto escolaDto) {
		converter.toDto(escola, escolaDto);
		return escolaDto;
	}

}
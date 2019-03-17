package com.dtb.saesc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.converters.Converter;
import com.dtb.saesc.api.model.dtos.EquipamentoCadastroDto;
import com.dtb.saesc.api.model.dtos.EquipamentoDto;
import com.dtb.saesc.api.model.dtos.EquipamentoResumidoDto;
import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.Funcionario;
import com.dtb.saesc.api.model.exceptions.ResourceNotFoundException;
import com.dtb.saesc.api.model.exceptions.messages.EquipamentoMessages;
import com.dtb.saesc.api.model.repositories.custom.filter.EquipamentoFilter;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.model.response.impl.ResponseData;
import com.dtb.saesc.api.model.response.impl.ResponseError;
import com.dtb.saesc.api.services.EquipamentoService;
import com.dtb.saesc.api.services.FuncionarioService;

@RestController
@RequestMapping(value = "/equipamentos")
public class EquipamentoController {
	@Autowired
	private EquipamentoService service;
	@Autowired
	private FuncionarioService userService;
	@Autowired
	private Converter<Equipamento, EquipamentoDto> converter;
	@Autowired
	private Converter<Equipamento,EquipamentoCadastroDto> converterCadastro;
	@Autowired
	private Converter<Equipamento, EquipamentoResumidoDto> converterResumido;

	@GetMapping
	public ResponseEntity<Response> buscartodos(EquipamentoFilter filter, Pageable pageable) {
		
		Page<Equipamento> equipamentos = service.buscarPaginaPorFiltros(filter, pageable)
				.orElseThrow(() -> new ResourceNotFoundException(EquipamentoMessages.PESQUISA_SEM_RESULTADOS));
		
		return ResponseEntity.ok(
				ResponseData.data(
						converterResumido.toDto(EquipamentoResumidoDto.class).convert(equipamentos)
						)
				);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarPeloId(@PathVariable("id") Long id) {
		
		Equipamento equipamento = service.buscarPeloId(id)
				.orElseThrow(() -> new ResourceNotFoundException(EquipamentoMessages.EQUIP_NAO_ENCONTRADO));
		
		return ResponseEntity.ok(
				ResponseData.data(
						converter.toDto(EquipamentoDto.class).convert(equipamento)
						)
				);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> atualizarPeloId(@PathVariable("id") Long id,
			@Validated @RequestBody EquipamentoCadastroDto dto) {
		
		Equipamento equipamento = service.buscarPeloId(id)
				.orElseThrow(() -> new ResourceNotFoundException(EquipamentoMessages.EQUIP_NAO_ENCONTRADO));
		
		return ResponseEntity.ok(
				ResponseData.data(
						service.persistir(
								converterCadastro.toEntity(equipamento).convert(dto),
								dto.getComentario(),
								buscarFuncionarioContext())
						.fold(
								ResponseError::exception,
								e -> converter
									.toDto(EquipamentoDto.class)
									.convert(e))
						)
				);
	}

	@PostMapping
	public ResponseEntity<Response> adicionar(@Validated @RequestBody EquipamentoCadastroDto dto) {
		return ResponseEntity.ok(
				ResponseData.data(
						service.persistir(
								converterCadastro.toEntity(Equipamento.class).convert(dto),
								dto.getComentario(),
								buscarFuncionarioContext())
						.fold(
								ResponseError::exception,
								e -> converter
									.toDto(EquipamentoDto.class)
									.convert(e))
						)
				);
	}

	private Funcionario buscarFuncionarioContext(){
		return userService.buscarPeloContexto();
	}

}

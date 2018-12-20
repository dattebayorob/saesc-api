package com.dtb.saesc.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.converters.EntityDtoConverter;
import com.dtb.saesc.api.model.dtos.EquipamentoCadastroDto;
import com.dtb.saesc.api.model.dtos.EquipamentoDto;
import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.Funcionario;
import com.dtb.saesc.api.model.exceptions.ResourceNotFoundException;
import com.dtb.saesc.api.model.repositories.custom.filter.EquipamentoFilter;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.services.EquipamentoService;
import com.dtb.saesc.api.services.FuncionarioService;

@RestController
@RequestMapping(value = "/equipamentos")
public class EquipamentoController {
	@Autowired
	private EquipamentoService equipamentoService;
	@Autowired
	private FuncionarioService userService;
	@Autowired
	private EntityDtoConverter<EquipamentoDto, Equipamento> converter;
	@Autowired
	private EntityDtoConverter<EquipamentoCadastroDto, Equipamento> cadastroConverter;

	@GetMapping
	public ResponseEntity<Response> buscartodos(EquipamentoFilter filter, Pageable pageable) {
		Page<EquipamentoDto> dtos = equipamentoService.buscarPaginaPorFiltros(filter, pageable)
				.map(e -> converter.toDto(e, EquipamentoDto.class));
		return dtos.hasContent() ? ResponseEntity.ok(Response.data(dtos)) : ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarPeloId(@PathVariable("id") Long id) {
		Optional<Equipamento> equipamentoPeloId = equipamentoService.buscarPeloId(id);
		if (!equipamentoPeloId.isPresent())
			throw new ResourceNotFoundException("Equipamento não encontrado pro id " + id);
		EquipamentoDto equipamentoDto = converter.toDto(equipamentoPeloId.get(), EquipamentoDto.class);
		return ResponseEntity.ok(Response.data(equipamentoDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> atualizarPeloId(@PathVariable("id") Long id,
			@Valid @RequestBody EquipamentoCadastroDto dto) {
		if (!equipamentoService.existePeloId(id))
			throw new ResourceNotFoundException("Equipamento não encontrado pro id " + id);
		Optional<Equipamento> equipamentoPeloId = equipamentoService.buscarPeloId(id);
		Equipamento equipamento = equipamentoService.persistir(cadastroConverter.toEntity(dto, equipamentoPeloId.get()),
				dto.getComentario(), buscarFuncionarioContext());
		return ResponseEntity.ok(Response.data(converter.toDto(equipamento, EquipamentoDto.class)));
	}

	@PostMapping
	public ResponseEntity<Response> adicionar(@Valid @RequestBody EquipamentoCadastroDto dto) {
		Equipamento equipamento = cadastroConverter.toEntity(dto, Equipamento.class);
		equipamentoService.persistir(equipamento, dto.getComentario(), buscarFuncionarioContext());
		return ResponseEntity.ok(Response.data(converter.toDto(equipamento, EquipamentoDto.class)));
	}

	private Funcionario buscarFuncionarioContext() {
		return userService.buscarPeloContexto();
	}

}

package com.dtb.saesc.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
		Page<Equipamento> equipamentos = equipamentoService.buscarPaginaPorFiltros(filter, pageable);
		Page<EquipamentoDto> dto = equipamentos.map(e -> converter.toDto(e, EquipamentoDto.class));
		return ResponseEntity.ok(Response.data(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarPeloId(@PathVariable("id") Long id) {
		Optional<Equipamento> equipamentoPeloId = equipamentoService.buscarPeloId(id);
		if (!equipamentoPeloId.isPresent())
			return new ResponseEntity<Response>(Response.error("Equipamento não encontrado pro id: "+id) , HttpStatus.NOT_FOUND);
		EquipamentoDto equipamentoDto = converter.toDto(equipamentoPeloId.get(), EquipamentoDto.class);
		return ResponseEntity.ok(Response.data(equipamentoDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> atualizarPeloId(@PathVariable("id") Long id,
			@Valid @RequestBody EquipamentoCadastroDto dto, BindingResult result) {
		if (!equipamentoService.existePorId(id)) {
			return new ResponseEntity<Response>(Response.error("Equipamento não encontrado pro id: "+id) , HttpStatus.NOT_FOUND);
		}
		Optional<Equipamento> equipamentoPeloId = equipamentoService.buscarPeloId(id);
		Equipamento equipamento = cadastroConverter.toEntity(dto, equipamentoPeloId.get());
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(Response.error(result.getAllErrors()));
		}
		equipamentoService.persistir(equipamento, dto.getComentario(), buscarFuncionarioContext());
		return ResponseEntity.ok(Response.data(converter.toDto(equipamento, EquipamentoDto.class)));
	}

	@PostMapping
	public ResponseEntity<Response> adicionar(@Valid @RequestBody EquipamentoCadastroDto dto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(Response.error(result.getAllErrors()));
		}
		Equipamento equipamento = cadastroConverter.toEntity(dto, Equipamento.class);
		equipamentoService.persistir(equipamento, dto.getComentario(), buscarFuncionarioContext());
		return ResponseEntity.ok(Response.data(converter.toDto(equipamento, EquipamentoDto.class)));
	}

	private Funcionario buscarFuncionarioContext() {
		return userService.buscarPeloContexto();
	}

}

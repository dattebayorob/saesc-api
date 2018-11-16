package com.dtb.saesc.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.internal.bytebuddy.implementation.bind.MethodDelegationBinder.BindingResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.converters.EntityDtoConverter;
import com.dtb.saesc.api.model.dtos.EquipamentoCadastroDto;
import com.dtb.saesc.api.model.dtos.EquipamentoDto;
import com.dtb.saesc.api.model.dtos.EquipamentoHistoricoDto;
import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.EquipamentoHistorico;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.services.EquipamentoService;

@RestController
@RequestMapping(value = "/equipamentos")
public class EquipamentoController {
	@Autowired
	private EquipamentoService equipamentoService;
	@Autowired
	private EntityDtoConverter<EquipamentoDto, Equipamento> converter;
	@Autowired
	private EntityDtoConverter<EquipamentoCadastroDto, Equipamento> cadastroConverter;
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarPeloId(@PathVariable("id") Long id){
		Optional<Equipamento> equipamentoPeloId = equipamentoService.buscarPeloId(id);
		if(!equipamentoPeloId.isPresent())
			return ResponseEntity.notFound().build();
		EquipamentoDto equipamentoDto = converter.toDto(equipamentoPeloId.get(), EquipamentoDto.class);
		return ResponseEntity.ok(Response.data(equipamentoDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response> atualizarPeloId(@PathVariable("id") Long id,@Valid @RequestBody EquipamentoCadastroDto equipamentoCadastroDto,BindingResult result){
		if(!equipamentoService.hasEquipamento(id)) {
			return ResponseEntity.notFound().build();
		}
		Optional<Equipamento> equipamentoPeloId = equipamentoService.buscarPeloId(id);
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(Response.error(result.getAllErrors()));
		}
		equipamentoCadastroDto.setId(id);
		Equipamento equipamento = cadastroConverter.toEntity(equipamentoCadastroDto, Equipamento.class);
		equipamento.setDataCriacao(equipamentoPeloId.get().getDataCriacao());
		equipamentoService.persistir(equipamento);
		return ResponseEntity.ok(Response.data(converter.toDto(equipamento, EquipamentoDto.class)));
	}
	
	@PostMapping
	public ResponseEntity<Response> adicionar(@Valid @RequestBody EquipamentoCadastroDto equipamentoCadastroDto, BindingResult result){
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(Response.error(result.getAllErrors()));
		}
		Equipamento equipamento = cadastroConverter.toEntity(equipamentoCadastroDto, Equipamento.class);
		equipamentoService.persistir(equipamento);
		return ResponseEntity.ok(Response.data(cadastroConverter.toDto(equipamento, equipamentoCadastroDto)));
	}
	
}

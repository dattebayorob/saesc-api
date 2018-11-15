package com.dtb.saesc.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.converters.EntityDtoConverter;
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
	private EntityDtoConverter<EquipamentoHistoricoDto, EquipamentoHistorico> historicoConverter;
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarPeloId(@PathVariable("id") Long id){
		Optional<Equipamento> equipamentoPeloId = equipamentoService.buscarPeloId(id);
		if(!equipamentoPeloId.isPresent())
			return ResponseEntity.notFound().build();
		EquipamentoDto equipamentoDto = converter.toDto(equipamentoPeloId.get(), EquipamentoDto.class);
		return ResponseEntity.ok(Response.data(equipamentoDto));
	}
	
}

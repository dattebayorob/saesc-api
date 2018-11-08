package com.dtb.saesc.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.dtos.ProvedorDto;
import com.dtb.saesc.api.model.entities.Provedor;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.services.ProvedorService;

@RestController
@RequestMapping(value="/links/provedor")
@CrossOrigin(value="*")
public class ProvedorController {
	
	@Autowired
	private ProvedorService provedorService;
	@Autowired
	private ModelMapper modelMapper;
	private static Response<ProvedorDto> response = new Response<>();
	
	@PostMapping
	public ResponseEntity<Response<ProvedorDto>> adicionar(@Valid @RequestBody ProvedorDto provedorDto,BindingResult result){
		Provedor provedor = converterDtoParaProvedor(provedorDto);
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		provedorService.persistir(provedor);
		response.setData(provedorDto = converterProvedorParaDto(provedor));
		return ResponseEntity.ok(response);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Response<ProvedorDto>> buscarPeloId(@PathVariable("id") Long id){
		Optional<Provedor> provedor = provedorService.buscarPeloId(id);
		if(!provedor.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		ProvedorDto provedorDto = modelMapper.map(provedor.get(), ProvedorDto.class);
		response.setData(provedorDto);
		return ResponseEntity.ok(response);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Response<ProvedorDto>> atualizarPeloId(@PathVariable("id") Long id, @Valid @RequestBody ProvedorDto provedorDto,BindingResult result){
		Optional<Provedor> provedor = provedorService.buscarPeloId(id);
		if(!provedor.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		provedorDto.setId(id);
		Provedor provedorSalvo = converterDtoParaProvedor(provedorDto);
		provedorService.persistir(provedorSalvo);
		response.setData(provedorDto);
		return ResponseEntity.ok(response);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ProvedorDto> removerPeloId(@PathVariable("id") Long id){
		Optional<Provedor> provedor = provedorService.buscarPeloId(id);
		if(!provedor.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		provedorService.removerPeloId(id);
		return ResponseEntity.noContent().build();
	}

	private ProvedorDto converterProvedorParaDto(Provedor provedor) {
		return modelMapper.map(provedor, ProvedorDto.class);
	}

	private Provedor converterDtoParaProvedor(ProvedorDto provedorDto) {
		return modelMapper.map(provedorDto, Provedor.class);
	}
}

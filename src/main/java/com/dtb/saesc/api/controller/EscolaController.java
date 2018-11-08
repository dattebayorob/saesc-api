package com.dtb.saesc.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.dtos.EscolaDto;
import com.dtb.saesc.api.model.dtos.LinkDto;
import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.services.LinkService;
import com.dtb.saesc.api.services.EscolaService;

@RestController
@RequestMapping(value="/escolas")
@CrossOrigin(value="*")
public class EscolaController {
	@Autowired
	private EscolaService escolaService;
	@Autowired
	private LinkService escolaLinkService;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<Response<Page<EscolaDto>>> buscarEscolas(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir
			){
		Response<Page<EscolaDto>> response = new Response<>();
		PageRequest pageRequest = PageRequest.of(page, 10, Direction.valueOf(dir), order);
		Page<Escola> escolas = escolaService.buscarTodas(pageRequest);
		Page<EscolaDto> escolasDto = escolas.map(escolaDto -> converterEscolaParaDto(escolaDto));
		response.setData(escolasDto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}/links")
	public ResponseEntity<Response<List<LinkDto>>> buscarEscolaLinks(@PathVariable("id") Long idEscola){
		Response<List<LinkDto>> response = new Response<>();
		Optional<Escola> escola = escolaService.buscarPeloId(idEscola);
		if(!escola.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		List<Link> ips = escolaLinkService.buscarPorescola(escola.get());
		List<LinkDto> ipsDto = new ArrayList<>();
		ips.forEach(ip -> ipsDto.add(converterLinksParaDto(ip)));
		response.setData(ipsDto);
		return ResponseEntity.ok(response);
	}
	private EscolaDto converterEscolaParaDto(Escola escola) {
		EscolaDto escolaDto = modelMapper.map(escola, EscolaDto.class);
		List<Link> links = escolaLinkService.buscarPorescola(escola);
		links.forEach(link -> escolaDto.getLinksIp().add(
				link.getProvedor().getNome()+" - "+link.getIp()));
		return escolaDto;
	}
	private LinkDto converterLinksParaDto(Link escolaLink) {
		LinkDto linkDto = modelMapper.map(escolaLink, LinkDto.class);
		return linkDto;
	}
}

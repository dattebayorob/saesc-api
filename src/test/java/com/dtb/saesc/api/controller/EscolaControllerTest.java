package com.dtb.saesc.api.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.converters.EntityDtoConverter;
import com.dtb.saesc.api.model.dtos.EscolaDto;
import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.services.EscolaService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EscolaControllerTest {
	@Autowired
	private EscolaService service;
	@Autowired
	private EntityDtoConverter<EscolaDto, Escola> converter;
	private EscolaDto dto;
	private Escola escola;

	@Before
	public void init() {
		EscolaDto dto = new EscolaDto();
		dto.setInep("");
		dto.setNome("");
		dto.setPrefixo("");
		dto.setCrede("");
		this.dto = dto;
		this.escola = converter.toEntity(dto, Escola.class);
	}


	@Test
	public void testValidationWithoutResult() {
		
	}
}

package com.dtb.saesc.api.model.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.converters.Converter;
import com.dtb.saesc.api.model.dtos.ProvedorDto;
import com.dtb.saesc.api.model.entities.Provedor;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConverterTest {
	@Autowired
	private Converter<Provedor, ProvedorDto> converter;
	
	private Provedor entity;
	private ProvedorDto dto;
	
	@Before
	public void init() {
		entity = new Provedor();
		entity.setCnpj("Some CNPJ");
		entity.setId(Long.valueOf(1));
		entity.setNome("Some Provedor");
		entity.setDataCriacao(new Date());
		
		dto = new ProvedorDto();
		dto.setCnpj("Some CNPJ");
		//dto.setId(Long.valueOf(1));
		dto.setNome("Some Provedor Dto");
	}
	
	@Test
	public void testConvertToDtoWithClass() {
		ProvedorDto dto = converter.toDto(ProvedorDto.class).convert(entity);
		assertNotNull(dto);
		assertEquals(dto.getId(), entity.getId());
	}
	
	@Test
	public void testConvertToDtoWithEntity() {
		ProvedorDto dto = converter.toDto(this.dto).convert(entity);
		assertNotNull(dto);
		assertEquals(dto.getId(), entity.getId());
		assertEquals(dto.getNome(),entity.getNome());
	}
	
	@Test
	public void testConvertToEntityWithClass() {
		Provedor entity = converter.toEntity(Provedor.class).convert(dto);
		System.out.println(entity.getId());
		assertNotNull(entity);
		assertEquals(entity.getNome(), dto.getNome());
		assertNotEquals(entity.getDataCriacao(), this.entity.getDataCriacao());		
	}
	
	@Test
	public void testConvertToEntityWithDto() {
		Provedor entity = converter.toEntity(this.entity).convert(dto);
		assertNotNull(entity);
		assertEquals(entity.getDataCriacao(), this.entity.getDataCriacao());
	}
}

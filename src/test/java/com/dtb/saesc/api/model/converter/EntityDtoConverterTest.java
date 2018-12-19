package com.dtb.saesc.api.model.converter;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.converters.EntityDtoConverter;
import com.dtb.saesc.api.model.dtos.EscolaDto;
import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.enums.CredeEnum;
import com.dtb.saesc.api.model.enums.PrefixoEnum;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EntityDtoConverterTest {
	@Autowired
	private EntityDtoConverter<EscolaDto, Escola> converter;
	
	private Escola entity;
	private EscolaDto dto;
	private Escola escola;
	@Before
	public void init() {
		Escola escola = new Escola();
		escola.setId(Long.valueOf(1));
		escola.setNome("Escola de Testes");
		escola.setInep("00000000");
		escola.setCrede(CredeEnum.valueOf("SEFOR_2"));
		escola.setPrefixo(PrefixoEnum.valueOf("EEFM"));
		escola.setDataCriacao(new Date());
		escola.setDataAtualizacao(new Date());
		this.entity = escola;
		
		EscolaDto dto = new EscolaDto();
		dto.setPrefixo("EEEMTI");
		dto.setNome("Escola de testes atualizada");
		dto.setInep("novoInep");
		dto.setCrede("SEFOR_3");
		this.dto = dto;
	}
	
	@Test
	public void testConverterDataCriacao() {
		escola = converter.toEntity(dto, entity);
		System.out.println(entity.getCrede());
		assertNotNull(entity.getDataCriacao());
	}
	@Test
	public void testConverterId() {
		escola = converter.toEntity(dto, entity);
		System.out.println(entity.getCrede());
		assertNotNull(entity.getId());
	}
}

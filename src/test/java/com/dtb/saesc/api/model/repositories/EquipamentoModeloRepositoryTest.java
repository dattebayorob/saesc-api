package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.EquipamentoMarca;
import com.dtb.saesc.api.model.entities.EquipamentoModelo;
import com.dtb.saesc.api.model.enums.EquipamentoTipoEnum;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EquipamentoModeloRepositoryTest {
	@Autowired
	private EquipamentoModeloRepository repository;
	private EquipamentoModelo modelo;
	
	@Before
	public void init() {
		modelo = new EquipamentoModelo();
		modelo.setNome("Modelo Fake");
		modelo.setMarca(new EquipamentoMarca(Long.valueOf(1)));
		modelo.setTipo(EquipamentoTipoEnum.COMPUTADOR);
		modelo = repository.save(modelo); // Já existe uma marca de Id 1 cadastrada
	}
	@After
	public void finish() {
		
	}
	
	@Test
	public void testSaveInit() {
		assertNotNull(modelo.getId());
	}
	@Test
	public void testFindAll() {
		assertFalse(repository.findAll().isEmpty());
	}
	@Test
	public void testFindById() {
		assertTrue(repository.findById(modelo.getId()).isPresent());
	}
}

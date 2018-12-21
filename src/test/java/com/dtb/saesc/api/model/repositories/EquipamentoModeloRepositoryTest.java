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
import com.dtb.saesc.api.model.repositories.utils.AdicionarMarca;
import com.dtb.saesc.api.model.repositories.utils.AdicionarModelo;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EquipamentoModeloRepositoryTest {
	@Autowired
	private EquipamentoModeloRepository repository;
	@Autowired
	private EquipamentoMarcaRepository marcaRepo;
	private EquipamentoModelo modelo;
	private EquipamentoMarca marca;
	
	@Before
	public void init() {
		marca = marcaRepo.save(AdicionarMarca.set("Marca Fake"));
		modelo = repository.save(AdicionarModelo.set(marca));
	}
	@After
	public void finish() {
		repository.deleteAll();
		marcaRepo.deleteAll();
	}
	
	@Test
	public void testSave() {
		assertNotNull(repository.save(AdicionarModelo.set(marca)));
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

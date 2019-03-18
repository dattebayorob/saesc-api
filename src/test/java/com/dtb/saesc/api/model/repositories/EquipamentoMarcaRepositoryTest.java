package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.EquipamentoMarca;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EquipamentoMarcaRepositoryTest {
	@Autowired
	private EquipamentoMarcaRepository repository;
	private EquipamentoMarca marca;
	
	@Before
	public void init() {
		marca = EquipamentoMarca
				.builder()
					.nome("Fake Marca "+(int) (Math.random()*1000))
				.build();
		repository.save(marca);
	}
	
	@Test
	public void testSave() {
		assertNotNull(marca.getId());
	}
	@Test
	public void testFindById() {
		assertNotNull(repository.findById(marca.getId()).get());
	}
}

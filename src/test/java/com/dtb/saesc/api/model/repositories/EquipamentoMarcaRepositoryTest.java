package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.EquipamentoMarca;
import com.dtb.saesc.api.model.repositories.utils.AdicionarMarca;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EquipamentoMarcaRepositoryTest {
	@Autowired
	private EquipamentoMarcaRepository repository;
	private static Long ID;
	
	@Before
	public void init() {
		EquipamentoMarca marca = repository.save(AdicionarMarca.set("fake"));
		ID = marca.getId();
	}
	
	@After
	public void finish() {
		repository.deleteAll();
	}
	
	@Test
	public void testSave() {
		assertNotNull(repository.save(AdicionarMarca.set("fake 2")));
	}
	@Test
	public void testFindById() {
		assertNotNull(repository.findById(ID).get());
	}
}

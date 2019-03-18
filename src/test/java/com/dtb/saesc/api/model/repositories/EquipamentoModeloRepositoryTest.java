package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	
	private static final Log log = LogFactory.getLog(EquipamentoModeloRepositoryTest.class);
	
	private EquipamentoModelo modelo;
	
	@Before
	public void init() {
		log.info("Instanciação e Persistencia inicial pros testes");
		
		modelo = EquipamentoModelo
				.builder()
					.nome("Modelo Fake")
					.marca(EquipamentoMarca
						.builder()
							.id(Long.valueOf(1))
						.build())
					.tipo(EquipamentoTipoEnum.COMPUTADOR)
				.build();
		modelo = repository.save(modelo);
	}
	@After
	public void finish() {
		log.info("Removendo entidade de testes");
		repository.deleteById(modelo.getId());
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

package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Provedor;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ProvedorRepositoryTest {
	@Autowired
	private ProvedorRepository repository;
	
	private static final Log log = LogFactory.getLog(ProvedorRepositoryTest.class);
	private static Long ID;
	private static String CNPJ;
	private Provedor provedor;
	@Before
	public void init() {
		log.info("Instanciando entidade inicial pros testes");
		provedor = new Provedor();
		provedor.setCnpj("00000000000001");
		provedor.setNome("Provedor de Testes 1");
		repository.save(provedor);
	}
	
	@Test
	public void testSave() {
		ID = provedor.getId();
		CNPJ = provedor.getCnpj();
		assertNotNull(ID);
	}
	@Test
	public void testFindById() {
		Optional<Provedor> provedor = repository.findById(ID);
		assertTrue(provedor.isPresent());
	}
	@Test
	public void testFindAll() {
		List<Provedor> provedores = repository.findAll();
		provedores.forEach(p -> System.out.println(p.getCnpj()));
		assertTrue(!provedores.isEmpty());
	}
	@Test
	public void testFindByCnpj() {
		assertTrue(repository.existsByCnpj(CNPJ));
	}
	@Test
	public void testDeleteById() {
		repository.deleteById(ID);
		assertFalse(repository.findById(ID).isPresent());
	}
}

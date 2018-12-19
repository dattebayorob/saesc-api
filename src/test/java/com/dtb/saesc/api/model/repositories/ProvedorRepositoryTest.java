package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Provedor;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProvedorRepositoryTest {
	@Autowired
	private ProvedorRepository repository;
	
	private static Long id = Long.valueOf(1);
	
	@Test
	public void testBuscarPeloId() {
		Optional<Provedor> provedor = repository.findById(id);
		System.out.println(provedor.get().getId());
		assertTrue(provedor.isPresent());
	}
	@Test
	public void testBuscarTodos() {
		List<Provedor> provedores = repository.findAll();
		assertTrue(!provedores.isEmpty());
	}
}

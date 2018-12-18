package com.dtb.saesc.api.service;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Funcionario;
import com.dtb.saesc.api.services.FuncionarioService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FuncionarioServiceTest {
	@Autowired
	private FuncionarioService service;
	
	private String email;
	
	@Before
	public void init() {
		// Verificar como simular um context security pro teste unitário
		//this.email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	@Test
	public void testBuscarFuncionarioPeloContext() {
		Optional<Funcionario> f = service.buscarPeloEmail(email);
		if(f.isPresent())
			System.out.println(f.get().getNome());
		assertTrue(f.isPresent());
	}
}

package com.dtb.saesc.api.service;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Funcionario;
import com.dtb.saesc.api.model.repositories.FuncionarioRepository;
import com.dtb.saesc.api.services.FuncionarioService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class FuncionarioServiceTest {
	@MockBean
	private FuncionarioRepository repository;
	@Autowired
	private FuncionarioService service;

	private static final String EMAIL = "fake@fake";
	
	@Before
	public void init() {
		Funcionario funcionario = new Funcionario(Long.valueOf(1));
		
		BDDMockito.given(repository.save(Mockito.any())).willReturn(funcionario);
		BDDMockito.given(repository.findByEmail(Mockito.anyString())).willReturn(Optional.ofNullable(funcionario));
	}
	
	@Test
	public void testPersistir() {
		assertNotNull(service.persistir(new Funcionario()).getId());
	}
	@Test
	public void testBuscarPeloEmail() {
		assertNotNull(service.buscarPeloEmail("EMAIL").get().getId());
	}

	@Test
    @WithMockUser(username = EMAIL, authorities = { "TECNICO" })
	public void testBuscarPeloContexto() {
		assertNotNull(service.buscarPeloContexto());
	}

}

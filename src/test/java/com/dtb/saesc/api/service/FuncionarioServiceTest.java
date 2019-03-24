package com.dtb.saesc.api.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

	@Rule
	public final ExpectedException e = ExpectedException.none();

	private static final String EMAIL = "fake@fake";

	@Before
	public void init() {
		Funcionario funcionario = Funcionario.builder().id(Long.valueOf(1)).email(EMAIL).build();

		BDDMockito.given(repository.save(Mockito.any())).willReturn(funcionario);
		BDDMockito.given(repository.findByEmail(Mockito.anyString())).willReturn(Optional.ofNullable(funcionario));
	}

	@Test
	public void testAdicionar() {
		assertTrue(service.adicionar(Funcionario.builder().build()).isRight());
	}
	
	@Test
	public void testAdicionarComEmailExistente() {
		assertTrue(service.adicionar(Funcionario.builder().email(EMAIL).build()).isLeft());
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

	@Test(expected = UsernameNotFoundException.class)
	@WithMockUser(username = EMAIL, authorities = { "TECNICO" })
	public void testBuscarPeloContextoEmailInexistente() {
		BDDMockito.given(repository.findByEmail(Mockito.anyString())).willReturn(Optional.empty());
		assertNull(service.buscarPeloContexto());
	}

}

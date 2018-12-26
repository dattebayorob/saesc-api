package com.dtb.saesc.api.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Provedor;
import com.dtb.saesc.api.model.exceptions.ValidationErrorsException;
import com.dtb.saesc.api.model.repositories.ProvedorRepository;
import com.dtb.saesc.api.services.ProvedorService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ProvedorServiceTest {
	@MockBean
	private ProvedorRepository repository;
	@Autowired
	private ProvedorService service;
	@Rule
	public final ExpectedException e = ExpectedException.none();
	
	private Provedor provedor;

	@Before
	public void init() {
		provedor = new Provedor();
		provedor.setId(Long.valueOf(1));
		provedor.setNome("Provedor fake");
		provedor.setCnpj("00000000000000");
		BDDMockito.given(repository.findById(Mockito.anyLong())).willReturn(Optional.ofNullable(provedor));
		BDDMockito.given(repository.existsByCnpj(Mockito.anyString())).willReturn(false);
		BDDMockito.given(repository.existsById(Mockito.anyLong())).willReturn(true);
		BDDMockito.given(repository.save(Mockito.any())).willReturn(provedor);
	}

	@Test
	public void testBuscarPeloId() {
		Optional<Provedor> provedor = service.buscarPeloId(Long.valueOf(1));
		assertNotNull(provedor);
	}

	@Test
	public void testAdicionar() {
		assertNotNull(repository.save(new Provedor()));
	}

	@Test
	public void testAtualizar() {
		assertNotNull(service.atualizar(provedor, "blabla").getId());
	}

	@Test(expected = ValidationErrorsException.class)
	public void testAtualizarComCnpjJaEmUso() {
		BDDMockito.given(repository.existsByCnpj(Mockito.anyString())).willReturn(true);
		service.atualizar(provedor, "someNewCnpj");
	}

	@Test
	public void testExistePeloId() {
		assertTrue(service.existePeloId(Long.valueOf(1)));
	}

	@Test
	public void testExistePeloCnpj() {
		assertFalse(service.existePeloCnpj("somecnpj"));
	}

	@Test
	public void testRemoverPeloId() {
		// Não há o que testar
	}
}

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
		provedor = Provedor
				.builder()
					.id(Long.valueOf(1))
					.nome("Provedor fake")
					.cnpj("00000000000000")
				.build();
		BDDMockito.given(repository.findById(Mockito.anyLong())).willReturn(Optional.ofNullable(provedor));
		BDDMockito.given(repository.existsByCnpj(Mockito.anyString())).willReturn(false);
		BDDMockito.given(repository.existsById(Mockito.anyLong())).willReturn(true);
		BDDMockito.given(repository.save(Mockito.any())).willReturn(provedor);
		BDDMockito.doNothing().when(repository).deleteById(Mockito.anyLong());
	}

	@Test
	public void testBuscarPeloId() {
		Optional<Provedor> provedor = service.buscarPeloId(Long.valueOf(1));
		assertNotNull(provedor);
	}

	@Test
	public void testAdicionar() {
		assertTrue(service.adicionar(Provedor.builder().build()).isRight());
	}
	
	@Test
	public void testAdicionarComCnpjJaEmUso() {
		BDDMockito.given(repository.existsByCnpj(Mockito.anyString())).willReturn(true);
		assertFalse(service.adicionar(provedor).isRight());
	}
	
	@Test
	public void testAtualizar() {
		assertNotNull(service.atualizar(provedor, "00000000000000").getOrElse(provedor).getId());
	}
	
	@Test
	public void testAtualizarComCnpjSemUso() {
		provedor.setCnpj("someNewCnpj");
		assertNotNull(service.atualizar(provedor, "00000000000000"));
	}

	@Test
	public void testAtualizarComCnpjJaEmUso() {
		BDDMockito.given(repository.existsByCnpj(Mockito.anyString())).willReturn(true);
		assertTrue(service.atualizar(provedor, "somenovocnpj").isLeft());
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
		BDDMockito.given(repository.findById(Mockito.anyLong())).willReturn(Optional.ofNullable(null));
		service.removerPeloId(provedor.getId());
		assertFalse(service.buscarPeloId(provedor.getId()).isPresent());
	}
}

package com.dtb.saesc.api.service;

import static org.junit.Assert.assertNotNull;

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
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.repositories.EscolaRepository;
import com.dtb.saesc.api.services.EscolaService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EscolaServiceTest {
	@MockBean
	private EscolaRepository repository;
	
	@Autowired
	private EscolaService service;
	
	@Rule
	public final ExpectedException e = ExpectedException.none();
	
	private Escola escola;
	
	@Before
	public void init() {
		Escola escolaFake = new Escola(); escolaFake.setInep("0000000");
		this.escola = escolaFake;
		BDDMockito.given(repository.save(Mockito.any())).willReturn(escolaFake);
		BDDMockito.given(repository.existsByInep(Mockito.anyString())).willReturn(false);
	}
	
	@Test
	public void testAdicionar() {
		assertNotNull(service.adicionar(new Escola()));
	}
	
	@Test //(expected = ValidationErrorsException.class)
	public void testAdicionarComInepEmUso() {
		Escola escola = service.adicionar(this.escola);
	}
}

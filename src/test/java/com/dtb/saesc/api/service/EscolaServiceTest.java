package com.dtb.saesc.api.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.exceptions.ValidationErrorsException;
import com.dtb.saesc.api.model.repositories.EscolaRepository;
import com.dtb.saesc.api.model.repositories.custom.filter.EscolaFilter;
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
		escola = new Escola();
		escola.setInep("0000000");
		BDDMockito.given(repository.save(Mockito.any())).willReturn(escola);
		BDDMockito.given(repository.findById(Mockito.anyLong())).willReturn(Optional.of(escola));
		BDDMockito.given(repository.findByInep(Mockito.anyString())).willReturn(Optional.of(escola));
		BDDMockito.given(repository.existsByInep(Mockito.anyString())).willReturn(false);
		BDDMockito.given(repository.findPageByNomeOrCredeOrPrefixo(Mockito.any(), Mockito.any())).
			willReturn(new PageImpl<>(new ArrayList<Escola>(){ 
			{
				add(escola);
				add(escola);
			}}
			));
	}
	
	@Test
	public void testBuscarPeloId() {
		assertTrue(service.buscarPeloId(Long.valueOf(1)).isPresent());
	}
	
	@Test
	public void testBuscarPeloInep() {
		assertTrue(service.buscarPeloInep("someinep").isPresent());
	}
	
	@Test
	public void testAdicionar() {
		assertNotNull(service.adicionar(new Escola()));
	}
	
	@Test(expected = ValidationErrorsException.class)
	public void testAdicionarComInepEmUso() {
		BDDMockito.given(repository.existsByInep(Mockito.anyString())).willReturn(true);
		service.adicionar(this.escola);
	}
	
	@Test
	public void testAtualizar() {
		assertNotNull(service.atualizar(escola, escola.getInep()).getInep());
	}
	
	@Test(expected = ValidationErrorsException.class)
	public void testAtualizarComInepEmUso() {
		BDDMockito.given(repository.existsByInep(Mockito.anyString())).willReturn(true);
		service.atualizar(escola, "someinep");
	}
	
	@Test
	public void testExistePeloInep() {
		assertFalse(service.existePeloInep("someinep"));
	}
	
	@Test
	public void testPesquisarEscolas() {
		assertTrue(service.pesquisarEscolas(new EscolaFilter(), PageRequest.of(0, 10)).hasContent());
	}
	
}

package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Escola;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EscolaRepositoryTest {
	@Autowired
	private EscolaRepository repository;
	
	private static final String SEARCH_CRITERIA = "";
	
	@Test
	public void testBuscarListaPeloNomeQuery() {
		List<Escola> es = repository.findListByNome(SEARCH_CRITERIA);
		if(!es.isEmpty())
			es.forEach(e -> System.out.println("Lista->"+e.getId()));
		assertTrue(!es.isEmpty());
	}
	@Test
	public void testBuscarPaginaPeloNomeQuery() {
		Page<Escola> es = repository.findAllByNome(SEARCH_CRITERIA, PageRequest.of(0, 10, Direction.valueOf("ASC"), "id"));
		if(es.hasContent())
			es.forEach(e -> System.out.println("Pagina->"+e.getId()));
		assertTrue(es.hasContent());
	}
	@Test
	public void testBuscarListaPeloNomeComNamedQuery() {
		List<Escola> es = repository.findListByNome(SEARCH_CRITERIA);
		if(!es.isEmpty())
			es.forEach(e -> System.out.println(e.getId()));
		assertTrue(!es.isEmpty());
	}
	@Test
	public void testBuscarPaginaPeloNomeComCriteriaBuilder() {
		// Do nothing for now. 
	}
}

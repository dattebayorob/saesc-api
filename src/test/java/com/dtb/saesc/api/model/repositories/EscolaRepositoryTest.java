package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.repositories.custom.filter.EscolaFilter;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EscolaRepositoryTest {
	@Autowired
	private EscolaRepository repository;
	
	private static final String SEARCH_CRITERIA = "";
	private static final String SEARCH_CREDE = "SEFOR_1";
	private static final String SEARCH_PREFIXO = "EEFM";
	
	private EscolaFilter filter;
	private Pageable page;
	
	@Before
	public void init() {
		this.filter = new EscolaFilter(SEARCH_CRITERIA, SEARCH_CREDE, SEARCH_PREFIXO);
		this.page = PageRequest.of(0, 20, Direction.ASC, "id");
	}
	@Test
	public void testBuscarListaPeloNomeComNamedQuery() {
		//
	}
	@Test
	public void testBuscarPaginaPeloNomeComCriteriaBuilder() {
		Page<Escola> es = repository.findPageByNomeOrCredeOrPrefixo(filter, page);
		if(es.hasContent())
			es.forEach(e -> System.out.println("Pagina Com CriteriaBuilder -> "+e.getId()));
		assertTrue(es.hasContent());
	}
}

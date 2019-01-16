package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.enums.CredeEnum;
import com.dtb.saesc.api.model.repositories.custom.filter.EscolaFilter;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EscolaRepositoryTest {
	@Autowired
	private EscolaRepository repository;
	
	private static final Log log = LogFactory.getLog(EscolaRepositoryTest.class);
	private static final String SEARCH_CRITERIA = "";
	private static final String SEARCH_CREDE = "";
	private static final String SEARCH_PREFIXO = "";

	private EscolaFilter filter;
	private Pageable page;
	private Escola escola;

	@Before
	public void init() {
		log.info("Instanciação e Persistencia inicial pros testes");
		this.filter = new EscolaFilter(SEARCH_CRITERIA, SEARCH_CREDE, SEARCH_PREFIXO);
		this.page = PageRequest.of(0, 20, Direction.ASC, "id");
		escola = new Escola();
		escola.setCrede(CredeEnum.SEFOR_2);
		escola.setNome("FAKE ESCOLA");
		escola.setInep("inep_"+(int)(Math.random()*1000));
		repository.save(escola);
	}
	@After
	public void finish() {
		log.info("Removendo entidade persistida no teste");
		repository.deleteById(escola.getId());
	}
	@Test
	public void testSave() {
		assertNotNull(escola.getId());
	}
	@Test
	public void testFindById() {
		assertNotNull(repository.findById(escola.getId()).get());
	}

	@Test
	public void testFindPageByNomeOrCredeOrPrefixo() {
		Page<Escola> es = repository.findPageByNomeOrCredeOrPrefixo(filter, page);
		assertTrue(es.hasContent());
	}
	@Test
	public void testFindPagebyNomeOrCredeOrPrefixo_WithWrongFilter() {
		filter.setNome("NOME TRILOCAO QUE NUM VAI EXISITR");
		Page<Escola> es = repository.findPageByNomeOrCredeOrPrefixo(filter, page);
		assertFalse(es.hasContent());
	}

	@Test
	public void testExistsByInep() {
		assertTrue(repository.existsByInep(escola.getInep()));
	}
}

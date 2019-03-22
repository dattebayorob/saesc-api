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

import com.dtb.saesc.api.model.entities.Instituicao;
import com.dtb.saesc.api.model.enums.CredeEnum;
import com.dtb.saesc.api.model.repositories.custom.filter.InstituicaoFilter;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class InstituicaoRepositoryTest {
	@Autowired
	private InstituicaoRepository repository;
	
	private static final Log log = LogFactory.getLog(InstituicaoRepositoryTest.class);
	private static final String SEARCH_CRITERIA = "FAKE";
	private static final String SEARCH_CREDE = "SEFOR_2";
	private static final String IP = "200.217.200.xxx";

	private InstituicaoFilter filter;
	private Pageable page;
	private Instituicao instituicao;

	@Before
	public void init() {
		log.info("Instanciação e Persistencia inicial pros testes");
		filter = new InstituicaoFilter();
		page = PageRequest.of(0, 20, Direction.ASC, "id");
		instituicao = Instituicao
					.builder()
						.crede(CredeEnum.SEFOR_2)
						.nome("FAKE ESCOLA")
						.inep("inep_"+(int)(Math.random()*1000))
					.build();
		repository.save(instituicao);
	}
	@After
	public void finish() {
		log.info("Removendo entidade persistida no teste");
		repository.deleteById(instituicao.getId());
	}
	@Test
	public void testSave() {
		assertNotNull(instituicao.getId());
	}
	@Test
	public void testFindById() {
		assertNotNull(repository.findById(instituicao.getId()).get());
	}
	
	@Test
	public void testFindPageWithoutFilter() {
		Page<Instituicao> es = repository.findPageByNomeOrCredeOrIp(filter, page);
		assertTrue(es.hasContent());
	}
	
	@Test
	public void testFindPageByNome() {
		filter.setNome(SEARCH_CRITERIA);
		Page<Instituicao> es = repository.findPageByNomeOrCredeOrIp(filter, page);
		assertTrue(es.hasContent());
	}
	@Test
	public void testFindPageByCrede() {
		filter.setCrede(SEARCH_CREDE);
		Page<Instituicao> es = repository.findPageByNomeOrCredeOrIp(filter, page);
		assertTrue(es.hasContent());
	}

	@Test
	public void testFindPageByIp() {
		filter.setIp(IP);
		Page<Instituicao> es = repository.findPageByNomeOrCredeOrIp(filter, page);
		assertTrue(es.hasContent());
	}
	
	@Test
	public void testFindPagebyNomeOrCredeOrPrefixo_WithWrongFilter() {
		filter.setNome("NOME TRILOCAO QUE NUM VAI EXISITR");
		Page<Instituicao> es = repository.findPageByNomeOrCredeOrIp(filter, page);
		assertFalse(es.hasContent());
	}

	@Test
	public void testExistsByInep() {
		assertTrue(repository.existsByInep(instituicao.getInep()));
	}
}

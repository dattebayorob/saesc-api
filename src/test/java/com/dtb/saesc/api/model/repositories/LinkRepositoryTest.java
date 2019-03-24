package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Instituicao;
import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.entities.Provedor;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class LinkRepositoryTest {
	@Autowired
	private LinkRepository repository;

	private Link link;
	private static final Log log = LogFactory.getLog(LinkRepositoryTest.class);
	private static final Long ID_ESCOLA = Long.valueOf(1);

	@Before
	public void init() {
		log.info("Instanciação e Persistencia inicial pros testes");

		link = Link.builder().instituicao(Instituicao.builder().id(ID_ESCOLA).build()).ip("172.xxx.xxx.xx1")
				.provedor(Provedor.builder().id(Long.valueOf(1)).build()).build();
		repository.save(link);
	}

	@After()
	public void finish() {
		log.info("Entidade removida no test, não há o que deletar");
	}

	@Test
	public void testSave() {
		assertNotNull(link.getId());
	}

	@Test
	public void testFindById() {
		assertNotNull(repository.findById(link.getId()).get());
	}

	@Test
	public void testDeleteById() {
		repository.deleteById(link.getId());
		assertFalse(repository.findById(link.getId()).isPresent());
	}

	@Test
	public void testFindByIdAndProvedor() {
		Optional<Link> link = repository.findByIpAndProvedor("200.217.200.xxx",
				Provedor.builder().id(this.link.getProvedor().getId()).build());
		assertTrue(link.isPresent());
	}
	@Test
	public void testFindByIdAndProvedor_WithNonCadastratedIp() {
		Optional<Link> link = repository.findByIpAndProvedor("ip",
				Provedor.builder().id(this.link.getProvedor().getId()).build());
		assertFalse(link.isPresent());
	}
}

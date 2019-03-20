package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

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

import com.dtb.saesc.api.model.entities.Escola;
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
	private static final String IP = "172"; // If null, all links will be returned.
	
	@Before
	public void init() {
		log.info("Instanciação e Persistencia inicial pros testes");
		
		link = Link
				.builder()
					.escola(Escola.builder().id(ID_ESCOLA).build())
					.ip("172.xxx.xxx.xx1")
					.provedor(Provedor.builder().id(Long.valueOf(1)).build())
				.build();
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
	public void deleteById() {
		repository.deleteById(link.getId());
		assertFalse(repository.findById(link.getId()).isPresent());
	}
	
	@Test
	public void testFindByEscolaId() {
		List<Link> links = repository.findByEscolaId(ID_ESCOLA);
		assertTrue(!links.isEmpty());
	}
	
	@Test
	public void testFindAllByIp() {
		List<Link> links = repository.findAllByIp(IP);
		assertTrue(!links.isEmpty());
	}
	@Test
	public void testFindAllByIpWithWrongIp() {
		List<Link> links = repository.findAllByIp("Num_vai_ter_nada");
		assertTrue(links.isEmpty());
	}
}

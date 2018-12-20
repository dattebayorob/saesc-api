package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Link;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LinkRepositoryTest {
	@Autowired
	private LinkRepository repository;
	
	private static final Long ID_ESCOLA = Long.valueOf(1);
	private static final String IP = "172"; // If null, all links will be returned.
	
	@Test
	public void testFindByEscolaId() {
		List<Link> links = repository.findByEscolaId(ID_ESCOLA);
		assertTrue(!links.isEmpty());
	}
	
	@Test
	public void testFindAllByIp() {
		List<Link> links = repository.findAllByIp(IP);
		if(!links.isEmpty())
			links.forEach(l -> System.out.println(l.getIp()));
		assertTrue(!links.isEmpty());
	}
	@Test
	public void testFindAllByIpWithWrongIp() {
		List<Link> links = repository.findAllByIp("Num_vai_ter_nada");
		assertTrue(links.isEmpty());
	}
}

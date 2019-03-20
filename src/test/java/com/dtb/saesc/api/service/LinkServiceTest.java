package com.dtb.saesc.api.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.repositories.LinkRepository;
import com.dtb.saesc.api.services.LinkService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class LinkServiceTest {
	@MockBean
	private LinkRepository repository;
	
	@Autowired
	private LinkService service;
	
	private static final String IP = "";
	
	@Before
	public void init() {
		BDDMockito.given(repository.findAllByIp(Mockito.anyString())).willReturn
			(Arrays.asList(Link.builder().build(),Link.builder().build()));
		BDDMockito.given(repository.findByEscolaId(Mockito.anyLong())).willReturn
			(Arrays.asList(Link.builder().build(),Link.builder().build()));
	}
	
	@Test
	public void testBuscarLinks() {
		assertTrue(service.buscarLinks(IP).isPresent());
	}
	
	@Test
	public void testBuscarPorescola() {
		assertTrue(service.buscarPorescola(Long.valueOf(1)).isPresent());
	}
	
	@Test
	public void testBuscarPorEscolaRetornandoNulo() {
		BDDMockito.given(repository.findByEscolaId(Mockito.anyLong())).willReturn(new ArrayList<>());
		assertFalse(service.buscarPorescola(Long.valueOf(1)).isPresent());
	}
}

package com.dtb.saesc.api.service;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.repositories.LinkRepository;
import com.dtb.saesc.api.services.LinkService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LinkServiceTest {
	@MockBean
	private LinkRepository repository;
	
	@Autowired
	private LinkService service;
	
	private static final String IP = "";
	
	@Before
	public void init() {
		//when(repository.findAllByIp(Mockito.anyString())).thenReturn
		BDDMockito.given(repository.findAllByIp(Mockito.anyString())).willReturn
			(Arrays.asList(new Link(),new Link()));
		BDDMockito.given(repository.findByEscolaId(Mockito.anyLong())).willReturn
			(Arrays.asList(new Link(),new Link()));
	}
	
	@Test
	public void testBuscarLinks() {
		assertFalse(service.buscarLinks(IP).isEmpty());
	}
	
	@Test
	public void testBuscarPelaEscola() {
		assertFalse(service.buscarPorescola(Long.valueOf(1)).isEmpty());
	}
}

package com.dtb.saesc.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

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

import com.dtb.saesc.api.model.entities.Instituicao;
import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.repositories.LinkRepository;
import com.dtb.saesc.api.services.LinkService;

import io.vavr.control.Either;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class LinkServiceTest {
	@MockBean
	private LinkRepository repository;
	
	@Autowired
	private LinkService service;
	
	private Link link;
	
	@Before
	public void init() {
		link = Link.builder()
				.id(Long.valueOf(1))
				.ip("172.24.xxx.xxx")
				.instituicao(Instituicao.builder().id(Long.valueOf(1)).build())
			.build();
		BDDMockito.given(repository.findById(Mockito.anyLong())).willReturn(Optional.of(link));
		BDDMockito.given(repository.save(Mockito.any())).willReturn(link);
		BDDMockito.given(repository.findByIpAndProvedor(Mockito.anyString(), Mockito.any())).willReturn(Optional.of(link));
	}
	
	@Test
	public void  testAdicionar() {
		Either<RuntimeException, Link> link = service.adicionar(Link.builder().build());
		assertTrue(link.isRight());
		assertEquals(link.getOrElse(Link.builder().build()).getId(), this.link.getId());
	}
	
	@Test
	public void testAtualizar() {
		this.link.setStatus("Me falaram que a Penha é o bixo");
		Either<RuntimeException, Link> link = service.atualizar(this.link, this.link.getIp());
		assertTrue(link.isRight());
	}
	
	@Test
	public void testAtualizarComIpSemUso() {
		BDDMockito.given(repository.findByIpAndProvedor(Mockito.anyString(), Mockito.any())).willReturn(Optional.empty());
		Either<RuntimeException, Link> link = service.atualizar(this.link, "172.24.xxx.yyy");
		assertTrue(link.isRight());
	}
	
	@Test
	public void testAtualizarComIpEmUso() {
		Either<RuntimeException, Link> link = service.atualizar(this.link, "200.217.200.xxx");
		assertTrue(link.isLeft());
	}
	
	@Test
	public void buscarPeloId() {
		Optional<Link> link = service.buscarPeloId(Long.valueOf(1));
		assertTrue(link.isPresent());
		assertNotNull(link.get().getId());
		assertEquals(link.get().getId(), this.link.getId());
	}
	
	@Test
	public void BuscarPeloIpEProvedor() {
		Optional<Link> link = service.buscarPeloIpEProvedor(this.link.getIp(), this.link.getProvedor());
		assertTrue(link.isPresent());
	}
}

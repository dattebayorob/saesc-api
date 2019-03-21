package com.dtb.saesc.api.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Instituicao;
import com.dtb.saesc.api.model.exceptions.ValidationErrorException;
import com.dtb.saesc.api.model.repositories.InstituicaoRepository;
import com.dtb.saesc.api.model.repositories.custom.filter.InstituicaoFilter;
import com.dtb.saesc.api.services.InstituicaoService;

import io.vavr.control.Either;

@SpringBootTest
@RunWith(SpringRunner.class)
public class instituicaoServiceTest {
	@MockBean
	private InstituicaoRepository repository;
	
	@Autowired
	private InstituicaoService service;
	
	@Rule
	public final ExpectedException e = ExpectedException.none();
		
	private Instituicao instituicao;
	
	@Before
	public void init() {
		instituicao = Instituicao.builder().inep("0000000").build();
		BDDMockito.given(repository.save(Mockito.any())).willReturn(instituicao);
		BDDMockito.given(repository.findById(Mockito.anyLong())).willReturn(Optional.of(instituicao));
		BDDMockito.given(repository.findByInep(Mockito.anyString())).willReturn(Optional.of(instituicao));
		BDDMockito.given(repository.existsByInep(Mockito.anyString())).willReturn(false);
		BDDMockito.given(repository.findPageByNomeOrCrede(Mockito.any(), Mockito.any())).
			willReturn(new PageImpl<>(Arrays.asList(instituicao,instituicao)));
	}
	
	@Test
	public void testBuscarPeloId() {
		assertTrue(service.buscarPeloId(Long.valueOf(1)).isPresent());
	}
	
	@Test
	public void testBuscarPeloInep() {
		assertTrue(service.buscarPeloInep("someinep").isPresent());
	}
	
	@Test
	public void testAdicionar() {
		Either<RuntimeException, Instituicao> either = service.adicionar(instituicao);
		assertTrue(either.isRight());
		assertNotNull(either.getOrElseThrow(ex -> new ValidationErrorException("some error message")).getInep());
	}
	
	@Test(expected=ValidationErrorException.class)
	public void testAdicionarComInepEmUso() {
		BDDMockito.given(repository.existsByInep(Mockito.anyString())).willReturn(true);
		service.adicionar(instituicao).getOrElseThrow( ex -> new ValidationErrorException("some error message"));
	}
	
	@Test
	public void testAtualizar() {
		Either<RuntimeException, Instituicao> either = service.atualizar(instituicao,instituicao.getInep());
		assertTrue(either.isRight());
		assertNotNull(either.getOrElseThrow(ex -> new ValidationErrorException("some error message")).getInep());
	}
	
	@Test
	public void testAtualizarComInepSemUso() {
		String inep = instituicao.getInep();
		instituicao.setInep("someNewInep");
		Either<RuntimeException, Instituicao> either = service.atualizar(instituicao,inep);
		assertTrue(either.isRight());
		assertNotNull(either.getOrElseThrow(ex -> new ValidationErrorException("some error message")).getInep());
	}
	
	@Test(expected=ValidationErrorException.class)
	public void testAtualizarComInepEmUso() {
		BDDMockito.given(repository.existsByInep(Mockito.anyString())).willReturn(true);
		service.atualizar(instituicao,"someinep").getOrElseThrow( ex -> new ValidationErrorException("some error message"));
	}
	
	@Test
	public void testExistePeloInep() {
		assertFalse(service.existePeloInep("someinep"));
	}
	
	@Test
	public void testPesquisarinstituicaos() {
		assertTrue(service.pesquisarEscolas(new InstituicaoFilter(), PageRequest.of(0, 10)).isPresent());
	}
	
}

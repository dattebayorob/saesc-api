package com.dtb.saesc.api.service;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Provedor;
import com.dtb.saesc.api.model.exceptions.ValidationErrorsException;
import com.dtb.saesc.api.model.repositories.ProvedorRepository;
import com.dtb.saesc.api.services.ProvedorService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProvedorServiceTest {
	@MockBean
	private ProvedorRepository repository;
	@Autowired
	private ProvedorService service;
	private Provedor provedor;
	
	@Before
	public void init() {
		Provedor provedorFake = new Provedor();
		provedorFake.setId(Long.valueOf(1));
		provedorFake.setNome("Provedor fake");
		provedorFake.setCnpj("00000000000000");
		this.provedor = provedorFake;
		BDDMockito.given(repository.findById(Mockito.anyLong())).willReturn(Optional.ofNullable(provedorFake));
		BDDMockito.given(repository.existsByCnpj(Mockito.matches("00000000000000"))).willReturn(true);
		BDDMockito.given(repository.save(Mockito.any())).willReturn(provedorFake);
	}
	
	@Test
	public void testServiceBuscarPeloId() {
		Optional<Provedor> provedor = service.buscarPeloId(Long.valueOf(1));
		assertNotNull(provedor);
	}
	
	@Test
	public void testServiceAdicionar() {
		assertNotNull(repository.save(new Provedor()));
	}
	
	@Test(expected = ValidationErrorsException.class)
	public void testServiceAtualizarComCnpjJaEmUso() {
		System.out.println(repository.existsByCnpj("00000000000000"));
		Provedor provedor = service.atualizar(this.provedor, "00000000000000");
	}
}

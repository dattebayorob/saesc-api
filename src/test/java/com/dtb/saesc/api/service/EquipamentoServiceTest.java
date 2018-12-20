package com.dtb.saesc.api.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.EquipamentoHistorico;
import com.dtb.saesc.api.model.entities.Funcionario;
import com.dtb.saesc.api.model.repositories.EquipamentoHistoricoRepository;
import com.dtb.saesc.api.model.repositories.EquipamentoRepository;
import com.dtb.saesc.api.services.EquipamentoService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EquipamentoServiceTest {
	@MockBean
	private EquipamentoRepository repository;
	@MockBean
	private EquipamentoHistoricoRepository historicoRepository;
	
	@Autowired
	private EquipamentoService service;
	
	@Before
	public void init() {
		Equipamento e = new Equipamento();
		e.setId(Long.valueOf(2));
		BDDMockito.given(repository.save(Mockito.any())).willReturn(new Equipamento());
		BDDMockito.given(historicoRepository.save(Mockito.any())).willReturn(new EquipamentoHistorico(e, new Funcionario(), "someShit"));
		BDDMockito.given(repository.existsById(Mockito.anyLong())).willReturn(true);
	}
	
	@Test
	public void testPersistir() {
		assertNotNull(service.persistir(new Equipamento(), "Some shit", new Funcionario()));
	}
	@Test
	public void testExistePeloId() {
		assertTrue(service.existePeloId(Long.valueOf(1)));
	}
}

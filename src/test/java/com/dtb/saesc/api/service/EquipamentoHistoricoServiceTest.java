package com.dtb.saesc.api.service;

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

import com.dtb.saesc.api.model.entities.EquipamentoHistorico;
import com.dtb.saesc.api.model.repositories.EquipamentoHistoricoRepository;
import com.dtb.saesc.api.services.EquipamentoHistoricoService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EquipamentoHistoricoServiceTest {
	@MockBean
	private EquipamentoHistoricoRepository repository;
	@Autowired
	private EquipamentoHistoricoService service;
	
	@Before
	public void init() {
		BDDMockito.given(repository.save(Mockito.any())).willReturn(EquipamentoHistorico.builder().build());
	}
	
	@Test
	public void testAdicionar() {
		//service.adicionar(new Equipamento(), new Funcionario(), "Some comentary");
	}
}

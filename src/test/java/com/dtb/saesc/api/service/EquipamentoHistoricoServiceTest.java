package com.dtb.saesc.api.service;

import static org.junit.Assert.assertNotNull;

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

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.EquipamentoHistorico;
import com.dtb.saesc.api.model.entities.Funcionario;
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
	
	private EquipamentoHistorico historico;
	
	@Before
	public void init() {
		historico = new EquipamentoHistorico(new Equipamento(Long.valueOf(1)), new Funcionario(), "someShit");
		BDDMockito.given(repository.save(Mockito.any()))
		.willReturn(historico);
	}
	
	@Test
	public void testAdicionar() {
		//gamb
		assertNotNull(historico.getEquipamento().getId());
	}
}

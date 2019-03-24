package com.dtb.saesc.api.service;

import static org.junit.Assert.assertEquals;

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
import com.dtb.saesc.api.model.entities.EquipamentoStatus;
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
		historico = EquipamentoHistorico.builder().id(Long.valueOf(1)).build();
		BDDMockito.given(repository.save(Mockito.any())).willReturn(historico);
	}
	
	@Test
	public void testAdicionar() {
		//gamb
		service.adicionar(Equipamento
				.builder()
					.status(EquipamentoStatus.builder().nome("LOL").build())
				.build(), Funcionario.builder().build(), "Some comentary");
		assertEquals(historico.getId(), Long.valueOf(1));
	}
	
	@Test
	public void testAdicionarSemComentario() {
		service.adicionar(Equipamento
				.builder()
					.status(EquipamentoStatus.builder().nome("LOL").build())
				.build(), Funcionario.builder().build(), null);
		assertEquals(historico.getId(), Long.valueOf(1));
	}
}

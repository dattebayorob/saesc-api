package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.EquipamentoHistorico;
import com.dtb.saesc.api.model.entities.Funcionario;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EquipamentoHistoricoRepositoryTest {
	@Autowired
	private EquipamentoHistoricoRepository repository;

	private EquipamentoHistorico historico;

	@Before
	public void init() {
		historico = EquipamentoHistorico
				.builder()
					.equipamento(Equipamento.builder().id(Long.valueOf(1)).build())
					.funcionario(Funcionario.builder().id(Long.valueOf(1)).build())
					.comentario("comentario Fake")
				.build();
		repository.save(historico);
	}
	@Test
	public void testSave() {
		assertNotNull(historico.getId());
	}
	@Test
	public void testSaveWithFullDate() {
		Date dataAtual = new Date();
		EquipamentoHistorico historicoNovo = repository
				.save(EquipamentoHistorico
						.builder()
						.equipamento(Equipamento.builder().id(Long.valueOf(1)).build())
						.funcionario(Funcionario.builder().id(Long.valueOf(1)).build())
						.comentario("Comentario do dia "+dataAtual.toString())
					.build()
					);
		assertEquals(dataAtual.toString(), historicoNovo.getData().toString());
		assertNotNull(historicoNovo.getId());
	}
	@Test
	public void testDeleteAndFindById() {
		assertNotNull(repository.findById(historico.getId()).get());
		repository.deleteById(historico.getId());
		assertFalse(repository.findById(historico.getId()).isPresent());
	}
	@Test
	public void testFindByEquipamentoId() {
		assertFalse(repository.findByEquipamentoId(Long.valueOf(1)).isEmpty());
	}
}

package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.EquipamentoStatus;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EquipamentoStatusRepositoryTest {
	@Autowired
	private EquipamentoStatusRepository repository;
	
	@Test
	public void testSave() {
		EquipamentoStatus status = new EquipamentoStatus();
		status.setNome("Fake Status");
		assertNotNull(repository.save(status));
	}
}

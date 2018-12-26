package com.dtb.saesc.api.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.EquipamentoHistorico;
import com.dtb.saesc.api.model.entities.EquipamentoStatus;
import com.dtb.saesc.api.model.entities.Funcionario;
import com.dtb.saesc.api.model.repositories.EquipamentoHistoricoRepository;
import com.dtb.saesc.api.model.repositories.EquipamentoRepository;
import com.dtb.saesc.api.model.repositories.custom.filter.EquipamentoFilter;
import com.dtb.saesc.api.services.EquipamentoService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EquipamentoServiceTest {
	@MockBean
	private EquipamentoRepository repository;
	@MockBean
	private EquipamentoHistoricoRepository historicoRepository;

	@Autowired
	private EquipamentoService service;

	private Equipamento equipamento;

	@Before
	public void init() {
		equipamento = new Equipamento();
		equipamento.setId(Long.valueOf(1));
		equipamento.setStatus(new EquipamentoStatus(Long.valueOf(1)));
		equipamento.getStatus().setNome("someShit");
		BDDMockito.given(repository.save(Mockito.any())).willReturn(new Equipamento());
		BDDMockito.given(historicoRepository.save(Mockito.any()))
				.willReturn(new EquipamentoHistorico(equipamento, new Funcionario(), "someShit"));
		BDDMockito.given(repository.existsById(Mockito.anyLong())).willReturn(true);
		BDDMockito.given(repository.findById(Mockito.anyLong())).willReturn(Optional.of(equipamento));
		BDDMockito.given(repository.findPageByDescricaoOrModeloOrStatusOrSerialOrTombamento(
				Mockito.any(),Mockito.any())).willReturn(new PageImpl<>(
						new ArrayList<Equipamento>() {
							{
								add(equipamento);
								add(equipamento);
							};
						}
						));
	}
	
	@Test
	public void testBuscarPeloId() {
		assertTrue(service.buscarPeloId(Long.valueOf(1)).isPresent());
	}
	
	@Test
	public void testBuscarPaginaPorFiltros() {
		assertTrue(service.buscarPaginaPorFiltros(new EquipamentoFilter(), PageRequest.of(0, 10)).hasContent());
	}
	
	@Test
	public void testPersistir() {
		assertNotNull(service.persistir(equipamento, "Some shit", new Funcionario()));
	}

	@Test
	public void testExistePeloId() {
		assertTrue(service.existePeloId(Long.valueOf(1)));
	}
}

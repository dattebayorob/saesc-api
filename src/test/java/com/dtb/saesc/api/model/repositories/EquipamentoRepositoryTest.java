package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.EquipamentoModelo;
import com.dtb.saesc.api.model.entities.EquipamentoStatus;
import com.dtb.saesc.api.model.entities.Instituicao;
import com.dtb.saesc.api.model.repositories.custom.filter.EquipamentoFilter;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EquipamentoRepositoryTest {
	@Autowired
	private EquipamentoRepository repository;

	private EquipamentoFilter filter;
	private Pageable page;
	private Equipamento equipamento;
	private static final Logger log = LoggerFactory.getLogger(EquipamentoRepositoryTest.class);
	private static final LocalDate DATA_LOCALDATE = LocalDate.now();
	private static final Long MODELO = Long.valueOf(2);
	private static final String DESCRICAO = "Fake";
	private static final String SERIAL = "LX1TT";
	private static final String TOMBAMENTO = "814633";

	@Before
	public void init() {
		log.info("Instanciação e Persistencia inicial pros testes");
		filter = new EquipamentoFilter();
		page = PageRequest.of(0, 10, Direction.ASC, "id");
		equipamento = Equipamento
				.builder()
					.instituicao(Instituicao
							.builder()
								.id(Long.valueOf(1))
							.build())
					.status(EquipamentoStatus
							.builder()
								.id(Long.valueOf(1))
							.build())
					.modelo(EquipamentoModelo
							.builder()
								.id(Long.valueOf(2))
							.build())
					.descricao("Fake equipamento descricao com modelo 2")
				.build();
		repository.save(equipamento);
	}

	@After
	public void finish() {
		log.info("Removendo entidade de testes");
		repository.deleteById(equipamento.getId());
	}

	@Test
	public void testSave() {
		log.info("Testando repository.save");
		assertNotNull(equipamento.getId());
	}

	@Test
	public void testFindById() {
		log.info("Testando repository.findById com id {}", equipamento.getId());
		assertNotNull(repository.findById(equipamento.getId()).get());
	}

	@Test
	public void testBuscarPaginaSemFiltro() {
		log.info("Testando Criteria Query sem filtros");
		Page<Equipamento> equipamentos = buscarPagina();
		assertTrue(equipamentos.hasContent());
	}

	@Test
	public void testBuscarPaginaPelaDescricao() {
		log.info("Testando Criteria Query com a descrição: {}", DESCRICAO);
		filter.setDescricao(DESCRICAO);
		Page<Equipamento> equipamentos = buscarPagina();
		assertTrue(equipamentos.hasContent());
	}

	@Test
	public void testBuscarPaginaPeloModelo() {
		log.info("Testando Criteria Query com o modelo de id: {}", MODELO);
		filter.setModeloId(MODELO);
		Page<Equipamento> equipamentos = buscarPagina();
		assertTrue(equipamentos.hasContent());
	}
	
	@Test
	public void testBuscarPaginaPeloSerial() {
		log.info("Testando Criteria Query com o serial: {}",SERIAL);
		filter.setSerial(SERIAL);
		Page<Equipamento> equipamentos = buscarPagina();
		//Não há equipamento com serial qualquer.
		assertFalse(equipamentos.hasContent());
	}
	
	@Test
	public void testBuscarPaginaPeloTombamento() {
		log.info("Testando Criteria Query com o tombamento: {}", TOMBAMENTO);
		filter.setTombamento(TOMBAMENTO);
		Page<Equipamento> equipamentos = buscarPagina();
		//Não há equipamento com tombamento qualquer.
		assertFalse(equipamentos.hasContent());
	}

	@Test
	public void testBuscarPorDataInicial() {
		log.info("Testando Criteria Query com a data: {}", DATA_LOCALDATE);
		filter.setCriadoDe(DATA_LOCALDATE);
		Page<Equipamento> equipamentos = buscarPagina();
		assertTrue(equipamentos.hasContent());
	}
	
	@Test
	public void testBuscarPorDataFinal() {
		log.info("Testando Criteria Query com a data: {}", DATA_LOCALDATE);
		filter.setCriadoAte(DATA_LOCALDATE);
		Page<Equipamento> equipamentos = buscarPagina();
		assertTrue(equipamentos.hasContent());
	}

	private Page<Equipamento> buscarPagina() {
		Page<Equipamento> equipamentos = repository.findPageByDescricaoOrModeloOrStatusOrSerialOrTombamento(filter,
				page);
		return equipamentos;
	}
}

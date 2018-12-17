package com.dtb.saesc.api.model.repositories;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.repositories.custom.filter.EquipamentoFilter;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EquipamentoRepositoryTest {
	@Autowired
	private EquipamentoRepository repository;

	private EquipamentoFilter filter;
	private Pageable page;
	private static final LocalDate DATA_LOCALDATE = LocalDate.now();
	private static final Date DATA_DATE = new Date();
	private static final Long MODELO = null;

	@Before
	public void init() {
		this.filter = new EquipamentoFilter();
		this.page = PageRequest.of(0, 10, Direction.ASC, "id");
	}

	@Test
	public void testConversaoLocalDateParaDate() {
		LocalDate date = DATA_DATE.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		assertTrue(DATA_LOCALDATE.equals(date));
	}

	/**
	 * 
	 * Testa se as entidades serão retornadas Caso o filtro seja nulo, será como se
	 * não houvesse filtro algum
	 * 
	 */

	@Test
	public void testBuscarPaginaPelaDescricao() {
		Page<Equipamento> equipamentos = buscarPagina("Por Descrição: ");
		assertTrue(equipamentos.hasContent());
	}
	
	/**
	 * 
	 * Testa se as entidades serão retornadas dado modeloId
	 * 
	 */

	@Test
	public void testBuscarPaginaPeloModelo() {
		this.filter.setModeloId(MODELO);
		Page<Equipamento> equipamentos = buscarPagina("Por Modelo Id: ");
		assertTrue(equipamentos.hasContent());
	}

	/**
	 * 
	 * Testa se as entidades serão retornadas a partir da data de criação
	 * 
	 */
	
	@Test
	public void testBuscarPorDataInicial() {
		this.filter.setCriadoDe(DATA_LOCALDATE);
		Page<Equipamento> equipamentos = buscarPagina("Por Data Inicial: ");
		assertTrue(equipamentos.hasContent());
	}
	
	private Page<Equipamento> buscarPagina(String string) {
		Page<Equipamento> equipamentos = repository.findPageByDescricaoOrModeloOrStatusOrSerialOrTombamento(this.filter,
				this.page);
		if (equipamentos.hasContent())
			equipamentos.forEach(e -> System.out.println(string + e.getDescricao()));
		return equipamentos;
	}
}

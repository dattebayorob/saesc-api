package com.dtb.saesc.api.model.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtb.saesc.api.model.enums.CredeEnum;
import com.dtb.saesc.api.model.enums.EquipamentoTipoEnum;
import com.dtb.saesc.api.model.enums.PrefixoEnum;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EnumUtilsTest {
	private static final String STRING_CREDE = "SEFOR_1";
	private static final String STRING_PREFIXO = "EEFM";
	private static final String STRING_EQUIPAMENTO = "SERVIDOR";
	private static final String STRING_CREDE_INVALIDA = "EEF";
	
	@Test
	public void testCredeEnum() {
		assertTrue(EnumUtils.isValid(STRING_CREDE, CredeEnum.values()));
	}
	
	@Test
	public void testPrefixoEnum() {
		assertTrue(EnumUtils.isValid(STRING_PREFIXO, PrefixoEnum.values()));
	}
	
	@Test
	public void testEquipamentoEnum() {
		assertTrue(EnumUtils.isValid(STRING_EQUIPAMENTO, EquipamentoTipoEnum.values()));
	}
	
	@Test
	public void testCredeEnumInvalido() {
		assertFalse(EnumUtils.isValid(STRING_CREDE_INVALIDA, CredeEnum.values()));
	}
}

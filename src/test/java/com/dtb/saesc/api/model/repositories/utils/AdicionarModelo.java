package com.dtb.saesc.api.model.repositories.utils;

import com.dtb.saesc.api.model.entities.EquipamentoMarca;
import com.dtb.saesc.api.model.entities.EquipamentoModelo;
import com.dtb.saesc.api.model.enums.EquipamentoTipoEnum;

public class AdicionarModelo {
	private EquipamentoModelo modelo;
	
	public AdicionarModelo(EquipamentoMarca marca) {
		modelo = new EquipamentoModelo();
		modelo.setMarca(marca);
		modelo.setNome("Fake modelo");
		modelo.setTipo(EquipamentoTipoEnum.COMPUTADOR);
	}
	public EquipamentoModelo getModelo() {
		return modelo;
	}
	public static EquipamentoModelo set(EquipamentoMarca marca) {
		return new AdicionarModelo(marca).getModelo();
	}
}

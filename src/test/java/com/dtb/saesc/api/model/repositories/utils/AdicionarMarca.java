package com.dtb.saesc.api.model.repositories.utils;

import com.dtb.saesc.api.model.entities.EquipamentoMarca;

public class AdicionarMarca {
	private EquipamentoMarca marca;
	public AdicionarMarca(String nome) {
		this.marca = new EquipamentoMarca();
		marca.setNome(nome);
	}
	public EquipamentoMarca getMarca() {
		return marca;
	}
	public static EquipamentoMarca set(String nome) {
		return new AdicionarMarca(nome).getMarca();
	}
}

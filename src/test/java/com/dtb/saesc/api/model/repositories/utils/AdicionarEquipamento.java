package com.dtb.saesc.api.model.repositories.utils;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.EquipamentoModelo;
import com.dtb.saesc.api.model.entities.EquipamentoStatus;
import com.dtb.saesc.api.model.entities.Escola;

public class AdicionarEquipamento {
	private Equipamento equipamento;
	public AdicionarEquipamento(EquipamentoModelo modelo,EquipamentoStatus status, Escola escola) {
		equipamento = new Equipamento();
		equipamento.setModelo(modelo);
		equipamento.setStatus(status);
		equipamento.setEscola(escola);
	}
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public static Equipamento set(EquipamentoModelo modelo,EquipamentoStatus status, Escola escola) {
		return new AdicionarEquipamento(modelo, status, escola).getEquipamento();
	}
}

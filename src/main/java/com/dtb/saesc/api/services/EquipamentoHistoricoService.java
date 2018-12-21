package com.dtb.saesc.api.services;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.Funcionario;

public interface EquipamentoHistoricoService {
	public void adicionar(Equipamento equipamento, Funcionario funcionario, String comentario);
}

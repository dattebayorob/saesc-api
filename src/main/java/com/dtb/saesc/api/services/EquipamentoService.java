package com.dtb.saesc.api.services;

import java.util.List;
import java.util.Optional;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.EquipamentoHistorico;

public interface EquipamentoService {
	public Optional<Equipamento> buscarPeloId(Long id);
	public List<EquipamentoHistorico> buscarHistorico(Long id);
	public Equipamento persistir(Equipamento equipamento);
	public Boolean hasEquipamento(Long id);
}

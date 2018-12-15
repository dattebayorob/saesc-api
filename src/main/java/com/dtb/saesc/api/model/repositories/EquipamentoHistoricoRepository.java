package com.dtb.saesc.api.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.EquipamentoHistorico;

public interface EquipamentoHistoricoRepository extends JpaRepository<EquipamentoHistorico, Long>{
	List<EquipamentoHistorico> findByEquipamento(Equipamento equipamento);
	List<EquipamentoHistorico> findByEquipamentoId(Long id);
}

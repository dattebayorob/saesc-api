package com.dtb.saesc.api.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.repositories.custom.EquipamentoRepositoryQuery;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>,EquipamentoRepositoryQuery{

}

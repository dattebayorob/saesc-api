package com.dtb.saesc.api.model.repositories.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.repositories.custom.filter.EquipamentoFilter;

public interface EquipamentoRepositoryQuery {
	Page<Equipamento> findPageByDescricaoOrModeloOrStatusOrSerialOrTombamento(EquipamentoFilter filter, Pageable pageable);
}

package com.dtb.saesc.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.repositories.custom.filter.EquipamentoFilter;

public interface EquipamentoService {
	public Optional<Equipamento> buscarPeloId(Long id);

	public Page<Equipamento> buscarPaginaPorFiltros(EquipamentoFilter filter, Pageable pageable);

	public Equipamento persistir(Equipamento equipamento);

	public Boolean existePorId(Long id);
}

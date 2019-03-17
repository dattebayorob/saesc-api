package com.dtb.saesc.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.Funcionario;
import com.dtb.saesc.api.model.repositories.custom.filter.EquipamentoFilter;

import io.vavr.control.Either;

public interface EquipamentoService {
	
	public Optional<Equipamento> buscarPeloId(Long id);

	public Optional<Page<Equipamento>> buscarPaginaPorFiltros(EquipamentoFilter filter, Pageable pageable);

	public Either<RuntimeException, Equipamento> persistir(Equipamento equipamento, String comentario, Funcionario funcionario);

	public Boolean existePeloId(Long id);
}

package com.dtb.saesc.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.Funcionario;
import com.dtb.saesc.api.model.repositories.EquipamentoRepository;
import com.dtb.saesc.api.model.repositories.custom.filter.EquipamentoFilter;
import com.dtb.saesc.api.services.EquipamentoHistoricoService;
import com.dtb.saesc.api.services.EquipamentoService;

import io.vavr.control.Either;

@Service
public class EquipamentoServiceImpl implements EquipamentoService {
	@Autowired
	private EquipamentoRepository repository;
	@Autowired
	private EquipamentoHistoricoService historicoService;

	@Override
	public Optional<Equipamento> buscarPeloId(Long id) {
		return repository.findById(id);
	}

	@Override
	public Either<RuntimeException, Equipamento> persistir(Equipamento equipamento, String comentario, Funcionario funcionario) {
		equipamento = repository.save(equipamento);
		historicoService.adicionar(equipamento, funcionario, comentario);
		return Either.right(equipamento);
	}

	@Override
	public Boolean existePeloId(Long id) {
		return repository.existsById(id);
	}

	@Override
	public Optional<Page<Equipamento>> buscarPaginaPorFiltros(EquipamentoFilter filter, Pageable pageable) {
		return Optional
				.of(repository.findPageByDescricaoOrModeloOrStatusOrSerialOrTombamento(filter, pageable))
				.filter(Page::hasContent);
	}

}

package com.dtb.saesc.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.EquipamentoHistorico;
import com.dtb.saesc.api.model.entities.Funcionario;
import com.dtb.saesc.api.model.repositories.EquipamentoHistoricoRepository;
import com.dtb.saesc.api.model.repositories.EquipamentoRepository;
import com.dtb.saesc.api.model.repositories.custom.filter.EquipamentoFilter;
import com.dtb.saesc.api.services.EquipamentoService;

@Service
public class EquipamentoServiceImpl implements EquipamentoService {
	@Autowired
	private EquipamentoRepository repository;
	@Autowired
	private EquipamentoHistoricoRepository historicoRepository;

	@Override
	public Optional<Equipamento> buscarPeloId(Long id) {
		return repository.findById(id);
	}

	@Override
	public Equipamento persistir(Equipamento equipamento, String comentario, Funcionario funcionario) {
		equipamento = repository.save(equipamento);
		historicoRepository.save(new EquipamentoHistorico(equipamento, funcionario, comentario));
		return equipamento;
	}

	@Override
	public Boolean existePeloId(Long id) {
		return repository.existsById(id);
	}

	@Override
	public Page<Equipamento> buscarPaginaPorFiltros(EquipamentoFilter filter, Pageable pageable) {
		return repository.findPageByDescricaoOrModeloOrStatusOrSerialOrTombamento(filter, pageable);
	}

}

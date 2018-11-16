package com.dtb.saesc.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.EquipamentoHistorico;
import com.dtb.saesc.api.model.repositories.EquipamentoHistoricoRepository;
import com.dtb.saesc.api.model.repositories.EquipamentoRepository;
import com.dtb.saesc.api.services.EquipamentoService;

@Service
public class EquipamentoServiceImpl implements EquipamentoService{
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	@Autowired
	private EquipamentoHistoricoRepository historicoRepository;
	@Override
	public Optional<Equipamento> buscarPeloId(Long id) {
		return equipamentoRepository.findById(id);
	}
	@Override
	public List<EquipamentoHistorico> buscarHistorico(Long id) {
		return historicoRepository.findByEquipamentoId(id);
	}
	@Override
	public Equipamento persistir(Equipamento equipamento) {
		return equipamentoRepository.save(equipamento);
	}
	@Override
	public Boolean hasEquipamento(Long id) {
		return equipamentoRepository.existsById(id);
	}

}

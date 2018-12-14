package com.dtb.saesc.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.enums.CredeEnum;
import com.dtb.saesc.api.model.enums.PrefixoEnum;
import com.dtb.saesc.api.model.repositories.EscolaRepository;
import com.dtb.saesc.api.services.EscolaService;

@Service
public class EscolaServiceImpl implements EscolaService {
	@Autowired
	private EscolaRepository escolaRepository;
	
	

	@Override
	public Optional<Escola> buscarPeloId(Long id) {
		return escolaRepository.findById(id);
	}

	@Override
	public Page<Escola> buscarTodas(PageRequest pageRequest, String search) {
		return escolaRepository.findAllByNome(search, pageRequest);
		//return escolaRepository.findAll(pageRequest);
	}

	@Override
	public Optional<Escola> buscarPeloInep(String inep) {
		return escolaRepository.findByInep(inep);
	}

	@Override
	public Escola persistir(Escola escola) {
		return escolaRepository.save(escola);
	}

	@Override
	public Page<Escola> buscarTodasPorCrede(Pageable pageable, CredeEnum crede, String s) {
		return escolaRepository.findByCrede(pageable, crede, s);
	}

	@Override
	public Page<Escola> buscarTodasPorPrefixo(PageRequest pageRequest, PrefixoEnum prefixo, String s) {
		return escolaRepository.findByPrefixo(pageRequest, prefixo, s);
	}

}

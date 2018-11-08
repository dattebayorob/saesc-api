package com.dtb.saesc.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Provedor;
import com.dtb.saesc.api.model.repositories.ProvedorRepository;
import com.dtb.saesc.api.services.ProvedorService;

@Service
public class ProvedorServiceImpl implements ProvedorService {
	@Autowired
	private ProvedorRepository provedorRepository;
	
	@Override
	public Provedor persistir(Provedor provedor) {
		return provedorRepository.save(provedor);
	}

	@Override
	public Optional<Provedor> buscarPeloId(Long id) {
		return provedorRepository.findById(id);
	}

	@Override
	public void removerPeloId(Long id) {
		provedorRepository.deleteById(id);
	}
	
}

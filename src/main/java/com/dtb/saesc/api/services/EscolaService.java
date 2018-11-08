package com.dtb.saesc.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.dtb.saesc.api.model.entities.Escola;

public interface EscolaService {
	//public Optional<Escola> buscarPeloInep(String inep);
	public Optional<Escola> buscarPeloId(Long id);
	public Page<Escola> buscarTodas(PageRequest pageRequest);
}

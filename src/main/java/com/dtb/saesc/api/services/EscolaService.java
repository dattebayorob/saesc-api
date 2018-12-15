package com.dtb.saesc.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.repositories.custom.filter.EscolaFilter;

public interface EscolaService {
	public Optional<Escola> buscarPeloId(Long id);

	public Optional<Escola> buscarPeloInep(String inep);

	public Escola persistir(Escola escola);

	public Page<Escola> pesquisarEscolas(EscolaFilter filtros, Pageable page);
}

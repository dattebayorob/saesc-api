package com.dtb.saesc.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.dtb.saesc.api.model.entities.Escola;

public interface EscolaService {
	public Optional<Escola> buscarPeloId(Long id);
	public Page<Escola> buscarTodas(PageRequest pageRequest, String search);
	public Optional<Escola> buscarPeloInep(String inep);
	public Escola persistir(Escola escola);
}

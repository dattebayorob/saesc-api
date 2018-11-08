package com.dtb.saesc.api.services;

import java.util.Optional;

import com.dtb.saesc.api.model.entities.Provedor;

public interface ProvedorService {
	public Provedor persistir(Provedor provedor);
	public Optional<Provedor> buscarPeloId(Long id);
	public void removerPeloId(Long id);
}

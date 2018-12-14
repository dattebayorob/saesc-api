package com.dtb.saesc.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.enums.CredeEnum;
import com.dtb.saesc.api.model.enums.PrefixoEnum;

public interface EscolaService {
	public Optional<Escola> buscarPeloId(Long id);
	public Page<Escola> buscarTodas(PageRequest pageRequest, String search);
	public Optional<Escola> buscarPeloInep(String inep);
	public Escola persistir(Escola escola);
	public Page<Escola> buscarTodasPorCrede(Pageable pageable, CredeEnum crede, String s);
	public Page<Escola> buscarTodasPorPrefixo(PageRequest pageRequest, PrefixoEnum valueOf, String s);
}

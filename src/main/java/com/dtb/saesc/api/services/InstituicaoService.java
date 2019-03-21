package com.dtb.saesc.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dtb.saesc.api.model.entities.Instituicao;
import com.dtb.saesc.api.model.repositories.custom.filter.InstituicaoFilter;

import io.vavr.control.Either;

public interface InstituicaoService {

	public Optional<Instituicao> buscarPeloId(Long id);
	
	public Optional<Instituicao> buscarPeloInep(String inep);
	
	public boolean existePeloInep(String inep);

	public Either<RuntimeException, Instituicao> adicionar(Instituicao escola);

	public Either<RuntimeException, Instituicao> atualizar(Instituicao escola, String inep);

	public Optional<Page<Instituicao>> pesquisarEscolas(InstituicaoFilter filtros, Pageable page);
}

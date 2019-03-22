package com.dtb.saesc.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Instituicao;
import com.dtb.saesc.api.model.exceptions.ValidationErrorException;
import com.dtb.saesc.api.model.repositories.InstituicaoRepository;
import com.dtb.saesc.api.model.repositories.custom.filter.InstituicaoFilter;
import com.dtb.saesc.api.services.InstituicaoService;

import io.vavr.control.Either;

@Service
public class InstituicaoServiceImpl implements InstituicaoService {
	@Autowired
	private InstituicaoRepository repository;

	@Override
	public Optional<Instituicao> buscarPeloId(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Instituicao> buscarPeloInep(String inep) {
		return repository.findByInep(inep);
	}

	@Override
	public Either<RuntimeException, Instituicao> adicionar(Instituicao escola){
		
		if(existePeloInep(escola.getInep()))
			return Either.left(new ValidationErrorException(""));
		return Either.right(repository.save((escola)));
	}
	@Override
	public Either<RuntimeException, Instituicao> atualizar(Instituicao escola, String inep){
		
		if(!escola.getInep().equals(inep) && this.existePeloInep(inep))
			return Either.left(new ValidationErrorException(""));
		return Either.right(repository.save(escola));
	}

	@Override
	public Optional<Page<Instituicao>> pesquisar(InstituicaoFilter filtros, Pageable page) {
		
		return Optional.of(repository
				.findPageByNomeOrCredeOrIp(filtros, page)).filter(p -> p.hasContent());
	}

	@Override
	public boolean existePeloInep(String inep) {
		return repository.existsByInep(inep);
	}

}

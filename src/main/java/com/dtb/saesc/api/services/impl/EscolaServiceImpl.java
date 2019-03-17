package com.dtb.saesc.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.exceptions.ValidationErrorException;
import com.dtb.saesc.api.model.repositories.EscolaRepository;
import com.dtb.saesc.api.model.repositories.custom.filter.EscolaFilter;
import com.dtb.saesc.api.services.EscolaService;

import io.vavr.control.Either;

@Service
public class EscolaServiceImpl implements EscolaService {
	@Autowired
	private EscolaRepository repository;

	@Override
	public Optional<Escola> buscarPeloId(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Escola> buscarPeloInep(String inep) {
		return repository.findByInep(inep);
	}

	@Override
	public Either<RuntimeException, Escola> adicionar(Escola escola){
		
		if(existePeloInep(escola.getInep()))
			return Either.left(new ValidationErrorException(""));
		return Either.right(repository.save((escola)));
	}
	@Override
	public Either<RuntimeException, Escola> atualizar(Escola escola, String inep){
		
		if(!escola.getInep().equals(inep) && this.existePeloInep(inep))
			return Either.left(new ValidationErrorException(""));
		return Either.right(repository.save(escola));
	}

	@Override
	public Optional<Page<Escola>> pesquisarEscolas(EscolaFilter filtros, Pageable page) {
		
		return Optional.of(repository
				.findPageByNomeOrCredeOrPrefixo(filtros, page)).filter(p -> p.hasContent());
	}

	@Override
	public boolean existePeloInep(String inep) {
		return repository.existsByInep(inep);
	}

}

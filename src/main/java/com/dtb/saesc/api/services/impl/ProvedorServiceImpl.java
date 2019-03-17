package com.dtb.saesc.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Provedor;
import com.dtb.saesc.api.model.exceptions.ValidationErrorException;
import com.dtb.saesc.api.model.exceptions.messages.ProvedorMessages;
import com.dtb.saesc.api.model.repositories.ProvedorRepository;
import com.dtb.saesc.api.services.ProvedorService;

import io.vavr.control.Either;

@Service
public class ProvedorServiceImpl implements ProvedorService {
	@Autowired
	private ProvedorRepository repository;

	@Override
	public Optional<Provedor> buscarPeloId(Long id) {
		return repository.findById(id);
	}

	@Override
	public void removerPeloId(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Either<RuntimeException, Provedor> adicionar(Provedor provedor) {

		if (existePeloCnpj(provedor.getCnpj()))
			return Either.left(
					new ValidationErrorException(ProvedorMessages.CNPJ_JA_CADASTRADO));

		return Either.right(repository.save(provedor));
	}

	@Override
	public Either<RuntimeException, Provedor> atualizar(Provedor provedor, String cnpj) {
		
		if (!provedor.getCnpj().equals(cnpj) && existePeloCnpj(provedor.getCnpj()))
			return Either.left(
					new ValidationErrorException(ProvedorMessages.CNPJ_JA_CADASTRADO));
		
		return Either.right(repository.save(provedor));
	}

	@Override
	public boolean existePeloCnpj(String cnpj) {
		return repository.existsByCnpj(cnpj);
	}

	@Override
	public boolean existePeloId(Long id) {
		return repository.existsById(id);
	}

}

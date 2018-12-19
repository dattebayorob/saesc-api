package com.dtb.saesc.api.services.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import com.dtb.saesc.api.model.entities.Provedor;
import com.dtb.saesc.api.model.exceptions.ValidationErrorsException;
import com.dtb.saesc.api.model.repositories.ProvedorRepository;
import com.dtb.saesc.api.services.ProvedorService;

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
	public Provedor adicionar(Provedor provedor) {
		if (this.existePeloCnpj(provedor.getCnpj()))
			throw new ValidationErrorsException(Arrays.asList(new ObjectError("Provedor", "Cnpj já cadastrado")));
		return repository.save(provedor);
	}

	@Override
	public Provedor atualizar(Provedor provedor, String cnpj) {
		if (!provedor.getCnpj().equals(cnpj)) {
			if (this.existePeloCnpj(provedor.getCnpj()))
				throw new ValidationErrorsException(Arrays.asList(new ObjectError("Provedor", "Cnpj já cadastrado")));
		}
		return repository.save(provedor);
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

package com.dtb.saesc.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Provedor;
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
	public Optional<Provedor> adicionar(Provedor provedor) {
		return this.existePeloCnpj(provedor.getCnpj()) ? Optional.ofNullable(null)
				: Optional.of(repository.save(provedor));
		// throw new ValidationErrorsException(Arrays.asList(new ObjectError("Provedor",
		// "Cnpj já cadastrado")));
	}

	@Override
	public Optional<Provedor> atualizar(Provedor provedor, String cnpj) {
		return !provedor.getCnpj().equals(cnpj) && this.existePeloCnpj(provedor.getCnpj()) ? Optional.ofNullable(null)
				: Optional.of(repository.save(provedor));
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

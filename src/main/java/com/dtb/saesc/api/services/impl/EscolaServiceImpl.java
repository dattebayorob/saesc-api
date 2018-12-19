package com.dtb.saesc.api.services.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.exceptions.ValidationErrorsException;
import com.dtb.saesc.api.model.repositories.EscolaRepository;
import com.dtb.saesc.api.model.repositories.custom.filter.EscolaFilter;
import com.dtb.saesc.api.services.EscolaService;

@Service
public class EscolaServiceImpl implements EscolaService {
	@Autowired
	private EscolaRepository escolaRepository;

	@Override
	public Optional<Escola> buscarPeloId(Long id) {
		return escolaRepository.findById(id);
	}

	@Override
	public Optional<Escola> buscarPeloInep(String inep) {
		return escolaRepository.findByInep(inep);
	}

	@Override
	public Escola adicionar(Escola escola) {
		if (this.existePeloInep(escola.getInep())) {
			throw new ValidationErrorsException(Arrays.asList(new ObjectError("Escola", "Inep já cadastrado")));
		}
		return escolaRepository.save(escola);
	}

	@Override
	public Escola atualizar(Escola escola, String inep) {
		if (!escola.getInep().equals(inep)) {
			if (this.existePeloInep(inep))
				throw new ValidationErrorsException(Arrays.asList(new ObjectError("Escola", "Inep já cadastrado")));
		}
		return escolaRepository.save(escola);
	}

	@Override
	public Page<Escola> pesquisarEscolas(EscolaFilter filtros, Pageable page) {
		return escolaRepository.findPageByNomeOrCredeOrPrefixo(filtros, page);
	}

	@Override
	public boolean existePeloInep(String inep) {
		return escolaRepository.existsByInep(inep);
	}

}

package com.dtb.saesc.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.enums.CredeEnum;
import com.dtb.saesc.api.model.enums.PrefixoEnum;
import com.dtb.saesc.api.model.exceptions.ValidationErrorsException;
import com.dtb.saesc.api.model.repositories.EscolaRepository;
import com.dtb.saesc.api.model.repositories.custom.filter.EscolaFilter;
import com.dtb.saesc.api.model.utils.EnumUtils;
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
		if (!validarCadastro(escola).isEmpty())
			throw new ValidationErrorsException(validarCadastro(escola));
		return escolaRepository.save(escola);
	}

	@Override
	public Escola atualizar(Escola escola, Escola escolaAntiga) {
		List<ObjectError> erros = validarAtualizacao(escola, escolaAntiga);
		if (!erros.isEmpty())
			throw new ValidationErrorsException(erros);
		return escolaRepository.save(escola);
	}

	private List<ObjectError> validarCadastro(Escola escola) {
		List<ObjectError> erros = new ArrayList<ObjectError>();
		if (this.existePeloInep(escola.getInep()))
			erros.add(new ObjectError("Escola", "Inep já cadastrado"));
		validar(escola, erros);
		return erros;
	}

	private List<ObjectError> validarAtualizacao(Escola escolaAntiga, Escola escola) {
		List<ObjectError> erros = new ArrayList<>();
		if (!escola.getInep().equals(escolaAntiga.getInep())) {
			if (existePeloInep(escola.getInep()))
				erros.add(new ObjectError("Escola", "Inep já cadastrado"));
		}
		validar(escola, erros);
		return erros;
	}

	private void validar(Escola escola, List<ObjectError> erros) {
		if(!EnumUtils.isValid(escola.getPrefixo().toString(), PrefixoEnum.values()))
			erros.add(new ObjectError("Escola","Prefixo invalido."));
		if(!EnumUtils.isValid(escola.getCrede().toString(), CredeEnum.values()))
			erros.add(new ObjectError("Escola", "Crede invalida."));
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

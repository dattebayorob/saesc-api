package com.dtb.saesc.api.services;

import java.util.Optional;

import com.dtb.saesc.api.model.entities.Funcionario;

import io.vavr.control.Either;

public interface FuncionarioService {
	
	Either<RuntimeException,Funcionario> adicionar(Funcionario funcionario);

	Optional<Funcionario> buscarPeloEmail(String email);
	/**
	 * 
	 * Retorno será substituído por um Either logo.
	 * 
	 * */
	Funcionario buscarPeloContexto();
}

package com.dtb.saesc.api.services;

import java.util.Optional;

import com.dtb.saesc.api.model.entities.Funcionario;

public interface FuncionarioService {
	Funcionario persistir(Funcionario funcionario);
	Optional<Funcionario> buscarPeloEmail(String email);
}

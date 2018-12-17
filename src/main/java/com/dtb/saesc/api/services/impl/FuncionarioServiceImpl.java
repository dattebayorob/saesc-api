package com.dtb.saesc.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Funcionario;
import com.dtb.saesc.api.model.repositories.FuncionarioRepository;
import com.dtb.saesc.api.services.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public Funcionario persistir(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	@Override
	public Optional<Funcionario> buscarPeloEmail(String email) {
		return funcionarioRepository.findByEmail(email);
	}

	@Override
	public Optional<Funcionario> buscarPelaSessao() {
			UserDetails userDetails =
					 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return funcionarioRepository.findByEmail(userDetails.getUsername());
	}
}

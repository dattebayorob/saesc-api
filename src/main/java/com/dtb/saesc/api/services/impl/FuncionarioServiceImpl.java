package com.dtb.saesc.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Funcionario;
import com.dtb.saesc.api.model.exceptions.ValidationErrorException;
import com.dtb.saesc.api.model.exceptions.messages.UserMessages;
import com.dtb.saesc.api.model.repositories.FuncionarioRepository;
import com.dtb.saesc.api.services.FuncionarioService;

import io.vavr.control.Either;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public Either<RuntimeException,Funcionario> adicionar(Funcionario funcionario) {
		if(buscarPeloEmail(funcionario.getEmail()).isPresent())
			return Either.left(new ValidationErrorException(UserMessages.EMAIL_JA_CADASTRADO));
		return Either.right(funcionarioRepository.save(funcionario));
	}

	@Override
	public Optional<Funcionario> buscarPeloEmail(String email) {
		return funcionarioRepository.findByEmail(email);
	}
	
	@Override
	public Funcionario buscarPeloContexto() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return funcionarioRepository.findByEmail(userDetails.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("Email não encontrado"));
	}
}

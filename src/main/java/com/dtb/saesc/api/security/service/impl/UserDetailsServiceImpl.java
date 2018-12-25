package com.dtb.saesc.api.security.service.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Funcionario;
import com.dtb.saesc.api.security.model.entities.Usuario;
import com.dtb.saesc.api.services.FuncionarioService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private FuncionarioService funcionarioService;

	@Override
	public UserDetails loadUserByUsername(String username){
		Optional<Funcionario> f = funcionarioService.buscarPeloEmail(username);
		if (!f.isPresent())
			throw new UsernameNotFoundException("Email não encontrado" + username);
		return new Usuario(username, f.get().getSenha(), true, true, true, true,
				Arrays.asList(new SimpleGrantedAuthority("ROLE"))
				, f.get());
	}

}

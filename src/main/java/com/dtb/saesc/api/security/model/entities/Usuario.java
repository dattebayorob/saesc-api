package com.dtb.saesc.api.security.model.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.dtb.saesc.api.model.entities.Funcionario;

public class Usuario extends User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8988002030953036277L;
	Funcionario funcionario;
	public Usuario(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, Funcionario funcionario) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.funcionario = funcionario;
	}

}

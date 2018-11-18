package com.dtb.saesc.api.model.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.dtb.saesc.api.model.entities.GenericEntity;

public class FuncionarioDto implements GenericEntity {
	private Long id;
	@NotEmpty(message = "O nome do funcionario deve ser informado.")
	private String nome;
	@NotEmpty(message = "O Cpf do funcionario deve ser inforamdo.")
	@CPF(message = "CPF invalido.")
	private String cpf;
	@NotEmpty(message = "O email do funcionario deve ser inforamdo.")
	@Email(message = "Email invalido.")
	private String email;
	@NotEmpty(message = "Senha deve ser informada")
	@Length(min = 5, max = 20, message = "Senha deve conter entre 5 e 20 caracteres")
	private String senha;
	private String perfil;
	@NotEmpty
	private String escolaId;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getEscolaId() {
		return escolaId;
	}

	public void setEscolaId(String escolaId) {
		this.escolaId = escolaId;
	}

}

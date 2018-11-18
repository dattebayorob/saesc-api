package com.dtb.saesc.api.model.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CNPJ;

import com.dtb.saesc.api.model.entities.GenericEntity;

public class ProvedorDto implements GenericEntity {
	private Long id;
	@NotEmpty(message = "O CNPJ do provedor de internet deve ser informado.")
	@CNPJ(message = "O CNPJ informado não é valido.")
	private String cnpj;
	@NotEmpty(message = "Nome do provedor de internet deve ser informado.")
	private String nome;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

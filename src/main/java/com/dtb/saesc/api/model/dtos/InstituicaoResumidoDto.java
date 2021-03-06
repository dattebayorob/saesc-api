package com.dtb.saesc.api.model.dtos;

import java.util.List;

public class InstituicaoResumidoDto {
	private Long id;
	private String crede;
	private String nome;
	private String inep;
	private String telefone;
	private List<LinkResumidoDto> links;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCrede() {
		return crede;
	}

	public void setCrede(String crede) {
		this.crede = crede;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getInep() {
		return inep;
	}

	public void setInep(String inep) {
		this.inep = inep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<LinkResumidoDto> getLinks() {
		return links;
	}

	public void setLinks(List<LinkResumidoDto> links) {
		this.links = links;
	}

}

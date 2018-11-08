package com.dtb.saesc.api.model.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EscolaDto {
	private Long id;
	private String prefixo;
	private String nome;
	private List<String> linksIp;
	private String crede;
	private String rua;
	private String bairro;
	private String telefone;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrefixo() {
		return prefixo;
	}
	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<String> getLinksIp() {
		if(this.linksIp == null) {
			this.linksIp = new ArrayList<>();
		}
		return linksIp;
	}
	public void setLinksIp(List<String> linksIp) {
		this.linksIp = linksIp;
	}
	public String getCrede() {
		return crede;
	}
	public void setCrede(String crede) {
		this.crede = crede;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}

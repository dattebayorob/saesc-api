package com.dtb.saesc.api.model.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EscolaDto {
	private Optional<Long> id;
	private String prefixo;
	private String nome;
	private List<String> ips;
	private String crede;
	private String rua;
	private String bairro;
	private String telefone;
	public Optional<Long> getId() {
		return id;
	}
	public void setId(Optional<Long> id) {
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
	public List<String> getIps() {
		if(this.ips == null) {
			this.ips = new ArrayList<>();
		}
		return ips;
	}
	public void setIps(List<String> ips) {
		this.ips = ips;
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

package com.dtb.saesc.api.model.repositories.custom.filter;

public class InstituicaoFilter {
	private String nome;
	private String crede;
	private String ip;
	public InstituicaoFilter() {
		// TODO Auto-generated constructor stub
	}
	
	public InstituicaoFilter(String nome, String crede) {
		this.nome = nome;
		this.crede = crede;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCrede() {
		return crede;
	}
	public void setCrede(String crede) {
		this.crede = crede;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}

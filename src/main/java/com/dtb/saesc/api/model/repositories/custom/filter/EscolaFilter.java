package com.dtb.saesc.api.model.repositories.custom.filter;

public class EscolaFilter {
	private String nome;
	private String crede;
	private String prefixo;
	public EscolaFilter() {
		// TODO Auto-generated constructor stub
	}
	
	public EscolaFilter(String nome, String crede, String prefixo) {
		this.nome = nome;
		this.crede = crede;
		this.prefixo = prefixo;
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
	public String getPrefixo() {
		return prefixo;
	}
	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}
	
}

package com.dtb.saesc.api.model.dtos;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;


public class LinkDto {
	
	private Optional<Long> id;
	@NotEmpty(message="Endereço ip do link deve ser informado.")
	private String ip;
	@NotEmpty(message="Provedor deve ser informado.")
	private String provedorNome;
	private String circuito;
	private String status;
	@NotEmpty(message="Escola deve ser informada.")
	private String escolaNome;
	public Optional<Long> getId() {
		return id;
	}
	public void setId(Optional<Long> id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getProvedorNome() {
		return provedorNome;
	}
	public void setProvedorNome(String provedorNome) {
		this.provedorNome = provedorNome;
	}
	public String getCircuito() {
		return circuito;
	}
	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEscolaNome() {
		return escolaNome;
	}
	public void setEscolaNome(String escolaNome) {
		this.escolaNome = escolaNome;
	}
	
	
}

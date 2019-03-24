package com.dtb.saesc.api.model.dtos;

import javax.validation.constraints.NotNull;

public class LinkDto {

	private Long id;
	@NotNull(message = "Endereço ip do link deve ser informado.")
	private String ip;
	@NotNull(message = "Provedor deve ser informado.")
	private Long provedorId;
	private String provedorNome;
	private String circuito;
	private String status;
	@NotNull(message = "Escola deve ser informada.")
	private long escolaId;
	private String escolaNome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getProvedorId() {
		return provedorId;
	}

	public void setProvedorId(Long provedorId) {
		this.provedorId = provedorId;
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

	public long getEscolaId() {
		return escolaId;
	}

	public void setEscolaId(long escolaId) {
		this.escolaId = escolaId;
	}

	public String getEscolaNome() {
		return escolaNome;
	}

	public void setEscolaNome(String escolaNome) {
		this.escolaNome = escolaNome;
	}

}

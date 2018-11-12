package com.dtb.saesc.api.model.dtos;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CNPJ;

public class ProvedorDto {
	private Long id;
	@NotEmpty(message="O CNPJ do provedor de internet deve ser informado.")
	@CNPJ(message="O CNPJ informado não é valido.")
	private String cnpj;
	@NotEmpty(message="Nome do provedor de internet deve ser informado.")
	private String nome;
	private Date dataCricao;
	private Date dataAtualizacao;
	public Long getId() {
		return id;
	}
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
	public Date getDataCricao() {
		return dataCricao;
	}
	public void setDataCricao(Date dataCricao) {
		this.dataCricao = dataCricao;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	
}

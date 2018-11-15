package com.dtb.saesc.api.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.dtb.saesc.api.model.enums.CredeEnum;
import com.dtb.saesc.api.model.enums.PrefixoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "escola")
public class Escola {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private PrefixoEnum prefixo;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false, unique = true)
	private String inep;
	@Enumerated(EnumType.STRING)
	private CredeEnum crede;
	private String rua;
	private String bairro;
	private String telefone;
	@Column(name = "data_criacao", nullable = false)
	private Date dataCriacao;
	@Column(name = "data_atualizacao", nullable = false)
	private Date dataAtualizacao;
	@OneToMany(mappedBy = "escola", fetch = FetchType.LAZY)
	//@JsonIgnore
	private List<Link> links;
	@OneToMany(mappedBy = "escola", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Funcionario> funcionarios;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PrefixoEnum getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(PrefixoEnum prefixo) {
		this.prefixo = prefixo;
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

	public CredeEnum getCrede() {
		return crede;
	}

	public void setCrede(CredeEnum crede) {
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

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@PreUpdate
	public void preUpdate() {
		this.dataAtualizacao = new Date();
	}

	@PrePersist
	public void prePersist() {
		final Date dataAtual = new Date();
		this.dataCriacao = dataAtual;
		this.dataAtualizacao = dataAtual;
	}

	@Override
	public String toString() {
		return "Escola [id=" + id + ", prefixo=" + prefixo + ", nome=" + nome + ", inep=" + inep + ", crede=" + crede
				+ ", rua=" + rua + ", bairro=" + bairro + ", telefone=" + telefone + ", dataCriacao=" + dataCriacao
				+ ", dataAtualizacao=" + dataAtualizacao + ", links=" + links + ", funcionarios=" + funcionarios + "]";
	}

	

	

}

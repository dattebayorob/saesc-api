package com.dtb.saesc.api.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "equipamento")
public class Equipamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_modelo", nullable = false)
	private EquipamentoModelo modelo;
	private String serial;
	private String tombamento;
	private String descricao;	
	@OneToMany(mappedBy = "equipamento", fetch = FetchType.LAZY)
	private List<EquipamentoHistorico> historico;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_escola", nullable = false)
	private Escola escola;
	@Column(name = "data_criacao", nullable = false)
	private Date dataCriacao;
	@Column(name = "data_atualizacao")
	private Date dataAtualizacao;

	public Equipamento() {
		// TODO Auto-generated constructor stub
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<EquipamentoHistorico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<EquipamentoHistorico> historico) {
		this.historico = historico;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

	public EquipamentoModelo getModelo() {
		return modelo;
	}

	public void setModelo(EquipamentoModelo modelo) {
		this.modelo = modelo;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getTombamento() {
		return tombamento;
	}

	public void setTombamento(String tombamento) {
		this.tombamento = tombamento;
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

}

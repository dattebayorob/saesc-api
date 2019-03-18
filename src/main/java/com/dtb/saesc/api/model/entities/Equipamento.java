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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
@Entity
@Table(name = "equipamento")
public class Equipamento implements GenericEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_modelo", nullable = false)
	private EquipamentoModelo modelo;
	private String serial;
	private String tombamento;
	private String descricao;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_status", nullable = false)
	private EquipamentoStatus status;
	@OneToMany(mappedBy = "equipamento", fetch = FetchType.LAZY, targetEntity = EquipamentoHistorico.class)
	@OrderBy(value = "id DESC")
	private List<EquipamentoHistorico> historico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_escola", nullable = false)
	private Escola escola;
	@Column(name = "data_criacao", nullable = false)
	private Date dataCriacao;
	@Column(name = "data_atualizacao")
	private Date dataAtualizacao;

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
	public Long getId() {
		return id;
	}
	@Override
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
	
	
	
	public EquipamentoStatus getStatus() {
		return status;
	}

	public void setStatus(EquipamentoStatus status) {
		this.status = status;
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

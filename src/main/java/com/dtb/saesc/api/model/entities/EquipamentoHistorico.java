package com.dtb.saesc.api.model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "equipamento_historico")
public class EquipamentoHistorico implements GenericEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_equipamento", nullable = false)
	private Equipamento equipamento;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcionario", nullable = false)
	private Funcionario funcionario;
	private String comentario;
	private Date data;
	public EquipamentoHistorico() {
		// TODO Auto-generated constructor stub
	}
	public EquipamentoHistorico(Equipamento equipamento, Funcionario funcionario, String comentario) {
		this.equipamento = equipamento;
		this.funcionario = funcionario;
		this.comentario = comentario;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@PreUpdate
	@PrePersist
	private void prePersist() {
		this.data = new Date();
	}

}

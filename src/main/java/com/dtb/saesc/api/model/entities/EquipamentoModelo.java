package com.dtb.saesc.api.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dtb.saesc.api.model.enums.EquipamentoTipoEnum;

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
@Table(name = "equipamento_modelo")
public class EquipamentoModelo{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_marca", nullable = false)
	private EquipamentoMarca marca;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EquipamentoTipoEnum tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EquipamentoMarca getMarca() {
		return marca;
	}

	public void setMarca(EquipamentoMarca marca) {
		this.marca = marca;
	}

	public EquipamentoTipoEnum getTipo() {
		return tipo;
	}

	public void setTipo(EquipamentoTipoEnum tipo) {
		this.tipo = tipo;
	}

}

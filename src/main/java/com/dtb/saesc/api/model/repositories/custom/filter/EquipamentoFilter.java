package com.dtb.saesc.api.model.repositories.custom.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class EquipamentoFilter {
	private String descricao;
	private String serial;
	private String tombamento;
	private Long modeloId;
	private String tipoEquipamento;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate criadoDe;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate criadoAte;

	public EquipamentoFilter() {
		// TODO Auto-generated constructor stub
	}

	public EquipamentoFilter(String descricao, String serial, String tombamento, Long modelo, String tipoEquipamento,
			LocalDate criadoDe, LocalDate criadoAte) {
		this.descricao = descricao;
		this.serial = serial;
		this.tombamento = tombamento;
		this.modeloId = modelo;
		this.tipoEquipamento = tipoEquipamento;
		this.criadoDe = criadoDe;
		this.criadoAte = criadoAte;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Long getModeloId() {
		return modeloId;
	}

	public void setModeloId(Long modeloId) {
		this.modeloId = modeloId;
	}

	public String getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(String tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public LocalDate getCriadoDe() {
		return criadoDe;
	}

	public void setCriadoDe(LocalDate criadoDe) {
		this.criadoDe = criadoDe;
	}

	public LocalDate getCriadoAte() {
		return criadoAte;
	}

	public void setCriadoAte(LocalDate criadoAte) {
		this.criadoAte = criadoAte;
	}

}

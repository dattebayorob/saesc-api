package com.dtb.saesc.api.model.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class EquipamentoDto {
	private Long id;
	@NotEmpty(message = "Descrição deve ser informado.")
	@Length(min = 5, max = 255, message = "Descrição deve conter entre 5 e 255 caracteres.")
	private String descricao;
	@NotEmpty(message = "O Modelo deve ser informado.")
	private Long modeloId;
	private String modeloNome;
	@NotEmpty(message = "A marca deve ser informada.")
	private Long modeloMarcaId;
	private String modeloTipo;
	private String modeloMarcaNome;
	@NotEmpty(message = "A escola deve ser informada.")
	private Long escolaId;
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
	public Long getModeloId() {
		return modeloId;
	}
	public void setModeloId(Long modeloId) {
		this.modeloId = modeloId;
	}
	public String getModeloNome() {
		return modeloNome;
	}
	public void setModeloNome(String modeloNome) {
		this.modeloNome = modeloNome;
	}
	public Long getModeloMarcaId() {
		return modeloMarcaId;
	}
	public void setModeloMarcaId(Long modeloMarcaId) {
		this.modeloMarcaId = modeloMarcaId;
	}
	public String getModeloTipo() {
		return modeloTipo;
	}
	public void setModeloTipo(String modeloTipo) {
		this.modeloTipo = modeloTipo;
	}
	public String getModeloMarcaNome() {
		return modeloMarcaNome;
	}
	public void setModeloMarcaNome(String modeloMarcaNome) {
		this.modeloMarcaNome = modeloMarcaNome;
	}
	public Long getEscolaId() {
		return escolaId;
	}
	public void setEscolaId(Long escolaId) {
		this.escolaId = escolaId;
	}
	
}

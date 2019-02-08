package com.dtb.saesc.api.model.dtos;

import com.dtb.saesc.api.model.entities.GenericEntity;

public class EquipamentoResumidoDto implements GenericEntity{
	private Long id;
	private String descricao;
	private String modeloNome;
	private String modeloMarcaNome;
	private String escolaId;
	private String escolaNome;
	private String statusNome;
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
	public String getModeloNome() {
		return modeloNome;
	}
	public void setModeloNome(String modeloNome) {
		this.modeloNome = modeloNome;
	}
	public String getModeloMarcaNome() {
		return modeloMarcaNome;
	}
	public void setModeloMarcaNome(String modeloMarcaNome) {
		this.modeloMarcaNome = modeloMarcaNome;
	}
	public String getEscolaId() {
		return escolaId;
	}
	public void setEscolaId(String escolaId) {
		this.escolaId = escolaId;
	}
	public String getEscolaNome() {
		return escolaNome;
	}
	public void setEscolaNome(String escolaNome) {
		this.escolaNome = escolaNome;
	}
	public String getStatusNome() {
		return statusNome;
	}
	public void setStatusNome(String statusNome) {
		this.statusNome = statusNome;
	}
	
}

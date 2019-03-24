package com.dtb.saesc.api.model.dtos;

public class EquipamentoResumidoDto {
	private Long id;
	private String descricao;
	private String modeloNome;
	private String modeloMarcaNome;
	private String instituicaoId;
	private String instituicaoNome;
	private String statusNome;

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

	public String getInstituicaoId() {
		return instituicaoId;
	}

	public void setInstituicaoId(String instituicaoId) {
		this.instituicaoId = instituicaoId;
	}

	public String getInstituicaoNome() {
		return instituicaoNome;
	}

	public void setInstituicaoNome(String instituicaoNome) {
		this.instituicaoNome = instituicaoNome;
	}

	public String getStatusNome() {
		return statusNome;
	}

	public void setStatusNome(String statusNome) {
		this.statusNome = statusNome;
	}

}

package com.dtb.saesc.api.model.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class EquipamentoCadastroDto {
	private Long id;
	@NotNull(message = "Uma descrição deve ser informada.")
	@Length(min = 5, max = 255, message = "Descrição deve conter entre 5 e 255 caracteres.")
	private String descricao;
	@NotNull(message = "Modelo deve ser informado.")
	private Long modeloId;
	@NotNull(message = "Instituicao deve ser informada.")
	private Long instituicaoId;
	@NotNull(message = "Status do equipamento deve ser informado.")
	private Long statusId;
	private String comentario;

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

	public Long getInstituicaoId() {
		return instituicaoId;
	}

	public void setInstituicaoId(Long instituicaoId) {
		this.instituicaoId = instituicaoId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}

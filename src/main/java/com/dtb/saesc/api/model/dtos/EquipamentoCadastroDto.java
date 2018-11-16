package com.dtb.saesc.api.model.dtos;

public class EquipamentoCadastroDto {
	private Long id;
	private String descricao;
	private Long modeloId;
	private Long escolaId;
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
	public Long getEscolaId() {
		return escolaId;
	}
	public void setEscolaId(Long escolaId) {
		this.escolaId = escolaId;
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

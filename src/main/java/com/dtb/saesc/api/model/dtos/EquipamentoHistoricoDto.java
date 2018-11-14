package com.dtb.saesc.api.model.dtos;

import javax.validation.constraints.NotEmpty;

public class EquipamentoHistoricoDto {
	private Long id;
	@NotEmpty(message = "O equipamento deve ser informado.")
	private Long equipamentoId;
	@NotEmpty(message = "O funcionario deve ser inforamdo.")
	private Long funcionarioId;
	private String funcionarioNome;
	private String comentario;
	private String data;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEquipamentoId() {
		return equipamentoId;
	}
	public void setEquipamentoId(Long equipamentoId) {
		this.equipamentoId = equipamentoId;
	}
	public Long getFuncionarioId() {
		return funcionarioId;
	}
	public void setFuncionarioId(Long funcionarioId) {
		this.funcionarioId = funcionarioId;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getFuncionarioNome() {
		return funcionarioNome;
	}
	public void setFuncionarioNome(String funcionarioNome) {
		this.funcionarioNome = funcionarioNome;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
	
}

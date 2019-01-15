package com.dtb.saesc.api.model.dtos;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EquipamentoHistoricoDto {
	private Long id;
	@NotNull(message = "O equipamento deve ser informado.")
	private Long equipamentoId;
	@NotNull(message = "O funcionario deve ser inforamdo.")
	private Long funcionarioId;
	private String funcionarioNome;
	private String comentario;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:MM",timezone = "America/Fortaleza")	
	private Date data;
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
	
}

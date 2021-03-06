package com.dtb.saesc.api.model.dtos;

import java.util.ArrayList;
import java.util.List;

public class EquipamentoDto{
	private Long id;
	private String descricao;
	private Long modeloId;
	private String modeloTipo;
	private String modeloNome;
	private Long modeloMarcaId;
	private String modeloMarcaNome;
	private Long instituicaoId;
	private String instituicaoNome;
	private String statusNome;
	private List<EquipamentoHistoricoDto> historico;

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

	public Long getInstituicaoId() {
		return instituicaoId;
	}

	public void setInstituicaoId(Long instituicaoId) {
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

	public List<EquipamentoHistoricoDto> getHistorico() {
		if (this.historico == null)
			historico = new ArrayList<>();
		return historico;
	}

	public void setHistorico(List<EquipamentoHistoricoDto> historico) {
		this.historico = historico;
	}

}

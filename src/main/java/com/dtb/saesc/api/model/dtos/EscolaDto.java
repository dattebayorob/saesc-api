package com.dtb.saesc.api.model.dtos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.dtb.saesc.api.model.enums.CredeEnum;
import com.dtb.saesc.api.model.enums.PrefixoEnum;
import com.dtb.saesc.api.model.utils.validators.Enum;

public class EscolaDto{
	private Long id;
	@NotNull(message = "Prefixo deve ser informado.")
	@Enum(enumClass = PrefixoEnum.class, message = "Prefixo invalido.")
	private String prefixo;
	@NotNull(message = "O nome da escola deve ser informado.")
	private String nome;
	@NotNull(message = "O inep da escola deve ser informado.")
	@Length(min = 6, max = 8, message = "O Inep da escola deve conter entre 3 e 8 caracteres.")
	private String inep;
	private List<LinkDto> links;
	@NotNull(message = "A crede da escola deve ser informada.")
	@Enum(enumClass = CredeEnum.class, message = "Crede invalida.")
	private String crede;
	private String rua;
	private String bairro;
	private String telefone;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getInep() {
		return inep;
	}

	public void setInep(String inep) {
		this.inep = inep;
	}

	
	
	public List<LinkDto> getlinks() {
		if(this.links == null)
			this.links = new ArrayList<>();
		return links;
	}

	public void setlinks(List<LinkDto> links) {
		this.links = links;
	}

	public String getCrede() {
		return crede;
	}

	public void setCrede(String crede) {
		this.crede = crede;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}

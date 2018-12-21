package com.dtb.saesc.api.model.repositories.utils;

import com.dtb.saesc.api.model.entities.Escola;

public class AdicionarEscola {
	private Escola escola;
	public AdicionarEscola(String inep) {
		escola = new Escola();
		escola.setInep(inep);
		escola.setNome("Fake Escola");
	}
	public Escola getEscola() {
		return escola;
	}
	public static Escola set(String inep) {
		return new AdicionarEscola(inep).getEscola();
	}
}

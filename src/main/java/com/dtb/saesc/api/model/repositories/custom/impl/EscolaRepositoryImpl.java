package com.dtb.saesc.api.model.repositories.custom.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.repositories.custom.EscolaRepositoryQuery;

public class EscolaRepositoryImpl implements EscolaRepositoryQuery{
	
	@Override
	public Page<Escola> findAllByNome(String search, Pageable pageable) {
		return null;
		
	}

}

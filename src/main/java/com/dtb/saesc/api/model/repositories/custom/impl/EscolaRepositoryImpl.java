package com.dtb.saesc.api.model.repositories.custom.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.repositories.custom.EscolaRepositoryQuery;

public class EscolaRepositoryImpl implements EscolaRepositoryQuery{
	
	@Override
	public Page<Escola> findAllByNome(String search, Pageable pageable) {
		return null;
		
	}

	@Override
	public List<Escola> findListByNome(String search) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.dtb.saesc.api.model.repositories.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.repositories.custom.filter.EscolaFilter;

public interface EscolaRepositoryQuery {

	Page<Escola> findPageByNomeOrCredeOrPrefixo(EscolaFilter filter, Pageable pageable);
}

package com.dtb.saesc.api.model.repositories.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dtb.saesc.api.model.entities.Instituicao;
import com.dtb.saesc.api.model.repositories.custom.filter.InstituicaoFilter;

public interface InstituicaoRepositoryQuery {

	Page<Instituicao> findPageByNomeOrCrede(InstituicaoFilter filter, Pageable pageable);
}

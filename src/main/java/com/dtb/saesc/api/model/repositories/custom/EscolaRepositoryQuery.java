package com.dtb.saesc.api.model.repositories.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dtb.saesc.api.model.entities.Escola;

public interface EscolaRepositoryQuery {

	/*
	 * TODO: Implementar CriteriaBuilder
	 * 
	 **/

	@Query("SELECT e FROM Escola e WHERE lower(e.nome) like lower('%' || :s || '%')")
	Page<Escola> findAllByNome(@Param("s") String search, Pageable pageable);
}

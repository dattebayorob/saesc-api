package com.dtb.saesc.api.model.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.repositories.custom.EscolaRepositoryQuery;

public interface EscolaRepository extends JpaRepository<Escola, Long>,EscolaRepositoryQuery {
	Page<Escola> findAll(Pageable pageable);

	Optional<Escola> findByInep(String inep);
	
}

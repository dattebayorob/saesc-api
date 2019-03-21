package com.dtb.saesc.api.model.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dtb.saesc.api.model.entities.Instituicao;
import com.dtb.saesc.api.model.repositories.custom.InstituicaoRepositoryQuery;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long>, InstituicaoRepositoryQuery {
	Page<Instituicao> findAll(Pageable pageable);

	Optional<Instituicao> findByInep(String inep);
	
	boolean existsByInep(String inep);

}

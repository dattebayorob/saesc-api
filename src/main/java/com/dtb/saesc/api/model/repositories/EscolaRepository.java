package com.dtb.saesc.api.model.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dtb.saesc.api.model.entities.Escola;
import java.lang.String;
import java.util.List;

public interface EscolaRepository extends JpaRepository<Escola, Long>{
	Page<Escola> findAll(Pageable pageable);

	Optional<Escola> findByInep(String inep);
}

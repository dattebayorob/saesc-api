package com.dtb.saesc.api.model.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.enums.CredeEnum;
import com.dtb.saesc.api.model.enums.PrefixoEnum;
import com.dtb.saesc.api.model.repositories.custom.EscolaRepositoryQuery;

public interface EscolaRepository extends JpaRepository<Escola, Long>,EscolaRepositoryQuery {
	Page<Escola> findAll(Pageable pageable);

	Optional<Escola> findByInep(String inep);
	
	@Query("select e from Escola e WHERE e.crede = :crede AND lower(e.nome) like lower('%' || :s || '%')")
	Page<Escola> findByCrede(Pageable pageable, @Param("crede") CredeEnum crede, @Param("s") String s);
	
	@Query("select e from Escola e WHERE e.prefixo = :pref AND lower(e.nome) like lower('%' || :s || '%')")
	Page<Escola> findByPrefixo(Pageable pageable, @Param("pref") PrefixoEnum prefixo, String s);
	
}

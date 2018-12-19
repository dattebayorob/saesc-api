package com.dtb.saesc.api.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtb.saesc.api.model.entities.Provedor;

public interface ProvedorRepository extends JpaRepository<Provedor, Long> {
	boolean existsByCnpj(String cnpj);

	boolean existsById(Long id);
}

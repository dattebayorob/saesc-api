package com.dtb.saesc.api.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.entities.Provedor;

public interface LinkRepository extends JpaRepository<Link, Long> {
	Optional<Link> findByIpAndProvedor(String ip,Provedor provedor);
}

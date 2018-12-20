package com.dtb.saesc.api.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dtb.saesc.api.model.entities.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
	List<Link> findByEscolaId(Long idEscola);

	@Query(value = "SELECT l FROM Link l WHERE l.ip like %:ip%")
	List<Link> findAllByIp(@Param("ip") String ip);
}

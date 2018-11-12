package com.dtb.saesc.api.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.entities.Escola;
import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long>{
	List<Link> findByEscola(Escola escola);
}

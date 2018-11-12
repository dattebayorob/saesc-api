package com.dtb.saesc.api.model.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.dtb.saesc.api.model.entities.Link;

@NamedQueries({
	@NamedQuery(name = "LinkRepository.findByEscolaId",
			query = "SELECT link FROM escola_link link WHERE link.escola.id = :idEscola")
})
public interface LinkRepository extends JpaRepository<Link, Long>{
	List<Link> findByEscolaId(@Param("idEscola") Long idEscola);
}

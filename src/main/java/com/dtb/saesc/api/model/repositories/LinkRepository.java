package com.dtb.saesc.api.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtb.saesc.api.model.entities.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
}

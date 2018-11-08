package com.dtb.saesc.api.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtb.saesc.api.model.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}

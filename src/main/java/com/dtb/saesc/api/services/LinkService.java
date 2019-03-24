package com.dtb.saesc.api.services;

import java.util.Optional;

import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.entities.Provedor;

import io.vavr.control.Either;

public interface LinkService {

	Either<RuntimeException, Link> adicionar(Link link);
	
	Either<RuntimeException, Link> atualizar(Link link, String ip);
	
	Optional<Link> buscarPeloId(Long id);
	
	Optional<Link> buscarPeloIpEProvedor(String ip, Provedor provedor);
}

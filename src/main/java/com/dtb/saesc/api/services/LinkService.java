package com.dtb.saesc.api.services;

import com.dtb.saesc.api.model.entities.Link;

import io.vavr.control.Either;

public interface LinkService {

	Either<RuntimeException, Link> adicionar(Link link);
}

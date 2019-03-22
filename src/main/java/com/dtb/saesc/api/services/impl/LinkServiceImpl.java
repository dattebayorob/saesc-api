package com.dtb.saesc.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.repositories.LinkRepository;
import com.dtb.saesc.api.services.LinkService;

import io.vavr.control.Either;

@Service
public class LinkServiceImpl implements LinkService {

	@Autowired
	private LinkRepository repository;
	
	@Override
	public Either<RuntimeException, Link> adicionar(Link link){
		return Either.right(repository.save(link));
	}

}

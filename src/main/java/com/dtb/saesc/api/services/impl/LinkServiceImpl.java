package com.dtb.saesc.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.entities.Provedor;
import com.dtb.saesc.api.model.exceptions.ValidationErrorException;
import com.dtb.saesc.api.model.exceptions.messages.LinkMessages;
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

	@Override
	public Optional<Link> buscarPeloId(Long id) {
		return repository.findById(id);
	}

	@Override
	public Either<RuntimeException, Link> atualizar(Link link, String ip) {
		if(! link.getIp().equals(ip) && buscarPeloIpEProvedor(ip, link.getProvedor()).isPresent())
			return Either.left(new ValidationErrorException(LinkMessages.IP_JA_CADASTRADO));
		return Either.right(link);
	}

	@Override
	public Optional<Link> buscarPeloIpEProvedor(String ip, Provedor provedor) {
		return repository.findByIpAndProvedor(ip, provedor);
	}

}

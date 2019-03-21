package com.dtb.saesc.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.repositories.LinkRepository;
import com.dtb.saesc.api.services.LinkService;

@Service
public class LinkServiceImpl implements LinkService {

	@Autowired
	private LinkRepository repository;

	@Override
	public Optional<List<Link>> buscarPorescola(Long id) {
		return Optional.of(repository.findByInstituicaoId(id)).filter(l -> !l.isEmpty());
	}

	@Override
	public Optional<List<Link>> buscarLinks(String ip) {
		return Optional.of(repository.findByIp(ip)).filter(l -> !l.isEmpty());
	}

}

package com.dtb.saesc.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.repositories.LinkRepository;
import com.dtb.saesc.api.services.LinkService;

@Service
public class LinkServiceImpl implements LinkService{

	@Autowired
	private LinkRepository repository;
	
	@Override
	public List<Link> buscarPorescola(Long id) {
		return repository.findByEscolaId(id);
	}

	@Override
	public List<Link> buscarLinks(String ip) {
		return repository.findAllByIp(ip);
	}
	
}

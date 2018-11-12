package com.dtb.saesc.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.model.repositories.EscolaLinkRepository;
import com.dtb.saesc.api.services.LinkService;

@Service
public class LinkServiceImpl implements LinkService{

	@Autowired
	private EscolaLinkRepository escolaLinkRepository;
	
	@Override
	public List<Link> buscarPorescola(Escola escola) {
		return escolaLinkRepository.findByEscola(escola);
	}
	
}
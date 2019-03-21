package com.dtb.saesc.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.repositories.LinkRepository;
import com.dtb.saesc.api.services.LinkService;

@Service
public class LinkServiceImpl implements LinkService {

	@Autowired
	private LinkRepository repository;

}

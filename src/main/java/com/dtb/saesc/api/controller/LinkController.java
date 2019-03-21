package com.dtb.saesc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtb.saesc.api.model.converters.Converter;
import com.dtb.saesc.api.model.dtos.LinkDto;
import com.dtb.saesc.api.model.entities.Link;
import com.dtb.saesc.api.services.LinkService;

@RestController
@RequestMapping("/links")
public class LinkController {
	
	@Autowired
	private LinkService service;
	
	@Autowired
	private Converter<Link, LinkDto> converter;

	
}

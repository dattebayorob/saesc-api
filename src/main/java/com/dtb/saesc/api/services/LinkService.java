package com.dtb.saesc.api.services;

import java.util.List;
import java.util.Optional;

import com.dtb.saesc.api.model.entities.Link;

public interface LinkService {
	
	public Optional<List<Link>> buscarPorescola(Long id);

	public Optional<List<Link>> buscarLinks(String s);
}

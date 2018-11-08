package com.dtb.saesc.api.services;

import java.util.List;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.entities.Link;

public interface LinkService {
	public List<Link> buscarPorescola(Escola escola);
}

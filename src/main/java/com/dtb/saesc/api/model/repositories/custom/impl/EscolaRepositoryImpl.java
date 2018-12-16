package com.dtb.saesc.api.model.repositories.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.enums.CredeEnum;
import com.dtb.saesc.api.model.enums.PrefixoEnum;
import com.dtb.saesc.api.model.repositories.custom.EscolaRepositoryQuery;
import com.dtb.saesc.api.model.repositories.custom.filter.EscolaFilter;

public class EscolaRepositoryImpl implements EscolaRepositoryQuery {
	@Autowired
	private EntityManager em;

	@Override
	public Page<Escola> findPageByNomeOrCredeOrPrefixo(EscolaFilter filter, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Escola> criteriaQuery = criteriaBuilder.createQuery(Escola.class);
		Root<Escola> root = criteriaQuery.from(Escola.class);
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(filter.getNome())) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")),
					"%" + filter.getNome().toLowerCase() + "%"));
		}

		try {
			if (!StringUtils.isEmpty(filter.getCrede())) {
				predicates.add(
						criteriaBuilder.equal(root.get("crede"), CredeEnum.valueOf(filter.getCrede().toUpperCase())));
			}
			if (!StringUtils.isEmpty(filter.getPrefixo())) {
				predicates.add(criteriaBuilder.equal(root.get("prefixo"),
						PrefixoEnum.valueOf(filter.getPrefixo().toUpperCase())));
			}
		} catch (IllegalArgumentException e) {
			//throw new AlgumaExcessãoMarota;
		}
		Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
		criteriaQuery.where(p);
		TypedQuery<Escola> tq = em.createQuery(criteriaQuery);
		List<Escola> escolas = tq.getResultList();
		return new PageImpl<>(escolas, pageable, escolas.size());
	}

}

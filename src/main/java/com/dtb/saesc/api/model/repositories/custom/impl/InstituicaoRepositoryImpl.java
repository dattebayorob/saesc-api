package com.dtb.saesc.api.model.repositories.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.dtb.saesc.api.model.entities.Instituicao;
import com.dtb.saesc.api.model.enums.CredeEnum;
import com.dtb.saesc.api.model.repositories.custom.InstituicaoRepositoryQuery;
import com.dtb.saesc.api.model.repositories.custom.filter.InstituicaoFilter;

public class InstituicaoRepositoryImpl implements InstituicaoRepositoryQuery {
	@Autowired
	private EntityManager em;

	@Override
	public Page<Instituicao> findPageByNomeOrCredeOrPrefixo(InstituicaoFilter filter, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Instituicao> criteriaQuery = criteriaBuilder.createQuery(Instituicao.class);
		Root<Instituicao> root = criteriaQuery.from(Instituicao.class);
		Predicate[] p = generatePredicates(filter, criteriaBuilder, root);
		criteriaQuery.where(p);
		List<Instituicao> escolas = em.createQuery(criteriaQuery).setFirstResult((int) pageable.getOffset())
				.setMaxResults(pageable.getPageSize()).getResultList();
		return new PageImpl<>(escolas, pageable, escolas.size());
	}

	private Predicate[] generatePredicates(InstituicaoFilter filter, CriteriaBuilder criteriaBuilder, Root<Instituicao> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(filter.getNome())) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")),
					"%" + filter.getNome().toLowerCase() + "%"));
		}
		if (filter.getCrede() != null) {
			predicates
					.add(criteriaBuilder.equal(root.get("crede"), CredeEnum.valueOf(filter.getCrede().toUpperCase())));
		}
		Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
		return p;
	}

}

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
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.util.StringUtils;

import com.dtb.saesc.api.model.entities.Instituicao;
import com.dtb.saesc.api.model.enums.CredeEnum;
import com.dtb.saesc.api.model.repositories.custom.InstituicaoRepositoryQuery;
import com.dtb.saesc.api.model.repositories.custom.filter.InstituicaoFilter;

public class InstituicaoRepositoryImpl implements InstituicaoRepositoryQuery {
	@Autowired
	private EntityManager em;

	@Override
	public Page<Instituicao> findPageByNomeOrCredeOrIp(InstituicaoFilter filter, Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Instituicao> cq = cb.createQuery(Instituicao.class);
		
		Root<Instituicao> root = cq.from(Instituicao.class);
		
		cq.where(generatePredicates(filter, cb, root));
		
		cq.orderBy(QueryUtils.toOrders(pageable.getSort(), root, cb));
		
		List<Instituicao> instituicoes = em.createQuery(cq).setFirstResult((int) pageable.getOffset())
				.setMaxResults(pageable.getPageSize()).getResultList();
		return new PageImpl<>(instituicoes, pageable, instituicoes.size());
	}

	private Predicate[] generatePredicates(InstituicaoFilter filter, CriteriaBuilder cb, Root<Instituicao> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(filter.getNome())) {
			predicates.add(cb.like(cb.lower(root.get("nome")),
					"%" + filter.getNome().toLowerCase() + "%"));
		}
		
		if (filter.getCrede() != null) {
			predicates
					.add(cb.equal(root.get("crede"), CredeEnum.valueOf(filter.getCrede().toUpperCase())));
		}
		
		if(!StringUtils.isEmpty(filter.getIp())) {
			predicates
					.add(cb.like(root.join("links").get("ip"), "%" + filter.getIp() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}

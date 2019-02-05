package com.dtb.saesc.api.model.repositories.custom.impl;

import java.sql.Date;
import java.time.ZoneId;
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

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.repositories.custom.EquipamentoRepositoryQuery;
import com.dtb.saesc.api.model.repositories.custom.filter.EquipamentoFilter;

public class EquipamentoRepositoryImpl implements EquipamentoRepositoryQuery {
	@Autowired
	private EntityManager em;

	@Override
	public Page<Equipamento> findPageByDescricaoOrModeloOrStatusOrSerialOrTombamento(EquipamentoFilter filter,
			Pageable pageable) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Equipamento> criteriaQuery = criteriaBuilder.createQuery(Equipamento.class);
		Root<Equipamento> root = criteriaQuery.from(Equipamento.class);
		Predicate[] p = generatePredicates(filter, criteriaBuilder, root);
		criteriaQuery.where(p);
		List<Equipamento> equipamentos = em.createQuery(criteriaQuery).setFirstResult((int) pageable.getOffset())
				.setMaxResults(pageable.getPageSize()).getResultList();
		return new PageImpl<>(equipamentos, pageable, equipamentos.size());
	}

	private Predicate[] generatePredicates(EquipamentoFilter filter, CriteriaBuilder criteriaBuilder,
			Root<Equipamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(filter.getDescricao())) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("descricao")),
					"%" + filter.getDescricao().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(filter.getSerial())) {
			predicates.add(criteriaBuilder.equal(root.get("serial"), filter.getSerial()));
		}
		if (!StringUtils.isEmpty(filter.getTombamento())) {
			predicates.add(criteriaBuilder.equal(root.get("tombamento"), filter.getTombamento()));
		}
		if (filter.getModeloId() != null) {
			predicates.add(criteriaBuilder.equal(root.get("modelo").get("id"), filter.getModeloId()));
		}
		if (!StringUtils.isEmpty(filter.getCriadoDe())) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataCriacao"),
					Date.from(filter.getCriadoDe().atStartOfDay(ZoneId.systemDefault()).toInstant())));
		}
		if (!StringUtils.isEmpty(filter.getCriadoAte())) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataCriacao"),
					Date.from(filter.getCriadoAte().atStartOfDay(ZoneId.systemDefault()).toInstant())));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}

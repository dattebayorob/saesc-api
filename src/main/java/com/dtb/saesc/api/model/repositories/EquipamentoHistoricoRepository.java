package com.dtb.saesc.api.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.dtb.saesc.api.model.entities.EquipamentoHistorico;
import com.dtb.saesc.api.model.entities.Equipamento;
import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	@NamedQuery(name = "EquipamentoHistoricoRepository.findByEquipamentoId",
			query = "SELECT historico FROM equipamento_historico historico WHERE historico.equipamento.id = :id")
})
public interface EquipamentoHistoricoRepository extends JpaRepository<EquipamentoHistorico, Long>{
	List<EquipamentoHistorico> findByEquipamento(Equipamento equipamento);
	List<EquipamentoHistorico> findByEquipamentoId(@Param("id") Long id);
}

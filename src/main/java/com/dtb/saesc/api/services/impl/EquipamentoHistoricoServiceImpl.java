package com.dtb.saesc.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtb.saesc.api.model.entities.Equipamento;
import com.dtb.saesc.api.model.entities.EquipamentoHistorico;
import com.dtb.saesc.api.model.entities.Funcionario;
import com.dtb.saesc.api.model.repositories.EquipamentoHistoricoRepository;
import com.dtb.saesc.api.services.EquipamentoHistoricoService;

@Service
public class EquipamentoHistoricoServiceImpl implements EquipamentoHistoricoService{
	@Autowired
	private EquipamentoHistoricoRepository repository;

	@Override
	public void adicionar(Equipamento equipamento, Funcionario funcionario, String comentario) {
		comentario = "Status: "+equipamento.getStatus().getNome()+
				(comentario==null?"":" - "+comentario);
		repository.save(EquipamentoHistorico
						.builder()
							.equipamento(equipamento)
							.funcionario(funcionario)
							.comentario(comentario)
						.build());
	}
	
}

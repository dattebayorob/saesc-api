package com.dtb.saesc.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.dtb.saesc.api.model.entities.Escola;
import com.dtb.saesc.api.model.repositories.custom.filter.EscolaFilter;

public interface EscolaService {

	/**
	 * 
	 * Buscar uma entidade Escola pelo Id
	 * 
	 * @param id
	 * @return Optional<Escola>
	 * 
	 */

	public Optional<Escola> buscarPeloId(Long id);

	/**
	 * 
	 * Buscar uma entidade Escola pelo Inep
	 * 
	 * @param inep
	 * @return Optional<Escola>
	 * 
	 */

	public Optional<Escola> buscarPeloInep(String inep);

	/**
	 * 
	 * Verificar a existencia de uma entidade Escola pelo Inep
	 * 
	 * @param inep
	 * @return boolean
	 * 
	 */

	public boolean existePeloInep(String inep);

	/**
	 * 
	 * Adicionar uma entidade Escola no banco de dados
	 * 
	 * @param escola
	 * @return Escola
	 * @throws MethodArgumentNotValidException 
	 * 
	 * @throws ValidationErrorsException
	 * 
	 * 
	 */

	public Optional<Escola> adicionar(Escola escola);

	/**
	 * 
	 * Atualizar uma entidade Escola no banco de dados
	 * 
	 * @param escola
	 * @param inep
	 * 
	 * @return Escola
	 * @throws MethodArgumentNotValidException 
	 * 
	 * @throws ValidationErrorsException
	 * 
	 **/

	public Optional<Escola> atualizar(Escola escola, String inep);
	
	/**
	 * 
	 * Pesquisar as Escolas de acordo com o filtro
	 * 
	 * @param filtros
	 * @param pageable
	 * 
	 * @return Page<Escola>
	 * 
	 * TODO: Melhorar a paginação, o sort na verdade não funciona
	 * 
	 * */
	
	public Page<Escola> pesquisarEscolas(EscolaFilter filtros, Pageable page);
}

package com.dtb.saesc.api.services;

import java.util.Optional;

import com.dtb.saesc.api.model.entities.Provedor;
import com.dtb.saesc.api.model.exceptions.ValidationErrorsException;

import io.vavr.control.Either;

public interface ProvedorService {

	/**
	 * Adiciona uma entidade provedor. Caso o cnpj informado já esteja cadastrado no
	 * banco de dados uma exceção será jogada
	 * 
	 * @param provedor
	 * @return Provedor
	 * 
	 * @throws ValidationErrorsException
	 * 
	 */

	public Either<RuntimeException, Provedor> adicionar(Provedor provedor);

	/**
	 * Atualiza uma entidade provedor no banco de dados. Caso o cnpj da entidade
	 * seja diferente do ultimo cnpj cadastrado (String cnpj), será checado e caso
	 * já exista cadastrado no banco de dados uma exceção será jogada
	 * 
	 * @param provedor
	 * @param cnpj
	 * 
	 * @return Provedor
	 * 
	 * @Throws ValidationErrorsException
	 * 
	 */

	public Either<RuntimeException,Provedor> atualizar(Provedor provedor, String cnpj);

	/**
	 * Busca uma entidade Provedor pelo Id
	 * 
	 * @param id
	 * 
	 * @return Optional<Provedor>
	 * 
	 */

	public Optional<Provedor> buscarPeloId(Long id);

	/**
	 * Verifica a existencia de uma entidade Provedor pelo cnpj
	 * 
	 * @param cnpj
	 * 
	 * @return boolean
	 * 
	 */

	public boolean existePeloCnpj(String cnpj);

	/**
	 * Verifica a existencia de uma entidade Provedor pelo Id
	 * 
	 * @param id
	 * 
	 * @return boolean
	 * 
	 */

	public boolean existePeloId(Long id);

	/**
	 * Remove uma entidade Provedor do banco de dados pelo Id
	 * 
	 * @param id
	 * 
	 */

	public void removerPeloId(Long id);
}

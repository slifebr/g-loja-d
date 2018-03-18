package com.slifesys.gloja.repository.api;

import java.util.List;

public interface Dao <T> {
	void incluir(T t) throws Exception;
	void alterar(T t) throws Exception;
	void excluir(T t) throws Exception;
	List<T> listar() throws Exception;
	T buscar(Long codigo) throws Exception;
}


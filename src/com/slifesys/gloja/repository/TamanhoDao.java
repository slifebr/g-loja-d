package com.slifesys.gloja.repository;

import java.util.List;

import com.slifesys.gloja.model.Tamanho;
import com.slifesys.gloja.repository.api.Dao;

public interface TamanhoDao extends Dao<Tamanho>{

		
		List<Tamanho> findAllByDescr (String descr) throws Exception;
		List<Tamanho> findAllBySigla (String sigla) throws Exception;

	}

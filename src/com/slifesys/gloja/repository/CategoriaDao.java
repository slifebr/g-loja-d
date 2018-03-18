package com.slifesys.gloja.repository;

import java.util.List;

import com.slifesys.gloja.model.Categoria;
import com.slifesys.gloja.repository.api.Dao;

public interface CategoriaDao  extends Dao<Categoria> {
	
	List<Categoria> findAllByDescr (String descr) throws Exception;

}

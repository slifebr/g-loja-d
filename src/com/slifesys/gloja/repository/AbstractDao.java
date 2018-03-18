package com.slifesys.gloja.repository;

import java.lang.reflect.ParameterizedType;

import com.slifesys.gloja.repository.api.Dao;

public abstract class AbstractDao <T> implements Dao<T> {
	
	private Class<?> classe;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		try{
			this.classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected Class<?> getClasse() {
		return classe;
	}
	
	protected String getNomeSimples(){
		return classe.getSimpleName();
	}
}

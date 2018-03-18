package com.slifesys.gloja.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.slifesys.gloja.repository.AbstractDao;

public class AbstractJPADao<T> extends AbstractDao<T> {

	private EntityManager manager;
	
	public AbstractJPADao() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("GLOJA_PU");
		manager = factory.createEntityManager();
	}
	
	
	public EntityManager getManager() {
		return manager;
	}
	
	private void begin() {
		manager.getTransaction().begin();
	}
	
	private void commit() {
		manager.getTransaction().commit();
	}
	
	
	@Override
	public void incluir(T t) throws Exception {
		begin();
		manager.persist(t);
		commit();
	}

	@Override
	public void alterar(T t) throws Exception {
		begin();
		manager.merge(t);
		commit();
	}

	@Override
	public void excluir(T t) throws Exception {
		begin();
		manager.remove(t);
		commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listar() throws Exception {
		limparCache();
		Query query = manager.createQuery("SELECT e from " + getNomeSimples() + " e ");
		
		@SuppressWarnings("rawtypes")
		List lista = query.getResultList();
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T buscar(Long id) throws Exception {
       return (T) manager.find(getClasse(), id);
	}

	protected void limparCache() {
		manager.getEntityManagerFactory().getCache().evictAll();
		manager.clear();
	}
}

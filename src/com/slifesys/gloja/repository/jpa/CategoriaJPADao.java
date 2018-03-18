package com.slifesys.gloja.repository.jpa;

import java.util.List;

import javax.persistence.Query;

import com.slifesys.gloja.model.Categoria;
import com.slifesys.gloja.repository.CategoriaDao;

public class CategoriaJPADao extends AbstractJPADao<Categoria> implements CategoriaDao {

	@Override
	public List<Categoria> listar(String descr) throws Exception {
		limparCache();
		Query query = getManager().createQuery("SELECT e FROM Categoria e WHERE UPPER(e.descr) like :descr");
		query.setParameter("descr", "%" + descr.toUpperCase() + "%");
		List lista = query.getResultList();
		return lista;
	}

}

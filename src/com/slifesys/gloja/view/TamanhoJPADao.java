package com.slifesys.gloja.view;

import java.util.List;

import javax.persistence.Query;

import com.slifesys.gloja.model.Tamanho;
import com.slifesys.gloja.repository.TamanhoDao;
import com.slifesys.gloja.repository.jpa.AbstractJPADao;

public class TamanhoJPADao extends AbstractJPADao<Tamanho> implements TamanhoDao {

	@Override
	public List<Tamanho> findAllByDescr(String descr) throws Exception {
		limparCache();
		Query query = getManager().createQuery("SELECT e FROM Tamanho e WHERE UPPER(e.descr) like :descr");
		query.setParameter("descr", "%" + descr.toUpperCase() + "%");
		List lista = query.getResultList();
		return lista;
	}

	@Override
	public List<Tamanho> findAllBySigla(String sigla) throws Exception {
		limparCache();
		Query query = getManager().createQuery("SELECT e FROM Tamanho e WHERE UPPER(e.sigla) like :sigla");
		query.setParameter("sigla", "%" + sigla.toUpperCase() + "%");
		List lista = query.getResultList();
		return lista;
	}

}

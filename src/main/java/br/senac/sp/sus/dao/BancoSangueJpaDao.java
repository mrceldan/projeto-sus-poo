package br.senac.sp.sus.dao;

import java.sql.SQLException;
import java.util.List;

import br.senac.sp.sus.model.BancoSangue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class BancoSangueJpaDao implements InterfaceDao<BancoSangue> {
	private EntityManager manager;
	
	public BancoSangueJpaDao(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void inserir(BancoSangue objeto) throws SQLException {
		this.manager.persist(objeto);
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();
	}

	@Override
	public void alterar(BancoSangue objeto) throws SQLException {
		this.manager.merge(objeto);
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();		
	}

	@Override
	public void excluir(int id) throws SQLException {
		BancoSangue bs = this.manager.find(BancoSangue.class, id);
		this.manager.remove(bs);
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();		
	}

	@Override
	public List<BancoSangue> listar() throws SQLException {		
		TypedQuery<BancoSangue> query = 
				manager.createQuery("select b from BancoSangue b order by b.nome", BancoSangue.class);
		return query.getResultList();
	}
	
}

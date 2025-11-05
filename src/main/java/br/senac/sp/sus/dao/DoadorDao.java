package br.senac.sp.sus.dao;

import java.sql.SQLException;
import java.util.List;

import br.senac.sp.sus.model.Doador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DoadorDao implements InterfaceDao<Doador> {
	private EntityManager manager;

	public DoadorDao(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void inserir(Doador objeto) throws SQLException {
		this.manager.persist(objeto);
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();
	}

	@Override
	public void alterar(Doador objeto) throws SQLException {
		this.manager.merge(objeto);
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();
	}

	@Override
	public void excluir(int id) throws SQLException {
		Doador d = this.manager.find(Doador.class, id);
		this.manager.remove(d);
	}

	@Override
	public List<Doador> listar() throws SQLException {
		TypedQuery<Doador> query = 
				this.manager.createQuery("select d from Doador d order by d.nome",Doador.class);
		return query.getResultList();
	}

}

package br.com.testaprojeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;

import br.com.caelum.vraptor.ioc.Component;
import br.com.testaprojeto.model.Projeto;

@Component
public class ProjetoDAO {

	private Session session = null;
	
	public ProjetoDAO(Session session) {
		super();
		this.session = session;
	}
	
	/**
	 * Lista os projetos cadastrados no banco de dados. 
	 * @return Lista com todos os projetos cadastrados.
	 */
	public List<Projeto> listar() {
		
		this.session.clear();
		
		Criteria criteria = this.session.createCriteria(Projeto.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc("titulo"));
		
		return criteria.list();

		
	}
	
	public Projeto consultar(String titulo){
		
		Criteria criteria = this.session.createCriteria(Projeto.class);
		criteria.add(Property.forName("titulo").eq(titulo));
		
		Projeto projeto = (Projeto)criteria.uniqueResult();
		this.session.flush();
		
		return projeto;
		
	}
	
	// Salva o projeto no banco de dados.
	public void salvar(Projeto projeto){
		
		this.session.save(projeto);
		this.session.flush();
	}
	
	public Projeto consultar(Integer id){
		
		Criteria criteria = this.session.createCriteria(Projeto.class);
		criteria.add(Property.forName("id").eq(id));
		
		return (Projeto)criteria.uniqueResult();
		
	}
	
	public Projeto consultar(String titulo,Integer id){
		
		Criteria criteria = this.session.createCriteria(Projeto.class);
		criteria.add(Property.forName("titulo").eq(titulo));
		criteria.add(Property.forName("id").ne(id));
		
		return (Projeto)criteria.uniqueResult();
		
	}
	
	// Salva o projeto no banco de dados.
	public void atualizar(Projeto projeto){
		
		this.session.update(projeto);
		this.session.flush();
				
	}
	
	// Remove um projeto do banco de dados.
	public void remover(int id){
				
		this.session.delete(this.consultar(id));
		this.session.flush();
	}
}

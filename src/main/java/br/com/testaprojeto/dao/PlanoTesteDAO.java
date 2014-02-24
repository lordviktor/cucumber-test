package br.com.testaprojeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Property;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.PrototypeScoped;
import br.com.testaprojeto.model.PlanoTeste;
import br.com.testaprojeto.model.Projeto;

@Component
public class PlanoTesteDAO {

	private Session session = null;
	private ProjetoDAO projetoDAO = null;
	
	public PlanoTesteDAO(Session session,ProjetoDAO projetoDAO) {
		super();
		this.session = session;
		this.projetoDAO = projetoDAO;
	}
	
	/**
	 * Lista os projetos cadastrados no banco de dados. 
	 * @return Lista com todos os projetos cadastrados.
	 */
	public List<PlanoTeste> listar(Integer id){
		
		this.session.clear();
		
		Criteria criteria = this.session.createCriteria(PlanoTeste.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Property.forName("projeto").eq(this.projetoDAO.consultar(id)));
		return criteria.list();
		
	}
	
	public PlanoTeste consultar(Integer planoTesteId){
		
		Criteria criteria = this.session.createCriteria(PlanoTeste.class);
		criteria.add(Property.forName("id").eq(planoTesteId));
		
		PlanoTeste plano = (PlanoTeste)criteria.uniqueResult();
		this.session.flush();
		return plano;
		
	}
	
	public PlanoTeste consultar(String titulo,Projeto projeto){
		Criteria criteria = this.session.createCriteria(PlanoTeste.class);
		criteria.add(Property.forName("titulo").eq(titulo));
		criteria.add(Property.forName("projeto").eq(projeto));
		return (PlanoTeste)criteria.uniqueResult();
	}
	
	public void salvar(PlanoTeste planoTeste){
		this.session.save(planoTeste);
		this.session.flush();
	}
	
	// Atualiza o plano de teste no banco de dados.
	public void atualizar(PlanoTeste planoTeste){	
		this.session.update(planoTeste);
		this.session.flush();
	}
	
	//Remove o plano de teste do banco
	public void remover(Integer id){
		this.session.delete(this.consultar(id));
		this.session.flush();
	}
	
	
	// Retorna se existe um outro plano de teste cadastrado com esse nome para o mesmo projeto.
	public PlanoTeste consultaPlanoTesteMesmoNome(String titulo, Integer id, Integer projeto_id) {

		Criteria criteria = this.session.createCriteria(PlanoTeste.class);
		criteria.add(Property.forName("titulo").eq(titulo));
		criteria.add(Property.forName("id").ne(id));
		criteria.add(Property.forName("projeto.id").eq(projeto_id));
		return (PlanoTeste)criteria.uniqueResult();

	}
	
}

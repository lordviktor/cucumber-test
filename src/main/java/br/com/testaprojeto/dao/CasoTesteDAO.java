package br.com.testaprojeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;

import br.com.caelum.vraptor.ioc.Component;
import br.com.testaprojeto.model.CasoTeste;
import br.com.testaprojeto.model.PlanoTeste;

@Component
public class CasoTesteDAO {

	private Session session = null;
	private PlanoTesteDAO planoTesteDAO = null;
	
	public CasoTesteDAO(Session session, PlanoTesteDAO planoTesteDAO){
		super();
		this.session = session;
		this.planoTesteDAO = planoTesteDAO;
	}
	
	public List<CasoTeste> listar(Integer planoTesteId) {
		
		// Obtém o plano de teste a ter os casos listados.
		this.session.clear();
		
		Criteria criteria = this.session.createCriteria(CasoTeste.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		criteria.add(Property.forName("planoTeste.id").eq(planoTesteId));
		criteria.add(Property.forName("deleted").eq(false));
		criteria.addOrder(Order.asc("titulo"));
		return criteria.list();
		
	}
	
	public void salvar(Integer plano_teste_id, CasoTeste casoTeste) {
		
		PlanoTeste planoTeste = this.planoTesteDAO.consultar(plano_teste_id);
		casoTeste.setPlanoTeste(planoTeste);
		
		casoTeste.setDeleted(false);
		this.session.save(casoTeste);
		this.session.flush();
	}

	public CasoTeste consultar(Integer plano_teste_id, String titulo) {
		Criteria criteria = this.session.createCriteria(CasoTeste.class);
		criteria.add(Property.forName("titulo").eq(titulo));
		criteria.add(Property.forName("planoTeste.id").eq(plano_teste_id));
		return (CasoTeste)criteria.uniqueResult();
	}

	public CasoTeste consultar(Integer id) {
		Criteria criteria = this.session.createCriteria(CasoTeste.class);
		criteria.add(Property.forName("id").eq(id));
		return (CasoTeste)criteria.uniqueResult();	
	}

	public CasoTeste consultar(Integer plano_teste_id, Integer id, String titulo) {
		Criteria criteria = this.session.createCriteria(CasoTeste.class);
		criteria.add(Property.forName("titulo").eq(titulo));
		criteria.add(Property.forName("planoTeste.id").eq(plano_teste_id));
		criteria.add(Property.forName("id").ne(id));
		return (CasoTeste)criteria.uniqueResult();
	}

	public void atualizar(CasoTeste casoTeste) {
		
		this.session.clear();
		
		casoTeste.setDeleted(false);
		this.session.update(casoTeste);
		this.session.flush();
		
	}
	
	public int remover(Integer id) {
		
		CasoTeste caso = this.consultar(id);
		caso.setDeleted(true);
		
		Integer planoTesteId = caso.getPlanoTeste().getId();
		
		this.session.update(caso);
		this.session.flush();
		
		return planoTesteId;
	}
	
}

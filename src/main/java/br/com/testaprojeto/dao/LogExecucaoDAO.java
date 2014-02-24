package br.com.testaprojeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;

import br.com.caelum.vraptor.ioc.Component;
import br.com.testaprojeto.model.CasoTeste;
import br.com.testaprojeto.model.LogExecucao;

@Component
public class LogExecucaoDAO {
	private Session session = null;
	private CasoTesteDAO casoTesteDAO = null;
	
	public LogExecucaoDAO(Session session, CasoTesteDAO casoTesteDAO){
		super();
		this.session=session;
		this.casoTesteDAO=casoTesteDAO;
		
	}
	
	public void cadastrar(LogExecucao log) {
		
		this.session.save(log);
		this.session.flush();
		
	}
	
	public List<LogExecucao> listar(Integer casoTesteId) {
		
		// Obtém o plano de teste a ter os casos listados.
		this.session.clear();
		
		Criteria criteria = this.session.createCriteria(LogExecucao.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		criteria.add(Property.forName("casoTeste.id").eq(casoTesteId));
		criteria.addOrder(Order.asc("dataExecucao"));
		return criteria.list();
		
	}
	
	/*public LogExecucao consultar(Integer id) {
		Criteria criteria = this.session.createCriteria(LogExecucao.class);
		criteria.add(Property.forName("id").eq(id));
		return (LogExecucao)criteria.uniqueResult();	
	}*/

}

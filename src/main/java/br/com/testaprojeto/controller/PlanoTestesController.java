package br.com.testaprojeto.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Property;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.testaprojeto.dao.PlanoTesteDAO;
import br.com.testaprojeto.dao.ProjetoDAO;
import br.com.testaprojeto.model.CasoTeste;
import br.com.testaprojeto.model.PlanoTeste;
import br.com.testaprojeto.model.Projeto;

@Resource
public class PlanoTestesController {
	
	Result result = null;
	Session session = null;
	Validator validator = null;
	ProjetoDAO projetoDAO = null;
	PlanoTesteDAO planoTesteDAO = null;
	
	
	public PlanoTestesController(Result result,Session session,ProjetoDAO projetoDAO,Validator validator,PlanoTesteDAO planoTesteDAO){
		super();
		this.result = result;
		this.session = session;
		this.projetoDAO = projetoDAO;
		this.validator = validator;
		this.planoTesteDAO = planoTesteDAO;
	}
	
	public List<PlanoTeste> index(Integer id){

		this.result.include("projeto_id",id);
		return this.planoTesteDAO.listar(id);
		
	}
	
	public PlanoTeste novo(Integer id){
		this.result.include("projeto_id",id);
		return new PlanoTeste();
	}
	
	public void cadastrar(final PlanoTeste planoTeste,Integer projeto_id){
		
		Projeto projeto = this.projetoDAO.consultar(projeto_id);
		planoTeste.setProjeto(projeto);
		
		//Verifica se h� um plano de teste com o mesmo nome				
		final PlanoTeste planoMesmoNome = this.planoTesteDAO.consultar(planoTeste.getTitulo(), projeto);
		
		// Valida se os atributos obrig�rios foram preenchidos.
		this.validator.checking(new Validations() {{
					
			that(!planoTeste.getTitulo().isEmpty(),"O t�tulo do Plano de Teste � obrigat�rio!","titulo");
			that(!planoTeste.getDescricao().isEmpty(),"A descri��o do Plano de Teste � obrigat�ria!","descricao");
			that(((planoTeste.getVersao() != null) && (planoTeste.getVersao() != 0)),"A vers�o do Plano de Teste � obrigat�ria e diferente de 0!","versao");
			that(planoMesmoNome == null,"J� existe um plano de teste cadastrado com esse t�tulo para esse projeto!","titulo");
			
		}});
		
		// Retorna para a tela de cadastro caso haja algum erro de preenchimento.
		this.result.include(planoTeste);
		this.validator.onErrorUsePageOf(this).novo(projeto_id);
		
		//Salva o Plano de Teste
		this.planoTesteDAO.salvar(planoTeste);
		
		this.result.include("notice", "Plano de teste cadastrado com sucesso.");
		this.result.redirectTo(this).index(projeto_id);
		
	}
	
	public PlanoTeste editar(Integer id){
		
		/*Criteria criteria = this.session.createCriteria(PlanoTeste.class);
		criteria.add(Property.forName("id").eq(id));
		PlanoTeste planoTeste = (PlanoTeste)criteria.uniqueResult();
		*/
		PlanoTeste planoTeste = this.planoTesteDAO.consultar(id);
		this.result.include("projeto_id", planoTeste.getProjeto().getId());
		return planoTeste;
		
	}
	
	public void atualizar(final PlanoTeste planoTeste, Integer projeto_id){
			
		//Verifica se h� um plano de teste com o mesmo nome	
		final PlanoTeste planoMesmoNome = this.planoTesteDAO.consultaPlanoTesteMesmoNome(planoTeste.getTitulo(), 
																						planoTeste.getId(), 
																						projeto_id);
				
		// Valida se os atributos obrig�rios foram preenchidos.
		this.validator.checking(new Validations() {{					
			that(!planoTeste.getTitulo().isEmpty(),"O t�tulo do Plano de Teste � obrigat�rio!","titulo");
			that(!planoTeste.getDescricao().isEmpty(),"A descri��o do Plano de Teste � obrigat�ria!","descricao");
			that(((planoTeste.getVersao() != null) && (planoTeste.getVersao() != 0)),"A vers�o do Plano de Teste � obrigat�ria e diferente de 0!","versao");
			that(planoMesmoNome == null,"J� existe um plano de teste cadastrado com esse t�tulo!","titulo");
		}});
						
		// Retorna para a tela de cadastro caso haja algum erro de preenchimento.
		this.result.include(planoTeste);
		this.validator.onErrorUsePageOf(this).novo(projeto_id);
				
		//Salva o plano de teste no banco
		this.planoTesteDAO.atualizar(planoTeste);
		
		// Retorna para a p�gina de listagem de planos de teste.
		this.result.include("notice", "Plano de teste atualizado com sucesso.");
		this.result.redirectTo(this).index(projeto_id);
		
	}
	
	public void remover(Integer id){

		PlanoTeste planoTeste = planoTesteDAO.consultar(id);
		planoTesteDAO.remover(id);
		
		this.result.include("notice", "Plano de teste removido com sucesso.");
		this.result.redirectTo(this).index(planoTeste.getProjeto().getId());
		
	}
	
	//Versiona o plano de teste
	public void versionar(Integer id) {
		
		PlanoTeste plano = planoTesteDAO.consultar(id);
		Integer versao = plano.getVersao();
		versao = versao+1;
		plano.setVersao(versao);
		this.result.include("notice","Vers�o do Plano de teste incrementada.");
		this.result.redirectTo(this).index(plano.getProjeto().getId());
	}
	
}

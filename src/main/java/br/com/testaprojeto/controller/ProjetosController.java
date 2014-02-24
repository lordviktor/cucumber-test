package br.com.testaprojeto.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Property;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.testaprojeto.dao.ProjetoDAO;
import br.com.testaprojeto.model.Projeto;

@Resource
public class ProjetosController {
	
	Result result = null;
	Session session = null;
	Validator validator = null;
	ProjetoDAO projetoDAO = null;
	
	public ProjetosController(Result result, ProjetoDAO projetoDAO, Session session, Validator validator){
		
		this.result = result;
		this.session = session;
		this.validator = validator;
		this.projetoDAO = projetoDAO;
		
	}
	
	/**
	 * Lista os projetos cadastrados no sitema.
	 * @return Lista contendo todos os projetos cadastrados no sistema.
	 */
	public List<Projeto> index() {
		
		return this.projetoDAO.listar();
		
	}
	
	/**
	 * Exibe o formul�rio para o cadastro de um novo projeto.
	 * @return Inst�ncia de um novo projeto.
	 */
	public Projeto novo(){
		return new Projeto();
	}
	
	
	/**
	 * Salva um novo projeto no banco de dados da aplica��o.
	 * @param projeto Inst�ncia do projeto a ser salvo.
	 */
	public void cadastrar(final Projeto projeto){
	
		// Verifica se j� existe um projeto com o mesmo titulo.
		final Projeto projetoMesmoTitulo = this.projetoDAO.consultar(projeto.getTitulo());
		
		// Valida se os atributos obrig�rios foram preenchidos.
		this.validator.checking(new Validations() {{
			
			that(!projeto.getTitulo().isEmpty(),"O t�tulo do projeto � obrigat�rio!","titulo");
			that(!projeto.getDescricao().isEmpty(),"A descri��o do projeto � obrigat�ria!","descricao");
			that(!projeto.getUrl().isEmpty(),"A url do projeto � obrigat�ria!","url");
			that(projetoMesmoTitulo == null,"J� existe um projeto cadastrado com esse t�tulo!","titulo");
			
		}});
		
		// Retorna para a tela de cadastro caso haja algum erro de preenchimento.
		this.result.include(projeto);
		this.validator.onErrorUsePageOf(this).novo();
		
		// Salva o projeto no banco de dados.
		this.projetoDAO.salvar(projeto);
		
		// Retorna para a p�gina de listagem de projetos.
		this.result.include("notice", "Projeto cadastrado com sucesso.");
		this.result.redirectTo(this).index();
		
	}
	
	// Exibe um projeto para edi��o.
	public Projeto editar(int id) {
		
		return this.projetoDAO.consultar(id);
		
	}
	
	// Salva as altera��es realizadas no projeto que foi alterado.
	public void atualizar(final Projeto projeto){
		
        // Verifica se j� existe um projeto com o mesmo titulo.
		final Projeto projetoMesmoTitulo = this.projetoDAO.consultar(projeto.getTitulo(),projeto.getId());
		
		// Valida se os atributos obrig�rios foram preenchidos.
		this.validator.checking(new Validations() {{
			
			that(!projeto.getTitulo().isEmpty(),"O t�tulo do projeto � obrigat�rio!","titulo");
			that(!projeto.getDescricao().isEmpty(),"A descri��o do projeto � obrigat�ria!","descricao");
			that(!projeto.getUrl().isEmpty(),"A url do projeto � obrigat�ria!","url");
			that(projetoMesmoTitulo == null,"J� existe um projeto cadastrado com esse t�tulo!","titulo");
			
		}});
		
		// Retorna para a tela de cadastro caso haja algum erro de preenchimento.
		this.result.include(projeto);
		this.validator.onErrorUsePageOf(this).editar(projeto.getId());
		
		// Salva o projeto no banco de dados.
		this.projetoDAO.atualizar(projeto);
		
		// Retorna para a p�gina de listagem de projetos.
		this.result.include("notice", "Projeto alterado com sucesso.");
		this.result.redirectTo(this).index();
		
	}
	
	public void remover(int id){
		
		this.projetoDAO.remover(id);
		
		// Retorna para a p�gina de listagem de projetos.
		this.result.include("notice", "Projeto foi excluido com sucesso.");
		this.result.redirectTo(this).index();
				
	}
	
	
		
	
}

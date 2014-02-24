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
	 * Exibe o formulário para o cadastro de um novo projeto.
	 * @return Instância de um novo projeto.
	 */
	public Projeto novo(){
		return new Projeto();
	}
	
	
	/**
	 * Salva um novo projeto no banco de dados da aplicação.
	 * @param projeto Instância do projeto a ser salvo.
	 */
	public void cadastrar(final Projeto projeto){
	
		// Verifica se já existe um projeto com o mesmo titulo.
		final Projeto projetoMesmoTitulo = this.projetoDAO.consultar(projeto.getTitulo());
		
		// Valida se os atributos obrigórios foram preenchidos.
		this.validator.checking(new Validations() {{
			
			that(!projeto.getTitulo().isEmpty(),"O título do projeto é obrigatório!","titulo");
			that(!projeto.getDescricao().isEmpty(),"A descrição do projeto é obrigatória!","descricao");
			that(!projeto.getUrl().isEmpty(),"A url do projeto é obrigatória!","url");
			that(projetoMesmoTitulo == null,"Já existe um projeto cadastrado com esse título!","titulo");
			
		}});
		
		// Retorna para a tela de cadastro caso haja algum erro de preenchimento.
		this.result.include(projeto);
		this.validator.onErrorUsePageOf(this).novo();
		
		// Salva o projeto no banco de dados.
		this.projetoDAO.salvar(projeto);
		
		// Retorna para a página de listagem de projetos.
		this.result.include("notice", "Projeto cadastrado com sucesso.");
		this.result.redirectTo(this).index();
		
	}
	
	// Exibe um projeto para edição.
	public Projeto editar(int id) {
		
		return this.projetoDAO.consultar(id);
		
	}
	
	// Salva as alterações realizadas no projeto que foi alterado.
	public void atualizar(final Projeto projeto){
		
        // Verifica se já existe um projeto com o mesmo titulo.
		final Projeto projetoMesmoTitulo = this.projetoDAO.consultar(projeto.getTitulo(),projeto.getId());
		
		// Valida se os atributos obrigórios foram preenchidos.
		this.validator.checking(new Validations() {{
			
			that(!projeto.getTitulo().isEmpty(),"O título do projeto é obrigatório!","titulo");
			that(!projeto.getDescricao().isEmpty(),"A descrição do projeto é obrigatória!","descricao");
			that(!projeto.getUrl().isEmpty(),"A url do projeto é obrigatória!","url");
			that(projetoMesmoTitulo == null,"Já existe um projeto cadastrado com esse título!","titulo");
			
		}});
		
		// Retorna para a tela de cadastro caso haja algum erro de preenchimento.
		this.result.include(projeto);
		this.validator.onErrorUsePageOf(this).editar(projeto.getId());
		
		// Salva o projeto no banco de dados.
		this.projetoDAO.atualizar(projeto);
		
		// Retorna para a página de listagem de projetos.
		this.result.include("notice", "Projeto alterado com sucesso.");
		this.result.redirectTo(this).index();
		
	}
	
	public void remover(int id){
		
		this.projetoDAO.remover(id);
		
		// Retorna para a página de listagem de projetos.
		this.result.include("notice", "Projeto foi excluido com sucesso.");
		this.result.redirectTo(this).index();
				
	}
	
	
		
	
}

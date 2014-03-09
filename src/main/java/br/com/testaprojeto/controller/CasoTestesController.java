package br.com.testaprojeto.controller;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.testaprojeto.dao.CasoTesteDAO;
import br.com.testaprojeto.dao.LogExecucaoDAO;
import br.com.testaprojeto.dao.PlanoTesteDAO;
import br.com.testaprojeto.model.CasoTeste;
import br.com.testaprojeto.model.LogExecucao;
import br.com.testaprojeto.model.PlanoTeste;
import br.com.testaprojeto.utils.CucumberUtil;

@Resource
public class CasoTestesController {

	Result result = null;
	Session session = null;
	Validator validator = null;
	PlanoTesteDAO planoTesteDAO = null;
	CasoTesteDAO casoTesteDAO = null;
	LogExecucaoDAO logExecucaoDAO = null;
	
	public CasoTestesController(Result result,
								Session session,
								Validator validator,
								PlanoTesteDAO planoTesteDAO, 
								CasoTesteDAO casoTesteDAO,
								LogExecucaoDAO logExecucaoDAO){
		super();
		this.result = result;
		this.session = session;
		this.validator = validator;
		this.planoTesteDAO = planoTesteDAO;
		this.casoTesteDAO = casoTesteDAO;
		this.logExecucaoDAO = logExecucaoDAO;
		
	}
	
	public List<CasoTeste> index(Integer id){

		PlanoTeste planoTeste = this.planoTesteDAO.consultar(id);
	
		this.result.include("plano_teste_id", id);
		this.result.include("projeto_id",planoTeste.getProjeto().getId());
		
		List<CasoTeste> casos = this.casoTesteDAO.listar(id);
		return casos;
		
	}
	
	public CasoTeste novo(Integer id){
		
		this.result.include("plano_teste_id",id);
		CasoTeste casoTeste = new CasoTeste();
		casoTeste.setDeleted(false);
		return casoTeste;
		
	}
	
	public void cadastrar(final CasoTeste casoTeste, Integer plano_teste_id){
		
				
		final CasoTeste casoMesmoNome = this.casoTesteDAO.consultar(plano_teste_id, casoTeste.getTitulo());
		
		// Valida se os atributos obrig�rios foram preenchidos.
		this.validator.checking(new Validations() {{				
			that(!casoTeste.getTitulo().isEmpty(),"O t�tulo do Caso de Teste � obrigat�rio!","titulo");
			that(!casoTeste.getDescricao().isEmpty(),"A descri��o do Caso de Teste � obrigat�ria!","descricao");
			that(!casoTeste.getCaso().isEmpty(),"O caso do Caso de Teste � obrigat�ria!","caso");
			that(casoMesmoNome == null,"J� existe um caso de teste cadastrado com esse t�tulo para esse plano de teste!","titulo");
		}});
						
		// Retorna para a tela de cadastro caso haja algum erro de preenchimento.
		this.result.include(casoTeste);
		this.validator.onErrorUsePageOf(this).novo(plano_teste_id);
		
		this.casoTesteDAO.salvar(plano_teste_id, casoTeste);
		this.result.include("notice","Caso de teste criado com sucesso!");
		this.result.redirectTo(this).index(plano_teste_id);
	
		
	}
	
	public CasoTeste editar(Integer id) {
	
		CasoTeste casoTeste = this.casoTesteDAO.consultar(id);
		this.result.include("plano_teste_id",casoTeste.getPlanoTeste().getId());
		return casoTeste;
		
	}
	
	public void atualizar(final CasoTeste casoTeste, Integer plano_teste_id){
		
		
		final CasoTeste casoMesmoNome = this.casoTesteDAO.consultar(plano_teste_id, 
																	casoTeste.getId(), 
																	casoTeste.getTitulo());
		
		// Valida se os atributos obrig�rios foram preenchidos.
		this.validator.checking(new Validations() {{				
			that(!casoTeste.getTitulo().isEmpty(),"O t�tulo do Caso de Teste � obrigat�rio!","titulo");
			that(!casoTeste.getDescricao().isEmpty(),"A descri��o do Caso de Teste � obrigat�ria!","descricao");
			that(!casoTeste.getCaso().isEmpty(),"O caso do Caso de Teste � obrigat�ria!","caso");
			that(casoMesmoNome == null,"J� existe um caso de teste cadastrado com esse t�tulo para esse plano de teste!","titulo");
		}});

		// Retorna para a tela de cadastro caso haja algum erro de preenchimento.
		this.result.include(casoTeste);
		this.validator.onErrorUsePageOf(this).editar(casoTeste.getId());
		
		CasoTeste casoTesteAtualizar = this.casoTesteDAO.consultar(casoTeste.getId());
		casoTesteAtualizar.setTitulo(casoTeste.getTitulo());
		casoTesteAtualizar.setDescricao(casoTeste.getDescricao());
		casoTesteAtualizar.setCaso(casoTeste.getCaso());
		
		this.casoTesteDAO.atualizar(casoTesteAtualizar);
				
		this.result.include("notice", "Plano de teste atualizado com sucesso.");
		this.result.redirectTo(this).index(casoTesteAtualizar.getPlanoTeste().getId());
	}
	
	public void remover(Integer id){

		
		Integer planoTesteId = this.casoTesteDAO.remover(id);

		this.result.include("notice", "Caso de teste removido com sucesso.");
		this.result.redirectTo(this).index(planoTesteId);		
		
	}
	
	
	// Executa o caso de teste.
	public void executarTeste(Integer id) {
		
		CasoTeste caso = this.casoTesteDAO.consultar(id);
		
		LogExecucao log = new LogExecucao();
		log.setCasoTeste(caso);
		log.setDataExecucao(GregorianCalendar.getInstance().getTime());
		
		boolean exception = false;
		String message = null;
		
		try {
			
			CucumberUtil.prepareFeature(caso.getCaso());
			message = CucumberUtil.executeCucumber();
			log.setMensagem(message);
			
			exception = (message.indexOf("failed") != -1);
				
		} catch (IOException e) {
			
			exception = true;
			this.result.include("notice", "O seguinte erro ocorreu ao executar o caso de teste: "+e.getMessage());
			
		} catch (InterruptedException e) {

			exception = true;
			this.result.include("notice", "O seguinte erro ocorreu ao executar o caso de teste: "+e.getMessage());
			
		}

		if (!exception) {
			this.result.include("notice", "Caso de teste executado.");
		}
		
		
		log.setSucesso(!exception);
		this.logExecucaoDAO.cadastrar(log);
		this.result.redirectTo(this).index(caso.getPlanoTeste().getId());
		
	}
	
}

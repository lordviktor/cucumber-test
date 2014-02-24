package br.com.testaprojeto.controller;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.testaprojeto.dao.CasoTesteDAO;
import br.com.testaprojeto.dao.LogExecucaoDAO;
import br.com.testaprojeto.model.CasoTeste;
import br.com.testaprojeto.model.LogExecucao;
import br.com.testaprojeto.model.PlanoTeste;

@Resource
public class LogExecucaosController {
	Result result = null;
	Session session = null;
	Validator validator = null;
	CasoTesteDAO casoTesteDAO = null;
	LogExecucaoDAO logExecucaoDAO = null;
	
	public LogExecucaosController(Result result,
								 Session session,
								 Validator validator,
								 CasoTesteDAO casoTesteDAO,
								 LogExecucaoDAO logExecucaoDAO){
		super();
		this.result=result;
		this.session=session;
		this.validator=validator;
		this.casoTesteDAO=casoTesteDAO;
		this.logExecucaoDAO=logExecucaoDAO;
	}
	
	public List<LogExecucao> index(Integer id){

		CasoTeste casoTeste = this.casoTesteDAO.consultar(id);
	
		this.result.include("caso_teste_id", id);
		this.result.include("caso_teste",casoTeste);
		this.result.include("plano_teste_id",casoTeste.getPlanoTeste().getId());
		
		List<LogExecucao> logs = this.logExecucaoDAO.listar(id);
		return logs;
		
	}

}

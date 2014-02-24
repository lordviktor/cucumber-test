package br.com.testaprojeto.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LogExecucao {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Date dataExecucao;
	private boolean sucesso;
	
	@Column(columnDefinition="LONGVARCHAR")
	private String mensagem;
	
	@ManyToOne(	targetEntity=CasoTeste.class,
				cascade=CascadeType.REMOVE)
	@JoinColumn(columnDefinition="caso_teste_id",
				referencedColumnName="id",
				updatable=false)
	private CasoTeste casoTeste = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public String getMensagem() {
		return mensagem.replaceAll("\n", "<br/>");
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public CasoTeste getCasoTeste() {
		return casoTeste;
	}

	public void setCasoTeste(CasoTeste casoTeste) {
		this.casoTeste = casoTeste;
	}

	
	
}

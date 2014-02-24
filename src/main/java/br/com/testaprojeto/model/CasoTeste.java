package br.com.testaprojeto.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class CasoTeste {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id = null;
	String titulo = null;

	@Column(columnDefinition="LONGVARCHAR")
	String descricao = null;

	@Column(columnDefinition="LONGVARCHAR")
	String caso = null;
	Boolean deleted = null;

	// Plano de teste.
	@ManyToOne(	targetEntity=PlanoTeste.class,
				fetch=FetchType.EAGER,
				cascade=CascadeType.ALL)
	@JoinColumn(columnDefinition="plano_teste_id",
				referencedColumnName="id",
				updatable=false)
	private PlanoTeste planoTeste = null;

	// Logs de execu��o.
	@OneToMany(targetEntity=LogExecucao.class,
			mappedBy="casoTeste",
			cascade=CascadeType.ALL)
	private Set<LogExecucao> logs = null;

	
	@Transient
	public int getRunTimes() {
		return logs.size();
	}
	
	
	public Set<LogExecucao> getLogs() {
		return logs;
	}
	public void setLogs(Set<LogExecucao> logs) {
		this.logs = logs;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCaso() {
		return caso;
	}
	public void setCaso(String caso) {
		this.caso = caso;
	}
	
	public PlanoTeste getPlanoTeste() {
		return planoTeste;
	}
	public void setPlanoTeste(PlanoTeste planoTeste) {
		this.planoTeste = planoTeste;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
}

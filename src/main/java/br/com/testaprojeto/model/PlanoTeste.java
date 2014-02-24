package br.com.testaprojeto.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class PlanoTeste {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id = null;
	String titulo = null;
	String descricao = null;
	Integer versao = 0;
	
	@ManyToOne(	targetEntity=Projeto.class,
				fetch=FetchType.EAGER)
	@JoinColumn(columnDefinition="projeto_id",
				referencedColumnName="id",
				updatable=false)
	Projeto projeto = null;

	@OneToMany(targetEntity=CasoTeste.class,
		fetch=FetchType.EAGER,
		mappedBy="planoTeste",
		cascade=CascadeType.REMOVE)
	private Set<CasoTeste> casos = null;
	
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

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Set<CasoTeste> getCasos() {
		return casos;
	}

	public void setCasos(Set<CasoTeste> casos) {
		this.casos = casos;
	}	
	
}

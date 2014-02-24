package br.com.testaprojeto.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Projeto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id = null;
	private String titulo = null;
	private String descricao = null;
	private String url = null;
	
	@OneToMany(targetEntity=PlanoTeste.class,
				fetch=FetchType.EAGER,
				mappedBy="projeto",
				cascade=CascadeType.REMOVE)
	private Set<PlanoTeste> planos = null;
	
	public Set<PlanoTeste> getPlanos() {
		return planos;
	}
	public void setPlanos(Set<PlanoTeste> planos) {
		this.planos = planos;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}

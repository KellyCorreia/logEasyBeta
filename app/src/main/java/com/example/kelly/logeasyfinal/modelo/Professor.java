package com.example.kelly.logeasyfinal.modelo;

import java.io.Serializable;
import java.util.List;

public class Professor extends AbstractDomainClass implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String nome;

	private User usuario;
	private List<Conteudo> conteudos;

	public Professor() {
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Conteudo> getConteudos() {
		return conteudos;
	}

	public void setConteudos(List<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public boolean equals(Object obj) {
		Professor prof = (Professor) obj;
		return this.id.equals(prof.id);
	}
}
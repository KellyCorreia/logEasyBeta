package com.example.kelly.logeasyfinal.modelo;

import java.io.Serializable;
import java.util.List;

public class Disciplina extends AbstractDomainClass implements Serializable {
	private static final long serialVersionUID = 1L;

	 private String codigo;
	 private String nome;
	 private String descricao;

	private List<Curso> cursos;

	public Disciplina() {
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
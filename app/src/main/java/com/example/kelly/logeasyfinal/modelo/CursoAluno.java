package com.example.kelly.logeasyfinal.modelo;

import java.io.Serializable;


public class CursoAluno extends AbstractDomainClass implements Serializable {
	private static final long serialVersionUID = 1L;

	private int pontuacao;
	private Double percentualErro;

	private Aluno aluno;
	private Curso curso;
	private Conteudo conteudo;

	public int getPontuacao() {
		return this.pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Double getPercentualErro() {
		return percentualErro;
	}

	public void setPercentualErro(Double percentualErro) {
		this.percentualErro = percentualErro;
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}
}
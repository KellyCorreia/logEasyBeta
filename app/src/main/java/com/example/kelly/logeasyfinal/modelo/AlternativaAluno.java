package com.example.kelly.logeasyfinal.modelo;

import java.io.Serializable;
import java.util.Calendar;

public class AlternativaAluno extends AbstractDomainClass implements Serializable {
	private static final long serialVersionUID = 1L;

	private Alternativa alternativa;
	private Aluno aluno;
	
	private Calendar dataHora;

	public AlternativaAluno() {
	}

	public AlternativaAluno(Alternativa alt, Aluno alu) {
		this.alternativa = alt;
		this.aluno = alu;
	}

	public Alternativa getAlternativa() {
		return this.alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Calendar getDataHora() {
		return dataHora;
	}

	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}

}
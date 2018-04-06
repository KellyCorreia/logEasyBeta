package com.example.kelly.logeasyfinal.modelo;

import java.io.Serializable;
import java.util.List;

public class AlunoWrap implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Aluno> alunos;

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
}

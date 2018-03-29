package com.example.kelly.logeasyfinal.modelo;

import java.io.Serializable;
import java.util.List;

public class Avatar extends AbstractDomainClass implements Serializable {
	private static final long serialVersionUID = 1L;

	 private String nome;
	 private String caracteristica;

	private List<Aluno> alunos;
	private List<AmbienteAvatar> ambientesAvatar;

	public Avatar() {
	}

	public Avatar(String n) {
		this.nome = n;
	}

	public String getCaracteristica() {
		return this.caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aluno> getAlunos() {
		return this.alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<AmbienteAvatar> getAmbientesAvatar() {
		return ambientesAvatar;
	}

	public void setAmbientesAvatar(List<AmbienteAvatar> ambientesAvatar) {
		this.ambientesAvatar = ambientesAvatar;
	}
}
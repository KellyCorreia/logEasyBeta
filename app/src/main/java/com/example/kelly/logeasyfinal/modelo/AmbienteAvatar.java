package com.example.kelly.logeasyfinal.modelo;

import java.io.Serializable;

public class AmbienteAvatar extends AbstractDomainClass implements Serializable {
	private static final long serialVersionUID = 1L;

	private String falaInicialNivel;

	private Avatar avatar;

	private Ambiente ambiente;

	public AmbienteAvatar(){

	}

	public AmbienteAvatar(Integer id){
		this.id = id;
	}

	public String getFalaInicialNivel() {
		return this.falaInicialNivel;
	}

	public void setFalaInicialNivel(String falaInicialNivel) {
		this.falaInicialNivel = falaInicialNivel;
	}

	public Avatar getAvatar() {
		return this.avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

}
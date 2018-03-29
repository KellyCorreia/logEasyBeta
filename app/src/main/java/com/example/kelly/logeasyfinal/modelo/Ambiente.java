package com.example.kelly.logeasyfinal.modelo;

import java.io.Serializable;
import java.util.List;

public class Ambiente extends AbstractDomainClass implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String objetivo;
    private String elemento;
    private String descricao;

	private Nivel nivel;
	private List<AmbienteAvatar> AmbientesAvatar;

	public Ambiente() {
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getElemento() {
		return this.elemento;
	}

	public void setElemento(String elemento) {
		this.elemento = elemento;
	}

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNiveis(Nivel nivel) {
		this.nivel = nivel;
	}

	public List<AmbienteAvatar> getAmbientesAvatar() {
		return AmbientesAvatar;
	}

	public void setAmbientesAvatar(List<AmbienteAvatar> ambientesAvatar) {
		AmbientesAvatar = ambientesAvatar;
	}

}
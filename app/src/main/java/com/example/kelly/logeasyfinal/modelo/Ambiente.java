package com.example.kelly.logeasyfinal.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Ambiente extends AbstractDomainClass implements Serializable, Parcelable {
	private static final long serialVersionUID = 1L;
	
	private String objetivo;
    private String elemento;
    private String descricao;

	private Nivel nivel;
	private List<AmbienteAvatar> ambientesAvatar;

	public Ambiente(){
	}

	public Ambiente(Parcel in) {
		readFromParcel(in);
	}

	public Ambiente(Integer id){
		this.id = id;
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
		return ambientesAvatar;
	}

	public void setAmbientesAvatar(List<AmbienteAvatar> ambientesAvatar) {
		this.ambientesAvatar = ambientesAvatar;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(id);
		out.writeString(objetivo);
		out.writeString(elemento);
		out.writeString(descricao);
		out.writeParcelable(nivel,  flags);
	}

	private void readFromParcel(Parcel in) {
		id = in.readInt();
		objetivo = in.readString();
		elemento = in.readString();
		descricao = in.readString();
		nivel = in.readParcelable(Nivel.class.getClassLoader());
	}

	@SuppressWarnings("unchecked")
	public static final Creator CREATOR = new Creator() {
		public Ambiente createFromParcel(Parcel in) {
			return new Ambiente(in);
		}
		public Ambiente[] newArray(int size) {
			return new Ambiente[size];
		}
	};

}
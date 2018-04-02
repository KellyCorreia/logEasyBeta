package com.example.kelly.logeasyfinal.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Professor extends AbstractDomainClass implements Serializable, Parcelable {
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String nome;

	private User usuario;
	private List<Conteudo> conteudos;

	public Professor() {
	}

	public Professor(Parcel in) {
		readFromParcel(in);
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(id);
		out.writeString(codigo);
		out.writeString(nome);
	}

	private void readFromParcel(Parcel in) {
		id = in.readInt();
		codigo = in.readString();
		nome = in.readString();
	}

	@SuppressWarnings("unchecked")
	public static final Creator CREATOR = new Creator() {
		public Professor createFromParcel(Parcel in) {
			return new Professor(in);
		}
		public Professor[] newArray(int size) {
			return new Professor[size];
		}
	};
}
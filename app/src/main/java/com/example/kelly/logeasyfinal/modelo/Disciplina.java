package com.example.kelly.logeasyfinal.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Disciplina extends AbstractDomainClass implements Serializable, Parcelable {
	private static final long serialVersionUID = 1L;

	 private String codigo;
	 private String nome;
	 private String descricao;

	private List<Curso> cursos;

	public Disciplina() {
	}

	public Disciplina(Parcel in) {
		readFromParcel(in);
	}

	public Disciplina(Integer i){
		this.id = i;
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(id);
		out.writeString(codigo);
		out.writeString(nome);
		out.writeString(descricao);
	}

	private void readFromParcel(Parcel in) {
		id = in.readInt();
		codigo = in.readString();
		nome = in.readString();
		descricao = in.readString();
	}

	@SuppressWarnings("unchecked")
	public static final Creator CREATOR = new Creator() {
		public Disciplina createFromParcel(Parcel in) {
			return new Disciplina(in);
		}
		public Disciplina[] newArray(int size) {
			return new Disciplina[size];
		}
	};

}
package com.example.kelly.logeasyfinal.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Aluno extends AbstractDomainClass implements Parcelable, Serializable {
	private static final long serialVersionUID = 1L;

	 private String codigo;
	 private String nome;

	 private User usuario;
	private Avatar avatar;
	private List<AlternativaAluno> alternativasAluno;

	private List<CursoAluno> cursosAluno;

	public Aluno() {
	}

    public Aluno(Parcel in) {
        readFromParcel(in);
    }

	public Aluno(String codigo, String nome, User usuario, Avatar avatar) {
		this.codigo = codigo;
		this.nome = nome;
		this.usuario = usuario;
		this.avatar = avatar;
	}

	public Aluno(Integer i){
		this.id = i;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Avatar getAvatar() {
		return this.avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public List<AlternativaAluno> getAlternativasAluno() {
		return alternativasAluno;
	}

	public void setAlternativasAluno(List<AlternativaAluno> alternativasAluno) {
		this.alternativasAluno = alternativasAluno;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<CursoAluno> getCursosAluno() {
		return cursosAluno;
	}

	public void setCursosAluno(List<CursoAluno> cursosAluno) {
		this.cursosAluno = cursosAluno;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
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
		out.writeParcelable(usuario, flags);
        out.writeParcelable(avatar, flags);
	}

	private void readFromParcel(Parcel in) {
		id = in.readInt();
		codigo = in.readString();
		nome = in.readString();
        usuario = in.readParcelable(User.class.getClassLoader());
		avatar = in.readParcelable(Avatar.class.getClassLoader());
	}

	@SuppressWarnings("unchecked")
	public static final Creator CREATOR = new Creator() {
		public Aluno createFromParcel(Parcel in) {
			return new Aluno(in);
		}

		public Aluno[] newArray(int size) {
			return new Aluno[size];
		}
	};
}
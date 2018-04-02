package com.example.kelly.logeasyfinal.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Avatar extends AbstractDomainClass implements Parcelable, Serializable {
	private static final long serialVersionUID = 1L;

	 private String nome;
	 private String caracteristica;

	private List<Aluno> alunos;
	private List<AmbienteAvatar> ambientesAvatar;

	public Avatar(){

	}

	public Avatar(Integer id){
		this.id = id;
	}

	protected Avatar(Parcel in) {
		readFromParcel(in);
	}

	public static final Creator<Avatar> CREATOR = new Creator<Avatar>() {
		@Override
		public Avatar createFromParcel(Parcel in) {
			return new Avatar(in);
		}

		@Override
		public Avatar[] newArray(int size) {
			return new Avatar[size];
		}
	};

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(id);
		out.writeString(nome);
		out.writeString(caracteristica);
	}

	private void readFromParcel(Parcel in) {
		id = in.readInt();
		nome = in.readString();
		caracteristica = in.readString();

	}
}
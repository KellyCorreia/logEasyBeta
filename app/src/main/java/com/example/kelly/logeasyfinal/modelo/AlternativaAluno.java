package com.example.kelly.logeasyfinal.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Calendar;

public class AlternativaAluno extends AbstractDomainClass implements Serializable, Parcelable {
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

    public AlternativaAluno(Integer i) {
	    this.id = i;
    }

	public AlternativaAluno(Parcel in) {
		readFromParcel(in);
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(id);
		out.writeParcelable(alternativa, flags);
		out.writeParcelable(aluno, flags);
	}

	private void readFromParcel(Parcel in) {
		id = in.readInt();
		alternativa = in.readParcelable(Alternativa.class.getClassLoader());
		aluno = in.readParcelable(Aluno.class.getClassLoader());
	}

	@SuppressWarnings("unchecked")
	public static final Creator CREATOR = new Creator() {
		public AlternativaAluno createFromParcel(Parcel in) {
			return new AlternativaAluno(in);
		}
		public AlternativaAluno[] newArray(int size) {
			return new AlternativaAluno[size];
		}
	};

}
package com.example.kelly.logeasyfinal.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Comparator;


public class CursoAluno implements Serializable, Parcelable {
	private static final long serialVersionUID = 1L;

	private int pontuacao;
	private Double percentualErro;

	private Aluno aluno;
	private Curso curso;
	private Conteudo conteudo;

	public CursoAluno(Parcel in) {
		readFromParcel(in);
	}

	public CursoAluno() {
	}

	public int getPontuacao() {
		return this.pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Double getPercentualErro() {
		return percentualErro;
	}

	public void setPercentualErro(Double percentualErro) {
		this.percentualErro = percentualErro;
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(pontuacao);
		out.writeDouble(percentualErro);
		out.writeParcelable(aluno, flags);
		out.writeParcelable(curso, flags);
		out.writeParcelable(conteudo, flags);
	}

	private void readFromParcel(Parcel in) {
		pontuacao = in.readInt();
		percentualErro = in.readDouble();
		aluno = in.readParcelable(Aluno.class.getClassLoader());
		curso = in.readParcelable(Curso.class.getClassLoader());
		conteudo = in.readParcelable(Conteudo.class.getClassLoader());
	}

	@SuppressWarnings("unchecked")
	public static final Creator CREATOR = new Creator() {
		public CursoAluno createFromParcel(Parcel in) {
			return new CursoAluno(in);
		}
		public CursoAluno[] newArray(int size) {
			return new CursoAluno[size];
		}
	};

}
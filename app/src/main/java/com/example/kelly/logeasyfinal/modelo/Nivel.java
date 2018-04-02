package com.example.kelly.logeasyfinal.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Nivel extends AbstractDomainClass implements Serializable, Parcelable {
	private static final long serialVersionUID = 1L;

	private String descricao;
	
	private int ordem;

	private int pontosQuestaoDefault;

	private int qtdPontosFinal;

	private int qtdPontosInicial;

	private Ambiente ambiente;

	private List<Conteudo> conteudos;

	public Nivel(){
	}

	public Nivel(Parcel in) {
		readFromParcel(in);
	}

	public Nivel(Integer id){
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPontosQuestaoDefault() {
		return this.pontosQuestaoDefault;
	}

	public void setPontosQuestaoDefault(int pontosQuestaoDefault) {
		this.pontosQuestaoDefault = pontosQuestaoDefault;
	}

	public int getQtdPontosFinal() {
		return this.qtdPontosFinal;
	}

	public void setQtdPontosFinal(int qtdPontosFinal) {
		this.qtdPontosFinal = qtdPontosFinal;
	}

	public int getQtdPontosInicial() {
		return this.qtdPontosInicial;
	}

	public void setQtdPontosInicial(int qtdPontosInicial) {
		this.qtdPontosInicial = qtdPontosInicial;
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	public List<Conteudo> getConteudos() {
		return conteudos;
	}

	public void setConteudos(List<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(id);
		out.writeString(descricao);
		out.writeInt(ordem);
		out.writeInt(pontosQuestaoDefault);
		out.writeInt(qtdPontosFinal);
		out.writeInt(qtdPontosInicial);
		out.writeParcelable(ambiente,  flags);
	}

	private void readFromParcel(Parcel in) {
		id = in.readInt();
		descricao = in.readString();
		ordem = in.readInt();
		pontosQuestaoDefault = in.readInt();
		qtdPontosFinal = in.readInt();
		qtdPontosInicial = in.readInt();
		ambiente = in.readParcelable(Ambiente.class.getClassLoader());
	}

	@SuppressWarnings("unchecked")
	public static final Creator CREATOR = new Creator() {
		public Nivel createFromParcel(Parcel in) {
			return new Nivel(in);
		}
		public Nivel[] newArray(int size) {
			return new Nivel[size];
		}
	};
	
}
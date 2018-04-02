package com.example.kelly.logeasyfinal.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Questao extends AbstractDomainClass implements Serializable, Parcelable {
    private static final long serialVersionUID = 1L;

    private String enunciado;
    private int pontuacao;
    private Conteudo conteudo;
    private List<Alternativa> alternativas;

    private int level_id;

    public Questao(){
        id = 0;
        level_id=0;
        this.alternativas = new ArrayList<Alternativa>();
        this.enunciado = " ";
    }

    public Questao(Integer q_id, String q_text, int l_id){
        id =q_id;
        enunciado =q_text;
        level_id=l_id;
    }

    public Questao(Parcel in) {
        readFromParcel(in);
    }

    public Questao(Integer id){
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String question_text) {
        this.enunciado = question_text;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public List<Alternativa> getAlternativas() {
        return this.alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public Conteudo getConteudo() {
        return this.conteudo;
    }

    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public boolean equals(Object obj){
        Questao q = (Questao) obj;

        return (this.enunciado.equals(q.enunciado));
    }

    @Override
    public int hashCode() {
        return this.enunciado.hashCode();
    }

    public void setLevel_id(int level_id){
        this.level_id=level_id;
    }

    public int getLevel_id(){
        return level_id;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return enunciado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(enunciado);
        out.writeInt(pontuacao);
    }

    private void readFromParcel(Parcel in) {
        id = in.readInt();
        enunciado = in.readString();
        pontuacao = in.readInt();
    }

    @SuppressWarnings("unchecked")
    public static final Creator CREATOR = new Creator() {
        public Questao createFromParcel(Parcel in) {
            return new Questao(in);
        }
        public Questao[] newArray(int size) {
            return new Questao[size];
        }
    };
}





package com.example.kelly.logeasyfinal.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Conteudo extends AbstractDomainClass implements Serializable, Parcelable {
    private static final long serialVersionUID = 1L;

    private String licao;
    private String dica;
    private String nome;
    private Professor professor;
    private Curso curso;
    private Nivel nivel;

    private List<Questao> questoes;

    private int curso_id;

    public Conteudo(){
        id = 0;
        curso_id = 0;
        nome ="";
        licao ="";
        dica ="";
        this.curso = new Curso();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String levelname) {
        this.nome = levelname;
    }

    public String getLicao() {
        return licao;
    }

    public void setLicao(String licao) {
        this.licao = licao;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Curso getGrupoConteudo() {
        return curso;
    }

    public void setGrupoConteudo(Curso grupoConteudo) {
        this.curso = grupoConteudo;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.dica + " - " + this.licao;
    }

    public int getCurso_id() {
        return curso_id;
    }
    public void setCurso_id(int c_id) {
        this.curso_id = c_id;
    }

    public Conteudo(Parcel in) {
        readFromParcel(in);
    }

    public Conteudo(int l_id, String l_name, String l_lesson, String l_tip, int c_id){
        id =l_id;
        curso_id = c_id;
        nome =l_name;
        licao =l_lesson;
        dica =l_tip;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeInt(curso_id);
        out.writeString(nome);
        out.writeString(licao);
        out.writeString(dica);
    }

    private void readFromParcel(Parcel in) {
        id = in.readInt();
        curso_id = in.readInt();
        nome = in.readString();
        licao = in.readString();
        dica = in.readString();
    }

    @SuppressWarnings("unchecked")
    public static final Creator CREATOR = new Creator() {
        public Conteudo createFromParcel(Parcel in) {
            return new Conteudo(in);
        }
        public Conteudo[] newArray(int size) {
            return new Conteudo[size];
        }
    };
}

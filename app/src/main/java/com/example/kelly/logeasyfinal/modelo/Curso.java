package com.example.kelly.logeasyfinal.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Curso extends AbstractDomainClass implements Parcelable, Serializable {
    private static final long serialVersionUID = 1L;

    //private int id;
    private String codigo;
    private String nome;
    private String descricao;
    private Disciplina disciplina;
    private List<CursoAluno> alunosCurso;
    private List<Conteudo> conteudos;


    public Curso(){
        id = 0;
        nome ="";
        descricao="";
        this.disciplina = new Disciplina();
    }

    public Curso(Parcel in) {
        readFromParcel(in);
    }

    public Curso(Integer c_id, String c_name, String desc){
        id =c_id;
        nome =c_name;
        descricao=desc;
    }

    public Curso(Integer i){
        this.id = i;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String cursoname) {
        this.nome = cursoname;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String desc) {
        this.descricao = desc;
    }

    public Disciplina getDisciplina() {
        return this.disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public List<CursoAluno> getGrupoConteudosAlunos() {
        return alunosCurso;
    }

    public void setGrupoConteudosAlunos(List<CursoAluno> cursosAlunos) {
        this.alunosCurso = cursosAlunos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<CursoAluno> getAlunosCurso() {
        return alunosCurso;
    }

    public void setAlunosCurso(List<CursoAluno> alunosCurso) {
        this.alunosCurso = alunosCurso;
    }

    public List<Conteudo> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<Conteudo> conteudos) {
        this.conteudos = conteudos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(nome);
        out.writeString(descricao);
        //out.writeParcelable(disciplina, flags);
    }

    private void readFromParcel(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        descricao = in.readString();
       // disciplina = in.readParcelable(Disciplina.class.getClassLoader());
    }

    @SuppressWarnings("unchecked")
    public static final Creator CREATOR = new Creator() {
        public Curso createFromParcel(Parcel in) {
            return new Curso(in);
        }
        public Curso[] newArray(int size) {
            return new Curso[size];
        }
    };
}

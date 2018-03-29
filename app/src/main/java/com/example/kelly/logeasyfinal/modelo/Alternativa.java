package com.example.kelly.logeasyfinal.modelo;

import java.io.Serializable;
import java.util.List;

public class Alternativa extends AbstractDomainClass implements Serializable {

    private String texto;
    private boolean valor;

    private Questao questao;
    private List<AlternativaAluno> alternativaAlunos;


    //remover
    private String question_id;

    public Alternativa() {
        id = 0;
        question_id = "";
        texto = "";
        this.texto = "";
        this.valor = false;
    }

    public Alternativa(Integer a_id, String a_text, String q_id, int a_state) {
        id =a_id;
        question_id=q_id;
        texto =a_text;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isValor() {
        return valor;
    }

    public void setValor(boolean valor) {
        this.valor = valor;
    }

    public Questao getQuestao() {
        return this.questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public List<AlternativaAluno> getAlternativaAlunos() {
        return this.alternativaAlunos;
    }

    public void setAlternativaAlunos(List<AlternativaAluno> alternativaAlunos) {
        this.alternativaAlunos = alternativaAlunos;
    }

    @Override
    public boolean equals(Object obj){
        Alternativa alt = (Alternativa) obj;

        return (this.texto.equals(alt.texto)&& this.questao.equals(alt.questao));
    }

    @Override
    public int hashCode() {
        return this.texto.hashCode();
    }

    @Override
    public String toString() {
        return texto;
    }

    //remover
    public String getQuestion_id() {
        return question_id;
    }
    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

}
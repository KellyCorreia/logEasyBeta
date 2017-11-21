package com.example.kelly.logeasyfinal;

/**
 * Created by Kelly on 03/04/2015.
 */
public class AnswerClass {

    private String answer_id;
    private String question_id;
    private String answer_text;

    public AnswerClass() {
        answer_id = "";
        question_id = "";
        answer_text = "";
    }

    public AnswerClass(String a_id, String a_text, String q_id) {
        answer_id=a_id;
        question_id=q_id;
        answer_text=a_text;

    }

    public String getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    @Override
    public String toString() {
        return answer_text;
    }
}
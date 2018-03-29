package com.example.kelly.logeasyfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kelly.logeasyfinal.modelo.AlternativaAluno;
import com.example.kelly.logeasyfinal.modelo.Aluno;
import com.example.kelly.logeasyfinal.modelo.Curso;
import com.example.kelly.logeasyfinal.modelo.CursoAluno;
import com.example.kelly.logeasyfinal.persistencia.MySQLiteHelper;
import com.example.kelly.logeasyfinal.modelo.Alternativa;
import com.example.kelly.logeasyfinal.modelo.Conteudo;
import com.example.kelly.logeasyfinal.modelo.Questao;

import java.util.ArrayList;
import java.util.List;


public class ActivityQuiz extends Activity {

    List<Questao> qList = new ArrayList<>();
    List<Alternativa> aList = new ArrayList<>();

    TextView txtQuest, txtPoints;
    RadioGroup grp;
    RadioButton rda, rdb, rdc;
    Alternativa alta, altb, altc;
    Questao questao;
    Button butNext, btnLesson, btnHint,btnLevelsQ;
    RelativeLayout layout;
    RadioButton rightAnswer,userAnswer;
    CursoAluno scoreAluno;

    Aluno aluno;
    Conteudo selecLevel;
    Intent intent = new Intent();
    MySQLiteHelper db;

    int score;
    AlternativaAluno alternativaAluno;
    Double wScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        db = new MySQLiteHelper(this);

        Bundle extras = getIntent().getExtras();
        aluno = extras.getParcelable("chosenUser");
        selecLevel = extras.getParcelable("chosenLevel");

        scoreAluno = db.getCursoAluno(aluno.getId(), selecLevel.getCurso().getId());

        score = scoreAluno.getPontuacao();
        wScore = scoreAluno.getPercentualErro();

        txtPoints = (TextView)findViewById(R.id.txtPoints);
        txtQuest =(TextView)findViewById(R.id.txtQuestion);
        grp=(RadioGroup)findViewById(R.id.radioGroupAnswers);
        rda=(RadioButton)findViewById(R.id.radioA);
        rdb=(RadioButton)findViewById(R.id.radioB);
        rdc=(RadioButton)findViewById(R.id.radioC);
        butNext=(Button)findViewById(R.id.btnNext);
        btnHint = (Button)findViewById(R.id.btnHint);
        btnLesson = (Button)findViewById(R.id.btnLesson);
        btnLevelsQ = (Button)findViewById(R.id.btnLevelsQ);
        layout = (RelativeLayout)findViewById(R.id.layoutQuiz);

        setQuestionView();

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((rda.isChecked()) || (rdb.isChecked())||(rdc.isChecked())) {

                    userAnswer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());

                    if (rda.isChecked()){
                        alternativaAluno = new AlternativaAluno(alta, aluno);
                    }else if(rdb.isChecked()){
                        alternativaAluno = new AlternativaAluno(altb, aluno);
                    }else if(rdc.isChecked()){
                        alternativaAluno = new AlternativaAluno(altc, aluno);
                    }

                    if (userAnswer == rightAnswer) {
                        Toast.makeText(ActivityQuiz.this, "Você Acertou!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ActivityQuiz.this, "Você errou!", Toast.LENGTH_SHORT).show();
                    }

                    if (selecLevel.getNivel().getQtdPontosFinal() > scoreAluno.getPontuacao()) {
                        setScoreBoard();
                    }
                    else{
                        setQuestionView();
                    }

                    grp.clearCheck();

                }else{
                    Toast.makeText(ActivityQuiz.this, "Selecione uma alternativa!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(ActivityQuiz.this, ActivityHint.class);
                intent.putExtra("chosenUser", (Parcelable) aluno);
                intent.putExtra("chosenLevel", (Parcelable) selecLevel);
                intent.putExtra("userScore", (Parcelable) scoreAluno);
                startActivity(intent);
            }
        });

        btnLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(ActivityQuiz.this, ActivityLesson.class);
                intent.putExtra("chosenUser", (Parcelable) aluno);
                intent.putExtra("chosenLevel", (Parcelable) selecLevel);
                intent.putExtra("userScore", (Parcelable) scoreAluno);
                startActivityForResult(intent,0);
            }
        });
        btnLevelsQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void setQuestionView(){

        if(qList.size() == 0) {
            qList = db.questoesConteudo(selecLevel);
        }

        rdc.setVisibility(View.GONE);

        aList = db.alternativasQuestao(qList.get(0));
        String backBaseName = "backgroundlevel";
        layout.setBackgroundResource(getResources().getIdentifier(backBaseName + String.valueOf(selecLevel.getNivel().getId()), "drawable", this.getPackageName()));

        txtPoints.setText(Integer.toString(scoreAluno.getPontuacao()));
        txtQuest.setText(qList.get(0).getEnunciado());
        questao = qList.get(0);
        int i= 0;
        while(i < aList.size()) {
            if(i == 0) {
                rda.setText(aList.get(i).getTexto());
                alta = aList.get(i);
            }else if(i == 1){
                rda.setText(aList.get(i).getTexto());
                altb = aList.get(i);
            }else if(i == 2){
                rdc.setVisibility(View.VISIBLE);
                rdc.setText(aList.get(i).getTexto());
                altc = aList.get(i);
            }
            i++;
        }
        if(aList.get(0).isValor()) {
            rightAnswer = rda;
        }else {
            if (aList.get(1).isValor()) {
                rightAnswer = rdb;
            } else {
                rightAnswer = rdc;
            }
        }
        qList.remove(0);

        score = scoreAluno.getPontuacao();
        wScore = scoreAluno.getPercentualErro();
    }
    public void setAlertView(){
        RelativeLayout lay1, lay2;
        Button next1;
        lay1 = (RelativeLayout)findViewById(R.id.ShowIt);
        lay2 = (RelativeLayout)findViewById(R.id.HideIt);
        next1 = (Button)findViewById(R.id.NextIt);
        lay1.setVisibility(View.VISIBLE);
        lay2.setVisibility(View.GONE);

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });
    }

    private void setScoreBoard(){

        intent.setClass(ActivityQuiz.this, ActivityLesson.class);
        CursoAluno scoreAtual = db.addPontuacao(alternativaAluno, selecLevel.getCurso());
        score = scoreAtual.getPontuacao();
        wScore = scoreAtual.getPercentualErro();
        scoreAluno = scoreAtual;

        if(score>=selecLevel.getNivel().getQtdPontosFinal()){

            Toast.makeText(ActivityQuiz.this, "Parabéns!! Você conseguiu "+ selecLevel.getNivel().getAmbiente().getObjetivo() +"!", Toast.LENGTH_SHORT).show();

            selecLevel = scoreAtual.getConteudo();
            intent.putExtra("chosenUser", (Parcelable) aluno);
            intent.putExtra("chosenLevel", (Parcelable) selecLevel);
            intent.putExtra("userScore", score);

            startActivity(intent);
            finish();
        }
    }
}
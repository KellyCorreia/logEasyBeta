package com.example.kelly.logeasyfinal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kelly.logeasyfinal.modelo.Aluno;
import com.example.kelly.logeasyfinal.modelo.AmbienteAvatar;
import com.example.kelly.logeasyfinal.modelo.Conteudo;
import com.example.kelly.logeasyfinal.modelo.Curso;
import com.example.kelly.logeasyfinal.modelo.CursoAluno;
import com.example.kelly.logeasyfinal.persistencia.MySQLiteHelper;

import java.util.Random;

public class ActivityLesson extends FragmentActivity {

    TextView txtPoints;
    ImageButton btnPlay, btnLevels;
    RelativeLayout layout;
    LinearLayout firstLayout;
    Conteudo selecLevel;
    ImageView ImgAvatar;
    AmbienteAvatar ambienteAvatar;
    MySQLiteHelper db = null;
    Random rd = new Random();

    Aluno aluno;
    Curso curso;

    CursoAluno score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        db = new MySQLiteHelper(this);

        Bundle extras = getIntent().getExtras();
        aluno = extras.getParcelable("chosenUser");
        curso = extras.getParcelable("chosenCurso");
        selecLevel = extras.getParcelable("chosenLevel");
        score = extras.getParcelable("userScore");
        ambienteAvatar = db.getAmbienteAvatar(selecLevel.getNivel().getAmbiente().getId(), aluno.getAvatar().getId());
        String licao = selecLevel.getLicao();
        selecLevel.setLicao(ambienteAvatar.getFalaInicialNivel()+". " + " \n Aqui vai a sua Lição:  " + " \n \n " + licao);

        txtPoints = (TextView)findViewById(R.id.txtPoints);
        btnPlay = (ImageButton)findViewById(R.id.btnPlay);
        btnLevels = (ImageButton)findViewById(R.id.btnLevels);
        layout = (RelativeLayout)findViewById(R.id.relativeLayoutLesson);
        firstLayout = (LinearLayout)findViewById(R.id.linearLayoutFirst);
        ImgAvatar = (ImageView)findViewById(R.id.imageViewAvatar);


        this.setLesson();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCallingActivity() == null) {
                    Intent intent = new Intent(ActivityLesson.this, ActivityQuiz.class);
                    intent.putExtra("chosenUser", (Parcelable) aluno);
                    intent.putExtra("chosenCurso", (Parcelable) curso);
                    intent.putExtra("chosenLevel", (Parcelable) selecLevel);
                    startActivity(intent);
                    finish();
                }else{
                    finish();
                }
            }
        });

        btnLevels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Calling the lesson  fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FragmentSlidingLesson fragment = new FragmentSlidingLesson();
        fragment.setArguments(extras);
        transaction.replace(R.id.sample_content_fragmentlesson, fragment);
        transaction.commit();

    }

    private void setLesson(){ //Method to take the lesson from the Level Class and from the User Class
        txtPoints.setText(Integer.toString(score.getPontuacao()));

        firstLayout.setBackgroundColor(Color.parseColor("#FF192030"));


        switch (aluno.getAvatar().getNome()) {
            case "avatar1":
                int random = rd.nextInt(4);
                if (random == 0)
                    ImgAvatar.setImageResource(R.drawable.avatar12);
                else {
                    if (random == 1)
                        ImgAvatar.setImageResource(R.drawable.avatar13);
                    else {
                        if (random == 2)
                            ImgAvatar.setImageResource(R.drawable.avatar14);
                        else {
                            ImgAvatar.setImageResource(R.drawable.avatar15);
                        }
                    }
                }
                break;

            case "avatar2":
                random = rd.nextInt(4);
                if (random == 0)
                    ImgAvatar.setImageResource(R.drawable.avatar22);
                else {
                    if (random == 1)
                        ImgAvatar.setImageResource(R.drawable.avatar23);
                    else {
                        if (random == 2)
                            ImgAvatar.setImageResource(R.drawable.avatar24);
                        else {
                            ImgAvatar.setImageResource(R.drawable.avatar25);
                        }
                    }
                }
                break;

            case "avatar3":
                random = rd.nextInt(4);
                if (random == 0)
                    ImgAvatar.setImageResource(R.drawable.avatar32);
                else {
                    if (random == 1)
                        ImgAvatar.setImageResource(R.drawable.avatar33);
                    else {
                        if (random == 2)
                            ImgAvatar.setImageResource(R.drawable.avatar34);
                        else {
                            ImgAvatar.setImageResource(R.drawable.avatar35);
                        }
                    }
                }
                break;

            case "avatar4":
                random = rd.nextInt(4);
                if (random == 0)
                    ImgAvatar.setImageResource(R.drawable.avatar42);
                else {
                    if (random == 1)
                        ImgAvatar.setImageResource(R.drawable.avatar43);
                    else {
                        if (random == 2)
                            ImgAvatar.setImageResource(R.drawable.avatar44);
                        else {
                            ImgAvatar.setImageResource(R.drawable.avatar45);
                        }
                    }
                }
                break;
        }

        switch(selecLevel.getNivel().getOrdem()){
            case 1:
                layout.setBackgroundResource(R.drawable.backgroundlevel1);
                btnPlay.setBackgroundResource(R.drawable.buttomlevel);
                btnLevels.setBackgroundResource(R.drawable.buttomhin1);
                break;
            case 2:
                layout.setBackgroundResource(R.drawable.backgroundlevel2);
                btnPlay.setBackgroundResource(R.drawable.buttomlevel2);
                btnLevels.setBackgroundResource(R.drawable.buttomhin2);
                break;
            case 3:
                layout.setBackgroundResource(R.drawable.backgroundlevel3);
                btnPlay.setBackgroundResource(R.drawable.buttomlevel3);
                btnLevels.setBackgroundResource(R.drawable.buttomhin3);
                break;
            case 4:
                layout.setBackgroundResource(R.drawable.backgroundlevel4);
                btnPlay.setBackgroundResource(R.drawable.buttomlevel4);
                btnLevels.setBackgroundResource(R.drawable.buttomhin4);
                break;
            case 5:
                layout.setBackgroundResource(R.drawable.backgroundlevel5);
                btnPlay.setBackgroundResource(R.drawable.buttomlevel5);
                btnLevels.setBackgroundResource(R.drawable.buttomhin5);
                break;
        }
    }
}

/*
    public String getCurrentClass() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfo = manager.getRunningTasks(1);

        ComponentName componentInfo = runningTaskInfo.get(0).topActivity;
        String className = componentInfo.getClassName();
        return className;
    }*/

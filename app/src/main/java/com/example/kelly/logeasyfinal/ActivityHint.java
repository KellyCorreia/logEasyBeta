package com.example.kelly.logeasyfinal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kelly.logeasyfinal.modelo.Aluno;
import com.example.kelly.logeasyfinal.modelo.CursoAluno;
import com.example.kelly.logeasyfinal.persistencia.MySQLiteHelper;
import com.example.kelly.logeasyfinal.modelo.Conteudo;

import java.util.Random;


public class ActivityHint extends Activity {

    TextView txtPoints, txtHint;
    Button btnPlay;
    ImageView ImgAvatar;
    //ImageView ImgHint;
    RelativeLayout layout;
    LinearLayout firstLayout;
    Aluno aluno;
    CursoAluno score;
    Conteudo seleclevel;
    Random rd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        Bundle extras = getIntent().getExtras();
        aluno = (Aluno) extras.getParcelable("chosenUser");
        seleclevel = (Conteudo)extras.getParcelable("chosenLevel");
        score = (CursoAluno) extras.getParcelable("userScore");

        txtPoints = (TextView)findViewById(R.id.txtPoints);
        txtHint =(TextView)findViewById(R.id.txtHint);
        btnPlay=(Button)findViewById(R.id.btnPlay);
        ImgAvatar = (ImageView)findViewById(R.id.imgAvatarHint);
        //ImgHint = (ImageView)findViewById(R.id.ivwHInt);
        layout = (RelativeLayout)findViewById(R.id.layoutHint);
        firstLayout = (LinearLayout)findViewById(R.id.layoutHintMiddle);

        setHintView();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setHintView(){
        MySQLiteHelper db = new MySQLiteHelper(this);

        txtPoints.setText(Integer.toString(score.getPontuacao()));
        txtHint.setText(seleclevel.getDica());

        switch (aluno.getAvatar().getNome()){
            case "Avatar1":
                int random = rd.nextInt(4);
                if(random == 0)
                    ImgAvatar.setImageResource(R.drawable.avatar12);
                else{
                    if(random == 1)
                        ImgAvatar.setImageResource(R.drawable.avatar13);
                    else{
                        if(random == 2)
                            ImgAvatar.setImageResource(R.drawable.avatar14);
                        else{
                            ImgAvatar.setImageResource(R.drawable.avatar15);
                        }
                    }
                }
                break;

            case "Avatar2":
                random = rd.nextInt(4);
                if(random == 0)
                    ImgAvatar.setImageResource(R.drawable.avatar22);
                else{
                    if(random == 1)
                        ImgAvatar.setImageResource(R.drawable.avatar23);
                    else{
                        if(random == 2)
                            ImgAvatar.setImageResource(R.drawable.avatar24);
                        else{
                            ImgAvatar.setImageResource(R.drawable.avatar25);
                        }
                    }
                }
                break;

            case "Avatar3":
                random = rd.nextInt(4);
                if(random == 0)
                    ImgAvatar.setImageResource(R.drawable.avatar32);
                else{
                    if(random == 1)
                        ImgAvatar.setImageResource(R.drawable.avatar33);
                    else{
                        if(random == 2)
                            ImgAvatar.setImageResource(R.drawable.avatar34);
                        else{
                            ImgAvatar.setImageResource(R.drawable.avatar35);
                        }
                    }
                }
                break;

            case "Avatar4":
                random = rd.nextInt(4);
                if(random == 0)
                    ImgAvatar.setImageResource(R.drawable.avatar42);
                else{
                    if(random == 1)
                        ImgAvatar.setImageResource(R.drawable.avatar43);
                    else{
                        if(random == 2)
                            ImgAvatar.setImageResource(R.drawable.avatar44);
                        else{
                            ImgAvatar.setImageResource(R.drawable.avatar45);
                        }
                    }
                }
                break;
        }

        switch(seleclevel.getNivel().getOrdem()){
            case 1:
                layout.setBackgroundResource(R.drawable.backgroundlevel1);
                btnPlay.setBackgroundResource(R.drawable.buttomhin1);
                //ImgHint.setImageResource(R.drawable.level1hint);
                break;
            case 2:
                layout.setBackgroundResource(R.drawable.backgroundlevel2);
                btnPlay.setBackgroundResource(R.drawable.buttomhin2);
                //ImgHint.setImageResource(R.drawable.level2hint);
                break;
            case 3:
                layout.setBackgroundResource(R.drawable.backgroundlevel3);
                btnPlay.setBackgroundResource(R.drawable.buttomhin3);
                //ImgHint.setImageResource(R.drawable.level3hint);
                break;
            case 4:
                layout.setBackgroundResource(R.drawable.backgroundlevel4);
                btnPlay.setBackgroundResource(R.drawable.buttomhin4);
                //ImgHint.setImageResource(R.drawable.level4hint);
                break;
            case 5:
                layout.setBackgroundResource(R.drawable.backgroundlevel5);
                btnPlay.setBackgroundResource(R.drawable.buttomhin5);
                //ImgHint.setImageResource(R.drawable.level5hint);
                break;
        }



    }
}

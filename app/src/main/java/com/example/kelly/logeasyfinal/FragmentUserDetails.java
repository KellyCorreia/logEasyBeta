package com.example.kelly.logeasyfinal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kelly.logeasyfinal.modelo.Aluno;
import com.example.kelly.logeasyfinal.modelo.CursoAluno;
import com.example.kelly.logeasyfinal.persistencia.MySQLiteHelper;
import com.example.kelly.logeasyfinal.modelo.Conteudo;
import com.example.kelly.logeasyfinal.modelo.User;

import java.util.Locale;

public class FragmentUserDetails extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CursoAluno userScore;
        Aluno aluno;
        MySQLiteHelper db;
        Conteudo userLevel;

        ProgressBar mProgress;

        int circleAvatarV,pointsUV, questionsA;
        Double answeredWrongUV;
        String levelNameV,levelDiscripV,usernameUV,emailUV, levelUV;

        View view = inflater.inflate(R.layout.fragment_userdetails, container, false);
        aluno = getArguments().getParcelable("chosenUser");
        db = new MySQLiteHelper(getActivity());
        userScore = db.getCursoAluno(aluno);
        userLevel = db.getConteudo(userScore.getConteudo().getId());

        ImageView circleAvatar = (ImageView)view.findViewById(R.id.circleAvatar);
        TextView levelName = (TextView)view.findViewById(R.id.levelName);
        TextView levelDiscrip = (TextView)view.findViewById(R.id.levelDiscrip);
        TextView usernameU = (TextView)view.findViewById(R.id.usernameU);
        TextView emailU = (TextView)view.findViewById(R.id.emailU);
        TextView pointsU = (TextView)view.findViewById(R.id.pointsU);
        TextView levelU = (TextView)view.findViewById(R.id.levelU);
        TextView answeredWrongU = (TextView)view.findViewById(R.id.answeredWrongU);
        TextView answeredQuestions = (TextView)view.findViewById(R.id.answeredQuestionsU);

        circleAvatarV = getResources().getIdentifier(aluno.getAvatar().getNome().toLowerCase(Locale.getDefault()),"drawable",getActivity().getPackageName());
        circleAvatar.setImageResource(circleAvatarV);

        levelNameV =userLevel.getNome();
        levelName.setText(levelNameV);

        levelDiscripV = userLevel.getNome();
        levelDiscrip.setText(levelDiscripV);

        usernameUV = aluno.getUsuario().getUsername();
        usernameU.setText(usernameUV);

        emailUV= aluno.getUsuario().getEmail();
        emailU.setText(emailUV);

        pointsUV = userScore.getPontuacao();
        pointsU.setText((String.valueOf(pointsUV)));

        answeredWrongUV = userScore.getPercentualErro();
        answeredWrongU.setText((String.valueOf(answeredWrongUV)));

        levelUV = Integer.toString(userScore.getConteudo().getNivel().getOrdem());
        levelU.setText(levelUV);

        questionsA = (int) Math.floor(answeredWrongUV*100);
        answeredQuestions.setText(String.valueOf(questionsA));

        mProgress = (ProgressBar) view.findViewById(R.id.progressBarU);
        mProgress.setProgress(pointsUV);

        ImageView btn;
        TextView txtV;
        for(int i=1; i<=userScore.getConteudo().getNivel().getOrdem(); i++){
                if(i==1) {
                    txtV = (TextView) view.findViewById(R.id.txvElements);
                    txtV.setVisibility(View.VISIBLE);
                }
                btn = (ImageView) view.findViewById(getResources().getIdentifier("imageView" + String.valueOf(i), "id", getActivity().getPackageName()));
                btn.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
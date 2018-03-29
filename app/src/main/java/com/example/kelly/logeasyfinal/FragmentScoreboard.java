package com.example.kelly.logeasyfinal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kelly.logeasyfinal.modelo.Aluno;
import com.example.kelly.logeasyfinal.modelo.CursoAluno;
import com.example.kelly.logeasyfinal.persistencia.MySQLiteHelper;
import com.example.kelly.logeasyfinal.modelo.Conteudo;
import com.example.kelly.logeasyfinal.modelo.User;

import java.util.ArrayList;
import java.util.List;

public class FragmentScoreboard extends Fragment {

    private MySQLiteHelper dbHelper;
    private ArrayList<ScoreboardScreen> list;
    private ArrayList<CursoAluno> cursoAlunoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_scoreboard_, container, false);

        ListView lview = (ListView)view.findViewById(R.id.listview);
        populateList();
        listviewAdapter adapter = new listviewAdapter(getActivity(), list);
        lview.setAdapter(adapter);
        return view;
    }

    private void populateList() {

        dbHelper = new MySQLiteHelper(getActivity());
        cursoAlunoList = dbHelper.getAllCursoAlunos();

        for (int i = 0; i < cursoAlunoList.size(); i++){

            list.get(i).setLevelName(cursoAlunoList.get(i).getConteudo().getNivel().getAmbiente().getElemento());
            list.get(i).setUserName(cursoAlunoList.get(i).getAluno().getNome());
            list.get(i).setPoints(cursoAlunoList.get(i).getPontuacao());
            list.get(i).setWrongPerc(cursoAlunoList.get(i).getPercentualErro());

        }
    }
}
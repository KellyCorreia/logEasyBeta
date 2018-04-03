package com.example.kelly.logeasyfinal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kelly.logeasyfinal.modelo.Aluno;
import com.example.kelly.logeasyfinal.modelo.Curso;
import com.example.kelly.logeasyfinal.modelo.CursoAluno;
import com.example.kelly.logeasyfinal.persistencia.MySQLiteHelper;
import com.example.kelly.logeasyfinal.modelo.Conteudo;
import com.example.kelly.logeasyfinal.modelo.User;
import com.example.kelly.logeasyfinal.util.Propriedades;
import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        new HttpRequestTaskAlunosPost().execute();
        cursoAlunoList = dbHelper.getAllCursoAlunos();
        list = new ArrayList<ScoreboardScreen>();

        for (int i = 0; i < cursoAlunoList.size(); i++){

            list.add(new ScoreboardScreen());
            list.get(i).setLevelName(cursoAlunoList.get(i).getConteudo().getNivel().getAmbiente().getElemento());
            list.get(i).setUserName(cursoAlunoList.get(i).getAluno().getNome());
            list.get(i).setPoints(cursoAlunoList.get(i).getPontuacao());
            list.get(i).setWrongPerc(cursoAlunoList.get(i).getPercentualErro());

        }
    }

    //Mandar os alunos para o banco remoto
    private class HttpRequestTaskAlunosPost extends AsyncTask<Void, Void, List<Aluno>> {
        @Override
        protected List<Aluno> doInBackground(Void... params) {
            try {

                final String url = Propriedades.getUrlServico()+"aluno/addAlunos/";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                List<Aluno> alunos = dbHelper.getAllAlunosUsuarios();
                for (Aluno a : alunos){
                    a.setAlternativasAluno(dbHelper.getAlternativasAluno(a.getId()));
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setAccept(MediaType.APPLICATION_JSON);

                Gson gson = new Gson();

                //set your entity to send
                HttpEntity entity = new HttpEntity<>(alunos, headers);

                // send it!
                restTemplate.postForObject(url, gson.toJson(entity), List.class);
                //restTemplate.postForEntity(url, gson.toJson(alunos), String.class);

                //restTemplate.postForObject(url, alunosPost, Aluno.class);

                return alunos;
            } catch (Exception e) {
                Log.i("errowebservice", e.getMessage());
                System.out.print("ERRO NO WEBSERVICE: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Aluno> listaAlunos) {
            for (Aluno aluno : listaAlunos) {

                Log.i("aluno", aluno.getId() + " , " + aluno.getNome());
                Log.i("usuario", aluno.getUsuario().getId() + " , " + aluno.getUsuario().getEmail());
            }
        }
    }
}
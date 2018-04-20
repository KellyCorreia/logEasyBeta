package com.example.kelly.logeasyfinal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kelly.logeasyfinal.modelo.AlternativaAluno;
import com.example.kelly.logeasyfinal.modelo.Aluno;
import com.example.kelly.logeasyfinal.modelo.AlunoWrap;
import com.example.kelly.logeasyfinal.modelo.CursoAluno;
import com.example.kelly.logeasyfinal.persistencia.MySQLiteHelper;
import com.example.kelly.logeasyfinal.util.Propriedades;
import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
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
        new HttpRequestTaskAlunosGet().execute();
        cursoAlunoList = dbHelper.getAllCursoAlunos();
        list = new ArrayList<ScoreboardScreen>();

        for (int i = 0; i < cursoAlunoList.size(); i++){

            list.add(new ScoreboardScreen());
            if (cursoAlunoList.get(i).getPontuacao() < 50){
                list.get(i).setLevelName("Ar");
            }else {
                if (cursoAlunoList.get(i).getPontuacao() < 100){
                    list.get(i).setLevelName("Água");
                }else{
                    if (cursoAlunoList.get(i).getPontuacao() < 150)
                    list.get(i).setLevelName("Terra");
                    else{
                        if (cursoAlunoList.get(i).getPontuacao() < 200){
                            list.get(i).setLevelName("Fogo");
                        }else {
                            if (cursoAlunoList.get(i).getPontuacao() >= 200) {
                                list.get(i).setLevelName("Mestre do Mundo");
                            }
                        }
                    }

                }
            }
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

                List<Aluno> alunos = dbHelper.getAllAlunosLocais();

                for (Aluno a : alunos){
                    //a.setAlternativasAluno(new ArrayList<AlternativaAluno>());
                    a.setAlternativasAluno(dbHelper.getAlternativasAluno(a));
                }

                AlunoWrap alunoWrap = new AlunoWrap();
                alunoWrap.setAlunos(alunos);
                String json = new Gson().toJson(alunoWrap);

                // Prepare header
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.setContentType(MediaType.APPLICATION_JSON);
                // Pass the new person and header
                HttpEntity<List<Aluno>> entity = new HttpEntity(json, headers);

                Log.i("Json Object: ", entity.toString());
                // Send the request as POST
                restTemplate.postForObject(url, alunoWrap, String.class);
                //ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

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

                Log.i("aluno", aluno.getId() + " , " + aluno.getNome() + " , " + aluno.getAlternativasAluno().size());
                Log.i("usuario", aluno.getUsuario().getId() + " , " + aluno.getUsuario().getEmail());
                for (AlternativaAluno aa : aluno.getAlternativasAluno()){
                    Log.i("alternativasAluno", aa.getId() + " , " + aa.getAlternativa().getId() + " , " + aa.getAlternativa().getTexto());
                }
            }
        }
    }

    //trazer os alunos do banco remoto
    private class HttpRequestTaskAlunosGet extends AsyncTask<Void, Void, List<Aluno>> {
        @Override
        protected List<Aluno> doInBackground(Void... params) {
            try {
                final String url = Propriedades.getUrlServico()+"aluno/alunos";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                //Nivel[] nivel = restTemplate.getForObject(url, Nivel[].class);
                //ResponseEntity<Nivel[]> responseEntity = restTemplate.getForEntity(url, Nivel[].class);
                //ResponseEntity<List<Nivel>> res = restTemplate.postForEntity(url, HttpMethod.GET, new ParameterizedTypeReference<List<Nivel>>() {});
                ResponseEntity<Aluno[]> responseEntity = restTemplate.getForEntity(url, Aluno[].class);
                return Arrays.asList(responseEntity.getBody());
            } catch (Exception e) {
                Log.i("errowebservice", e.getMessage());
                System.out.print("ERRO NO WEBSERVICE: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Aluno> listaRemoto) {
            List<Aluno> alunosLocais = dbHelper.getAllAlunosLocais();
            List<Aluno> alunos = dbHelper.getAllAlunos();
            boolean existe_no_banco = false;
            boolean is_local = false;

            for (Aluno remoto: listaRemoto) {
                for (Aluno aluno: alunos){
                    if (remoto.getUsuario() != null && remoto.getUsuario().getEmail().equals(aluno.getUsuario().getEmail())){
                        existe_no_banco = true;
                        remoto.setId(aluno.getId());
                    }
                }
                for (Aluno local: alunosLocais){
                    if (remoto.getUsuario() != null && remoto.getUsuario().getEmail().equals(local.getUsuario().getEmail())){
                        is_local = true;
                    }
                }
                if (!is_local){
                    if (!existe_no_banco){
                        //add Aluno + Alternativas_Aluno
                        remoto.setId(dbHelper.addAlunoRemoto(remoto).getId());
                        Log.i("aluno-remoto-insert", remoto.getId() + " , " + remoto.getNome());
                        for (AlternativaAluno aa: remoto.getAlternativasAluno()){
                            aa.setAluno(remoto);
                            dbHelper.addAlternativaAluno(aa);
                            Log.i("alter-aluno-insert", aa.getId()  + " , " + aa.getAlternativa().getId());
                        }
                    }else {
                        //remove as alt_alunos e add de novo
                        dbHelper.deletarAlternativasAluno(remoto);
                        for (AlternativaAluno aa: remoto.getAlternativasAluno()) {
                            aa.setAluno(remoto);
                            dbHelper.addAlternativaAluno(aa);
                            Log.i("alter-aluno-insert", remoto.getId() + " , " + aa.getId()  + " , " + aa.getAlternativa().getId());
                        }


                    }
                }

                //db.addCursoPreSelecao(curso);

                //Log.i("disciplina-inserida", curso.getDisciplina().getId() + " , " + curso.getDisciplina().getCodigo());
                //Log.i("curso-inserid", curso.getId() + " , " + curso.getCodigo());
            }
            //new HttpRequestTaskAlunosPost().execute();
        }
    }

}
package com.example.kelly.logeasyfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.kelly.logeasyfinal.modelo.Alternativa;
import com.example.kelly.logeasyfinal.modelo.AlternativaAluno;
import com.example.kelly.logeasyfinal.modelo.Aluno;
import com.example.kelly.logeasyfinal.modelo.AmbienteAvatar;
import com.example.kelly.logeasyfinal.modelo.Conteudo;
import com.example.kelly.logeasyfinal.modelo.Nivel;
import com.example.kelly.logeasyfinal.modelo.Questao;
import com.example.kelly.logeasyfinal.persistencia.MySQLiteHelper;
import com.example.kelly.logeasyfinal.modelo.Curso;
import com.example.kelly.logeasyfinal.util.Propriedades;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class ActivityChoose_Curso extends Activity {
    private ImageButton btncontinuar;
    private GridView grid;
    private List<String> web = new ArrayList<String>();
    private List<Integer> imageId = new ArrayList<Integer>();
    private List<Curso> cursos = new ArrayList<Curso>();
    private Curso cursoSelecionado;
    private Aluno aluno;
    private  MySQLiteHelper db = null;

    public void addContentGrid(){

        cursos = db.getAllCursosPreSelecao();

        if(cursos.size()!=0) {
            for (int i = 0; i < cursos.size(); i++) {
                imageId.add(i, R.drawable.level1s);
                web.add(i, cursos.get(i).getNome());
            }
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__curso);

        db = new MySQLiteHelper(this);
        new HttpRequestTaskCursos().execute();

        //Getting the object aluno from the previous screen
        Bundle extras = getIntent().getExtras();
        aluno = extras.getParcelable("chosenUser");
        final int i = extras.getInt("toast");

        addContentGrid();
        //Log.d("Insert: ", "values from db" + web[0] + "  " + web[1]);
       // Toast.makeText(ActivityChoose_Player.this, "Teste quantidade " + web.get(0) + "  " +web.get(1), Toast.LENGTH_SHORT).show();

        CustomGrid gridAdapter = new CustomGrid(ActivityChoose_Curso.this, web, imageId);
        grid=(GridView)findViewById(R.id.gridcursos);
        grid.setAdapter(gridAdapter);

        /*for (int i = 0; i < users.size(); i++) {
            Toast.makeText(ActivityChoose_Player.this, " " + users.get(i).getUsername() + " , " + users.get(i).getAvatar(), Toast.LENGTH_LONG).show();
        }*/

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ActivityChoose_Player.this, "Voce clicou em " + web.get(position), Toast.LENGTH_SHORT).show();
                //Remover o curso que estiver no banco e add o novo.
                cursoSelecionado = cursos.get(position);
                new HttpRequestTaskCursoById().execute();

                Intent intent = new Intent(ActivityChoose_Curso.this, ActivityLevels.class);
                intent.putExtra("chosenUser", (Parcelable) aluno);
                intent.putExtra("toast", i);
                intent.putExtra("chosenCurso", (Parcelable) cursos.get(position));
                //intent.putExtra(users.get(position).getUsername(), users.get(position).getAvatar());//now I have to get the data in log in
                startActivity(intent);
                finish();
            }
        });

        btncontinuar = (ImageButton) findViewById(R.id.btncontinuar);
        btncontinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.getAllCursos();
                cursoSelecionado = cursos.get(0);
                Intent intent = new Intent(ActivityChoose_Curso.this, ActivityLevels.class);
                intent.putExtra("chosenUser", (Parcelable) aluno);
                intent.putExtra("chosenCurso", (Parcelable) cursoSelecionado);
                intent.putExtra("toast", i);
                startActivity(intent);
                finish();
            }
        });
        if(cursos.isEmpty()){
            grid.setVisibility(View.INVISIBLE);
            LinearLayout layoutBackground =(LinearLayout)findViewById(R.id.layoutGridcursos);
            layoutBackground.setBackgroundResource(R.drawable.nouser);
        }
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

    //Buscar todos os cursos do banco remoto
    private class HttpRequestTaskCursos extends AsyncTask<Void, Void, List<Curso>> {
        @Override
        protected List<Curso> doInBackground(Void... params) {
            try {
                final String url = Propriedades.getUrlServico()+"curso/cursos";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                //Nivel[] nivel = restTemplate.getForObject(url, Nivel[].class);
                //ResponseEntity<Nivel[]> responseEntity = restTemplate.getForEntity(url, Nivel[].class);
                //ResponseEntity<List<Nivel>> res = restTemplate.postForEntity(url, HttpMethod.GET, new ParameterizedTypeReference<List<Nivel>>() {});
                ResponseEntity<Curso[]> responseEntity = restTemplate.getForEntity(url, Curso[].class);
                return Arrays.asList(responseEntity.getBody());
            } catch (Exception e) {
                Log.i("errowebservice", e.getMessage());
                System.out.print("ERRO NO WEBSERVICE: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Curso> listaCursos) {
            db.deletarCursosPreSelecao();

            for (Curso curso: listaCursos) {

                db.addCursoPreSelecao(curso);

                Log.i("disciplina-inserida", curso.getDisciplina().getId() + " , " + curso.getDisciplina().getCodigo());
                Log.i("curso-inserid", curso.getId() + " , " + curso.getCodigo());
            }
        }
    }

    /*Buscar o curso selecionado e todos os seus artefatos*/
    private class HttpRequestTaskCursoById extends AsyncTask<Void, Void, Curso> {
        @Override
        protected Curso doInBackground(Void... params) {
            try {
                final String url = Propriedades.getUrlServico()+"curso/"+cursoSelecionado.getId()+"/";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Curso curso = restTemplate.getForObject(url, Curso.class);
                return curso;
            } catch (Exception e) {
                Log.i("errowebservice", e.getMessage());
                System.out.print("ERRO NO WEBSERVICE: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Curso curso) {
            HashMap<Integer, Aluno> alunos = new HashMap<Integer, Aluno>();
            HashMap<Integer, AlternativaAluno> altAlunos = new HashMap<Integer, AlternativaAluno>();
            db.deletarCursos();
            db.addDisciplina(curso.getDisciplina());
            db.addCurso(curso);
            for (Conteudo conteudo : curso.getConteudos()){
                conteudo.setCurso(curso);
                db.addConteudo(conteudo);
                for (Questao questao :  conteudo.getQuestoes()){
                    questao.setConteudo(conteudo);
                    db.addQuestao(questao);
                    for (Alternativa alternativa : questao.getAlternativas()) {
                        alternativa.setQuestao(questao);
                        db.addAlternativa(alternativa);
                        for (AlternativaAluno altAluno :  alternativa.getAlternativaAlunos()){
                            altAluno.setAlternativa(alternativa);
                            altAlunos.put(altAluno.getId(), altAluno);
                            alunos.put(altAluno.getAluno().getId(), altAluno.getAluno());
                        }
                    }
                }
            }
            /*Set<Integer> idAlunos = alunos.keySet();
            for(Integer id : idAlunos){
                db.addAlunoSemUser(alunos.get(id));
            }
            Set<Integer> idAltAlunos = altAlunos.keySet();
            for (Integer id : idAltAlunos){
                db.addAlternativaAluno(altAlunos.get(id));
            }*/
            Log.i("cursoname","CURSO==>" + curso.getId()+", "+ curso.getNome());
            Log.i("conteudo","CONTEUDOS==>" + curso.getConteudos());
            Log.i("disciplina","DISCIPLINA==>" + curso.getDisciplina());
        }

    }
}

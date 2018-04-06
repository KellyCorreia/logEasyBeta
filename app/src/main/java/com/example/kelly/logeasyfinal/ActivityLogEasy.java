package com.example.kelly.logeasyfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.kelly.logeasyfinal.modelo.Curso;
import com.example.kelly.logeasyfinal.util.Propriedades;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

public class ActivityLogEasy extends Activity {
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_easy);

        ImageButton btnStart;
        ImageButton btnInfo;


        btnStart = (ImageButton)findViewById(R.id.btnStart);
        btnInfo  = (ImageButton)findViewById(R.id.btnInfo);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ActivityLogEasy.this,ActivityChoose_Player.class);
                startActivity(intent);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogEasy.this, ActivityInfo.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Curso> {
        @Override
        protected Curso doInBackground(Void... params) {
            try {
                final String url = Propriedades.getUrlServico()+"curso/2/";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Curso curso = restTemplate.getForObject(url, Curso.class);
                Log.i("curso", "CURSOOOO");
                return curso;
            } catch (Exception e) {
                Log.i("errowebservice", e.getMessage());
                System.out.print("ERRO NO WEBSERVICE: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Curso curso) {
            Log.i("cursoname","CURSO==>" + curso.getId()+", "+ curso.getNome());
            Log.i("conteudo","CONTEUDOS==>" + curso.getConteudos());
            Log.i("disciplina","DISCIPLINA==>" + curso.getDisciplina());
        }

    }
}

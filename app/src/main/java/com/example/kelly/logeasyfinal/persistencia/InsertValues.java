package com.example.kelly.logeasyfinal.persistencia;


import android.os.AsyncTask;
import android.util.Log;

import com.example.kelly.logeasyfinal.modelo.Ambiente;
import com.example.kelly.logeasyfinal.modelo.AmbienteAvatar;
import com.example.kelly.logeasyfinal.modelo.Avatar;
import com.example.kelly.logeasyfinal.modelo.Nivel;
import com.example.kelly.logeasyfinal.util.Propriedades;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class InsertValues {

    private MySQLiteHelper db = null;

    public InsertValues(MySQLiteHelper database){
        db = database;
    }

    public void insertAllTabelasFixas(){
        new HttpRequestTaskAvatars().execute();
        new HttpRequestTaskNiveis().execute();
    }

    /*Buscando os Avatars*/
    private class HttpRequestTaskAvatars extends AsyncTask<Void, Void, List<Avatar>> {
        @Override
        protected List<Avatar> doInBackground(Void... params) {
            try {
                final String url = Propriedades.getUrlServico()+"curso/avatars";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<Avatar[]> responseEntity = restTemplate.getForEntity(url, Avatar[].class);
                return Arrays.asList(responseEntity.getBody());
            } catch (Exception e) {
                Log.i("errowebservice", e.getMessage());
                System.out.print("ERRO NO WEBSERVICE: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Avatar> avatars) {

            for (Avatar av: avatars) {
                Log.i("avatar", "Avatar==>" + av.getId() + " , " + av.getCaracteristica() + " , " + av.getNome());
                db.addAvatar(av);
            }
            //teste
            Log.i("avatarInserido", "Avatar==>" +  db.getAvatar(3).getNome() + " , " +  db.getAvatar(3).getCaracteristica());
        }
    }

    /*Buscando os níveis*/
    private class HttpRequestTaskNiveis extends AsyncTask<Void, Void, List<Nivel>> {
        @Override
        protected List<Nivel> doInBackground(Void... params) {
            try {
                final String url = Propriedades.getUrlServico()+"curso/niveis";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                //Nivel[] nivel = restTemplate.getForObject(url, Nivel[].class);
                //ResponseEntity<Nivel[]> responseEntity = restTemplate.getForEntity(url, Nivel[].class);
                //ResponseEntity<List<Nivel>> res = restTemplate.postForEntity(url, HttpMethod.GET, new ParameterizedTypeReference<List<Nivel>>() {});
                ResponseEntity<Nivel[]> responseEntity = restTemplate.getForEntity(url, Nivel[].class);
                return Arrays.asList(responseEntity.getBody());
            } catch (Exception e) {
                Log.i("errowebservice", e.getMessage());
                System.out.print("ERRO NO WEBSERVICE: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Nivel> niveis) {

            for (Nivel nivel: niveis) {
                //Log.i("avatarNome","Avatar==>" + nivel.getId()+", "+ nivel.getOrdem());
                //Log.i("avatar","Ambientes==>" + nivel.getAmbiente().getDescricao());
                db.addAmbiente(nivel.getAmbiente());
                db.addNivel(nivel);
                if (nivel.getAmbiente() != null && nivel.getAmbiente().getAmbientesAvatar() != null) {
                    for (AmbienteAvatar av : nivel.getAmbiente().getAmbientesAvatar()) {

                        db.addAmbienteAvatar(av);

                        //Log.i("avatar", "Avatar==>" + av.getId() + " , " + av.getFalaInicialNivel() + "idAvatar" + av.getAvatar().getId() + "idAmbiente" + av.getAmbiente().getId());
                        //Log.i("avatarCarac", "Avatar==>" + av.getAvatar().getId() + " , " + av.getAvatar().getNome() + " , " + av.getAvatar().getCaracteristica());
                    }
                }
            }
            //teste para ver se está salvando
            Nivel nv = db.getNivel(8);
            Log.i("Nivel ", "desc " + nv.getDescricao() + "Ambiente: " + nv.getAmbiente().getElemento());
            nv = db.getNivel(10);
            Log.i("Nivel ", "desc " + nv.getDescricao() + "Ambiente: " + nv.getAmbiente().getElemento());
        }
    }

    /*private void addQuestions() {

       //These are the questions from the first version of the app
        Questao q1 = new Questao("Q001", "Which one is the contradictory of the " +
                "following claim: \n “Sometimes the wind is blowing hard.”", 1);
        db.addQuestion(q1);

        Questao q2 = new Questao("Q002", "Which one is the contradictory of the " +
                "following statement: \n “The wind is blowing all the time.”", 1);
        db.addQuestion(q2);

        Questao q3 = new Questao("Q003", "Which one of the following sentences becomes " +
                "true after applying the NOT operator.", 1);
        db.addQuestion(q3);

        Questao q4 = new Questao("Q004", "Which one of the following affirmation becomes " +
                "true after applying the NOT operator.", 1);
        db.addQuestion(q4);

        Questao q5 = new Questao("Q005", "Which one of the following affirmation becomes " +
                "true after applying the NOT operator.", 1);
        db.addQuestion(q5);

    }

    private void addAnswers() {

        //These are the answers for the first version of logeasy
        Alternativa a1 = new Alternativa("A001a", "The wind is not blowing hard.", "Q001", 0);
        db.addAnswer(a1);

        Alternativa a2 = new Alternativa("A001b", "Sometimes the wind is not blowing hard.", "Q001", 0);
        db.addAnswer(a2);

        Alternativa a3 = new Alternativa("A001c", "The wind is never blowing hard.", "Q001", 1);
        db.addAnswer(a3);

        Alternativa a4 = new Alternativa("A002a", "The wind is not blowing.", "Q002", 0);
        db.addAnswer(a4);

        Alternativa a5 = new Alternativa("A002b", "Sometimes the wind is not blowing.", "Q002", 0);
        db.addAnswer(a5);

        Alternativa a6 = new Alternativa("A002c", "The wind is never blowing.", "Q002", 0);
        db.addAnswer(a6);

        Alternativa a7 = new Alternativa("A003a", "The wind can change directions.", "Q003", 0);
        db.addAnswer(a7);

        Alternativa a8 = new Alternativa("A003b", "The wind is blowing in the same direction.", "Q003", 0);
        db.addAnswer(a8);

        Alternativa a9 = new Alternativa("A003c", "The wind can never change directions.", "Q003", 1);
        db.addAnswer(a9);

        Alternativa a10 = new Alternativa("A004a", "The wind has the power to destroy houses", "Q004", 0);
        db.addAnswer(a10);

        Alternativa a11 = new Alternativa("A004b", "The wind can sometimes destroy houses.", "Q004", 1);
        db.addAnswer(a11);

        Alternativa a12 = new Alternativa("A004c", "The wind can destroy houses.", "Q004", 0);
        db.addAnswer(a12);

        Alternativa a13 = new Alternativa("A005a", "The wind can blow hard.", "Q005", 0);
        db.addAnswer(a13);

        Alternativa a14 = new Alternativa("A005b", "The wind blows hard all the time.", "Q005", 1);
        db.addAnswer(a14);

        Alternativa a15 = new Alternativa("A005c", "The wind is not blowing hard.", "Q005", 0);
        db.addAnswer(a15);

    }

    private void addCursos(){
        Curso c1 = new Curso(1, "Curso de Logica", "Curso de logica proposicional");
        db.addCurso(c1);
    }

    private void addAvatar(){
        Avatar av = new Avatar("avatar1");
        db.addCurso(c1);
    }

    private void addLevels() {

        //This is the insert for test purposes on the summer project
        Conteudo l1 = new Conteudo(1,"Ar", "<p><b>Hi, Alchemist!</b></p> <p>Let’s master the" +
                " four basic nature elements and become a hero!</p> | <p>So this is the first level" +
                " of your power conquest. On this level you are going to learn the concept of " +
                "propositional logic. In the end of this level you will have the wind power which" +
                " is the first step to get the air power and to achieve that you need to prove " +
                "your knowledge about wind by answering 5 questions.</p> | <p>So lets get started " +
                "with the concepts:</p> <p>A concept identifies a certain class of things; a " +
                "proposition asserts something about that class. Concepts give us indispensable " +
                "tools for thought and speech by grouping together similar objects, actions, " +
                "properties, and relationships.| However, a concept by itself is not a complete " +
                "thought, and a word by itself doesn't say anything.</p> <p>Words need to be put " +
                "together into sentences that express propositions. One essential feature of a " +
                "proposition is that it is either true or false.</p> | <p>To assert a proposition, " +
                "we need a complete declarative sentence, with a subject and a predicate.</p> |" +
                "<p> Example:</p> <p><b>The following are propositions:</b></p> <p>The wind is the" +
                " flow of gases.</p> <p>The wind blows south in the winter.</p> <p>The wind is " +
                "blowing now.</p> | <p><b>The following are fragments:</b></p> <p> Wind </p> <p> " +
                "The wind from Ireland </p> <p> What a cold wind!</p>", "Tip1", 1);
        db.addLevel(l1);

        Conteudo l2 = new Conteudo(2,"Água", "<p>Hello! This is Level 2, the first step you have to go through" +
                " to get the <b>Water</b> power. On this level you are going to learn about the <b>contradictory of a disjunction</b>" +
                " and to complete the level 5 you have to prove your knowledge about <b>sand</b> by answering 5 questions right. </p>" +
                "<p>So, lets get started with the concepts:</p>" +
                "<p>Remember that the Contradictory of A is a claim that always has the opposite truth value of A.</p>" +
                "<p>And also remember that the conjunction is the compound claim that has the operator “OR” present. In order " +
                "to write the contradictory of a conjunction, you need to know the following rule:</p>" +
                "<p>not(A or B) = notA and notB</p>", "Tip 5", 1);
        db.addLevel(l2);


        Conteudo l3 = new Conteudo(3,"Terra", "<p>Hello! This is Level 3, the last step you have to go through" +
                " to get the <b>Earth</b> power. On this level you are going to learn about the <b>contradictory of a conjunction" +
                "</b> and to complete the level 4 you have to prove your knowledge about Sand by answering 5 questions right. </p>" +
                "<p>So, lets get started with the concepts:</p>" +
                "<p>The <b>Contradictory</b> of A is a claim that <b>always</b> has the <b>opposite</b> truth value of A.</p>" +
                "<p>Remember the conjunction is the compound claim that has the operator “OR” present. In order to write the " +
                "contradictory of a conjunction, you need to know the following rule:<p>" +
                "<p>not(A and B) = notA or notB</p>", "Tip 4", 1);
        db.addLevel(l3);

        Conteudo l4 = new Conteudo(4,"Fogo", "<p>Hello! This is Level 8, the last step you have to go through to get the " +
                "<b>Fire</b> Power. On this level you are going to learn about the <b>equivalence</b> and, in order to complete this level, you have" +
                " to prove your knowledge about <b>Lava</b> by answering 5 questions right.</p>" + "<p>So, lets get started with the concepts:</p>" +
                "<p>Only If rule: A only if B = if A then B </p>" + "<p>Biconditional rule (conjoining two conditionals):</p>" +
                "<p>A if and only if B = (A if B) and (A only if B)</p>" + "<p>written as: A if and only if B = A iff B = A=B</p>" +
                "<p>or (if B then A) and (if A then B)</p>" + "<p>In this case, the claims A and B always have the same truth value: " +
                "if A is true then B is true and vice versa. We symbolize the logical equivalence of statement p and q by p ≡ q ( p <--> q ).</p>", "Tip 8", 1);
        db.addLevel(l4);

        Conteudo l5 = new Conteudo(5, "World Master", "<p><b>Congratulation!</b> This is Level 5, the last step you have to go through in order to become " +
                "a <b>The World Master</b> On this level you are going to learn about <b>inference</b> and, in order to complete this level, you have to save the world by answering" +
                " 5 questions right.</p>" + "<p>So, lets get started with the concepts:</p>" + "<p>The rule: true statements stand as premises and they can derive in conclusions" +
                " (respecting the rules learned by now and the semantics). </p>" + "<p>A→B</p>" + "<p>A/B</p>", "Tip 10", 1);
        db.addLevel(l5);
    }*/
}

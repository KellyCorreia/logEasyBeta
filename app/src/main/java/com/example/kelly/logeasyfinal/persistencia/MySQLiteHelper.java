package com.example.kelly.logeasyfinal.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kelly.logeasyfinal.modelo.Alternativa;
import com.example.kelly.logeasyfinal.modelo.AlternativaAluno;
import com.example.kelly.logeasyfinal.modelo.Aluno;
import com.example.kelly.logeasyfinal.modelo.Ambiente;
import com.example.kelly.logeasyfinal.modelo.AmbienteAvatar;
import com.example.kelly.logeasyfinal.modelo.Avatar;
import com.example.kelly.logeasyfinal.modelo.Curso;
import com.example.kelly.logeasyfinal.modelo.Conteudo;
import com.example.kelly.logeasyfinal.modelo.CursoAluno;
import com.example.kelly.logeasyfinal.modelo.Disciplina;
import com.example.kelly.logeasyfinal.modelo.Nivel;
import com.example.kelly.logeasyfinal.modelo.Questao;
import com.example.kelly.logeasyfinal.modelo.User;
import com.example.kelly.logeasyfinal.util.Propriedades;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class MySQLiteHelper extends SQLiteOpenHelper {

    //Declaration of Table USERS
    public static final String TABLE_USERS = "table_users";
    public static final String COLUMN_USER_ID = "u_id";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASS = "password";
    private static final String USERS_DATABASE_CREATE = "CREATE TABLE "
            + TABLE_USERS + "(" + COLUMN_USER_ID
            + " integer primary key AUTOINCREMENT, " + COLUMN_USER_USERNAME
            + " text not null, " + COLUMN_USER_EMAIL
            + " text not null, " + COLUMN_USER_PASS
            + " text not null);";

    //Declaration of Table AVATAR
    public static final String TABLE_AVATAR = "table_avatar";
    public static final String COLUMN_AVATAR_ID = "av_id";
    public static final String COLUMN_AVATAR_NOME = "av_nome";
    public static final String COLUMN_AVATAR_CARACT = "av_caracteristica";
    private static final String AVATAR_DATABASE_CREATE = "create table "
            + TABLE_AVATAR + "(" + COLUMN_AVATAR_ID
            + " integer primary key, " + COLUMN_AVATAR_NOME
            + " text not null, " + COLUMN_AVATAR_CARACT
            + " text);";

    //Declaration of Table ALUNO
    public static final String TABLE_ALUNO = "table_aluno";
    public static final String COLUMN_ALUNO_ID = "al_id";
    public static final String COLUMN_ALUNO_CODIGO = "al_codigo";
    public static final String COLUMN_ALUNO_NOME = "al_nome";
    private static final String ALUNO_DATABASE_CREATE = "create table "
            + TABLE_ALUNO + "(" + COLUMN_ALUNO_ID
            + " integer primary key, " + COLUMN_ALUNO_CODIGO
            + " text not null, " + COLUMN_ALUNO_NOME
            + " text not null, " + COLUMN_AVATAR_ID
            + " integer not null, " + COLUMN_USER_ID
            + " integer);";

    //Declaration of Table Ambiente
    public static final String TABLE_AMBIENTE = "table_ambiente";
    public static final String COLUMN_AMBIENTE_ID = "am_id";
    public static final String COLUMN_AMBIENTE_DESCRICAO = "am_descricao";
    public static final String COLUMN_AMBIENTE_ELEMENTO = "am_elemento";
    public static final String COLUMN_AMBIENTE_OBJETIVO = "am_objetivo";
    private static final String AMBIENTE_DATABASE_CREATE = "create table "
            + TABLE_AMBIENTE + "(" + COLUMN_AMBIENTE_ID
            + " integer primary key, " + COLUMN_AMBIENTE_DESCRICAO
            + " text, " + COLUMN_AMBIENTE_ELEMENTO
            + " text not null, " + COLUMN_AMBIENTE_OBJETIVO
            + " text not null);";

    //Declaration of Table AmbienteAvatar
    public static final String TABLE_AMBIENTE_AVATAR = "table_ambiente_avatar";
    public static final String COLUMN_AMBIENTE_AVATAR_ID = "am_av_id";
    public static final String COLUMN_AMBIENTE_AVATAR_FALA = "am_av_fala";
    private static final String AMBIENTE_AVATAR_DATABASE_CREATE = "create table "
            + TABLE_AMBIENTE_AVATAR + "(" + COLUMN_AMBIENTE_AVATAR_ID
            + " integer primary key, " + COLUMN_AMBIENTE_AVATAR_FALA
            + " text not null, " + COLUMN_AMBIENTE_ID
            + " integer not null, " + COLUMN_AVATAR_ID
            + " integer not null);";

    //Declaration of Table NIVEL
    public static final String TABLE_NIVEL = "table_nivel";
    public static final String COLUMN_NIVEL_ID = "n_id";
    public static final String COLUMN_NIVEL_ORDEM = "n_ordem";
    public static final String COLUMN_NIVEL_DESCRICAO = "n_descricao";
    public static final String COLUMN_NIVEL_PTS_DEFAULT = "n_pts_quest_default";
    public static final String COLUMN_NIVEL_PTS_MIN = "n_pts_minimo";
    public static final String COLUMN_NIVEL_PTS_MAX = "n_pts_maximo";
    private static final String NIVEL_DATABASE_CREATE = "CREATE TABLE "
            + TABLE_NIVEL + "(" + COLUMN_NIVEL_ID
            + " integer primary key, " + COLUMN_NIVEL_ORDEM
            + " integer not null, " + COLUMN_NIVEL_DESCRICAO
            + " text, " + COLUMN_NIVEL_PTS_DEFAULT
            + " integer, " + COLUMN_NIVEL_PTS_MIN
            + " integer not null, " + COLUMN_NIVEL_PTS_MAX
            + " integer not null, " + COLUMN_AMBIENTE_ID
            + " integer not null);";

    //Declaration of Table DISCIPLINA
    public static final String TABLE_DISCIPLINA = "table_disciplina";
    public static final String COLUMN_DISCIPLINA_ID = "d_id";
    public static final String COLUMN_DISCIPLINA_CODIGO = "d_codigo";
    public static final String COLUMN_DISCIPLINA_NOME = "d_nome";
    public static final String COLUMN_DISCIPLINA_DESCRICAO = "d_descricao";
    private static final String DISCIPLINA_DATABASE_CREATE = "create table "
            + TABLE_DISCIPLINA + "(" + COLUMN_DISCIPLINA_ID
            + " integer primary key, " + COLUMN_DISCIPLINA_CODIGO
            + " text not null, " + COLUMN_DISCIPLINA_NOME
            + " text not null, " + COLUMN_DISCIPLINA_DESCRICAO
            + " text);";

    //Declaration of Table CURSO
    public static final String TABLE_CURSO = "table_curso";
    public static final String COLUMN_CURSO_ID = "c_id";
    public static final String COLUMN_CURSO_CODIGO = "c_codigo";
    public static final String COLUMN_CURSO_NAME = "c_name";
    public static final String COLUMN_CURSO_DESCRICAO = "c_descricao";
    private static final String CURSO_DATABASE_CREATE = "CREATE TABLE "
            + TABLE_CURSO + "(" + COLUMN_CURSO_ID
            + " integer primary key, " + COLUMN_CURSO_CODIGO
            + " text not null, " + COLUMN_CURSO_NAME
            + " text not null, " + COLUMN_CURSO_DESCRICAO
            + " text, " + COLUMN_DISCIPLINA_ID
            + " integer not null);";

    //Tabela de cursos em pré-seleção
    public static final String TABLE_CURSO_PRE = "table_curso_pre";
    public static final String COLUMN_CURSO_ID_PRE = "c_id_pre";
    public static final String COLUMN_CURSO_CODIGO_PRE = "c_codigo_pre";
    public static final String COLUMN_CURSO_NAME_PRE = "c_name_pre";
    public static final String COLUMN_CURSO_DESCRICAO_PRE = "c_descricao_pre";
    private static final String CURSO_DATABASE_CREATE_PRE = "CREATE TABLE "
            + TABLE_CURSO_PRE + "(" + COLUMN_CURSO_ID_PRE
            + " integer primary key, " + COLUMN_CURSO_CODIGO_PRE
            + " text not null, " + COLUMN_CURSO_NAME_PRE
            + " text not null, " + COLUMN_CURSO_DESCRICAO_PRE
            + " text);";

    //Declaration of Table CONTEUDO
    public static final String TABLE_CONTEUDO = "table_conteudo";
    public static final String COLUMN_CONTEUDO_ID = "co_id";
    public static final String COLUMN_CONTEUDO_DICA = "co_dica";
    public static final String COLUMN_CONTEUDO_LICAO = "co_licao";
    public static final String COLUMN_CONTEUDO_NOME = "co_nome";
    private static final String CONTEUDO_DATABASE_CREATE = "CREATE TABLE "
            + TABLE_CONTEUDO + "(" + COLUMN_CONTEUDO_ID
            + " integer primary key, " + COLUMN_CONTEUDO_DICA
            + " text not null, " + COLUMN_CONTEUDO_LICAO
            + " text not null, " + COLUMN_CONTEUDO_NOME
            + " text not null, " + COLUMN_CURSO_ID
            + " integer not null, " + COLUMN_NIVEL_ID
            + " integer not null);";

    //Declaration of Table QUESTAO
    public static final String TABLE_QUESTAO = "table_questao";
    public static final String COLUMN_QUESTAO_ID = "q_id";
    public static final String COLUMN_QUESTAO_ENUNCIADO = "q_enunciado";
    public static final String COLUMN_QUESTAO_PTS = "q_PTS";
    private static final String QUESTAO_DATABASE_CREATE = "create table "
            + TABLE_QUESTAO + "(" + COLUMN_QUESTAO_ID
            + " integer primary key, " + COLUMN_QUESTAO_ENUNCIADO
            + " text not null, " + COLUMN_QUESTAO_PTS
            + " integer not null, " + COLUMN_CONTEUDO_ID
            + " integer not null);";


    //Declaration of Table ALTERNATIVA
    public static final String TABLE_ALTERNATIVA = "table_alternativa";
    public static final String COLUMN_ALTERNATIVA_ID = "a_id";
    public static final String COLUMN_ALTERNATIVA_TEXTO = "a_texto";
    public static final String COLUMN_ALTERNATIVA_VALOR = "a_valor";
    private static final String ALTERNATIVA_DATABASE_CREATE = "CREATE TABLE "
            + TABLE_ALTERNATIVA + "(" + COLUMN_ALTERNATIVA_ID
            + " integer primary key, " + COLUMN_ALTERNATIVA_TEXTO
            + " text not null, " + COLUMN_ALTERNATIVA_VALOR
            + " integer not null," + COLUMN_QUESTAO_ID
            + " integer not null);";

    //Declaration of Table ALTERNATIVA_ALUNO
    public static final String TABLE_ALTERNATIVA_ALUNO = "table_alternativa_aluno";
    public static final String COLUMN_ALTERNATIVA_ALUNO_ID = "a_a_id";
    private static final String ALTERNATIVA_ALUNO_DATABASE_CREATE = "CREATE TABLE "
            + TABLE_ALTERNATIVA_ALUNO + "(" + COLUMN_ALTERNATIVA_ALUNO_ID
            + " integer primary key, " + COLUMN_ALTERNATIVA_ID
            + " integer not null, " + COLUMN_ALUNO_ID
            + " integer not null);";

    private static final String DATABASE_NAME = Propriedades.getNomeBancoSQLite();
    private static final int DATABASE_VERSION = 2;
    //OR:     private SQLiteDatabase dbase;

    public SQLiteDatabase database = this.getWritableDatabase();

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        database = db;

        db.execSQL(USERS_DATABASE_CREATE);
        db.execSQL(AVATAR_DATABASE_CREATE);
        db.execSQL(ALUNO_DATABASE_CREATE);
        db.execSQL(AMBIENTE_DATABASE_CREATE);
        db.execSQL(AMBIENTE_AVATAR_DATABASE_CREATE);
        db.execSQL(NIVEL_DATABASE_CREATE);
        db.execSQL(DISCIPLINA_DATABASE_CREATE);
        db.execSQL(CURSO_DATABASE_CREATE);
        db.execSQL(CONTEUDO_DATABASE_CREATE);
        db.execSQL(QUESTAO_DATABASE_CREATE);
        db.execSQL(ALTERNATIVA_DATABASE_CREATE);
        db.execSQL(ALTERNATIVA_ALUNO_DATABASE_CREATE);
        db.execSQL(CURSO_DATABASE_CREATE_PRE);

        InsertValues data = new InsertValues(this);
        data.insertAllTabelasFixas();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        //onCreate(db);
    }

    /*
     All methods to add values to the database.
                                               */

    public void addAvatar(Avatar av) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_AVATAR_ID, av.getId());
        values.put(COLUMN_AVATAR_NOME, av.getNome());
        values.put(COLUMN_AVATAR_CARACT, av.getCaracteristica());

        database.insert(TABLE_AVATAR, null, values);
    }

    public void addAmbiente(Ambiente am) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_AMBIENTE_ID, am.getId());
        values.put(COLUMN_AMBIENTE_DESCRICAO, am.getDescricao());
        values.put(COLUMN_AMBIENTE_ELEMENTO, am.getElemento());
        values.put(COLUMN_AMBIENTE_OBJETIVO, am.getObjetivo());

        database.insert(TABLE_AMBIENTE, null, values);
    }

    public void addAmbienteAvatar(AmbienteAvatar am_av) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_AMBIENTE_AVATAR_ID, am_av.getId());
        values.put(COLUMN_AMBIENTE_AVATAR_FALA, am_av.getFalaInicialNivel());
        values.put(COLUMN_AMBIENTE_ID, am_av.getAmbiente().getId());
        values.put(COLUMN_AVATAR_ID, am_av.getAvatar().getId());

        database.insert(TABLE_AMBIENTE_AVATAR, null, values);
    }

    public void addNivel(Nivel n) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NIVEL_ID, n.getId());
        values.put(COLUMN_NIVEL_ORDEM, n.getOrdem());
        values.put(COLUMN_NIVEL_DESCRICAO, n.getDescricao());
        values.put(COLUMN_NIVEL_PTS_DEFAULT, n.getPontosQuestaoDefault());
        values.put(COLUMN_NIVEL_PTS_MIN, n.getQtdPontosInicial());
        values.put(COLUMN_NIVEL_PTS_MAX, n.getQtdPontosFinal());
        values.put(COLUMN_AMBIENTE_ID, n.getAmbiente().getId());

        database.insert(TABLE_NIVEL, null, values);
    }

    //Add user tornou-se add Aluno
    public Aluno addAluno(Aluno aluno) {
        Integer idAluno = 0;
        Aluno alunoNew = aluno;
        User user = aluno.getUsuario();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_USERNAME, aluno.getUsuario().getUsername());
        values.put(COLUMN_USER_EMAIL, aluno.getUsuario().getEmail());
        values.put(COLUMN_USER_PASS, aluno.getUsuario().getPassword());

         user.setId((int) database.insert(TABLE_USERS,null, values));

        if (aluno.getId() == null){
            idAluno = this.getProximoIdAluno();
        }else {
            idAluno = aluno.getId();
        }

        ContentValues values2 = new ContentValues();
        values2.put(COLUMN_ALUNO_ID, idAluno);
        values2.put(COLUMN_ALUNO_CODIGO, aluno.getCodigo());
        values2.put(COLUMN_ALUNO_NOME, aluno.getNome());
        values2.put(COLUMN_AVATAR_ID, aluno.getAvatar().getId());
        values2.put(COLUMN_USER_ID, user.getId());

        alunoNew.setId((int) database.insert(TABLE_ALUNO,null, values2));
        alunoNew.setUsuario(user);

        return alunoNew;
    }

    //Add Aluno para scoreBoard, esses alunos não são mostrados no login
    public void addAlunoSemUser(Aluno aluno) {

        database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ALUNO_ID, aluno.getId());
        values.put(COLUMN_ALUNO_CODIGO, aluno.getCodigo());
        values.put(COLUMN_ALUNO_NOME, aluno.getNome());
        values.put(COLUMN_AVATAR_ID, aluno.getAvatar().getId());

        database.insert(TABLE_ALUNO,null, values);

        return;
    }

    private Integer getProximoIdAluno() {
        Integer proxId = 0;

        String selectQuery = "SELECT MAX("+COLUMN_ALUNO_ID+") FROM " + TABLE_ALUNO + " ; ";
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            proxId = cursor.getInt(0)+1;
        }

        return proxId;
    }

    public void addDisciplina(Disciplina d) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_DISCIPLINA_ID, d.getId());
        values.put(COLUMN_DISCIPLINA_CODIGO, d.getCodigo());
        values.put(COLUMN_DISCIPLINA_NOME, d.getNome());
        values.put(COLUMN_DISCIPLINA_DESCRICAO, d.getDescricao());

        database.insert(TABLE_DISCIPLINA, null, values);
    }

    public void addCurso(Curso c) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_CURSO_ID, c.getId());
        values.put(COLUMN_CURSO_CODIGO, c.getCodigo());
        values.put(COLUMN_CURSO_NAME, c.getNome());
        values.put(COLUMN_CURSO_DESCRICAO, c.getDescricao());
        values.put(COLUMN_DISCIPLINA_ID, c.getDisciplina().getId());

        database.insert(TABLE_CURSO, null, values);
    }

    public void addCursoPreSelecao(Curso c) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_CURSO_ID_PRE, c.getId());
        values.put(COLUMN_CURSO_CODIGO_PRE, c.getCodigo());
        values.put(COLUMN_CURSO_NAME_PRE, c.getNome());
        values.put(COLUMN_CURSO_DESCRICAO_PRE, c.getDescricao());

        database.insert(TABLE_CURSO_PRE, null, values);
    }

    public void addConteudo(Conteudo c) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTEUDO_ID, c.getId());
        values.put(COLUMN_CONTEUDO_DICA, c.getDica());
        values.put(COLUMN_CONTEUDO_LICAO, c.getLicao());
        values.put(COLUMN_CONTEUDO_NOME, c.getNome());
        values.put(COLUMN_CURSO_ID, c.getCurso().getId());
        values.put(COLUMN_NIVEL_ID, c.getNivel().getId());

        database.insert(TABLE_CONTEUDO, null, values);
    }

    public void addQuestao(Questao q) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTAO_ID, q.getId());
        values.put(COLUMN_QUESTAO_ENUNCIADO, q.getEnunciado());
        values.put(COLUMN_QUESTAO_PTS, q.getPontuacao());
        values.put(COLUMN_CONTEUDO_ID, q.getConteudo().getId());

        database.insert(TABLE_QUESTAO, null, values);
    }

    public void addAlternativa(Alternativa a) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_ALTERNATIVA_ID, a.getId());
        values.put(COLUMN_ALTERNATIVA_TEXTO, a.getTexto());
        values.put(COLUMN_ALTERNATIVA_VALOR, a.isValor());
        values.put(COLUMN_QUESTAO_ID, a.getQuestao().getId());

        database.insert(TABLE_ALTERNATIVA, null, values);
    }

    public void addAlternativaAluno(AlternativaAluno a) {
        Cursor cursor;
        Integer proxId = 0;

        String selectQuery = "SELECT MAX("+COLUMN_ALTERNATIVA_ALUNO_ID+") FROM " + TABLE_ALTERNATIVA_ALUNO + " ; ";
        database = this.getReadableDatabase();
        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
           proxId = cursor.getInt(0) + 1;
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_ALTERNATIVA_ALUNO_ID, proxId);
        values.put(COLUMN_ALTERNATIVA_ID, a.getAlternativa().getId());
        values.put(COLUMN_ALUNO_ID, a.getAluno().getId());

        database = this.getWritableDatabase();

        database.insert(TABLE_ALTERNATIVA_ALUNO, null, values);
    }

    /*
       All methods to recovery information from the database.
                                                             */
    public List<Questao> questoesConteudo(Conteudo conteudo) {
        List<Questao> questionsList = new ArrayList<Questao>();
        Cursor cursor;
        Integer idConteudo = conteudo.getId();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTAO + " WHERE " + COLUMN_CONTEUDO_ID + " = '" + idConteudo + "' ; ";
        database = this.getReadableDatabase();
        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Questao questao = new Questao();
                questao.setId(cursor.getInt(0));
                questao.setEnunciado(cursor.getString(1));
                questao.setPontuacao(cursor.getInt(2));
                questao.setConteudo(conteudo);
                questionsList.add(questao);
            } while (cursor.moveToNext());
        }

        return questionsList;
    }

    public List<Alternativa> alternativasQuestao(Questao questao) {
        List<Alternativa> listaAlternativas = new ArrayList<Alternativa>();
        Integer idQuestao = questao.getId();
        String selectQuery = "SELECT * FROM " + TABLE_ALTERNATIVA + " WHERE " + COLUMN_QUESTAO_ID + " = '" + idQuestao + "' ;";
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                Alternativa alternativa = new Alternativa();
                alternativa.setId(cursor.getInt(0));
                alternativa.setTexto(cursor.getString(1));
                if (cursor.getInt(2) == 1){
                    alternativa.setValor(true);
                }else{
                    alternativa.setValor(false);
                }
                alternativa.setQuestao(questao);
                listaAlternativas.add(alternativa);

            } while (cursor.moveToNext());
        }
        return listaAlternativas;
    }

    public Avatar getAvatar(Integer idAvatar) {
        Avatar avatar  = new Avatar();

        String selectQuery = "SELECT * FROM " + TABLE_AVATAR + " WHERE " + COLUMN_AVATAR_ID + " = " + idAvatar + " ; ";
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
                avatar.setId(cursor.getInt(0));
                avatar.setNome(cursor.getString(1));
                avatar.setCaracteristica(cursor.getString(2));
        }

        return avatar;
    }

    public Avatar getAvatarByName(String nome) {
        Avatar avatar  = new Avatar();

        String selectQuery = "SELECT * FROM " + TABLE_AVATAR + " WHERE " + COLUMN_AVATAR_NOME + " like '%" + nome + "%' ; ";
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            avatar.setId(cursor.getInt(0));
            avatar.setNome(cursor.getString(1));
            avatar.setCaracteristica(cursor.getString(2));
        }

        return avatar;
    }


    private User getUser(Integer idUser) {
        User user  = new User();

        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_ID + " = '" + idUser + "' ; ";
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
        }

        return user;
    }

    public Disciplina getDisciplina(Integer idDisciplina) {
        Disciplina dis  = new Disciplina();

        String selectQuery = "SELECT * FROM " + TABLE_DISCIPLINA + " WHERE " + COLUMN_DISCIPLINA_ID + " = " + idDisciplina + " ; ";
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            dis.setId(cursor.getInt(0));
            dis.setCodigo(cursor.getString(2));
            dis.setNome(cursor.getString(3));
        }

        return dis;
    }

    public List<Aluno> getAllAlunosUsuarios() {
        List<Aluno> alunosList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_ALUNO + " WHERE " + COLUMN_USER_ID + " is not null;";
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Aluno a = new Aluno();
                a.setId(cursor.getInt(0));
                a.setCodigo(cursor.getString(1));
                a.setNome(cursor.getString(2));
                a.setAvatar(this.getAvatar(cursor.getInt(3)));
                a.setUsuario(this.getUser(cursor.getInt(4)));

                alunosList.add(a);

            } while (cursor.moveToNext());
        }
        return alunosList;
    }

    public List<Aluno> getAllAlunos() {
        List<Aluno> alunosList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_ALUNO + ";";
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Aluno a = new Aluno();
                a.setId(cursor.getInt(0));
                a.setCodigo(cursor.getString(1));
                a.setNome(cursor.getString(2));
                a.setAvatar(this.getAvatar(cursor.getInt(3)));
                a.setUsuario(this.getUser(cursor.getInt(4)));

                alunosList.add(a);

            } while (cursor.moveToNext());
        }
        return alunosList;
    }

    public Aluno getAluno(Integer AlunoID) {
        Aluno a = new Aluno();
        String selectQuery = "SELECT * FROM " + TABLE_ALUNO + " WHERE " + COLUMN_ALUNO_ID + " = '" + AlunoID + "' ; ";
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            a.setId(cursor.getInt(0));
            a.setCodigo(cursor.getString(1));
            a.setNome(cursor.getString(2));
            a.setAvatar(this.getAvatar(cursor.getInt(3)));
            a.setUsuario(this.getUser(cursor.getInt(4)));
        }
        return a;
    }

    public List<Curso> getAllCursos() {
        List<Curso> cursosList = new ArrayList<Curso>();
        String selectQuery = "SELECT * FROM " + TABLE_CURSO + ";";
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Curso curso = new Curso();
                curso.setId(cursor.getInt(0));
                curso.setCodigo(cursor.getString(1));
                curso.setNome(cursor.getString(2));
                curso.setDescricao(cursor.getString(3));
                curso.setDisciplina(this.getDisciplina(cursor.getInt(4)));

                cursosList.add(curso);
            } while (cursor.moveToNext());
        }
        return cursosList;
    }

    public List<Curso> getAllCursosPreSelecao() {
        List<Curso> cursosList = new ArrayList<Curso>();
        String selectQuery = "SELECT * FROM " + TABLE_CURSO_PRE + ";";
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Curso curso = new Curso();
                curso.setId(cursor.getInt(0));
                curso.setCodigo(cursor.getString(1));
                curso.setNome(cursor.getString(2));
                curso.setDescricao(cursor.getString(3));

                cursosList.add(curso);
            } while (cursor.moveToNext());
        }
        return cursosList;
    }

    public Curso getCurso(Integer cursoID) {
        Curso curso = new Curso();
        String selectQuery =  "SELECT * FROM " + TABLE_CURSO + " WHERE " + COLUMN_CURSO_ID + " = '" + cursoID + "' ; ";

        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            curso.setId(cursor.getInt(0));
            curso.setCodigo(cursor.getString(1));
            curso.setNome(cursor.getString(2));
            curso.setDescricao(cursor.getString(3));
            curso.setDisciplina(this.getDisciplina(cursor.getInt(4)));
        }
        return curso;
    }

    public Conteudo getConteudo(int conteudoID) {
        Conteudo conteudo = new Conteudo();
        Cursor cursor;
        String selectQuery = "SELECT * FROM " + TABLE_CONTEUDO + " WHERE " + COLUMN_CONTEUDO_ID + " = '" + conteudoID + "' ; ";
        database = this.getReadableDatabase();
        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            conteudo.setId(cursor.getInt(0));
            conteudo.setDica(cursor.getString(1));
            conteudo.setLicao(cursor.getString(2));
            conteudo.setNome(cursor.getString(3));
            conteudo.setCurso(this.getCurso(cursor.getInt(4)));
            conteudo.setNivel(this.getNivel(cursor.getInt(5)));
        }
        return conteudo;
    }


    public Nivel getNivelByPontuacao(Integer pontuacao) {
        Nivel nivel;
        Integer id = 0, idMax = 0;

        Cursor cursor1;
        String selectQuery1 = "SELECT " + COLUMN_NIVEL_ID + " FROM " + TABLE_NIVEL + " WHERE " + COLUMN_NIVEL_ORDEM + " = " + 5 + " ; ";
        database = this.getReadableDatabase();
        cursor1 = database.rawQuery(selectQuery1, null);
        if (cursor1.moveToFirst()){
            idMax = cursor1.getInt(0);
        }

        Cursor cursor;
        String selectQuery = "SELECT " + COLUMN_NIVEL_ID + " FROM " + TABLE_NIVEL + " WHERE " + COLUMN_NIVEL_PTS_MIN + " <= " + pontuacao + " AND "
                                + COLUMN_NIVEL_PTS_MAX  + " > " + pontuacao + " ; ";
        database = this.getReadableDatabase();
        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }

        if(id == null){
            id = idMax;
        }else {
            id = cursor.getInt(0);
        }
        nivel = getNivel(id);
        return nivel;
    }

    public Nivel getNivelByOrdem(Integer ordem) {
        Nivel nivel;
        Integer id = 0;

        Cursor cursor;
        String selectQuery1 = "SELECT " + COLUMN_NIVEL_ID + " FROM " + TABLE_NIVEL + " WHERE " + COLUMN_NIVEL_ORDEM + " = " + ordem + " ; ";
        database = this.getReadableDatabase();
        cursor = database.rawQuery(selectQuery1, null);
        if (cursor.moveToFirst()){
            id = cursor.getInt(0);
        }

        nivel = getNivel(id);
        return nivel;
    }


    public Conteudo getConteudoByNivel(Integer nivelId) {
        Conteudo conteudo = new Conteudo();
        Cursor cursor;
        String selectQuery = "SELECT * FROM " + TABLE_CONTEUDO + " WHERE " + COLUMN_NIVEL_ID  + " = " + nivelId + " ; ";
        database = this.getReadableDatabase();
        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            conteudo.setId(cursor.getInt(0));
            conteudo.setDica(cursor.getString(1));
            conteudo.setLicao(cursor.getString(2));
            conteudo.setNome(cursor.getString(3));
            conteudo.setCurso(this.getCurso(cursor.getInt(4)));
            conteudo.setNivel(this.getNivel(cursor.getInt(5)));
        }
        return conteudo;
    }


    public Nivel getNivel(int nivelID) {
        Nivel nivel = new Nivel();
        Cursor cursor;
        String selectQuery = "SELECT * FROM " + TABLE_NIVEL + " WHERE " + COLUMN_NIVEL_ID + " = '" + nivelID + "' ; ";
        database = this.getReadableDatabase();
        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            nivel.setId(cursor.getInt(0));
            nivel.setOrdem(cursor.getInt(1));
            nivel.setDescricao(cursor.getString(2));
            nivel.setPontosQuestaoDefault(cursor.getInt(3));
            nivel.setQtdPontosInicial(cursor.getInt(4));
            nivel.setQtdPontosFinal(cursor.getInt(5));
            nivel.setAmbiente(this.getAmbiente(cursor.getInt(6)));
        }
        return nivel;
    }

    public Ambiente getAmbiente(int ambienteID) {
        Ambiente ambiente = new Ambiente();
        Cursor cursor;
        String selectQuery = "SELECT * FROM " + TABLE_AMBIENTE + " WHERE " + COLUMN_AMBIENTE_ID + " = '" + ambienteID + "' ; ";
        database = this.getReadableDatabase();
        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            ambiente.setId(cursor.getInt(0));
            ambiente.setDescricao(cursor.getString(1));
            ambiente.setElemento(cursor.getString(2));
            ambiente.setObjetivo(cursor.getString(3));
        }
        return ambiente;
    }

    /*Remover curso*/
    public void deletarCursos(){
        database = this.getWritableDatabase();

        database.delete(TABLE_ALTERNATIVA_ALUNO, null, null);
        database.delete(TABLE_ALTERNATIVA, null, null);
        database.delete(TABLE_QUESTAO, null, null);
        database.delete(TABLE_CONTEUDO, null, null);
        database.delete(TABLE_CURSO, null, null);
        database.delete(TABLE_DISCIPLINA, null, null);

    }

    /*Remover cursos pré seleção*/
    public void deletarCursosPreSelecao(){
        database = this.getWritableDatabase();
        database.delete(TABLE_CURSO_PRE, null, null);
    }

    public CursoAluno getCursoAluno(Aluno aluno) {
        Integer qtdAcertos = 0, pontuacao = 0, qtdErros = 0;
        Double percentualErro;
        Nivel nivel;
        Conteudo conteudo;
        CursoAluno cursoAluno = null;

        //Buscando a quantidade de acertos e a pontuação baseado na tabela alternativaAluno
        Cursor cursor;
        String selectQuery = "SELECT COUNT(0), IFNULL(SUM(" + COLUMN_QUESTAO_PTS + "), 0) FROM " + TABLE_ALTERNATIVA_ALUNO
                + " AA INNER JOIN " + TABLE_ALTERNATIVA + " A ON AA." + COLUMN_ALTERNATIVA_ID + " = " + " A."
                + COLUMN_ALTERNATIVA_ID + " INNER JOIN " + TABLE_QUESTAO + " Q ON Q." + COLUMN_QUESTAO_ID + " = "
                + " A." + COLUMN_QUESTAO_ID + " WHERE " + COLUMN_ALTERNATIVA_VALOR + " = " + 1 + " AND AA."
                + COLUMN_ALUNO_ID + " = " + aluno.getId();

        database = this.getReadableDatabase();
        cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            qtdAcertos = cursor.getInt(0);
            pontuacao = cursor.getInt(1);
        }

        //Buscando a quantidade de erros baseado na tabela alternativaAluno
        Cursor cursor2;
        String selectQuery2 = "SELECT COUNT(0) FROM " + TABLE_ALTERNATIVA_ALUNO
                + " AA INNER JOIN " + TABLE_ALTERNATIVA + " A ON AA." + COLUMN_ALTERNATIVA_ID + " = " + " A."
                + COLUMN_ALTERNATIVA_ID + " WHERE (A." + COLUMN_ALTERNATIVA_VALOR + " IS NULL OR A." + COLUMN_ALTERNATIVA_VALOR
                + " <> " + 1 + ") AND AA." + COLUMN_ALUNO_ID + " = " + aluno.getId();

        database = this.getReadableDatabase();
        cursor2 = database.rawQuery(selectQuery2, null);
        if (cursor2.moveToFirst()) {
            qtdErros = cursor2.getInt(0);
        }

        //Calculando o percentual de erros
        if ((qtdErros + qtdAcertos) == 0){
            percentualErro = 0.00;
        }else {
            percentualErro = (qtdErros.doubleValue()/(qtdErros + qtdAcertos))*100;
            Integer v = (percentualErro.intValue());
            percentualErro = v.doubleValue();

                    //(new BigDecimal((qtdErros / (qtdErros + qtdAcertos)*100)).setScale(2, RoundingMode.HALF_EVEN)).doubleValue();
        }

        nivel = getNivelByPontuacao(pontuacao);
        conteudo = getConteudoByNivel(nivel.getId());


        //Preenche o objeto cursoAluno Atualizado
        cursoAluno = new CursoAluno();
        cursoAluno.setConteudo(conteudo);
        cursoAluno.setCurso(conteudo.getCurso());
        cursoAluno.setPercentualErro(percentualErro);
        cursoAluno.setPontuacao(pontuacao);
        cursoAluno.setAluno(aluno);

        return cursoAluno;
    }

    //Método para ser utilizado no scoreBoard
    public ArrayList<CursoAluno> getAllCursoAlunos() {
        ArrayList<CursoAluno> listaScore = new ArrayList<CursoAluno>();
        List<Aluno> alunos;
        CursoAluno item;

        alunos = this.getAllAlunos();

        for (Aluno a:  alunos){
            item = this.getCursoAluno(a);

            listaScore.add(item);
        }

        return listaScore;
    }

}


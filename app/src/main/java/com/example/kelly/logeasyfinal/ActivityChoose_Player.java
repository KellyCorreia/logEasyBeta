package com.example.kelly.logeasyfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.kelly.logeasyfinal.modelo.Aluno;
import com.example.kelly.logeasyfinal.persistencia.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;


public class ActivityChoose_Player extends Activity {
    private ImageButton btnnewuser;
    private GridView grid;
    private List<String> web = new ArrayList<String>();
    private List<Integer> imageId = new ArrayList<Integer>();
    private List<Aluno> alunos;

    public void addContentGrid(){

        MySQLiteHelper db = new MySQLiteHelper(this);
        alunos = db.getAllAlunosLocais();
        if(alunos.size()!=0) {

            String imageAvatar;

            for (int i = 0; i < alunos.size(); i++) {
                imageAvatar = alunos.get(i).getAvatar().getNome();
                int identifier = getResources().getIdentifier(imageAvatar, "drawable", this.getPackageName());
                // imageId.add(i, R.drawable.avatar1);, foi trocado por:
                imageId.add(i, identifier);

                web.add(i, alunos.get(i).getUsuario().getUsername());
            }
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__player);

        addContentGrid();
        //Log.d("Insert: ", "values from db" + web[0] + "  " + web[1]);
       // Toast.makeText(ActivityChoose_Player.this, "Teste quantidade " + web.get(0) + "  " +web.get(1), Toast.LENGTH_SHORT).show();

        CustomGrid gridAdapter = new CustomGrid(ActivityChoose_Player.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(gridAdapter);

        /*for (int i = 0; i < users.size(); i++) {
            Toast.makeText(ActivityChoose_Player.this, " " + users.get(i).getUsername() + " , " + users.get(i).getAvatar(), Toast.LENGTH_LONG).show();
        }*/

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ActivityChoose_Player.this, "Voce clicou em " + web.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActivityChoose_Player.this, ActivityLogin.class);
                intent.putExtra("chosenUser", (Parcelable) alunos.get(position));
                //intent.putExtra(users.get(position).getUsername(), users.get(position).getAvatar());//now I have to get the data in log in
                startActivity(intent);
                finish();
            }
        });

        btnnewuser = (ImageButton) findViewById(R.id.btnnewuser);
        btnnewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityChoose_Player.this, ActivityCreate_User.class);
                startActivity(intent);
                finish();
            }
        });
        if(alunos.isEmpty()){
            grid.setVisibility(View.INVISIBLE);
            LinearLayout layoutBackground =(LinearLayout)findViewById(R.id.layoutGrid);
            layoutBackground.setBackgroundResource(R.drawable.nouser);
        }
    }
}

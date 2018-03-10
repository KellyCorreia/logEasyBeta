package com.example.kelly.logeasyfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class ActivityChoose_Curso extends Activity {
    private ImageButton btncontinuar;
    private GridView grid;
    private List<String> web = new ArrayList<String>();
    private List<Integer> imageId = new ArrayList<Integer>();
    private List<ClassCurso> cursos;
    private ClassUser user;

    public void addContentGrid(){
        MySQLiteHelper db = new MySQLiteHelper(this);
        cursos = db.getAllCursos();
        if(cursos.size()!=0) {

            for (int i = 0; i < cursos.size(); i++) {

                imageId.add(i, R.drawable.level1s);
                web.add(i, cursos.get(i).getCursoname());
            }
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__curso);

        //Getting the object user from the previous screen
        Bundle extras = getIntent().getExtras();
        user = extras.getParcelable("chosenUser");
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
                Intent intent = new Intent(ActivityChoose_Curso.this, ActivityLevels.class);
                intent.putExtra("chosenUser", user);
                intent.putExtra("toast", i);
                intent.putExtra("chosenCurso", cursos.get(position));
                //intent.putExtra(users.get(position).getUsername(), users.get(position).getAvatar());//now I have to get the data in log in
                startActivity(intent);
                finish();
            }
        });

        btncontinuar = (ImageButton) findViewById(R.id.btncontinuar);
        btncontinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityChoose_Curso.this, ActivityLevels.class);
                intent.putExtra("chosenUser", user);
                intent.putExtra("toast", i);
                startActivity(intent);
                finish();
            }
        });
        if(cursos.isEmpty()){
            grid.setVisibility(View.INVISIBLE);
            LinearLayout layoutBackground =(LinearLayout)findViewById(R.id.layoutGrid);
            layoutBackground.setBackgroundResource(R.drawable.nouser);
        }
    }
}

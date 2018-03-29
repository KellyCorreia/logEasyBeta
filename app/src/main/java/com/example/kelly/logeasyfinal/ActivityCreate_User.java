package com.example.kelly.logeasyfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.kelly.logeasyfinal.modelo.Aluno;
import com.example.kelly.logeasyfinal.modelo.Avatar;
import com.example.kelly.logeasyfinal.persistencia.MySQLiteHelper;
import com.example.kelly.logeasyfinal.modelo.User;

import java.util.List;


public class ActivityCreate_User extends Activity {
    private Button btnsave;
    private String AvatarSelected;
    private String UserName;
    private String Email;
    private String Password;
    private Aluno NewAluno;
    private MySQLiteHelper dbHelper;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__user);
        rb1 = (RadioButton) findViewById(R.id.rbtAvatar1);
        rb2 = (RadioButton) findViewById(R.id.rbtAvatar2);
        rb3 = (RadioButton) findViewById(R.id.rbtAvatar3);
        rb4 = (RadioButton) findViewById(R.id.rbtAvatar4);
        GRadioGroup gr = new GRadioGroup(rb1, rb2, rb3, rb4);//This is to do not allow more than one Radio be clicked.
    }

   public void btnCreateOnClick(View view){
       if(rb1.isChecked()){
           AvatarSelected = "Avatar1";
       }else if(rb2.isChecked()){
           AvatarSelected = "Avatar2";
       }else if(rb3.isChecked()){
           AvatarSelected = "Avatar3";
       }else if(rb4.isChecked()){
           AvatarSelected = "Avatar4";
       }
       EditText txtname;
       txtname = (EditText)findViewById(R.id.txtusername);
       UserName = txtname.getText().toString();
       EditText txtemail;
       txtemail = (EditText)findViewById(R.id.txtemail);
       Email = txtemail.getText().toString();
       EditText txtpass;
       txtpass = (EditText)findViewById(R.id.txtpassword);
       Password = txtpass.getText().toString();

           if (TextUtils.isEmpty(UserName)) {
               txtname.setError(getString(R.string.error_empty_field));
           }
           if (TextUtils.isEmpty(Email)) {
               txtemail.setError(getString(R.string.error_empty_field));
           }
           if (TextUtils.isEmpty(Password)) {
               txtpass.setError(getString(R.string.error_empty_field));
           }

       /* Code to avoid duplicated usernames or emails on the database.*/
       dbHelper = new MySQLiteHelper(this);
       List<Aluno> alunos;
       alunos = dbHelper.getAllAlunos();
       boolean namefound = false, emailfound = false;
       for(int i = 0; i < alunos.size();i++){
           if (UserName.equals(alunos.get(0).getUsuario().getUsername())){
               namefound = true;
               txtname.setError(getString(R.string.error_duplicated_username));
           }
           if (Email.equals(alunos.get(0).getUsuario().getEmail())){
               emailfound = true;
               txtemail.setError(getString(R.string.error_duplicated_email));
           }
       }

       if((!TextUtils.isEmpty(UserName)) && (!TextUtils.isEmpty(Email)) && (!TextUtils.isEmpty(Password)) && (namefound == false) && (emailfound == false)) {
           //Toast.makeText(ActivityCreate_User.this, AvatarSelected + " , " + UserName + " , " + Email + " , " + Password, Toast.LENGTH_SHORT).show();
           Avatar avatar = dbHelper.getAvatarByName(AvatarSelected);
           User user = new User(UserName, Email, Password);

           NewAluno = new Aluno(UserName, UserName, user, avatar);
           dbHelper = new MySQLiteHelper(this);

           Integer alunoID = dbHelper.addAluno(NewAluno);
           NewAluno.setId(alunoID);

           if (alunoID!=0) {
               Intent intent = new Intent(ActivityCreate_User.this, ActivityLogin.class);
               intent.putExtra("chosenUser", (Parcelable) NewAluno);
               startActivity(intent);
               finish();
           }
       }
    }
}

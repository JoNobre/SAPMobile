package com.example.sapmobile;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    Button btlogin_us,btvoltar;
    EditText usnome, ussenha;
    SQLiteDatabase db;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btlogin_us = (Button) findViewById(R.id.btlogin_us);
        usnome = (EditText) findViewById(R.id.usnome);
        ussenha = (EditText) findViewById(R.id.ussenha);



        try {
            db = openOrCreateDatabase("banco_dados",
                    Context.MODE_PRIVATE,null);
        }
        catch (Exception e){
            MostraMensagem("Erro : "+e.toString());
        }
        btlogin_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = usnome.getText().toString();
                String senha = ussenha.getText().toString();
                btvoltar = (Button) findViewById(R.id.btvoltar);
                btvoltar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                try {
                    db = openOrCreateDatabase
                            ("banco_dados",Context.MODE_PRIVATE,null);

                    c = db.query("usuario",new String[]
                                    {"nome","senha"},
                            "nome = ? and senha = ?",new String[]
                                    {nome,senha},null,null,null);
                    if(c.getCount() > 0){
                        Intent menuChara = new Intent(LoginActivity.this,
                                CharaMenuActivity.class);
                        startActivity(menuChara);
                    }else {
                        MostraMensagem("Usuario inexistente");
                    }
                }catch (Exception e){
                    MostraMensagem("Erro : "+e.toString());
                }
            }
        });
    }
    public void MostraMensagem(String str){
        AlertDialog.Builder dialogo = new
                AlertDialog.Builder(LoginActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("Ok",null);
        dialogo.show();
    }
}
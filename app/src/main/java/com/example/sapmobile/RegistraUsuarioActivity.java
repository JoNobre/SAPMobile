package com.example.sapmobile;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;


public class RegistraUsuarioActivity extends AppCompatActivity {

    Button btcadastrar_us;
    EditText usnome, ussenha;
    SQLiteDatabase db;
    Cursor c;
    Button btvoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_usuario);

        btcadastrar_us = (Button) findViewById(R.id.btcadastrar_us);
        usnome = (EditText) findViewById(R.id.usnome);
        ussenha = (EditText) findViewById(R.id.ussenha);
        btvoltar = (Button) findViewById(R.id.btvoltar);
        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            db = openOrCreateDatabase("banco_dados",
                    Context.MODE_PRIVATE,null);
        }
        catch (Exception e){
            MostraMensagem("Erro : "+e.toString());
        }
        btcadastrar_us.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String nome = usnome.getText().toString();
                String senha = ussenha.getText().toString();

                ContentValues valor = new ContentValues();

                valor.put("nome",nome);
                valor.put("senha",senha);

                try {
                    db = openOrCreateDatabase
                            ("banco_dados",Context.MODE_PRIVATE,null);

                    c = db.query("usuario",new String[]
                                    {"nome","senha"},
                            "nome = ?",new String[]
                                    {nome},null,null,null);
                    if(c.getCount() <= 0){
                        try {
                            db.insert("usuario",null,valor);
                            MostraMensagem("Dados cadastrados com sucesso");
                            //setContentView(R.layout.activity_main);

                        }catch (Exception e){
                            MostraMensagem("Erro : "+e.toString());
                        }
                    }else {
                        MostraMensagem("Usuario já existe");
                    }
                }catch (Exception e){
                    MostraMensagem("Erro : "+e.toString());
                }
            }
        });
    }
    public void MostraMensagem(String str){
        AlertDialog.Builder dialogo = new
                AlertDialog.Builder(RegistraUsuarioActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("Ok",null);
        dialogo.show();
    }
}
package com.example.sapmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.*;
import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

public class GravaRegistrosActivity extends AppCompatActivity {

    Button btcadastrar;
    EditText ednome, edtelefone, edemail;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grava_registros);

        btcadastrar = (Button) findViewById(R.id.btcadastrar);
        ednome = (EditText) findViewById(R.id.ednome);
        edtelefone = (EditText) findViewById(R.id.edtelefone);
        edemail = (EditText) findViewById(R.id.edemail);
        try {
            db = openOrCreateDatabase("banco_dados",
                    Context.MODE_PRIVATE,null);
        }
        catch (Exception e){
            MostraMensagem("Erro : "+e.toString());
        }
        btcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = ednome.getText().toString();
                String telefone = edtelefone.getText().toString();
                String email = edemail.getText().toString();

                ContentValues valor = new ContentValues();

                valor.put("nome",nome);
                valor.put("telefone",telefone);
                valor.put("email",email);

                try {
                    db.insert("usuarios",null,valor);
                    MostraMensagem("Dados cadastrados com sucesso");
                }catch (Exception e){
                    MostraMensagem("Erro : "+e.toString());
                }
                /*
                try{
                    db.execSQL("insert into usuarios(nome, telefone, " +
                            "email) values('"+nome+"','"+telefone+"'," +
                            "'"+email+"')");
                    MostraMensagem("Dados cadastros com sucesso");
                }
                catch (Exception e){
                    MostraMensagem("Erro : "+e.toString());
                }
                */
            }
        });
    }
    public void MostraMensagem(String str){
        AlertDialog.Builder dialogo = new
                AlertDialog.Builder(GravaRegistrosActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("Ok",null);
        dialogo.show();
    }
}

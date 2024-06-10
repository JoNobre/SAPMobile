package com.example.sapmobile;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;



public class RegistraCharaActivity extends AppCompatActivity {
    Button btregistra_ch, btvoltar;
    EditText chnome, chdesc;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_chara);

        btregistra_ch = (Button) findViewById(R.id.btregistra_ch);
        chnome = (EditText) findViewById(R.id.chnome);
        chdesc = (EditText) findViewById(R.id.chdesc);
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
        btregistra_ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = chnome.getText().toString();
                String desc = chdesc.getText().toString();

                ContentValues valor = new ContentValues();

                valor.put("nome",nome);
                valor.put("descricao",desc);

                try {
                    db.insert("chara",null,valor);
                    MostraMensagem("Personagem criado com sucesso");
                    //setContentView(R.layout.activity_main);
                    finish();
                }catch (Exception e){
                    MostraMensagem("Erro : "+e.toString());
                }
            }
        });

    }
    public void MostraMensagem(String str){
        AlertDialog.Builder dialogo = new
                AlertDialog.Builder(RegistraCharaActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("Ok",null);
        dialogo.show();
    }
}
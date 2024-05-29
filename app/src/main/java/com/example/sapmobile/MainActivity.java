package com.example.sapmobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btcriarbanco;
    Button btcadastrados;
    Button btconsultardados;
    Button btalterardados;
    Button btexcluirdados;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btcriarbanco = findViewById(R.id.btcriarbanco);
        btcadastrados = findViewById(R.id.btcadastrardados);
        btconsultardados = findViewById(R.id.btconsultardados);
        btalterardados = findViewById(R.id.btalterardados);
        btexcluirdados = findViewById(R.id.btexcluirdados);

        btcriarbanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
                    db.execSQL("create table if not exists usuarios(numreg integer primary key " +
                            "autoincrement, nome text not null , telefone text not null, " +
                            "email text not null)");
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                    dialogo.setTitle("Aviso").setMessage("Banco de dados criado com sucesso!")
                            .setNeutralButton("OK",null).show();
                } catch (Exception e){
                }
            }
        });
        btcadastrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gravaRegistroActivity = new Intent(MainActivity.this,
                        GravaRegistrosActivity.class);
                MainActivity.this.startActivity(gravaRegistroActivity);
            }
        });
        btconsultardados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent consultaDadosActivity = new Intent(MainActivity.this,
                        ConsultaDadosActivity.class);
                MainActivity.this.startActivity(consultaDadosActivity);
            }
        });
        btalterardados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alterarDadosActivity = new Intent(MainActivity.this,
                        AlterarDadosActivity.class);
                MainActivity.this.startActivity(alterarDadosActivity);
            }
        });
        btexcluirdados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent excluirDadosActivity = new Intent(MainActivity.this,
                        ExcluirDadosActivity.class);
                MainActivity.this.startActivity(excluirDadosActivity);
            }
        });


    }
}
package com.example.sapmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button btreg_usuario, btlogin, btlimpaus;
    SQLiteDatabase db;
    DialogInterface.OnClickListener diExcluiBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btreg_usuario = findViewById(R.id.btreg_usuario);
        btlogin = findViewById(R.id.btlogin);
        btlimpaus = findViewById(R.id.btlimpaus);

        try{
            db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
            //db.execSQL("drop table usuario");
            db.execSQL("create table if not exists usuario(numreg integer primary key " +
                    "autoincrement, nome text not null , senha text not null)");
            db.execSQL("create table if not exists chara(numreg integer primary key " +
                    "autoincrement, nome text not null , descricao text)");
        }catch (Exception e){
            MostraMensagem("Erro ao criar banco : "+e.toString());
        }
        btreg_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registraUsuarioActivity = new Intent(MainActivity.this,
                        RegistraUsuarioActivity.class);
                MainActivity.this.startActivity(registraUsuarioActivity);
            }
        });
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(MainActivity.this,
                        LoginActivity.class);
                MainActivity.this.startActivity(loginActivity);
            }
        });
        btlimpaus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new
                        AlertDialog.Builder(MainActivity.this);
                dialogo.setTitle("Confirma");
                dialogo.setMessage("Deseja excluir todos os usuarios?");
                dialogo.setNegativeButton("Não", null);
                dialogo.setPositiveButton("Sim", diExcluiBanco);
                dialogo.show();
            }
        });
        diExcluiBanco = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Exclui as informações do registro na tabela
                db.execSQL("drop table usuario");
                db.execSQL("create table if not exists usuario(numreg integer primary key " +
                        "autoincrement, nome text not null , senha text not null)");
                MostraMensagem("Usuarios excluidos com sucesso!");
            }
        };
        /*
        btcadastrados = findViewById(R.id.btreg_usuario);
        btconsultardados = findViewById(R.id.btlogin);

        btcriarbanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
                    db.execSQL("create table if not exists usuarios(numreg integer primary key " +
                            "autoincrement, nome text not null , senha text not null, " +
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
        */


    }
    public void MostraMensagem(String str){
        android.app.AlertDialog.Builder dialogo = new
                AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("Ok",null);
        dialogo.show();
    }
}
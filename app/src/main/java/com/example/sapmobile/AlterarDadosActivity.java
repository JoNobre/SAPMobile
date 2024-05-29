package com.example.sapmobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;


public class AlterarDadosActivity extends AppCompatActivity {

    EditText txtnome, txttelefone, txtemail;
    TextView txtstatus_registro;
    SQLiteDatabase db;
    ImageView imgprimeiro, imganterior, imgproximo, imgultimo;
    int indice, numreg;
    Cursor c;
    Button btalterardados;
    DialogInterface.OnClickListener diAlteraInformacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_dados);

        txtnome = (EditText) findViewById(R.id.txtnome);
        txttelefone = (EditText) findViewById(R.id.txttelefone);
        txtemail = (EditText) findViewById(R.id.txtemail);

        txtstatus_registro = (TextView) findViewById(R.id.txtstatus_registro);

        imgprimeiro = (ImageView) findViewById(R.id.imgprimeiro);
        imganterior = (ImageView) findViewById(R.id.imganterior);
        imgproximo = (ImageView) findViewById(R.id.imgproximo);
        imgultimo = (ImageView) findViewById(R.id.imgultimo);

        btalterardados = (Button) findViewById(R.id.btalterardados);
        try {
            db = openOrCreateDatabase
                    ("banco_dados", Context.MODE_PRIVATE, null);

            c = db.query("usuarios", new String[]
                            {"numreg", "nome", "telefone", "email"},
                    null, null, null, null, null);
            if (c.getCount() > 0) {

                //Move para o primeiro registro
                c.moveToFirst();
                indice = 1;

                numreg = c.getInt(0);
                txtnome.setText(c.getString(1));//obtem o nome
                txttelefone.setText(c.getString(2));//obtem o telefone
                txtemail.setText(c.getString(3));//obtem o email

                txtstatus_registro.setText(indice + " / " + c.getCount());

            } else {
                txtstatus_registro.setText("Nenhum registro");
            }
        } catch (Exception e) {
        }
        imgprimeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c.getCount() > 0){
                    //Move para o primeiro registro
                    c.moveToFirst();
                    indice = 1;

                    numreg = c.getInt(0);
                    txtnome.setText(c.getString(1));//obtem o nome
                    txttelefone.setText(c.getString(2));//obtem o telefone
                    txtemail.setText(c.getString(3));//obtem o email

                    txtstatus_registro.setText(indice + " / "+c.getCount());
                }
            }
        });
        imganterior.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (c.getCount() > 0){
                    if(indice > 1){
                        indice--;

                        //move para o registro anterior
                        c.moveToPrevious();

                        numreg = c.getInt(0);
                        txtnome.setText(c.getString(1));
                        txttelefone.setText(c.getString(2));
                        txtemail.setText(c.getString(3));

                        txtstatus_registro.setText(indice + " / "+c.getCount());
                    }
                }
            }
        });
        imgproximo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (c.getCount() > 0){
                    if(indice != c.getCount()){
                        indice++;

                        //move para o registro anterior
                        c.moveToNext();

                        numreg = c.getInt(0);
                        txtnome.setText(c.getString(1));
                        txttelefone.setText(c.getString(2));
                        txtemail.setText(c.getString(3));

                        txtstatus_registro.setText(indice + " / "+c.getCount());
                    }
                }
            }
        });
        imgultimo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (c.getCount() > 0){
                    //Move para o primeiro registro
                    c.moveToLast();
                    indice = c.getCount();

                    numreg = c.getInt(0);
                    txtnome.setText(c.getString(1));//obtem o nome
                    txttelefone.setText(c.getString(2));//obtem o telefone
                    txtemail.setText(c.getString(3));//obtem o email

                    txtstatus_registro.setText(indice + " / "+c.getCount());
                }
            }
        });
        diAlteraInformacoes = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                String nome = txtnome.getText().toString();
                String telefone = txttelefone.getText().toString();
                String email = txtemail.getText().toString();

                try{
                    db.execSQL("update usuarios set nome = '"+nome+"', " +
                            "telefone = '"+telefone+"', email = '"+
                            email+"' where numreg = "+numreg);
                    MostraMensagem("Dados alterados com sucesso.");
                } catch (Exception e){
                    MostraMensagem("Erro : "+e.toString());
                }
                setContentView(R.layout.activity_main);
            }
        };
        btalterardados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new
                    AlertDialog.Builder(AlterarDadosActivity.this);
                dialogo.setTitle("Confirma");
                dialogo.setMessage("Deseja alteras as informações?");
                dialogo.setNegativeButton("Não",null);
                dialogo.setPositiveButton("Sim",diAlteraInformacoes);
                dialogo.show();
            }
        });
    }
    public void MostraMensagem(String str){
        android.app.AlertDialog.Builder dialogo = new
                android.app.AlertDialog.Builder(AlterarDadosActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("Ok",null);
        dialogo.show();
    }
}
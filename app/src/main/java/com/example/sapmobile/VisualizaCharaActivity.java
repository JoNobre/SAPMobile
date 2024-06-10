package com.example.sapmobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;


public class VisualizaCharaActivity extends AppCompatActivity {

    EditText txtnome, txtdesc;
    TextView txtstatus_registro;
    SQLiteDatabase db;
    ImageView imgprimeiro, imganterior, imgproximo, imgultimo;
    int indice, numreg;
    Cursor c;
    Button btalterardados, btexcluirdados;
    Button btvoltar;
    DialogInterface.OnClickListener diAlteraInformacoes;
    DialogInterface.OnClickListener diExcluiRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiza_chara);

        txtnome = (EditText) findViewById(R.id.txtnome);
        txtdesc = (EditText) findViewById(R.id.txtdesc);


        txtstatus_registro = (TextView) findViewById(R.id.txtstatus_registro);

        imgprimeiro = (ImageView) findViewById(R.id.imgprimeiro);
        imganterior = (ImageView) findViewById(R.id.imganterior);
        imgproximo = (ImageView) findViewById(R.id.imgproximo);
        imgultimo = (ImageView) findViewById(R.id.imgultimo);

        btalterardados = (Button) findViewById(R.id.btalterardados);
        btexcluirdados = (Button) findViewById(R.id.btexcluirdados);
        btvoltar = (Button) findViewById(R.id.btvoltar);
        try {
            db = openOrCreateDatabase
                    ("banco_dados", Context.MODE_PRIVATE, null);
            CarregaDados();

            c = db.query("chara", new String[]
                            {"numreg", "nome", "descricao"},
                    null, null, null, null, null);
            if (c.getCount() > 0) {

                //Move para o primeiro registro
                c.moveToFirst();
                indice = 1;

                numreg = c.getInt(0);
                txtnome.setText(c.getString(1));//obtem o nome
                txtdesc.setText(c.getString(2));//obtem a descrição

                txtstatus_registro.setText(indice + " / " + c.getCount());

            } else {
                txtstatus_registro.setText("Nenhum registro");
            }
        } catch (Exception e) {
        }
        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgprimeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c.getCount() > 0){
                    //Move para o primeiro registro
                    c.moveToFirst();
                    indice = 1;

                    numreg = c.getInt(0);
                    txtnome.setText(c.getString(1));//obtem o nome
                    txtdesc.setText(c.getString(2));//obtem a descrição


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
                        txtdesc.setText(c.getString(2));//obtem a descrição

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
                        txtdesc.setText(c.getString(2));//obtem a descrição

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
                    txtdesc.setText(c.getString(2));//obtem a descrição

                    txtstatus_registro.setText(indice + " / "+c.getCount());
                }
            }
        });
        diAlteraInformacoes = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                String nome = txtnome.getText().toString();
                String desc = txtdesc.getText().toString();

                try{
                    db.execSQL("update chara set nome = '"+nome+"', " +
                            "descricao = '"+desc+"' where numreg = "+numreg);
                    MostraMensagem("Dados alterados com sucesso.");
                } catch (Exception e){
                    MostraMensagem("Erro : "+e.toString());
                }
                CarregaDados();
            }
        };
        btalterardados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new
                        AlertDialog.Builder(VisualizaCharaActivity.this);
                dialogo.setTitle("Confirma");
                dialogo.setMessage("Deseja alteras as informações?");
                dialogo.setNegativeButton("Não",null);
                dialogo.setPositiveButton("Sim",diAlteraInformacoes);
                dialogo.show();
            }
        });
        diExcluiRegistro = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Exclui as informações do registro na tabela
                db.execSQL("delete from chara where numreg = " + numreg);
                CarregaDados();
                MostraMensagem("Dados excluidos com sucesso!");
            }
        };
        btexcluirdados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (c.getCount() > 0) {

                    android.app.AlertDialog.Builder dialogo = new
                            android.app.AlertDialog.Builder(VisualizaCharaActivity.this);
                    dialogo.setTitle("Confirma");
                    dialogo.setMessage("Deseja excluir esse registro ?");
                    dialogo.setNegativeButton("Não", null);
                    dialogo.setPositiveButton("Sim", diExcluiRegistro);
                    dialogo.show();
                } else {
                    MostraMensagem("Não existem registros para excluir!");
                }
            }
        });
    }
    public void MostraMensagem(String str){
        android.app.AlertDialog.Builder dialogo = new
                android.app.AlertDialog.Builder(VisualizaCharaActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("Ok",null);
        dialogo.show();
    }
    public void CarregaDados(){
        c = db.query("chara",new String []
                        {"numreg","nome","descricao"},
                null,null,null,null,null);

        txtnome.setText("");
        txtdesc.setText("");

        if(c.getCount() > 0) {
            //Move para o primeiro registro
            c.moveToFirst();
            indice = 1;
            numreg = c.getInt(0);
            //Obtem o número de registro
            txtnome.setText(c.getString(1));//Obtem o nome
            //Obtém o telefone
            txtdesc.setText(c.getString(2));


            txtstatus_registro.setText(indice + " / " + c.getCount());
        }
        else {
            txtstatus_registro.setText("Nenhum Registro");
        }
    }

}
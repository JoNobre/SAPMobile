package com.example.sapmobile;

import android.app.AlertDialog;
import android.content.Context;

public class Alertas {
    public void MostraMensagem(String str,AlertDialog.Builder dialogo){
        //AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("Ok",null);
        dialogo.show();
    }
}

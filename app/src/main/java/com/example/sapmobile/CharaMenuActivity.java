package com.example.sapmobile;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CharaMenuActivity extends AppCompatActivity {

    Button btnovochara, btvisualizachara, btvoltar;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chara_menu);

        btnovochara = (Button) findViewById(R.id.btnovochara);
        btvisualizachara = (Button) findViewById(R.id.btvisualizachara);
        btvoltar = (Button) findViewById(R.id.btvoltar);
        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnovochara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registraChara = new Intent(CharaMenuActivity.this,
                        RegistraCharaActivity.class);
                startActivity(registraChara);
            }
        });
        btvisualizachara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visualizaChara = new Intent(CharaMenuActivity.this,
                        VisualizaCharaActivity.class);
                startActivity(visualizaChara);
            }
        });

    }
}
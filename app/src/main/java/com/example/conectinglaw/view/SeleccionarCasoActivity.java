package com.example.conectinglaw.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.conectinglaw.R;

public class SeleccionarCasoActivity extends AppCompatActivity {

    Button btnMer,btnPen, btnCiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_caso);

        asEl();

        btnPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), ListadoAbogadosActivity.class);
                i.putExtra("TipoAb", "Penal");
                startActivity(i);
                finish();

            }
        });

        btnMer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), ListadoAbogadosActivity.class);
                i.putExtra("TipoAb", "Mercantil");
                startActivity(i);
                finish();

            }
        });

        btnCiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), ListadoAbogadosActivity.class);
                i.putExtra("TipoAb", "Civil");
                startActivity(i);
                finish();
            }
        });

    }

    public void asEl(){
        btnCiv=(Button)findViewById(R.id.btnCivil);
        btnMer=(Button)findViewById(R.id.btnMercantil);
        btnPen=(Button)findViewById(R.id.btnPenal);
    }
}

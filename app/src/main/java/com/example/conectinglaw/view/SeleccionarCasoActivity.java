package com.example.conectinglaw.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.conectinglaw.R;
import com.example.conectinglaw.repository.FirebaseService;

public class SeleccionarCasoActivity extends AppCompatActivity {

    Button btnMer,btnPen, btnCiv;

    FirebaseService firebaseService = new FirebaseService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_caso);

        asEl();

        btnPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchLawyer("penal");
            }
        });

        btnMer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchLawyer("mercantil");
            }
        });

        btnCiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchLawyer("civil");
            }
        });

    }

    public void asEl(){
        btnCiv=(Button)findViewById(R.id.btnCivil);
        btnMer=(Button)findViewById(R.id.btnMercantil);
        btnPen=(Button)findViewById(R.id.btnPenal);
    }

    public void searchLawyer(String lawyerType){
        firebaseService.listLawyersType(lawyerType, this);
    }
}

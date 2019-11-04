package com.example.conectinglaw.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.conectinglaw.R;
import com.example.conectinglaw.adapter.ListadoAbAdapter;
import com.example.conectinglaw.model.Lawyer;
import com.example.conectinglaw.model.User;
import com.example.conectinglaw.repository.FirebaseService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ListadoAbogadosActivity extends AppCompatActivity {

    FirebaseService firebaseService = new FirebaseService();

    ArrayList<Lawyer> abogados = new ArrayList<>();
    RecyclerView rvListaAb;
    ArrayList<Lawyer> abFiltrados;

    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_abogados);

        asociarEl();

//        String TipoAb = getIntent().getExtras().getString("TipoAb");
//        makeFakeDATA();
//        filtrarAb(TipoAb);

        abFiltrados = (ArrayList<Lawyer>) getIntent().getSerializableExtra("lawyers");

        rvListaAb.setLayoutManager(new LinearLayoutManager(this));
        ListadoAbAdapter adapter =
                new ListadoAbAdapter(abFiltrados, R.layout.listado_abogados_cardview, new ListadoAbAdapter.onItemClickListener() {
                    @Override
                    public void onItemClick(Lawyer lawyer, int position) {
                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        firebaseService.addChatToList(firebaseUser.getEmail(), lawyer.getEmail());
                        goToChat(firebaseUser, lawyer);
                    }
                });

        rvListaAb.setAdapter(adapter);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void asociarEl(){
        rvListaAb = (RecyclerView)findViewById(R.id.rvListaAb);
        ivBack = findViewById(R.id.iv_back);
    }

    public void goToChat(FirebaseUser firebaseUser, Lawyer lawyer){
        firebaseService.getMessages(firebaseUser.getEmail(), lawyer.getEmail(), "client",
                this);
    }

}

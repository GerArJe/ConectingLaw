package com.example.conectinglaw.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class ListadoAbogadosActivity extends AppCompatActivity {

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
                        Intent intent = new Intent(getBaseContext(), ChatActivity.class);
                        intent.putExtra("receiver", lawyer.getEmail());
                        startActivity(intent);
                        finish();
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

    public void makeFakeDATA(){

        Lawyer abogado1 = new Lawyer(
                "aleps",
                "Rye",
                "yreyrey@unab.edu.co",
                1234, 1234656, true, false, false);
        Lawyer abogado2 = new Lawyer(
                "Germna",
                "arevalo",
                "Garevalo12@unab.edu.co",
                1234,
                123456 ,
                false,
                false,
                true);
        Lawyer abogado3 = new Lawyer("Jesus", " Castellanos", "JCastellanos123@unab.edu.co", 1234, 1234, false, true, false);

        abogados.add(abogado1);
        abogados.add(abogado2);
        abogados.add(abogado3);


    }

    private void filtrarAb(String TipoAb){

        for (Lawyer ab: abogados ){
            if (TipoAb.equals("Penal") && ab.isPenal() == true){
                abFiltrados.add(ab);
            }

            else if (TipoAb.equals("Mercantil") && ab.isMercantil() == true){abFiltrados.add(ab);
            }

            else if (TipoAb.equals("Civil") && ab.isCivil()==true){abFiltrados.add(ab);
            }
        }
    }
}

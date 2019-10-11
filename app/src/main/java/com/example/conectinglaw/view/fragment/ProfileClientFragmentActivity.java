package com.example.conectinglaw.view.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.conectinglaw.R;

public class ProfileClientFragmentActivity extends Fragment {

    private String TAG = "ProfileClientFragmentActivity";

    TextView txtName, txtApellidos, txtCedula,
            txtCorreo, txtCelular;

    Button btnCerrarSesion;

    ImageButton imageButtonProfilePhoto;

    public ProfileClientFragmentActivity(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w(TAG, "Iniciando" + TAG);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_profile_client_fragment, container, false);

        txtName = view.findViewById(R.id.txt_name);
        txtApellidos = view.findViewById(R.id.txt_apellidos);
        txtCedula = view.findViewById(R.id.txt_cedula);
        txtCorreo = view.findViewById(R.id.txt_correo);
        txtCelular = view.findViewById(R.id.txt_celular);
        btnCerrarSesion = view.findViewById(R.id.btn_cerrarSesion);
        imageButtonProfilePhoto = view.findViewById(R.id.imageButton_profilePhoto);

        return view;
    }
}

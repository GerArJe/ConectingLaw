package com.example.conectinglaw.view.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.conectinglaw.R;
import com.example.conectinglaw.repository.FirebaseService;

public class ProfileLawyerFragmentActivity extends Fragment {

    private String TAG = "ProfileLawyerFragmentActivity";

    TextView txtName, txtApellidos, txtCedula,
            txtCorreo, txtCelular, txtTipoAbogado;
    Button btnCerrarSesion;
    ImageButton imageButtonProfilePhoto;

    public ProfileLawyerFragmentActivity(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w(TAG, "Iniciando" + TAG);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_profile_lawyer_fragment, container, false);

        txtName = view.findViewById(R.id.txt_name);
        txtApellidos = view.findViewById(R.id.txt_apellidos);
        txtCedula = view.findViewById(R.id.txt_cedula);
        txtCorreo = view.findViewById(R.id.txt_correo);
        txtCelular = view.findViewById(R.id.txt_celular);
        txtTipoAbogado = view.findViewById(R.id.txt_tipoAbogado);
        btnCerrarSesion = view.findViewById(R.id.btn_cerrarSesion);
        imageButtonProfilePhoto = view.findViewById(R.id.imageButton_profilePhoto);

        return view;
    }

}

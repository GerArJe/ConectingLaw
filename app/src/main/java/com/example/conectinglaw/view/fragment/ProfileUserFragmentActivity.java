package com.example.conectinglaw.view.fragment;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conectinglaw.R;
import com.example.conectinglaw.model.Client;
import com.example.conectinglaw.model.Lawyer;
import com.example.conectinglaw.model.User;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class ProfileUserFragmentActivity extends Fragment {

    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private String TAG = "ProfileUserFragmentActivity";

    TextView txtNombreUser, txtApellidosUser, txtCedulaUser,
            txtCorreoUser, txtCelularUser, txtTipoAbogadoUser;
    ImageView fotoPerfilUser;

    public ProfileUserFragmentActivity(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w(TAG, "Iniciando" + TAG);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_profile_user_fragment, container, false);

        txtNombreUser = view.findViewById(R.id.txtNombre_user);
        txtApellidosUser = view.findViewById(R.id.txtApellidos_user);
        txtCedulaUser = view.findViewById(R.id.txtCedula_user);
        txtCorreoUser = view.findViewById(R.id.txtCorreo_user);
        txtCelularUser = view.findViewById(R.id.txtCelular_user);
        txtTipoAbogadoUser = view.findViewById(R.id.txtTipoAb_user);
        fotoPerfilUser = view.findViewById(R.id.fotoPerfilUser);

        String userType = getArguments().getString("userType");
        User user = (User) getArguments().getSerializable("user");

        if (!user.getPhotoUrl().isEmpty()){
            Picasso.get()
                    .load(user.getPhotoUrl())
                    .error(R.drawable.user_icon)
                    .centerCrop()
                    .into(fotoPerfilUser);
        }

        txtNombreUser.setText("Nombre: " + user.getName());
        txtApellidosUser.setText("Apellidos: " + user.getLastname());
        txtCedulaUser.setText("Cedula: " + user.getCedula());
        txtCorreoUser.setText("Correo: " + user.getEmail());
        txtCelularUser.setText("Celular: " + user.getTelephoneNumber());

        if (userType.equals("lawyer")){
            txtTipoAbogadoUser.setText(((Lawyer) user).tipoAbogado());
        }

        fotoPerfilUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPicture();
            }
        });

        return view;
    }

    private void setPicture() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            Picasso.get()
                    .load(imageUri)
                    .error(R.drawable.user_icon)
                    .into(fotoPerfilUser);
        }
    }
}

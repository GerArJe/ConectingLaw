package com.example.conectinglaw.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.conectinglaw.R;
import com.example.conectinglaw.repository.FirebaseService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String TAG = "CreateAccountActivity";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseService firebaseService = new FirebaseService();

    public Button btnRegister;
    public CheckBox cbPJ, cbPN;
    public EditText edtNombre, edtCedula, edtEmail, edtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        asociarElement();
        initialize();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(edtEmail.getText().toString(), edtPassword.getText().toString());
            }
        });
    }

    //registro
    public void registrar(View vista) {

        if (cbPN.isActivated()) {
            Intent i = new Intent(this, HomeClientActivity.class);
            startActivity(i);
            Toast.makeText(getBaseContext(),
                    "Has sido registrado como persona Natural", Toast.LENGTH_LONG).show();
        }

        else if(cbPJ.isActivated())
        {
            Intent i = new Intent(this, HomeLawyerActivity.class);
            startActivity(i);
            Toast.makeText(getBaseContext(),
                    "Has sido registrado como Persona Jurídica", Toast.LENGTH_LONG).show();
        }
    }

    public void setCheckboxPersonaJuridica(View view){
        if (cbPJ.isChecked()){
            cbPN.setChecked(false);
        }
    }

    public void setCheckboxPersonaNatural(View view){
        if (cbPN.isChecked()){
            cbPJ.setChecked(false);
        }
    }

    public void asociarElement () {
        btnRegister = findViewById(R.id.btn_register);
        cbPJ = findViewById(R.id.cb_PJ);
        cbPN = findViewById(R.id.cb_PN);
        edtNombre = findViewById(R.id.edt_name);
        edtPassword = findViewById(R.id.edt_password);
        edtEmail = findViewById(R.id.edt_email);
        edtCedula = findViewById(R.id.edt_Cedula);
    }

    //inicializar instancia de Firebase
    private void initialize() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Log.w(TAG, "onAuthStateChanged - signed_in" + firebaseUser.getUid());
                    Log.w(TAG, "onAuthStateChanged - signed_in" + firebaseUser.getEmail());
                } else {
                    Log.w(TAG, "onAuthStateChanged - signed_out");
                }
            }
        };
    }

    //crear cuenta
    private void createAccount(String email, String password){
        firebaseService.createAccount(email, password, this, firebaseAuth);
    }

    //volver al login
    public void backToLogin(View view){
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

}

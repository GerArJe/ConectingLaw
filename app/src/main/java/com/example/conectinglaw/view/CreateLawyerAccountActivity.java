package com.example.conectinglaw.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.conectinglaw.R;
import com.example.conectinglaw.model.Lawyer;
import com.example.conectinglaw.model.User;
import com.example.conectinglaw.repository.FirebaseService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class CreateLawyerAccountActivity extends AppCompatActivity {

    private static final String TAG = "CreateAccountLawyer";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseService firebaseService = new FirebaseService();

    public Button btnRegister;
    public CheckBox cbPenal, cbCivil, cbMercantil;
    public EditText edtNombre, edtLastname, edtCedula, edtEmail, edtPassword, edtTelephoneNumber;
    ProgressBar progressBarRegister;
    ImageView ivBack;

    private Lawyer lawyer;
    private boolean penal = false;
    private boolean civil = false;
    private boolean mercantil = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lawyer_account);

        asociarElement();
        hideProgressBar();
        initialize();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar();
                if (cbPenal.isChecked()){
                    penal = true;
                }
                if (cbCivil.isChecked()){
                    civil = true;
                }
                if (cbMercantil.isChecked()){
                    mercantil = true;
                }
                lawyer = new Lawyer(edtNombre.getText().toString(),
                        edtLastname.getText().toString(),
                        edtEmail.getText().toString(),
                        Integer.parseInt(edtCedula.getText().toString()),
                        Integer.parseInt(edtTelephoneNumber.getText().toString()),
                        penal,
                        civil,
                        mercantil);
                createAccount(edtEmail.getText().toString(),
                        edtPassword.getText().toString(),
                        lawyer);
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void asociarElement () {
        edtNombre = findViewById(R.id.edt_name);
        edtPassword = findViewById(R.id.edt_password);
        edtEmail = findViewById(R.id.edt_email);
        edtCedula = findViewById(R.id.edt_Cedula);
        edtLastname = findViewById(R.id.edt_lastname);
        edtTelephoneNumber = findViewById(R.id.edt_telephoneNumber);
        cbPenal = findViewById(R.id.cb_penal);
        cbCivil = findViewById(R.id.cb_civil);
        cbMercantil = findViewById(R.id.cb_mercantil);
        btnRegister = findViewById(R.id.btn_register);
        progressBarRegister = findViewById(R.id.progressBar_register);
        ivBack = findViewById(R.id.iv_back);
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
    private void createAccount(final String email, final String password, final User user){
        firebaseService.createUserWithEmailAndPassword(
                email,
                password,
                CreateLawyerAccountActivity.this,
                firebaseAuth,
                user,
                "lawyer");
    }

    //mostar progressbar
    public void showProgressBar(){
        progressBarRegister.setVisibility(View.VISIBLE);
    }

    //ocultar progressbar
    public void hideProgressBar() {
        progressBarRegister.setVisibility(View.GONE);
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

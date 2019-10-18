package com.example.conectinglaw.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.conectinglaw.R;
import com.example.conectinglaw.repository.FirebaseService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseService firebaseService = new FirebaseService();

    TextInputEditText username, password;
    Button btnLogin;
    TextView txtCreateAccount;
    ProgressBar progressbarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //hideProgressBar();

        asociateElements();
        initialize();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar();
                signIn(username.getText().toString(), password.getText().toString());
            }
        });
    }

    public void asociateElements(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
        txtCreateAccount = findViewById(R.id.txt_createAccount);
        progressbarLogin = findViewById(R.id.progressbar_login);
    }

    //ir a crear cuenta abogado
    public void goCreateAccountLawyer(View view){
        Intent intent = new Intent(this, CreateLawyerAccountActivity.class);
        startActivity(intent);
    }

    //ir a crear cuenta cliente
    public void goCreateAccountClient(View view){
        Intent intent = new Intent(this, CreateClientAccountActivity.class);
        startActivity(intent);
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

    //iniciar sesion
    private void signIn(String email, String password){
        firebaseService.signIn(email, password, this, firebaseAuth);
    }

    //mostar progressbar
    public void showProgressBar(){
        progressbarLogin.setVisibility(View.VISIBLE);
    }

    //ocultar progressbar
    public void hideProgressBar() {
        progressbarLogin.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(authStateListener);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            SharedPreferences preferences = getSharedPreferences("USER",
                    Context.MODE_PRIVATE);
            String email = preferences.getString("email", "email");
            String password = preferences.getString("password", "password");
            signIn(email, password);
        }else if (user == null){
            hideProgressBar();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
}

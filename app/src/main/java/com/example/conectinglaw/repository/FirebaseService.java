package com.example.conectinglaw.repository;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.conectinglaw.view.CreateAccountActivity;
import com.example.conectinglaw.view.HomeClientActivity;
import com.example.conectinglaw.view.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseService {

    private String TAG = "FirebaseService";

    public void signIn(String email, String password, final Activity activity, FirebaseAuth firebaseAuth){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(activity.getBaseContext(),
                            "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity.getBaseContext(), HomeClientActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                }else {
                    Toast.makeText(activity.getBaseContext(),
                            "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void createAccount(String email, String password, final Activity activity, FirebaseAuth firebaseAuth){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(activity.getBaseContext(),
                            "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity.getBaseContext(), HomeClientActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                }else {
                    Toast.makeText(activity.getBaseContext(),
                            "No se pudo crear la cuenta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

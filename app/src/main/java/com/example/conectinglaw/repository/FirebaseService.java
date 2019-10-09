package com.example.conectinglaw.repository;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.conectinglaw.model.User;
import com.example.conectinglaw.view.HomeClientActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseService {

    private String TAG = "FirebaseService";

    //Instancia de Cloud Firestore
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //iniciar sesion
    public void signIn(String email, String password, final Activity activity, FirebaseAuth firebaseAuth) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(activity.getBaseContext(),
                            "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity.getBaseContext(), HomeClientActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                } else {
                    Toast.makeText(activity.getBaseContext(),
                            "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //crear cuenta verificando primero que tipo de cliente es
    public void createAccount(String email, String password, final Activity activity,
                              final FirebaseAuth firebaseAuth, final User user, final String userType) {

        if (userType.equals("lawyer")) {
            db.collection("lawyers")
                    .document(user.getEmail())
                    .set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });
        } else if (userType.equals("client")) {
            db.collection("clients")
                    .document(user.getEmail())
                    .set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(activity.getBaseContext(),
                            "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity.getBaseContext(), HomeClientActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                } else {
                    Toast.makeText(activity.getBaseContext(),
                            "No se pudo crear la cuenta auth", Toast.LENGTH_SHORT).show();
                    if (userType.equals("lawyer")) {
                        db.collection("lawyers").document(user.getEmail()).delete();
                    } else if (userType.equals("client")) {
                        db.collection("clients").document(user.getEmail()).delete();
                    }
                }
            }
        });
    }
}

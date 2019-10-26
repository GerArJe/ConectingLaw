package com.example.conectinglaw.repository;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.conectinglaw.model.Chat;
import com.example.conectinglaw.model.Client;
import com.example.conectinglaw.model.Lawyer;
import com.example.conectinglaw.model.User;
import com.example.conectinglaw.view.ContainerActivity;
import com.example.conectinglaw.view.ListadoAbogadosActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseService {

    private String TAG = "FirebaseService";

    //Instancia de Cloud Firestore
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //iniciar sesion
    public void signIn(final String email, final String password, final Activity activity, FirebaseAuth firebaseAuth) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    createPreferencesSignIn(email, password, activity);
                    searchTypeUser(email, activity);
                } else {
                    Toast.makeText(activity.getBaseContext(),
                            "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //crear cuenta verificando primero que tipo de cliente es
    public void createAccount(final String email, final String password, final Activity activity,
                              final FirebaseAuth firebaseAuth, final User user, final String userType) {

        if (userType.equals("lawyer")) {
            db.collection("lawyers")
                    .document(user.getEmail())
                    .set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                            Toast.makeText(activity.getBaseContext(),
                                    "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();
                            createPreferencesSignIn(email, password, activity);

                            Intent intent = new Intent(activity.getBaseContext(), ContainerActivity.class);
                            intent.putExtra("userType", userType);
                            intent.putExtra("user", user);
                            activity.startActivity(intent);
                            activity.finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                            Toast.makeText(activity.getBaseContext(),
                                    "No se pudo crear la cuenta", Toast.LENGTH_SHORT).show();

                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Log.d(TAG, "User account deleted.");
                                    }
                                }
                            });
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
                            Toast.makeText(activity.getBaseContext(),
                                    "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();
                            createPreferencesSignIn(email, password, activity);

                            Intent intent = new Intent(activity.getBaseContext(), ContainerActivity.class);
                            intent.putExtra("userType", userType);
                            intent.putExtra("user", user);
                            activity.startActivity(intent);
                            activity.finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                            Toast.makeText(activity.getBaseContext(),
                                    "No se pudo crear la cuenta", Toast.LENGTH_SHORT).show();
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Log.d(TAG, "User account deleted.");
                                    }
                                }
                            });
                        }
                    });
        }

    }

    //crear autenticación con email y contraseña
    public void createUserWithEmailAndPassword(final String email, final String password, final Activity activity,
                                               final FirebaseAuth firebaseAuth, final User user,
                                               final String userType){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Creación de autenticación exitoso");
                    createAccount(email, password, activity, firebaseAuth, user, userType);
                } else {
                    Log.d(TAG, "Error al crear la autenticación");
                    Toast.makeText(activity.getBaseContext(),
                            "No se pudo crear la cuenta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //verificar si el usuario existe en la base de datos y que tipo de usuario es
    public void searchTypeUser(final String email, final Activity activity){

        db.collection("lawyers").document(email)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    Lawyer lawyer = documentSnapshot.toObject(Lawyer.class);
                    if (documentSnapshot.exists()){
                        Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());

                        Toast.makeText(activity.getBaseContext(),
                                "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity.getBaseContext(), ContainerActivity.class);
                        intent.putExtra("userType", "lawyer");
                        intent.putExtra("user", lawyer);
                        activity.startActivity(intent);
                        activity.finish();
                    }else {
                        Log.d(TAG, "No such document");

                        Toast.makeText(activity.getBaseContext(),
                                "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Log.d(TAG, "get failed with ", task.getException());

                    Toast.makeText(activity.getBaseContext(),
                            "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });

        db.collection("clients").document(email)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    Client client = documentSnapshot.toObject(Client.class);
                    if (documentSnapshot.exists()){
                        Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());

                        Toast.makeText(activity.getBaseContext(),
                                "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity.getBaseContext(), ContainerActivity.class);
                        intent.putExtra("userType", "client");
                        intent.putExtra("user", client);
                        activity.startActivity(intent);
                        activity.finish();
                    }else {
                        Log.d(TAG, "No such document");

                        Toast.makeText(activity.getBaseContext(),
                                "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Log.d(TAG, "get failed with ", task.getException());

                    Toast.makeText(activity.getBaseContext(),
                            "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //buscar los abogados que cumplan con el tipo que se requiere
    public void listLawyersType(String lawyerType, final Activity activity){
        db.collection("lawyers")
                .whereEqualTo(lawyerType, true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            ArrayList<Lawyer> lawyers = new ArrayList<>();
                            for (QueryDocumentSnapshot snapshot : task.getResult()){
                                Log.d(TAG, snapshot.getId() + " => " + snapshot.getData());
                                lawyers.add(snapshot.toObject(Lawyer.class));
                            }
                            Intent intent =
                                    new Intent(activity.getBaseContext(), ListadoAbogadosActivity.class);
                            intent.putExtra("lawyers", lawyers);
                            activity.startActivity(intent);
                            activity.finish();
                        }else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                            Toast.makeText(activity.getBaseContext()
                                    , "Ocurrió un error intente otra vez",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //cerrar sesión
    public void signOut(FirebaseAuth firebaseAuth,final Activity activity){
        SharedPreferences preferences = activity.getSharedPreferences("USER",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("email");
        editor.remove("password");
        editor.apply();
        firebaseAuth.signOut();
        activity.finish();
    }

    //guardar email y password para el incio de sesión
    public void createPreferencesSignIn(final String email, final String password, final Activity activity){
        SharedPreferences preferences = activity.getSharedPreferences("USER",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.commit();
    }

    public void sendMessage(Chat chat, String idSender, String idReceiver){
        db.collection("chats")
                .document(idSender + "&" + idReceiver)
                .collection("messages")
                .document()
                .set(chat)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "mensaje enviado exitosamente");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "fallo al enviar mensaje" + e);
                    }
                });
    }
}

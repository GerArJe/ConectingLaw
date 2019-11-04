package com.example.conectinglaw.view.fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.conectinglaw.R;
import com.example.conectinglaw.adapter.UserAdapter;
import com.example.conectinglaw.model.Lawyer;
import com.example.conectinglaw.model.User;
import com.example.conectinglaw.repository.FirebaseService;
import com.example.conectinglaw.view.SeleccionarCasoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ChatFragmentActivity extends Fragment {

    private String TAG = "ChatFragmentActivity";

    RecyclerView rvChat;
    FloatingActionButton fabAddCase;

    ArrayList<String> nameReferencesChats;

    FirebaseService firebaseService = new FirebaseService();

    public ChatFragmentActivity(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w(TAG, "Iniciando" + TAG);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_chat_fragment, container, false);

        nameReferencesChats = new ArrayList<>();
        final String userType = getArguments().getString("userType");
        final User user = (User) getArguments().getSerializable("user");
        for (String nameReference : user.getChatList()) {
            nameReferencesChats.add(nameReference);
        }

        rvChat = view.findViewById(R.id.rv_Chat);
        fabAddCase = view.findViewById(R.id.fabAddCase);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        UserAdapter userAdapter = new UserAdapter(nameReferencesChats, R.layout.chat_cardview, new UserAdapter.onItemClickListener() {
            @Override
            public void onItemClick(String nameReferenceChat, int position) {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (userType.equals("client")){
                    goToChat(firebaseUser.getEmail(), nameReferenceChat, userType);
                }else if (userType.equals("lawyer")){
                    goToChat(nameReferenceChat, firebaseUser.getEmail(), userType);
                }
            }
        });

        rvChat.setLayoutManager(linearLayoutManager);
        rvChat.setAdapter(userAdapter);

        fabAddCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), SeleccionarCasoActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

    public void goToChat(String emailClient, String emailLawyer, String userType){
        firebaseService.getMessages(emailClient, emailLawyer,userType, getActivity());
    }

}

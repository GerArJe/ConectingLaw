package com.example.conectinglaw.view.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conectinglaw.R;
import com.example.conectinglaw.adapter.UserAdapter;
import com.example.conectinglaw.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ChatFragmentActivity extends Fragment {

    private String TAG = "ChatFragmentActivity";

    RecyclerView rvChat;
    FloatingActionButton fabAddCase;

    ArrayList<User> users;

    public ChatFragmentActivity(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w(TAG, "Iniciando" + TAG);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_chat_fragment, container, false);

        users = new ArrayList<>();

        getFakeData();

        rvChat = view.findViewById(R.id.rv_Chat);
        fabAddCase = view.findViewById(R.id.fabAddCase);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        UserAdapter userAdapter = new UserAdapter(users, R.layout.chat_cardview, new UserAdapter.onItemClickListener() {
            @Override
            public void onItemClick(User user, int position) {
                Toast.makeText(getContext(), "excelente", Toast.LENGTH_SHORT).show();
            }
        });

        rvChat.setLayoutManager(linearLayoutManager);
        rvChat.setAdapter(userAdapter);

        return view;
    }

    public void getFakeData(){
        User user1 = new User("jose", "Alodsd", "sdasd@gmail.com",
                123, 123);
        User user2 = new User("maria", "dfsdfsd", "dffdfdf@gmail.com",
                123, 123);
        users.add(user1);
        users.add(user2);
    }
}

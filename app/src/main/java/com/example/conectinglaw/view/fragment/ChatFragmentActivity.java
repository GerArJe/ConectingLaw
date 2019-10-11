package com.example.conectinglaw.view.fragment;

import androidx.appcompat.app.AppCompatActivity;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChatFragmentActivity extends Fragment {

    private String TAG = "ChatFragmentActivity";

    RecyclerView rvChat;
    FloatingActionButton fabAddCase;

    public ChatFragmentActivity(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w(TAG, "Iniciando" + TAG);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_chat_fragment, container, false);

        rvChat = view.findViewById(R.id.rv_Chat);
        fabAddCase = view.findViewById(R.id.fabAddCase);

        return view;
    }
}

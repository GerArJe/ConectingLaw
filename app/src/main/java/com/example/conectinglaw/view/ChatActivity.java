package com.example.conectinglaw.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conectinglaw.R;
import com.example.conectinglaw.adapter.ChatAdapter;
import com.example.conectinglaw.model.Chat;
import com.example.conectinglaw.repository.FirebaseService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private String TAG = "ChatActivity";

    RecyclerView rvMessages;
    EditText messageArea;
    ImageView btnSendMesssage, iv_back;
    TextView txtAppbar;

    private List<Chat> chats = new ArrayList<>();
    private String idUser;

    FirebaseUser firebaseUser;
    FirebaseService firebaseService = new FirebaseService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        asociateElemnts();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String idReceiver = getIntent().getExtras().getString("receiver");

        txtAppbar.setText(idReceiver);

        idUser = firebaseUser.getEmail();
        rvMessages.setLayoutManager(new LinearLayoutManager(this));
        final ChatAdapter chatAdapter = new ChatAdapter(chats, idUser);
        rvMessages.setAdapter(chatAdapter);

        btnSendMesssage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chat chat = new Chat(idUser, messageArea.getText().toString());
//                firebaseService.sendMessage(
//                        chat,
//                        idUser,
//                        idReceiver);
                chats.add(chat);
                messageArea.setText("");
                chatAdapter.notifyDataSetChanged();
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void asociateElemnts(){
        rvMessages = findViewById(R.id.rv_messages);
        messageArea = findViewById(R.id.messageArea);
        btnSendMesssage = findViewById(R.id.btn_sendMesssage);
        txtAppbar = findViewById(R.id.txt_appbar);
        iv_back = findViewById(R.id.iv_back);
    }
}

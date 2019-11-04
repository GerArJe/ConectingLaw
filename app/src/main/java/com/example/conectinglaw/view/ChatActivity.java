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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ChatActivity extends AppCompatActivity {

    private String TAG = "ChatActivity";

    RecyclerView rvMessages;
    EditText messageArea;
    ImageView btnSendMesssage, iv_back;
    TextView txtAppbar;

    private ArrayList<Chat> chats = new ArrayList<>();
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
        chats = (ArrayList<Chat>) getIntent().getSerializableExtra("chats");
        final String userType = getIntent().getExtras().getString("userType");

        txtAppbar.setText(idReceiver);

        idUser = firebaseUser.getEmail();
        rvMessages.setLayoutManager(new LinearLayoutManager(this));
        final ChatAdapter chatAdapter = new ChatAdapter(chats, idUser);
        rvMessages.setAdapter(chatAdapter);

        firebaseService.listenForUpdatesMessages(chatAdapter, chats, idUser, idReceiver);

        btnSendMesssage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String place = "America/Bogota";
                TimeZone timeZone = java.util.TimeZone.getTimeZone(place);
                Calendar calendar = Calendar.getInstance(timeZone);
                DateFormat dateFormat = DateFormat.getDateTimeInstance();
                dateFormat.setCalendar(calendar);
                Chat chat = new Chat(idUser,
                        messageArea.getText().toString(),
                        dateFormat.format(new Date()));
                if (userType.equals("client")){
                    firebaseService.sendMessage(
                            chat,
                            idUser,
                            idReceiver);
                }else if (userType.equals("lawyer")){
                    firebaseService.sendMessage(
                            chat,
                            idReceiver,
                            idUser);
                }
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

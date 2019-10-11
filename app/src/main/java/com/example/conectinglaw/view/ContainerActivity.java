package com.example.conectinglaw.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.conectinglaw.R;
import com.example.conectinglaw.view.fragment.ChatFragmentActivity;
import com.example.conectinglaw.view.fragment.ProfileClientFragmentActivity;
import com.example.conectinglaw.view.fragment.ProfileLawyerFragmentActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContainerActivity extends AppCompatActivity {

    private static final String TAG = "ContainerActivity";
    final ProfileLawyerFragmentActivity profileLawyerFragmentActivity =
            new ProfileLawyerFragmentActivity();
    final ProfileClientFragmentActivity profileClientFragmentActivity =
            new ProfileClientFragmentActivity();
    final ChatFragmentActivity chatFragmentActivity =
            new ChatFragmentActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(TAG, "Iniciando" + TAG);
        setContentView(R.layout.activity_container);

        final String userType = getIntent().getExtras().getString("userType");

        //set HomeFragment as Default on first load (Login)
        if (userType.equals("lawyer")){
            if (savedInstanceState == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, profileLawyerFragmentActivity)
                        .commit();
            }
        }else if (userType.equals("client")){
            if (savedInstanceState == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, profileClientFragmentActivity)
                        .commit();
            }
        }

        BottomNavigationView bottombar = findViewById(R.id.bottombar);
        bottombar.setSelectedItemId(R.id.profile);

        bottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.profile:
                        if (userType.equals("lawyer")){
                            addFragment(profileLawyerFragmentActivity);
                        }else if (userType.equals("client")){
                            addFragment(profileClientFragmentActivity);
                        }
                        break;
                    case R.id.chat:
                        addFragment(chatFragmentActivity);
                        break;

                }
                return true;
            }

            //Set fragment
            private void addFragment(Fragment fragment) {
                if (null != fragment) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }
}

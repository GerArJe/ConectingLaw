package com.example.conectinglaw.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.conectinglaw.R;
import com.example.conectinglaw.repository.FirebaseService;
import com.example.conectinglaw.view.fragment.ChatFragmentActivity;
import com.example.conectinglaw.view.fragment.ProfileClientFragmentActivity;
import com.example.conectinglaw.view.fragment.ProfileLawyerFragmentActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ContainerActivity extends AppCompatActivity {

    private static final String TAG = "ContainerActivity";

    final ProfileLawyerFragmentActivity profileLawyerFragmentActivity =
            new ProfileLawyerFragmentActivity();
    final ProfileClientFragmentActivity profileClientFragmentActivity =
            new ProfileClientFragmentActivity();
    final ChatFragmentActivity chatFragmentActivity =
            new ChatFragmentActivity();

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    FirebaseService firebaseService = new FirebaseService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(TAG, "Iniciando" + TAG);
        setContentView(R.layout.activity_container);

        initialize();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mSignOut:
                firebaseService.signOut(firebaseAuth, this);
                Toast.makeText(this, "Se cerro la sesión", Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(ContainerActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.mAbout:
                Toast.makeText(this, "ConectingLaw por Germán Y Alex", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
}

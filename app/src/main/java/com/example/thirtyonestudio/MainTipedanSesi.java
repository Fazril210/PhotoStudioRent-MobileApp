package com.example.thirtyonestudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainTipedanSesi extends AppCompatActivity {

    private TabLayout tab_layout;
    private TabItem tab_studio,tab_outdoor,tab_lainnya;
    private ViewPager view_pager;
    private ImageView back_home;
    private TextView username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tipedan_sesi);

        tab_layout = findViewById(R.id.tabLayout);
        tab_studio = findViewById(R.id.tabStudio);
        tab_outdoor = findViewById(R.id.tabOutdoor);
        tab_lainnya = findViewById(R.id.tabLainnya);
        view_pager = findViewById(R.id.viewPager);
        back_home = findViewById(R.id.IVhome);


        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
                finish();
            }
        });



        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tab_layout.getTabCount());
        view_pager.setAdapter(pagerAdapter);

        tab_layout.setupWithViewPager(view_pager);

    }
}
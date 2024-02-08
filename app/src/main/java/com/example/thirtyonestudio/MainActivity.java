package com.example.thirtyonestudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private Button pesan, tipe_sesi;

    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef;
    private List<Main> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        pesan = findViewById(R.id.button_pesan);
        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent message = new Intent(getApplicationContext(), BuatPesanan.class);
                startActivity(message);
            }
        });

        mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("images");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()){
                    Main upload = postSnapshot.getValue(Main.class);
                    mUploads.add(upload);
                }
                mAdapter  =new MyAdapter(MainActivity.this, mUploads);
                mRecyclerView.setAdapter(mAdapter);

                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        tipe_sesi = findViewById(R.id.button_tipesesi);
        tipe_sesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tipeSesi = new Intent(getApplicationContext(), MainTipedanSesi.class);
                startActivity(tipeSesi);
            }
        });
    }
}
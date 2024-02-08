package com.example.thirtyonestudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;


public class MaternitySession extends AppCompatActivity {

    private Button Maternity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maternity_session);

        Maternity = findViewById(R.id.button1_booking);
        Maternity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sewa = new Intent(getApplicationContext(), BuatPesanan.class);
                startActivity(sewa);
            }
        });

        // Panggil metode setupImageSlider di sini
        setupImageSlider();
    }

    private void setupImageSlider() {
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.maternity2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.maternity, ScaleTypes.FIT));


        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);
    }
}
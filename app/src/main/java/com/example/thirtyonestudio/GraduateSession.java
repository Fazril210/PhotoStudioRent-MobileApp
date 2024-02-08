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

public class GraduateSession extends AppCompatActivity {
    private  Button Graduate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graduate_session);

        Graduate = findViewById(R.id.button1_booking);
        Graduate.setOnClickListener(new View.OnClickListener() {
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
        imageList.add(new SlideModel(R.drawable.graduate, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.graduate2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.graduate3, ScaleTypes.FIT));

        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);
    }
}
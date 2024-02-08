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

public class Wedding_Outdoor extends AppCompatActivity {

    private Button Booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_outdoor);
        Booking = findViewById(R.id.button1_booking);
        Booking.setOnClickListener(new View.OnClickListener() {
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
        imageList.add(new SlideModel(R.drawable.weddingoutdoor1, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.weddingoutdoor2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.weddingoutdoor3, ScaleTypes.FIT));

        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);
    }
}
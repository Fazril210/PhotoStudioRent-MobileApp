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

public class TipeSesiFoto extends AppCompatActivity {

    private Button booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipe_sesi_foto);

        booking = findViewById(R.id.button1_booking);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sewa = new Intent(getApplicationContext(), BuatPesanan.class);
                startActivity(sewa);
            }
        });

        // Panggil metode setupImageSlider di sini
        setupImageSlider();
    }

    // Pindahkan metode setupImageSlider ke luar metode onCreate
    private void setupImageSlider() {
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.group3, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.group2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.group, ScaleTypes.FIT));

        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);
    }
}

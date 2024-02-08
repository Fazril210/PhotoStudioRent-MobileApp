package com.example.thirtyonestudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class Pembayaran extends AppCompatActivity {

    private TextView namaTextView;
    private TextView tanggalTextView;
    private TextView jamTextView;
    private TextView sesiTextView;
    private TextView paketTextView;
    private TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        // Ambil data dari Intent
        String nama = getIntent().getStringExtra("nama");
        String tanggal = getIntent().getStringExtra("tanggal");
        String jam = getIntent().getStringExtra("jam");
        String sesi = getIntent().getStringExtra("sesi");
        String paket = getIntent().getStringExtra("paket");
        int total = getIntent().getIntExtra("total", 0);

        // Temukan TextView dalam tata letak Pembayaran Anda
        TextView namaTextView = findViewById(R.id.namaTextView);
        TextView tanggalTextView = findViewById(R.id.tanggalTextView);
        TextView jamTextView = findViewById(R.id.jamTextView);
        TextView sesiTextView = findViewById(R.id.sesiTextView);
        TextView paketTextView = findViewById(R.id.paketTextView);
        TextView totalTextView = findViewById(R.id.totalTextView);

        // Setel nilai TextView dengan data yang diambil dari Intent
        namaTextView.setText("Nama:     \t" + nama);
        tanggalTextView.setText("Tanggal: \t" + tanggal);
        jamTextView.setText("Jam:      \t\t" + jam);
        sesiTextView.setText("Sesi:       \t" + sesi);
        paketTextView.setText("Paket:     \t" + paket);
        totalTextView.setText("Total:      \t" + formatRupiah(total)); // Gunakan formatRupiah untuk menampilkan total dalam format mata uang Rupiah
    }

    private String formatRupiah(int total) {
        // Menggunakan DecimalFormat untuk format mata uang Rupiah
        DecimalFormat currencyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();

        // Mengatur simbol mata uang ke "Rp. "
        DecimalFormatSymbols symbols = currencyFormat.getDecimalFormatSymbols();
        symbols.setCurrencySymbol("Rp. ");
        currencyFormat.setDecimalFormatSymbols(symbols);

        // Mengatur jumlah digit desimal
        currencyFormat.setMaximumFractionDigits(0);

        return currencyFormat.format(total);
    }
}
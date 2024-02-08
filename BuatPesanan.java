package com.example.thirtyonestudio;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BuatPesanan extends AppCompatActivity {
    private TextView etTanggal;
    private TextView tvjam;
    private TextView Total;
    private Button lanjut;
    private Spinner sesi;
    private Spinner paket;
    private EditText etNama;
    private ImageView ivBack;

    // Firebase
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);

        // Inisialisasi Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("pesanan");

        etTanggal = findViewById(R.id.ETtanggal);
        tvjam = findViewById(R.id.TVjam);
        ivBack = findViewById(R.id.IVback);
        etNama = findViewById(R.id.ETnama);
        sesi = findViewById(R.id.sesifoto);
        paket = findViewById(R.id.paketfoto);
        Total = findViewById(R.id.total);
        lanjut = findViewById(R.id.button_lanjut);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validasi input sebelum menyimpan data
                if (isValidInput()) {
                    // Simpan data ke Firebase
                    simpanDataKeFirebase();

                    // Pindah ke halaman pembayaran dan kirim data sebagai ekstra
                    Intent bayar = new Intent(getApplicationContext(), Pembayaran.class);
                    bayar.putExtra("nama", etNama.getText().toString());
                    bayar.putExtra("tanggal", etTanggal.getText().toString());
                    bayar.putExtra("jam", tvjam.getText().toString());
                    bayar.putExtra("sesi", sesi.getSelectedItem().toString());
                    bayar.putExtra("paket", paket.getSelectedItem().toString());
                    bayar.putExtra("total", hitungTotal()); // Anda mungkin ingin melewatkan total aktual, bukan menghitung ulang
                    startActivity(bayar);
                } else {
                    // Tampilkan peringatan jika input tidak valid
                    Toast.makeText(BuatPesanan.this, "Lengkapi semua data terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Menambahkan pilihan untuk sesi
        ArrayAdapter<CharSequence> sesiAdapter = ArrayAdapter.createFromResource(this,
                R.array.sesi_array, R.layout.custom_spinner_item);
        sesiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sesi.setAdapter(sesiAdapter);

        sesi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Menambahkan pilihan untuk paket
        ArrayAdapter<CharSequence> paketAdapter = ArrayAdapter.createFromResource(this,
                R.array.paket_array, R.layout.custom_spinner_item);
        paketAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paket.setAdapter(paketAdapter);
        paket.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Button kembali ke halaman MainActivity
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });

        tvjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void updateTotal() {
        // Mendapatkan pilihan dari Spinner sesi dan paket
        String selectedSesi = sesi.getSelectedItem().toString();
        String selectedPaket = paket.getSelectedItem().toString();
        int total = 0;
        if (selectedSesi.equals("Wisuda") && selectedPaket.equals("Mythic")) {
            total = 430000;
        } else if ((selectedSesi.equals("Group") || selectedSesi.equals("Family")) && selectedPaket.equals("Mythic")) {
            total = 480000;
        } else if ((selectedSesi.equals("Group") || selectedSesi.equals("Family") || selectedSesi.equals("Wisuda")) && selectedPaket.equals("Legend")) {
            total = 320000;
        } else if ((selectedSesi.equals("Group") || selectedSesi.equals("Family") || selectedSesi.equals("Wisuda")) && selectedPaket.equals("Epic")) {
            total = 180000;
        } else if (selectedSesi.equals("Couple") && selectedPaket.equals("Mythic")) {
            total = 540000;
        } else if (selectedSesi.equals("Couple") && selectedPaket.equals("Legend")) {
            total = 440000;
        } else if (selectedSesi.equals("Prewedding") && selectedPaket.equals("Mythic")) {
            total = 1700000;
        } else if (selectedSesi.equals("Prewedding") && selectedPaket.equals("Legend")) {
            total = 1200000;
        } else if (selectedSesi.equals("Prewedding") && selectedPaket.equals("Epic")) {
            total = 700000;
        } else if (selectedSesi.equals("Prewedding+Gaun") && selectedPaket.equals("Mythic")) {
            total = 2300000;
        } else if (selectedSesi.equals("Prewedding+Gaun") && selectedPaket.equals("Legend")) {
            total = 1800000;
        } else if (selectedSesi.equals("Prewedding+Gaun") && selectedPaket.equals("Epic")) {
            total = 1300000;
        } else if (selectedSesi.equals("Prewedding+Baju Adat Jawa") && selectedPaket.equals("Mythic")) {
            total = 2100000;
        } else if (selectedSesi.equals("Prewedding+Baju Adat Jawa") && selectedPaket.equals("Legend")) {
            total = 1600000;
        } else if (selectedSesi.equals("Prewedding+Baju Adat Jawa") && selectedPaket.equals("Epic")) {
            total = 1100000;
        } else if (selectedSesi.equals("Maternity") && selectedPaket.equals("Mythic")) {
            total = 430000;
        } else if (selectedSesi.equals("Maternity") && selectedPaket.equals("Legend")) {
            total = 280000;
        } else if (selectedSesi.equals("Maternity") && selectedPaket.equals("Epic")) {
            total = 180000;
        }

        Total.setText("Total                             " + formatRupiah(total));
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

    private void showTimePickerDialog() {
        // Mendapatkan waktu saat ini
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Membuat TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        // Menangani aksi setelah pengguna memilih waktu
                        String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);

                        tvjam.setText(selectedTime);
                    }
                },
                hour, minute, true);

        // Menampilkan dialog
        timePickerDialog.show();
    }

    private void showDatePickerDialog() {
        // Mendapatkan tanggal saat ini
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        // Membuat DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        // Menangani aksi setelah pengguna memilih tanggal
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(selectedYear, selectedMonth, selectedDay);

                        // Menggunakan SimpleDateFormat untuk mengatur format tanggal
                        SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-yyyy", Locale.getDefault());
                        String selectedDate = dateFormat.format(selectedCalendar.getTime());

                        etTanggal.setText(selectedDate);
                    }
                },
                year, month, dayOfMonth);

        Calendar minDate = Calendar.getInstance();
        minDate.set(2023, 0, 1);
        datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());

        // Menampilkan dialog
        datePickerDialog.show();
    }

    private void simpanDataKeFirebase() {
        // Ambil data dari input pengguna
        String nama = etNama.getText().toString();
        String tanggal = etTanggal.getText().toString();
        String jam = tvjam.getText().toString();
        String sesiFoto = sesi.getSelectedItem().toString();
        String paketFoto = paket.getSelectedItem().toString();
        int total = hitungTotal(); // Metode untuk menghitung total berdasarkan pilihan sesi dan paket

        // Buat objek Pesanan
        Pesanan pesanan = new Pesanan();
        pesanan.setNama(nama);
        pesanan.setTanggal(tanggal);
        pesanan.setJam(jam);
        pesanan.setSesi(sesiFoto);
        pesanan.setPaket(paketFoto);
        pesanan.setTotal(total);

        // Simpan objek Pesanan ke Firebase
        databaseReference.push().setValue(pesanan);
    }

    private int hitungTotal() {
        String selectedSesi = sesi.getSelectedItem().toString();
        String selectedPaket = paket.getSelectedItem().toString();
        int total = 0;

        if (selectedSesi.equals("Wisuda") && selectedPaket.equals("Mythic")) {
            total = 430000;
        } else if ((selectedSesi.equals("Group") || selectedSesi.equals("Family")) && selectedPaket.equals("Mythic")) {
            total = 480000;
        } else if ((selectedSesi.equals("Group") || selectedSesi.equals("Family") || selectedSesi.equals("Wisuda")) && selectedPaket.equals("Legend")) {
            total = 320000;
        } else if ((selectedSesi.equals("Group") || selectedSesi.equals("Family") || selectedSesi.equals("Wisuda")) && selectedPaket.equals("Epic")) {
            total = 180000;
        } else if (selectedSesi.equals("Couple") && selectedPaket.equals("Mythic")) {
            total = 540000;
        } else if (selectedSesi.equals("Couple") && selectedPaket.equals("Legend")) {
            total = 440000;
        } else if (selectedSesi.equals("Prewedding") && selectedPaket.equals("Mythic")) {
            total = 1700000;
        } else if (selectedSesi.equals("Prewedding") && selectedPaket.equals("Legend")) {
            total = 1200000;
        } else if (selectedSesi.equals("Prewedding") && selectedPaket.equals("Epic")) {
            total = 700000;
        } else if (selectedSesi.equals("Prewedding+Gaun") && selectedPaket.equals("Mythic")) {
            total = 2300000;
        } else if (selectedSesi.equals("Prewedding+Gaun") && selectedPaket.equals("Legend")) {
            total = 1800000;
        } else if (selectedSesi.equals("Prewedding+Gaun") && selectedPaket.equals("Epic")) {
            total = 1300000;
        } else if (selectedSesi.equals("Prewedding+Baju Adat Jawa") && selectedPaket.equals("Mythic")) {
            total = 2100000;
        } else if (selectedSesi.equals("Prewedding+Baju Adat Jawa") && selectedPaket.equals("Legend")) {
            total = 1600000;
        } else if (selectedSesi.equals("Prewedding+Baju Adat Jawa") && selectedPaket.equals("Epic")) {
            total = 1100000;
        } else if (selectedSesi.equals("Maternity") && selectedPaket.equals("Mythic")) {
            total = 430000;
        } else if (selectedSesi.equals("Maternity") && selectedPaket.equals("Legend")) {
            total = 280000;
        } else if (selectedSesi.equals("Maternity") && selectedPaket.equals("Epic")) {
            total = 180000;
        }

        return total;
    }



    private boolean isValidInput() {
        // Validasi semua input sesuai kebutuhan Anda
        String nama = etNama.getText().toString().trim();
        String tanggal = etTanggal.getText().toString().trim();
        String jam = tvjam.getText().toString().trim();

        if (nama.isEmpty() || tanggal.isEmpty() || jam.isEmpty()) {
            // Tampilkan pesan kesalahan jika ada input yang kosong
            Toast.makeText(this, "Mohon lengkapi semua data", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}

package com.example.thirtyonestudio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private ImageView logoStudio;
    private Button cetakStruk;

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

        // Temukan TextView dan ImageView dalam tata letak Pembayaran Anda
        namaTextView = findViewById(R.id.namaTextView);
        tanggalTextView = findViewById(R.id.tanggalTextView);
        jamTextView = findViewById(R.id.jamTextView);
        sesiTextView = findViewById(R.id.sesiTextView);
        paketTextView = findViewById(R.id.paketTextView);
        totalTextView = findViewById(R.id.totalTextView);
        logoStudio = findViewById(R.id.logostudio);

        cetakStruk = findViewById(R.id.btnPrint);

        // Setel nilai TextView dengan data yang diambil dari Intent
        namaTextView.setText("Nama      : \t" + nama);
        tanggalTextView.setText("Tanggal  : \t" + tanggal);
        jamTextView.setText("Jam        : \t\t" + jam);
        sesiTextView.setText("Sesi        : \t" + sesi);
        paketTextView.setText("Paket      : \t" + paket);
        totalTextView.setText("Total       : \t" + formatRupiah(total)); // Gunakan formatRupiah untuk menampilkan total dalam format mata uang Rupiah

        cetakStruk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPDF();
            }
        });
    }

    private void createPDF() {
        // Membuat file PDF di direktori internal aplikasi
        File internalDir = getFilesDir();
        String filePath = internalDir + "/strukThirtyOneStudio.pdf";

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Menambahkan gambar dari ImageView ke PDF
            Image logo = getImageFromImageView(logoStudio);
            float pageWidth = document.getPageSize().getWidth();
            float logoWidth = logo.getScaledWidth();
            float horizontalPosition = (pageWidth - logoWidth) / 2 + 370;

            // Set posisi gambar di tengah atas
            float verticalPosition = document.getPageSize().getTop() - logo.getScaledHeight() + 170; // Sesuaikan nilai sesuai kebutuhan
            logo.setAbsolutePosition(horizontalPosition, verticalPosition);
            logo.scaleAbsolute(150, 90); // Set the size of the logo

            // Menambahkan gambar ke PDF menggunakan document.add()
            document.add(logo);
            Paragraph addressParagraph = new Paragraph("Jl. Perum Griya Alam, Pasir Angin, Kec. Cileungsi, Kabupaten Bogor, Jawa Barat 16820");
            addressParagraph.setAlignment(Element.ALIGN_CENTER); // Menetapkan teks ke tengah
            document.add(new Paragraph("\n\n\n\n\n"));
            document.add(addressParagraph);
            document.add(new Paragraph("\n\n")); // Memberikan beberapa baris kosong sebelum alamat

            // Membuat objek PdfPTable dengan 1 kolom
            PdfPTable table = new PdfPTable(1);

            // Set lebar kolom
            table.setWidthPercentage(50);  // Sesuaikan dengan kebutuhan Anda, misalnya, 50% dari lebar halaman

            // Menambahkan data ke dalam tabel dengan font yang lebih kecil
            Font smallFont = new Font(Font.FontFamily.HELVETICA, 8);  // Sesuaikan ukuran font dengan kebutuhan Anda

            // Ambil warna dari resource colors
            int backgroundColorCell = ContextCompat.getColor(this, R.color.warna5);  // Ganti dengan resource colors yang diinginkan

            PdfPCell cell = new PdfPCell(new Phrase("" + namaTextView.getText(), smallFont));
            // Set warna latar belakang
            cell.setBackgroundColor(new BaseColor(backgroundColorCell)); // Ganti dengan warna dari resource colors
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("" + tanggalTextView.getText(), smallFont));
            // Set warna latar belakang
            cell.setBackgroundColor(new BaseColor(backgroundColorCell)); // Ganti dengan warna dari resource colors
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("" + jamTextView.getText(), smallFont));
            // Set warna latar belakang
            cell.setBackgroundColor(new BaseColor(backgroundColorCell)); // Ganti dengan warna dari resource colors
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("" + sesiTextView.getText(), smallFont));
            // Set warna latar belakang
            cell.setBackgroundColor(new BaseColor(backgroundColorCell)); // Ganti dengan warna dari resource colors
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("" + paketTextView.getText(), smallFont));
            // Set warna latar belakang
            cell.setBackgroundColor(new BaseColor(backgroundColorCell)); // Ganti dengan warna dari resource colors
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("" + totalTextView.getText(), smallFont));
            // Set warna latar belakang
            cell.setBackgroundColor(new BaseColor(backgroundColorCell)); // Ganti dengan warna dari resource colors
            table.addCell(cell);

            // Menambahkan tabel ke dalam dokumen
            document.add(table);

            document.add(new Paragraph("\n\n\n"));
            Paragraph ucapan = new Paragraph("Terima Kasih telah memesan studio di tempat kami.");
            ucapan.setAlignment(Element.ALIGN_CENTER);
            document.add(ucapan);

            document.close();

            // Meluncurkan layanan cetak
            printPDF(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("PDF Creation", "Terjadi kesalahan saat membuat PDF.", e);
            Toast.makeText(this, "Terjadi kesalahan saat membuat PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private Image getImageFromImageView(ImageView imageView) throws IOException, BadElementException {
        // Mengambil drawable dari ImageView
        Drawable drawable = imageView.getDrawable();

        // Mengambil bitmap dari drawable
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();

        // Mengubah bitmap menjadi byte array
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        // Mengambil gambar dari byte array
        try {
            return Image.getInstance(byteArray);
        } catch (BadElementException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printPDF(String filePath) {
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        String jobName = getString(R.string.app_name) + " Document";

        printManager.print(jobName, new MyPrintDocumentAdapter(filePath), null);
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

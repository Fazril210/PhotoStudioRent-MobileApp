package com.example.thirtyonestudio;
import java.util.Date;


public class Pesanan {
    private String tanggal;
    private String jam;
    private String nama;
    private String sesi;
    private String paket;
    private int total;

    public Pesanan(){

    }

    public Pesanan(String tanggal, String jam, String nama, String sesi, String paket, int total) {
        this.tanggal = tanggal;
        this.jam = jam;
        this.nama = nama;
        this.sesi = sesi;
        this.paket = paket;
        this.total = total;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSesi() {
        return sesi;
    }

    public void setSesi(String sesi) {
        this.sesi = sesi;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

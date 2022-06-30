package com.example.sigap1;

public class Riwayat {
    private String nama;
    private String nik;

    public Riwayat(String nama, String nik) {
        this.nama = nama;
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }
}

package com.example.sigap1;

public class Riwayat {
    private String jenis;
    private String detail;
    private String lokasi;
    private String tanggal;
    private String id;

    public Riwayat(String jenis, String detail, String lokasi, String tanggal, String id) {
        this.jenis = jenis;
        this.detail = detail;
        this.lokasi = lokasi;
        this.tanggal = tanggal;
        this.id=id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

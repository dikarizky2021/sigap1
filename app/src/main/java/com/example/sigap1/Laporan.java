package com.example.sigap1;

public class Laporan {

    private String id,jenis, detail, nik, nama,tindakan,longi,lati,lokasi,username,waktu,kecamatan,kelurahan;

    public Laporan(String id, String jenis, String detail, String nik, String nama, String tindakan, String longi, String lati, String lokasi, String username, String waktu, String kecamatan, String kelurahan) {
        this.id = id;
        this.jenis = jenis;
        this.detail = detail;
        this.nik = nik;
        this.nama = nama;
        this.tindakan = tindakan;
        this.longi = longi;
        this.lati = lati;
        this.lokasi = lokasi;
        this.username = username;
        this.waktu = waktu;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getId() {
        return id;
    }

    public String getJenis() {
        return jenis;
    }

    public String getDetail() {
        return detail;
    }

    public String getNik() {
        return nik;
    }

    public String getNama() {
        return nama;
    }

    public String getTindakan() {
        return tindakan;
    }

    public String getLongi() {
        return longi;
    }

    public String getLati() {
        return lati;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getUsername() {
        return username;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }
}

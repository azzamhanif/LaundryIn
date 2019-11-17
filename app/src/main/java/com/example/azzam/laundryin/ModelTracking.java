package com.example.azzam.laundryin;

/**
 * Created by azzam on 10/28/2019.
 */

public class ModelTracking {
    private String id_transaksi;
    private String paket;
    private String berat;
    private String tanggal_masuk;
    private String harga;

    public ModelTracking(String id_transaksi, String paket, String berat, String tanggal_masuk, String harga) {
        this.id_transaksi = id_transaksi;
        this.paket = paket;
        this.berat = berat;
        this.tanggal_masuk = tanggal_masuk;
        this.harga = harga;
    }

    public ModelTracking() {
    }

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getTanggal_masuk() {
        return tanggal_masuk;
    }

    public void setTanggal_masuk(String tanggal_masuk) {
        this.tanggal_masuk = tanggal_masuk;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}

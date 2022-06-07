package com.example.uts_10119294_lingga.models;

import java.io.Serializable;

/*
 * NIM : 10119294
 * NAMA : Lingga Juliansyah
 * Kelas : IF-7
 * */
public class Note implements Serializable {
    private int id;
    private String judul;
    private String kategori;
    private String tanggal;
    private String isi;

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getJudul() {
        return judul;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTanggal() {
        return tanggal;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

}

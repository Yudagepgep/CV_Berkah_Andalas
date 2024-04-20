package com.aplikasi.ADMIN.Pemesanan_Costumer;

public class help_data_pemesanan {

    private  String id;
    private  String nama_user;
    private  String tanggal;
    private  String banyak_pesanan;
    private  String jenis_pesanan;
    private  String keterangan;

    public help_data_pemesanan(String id, String nama_user,String tanggal,String banyak_pesanan,String jenis_pesanan,String keterangan){
        this.id= id;
        this.tanggal=tanggal;
        this.nama_user=nama_user;
        this.banyak_pesanan = banyak_pesanan;
        this.jenis_pesanan = jenis_pesanan;
        this.keterangan = keterangan;
    }

    public String getId() {
        return id;
    }

    public String getNama_user() {
        return nama_user;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getBanyak_pesanan() {
        return banyak_pesanan;
    }

    public String getJenis_pesanan() {
        return jenis_pesanan;
    }

    public String getKeterangan() {
        return keterangan;
    }
}

package com.aplikasi.ADMIN.Bahan;

public class help_bahan {

    private String id_bahan;
    private String nama_bahan;
    private String tinggi_bahan;
    private String lebar_bahan;
    private String harga_bahan;
    private String satuan;

    public help_bahan(String id_bahan,String nama_bahan,String tinggi_bahan,String lebar_bahan,
                      String harga_bahan, String satuan){

        this.id_bahan=id_bahan;
        this.nama_bahan=nama_bahan;
        this.tinggi_bahan=tinggi_bahan;
        this.lebar_bahan=lebar_bahan;
        this.harga_bahan=harga_bahan;
        this.satuan=satuan;

    }

    public String getId_bahan() {
        return id_bahan;
    }

    public String getNama_bahan() {
        return nama_bahan;
    }

    public String getTinggi_bahan() {
        return tinggi_bahan;
    }

    public String getLebar_bahan() {
        return lebar_bahan;
    }

    public String getHarga_bahan() {
        return harga_bahan;
    }

    public String getSatuan() {
        return satuan;
    }
}

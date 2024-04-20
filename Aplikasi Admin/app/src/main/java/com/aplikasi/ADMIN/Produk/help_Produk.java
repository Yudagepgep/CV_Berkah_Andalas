package com.aplikasi.ADMIN.Produk;

public class help_Produk {

    private String id_produk;
    private String nama_produk;
    private String tinggi_cetak;
    private String lebar_cetak;
    private String id_bahan;
    private String id_plat;

    public  help_Produk (String id_produk,String nama_produk, String tinggi_cetak, String lebar_cetak,
                         String id_bahan, String id_plat) {


        this.id_produk = id_produk;
        this.nama_produk = nama_produk;
        this.tinggi_cetak = tinggi_cetak;
        this.lebar_cetak = lebar_cetak;
        this.id_bahan = id_bahan;
        this.id_plat = id_plat;

    }

    public String getId_produk() {
        return id_produk;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public String getTinggi_cetak() {
        return tinggi_cetak;
    }

    public String getLebar_cetak() {
        return lebar_cetak;
    }

    public String getId_bahan() {
        return id_bahan;
    }

    public String getId_plat() {
        return id_plat;
    }
}

package com.aplikasi.ADMIN.Plat;

public class help_plat {
    private String id_plat;
    private String nama_plat;
    private String harga_plat;
    private String upah_cetak;

    public help_plat(String id_plat,String nama_plat, String harga_plat,String upah_cetak){

        this.id_plat=id_plat;
        this.nama_plat=nama_plat;
        this.harga_plat=harga_plat;
        this.upah_cetak=upah_cetak;
    }

    public String getId_plat() {
        return id_plat;
    }

    public String getNama_plat() {
        return nama_plat;
    }

    public String getHarga_plat() {
        return harga_plat;
    }

    public String getUpah_cetak() {
        return upah_cetak;
    }
}

package com.aplikasi.ADMIN.LAPORAN;

public class help_laporan {

    private String id;
    private String tanggal;
    private String costumer;
    private String produk;
    private String nama_bahan;
    private String nama_plat;
    private String quantity;
    private String lebar_bahan;
    private String tinggi_bahan;
    private String lebar_cetak;
    private String tinggi_cetak;
    private String banyak_bahan;
    private String satuan;
    private String total_harga;
    private String jawab;
    private String status;

    public help_laporan(String id, String tanggal, String costumer, String nama_produk, String nama_bahan, String nama_plat, String quantity
            , String lebar_cetak, String tinggi_cetak, String lebar_bahan, String tinggi_bahan, String banyak_bahan, String satuan, String total_harga, String jawab, String status ) {
        this.id = id;
        this.tanggal = tanggal;
        this.costumer = costumer;
        this.produk = nama_produk;
        this.nama_bahan = nama_bahan;
        this.nama_plat = nama_plat;
        this.quantity = quantity;
        this.lebar_bahan = lebar_bahan;
        this.tinggi_bahan = tinggi_bahan;
        this.lebar_cetak= lebar_cetak;
        this.tinggi_cetak= tinggi_cetak;
        this.banyak_bahan = banyak_bahan;
        this.satuan= satuan;
        this.total_harga = total_harga;
        this.jawab = jawab;
        this.status= status;


    }

    public String getId() {
        return id;
    }



    public String getTanggal() {
        return tanggal;
    }

    public String getCostumer() {
        return costumer;
    }


    public String getProduk() {
        return produk;
    }

    public String getNama_bahan() {
        return nama_bahan;
    }

    public String getNama_plat() {
        return nama_plat;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    public String getLebar_bahan() {
        return lebar_bahan;
    }

    public String getTinggi_bahan() {
        return tinggi_bahan;
    }

    public String getLebar_cetak() {
        return lebar_cetak;
    }

    public String getTinggi_cetak() {
        return tinggi_cetak;
    }

    public String getBanyak_bahan() {
        return banyak_bahan;
    }

    public void setBanyak_bahan(String banyak_bahan) {
        this.banyak_bahan = banyak_bahan;
    }


    public String getSatuan() {
        return satuan;
    }

    public String getTotal_harga() {
        return total_harga;
    }


    public String getJawab() {
        return jawab;
    }


    public String getStatus() {
        return status;
    }



}

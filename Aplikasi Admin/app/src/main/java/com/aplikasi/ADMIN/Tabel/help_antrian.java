package com.aplikasi.ADMIN.Tabel;

public class help_antrian {
    private String id;
    private String user;
    private String produk;
    private String tanggal;
    private String quantity;
    private String banyak_bahan;
    private String laba;
    private String total_harga;
    private String penaggung_jawab;
    private String status;

    public  help_antrian ( String id,String user, String produk, String tanggal, String quantity, String banyak_bahan,
                            String laba, String total_harga,String penaggung_jawab, String status){


        this.id = id;
        this.user = user;
        this.produk = produk;
        this.tanggal = tanggal;
        this.quantity = quantity;
        this.banyak_bahan = banyak_bahan;
        this.laba = laba;
        this.total_harga = total_harga;
        this.penaggung_jawab = penaggung_jawab;
        this.status= status;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getProduk() {
        return produk;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getBanyak_bahan() {
        return banyak_bahan;
    }


    public String getLaba() {
        return laba;
    }

    public String getTotal_harga() {
        return total_harga;
    }

    public String getPenaggung_jawab() {
        return penaggung_jawab;
    }

    public String getStatus() {
        return status;
    }
}

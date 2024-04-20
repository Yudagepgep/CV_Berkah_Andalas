package com.aplikasi.ADMIN.LAPORAN;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.aplikasi.ADMIN.R;

import androidx.appcompat.app.AppCompatActivity;

public class Detail_Laporan extends AppCompatActivity {
    TextView prd,plt,bhn,stn,tgl,cstm,usr,l_ctk,l_bhn,t_bhn,t_ctk,qun,byk_bhn, ttl_byr, jawab,sts;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__laporan);

        tgl =(TextView) findViewById(R.id.txt_tanggal);
        cstm =(TextView) findViewById(R.id.txt_costumer);
        prd =(TextView) findViewById(R.id.txt_pdk);
        bhn =(TextView) findViewById(R.id.txt_bhn);
        plt =(TextView) findViewById(R.id.txt_plt);
        qun = (TextView) findViewById(R.id.txt_quantity);
        l_ctk =(TextView) findViewById(R.id.txt_lb_cetak);
        t_ctk =(TextView) findViewById(R.id.txt_tg_cetak);
        l_bhn =(TextView) findViewById(R.id.txt_lb_bahan);
        t_bhn =(TextView) findViewById(R.id.txt_tg_bahan);
        byk_bhn =(TextView) findViewById(R.id.txt_banyak_bahan);
        stn=(TextView) findViewById(R.id.txt_st);
        ttl_byr =(TextView) findViewById(R.id.txt_total_harga);
        jawab = (TextView) findViewById(R.id.txt_jwb);
        sts = (TextView) findViewById(R.id.txt_status);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");


        tgl.setText(Laporan_Pesanan.arrayListproduct.get(position).getTanggal());
        cstm.setText(Laporan_Pesanan.arrayListproduct.get(position).getCostumer());
        prd.setText(Laporan_Pesanan.arrayListproduct.get(position).getProduk());
        bhn.setText(Laporan_Pesanan.arrayListproduct.get(position).getNama_bahan());
        plt.setText(Laporan_Pesanan.arrayListproduct.get(position).getNama_plat());
       qun.setText(Laporan_Pesanan.arrayListproduct.get(position).getQuantity());
        l_bhn.setText(Laporan_Pesanan.arrayListproduct.get(position).getLebar_bahan());
        t_bhn.setText(Laporan_Pesanan.arrayListproduct.get(position).getTinggi_cetak());
        l_ctk.setText(Laporan_Pesanan.arrayListproduct.get(position).getLebar_cetak());
        t_ctk.setText(Laporan_Pesanan.arrayListproduct.get(position).getTinggi_cetak());
       byk_bhn.setText(Laporan_Pesanan.arrayListproduct.get(position).getBanyak_bahan());
       stn.setText(Laporan_Pesanan.arrayListproduct.get(position).getSatuan());
       ttl_byr.setText(Laporan_Pesanan.arrayListproduct.get(position).getTotal_harga());
       jawab.setText(Laporan_Pesanan.arrayListproduct.get(position).getJawab());
       sts.setText(Laporan_Pesanan.arrayListproduct.get(position).getStatus());//


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            new AlertDialog.Builder(this)
                    .setMessage("Apakah Anda Ingin Keluar?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            moveTaskToBack(true);
                            finish();

                        }
                    })
                    .setNegativeButton("Tidak", null)
                    .show();
        }
        return true;

    }
}

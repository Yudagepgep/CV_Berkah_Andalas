package com.aplikasi.ADMIN.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.aplikasi.ADMIN.LAPORAN.Detail_Laporan;
import com.aplikasi.ADMIN.LAPORAN.Laporan_Pesanan;
import com.aplikasi.ADMIN.LAPORAN.help_laporan;
import com.aplikasi.ADMIN.R;

import java.util.ArrayList;


public class Laporan_Adapter extends ArrayAdapter<help_laporan> {


    private static Context context;
    LayoutInflater inflater;
    ArrayList<help_laporan> arrayListproduct;



    public Laporan_Adapter(Laporan_Pesanan context, ArrayList<help_laporan> arrayListproduct) {
        super(context,R.layout.laporan_list,arrayListproduct);
        inflater = LayoutInflater.from(context);

        this.context = context;
        this.arrayListproduct = arrayListproduct;

    }

    @Override
    public int getCount() {
        return arrayListproduct.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setfilter(ArrayList<help_laporan>productAll){
        arrayListproduct=new ArrayList<help_laporan>();
        arrayListproduct.addAll(productAll);
        notifyDataSetChanged();

    }






    @Override
    public View getView(final int position,  View convertView, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.laporan_list, viewGroup, false);
        TextView produk = view.findViewById(R.id.produk);
        TextView bahan = view.findViewById(R.id.bahan);
        TextView tanggal = view.findViewById(R.id.tgl);
        TextView costumer = view.findViewById(R.id.costumer);
        TextView tot_hg = view.findViewById(R.id.total);
        TextView sts = view.findViewById(R.id.satus);
        Button deleted = view.findViewById(R.id.hapus);
        Button Detail_Data = view.findViewById(R.id.detail);


        tanggal.setText(arrayListproduct.get(position).getTanggal());
        costumer.setText(arrayListproduct.get(position).getCostumer());
        produk.setText(arrayListproduct.get(position).getProduk());
        bahan.setText(arrayListproduct.get(position).getNama_bahan());
        tot_hg.setText(arrayListproduct.get(position).getTotal_harga());
        sts.setText(arrayListproduct.get(position).getStatus());

        Detail_Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Detail_Laporan.class)
                        .putExtra("position",position);
                context.startActivity(intent);
            }
        });

        deleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Laporan_Pesanan.deleteData(arrayListproduct.get(position).getId());

            }
        });

        return view;
    }

}

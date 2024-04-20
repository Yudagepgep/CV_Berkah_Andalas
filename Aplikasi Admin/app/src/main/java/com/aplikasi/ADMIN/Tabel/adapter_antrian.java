package com.aplikasi.ADMIN.Tabel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.aplikasi.ADMIN.R;


import java.util.ArrayList;


public class adapter_antrian extends ArrayAdapter<help_antrian> {

    private static Context context;
    LayoutInflater inflater;
    ArrayList<help_antrian> arrayListAntriant;

    public adapter_antrian( Tabel_Antrian context, ArrayList<help_antrian> arrayListAntriant) {
        super(context, R.layout.tabel_antrian_list,arrayListAntriant);
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.arrayListAntriant = arrayListAntriant;
    }

    @Override
    public int getCount() {
        return arrayListAntriant.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setfilter(ArrayList<help_antrian>productAll){
        arrayListAntriant=new ArrayList<help_antrian>();
        arrayListAntriant.addAll(productAll);
        notifyDataSetChanged();

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        View view = inflater.inflate(R.layout.tabel_antrian_list, viewGroup, false);
        TextView produk = view.findViewById(R.id.produk);
        TextView user = view.findViewById(R.id.usr);
        TextView tgl = view.findViewById(R.id.tgal);
        TextView quan = view.findViewById(R.id.quantit);
        TextView bahn = view.findViewById(R.id.by_bhn);
        TextView laba = view.findViewById(R.id.lba);
        TextView tot_hg = view.findViewById(R.id.tot_hrg);
        TextView jwb = view.findViewById(R.id.p_jwb);
        TextView sta = view.findViewById(R.id.stus);
        Button Update = view.findViewById(R.id.edit);

        user.setText(arrayListAntriant.get(position).getUser());
        produk.setText(arrayListAntriant.get(position).getProduk());
        tgl.setText(arrayListAntriant.get(position).getTanggal());
        quan.setText(arrayListAntriant.get(position).getQuantity());
        bahn.setText(arrayListAntriant.get(position).getBanyak_bahan());
        laba.setText(arrayListAntriant.get(position).getLaba());
        tot_hg.setText(arrayListAntriant.get(position).getTotal_harga());
        jwb.setText(arrayListAntriant.get(position).getPenaggung_jawab());
        sta.setText(arrayListAntriant.get(position).getStatus());

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Edit_Data_Antrian.class);
                intent.putExtra("id", arrayListAntriant.get(position).getId());
                intent.putExtra("id_user", arrayListAntriant.get(position).getUser());
                intent.putExtra("id_produk", arrayListAntriant.get(position).getProduk());
                intent.putExtra("tanggal", arrayListAntriant.get(position).getTanggal());
                intent.putExtra("quantity", arrayListAntriant.get(position).getQuantity());
                intent.putExtra("laba", arrayListAntriant.get(position).getLaba());
                intent.putExtra("jawab", arrayListAntriant.get(position).getPenaggung_jawab());
                intent.putExtra("status", arrayListAntriant.get(position).getStatus());
                context.startActivity(intent);
            }
        });


        return view;
    }
}

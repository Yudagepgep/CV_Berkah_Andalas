package com.aplikasi.ADMIN.Plat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.aplikasi.ADMIN.Bahan.Edit_Bahan;
import com.aplikasi.ADMIN.Bahan.help_bahan;
import com.aplikasi.ADMIN.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class adapter_plat extends ArrayAdapter<help_plat> {

    private static Context context;
    LayoutInflater inflater;
    ArrayList<help_plat> arrayListPlat;

    public adapter_plat( Data_Plat context,   ArrayList<help_plat> arrayListPlat) {
        super(context, R.layout.data_plat_list, arrayListPlat);

        inflater = LayoutInflater.from(context);
        this.context = context;
        this.arrayListPlat = arrayListPlat;
    }
    @Override
    public int getCount() {
        return arrayListPlat.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setfilter(ArrayList<help_plat>productAll){
        arrayListPlat=new ArrayList<help_plat>();
        arrayListPlat.addAll(productAll);
        notifyDataSetChanged();

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.data_plat_list, viewGroup, false);
        TextView id = view.findViewById(R.id.plt_id_plat);
        TextView nama = view.findViewById(R.id.plt_nama_plat);
        TextView harga = view.findViewById(R.id.plt_harga_plat);
        TextView upah = view.findViewById(R.id.plt_upah_cetak);
        Button update = view.findViewById(R.id.Edit_Plat);
        Button hapus = view.findViewById(R.id.hapus_Plat);

        id.setText(arrayListPlat.get(position).getId_plat());
        nama.setText(arrayListPlat.get(position).getNama_plat());
        harga.setText(arrayListPlat.get(position).getHarga_plat());
        upah.setText(arrayListPlat.get(position).getUpah_cetak());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Edit_Plat.class);
                intent.putExtra("id_plat", arrayListPlat.get(position).getId_plat());
                intent.putExtra("nama", arrayListPlat.get(position).getNama_plat());
                intent.putExtra("harga", arrayListPlat.get(position).getHarga_plat());
                intent.putExtra("upah", arrayListPlat.get(position).getUpah_cetak());
                context.startActivity(intent);

            }
        });
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data_Plat.deletPlat(arrayListPlat.get(position).getId_plat());

            }
        });

        return view;
    }
}

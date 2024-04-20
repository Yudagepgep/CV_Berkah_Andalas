package com.aplikasi.ADMIN.Bahan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.aplikasi.ADMIN.Produk.Data_Produk;
import com.aplikasi.ADMIN.Produk.Edit_Produk;
import com.aplikasi.ADMIN.Produk.help_Produk;
import com.aplikasi.ADMIN.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class adapter_bahan extends ArrayAdapter<help_bahan> {

    private static Context context;
    LayoutInflater inflater;
    ArrayList<help_bahan> arrayListBahan;

    public adapter_bahan(Data_Bahan  context, ArrayList<help_bahan>  arrayListBahan) {
        super(context, R.layout.data_bahan_list,arrayListBahan);

        inflater = LayoutInflater.from(context);
        this.context = context;
        this.arrayListBahan = arrayListBahan;
    }
    @Override
    public int getCount() {
        return arrayListBahan.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setfilter(ArrayList<help_bahan>productAll){
        arrayListBahan=new ArrayList<help_bahan>();
        arrayListBahan.addAll(productAll);
        notifyDataSetChanged();

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        View view = inflater.inflate(R.layout.data_bahan_list, viewGroup, false);
        TextView id_bahan = view.findViewById(R.id.bhn_id_bahan);
        TextView nama_bahan = view.findViewById(R.id.bhn_nama_bahan);
        TextView tinggi_bahan = view.findViewById(R.id.bhn_tinggi_bahan);
        TextView lebar_bahan = view.findViewById(R.id.bhn_lebar_bahan);
        TextView harga_bahan = view.findViewById(R.id.bhn_harga_bahan);
        TextView  satuan = view.findViewById(R.id.bhn_satuan);
        Button Update = view.findViewById(R.id.Edit_Bahan);
        Button hapus = view.findViewById(R.id.hapus_bahan);

        id_bahan.setText(arrayListBahan.get(position).getId_bahan());
        nama_bahan.setText(arrayListBahan.get(position).getNama_bahan());
        tinggi_bahan.setText(arrayListBahan.get(position).getTinggi_bahan());
        lebar_bahan.setText(arrayListBahan.get(position).getLebar_bahan());
        harga_bahan.setText(arrayListBahan.get(position).getHarga_bahan());
        satuan.setText(arrayListBahan.get(position).getSatuan());

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Edit_Bahan.class);

                intent.putExtra("id_bahan", arrayListBahan.get(position).getId_bahan());
                intent.putExtra("nama", arrayListBahan.get(position).getNama_bahan());
                intent.putExtra("tinggi", arrayListBahan.get(position).getTinggi_bahan());
                intent.putExtra("lebar", arrayListBahan.get(position).getLebar_bahan());
                intent.putExtra("harga", arrayListBahan.get(position).getHarga_bahan());
                intent.putExtra("satuan", arrayListBahan.get(position).getSatuan());
                context.startActivity(intent);

            }
        });
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data_Bahan.DeletBahan(arrayListBahan.get(position).getId_bahan());
            }
        });





        return view;
    }


    }

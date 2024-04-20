package com.aplikasi.ADMIN.Produk;

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

public class Adapter_Produk extends ArrayAdapter<help_Produk> {
    private static Context context;
    LayoutInflater inflater;
    ArrayList<help_Produk> arrayListProduk;

    public Adapter_Produk( Data_Produk context,   ArrayList<help_Produk> arrayListProduk) {
        super(context,R.layout.produk_list,arrayListProduk);
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.arrayListProduk = arrayListProduk;
    }
    @Override
    public int getCount() {
        return arrayListProduk.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setfilter(ArrayList<help_Produk>productAll){
        arrayListProduk=new ArrayList<help_Produk>();
        arrayListProduk.addAll(productAll);
        notifyDataSetChanged();

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        View view = inflater.inflate(R.layout.produk_list, viewGroup, false);

        TextView id_produk = view.findViewById(R.id.txt_id_produk);
        TextView nama_produk = view.findViewById(R.id.txt_nama_produk);
        TextView tinggi = view.findViewById(R.id.txt_tinggi_cetak);
        TextView lebar = view.findViewById(R.id.txt_lebar_cetak);
        TextView id_bahan = view.findViewById(R.id.txt_id_bahan);
        TextView id_plat = view.findViewById(R.id.txt_id_plat);
        Button Update = view.findViewById(R.id.Edit_Produk);
        Button hapus = view.findViewById(R.id.hapus_produk);

        id_produk.setText(arrayListProduk.get(position).getId_produk());
        nama_produk.setText(arrayListProduk.get(position).getNama_produk());
        tinggi.setText(arrayListProduk.get(position).getTinggi_cetak());
        lebar.setText(arrayListProduk.get(position).getLebar_cetak());
        id_bahan.setText(arrayListProduk.get(position).getId_bahan());
        id_plat.setText(arrayListProduk.get(position).getId_plat());

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data_Produk.DeletProduk(arrayListProduk.get(position).getId_produk());
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Edit_Produk.class);

                intent.putExtra("id_produk", arrayListProduk.get(position).getId_produk());
                intent.putExtra("nama", arrayListProduk.get(position).getNama_produk());
                intent.putExtra("tinggi", arrayListProduk.get(position).getTinggi_cetak());
                intent.putExtra("lebar", arrayListProduk.get(position).getLebar_cetak());
                intent.putExtra("bahan", arrayListProduk.get(position).getId_bahan());
                intent.putExtra("plat", arrayListProduk.get(position).getId_plat());
                context.startActivity(intent);
            }
        });


        return view;
    }
}

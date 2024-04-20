package com.aplikasi.ADMIN.Pemesanan_Costumer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.aplikasi.ADMIN.LAPORAN.Laporan_Pesanan;
import com.aplikasi.ADMIN.R;
import com.aplikasi.ADMIN.Tabel.help_antrian;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class adapter_pemesanan extends ArrayAdapter<help_data_pemesanan> {
    private static Context context;
    LayoutInflater inflater;
    ArrayList<help_data_pemesanan> arrayListPemesanan;

    public adapter_pemesanan(Pemesanan context, ArrayList<help_data_pemesanan> arrayListPemesanan) {
        super(context, R.layout.pemesanan_costumer_list, arrayListPemesanan);
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.arrayListPemesanan = arrayListPemesanan;
    }

    @Override
    public int getCount() {
        return arrayListPemesanan.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setfilter(ArrayList<help_data_pemesanan>productAll){
        arrayListPemesanan=new ArrayList<help_data_pemesanan>();
        arrayListPemesanan.addAll(productAll);
        notifyDataSetChanged();

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        View view = inflater.inflate(R.layout.pemesanan_costumer_list, viewGroup, false);
        TextView user = view.findViewById(R.id.user_pemesanan);
        TextView tgl = view.findViewById(R.id.tgl_pemesanan);
        TextView byk = view.findViewById(R.id.byk_pemesanan);
        TextView jns = view.findViewById(R.id.jns_pemesanan);
        TextView ket = view.findViewById(R.id.ket_pemesanan);
        Button hapus_pemesan=view.findViewById(R.id.hapus_pemesanan);

        user.setText(arrayListPemesanan.get(position).getNama_user());
        tgl.setText(arrayListPemesanan.get(position).getTanggal());
        byk.setText(arrayListPemesanan.get(position).getBanyak_pesanan());
        jns.setText(arrayListPemesanan.get(position).getJenis_pesanan());
        ket.setText(arrayListPemesanan.get(position).getKeterangan());

        hapus_pemesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pemesanan.deleteData(arrayListPemesanan.get(position).getId());
            }
        });

        return view;
    }
}

package com.aplikasi.ADMIN.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplikasi.ADMIN.ANTRIAN.Data_Antrian;
import com.aplikasi.ADMIN.ANTRIAN.Simpan_Antrian;
import com.aplikasi.ADMIN.ANTRIAN.product;
import com.aplikasi.ADMIN.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  {

    private Context mCtx;
    private List<product> productList;



    public ProductAdapter(Data_Antrian mCtx, List<product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;

    }




    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.pesanan_list, parent, false);
        return new ProductAdapter.ProductViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        final product product = productList.get(position);

        holder.id.setText(product.getId());
        holder.tanggal.setText(product.getTanggal());
        holder.costumer.setText(product.getCostumer());
        holder.produk.setText(product.getProduk());
        holder.quan.setText(product.getQuantity());
        holder.nm_bhn.setText(product.getNama_bahan());
        holder.lbr_bhn.setText(product.getLebar_bahan());
        holder.tgi_bhn.setText(product.getTinggi_bahan());
        holder.lbr_cetak.setText(product.getLebar_cetak());
        holder.tgi_cetak.setText(product.getTinggi_cetak());
        holder.byk_bhn.setText(product.getBanyak_bahan());
        holder.nm_plt.setText(product.getNama_plat());
        holder.satuan.setText(product.getSatuan());
        holder.tot_hg.setText(product.getTotal_harga());
        holder.jwb.setText(product.getJawab());
        holder.sts.setText(product.getStatus());
        holder.simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpan_antrian(position);
            }
        });
        holder.delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, "https://berkah-andalas06.000webhostapp.com/Laporan_Antrian/Hapus_Antrian.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if (response.equalsIgnoreCase("Data Terhapus")) {

                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                            mCtx);
                                    alertDialogBuilder.setTitle("Sukses");
                                    alertDialogBuilder
                                            .setMessage("Data Berhasil Dihapus")
                                            .setCancelable(false)
                                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    productList.remove(position);
                                                    notifyItemRemoved(position);
                                                    notifyItemRangeChanged(position, productList.size());
                                                }
                                            });
                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();


                                } else {
                                    Toast.makeText(mCtx, "Data Gagal DiHapus", Toast.LENGTH_SHORT).show();
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(mCtx, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda", Toast.LENGTH_LONG).show();
                            }
                        }

                ){
                    @Override
                    protected Map<String, String> getParams() {

                        Map<String, String> params = new HashMap();
                        params.put("id_pesanan", product.getId());
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                requestQueue.add(request);
            }
        });
    }

    private void simpan_antrian(final int position) {

        Intent intent = new Intent(mCtx, Simpan_Antrian.class);
        intent.putExtra("id", productList.get(position).getId());


        mCtx.startActivity(intent);
    }






    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void setfilter(List<product>productAll){
        productList=new ArrayList<>();
        productList.addAll(productAll);
        notifyDataSetChanged();

    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView  id,tanggal, costumer, produk, nm_bhn,nm_plt,quan,  byk_bhn,
                satuan, tot_hg, jwb, sts,lbr_bhn,tgi_bhn,lbr_cetak,tgi_cetak;
        Button edt, delet, simpan;

        public ProductViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.pesanan_id);
            tanggal = itemView.findViewById(R.id.tanggal);
            costumer = itemView.findViewById(R.id.costumer);
            produk = itemView.findViewById(R.id.produk);
            nm_bhn = itemView.findViewById(R.id.nama_bahan);
            nm_plt = itemView.findViewById(R.id.nama_plat);
            quan = itemView.findViewById(R.id.quantity);
            lbr_bhn = itemView.findViewById(R.id.lb_bhn);
            tgi_bhn = itemView.findViewById(R.id.tg_bhn);
            lbr_cetak = itemView.findViewById(R.id.lb_ctk);
            tgi_cetak = itemView.findViewById(R.id.tg_ctk);
            byk_bhn = itemView.findViewById(R.id.banyak_bahan);
            satuan = itemView.findViewById(R.id.satuan);
            tot_hg = itemView.findViewById(R.id.total_harga);
            jwb = itemView.findViewById(R.id.jwb);
            sts = itemView.findViewById(R.id.status);
            //edt = itemView.findViewById(R.id.edit);
            delet = itemView.findViewById(R.id.hapus);
            simpan = itemView.findViewById(R.id.kirim);


        }


    }
}





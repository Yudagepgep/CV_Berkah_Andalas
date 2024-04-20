package com.aplikasi.ADMIN.ANTRIAN;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplikasi.ADMIN.Adapter.ProductAdapter;
import com.aplikasi.ADMIN.R;
import com.aplikasi.ADMIN.Tabel.Tabel_Antrian;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class Data_Antrian extends AppCompatActivity  {
    static List<product> productList;
    RecyclerView recyclerView;
    private static ProductAdapter adapter;
    private static ProgressDialog mProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__antrian);

        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        adapter = new ProductAdapter(Data_Antrian.this, productList);


                Tampil_Antrian();
            }




        private void Tampil_Antrian() {
            mProgressDialog = new ProgressDialog(Data_Antrian.this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);
            mProgressDialog.setProgressNumberFormat(null);
            mProgressDialog.setProgressPercentFormat(null);
            mProgressDialog.show();
        String URL_PRODUCTS = "https://berkah-andalas06.000webhostapp.com/Laporan_Antrian/Semua_Pesanan.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("Hasil");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject product = jsonArray.getJSONObject(i);
                                productList.add(new product(
                                        product.getString("id_pesanan"),
                                        product.getString("tanggal"),
                                        product.getString("nama_lengkap"),
                                        product.getString("nama_produk"),
                                        product.getString("nama_bahan"),
                                        product.getString("nama_plat"),
                                        product.getString("quantity"),
                                        product.getString("lebar_bahan"),
                                        product.getString("tinggi_bahan"),
                                        product.getString("lebar_cetak"),
                                        product.getString("tinggi_cetak"),
                                        product.getString("banyak_bahan"),
                                        product.getString("satuan"),
                                        product.getString("total_harga"),
                                        product.getString("penanggung_jawab"),
                                        product.getString("status")


                                ));

                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                mProgressDialog.dismiss();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Data_Antrian.this, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                final List<product> filterdataList = filter(productList, s);
                adapter.setfilter(filterdataList);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.tb_antrian) {
            startActivity(new Intent(this, Tabel_Antrian.class));
        } else if (item.getItemId()==R.id.logout) {
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


    private List<product> filter(List<product> hi, String s) {
        s = s.toLowerCase();
        final List<product> filterdataList = new ArrayList<>();
        for (product data : hi) {
            final String text = data.getCostumer().toLowerCase();
            if (text.startsWith(s)) {
                filterdataList.add(data);
            }
        }
        return filterdataList;

    }


}

package com.aplikasi.ADMIN.LAPORAN;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplikasi.ADMIN.Adapter.Laporan_Adapter;
import com.aplikasi.ADMIN.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class Laporan_Pesanan extends AppCompatActivity {

    public static Context context;
    public static ArrayList<help_laporan> arrayListproduct ;
    static ListView listView;
    private static Laporan_Adapter adapter;
    private static ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laporan_pesanan);
        context=this;
        listView = findViewById(R.id.list);
        arrayListproduct = new ArrayList<>();
        adapter = new Laporan_Adapter(Laporan_Pesanan.this, arrayListproduct);
        Tampil_Laporan();
    }


    @Override
    protected void onStart() {
        //loadproducts();
        super.onStart();
    }
    public static void Tampil_Laporan() {
        mProgressDialog = new ProgressDialog( context);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgress(0);
        mProgressDialog.setProgressNumberFormat(null);
        mProgressDialog.setProgressPercentFormat(null);
        mProgressDialog.show();
        String URL_PRODUCTS = "https://berkah-andalas06.000webhostapp.com/Laporan_Detail/Tampil_Laporan_Detail.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        arrayListproduct.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Hasil");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject help_laporan = jsonArray.getJSONObject(i);
                                    arrayListproduct.add(new help_laporan(
                                            help_laporan.getString("id"),
                                            help_laporan.getString("tanggal"),
                                            help_laporan.getString("nama_lengkap"),
                                            help_laporan.getString("nama_produk"),
                                            help_laporan.getString("nama_bahan"),
                                            help_laporan.getString("nama_plat"),
                                            help_laporan.getString("quantity"),
                                            help_laporan.getString("lebar_bahan"),
                                            help_laporan.getString("tinggi_bahan"),
                                            help_laporan.getString("lebar_cetak"),
                                            help_laporan.getString("tinggi_cetak"),
                                            help_laporan.getString("banyak_bahan"),
                                            help_laporan.getString("satuan"),
                                            help_laporan.getString("total_harga"),
                                            help_laporan.getString("penanggung_jawab"),
                                            help_laporan.getString("status")


                                    ));


                                    listView.setAdapter(adapter);
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
                        Toast.makeText(context, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();

                    }
                });

        Volley.newRequestQueue(context).add(stringRequest);
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
                final ArrayList<help_laporan> filterdataList=filter(arrayListproduct,s);
                adapter.setfilter(filterdataList);
                return true;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

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

    private ArrayList<help_laporan> filter(ArrayList<help_laporan>hi, String s){
        s=s.toLowerCase();
        final ArrayList<help_laporan>filterdataList=new ArrayList<>();
        for (help_laporan data:hi){
            final String text=data.getCostumer().toLowerCase();
            if (text.startsWith(s)){
                filterdataList.add(data);
            }
        }
        return filterdataList;
    }
    public static void deleteData(final String id) {
        StringRequest request = new StringRequest(Request.Method.POST, "https://berkah-andalas06.000webhostapp.com/Laporan_Detail/Hapus_Detail.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Data Terhapus")) {
                            Toast.makeText(context, "Data Berhasil DiHapus", Toast.LENGTH_SHORT).show();
                            Tampil_Laporan();


                        } else {
                            Toast.makeText(context, "Data Gagal DiHapus", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap();
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);



    }

}






package com.aplikasi.ADMIN.Produk;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.aplikasi.ADMIN.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

public class Data_Produk extends AppCompatActivity {

    public static Context context;
    public static ArrayList<help_Produk> arrayListProduk ;
    static ListView listView;
    private static Adapter_Produk adapter;
    private static ProgressDialog mProgressDialog;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.data_produk);

        context=this;
        listView = findViewById(R.id.list_produk);
        arrayListProduk = new ArrayList<>();
        adapter = new Adapter_Produk(Data_Produk.this, arrayListProduk);
        Tampil_Produk();
    }

    private static void Tampil_Produk() {
        mProgressDialog = new ProgressDialog( context);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgress(0);
        mProgressDialog.setProgressNumberFormat(null);
        mProgressDialog.setProgressPercentFormat(null);
        mProgressDialog.show();
        String URL_PRODUCTS = "https://berkah-andalas06.000webhostapp.com/Produk/Tampil_Produk_All.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        arrayListProduk.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Hasil");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                arrayListProduk.add(new help_Produk(

                                        data.getString("id_produk"),
                                        data.getString("nama_produk"),
                                        data.getString("tinggi_cetak"),
                                        data.getString("lebar_cetak"),
                                        data.getString("id_bahan"),
                                        data.getString("id_plat")


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
    public static void DeletProduk(final String id_produk) {
        StringRequest request = new StringRequest(Request.Method.POST, "https://berkah-andalas06.000webhostapp.com/Produk/Hapus_Produk.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Data Terhapus")) {
                            Toast.makeText(context, "Data Berhasil DiHapus", Toast.LENGTH_SHORT).show();
                            Tampil_Produk();


                        } else {
                            Toast.makeText(context, "Data Gagal DiHapus", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, " Jaringan Tidak Ada \n Mohon Periksa Kembali Jaringan Anda" , Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap();
                params.put("id_produk", id_produk);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);




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
        menuInflater.inflate(R.menu.tambah_produk, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                final ArrayList<help_Produk> filterdataList=filter(arrayListProduk,s);
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
        }else if (item.getItemId() == R.id.tmb_data_produk){
            startActivity(new Intent(this, Main_Tambah_Produk.class));
        }

        return true;
    }

    private ArrayList<help_Produk> filter(ArrayList<help_Produk>hi, String s){
        s=s.toLowerCase();
        final ArrayList<help_Produk>filterdataList=new ArrayList<>();
        for (help_Produk data:hi){
            final String text=data.getNama_produk().toLowerCase();
            if (text.startsWith(s)){
                filterdataList.add(data);
            }
        }
        return filterdataList;
    }

}

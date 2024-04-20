package com.aplikasi.ADMIN.Pemesanan_Costumer;

import androidx.appcompat.app.AppCompatActivity;
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
import com.aplikasi.ADMIN.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pemesanan extends AppCompatActivity {
    public static Context context;
    public static ArrayList<help_data_pemesanan> arrayListPemesanan ;
    static ListView listView;
    private static adapter_pemesanan adapter;
    private static ProgressDialog mProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        context=this;
        listView = findViewById(R.id.list_pemesanan);
        arrayListPemesanan = new ArrayList<>();
        adapter = new adapter_pemesanan(Pemesanan.this, arrayListPemesanan);
        Tampil_Pemesanan();
    }

    private static void Tampil_Pemesanan() {
        mProgressDialog = new ProgressDialog( context);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgress(0);
        mProgressDialog.setProgressNumberFormat(null);
        mProgressDialog.setProgressPercentFormat(null);
        mProgressDialog.show();
        String URL_PRODUCTS = "https://berkah-andalas06.000webhostapp.com/pemesanan_costumer/pemesanan.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        arrayListPemesanan.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Hasil");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject Data = jsonArray.getJSONObject(i);
                                arrayListPemesanan.add(new help_data_pemesanan(
                                        Data.getString("id_pemesanan"),
                                        Data.getString("nama_user"),
                                        Data.getString("tanggal"),
                                        Data.getString("banyak_pesanan"),
                                        Data.getString("jenis_pesanan"),
                                        Data.getString("keterangan")



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
    public static void deleteData(final String id) {
        StringRequest request = new StringRequest(Request.Method.POST, "https://berkah-andalas06.000webhostapp.com/pemesanan_costumer/Hapus_Pemesanan.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Data Terhapus")) {
                            Toast.makeText(context, "Data Berhasil DiHapus", Toast.LENGTH_SHORT).show();
                            Tampil_Pemesanan();


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
                params.put("id_pemesanan", id);
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
                final ArrayList<help_data_pemesanan> filterdataList=filter(arrayListPemesanan,s);
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

    private ArrayList<help_data_pemesanan> filter(ArrayList<help_data_pemesanan>hi, String s){
        s=s.toLowerCase();
        final ArrayList<help_data_pemesanan>filterdataList=new ArrayList<>();
        for (help_data_pemesanan data:hi){
            final String text=data.getNama_user().toLowerCase();
            if (text.startsWith(s)){
                filterdataList.add(data);
            }
        }
        return filterdataList;
    }
    }

